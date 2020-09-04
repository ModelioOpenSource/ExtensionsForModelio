package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import com.modelio.module.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.Visibility;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;

/**
 * This class loads a classifier from the class path and populates the
 * structural model with its content. It is designed after SourceAnalyzer and
 * most of the code is copied from BinaryGenerator. These are clues to future
 * refactoring.
 */
public class BinaryAnalyzer {
    /**
     * cache of unknown packages
     */
    private Set<String> unknownPackages = new HashSet<>();

    /**
     * cache of unknown classifiers
     */
    private Set<String> unknownClassifiers = new HashSet<>();

    /**
     * The classpath for the current reverse
     */
    private List<File> classpath;

    /**
     * The class loader build from the classpath used to load classifiers before
     * structural analysis
     */
    private ClassLoader classLoader;

    private IReportWriter report;

    BinaryAnalyzer(final List<File> aClasspath, final IReportWriter report) {
        this.classpath = aClasspath;
        this.report = report;
        
        // Create the class loader from the classpath
        URL[] urlList = new URL[aClasspath.size()];
        int index = 0;
        for (File file : aClasspath) {
            try {
                urlList[index++] = file.toURI().toURL();
            } catch (MalformedURLException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().warning(e);
                report.addWarning(Messages.getString("BinaryAnalyzer.MalformedURLException.title", file.toString()), 
                        null,
                        Messages.getString("BinaryAnalyzer.MalformedURLException.desc", file.toString(), e.getLocalizedMessage()));
        
            }
        }
        
        // Create a new class loader with the directory
        this.classLoader = new URLClassLoader(urlList);
    }

    /**
     * Add a new structural element, provided it can be found in the reverse
     * classpath.
     * @param aFullQualifiedName full qualified name of the new element
     * @return true a new structural has been added
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     */
    boolean addBinaryEntryToStructuralModel(final String aFullQualifiedName, final PackageDef root) throws NameCollisionException {
        if (this.unknownClassifiers.contains(aFullQualifiedName)) {
            return false;
        }
        Class<?> lClass = null;
        String loadableName = aFullQualifiedName;
        do {
            try {
                /*
                 * forName loads only the metaclass description. It doesn't
                 * link (or resolve) the whole class so there is no need for the
                 * reverse classpath to have indirect jar in it. For example
                 * class loader is able to load a class that implements or extends
                 * an element even if the later is not in the classpath provided
                 * the implementing/extending class is.
                 * BUT, Class<?> are nevertheless created for implemented or extended 
                 * classifiers not in the classpath.
                 * 
                 * Class.forName is better than classLoader.loadClass because it doesn't reload already loaded class.
                 */
                lClass = Class.forName(loadableName, false, this.classLoader);
                ClassifierDef cls = defineClassDef(lClass, root, true);
                if (cls == null) {
                    this.unknownClassifiers.add(aFullQualifiedName);
                }
                return cls != null;
            } catch (ClassNotFoundException e) {
                // Class not found in class path
                // It's ok : a class may not be found in the current classpath
                // in this context
            } catch (NoClassDefFoundError e)  {
                // Class found in the class path but unloadable for missing depending class(es)
                // register what is available on the class
                JavaDesignerModule.getInstance().getModuleContext().getLogService().info(e);
        
                ClassifierDef cls = defineClassDef(loadableName, root);
                if (cls == null) {
                    this.unknownClassifiers.add(aFullQualifiedName);
                }
                return cls != null;
            } catch (LinkageError e) {
                // Class found in the class path but unloadable for unexpected reason.
                // register what is available on the class
                JavaDesignerModule.getInstance().getModuleContext().getLogService().warning(e.toString());
                JavaDesignerModule.getInstance().getModuleContext().getLogService().info(e);
        
                
                ClassifierDef cls = defineClassDef(loadableName, root);
                if (cls == null) {
                    this.unknownClassifiers.add(aFullQualifiedName);
                    
                    this.report.addWarning(Messages.getString("BinaryAnalyzer.LinkageError.notfound.title", loadableName), 
                            null,
                            Messages.getString("BinaryAnalyzer.LinkageError.notfound.desc", 
                                    loadableName, 
                                    e.getLocalizedMessage()));
                    
                } else {
                    this.report.addWarning(
                            Messages.getString("BinaryAnalyzer.LinkageError.found.title", loadableName, getMessage(cls.getKind())), 
                            null,
                            Messages.getString("BinaryAnalyzer.LinkageError.found.desc", 
                                    loadableName, 
                                    e.getLocalizedMessage(),
                                    cls.getFullQualifiedName(),
                                    getMessage(cls.getKind())));
                    
                }
                return cls != null;
            }
            // replace the last dot '.' with dollar '$' in case
            // aFullQualifiedName is a nested classifier
            int lastDotPos = loadableName.lastIndexOf('.');
            if (lastDotPos == -1) {
                // no more dot, no more chance
                return false;
            }
            loadableName = loadableName.substring(0, lastDotPos) + '$' + loadableName.substring(lastDotPos + 1);
        } while (true);
    }

