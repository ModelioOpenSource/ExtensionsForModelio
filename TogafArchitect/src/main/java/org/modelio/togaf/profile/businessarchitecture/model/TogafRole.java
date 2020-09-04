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

import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafRole extends OrganizationParticipant {

	public TogafRole() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createActor());
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafRole");
		this.element.setName(ResourceManager.getName("TogafRole"));
	}

	public TogafRole(Actor element) {
		super(element);
	}

	public String getSkillsRequirements() {
		String res = ModelUtils.getTaggedValue("TogafRole_SkillsRequirements", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setSkillsRequirements(String value) {
		ModelUtils.addValue("TogafArchitect","TogafRole_SkillsRequirements", value, this.element);
	}

	public List<Assumes> getAssumes() {
		List<Assumes> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Assumes(model_element));
		}
		return res;
	}

	public void addAssumes(Assumes model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

}
