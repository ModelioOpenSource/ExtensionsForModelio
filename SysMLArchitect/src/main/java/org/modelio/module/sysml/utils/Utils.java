/**
 * Java Class : Utils.java
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
 * @category   Util
 * @package    com.modeliosoft.modelio.sysml.utils
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.filters.ViewpointFilter;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7e4d165a-97e6-4c08-9f59-2ddbcbc4c41f")
public class Utils {
    @objid ("03c844c0-55b0-498c-86b3-93aa9326ade4")
    private static String namespacingSeparator = "::";

    @objid ("1eb66349-9e40-455c-95a6-a415cd482946")
    private static String elementSeparator = ";";

    @objid ("f1bc4fde-ed8c-4d63-9266-5be37e655fc2")
    public static boolean accept(MObject selectedElement) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        for (MObject libRoot : model.getLibraryRoots()){
            if (selectedElement.equals(libRoot)){
                return false;
            }
        }
        
        for (MObject modelRoot : model.getModelRoots()){
            if (selectedElement.equals(modelRoot)){
                return false;
            }
        }
        return !((selectedElement.equals(model)));
    }

    /**
     * @return
     */
    @objid ("eba0e676-65d5-452e-854b-fb153ee6c171")
    public static List<Stereotype> computePropertyList(final ModelElement element) {
        List<Stereotype> result = new ArrayList<>();
        int i = 0;
        
        for (Stereotype ster : element.getExtension()) {
            if (ster.getOwner().getOwnerModule().getName().equals("SysMLArchitect")) {
                if (!(result.contains(ster))) {
                    result.add(ster);
        
                    Stereotype parent = ster.getParent();
                    while ((parent != null) && (!(result.contains(parent)))) {
                        result.add(i, parent);
                        ster = parent;
                        parent = ster.getParent();
                    }
                    i = result.size();
                }
        
            }
        }
        return result;
    }

    /**
     * Method getFreeName
     * @author ebrosse
     * @return
     */
    @objid ("5ca7cf77-4aec-4465-8577-0588c7ad472d")
    public static String getFreeName(ModelElement parent, String type, int nb) {
        ArrayList<ModelElement> children = null;
        StringBuffer testedName;
        
        testedName = new StringBuffer(type);
        if (nb != 0) {
            if ((type == SysMLStereotypes.BLOCKDIAGRAM) || (type == SysMLStereotypes.INTERNALBLOCKDIAGRAM)
                    || (type == SysMLStereotypes.PARAMETRICDIAGRAM)) {
                testedName.append(" (" + nb + ")");
        
            } else {
                testedName.append(nb);
            }
        
        }
        
        // Retrieve the children list
        if ((type == SysMLStereotypes.BLOCKDIAGRAM) || (type == SysMLStereotypes.INTERNALBLOCKDIAGRAM)
                || (type == SysMLStereotypes.PARAMETRICDIAGRAM)) {
            ModelTree element = (ModelTree) parent;
            children = new ArrayList<>(element.getProduct(StaticDiagram.class));
        
        } else if (type == SysMLStereotypes.VIEW) {
            ModelTree element = (ModelTree) parent;
            children = new ArrayList<>(element.getOwnedElement(Package.class));
        
        } else if ((type == SysMLStereotypes.BLOCK) || (type == SysMLStereotypes.VIEWPOINT)
                || (type == SysMLStereotypes.CONSTRAINTBLOCK)) {
            ModelTree element = (ModelTree) parent;
            children = new ArrayList<>(element.getOwnedElement(Class.class));
        
        } else if (type == SysMLStereotypes.FLOWSPECIFICATION) {
            ModelTree element = (ModelTree) parent;
            children = new ArrayList<>(element.getOwnedElement(Interface.class));
        
        } else if (type == SysMLStereotypes.VALUETYPE) {
            ModelTree element = (ModelTree) parent;
            children = new ArrayList<>(element.getOwnedElement(DataType.class));
        
        } else if (type == SysMLStereotypes.FLOWPORT) {
            if (parent instanceof Instance) {
                Instance element = (Instance) parent;
                children = new ArrayList<>(element.getPart(Port.class));
            } else {
                Classifier element = (Classifier) parent;
                children = new ArrayList<>(element.getInternalStructure(Port.class));
            }
        }
        
        if (children != null) {
            // On recherche parmi tous les enfants si le nom choisi existe
            for (ModelElement child : children) {
                if (child.getName().equals(testedName.toString())) {
                    return Utils.getFreeName(parent, type, nb + 1);
                }
            }
        }
        
        // Si on arrive ici, cela veut dire que le nom choisi n'existe pas.
        return testedName.toString();
    }

    /**
     * Method getViewpoint
     * @author ebrosse
     * @return
     */
    @objid ("aef47cfa-a040-4d69-a3bd-a6b27d9249da")
    public static Class getViewpoint(Package view) {
        List<Dependency> dependencies = view.getDependsOnDependency();
        for (Dependency dependency : dependencies) {
            ModelElement viewpoint = dependency.getDependsOn();
            if (ViewpointFilter.isAViewpoint(viewpoint)) {
                return (Class) viewpoint;
            }
        }
        return null;
    }

    /**
     * Method getNameSpaceOwner
     * @author ebrosse
     * @return
     */
    @objid ("c7f0c313-78ea-447b-9ba7-4910250a4f4b")
    public static NameSpace getNameSpaceOwner(MObject element) {
        MObject parent = element.getCompositionOwner();
        while (!(parent instanceof NameSpace)) {
            parent = parent.getCompositionOwner();
        }
        return (NameSpace) parent;
    }

