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
package org.modelio.togaf.profile.utils;

import java.util.Vector;

import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Operation;

public class NameFormater {

	public static String formatName(String name, ModelElement element) {
		Vector<String> sim_names = new Vector<>();

		if (element instanceof ModelTree) {
			for (ModelElement sub : ((ModelTree) element).getOwner().getOwnedElement()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
		} else if (element instanceof Attribute) {
			
			for (Attribute sub : ((Attribute) element).getOwner().getOwnedAttribute()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
			
		}else if (element instanceof Operation) {
			
			for (Operation sub : ((Operation) element).getOwner().getOwnedOperation()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
			
		}else if (element instanceof AssociationEnd) {
			
			for (AssociationEnd sub : ((AssociationEnd) element).getSource().getOwnedEnd()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
			
		} else if (element instanceof BindableInstance) {
			if (((BindableInstance) element).getCluster() != null) {
				for (BindableInstance sub : ((BindableInstance) element).getCluster().getPart()) {
					if (!sub.equals(element))
						sim_names.add(sub.getName());
				}
			}
			if (((BindableInstance) element).getInternalOwner() != null) {
				for (BindableInstance sub : ((BindableInstance) element).getInternalOwner().getInternalStructure()) {
					if (!sub.equals(element))
						sim_names.add(sub.getName());
				}
			}

		} else if (element instanceof Instance) {
			for (Instance sub : ((Instance) element).getOwner().getDeclared()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
		} else if (element instanceof Behavior) {
			for (Behavior sub : ((Behavior) element).getOwner().getOwnedBehavior()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
		} else if (element instanceof EnumerationLiteral) {
			for (EnumerationLiteral sub : ((EnumerationLiteral) element).getValuated().getValue()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
		} else if (element instanceof AbstractDiagram) {
			for (AbstractDiagram sub : ((StaticDiagram) element).getOrigin().getProduct()) {
				if (!sub.equals(element))
					sim_names.add(sub.getName());
			}
		}

		boolean isUnique = false;
		int rank = 0;
		while (!isUnique) {
			if ((rank == 0 && sim_names.contains(name)) || sim_names.contains(name + rank)) {
				rank++;
			} else {
				isUnique = true;
			}

		}

		if (rank != 0) {
			return name + rank;
		}
		return name;
	}

}
