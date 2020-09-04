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
package org.modelio.togaf.profile.applicationarchitecture.model;

import java.util.List;

import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.ApplicationArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;
public class ServiceDataFragment {
	protected Class element;

	public ServiceDataFragment() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createClass();
		ModelUtils.setStereotype(this.element, "TogafArchitect","ServiceDataFragment");
		this.element.setName(ResourceManager.getName("ServiceDataFragment"));
	}

	public ServiceDataFragment(Class element) {
		this.element = element;
	}

	public Class getElement() {
		return this.element;
	}

	public void setParent(ModelTree parent) {
		this.element.setOwner(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public ApplicationArchitecture getApplicationArchitecture() {
		return new ApplicationArchitecture((Package) this.element.getOwner());
	}

	public void addApplicationArchitecture(ApplicationArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public ServiceData getServiceData() {
		return new ServiceData((Class) this.element.getOwner());
	}

	public void addServiceData(ServiceData model) {
		this.element.setOwner(model.getElement());
	}

	public List<ApplicationArchitectureAttribute> getApplicationArchitectureAttribute() {
		List<ApplicationArchitectureAttribute> res = new java.util.ArrayList<>();
		for (Attribute model_element : this.element.getOwnedAttribute()) {
			res.add(new ApplicationArchitectureAttribute( model_element));
		}
		return res;
	}

	public void addApplicationArchitectureAttribute(ApplicationArchitectureAttribute model) {
		this.element.getOwnedAttribute().add(model.getElement());
	}

	public List<ServiceDataFragment> getFragment() {
		List<ServiceDataFragment> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new ServiceDataFragment((Class) model_element));
		}
		return res;
	}

	public void addFragment(ServiceDataFragment model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public ServiceDataFragment getParentFragment() {
		return new ServiceDataFragment((Class) this.element.getOwner());
	}

	public void addParentFragment(ServiceDataFragment model) {
		this.element.setOwner(model.getElement());
	}

}
