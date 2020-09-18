/**
 * Java Class : AddItemFlowDiagramCommand.java
 *
 * Description :
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 *
 * @category   Command Diagram
 * @package    org.modelio.module.sysml.gui.diagram
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.commands.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an Item Flow
 * @author ebrosse
 */
@objid ("ded488ae-1b65-4e98-97e4-55621a32d0b3")
public class ItemFlowDiagramCommand extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("d6323c28-e4c5-4052-b4e8-5e9552b0e078")
    public ItemFlowDiagramCommand() {
        super();
    }

    @objid ("e865434f-8782-495f-b0d7-47d4afa893fd")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic link) {
        MObject element = link.getElement();
        return  ((element.getStatus().isModifiable ()) 
                                                                        && (( element instanceof AssociationEnd )
                                                                                || element instanceof ConnectorEnd));
    }

    @objid ("e1ecdbe4-1ff3-4c08-afa4-035de57c0eaa")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic arg1, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Item Flow"))){
            IDiagramLink link = ((IDiagramLink) arg1);
        
            Object[] points  = link.getPath().getPoints().toArray(); 
            Point orignPoint = (Point) points[0];
            Point targetPoint = (Point) points[points.length -1];
            Point currentPoint = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
        
            MObject source = null;
            MObject target = null;
            if (currentPoint.getDistance(orignPoint) > currentPoint.getDistance(targetPoint) ){
                source = link.getFrom().getElement();
                target = link.getTo().getElement();
            }else{
                target = link.getFrom().getElement();
                source = link.getTo().getElement();
            }
        
            SysMLFactory.createItemFlow ((ModelElement) link.getElement(), (UmlModelElement) source, (UmlModelElement) target);
        
            transaction.commit ();
        }
    }

}
