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

import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.tools.DefaultBoxTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessarchitecture.model.TogafExternalRole;

public class TogafExternalRoleDiagramCommande extends DefaultBoxTool {

	@Override
	public boolean acceptElement(IDiagramHandle representation, IDiagramGraphic graphic) {
		ModelElement owner = null;

		if (graphic instanceof IDiagramDG) {
			owner = representation.getDiagram().getOrigin();
		} else {
			owner = (ModelElement) graphic.getElement();
		}

		if (owner.isStereotyped("TogafArchitect","BusinessArchitecture") || owner.isStereotyped("TogafArchitect","ApplicationArchitecture")) {
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(IDiagramHandle representation, IDiagramGraphic graphic, Rectangle rec) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {

			ModelElement parent = null;

			if (graphic instanceof IDiagramDG) {
				parent = representation.getDiagram().getOrigin();
			} else {
				parent = (ModelElement) graphic.getElement();
			}

			TogafExternalRole proxy = new TogafExternalRole();

			if (parent instanceof Package) {
				proxy.setParent((Package) parent);
			}

			List<IDiagramGraphic> graph = representation.unmask(proxy.getElement(), rec.x, rec.y);
			((IDiagramNode) graph.get(0)).setBounds(rec);
			((IDiagramNode) graph.get(0)).setProperty("ACTOR_REPMODE", "IMAGE");
			representation.save();

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean accept(IModule module, IDiagramHandle diagram) {
		ModelElement owner = diagram.getDiagram().getOrigin();
		if (owner.isStereotyped("TogafArchitect","OrganizationParticipant")) {
			return false;
		}
		return true;
	}

}
