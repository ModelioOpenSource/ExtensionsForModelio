/**
 * Java Class : PinPropertyPage.java
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
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;

/**
 * This class handles the properties associated to the Pin stereotype
 * @author ebrosse
 */
@objid ("62f2677a-1070-4f3b-8e77-96b843a81055")
public class PinPropertyPage implements IPropertyContent {
    /**
     * Constructor PinPropertyPage
     * @author ebrosse
     */
    @objid ("3117283d-e9af-4f50-8297-8c5bf218c3fb")
    public PinPropertyPage() {
    }

    @objid ("0fbc4a26-cf88-4bf1-9dc7-ad3037be404e")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME,SysMLTagTypes.PIN_ISSTREAM, value, element);
        } else if(row == 2){
            ((Pin)element).setIsControl(Boolean.valueOf(value));
        }
    }

    @objid ("689c2d00-02ae-4662-8591-125835905afc")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.PIN_ISSTREAM),
                ModelUtils.hasTaggedValue(SysMLTagTypes.PIN_ISSTREAM, element));
        
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName("Pin_isControl"),
                ((Pin)element).isIsControl());
    }

}
