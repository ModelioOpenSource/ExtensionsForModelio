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
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class BusinessInvariant {
	protected Constraint element;

	public BusinessInvariant() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createConstraint();
		ModelUtils.setStereotype(this.element, "TogafArchitect","BusinessInvariant");
		this.element.setName(ResourceManager.getName("BusinessInvariant"));
	}

	public BusinessInvariant(Constraint element) {
		this.element = element;
	}

	public Constraint getElement() {
		return this.element;
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public List<BusinessEntity> getBusinessEntity() {
		List<BusinessEntity> res = new java.util.ArrayList<>();
		for (ModelElement model_element : this.element.getConstrainedElement()) {
			res.add(new BusinessEntity((Class) model_element));
		}
		return res;
	}

	public void addBusinessEntity(BusinessEntity model) {
		this.element.getConstrainedElement().add(model.getElement());
	}

}
