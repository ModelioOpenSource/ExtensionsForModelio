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
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;

public class ContractOfDiagramCommand extends DefaultLinkTool {

	@Override
	public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
		if (arg1.getElement() instanceof ModelElement) {
			ModelElement elem = (ModelElement) arg1.getElement();

			if (elem.isStereotyped("TogafArchitect","TogafService")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean acceptSecondElement(IDiagramHandle arg0, IDiagramGraphic arg1, IDiagramGraphic arg2) {
		if (arg2.getElement() instanceof ModelElement) {
			ModelElement elem = (ModelElement) arg2.getElement();
			if (elem.isStereotyped("TogafArchitect","TogafServiceContract")) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void actionPerformed(IDiagramHandle representation, IDiagramGraphic source, IDiagramGraphic destination, LinkRouterKind kind, ILinkPath path) {

		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		IUmlModel model = session.getModel();
		try (ITransaction transaction = session.createTransaction("");) {
			Dependency gen = model.createDependency();
			gen.setDependsOn((ModelElement) destination.getElement());
			gen.setImpacted((ModelElement) source.getElement());
			gen.setName("");
			// ModelUtils.setStereotype(gen, "trace");
			ModelUtils.setStereotype(gen,"WSDLDesigner", "ContractOf");

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
