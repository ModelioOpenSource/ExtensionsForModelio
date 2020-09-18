/**
 * Java Class : AddBlockDiagramExplorerCommand.java
 *
 * Description :
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 *
 * @category   Command explorer
 * @package    com.modeliosoft.modelio.sysml.gui.explorer
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.commands.explorer.diagram.wizard;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * This class handles the creation of SysML use case diagram
 * 
 * @author ebrosse
 */
@objid ("81d04a0d-544f-491f-903e-094c9e8cea14")
public class SysMLUseCaseDiagramWizard extends AbstractDiagramWizardContributor {
    @objid ("91c87606-6111-4705-bfc2-f17b16a04873")
    @Override
    public AbstractDiagram actionPerformed(ModelElement element, String diagramName, String description) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        AbstractDiagram diagram = null;
        try (ITransaction transaction = session.createTransaction(I18nMessageService.getString("Info.Session.Create", "Activity"))) {
        
            diagram = SysMLFactory.createSysMLUseCaseDiagram(element, diagramName, description);
        
            if (diagram != null) {
                SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
            }
        
            transaction.commit();
        }
        return diagram;
    }

    @objid ("8467c2e8-e993-4e4e-a77b-2fad92e70e37")
    @Override
    public ElementDescriptor getCreatedElementType() {
        IModuleContext moduleContext = getModule().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        MClass mClass = metamodel.getMClass(UseCaseDiagram.class);
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();
        Stereotype stereotype = extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLUSECASEDIAGRAM, mClass);
        return stereotype != null ? new ElementDescriptor(mClass, stereotype) : null;
    }

    @objid ("a6c2dda7-6a8d-4a1f-9cc2-e16db71f95c1")
    public SysMLUseCaseDiagramWizard() {
        super();
        
        setLabel(I18nMessageService.getString ("Ui.Command.SysMLUseCaseDiagramExplorerCommand.Label"));
        setDetails(I18nMessageService.getString ("Ui.Command.SysMLUseCaseDiagramExplorerCommand.Details"));
        setIconDescriptor(ImageDescriptor.createFromFile(null, SysMLResourcesManager.getInstance().getImage("sysmlusecasediagram.png")));
        setInformation(I18nMessageService.getString ("Ui.Command.SysMLUseCaseDiagramExplorerCommand.Information"));
        
        IModuleContext moduleContext = SysMLModule.getInstance().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        setScopes(Arrays.asList(
                new ElementScope(metamodel.getMClass(Package.class), true, null, true),
                new ElementScope(metamodel.getMClass(UseCase.class), true, null, true)
                ));
    }

    @objid ("66c1a8f8-b155-43ac-9267-738c91c81919")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return !(owner instanceof Profile);
    }

    @objid ("ddf37307-389a-48b7-a7c9-4a5cf4674176")
    @Override
    public void dispose() {
        // Nothing to do
    }

}
