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
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;
public class EntityLifeCycle {
	protected StateMachine element;

	public EntityLifeCycle() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createStateMachine();
		ModelUtils.setStereotype(this.element, "TogafArchitect","EntityLifeCycle");
		this.element.setName(ResourceManager.getName("EntityLifeCycle"));
	}

	public EntityLifeCycle(StateMachine element) {
		this.element = element;
	}

	public StateMachine getElement() {
		return this.element;
	}

	public void setParent(NameSpace parent) {
		this.element.setOwner(parent);
	}

	public void setParent(Operation parent) {
		this.element.setOwnerOperation(parent);
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

	public List<TogafDataLifeCycleDiagram> getTogafDataLifeCycleDiagram() {
		List<TogafDataLifeCycleDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafDataLifeCycleDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafDataLifeCycleDiagram(TogafDataLifeCycleDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

}
