/**
 * Java Class : ModelUtils.java
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
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * @author Tony Marchand This class provides services on model. At the moment, services are about tagged values.
 */
@objid ("c98c4981-c6fe-4a06-89ca-6e7daa7678e8")
public class ModelUtils {
    /**
     * This operation returns the first tagged value with the &lt;tagName&gt; name on the &lt;element&gt; ModelElement.
     * @param MObject
     * ModelElement on which the tagged value is search for.
     * 
     * @param tagName String containing the tagged value name
     * @return The TaggedValue
     */
    @objid ("7e417d2c-009d-4c63-86cc-49020a7442cd")
    public static TaggedValue getFirstTaggedValue(ModelElement element, String tagName) {
        TaggedValue tag = null;
        int i;
        List<TaggedValue> tags = element.getTag();
        for (i = 0; (i < tags.size()) && (tag == null); i++) {
            TaggedValue localTag = tags.get(i);
            if (localTag.getDefinition().getName().contentEquals(tagName)) {
                tag = localTag;
            }
        }
        return tag;
    }

    /**
     * This operation returns the parameter values of the first tagged value with the &lt;tagName&gt; name on the &lt;element&gt;
     * ModelElement. The tagged value must have a parameter.
     * @param MObject
     * ModelElement on which the tagged value is search for.
     * 
     * @param tagName String containing the tagged value name
     * @return The parameter values
     */
    @objid ("4e584430-2e49-467e-a3e9-1c5cd79280c3")
    public static ArrayList<String> getParametersOfTag(ModelElement element, String tagName) {
        List<TaggedValue> tags = element.getTag();
        ArrayList<String> parameters = null;
        
        for (TaggedValue tag : tags) {
            if (tag.getDefinition().getName().contentEquals(tagName)) {
                parameters = new ArrayList<>(tag.getActual().size());
                for (TagParameter parameter : tag.getActual()) {
                    parameters.add(parameter.getValue());
                }
            }
        }
        return parameters;
    }

    /**
     * This operation returns the content of the first parameter of the first tagged value with the &lt;tagName&gt; name on the
     * &lt;element&gt; ModelElement. The tagged value must have a parameter.
     * @param MObject
     * ModelElement on which the tagged value is search for.
     * 
     * @param tagName String containing the tagged value name
     * @return The value of the first tag parameter
     */
    @objid ("a53e4797-81d4-482f-a764-b9804fac92ff")
    public static String getProperty(ModelElement element, String tagName) {
        TaggedValue tag = getFirstTaggedValue(element, tagName);
        String value;
        if (tag != null) {
            value = tag.getActual().get(0).getValue();
        } else {
            value = "";
        }
        return value;
    }

    /**
     * Method getNearestClassifier
     * @author ebrosse
     * @return
     */
    @objid ("60e5b461-b630-462e-b447-34f48619077b")
    public static NameSpace getNearestNameSpace(ModelElement source) {
        ModelElement owner = source;
        
        while (!(owner instanceof NameSpace)) {
        
            if (owner instanceof BindableInstance) {
                if (((BindableInstance) owner).getInternalOwner() != null)
                    owner = ((BindableInstance) owner).getInternalOwner();
                else
                    owner = ((BindableInstance) owner).getCluster();
            } else if (owner instanceof Instance) {
                owner = ((Instance) owner).getOwner();
            }
        
        }
        return (NameSpace) owner;
    }

    /**
     * Method addValue
     * @author ebrosse
     * @return
     */
    @objid ("19fd6e45-4698-4507-91a1-ea202c42d180")
    public static void addValue(String modulename, String name, String values, ModelElement element) {
        // DON'T place Transition HERE
        boolean exist = false;
        List<TaggedValue> tagElements = element.getTag();
        TaggedValue tvFound = null;
        
        // existing verification
        if (!tagElements.isEmpty()) {
            for (TaggedValue tag : tagElements) {
        
                TagType type = tag.getDefinition();
                String tagname = type.getName();
        
                if (tagname.equals(name)) {
                    exist = true;
                    // Modelio.out.println("tvFound FOUND");
                    tvFound = tag;
                }
            }
        }
        
        // if the tagged value doesn't exist yet, we create this
        if (!exist) {
            try {
                // Modelio.out.println("tvFound does not exist");
                TaggedValue v = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(modulename, name, element);
                element.getTag().add(v);
                if (!v.getDefinition().getParamNumber().equals("0")) {
                    setTaggedValue(name, element, values);
                }
            } catch (Exception e) {
                SysMLModule.logService.error(e);
            }
        }
        // if the tagged value already exists
        else {
            if ((tvFound != null ) && (tvFound.getDefinition().getParamNumber().equals("0"))) {
                // Modelio.out.println("tvFound.getDefinition().getParamNumber().equals(0), the tv is deleted");
                tvFound.delete();
            } else {
                setTaggedValue(name, element, values);
            }
        }
    }

