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


public class Utils {
    
    private static String namespacingSeparator = "::";

    
    private static String elementSeparator = ";";

    
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
//    
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
