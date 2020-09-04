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
package org.modelio.togaf.profile.structure.commande.explorer;

import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.vcore.smkernel.mapi.MObject;

public class DistributionCommande extends DefaultModuleCommandHandler {

	@Override
	public boolean accept(List<MObject> selected_element, IModule module) {
		return true;
	}

	@Override
	public void actionPerformed(List<MObject> selected_element, IModule module) {

		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {
			IUmlModel model = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel();
			Package n_package = model.createPackage("Software Distribution", (NameSpace) selected_element.get(0));
			StaticDiagram diagram = model.createStaticDiagram("software distribution diagram", n_package, TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect","class", TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class)));

			TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
			transaction.commit();
			TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getNavigationService().fireNavigate(n_package);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
