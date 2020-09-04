package org.modelio.module.javadesigner.commands.javadoc;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.javadoc.JavadocManager;
import org.modelio.vcore.smkernel.mapi.MObject;

public class VisualizeJavaDoc extends DefaultModuleCommandHandler {
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
        JavadocManager generator = new JavadocManager(module);
        
        for (MObject element : selectedElements) {
            generator.visualizeDoc((NameSpace) element);
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
        JavadocManager javadocManager = new JavadocManager(module);
        
        // Gray the command if the file doesn't exist
        for (MObject element : selectedElements) {
            if (!javadocManager.isJavaDocExists ((NameSpace) element)) {
                return false;
            }
        }
        return true;
    }

}
