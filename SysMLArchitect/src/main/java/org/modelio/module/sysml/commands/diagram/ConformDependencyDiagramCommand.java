/**
 * Java Class : AddConformDependencyDiagramCommand.java
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
 * @category   Command Diagram
 * @package    org.modelio.module.sysml.gui.diagram
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.commands.diagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.sysml.filters.ViewFilter;
import org.modelio.module.sysml.filters.ViewpointFilter;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of a Conform dependency
 * @author ebrosse
 */

public class ConformDependencyDiagramCommand extends DefaultLinkTool {
    /**
     * Default constructor
     */
    
    public ConformDependencyDiagramCommand() {
        super();
    }

    
    @Override
    public boolean acceptFirstElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (element.getStatus().isModifiable ()
                                                                        &&  (ViewFilter.isAView (element)) 
                                                                        && (Utils.getViewpoint ((Package) element) == null));
    }

    
    @Override
    public boolean acceptSecondElement(IDiagramHandle representation, IDiagramGraphic origin, IDiagramGraphic target) {
        MObject element = target.getElement();
        return (ViewpointFilter.isAViewpoint (element));
    }

    
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic origin, IDiagramGraphic target, LinkRouterKind kind, ILinkRoute path) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Conform"))){
        
            ModelElement originElement = (ModelElement) origin.getElement();
            ModelElement targetElement = (ModelElement) target.getElement();
            Dependency dependency = SysMLFactory.createConformDependency ((Package) originElement, (Class) targetElement);
            List<IDiagramGraphic> graphics = representation.unmask(dependency, 0 , 0);
        
            for (IDiagramGraphic graphic :graphics ){
                if (graphic.getElement().equals(dependency)){
                    IDiagramLink link = (IDiagramLink) graphic;
                    try {
                        link.setRoute (path);
                    } catch (InvalidPointsPathException e) {
                        SysMLModule.logService.error(e);
                    } catch (InvalidSourcePointException e) {
                        SysMLModule.logService.error(e);
                    } catch (InvalidDestinationPointException e) {
                        SysMLModule.logService.error(e);
                    }
                }
            }
            representation.save();
            representation.close();
            transaction.commit ();
        }
    }

}
