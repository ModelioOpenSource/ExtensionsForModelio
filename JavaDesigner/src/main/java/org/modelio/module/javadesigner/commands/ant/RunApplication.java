package org.modelio.module.javadesigner.commands.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.dialog.InfoDialogManager;
import org.modelio.module.javadesigner.dialog.JConsoleWithDialog;
import org.modelio.module.javadesigner.execution.ExecutionArgumentDialog;
import org.modelio.module.javadesigner.execution.ExecutionArgumentModel;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.module.javadesigner.utils.ProcessManager;
import org.modelio.vcore.smkernel.mapi.MObject;

public class RunApplication extends DefaultModuleCommandHandler {
    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        boolean result = (selectedElements.size () == 1);
        
        for (MObject element : selectedElements) {
            if (element instanceof ModelElement) {
                ModelElement modelelement = (ModelElement) element;
                if (!modelelement.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
                    result = false;
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        JConsoleWithDialog console = new JConsoleWithDialog(InfoDialogManager.getExecutionDialog());
        
        IModuleUserConfiguration config = module.getModuleContext().getConfiguration();
        
        for (MObject element : selectedElements) {
            Artifact jarFile = (Artifact) element;
            String mainClass = ModelUtils.getFirstTagParameter(jarFile, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS);
            
            try {
                ExecutionArgumentModel executionArguments = ExecutionArgumentDialog.getExecutionArguments(Display.getDefault().getActiveShell(), config.getParameterValue(JavaDesignerParameters.RUNPARAMETERS), "");
                
                File jarFileName = JavaDesignerUtils.getFilename(jarFile, module);
                
                String classPathSeparator = System.getProperty ("os.name").startsWith ("Windows") ? ";" : ":";
                
                // ClassPath
                String classpath = jarFileName + classPathSeparator + config.getParameterValue(JavaDesignerParameters.ACCESSIBLECLASSES);
                
                for (File usedJar : getUsedJar(jarFile, module)) {
                    classpath += classPathSeparator + usedJar.getCanonicalPath();
                }
                
                if (mainClass.isEmpty()) {
                    console.writeError("Execution failed, no Main class found\n\n");
                } else {
                    // Build command
                    String command = "java -classpath \"" + classpath + "\" " + mainClass + " " + executionArguments.getArguments();
                    
                    console.writeInfo("Lauching application\n");
                    console.writeInfo(command + "\n");
                    
                    ProcessManager manager = new ProcessManager(console);
                    /* Anomalie #19:
                     * Description: Don't wait for process to end inside actionPerformed/"AWT Event thread".
                     *              Just run and go.
                     */
                    manager.execute(command, false);
                }
            } catch (InterruptedException e) {
                // Execution canceled, ignore error
            } catch (IOException e) {
                console.writeError("An error occured: " + e.getMessage());
            }
        }
    }

    private List<File> getUsedJar(final Artifact jarFile, final IModule module) {
        List<File> ret = new ArrayList<>();
        
        for (Dependency dep : jarFile.getDependsOnDependency()) {
            ModelElement dependsOnElt = dep.getDependsOn();
            
            if (dependsOnElt.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
                ret.add(JavaDesignerUtils.getFilename(jarFile, module));
            }
        }
        return ret;
    }

    /**
     * This method precizes if a command has to be desactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on
     * specific constraints that are specific to the module.
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        boolean result = true;
        for (MObject element : selectedElements) {
            if (element instanceof ModelElement) {
                ModelElement modelelement = (ModelElement) element;
                if (!modelelement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS)) {
                    result = false;
                }
            } else {
                result = false;
            }
        }
        return result;
    }

}
