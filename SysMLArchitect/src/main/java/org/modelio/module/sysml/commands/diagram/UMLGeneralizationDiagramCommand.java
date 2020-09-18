/**
 * Java Class : UMLGeneralizationDiagramCommand.java
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
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an UML/SysML Generalization
 * @author ebrosse
 */
@objid ("4b2c0e2f-d712-4628-b5be-66a53ca378e1")
public class UMLGeneralizationDiagramCommand extends DefaultLinkTool {
    @objid ("610890e6-1957-46a7-91b3-55d6e2b2e53d")
    @Override
    public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic targetNode) {
        if ((targetNode != null) && (targetNode.getElement() != null)){
            MObject element = targetNode.getElement();
            return (element.getStatus().isModifiable () && (element instanceof  NameSpace)); 
        }
        return false;
    }

    /**
     * This method accept or refuse the interaction with the diagram for the destination MObject of the link.
     * <p>
     * 
     * This method is called after the user has choosen the origin of the link.<br>
     * If the interaction is allowed the method <code>return new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method <code>return new InteractionStatus(false, "Tooltip message");</code>
     * . In this case the mouse pointer is changed to a "forbiden" icon and the tooltip message is displayed in a
     * tooltip near the mouse pointer<br>
     * The acceptSecondElement method will be caled until the user choose the destination of the link.
     * @joni-public
     * 
     * @param diagramRepresentation the represention of the diagram in which the interction occurs.
     * @param originNode the graphic that is the origin of the link.
     * @param targetNode the graphic the is below the mouse ponter.
     * @return an InteractionStatus that represents the result of the acceptSecondElement method.
     */
    @objid ("2be1e38a-26df-456f-8078-3210d9120c74")
    @Override
    public boolean acceptSecondElement(IDiagramHandle diagramRepresentation, IDiagramGraphic originNode, IDiagramGraphic targetNode) {
        ModelElement target = (ModelElement) targetNode.getElement();  
        
        ModelElement origin = (NameSpace) originNode.getElement();  
        
        if (target instanceof NameSpace) {
        
            if  (!(origin.getClass().equals(target.getClass()) )) 
                return false; 
        
            if (origin.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.BLOCK) 
                    && (!(target.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.BLOCK))))
                return false; 
        
            if (origin.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONSTRAINTBLOCK) 
                    && (!(target.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONSTRAINTBLOCK))))
                return false; 
        
            if (origin.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPE) 
                    && (!(target.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPE))))
                return false; 
        
            return true;
        }
        return false;
    }

    @objid ("066c682f-2ffc-436d-83fa-8cc497c62547")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic originNode, IDiagramGraphic targetNode, LinkRouterKind kind, ILinkPath path) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "UML Generalization"))){
            NameSpace src = (NameSpace) originNode.getElement();
            NameSpace dst = (NameSpace) targetNode.getElement();
        
            Generalization element = session.getModel().createGeneralization();
            element.setName("");
            element.setSubType(src);
            element.setSuperType(dst);
        
            List<IDiagramGraphic> graphics = representation.unmask(element, 0 , 0);
            for (IDiagramGraphic graphic : graphics){
                if (graphic instanceof IDiagramLink){
                    IDiagramLink link = (IDiagramLink) graphic;
                    link.setPath(path);
                }
            }
        
            representation.save();
            representation.close();
            transaction.commit ();
        } catch (InvalidSourcePointException e) {
            SysMLModule.logService.error(e);
        } catch (InvalidPointsPathException e) {
            SysMLModule.logService.error(e);
        } catch (InvalidDestinationPointException e) {
            SysMLModule.logService.error(e);
        }
    }

}
