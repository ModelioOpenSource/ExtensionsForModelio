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

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafFunction extends TogafService {

	public TogafFunction() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createInterface());
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafFunction");
		this.element.setName(ResourceManager.getName("TogafFunction"));
		try {
			
			this.element.putNoteContent( "TogafArchitect", "TogafService_contract", "");

			this.element.putNoteContent( "TogafArchitect", "TogafFunction_valueFactor", "");

			this.element.putNoteContent( "TogafArchitect", "TogafFunction_changesNeeded", "");

			this.element.putNoteContent( "TogafArchitect", "TogafFunction_increments", "");

		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

	public TogafFunction(Interface element) {
		super(element);
	}

	public String getvalueFactor() {
		String res = this.element.getNoteContent("TogafArchitect","TogafFunction_valueFactor");
		if (res == null || res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setvalueFactor(String value) {
		try {
			this.element.putNoteContent( "TogafArchitect", "TogafFunction_valueFactor", value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getchangesNeeded() {
		String res = this.element.getNoteContent("TogafArchitect","TogafFunction_changesNeeded");
		if (res == null || res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setchangesNeeded(String value) {
		try {
			this.element.putNoteContent( "TogafArchitect", "TogafFunction_changesNeeded", value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getincrements() {
		String res = this.element.getNoteContent("TogafArchitect","TogafFunction_increments");
		if (res == null || res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setincrements(String value) {
		try {
			this.element.putNoteContent( "TogafArchitect", "TogafFunction_increments", value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BusinessArchitecture getBusinessArchitecture() {
		return new BusinessArchitecture((Package) this.element.getOwner());
	}

	public void addBusinessArchitecture(BusinessArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public List<TogafService> getTogafService() {
		List<TogafService> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafService((Interface) model_element));
		}
		return res;
	}

	public void addTogafService(TogafService model) {
		this.element.getOwnedElement().add(model.getElement());
	}

	public List<TogafFunctionSequence> getOutTogafFunctionSequence() {
		List<TogafFunctionSequence> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new TogafFunctionSequence(model_element));
		}
		return res;
	}

	public void addOutTogafFunctionSequence(TogafFunctionSequence model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<TogafFunctionSequence> getInTogafFunctionSequence() {
		List<TogafFunctionSequence> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new TogafFunctionSequence(model_element));
		}
		return res;
	}

	public void addInTogafFunctionSequence(TogafFunctionSequence model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

}
