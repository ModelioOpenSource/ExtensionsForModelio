/**
 * Java Class : UMLAggregationDiagramCommand.java
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
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an SysML/UML Aggregation
 * @author ebrosse
 */
@objid ("d71e1bd4-8a1d-414f-a589-e8bd98ec64d4")
public class UMLAggregationDiagramCommand extends DefaultLinkTool {
    @objid ("0338b07f-cfb7-4938-b4fe-e472276e9b73")
    @Override
    public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
        if ((arg1 != null) && (arg1.getElement() != null)){ 
            MObject element = arg1.getElement();
            return(element.getStatus().isModifiable() &&  (element instanceof Classifier));
        }
        return false;
    }

    @objid ("2cf715ae-25e1-4be6-abc3-a5eace7674c2")
    @Override
    public boolean acceptSecondElement(IDiagramHandle arg0, IDiagramGraphic arg1, IDiagramGraphic arg2) {
        return ((arg2 != null) && (arg2.getElement() != null) && (arg2.getElement() instanceof Classifier));
    }

    @objid ("c1bf511e-c8eb-4809-ad6d-2ff9807fbf37")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic arg1, IDiagramGraphic arg2, LinkRouterKind kind, ILinkRoute path) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();        
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "UML Aggregation"))){
        
            Classifier c_source = (Classifier)arg1.getElement();
            Classifier c_destination = (Classifier)arg2.getElement();
        
            Association association = model.createAssociation();
        
            AssociationEnd endSource = model.createAssociationEnd();          
            endSource.setMultiplicityMin("0");
            endSource.setMultiplicityMax("1");
            endSource.setSource(c_destination);
            endSource.setTarget(c_source);
        
            AssociationEnd endTarget = model.createAssociationEnd();
            endTarget.setName(c_destination.getName().toLowerCase());
            endTarget.setAggregation(AggregationKind.KINDISAGGREGATION);
            endTarget.setMultiplicityMin("1");
            endTarget.setMultiplicityMax("1");
           
            association.getEnd().add(endTarget);    
            association.getEnd().add(endSource);
             
            
            endSource.setOpposite(endTarget);
            endTarget.setOpposite(endSource);  
            
            endSource.setNavigable(false);
            endTarget.setNavigable(true);
        
        
            List<IDiagramGraphic> graphics = representation.unmask(association, 0 , 0);
            for (IDiagramGraphic graphic : graphics){
                if (graphic instanceof IDiagramLink){
                    IDiagramLink link = (IDiagramLink) graphic;
                    link.setRoute(path);                
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
