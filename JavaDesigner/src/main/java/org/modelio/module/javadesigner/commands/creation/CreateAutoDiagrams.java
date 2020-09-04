package org.modelio.module.javadesigner.commands.creation;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.autodiagram.IDiagramCreator;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class CreateAutoDiagrams extends DefaultModuleCommandHandler {
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
        try (ITransaction transaction = session.createTransaction("CreateAutoDiagrams")) {
            Set<NameSpace> elementsToGenerate = new HashSet<> ();
            for (MObject element : selectedElements) {
                NameSpace producingParent = JavaDesignerUtils.getNearestNameSpace (element);
                if (producingParent != null) {
                    elementsToGenerate.add (producingParent);
                }
            }
        
            IDiagramService diagramService = module.getModuleContext().getModelioServices().getDiagramService();
            Collection<NameSpace> allComponentsToTreat = JavaDesignerUtils.getAllComponentsToTreat (elementsToGenerate, module);
            for (ModelTree elt : allComponentsToTreat) {
                generateDiagrams(elt, diagramService);
            }
        
            transaction.commit();
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    protected void generateDiagrams(final ModelTree elt, final IDiagramService diagramService) {
        if (elt instanceof Package) {
            IDiagramCreator creator = diagramService.getAutoDiagramFactory().createSubPackageStructureCreator();
            AbstractDiagram diagram = creator.createDiagram(elt);
            if (diagram != null) {
                try (IDiagramHandle diagramHandle = diagramService.getDiagramHandle(diagram)) {
        
                    // no elements means no subpackages
                    IDiagramDG diagramNode = diagramHandle.getDiagramNode();
                    boolean isEmpty = diagramNode.getNodes().isEmpty();
        
                    if (isEmpty) {
                        diagram.delete();
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().info("no SubPackageStructure diagram for " + elt.getName());
                    }
        
                    diagramHandle.close();
                }
            }
        
            creator = diagramService.getAutoDiagramFactory().createDependencyCreator();
            creator.createDiagram(elt);
        } else {
            IDiagramCreator creator = diagramService.getAutoDiagramFactory().createClassStructureCreator();
            creator.createDiagram(elt);
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
