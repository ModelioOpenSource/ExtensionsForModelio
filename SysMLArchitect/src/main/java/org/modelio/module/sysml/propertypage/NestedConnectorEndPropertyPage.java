/**
 * Java Class : NestedConnectorEndPropertyPage.java
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

/**
 * This class handles the properties associated to the Nested Connector End stereotype
 * @author ebrosse
 */
@objid ("7130385e-0d98-440c-ab52-d677e994931b")
public class NestedConnectorEndPropertyPage implements IPropertyContent {
    /**
     * Constructor NestedConnectorEndPropertyPage
     * @author ebrosse
     */
    @objid ("9a76ee35-acd9-4cec-b166-ce6224853e22")
    public NestedConnectorEndPropertyPage() {
    }

    @objid ("5fd17467-1a61-4534-b774-bf2924c7ad98")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
    }

    @objid ("546a2b87-e8df-402e-a6d5-9a13e9d5085b")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
    }

}
