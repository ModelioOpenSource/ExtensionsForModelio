/**
 * Java Class : FlowPropertyPropertyPage.java
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
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;

/**
 * This class handles the properties associated to the flow property stereotype
 * @author ebrosse
 */
@objid ("c8f2ba31-9951-4fc9-9961-fddcff485c75")
public class FlowPropertyPropertyPage implements IPropertyContent {
    /**
     * Constructor FlowPropertyPropertyPage
     * @author ebrosse
     */
    @objid ("e823d07a-6c2b-4e28-9df7-89f5b82de545")
    public FlowPropertyPropertyPage() {
    }

    @objid ("fd780761-3a8a-4288-9d57-85cc00da18e9")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.FLOWPROPERTY_DIRECTION, value, element);
        }
    }

    @objid ("2b3f3321-2794-4984-aa3d-f3de3a005d37")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String[] tabDirection = {"In", "Out", "In/Out"};
        String value_kind = ModelUtils.getTaggedValue(SysMLTagTypes.FLOWPROPERTY_DIRECTION, element);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.FLOWPROPERTY_DIRECTION), value_kind,tabDirection);
    }

}
