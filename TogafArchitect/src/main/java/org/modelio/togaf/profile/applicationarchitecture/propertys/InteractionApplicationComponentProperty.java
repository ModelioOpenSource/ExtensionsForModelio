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
import org.modelio.togaf.profile.utils.IPropertyContent;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;
import org.modelio.togaf.properties.TogafComponentLevel;

public class InteractionApplicationComponentProperty implements IPropertyContent {

	@Override
	public void changeProperty(ModelElement element, int row, String value) {
		if (row == 1)
			element.setName(value);

		if (row == 2) {
			ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_level", value, element);
		}
		if (row == 3) {
			ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_size", value, element);
		}
		if (row == 4) {
			ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_benefits", value, element);
		}
		if (row == 5) {
			ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_complexity", value, element);
		}
	}

	@Override
	public void update(ModelElement element, IModulePropertyTable table) {
		table.addProperty(ResourceManager.getPropertyName("Name"), element.getName());

		String value_TogafApplicationComponent_level = ModelUtils.getTaggedValue("TogafApplicationComponent_level", element);
		if (value_TogafApplicationComponent_level.equals("")) {
			value_TogafApplicationComponent_level = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafApplicationComponent_TogafApplicationComponent_level"), value_TogafApplicationComponent_level, TogafComponentLevel.getValues());
		String value_size = ModelUtils.getTaggedValue("TogafApplicationComponent_size", element);
		if (value_size.equals("")) {
			value_size = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafApplicationComponent_size"), value_size);
		String value_benefits = ModelUtils.getTaggedValue("TogafApplicationComponent_benefits", element);
		if (value_benefits.equals("")) {
			value_benefits = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafApplicationComponent_benefits"), value_benefits);
		String value_complexity = ModelUtils.getTaggedValue("TogafApplicationComponent_complexity", element);
		if (value_complexity.equals("")) {
			value_complexity = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafApplicationComponent_complexity"), value_complexity);
	}

}
