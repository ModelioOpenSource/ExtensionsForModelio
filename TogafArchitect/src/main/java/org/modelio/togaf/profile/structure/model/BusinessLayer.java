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

import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class BusinessLayer extends Layer {

	public BusinessLayer() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createPackage());
		ModelUtils.setStereotype(this.element, "TogafArchitect","BusinessLayer");
		this.element.setName(ResourceManager.getName("BusinessLayer"));
	}

	public BusinessLayer(Package element) {
		super(element);
	}

	public List<BusinessEntities> getBusinessEntities() {
		List<BusinessEntities> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new BusinessEntities((Package) model_element));
		}
		return res;
	}

	public void addBusinessEntities(BusinessEntities model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<BusinessArchitecture> getBusinessArchitecture() {
		List<BusinessArchitecture> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new BusinessArchitecture((Package) model_element));
		}
		return res;
	}

	public void addBusinessArchitecture(BusinessArchitecture model) {
		this.element.getOwnedElement().add(model.getElement());
	}

}
