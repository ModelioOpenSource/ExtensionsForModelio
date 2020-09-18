/**
 * Java Class : ActivityEdgePropertyPage.java
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
 * This class handles the properties associated to the probability stereotype
 * @author ebrosse
 */
@objid ("fe1f3108-c198-46e7-8ac5-c078c774f11d")
public class ProbabilityPropertyPage implements IPropertyContent {
    /**
     * Constructor ActivityEdgePropertyPage
     * @author ebrosse
     */
    @objid ("aedd852d-39c4-4bc5-a2bc-d912fd7f6468")
    public ProbabilityPropertyPage() {
    }

    @objid ("a5dfca59-3c86-40d9-b7c3-19b2fbd0f6fa")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.PROBABILITY_PROBABILITY, value, element);
        }
    }

    @objid ("c4ae9b7b-4e8a-4b6d-aba5-f764ee7ecb82")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value_kind = ModelUtils.getTaggedValue(SysMLTagTypes.PROBABILITY_PROBABILITY, element);
        
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.PROBABILITY_PROBABILITY),value_kind);
    }

}
