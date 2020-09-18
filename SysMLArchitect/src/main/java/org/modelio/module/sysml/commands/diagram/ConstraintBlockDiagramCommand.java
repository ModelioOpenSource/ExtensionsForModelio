/**
 * Java Class : AddConstraintBlockDiagramCommand.java
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
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Constraint Block
 * @author ebrosse
 */
@objid ("74d7c64d-9721-4f5f-bff5-8375c853c562")
public class ConstraintBlockDiagramCommand extends DefaultBoxTool {
    /**
     * Default constructor
     */
    @objid ("e4c09b96-df26-4625-851c-58dee1b4ab5f")
    public ConstraintBlockDiagramCommand() {
        super();
    }

    @objid ("4360c024-2923-4bcc-a642-702538b52377")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        
        if (element instanceof AbstractDiagram) {
            element = ((AbstractDiagram) element).getOrigin();
        }
        return  ((element != null) 
                                                                        && (element.getStatus().isModifiable()) 
                                                                        && (element instanceof Package));
    }

    @objid ("6c18ea56-aa5a-4fb9-b5d7-eee4430a76cd")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, Rectangle rect) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Constraint Block"))){
            MObject element = target.getElement();
            
            if (element instanceof AbstractDiagram) {
                element = ((AbstractDiagram) element).getOrigin();
            }
            
            MObject block = SysMLFactory.createConstraintBlock ((NameSpace) element);
            List<IDiagramGraphic> graph = representation.unmask (block, rect.x, rect.y);
            
            if((graph != null) &&  (graph.size() > 0) && (graph.get(0) instanceof IDiagramNode))
                ((IDiagramNode)graph.get(0)).setBounds(rect);
            
            representation.save();
            representation.close();
            transaction.commit ();
        }
    }

}
