package org.modelio.togaf.migration.migration37;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.impl.TogafArchitectModule;

public class MigrationDiagramLink {
    private AbstractDiagram diagram;

    private ModelElement element;

    private List<Point> path;

    public MigrationDiagramLink(String data) {
        this.path = new ArrayList<>();
        String[] datas = data.split(":");
        if (datas.length == 4) {
            this.diagram = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().findElementById(AbstractDiagram.class, datas[1]);
            this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().findElementById(ModelElement.class, datas[2]);
        
            String pointList = datas[3].substring(datas[3].indexOf("{")+1, datas[3].indexOf("}"));
            for(String point : pointList.split("\\|")){
                String[] pos = point.split("&");
                if(pos.length == 2){
                    this.path.add(new Point(Integer.valueOf(pos[0]),Integer.valueOf(pos[1])));
                 }
            }
          
        }
    }

    public AbstractDiagram getDiagram() {
        return this.diagram;
    }

    public ModelElement getElement() {
        return this.element;
    }

    public List<Point> getPath() {
        return this.path;
    }

    public MigrationDiagramLink(AbstractDiagram diagram, ModelElement element, List<Point> path) {
        this.diagram = diagram;
        this.element = element;
        this.path = path;
    }

}
