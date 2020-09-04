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
package org.modelio.togaf.profile.businessarchitecture.propertys;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.profile.utils.IPropertyContent;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafProcessProperty implements IPropertyContent {

	@Override
	public void changeProperty(ModelElement element, int row, String value) {
		if (row == 1)
			element.setName(value);

		if (row == 2) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_criticality", value, element);
		} else if (row == 3) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_isAutomated", value, element);
		} else if (row == 4) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_volumetrics", value, element);
		} else if (row == 5) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_duration", value, element);
		} else if (row == 6) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_KPI", value, element);
		} else if (row == 7) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_used_resources", value, element);
		} else if (row == 8) {
			ModelUtils.addValue("TogafArchitect","TogafProcess_kind", value, element);
		}
	}

	@Override
	public void update(ModelElement element, IModulePropertyTable table) {
		table.addProperty(ResourceManager.getPropertyName("Name"), element.getName());

		String value_criticality = ModelUtils.getTaggedValue("TogafProcess_criticality", element);
		if (value_criticality.equals("")) {
			value_criticality = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_criticality"), value_criticality);
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_isAutomated"), ModelUtils.hasTaggedValue("TogafProcess_isAutomated", element));
		String value_volumetrics = ModelUtils.getTaggedValue("TogafProcess_volumetrics", element);
		if (value_volumetrics.equals("")) {
			value_volumetrics = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_volumetrics"), value_volumetrics);

		String value_duration = ModelUtils.getTaggedValue("TogafProcess_duration", element);
		if (value_duration.equals("")) {
			value_duration = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_duration"), value_duration);

		String value_KPI = ModelUtils.getTaggedValue("TogafProcess_KPI", element);
		if (value_KPI.equals("")) {
			value_KPI = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_KPI"), value_KPI);

		String value_used_resources = ModelUtils.getTaggedValue("TogafProcess_used_resources", element);
		if (value_used_resources.equals("")) {
			value_used_resources = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_used_resources"), value_used_resources);

		String value_kind = ModelUtils.getTaggedValue("TogafProcess_kind", element);
		if (value_kind.equals("")) {
			value_kind = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafProcess_kind"), value_kind, ProcessKind.getValues());
	}

}
