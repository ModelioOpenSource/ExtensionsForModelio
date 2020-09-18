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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.sysml.utils.SysMLResourcesManager;

/**
 * This class handles the properties associated to the Port
 * @author ebrosse
 */
@objid ("fc9da517-e30d-4fff-a3b6-1ab3fb3cf819")
public class PortPropertyPage implements IPropertyContent {
    /**
     * Constructor PinPropertyPage
     * @author ebrosse
     */
    @objid ("be36d968-e589-423a-b0ac-184b6e880609")
    public PortPropertyPage() {
    }

    @objid ("dcb4d18e-ac18-4195-9f3d-463a9f48d284")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ((Port) element).setIsConjugated(Boolean.valueOf(value));
        }
    }

    @objid ("96101376-69c3-46c0-9228-09dfa5303686")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName("Port_IsConjugated"), ((Port) element).isIsConjugated());
    }

}
