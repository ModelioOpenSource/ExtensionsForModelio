package org.modelio.module.javadesigner.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerProperties;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.dialog.modelselector.ModelExplorer;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaDesignerUtils {
    public static final String BOOLEAN = "boolean"; // $NON-NLS-1$

    public static final String BYTE = "byte"; // $NON-NLS-1$

    public static final String CHAR = "char"; // $NON-NLS-1$

    public static final String DATE = "date"; // $NON-NLS-1$

    public static final String DOUBLE = "double"; // $NON-NLS-1$

    public static final String FLOAT = "float"; // $NON-NLS-1$

    public static final String INT = "int"; // $NON-NLS-1$

    public static final String INTEGER = "integer"; // $NON-NLS-1$

    public static final String LONG = "long"; // $NON-NLS-1$

    public static final String SHORT = "short"; // $NON-NLS-1$

    public static final String STRING = "string"; // $NON-NLS-1$

    public static final String UNDEFINED = "undefined"; // $NON-NLS-1$

    private static String projectGenRoot = null;

    private static Stereotype abstractAnnotationStereotype;

    /**
     * This operation returns the absolute filename of the namespace
     * @return The absolute filename
     */
    public static File getFilename(final NameSpace element, final IModule module) {
        IModuleContext moduleContext = module.getModuleContext();
        StringBuilder result = new StringBuilder ();
        if (element instanceof Package) {
            result.append (getGenerationPath (element, module));
            if (isRootPackage(element)) {
                return new File (result.toString ());
            }
            result.append (File.separatorChar);
            File directory = getDirectory (moduleContext.getModelingSession(), element);
            if (!directory.toString ().isEmpty ()) {
                result.append (directory);
                result.append (File.separatorChar);
            }
            result.append (getJavaName (element).replace ('.', File.separatorChar));
            if (isPackageAnnotated ((Package) element)) {
                result.append (File.separatorChar);
                result.append ("package-info.java"); //$NON-NLS-1$
            }
        } else if (element instanceof Component && isAJavaComponent(element)) {
            result.append (getGenerationPath (element, module));
            return new File (result.toString ());
        } else if (element instanceof Artifact && element.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
            Artifact jarFile = (Artifact) element;
            String jarFilePath = jarFile.getFileName().trim();
        
            if (jarFilePath.endsWith(".jar")) {
                jarFilePath = jarFilePath.substring(0, jarFilePath.length() - 4);
            }
        
            if (jarFilePath.isEmpty()) {
                jarFilePath = JavaDesignerUtils.getJavaName(jarFile);
            }
        
            if (!new File(jarFilePath).isAbsolute()) {
                result.append (moduleContext.getConfiguration().getParameterValue (JavaDesignerParameters.JARFILEPATH));
            }
        
            result.append (File.separatorChar);
            File directory = getDirectory (moduleContext.getModelingSession(), element);
            if (!directory.toString ().isEmpty ()) {
                result.append (directory);
                result.append (File.separatorChar);
            }
            result.append (jarFilePath);
            result.append (".jar"); //$NON-NLS-1$
        } else if (element instanceof Artifact && element.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVARESOURCE)) {
            result.append (getGenerationPath (element, module));
            result.append (File.separatorChar);
            File directory = getDirectory (moduleContext.getModelingSession(), element);
            if (!directory.toString ().isEmpty ()) {
                result.append (directory);
                result.append (File.separatorChar);
            }
            result.append (getJavaName (element));
        } else {
            result.append (getGenerationPath (element, module));
            result.append (File.separatorChar);
            File directory = getDirectory (moduleContext.getModelingSession(), element);
            if (!directory.toString ().isEmpty ()) {
                result.append (directory);
                result.append (File.separatorChar);
            }
            result.append (getJavaName (element).replace ('.', File.separatorChar));
            result.append (".java"); //$NON-NLS-1$
        }
        
        String path = addProjectSpace(module, result.toString ());
        return new File(path);
    }

    private static boolean isRootPackage(final ModelTree element) {
        if (element instanceof Package) {
            ModelTree owner = element.getOwner();
            if (owner == null || owner instanceof Project) {
                return true;
            }
        }
        return false;
    }

    /**
     * This operation returns the absolute JavaDoc filename of the namespace
     * @return The absolute JavaDocfilename
     */
    public static File getJavaDocFilename(final NameSpace element, final IModule module) {
        StringBuilder result = new StringBuilder ();
        result.append (getJavaDocGenerationPath (element, module));
        result.append (File.separatorChar);
        result.append ("index.html"); //$NON-NLS-1$
        return new File (result.toString ());
    }

    /**
     * Get the Java name for a model element. It can be:
     * - an empty string when the element is tagged {JavaNoPackage}.
     * - the content of the {JavaName} tagged value if not empty.
     * - the UML name.
     * @param element The element to get the name from.
     * @return the Java name for the given element.
     */
    public static String getJavaName(final ModelElement element) {
        if (element == null) {
            return "";
        }
        
        // {JavaNoPackage} elements have no name.
        if (element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PACKAGE_JAVANOPACKAGE)) {
            return "";
        }
        
        // if {JavaName} isn't empty, return it
        String javaName = ModelUtils.getFirstTagParameter (element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME);
        if (!javaName.isEmpty()) {
            return javaName;
        }
        
        // Default case, return the UML name
        return element.getName ();
    }

    /**
     * This operation returns the generation path for the element.
     */
    public static File getJavaDocGenerationPath(final NameSpace element, final IModule module) {
        String path = ""; //$NON-NLS-1$
        NameSpace currentElement = element;
        
        while ((currentElement != null) && (!isAJavaComponent (currentElement))) {
            currentElement = (NameSpace) currentElement.getOwner ();
        }
        
        if (currentElement == null) {
            // The first element is not in a Plugin
            path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENDOCPATH);
        
        } else {
            // Retrieve the path of the plugin
            path = ModelUtils.getFirstTagParameter (currentElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH);
            if (!path.contentEquals ("")) { //$NON-NLS-1$
                // TODO should be an MDAC option
                path = path.concat (File.separatorChar +
                        "doc" + File.separatorChar); //$NON-NLS-1$
            } else {
                path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENDOCPATH);
            }
        }
        
        path = addProjectSpace(module, path);
        return new File (path);
    }

    public static File getJavahGenerationPath(final IModule module) {
        String path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.JAVAHGENERATIONPATH);
        path = addProjectSpace(module, path);
        return new File(path);
    }

    /**
     * This operation returns the generation path for the element.
     */
    public static File getGenerationPath(final NameSpace element, final IModule module) {
        String path = ""; //$NON-NLS-1$
        NameSpace currentElement = element;
        
        while ((currentElement != null) && !isAJavaComponent (currentElement) &&
                !isAModule (currentElement)) {
            currentElement = (NameSpace) currentElement.getOwner ();
        }
        
        if (currentElement == null) {
            // The first element is not in a Plugin/module
            path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONPATH);
        } else {
            if (isAJavaComponent (currentElement)) {
                // Retrieve the path of the plugin
                path = ModelUtils.getFirstTagParameter (currentElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH);
                if (!path.equals ("")) { //$NON-NLS-1$
                    String subpath = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.COMPONENTSUBPATH);
                    if (subpath != null && !subpath.isEmpty()) {
                        path = path.concat (File.separatorChar + subpath); //$NON-NLS-1$
                    }
                    path += File.separatorChar;
                } else {
                    path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONPATH);
                }
            } else {
                // Retrieve the path of the module
                path = ModelUtils.getFirstTagParameter (currentElement, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.WORKSPACE);
                if (!path.equals ("")) { //$NON-NLS-1$
                    String subpath = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.COMPONENTSUBPATH);
                    if (!subpath.isEmpty()) {
                        path = path.concat (File.separatorChar + subpath); //$NON-NLS-1$
                    }
                    path += File.separatorChar;
                } else {
                    path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONPATH);
                }
            }
        }
        
        if (projectGenRoot != null && projectGenRoot.length () > 0) {
            if (new File (projectGenRoot).isAbsolute ()) {
                path = projectGenRoot;
            } else {
                path = path + File.separatorChar + projectGenRoot;
            }
        }
        
        path = addProjectSpace(module, path);
        return new File (path);
    }

    /**
     * This operation returns the compilation path for the element.
     */
    public static File getCompilationPath(final NameSpace element, final IModule module) {
        String path = ""; //$NON-NLS-1$
        NameSpace currentElement = element;
        
        while ((currentElement != null) && !isAJavaComponent (currentElement) &&
                !isAModule (currentElement)) {
            currentElement = (NameSpace) currentElement.getOwner ();
        }
        
        if (currentElement == null) {
            // The first element is not in a Plugin
            path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.COMPILATIONPATH);
        
        } else {
            if (isAJavaComponent (currentElement)) {
                // Retrieve the path of the plugin
                path = ModelUtils.getFirstTagParameter (currentElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH);
                if (!path.contentEquals ("")) { //$NON-NLS-1$
                    // TODO should be an MDAC option
                    path = path.concat (File.separatorChar + "bin" + File.separatorChar); //$NON-NLS-1$
                } else {
                    path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.COMPILATIONPATH);
                }
            } else {
                // Retrieve the path of the module
                path = ModelUtils.getFirstTagParameter (currentElement, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.WORKSPACE);
                if (!path.equals ("")) { //$NON-NLS-1$
                    // TODO should be an MDAC option
                    path = path.concat (File.separatorChar +
                            "bin" + File.separatorChar); //$NON-NLS-1$
                } else {
                    path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.COMPILATIONPATH);
                }
            }
        }
        
        path = addProjectSpace(module, path);
        return new File (path);
    }

    /**
     * This service returns the generation root. Its value is: <java_resource_path>/../../java/
     */
    public static File getGenRoot(final IModule module) {
        Path workspace = module.getModuleContext().getProjectStructure().getPath();
        String genroot = workspace + File.separator;
        return new File (genroot);
    }

    /**
     * Return the full name for Package, GeneralClass, Feature and EnumerationLiteral
     */
    public static String getFullJavaName(final IModelingSession session, final ModelElement element) {
        StringBuilder ret = new StringBuilder ();
        
        if (element instanceof Package) {
            if (!(((element instanceof Package) && ((Package) element).getOwner() == null)) && isJavaElement (element)) {
                ModelTree parent = ((Package) element).getOwner ();
                // the full name is stopped by the Plugin element
                if (!isAJavaComponent (parent) && !isAModelComponent (parent) && !isAModule (parent)) {
                    ret.append (getFullJavaName (session, parent));
                }
        
                String packageName = JavaDesignerUtils.getJavaName (element);
                if (packageName.length () != 0) {
                    if (ret.length () > 0) {
                        ret.append ("."); //$NON-NLS-1$
                    }
                    ret.append (packageName);
                }
            }
        } else if (element instanceof TemplateParameter) {
            ret.append(element.getName());
        } else if (element instanceof GeneralClass) {
            ModelTree parent = ((GeneralClass) element).getOwner ();
            // the full name is stopped by the Plugin element
            if (!isAJavaComponent (parent) && !isAModelComponent (parent) &&
                    !isAModule (parent)) {
                ret.append (getFullJavaName (session, parent));
                if (ret.length () > 0) {
                    ret.append ("."); //$NON-NLS-1$
                }
            }
        
            // The name mustn't be added if the element is a plugin, model component or module.
            if (!isAJavaComponent (element) && !isAModelComponent (element) &&
                    !isAModule (element) && isJavaElement (element)) {
                ret.append (JavaDesignerUtils.getJavaName (element));
            }
        
        } else if (element instanceof Operation) {
            ret.append (getFullJavaName (session, ((Operation) element).getOwner ()));
            if (ret.length () > 0) {
                ret.append ("#"); //$NON-NLS-1$
            }
            ret.append (JavaDesignerUtils.getJavaName (element));
        
        } else if (element instanceof Attribute) {
            ret.append (getFullJavaName (session, ((Attribute) element).getOwner ()));
            if (ret.length () > 0) {
                ret.append ("#"); //$NON-NLS-1$
            }
            ret.append (JavaDesignerUtils.getJavaName (element));
        
        } else if (element instanceof AssociationEnd) {
            ret.append (getFullJavaName (session, ((AssociationEnd) element).getSource ()));
            if (ret.length () > 0) {
                ret.append ("#"); //$NON-NLS-1$
            }
            ret.append (JavaDesignerUtils.getJavaName (element));
        
        } else if (element instanceof EnumerationLiteral) {
            ret.append (getFullJavaName (session, ((EnumerationLiteral) element).getValuated ()));
            ret.append ("."); //$NON-NLS-1$
            ret.append (JavaDesignerUtils.getJavaName (element));
        } else if (element instanceof Artifact && element.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVARESOURCE)) {
            ret.append (getFullJavaName (session, ((Artifact) element).getOwner ()));
            if (ret.length () > 0) {
                ret.append ("."); //$NON-NLS-1$
            }
        
            Artifact jarFile = (Artifact) element;
            String jarFilePath = jarFile.getFileName().trim();
        
            if (jarFilePath.isEmpty()) {
                jarFilePath = JavaDesignerUtils.getJavaName(jarFile);
            }
        
            ret.append (jarFilePath);
        }
        return ret.toString ();
    }

    public static boolean isAModule(final ModelElement element) {
        return (element instanceof Component) && (element.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODULE_IMPLEMENTATION) || element.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.JAVA_MODULE));
    }

    /**
     * This operation return this directory of the element from the root Example: p1/p2/p3
     */
    public static File getDirectory(final IModelingSession session, final ModelTree element) {
        ModelTree parent = element.getOwner ();
        String path = ""; //$NON-NLS-1$
        if (parent != null && isJavaElement (parent)) {
            path = getFullJavaName (session, parent).replace (".", File.separator); //$NON-NLS-1$
        }
        return new File (path);
    }

    /**
     * This operation returns true if the element is a ModelComponent.
     * <p>
     * A ModelComponent is a Component stereotyped "ModelComponent"
     */
    public static boolean isAModelComponent(final ModelElement element) {
        return ((element instanceof Component) && (element.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODEL_COMPONENT)));
    }

    /**
     * This operation returns true if the element is a java component.
     * <p>
     * A java component is a Component stereotyped "JavaComponent"
     */
    public static boolean isAJavaComponent(final ModelElement element) {
        return ((element instanceof Component) && (element.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACOMPONENT)));
    }

    public static boolean isPackageAnnotated(final Package currentPackage) {
        boolean result = false;
        if (currentPackage.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.PACKAGE_JAVAANNOTATION) != null) {
            result = true;
        }
        return result;
    }

    /**
     * This service copies a file in another file.
     */
    public static void copyFile(final File source, final File target) {
        if (!source.getAbsolutePath ().contentEquals (target.getAbsolutePath ())) {
            File parentFile = target.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        
            // Delete existing target file
            if (target.exists()) {
                target.delete();
            }
        
            // Copy source to target.
            if (source.isFile()) {
                try (FileInputStream fileInputStream = new FileInputStream (source);
                        FileOutputStream fileOutputStream = new FileOutputStream (target);
                        FileChannel in = fileInputStream.getChannel ();
                        FileChannel out = fileOutputStream.getChannel ()) {
        
                    in.transferTo (0, in.size (), out);
                } catch (Exception e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
                } finally {
                    if (!source.canWrite ()) {
                        target.setReadOnly ();
                    }
                }
            }
        }
    }

    public static Collection<NameSpace> getAllComponentsToTreat(final Collection<NameSpace> elements, final IModule module) {
        HashSet<NameSpace> allElements = new HashSet<> ();
        // Ajout des ?l?ments manifest?s ? la place des artifacts
        for (NameSpace element : elements) {
            if (element instanceof Artifact) {
                Artifact artifact = (Artifact) element;
                for (Manifestation manifestation : artifact.getUtilized ()) {
                    ModelElement manifestedElement = manifestation.getUtilizedElement ();
                    if (manifestedElement instanceof NameSpace) {
                        addElementsToGenerateFor (manifestedElement, allElements);
                    }
                }
            } else {
                addElementsToGenerateFor (element, allElements);
        
                if (element instanceof Package) {
                    allElements.add (element);
                }
        
                if ((element instanceof Component) &&
                        ((Component) element).isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODULE_IMPLEMENTATION)) {
                    Component theModule = (Component) element;
        
                    Set<NameSpace> subElements = new HashSet<> ();
                    for (ModelTree child : theModule.getOwnedElement ()) {
                        if (child instanceof NameSpace) {
                            subElements.add ((NameSpace) child);
                        }
                    }
        
                    allElements.addAll (getAllComponentsToTreat (subElements, module));
                }
            }
        }
        return allElements;
    }

    private static void addElementsToGenerateFor(final MObject element, final HashSet<NameSpace> allElements) {
        if (JavaDesignerUtils.isJavaElement (element)) {
            if (((element instanceof Class) && !(element instanceof Component)) ||
                    (element instanceof Interface) ||
                    (element instanceof Enumeration) ||
                    (element instanceof DataType) ||
                    (element instanceof Package) ||
                    (element instanceof Component && isAJavaComponent((Component) element))) {
                allElements.add ((NameSpace) element);
            }
        }
        
        // Recursivity
        if (element instanceof NameSpace) {
            for (NameSpace component : JavaDesignerUtils.getComponents ((NameSpace) element)) {
                if (!JavaDesignerUtils.isExtern (component) &&
                        !JavaDesignerUtils.isInner (component) &&
                        !JavaDesignerUtils.isInFileGroup (component)) {
                    addElementsToGenerateFor (component, allElements);
                }
            }
        }
    }

    /**
     * This operation returns the components to generate of the parameter
     * @return For a Package
     * <ul>
     * <li>Package
     * <li>Class (but not Component)
     * <li>Plugin (Component stereotyped "JavaComponent")
     * <li>Interface
     * <li>DataType
     * <li>Enumeration
     * <li>Artifact stereotypes "JavaResource"
     * </ul>
     * For a Java Component (Component stereotyped "JavaComponent")
     * <ul>
     * <li>Package
     * <li>Class (but not Component)
     * <li>Interface
     * <li>DataType
     * <li>Enumeration
     * <li>Artifact stereotypes "JavaResource"
     * </ul>
     */
    public static HashSet<NameSpace> getComponents(final NameSpace element) {
        HashSet<NameSpace> components = new HashSet<> ();
        if (element instanceof Package) {
            Package currentPackage = (Package) element;
            for (ModelTree modeltree : currentPackage.getOwnedElement ()) {
                if ((modeltree instanceof Package) ||
                        ((modeltree instanceof Class) && !(modeltree instanceof Component)) ||
                        (JavaDesignerUtils.isAJavaComponent (modeltree)) ||
                        (modeltree instanceof Interface) ||
                        (modeltree instanceof DataType) ||
                        (modeltree instanceof Enumeration) ||
                        ((modeltree instanceof Artifact) && modeltree.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVARESOURCE))) {
                    components.add ((NameSpace) modeltree);
                }
            }
        } else if (JavaDesignerUtils.isAJavaComponent (element)) {
            Component component = (Component) element;
            for (ModelTree modeltree : component.getOwnedElement ()) {
                if ((modeltree instanceof Package) ||
                        ((modeltree instanceof Class) && !(modeltree instanceof Component)) ||
                        (modeltree instanceof Interface) ||
                        (modeltree instanceof DataType) ||
                        (modeltree instanceof Enumeration) ||
                        ((modeltree instanceof Artifact) && modeltree.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVARESOURCE))) {
                    components.add ((NameSpace) modeltree);
                }
            }
        }
        return components;
    }

    /**
     * Identical code in J profile
     * @param element The element to check.
     * @return true if the element has the "JavaExtern", "nocode" or "JavaNoCode" tag
     */
    public static boolean isExtern(final MObject element) {
        boolean result = false;
        if (element instanceof ModelElement) {
            ModelElement modelelement = (ModelElement) element;
            result = (isNoCode (modelelement) || modelelement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTERN));
        }
        return result;
    }

    /**
     * Identical code in J profile
     * @param modelelement The element to check.
     * @return true if the element has the "nocode" or "JavaNoCode" tag
     */
    public static boolean isNoCode(final ModelElement modelelement) {
        return (modelelement.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODELELEMENT_NOCODE) || modelelement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE));
    }

    /**
     * Identical code in J profile
     */
    public static boolean isInFileGroup(final MObject element) {
        if (element instanceof GeneralClass) {
            GeneralClass generalclass = (GeneralClass) element;
            for (ElementImport elementImport : generalclass.getImporting ()) {
                if (elementImport.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAFILEGROUP)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Identical code in J profile
     */
    public static boolean isInner(final MObject element) {
        boolean result = false;
        ModelTree owner = null;
        if (element instanceof DataType) {
            DataType datatype = (DataType) element;
            owner = datatype.getOwner ();
        } else if (element instanceof Enumeration) {
            Enumeration enumeration = (Enumeration) element;
            owner = enumeration.getOwner ();
        } else if (element instanceof Interface) {
            Interface theInterface = (Interface) element;
            owner = theInterface.getOwner ();
        } else if (element instanceof GeneralClass) {
            GeneralClass generalclass = (GeneralClass) element;
            owner = generalclass.getOwner ();
        
        }
        
        if (owner != null && !(owner instanceof Package) &&
                !(owner instanceof Component)) {
            result = true;
        }
        return result;
    }

    /**
     * This operation returns the JavaComponents of the model.
     * <p>
     * A Java Component is a Component stereotyped "JavaComponent" and defined under the root package.
     */
    public static List<Component> getJavaComponents(final IModelingSession session) {
        List<Component> javaComponents = new ArrayList<> ();
        
        // Retrieve all Java Components
        for (ModelTree modeltree : session.findByClass(Component.class)) {
            if (JavaDesignerUtils.isAJavaComponent (modeltree)) {
                javaComponents.add ((Component) modeltree);
            }
        }
        return javaComponents;
    }

    /**
     * This operation update the local property containing the element's generation date.
     */
    public static void updateDate(final IModelingSession session, final NameSpace element, final long time) {
        // Update Reverse date
        ModelUtils.setLocalProperty (session, element, JavaDesignerProperties.JAVA_LASTGENERATED, Long.toString (time));
    }

    /**
     * Check if the given Element has a Java stereotype.
     */
    public static boolean isJavaElement(final MObject element) {
        return element != null && JavaElementIdentificator.isJavaElement (element);
    }

    /**
     * Returns the NameSpace that will be generated as a file. It can be directly the given element, or one of its
     * parent.
     */
    public static NameSpace getProducingNameSpace(final NameSpace theNameSpace) {
        NameSpace element = theNameSpace;
        
        // Take parents for Inner elements
        while ((element != null) && JavaDesignerUtils.isInner (element)) {
            ModelTree parent = element.getOwner ();
            if (parent instanceof NameSpace) {
                element = (NameSpace) parent;
            } else {
                element = null;
                return null;
            }
        }
        
        if (element == null) {
            return null;
        }
        
        if (isInFileGroup (element)) {
            for (ElementImport fileGroup : element.getImporting ()) {
                if (fileGroup.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAFILEGROUP)) {
                    element = fileGroup.getImportingNameSpace ();
                    break;
                }
            }
        }
        
        if (element instanceof Package) {
            // For a package, just check if it can be generated
            if (isExtern (element)) {
                element = null;
            }
        } else {
            // Check if this is a correct Java Element, and can be generated
            if (!JavaDesignerUtils.isJavaElement (element) || isNoCode (element)) {
                element = null;
            }
        }
        return element;
    }

    /**
     * Returns the element if it is a NameSpace, or its nearest parent NameSpace.
     * @param theElement The element from which we will get the owner.
     * @return the nearest NameSpace of the element given as parameter.
     */
    public static NameSpace getNearestNameSpace(final MObject theElement) {
        return FileGeneratorGetter.getInstance ().getNearestNameSpace (theElement);
    }

    /**
     * Return true if the type name corresponds to a predefined UML type name.
     * @param typeName The type to test.
     */
    public static boolean isPredefinedType(final String typeName) {
        return (typeName.equalsIgnoreCase (BOOLEAN)) ||
                        (typeName.equalsIgnoreCase (BYTE)) ||
                        (typeName.equalsIgnoreCase (CHAR)) ||
                        (typeName.equalsIgnoreCase (DATE)) ||
                        (typeName.equalsIgnoreCase (DOUBLE)) ||
                        (typeName.equalsIgnoreCase (FLOAT)) ||
                        (typeName.equalsIgnoreCase (INTEGER)) ||
                        (typeName.equalsIgnoreCase (LONG)) ||
                        (typeName.equalsIgnoreCase (SHORT)) ||
                        (typeName.equalsIgnoreCase (STRING)) ||
                        (typeName.equalsIgnoreCase (UNDEFINED));
    }

    /**
     * Return true if the type name corresponds to a Java base type name.
     * @param typeName The type to test.
     */
    public static boolean isJavaBaseType(final String typeName) {
        return (typeName.equals (BOOLEAN)) || (typeName.equals (BYTE)) ||
                        (typeName.equals (CHAR)) || (typeName.equals (DOUBLE)) ||
                        (typeName.equals (FLOAT)) || (typeName.equals (INT)) ||
                        (typeName.equals (LONG)) || (typeName.equals (SHORT));
    }

    /**
     * Returns the JDK path from the System.
     */
    public static String getJDKPath() {
        String result = ""; //$NON-NLS-1$
        List<String> jdks = JDKFinder.searchForJDKPath ();
        if (jdks.size () > 0) {
            for (String jdk : jdks) {
                File jdkFile = new File (jdk);
                if (jdkFile.exists ()) {
                    result = jdk;
                    if (jdk.contains ("1.6")) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Returns the JDK path from the module parameters.
     * The returned path is absolute, even when the JDK path parameter content is relative.
     */
    public static File getJDKPath(final IModule module) {
        String path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.JDKPATH);
        path = addProjectSpace(module, path);
        return new File(path);
    }

    /**
     * Returns the library path.
     */
    public static String getLibraryPath(final Artifact element, final IModule module) {
        ModelTree owner = element.getOwner ();
        
        while (owner != null && !isRootPackage(owner)) {
            if (isAModule (owner)) {
                return ModelUtils.getFirstTagParameter (owner, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.WORKSPACE);
            }
            owner = owner.getOwner ();
        }
        return JavaDesignerUtils.getGenRoot (module).getAbsolutePath ();
    }

    /**
     * This operation returns true if the "GenerationMode" module parameter is equal to "Round trip".
     * @return boolean
     */
    public static boolean isRoundtripMode(final IModule module) {
        return module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONMODE).contentEquals (JavaDesignerParameters.GenerationMode.RoundTrip.toString ());
    }

    /**
     * This operation returns true if the "GenerationMode" module parameter is equal to "Model driven".
     * @return boolean
     */
    public static boolean isModelDrivenMode(final IModule module) {
        return module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONMODE).contentEquals (JavaDesignerParameters.GenerationMode.ModelDriven.toString ());
    }

    /**
     * This operation returns true if the "GenerationMode" module parameter is equal to "Release".
     * @return boolean
     */
    public static boolean isReleaseMode(final IModule module) {
        return module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONMODE).contentEquals (JavaDesignerParameters.GenerationMode.Release.toString ());
    }

    public static void getBuildTargets(final NameSpace selectedElement, final Set<Artifact> buildTargets) {
        if (selectedElement instanceof Artifact) {
            if (selectedElement.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
                // If this is a build target, add it to the list
                buildTargets.add ((Artifact) selectedElement);
            }
        } else {
            // Look at manifestations
            for (Manifestation manif : selectedElement.getManifesting ()) {
                Artifact artifact = manif.getOwner ();
                getBuildTargets (artifact, buildTargets);
            }
        
            // Look at owner
            ModelTree owner = selectedElement.getOwner ();
            if (owner != null && owner instanceof NameSpace) {
                getBuildTargets ((NameSpace) owner, buildTargets);
            }
        }
    }

    private static Artifact getBuildTarget(final Collection<NameSpace> selectedElements) throws InterruptedException {
        Set<Artifact> buildTargets = new HashSet<> ();
        for (NameSpace selectedElement : selectedElements) {
            getBuildTargets (selectedElement, buildTargets);
        }
        
        // Remove build targets without "JavaSourcesPath" tag
        for (Artifact art : new HashSet<> (buildTargets)) {
            if (!art.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVASOURCESPATH)) {
                buildTargets.remove (art);
            }
        }
        
        if (buildTargets.size () == 1) {
            return buildTargets.iterator ().next ();
        } else if (buildTargets.size () > 1) {
            return ModelExplorer.getBuildTarget (Display.getDefault().getActiveShell(), buildTargets);
        }
        return null;
    }

    public static void setProjectGenRoot(final String projectGenRoot) {
        JavaDesignerUtils.projectGenRoot = projectGenRoot;
    }

    public static String getProjectGenRoot() {
        return JavaDesignerUtils.projectGenRoot;
    }

    public static void initCurrentGenRoot(final Collection<NameSpace> elts) throws InterruptedException {
        Artifact buildTarget = JavaDesignerUtils.getBuildTarget (elts);
        
        if (buildTarget != null) {
            String genRoot = ModelUtils.getFirstTagParameter (buildTarget, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVASOURCESPATH);
            JavaDesignerUtils.setProjectGenRoot (genRoot);
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error("Using \"" + genRoot + "\"");
        } else {
            JavaDesignerUtils.setProjectGenRoot (null);
        }
    }

    public static List<File> getClassPath(final IModule module) {
        List<File> classpath = new ArrayList<> ();
        
        String sep = System.getProperty ("os.name").startsWith ("Windows") ? ";" : ":";
        
        StringTokenizer st = new StringTokenizer (module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.ACCESSIBLECLASSES), sep); //$NON-NLS-1$
        while (st.hasMoreTokens ()) {
            File file = new File (st.nextToken ());
            if (!classpath.contains(file)) {
                classpath.add (file);
            }
        }
        
        for (File file : getRamcClasspath (module)) {
            if (!classpath.contains(file)) {
                classpath.add (file);
            }
        }
        return classpath;
    }

    /**
     * This operation returns the class path for all deployed ramcs
     */
    private static List<File> getRamcClasspath(final IModule module) {
        List<File> classpath = new Vector<> ();
        File compilationPath;
        File completeCompilationPath;
        
        IModelingSession session = module.getModuleContext().getModelingSession ();
        
        // Retrieve all ramc artifact
        for (ModelTree modeltree : session.findByClass(Component.class)) {
            Component component = (Component) modeltree;
            if (component.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODEL_COMPONENT)) { //$NON-NLS-1$
                for (ModelTree obArtifact : component.getOwnedElement (Artifact.class)) {
                    Artifact artifact = (Artifact) obArtifact;
                    if (artifact.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODEL_COMPONENT_ARCHIVE) && ModelUtils.isLibrary(artifact)) { //$NON-NLS-1$
                        compilationPath = JavaDesignerUtils.getCompilationPath (artifact, module);
        
                        completeCompilationPath = new File (compilationPath +
                                File.separator +
                                ".." + File.separator + JavaDesignerUtils.getJavaName (artifact) + File.separator + "bin" + File.separator + JavaDesignerUtils.getJavaName (artifact) + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        if (completeCompilationPath.exists ()) {
                            classpath.add (completeCompilationPath);
                        } else {
                            completeCompilationPath = new File (compilationPath +
                                    File.separator +
                                    ".." + File.separator + JavaDesignerUtils.getJavaName (artifact) + File.separator + "lib" + File.separator + JavaDesignerUtils.getJavaName (artifact) + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            if (completeCompilationPath.exists ()) {
                                classpath.add (completeCompilationPath);
                            } else {
                                completeCompilationPath = new File (JavaDesignerUtils.getGenRoot (module), File.separator +
                                        "lib" + File.separator + JavaDesignerUtils.getJavaName (artifact) + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
                                if (completeCompilationPath.exists ()) {
                                    classpath.add (completeCompilationPath);
                                }
                            }
                        }
                    }
                }
            }
        }
        return classpath;
    }

    public static File getAntGenerationPath(final Artifact element, final IModule module) {
        String path = ""; //$NON-NLS-1$
        NameSpace currentElement = element;
        
        while ((currentElement != null) && !isAJavaComponent (currentElement) &&
                !isAModule (currentElement)) {
            currentElement = (NameSpace) currentElement.getOwner ();
        }
        
        if (currentElement == null) {
            // The first element is not in a Plugin/module
            path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONPATH);
        
        } else {
            if (isAJavaComponent (currentElement)) {
                // Retrieve the path of the plugin
                path = ModelUtils.getFirstTagParameter (currentElement, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH);
                if (!path.equals ("")) { //$NON-NLS-1$
                    path = path += File.separatorChar;
                } else {
                    path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONPATH);
                }
            } else {
                // Retrieve the path of the module
                path = ModelUtils.getFirstTagParameter (currentElement, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.WORKSPACE);
                if (!path.isEmpty()) { //$NON-NLS-1$
                    path = path += File.separatorChar;
                } else {
                    path = module.getModuleContext().getConfiguration().getParameterValue (JavaDesignerParameters.GENERATIONPATH);
                }
            }
        }
        
        if (projectGenRoot != null && projectGenRoot.length () > 0) {
            if (new File (projectGenRoot).isAbsolute ()) {
                path = projectGenRoot;
            } else {
                path = path + File.separatorChar + projectGenRoot;
            }
        }
        
        path = addProjectSpace(module, path);
        return new File (path);
    }

    public static File getAntFileName(final Artifact jarFile, final IModule module) {
        File genRoot = getAntGenerationPath(jarFile, module);
        
        // Use the tagged value
        if (jarFile.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_ANTFILEPATH)) {
            String antFilePath = ModelUtils.getFirstTagParameter(jarFile, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_ANTFILEPATH).trim();
        
            if (!antFilePath.isEmpty()) {
                // Resolve $(GenRoot)
                antFilePath = antFilePath.replace("$(GenRoot)", genRoot.getAbsolutePath());
        
                // Add genroot if necessary
                File antFile = new File(antFilePath);
                if (!antFile.isAbsolute()) {
                    antFile = new File(antFilePath, antFilePath);
                }
        
                return antFile;
            }
        }
        
        // Default choice...
        return new File(genRoot, getJavaName(jarFile) + ".xml");
    }

    private static String addProjectSpace(final IModule module, final String initialPath) {
        String projectSpacePath = module.getModuleContext().getProjectStructure().getPath().toAbsolutePath().toString();
        
        // Check $(Project)
        if (initialPath.startsWith("$(Project)")) {
            return projectSpacePath + initialPath.substring(10);
        
        }
        
        // Add the project space
        if (!new File (initialPath).isAbsolute ()) {
            return projectSpacePath + File.separatorChar + initialPath;
        }
        return initialPath;
    }

    private static void getModelPackageFromJavaName(final IModelingSession session, final String javaFullPackageName, final ModelTree root, final List<Package> result) {
        for (ModelTree mt : root.getOwnedElement()) {
            if (mt instanceof Package || isAModelComponent(mt) || isAJavaComponent(mt)) {
                String stjavaname = JavaDesignerUtils.getFullJavaName(session, mt);
                if (javaFullPackageName.startsWith(stjavaname.toString())) {
                    // mt java name is a prefix of the searched package name, drill
                    // into mt
                    // children to look for a match.
                    getModelPackageFromJavaName(session, javaFullPackageName, mt, result);
                }
                // If name matches, mt is a good result.
                // (this part is after the prefix test in order to have terminal
                // {JavaNoPackage} packages
                // before their parents in the result list.)
                if (mt instanceof Package && stjavaname.equals(javaFullPackageName)) {
                    result.add((Package) mt);
                }
            }
        }
    }

    /**
     * return the package in the model whose name matches with
     * javaFullPackageName. The package model is search under root.
     * 
     * The search take into account {JavaName} and {JavaNoPackage}
     * 
     * Both {JavaName} and model package names can contain '.' (dot)
     * @param session the Modelio session
     * @param javaFullPackageName Java package name. It is a full qualified name (ie java.util)
     * @return package matching jaxb package or null if no matching package
     * found in the model. The package may not be a direct child of
     * owner if JavaNoPackage tag is involved.
     */
    public static List<Package> getModelPackageFromJavaName(final IModelingSession session, final String javaFullPackageName) {
        List<Package> result = new ArrayList<>();
        
        for (MObject root : session.getModel().getModelRoots()) {
            if (root instanceof Project) {
                for (Package rootPackage : ((Project)root).getModel()) {
                    getModelPackageFromJavaName(session, javaFullPackageName, rootPackage, result);
                }
            } else if (root instanceof ModelTree) {
                getModelPackageFromJavaName(session, javaFullPackageName, (ModelTree) root, result);
            }
        }
        
        for (MObject root : session.getModel().getLibraryRoots()) {
            if (root instanceof Project) {
                for (Package rootPackage : ((Project)root).getModel()) {
                    getModelPackageFromJavaName(session, javaFullPackageName, rootPackage, result);
                }
            } else if (root instanceof ModelTree) {
                getModelPackageFromJavaName(session, javaFullPackageName, (ModelTree) root, result);
            }
        }
        return result;
    }

    public static Stereotype getAbstractAnnotationStereotype() {
        if (abstractAnnotationStereotype == null) {
            abstractAnnotationStereotype = JavaDesignerModule.getInstance().getModuleContext().getModelingSession().findElementById(Stereotype.class, "e8ecd0b2-3bf5-49d1-baca-ee1504b9ea22");
        }
        return abstractAnnotationStereotype;
    }

    public static boolean isAnnotationStereotype(final Stereotype stereo) {
        return stereo != null && stereo.getParent() == getAbstractAnnotationStereotype();
    }

    public static String getDate(final ModelElement element) {
        
        return ModelUtils.getLocalProperty (element, JavaDesignerProperties.JAVA_LASTGENERATED);
    }
}
