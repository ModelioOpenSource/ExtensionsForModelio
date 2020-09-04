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

import java.util.List;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnProcessDesignDiagram;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.structure.model.BusinessArchitecture;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafProcess {
	protected BpmnProcess element;

	public TogafProcess() {
		this.element = (BpmnProcess) TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createElement(TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(BpmnProcess.class).getName());
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafProcess");
		this.element.setName(ResourceManager.getName("TogafProcess"));

		BpmnProcessDesignDiagram diargam = (BpmnProcessDesignDiagram) TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createElement(TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(BpmnProcessDesignDiagram.class).getName());
		diargam.setName(ResourceManager.getName("TogafProcess"));
		diargam.setOrigin(this.element);
	}

	public TogafProcess(BpmnProcess element) {
		this.element = element;
	}

	public BpmnProcess getElement() {
		return this.element;
	}

	public void setParent(NameSpace parent) {
		this.element.setOwner(parent);
	}

	public void setParent(Operation parent) {
		this.element.setOwnerOperation(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public String getcriticality() {
		String res = ModelUtils.getTaggedValue("TogafProcess_criticality", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setcriticality(String value) {
		ModelUtils.addValue("TogafArchitect","TogafProcess_criticality", value, this.element);
	}

	public boolean isisAutomated() {
		return ModelUtils.hasTaggedValue("TogafProcess_isAutomated", this.element);
	}

	public void isisAutomated(boolean value) {
		ModelUtils.addValue("TogafArchitect","TogafProcess_isAutomated", value, this.element);
	}

	public String getvolumetrics() {
		String res = ModelUtils.getTaggedValue("TogafProcess_volumetrics", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setvolumetrics(String value) {
		ModelUtils.addValue("TogafArchitect","TogafProcess_volumetrics", value, this.element);
	}

	public BusinessArchitecture getBusinessArchitecture() {
		return new BusinessArchitecture((Package) this.element.getOwner());
	}

	public void addBusinessArchitecture(BusinessArchitecture model) {
		this.element.setOwner(model.getElement());
	}

	public List<Initiator> getInitiator() {
		List<Initiator> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Initiator(model_element));
		}
		return res;
	}

	public void addInitiator(Initiator model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<Participates> getParticipates() {
		List<Participates> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Participates(model_element));
		}
		return res;
	}

	public void addParticipates(Participates model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<Owner> getOwner() {
		List<Owner> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new Owner(model_element));
		}
		return res;
	}

	public void addOwner(Owner model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<IOFlow> getInIOFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addInIOFlow(IOFlow model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<IOFlow> getOutIOFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addOutIOFlow(IOFlow model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

	public List<ServiceProcessSupport> getInServiceProcessSupport() {
		List<ServiceProcessSupport> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new ServiceProcessSupport(model_element));
		}
		return res;
	}

	public void addInServiceProcessSupport(ServiceProcessSupport model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<ServiceProcessSupport> getOutServiceProcessSupport() {
		List<ServiceProcessSupport> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getDependsOnDependency()) {
			res.add(new ServiceProcessSupport(model_element));
		}
		return res;
	}

	public void addOutServiceProcessSupport(ServiceProcessSupport model) {
		this.element.getDependsOnDependency().add(model.getElement());
	}

}
