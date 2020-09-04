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
package org.modelio.togaf.profile.businessentities.model;

import java.util.List;

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessEntities;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;
public class BusinessEntity {
	protected Class element;

	public BusinessEntity() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createClass();
		ModelUtils.setStereotype(this.element, "TogafArchitect","BusinessEntity");
		this.element.setName(ResourceManager.getName("BusinessEntity"));
	}

	public BusinessEntity(Class element) {
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

	public BusinessEntities getBusinessEntities() {
		return new BusinessEntities((Package) this.element.getOwner());
	}

	public void addBusinessEntities(BusinessEntities model) {
		this.element.setOwner(model.getElement());
	}

	public List<BusinessAttribute> getBusinessAttribute() {
		List<BusinessAttribute> res = new java.util.ArrayList<>();
		for (Attribute model_element : this.element.getOwnedAttribute()) {
			res.add(new BusinessAttribute(model_element));
		}
		return res;
	}

	public void addBusinessAttribute(BusinessAttribute model) {
		this.element.getOwnedAttribute().add(model.getElement());
	}

	public List<EntityLifeCycle> getEntityLifeCycle() {
		List<EntityLifeCycle> res = new java.util.ArrayList<>();
		for (Behavior model_element : this.element.getOwnedBehavior()) {
			res.add(new EntityLifeCycle((StateMachine) model_element));
		}
		return res;
	}

	public void addEntityLifeCycle(EntityLifeCycle model) {
		this.element.getOwnedBehavior().add(model.getElement());
	}

	public List<TogafEnumeration> getTogafEnumeration() {
		List<TogafEnumeration> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafEnumeration((Enumeration) model_element));
		}
		return res;
	}

	public void addTogafEnumeration(TogafEnumeration model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<BusinessDataType> getBusinessDataType() {
		List<BusinessDataType> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new BusinessDataType((DataType) model_element));
		}
		return res;
	}

	public void addBusinessDataType(BusinessDataType model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafClassDiagram> getTogafClassDiagram() {
		List<TogafClassDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafClassDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafClassDiagram(TogafClassDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<BusinessOperation> getBusinessOperation() {
		List<BusinessOperation> res = new java.util.ArrayList<>();
		for (Operation model_element : this.element.getOwnedOperation()) {
			res.add(new BusinessOperation(model_element));
		}
		return res;
	}

	public void addBusinessOperation(BusinessOperation model) {
		this.element.getOwnedOperation().add(model.getElement());
	}

	public List<BusinessInvariant> getBusinessInvariant() {
		List<BusinessInvariant> res = new java.util.ArrayList<>();
		for (Constraint model_element : this.element.getConstraintDefinition()) {
			res.add(new BusinessInvariant(model_element));
		}
		return res;
	}

	public void addBusinessInvariant(BusinessInvariant model) {
		this.element.getConstraintDefinition().add(model.getElement());
	}

}
