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

import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.structure.model.BusinessEntities;
import org.modelio.togaf.profile.utils.ModelUtils;

public class IOFlow {
	protected Dependency element;

	public IOFlow() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createDependency();
		ModelUtils.setStereotype(this.element, "TogafArchitect","IOFlow");
		// element.setName(ResourceManager.getName("IOFlow"));
		this.element.setName("");
	}

	public IOFlow(Dependency element) {
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
		String res = ModelUtils.getTaggedValue("IOFlow_habilitation", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void sethabilitation(String value) {
		ModelUtils.addValue("TogafArchitect","IOFlow_habilitation", value, this.element);
	}

	public BusinessEntities getSourceBusinessEntities() {
		return new BusinessEntities((Package) this.element.getImpacted());
	}

	public void addSourceBusinessEntities(BusinessEntities model) {
		this.element.setImpacted(model.getElement());
	}

	public BusinessEntities getTargetBusinessEntities() {
		return new BusinessEntities((Package) this.element.getDependsOn());
	}

	public void addTargetBusinessEntities(BusinessEntities model) {
		this.element.setDependsOn(model.getElement());
	}

	public BusinessArchitecture getSourceBusinessArchitecture() {
		return new BusinessArchitecture((Package) this.element.getImpacted());
	}

	public void addSourceBusinessArchitecture(BusinessArchitecture model) {
		this.element.setImpacted(model.getElement());
	}

	public BusinessArchitecture getTargetBusinessArchitecture() {
		return new BusinessArchitecture((Package) this.element.getDependsOn());
	}

	public void addTargetBusinessArchitecture(BusinessArchitecture model) {
		this.element.setDependsOn(model.getElement());
	}

	public TogafEvent getSourceTogafEvent() {
		return new TogafEvent((Signal) this.element.getImpacted());
	}

	public void addSourceTogafEvent(TogafEvent model) {
		this.element.setImpacted(model.getElement());
	}

	public TogafEvent getTargetTogafEvent() {
		return new TogafEvent((Signal) this.element.getDependsOn());
	}

	public void addTargetTogafEvent(TogafEvent model) {
		this.element.setDependsOn(model.getElement());
	}

	public OrganizationParticipant getSourceOrganizationParticipant() {
		return new OrganizationParticipant((Actor) this.element.getImpacted());
	}

	public void addSourceOrganizationParticipant(OrganizationParticipant model) {
		this.element.setImpacted(model.getElement());
	}

	public OrganizationParticipant getTargetOrganizationParticipant() {
		return new OrganizationParticipant((Actor) this.element.getDependsOn());
	}

	public void addTargetOrganizationParticipant(OrganizationParticipant model) {
		this.element.setDependsOn(model.getElement());
	}

	public TogafProcess getTargetTogafProcess() {
		return new TogafProcess((BpmnProcess) this.element.getDependsOn());
	}

	public void addTargetTogafProcess(TogafProcess model) {
		this.element.setDependsOn(model.getElement());
	}

	public TogafProcess getSourceTogafProcess() {
		return new TogafProcess((BpmnProcess) this.element.getImpacted());
	}

	public void addSourceTogafProcess(TogafProcess model) {
		this.element.setImpacted(model.getElement());
	}

	public TogafService getSourceTogafService() {
		return new TogafService((Interface) this.element.getImpacted());
	}

	public void addSourceTogafService(TogafService model) {
		this.element.setImpacted(model.getElement());
	}

	public TogafService getInTogafService() {
		return new TogafService((Interface) this.element.getDependsOn());
	}

	public void addInTogafService(TogafService model) {
		this.element.setDependsOn(model.getElement());
	}

	public TogafProduct getSourceTogafProduct() {
		return new TogafProduct((Signal) this.element.getImpacted());
	}

	public void addSourceTogafProduct(TogafProduct model) {
		this.element.setImpacted(model.getElement());
	}

	public TogafProduct getInTogafProduct() {
		return new TogafProduct((Signal) this.element.getDependsOn());
	}

	public void addInTogafProduct(TogafProduct model) {
		this.element.setDependsOn(model.getElement());
	}

}
