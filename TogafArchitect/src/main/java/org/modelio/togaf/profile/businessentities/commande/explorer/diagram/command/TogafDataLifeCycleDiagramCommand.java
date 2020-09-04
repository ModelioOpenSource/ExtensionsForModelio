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
package org.modelio.togaf.profile.businessentities.commande.explorer.diagram.command;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessentities.model.BusinessEntity;
import org.modelio.togaf.profile.businessentities.model.EntityLifeCycle;
import org.modelio.togaf.profile.businessentities.model.TogafDataLifeCycleDiagram;
import org.modelio.togaf.profile.utils.ResourceManager;
import org.modelio.vcore.smkernel.mapi.MObject;

public class TogafDataLifeCycleDiagramCommand extends DefaultModuleCommandHandler {

	@Override
	public boolean accept(List<MObject> selected_element, IModule module) {
		if (selected_element.size() != 0 && selected_element.get(0) instanceof ModelElement) {
			ModelElement element = (ModelElement) selected_element.get(0);

			if (element instanceof NameSpace) {
				if (element.isStereotyped("TogafArchitect", "EntityLifeCycle")) {
					return true;
				}

				if (element.isStereotyped("TogafArchitect", "BusinessEntities")) {
					return true;
				}
				if (element.isStereotyped("TogafArchitect", "BusinessEntity")) {
					return true;
				}
			} else {
				ModelElement owner = (ModelElement) element.getCompositionOwner();
				if (owner != null && owner.isStereotyped("TogafArchitect", "TogafProduct")) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(List<MObject> selected_element, IModule module) {
		ModelElement diagramOwner = (ModelElement) selected_element.get(0);
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
        IDiagramService diagramService = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        try (ITransaction transaction = session.createTransaction("");) {
        	IStyleHandle style = diagramService.getStyle(getParameters().get("style"));    
			TogafDataLifeCycleDiagram proxy = null;
			if (diagramOwner instanceof Package) {

				BusinessEntity entity = new BusinessEntity();
				entity.setParent((Package) diagramOwner);

				EntityLifeCycle element = new EntityLifeCycle();
				element.setParent(entity.getElement());
				diagramOwner = element.getElement();
				proxy = new TogafDataLifeCycleDiagram((StateMachine) diagramOwner,style);
				TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(proxy.getElement());
			} else if (diagramOwner instanceof NameSpace) {
				EntityLifeCycle element = new EntityLifeCycle();
				element.setParent((NameSpace) diagramOwner);
				diagramOwner = element.getElement();
				proxy = new TogafDataLifeCycleDiagram((StateMachine) diagramOwner,style);
				TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(proxy.getElement());
			} else if (diagramOwner instanceof StateMachine) {
				proxy = new TogafDataLifeCycleDiagram((StateMachine) diagramOwner,style);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getLabel() {
		return ResourceManager.getName("TogafDataLifeCycleDiagram");
	}

	public String getInformation() {
		return ResourceManager.getCommandeToolType("TogafDataLifeCycleDiagram");
	}

	public String getDetails() {
		return org.modelio.togaf.i18n.Messages.getString("TogafDataLifeCycleDiagram_SHORTNOTE");
	}
}
