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

import java.util.ArrayList;
import java.util.List;

import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.profile.utils.IPropertyContent;
import org.modelio.togaf.profile.utils.ResourceManager;
import org.modelio.togaf.properties.HabilitationKind;

public class TogafConsumesProperty implements IPropertyContent {

	@Override
	public void changeProperty(ModelElement element, int row, String value) {
		try {
			List<String> news_params = new ArrayList<>();
			news_params.add(HabilitationKind.getHabilitationKind(value).getValue());		
			element.removeTags("TogafArchitect", "IOFlow_habilitation");
			
			element.putTagValues( "TogafArchitect", "IOFlow_habilitation", news_params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ModelElement element, IModulePropertyTable table) {
		List<String> params = element.getTagValues("TogafArchitect","IOFlow_habilitation");
		String value = "";
		if (params.size() > 0) {
			value = params.get(0);
			if (HabilitationKind.getHabilitationKind(value) == HabilitationKind.none) {
				value = "none";
			}
		}
		table.addProperty(ResourceManager.getPropertyName("IOFlow_habilitation"), value, HabilitationKind.getValues());
	}

}
