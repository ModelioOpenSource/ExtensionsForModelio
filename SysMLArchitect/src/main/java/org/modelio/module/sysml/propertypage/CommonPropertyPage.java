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
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.utils.Utils;

/**
 * This class handles the properties common to all SysML stereotypes
 * @author ebrosse
 */
@objid ("51ddb891-77b7-4aa5-b1f4-f4a4086b13ec")
public class CommonPropertyPage implements IPropertyContent {
    /**
     * Constructor CommonPropertyPage
     * @author ebrosse
     */
    @objid ("7f608111-53b0-4f3f-81fb-4e1694d0d1a5")
    public CommonPropertyPage() {
    }

    @objid ("b4b8d8a8-c54b-40b0-9cb9-e1b100bb1268")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        /*
         * Display Trace
         */
        
        ArrayList<ModelElement> value = new ArrayList<>(); 
        for (Dependency dependency : element.getImpactedDependency ()) {
            if(dependency.isStereotyped("ModelerModule", "trace"))
                value.add(dependency.getImpacted()); 
        }
        
        String valuetab = Utils.getAbsoluteNamesWithSeparator(value);
        if (!valuetab.equals(""))
            table.addConsultProperty(I18nMessageService.getString("Ui.Trace.From"), valuetab);
        
        value = new ArrayList<>(); 
        for (Dependency dependency : element.getDependsOnDependency ()) {
            if(dependency.isStereotyped("ModelerModule", "trace"))
                value.add(dependency.getDependsOn());
        }
        
        valuetab = Utils.getAbsoluteNamesWithSeparator(value);
        if (!valuetab.equals(""))
            table.addConsultProperty(I18nMessageService.getString("Ui.Trace.To"), valuetab);
    }

    @objid ("d185fc0b-9730-4101-8150-1320d0b3eb72")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        // Nothing Todo
    }

}
