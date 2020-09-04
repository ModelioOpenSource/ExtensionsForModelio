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
package org.modelio.togaf.profile.structure.commande.diagram;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.tools.DefaultLinkTool;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.togaf.impl.TogafArchitectModule;

public class UMLInformationFLowDiagramCommand extends DefaultLinkTool {

	@Override
	public boolean acceptFirstElement(IDiagramHandle arg0, IDiagramGraphic arg1) {
		if (arg1.getElement() instanceof ModelElement) {
			return true;
		}
		return false;
	}

	@Override
	public boolean acceptSecondElement(IDiagramHandle arg0, IDiagramGraphic arg1, IDiagramGraphic arg2) {
		if (arg2.getElement() instanceof ModelElement) {
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(IDiagramHandle representation, IDiagramGraphic source, IDiagramGraphic destination, LinkRouterKind kind, ILinkPath path) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {
			IUmlModel model = session.getModel();

			UmlModelElement ob_source = (UmlModelElement) source.getElement();
			UmlModelElement ob_destination = (UmlModelElement) destination.getElement();

			Dependency dflow = null;
			for (Dependency flow : ob_source.getImpactedDependency()) {

				if (flow.isStereotyped("TogafArchitect","flow") && ob_destination.equals(flow.getImpacted())) {
					dflow = flow;
				}
			}

			for (Dependency flow : ob_source.getDependsOnDependency()) {
				if (flow.isStereotyped("TogafArchitect","flow") && ob_destination.equals(flow.getDependsOn())) {
					dflow = flow;
				}
			}

			if (dflow == null) {

				try (ITransaction transaction2 = session.createTransaction("");) {
					dflow = model.createDependency((ModelElement) source.getElement(), (ModelElement) destination.getElement(),TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype("TogafArchitect", "flow",TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Dependency.class)));
					dflow.setName("");
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<IDiagramGraphic> graph = representation.unmask(dflow, 0, 0);
			IDiagramLink link = (IDiagramLink) graph.get(0);
			link.setRouterKind(kind);
			link.setPath(path);

			InformationFlow gen = model.createInformationFlow();

			if (ob_source instanceof NameSpace) {
				gen.setOwner((NameSpace) ob_source);
			} else if (ob_source instanceof Port) {
				Port p_source = (Port) ob_source;
				if (p_source.getOwner() != null) {
					gen.setOwner(p_source.getOwner());
				} else if (p_source.getBase() != null) {
					gen.setOwner(p_source.getBase());
				} else if (p_source.getInternalOwner() != null) {
					gen.setOwner(p_source.getInternalOwner());
				}
			} else if (ob_source instanceof BindableInstance) {
				BindableInstance i_source = (BindableInstance) ob_source;
				Classifier namespace = i_source.getInternalOwner();
				gen.setOwner(namespace);
			} else if (ob_source instanceof Instance) {
				Instance i_source = (Instance) ob_source;
				NameSpace namespace = i_source.getOwner();
				gen.setOwner(namespace);
			}

			gen.getInformationSource().add(ob_source);
			gen.getInformationTarget().add(ob_destination);
			gen.setName("InformationFlow");

			List<IDiagramGraphic> lgraph = representation.unmask(gen, 0, 0);
			IDiagramLink slink = (IDiagramLink) lgraph.get(0);
			slink.setProperty("stereotype", "none");
			representation.unmask(gen, 0, 0);
			representation.save();

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
