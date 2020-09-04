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

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.AbstractModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.profile.utils.IPropertyContent;
import org.modelio.vcore.smkernel.mapi.MObject;

public class TogafPropertyPage extends AbstractModulePropertyPage {

	public TogafPropertyPage(IModule module, String name, String label, String bitmap) {
		super(module, name, label, bitmap);
	}

	/**
	 * This method is called when a value has been edited in the property box in
	 * the 'row' row. The 'selectedElements' parameter contains the list of the
	 * currently selected elements. The 'row' parameter is the row number of the
	 * modified value. The 'value' parameter is the new value the user has set
	 * to the given row.
	 */
	@Override
	public void changeProperty(List<MObject> selectedElements, int row, String value) {
		if (selectedElements.size() > 0 && selectedElements.get(0) instanceof ModelElement) {
			IPropertyContent content = PropertyManager.getPalette((ModelElement) selectedElements.get(0));
			content.changeProperty((ModelElement) selectedElements.get(0), row, value);
		}
	}

	/**
	 * This method is called when the current selection changes and the property
	 * box content requires an update. The 'selectedElements' parameter contains
	 * the list of the newly selected elements. The 'table' parameter is the
	 * table that must be filled with the updated contents of the property box
	 * before returning.
	 */
	@Override
	public void update(List<MObject> selectedElements, IModulePropertyTable table) {
		if (selectedElements.size() > 0 && selectedElements.get(0) instanceof ModelElement) {
			IPropertyContent content = PropertyManager.getPalette((ModelElement) selectedElements.get(0));
			content.update((ModelElement) selectedElements.get(0), table);
		}
	}

}
