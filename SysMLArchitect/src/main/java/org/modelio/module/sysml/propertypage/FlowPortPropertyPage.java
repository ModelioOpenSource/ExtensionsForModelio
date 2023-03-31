/**
 * Java Class : FlowPortPropertyPage.java
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
 * @category   PropertyDefinition page
 * @package    org.modelio.module.sysml.gui.propertypage
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.propertypage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;

/**
 * This class handles the properties associated to the flow port stereotype
 * @author ebrosse
 */

public class FlowPortPropertyPage implements IPropertyContent {
    /**
     * Constructor FlowPortPropertyPage
     * @author ebrosse
     */
    
    public FlowPortPropertyPage() {
    }

    
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        //Direction
        if(row == 1){
            Port port = (Port) element;
            switch (PortOrientation.get(value)) {
            case OUT:
                port.setDirection(PortOrientation.OUT);
                break;
            case IN:
                port.setDirection(PortOrientation.IN);
                break;
        
            default:
                port.setDirection(PortOrientation.INOUT);
                break;
            }
        
        }
        //IsAtomic
        else if(row == 2){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME,SysMLTagTypes.FLOWPORT_ISATOMIC, value, element);
        }
    }

    
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        SysMLResourcesManager sysMLResources = SysMLResourcesManager.getInstance();
        
        //Direction
        String[] tabDirection = new String[3];
        tabDirection[0] = PortOrientation.IN.toString();
        tabDirection[1] = PortOrientation.OUT.toString();
        tabDirection[2] = PortOrientation.INOUT.toString();
        
        table.addProperty(sysMLResources.getPropertyName("Port_Direction"), ((Port) element).getDirection().toString(), tabDirection);
        
        //Is Atomic
        table.addProperty(sysMLResources.getPropertyName(SysMLTagTypes.FLOWPORT_ISATOMIC),
                ModelUtils.hasTaggedValue(SysMLTagTypes.FLOWPORT_ISATOMIC, element));
    }

}
