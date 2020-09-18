/**
 * Java Class : SysMLFactory.java
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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.ActivityDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.diagrams.StateMachineDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.commonBehaviors.BehaviorParameter;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.stateMachineModel.StateMachine;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.PortOrientation;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class handles the creation of all SysML elements
 * @author ebrosse
 */
@objid ("73af9047-e4a9-4c9c-b9d1-b8936d54f79c")
public class SysMLFactory {
    @objid ("184027c2-aaf7-4f3d-aec2-09e602b94956")
    private static final String LOCALS = "locals";

    @objid ("42582255-c35c-4889-84d3-95e9310f5e7c")
    private static final String DESCRIPTION = "description";

    @objid ("fece2683-2281-43c0-acd2-3b1f6a4c7f4a")
    private static final String FLOW_PORT = "FlowPort";

    @objid ("10566a4f-039e-4665-9085-a99e9173ee76")
    private static final String FLOW_SPECIFICATION = "FlowSpecification";

    @objid ("59937dc8-163e-4ef3-b59d-404e295c325f")
    private static final String VIEW = "View";

    @objid ("bd499d4c-86d4-468c-9eca-96826914f149")
    private static final String VALUETYPE = "VT";

    @objid ("5d7e8e92-f220-4e71-a690-d520af5858e3")
    private static final String VIEWPOINT = "Viewpoint";

    @objid ("4199f649-3fd3-4972-8d95-361a1c50f058")
    private static final String UNIT = "Unit";

    @objid ("5366423a-c9bc-48d9-8e52-a11fdf734369")
    private static final String QUANTITY = "Quantity";

    @objid ("95b5db51-b87d-450b-b844-af198b2a4b3d")
    private static final String CONNECTORPROPERTY = "co";

    @objid ("d73ce528-a1a7-4226-a795-df64f18a420d")
    private static final String DISTRIBUTEDPROPERTY = "d";

    @objid ("24114796-6e4d-40b0-9752-dfda0a8d7b9b")
    private static final String PARTICIPANTPROPERTY = "p";

    @objid ("d4a9cc0d-b92a-4b5a-a54e-dabeddddcf92")
    private static final String CONSTRAINTPROPERTY = "ct";

    @objid ("2b721666-ba8c-43b5-811c-8e5b66df91b5")
    private static final String PROPERTYSPECIFICTYPE = "PST";

    @objid ("7e713e75-8f9b-41c6-b4cb-06d4de7e73bd")
    private static final String OPERATOR = "Operator";

    @objid ("4953cc38-067c-4639-b78f-6b144653b8bb")
    private static final String OPERATION = "Operation";

    @objid ("385d6135-613e-4897-85fc-749e645d11c0")
    private static final String ENUMERATION_LITERAL = "Enumeration Literal";

    @objid ("607f1824-56a5-42ec-a809-343eaccfddab")
    private static final String ENUMERATION = "Enumeration";

    @objid ("d1ad2238-f348-49a8-b3b7-8c639ae3e189")
    private static final String ACTOR = "Actor";

    @objid ("2d372422-ff7f-44c8-b3a6-542f9076aab2")
    private static final String USE_CASE = "Use Case";

    @objid ("74dbe298-7371-46b8-b067-21750421363a")
    private static final String RETURN_PARAMETER = "Return Parameter";

    @objid ("850699d1-885e-42a1-8dc0-c836e289b16d")
    private static final String PARAMETER = "Parameter";

    @objid ("93a33414-6403-4b30-a29d-848079e3e053")
    private static final String FLOWPROPERTY = "fp";

    @objid ("0157d0b6-c4e3-4846-b6c4-138dc11925c0")
    private static final String TRACE = "trace";

    @objid ("e9ee1585-e53c-473d-8f63-edb3ca18544e")
    private static final String MODELER_MODULE = "ModelerModule";

    @objid ("2178c9cd-33b1-4879-af69-0dfa4459bf61")
    private static final String DISCRETE = "discrete";

    @objid ("bad45d82-f6eb-4105-95a5-1e00ce3c7354")
    private static final String TESTCASE = "TC";

    @objid ("996be1e3-033f-4b43-be6d-7b984866ca59")
    private static final String OPTIONAL = "opt";

    @objid ("21f31767-5bb8-4683-9e9f-ab54860e1f36")
    private static final String RATE = "rate";

    @objid ("946429b2-0c19-4caa-912c-c16a6b98a00a")
    private static final String CONTINUOUS = "continuous";

    @objid ("8ca21932-ec44-46d7-bd78-2310a71b610c")
    private static void setContinuousParameter(Parameter result) {
        IModelingSession modelingSession = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = modelingSession.getModel();
        
        result.setIsStream(true);
        result.getExtension().add (modelingSession.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONTINUOUS_PARAMETER, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, CONTINUOUS);
    }

