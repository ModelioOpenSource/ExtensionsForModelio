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

import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.togaf.profile.applicationarchitecture.propertys.DataBaseApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.EntityApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.InteractionApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.IntermediaryApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.ProcessApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.PublicApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.ServiceApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.SystemApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.TogafApplicationComponentInstanceProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.TogafApplicationComponentProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.TogafApplicationProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.TogafEnterpriseSystemProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.TogafSystemFederationProperty;
import org.modelio.togaf.profile.applicationarchitecture.propertys.UtilityApplicationComponentProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.HeadquarterLocationProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.IOFlowProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafBusinessCapabilityProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafBusinessServiceProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafConsumesProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafExternalRoleProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafFunctionProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafISServiceProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafInternalRoleProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafLocationProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafProcessProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafRoleProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafServiceContractProperty;
import org.modelio.togaf.profile.businessarchitecture.propertys.TogafServiceProperty;
import org.modelio.togaf.profile.structure.propertys.DefaultProperty;
import org.modelio.togaf.profile.technologyarchitecture.propertys.ConnexionProperty;
import org.modelio.togaf.profile.utils.IPropertyContent;

public class PropertyManager {

	public static IPropertyContent getPalette(ModelElement element) {
		if (element.isStereotyped("TogafArchitect","SystemApplicationComponent")) {
			return new SystemApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","ServiceApplicationComponent")) {
			return new ServiceApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafApplicationComponentInstance")) {
			return new TogafApplicationComponentInstanceProperty();
		}
		if (element.isStereotyped("TogafArchitect","DataBaseApplicationComponent")) {
			return new DataBaseApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafApplication")) {
			return new TogafApplicationProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafEnterpriseSystem")) {
			return new TogafEnterpriseSystemProperty();
		}
		if (element.isStereotyped("TogafArchitect","TogafSystemFederation")) {
			return new TogafSystemFederationProperty();
		}
		if (element.isStereotyped("TogafArchitect","EntityApplicationComponent")) {
			return new EntityApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","InteractionApplicationComponent")) {
			return new InteractionApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","IntermediaryApplicationComponent")) {
			return new IntermediaryApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","ProcessApplicationComponent")) {
			return new ProcessApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","UtilityApplicationComponent")) {
			return new UtilityApplicationComponentProperty();
		}
		if (element.isStereotyped("TogafArchitect","PublicApplicationComponent")) {
			return new PublicApplicationComponentProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafProcess")) {
			return new TogafProcessProperty();
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
		if (element.isStereotyped("TogafArchitect","Connexion")) {
			return new ConnexionProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafRole")) {
			return new TogafRoleProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafISService")) {
			return new TogafISServiceProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafService")) {
			return new TogafServiceProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafApplicationComponent")) {
			return new TogafApplicationComponentProperty();
		}

		if (element.isStereotyped("TogafArchitect","TogafServiceContract")) {
			return new TogafServiceContractProperty();
		}

		if (element.getExtension().size() > 0) {
			return new TogafElementProperty();
		}

		if (element instanceof Instance) {
			return new InstanceProperty();
		}
		return new DefaultProperty();
	}

}
