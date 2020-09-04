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
package org.modelio.togaf.profile.businessarchitecture.model;

import java.util.List;

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafOrganizationUnit extends BusinessArchitecture {

	public TogafOrganizationUnit() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createPackage());
		ModelUtils.setStereotype(this.element,"TogafArchitect", "TogafOrganizationUnit");
		this.element.setName(ResourceManager.getName("TogafOrganizationUnit"));
	}

	public TogafOrganizationUnit(Package element) {
		super(element);
	}

	public BusinessArchitecture getBusinessArchitecture() {
		return new BusinessArchitecture((Package) this.element.getOwner());
	}

	public void addBusinessArchitecture(BusinessArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public List<Responsible> getResponsible() {
		List<Responsible> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Responsible(model_element));
		}
		return res;
	}

	public void addResponsible(Responsible model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<Participates> getParticipates() {
		List<Participates> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new Participates(model_element));
		}
		return res;
	}

	public void addParticipates(Participates model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<Initiator> getInitiator() {
		List<Initiator> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new Initiator(model_element));
		}
		return res;
	}

	public void addInitiator(Initiator model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<Owner> getOwner() {
		List<Owner> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new Owner(model_element));
		}
		return res;
	}

	public void addOwner(Owner model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

}
