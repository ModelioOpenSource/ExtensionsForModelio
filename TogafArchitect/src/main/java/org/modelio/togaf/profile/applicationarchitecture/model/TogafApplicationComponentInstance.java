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
package org.modelio.togaf.profile.applicationarchitecture.model;

import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.ApplicationArchitecture;
import org.modelio.togaf.profile.structure.model.TechnologyArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;

public class TogafApplicationComponentInstance {
	protected Instance element;

	public TogafApplicationComponentInstance() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createInstance();
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafApplicationComponentInstance");
		this.element.setName(" ");
	}

	public TogafApplicationComponentInstance(Instance element) {
		this.element = element;
	}

	public Instance getElement() {
		return this.element;
	}

	public void setParent(NameSpace parent) {
		this.element.setOwner(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public String getoccurencesNumber() {
		String res = ModelUtils.getTaggedValue("TogafApplicationComponentInstance_occurencesNumber", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setoccurencesNumber(String value) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponentInstance_occurencesNumber", value, this.element);
	}

	public String getcapacity() {
		String res = ModelUtils.getTaggedValue("TogafApplicationComponentInstance_capacity", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setcapacity(String value) {
		ModelUtils.addValue("TogafArchitect","TogafApplicationComponentInstance_capacity", value, this.element);
	}

	public ApplicationArchitecture getApplicationArchitecture() {
		return new ApplicationArchitecture((Package) this.element.getOwner());
	}

	public void addApplicationArchitecture(ApplicationArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public TechnologyArchitecture getTechnologyArchitecture() {
		return new TechnologyArchitecture((Package) this.element.getOwner());
	}

	public void addTechnologyArchitecture(TechnologyArchitecture model) {
		this.element.setOwner(model.getElement());
	}

}
