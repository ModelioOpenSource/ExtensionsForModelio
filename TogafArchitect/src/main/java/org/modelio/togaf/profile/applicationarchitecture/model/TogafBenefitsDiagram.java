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

import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.Module;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafBenefitsDiagram extends TogafApplicationCommunicationDiagram {

	public TogafBenefitsDiagram(ModelElement owner, IStyleHandle style) throws Exception {
		super(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createStaticDiagram(owner.getName() + " " + ResourceManager.getName("TogafBenefitsDiagram"), owner, TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect","TogafBenefitsDiagram", TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class))));

		try (IDiagramHandle diagramHandler = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(getElement())) {
			diagramHandler.getDiagramNode().setStyle(style);
			diagramHandler.save();
			diagramHandler.close();
		}

		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		IPeerModule peer = Module.getPeer();
			Note note = session.getModel().createNote(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getNoteType("TogafArchitect","TOGAFModelingGuide", getElement().getMClass()), getElement(), org.modelio.togaf.i18n.Messages.getString("TogafBenefitsDiagram_NOTE"));
			try (IDiagramHandle rep = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(getElement())) {
				IDiagramNode node = (IDiagramNode) rep.unmask(note, 0, 0).get(0);
				node.setBounds(new Rectangle(0, 0, 500, 300));
				rep.save();
				rep.close();
			}
	
	}

	public TogafBenefitsDiagram(StaticDiagram element) {
		super(element);
	}


}
