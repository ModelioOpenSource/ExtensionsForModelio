package org.modelio.module.javadesigner.commands.reverse;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.report.ReportManager;
import org.modelio.module.javadesigner.report.ReportModel;
import org.modelio.module.javadesigner.reverse.ReverseType;
import org.modelio.module.javadesigner.reverse.Reversor;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class ReverseSources extends DefaultModuleCommandHandler {

    /**
     * The command is displayed if the selected element is the root package.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        boolean result = (selectedElements.size () != 0);
        
        // "Reverse" button only for Root package
        for (MObject element : selectedElements) {
            if (!((element instanceof Package) && ((Package) element).getOwner() == null) &&
                    !JavaDesignerUtils.isAJavaComponent ((ModelElement) element) &&
                    !JavaDesignerUtils.isAModule ((ModelElement) element)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        ReportModel report = ReportManager.getNewReport ();
        
        Reversor reversor = new Reversor (module, report);
        // No confirm box. Date files are not checked.
        reversor.reverseWizard (ReverseType.SOURCE, (NameSpace) selectedElements.get (0));
        
        ReportManager.showGenerationReport (report);
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
