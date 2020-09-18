/**
 * Java Class : UMLAssociationDiagramCommand.java
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
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an UML/SysML Association
 * @author ebrosse
 */
@objid ("0d0facbc-a700-4add-906a-1d350c58b398")
public class UMLAssociationDiagramCommand extends DefaultLinkTool {
    @objid ("09650535-22f6-4f18-b9da-0e1b13153ee7")
    @Override
    public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
        if ((arg1 != null) && (arg1.getElement() != null)){ 
            MObject element = arg1.getElement();
            return(element.getStatus().isModifiable() &&  (element instanceof Classifier));
        }
        return false;
    }

    @objid ("c40dd1f3-8231-4548-b7c1-a80d0d0fec34")
    @Override
    public boolean acceptSecondElement(IDiagramHandle arg0, IDiagramGraphic arg1, IDiagramGraphic arg2) {
        return ((arg2 != null) && (arg2.getElement() != null) && (arg2.getElement() instanceof Classifier));
    }

    @objid ("3c1f251d-1302-4671-9d3d-3554c7e192c0")
    @Override
    public void actionPerformed(final IDiagramHandle representation, final IDiagramGraphic arg1, final IDiagramGraphic arg2, final LinkRouterKind kind, final ILinkPath path) {
        IModelingSession session =  SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        try (ITransaction transaction = session.createTransaction(I18nMessageService.getString("Info.Session.Create", ""))) {
        
            Classifier source = (Classifier) arg1.getElement();
            Classifier destination = (Classifier) arg2.getElement();
        
            AssociationEnd sourceRole = model.createAssociationEnd();
            sourceRole.setSource(source);
            sourceRole.setTarget(destination);
            sourceRole.setName(destination.getName().toLowerCase());
            sourceRole.setMultiplicityMin("1");
            sourceRole.setMultiplicityMax("1");
        
            AssociationEnd destinationRole = model.createAssociationEnd();
            destinationRole.setMultiplicityMin("0");
            destinationRole.setMultiplicityMax("1");
        
            // Opposite relation must be set for both ends
            destinationRole.setOpposite(sourceRole);
            sourceRole.setOpposite(destinationRole);
        
            // Create the association itself
            Association association = model.createAssociation();
            destinationRole.setAssociation(association);
            sourceRole.setAssociation(association);
        
            List<IDiagramGraphic> graphics = representation.unmask(association, 0, 0);
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
             SysMLModule .logService.error(e);
        } catch (InvalidPointsPathException e) {
             SysMLModule .logService.error(e);
        } catch (InvalidDestinationPointException e) {
             SysMLModule .logService.error(e);
        }
    }

}
