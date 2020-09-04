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

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.profile.utils.IPropertyContent;

public class PropertyManager {

	public static IPropertyContent getPalette(ModelElement element) {
		if (element.isStereotyped("TogafArchitect","TogafApplicationComponent")) {
			return new TogafApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafApplicationComponentInstance")) {
			return new TogafApplicationComponentInstanceProperty();
		}
		if (element.isStereotyped("TogafArchitect","SystemApplicationComponent")) {
			return new SystemApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","ServiceApplicationComponent")) {
			return new ServiceApplicationComponentProperty();
		}

		// if(element.isStereotyped("TogafArchitect","DataBaseApplicationComponent")){
		// return new DataBaseApplicationComponentProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","TogafApplication")){
		// return new TogafApplicationProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","TogafEnterpriseSystem")){
		// return new TogafEnterpriseSystemProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","TogafSystemFederation")){
		// return new TogafSystemFederationProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","EntityApplicationComponent")){
		// return new EntityApplicationComponentProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","InteractionApplicationComponent")){
		// return new InteractionApplicationComponentProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","IntermediaryApplicationComponent")){
		// return new IntermediaryApplicationComponentProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","ProcessApplicationComponent")){
		// return new ProcessApplicationComponentProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","UtilityApplicationComponent")){
		// return new UtilityApplicationComponentProperty();
		// }
		// if(element.isStereotyped("TogafArchitect","PublicApplicationComponent")){
		// return new PublicApplicationComponentProperty();
		// }
		return new DefaultProperty();
	}

}