    @objid ("a347b978-3fb6-4dd1-9b57-206d9fe5b276")
    private static void setRateParameter(Parameter result) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        result.setIsStream(true);
        result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE_PARAMETER, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, RATE);
    }

    @objid ("e2557b8a-2628-4919-a4ea-5759fa3a044a")
    private static void setOptionalParameter(Parameter result) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.OPTIONAL, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, OPTIONAL);
        result.setMultiplicityMin("0");
    }

    @objid ("2927aeeb-86b0-4573-af8f-a25c65b35f52")
    private static void setDiscreteParameter(Parameter result) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        result.setIsStream(true);
        result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.DISCRETE_PARAMETER, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, DISCRETE);
    }

    /**
     * Method createAllocateDependency
     * @author ebrosse
     * 
     * @param origin : the origin of the dependency
     * @param target : the target of the dependency
     * @return the allocate dependency
     */
    @objid ("94cf556b-d566-4bf7-9561-9119eb084d44")
    public static Dependency createAllocateAbstraction(ModelElement origin, ModelElement target) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        MMetamodel metamodel = context.getModelioServices().getMetamodelService().getMetamodel();
        
        try {
        
            Dependency allocation = session.getModel().createDependency();
            allocation.setImpacted(origin);
            allocation.setDependsOn(target);
            allocation.setName("");
            Stereotype allocateStereotype = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ALLOCATE, metamodel.getMClass(Dependency.class));
            allocation.getExtension().add(allocateStereotype);
        
            return  allocation;
        }catch(Exception e){
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createBlock
     * @author ebrosse
     * @return
     */
    @objid ("222fabd0-fe7e-47a5-9642-bc2f075726e4")
    public static Class createBlock(MObject owner) {
        if (owner instanceof Package){
            return createBlock((Package) owner);
        }else if (owner instanceof Class){
            return createBlock((Class) owner);
        }
        return null;
    }

    /**
     * Method createBlock
     * @author ebrosse
     * @return
     */
    @objid ("77d4b068-aa1b-4307-8aca-f256208b4a75")
    private static Class createBlock(Package owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        
        try {
            Class result = model.createClass("", owner, ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.BLOCK);
            model.getDefaultNameService().setDefaultName(result, SysMLStereotypes.BLOCK);
            return result;
        } catch (Exception e){
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createBlock
     * @author ebrosse
     * @return
     */
    @objid ("e1247f5d-3bd9-4d10-ae03-f24089272111")
    private static Class createBlock(Class owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ();
        
        try {
            Class result = model.createClass ("", owner, ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.BLOCK);
            model.getDefaultNameService().setDefaultName(result, SysMLStereotypes.BLOCK);
            return result;
        } catch (Exception e){
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createSequenceDiagram
     * @author ebrosse
     * @return
     */
    @objid ("a68fe0c3-48d8-4767-b5f7-071117e410f4")
    public static SequenceDiagram createSysMLSequenceDiagram(ModelElement owner, String diagramName, String description) {
        Interaction interaction = null;
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        
        IUmlModel model = session.getModel();
        if (owner instanceof Interaction){
            interaction = (Interaction) owner;
        }else{
            interaction = model.createInteraction();
            if (owner instanceof  Operation)
                interaction.setOwnerOperation((Operation) owner);
            else   if (owner instanceof  NameSpace)
                interaction.setOwner((NameSpace) owner);
        
            model.getDefaultNameService().setDefaultName(interaction, I18nMessageService.getString("Ui.Create.Interaction.Name"));
        }
        
        boolean notFound = true;
        for (Collaboration colla : interaction.getOwnedCollaboration()){
            if (colla.getName().equals(LOCALS)){
                notFound = false;
                break;
            }
        
        }
        
        if (notFound){
            Collaboration locals = model.createCollaboration();
            locals.setName(LOCALS);
            interaction.getOwnedCollaboration().add(locals);
        }
        
        SequenceDiagram diagram = model.createSequenceDiagram();
        diagram.setOrigin(interaction);
        model.getDefaultNameService().setDefaultName(diagram, diagramName);
        
        try {
            Stereotype sysSeqSter = session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLSEQUENCEDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(SequenceDiagram.class));
            diagram.getExtension().add(sysSeqSter);
            model.createNote(MODELER_MODULE,DESCRIPTION, diagram, description);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
        return diagram;
    }

    /**
     * Method createSequenceDiagram
     * @author ebrosse
     * @return
     */
    @objid ("b027315d-c329-40b5-b6b4-196f9cd45c74")
    public static SequenceDiagram createSysMLSequenceDiagram(ModelElement owner, String diagramName) {
        Interaction interaction = null;
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        if (owner instanceof Interaction){
            interaction = (Interaction) owner;
        }else{
            interaction = model.createInteraction();
        
            if (owner instanceof  Operation)
                interaction.setOwnerOperation((Operation) owner);
            else   if (owner instanceof  NameSpace)
                interaction.setOwner((NameSpace) owner);
        
            model.getDefaultNameService().setDefaultName(interaction, I18nMessageService.getString("Ui.Create.Interaction.Name"));
        }
        
        boolean notFound = true;
        for (Collaboration colla : interaction.getOwnedCollaboration()){
            if (colla.getName().equals(LOCALS)){
                notFound = false;
                break;
            }
        
        }
        
        if (notFound){
            Collaboration locals = model.createCollaboration();
            locals.setName(LOCALS);
            interaction.getOwnedCollaboration().add(locals);
        }
        
        SequenceDiagram diagram = model.createSequenceDiagram();
        diagram.setOrigin(interaction);
        model.getDefaultNameService().setDefaultName(diagram, diagramName);
        
        try {
            Stereotype sysSeqSter = session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLSEQUENCEDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(SequenceDiagram.class));
            diagram.getExtension().add(sysSeqSter);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
        return diagram;
    }

    /**
     * Method createStateMachineDiagram
     * @author ebrosse
     * @return
     */
    @objid ("13db3c1e-e7a4-43b5-a626-769494c808b8")
    public static StateMachineDiagram createSysMLStateMachineDiagram(ModelElement owner, String diagramName, String description) {
        StateMachine statemachine = null;
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        if (owner instanceof StateMachine){
            statemachine = (StateMachine) owner;
        }else{
            statemachine = session.getModel().createStateMachine();
        
            if (owner instanceof  Operation)
                statemachine.setOwnerOperation((Operation) owner);
            else   if (owner instanceof  NameSpace)
                statemachine.setOwner((NameSpace) owner);
        
            model.getDefaultNameService().setDefaultName(statemachine, I18nMessageService.getString("Ui.Create.StateMachine.Name"));
        }
        
        boolean notFound = true;
        for (Collaboration colla : statemachine.getOwnedCollaboration()){
            if (colla.getName().equals(LOCALS)){
                notFound = false;
                break;
            }
        }
        
        if (notFound){
            Collaboration locals = model.createCollaboration();
            locals.setName(LOCALS);
            statemachine.getOwnedCollaboration().add(locals);
        }
        
        StateMachineDiagram diagram = null;
        try{
            Stereotype ster = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLSTATEMACHINEDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StateMachineDiagram.class));
            diagram = model.createStateMachineDiagram("", statemachine,ster);
            diagram.setOrigin(statemachine);
            model.getDefaultNameService().setDefaultName(diagram, diagramName);
            model.createNote(MODELER_MODULE,DESCRIPTION, diagram, description);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
        return diagram;
    }

    /**
     * Method createStateMachineDiagram
     * @author ebrosse
     * @return
     */
    @objid ("06682b35-ac55-4c42-9bd5-8042ee31f799")
    public static StateMachineDiagram createSysMLStateMachineDiagram(ModelElement owner, String diagramName) {
        StateMachine statemachine = null;
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        if (owner instanceof StateMachine){
            statemachine = (StateMachine) owner;
        
        }else{
            statemachine = model.createStateMachine();
            if (owner instanceof  Operation)
                statemachine.setOwnerOperation((Operation) owner);
            else   if (owner instanceof  NameSpace)
                statemachine.setOwner((NameSpace) owner);
        
            model.getDefaultNameService().setDefaultName(statemachine,  I18nMessageService.getString("Ui.Create.StateMachine.Name"));
        }
        
        boolean notFound = true;
        for (Collaboration colla : statemachine.getOwnedCollaboration()){
            if (colla.getName().equals(LOCALS)){
                notFound = false;
                break;
            }
        }
        
        if (notFound){
            Collaboration locals = model.createCollaboration();
            locals.setName(LOCALS);
            statemachine.getOwnedCollaboration().add(locals);
        }
        
        StateMachineDiagram diagram = null;
        try{
            Stereotype ster = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLSTATEMACHINEDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(StateMachineDiagram.class));
            diagram = model.createStateMachineDiagram("", statemachine,ster);
            diagram.setOrigin(statemachine);
            model.getDefaultNameService().setDefaultName(diagram,  diagramName);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
        return diagram;
    }

    /**
     * Method createSysMLActivityDiagram
     * @author ebrosse
     * @return
     */
    @objid ("ca8163f8-8da5-405e-9484-3766ac818bb3")
    public static ActivityDiagram createSysMLActivityDiagram(ModelElement owner, String diagName, String description) {
        Activity activity = null;
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        
        if (owner instanceof Activity){
            activity = (Activity) owner;
        }else{
            activity = model.createActivity();
            if (owner instanceof  Operation)
                activity.setOwnerOperation((Operation) owner);
            else   if (owner instanceof  NameSpace)
                activity.setOwner((NameSpace) owner);
        
            model.getDefaultNameService().setDefaultName(activity,  I18nMessageService.getString("Ui.Create.Activity.Name"));
        }
        
        boolean notFound = true;
        for (Collaboration colla : activity.getOwnedCollaboration()){
            if (colla.getName().equals(LOCALS)){
                notFound = false;
                break;
            }
        }
        
        if (notFound){
            Collaboration locals = model.createCollaboration();
            locals.setName(LOCALS);
            activity.getOwnedCollaboration().add(locals);
        }
        
        ActivityDiagram diagram = model.createActivityDiagram("", activity);
        model.getDefaultNameService().setDefaultName(diagram, diagName);
        try {
            Stereotype sysActDiag = session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLACTIVITYDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ActivityDiagram.class));
            diagram.getExtension().add(sysActDiag);
            model.createNote(MODELER_MODULE,DESCRIPTION, diagram, description);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
        return diagram;
    }

    /**
     * Method createSysMLActivityDiagram
     * @author ebrosse
     * @return
     */
    @objid ("56308980-2fe4-4a86-b644-e4f8ff0ef34f")
    public static ActivityDiagram createSysMLActivityDiagram(ModelElement owner, String diagName) {
        Activity activity = null;
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        if (owner instanceof Activity){
            activity = (Activity) owner;
        }else{
            activity = model.createActivity();
            if (owner instanceof  Operation)
                activity.setOwnerOperation((Operation) owner);
            else   if (owner instanceof  NameSpace)
                activity.setOwner((NameSpace) owner);
        
            model.getDefaultNameService().setDefaultName(activity,  I18nMessageService.getString("Ui.Create.Activity.Name"));
        }
        
        boolean notFound = true;
        for (Collaboration colla : activity.getOwnedCollaboration()){
            if (colla.getName().equals(LOCALS)){
                notFound = false;
                break;
            }
        
        }
        
        if (notFound){
            Collaboration locals = model.createCollaboration();
            locals.setName(LOCALS);
            activity.getOwnedCollaboration().add(locals);
        }
        
        ActivityDiagram diagram = model.createActivityDiagram("", activity);
        model.getDefaultNameService().setDefaultName(diagram,  diagName);
        
        try {
            Stereotype sysActDiag = session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLACTIVITYDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ActivityDiagram.class));
            diagram.getExtension().add(sysActDiag);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        
        SysMLModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
        return diagram;
    }

    /**
     * Method createUseCaseDiagram
     * @author ebrosse
     * @return
     */
    @objid ("c3dd17ad-7c3c-418d-9f18-7c6915a6ce2e")
    public static StaticDiagram createSysMLUseCaseDiagram(ModelElement owner, String name, String descriptionContent) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        StaticDiagram diagram = null;
        
        try {
            Stereotype ster = session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLUSECASEDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(UseCaseDiagram.class));
            IUmlModel model = session.getModel();
            diagram = model.createUseCaseDiagram("", owner, ster);
            model.getDefaultNameService().setDefaultName(diagram,  name);
            model.createNote(MODELER_MODULE, DESCRIPTION, diagram, descriptionContent);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return diagram;
    }

    /**
     * Method createUseCaseDiagram
     * @author ebrosse
     * @return
     */
    @objid ("f4f9836d-651c-461e-b254-595c1acbedd6")
    public static StaticDiagram createSysMLUseCaseDiagram(ModelElement owner, String name) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        StaticDiagram diagram = null;
        try {
            Stereotype ster = session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.SYSMLUSECASEDIAGRAM, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(UseCaseDiagram.class));
            IUmlModel model = session.getModel();
            diagram = model.createUseCaseDiagram("", owner, ster);
            model.getDefaultNameService().setDefaultName(diagram,  name);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return diagram;
    }

    /**
     * Method createConformDependency
     * @author ebrosse
     * @return
     */
    @objid ("cc10566c-87f1-4b6f-8445-638e750a455f")
    public static Dependency createConformDependency(Package view, Class viewpoint) {
        try {
            return SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ().createDependency (view, viewpoint,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONFORM);
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createConstraintBlock
     * @author ebrosse
     * @return
     */
    @objid ("200bd6d7-fe50-4810-b9d9-c98f8f14f7af")
    public static Class createConstraintBlock(NameSpace owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ();
        
        try {
            Class result = model.createClass ("", owner, ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONSTRAINTBLOCK);
            model.getDefaultNameService().setDefaultName(result, SysMLStereotypes.CONSTRAINTBLOCK);
            return result;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createFlowPort
     * @author ebrosse
     * @return
     */
    @objid ("6dfeb610-fae1-4d96-acc3-3b57a91c264f")
    public static Port createFlowPort(Instance owner) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        Port port = model.createPort ();
        try {
            port.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPORT, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class)));
            owner.getPart().add (port);
            model.getDefaultNameService().setDefaultName(port, FLOW_PORT);
        //            TaggedValue taggedValue = model.createTaggedValue(ISysMLPeerModule.MODULE_NAME,SysMLTagTypes.FLOWPORT_FLOWPORT_DIRECTION, port);
        //            model.createTagParameter("INOUT", taggedValue);
            port.setDirection(PortOrientation.INOUT);
            return port;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createFlowPort
     * @author ebrosse
     * @return
     */
    @objid ("f6d70986-961b-4bb6-8a53-2d51a2520ef9")
    public static Port createFlowPort(Classifier owner) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel ();
        Port port = model.createPort();
        try {
            port.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPORT, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Port.class)));
            owner.getInternalStructure().add(port);
        //            TaggedValue taggedValue = model.createTaggedValue(ISysMLPeerModule.MODULE_NAME,SysMLTagTypes.FLOWPORT_FLOWPORT_DIRECTION, port);
        //            model.createTagParameter("INOUT", taggedValue);
            port.setDirection(PortOrientation.INOUT);
            model.getDefaultNameService().setDefaultName(port, FLOW_PORT);
            return port;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createFlowSpecification
     * @author ebrosse
     * @return
     */
    @objid ("e263a36d-389d-4910-b0b3-e76e6c8ab424")
    public static Interface createFlowSpecification(NameSpace owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ();
        
        try {
            Interface result = model.createInterface ("", owner, ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.FLOWSPECIFICATION);
            model.getDefaultNameService().setDefaultName(result, FLOW_SPECIFICATION);
            return result;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createItemFlow
     * @author ebrosse
     * @return
     */
    @objid ("b00a45e3-9ab6-42b1-ad15-8e3cee7ddf01")
    public static void createItemFlow(ModelElement end, UmlModelElement originElement, UmlModelElement targetElement) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        InformationFlow flow = model.createInformationFlow();
        
        if (end instanceof AssociationEnd){
            AssociationEnd assocEnd = (AssociationEnd) end;
            for (AssociationEnd ends : assocEnd.getAssociation().getEnd()){
                Classifier tempTarget = ends.getTarget();
                if ((tempTarget != null) && (tempTarget.equals(targetElement)))
                    flow.getRealizingFeature().add(ends);
            }
        }else{
            ConnectorEnd linkEnd = (ConnectorEnd) end;
            for (LinkEnd linkEnds : linkEnd.getLink().getLinkEnd()){
        
                Instance tempTarget = linkEnds.getTarget();
                if ((tempTarget != null) && (tempTarget.equals(targetElement)))
                    flow.getRealizingLink().add(linkEnds);
            }
        
        }
        
        // Retrieve the namespace owner
        if (originElement instanceof Classifier)
            flow.setOwner((Classifier) originElement);
        else
            flow.setOwner (Utils.getNameSpaceOwner(originElement));
        
        flow.getInformationSource().add (originElement);
        flow.getInformationTarget().add (targetElement);
        
        try {
            flow.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ITEMFLOW, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(InformationFlow.class)));
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
    }

    /**
     * Method createValueType
     * @author ebrosse
     * @return
     */
    @objid ("32d3fff6-9304-4276-b8e9-0267cd75352f")
    public static DataType createValueType(NameSpace owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ();
        try {
        
            DataType result = model.createDataType ("", owner,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VALUETYPE);
            model.getDefaultNameService().setDefaultName(result, VALUETYPE);
            return result;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createView
     * @author ebrosse
     * @return
     */
    @objid ("e3273a5c-0de4-4a4d-b743-bf1dddeb934f")
    public static Package createView(NameSpace owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ();
        
        try {
            Package result = model.createPackage("", owner,ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEW);
            model.getDefaultNameService().setDefaultName(result, VIEW);
            return result;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createViewpoint
     * @author ebrosse
     * @return
     */
    @objid ("5559940a-9849-4712-bbf3-04f12bd5b33a")
    public static Class createViewpoint(NameSpace owner) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession ().getModel ();
        
        try {
            Class result = model.createClass("", owner, ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.VIEWPOINT);
            model.getDefaultNameService().setDefaultName(result, VIEWPOINT);
            return result;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createUnit
     * @author ebrosse
     * @return
     */
    @objid ("e89269bd-a598-485e-ba20-eb647a4ef6f0")
    public static Instance createUnit(NameSpace element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        Instance result = null;
        IUmlModel model = session.getModel ();
        
        try {
            if (element instanceof Classifier){
        
                result = model.createBindableInstance();
                ((Classifier) element).getInternalStructure().add((BindableInstance) result);
        
            }else{
                result = model.createInstance();
                result.setOwner(element);
        
            }
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNIT, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Instance.class)));
            model.getDefaultNameService().setDefaultName(result, UNIT);
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createQuantityKind
     * @author ebrosse
     * @return
     */
    @objid ("d139a792-b05f-4ee1-b512-07e0a700ca57")
    public static Instance createQuantityKind(NameSpace element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        Instance result = null;
        IUmlModel model = session.getModel ();
        
        try {
            if (element instanceof Classifier){
                result = model.createBindableInstance();
                ((Classifier)element).getInternalStructure().add((BindableInstance)result);
        
            }else{
                result = model.createInstance();
                result.setOwner(element);
        
            }
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.QUANTITYKIND, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Instance.class)));
            model.getDefaultNameService().setDefaultName(result, QUANTITY);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createConnectorProperty
     * @author ebrosse
     * @return
     */
    @objid ("9ab5c12c-1b7b-41c0-ba80-d82995d8e7f9")
    public static BindableInstance createConnectorProperty(Classifier element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        BindableInstance result = null;
        IUmlModel model = session.getModel ();
        
        try {
            result = model.createBindableInstance();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONNECTORPROPERTY, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(BindableInstance.class)));
            result.setInternalOwner(element);
            model.getDefaultNameService().setDefaultName(result, CONNECTORPROPERTY);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        
        }
        return result;
    }

    /**
     * Method createDistributedProperty
     * @author ebrosse
     * @return
     */
    @objid ("fb140874-7408-4720-a843-d90a36f3d07e")
    public static Attribute createDistributedProperty(Classifier element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        Attribute result = null;
        IUmlModel model = session.getModel ();
        
        try {
            result = model.createAttribute();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.DISTRIBUTEDPROPERTY, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Attribute.class)));
            result.setOwner(element);
            model.getDefaultNameService().setDefaultName(result, DISTRIBUTEDPROPERTY);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createParticipantProperty
     * @author ebrosse
     * @return
     */
    @objid ("09235b93-6551-450b-9663-d51bd6ec58d7")
    public static BindableInstance createParticipantProperty(NameSpace element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        BindableInstance result = null;
        IUmlModel model = session.getModel();
        
        try {
            result = model.createBindableInstance();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(BindableInstance.class)));
            if (element instanceof Classifier)
                result.setInternalOwner((Classifier)element);
            else
                result.setOwner(element);
            model.getDefaultNameService().setDefaultName(result, PARTICIPANTPROPERTY);
        
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        
        }
        return result;
    }

    /**
     * Method createConstraintProperty
     * @author ebrosse
     * @return
     */
    @objid ("afda50f8-ab22-4b54-9135-03307ca70745")
    public static BindableInstance createConstraintProperty(NameSpace element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        BindableInstance result = null;
        IUmlModel model = session.getModel();
        
        try {
        
            result = model.createBindableInstance();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONSTRAINTPROPERTY, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(BindableInstance.class)));
            if (element instanceof Classifier)
                result.setInternalOwner((Classifier)element);
            else
                result.setOwner(element);
        
            model.getDefaultNameService().setDefaultName(result, CONSTRAINTPROPERTY);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createPropertySpecificType
     * @author ebrosse
     * @return
     */
    @objid ("e4b237bb-2a9c-4dbd-afc9-c72fdf0418a9")
    public static Class createPropertySpecificType(NameSpace element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel ();
        Class result = null;
        
        try {
            result = model.createClass();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PROPERTYSPECIFICTYPE, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Class.class)));
            result.setOwner(element);
            model.getDefaultNameService().setDefaultName(result, PROPERTYSPECIFICTYPE);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createControlOperatorOperation
     * @author ebrosse
     * @return
     */
    @objid ("f9341ab1-fe17-4276-b023-9a227bc50c0a")
    public static Operation createControlOperatorOperation(Classifier element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel ();
        Operation result = null;
        
        try {
            result = model.createOperation();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONTROLOPERATOR_OPERATION, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Operation.class)));
            result.setOwner(element);
            model.getDefaultNameService().setDefaultName(result, OPERATOR);
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createContinuousParameter
     * @author ebrosse
     * @return
     */
    @objid ("68d2cc76-c6f4-4948-86ec-eaab4772ff82")
    public static Parameter createContinuousParameter(MObject element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        Parameter result = null;
        
        try {
        
            if (element instanceof Operation){
                result = session.getModel ().createParameter();
                result.setComposed((Operation)element);
                setContinuousParameter(result);
            }else if (element instanceof Activity){
                result = session.getModel ().createBehaviorParameter();
                ((Activity)element).getParameter().add((BehaviorParameter)result);
                setContinuousParameter(result);
            }
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createContinuousEdge
     * @author ebrosse
     * @return
     */
    @objid ("a94502e6-1fde-49e9-a77f-8027c722d1b4")
    public static ActivityEdge createContinuousEdge(ActivityNode source, ActivityNode target) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        try {
            ActivityEdge result = createActivityEdge(source, target);
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype (SysMLStereotypes.CONTINUOUS_ACTIVITYEDGE, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ActivityEdge.class) ));
            model.getDefaultNameService().setDefaultName(result, CONTINUOUS);
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createRateParameter
     * @author ebrosse
     * @return
     */
    @objid ("2207f2a7-6459-41e1-b138-181807cf29de")
    public static Parameter createRateParameter(MObject element) {
        try {
            IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
            Parameter result = null;
        
            if (element instanceof Operation){
                result = session.getModel ().createParameter();
                result.setComposed((Operation)element);
                setRateParameter(result);
            }else if (element instanceof Activity){
                result = session.getModel ().createBehaviorParameter();
                ((Activity)element).getParameter().add((BehaviorParameter)result);
                setRateParameter(result);
            }
        
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createOptional
     * @author ebrosse
     * @return
     */
    @objid ("d53ff779-4809-4cac-835c-234f42a0262d")
    public static Parameter createOptional(MObject element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel ();
        Parameter result = null;
        
        try {
        
            if (element instanceof Operation){
                result = model.createParameter();
                result.setComposed((Operation) element);
                setOptionalParameter(result);
        
            }else if (element instanceof Activity){
                result = model.createBehaviorParameter();
                ((Activity)element).getParameter().add((BehaviorParameter)result);
                setOptionalParameter(result);
            }
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createTestcase
     * @author ebrosse
     * @return
     */
    @objid ("a84a517e-15ac-4b76-a337-617820c58794")
    public static Operation createTestcase(Classifier element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel ();
        
        try {
            Operation result = model.createOperation();
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.TESTCASE_OPERATION, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Operation.class)));
            result.setOwner(element);
            model.getDefaultNameService().setDefaultName(result, TESTCASE);
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createDiscreteParameter
     * @author ebrosse
     * @return
     */
    @objid ("fc67a65e-d0a0-44ef-b591-cceefdd39f2c")
    public static Parameter createDiscreteParameter(MObject element) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        Parameter result = null;
        IUmlModel model = session.getModel ();
        
        try {
        
            if (element instanceof Operation){
                result = model.createParameter();
                result.setComposed((Operation)element);
                setDiscreteParameter(result);
            }else if (element instanceof Activity){
                result = model.createBehaviorParameter();
                ((Activity)element).getParameter().add((BehaviorParameter)result);
                setDiscreteParameter(result);
            }
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return result;
    }

    /**
     * Method createDiscreteEdge
     * @author ebrosse
     * @return
     */
    @objid ("3c936905-d32f-4a44-8dbe-1afdfdb988a9")
    public static ActivityEdge createDiscreteEdge(ActivityNode source, ActivityNode target) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        
        try {
        
            ActivityEdge result = createActivityEdge(source,target);
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.DISCRETE_ACTIVITYEDGE, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ActivityEdge.class)));
            result.setName("");
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createRateEdge
     * @author ebrosse
     * @return
     */
    @objid ("7d03763d-d59d-4e72-9da0-dfbbf80bcbf1")
    public static ActivityEdge createRateEdge(ActivityNode source, ActivityNode target) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        
        try {
        
            ActivityEdge result = createActivityEdge(source,target);
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.RATE_ACTIVITYEDGE, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ActivityEdge.class) ));
            result.setName("");
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createBindingConnector
     * @author ebrosse
     * @return
     */
    @objid ("1434b4ce-09db-42a9-aeee-c36ef585b4f8")
    public static Link createBindingConnector(BindableInstance source, BindableInstance target) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        try {
        
            Link result = model.createConnector(source, target, "");
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BINDINGCONNECTOR, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Connector.class)));
        
            result.setName("");
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createProbability
     * @author ebrosse
     * @return
     */
    @objid ("5f064565-b239-4792-8d5b-6bde9789eec1")
    public static ActivityEdge createProbability(ActivityNode source, ActivityNode target) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        
        try {
        
            ActivityEdge result = createActivityEdge(source,target);
            result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PROBABILITY, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(ActivityEdge.class)));
            result.setName("");
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
            return null;
        }
    }

    /**
     * Method createTraceDependency
     * @author ebrosse
     * @return
     */
    @objid ("206a9a33-52ac-4200-8cc3-93510bf9905b")
    public static Dependency createTraceDependency(ModelElement originElement, ModelElement targetElement) {
        try {
            IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
            Dependency result = session.getModel().createDependency (originElement, targetElement, MODELER_MODULE, TRACE);
            result.setName("");
        
            return result;
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createFlowProperty
     * @author ebrosse
     * @return
     */
    @objid ("730341bc-7a95-4cc7-91dc-df15c65d164c")
    public static Attribute createFlowProperty(Classifier owner) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel ();
        
        try {
        
            Attribute result = model.createAttribute();
            result.getExtension().add(session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPROPERTY, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Attribute.class)));
            owner.getOwnedAttribute().add(result);
            model.getDefaultNameService().setDefaultName(result, FLOWPROPERTY);
            return result;
        
        } catch (Exception e) {
            SysMLModule.logService.error(e);
        }
        return null;
    }

    /**
     * Method createOperation
     * @author ebrosse
     * 
     * @return the created UML Operation
     */
    @objid ("fc54e2b3-b1f2-4c88-8ae6-b74b6bab4122")
    public static Operation createOperation(Classifier element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createOperation(OPERATION, element);
    }

    /**
     * Method createParameter
     * @author ebrosse
     * 
     * @return the created UML Parameter
     */
    @objid ("9b17f88f-fab7-4976-8658-6a589126e87f")
    public static Parameter createParameter(Operation element) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        Parameter result = model.createParameter();
        element.getIO().add(result);
        model.getDefaultNameService().setDefaultName(result, PARAMETER);
        return result;
    }

    /**
     * Method createReturnParameter
     * @author ebrosse
     * 
     * @return the created UML Return Parameter
     */
    @objid ("a09cfa76-bd66-44cf-b945-af84ee223775")
    public static Parameter createReturnParameter(Operation element) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        Parameter result = model.createParameter();
        element.setReturn(result);
        model.getDefaultNameService().setDefaultName(result, RETURN_PARAMETER);
        return result;
    }

    /**
     * Method createUseCAse
     * @author ebrosse
     * 
     * @return the created UML Use Case
     */
    @objid ("557613cc-143a-4850-87a1-112c6b51f293")
    public static UseCase createUseCase(NameSpace element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createUseCase(USE_CASE, element);
    }

    /**
     * Method createActor
     * @author ebrosse
     * 
     * @return the created UML Actor
     */
    @objid ("f5192061-b112-4498-a504-cebb5be92491")
    public static Actor createActor(NameSpace element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createActor(ACTOR, element);
    }

    /**
     * Method createEnumeration
     * @author ebrosse
     * 
     * @return the created UML Enumeration
     */
    @objid ("c3a270c6-c34f-4e06-803c-ae7e523ac5c8")
    public static Enumeration createEnumeration(NameSpace element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createEnumeration(ENUMERATION, element);
    }

    /**
     * Method createEnumerationLiteral
     * @author ebrosse
     * 
     * @return the created UML Enumeration Literal
     */
    @objid ("b0aaef76-c293-4c1d-a766-ea58ff57aa26")
    public static EnumerationLiteral createEnumerationLiteral(Enumeration element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createEnumerationLiteral(ENUMERATION_LITERAL, element);
    }

    /**
     * Method createBlockDiagram
     * @author ebrosse
     * @return
     */
    @objid ("6dee890a-d093-4d98-be20-3ff9324b13da")
    public static StaticDiagram createBlockDiagram(ModelElement element, String name, String descriptionContent) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            result = createBlockDiagram(element, name);
            model.createNote(MODELER_MODULE, DESCRIPTION, result, descriptionContent);
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    /**
     * Method createBlockDiagram
     * @author ebrosse
     * @return
     */
    @objid ("b9112fdd-12c6-4eb6-b237-406d2d5aa769")
    public static StaticDiagram createBlockDiagram(ModelElement element, String name) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            MClass mclass = context.getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class);
            Stereotype ster = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BLOCKDIAGRAM, mclass);
            result = model.createStaticDiagram("", element, ster);
            model.getDefaultNameService().setDefaultName(result, name);
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    /**
     * Method createIBD
     * @author ebrosse
     * @return
     */
    @objid ("86836491-5454-47fc-b595-ce79feb9d339")
    public static StaticDiagram createIBD(ModelElement element, String name, String descriptionContent) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            result = createIBD(element, name);
            model.createNote(MODELER_MODULE, DESCRIPTION, result, descriptionContent);
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    @objid ("6c7ce4a5-934e-47d2-8e14-2c1ff1c2963c")
    public static StaticDiagram createIBD(ModelElement element, String name) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            MClass mclass = context.getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class);
            Stereotype ster = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.INTERNALBLOCKDIAGRAM, mclass);
            result = model.createStaticDiagram("", element, ster);
            model.getDefaultNameService().setDefaultName(result, name);
            return result;
        
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    @objid ("036d1ca8-c4e3-429f-ba38-98866882473f")
    public static StaticDiagram createPackageDiagram(ModelElement owner, String name) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            MClass mclass = context.getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class);
            Stereotype ster = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PACKAGEDIAGRAM, mclass);
            result = model.createStaticDiagram("", owner, ster);
            model.getDefaultNameService().setDefaultName(result, name);
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    /**
     * Method createPackage Diagram
     * @author ebrosse
     * @return
     */
    @objid ("e286c980-b0d3-4f5b-a470-d7c683f2df87")
    public static StaticDiagram createPackageDiagram(ModelElement element, String name, String descriptionContent) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            result = createPackageDiagram(element, name);
            model.createNote(MODELER_MODULE, DESCRIPTION, result, descriptionContent);
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    /**
     * Method create Parametric Diagram
     * @author ebrosse
     * @return
     */
    @objid ("f5278a50-27f3-4910-b202-77d8fb05dafd")
    public static StaticDiagram createParametricDiagram(ModelElement element, String name, String descriptionContent) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            result = createParametricDiagram(element, name);
            model.createNote(MODELER_MODULE, DESCRIPTION, result, descriptionContent);
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    @objid ("bc17507d-cf82-4796-911e-bb231f1b5797")
    public static StaticDiagram createParametricDiagram(ModelElement owner, String name) {
        IModuleContext context = SysMLModule.getInstance().getModuleContext();
        IModelingSession session  =  context.getModelingSession();
        IUmlModel model = session.getModel();
        StaticDiagram result = null;
        
        try {
            MClass mclass = context.getModelioServices().getMetamodelService().getMetamodel().getMClass(StaticDiagram.class);
            Stereotype ster = session.getMetamodelExtensions().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PARAMETRICDIAGRAM, mclass);
            result = model.createStaticDiagram("", owner, ster);
            model.getDefaultNameService().setDefaultName(result, name);
            return result;
        } catch (Exception e){
            SysMLModule.logService.error(e);
        };
        return result;
    }

    /**
     * Method createActivityEdge
     * @author ebrosse
     * @return
     */
    @objid ("2200d973-d3fb-4cbb-a11f-2bbd876612ff")
    public static ActivityEdge createActivityEdge(ActivityNode source, ActivityNode target) {
        IUmlModel model = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel();
        ActivityEdge result = null;
        if ((source instanceof ObjectNode) || (target instanceof ObjectNode))
            result = model.createObjectFlow();
        else
            result = model.createControlFlow();
        
        result.setSource(source);
        result.setTarget(target);
        return result;
    }

    /**
     * Method createAssociation
     * @author ebrosse
     * @return
     */
    @objid ("9c5ac333-0c44-4f0e-8aee-2db7326edbfe")
    public static Association createAssociation(Classifier c_source, Classifier c_destination) {
        Association association = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel()
                .createAssociation(c_source, c_destination, "");
        
        for (AssociationEnd end : association.getEnd()) {
            if (end.getOwner().equals(c_source)) {
                end.setName(c_destination.getName().toLowerCase());
                end.setMultiplicityMin("1");
                end.setMultiplicityMax("1");
            } else if (end.getOwner().equals(c_destination)) {
                end.setName(c_source.getName().toLowerCase());
            }
        }
        return association;
    }

    /**
     * Method createConnector
     * @author ebrosse
     * @return
     */
    @objid ("c09e27cf-5ed2-42d6-8753-bd3a93c21b1d")
    public static Link createSmartLink(Instance c_source, Instance c_destination) {
        Link result = null;
        
        if (c_source instanceof BindableInstance)
            result = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel()
            .createConnector((BindableInstance) c_source, (BindableInstance) c_destination, "");
        else
            result = SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createLink(c_source, c_destination, "");
        
        List<LinkEnd> ends = result.getLinkEnd();
        for (LinkEnd end : ends) {
            end.setName("");
            if (end.getOwner().equals(c_destination))
                end.setNavigable(true);
        }
        
        result.setName("");
        return result;
    }

}
