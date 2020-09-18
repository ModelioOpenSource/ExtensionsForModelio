/**
 * Java Class : AddPackageDiagramExplorerCommand.java
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
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
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
 * This class handles the creation of SysML package diagram
 * 
 * @author ebrosse
 */
@objid ("0f316b78-9b9b-4758-9ef9-0567dd4f7032")
public class PackageDiagramWizard extends AbstractDiagramWizardContributor {
    @objid ("c70fe35a-b643-4f7e-a2fb-1a8bcb918b0f")
    @Override
    public AbstractDiagram actionPerformed(ModelElement element, String diagramName, String description) {
        IModuleContext moduleContext = SysMLModule.getInstance().getModuleContext();
        IModelingSession session = moduleContext.getModelingSession();
        String name = element.getName() + " " + diagramName;
        StaticDiagram diagram = null;
        
        try (ITransaction transaction = session.createTransaction(I18nMessageService.getString("Info.Session.Create", "Package Diagram"))) {
        
            diagram = SysMLFactory.createPackageDiagram(element, name, description);
        
            if (diagram != null) {
        
                IDiagramService ds = moduleContext.getModelioServices().getDiagramService();
                try(IDiagramHandle handler = ds.getDiagramHandle(diagram);){
                    IDiagramDG dg = handler.getDiagramNode();
                    for (IStyleHandle style : ds.listStyles()) {
                        if (style.getName().equals("sysmlpackage")) {
                            dg.setStyle(style);
                            break;
                        }
                    }
        
                    handler.save();
                    handler.close();
                }
        
                moduleContext.getModelioServices().getEditionService().openEditor(diagram);
        
            }
            transaction.commit();
        } catch (ExtensionNotFoundException e) {
            SysMLModule.logService.error(e);
        }
        return diagram;
    }

    @objid ("230367ce-1ee8-43f7-9f8a-484e8c11d5cf")
    @Override
    public ElementDescriptor getCreatedElementType() {
        IModuleContext moduleContext = getModule().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        MClass mClass = metamodel.getMClass(StaticDiagram.class);
        IMetamodelExtensions extensions = moduleContext.getModelingSession().getMetamodelExtensions();
        Stereotype stereotype = extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PACKAGEDIAGRAM, mClass);
        return stereotype != null ? new ElementDescriptor(mClass, stereotype) : null;
    }

    @objid ("d80daf43-fa9b-4525-8743-dbf2abe22837")
    public PackageDiagramWizard() {
        super();
        
        setLabel(I18nMessageService.getString ("Ui.Command.PackageDiagramExplorerCommand.Label"));
        setDetails(I18nMessageService.getString ("Ui.Command.PackageDiagramExplorerCommand.Details"));
        setIconDescriptor(ImageDescriptor.createFromFile(null, SysMLResourcesManager.getInstance().getImage("packagediagram.png")));
        setInformation(I18nMessageService.getString ("Ui.Command.PackageDiagramExplorerCommand.Information"));
        
        IModuleContext moduleContext = SysMLModule.getInstance().getModuleContext();
        MMetamodel metamodel = moduleContext.getModelioServices().getMetamodelService().getMetamodel();
        setScopes(Arrays.asList(
                new ElementScope(metamodel.getMClass(Package.class), true, null, true)
                ));
    }

    @objid ("8a34bdec-3850-4e28-af79-b7197dc8b7b7")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return true;
    }

    @objid ("379c96f4-dfda-446c-8730-d23981af774d")
    @Override
    public void dispose() {
        // Nothing to do
    }

}
