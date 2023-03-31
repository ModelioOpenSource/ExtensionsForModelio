/**
 * Java Class : UnitPropertyPage.java
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
 * This class handles the properties associated to the Unit stereotype
 * @author ebrosse
 */

public class UnitPropertyPage implements IPropertyContent {
    
    private static List<MObject> _quantityKinds = null;

    /**
     * Constructor UnitPropertyPage
     * @author ebrosse
     */
    
    public UnitPropertyPage() {
    }

    
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.UNIT_SYMBOL, value, element);
        }
        else if(row == 2){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.UNIT_DESCRIPTION, value, element);
        }
        else if(row == 3){
            ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.UNIT_DESCRIPTIONURI, value, element);
        }
        else if(row == 4){
        
            if (value.equals("")){
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.UNITQUANTITYKIND);
            }else{
        
                for (MObject quantityKind : _quantityKinds){
                    if (Utils.getName(quantityKind).equals(value) && (quantityKind instanceof Instance)){
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME,SysMLTagTypes.UNIT_QUANTITYKIND, value, element, (Instance)quantityKind, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNITQUANTITYKIND);
                    }
                }
            }
        }
    }

    
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value_kind = ModelUtils.getTaggedValue(SysMLTagTypes.UNIT_SYMBOL, element);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.UNIT_SYMBOL),value_kind);
        
        value_kind = ModelUtils.getTaggedValue(SysMLTagTypes.UNIT_DESCRIPTION, element);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.UNIT_DESCRIPTION),value_kind);
        
        value_kind = ModelUtils.getTaggedValue(SysMLTagTypes.UNIT_DESCRIPTIONURI, element);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.UNIT_DESCRIPTIONURI),value_kind);
        
        value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNITQUANTITYKIND, element);
        _quantityKinds =  ModelUtils.searchElement(Instance.class,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.QUANTITYKIND);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.UNIT_QUANTITYKIND), value_kind, Utils.getNames(_quantityKinds));
    }

}
