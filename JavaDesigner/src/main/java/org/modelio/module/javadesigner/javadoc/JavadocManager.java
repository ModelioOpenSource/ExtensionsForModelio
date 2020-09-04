package org.modelio.module.javadesigner.javadoc;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.dialog.DialogManager;
import org.modelio.module.javadesigner.dialog.JConsoleWithDialog;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.BrowserLauncher;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ProcessManager;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavadocManager {
    private IModule module;

    private JConsoleWithDialog console;

    public JavadocManager (final IModule module) {
        this(module, new JConsoleWithDialog(null));
    }

    public JavadocManager (final IModule module, final JConsoleWithDialog console) {
        this.module = module;
        this.console = console;
    }

    public void generateDoc(final List<MObject> selectedElements) {
        // First, check that javadoc.exe is in the right place
        IModuleUserConfiguration config = this.module.getModuleContext().getConfiguration();
        String jdkPath = config.getParameterValue (JavaDesignerParameters.JDKPATH);
        File javadocFile;
        if (System.getProperty ("os.name").startsWith ("Windows")) {
            javadocFile= new File(jdkPath, "bin" + File.separator + "javadoc.exe");
        } else {
            javadocFile= new File(jdkPath, "bin" + File.separator + "javadoc");
        }
        if (!javadocFile.exists()) {
            String errorMsg = "Unable to execute javadoc: ";
            errorMsg += "\"" + javadocFile + "\" not found.\n";
            errorMsg += "Please check your JDK module parameter.";
            
            this.console.writeError(errorMsg);
            
            return;
        }

        // Check the module parameter to open the doc after generation
        boolean automaticallyOpenJavaDoc = "TRUE".equalsIgnoreCase (this.module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.AUTOMATICALLYOPENJAVADOC));
        for (MObject element : selectedElements) {
            // Generate doc
            if (generateDoc((NameSpace) element) == 0) {

                // Open the doc after successful generation
            if (automaticallyOpenJavaDoc) {
                visualizeDoc((NameSpace) element);
            }
        }
    }
    }

    private int generateDoc(final NameSpace source) {
        File targetDocFile = getJavaDocFilename (source);

        this.console.writeInfo("Generating javadoc for " + source.getName() + "\n\n");

        String ClassPathSeparator = System.getProperty ("os.name").startsWith ("Windows") ? ";" : ":";

        IModuleUserConfiguration config = this.module.getModuleContext().getConfiguration();

        String javaDocOptions = config.getParameterValue (JavaDesignerParameters.JAVADOCOPTIONS);

        File javaDocPath = JavaDesignerUtils.getJavaDocGenerationPath(source, this.module);

        File lJDKPath = JavaDesignerUtils.getJDKPath(this.module);
        
        String lCommand = "\"" + lJDKPath + File.separator + "bin" + File.separator + "javadoc\" " + javaDocOptions;

        if (!targetDocFile.getParentFile().exists()) {
            targetDocFile.getParentFile().mkdirs();
        }

        String sourcePath;
        //  Gather generation paths
        File globalSourcePath = JavaDesignerUtils.getGenerationPath(source, this.module);
        if (globalSourcePath.exists()) {
            sourcePath = globalSourcePath.getAbsolutePath();
        } else {
            sourcePath = "";
        }

        // Add all java components paths
        for (Component modeltree : source.getOwnedElement (Component.class)) {
            if (JavaDesignerUtils.isAJavaComponent (modeltree)) {
                File componentPath = JavaDesignerUtils.getGenerationPath (modeltree, this.module);
                if (componentPath.exists()) {
                    sourcePath += sourcePath.isEmpty() ? componentPath : ClassPathSeparator + componentPath.getAbsolutePath();
                }
            }
        }

        // Is there a generation path?
        if (sourcePath.isEmpty()) {
            this.console.writeError("Package doesn't exist, please generate it " + sourcePath + "\n");
            return -1;
        }

        lCommand += " -d \"" + javaDocPath.getAbsolutePath() + "\" ";

        String AccessibleClasses = config.getParameterValue (JavaDesignerParameters.ACCESSIBLECLASSES);

        String JavaDesignerLib = config.getModuleResourcesPath() + File.separator + "bin" + File.separator + "javadesigner.jar";
        lCommand += " -classpath \"" +
                sourcePath +
                ClassPathSeparator +
                AccessibleClasses +
                ClassPathSeparator +
                JavaDesignerLib +
                "\"";

        String packageList = "";

        for (Package pack : getAllPackages(source)) {
            packageList += " " + JavaDesignerUtils.getFullJavaName(this.module.getModuleContext().getModelingSession(), pack);    
        }

        // CHM Mantis 7429
        if (packageList.length() > 1) {
            lCommand += packageList;
        } else {
            // CHM Mantis 7279
            for (ModelTree child : source.getOwnedElement(NameSpace.class)) {
                if (!JavaDesignerUtils.isNoCode(child) && !JavaDesignerUtils.isExtern(child)) {
                    if (child.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACLASS)
                            || child.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADATATYPE)
                            || child.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAENUMERATION)
                            || child.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINTERFACE)) {
                        lCommand += " \"" + sourcePath + "/" + JavaDesignerUtils.getFullJavaName(this.module.getModuleContext().getModelingSession(), child) + ".java\"" ;
                    }
                }
            }
        }

        this.console.writeInfo("Launching Javadoc:\n" + lCommand + "\n\n");

        ProcessManager manager = new ProcessManager(this.console);
        return manager.execute(lCommand, true);
    }

    private Set<Package> getAllPackages(final ModelTree source) {
        HashSet<Package> allElements = new HashSet<> ();

        // Keep only java packages
        if (source instanceof Package && JavaDesignerUtils.isJavaElement(source)) {
            for (ModelTree child : source.getOwnedElement()) {
                // They must contain at least a java Class, Interface, DataType or Enumeration
                if ((child instanceof Class) || (child instanceof Interface) || (child instanceof Enumeration) || (child instanceof DataType)) {
                    if (JavaDesignerUtils.isJavaElement(child)) {
                        allElements.add((Package) source);
                        break;
                    }
                }
            }
        }

        // Recurse on sub packages and components
        for (ModelTree child : source.getOwnedElement()) {
            if ((child instanceof Package) || (child instanceof Component)) {
                if (!JavaDesignerUtils.isNoCode(child) && !JavaDesignerUtils.isExtern(child)) {
                    allElements.addAll(getAllPackages(child));
                }
            }
        }
        return allElements;
    }

    public void visualizeDoc(final NameSpace source) {
        File targetDocFile = getJavaDocFilename (source);

        try {
            BrowserLauncher.openURL (targetDocFile);
        } catch (Exception e) {
            String message = Messages.getString ("Error.JavaDocVisualizationError", targetDocFile);
            DialogManager.openError (Messages.getString ("Error.JavaDocVisualizationTitle"), message);
        }
    }

    public boolean isJavaDocExists(final NameSpace element) {
        File javaDocFile = getJavaDocFilename (element);
        return javaDocFile.exists();
    }

    /**
     * This operation returns the absolute JavaDoc filename of the namespace
     * @return The absolute JavaDocfilename
     */
    private File getJavaDocFilename(final NameSpace element) {
        StringBuilder result = new StringBuilder ();
        result.append (JavaDesignerUtils.getJavaDocGenerationPath (element, this.module));
        result.append (File.separatorChar);
        result.append ("index.html"); //$NON-NLS-1$
        return new File (result.toString ());
    }

}
