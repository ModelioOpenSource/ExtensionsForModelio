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


import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.TogafDiagram;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafUseCaseDiagram extends TogafDiagram {

	public TogafUseCaseDiagram(ModelElement owner, String module,String stereotype, IStyleHandle style){
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createUseCaseDiagram(owner.getName() + " " + ResourceManager.getName(stereotype), owner, TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype(module, stereotype, TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(UseCaseDiagram.class))));

		try (IDiagramHandle diagramHandler = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(getElement())) {
			diagramHandler.getDiagramNode().setStyle(style);
			diagramHandler.save();
			diagramHandler.close();
		}
	}

}
