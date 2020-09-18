/**
 * Java Class : AddFlowPortDiagramCommand.java
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
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a SysML Flow port
 * 
 * @author ebrosse
 */
@objid ("3ebbfab3-2abc-4cd7-96bc-4cf3dfc9cf5c")
public class FlowPortDiagramCommand extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("16869e97-4584-47c4-8452-dcaf0a7406ec")
    public FlowPortDiagramCommand() {
        super();
    }

    @objid ("dc9f9a6f-ae76-4a05-8f80-27a2245f924b")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (element.getStatus().isModifiable() && ((element instanceof Class) || (element instanceof Instance)
                                                                        || (element instanceof Component) || (element instanceof Artifact) || (element instanceof Node) || (element instanceof Signal)));
    }

    @objid ("8fbf535e-aa48-49e6-84e2-b919d523f872")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try (ITransaction transaction = session.createTransaction(I18nMessageService.getString("Info.Session.Create", ""))) {
            MObject element = target.getElement();
            Port port = null;
        
            if (element instanceof Instance) {
                port = SysMLFactory.createFlowPort((Instance) element);
            } else {
                port = SysMLFactory.createFlowPort((Classifier) element);
            }
        
            if (port != null) {
                List<IDiagramGraphic> graph = representation.unmask(port, rect.x, rect.y);
                if ((graph != null) && (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                    ((IDiagramNode) graph.get(0)).setBounds(rect);
        
                representation.save();
                representation.close();
                transaction.commit();
            }
        }
    }

}
