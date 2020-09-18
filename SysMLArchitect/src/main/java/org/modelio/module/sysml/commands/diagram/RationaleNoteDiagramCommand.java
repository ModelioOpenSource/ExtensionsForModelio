/**
 * Java Class : AddRationaleNoteDiagramCommand.java
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
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.tools.DefaultAttachedBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLNoteTypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Rationale Note
 * @author ebrosse
 */
@objid ("4fbb3131-01f7-4b24-9286-3712b514de1f")
public class RationaleNoteDiagramCommand extends DefaultAttachedBoxTool {
    @objid ("2285e589-7b21-4bf2-9803-fed172b93517")
    @Override
    public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic targetNode) {
        MObject target = targetNode.getElement();
        return ((target instanceof ModelElement) && target.getStatus().isModifiable ()) ;
    }

    @objid ("0c80ff4c-03d6-4dca-9c07-638c5ce08b20")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic target, LinkRouterKind kind, ILinkPath path, Point point) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Rational Note"))){
            ModelElement targetElement = (ModelElement) target.getElement();
        
            Note note =  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel ().createNote(ISysMLPeerModule.MODULE_NAME,SysMLNoteTypes.MODELELEMENT_RATIONALE, targetElement, ""); 
        
            representation.unmask(note, point.x, point.y);
            
            representation.save();
            representation.close();
            transaction.commit ();
        } catch (ExtensionNotFoundException e) {
            SysMLModule.logService.error(e);
        }
    }

    @objid ("3f6a8706-e8d2-4249-9ab3-bb137e8a754d")
    @Override
    public void actionPerformedInDiagram(IDiagramHandle representation, Rectangle rect) {
    }

}
