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
package org.modelio.togaf.profile.applicationarchitecture.commande.explorer.diagram.wizard;

import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.togaf.api.ITogafArchitectPeerModule;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.applicationarchitecture.model.TogafSystemUseCaseDiagram;
import org.modelio.togaf.profile.utils.TogafDiagramWizardContributor;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

public class TogafSystemUseCaseDiagramWizardContributor extends TogafDiagramWizardContributor {

    @Override
    public boolean accept(MObject ielement) {
        ModelElement element = (ModelElement) ielement;
        if (element.isStereotyped("TogafArchitect", "ApplicationArchitecture")) {
            return true;
        }
        if (element.isStereotyped("TogafArchitect", "TogafApplicationComponent")) {
            return true;
        }

        if (element.isStereotyped("TogafArchitect", "TogafService")) {
            return true;
        }
        return false;
    }

    @Override
    public AbstractDiagram actionPerformed(ModelElement diagramOwner, String diagramName, String diagramDescription) {

        IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
        IDiagramService diagramService = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        try (ITransaction transaction = session.createTransaction("");) {
        	IStyleHandle style = diagramService.getStyle(getStyle());         
        	TogafSystemUseCaseDiagram proxy = new TogafSystemUseCaseDiagram(diagramOwner,style);
            proxy.getElement().setName(diagramName);
            proxy.getElement().putNoteContent("ModelerModule", "description", diagramDescription);
            TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(proxy.getElement());
            transaction.commit();
            return proxy.getElement();
        } catch (Exception e) {
        	
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ElementDescriptor getCreatedElementType() {
        IModuleContext moduleContext = getModule().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        MClass mClass = metamodel.getMClass(UseCaseDiagram.class);
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();
        Stereotype stereotype = extensions.getStereotype(ITogafArchitectPeerModule.MODULE_NAME, "TogafSystemUseCaseDiagram", mClass);
        return stereotype != null ? new ElementDescriptor(mClass, stereotype) : null;
    }
   
}
