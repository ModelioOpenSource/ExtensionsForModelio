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
package org.modelio.togaf.profile.structure.model;

import java.util.List;

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.applicationarchitecture.model.ApplicationArchitectureDomain;
import org.modelio.togaf.profile.applicationarchitecture.model.ServiceData;
import org.modelio.togaf.profile.applicationarchitecture.model.ServiceDataDiagram;
import org.modelio.togaf.profile.applicationarchitecture.model.ServiceDataFragment;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafApplicationCommunicationDiagram;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafApplicationComponentInstance;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafISService;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafSystemUseCaseDiagram;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;


public class ApplicationArchitecture extends Layer {

	public ApplicationArchitecture() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createPackage());
		ModelUtils.setStereotype(this.element, "TogafArchitect","ApplicationArchitecture");
		this.element.setName(ResourceManager.getName("ApplicationArchitecture"));
	}

	public ApplicationArchitecture(Package element) {
		super(element);
	}

	public ApplicationLayer getApplicationLayer() {
		return new ApplicationLayer((Package) this.element.getOwner());
	}

	public void addApplicationLayer(ApplicationLayer model) {
		this.element.setOwner(model.getElement());
	}

	public List<ServiceData> getServiceData() {
		List<ServiceData> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new ServiceData((Class) model_element));
		}
		return res;
	}

	public void addServiceData(ServiceData model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<ServiceDataDiagram> getServiceDataDiagram() {
		List<ServiceDataDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new ServiceDataDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addServiceDataDiagram(ServiceDataDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafISService> getTogafISService() {
		List<TogafISService> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafISService((Interface) model_element));
		}
		return res;
	}

	public void addTogafISService(TogafISService model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TraceabilityDiagram> getTraceabilityDiagram() {
		List<TraceabilityDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TraceabilityDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTraceabilityDiagram(TraceabilityDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafApplicationCommunicationDiagram> getTogafApplicationCommunicationDiagram() {
		List<TogafApplicationCommunicationDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafApplicationCommunicationDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafApplicationCommunicationDiagram(TogafApplicationCommunicationDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public void addTogafSystemUseCaseDiagram(TogafSystemUseCaseDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafApplicationComponentInstance> getTogafApplicationComponentInstance() {
		List<TogafApplicationComponentInstance> res = new java.util.ArrayList<>();
		for (Instance model_element : this.element.getDeclared()) {
			res.add(new TogafApplicationComponentInstance(model_element));
		}
		return res;
	}

	public void addTogafApplicationComponentInstance(TogafApplicationComponentInstance model) {
		this.element.getDeclared().add(model.getElement());
	}

	public List<ServiceDataFragment> getServiceDataFragment() {
		List<ServiceDataFragment> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new ServiceDataFragment((Class) model_element));
		}
		return res;
	}

	public void addServiceDataFragment(ServiceDataFragment model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<ApplicationArchitectureDomain> getApplicationArchitectureDomain() {
		List<ApplicationArchitectureDomain> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new ApplicationArchitectureDomain((Package) model_element));
		}
		return res;
	}

	public void addApplicationArchitectureDomain(ApplicationArchitectureDomain model) {
		this.element.getOwnedElement().add(model.getElement());
	}

}
