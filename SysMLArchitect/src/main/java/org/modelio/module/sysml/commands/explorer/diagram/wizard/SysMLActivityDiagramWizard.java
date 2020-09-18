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
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.filters.ConstraintBlockFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.module.sysml.utils.SysMLResourcesManager;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * This class handles the creation of SysML activity diagram
 * @author ebrosse
 */
@objid ("febf2166-ada0-44ef-a721-2e2aa775b1dd")
public class SysMLActivityDiagramWizard extends AbstractDiagramWizardContributor {
    @objid ("80728d9d-04ab-4da4-8498-922ad8ea6eed")
    @Override
    public AbstractDiagram actionPerformed(ModelElement element, String diagramName, String description) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        AbstractDiagram diagram = null;
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Activity"))){
        
            diagram = SysMLFactory.createSysMLActivityDiagram(element, diagramName, description);
        
            if (diagram != null) {
                SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
            }
        
            transaction.commit ();
        }
        return diagram;
    }

    @objid ("a7ca22b9-ed36-4c8f-bec9-97903663c02c")
    @Override
    public ElementDescriptor getCreatedElementType() {
        IModuleContext moduleContext = getModule().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        MClass mClass = metamodel.getMClass(ActivityDiagram.class);
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();
        Stereotype stereotype = extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLACTIVITYDIAGRAM, mClass);
        return stereotype != null ? new ElementDescriptor(mClass, stereotype) : null;
    }

    @objid ("59843c15-5a8b-4306-a5b1-96411fe6b3ea")
    public SysMLActivityDiagramWizard() {
        super();
        
        setLabel(I18nMessageService.getString ("Ui.Command.SysMLActivityDiagramExplorerCommand.Label"));
        setDetails(I18nMessageService.getString ("Ui.Command.SysMLActivityDiagramExplorerCommand.Details"));
        setIconDescriptor(ImageDescriptor.createFromFile(null, SysMLResourcesManager.getInstance().getImage("sysmlactivitydiagram.png")));
        setInformation(I18nMessageService.getString ("Ui.Command.SysMLActivityDiagramExplorerCommand.Information"));
        
        IModuleContext moduleContext = SysMLModule.getInstance().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        setScopes(Arrays.asList(
                new ElementScope(metamodel.getMClass(Package.class), true, null, true),
                new ElementScope(metamodel.getMClass(Class.class), true, null, true),
                new ElementScope(metamodel.getMClass(Interface.class), true, null, true),
                new ElementScope(metamodel.getMClass(Signal.class), true, null, true),
                new ElementScope(metamodel.getMClass(Actor.class), true, null, true),
                new ElementScope(metamodel.getMClass(Component.class), true, null, true),
                new ElementScope(metamodel.getMClass(Node.class), true, null, true),
                new ElementScope(metamodel.getMClass(UseCase.class), true, null, true),
                new ElementScope(metamodel.getMClass(Operation.class), true, null, true),
                new ElementScope(metamodel.getMClass(Collaboration.class), true, null, true),
                new ElementScope(metamodel.getMClass(Activity.class), true, null, true)
                ));
    }

    @objid ("5ff6db3e-caa5-441e-9bd6-a619ae901c3c")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return !ConstraintBlockFilter.isAConstraintBlock (owner) && !(owner instanceof Profile);
    }

    @objid ("da906817-d4e1-4d22-ba45-d0e376b1d666")
    @Override
    public void dispose() {
        // Nothing to do
    }

}
