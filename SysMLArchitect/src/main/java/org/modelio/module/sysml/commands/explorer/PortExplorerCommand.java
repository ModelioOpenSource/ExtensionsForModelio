/**
 * Java Class : AddPortExplorerButton.java
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
package org.modelio.module.sysml.commands.explorer;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This command handles the creation of UML port in the model explorer
 * @author ebrosse
 */
@objid ("99776d18-a0cd-44a9-8e84-e135598375b1")
public class PortExplorerCommand extends DefaultModuleCommandHandler {
    @objid ("427e2509-890d-4994-91ab-5c6505604fa7")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Port"))){
        
            for (MObject element : selectedElements) {
                Port port = session.getModel().createPort();
                if (element instanceof Instance){
                    ((Instance) element).getPart().add(port);
                    
                    port.setName("p" + ((Instance) element).getPart().size());
                }else if (element instanceof Classifier){
                    ((Classifier) element).getInternalStructure().add(port);
                    port.setName("p" +   ((Classifier) element).getInternalStructure().size());
                }
                      }
        
            transaction.commit ();
        }
    }

    @objid ("2f131095-2d84-414f-8825-9dee7cb0daa5")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module)){
        return ((selectedElements == null)
                || (selectedElements.size() == 0)
                || (selectedElements.get(0) instanceof Instance)
                || (selectedElements.get(0) instanceof Classifier));
        
        }
        return false;
    }

    @objid ("df66b217-acb1-42d0-b346-2b545d4e5718")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        MObject selectedElt = selectedElements.get(0);
        if ((selectedElt instanceof Profile)  || (selectedElt instanceof IModule))
            return false;
        return selectedElt.getStatus().isModifiable();
    }

}
