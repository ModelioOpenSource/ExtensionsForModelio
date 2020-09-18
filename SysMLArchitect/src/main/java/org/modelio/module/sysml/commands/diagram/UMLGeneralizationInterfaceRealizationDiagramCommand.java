/**
 * Java Class : UMLGeneralizationInterfaceRealizationDiagramCommand.java
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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an UML/SysML Generalization
 * Interface
 * 
 * @author ebrosse
 */
@objid ("c9bc94f0-af1f-45c3-87b7-7ca03de3a82c")
public class UMLGeneralizationInterfaceRealizationDiagramCommand extends DefaultLinkTool {
    @objid ("4d553ca3-688d-40f1-8837-b3d973d13004")
    @Override
    public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic targetNode) {
        return ((targetNode != null) && (targetNode.getElement() != null))
                                                                        && (targetNode.getElement().getStatus().isModifiable())
                                                                        && (targetNode.getElement() instanceof NameSpace);
    }

    @objid ("cbdd5641-eb3d-4df2-bce9-3d062659d68f")
    @Override
    public boolean acceptSecondElement(IDiagramHandle diagramRepresentation, IDiagramGraphic originNode, IDiagramGraphic targetNode) {
        if (targetNode != null) {
            ModelElement target = (ModelElement) targetNode.getElement();
        
            ModelElement origin = (NameSpace) originNode.getElement();
        
            if (origin.equals(target))
                return false;
        
            if (target instanceof NameSpace) {
                
                if (origin instanceof Class && target instanceof Class)
                    return true;
        
                if (origin instanceof Class && target instanceof Interface)
                    return true;
        
                if (origin instanceof Interface && target instanceof Interface)
                    return true;
            }
        }
        return false;
    }

    @objid ("581414bb-0ada-4fa0-b084-75f9c7daa7af")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic originNode, IDiagramGraphic targetNode, LinkRouterKind routerType, ILinkPath path) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try (ITransaction transaction = session
                .createTransaction(I18nMessageService.getString(
                        "Info.Session.Create", ""))) {
            NameSpace src = (NameSpace) originNode.getElement();
            NameSpace dst = (NameSpace) targetNode.getElement();
        
            MObject element = null;
            if ((src instanceof Class) && (dst instanceof Interface)) {
                element = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel()
                        .createInterfaceRealization();
                ((InterfaceRealization) element).setImplementer(src);
                ((InterfaceRealization) element)
                        .setImplemented((Interface) dst);
        
            } else {
        
                element = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel()
                        .createGeneralization();
                ((Generalization) element).setSubType(src);
                ((Generalization) element).setSuperType(dst);
        
            }
        
            element.setName("");
            List<IDiagramGraphic> graphics = representation.unmask(element, 0,
                    0);
            for (IDiagramGraphic graphic : graphics) {
                if (graphic instanceof IDiagramLink) {
                    IDiagramLink link = (IDiagramLink) graphic;
                    link.setPath(path);
                }
            }
        
            representation.save();
            representation.close();
            transaction.commit();
        } catch (InvalidSourcePointException e) {
            SysMLModule.logService.error(e);
        } catch (InvalidPointsPathException e) {
            SysMLModule.logService.error(e);
        } catch (InvalidDestinationPointException e) {
            SysMLModule.logService.error(e);
        }
    }

}
