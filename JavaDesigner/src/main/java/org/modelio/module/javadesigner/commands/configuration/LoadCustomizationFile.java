package org.modelio.module.javadesigner.commands.configuration;

import java.util.List;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.custom.CustomFileException;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.vcore.smkernel.mapi.MObject;

public class LoadCustomizationFile extends DefaultModuleCommandHandler {
    /**
     * This command is available on the model root only.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        if (selectedElements.size () == 1) {
            MObject selectedElement = selectedElements.get (0);
            return (selectedElement instanceof Package) && ((Package) selectedElement).getOwner() == null;
        }
        return false;
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        // Loading custom file
        IModuleUserConfiguration configuration = module.getModuleContext().getConfiguration ();
        String customFilePath = configuration.getParameterValue (JavaDesignerParameters.CUSTOMIZATIONFILE);
        
        try {
            JavaTypeManager.getInstance ().loadCustomizationFile (module, customFilePath);
        } catch (CustomFileException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error("Unable to load configuration file " + customFilePath);
        }
    }

    @Override
    public boolean isActiveFor(final List<MObject> selectedElements, final IModule module) {
        if (!super.isActiveFor(selectedElements, module)) {
            return false;
        }
        return true;
    }

}
