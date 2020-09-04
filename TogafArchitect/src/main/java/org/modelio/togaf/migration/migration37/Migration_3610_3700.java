package org.modelio.togaf.migration.migration37;

import java.util.ArrayList;
import java.util.List;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.migration.migration37.MigrationNoteParser.MigrationInformationFlow;
import org.modelio.vcore.smkernel.mapi.MObject;

public class Migration_3610_3700 {
    private Project migrationRoot;

    public Migration_3610_3700(Project migrationRoot) {
        this.migrationRoot = migrationRoot;
    }

    public void migrate() throws ExtensionNotFoundException {
        IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
        for (BpmnCollaboration collaboration : session.findByClass(BpmnCollaboration.class)) {
            if (session.getModel().getRoot(collaboration).equals(this.migrationRoot)) {
                migrateCollaboration(collaboration);
            }
        }

        for (BpmnProcess process : session.findByClass(BpmnProcess.class)) {
            if (session.getModel().getRoot(process).equals(this.migrationRoot)) {
                migrateProcess(process);
            }
        }
    }

    private void migrateCollaboration(BpmnCollaboration collaboration) throws ExtensionNotFoundException {
        // Add stereotype on Container
        if (collaboration.getCompositionOwner() instanceof Package &&
                !((Package) collaboration.getCompositionOwner()).isStereotyped(TogafArchitectModule.MODULE_NAME, "BusinessOrganizationDomain")) {
            ((Package) collaboration.getCompositionOwner()).addStereotype(TogafArchitectModule.MODULE_NAME, "BusinessOrganizationDomain");
        }

        // Add stereotypes on Process
        for (BpmnParticipant part : collaboration.getParticipants()) {
            if (part.getProcess() != null && !part.getProcess().isStereotyped(TogafArchitectModule.MODULE_NAME, "TogafProcess")) {
                part.getProcess().addStereotype(TogafArchitectModule.MODULE_NAME, "TogafProcess");
            }
        }

        // Parse MigrationNote
        for (Note note : collaboration.getDescriptor()) {
            if (note.getName().equals("Migration37")) {
                MigrationNoteParser parser = new MigrationNoteParser();
                parser.pars(note);

                // Clean Diagram
                for (AbstractDiagram diag : parser.getDiagrams()) {
                    cleanDiagram(parser,diag);
                }

                for (MigrationInformationFlow mflow : parser.getInformationsflows()) {
                    migrateFlow(collaboration, mflow);
                }

                for (MigrationDiagramNode mnode : parser.getDiagramNodes()) {
                    migrateDiagramNode(mnode);
                }

                for (MigrationDiagramLink mlink : parser.getDiagramLinks()) {
                    migrateDiagramLink(mlink);
                }
            }
        }
    }

    private void cleanDiagram(MigrationNoteParser parser, AbstractDiagram diag) {
        try (IDiagramHandle handle = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(diag)){
            for (IDiagramNode node : handle.getDiagramNode().getNodes()) {
                if (node.getElement() == null) {
                    handle.mask(node);
                }else if(node.getElement() instanceof Package && ((Package)node.getElement()).isStereotyped(TogafArchitectModule.MODULE_NAME, "BusinessOrganizationDomain")){
                    // Keep  links on childrens


                    List<List<IDiagramGraphic>> children_links_gr = new ArrayList<>();

                    for(MObject children : node.getElement().getCompositionChildren()){
                        if(children instanceof BpmnCollaboration){
                            for(Dependency dp : ((BpmnCollaboration) children).getImpactedDependency()){
                                List<IDiagramGraphic> graphs = handle.getDiagramGraphics(dp);
                                if(graphs != null && !graphs.isEmpty() && graphs.get(0) instanceof IDiagramLink){
                                    IDiagramLink lk = (IDiagramLink)graphs.get(0);
                                    children_links_gr.add(handle.getDiagramGraphics(dp));
                                    parser.getDiagramLinks().add(new MigrationDiagramLink(diag,dp,lk.getPath().getPoints()));
                                }
                            }

                            for(Dependency dp : ((BpmnCollaboration) children).getDependsOnDependency()){
                                List<IDiagramGraphic> graphs = handle.getDiagramGraphics(dp);
                                if(graphs != null && !graphs.isEmpty() && graphs.get(0) instanceof IDiagramLink){
                                    IDiagramLink lk = (IDiagramLink)graphs.get(0);
                                    children_links_gr.add(handle.getDiagramGraphics(dp));
                                    parser.getDiagramLinks().add(new MigrationDiagramLink(diag,dp,lk.getPath().getPoints()));
                                }
                            }
                        }
                    }
                    handle.mask(node);
                }
            }
        }
    }

    private void migrateFlow(UmlModelElement collaboration, MigrationInformationFlow mflow) {
        if (mflow.getFlow() != null) {
            if (mflow.isSource()) {
                mflow.getFlow().getInformationSource().clear();
                mflow.getFlow().getInformationSource().add(collaboration);
            } else {
                mflow.getFlow().getInformationTarget().clear();
                mflow.getFlow().getInformationTarget().add(collaboration);
            }
        }
    }

    private void migrateDiagramLink(MigrationDiagramLink mlink) {
        if (mlink.getDiagram() != null && mlink.getElement() != null) {
            try (IDiagramHandle handle = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(mlink.getDiagram())) {
                List<IDiagramGraphic> graph = handle.getDiagramGraphics(mlink.getElement());
                if (graph == null || graph.isEmpty()) {
                    graph = handle.unmask(mlink.getElement(), 0, 0);
                }

                if (graph != null && !graph.isEmpty() && graph.get(0) instanceof IDiagramLink && mlink.getPath() != null) {
                    IDiagramLink dglink = (IDiagramLink) graph.get(0);
                    try {
                        dglink.setPath(mlink.getPath());
                    } catch (Exception e) {
                        // e.printStackTrace();
                    }
                }
            }
        }
    }

    private void migrateDiagramNode(MigrationDiagramNode mnode) {
        if (mnode.getDiagram() != null && mnode.getElement() != null) {
            try (IDiagramHandle handle = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(mnode.getDiagram())){
                List<IDiagramGraphic> graph = handle.getDiagramGraphics(mnode.getElement());
                if (graph == null || graph.isEmpty()) {
                    graph = handle.unmask(mnode.getElement(), mnode.getPosition().x, mnode.getPosition().y);
                }

                if (graph != null && !graph.isEmpty() && graph.get(0) instanceof IDiagramNode) {
                    IDiagramNode dgNode = (IDiagramNode) graph.get(0);
                    dgNode.setBounds(mnode.getPosition());
                }
            }
        }
    }

    private void migrateProcess(BpmnProcess process) {
        for (Note note : process.getDescriptor()) {
            if (note.getName().equals("Migration37")) {
                MigrationNoteParser parser = new MigrationNoteParser();
                parser.pars(note);

                // Clean Diagram
                for (AbstractDiagram diag : parser.getDiagrams()) {
                    cleanDiagram(parser,diag);
                }

                for (MigrationInformationFlow mflow : parser.getInformationsflows()) {
                    migrateFlow(process, mflow);
                }

                for (MigrationDiagramNode mnode : parser.getDiagramNodes()) {
                    migrateDiagramNode(mnode);
                }

                for (MigrationDiagramLink mlink : parser.getDiagramLinks()) {
                    migrateDiagramLink(mlink);
                }
            }
        }
    }

}
