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
package org.modelio.togaf.profile.businessentities.commande.explorer.diagram.wizard;

import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.api.ITogafArchitectPeerModule;
import org.modelio.togaf.profile.businessentities.model.BusinessEntity;
import org.modelio.togaf.profile.businessentities.model.EntityLifeCycle;
import org.modelio.togaf.profile.businessentities.model.TogafDataLifeCycleDiagram;
import org.modelio.togaf.profile.utils.TogafDiagramWizardContributor;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

public class TogafDataLifeCycleDiagramWizardContributor extends TogafDiagramWizardContributor {
    @Override
    public boolean accept(ModelElement element) {
        if (element instanceof NameSpace) {
            return super.accept(element);
        } else {
            ModelElement owner = (ModelElement) element.getCompositionOwner();
            if (owner != null && owner.isStereotyped("TogafArchitect", "TogafProduct")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractDiagram actionPerformed(ModelElement diagramOwner, String diagramName, String diagramDescription) {
        IModuleContext moduleContext = getModule().getModuleContext();
        IModelingSession session = moduleContext.getModelingSession();
        IDiagramService diagramService = moduleContext.getModelioServices().getDiagramService();
        try (ITransaction transaction = session.createTransaction("");) {
            IStyleHandle style = diagramService.getStyle(getStyle());
            TogafDataLifeCycleDiagram proxy = null;
            if (diagramOwner instanceof Package) {

                BusinessEntity entity = new BusinessEntity();
                entity.setParent((Package) diagramOwner);

                EntityLifeCycle element = new EntityLifeCycle();
                element.setParent(entity.getElement());
                proxy = new TogafDataLifeCycleDiagram((StateMachine) diagramOwner, style);
                moduleContext.getModelioServices().getEditionService().openEditor(proxy.getElement());
            } else if (diagramOwner instanceof NameSpace) {
                EntityLifeCycle element = new EntityLifeCycle();
                element.setParent((NameSpace) diagramOwner);
                proxy = new TogafDataLifeCycleDiagram((StateMachine) diagramOwner, style);
                moduleContext.getModelioServices().getEditionService().openEditor(proxy.getElement());
            } else if (diagramOwner instanceof StateMachine) {
                proxy = new TogafDataLifeCycleDiagram((StateMachine) diagramOwner, style);
            }
            transaction.commit();
            return proxy != null ? proxy.getElement() : null;
        } catch (Exception e) {
            moduleContext.getLogService().error(e);
        }
        return null;
    }

    @Override
    public ElementDescriptor getCreatedElementType() {
        IModuleContext moduleContext = getModule().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        MClass mClass = metamodel.getMClass(StateMachineDiagram.class);
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();
        Stereotype stereotype = extensions.getStereotype(ITogafArchitectPeerModule.MODULE_NAME, "TogafDataLifeCycleDiagram", mClass);
        return stereotype != null ? new ElementDescriptor(mClass, stereotype) : null;
    }

}