    /**
     * Method getTaggedValue
     * @author ebrosse
     * @return
     */
    @objid ("2859dbba-2d61-4628-904d-b5c554645b8f")
    public static String getTaggedValue(String tagtype, ModelElement element) {
        for (TaggedValue tag : element.getTag()) {
            TagType type = tag.getDefinition();
            String tagname = type.getName();
        
            if (tagname.equals(tagtype)) {
        
                List<TagParameter> actuals = tag.getActual();
                if ((actuals != null) && (actuals.size() > 0)) {
                    return actuals.get(0).getValue();
                } else
                    return "";
        
            }
        }
        return "";
    }

    /**
     * Method addValue
     * @author ebrosse
     * @return
     */
    @objid ("6e44f1b3-b1cb-4ffa-8b1a-53601a8099be")
    public static void addValue(String modulename, String name, String value, ModelElement element, ModelElement related, String modulelink, String stereotypeLink) {
        // DON'T place Transition HERE
        
        boolean exist = false;
        
        TaggedValue tag = null;
        List<TaggedValue> tagElements = element.getTag();
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        if (!tagElements.isEmpty()) {
            for (TaggedValue currentTag : tagElements) {
                TagType type = currentTag.getDefinition();
                String tagname = type.getName();
        
                if (tagname.equals(name)) {
                    exist = true;
                    tag = currentTag;
                    break;
        
                }
            }
        }
        
        if (!exist) {
            try {
                tag = model.createTaggedValue(modulename, name, element);
        
            } catch (Exception e) {
                SysMLModule.logService.error(e);
            }
        
        }
        
        setTaggedValue(tag, element, value, related, modulelink, stereotypeLink);
    }

    /**
     * Method hasTaggedValue
     * @author ebrosse
     * 
     * @param _element @return
     */
    @objid ("522b4d73-046f-44e3-9cee-35d8d7af605b")
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

    /**
     * Method setTaggedValue
     * @author ebrosse
     * @return
     */
    @objid ("cfe4f93a-f764-414f-913d-c00134b467e6")
    public static void setTaggedValue(String name, ModelElement elt, String value) {
        List<TaggedValue> tagElements = elt.getTag();
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        if (!tagElements.isEmpty()) {
        
            for (TaggedValue tag : tagElements) {
                String tagname = tag.getDefinition().getName();
                if (tagname.equals(name)) {
        
                    TagParameter firstElt = null;
                    List<TagParameter> actuals = tag.getActual();
                    if ((actuals != null) && (actuals.size() > 0)) {
                        firstElt = actuals.get(0);
                    } else {
                        firstElt = model.createTagParameter();
                        tag.getActual().add(firstElt);
                    }
        
                    if (((value.equals("false")) && (tag.getDefinition().getParamNumber().equals("0")))
                            || ((value.equals("")) && (tag.getDefinition().getParamNumber().equals("1")))) {
                        tag.delete();
                    } else {
                        firstElt.setValue(value);
                    }
                }
            }
        }
    }

    /**
     * Method setTaggedValue
     * @author ebrosse
     * @return
     */
    @objid ("7464086c-3b04-4192-b676-defd0632a1b3")
    public static void setTaggedValue(TaggedValue tvFound, ModelElement elt, String value, ModelElement related, String modulelink, String stereotypeLink) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        for (Dependency existingLinks : new ArrayList<>(elt.getDependsOnDependency())) {
            if (existingLinks.isStereotyped(modulelink,stereotypeLink)) {
                existingLinks.delete();
            }
        }
        
        TagParameter firstElt = null;
        List<TagParameter> actuals = tvFound.getActual();
        if ((actuals != null) && (actuals.size() > 0)) {
            firstElt = actuals.get(0);
        } else {
            firstElt = model.createTagParameter();
            tvFound.getActual().add(firstElt);
        }
        
