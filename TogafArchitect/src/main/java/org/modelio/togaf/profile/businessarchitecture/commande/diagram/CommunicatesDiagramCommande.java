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
package org.modelio.togaf.profile.businessarchitecture.commande.diagram;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessarchitecture.model.Communicates;

public class CommunicatesDiagramCommande extends DefaultLinkTool {

	@Override
	public boolean acceptFirstElement(IDiagramHandle representation, IDiagramGraphic graphic) {
		ModelElement owner = null;

		if (graphic instanceof IDiagramDG) {
			owner = representation.getDiagram().getOrigin();
		} else {
			owner = (ModelElement) graphic.getElement();
		}

		if (owner.isStereotyped("TogafArchitect","OrganizationParticipant")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean acceptSecondElement(IDiagramHandle representation, IDiagramGraphic graphic_source, IDiagramGraphic graphic_target) {
		ModelElement owner = null;

		if (graphic_target == null) {
			owner = representation.getDiagram().getOrigin();
		} else {
			owner = (ModelElement) graphic_target.getElement();
		}

		if (owner.isStereotyped("TogafArchitect","OrganizationParticipant")) {
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(IDiagramHandle representation, IDiagramGraphic graphic_source, IDiagramGraphic graphic_target, LinkRouterKind kind, ILinkPath path) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {

			ModelElement source = (ModelElement) graphic_source.getElement();
			ModelElement target = (ModelElement) graphic_target.getElement();

			Communicates proxy = new Communicates();
			proxy.setParent(source, target);

			List<IDiagramGraphic> graph = representation.unmask(proxy.getElement(), 0, 0);
			IDiagramLink link = (IDiagramLink) graph.get(0);
			link.setRouterKind(kind);
			link.setPath(path);
			representation.save();

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
