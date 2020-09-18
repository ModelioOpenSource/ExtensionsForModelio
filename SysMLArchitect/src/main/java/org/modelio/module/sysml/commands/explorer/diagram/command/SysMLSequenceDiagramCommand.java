/**
 * Java Class : AddBlockDiagramExplorerCommand.java
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
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the creation of SysML sequence diagram
 * 
 * @author ebrosse
 */
@objid ("e5510059-7e95-4a5f-8b47-898d7009c87e")
public class SysMLSequenceDiagramCommand extends DefaultModuleCommandHandler {
    @objid ("151536d9-15f3-481c-93ae-708ae8ee933e")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        ModelElement element = (ModelElement) selectedElements.get(0);        
        String diagramName = I18nMessageService.getString("Ui.Command.SysMLSequenceDiagramExplorerCommand.Label",element.getName());
        
        AbstractDiagram diagram = null;
        try (ITransaction transaction = session.createTransaction(I18nMessageService.getString("Info.Session.Create", "Activity"))) {
        
            diagram = SysMLFactory.createSysMLSequenceDiagram(element, diagramName);
        
            if (diagram != null) {
                SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
            }
        
            transaction.commit();
        }
    }

    @objid ("bc5cf5d0-853f-4870-919c-c3095473a6e5")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1)){
            MObject selectedElement = selectedElements.get(0);
            return ((selectedElement != null) 
                    && selectedElement.getStatus().isModifiable() 
                    && (((selectedElement instanceof Package)
                            && !(selectedElement instanceof Profile)))                    
                    || ((selectedElement instanceof Class) 
                            && !(ConstraintBlockFilter.isAConstraintBlock(selectedElement)))
                    || (selectedElement instanceof Interface)
                    || (selectedElement instanceof Actor) 
                    || (selectedElement instanceof Component) 
                    || (selectedElement instanceof Node)
                    || (selectedElement instanceof UseCase) 
                    || (selectedElement instanceof Collaboration) 
                    || (selectedElement instanceof Package)
                    || (selectedElement instanceof Operation) 
                    || (selectedElement instanceof Interaction));
        }
        return false;
    }

}
