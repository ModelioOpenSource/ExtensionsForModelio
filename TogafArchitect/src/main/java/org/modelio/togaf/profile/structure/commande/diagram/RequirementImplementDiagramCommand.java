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
package org.modelio.togaf.profile.structure.commande.diagram;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.togaf.impl.TogafArchitectModule;

public class RequirementImplementDiagramCommand extends DefaultLinkTool {

	@Override
	public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
		if (arg1.getElement() instanceof Activity || arg1.getElement() instanceof Class) {
			return true;
		}
		return false;
	}

	@Override
	public boolean acceptSecondElement(IDiagramHandle arg0, IDiagramGraphic arg1, IDiagramGraphic arg2) {
		if (arg2.getElement() instanceof ModelElement) {
			ModelElement element = (ModelElement) arg2.getElement();
			if (element.isStereotyped("TogafArchitect","business_rule")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(IDiagramHandle representation, IDiagramGraphic source, IDiagramGraphic destination, LinkRouterKind kind, ILinkPath path) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {
			IUmlModel model = session.getModel();

			Dependency gen = model.createDependency((ModelElement) source.getElement(), (ModelElement) destination.getElement(),TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect", "implement",TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Dependency.class)));
			gen.setName("");

			List<IDiagramGraphic> graph = representation.unmask(gen, 0, 0);
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
