/**
 * Java Class : BlockPropertyPage.java
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
 * This class handles the properties associated to the block stereotype
 * @author ebrosse
 */
@objid ("a9277c7d-9f65-4878-a050-a85b30be9742")
public class BlockPropertyPage implements IPropertyContent {
    /**
     * Constructor BlockPropertyPage
     * @author ebrosse
     */
    @objid ("c62ebf79-3377-4da9-ba2a-fae69981ea49")
    public BlockPropertyPage() {
    }

    @objid ("dc083f14-16da-4384-889c-dd5fce538bd4")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.BLOCK_ISENCAPSULATED, value, element);
        }
    }

    @objid ("1ed9737d-7806-4c4c-a63b-d4f155d33068")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.BLOCK_ISENCAPSULATED), ModelUtils.hasTaggedValue(SysMLTagTypes.BLOCK_ISENCAPSULATED, element));
    }

}
