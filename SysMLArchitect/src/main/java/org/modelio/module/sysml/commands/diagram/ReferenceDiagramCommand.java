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
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an SysML/UML Attribute
 * @author ebrosse
 */
@objid ("7151dbfc-8ef3-47ed-9c35-5898dd558395")
public class ReferenceDiagramCommand extends DefaultBoxTool {
    @objid ("825d7081-b21f-4c54-928b-ae36fe501e10")
    @Override
    public boolean acceptElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
        if ((arg1 != null) && (arg1.getElement() != null)){ 
            MObject element = arg1.getElement();
            return(element.getStatus().isModifiable() 
                    &&  (element instanceof Classifier) 
                    &&  !ConstraintBlockFilter.isAConstraintBlock(element));
        }
        return false;
    }

    @objid ("72543e18-98ff-4c6e-8090-009898ac432c")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic arg1, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();        
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Reference"))){
            Classifier owner = (Classifier) arg1.getElement();
        
            Attribute attribute = model.createAttribute();
            attribute.setOwner(owner);
            attribute.setName("ref" + owner.getOwnedAttribute().size());
            List<IDiagramGraphic> graph = representation.unmask(attribute, rect.x , rect.y);
            if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                ((IDiagramNode)graph.get(0)).setBounds(rect);
            
            representation.save();
            representation.close();
            transaction.commit ();
        }
    }

}
