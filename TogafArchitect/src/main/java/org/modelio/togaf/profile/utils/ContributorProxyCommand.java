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
package org.modelio.togaf.profile.utils;

import java.util.List;

import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

public class ContributorProxyCommand extends DefaultModuleCommandHandler {

    private IDiagramWizardContributor contributor;
    public ContributorProxyCommand(IDiagramWizardContributor contributor){
        this.contributor = contributor;
    }
    
    
	@Override
	public boolean accept(List<MObject> selected_element, IModule module) {
	    return this.contributor.accept(selected_element.get(0));
	}
	
	@Override
	public void actionPerformed(List<MObject> selected_element, IModule module) {
	     this.contributor.actionPerformed((ModelElement) selected_element.get(0),this.contributor.getLabel(), this.contributor.getDetails());
	}

}
