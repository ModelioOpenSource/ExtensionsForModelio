/**
 * Java Class : AddContinuousParameterDiagramCommand.java
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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Continuous parameter
 * @author ebrosse
 */
@objid ("d729f765-5156-4051-b968-2f37112c3b47")
public class ContinuousParameterDiagramCommand extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("b761732f-b6c8-482a-a6d2-bcd826d5710c")
    public ContinuousParameterDiagramCommand() {
        super();
    }

    @objid ("3f9bfa3c-de5b-410a-bede-302f520597b7")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        if (element instanceof AbstractDiagram) {
            element = ((AbstractDiagram) element).getOrigin();
        }
        return ((element != null)  &&  (element.getStatus().isModifiable () 
                                                                        && (( element instanceof Operation) || ( element instanceof Activity))));
    }

    @objid ("f5cddfec-8a47-4545-9b4e-15231b37fa65")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Continuous Parameter"))){
            MObject element = target.getElement();
        
            if (element instanceof AbstractDiagram) {
                element = ((AbstractDiagram) element).getOrigin();
            }
            
            List<IDiagramGraphic> graph = null;
            Parameter parameter = SysMLFactory.createContinuousParameter(element);
            if (element instanceof Activity){
        
                ActivityParameterNode actparameter = session.getModel().createActivityParameterNode();
                actparameter.setName(parameter.getName());
                if (parameter instanceof BehaviorParameter){
                    actparameter.setRepresentedRealParameter((BehaviorParameter) parameter);
                } 
                actparameter.setOwner((Activity) element);
                graph = representation.unmask(actparameter, rect.x, rect.y);
                
        
            }else{
                graph = representation.unmask (parameter, rect.x, rect.y);
            }
            
            if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                ((IDiagramNode)graph.get(0)).setBounds(rect);
            
            representation.save();
            representation.close();
            transaction.commit ();
        }
    }

}
