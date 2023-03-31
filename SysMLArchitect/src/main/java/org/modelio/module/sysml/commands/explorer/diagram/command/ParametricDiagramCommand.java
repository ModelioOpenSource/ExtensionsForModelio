/**
 * Java Class : AddParametricDiagramExplorerCommand.java
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
 * @category   Command explorer
 * @package    com.modeliosoft.modelio.sysml.gui.explorer
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.commands.explorer.diagram.command;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.sysml.filters.BlockFilter;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the creation of SysML parametric diagram
 * @author ebrosse
 */

public class ParametricDiagramCommand extends DefaultModuleCommandHandler {
    
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModuleContext moduleContext = SysMLModule.getInstance().getModuleContext();
        IModelingSession session = moduleContext.getModelingSession();
        ModelElement owner = (ModelElement) selectedElements.get(0);
        String name = I18nMessageService.getString ("Ui.Command.ParametricDiagramExplorerCommand.Name", owner.getName());
        StaticDiagram diagram = null;
        
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Parametric Diagram"))){
        
            diagram = SysMLFactory.createParametricDiagram(owner, name);
        
            if (diagram != null) {
                // Unmask the block in the diagram
                IDiagramService ds = SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService();

                try( IDiagramHandle handler = ds.getDiagramHandle(diagram);){
                    IDiagramDG dg = handler.getDiagramNode();
                    for (IStyleHandle style : ds.listStyles()){
                        if (style.getName().equals("sysmlinternal")){
                            dg.setStyle(style);
                            break;
                        }
                    }
        
                    List<IDiagramGraphic> dgs = handler.unmask(owner, 0, 0);
                    for (IDiagramGraphic dg2 : dgs){
                        if (dg2 instanceof IDiagramNode)
                            ((IDiagramNode) dg2).setBounds(new Rectangle (100, 100, 300, 250));
                    }
        
                    // Open the diagram
        
                    handler.save();
                    handler.close();
                }
        
                SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
            }
        
        
            transaction.commit ();
        } catch (ExtensionNotFoundException e) {
            SysMLModule.logService.error(e);
        }
    }

    
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1)){
            MObject selectedElement = selectedElements.get(0);
            return ((selectedElement != null) &&
                    ((BlockFilter.isABlock (selectedElement)
                            || ConstraintBlockFilter.isAConstraintBlock (selectedElement))));
        }
        return false;
    }

}
