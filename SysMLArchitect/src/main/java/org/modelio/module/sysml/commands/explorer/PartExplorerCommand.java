/**
 * Java Class : AddPartExplorerButton.java
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
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.CommandExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This command handles the creation of UML part in the model explorer
 * @author ebrosse
 */
@objid ("fa4707d6-4ea4-4192-97c0-fb49ea04e05b")
public class PartExplorerCommand extends DefaultModuleCommandHandler {
    @objid ("1eff9329-3c07-4518-9a2e-712132c9d0e0")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Part"))){
        
            for (MObject element : selectedElements) {
                if (element instanceof NameSpace){
                    BindableInstance part = session.getModel().createBindableInstance();
                    part.setInternalOwner((Class)element);
                    part.setName("i" + ((Class)element).getInternalStructure().size());
                }
            }
        
            transaction.commit ();
        }
    }

    @objid ("4848273c-e352-4e99-a651-a9229ab4f10b")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module)){
              return (selectedElements.size() > 0) 
                      && (CommandExpert.accept(selectedElements.get(0))
                              && !ConstraintBlockFilter.isAConstraintBlock(selectedElements.get(0)));
        }
        return false;
    }

    @objid ("03bbeeb8-edcb-476f-94d4-e6784c217259")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        MObject selectedElt = selectedElements.get(0);
        if ((selectedElt instanceof Profile)  || (selectedElt instanceof IModule))
            return false;
        return selectedElt.getStatus().isModifiable();
    }

}
