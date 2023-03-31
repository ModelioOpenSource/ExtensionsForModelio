/**
 * Java Class : SysMLModelChangeHandler.java
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
 * @category   Modelio Impl
 * @package    org.modelio.module.sysml.impl
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.impl;

import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * @author ebrosse
 */

public class SysMLModelChangeHandler implements IModelChangeHandler {
    
    @Override
    public void handleModelChange(IModelingSession session, IModelChangeEvent event) {

        //Update events
        Set<MObject> updatedElements = event.getUpdateEvents();

        for (MObject updatedElement : updatedElements){

            if (updatedElement instanceof ModelElement){
                ((ModelElement) updatedElement).removeStereotypes(ISysMLPeerModule.MODULE_NAME,  SysMLStereotypes.ALLOCATED);
                ((ModelElement) updatedElement).removeStereotypes(ISysMLPeerModule.MODULE_NAME,  SysMLStereotypes.REQUIREMENTRELATED);
            }

            if (updatedElement instanceof Port){

                updateFlowPort((Port) updatedElement);

            } else if (updatedElement instanceof TagParameter){

                ModelElement owner = ((TaggedValue)((TagParameter) updatedElement).getCompositionOwner()).getAnnoted();
                if (owner instanceof Port)
                    updateFlowPort((Port) owner);
            }

        }

        //Create events
        Set<MObject> createdEvents = event.getCreationEvents();

        for (MObject createdElt : createdEvents){
            if (createdElt instanceof Port){
                updateFlowPort((Port) createdElt);
            }
        }
    }

    
    private void updateFlowPort(Port port) {
        if (port.isStereotyped(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.FLOWPORT)){
           if (port.getDirection().equals(PortOrientation.NONE)){
               port.setDirection(PortOrientation.INOUT);
           }
        }
    }

}
