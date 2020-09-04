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

import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.togaf.impl.TogafArchitectModule;
import org.modelio.togaf.profile.utils.ModelUtils;
import org.modelio.togaf.profile.utils.ResourceManager;

public class TogafService {
	protected Interface element;

	public TogafService() {
		this.element = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel().createInterface();
		ModelUtils.setStereotype(this.element, "TogafArchitect","TogafService");
		this.element.setName(ResourceManager.getName("TogafService"));
	}

	public TogafService(Interface element) {
		this.element = element;
	}

	public Interface getElement() {
		return this.element;
	}

	public void setParent(ModelTree parent) {
		this.element.setOwner(parent);
	}

	public void setParent(TemplateParameter parent) {
		this.element.setOwnerTemplateParameter(parent);
	}

	public String gettype() {
		String res = ModelUtils.getTaggedValue("TogafService_type", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void settype(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_type", value, this.element);
	}

	public String getbusinessImportance() {
		String res = ModelUtils.getTaggedValue("TogafService_businessImportance", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setbusinessImportance(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_businessImportance", value, this.element);
	}

	public String getQOS() {
		String res = ModelUtils.getTaggedValue("TogafService_QOS", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setQOS(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_QOS", value, this.element);
	}

	public String getSLA() {
		String res = ModelUtils.getTaggedValue("TogafService_SLA", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setSLA(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_SLA", value, this.element);
	}

	public String gettroughput() {
		String res = ModelUtils.getTaggedValue("TogafService_troughput", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void settroughput(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_troughput", value, this.element);
	}

	public String gettroughputPeriod() {
		String res = ModelUtils.getTaggedValue("TogafService_troughputPeriod", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void settroughputPeriod(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_troughputPeriod", value, this.element);
	}

	public String getgrowth() {
		String res = ModelUtils.getTaggedValue("TogafService_growth", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setgrowth(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_growth", value, this.element);
	}

	public String getgrowthPeriod() {
		String res = ModelUtils.getTaggedValue("TogafService_growthPeriod", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setgrowthPeriod(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_growthPeriod", value, this.element);
	}

	public String getserviceTimes() {
		String res = ModelUtils.getTaggedValue("TogafService_serviceTimes", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setserviceTimes(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_serviceTimes", value, this.element);
	}

	public String getpeakProfileShortTerm() {
		String res = ModelUtils.getTaggedValue("TogafService_peakProfileShortTerm", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setpeakProfileShortTerm(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_peakProfileShortTerm", value, this.element);
	}

	public String getpeakProfileLongTerm() {
		String res = ModelUtils.getTaggedValue("TogafService_peakProfileLongTerm", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setpeakProfileLongTerm(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_peakProfileLongTerm", value, this.element);
	}

	public String getRACI() {
		String res = ModelUtils.getTaggedValue("TogafService_RACI", this.element);
		if (res.equals("")) {
			res = "";
		}
		return res;
	}

	public void setRACI(String value) {
		ModelUtils.addValue("TogafArchitect","TogafService_RACI", value, this.element);
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

	public List<IOFlow> getTargetIOFlow() {
		List<IOFlow> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new IOFlow(model_element));
		}
		return res;
	}

	public void addTargetIOFlow(IOFlow model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public List<TogafConsumes> getTogafConsumes() {
		List<TogafConsumes> res = new java.util.ArrayList<>();
		for (Dependency model_element : this.element.getImpactedDependency()) {
			res.add(new TogafConsumes(model_element));
		}
		return res;
	}

	public void addTogafConsumes(TogafConsumes model) {
		this.element.getImpactedDependency().add(model.getElement());
	}

	public TogafFunction getTogafFunction() {
		return new TogafFunction((Interface) this.element.getOwner());
	}

	public void addTogafFunction(TogafFunction model) {
		this.element.setOwner(model.getElement());
	}

	public List<TogafServiceOperation> getTogafServiceOperation() {
		List<TogafServiceOperation> res = new java.util.ArrayList<>();
		for (Operation model_element : this.element.getOwnedOperation()) {
			res.add(new TogafServiceOperation( model_element));
		}
		return res;
	}

	public void addTogafServiceOperation(TogafServiceOperation model) {
		this.element.getOwnedOperation().add(model.getElement());
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

	public List<TogafServiceContract> getTogafServiceContract() {
		List<TogafServiceContract> res = new java.util.ArrayList<>();
		for (ModelTree model_element : this.element.getOwnedElement()) {
			res.add(new TogafServiceContract((Collaboration) model_element));
		}
		return res;
	}

	public void addTogafServiceContract(TogafServiceContract model) {
		this.element.getOwnedElement().add(model.getElement());
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

}
