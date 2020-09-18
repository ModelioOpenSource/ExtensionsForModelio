/**
 * Java Class : AddDistributedPropertyDiagramCommand.java
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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.filters.BlockFilter;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Distributed property
 * @author ebrosse
 */
@objid ("68ab433a-fd6d-4f0b-be23-1211e5372cdd")
public class DistributedPropertyDiagramCommand extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("cc33ffab-d430-4cac-a415-99f682bf5e80")
    public DistributedPropertyDiagramCommand() {
        super();
    }

    @objid ("4a0edc36-aac6-4400-b5f3-8550b4fcc9a3")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (element.getStatus().isModifiable () && (element instanceof Classifier)
                                                                        && ((BlockFilter.isABlock(element)
                                                                                && (!ConstraintBlockFilter.isAConstraintBlock(element)))
                                                                                || (((Classifier) element).isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPE))));
    }

    @objid ("e20d8aea-40b5-4f9a-9633-3375add2b947")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Distributed Property"))){
            MObject element = target.getElement();
            MObject distributed = SysMLFactory.createDistributedProperty((Classifier) element);
            List<IDiagramGraphic> graph = representation.unmask (distributed, rect.x, rect.y);
        
            if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                ((IDiagramNode)graph.get(0)).setBounds(rect);
            
            representation.save();
            representation.close();
            transaction.commit ();
        }
    }

}
