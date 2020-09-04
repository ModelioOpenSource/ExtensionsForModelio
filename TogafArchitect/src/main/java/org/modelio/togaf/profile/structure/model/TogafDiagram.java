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

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafDiagram {
	protected AbstractDiagram element;

	public TogafDiagram(ModelElement owner) {
		try {
			this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createStaticDiagram(owner.getName() + " " + ResourceManager.getName("TogafDiagram"), owner, TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect","TogafDiagram", TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class)));
			ModelUtils.setStereotype(getElement(),"TogafArchitect", "TogafDiagram");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TogafDiagram(AbstractDiagram element) {
		this.element = element;
	}

	public AbstractDiagram getElement() {
		return this.element;
	}

	public void setParent(ModelElement parent) {
		this.element.setOrigin(parent);
	}
}
