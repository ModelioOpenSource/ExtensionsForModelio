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
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessarchitecture.model.BusinessOrganizationDomain;
import org.modelio.togaf.profile.businessarchitecture.model.IOFlow;
import org.modelio.togaf.profile.businessarchitecture.model.OrganizationParticipant;
import org.modelio.togaf.profile.businessarchitecture.model.TogafBusinessService;
import org.modelio.togaf.profile.businessarchitecture.model.TogafBusinessServiceInformationDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafBusinessUseCaseDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafDataSecurityDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafEvent;
import org.modelio.togaf.profile.businessarchitecture.model.TogafEventDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafFunction;
import org.modelio.togaf.profile.businessarchitecture.model.TogafFunctionalDecompositionDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafGoalObjectiveServiceDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafLocation;
import org.modelio.togaf.profile.businessarchitecture.model.TogafOrganizationDecompositionDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafOrganizationRoleDiagram;
import org.modelio.togaf.profile.businessarchitecture.model.TogafOrganizationUnit;
import org.modelio.togaf.profile.businessarchitecture.model.TogafProcess;
import org.modelio.togaf.profile.businessarchitecture.model.TogafProduct;
import org.modelio.togaf.profile.businessarchitecture.model.TogafValueChainDiagram;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class BusinessArchitecture extends Layer {

	public BusinessArchitecture() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createPackage());
		ModelUtils.setStereotype(this.element, "TogafArchitect","BusinessArchitecture");
		this.element.setName(ResourceManager.getName("BusinessArchitecture"));
	}

	public BusinessArchitecture(Package element) {
		super(element);
	}

	public BusinessLayer getBusinessLayer() {
		return new BusinessLayer((Package) this.element.getOwner());
	}

	public void addBusinessLayer(BusinessLayer model) {
		this.element.setOwner(model.getElement());
	}

	public List<BusinessOrganizationDomain> getBusinessOrganizationDomain() {
		List<BusinessOrganizationDomain> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new BusinessOrganizationDomain((Package) model_element));
		}
		return res;
	}

	public void addBusinessOrganizationDomain(BusinessOrganizationDomain model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafEvent> getTogafEvent() {
		List<TogafEvent> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafEvent((Signal) model_element));
		}
		return res;
	}

	public void addTogafEvent(TogafEvent model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public void addTogafBusinessUseCaseDiagram(TogafBusinessUseCaseDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafEventDiagram> getTogafEventDiagram() {
		List<TogafEventDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafEventDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafEventDiagram(TogafEventDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafFunctionalDecompositionDiagram> getTogafFunctionalDecompositionDiagram() {
		List<TogafFunctionalDecompositionDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafFunctionalDecompositionDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafFunctionalDecompositionDiagram(TogafFunctionalDecompositionDiagram model) {
		this.element.getProduct().add(model.getElement());
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

	public List<TogafProduct> getTogafProduct() {
		List<TogafProduct> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafProduct((Signal) model_element));
		}
		return res;
	}

	public void addTogafProduct(TogafProduct model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafFunction> getTogafFunction() {
		List<TogafFunction> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafFunction((Interface) model_element));
		}
		return res;
	}

	public void addTogafFunction(TogafFunction model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafBusinessService> getTogafBusinessService() {
		List<TogafBusinessService> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafBusinessService((Interface) model_element));
		}
		return res;
	}

	public void addTogafBusinessService(TogafBusinessService model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafProcess> getTogafProcess() {
		List<TogafProcess> res = new java.util.ArrayList<>();
		for (Behavior model_element : this.element.getOwnedBehavior()) {
			res.add(new TogafProcess((BpmnProcess) model_element));
		}
		return res;
	}

	public void addTogafProcess(TogafProcess model) {
		this.element.getOwnedBehavior().add(model.getElement());
	}

	public List<OrganizationParticipant> getOrganizationParticipant() {
		List<OrganizationParticipant> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new OrganizationParticipant((Actor) model_element));
		}
		return res;
	}

	public void addOrganizationParticipant(OrganizationParticipant model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafLocation> getTogafLocation() {
		List<TogafLocation> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafLocation((Node) model_element));
		}
		return res;
	}

	public void addTogafLocation(TogafLocation model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafDataSecurityDiagram> getTogafDataSecurityDiagram() {
		List<TogafDataSecurityDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafDataSecurityDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafDataSecurityDiagram(TogafDataSecurityDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafOrganizationDecompositionDiagram> getTogafOrganizationDecompositionDiagram() {
		List<TogafOrganizationDecompositionDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafOrganizationDecompositionDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafOrganizationDecompositionDiagram(TogafOrganizationDecompositionDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafBusinessServiceInformationDiagram> getTogafBusinessServiceInformationDiagram() {
		List<TogafBusinessServiceInformationDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafBusinessServiceInformationDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafBusinessServiceInformationDiagram(TogafBusinessServiceInformationDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafValueChainDiagram> getTogafValueChainDiagram() {
		List<TogafValueChainDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafValueChainDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafValueChainDiagram(TogafValueChainDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafGoalObjectiveServiceDiagram> getTogafGoalObjectiveServiceDiagram() {
		List<TogafGoalObjectiveServiceDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafGoalObjectiveServiceDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafGoalObjectiveServiceDiagram(TogafGoalObjectiveServiceDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TogafOrganizationUnit> getTogafOrganizationUnit() {
		List<TogafOrganizationUnit> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafOrganizationUnit((Package) model_element));
		}
		return res;
	}

	public void addTogafOrganizationUnit(TogafOrganizationUnit model) {
		this.element.getOwnedElement().add(model.getElement());
	}

}
