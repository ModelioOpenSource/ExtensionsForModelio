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
package org.modelio.togaf.profile.businessarchitecture.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.utils.Module;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafBusinessFootPrintDiagram {
    protected StaticDiagram element;

    public TogafBusinessFootPrintDiagram(ModelElement owner, IStyleHandle style) {
        try {
            this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createStaticDiagram(owner.getName() + " " + ResourceManager.getName("TogafBusinessFootPrintDiagram"), owner,
                    TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect", "TogafBusinessFootPrintDiagram",
                            TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class)));

            try (IDiagramHandle diagramHandler = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(getElement())) {
                diagramHandler.getDiagramNode().setStyle(style);
                diagramHandler.save();
                diagramHandler.close();
            }

            IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
            IPeerModule peer = Module.getPeer();
            Note note = session.getModel().createNote(TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getNoteType("TogafArchitect", "TOGAFModelingGuide", getElement().getMClass()), getElement(),
                    org.modelio.togaf.i18n.Messages.getString("TogafBusinessFootPrintDiagram_NOTE"));
            try (IDiagramHandle rep = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService().getDiagramHandle(getElement())) {
                IDiagramNode node = (IDiagramNode) rep.unmask(note, 0, 0).get(0);
                node.setBounds(new Rectangle(0, 0, 500, 300));
                rep.save();
                rep.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TogafBusinessFootPrintDiagram(StaticDiagram element) {
        this.element = element;
    }

    public StaticDiagram getElement() {
        return this.element;
    }

    public void setParent(ModelElement parent) {
        this.element.setOrigin(parent);
    }

    public BusinessArchitecture getBusinessArchitecture() {
        return new BusinessArchitecture((Package) this.element.getOrigin());
    }

    public void addBusinessArchitecture(BusinessArchitecture model) {
        this.element.setOrigin(model.getElement());
    }

}
