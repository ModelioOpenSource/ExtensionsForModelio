/**
 * Java Class : ItemFlowPropertyPage.java
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
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the properties associated to the ItemFlow stereotype
 * @author ebrosse
 */
@objid ("214e4c34-136a-4216-b92f-a43e1a479d17")
public class ItemFlowPropertyPage implements IPropertyContent {
    @objid ("eca98168-15d4-4554-9904-0b52631a9298")
    private static List<MObject> _itemproperties = null;

    /**
     * Constructor ItemFlowPropertyPage
     * @author ebrosse
     */
    @objid ("ee9cb6bf-149c-4f2c-b2ba-e25d56f0a4c3")
    public ItemFlowPropertyPage() {
    }

    @objid ("b9b12fbf-9a90-450b-a55e-f61b206a0da8")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            if (value.equals("")){
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.ITEMFLOW);
            }else{
        
                for (MObject itemproperty : _itemproperties){
                    if (Utils.getName(itemproperty).equals(value)){
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.ITEMFLOW_ITEMPROPERTY, value, element, (InformationFlow)itemproperty, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ITEMPROPERTY);
                    }
                }
            }
        }
    }

    @objid ("a050f80c-849e-401b-8857-577720330738")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ITEMPROPERTY, element);
        _itemproperties = ModelUtils.getBindableInstance((InformationFlow) element);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.ITEMFLOW_ITEMPROPERTY), value_kind, Utils.getNames(_itemproperties) );
    }

}
