/** 
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements.  See the NOTICE file 
 * distributed with this work for additional information 
 * regarding copyright ownership.  The ASF licenses this file 
 * to you under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance 
 * with the License.  You may obtain a copy of the License at 
 * 
 *	http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *	Unless required by applicable law or agreed to in writing, 
 *	software distributed under the License is distributed on an 
 *	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY 
 *	KIND, either express or implied.  See the License for the 
 *	specific language governing permissions and limitations 
 *	under the License. 
 * 
 * 
 * @package    org.modelio.togaf. 
 * @author     Modelio 
 * @license    http://www.apache.org/licenses/LICENSE-2.0 
 * @version  1.0.00 
 **/
package org.modelio.togaf.profile.applicationarchitecture.propertys;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.IPropertyContent;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafApplicationComponentInstanceProperty implements IPropertyContent {

    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if (row == 1)
            element.setName(value);

        if (row == 2) {

            Stereotype sterType = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions()
                    .getStereotype("TogafArchitect", "TogafApplicationComponentInstance", element.getMClass());
            element.getExtension().remove(sterType);

        }

        if (row == 3) {
            ModelUtils.addValue("TogafArchitect","TogafApplicationComponentInstance_occurencesNumber", value, element);
        }
        if (row == 4) {
            ModelUtils.addValue("TogafArchitect","TogafApplicationComponentInstance_capacity", value, element);
        }
    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(ResourceManager.getPropertyName("Name"), element.getName());

        table.addProperty("application instance", element.isStereotyped("TogafArchitect", "TogafApplicationComponentInstance"));

        String value_occurencesNumber = ModelUtils.getTaggedValue("TogafApplicationComponentInstance_occurencesNumber", element);
        if (value_occurencesNumber.equals("")) {
            value_occurencesNumber = "";
        }
        table.addProperty(ResourceManager.getPropertyName("TogafApplicationComponentInstance_occurencesNumber"),
                value_occurencesNumber);
        String value_capacity = ModelUtils.getTaggedValue("TogafApplicationComponentInstance_capacity", element);
        if (value_capacity.equals("")) {
            value_capacity = "";
        }
        table.addProperty(ResourceManager.getPropertyName("TogafApplicationComponentInstance_capacity"), value_capacity);
    }

}
