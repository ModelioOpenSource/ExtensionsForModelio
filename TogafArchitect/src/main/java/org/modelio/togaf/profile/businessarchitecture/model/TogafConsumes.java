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

import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;

public class TogafConsumes {
	protected Dependency element;

	public TogafConsumes() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createDependency();
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafConsumes");
		// element.setName(ResourceManager.getName("TogafConsumes"));
		this.element.setName("");
	}

	public TogafConsumes(Dependency element) {
		this.element = element;
	}

	public void setParent(ModelElement source, ModelElement destination) {
		this.element.setImpacted(source);
		this.element.setDependsOn(destination);
	}

	public Dependency getElement() {
		return this.element;
	}

	public String gethabilitation() {
		String res = ModelUtils.getTaggedValue("TogafConsumes_habilitation", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void sethabilitation(String value) {
		ModelUtils.addValue("TogafArchitect","TogafConsumes_habilitation", value, this.element);
	}

	public OrganizationParticipant getOrganizationParticipant() {
		return new OrganizationParticipant((Actor) this.element.getImpacted());
	}

	public void addOrganizationParticipant(OrganizationParticipant model) {
		this.element.setImpacted(model.getElement());
	}

	public TogafService getTogafService() {
		return new TogafService((Interface) this.element.getDependsOn());
	}

	public void addTogafService(TogafService model) {
		this.element.setDependsOn(model.getElement());
	}

	public TogafServiceOperation getTogafServiceOperation() {
		return new TogafServiceOperation((Operation) this.element.getDependsOn());
	}

	public void addTogafServiceOperation(TogafServiceOperation model) {
		this.element.setDependsOn(model.getElement());
	}

}
