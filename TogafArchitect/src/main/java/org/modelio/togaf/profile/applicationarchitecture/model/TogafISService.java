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


import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessarchitecture.model.TogafService;
import org.modelio.togaf.profile.structure.model.ApplicationArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;
public class TogafISService extends TogafService {

	public TogafISService() {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createInterface());
		ModelUtils.setStereotype(this.element,"TogafArchitect", "TogafISService");
		this.element.setName(ResourceManager.getName("TogafISService"));
		try {
		    this.element.putNoteContent("TogafArchitect", "TogafService_contract", "");
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

	public TogafISService(Interface element) {
		super(element);
	}

	public ApplicationArchitecture getApplicationArchitecture() {
		return new ApplicationArchitecture((Package) this.element.getOwner());
	}

	public void addApplicationArchitecture(ApplicationArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public TogafApplicationComponent getTogafApplicationComponent() {
		return new TogafApplicationComponent((Component) this.element.getOwner());
	}

	public void addTogafApplicationComponent(TogafApplicationComponent model) {
		this.element.setOwner(model.getElement());
	}

}
