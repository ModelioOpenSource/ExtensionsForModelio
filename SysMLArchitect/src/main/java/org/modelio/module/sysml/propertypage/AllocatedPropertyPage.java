/**
 * Java Class : CommonPropertyPage.java
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

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.utils.Utils;

/**
 * This class handles the properties associated to the Allocated stereotype
 * @author ebrosse
 */
@objid ("66d0d14c-ae68-4b5c-ab1f-62388011746b")
public class AllocatedPropertyPage implements IPropertyContent {
    /**
     * Default constructor
     */
    @objid ("8832a2c0-e99d-4364-9067-81b40a3a0052")
    public AllocatedPropertyPage() {
    }

    @objid ("a6607e50-ba88-4f04-9796-ab79d506a349")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        /*
         * Display Trace
         */
        
        ArrayList<ModelElement> value = new ArrayList<>(); 
        for (Dependency dependency : element.getImpactedDependency ()){
            if(dependency.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ALLOCATE))
                value.add(dependency.getImpacted()); 
        }
        
        String valuetab = Utils.getAbsoluteNamesWithSeparator(value);
            table.addConsultProperty(I18nMessageService.getString("Ui.Allocated.From"), valuetab);
        
        value = new ArrayList<>(); 
        for (Dependency dependency : element.getDependsOnDependency ()) {
            if(dependency.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ALLOCATE))
                value.add(dependency.getDependsOn());
        }
        
        valuetab = Utils.getAbsoluteNamesWithSeparator(value);
            table.addConsultProperty(I18nMessageService.getString("Ui.Allocated.To"), valuetab);
    }

    @objid ("6df173d4-0083-410d-b7f2-9970f59b0b02")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        // Nothing Todo
    }

}
