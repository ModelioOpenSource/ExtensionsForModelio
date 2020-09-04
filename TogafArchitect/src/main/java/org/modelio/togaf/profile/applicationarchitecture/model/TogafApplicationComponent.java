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

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.LogicalDataModel;
import org.modelio.togaf.profile.structure.model.TechnologyArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;
import org.modelio.togaf.properties.TogafComponentLevel;
public class TogafApplicationComponent {
	protected Component element;

	public TogafApplicationComponent() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createComponent();
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafApplicationComponent");
		this.element.setName(ResourceManager.getName("TogafApplicationComponent"));
	}

	public TogafApplicationComponent(Component element) {
		this.element = element;
	}

	public Component getElement() {
		return this.element;
	}

	public void setParent(ModelTree parent) {
		this.element.setOwner(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public String getTogafApplicationComponent_level() {
		String res = ModelUtils.getTaggedValue("TogafApplicationComponent_level", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setTogafApplicationComponent_level(String value) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_level", value, this.element);
	}

	public String getsize() {
		String res = ModelUtils.getTaggedValue("TogafApplicationComponent_size", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setsize(String value) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_size", value, this.element);
	}

	public String getbenefits() {
		String res = ModelUtils.getTaggedValue("TogafApplicationComponent_benefits", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setbenefits(String value) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_benefits", value, this.element);
	}

	public String getcomplexity() {
		String res = ModelUtils.getTaggedValue("TogafApplicationComponent_complexity", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setcomplexity(String value) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_complexity", value, this.element);
	}

	public TechnologyArchitecture getTechnologyArchitecture() {
		return new TechnologyArchitecture((Package) this.element.getOwner());
	}

	public void addTechnologyArchitecture(TechnologyArchitecture model) {
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

	public List<ApplicationArchitectureAttribute> getApplicationArchitectureAttribute() {
		List<ApplicationArchitectureAttribute> res = new java.util.ArrayList<>();
		for (Attribute model_element : this.element.getOwnedAttribute()) {
			res.add(new ApplicationArchitectureAttribute(model_element));
		}
		return res;
	}

	public void addApplicationArchitectureAttribute(ApplicationArchitectureAttribute model) {
		this.element.getOwnedAttribute().add(model.getElement());
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

	public void addTogafSystemUseCaseDiagram(TogafSystemUseCaseDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<LogicalDataModel> getLogicalDataModel() {
		List<LogicalDataModel> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new LogicalDataModel((Package) model_element));
		}
		return res;
	}

	public void addLogicalDataModel(LogicalDataModel model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<ComponentRealization> getOutComponentRealization() {
		List<ComponentRealization> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new ComponentRealization(model_element));
		}
		return res;
	}

	public void addOutComponentRealization(ComponentRealization model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<ComponentRealization> getInComponentRealization() {
		List<ComponentRealization> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new ComponentRealization(model_element));
		}
		return res;
	}

	public void addInComponentRealization(ComponentRealization model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<ApplicationComponentOperation> getApplicationComponentOperation() {
		List<ApplicationComponentOperation> res = new java.util.ArrayList<>();
		for (Operation model_element : this.element.getOwnedOperation()) {
			res.add(new ApplicationComponentOperation(model_element));
		}
		return res;
	}

	public void addApplicationComponentOperation(ApplicationComponentOperation model) {
		this.element.getOwnedOperation().add(model.getElement());
	}

	public void setLevel(TogafComponentLevel level) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponent_level", level.name(), this.element);
	}

}
