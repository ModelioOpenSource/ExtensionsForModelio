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

import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;

public class TogafProcessFlowDiagram {
    private BpmnProcess process = null;
    private BpmnProcessDesignDiagram diagram;

    public TogafProcessFlowDiagram(ModelElement owner, String diagramName) {
        IUmlModel model = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel();

        if (owner instanceof BpmnProcess) {
            this.process = (BpmnProcess) owner;
        }

        if (this.process != null) {
             this.diagram = (BpmnProcessDesignDiagram) model
                    .createElement("BpmnProcessDesignDiagram");

            Stereotype sterType = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions()
                    .getStereotype("TogafArchitect", "TogafProcessFlowDiagram", this.diagram.getMClass());
            this.diagram.getExtension().add(sterType);

            this.diagram.setOrigin(this.process);
            this.diagram.setName(diagramName);

            TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(this.diagram);
        }
    }

    public BusinessArchitecture getBusinessArchitecture() {
        return new BusinessArchitecture((Package) this.process.getOwner());
    }

    public void addBusinessArchitecture(BusinessArchitecture owner) {
        this.process.setOwner(owner.getElement());
    }

    
    public BpmnProcessDesignDiagram getElement(){
        return this.diagram;
    }


}
