/**
 * Java Class : AddOptionalExplorerButton.java
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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.CommandExpert;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This command handles the creation of SysML optional in the model explorer
 * @author ebrosse
 */
@objid ("c0098d2f-ed24-496c-b7ea-4bd7df0777e1")
public class ParameterExplorerCommand extends DefaultModuleCommandHandler {
    @objid ("8a7f30df-4c21-4bb2-8cd9-0aa8c7cdf332")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Parameter"))){
        
            for (MObject element : selectedElements) {
                SysMLFactory.createParameter((Operation)element);
            }
        
            transaction.commit ();
        }
    }

    @objid ("a8e38465-b03d-408f-b42d-50b9b92acaa3")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module)){
            return (selectedElements.size() > 0) 
                    && CommandExpert.accept(selectedElements.get(0));
        }
        return false;
    }

    @objid ("488226cf-3b14-4ceb-bf3b-ff2d3fce1377")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        MObject selectedElt = selectedElements.get(0);
        if ((selectedElt instanceof Profile)  || (selectedElt instanceof IModule))
            return false;
        return selectedElt.getStatus().isModifiable();
    }

}
