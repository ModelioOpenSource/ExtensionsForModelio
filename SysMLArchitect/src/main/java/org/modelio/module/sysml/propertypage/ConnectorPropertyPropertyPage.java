/**
 * Java Class : ConnectorPropertyPage.java
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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the properties associated to the connector property stereotype
 * @author ebrosse
 */
@objid ("b6ff5c85-5a9d-4980-9fd8-e02ef832e6a8")
public class ConnectorPropertyPropertyPage implements IPropertyContent {
    @objid ("b9addf0e-588c-4ca4-8f6d-6f6738d348bd")
    private static List<MObject> _connectors = null;

    /**
     * Constructor ConnectorPropertyPropertyPage
     * @author ebrosse
     */
    @objid ("0cc06e03-eff9-4c24-a5d8-66a1d7aaf5d3")
    public ConnectorPropertyPropertyPage() {
    }

    @objid ("8981c372-0e7a-49e7-bfbb-2892b2110b7c")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
        
            if (value.equals("")){
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.CONNECTORPROPERTYCONNECTOR);
            }else{
        
                for (MObject connector : _connectors){
                    if (Utils.getName(connector).equals(value) && (connector instanceof BindableInstance)){
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.CONNECTORPROPERTY_CONNECTOR, value,
                                            element, (BindableInstance) connector, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONNECTORPROPERTYCONNECTOR);
                    }
                }
            }
        }
    }

    @objid ("657e1523-89b3-4374-9b84-376e5ab380bf")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONNECTORPROPERTYCONNECTOR, element);
        _connectors = ModelUtils.searchConnector(((BindableInstance)element).getInternalOwner());
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.CONNECTORPROPERTY_CONNECTOR), value_kind, Utils.getNames(_connectors) );
    }

}
