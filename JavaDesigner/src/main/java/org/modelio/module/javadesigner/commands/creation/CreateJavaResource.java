package org.modelio.module.javadesigner.commands.creation;

import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class CreateJavaResource extends DefaultModuleCommandHandler {
    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        for (MObject element : selectedElements) {
            if (!JavaDesignerUtils.isJavaElement (element)) {
                return false;
            }
        }
        return (selectedElements.size () != 0);
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession ();
        try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.CreateJavaResource"))) {
            IUmlModel model = session.getModel ();
            boolean result = true;
        
            for (MObject element : selectedElements) {
                if (element instanceof Package) {
                    Package currentPackage = (Package) element;
                    try {
                        Artifact createdElement = model.createArtifact ("", currentPackage, null);
                        ModelUtils.addStereotype(createdElement, JavaDesignerStereotypes.JAVARESOURCE);
        
                        model.getDefaultNameService().setDefaultName(createdElement, "ResourceFile");
                    } catch (ExtensionNotFoundException e) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVARESOURCE)); //$NON-NLS-1$
                        result = false;
                    }
                }
            }
        
            // An error has occured: commit or rollback of the transaction
            if (result) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
    }

    /**
     * The command is always active
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        boolean result = true;
        
        // Not available on model components
        for (MObject element : selectedElements) {
            if (ModelUtils.isLibrary(element)) {
                result = false;
                break;
            }
        }
        return result;
    }

}
