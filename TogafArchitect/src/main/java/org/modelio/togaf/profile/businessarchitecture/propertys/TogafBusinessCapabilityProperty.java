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
import org.modelio.togaf.properties.TogafServiceKind;

public class TogafBusinessCapabilityProperty implements IPropertyContent {

	@Override
	public void changeProperty(ModelElement element, int row, String value) {
		if (row == 1)
			element.setName(value);

		if (row == 2) {
			ModelUtils.addValue("TogafArchitect","TogafService_type", value, element);
		}
		if (row == 3) {
			ModelUtils.addValue("TogafArchitect","TogafService_businessImportance", value, element);
		}
		if (row == 4) {
			ModelUtils.addValue("TogafArchitect","TogafService_QOS", value, element);
		}
		if (row == 5) {
			ModelUtils.addValue("TogafArchitect","TogafService_SLA", value, element);
		}
		if (row == 6) {
			ModelUtils.addValue("TogafArchitect","TogafService_troughput", value, element);
		}
		if (row == 7) {
			ModelUtils.addValue("TogafArchitect","TogafService_troughputPeriod", value, element);
		}
		if (row == 8) {
			ModelUtils.addValue("TogafArchitect","TogafService_growth", value, element);
		}
		if (row == 9) {
			ModelUtils.addValue("TogafArchitect","TogafService_growthPeriod", value, element);
		}
		if (row == 10) {
			ModelUtils.addValue("TogafArchitect","TogafService_serviceTimes", value, element);
		}
		if (row == 11) {
			ModelUtils.addValue("TogafArchitect","TogafService_peakProfileShortTerm", value, element);
		}
		if (row == 12) {
			ModelUtils.addValue("TogafArchitect","TogafService_peakProfileLongTerm", value, element);
		}
		if (row == 13) {
			ModelUtils.addValue("TogafArchitect","TogafService_RACI", value, element);
		}

		if (row == 14) {
			try {
				element.putNoteContent("TogafArchitect", "TogafService_contract", value);
			} catch (Exception e ) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(ModelElement element, IModulePropertyTable table) {
		table.addProperty(ResourceManager.getPropertyName("Name"), element.getName());

		String value_type = ModelUtils.getTaggedValue("TogafService_type", element);
		if (value_type.equals("")) {
			value_type = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_type"), value_type, TogafServiceKind.getValues());
		String value_businessImportance = ModelUtils.getTaggedValue("TogafService_businessImportance", element);
		if (value_businessImportance.equals("")) {
			value_businessImportance = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_businessImportance"), value_businessImportance);
		String value_QOS = ModelUtils.getTaggedValue("TogafService_QOS", element);
		if (value_QOS.equals("")) {
			value_QOS = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_QOS"), value_QOS);
		String value_SLA = ModelUtils.getTaggedValue("TogafService_SLA", element);
		if (value_SLA.equals("")) {
			value_SLA = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_SLA"), value_SLA);
		String value_troughput = ModelUtils.getTaggedValue("TogafService_troughput", element);
		if (value_troughput.equals("")) {
			value_troughput = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_troughput"), value_troughput);
		String value_troughputPeriod = ModelUtils.getTaggedValue("TogafService_troughputPeriod", element);
		if (value_troughputPeriod.equals("")) {
			value_troughputPeriod = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_troughputPeriod"), value_troughputPeriod);
		String value_growth = ModelUtils.getTaggedValue("TogafService_growth", element);
		if (value_growth.equals("")) {
			value_growth = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_growth"), value_growth);
		String value_growthPeriod = ModelUtils.getTaggedValue("TogafService_growthPeriod", element);
		if (value_growthPeriod.equals("")) {
			value_growthPeriod = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_growthPeriod"), value_growthPeriod);
		String value_serviceTimes = ModelUtils.getTaggedValue("TogafService_serviceTimes", element);
		if (value_serviceTimes.equals("")) {
			value_serviceTimes = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_serviceTimes"), value_serviceTimes);
		String value_peakProfileShortTerm = ModelUtils.getTaggedValue("TogafService_peakProfileShortTerm", element);
		if (value_peakProfileShortTerm.equals("")) {
			value_peakProfileShortTerm = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_peakProfileShortTerm"), value_peakProfileShortTerm);
		String value_peakProfileLongTerm = ModelUtils.getTaggedValue("TogafService_peakProfileLongTerm", element);
		if (value_peakProfileLongTerm.equals("")) {
			value_peakProfileLongTerm = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_peakProfileLongTerm"), value_peakProfileLongTerm);
		String value_RACI = ModelUtils.getTaggedValue("TogafService_RACI", element);
		if (value_RACI.equals("")) {
			value_RACI = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_RACI"), value_RACI);

		String contract = element.getNoteContent("TogafArchitect","TogafService_contract");
		if (contract == null) {
			contract = "";
		}
		table.addProperty(ResourceManager.getPropertyName("TogafService_contract"), contract);
	}

}
