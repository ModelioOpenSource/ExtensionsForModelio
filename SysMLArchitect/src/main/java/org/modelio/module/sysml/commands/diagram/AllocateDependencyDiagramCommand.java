/**
 * Java Class : AddAllocateDependencyDiagramCommand.java
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
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.SysMLFactory;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * The diagram command which handles the creation of an Allocate Dependency
 * @author ebrosse
 */
@objid ("59e146ec-7482-4bbf-a9d2-7cbc0a29b3a8")
public class AllocateDependencyDiagramCommand extends DefaultLinkTool {
    /**
     * Default constructor
     */
    @objid ("a18dec92-3168-4450-a5f4-70283758fa4f")
    public AllocateDependencyDiagramCommand() {
    }

    /**
     * Method acceptFirstElement
     * @author ebrosse
     *
     * @param representation : the diagram handle
     * @param target : the tested node
     * @return the boolean representing the acceptation
     */
    @objid ("a70a3757-28a6-4994-bc1b-c3fb885d95af")
    @Override
    public boolean acceptFirstElement(IDiagramHandle representation, IDiagramGraphic target) {
        MObject element = target.getElement();
        return ((element.getStatus().isModifiable ())
                        && (element instanceof ModelElement));
    }

    @objid ("9e3a6ce8-8bc6-4886-a66f-d221e69611c3")
    @Override
    public boolean acceptSecondElement(IDiagramHandle representation, IDiagramGraphic source, IDiagramGraphic target) {
        MObject element = target.getElement();
        return  (element instanceof ModelElement) ;
    }

    @objid ("c48f42c1-3176-4bfd-94af-a5911dbdd3f7")
    @Override
    public void actionPerformed(IDiagramHandle representation, IDiagramGraphic origin, IDiagramGraphic target, LinkRouterKind kind, ILinkRoute path) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        try( ITransaction transaction = session.createTransaction (I18nMessageService.getString ("Info.Session.Create", "Allocate"))){
            ModelElement originElement = (ModelElement) origin.getElement();
            ModelElement targetElement = (ModelElement) target.getElement();

            Dependency dependency = SysMLFactory.createAllocateAbstraction(originElement, targetElement);

            List<IDiagramGraphic> graphics = representation.unmask(dependency, 0, 0);

            for (IDiagramGraphic graphic : graphics ){
                if (graphic.getElement().equals(dependency)){
                    IDiagramLink link = (IDiagramLink) graphic;
                    try {

                        link.setRoute(path);
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
