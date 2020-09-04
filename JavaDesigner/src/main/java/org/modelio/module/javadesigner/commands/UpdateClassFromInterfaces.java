package org.modelio.module.javadesigner.commands;

import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.javadesigner.automation.InterfaceImplementer;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class UpdateClassFromInterfaces extends DefaultModuleCommandHandler {
    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        for (MObject theElement : selectedElements) {
            if (!JavaDesignerUtils.isJavaElement (theElement)) {
                return false;
            }
            Classifier theClassifier = (Classifier) theElement;
            if (theClassifier.getRealized ().size () == 0) {
                return false;
            }
        }
        return (selectedElements.size () != 0);
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        IModelingSession session = module.getModuleContext().getModelingSession ();
        InterfaceImplementer interfaceManager = new InterfaceImplementer (session);
        try (ITransaction transaction = session.createTransaction ("Update class from interfaces")) {
            boolean hasDoneWork = false;
            for (MObject theElement : selectedElements) {
                Classifier theClassifier = (Classifier) theElement;
        
                boolean newResult = interfaceManager.implementInterfaces (theClassifier);
                hasDoneWork = hasDoneWork || newResult;
            }
        
            if (hasDoneWork) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
        }
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
        return true;
    }

}
