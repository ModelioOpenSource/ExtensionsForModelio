package org.modelio.module.javadesigner.commands.creation;

import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class CreatePlugin extends DefaultModuleCommandHandler {
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
        try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.CreatePlugin"))) {
            IUmlModel model = session.getModel ();
            boolean result = true;
        
            for (MObject element : selectedElements) {
                if (element instanceof Package) {
                    Package currentPackage = (Package) element;
                    try {
                        Component createdElement = model.createComponent ("", currentPackage, null);
                        ModelUtils.addStereotype(createdElement, JavaDesignerStereotypes.JAVACOMPONENT);
        
                        model.getDefaultNameService().setDefaultName(createdElement, "Component");
                    } catch (ExtensionNotFoundException e) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVACOMPONENT)); //$NON-NLS-1$
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
     * The command is active for if the current package is the root package The element must not be a model component.
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        boolean result = true;
        
        // "Create java component" button only for Root package
        for (MObject element : selectedElements) {
            if (!(element instanceof Package) || ((Package) element).getOwner() != null) {
                result = false;
                break;
            } else {
                // Not available on libraries
                if (ModelUtils.isLibrary(element)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
