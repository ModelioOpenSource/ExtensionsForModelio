package org.modelio.module.javadesigner.commands.creation;

import java.util.List;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Manifestation;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class CreateAntArtifact extends DefaultModuleCommandHandler {
    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        return (selectedElements.size () != 0);
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession ();
        try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.CreateAntArtifact"))) {
            IUmlModel model = session.getModel ();
            boolean result = true;
        
            for (MObject element : selectedElements) {
                if (element instanceof NameSpace) {
                    NameSpace owner = (NameSpace) element;
                    try {
                        Artifact jarArtifact = model.createArtifact ("", owner, null);
                        ModelUtils.addStereotype(jarArtifact, JavaDesignerStereotypes.JARFILE);
        
                        // Create a manifestation link towards the owner
                        Manifestation manif = model.createManifestation ();
                        manif.setOwner (jarArtifact);
                        manif.setUtilizedElement (owner);
        
                        model.getDefaultNameService().setDefaultName(jarArtifact, owner.getName ());
        
                        if (jarArtifact != null) {
                            // Create a deployment diagram below the Artifact
                            DeploymentDiagram deploymentDiagram = model.createDeploymentDiagram (Messages.getString ("Gui.Command.CreateAntArtifact.diagramName", jarArtifact.getName ()), jarArtifact, null);
        
                            if (deploymentDiagram != null) {
                                // Unmask the manifestation link into the deployment diagram
                                try (IDiagramHandle rep = module.getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(deploymentDiagram)) {
        
                                    IDiagramDG diagramDG = rep.getDiagramNode();
                                    diagramDG.setProperty("ARTIFACT_REPMODE", "IMAGE");
        
                                    rep.unmask (jarArtifact, 100, 100);
                                    rep.unmask (owner, 300, 100);
        
                                    rep.unmask (manif, 200, 100);
        
                                    rep.save();
        
                                    rep.close();
                                }
                                // Open the diagram editor
                                module.getModuleContext().getModelioServices().getEditionService().openEditor(deploymentDiagram);
                            }
                        }
                    } catch (ExtensionNotFoundException e) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JARFILE)); //$NON-NLS-1$
                        result = false;
                    }
                }
            }
        
            // An error has occurred: commit or rollback of the transaction
            if (result) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    /**
     * The command is active for if the current package is the root package
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        boolean result = true;
        
        // Not available on libraries
        for (MObject element : selectedElements) {
            if (ModelUtils.isLibrary(element)) {
                result = false;
                break;
            }
        }
        return result;
    }

}
