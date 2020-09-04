package org.modelio.module.javadesigner.reverse.javatoxml.binary;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import com.modelio.module.xmlreverse.IReportWriter;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.progress.ProgressBar;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.identification.IdentifierManager;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.StructuralModelUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;

public class XMLGeneratorFromBinary {
    private IdentifierManager identifierManager = new IdentifierManager();

    private ClassLoader classLoader;

    private IReportWriter report;

    /**
     * Virtual root package. Starting point of any lookup.
     */
    private PackageDef root = new PackageDef("");

    public XMLGeneratorFromBinary(final List<File> classpath, final IReportWriter report) {
        this.report = report;
        URL[] urlList = new URL[classpath.size ()];
        int index = 0;
        
        try {
            for (File file : classpath) {
                urlList[index++] = file.toURI ().toURL ();
            }
        } catch (MalformedURLException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
        // Create a new class loader with the directory
        this.classLoader = new URLClassLoader (urlList);
    }

    private StringBuilder generateClassDef(final ClassifierDef element) throws IOException {
        try {
            ProgressBar.setTaskName (Messages.getString ("Info.ProgressBar.Reverse.Parsing", element.getFullQualifiedName ()));
        
            Class<?> lClass = Class.forName (element.getFullQualifiedName (), false, this.classLoader);
            XMLBuffer.model.write (getClassStr (lClass));
            ProgressBar.updateProgressBar (null);
        } catch (NoClassDefFoundError e) {
            // TODO : Probleme si tous les jars ne sont pas fournis
            /*
             * NoClassDefFoundError est appel? si dans la classe ? reverser, on a une operation qui retourne une classe
             * ne faisant pas partie du jar ? reverser ni dans les jars utilis?s. Et dans ce cas, on g?n?re la classe
             * comme undefined, alors que ce n'est pas elle qui pose probleme. Le pire est que si ce n'est pas une
             * classe mais une interface, alors dans ce cas, le reverse n'est pas possible. Exemple en essayant de
             * reverser IModule.java faisant du jar "core".
             */
        
            this.report.addWarning(Messages.getString ("reverse.Binary_class_not_found_title", element.getFullQualifiedName ()), null, Messages.getString ("reverse.Binary_class_not_found", element.getFullQualifiedName (), e.getMessage ()));
        
            XMLBuffer.model.write (getUndefinedClassStr (element, "class"));
            ProgressBar.updateProgressBar (null);
        } catch (ClassNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        } catch (Error e) {
            // impossible to find a class on structural reverse.
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error("Impossible to load class : " +
                    element.getFullQualifiedName () + " ( " +
                    e.getClass ().getName () + ")");
            XMLBuffer.model.write (getUndefinedClassStr (element, "class"));
            ProgressBar.updateProgressBar (null);
            if (e instanceof OutOfMemoryError) {
                throw e;
            }
        }
        return null;
    }

    /**
     * Return a class declaration just from its name, if we don't know all its configuration
     * @param tagName
     */
    private String getUndefinedClassStr(final ClassifierDef element, final String tagName) {
        StringBuilder lBuffer = new StringBuilder ();
        String id = this.identifierManager.defineIdentifier(element);
        lBuffer.append ("<");
        lBuffer.append (tagName);
        lBuffer.append (" id=\""); //$NON-NLS-1$
        lBuffer.append (id);
        lBuffer.append ("\"");
        lBuffer.append (" name=\"" + element.getName () + "\"");
        lBuffer.append (" visibility=\"Public\"");
        lBuffer.append (" is-abstract=\"false\"");
        lBuffer.append (" is-leaf=\"false\"");
        lBuffer.append (">\n");
        
        lBuffer.append ("</");
        lBuffer.append (tagName);
        lBuffer.append (">\n");
        return lBuffer.toString ();
    }

    /**
     * Launch XML file generation
     */
    private void generateXML() throws IOException {
        for (StructuralTree nsDef : this.root.getOwned().values()) {
            generateNameSpaceDef(nsDef);
        }
    }

    private void generateNameSpaceDef(final StructuralTree nsDef) throws IOException {
        if (nsDef instanceof ClassifierDef ) {
            generateClassDef((ClassifierDef) nsDef);
        } else if (nsDef instanceof PackageDef) {
            generatePackageDef((PackageDef) nsDef);
        }
    }

    private void generatePackageDef(final PackageDef element) throws IOException {
        XMLBuffer.model.write ("<package");
        String id = this.identifierManager.defineIdentifier(element);
        XMLBuffer.model.write (" id=\""); //$NON-NLS-1$
        XMLBuffer.model.write (id);
        XMLBuffer.model.write ("\"");
        XMLBuffer.model.write (" name=\""); //$NON-NLS-1$
        XMLBuffer.model.write (element.getFullQualifiedName());
        XMLBuffer.model.write ("\">\n"); //$NON-NLS-1$
        for (StructuralTree ns : element.getOwned().values()) {
            generateNameSpaceDef(ns);
        }
        XMLBuffer.model.write ("</package>\n"); //$NON-NLS-1$
    }

    public void printClasses(final List<String> classList) throws NameCollisionException {
        try {
            XMLBuffer.model.write ("<model>\n");
        
            createStructuralModel (classList);
            generateXML ();
        
            XMLBuffer.model.write ("</model>\n");
        
            finalizeXMLFile();
        } catch (MalformedURLException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    private void createStructuralModel(final List<String> classList) throws NameCollisionException {
        for (String className : classList) {
            int index = className.lastIndexOf (".");
            if (index == -1) {
                // no package part, add the class
                StructuralModelUtils.addClass(className, ClassifierDefKind.UNKNOWN, this.root);
            } else {
                String packageName = className.substring (0, index);
                className = className.substring (index + 1);
        
                // Create the package hierarchy
                PackageDef parentPackage;
                if (!packageName.isEmpty()) {
                    parentPackage = StructuralModelUtils.addPackageHierarchy(packageName, this.root);
                } else {
                    parentPackage = this.root;
                }
                // Add the class under its package
                StructuralModelUtils.addClass(className, ClassifierDefKind.UNKNOWN, parentPackage);
            }
        }
    }

    private String getParametersStr(final Method pMethod) {
        StringBuilder lBuffer = new StringBuilder ();
        Class<?>[] pParameters = pMethod.getParameterTypes ();
        String lTypeName;
        
        int cpt = 0;
        
        for (int i = 0 ; i < pParameters.length ; i++) {
            lTypeName = getTypeStr (pParameters[i]);
        
            lBuffer.append ("<parameter name=\"p" + cpt + "\"");
            lBuffer.append (" multiplicity=\"" +
                    getMultiplicity (pParameters[i]) + "\">\n");
        
            if (pParameters[i].isArray ()) {
                int dimension = getArrayDimension (pParameters[i]);
        
                lBuffer.append ("<tagged-value tag-type=\"type\">\n"); //$NON-NLS-1$
                lBuffer.append ("<tag-parameter>Array</tag-parameter>\n"); //$NON-NLS-1$
                lBuffer.append ("</tagged-value>\n"); //$NON-NLS-1$
        
                if (dimension > 1) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaArrayDimension\">\n"); //$NON-NLS-1$
                    lBuffer.append ("<tag-parameter>"); //$NON-NLS-1$
                    lBuffer.append (dimension); //$NON-NLS-1$
                    lBuffer.append ("</tag-parameter>\n"); //$NON-NLS-1$
                    lBuffer.append ("</tagged-value>\n"); //$NON-NLS-1$
                }
            }
        
            if (isJavaType (lTypeName) || lTypeName.equals ("java.lang.String")) {
                if (lTypeName.equals ("java.lang.String")) {
                    lTypeName = "String";
                }
                lBuffer.append ("<base-type>" + lTypeName + "</base-type>\n");
            } else {
                lBuffer.append ("<class-type>\n");
                lBuffer.append(generateDestination(pParameters[i]));
                lBuffer.append ("</class-type>\n");
            }
            lBuffer.append ("</parameter>");
            cpt++;
        }
        return lBuffer.toString ();
    }

    private String getConstructorParametersStr(final Constructor<?> pMethod) {
        StringBuilder lBuffer = new StringBuilder ();
        Class<?>[] pParameters = pMethod.getParameterTypes ();
        String lTypeName;
        Class<?> realType;
        
        int cpt = 0;
        
        for (int i = 0 ; i < pParameters.length ; i++) {
            lTypeName = getTypeStr (pParameters[i]);
            realType = getType (pParameters[i]);
            lBuffer.append ("<parameter name=\"p" + cpt + "\"");
            lBuffer.append (" multiplicity=\"" +
                    getMultiplicity (pParameters[i]) + "\">\n");
        
            if (pParameters[i].isArray ()) {
                int dimension = getArrayDimension (pParameters[i]);
        
                lBuffer.append ("<tagged-value tag-type=\"type\">\n"); //$NON-NLS-1$
                lBuffer.append ("<tag-parameter>Array</tag-parameter>\n"); //$NON-NLS-1$
                lBuffer.append ("</tagged-value>\n"); //$NON-NLS-1$
        
                if (dimension > 1) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaArrayDimension\">\n"); //$NON-NLS-1$
                    lBuffer.append ("<tag-parameter>"); //$NON-NLS-1$
                    lBuffer.append (dimension); //$NON-NLS-1$
                    lBuffer.append ("</tag-parameter>\n"); //$NON-NLS-1$
                    lBuffer.append ("</tagged-value>\n"); //$NON-NLS-1$
                }
            }
        
            if (isJavaType (lTypeName) || lTypeName.equals ("java.lang.String")) {
                if (lTypeName.equals ("java.lang.String")) {
                    lTypeName = "String";
                }
                lBuffer.append ("<base-type>" + lTypeName + "</base-type>\n");
            } else {
                lBuffer.append ("<class-type>\n");
                lBuffer.append(generateDestination(realType));
                lBuffer.append ("</class-type>\n");
            }
            lBuffer.append ("</parameter>");
            cpt++;
        }
        return lBuffer.toString ();
    }

    private String getReturnTypeStr(final Method pMethod) {
        StringBuilder lBuffer = new StringBuilder ();
        Class<?> type = pMethod.getReturnType ();
        Class<?> realType = getType (type);
        String lTypeName = getTypeStr (type);
        if (!lTypeName.equals ("void")) {
            lBuffer.append ("<return-parameter passing-mode=\"Out\" multiplicity=\"" +
                    getMultiplicity (type) + "\">\n");
        
            if (type.isArray ()) {
                int dimension = getArrayDimension (type);
        
                lBuffer.append ("<tagged-value tag-type=\"type\">\n"); //$NON-NLS-1$
                lBuffer.append ("<tag-parameter>Array</tag-parameter>\n"); //$NON-NLS-1$
                lBuffer.append ("</tagged-value>\n"); //$NON-NLS-1$
        
                if (dimension > 1) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaArrayDimension\">\n"); //$NON-NLS-1$
                    lBuffer.append ("<tag-parameter>"); //$NON-NLS-1$
                    lBuffer.append (dimension); //$NON-NLS-1$
                    lBuffer.append ("</tag-parameter>\n"); //$NON-NLS-1$
                    lBuffer.append ("</tagged-value>\n"); //$NON-NLS-1$
                }
            }
        
            if (isJavaType (lTypeName) || lTypeName.equals ("java.lang.String")) {
                if (lTypeName.equals ("java.lang.String")) {
                    lTypeName = "String";
                }
                lBuffer.append ("<base-type>" + lTypeName + "</base-type>\n");
            } else {
                lBuffer.append ("<class-type>\n");
                lBuffer.append(generateDestination(realType));
                lBuffer.append ("</class-type>\n");
            }
            lBuffer.append ("</return-parameter>");
        }
        return lBuffer.toString ();
    }

    private int getArrayDimension(final Class<?> type) {
        if (type.isArray ()) {
            Class<?> lType = type.getComponentType ();
            return getArrayDimension (lType) + 1;
        } else {
            return 0;
        }
    }

    /**
     * @param i @return
     */
    private String getIsAbstract(final int i) {
        if (Modifier.isAbstract (i)) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * @param i @return
     */
    private String getIsFinal(final int i) {
        if (Modifier.isFinal (i)) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * @param i @return
     */
    private String getIsStatic(final int i) {
        if (Modifier.isStatic (i)) {
            return "true";
        } else {
            return "false";
        }
    }

    private String getShortName(final String pName) {
        int lIndex = pName.lastIndexOf ('$');
        String lRet;
        
        if (lIndex != -1) {
            lRet = pName.substring (lIndex + 1);
        } else {
            lIndex = pName.lastIndexOf ('.');
            if (lIndex != -1) {
                lRet = pName.substring (lIndex + 1);
            } else {
                lRet = pName;
            }
        }
        return lRet;
    }

    private String getClassName(final String pName) {
        String lRet;
        int lIndex;
        
        lIndex = pName.lastIndexOf ('.');
        if (lIndex != -1) {
            lRet = pName.substring (lIndex + 1);
        } else {
            lRet = pName;
        }
        
        lIndex = lRet.lastIndexOf ('$');
        
        if (lIndex != -1) {
            lRet = lRet.replace ('$', '.');
        }
        
        
        if (lRet.endsWith(";")) {
            lRet = lRet.substring(0, lRet.length() - 1);
        }
        return lRet;
    }

    private String getVisibility(final int modifier) {
        if (Modifier.isPublic (modifier)) {
            return "Public";
        } else if (Modifier.isProtected (modifier)) {
            return "Protected";
        } else if (Modifier.isPrivate (modifier)) {
            return "Private";
        } else {
            return "Visibility_Undefined";
        }
    }

    private String getMultiplicity(final Field pField) {
        if (pField.getType ().isArray ()) {
            return "*";
        } else {
            return "1";
        }
    }

    private String getMultiplicity(final Class<?> lClass) {
        if (lClass.isArray ()) {
            return "*";
        } else {
            return "1";
        }
    }

    private Class<?> getType(final Class<?> pType) {
        if (pType.isArray ()) {
            Class<?> lType = pType.getComponentType ();
            return getType (lType);
        } else {
            return pType;
        }
    }

    private boolean isJavaType(final String str) {
        if (str.startsWith ("void") || str.startsWith ("boolean") || str.startsWith ("byte") || str.startsWith ("char") || str.startsWith ("short") || str.startsWith ("int") || str.startsWith ("float") || str.startsWith ("long") || str.startsWith ("double")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
            return true;
        } else {
            return false;
        }
    }

    private StringBuilder generateDestination(final Class<?> aDest) {
        // For arrays, the destination should be the type, not the array itself
        if (aDest.isArray()) {
            return generateDestination (aDest.getComponentType());
        }
        StringBuilder lBuffer = new StringBuilder ();
        lBuffer.append ("<destination");
        lBuffer.append (" refid=\"");
        lBuffer.append (this.identifierManager.declareReferenceIdentifier(aDest));
        lBuffer.append ("\"");
        lBuffer.append (" package=\"");
        if (aDest.getPackage () != null) {
            lBuffer.append (aDest.getPackage ().getName ());
        }
        lBuffer.append ("\"");
        lBuffer.append (" class=\"");
        lBuffer.append (getClassName (aDest.getName ()));
        lBuffer.append ("\"/>\n");
        return lBuffer;
    }

    private StringBuilder generateGeneralization(final Class<?> aBaseClass) {
        StringBuilder lBuffer = new StringBuilder ();
        lBuffer.append ("<generalization>\n");
        lBuffer.append ("<super-type>\n");
        lBuffer.append(generateDestination(aBaseClass));
        lBuffer.append ("</super-type>\n");
        lBuffer.append ("</generalization>\n");
        return lBuffer;
    }

    private String getClassStr(final Class<?> pClass) {
        StringBuilder lBuffer = new StringBuilder ();
        Field[] lFields;
        Constructor<?>[] lConstructors;
        Method[] lMethods;
        Class<?>[] lClasses;
        Class<?>[] lInterfaces;
        String tagName;
        int modifiers = pClass.getModifiers ();
        
        // skip package-info synthetic interface for now
        // TODO : attach annotations found in package-info to the package XML tag
        if ("package-info".equals(pClass.getSimpleName())) {
            return "";
        }
        
        if (pClass.isAnnotation()) {
            // isInterface() returns true for an annotation but it should be reversed as a class
            tagName = "class";
        } else if (pClass.isInterface ()) {
            tagName = "interface";
        } else if (pClass.isEnum ()) {
            tagName = "enumeration"; //$NON-NLS-1$
        } else {
            tagName = "class";
        }
        lBuffer.append ("<" + tagName);
        
        String id = this.identifierManager.defineIdentifier(pClass);
        lBuffer.append (" id=\""); //$NON-NLS-1$
        lBuffer.append (id);
        lBuffer.append ("\"");
        lBuffer.append (" name=\"");
        lBuffer.append (getShortName (pClass.getName ()));
        lBuffer.append ("\"");
        lBuffer.append (" visibility=\"");
        lBuffer.append (getVisibility (modifiers));
        lBuffer.append ("\"");
        lBuffer.append (" is-abstract=\"");
        lBuffer.append (getIsAbstract (modifiers));
        lBuffer.append ("\"");
        lBuffer.append (" is-leaf=\"");
        lBuffer.append (getIsFinal (modifiers));
        lBuffer.append ("\"");
        lBuffer.append (">\n");
        
        // Handle static modifier for classes only, as  interfaces and enums are automatically static.
        if (!pClass.isInterface () && !pClass.isEnum () && !pClass.isAnnotation() && Modifier.isStatic (modifiers)) {
            lBuffer.append ("<tagged-value tag-type=\"JavaStatic\"/>"); //$NON-NLS-1$
        }
        
        // Annotation is a class stereotyped <<JavaAnnotation>>
        if (pClass.isAnnotation()) {
            lBuffer.append ("<stereotype stereotype-type=\"JavaAnnotation\"/>"); //$NON-NLS-1$
        }
        
        // ----------------------------------
        // Inheritances and Implementation :
        // ----------------------------------
        if (pClass.isInterface () && !pClass.isAnnotation()) {
            lInterfaces = pClass.getInterfaces ();
            if (lInterfaces.length > 0) {
                for (int i = 0 ; i < lInterfaces.length ; i++) {
                    Class<?> localClass = lInterfaces[i];
                    if (localClass != null) {
                        if (!localClass.getName ().equals ("java.lang.Object")) {
                            lBuffer.append(generateGeneralization(localClass));
                        }
                    }
                }
            }
        } else if (!pClass.isEnum () && !pClass.isAnnotation()) {
            Class<?> superClass = pClass.getSuperclass ();
            if (superClass != null) {
                if (!superClass.getName ().equals ("java.lang.Object")) {
                    lBuffer.append(generateGeneralization(superClass));
                }
            }
        
            lInterfaces = pClass.getInterfaces ();
            if (lInterfaces.length > 0) {
                for (int i = 0 ; i < lInterfaces.length ; i++) {
                    Class<?> localClass = lInterfaces[i];
                    if (localClass != null &&
                            !localClass.getName ().equals ("java.lang.Object")) {
                        lBuffer.append ("<realization>\n");
                        lBuffer.append ("<implemented-class>\n");
                        lBuffer.append(generateDestination(localClass));
                        lBuffer.append ("</implemented-class>\n");
                        lBuffer.append ("</realization>\n");
                    }
                }
            }
        }
        
        lFields = pClass.getDeclaredFields ();
        for (int i = 0 ; i < lFields.length ; i++) {
            lBuffer.append (getFieldStr (lFields[i]));
        }
        
        lConstructors = pClass.getDeclaredConstructors ();
        for (int i = 0 ; i < lConstructors.length ; i++) {
            lBuffer.append (getConstructorStr (lConstructors[i]));
        }
        
        try {
            lMethods = pClass.getDeclaredMethods ();
            for (int i = 0 ; i < lMethods.length ; i++) {
                lBuffer.append (getMethodStr (lMethods[i]));
            }
        } catch (ClassFormatError e) {
            // Ignore class format errors, some jars such as j2ee contain stubs without implementations...
        }
        
        lClasses = pClass.getDeclaredClasses ();
        for (int i = 0 ; i < lClasses.length ; i++) {
            if (!isAnonymousClass (lClasses[i])) {
                lBuffer.append (getClassStr (lClasses[i]));
            }
        }
        
        lBuffer.append ("</" + tagName + ">\n"); //$NON-NLS-1$
        return lBuffer.toString ();
    }

    private String getMethodStr(final Method pMethod) {
        StringBuilder lBuffer = new StringBuilder ();
        
        int modifiers = pMethod.getModifiers ();
        
        // use getReturnTypeStr, because the J in ReverseJava attend the
        // difference format for return and parameter type
        String lMethodName = pMethod.getName ();
        if (lMethodName.startsWith ("access$")) {
            return "";
        }
        
        lBuffer.append ("<operation name=\"" + pMethod.getName () + "\""); //$NON-NLS-1$//$NON-NLS-2$
        lBuffer.append (" visibility=\"" + getVisibility (modifiers) + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        lBuffer.append (" is-abstract=\"" + getIsAbstract (modifiers) + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        lBuffer.append (" is-class=\"" + getIsStatic (modifiers) + "\""); //$NON-NLS-1$ //$NON-NLS-2$
        lBuffer.append (" final=\"" + getIsFinal (modifiers) + "\""); //$NON-NLS-1$//$NON-NLS-2$
        lBuffer.append (">\n"); //$NON-NLS-1$
        
        if (Modifier.isStrict (modifiers)) {
            lBuffer.append ("<tagged-value tag-type=\"JavaStrict\"/>"); //$NON-NLS-1$
        }
        if (Modifier.isSynchronized (modifiers)) {
            lBuffer.append ("<tagged-value tag-type=\"JavaSynchronized\"/>"); //$NON-NLS-1$
        }
        if (Modifier.isNative (modifiers)) {
            lBuffer.append ("<tagged-value tag-type=\"JavaNative\"/>"); //$NON-NLS-1$
        }
        
        if ("finalize".equals(pMethod.getName ()) && "Protected".equals(getVisibility (modifiers))) { //$NON-NLS-1$ //$NON-NLS-2$
            // this is a destructor
            lBuffer.append ("<stereotype stereotype-type=\"destroy\"/>\n"); //$NON-NLS-1$
        }
        
        Class<?>[] lExceptions = pMethod.getExceptionTypes ();
        for (int i = 0 ; i < lExceptions.length ; i++) {
            lBuffer.append ("<raised-exception>\n"); //$NON-NLS-1$
            lBuffer.append ("<used-class>\n"); //$NON-NLS-1$
            lBuffer.append(generateDestination(lExceptions[i]));
            lBuffer.append ("</used-class>\n"); //$NON-NLS-1$
            //lBuffer.append ("<stereotype stereotype-type=\"throw\"/>\n"); //$NON-NLS-1$
            lBuffer.append ("</raised-exception>\n"); //$NON-NLS-1$
        }
        
        lBuffer.append (getParametersStr (pMethod));
        
        lBuffer.append (getReturnTypeStr (pMethod));
        
        lBuffer.append ("</operation>\n");
        return lBuffer.toString ();
    }

    private String getConstructorStr(final Constructor<?> pConstructor) {
        StringBuilder lBuffer = new StringBuilder ();
        
        // use getReturnTypeStr, because the J in ReverseJava attend the
        // difference format for return and parameter type
        String lMethodName = pConstructor.getName ();
        if (lMethodName.startsWith ("access$")) {
            return "";
        }
        
        lBuffer.append ("<operation name=\"" +
                getShortName (pConstructor.getDeclaringClass ().getName ()) +
                "\"");
        lBuffer.append (" visibility=\"" +
                getVisibility (pConstructor.getModifiers ()) + "\"");
        lBuffer.append (">\n");
        
        Class<?>[] lExceptions = pConstructor.getExceptionTypes ();
        for (int i = 0 ; i < lExceptions.length ; i++) {
            lBuffer.append ("<raised-exception>\n");
            lBuffer.append ("<used-class>\n");
            lBuffer.append(generateDestination(lExceptions[i]));
            lBuffer.append ("</used-class>\n");
            // lBuffer.append ("<stereotype stereotype-type=\"throw\"/>\n");
            lBuffer.append ("</raised-exception>\n");
        }
        
        lBuffer.append (getConstructorParametersStr (pConstructor));
        
        lBuffer.append ("<stereotype stereotype-type=\"create\"/>\n");
        lBuffer.append ("</operation>\n");
        return lBuffer.toString ();
    }

    private boolean isAnonymousClass(final Class<?> pClass) {
        String className = getShortName (pClass.getName ());
        boolean ret;
        
        try {
            Integer.parseInt (className);
            ret = true;
        } catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    private String getFieldStr(final Field pField) {
        StringBuilder lBuffer = new StringBuilder ();
        String lTypeName;
        
        if (!pField.getName ().startsWith ("this$") &&
                !pField.getName ().startsWith ("class$")) {
            lTypeName = getTypeStr (pField.getType ());
            int modifiers = pField.getModifiers ();
            if (isJavaType (lTypeName) || lTypeName.equals ("java.lang.String")) {
                lBuffer.append ("<attribute name=\"" + pField.getName () + "\"");
                lBuffer.append (" visibility=\"" +
                        getVisibility (modifiers) + "\"");
                lBuffer.append (" is-abstract=\"" +
                        getIsAbstract (modifiers) + "\"");
                lBuffer.append (" is-class=\"" +
                        getIsStatic (modifiers) + "\"");
                lBuffer.append (" multiplicity=\"" + getMultiplicity (pField) +
                        "\"");
                lBuffer.append (">\n");
        
                if (Modifier.isFinal (modifiers)) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaFinal\"/>");
                }
                if (Modifier.isVolatile (modifiers)) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaVolatile\"/>");
                }
                if (Modifier.isTransient (modifiers)) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaTransient\"/>");
                }
        
                if (lTypeName.equals ("java.lang.String")) {
                    lTypeName = "String";
                }
        
                lBuffer.append ("<base-type>" + lTypeName + "</base-type>\n");
                lBuffer.append ("</attribute>\n");
            } else {
                lBuffer.append ("<association-end name=\"" + pField.getName () +
                        "\"");
                lBuffer.append (" visibility=\"" +
                        getVisibility (modifiers) + "\"");
                lBuffer.append (" is-abstract=\"" +
                        getIsAbstract (modifiers) + "\"");
                lBuffer.append (" is-class=\"" +
                        getIsStatic (modifiers) + "\"");
                lBuffer.append (" multiplicity-min=\"0\"");
                lBuffer.append (" multiplicity-max=\"" +
                        getMultiplicity (pField) + "\"");
                lBuffer.append (">\n");
        
                if (Modifier.isFinal (modifiers)) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaFinal\"/>");
                }
                if (Modifier.isVolatile (modifiers)) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaVolatile\"/>");
                }
                if (Modifier.isTransient (modifiers)) {
                    lBuffer.append ("<tagged-value tag-type=\"JavaTransient\"/>");
                }
        
                Class<?> type = getType (pField.getType ());
                if (type != null) {
                    lBuffer.append ("<class-type>\n");
                    lBuffer.append(generateDestination(type));
                    lBuffer.append ("</class-type>\n");
                }
                lBuffer.append ("</association-end>\n");
            }
        }
        return lBuffer.toString ();
    }

    private String getTypeStr(final Class<?> pType) {
        StringBuilder lBuffer = new StringBuilder ();
        
        if (pType.isArray ()) {
            Class<?> lType = pType.getComponentType ();
            lBuffer.append (getTypeStr (lType));
        } else {
            lBuffer.append (pType.getName ());
        }
        return lBuffer.toString ();
    }

    /**
     * Finalize the XML file by giving it the list of undefined identifiers
     * @throws IOException
     */
    private void finalizeXMLFile() throws IOException {
        GeneratorUtils.generateExternalReferences(this.identifierManager.getUndefinedIdentifiers());
        GeneratorUtils.generateReportList();
        
        XMLBuffer.close();
    }

}
