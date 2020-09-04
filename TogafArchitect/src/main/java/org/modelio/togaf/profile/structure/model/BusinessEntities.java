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

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessarchitecture.model.IOFlow;
import org.modelio.togaf.profile.businessentities.model.BusinessDataType;
import org.modelio.togaf.profile.businessentities.model.BusinessEntity;
import org.modelio.togaf.profile.businessentities.model.TogafClassDiagram;
import org.modelio.togaf.profile.businessentities.model.TogafEnumeration;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class BusinessEntities extends Layer {

	public BusinessEntities() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createPackage());
		ModelUtils.setStereotype(this.element, "TogafArchitect","BusinessEntities");
		this.element.setName(ResourceManager.getName("BusinessEntities"));
	}

	public BusinessEntities(Package element) {
		super(element);
	}

	public BusinessLayer getBusinessLayer() {
		return new BusinessLayer((Package) this.element.getOwner());
	}

	public void addBusinessLayer(BusinessLayer model) {
		this.element.setOwner(model.getElement());
	}

	public List<BusinessEntities> getSubBusinessEntities() {
		List<BusinessEntities> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new BusinessEntities((Package) model_element));
		}
		return res;
	}

	public void addSubBusinessEntities(BusinessEntities model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public BusinessEntities getParentBusinessEntities() {
		return new BusinessEntities((Package) this.element.getOwner());
	}

	public void addParentBusinessEntities(BusinessEntities model) {
		this.element.setOwner(model.getElement());
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

	public List<BusinessEntity> getBusinessEntity() {
		List<BusinessEntity> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new BusinessEntity((Class) model_element));
		}
		return res;
	}

	public void addBusinessEntity(BusinessEntity model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<IOFlow> getOutIoFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addOutIoFlow(IOFlow model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<IOFlow> getInIoFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addInIoFlow(IOFlow model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

}
