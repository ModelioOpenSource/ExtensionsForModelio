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

public enum ServiceType {
	SERVICE("ServiceApplicationComponent", ResourceManager.getName("ServiceApplicationComponent")), ENTITY("EntityApplicationComponent", ResourceManager.getName("EntityApplicationComponent")), INTERACTION("InteractionApplicationComponent", ResourceManager.getName("InteractionApplicationComponent")), INTERMEDIARY(
			"IntermediaryApplicationComponent", ResourceManager.getName("IntermediaryApplicationComponent")), PROCESS("ProcessApplicationComponent", ResourceManager.getName("ProcessApplicationComponent")), PUBLIC("PublicApplicationComponent", ResourceManager.getName("PublicApplicationComponent")), UTILITY(
			"UtilityApplicationComponent", ResourceManager.getName("UtilityApplicationComponent"));

	private ServiceType(String stereotype, String name) {
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

	public static ServiceType getTypeByName(String name) {
		if (name.equals(ResourceManager.getName("EntityApplicationComponent"))) {
			return ENTITY;
		} else if (name.equals(ResourceManager.getName("InteractionApplicationComponent"))) {
			return INTERACTION;
		} else if (name.equals(ResourceManager.getName("IntermediaryApplicationComponent"))) {
			return INTERMEDIARY;
		} else if (name.equals(ResourceManager.getName("ProcessApplicationComponent"))) {
			return PROCESS;
		} else if (name.equals(ResourceManager.getName("PublicApplicationComponent"))) {
			return PUBLIC;
		} else if (name.equals(ResourceManager.getName("UtilityApplicationComponent"))) {
			return UTILITY;
		} else {
			return SERVICE;
		}
	}

	public static ServiceType getTypeByStereotype(String stereotype) {
		if (stereotype.equals("EntityApplicationComponent")) {
			return ENTITY;
		} else if (stereotype.equals("InteractionApplicationComponent")) {
			return INTERACTION;
		} else if (stereotype.equals("IntermediaryApplicationComponent")) {
			return INTERMEDIARY;
		} else if (stereotype.equals("ProcessApplicationComponent")) {
			return PROCESS;
		} else if (stereotype.equals("PublicApplicationComponent")) {
			return PUBLIC;
		} else if (stereotype.equals("UtilityApplicationComponent")) {
			return UTILITY;
		} else {
			return SERVICE;
		}
	}

	public static String[] getTypes() {
		return new String[] { ResourceManager.getName("ServiceApplicationComponent"), ResourceManager.getName("InteractionApplicationComponent"), ResourceManager.getName("ProcessApplicationComponent"), ResourceManager.getName("IntermediaryApplicationComponent"),
				ResourceManager.getName("PublicApplicationComponent"), ResourceManager.getName("UtilityApplicationComponent"), ResourceManager.getName("EntityApplicationComponent") };
	}

}
