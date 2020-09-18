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
 * @package    com.modeliosoft.modelio.sysml.gui.propertypage
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.propertypage;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the properties associated to the Rate stereotype
 * @author ebrosse
 */
@objid ("c7a10db7-5f6d-4ca0-aca4-7d7dfd3d37f5")
public class RateParameterPropertyPage implements IPropertyContent {
    @objid ("e38f374d-3aa3-42d1-929c-e2f6ce484c33")
    private static Collection<Instance> _instances;

    /**
     * Constructor ValueTypePropertyPage
     * @author ebrosse
     */
    @objid ("bfa379e7-ac48-42c8-8d56-056c27c8e83a")
    public RateParameterPropertyPage() {
    }

    @objid ("f7f73136-6467-4c08-ad94-e4d6b441c4c7")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            if (value.equals("")){
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.RATE);
            }else{

                for (MObject instance : _instances){
                    if ((instance instanceof Instance) && (Utils.getName(instance).equals(value))){
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME,SysMLTagTypes.RATE_PARAMETER_RATE, value, element, (Instance) instance, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE);                    }
                }

            }
        }
    }

    @objid ("c93b05c6-7f29-4ae9-b3e3-ef9c98cd51bf")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.RATE, element);
        _instances =  SysMLModule.getInstance().getModuleContext().getModelingSession().findByClass(Instance.class);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.RATE_PARAMETER_RATE), value_kind, Utils.getNames(_instances) );
    }

}