        if (value.equals("false")) {
            tvFound.delete();
        } else {
            firstElt.setValue(value);
            try {
                model.createDependency(elt, related,modulelink, stereotypeLink);
            } catch (Exception e) {
                SysMLModule.logService.error(e);
            }
        }
    }

    /**
     * Method searchElement
     * @author ebrosse
     * @return
     */
    @objid ("8499bc6d-983a-4435-b989-428f9509c8aa")
    public static List<MObject> searchElement(java.lang.Class<? extends MObject> extendedClass, String modulename, String stereotype) {
        // initialize the result
        List<MObject> result = new ArrayList<>();
        
        // dynamic elements list creating
        Collection<? extends MObject> listElements =  SysMLModule.getInstance().getModuleContext().getModelingSession()
                .findByClass(extendedClass);
        
        // vector initialization
        for (MObject elt : listElements) {
            if ((elt instanceof ModelElement)
                    && (((ModelElement)elt).isStereotyped(modulename,stereotype))) {
                result.add(elt);
            }
        }
        return result;
    }

    /**
     * Method updateSource
     * @author ebrosse
     */
    @objid ("165e7abd-efda-4e2f-9813-f3a862840ff5")
    public static void updateSource(ModelElement element, String tagtypeName, String modulename, String linkName) {
        try {
        
            ArrayList<Dependency> linkList = new ArrayList<>();
            for (Dependency existingLinks : element.getDependsOnDependency()) {
                if (existingLinks.isStereotyped(modulename,linkName)) {
                    linkList.add(existingLinks);
                }
            }
        
            // search for taggedValue
            TaggedValue tag = null;
            List<TaggedValue> taglist = element.getTag();
            for (TaggedValue taggedValue : taglist) {
                if (taggedValue.getDefinition().getName().equals(tagtypeName)) {
                    tag = taggedValue;
                    break;
                }
            }
        
            IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
            if (linkList.size() > 0) {
        
                // if not exist the tagged value is created
                if (tag == null) {
                    tag = model.createTaggedValue(modulename,tagtypeName, element);
                }
                List<TagParameter> actuals = tag.getActual();
        
                for (TagParameter actual : actuals) {
                    actual.delete();
                }
        
                for (Dependency link : linkList) {
                    TagParameter currentTag = model.createTagParameter();
                    tag.getActual().add(currentTag);
                    currentTag.setValue(link.getDependsOn().getName());
                }
        
            } else {
        
                if (tag != null) {
                    tag.delete();
                }
            }
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
    }

    /**
     * Removes all dependencies stereotyped
     * @author ebrosse
     * 
     * @param element : element source of stereotyped dependencies
     * @param stereotypelink : stereotype name of the dependency
     */
    @objid ("d66041f8-0f8f-4236-9de3-bf3226ab4621")
    public static void removeStereotypedLink(ModelElement element, String stereotypelink) {
        for (Dependency existingLinks : element.getDependsOnDependency()) {
            if (existingLinks.isStereotyped(ISysMLPeerModule.MODULE_NAME, stereotypelink)) {
                existingLinks.delete();
            }
        }
    }

    /**
     * Method updateTarget
     * @author ebrosse
     * @return
     */
    @objid ("1274094e-4920-47a4-84fd-e0395e6ddf3e")
    public static void updateTarget(ModelElement element, String modulename, String tagtypeName, String modulelinkname, String linkName) {
        if (element.getStatus().isModifiable()){
        
            try {
        
                // search stereotyped dependency
                ArrayList<Dependency> linkList = new ArrayList<>();
                for (Dependency existingLinks : element.getImpactedDependency()) {
                    if (existingLinks.isStereotyped(modulelinkname, linkName)) {
                        linkList.add(existingLinks);
                    }
                }
        
                // search for taggedValue
                TaggedValue tag = null;
                List<TaggedValue> taglist = element.getTag();
                for (TaggedValue taggedValue : taglist) {
                    if (taggedValue.getDefinition().getName().equals(tagtypeName)) {
                        tag = taggedValue;
                        break;
                    }
                }
        
                IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
                if (linkList.size() > 0) {
        
                    // if not exist the tagged value is created
                    if (tag == null) {
                        tag = model.createTaggedValue(modulename,tagtypeName, element);
                    }
        
                    List<TagParameter> actuals = tag.getActual();
        
                    for (TagParameter actual : actuals) {
                        actual.delete();
                    }
        
                    for (Dependency link : linkList) {
                        TagParameter currentTag = model.createTagParameter();
                        tag.getActual().add(currentTag);
                        currentTag.setValue(link.getImpacted().getName());
                    }
        
                } else {
                    if (tag != null) {
                        tag.delete();
                    }
        
                }
            } catch (Exception e) {
                SysMLModule.logService.error(e);
            }
        }
    }

//    /**
//     * Method synchronizeSysMLModel
//     * @author ebrosse
//     * @return
//     */
//    @objid ("45de48fa-6e35-4341-b08f-68877f0ac5de")
//    public static void synchronizeSysMLModel() {
//        synchronizeUnit();
//        synchronizeValueType();
//        synchronizeView();
//    }
    /**
     * Method synchronizeView
     * @author ebrosse
     * @return
     */
    @objid ("213168ef-3a36-4016-9b5c-04e3b961f336")
    private static void synchronizeView() {
        Collection<Class> listElements =  SysMLModule.getInstance().getModuleContext().getModelingSession().findByClass(Class.class);
        
        for (Class temp : listElements) {
            if (temp.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEW))
                updateSource(temp, ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.VIEW_VIEWPOINT, SysMLStereotypes.CONFORM);
        }
    }

    /**
     * Method update
     * @author ebrosse
     * @return
     */
    @objid ("fd777d3f-7aa7-4913-bbc8-2ff48a5c1777")
    private static void update(Dependency updatedElement, String modulename, String linkStereotype, String tagSource, String tagTarget) {
        updateSource(updatedElement.getDependsOn(),modulename, tagSource, linkStereotype);
        updateTarget(updatedElement.getImpacted(),modulename, tagTarget,modulename, linkStereotype);
    }

