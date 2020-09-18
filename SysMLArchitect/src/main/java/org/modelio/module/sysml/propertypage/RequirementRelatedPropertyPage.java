/**
 * Java Class : RequirementRelatedPropertyPage.java
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
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.utils.Utils;

/**
 * This class handles the properties associated to the requirement related stereotype
 * @author ebrosse
 */
@objid ("cdf1abff-f261-4391-8a43-bc28a62f450b")
public class RequirementRelatedPropertyPage implements IPropertyContent {
    /**
     * Constructor RequirementRelatedPropertyPage
     * @author ebrosse
     */
    @objid ("dac29c19-7887-4e4c-8abd-37b409bab337")
    public RequirementRelatedPropertyPage() {
    }

    @objid ("864dad29-ac59-4fe5-9fde-8cfa4dc418a2")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        /*
         * Display Refinement
         */
        
        ArrayList<ModelElement> value = new ArrayList<>(); 
        for (Dependency dependency : element.getDependsOnDependency ()) {
            if(dependency.isStereotyped("ModelerModule", "refine"))
                value.add(dependency.getDependsOn());
        }
        
        String valuetab = Utils.getAbsoluteNamesWithSeparator(value);
        table.addConsultProperty(I18nMessageService.getString("Ui.Refine.To"), valuetab); 
        
        
        /*
         * Display Satisfy
         */
        value = new ArrayList<>(); 
        for (Dependency dependency : element.getDependsOnDependency ()) {
              if(dependency.isStereotyped("ModelerModule", "satisfy"))
                   value.add(dependency.getDependsOn());
        }
        valuetab = Utils.getAbsoluteNamesWithSeparator(value);
        table.addConsultProperty(I18nMessageService.getString("Ui.Satisfy.To"), valuetab); 
        
        
        /*
         * Display Verify
         */
        value = new ArrayList<>(); 
        for (Dependency dependency : element.getDependsOnDependency ()) {
            if (dependency.isStereotyped("ModelerModule", "verify"))
                 value.add(dependency.getDependsOn());
        }
        valuetab = Utils.getAbsoluteNamesWithSeparator(value);
        table.addConsultProperty(I18nMessageService.getString("Ui.Verify.To"), valuetab);
    }

    @objid ("02ea4edf-27d7-469b-b198-775fdcc4b4aa")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
    }

}
