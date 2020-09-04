package org.modelio.module.javadesigner.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.generator.Generator;
import org.modelio.module.javadesigner.report.ReportManager;
import org.modelio.module.javadesigner.report.ReportModel;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class Generate extends DefaultModuleCommandHandler {
    private Set<NameSpace> elementsToGenerate = new HashSet<> ();

    /**
     * This methods authorizes a command to be displayed in a defined context. The commands are displayed, by default,
     * depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(final List<MObject> selectedElements, final IModule module) {
        if (!super.accept(selectedElements, module)) {
            return false;
        }
        this.elementsToGenerate.clear ();
        
        for (MObject element : selectedElements) {
            NameSpace producingParent = JavaDesignerUtils.getNearestNameSpace (element);
            if (producingParent == null) {
                return false;
            } else {
                this.elementsToGenerate.add (producingParent);
            }
        }
        return (selectedElements.size () != 0);
    }

    @Override
    public void actionPerformed(final List<MObject> selectedElements, final IModule module) {
        try {
            JavaDesignerUtils.initCurrentGenRoot (this.elementsToGenerate);
        } catch (InterruptedException e) {
            return;
        }
        
        ReportModel report = ReportManager.getNewReport ();
        
        Collection<NameSpace> baseElements = JavaDesignerUtils.getAllComponentsToTreat (this.elementsToGenerate, module);
        Generator generator = new Generator (baseElements, module);
        generator.generate (report);
        
        ReportManager.showGenerationReport (report);
        
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
        Set<NameSpace> tempList = new HashSet<> (this.elementsToGenerate);
        
        for (NameSpace element : tempList) {
            NameSpace producingParent = JavaDesignerUtils.getProducingNameSpace (element);
            if (producingParent != null && !JavaDesignerUtils.isExtern(producingParent)) {
                // Update the Set content
                this.elementsToGenerate.remove (element);
                this.elementsToGenerate.add (producingParent);
            } else {
                return false;
            }
        }
        return true;
    }

}
