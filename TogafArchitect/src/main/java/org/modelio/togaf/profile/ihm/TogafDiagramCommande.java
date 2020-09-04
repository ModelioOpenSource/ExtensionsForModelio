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
package org.modelio.togaf.profile.ihm;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.vcore.smkernel.mapi.MObject;

public class TogafDiagramCommande extends DefaultModuleCommandHandler {

	@Override
	public boolean accept(List<MObject> selected_element, IModule module) {
		return true;
	}

	@Override
	public void actionPerformed(List<MObject> selected_element, IModule module) {
	    // TODO
//		ContributionModel contribution = new ContributionModel();
//		contribution.addDiagramContributors(org.modelio.togaf.i18n.Messages.getDiagramContribution());
//		DiagramWizardModel model = new DiagramWizardModel();
//		model.setShowInvalidDiagram(org.modelio.togaf.i18n.Messages.setShowInvalidDiagram());
//		model.setContext((ModelElement) selected_element.get(0));
//
//		DiagramWizardDialog dilaog = new DiagramWizardDialog(Display.getDefault().getActiveShell(), contribution, model);
//		if (dilaog.open() == 0) {
//			dilaog.getResultModel().getSelectedContributor().actionPerformed(dilaog.getResultModel().getContext(), dilaog.getResultModel().getName(), dilaog.getResultModel().getDescription());
//		}

	}

}
