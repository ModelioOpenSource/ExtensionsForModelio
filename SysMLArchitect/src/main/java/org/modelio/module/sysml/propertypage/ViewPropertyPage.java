/**
 * Java Class : ViewPropertyPage.java
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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the properties associated to the View stereotype
 * @author ebrosse
 */
@objid ("04196877-9be3-4028-820f-98ffac132728")
public class ViewPropertyPage implements IPropertyContent {
    @objid ("d26b4ac0-a7da-4b98-8342-cdfd22e7df53")
    private static List<MObject> _viewpoints = null;

    /**
     * Constructor ViewPropertyPage
     * @author ebrosse
     */
    @objid ("042d3cce-42de-41ae-9d70-2cfd4fa0e8d9")
    public ViewPropertyPage() {
    }

    @objid ("09453224-71e9-46ea-8484-2a9008cb9e60")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if(row == 1){
            if (value.equals("")){
                ModelUtils.removeStereotypedLink(element, SysMLStereotypes.CONFORM);
            }else{
        
                for (MObject viewpoint : _viewpoints){
                    if (Utils.getName(viewpoint).equals(value) && (viewpoint instanceof Class)){
                        ModelUtils.addValue(ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.VIEW_VIEWPOINT, value, element, (Class)viewpoint, ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONFORM);
                    }
                }
            }
        }
    }

    @objid ("a65cd57d-7d7a-4ed7-b674-db51ea3e5f34")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        String value_kind = ModelUtils.getTaggedValueLink(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONFORM, element);
        _viewpoints = ModelUtils.searchElement(Class.class, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEWPOINT);
        table.addProperty(SysMLResourcesManager.getInstance().getPropertyName(SysMLTagTypes.VIEW_VIEWPOINT), value_kind, Utils.getNames(_viewpoints));
    }

}
