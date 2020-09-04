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

import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class BusinessOperation {
	protected Operation element;

	public BusinessOperation() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createOperation();
		ModelUtils.setStereotype(this.element, "TogafArchitect","BusinessOperation");
		this.element.setName(ResourceManager.getName("BusinessOperation"));
		this.element.setVisibility(VisibilityMode.VISIBILITYUNDEFINED);
	}

	public BusinessOperation(Operation element) {
		this.element = element;
	}

	public Operation getElement() {
		return this.element;
	}

	public void setParent(Classifier parent) {
		this.element.setOwner(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public BusinessEntity getBusinessEntity() {
		return new BusinessEntity((Class) this.element.getOwner());
	}

	public void addBusinessEntity(BusinessEntity model) {
		this.element.setOwner(model.getElement());
	}

	public List<PreCondition> getPreCondition() {
		List<PreCondition> res = new java.util.ArrayList<>();
		for (Constraint model_element : this.element.getConstraintDefinition()) {
			res.add(new PreCondition(model_element));
		}
		return res;
	}

	public void addPreCondition(PreCondition model) {
		this.element.getConstraintDefinition().add(model.getElement());
	}

	public List<PostCondition> getPostCondition() {
		List<PostCondition> res = new java.util.ArrayList<>();
		for (Constraint model_element : this.element.getConstraintDefinition()) {
			res.add(new PostCondition(model_element));
		}
		return res;
	}

	public void addPostCondition(PostCondition model) {
		this.element.getConstraintDefinition().add(model.getElement());
	}

}