//    /**
//     * Method setUMLFreeName
//     * @author ebrosse
//     * @param element
//     * @param testedName @return
//     */
//    @objid ("3fe6b6b6-ea84-4248-bc77-c3d4afdeb8bd")
//    public static void setUMLFreeName(ModelElement element, String testedName) {
//        element.setName("");
//        int i = 1;
//        String extension = "";
//        Boolean find = true;
//        while (find) {
//            find = false;
//            for (MObject sub : element.getCompositionOwner().getCompositionChildren()) {
//                if (sub.getName().equals(testedName + extension)) {
//                    extension = String.valueOf(i);
//                    i++;
//                    find = true;
//                    break;
//                }
//            }
//        }
//        element.setName(testedName + extension);
//    }
    /**
     * Method getAbsoluteNames
     * @author ebrosse
     * @return
     */
    @objid ("b1350628-e92c-4349-8d03-816b4456221c")
    public static String[] getAbsoluteNames(ArrayList<ModelElement> listElt) {
        String[] result = new String[listElt.size()];
        int i = 0;
        for (ModelElement elt : listElt) {
            result[i] = getAbsoluteName(elt);
            i++;
        }
        return result;
    }

    /**
     * Method getNames
     * @author ebrosse
     * @return
     */
    @objid ("3f391c00-3088-469e-bbec-51289fd2e1d0")
    public static String[] getNames(Collection<? extends MObject> listElt) {
        String[] result = new String[listElt.size() + 1];
        List<String> temp = new ArrayList<>();
        temp.add("");
        
        for (MObject elt : listElt) {
            MObject owner = elt.getCompositionOwner();
            if ((owner != null) && (owner instanceof ModelElement) && !((ModelElement) owner).getName().equals("")) {
                temp.add(((ModelElement) owner).getName() + namespacingSeparator + elt.getName());
            } else {
                temp.add(elt.getName());
            }
        }
        Collections.sort(temp);
        result = temp.toArray(result);
        return result;
    }

    /**
     * Method getName
     * @author ebrosse
     * @return
     */
    @objid ("c1624fa6-61ab-44e8-9d3d-889a1de3696c")
    public static String getName(MObject elt) {
        MObject owner = elt.getCompositionOwner();
        
        if ((owner != null) && (owner instanceof ModelElement) && !((ModelElement) owner).getName().equals("")) {
            return ((ModelElement) owner).getName() + namespacingSeparator + elt.getName();
        } else {
            return elt.getName();
        }
    }

    /**
     * Method getAbsoluteName
     * @author ebrosse
     * @return
     */
    @objid ("fb4e9f93-1b49-4cc0-a8d4-b2d49f569144")
    public static String getAbsoluteName(ModelElement elt) {
        String result = elt.getName();
        MObject temp = elt;
        while (temp.getCompositionOwner() != null) {
            temp = temp.getCompositionOwner();
            if (temp instanceof ModelElement) {
                result = ((ModelElement) temp).getName() + namespacingSeparator + result;
            }
        }
        return result;
    }

    /**
     * Method getAbsoluteNamesWithSeparator
     * @author ebrosse
     * @return
     */
    @objid ("1b5f0e0b-4e7a-4a0c-9943-eeb467e010fb")
    public static String getAbsoluteNamesWithSeparator(List<ModelElement> listElt) {
        String result = "";
        int size = listElt.size();
        if (size > 1) {
        
            for (ModelElement elt : listElt) {
                result = result.concat(" " + elementSeparator + " " + getAbsoluteName(elt));
            }
        
            result = result.replaceFirst(" " + elementSeparator + " " , "");
        
        } else if (size == 1) {
            result = getAbsoluteName(listElt.get(0));
        }
        return result;
    }

}
