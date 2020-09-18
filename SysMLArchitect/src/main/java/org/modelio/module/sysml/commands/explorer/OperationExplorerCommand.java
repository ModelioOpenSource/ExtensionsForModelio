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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This command handles the creation of SysML optional in the model explorer
 * @author ebrosse
 */
@objid ("be7761db-c0bd-47c9-9e19-5007bd21f978")
public class OperationExplorerCommand extends DefaultModuleCommandHandler {
    @objid ("6e10f21e-bde2-4511-b193-dfaa671b9e0c")
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Operation"))){
        
            for (MObject element : selectedElements) {
                SysMLFactory.createOperation((Classifier)element);
            }
        
            transaction.commit ();
        }
    }

    @objid ("16498e0c-4ae0-4c0b-8b87-d3643987595d")
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if (super.accept(selectedElements, module)){
            if(selectedElements.size() > 0){
                MObject selectedElt = selectedElements.get(0);
                if ((selectedElt instanceof Profile)  || (selectedElt instanceof IModule))
                    return false;
        
                return(selectedElt.getStatus().isModifiable() 
                        &&  (selectedElt instanceof Classifier) 
                        && !ConstraintBlockFilter.isAConstraintBlock(selectedElt));
            }
        }
        return false;
    }

    @objid ("98f07bcc-7e1f-4fb4-a041-d611f317986f")
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
