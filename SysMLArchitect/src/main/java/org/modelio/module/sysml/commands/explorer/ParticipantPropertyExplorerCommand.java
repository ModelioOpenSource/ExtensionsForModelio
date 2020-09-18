/**
 * Java Class : AddParticipantPropertyExplorerButton.java
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
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.sysml.filters.BlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This command handles the creation of SysML participant properties in the model explorer
 * @author ebrosse
 */
@objid ("19a30fe4-97f9-4c40-92ea-8e44d055dcec")
public class ParticipantPropertyExplorerCommand extends DefaultModuleCommandHandler {
    @objid ("a010fec4-382e-47c4-b7ed-9dc8e150e1e4")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Participant Property"))){
        
            for (MObject element : selectedElements) {
                SysMLFactory.createParticipantProperty ((NameSpace) element);
            }
        
            transaction.commit ();
        }
    }

    @objid ("d4349b6b-91c2-48ab-af5c-7dcb38fe3161")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module)){
            return ( (selectedElements != null)
                    && (selectedElements.size() != 0)
                    &&  (BlockFilter.isABlock(selectedElements.get(0))));
        }
        return false;
    }

    @objid ("6eaebd95-71cb-467b-901d-4ad6a75c8e33")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        MObject selectedElt = selectedElements.get(0);
        if ((selectedElt instanceof Profile)  || (selectedElt instanceof IModule))
            return false;
        return selectedElt.getStatus().isModifiable();
    }

}
