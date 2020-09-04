package org.modelio.togaf.migration.migration37;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.togaf.impl.TogafArchitectModule;

public class MigrationNoteParser {
    private List<MigrationDiagramNode> diagramNodes;

    private List<MigrationDiagramLink> diagramLinks;

    private List<MigrationInformationFlow> informationsflows;

    private Set<AbstractDiagram> diagrams;

    public MigrationNoteParser() {
        this.diagramNodes = new ArrayList<>();
        this.diagramLinks = new ArrayList<>();
        this.informationsflows = new ArrayList<>();
        this.diagrams = new HashSet<>();
    }

    public void pars(Note note) {
        String data = note.getContent();
        
        if (data.length() > 0) {
            if (data.contains(",")) {
                for (String entry : data.split(",")) {
                    parseEntry(entry);
                }
            } else {
                parseEntry(data);
            }
        }
    }

    private void parseEntry(String entry) {
        if (entry.startsWith("Diagram:") && entry.contains(":node")) {
            MigrationDiagramNode ng = new MigrationDiagramNode(entry);
            if(!this.diagrams.contains(ng.getDiagram())){
                this.diagrams.add(ng.getDiagram());
            }
            this.diagramNodes.add(ng);
        } else if (entry.startsWith("Diagram:") && entry.contains(":link")) {
            MigrationDiagramLink lk = new MigrationDiagramLink(entry);
            if(!this.diagrams.contains(lk.getDiagram())){
                this.diagrams.add(lk.getDiagram());
            }
            this.diagramLinks.add(lk);
         
        } else if (entry.startsWith("InformationFlow")) {
            this.informationsflows.add(new MigrationInformationFlow(entry));
        }
    }

    public List<MigrationDiagramNode> getDiagramNodes() {
        return this.diagramNodes;
    }

    public List<MigrationDiagramLink> getDiagramLinks() {
        return this.diagramLinks;
    }

    public List<MigrationInformationFlow> getInformationsflows() {
        return this.informationsflows;
    }

    public Set<AbstractDiagram> getDiagrams() {
        return this.diagrams;
    }

    public class MigrationInformationFlow {
        private boolean source;

        private InformationFlow flow;

        public MigrationInformationFlow(String data) {
            if(data.contains("InformationTarge")){
                this.source = true;
            }else if(data.contains("InformationSource")){
                this.source = false; 
            }
            
            String id = data.substring(data.indexOf(":") + 1,data.indexOf("]"));
            this.flow = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().findElementById(InformationFlow.class, id);
        }

        public InformationFlow getFlow() {
            return this.flow;
        }

        public boolean isSource() {
            return this.source;
        }

    }

}
