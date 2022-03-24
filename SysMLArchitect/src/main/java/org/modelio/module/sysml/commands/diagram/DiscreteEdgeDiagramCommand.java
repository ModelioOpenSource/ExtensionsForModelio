/**
 * Java Class : AddDiscreteEdgeDiagramCommand.java
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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a discrete edge
 * @author ebrosse
 */
@objid ("f906ae40-3970-444e-916c-4b239e43dce9")
public class DiscreteEdgeDiagramCommand extends DefaultLinkTool {
    /**
     * Default constructor
     */
    @objid ("19fea02b-7e41-4e67-ab13-e791047c52b9")
    public DiscreteEdgeDiagramCommand() {
        super();
    }

    @objid ("2460718b-a085-4a2d-9b10-fb14acde9139")
    @Override
    public boolean acceptFirstElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (element.getStatus().isModifiable () 
                                                                        && (element instanceof ActivityNode));
    }

    @objid ("65af8170-bdf0-4ff0-93ad-eeecde060aa9")
    @Override
    public boolean acceptSecondElement(IDiagramHandle representation, IDiagramGraphic origin, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (element instanceof ActivityNode) ;
    }

    @objid ("0b316433-e355-4780-aa18-98b4e8368f6e")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic origin, IDiagramGraphic target, LinkRouterKind kind, ILinkRoute path) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Discrete Edge"))){
            ActivityNode originElement = (ActivityNode) origin.getElement();
            ActivityNode targetElement = (ActivityNode) target.getElement();
        
            ActivityEdge edge = SysMLFactory.createDiscreteEdge(originElement, targetElement);
        
            List<IDiagramGraphic> graphics = representation.unmask (edge, 0 ,0);
            for (IDiagramGraphic graphic : graphics){
                IDiagramLink link = (IDiagramLink) graphic;
                if (link != null) {
                    try {
                        link.setRoute (path);
                    } catch (InvalidPointsPathException e) {
                        SysMLModule.logService.error(e);
                    } catch (InvalidSourcePointException e) {
                        SysMLModule.logService.error(e);
                    } catch (InvalidDestinationPointException e) {
                        SysMLModule.logService.error(e);
                    }
                }
            }
            representation.save();
            representation.close();
            transaction.commit ();
        }
    }

}
