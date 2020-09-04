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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafApplicationComponent;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafApplicationComponentInstance;
import org.modelio.togaf.profile.businessarchitecture.model.TogafLocation;
import org.modelio.togaf.profile.technologyarchitecture.model.HardwareTechnologyComponent;
import org.modelio.togaf.profile.technologyarchitecture.model.TechnologyArchitectureDomain;
import org.modelio.togaf.profile.technologyarchitecture.model.TogafPlatformDecompositionDiagram;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TechnologyArchitecture extends Layer {

	public TechnologyArchitecture() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createPackage());
		ModelUtils.setStereotype(this.element, "TogafArchitect","TechnologyArchitecture");
		this.element.setName(ResourceManager.getName("TechnologyArchitecture"));
	}

	public TechnologyArchitecture(Package element) {
		super(element);
	}

	public List<TogafApplicationComponent> getTogafApplicationComponent() {
		List<TogafApplicationComponent> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafApplicationComponent((Component) model_element));
		}
		return res;
	}

	public void addTogafApplicationComponent(TogafApplicationComponent model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafApplicationComponentInstance> getTogafApplicationComponentInstance() {
		List<TogafApplicationComponentInstance> res = new java.util.ArrayList<>();
		for (Instance model_element : this.element.getDeclared()) {
			res.add(new TogafApplicationComponentInstance(model_element));
		}
		return res;
	}

	public void addTogafApplicationComponentInstance(TogafApplicationComponentInstance model) {
		this.element.getDeclared().add(model.getElement());
	}

	public List<HardwareTechnologyComponent> getHardwareTechnologyComponent() {
		List<HardwareTechnologyComponent> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new HardwareTechnologyComponent((Node) model_element));
		}
		return res;
	}

	public void addHardwareTechnologyComponent(HardwareTechnologyComponent model) {
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

	public List<TogafPlatformDecompositionDiagram> getTogafPlatformDecompositionDiagram() {
		List<TogafPlatformDecompositionDiagram> res = new java.util.ArrayList<>();
		for (AbstractDiagram model_element : this.element.getProduct()) {
			res.add(new TogafPlatformDecompositionDiagram((StaticDiagram) model_element));
		}
		return res;
	}

	public void addTogafPlatformDecompositionDiagram(TogafPlatformDecompositionDiagram model) {
		this.element.getProduct().add(model.getElement());
	}

	public List<TechnologyArchitectureDomain> getTechnologyArchitectureDomain() {
		List<TechnologyArchitectureDomain> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TechnologyArchitectureDomain((Package) model_element));
		}
		return res;
	}

	public void addTechnologyArchitectureDomain(TechnologyArchitectureDomain model) {
		this.element.getOwnedElement().add(model.getElement());
	}

}
