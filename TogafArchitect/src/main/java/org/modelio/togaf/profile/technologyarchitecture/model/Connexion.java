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
package org.modelio.togaf.profile.technologyarchitecture.model;

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;

public class Connexion {
	protected ModelElement element;

	public Connexion(ModelElement source, ModelElement destination) {
		try {
			if (source instanceof BindableInstance && destination instanceof BindableInstance) {
				this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createConnector((BindableInstance) source, (BindableInstance) destination, "Connexion");
			} else if (source instanceof Classifier && destination instanceof Classifier) {
				this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createAssociation((Classifier) source, (Classifier) destination, "Connexion");
			} else {
				this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createDependency(source, destination,TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect", "Connexion", TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Dependency.class)) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
		this.element.setName("");
	}

	public Connexion(Dependency element) {
		this.element = element;
	}

	public ModelElement getElement() {
		return this.element;
	}

	public String getprotocol() {
		String res = ModelUtils.getTaggedValue("Connexion_protocol", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setprotocol(String value) {
		ModelUtils.addValue("TogafArchitect","Connexion_protocol", value, this.element);
	}

	public String getcapacity() {
		String res = ModelUtils.getTaggedValue("Connexion_capacity", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setcapacity(String value) {
		ModelUtils.addValue("TogafArchitect","Connexion_capacity", value, this.element);
	}
}
