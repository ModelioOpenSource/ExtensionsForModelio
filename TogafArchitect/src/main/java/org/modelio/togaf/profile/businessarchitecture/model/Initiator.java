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
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;

public class Initiator {
	protected Dependency element;

	public Initiator() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createDependency();
		ModelUtils.setStereotype(this.element, "TogafArchitect","Initiator");
		// element.setName(ResourceManager.getName("Initiator"));
		this.element.setName("");
	}

	public Initiator(Dependency element) {
		this.element = element;
	}

	public void setParent(ModelElement source, ModelElement destination) {
		this.element.setImpacted(source);
		this.element.setDependsOn(destination);
	}

	public Dependency getElement() {
		return this.element;
	}

	public TogafProcess getTogafProcess() {
		return new TogafProcess((BpmnProcess) this.element.getDependsOn());
	}

	public void addTogafProcess(TogafProcess model) {
		this.element.setDependsOn(model.getElement());
	}

	public OrganizationParticipant getTargetOrganizationParticipant() {
		return new OrganizationParticipant((Actor) this.element.getDependsOn());
	}

	public void addTargetOrganizationParticipant(OrganizationParticipant model) {
		this.element.setDependsOn(model.getElement());
	}

	public OrganizationParticipant getSourceOrganizationParticipant() {
		return new OrganizationParticipant((Actor) this.element.getImpacted());
	}

	public void addSourceOrganizationParticipant(OrganizationParticipant model) {
		this.element.setImpacted(model.getElement());
	}

	public TogafOrganizationUnit getTogafOrganizationUnit() {
		return new TogafOrganizationUnit((Package) this.element.getImpacted());
	}

	public void addTogafOrganizationUnit(TogafOrganizationUnit model) {
		this.element.setImpacted(model.getElement());
	}

}
