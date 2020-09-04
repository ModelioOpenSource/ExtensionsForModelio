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

import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafServiceContract {
	protected Collaboration element;

	public TogafServiceContract() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createCollaboration();
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafServiceContract");
		this.element.setName(ResourceManager.getName("TogafServiceContract"));
		try {
		    this.element.putNoteContent("TogafArchitect", "Measure", "");
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}

	public TogafServiceContract(Collaboration element) {
		this.element = element;
	}

	public void setParent(ModelTree parent) {
		this.element.setOwner(parent);
	}

	public Collaboration getElement() {
		return this.element;
	}

	public TogafService getTogafService() {
		return new TogafService((Interface) this.element.getOwner());
	}

	public void addTogafService(TogafService model) {
		this.element.setOwner(model.getElement());
	}

	public void setParent(Operation destination) {
		this.element.setORepresented(destination);
	}

}
