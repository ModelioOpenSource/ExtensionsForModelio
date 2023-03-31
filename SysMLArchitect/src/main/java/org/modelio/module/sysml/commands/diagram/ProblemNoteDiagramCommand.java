/**
 * Java Class : AddProblemNoteDiagramCommand.java
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.tools.DefaultAttachedBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLNoteTypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Problem Note
 * @author ebrosse
 */

public class ProblemNoteDiagramCommand extends DefaultAttachedBoxTool {
    
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic targetNode) {
        MObject target = targetNode.getElement();
        return ((target instanceof ModelElement) && target.getStatus().isModifiable ()) ;
    }

    
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic arg2, LinkRouterKind arg3, ILinkRoute arg4, Point arg5) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();                
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Problem Note"))){
            ModelElement owner = (ModelElement) arg2.getElement();
            Note note = model.createNote(ISysMLPeerModule.MODULE_NAME,SysMLNoteTypes.MODELELEMENT_PROBLEM, owner, "Problem Note");   
            representation.unmask(note, arg5.x, arg5.y );
            
            representation.save();
            representation.close();
            transaction.commit();
        } catch (ExtensionNotFoundException e) {
            SysMLModule.logService.error(e);
        }
    }

    
    @Override
    public void actionPerformedInDiagram(IDiagramHandle arg0, Rectangle arg1) {
    }

}