    private String getMessage(final ClassifierDefKind kind) {
        return Messages.getString("ClassifierDefKind."+kind.name());
    }

    private ClassifierDef defineClassDef(final String aLoadableClassName, final PackageDef root) {
        String afqn = aLoadableClassName.replace('$', '.');
        ClassifierDef newClass = (ClassifierDef) StructuralModelUtils.getStructuralTree(afqn, root);
        if (newClass != null) {
            return newClass;
        }
        StructuralTree par; // the package that owned aLoadableClassName then the embedding class if nested
        String classparts;
        int lstpnt = aLoadableClassName.lastIndexOf('.');
        if (lstpnt == -1) {
            // No package in aLoadableClassName
            par = root;
            classparts = aLoadableClassName;
        } else {    
            String packageName = aLoadableClassName.substring(0, lstpnt);
            classparts = aLoadableClassName.substring(lstpnt+1, aLoadableClassName.length());
            par = StructuralModelUtils.getStructuralTree(packageName, root);
            if (par == null) {
                try {
                    par = StructuralModelUtils.addPackageHierarchy(packageName, root);
                } catch (NameCollisionException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().warning(e);
                }
            }
        }
        String clazz;
        int dolidx = classparts.indexOf('$');
        int beg = 0;
        while (dolidx != -1) {
            clazz = classparts.substring(beg, dolidx);
            par = StructuralModelUtils.addClass(clazz, ClassifierDefKind.UNKNOWN, par);
            beg = dolidx+1;
            dolidx = classparts.indexOf(beg, '$');
        }
        newClass = StructuralModelUtils.addClass(classparts.substring(beg, classparts.length()), ClassifierDefKind.UNKNOWN, par);
        return newClass;
    }

