/**
 * Java Class : AddFlowPropertyDiagramCommand.java
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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Flow property
 * @author ebrosse
 */
@objid ("290d5515-64fe-4851-a12e-7f7e038ee035")
public class FlowPropertyDiagramCommand extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("a7266127-e7e3-4ebd-b5cf-0c4dbfd3ef2d")
    public FlowPropertyDiagramCommand() {
        super();
    }

    @objid ("1f1216a7-ab1d-46bf-83ca-f874bd9d8d33")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (element.getStatus().isModifiable () && (element instanceof Classifier) 
                                                                        && (((Classifier)element).isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.BLOCK)
                                                                                || ((Classifier)element).isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.FLOWSPECIFICATION))
                                                                                && (!((Classifier)element).isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONSTRAINTBLOCK)));
    }

    @objid ("8259fb51-48eb-4dfc-8bbe-1995a86143bd")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Flow Property"))){
            MObject element = target.getElement();
            if (element instanceof Classifier) {
        
                Attribute attribute = SysMLFactory.createFlowProperty((Classifier) element);
                List<IDiagramGraphic> graph = representation.unmask (attribute, rect.x, rect.y);
                
                if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                    ((IDiagramNode)graph.get(0)).setBounds(rect);
        
                representation.save();
                representation.close();
                transaction.commit ();
            }
        }
    }

}
