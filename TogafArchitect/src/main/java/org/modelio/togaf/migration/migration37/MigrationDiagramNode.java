package org.modelio.togaf.migration.migration37;

import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.impl.TogafArchitectModule;

public class MigrationDiagramNode {
    private AbstractDiagram diagram;

    private ModelElement element;

    private Rectangle position;

    public MigrationDiagramNode(String data) {
        String[] datas = data.split(":");
        if (datas.length == 4) {
            this.diagram = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().findElementById(AbstractDiagram.class, datas[1]);
            this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().findElementById(ModelElement.class, datas[2]);
        
            String posString = datas[3].substring(datas[3].indexOf("{")+1, datas[3].indexOf("}"));
            String[] pos = posString.split("&");
            if(pos.length == 4){
               this.position = new Rectangle(Integer.valueOf(pos[0]), Integer.valueOf(pos[1]),Integer.valueOf(pos[2]), Integer.valueOf(pos[3]));
            }
        }
    }

    public AbstractDiagram getDiagram() {
        return this.diagram;
    }

    public ModelElement getElement() {
        return this.element;
    }

    public Rectangle getPosition() {
        return this.position;
    }

}
