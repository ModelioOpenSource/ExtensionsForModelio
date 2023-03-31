/**
 * Java Class : ValueTypePropertyPage.java
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
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the properties associated to the Value Type stereotype
 * 
 * @author ebrosse
 */

public class ValueTypePropertyPage implements IPropertyContent {
    
    private static List<MObject> _quantityKinds = null;

    
    private static List<MObject> _units = null;

    /**
     * Constructor ValueTypePropertyPage
     * @author ebrosse
     */
    
    public ValueTypePropertyPage() {
    }

    
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        //Quantity Kind
        if (row == 1) {
            if (value.equals("")) {
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.VALUETYPEQUANTITYKIND);
            } else {
                _quantityKinds = ModelUtils.searchElement(Instance.class,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.QUANTITYKIND);
                for (MObject quantityKind : _quantityKinds) {
                    if (Utils.getName(quantityKind).equals(value) && (quantityKind instanceof Instance)) {
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.VALUETYPE_QUANTITYKIND, value, element,
                                (Instance) quantityKind, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VALUETYPEQUANTITYKIND);
                    }
                }
        
            }
            //Unit
        } else if (row == 2) {
            if (value.equals("")) {
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.VALUETYPEUNIT);
            } else {
                _units = ModelUtils.searchElement(Instance.class, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNIT);
                for (MObject unit : _units) {
                    if (Utils.getName(unit).equals(value) && (unit instanceof Instance)) {
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.VALUETYPE_UNIT, value, element, (Instance) unit,
                                ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPEUNIT);
                    }
                }
        
            }
        
        }
    }

    
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        //Quantity Kind
        String value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPEQUANTITYKIND, element);
        _quantityKinds = ModelUtils.searchElement(Instance.class,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.QUANTITYKIND);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.VALUETYPE_QUANTITYKIND),
                value_kind, Utils.getNames(_quantityKinds));
        
        //Unit
        value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VALUETYPEUNIT, element);
        _units = ModelUtils.searchElement(Instance.class,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNIT);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.VALUETYPE_UNIT), value_kind,
                Utils.getNames(_units));
    }

}
