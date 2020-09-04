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

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class OrganizationParticipant {
	protected Actor element;

	public OrganizationParticipant() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createActor();
		ModelUtils.setStereotype(this.element, "TogafArchitect","OrganizationParticipant");
		this.element.setName(ResourceManager.getName("OrganizationParticipant"));
	}

	public OrganizationParticipant(Actor element) {
		this.element = element;
	}

	public Actor getElement() {
		return this.element;
	}

	public void setParent(ModelTree parent) {
		this.element.setOwner(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public BusinessArchitecture getBusinessArchitecture() {
		return new BusinessArchitecture((Package) this.element.getOwner());
	}

	public void addBusinessArchitecture(BusinessArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public List<Communicates> getinCommunicates() {
		List<Communicates> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Communicates(model_element));
		}
		return res;
	}

	public void addinCommunicates(Communicates model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<Communicates> getOutCommunicates() {
		List<Communicates> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new Communicates(model_element));
		}
		return res;
	}

	public void addOutCommunicates(Communicates model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<Initiator> getInInitiator() {
		List<Initiator> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Initiator(model_element));
		}
		return res;
	}

	public void addInInitiator(Initiator model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<Initiator> getOutInitiator() {
		List<Initiator> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new Initiator(model_element));
		}
		return res;
	}

	public void addOutInitiator(Initiator model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<Responsible> getInResponsible() {
		List<Responsible> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Responsible(model_element));
		}
		return res;
	}

	public void addInResponsible(Responsible model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<Responsible> getOutResponsible() {
		List<Responsible> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new Responsible(model_element));
		}
		return res;
	}

	public void addOutResponsible(Responsible model) {
		this.element.getDependsOnDependency().add(model.getElement());
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

	public List<IOFlow> getOutIOFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addOutIOFlow(IOFlow model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<IOFlow> getInIOFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addInIOFlow(IOFlow model) {
		this.element.getImpactedDependency().add(model.getElement());
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

	public List<TogafParticipantDecomposition> getOutTogafParticipantDecomposition() {
		List<TogafParticipantDecomposition> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new TogafParticipantDecomposition(model_element));
		}
		return res;
	}

	public void addOutTogafParticipantDecomposition(TogafParticipantDecomposition model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<TogafConsumes> getTogafConsumes() {
		List<TogafConsumes> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new TogafConsumes(model_element));
		}
		return res;
	}

	public void addTogafConsumes(TogafConsumes model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<TogafOrganizationRoleDiagram> getOrganizationRoleDiagram() {
		List<TogafOrganizationRoleDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafOrganizationRoleDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addOrganizationRoleDiagram(TogafOrganizationRoleDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafParticipantDecomposition> getTargetTogafParticipantDecomposition() {
		List<TogafParticipantDecomposition> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new TogafParticipantDecomposition(model_element));
		}
		return res;
	}

	public void addTargetTogafParticipantDecomposition(TogafParticipantDecomposition model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

}