//    /**
//     * Method synchronizeValueType
//     * @author ebrosse
//     * @return
//     */
//    @objid ("9e26ba5e-2f3d-49ee-b7c3-3d0dc2eb6d17")
//    private static void synchronizeValueType() {
//        Collection<DataType> listElements =  SysMLModule.getInstance().getModuleContext().getModelingSession().findByClass(DataType.class);
//
//        for (DataType temp : listElements) {
//            if (temp.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VALUETYPE)) {
//                updateSource(temp, ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.VALUETYPE_VALUETYPE_UNIT, SysMLStereotypes.VALUETYPEUNIT);
//                updateTarget(temp, ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.VALUETYPE_VALUETYPE_QUANTITYKIND, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VALUETYPEQUANTITYKIND);
//            }
//        }
//    }
//    /**
//     * Method synchronizeUnit
//     * @author ebrosse
//     * @return
//     */
//    @objid ("24dd9696-12ae-489b-81bd-afc9c794636e")
//    private static void synchronizeUnit() {
//        Collection<Instance> listElements =  SysMLModule.getInstance().getModuleContext().getModelingSession().findByClass(Instance.class);
//
//        for (Instance temp : listElements) {
//            if (temp.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNIT)) {
//                updateSource(temp,ISysMLPeerModule.MODULE_NAME, SysMLTagTypes.UNIT_UNIT_QUANTITYKIND, SysMLStereotypes.UNITQUANTITYKIND);
//            }
//        }
//    }
    /**
     * Method isStereotypedSysML
     * @author ebrosse
     * @return
     */
    @objid ("1f6b3812-619f-40b1-a0cd-d0e4a8d23641")
    public static boolean isStereotypedSysML(ModelElement eltToTest) {
        List<Stereotype> sterList = eltToTest.getExtension();
        for (Stereotype sterCurrent : sterList) {
            if (!(sterCurrent.getOwner().getOwnerModule().getUuid().toString().equals("00bc42d0-0000-19fb-0000-000000000000")))
                return false;
        }
        return true;
    }

    /**
     * Method searchConnector
     * @author ebrosse
     * @return
     */
    @objid ("e0a5d0a6-c52f-45c4-a17f-cd76227d3af7")
    public static ArrayList<MObject> searchConnector(NameSpace owner) {
        ArrayList<MObject> result = new ArrayList<>();
        if ((owner instanceof Classifier) && (owner.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BLOCK))) {
            for (BindableInstance binstance : ((Classifier) owner).getInternalStructure()) {
                for (LinkEnd linkEnd : binstance.getOwnedEnd()) {
                    Link link = linkEnd.getLink();
        
                    boolean isConnector = true;
                    for (LinkEnd end : link.getLinkEnd()) {
                        Instance endOwner = end.getOwner();
                        if ((endOwner.getCompositionOwner() == null) || (!(endOwner.getCompositionOwner().equals(owner)))) {
        
                            isConnector = false;
                        }
                    }
        
                    if ((isConnector) && (!(result.contains(link))))
                        result.add(link);
        
                }
            }
        }
        return result;
    }

    /**
     * Method getTaggedValueLink
     * @author ebrosse
     * @return
     */
    @objid ("f24d5be2-5b8e-42f9-8a79-b878efd5e950")
    public static String getTaggedValueLink(String module, String stereotypeLink, ModelElement element) {
        for (Dependency depend : element.getDependsOnDependency()) {
            if (depend.isStereotyped(module,stereotypeLink)) {
                return depend.getDependsOn().getName();
            }
        }
        return "";
    }

    /**
     * Method searchEnds
     * @author ebrosse
     * @return
     */
    @objid ("2a179c90-346b-40d4-9bfb-fd95cef176a3")
    public static List<MObject> searchEnds(BindableInstance element) {
        NameSpace base = element.getBase();
        List<MObject> result = new ArrayList<>();
        if (base != null) {
            List<AssociationEnd> connection = new ArrayList<>();
        
            ClassAssociation classAssoc = ((Class) element.getInternalOwner()).getLinkToAssociation();
            connection.addAll(classAssoc.getAssociationPart().getEnd());
        
        
            for (AssociationEnd end : connection) {
                if ((end.getMultiplicityMax().equals("1")) && (end.getMultiplicityMin().equals("1"))
                        && (end.getTarget().equals(base)))
                    result.add(end);
            }
        }
        return result;
    }

    /**
     * Method getBindableInstance
     * @author ebrosse
     * @return
     */
    @objid ("517f339b-dc18-4574-9115-d66df6d41d83")
    public static ArrayList<MObject> getBindableInstance(InformationFlow element) {
        ArrayList<MObject> result = new ArrayList<>();
        
        if (element.getRealizingLink().size() > 0) {
        
            NameSpace nearestNamespace = getNearestNameSpace(element.getRealizingLink().get(0).getOwner());
        
            if (nearestNamespace instanceof Classifier){
                Classifier nearestClassifier = (Classifier) nearestNamespace;
                ArrayList<BindableInstance> temps = new ArrayList<>();
                ArrayList<BindableInstance> topInstances = new ArrayList<>();
        
                topInstances.addAll(nearestClassifier.getInternalStructure());
                temps.addAll(topInstances);
        
                ArrayList<BindableInstance> lowInstances = new ArrayList<>();
                for (BindableInstance instance : topInstances) {
                    lowInstances.addAll(instance.getPart());
                }
        
                while (lowInstances.size() > 0) {
                    topInstances = lowInstances;
                    temps.addAll(topInstances);
        
                    lowInstances = new ArrayList<>();
                    for (BindableInstance instance : topInstances) {
                        lowInstances.addAll(instance.getPart());
                    }
        
                }
        
                List<Classifier> convoyed = element.getConveyed();
        
                for (BindableInstance temp : temps) {
                    if (convoyed.contains(temp.getBase())) {
                        result.add(temp);
                    }
                }
            }
        }
        return result;
    }

     @objid ("e71ccebe-c3c1-4da1-91db-adc3bb4773c0")
    public static boolean isAllocated(ModelElement element) {
        boolean isAllocated = false;
        
        
        for (Dependency existingLinks : element.getDependsOnDependency()) {
            if (existingLinks.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ALLOCATE)) {
                isAllocated = true;
            }
        }
        
        for (Dependency existingLinks : element.getImpactedDependency()) {
            if (existingLinks.isStereotyped(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ALLOCATE)) {
                isAllocated = true;
            }
        
        }
        return isAllocated;
    }

    /**
     * Removes all tagtypes stereotyped
     * @author ebrosse
     * 
     * @param element : element source of stereotyped dependencies
     * @param tagtypeName : stereotype name of the dependency
     */
    @objid ("bb5c0150-5732-459d-b79b-66ed5cde7737")
    public static void removeTagType(ModelElement element, String tagtypeName) {
        List<TaggedValue> taglist = element.getTag();
        for (TaggedValue taggedValue : taglist) {
            if (taggedValue.getDefinition().getName().equals(tagtypeName)) {
                taggedValue.delete();
                break;
            }
        }
    }

}
