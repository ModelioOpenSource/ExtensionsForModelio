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
package org.modelio.togaf.properties;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.profile.utils.IPropertyContent;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafElementProperty implements IPropertyContent {

	@Override
	public void changeProperty(ModelElement element, int row, String value) {
		if (row == 1)
			element.setName(value);

		if (row == 2) {
			ModelUtils.addValue("TogafArchitect","TogafElement_reference", value, element);
		} else if (row == 3) {
			ModelUtils.addValue("TogafArchitect","TogafElement_category", value, element);
		} else if (row == 4) {
			ModelUtils.addValue("TogafArchitect","TogafElement_source", value, element);
		} else if (row == 5) {
			ModelUtils.addValue("TogafArchitect","TogafElement_owner", value, element);
		}
	}

	@Override
	public void update(ModelElement element, IModulePropertyTable table) {
		table.addProperty(ResourceManager.getPropertyName("Name"), element.getName());

		String value_reference = ModelUtils.getTaggedValue("TogafElement_reference", element);
		if (value_reference.equals("")) {
			value_reference = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafElement_reference"), value_reference);

		String value_category = ModelUtils.getTaggedValue("TogafElement_category", element);
		if (value_category.equals("")) {
			value_category = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafElement_category"), value_category);

		String value_source = ModelUtils.getTaggedValue("TogafElement_source", element);
		if (value_source.equals("")) {
			value_source = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafElement_source"), value_source);

		String value_owner = ModelUtils.getTaggedValue("TogafElement_owner", element);
		if (value_owner.equals("")) {
			value_owner = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafElement_owner"), value_owner);
	}

}
