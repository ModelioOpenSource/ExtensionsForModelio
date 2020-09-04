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

import java.util.Iterator;
import java.util.List;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.togaf.impl.TogafArchitectModule;

public class ModelUtils {

	public static void setStereotype(ModelElement element,String module, String stereotype) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {

			Stereotype ster = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getStereotype(module,stereotype,element.getMClass());
			element.getExtension().add(ster);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addValue(String module,String name, String values, ModelElement _element) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {
			boolean exist = false;

			List<TaggedValue> tagElements = _element.getTag();
			Iterator<TaggedValue> itChildren = tagElements.iterator();
			while (itChildren.hasNext()) {
				TaggedValue tag = itChildren.next();
				TagType type = tag.getDefinition();
				String tagname = type.getName();

				if (tagname.equals(name)) {
					exist = true;

					List<TagParameter> actualElements = tag.getActual();
					if (values != null) {
						TagParameter tagParam = actualElements.get(0);
						tagParam.setValue(values);
					}
				}
			}
			if (!exist) {
				IUmlModel model = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel();
				TagType tag = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getTagType(module, name, _element.getMClass());
				TaggedValue taggedValue = model.createTaggedValue(tag, _element);
				if (values != null) {
					TagParameter param = model.createTagParameter();
					param.setValue(values);
					taggedValue.getActual().add(param);
				}
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTaggedValue(String tagtype, ModelElement _element) {
		List<TaggedValue> tagElements = _element.getTag();
		Iterator<TaggedValue> itChildren = tagElements.iterator();

		while (itChildren.hasNext()) {
			TaggedValue tag = itChildren.next();
			TagType type = tag.getDefinition();
			String tagname = type.getName();
			if (tagname.equals(tagtype)) {
				List<TagParameter> actualElements = tag.getActual();
				TagParameter tagParam = actualElements.get(0);
				return tagParam.getValue();
			}

		}
		return "";
	}

	public static void addValue(String module,String name, boolean values, ModelElement _element) {
		IModelingSession session = TogafArchitectModule.getInstance().getModuleContext().getModelingSession();
		try (ITransaction transaction = session.createTransaction("");) {
			boolean exist = false;

			List<TaggedValue> tagElements = _element.getTag();
			Iterator<TaggedValue> itChildren = tagElements.iterator();
			while (itChildren.hasNext()) {
				TaggedValue tag = itChildren.next();
				TagType type = tag.getDefinition();
				String tagname = type.getName();

				if (tagname.equals(name)) {
					exist = true;
				}
			}

			if (!exist) {
				IUmlModel model = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getModel();
				TagType tag = TogafArchitectModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions().getTagType(module, name, _element.getMClass());
				model.createTaggedValue(tag, _element);
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean hasTaggedValue(String tagtype, ModelElement _element) {
		List<TaggedValue> tagElements = _element.getTag();
		Iterator<TaggedValue> itChildren = tagElements.iterator();

		while (itChildren.hasNext()) {
			TaggedValue tag = itChildren.next();
			TagType type = tag.getDefinition();
			String tagname = type.getName();
			if (tagname.equals(tagtype)) {
				return true;
			}

		}
		return false;
	}

}
