package org.modelio.module.javadesigner.commands;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.dialog.DialogManager;
import org.modelio.module.javadesigner.editor.EditorManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.report.ReportManager;
import org.modelio.module.javadesigner.report.ReportModel;
import org.modelio.module.javadesigner.reverse.Reversor;
import org.modelio.module.javadesigner.utils.BrowserLauncher;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class Edit extends DefaultModuleCommandHandler {
    private Set<NameSpace> elementsToEdit = new HashSet<> ();


    /**
     * No command for Component (inherits from Class)
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        this.elementsToEdit.clear ();
        
        for (MObject element : selectedElements) {
            NameSpace producingParent = JavaDesignerUtils.getNearestNameSpace (element);
            if (producingParent == null) {
                return false;
            } else {
                if (!(producingParent instanceof Component) &&
                        !(producingParent instanceof Package) &&
                        !(producingParent instanceof Artifact)) {
                    this.elementsToEdit.add (producingParent);
                } else {
                    return false;
                }
            }
        }
        
        boolean useExternalEdition = "true".equalsIgnoreCase (module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.USEEXTERNALEDITION));
        if (useExternalEdition) {
            return (selectedElements.size () == 1);
        } else {
            return (selectedElements.size () != 0);
        }
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        boolean useExternalEdition = "true".equalsIgnoreCase (module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.USEEXTERNALEDITION));
        
        try {
            JavaDesignerUtils.initCurrentGenRoot (this.elementsToEdit);
        } catch (InterruptedException e) {
            return;
        }
        
        if (useExternalEdition) {
            String editor = module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.EXTERNALEDITORCOMMANDLINE);
        
            if (editor != null && !editor.isEmpty ()) {
                for (NameSpace element : this.elementsToEdit) {
                    File targetJavaFile = JavaDesignerUtils.getFilename (element, module);
                    try {
                        BrowserLauncher.openExternalEditor (editor, targetJavaFile);
                    } catch (Exception e) {
                        String message = Messages.getString ("Error.ExternalEditionError", targetJavaFile, editor);
                        DialogManager.openError (Messages.getString ("Error.ExternalEditionTitle"), message);
                    }
                }
        
                ReportModel report = ReportManager.getNewReport ();
                // Launch the reverse when edition ends
                Reversor reversor = new Reversor (module, report);
        
                reversor.update (this.elementsToEdit, EditorManager.getInstance ());
        
                ReportManager.showGenerationReport (report);
            } else {
                String message = Messages.getString ("Error.ExternalEditionNoEditor");
                DialogManager.openError (Messages.getString ("Error.ExternalEditionTitle"), message);
            }
        } else {
            for (NameSpace element : this.elementsToEdit) {
                EditorManager.getInstance ().open (element, module);
            }
        }
        
        JavaDesignerUtils.setProjectGenRoot (null);
    }

    /**
     * This method precizes if a command has to be desactivated. If the command has to be displayed (which means that
     * the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on
     * specific constraints that are specific to the MDAC.
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        // Use a temp storage to avoid concurrent modifications
        Set<NameSpace> tempList = new HashSet<> (this.elementsToEdit);
        
        for (NameSpace element : tempList) {
            NameSpace producingParent = JavaDesignerUtils.getProducingNameSpace (element);
            if (producingParent != null && !JavaDesignerUtils.isExtern(producingParent)) {
                // Update the Set content
                this.elementsToEdit.remove (element);
                this.elementsToEdit.add (producingParent);
            } else {
                return false;
            }
        }
        
        try {
            JavaDesignerUtils.initCurrentGenRoot (this.elementsToEdit);
        } catch (InterruptedException e) {
            return false;
        }
        
        // Gray the command if the file doesn't exist
        for (NameSpace element : this.elementsToEdit) {
            File targetJavaFile = JavaDesignerUtils.getFilename (element, module);
            if (!targetJavaFile.exists ()) {
                return false;
            }
        }
        
        JavaDesignerUtils.setProjectGenRoot (null);
        return true;
    }

}
