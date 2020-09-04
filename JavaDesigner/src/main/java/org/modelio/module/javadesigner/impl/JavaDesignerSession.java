package org.modelio.module.javadesigner.impl;

import java.io.File;
import java.util.Map;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.api.modelio.model.event.IModelChangeListener;
import org.modelio.api.modelio.model.event.IStatusChangeListener;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.lifecycle.DefaultModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.module.javadesigner.api.ISessionWithHandler;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.custom.CustomFileException;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.editor.EditorManager;
import org.modelio.module.javadesigner.reverse.newwizard.ImageManager;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vbasic.version.Version;

public class JavaDesignerSession extends DefaultModuleLifeCycleHandler implements ISessionWithHandler {
    protected IModelChangeHandler modelChangeHandler = null;

    protected IStatusChangeListener statusChangeHandler = null;

    protected IModelChangeListener modelChangeListener = null;

    public JavaDesignerSession(final JavaDesignerModule module) {
        super (module);
    }

    public static boolean install(final String objingPath, final String mdaplugsPath) throws ModuleException {
        return DefaultModuleLifeCycleHandler.install (objingPath, mdaplugsPath);
    }

    @Override
    public boolean select() throws ModuleException {
        String jdkPath = JavaDesignerUtils.getJDKPath ();
        String customFile = "res" + //$NON-NLS-1$
                File.separator + "custom" + //$NON-NLS-1$
                File.separator + "javaCustomizationFile.xml"; //$NON-NLS-1$

        IModuleUserConfiguration configuration = this.module.getModuleContext().getConfiguration ();
        configuration.setParameterValue (JavaDesignerParameters.ACCESSIBLECLASSES, jdkPath +
                File.separator +
                "jre" + File.separator + "lib" + File.separator + "rt.jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        configuration.setParameterValue (JavaDesignerParameters.ACCESSORSGENERATION, JavaDesignerParameters.AccessorsGenerationMode.Smart.toString ());
        configuration.setParameterValue (JavaDesignerParameters.AUTOMATICALLYOPENJAVADOC, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.COMPILATIONOPTIONS, ""); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.COMPILATIONPATH, "$(Project)/bin"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.CUSTOMIZATIONFILE, customFile);
        configuration.setParameterValue (JavaDesignerParameters.DESCRIPTIONASJAVADOC, "false"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.ERRORONFIRSTWARNING, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATEFINALPARAMETERS, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATEINVARIANTS, "false"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATEPREPOSTCONDITIONS, "false"); //$NON-NLS-1$
        if (System.getProperty ("os.name").startsWith ("Windows")) {
            configuration.setParameterValue (JavaDesignerParameters.EXTERNALEDITORCOMMANDLINE, "notepad.exe"); //$NON-NLS-1$
        }
        configuration.setParameterValue (JavaDesignerParameters.FRIENDLYACCESSORVISIBILITY, "Public"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.FRIENDLYMODIFIERVISIBILITY, "Public"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.FULLNAMEGENERATION, "false"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENDOCPATH, "$(Project)/doc"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATEJAVADOC, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATIONMODE, JavaDesignerParameters.GenerationMode.RoundTrip.toString ());
        configuration.setParameterValue (JavaDesignerParameters.GENERATIONPATH, "$(Project)/src"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.INTERFACEIMPLEMENTATION, JavaDesignerParameters.InterfaceImplementationMode.Ask.toString ());
        configuration.setParameterValue (JavaDesignerParameters.INVARIANTSNAME, "invariant"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.JAVACOMPATIBILITY, JavaDesignerParameters.CompatibilityLevel.Java8.toString ());
        configuration.setParameterValue (JavaDesignerParameters.JARFILEPATH, "$(Project)/bin"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.JAVADOCOPTIONS, "-private"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.JAVAHGENERATIONPATH, "$(Project)/src"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.JDKPATH, jdkPath);
        configuration.setParameterValue (JavaDesignerParameters.PACKAGEJARINRAMC, "false"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.PACKAGESRCINRAMC, "false"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.PRIVATEACCESSORVISIBILITY, JavaDesignerParameters.AccessorVisibility.Public.toString ());
        configuration.setParameterValue (JavaDesignerParameters.PRIVATEMODIFIERVISIBILITY, JavaDesignerParameters.AccessorVisibility.Public.toString ());
        configuration.setParameterValue (JavaDesignerParameters.PROTECTEDACCESSORVISIBILITY, JavaDesignerParameters.AccessorVisibility.Public.toString ());
        configuration.setParameterValue (JavaDesignerParameters.PROTECTEDMODIFIERVISIBILITY, JavaDesignerParameters.AccessorVisibility.Public.toString ());
        configuration.setParameterValue (JavaDesignerParameters.PUBLICACCESSORVISIBILITY, "Public"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.PUBLICMODIFIERVISIBILITY, "Public"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.RETRIEVEDEFAULTBEHAVIOUR, JavaDesignerParameters.RetrieveMode.Ask.toString ());
        configuration.setParameterValue (JavaDesignerParameters.RUNPARAMETERS, ""); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.USEEXTERNALEDITION, "false"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.USEJAVAH, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.ENCODING, "UTF-8"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATEDEFAULTRETURN, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.GENERATEJAVADOC_MARKERS, "true"); //$NON-NLS-1$
        configuration.setParameterValue (JavaDesignerParameters.COMPONENTSUBPATH, "src"); //$NON-NLS-1$
        return super.select ();
    }

    @Override
    public boolean start() throws ModuleException {
        IModuleContext context = this.module.getModuleContext();
        IModelingSession session = context.getModelingSession();
        
        // Remove the metamodelVersion
        Version version = this.module.getVersion ();
        String fullVersion = version.toString();

        // Display the copyright
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info("Modelio/" + this.module.getName() + " " + fullVersion + " - Copyright 2008-2015 Modeliosoft"); //$NON-NLS-1$ //$NON-NLS-2$  //$NON-NLS-3$

        /*
         * Notifications
         */
        this.statusChangeHandler = new StatusChangeHandler();
        session.addStatusListener(this.statusChangeHandler);

        this.modelChangeHandler = new ModelChangeHandler ();
        session.addModelHandler (this.modelChangeHandler);

        this.modelChangeListener = new ModelChangeListener();
        session.addModelListener(this.modelChangeListener);

        // Set the main module parameters if they are empty
        IModuleUserConfiguration configuration = context.getConfiguration();

        String jdkPath = null;

        if (configuration.getParameterValue (JavaDesignerParameters.ACCESSIBLECLASSES).isEmpty ()) {
            // find the JDK path
            jdkPath = JavaDesignerUtils.getJDKPath ();
            configuration.setParameterValue (JavaDesignerParameters.ACCESSIBLECLASSES, jdkPath +
                    File.separator +
                    "jre" + File.separator + "lib" + File.separator + "rt.jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

        if (configuration.getParameterValue (JavaDesignerParameters.ACCESSORSGENERATION).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.ACCESSORSGENERATION, JavaDesignerParameters.AccessorsGenerationMode.Smart.toString ());
        }

        if (configuration.getParameterValue (JavaDesignerParameters.COMPILATIONPATH).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.COMPILATIONPATH, "$(Project)/bin"); //$NON-NLS-1$
        }

        if (configuration.getParameterValue (JavaDesignerParameters.JAVACOMPATIBILITY).isEmpty()) {
            configuration.setParameterValue (JavaDesignerParameters.JAVACOMPATIBILITY, JavaDesignerParameters.CompatibilityLevel.Java7.toString ());
        }

        if (configuration.getParameterValue (JavaDesignerParameters.GENDOCPATH).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.GENDOCPATH, "$(Project)/doc"); //$NON-NLS-1$
        }

        if (configuration.getParameterValue (JavaDesignerParameters.GENERATIONMODE).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.GENERATIONMODE, JavaDesignerParameters.GenerationMode.RoundTrip.toString ());
        }

        if (configuration.getParameterValue (JavaDesignerParameters.GENERATIONPATH).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.GENERATIONPATH, "$(Project)/src"); //$NON-NLS-1$
        }

        if (configuration.getParameterValue (JavaDesignerParameters.INTERFACEIMPLEMENTATION).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.INTERFACEIMPLEMENTATION, JavaDesignerParameters.InterfaceImplementationMode.Ask.toString ());
        }

        if (configuration.getParameterValue (JavaDesignerParameters.JARFILEPATH).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.JARFILEPATH, "$(Project)/bin"); //$NON-NLS-1$
        }

        if (configuration.getParameterValue (JavaDesignerParameters.JAVAHGENERATIONPATH).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.JAVAHGENERATIONPATH, "$(Project)/src"); //$NON-NLS-1$
        }

        if (configuration.getParameterValue (JavaDesignerParameters.JDKPATH).isEmpty ()) {
            // find the JDK path if necessary
            if (jdkPath == null) {
                jdkPath = JavaDesignerUtils.getJDKPath ();
            }

            configuration.setParameterValue (JavaDesignerParameters.JDKPATH, jdkPath);
        }

        if (configuration.getParameterValue (JavaDesignerParameters.RETRIEVEDEFAULTBEHAVIOUR).isEmpty ()) {
            configuration.setParameterValue (JavaDesignerParameters.RETRIEVEDEFAULTBEHAVIOUR, JavaDesignerParameters.RetrieveMode.Ask.toString ());
        }

        String customFilePath = configuration.getParameterValue (JavaDesignerParameters.CUSTOMIZATIONFILE);
        // Set a default value if the parameter is empty
        if (customFilePath.isEmpty ()) {
            customFilePath = "res" + //$NON-NLS-1$
                    File.separator + "custom" + //$NON-NLS-1$
                    File.separator + "javaCustomizationFile.xml"; //$NON-NLS-1$
            configuration.setParameterValue (JavaDesignerParameters.CUSTOMIZATIONFILE, customFilePath);
        }

        String encoding = configuration.getParameterValue(JavaDesignerParameters.ENCODING);
        if(encoding == null || encoding.isEmpty() || encoding.contains("_")) {
            configuration.setParameterValue (JavaDesignerParameters.ENCODING, "UTF-8"); //$NON-NLS-1$
        }

        // Load the customization file
        try {
            JavaTypeManager.getInstance().loadCustomizationFile(this.module, customFilePath);
        } catch(CustomFileException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }

        // Init image cache
        String modulePath = configuration.getModuleResourcesPath().toAbsolutePath().toString();
        ImageManager.setModulePath(modulePath);
        return super.start ();
    }

    @Override
    public void stop() throws ModuleException {
        IModuleContext context = this.module.getModuleContext();
        IModelingSession session = context.getModelingSession();
        
        session.removeStatusListener(this.statusChangeHandler);
        session.removeModelHandler (this.modelChangeHandler);
        session.removeModelListener (this.modelChangeListener);
        EditorManager.getInstance ().closeEditors ();

        super.stop ();
    }

    @Override
    public void unselect() throws ModuleException {
        super.unselect ();
    }

    @Override
    public void upgrade(final Version oldVersion, final Map<String, String> oldParameters) throws ModuleException {
        try {
                super.upgrade (oldVersion, oldParameters);
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    @Override
    public IModelChangeHandler getModelChangeHandler() {
        return this.modelChangeHandler;
    }

}
