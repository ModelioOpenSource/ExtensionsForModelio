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
package org.modelio.togaf.profile.businessarchitecture.commande.explorer.diagram.command;

import java.util.ArrayList;
import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.businessarchitecture.model.TogafProcess;
import org.modelio.togaf.profile.businessarchitecture.model.TogafProcessFlowDiagram;
import org.modelio.togaf.profile.utils.ResourceManager;
import org.modelio.vcore.smkernel.mapi.MObject;

public class TogafProcessFlowDiagramCommande extends DefaultModuleCommandHandler {

	@Override
	public boolean accept(List<MObject> selected_element, IModule module) {
		if (selected_element.size() != 0 && selected_element.get(0) instanceof ModelElement) {
			ModelElement element = (ModelElement) selected_element.get(0);

	        if (element.isStereotyped("TogafArchitect", "BusinessArchitecture")) {
	            return true;
	        }

	        if (element.isStereotyped("TogafArchitect", "TogafProcess")) {
	            return true;
	        }
		}
		return false;
	}

	@Override
	public void actionPerformed(List<MObject> selected_element, IModule module) {
		ModelElement diagramOwner = (ModelElement)selected_element.get(0);
		String diagramName = getLabel();
		String diagramDescription = getInformation();
        IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
        try (ITransaction transaction = session.createTransaction("");) {
            TogafProcessFlowDiagram proxy = null;
            if (diagramOwner instanceof NameSpace){
               
                TogafProcess process = new TogafProcess();
                process.setParent((NameSpace)diagramOwner);
                
               for(AbstractDiagram diag : new ArrayList<>(process.getElement().getProduct())){
                   diag.delete();
               }
                
                proxy = new TogafProcessFlowDiagram(process.getElement(), diagramName);
                proxy.getElement().putNoteContent("ModelerModule", "description", diagramDescription);

            } else if( diagramOwner instanceof Behavior) {
                proxy = new TogafProcessFlowDiagram(diagramOwner, diagramName);
                proxy.getElement().putNoteContent("ModelerModule", "description", diagramDescription);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLabel() {
        return ResourceManager.getName("TogafProcessFlowDiagram");
    }

    public String getInformation() {
        return ResourceManager.getCommandeToolType("TogafProcessFlowDiagram");
    }

    public String getDetails() {
        return org.modelio.togaf.i18n.Messages.getString("TogafProcessFlowDiagram_SHORTNOTE");
    }
}
