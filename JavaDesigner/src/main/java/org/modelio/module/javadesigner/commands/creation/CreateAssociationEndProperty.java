package org.modelio.module.javadesigner.commands.creation;

import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class CreateAssociationEndProperty extends DefaultModuleCommandHandler {
    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession();
        try(ITransaction transaction = session.createTransaction("CreateAssociationEndProperty")) {
            IUmlModel model = session.getModel();
        
            for(MObject element : selectedElements) {
                if(element instanceof GeneralClass) {
                    GeneralClass currentClass =(GeneralClass) element;
        
                    Association createdAssoc = model.createAssociation(currentClass, currentClass, "");
                    for(AssociationEnd end : createdAssoc.getEnd()) {
                        if(end.getTarget() != null) {
                            ModelUtils.addStereotype(end, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY);
                        }
                    }
                }
            }
        
            transaction.commit();
        } catch(ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVACOMPONENT)); //$NON-NLS-1$
        } catch(Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if(!super.accept(selectedElements, module)) {
            return false;
        }
        for(MObject element : selectedElements) {
            if(!JavaDesignerUtils.isJavaElement(element) ||(element instanceof Component)) {
                return false;
            }
        }
        return(selectedElements.size() != 0);
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed(which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if(!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        boolean result = true;
        
        // Not available on libraries
        for(MObject element : selectedElements) {
            if(ModelUtils.isLibrary(element)) {
                result = false;
                break;
            }
        }
        return result;
    }

}