    private ClassifierDef defineClassDef(final Class<?> aClass, final PackageDef root, final boolean checkClassPath) throws NameCollisionException {
        ClassifierDef newClass = (ClassifierDef) StructuralModelUtils.getStructuralTree(aClass.getCanonicalName(), root);
        if (newClass != null) {
            return newClass;
        }
        if (!checkClassPath || (checkClassPath && isInClassPath(aClass))) {
        
            StructuralTree parent = null;
            // 1) look for enclosing class
            Class<?> enclosing = aClass.getEnclosingClass();
            if (enclosing != null) {
                parent = defineClassDef(enclosing, root, false);
            } else {
                // 2) else look for a package
                Package pack = aClass.getPackage();
                if (pack != null) {
                    parent = StructuralModelUtils.addPackageHierarchy(pack.getName(), root);
                } else {
                    parent = root;
                }
            }
        
            // Choose classifierDef kind
            ClassifierDefKind kind;
            if (aClass.isAnnotation()) {
                kind = ClassifierDefKind.CLASS;
            } else if (aClass.isInterface()) {
                kind = ClassifierDefKind.INTERFACE;
            } else if (aClass.isEnum()) {
                kind = ClassifierDefKind.ENUMERATION;
            } else if (aClass.isPrimitive()) {
                kind = ClassifierDefKind.DATATYPE;
            } else {
                kind = ClassifierDefKind.CLASS;
            }
            
            newClass = StructuralModelUtils.addClass(aClass.getSimpleName(), kind, parent);
            
        
            // Set visibility of classifierDef
            if (Modifier.isPublic(aClass.getModifiers())) {
                newClass.setVisibility(Visibility.PUBLIC);
            } else if (Modifier.isProtected(aClass.getModifiers())) {
                newClass.setVisibility(Visibility.PROTECTED);
            } else if (Modifier.isPrivate(aClass.getModifiers())) {
                newClass.setVisibility(Visibility.PRIVATE);
            } else {
                newClass.setVisibility(Visibility.PACKAGE);
            }
        
            // 3) register all members class and interfaces
            // (because this class defines binary classes in the process of
            // reversing source files,
            // we don't care of private binary stuff)
            for (Class<?> mc : aClass.getDeclaredClasses()) {
                if (!mc.isSynthetic() && !mc.isLocalClass() && !Modifier.isPrivate(mc.getModifiers())) {
                    defineClassDef(mc, root, false);
                }
            }
            // 4) register inherited class and interfaces if they belong to the
            // classpath
            Class<?> base = aClass.getSuperclass();
            if (base != null) {
                newClass.addInherits(defineClassDef(base, root, true));
            }
            for (Class<?> ii : aClass.getInterfaces()) {
                if (!ii.isSynthetic()) {
                    ClassifierDef intrfce = defineClassDef(ii, root, true);
                    if (intrfce != null) {
                        newClass.addInherits(intrfce);
                    }
                }
            }
        }
        return newClass;
    }

    /**
     * Tell if a aFullQualifiedName is a binary package. A binary package is a
     * directory in a jar of the classpath
     * @param aFullQualifiedName a fully qualified java name
     * @return <i>true</i> if a aFullQualifiedName is a binary package.
     */
    boolean isBinaryPackage(final String aFullQualifiedName) {
        if (this.unknownPackages.contains(aFullQualifiedName)) {
            return false;
        }
        String filePath = new StringBuilder(aFullQualifiedName.replace('.', '/')).append("/").toString();
        for (File path : this.classpath) {
            try (JarFile jf = new JarFile(path)) {
                // JarEntry or ZipEntry don't allow to retrieve directory, so
                // iterate on each entry until the path is found
                for (Enumeration<JarEntry> em = jf.entries(); em.hasMoreElements();) {
                    String entry = em.nextElement().toString();
                    if (entry.startsWith(filePath)) {
                        return true;
                    }
                }
                jf.close();
            } catch (IOException e) {
                // Ignore error
            }
        }
        // Memorize that we failed on this one, in order to speed up another request with the same fqn
        this.unknownPackages.add(aFullQualifiedName);
        return false;
    }

    /**
     * Verify that a class is in the classpath
     * @param aClass Class to check
     * @return <i>true</i> if the class is in the class path.
     */
    private boolean isInClassPath(final Class<?> aClass) {
        // getName() keep '$' for inner classifier (not the case for getCanonicalName() )
        String name = aClass.getName();
        if (name == null) {
            return false;
        }
        return isInClassPath(name);
    }

    private boolean isInClassPath(final String aFullQualifiedName) {
        String filePath = new StringBuilder (aFullQualifiedName.replace('.', '/')).append(".class").toString();
        for (File path : this.classpath) {
            try (JarFile jf = new JarFile(path)) {
                if (jf.getJarEntry(filePath) != null) {
                    return true;
                }
                jf.close();
            } catch (IOException e) {
                // Ignore error
            }
        }
        return false;
    }

}
