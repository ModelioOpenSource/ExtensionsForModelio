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

import org.modelio.togaf.profile.utils.ResourceManager;

public enum SystemType {
	SYSTEM("SystemApplicationComponent", ResourceManager.getName("SystemApplicationComponent")), SYSTEMFEDERATION("TogafSystemFederation", ResourceManager.getName("TogafSystemFederation")), ENTREPRISESYSTEM("TogafEnterpriseSystem", ResourceManager.getName("TogafEnterpriseSystem")), APPLICATION(
			"TogafApplication", ResourceManager.getName("TogafApplication"));

	private SystemType(String stereotype, String name) {
		this.name = name;
		this.stereotype = stereotype;
	}

	private String name;
	private String stereotype;

	public String getName() {
		return this.name;
	}

	public String getStereotype() {
		return this.stereotype;
	}

	public static SystemType getTypeByName(String name) {
		if (name.equals(ResourceManager.getName("TogafSystemFederation"))) {
			return SystemType.SYSTEMFEDERATION;
		} else if (name.equals(ResourceManager.getName("TogafEnterpriseSystem"))) {
			return SystemType.ENTREPRISESYSTEM;
		} else if (name.equals(ResourceManager.getName("TogafApplication"))) {
			return SystemType.APPLICATION;
		} else {
			return SystemType.SYSTEM;
		}
	}

	public static SystemType getTypeByStereotype(String stereotype) {
		if (stereotype.equals("TogafSystemFederation")) {
			return SystemType.SYSTEMFEDERATION;
		} else if (stereotype.equals("TogafEnterpriseSystem")) {
			return SystemType.ENTREPRISESYSTEM;
		} else if (stereotype.equals("TogafApplication")) {
			return SystemType.APPLICATION;
		} else {
			return SystemType.SYSTEM;
		}
	}

	public static String[] getTypes() {
		return new String[] { SYSTEM.getName(), SYSTEMFEDERATION.getName(), ENTREPRISESYSTEM.getName(), APPLICATION.getName() };
	}

}
