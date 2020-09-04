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

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.profile.utils.IPropertyContent;

public class PropertyManager {

	public static IPropertyContent getPalette(ModelElement element) {
		if (element.isStereotyped("TogafArchitect","TogafRole")) {
			return new TogafRoleProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafISService")) {
			return new TogafISServiceProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafProcess")) {
			return new TogafProcessProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafServiceContract")) {
			return new TogafServiceContractProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafExternalRole")) {
			return new TogafExternalRoleProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafInternalRole")) {
			return new TogafInternalRoleProperty();
		}
		if (element.isStereotyped("TogafArchitect","IOFlow")) {
			return new IOFlowProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafBusinessService")) {
			return new TogafBusinessServiceProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafConsumes")) {
			return new TogafConsumesProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafFunction")) {
			return new TogafFunctionProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafBusinessCapability")) {
			return new TogafBusinessCapabilityProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafLocation")) {
			return new TogafLocationProperty();
		}
		if (element.isStereotyped("TogafArchitect","HeadquarterLocation")) {
			return new HeadquarterLocationProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafService")) {
			return new TogafServiceProperty();
		}
		return new DefaultProperty();
	}

}
