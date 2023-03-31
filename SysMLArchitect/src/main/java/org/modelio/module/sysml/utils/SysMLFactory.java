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

public class SysMLFactory {
    
    private static final String LOCALS = "locals";

    
    private static final String DESCRIPTION = "description";

    
    private static final String FLOW_PORT = "FlowPort";

    
    private static final String FLOW_SPECIFICATION = "FlowSpecification";

    
    private static final String VIEW = "View";

    
    private static final String VALUETYPE = "VT";

    
    private static final String VIEWPOINT = "Viewpoint";

    
    private static final String UNIT = "Unit";

    
    private static final String QUANTITY = "Quantity";

    
    private static final String CONNECTORPROPERTY = "co";

    
    private static final String DISTRIBUTEDPROPERTY = "d";

    
    private static final String PARTICIPANTPROPERTY = "p";

    
    private static final String CONSTRAINTPROPERTY = "ct";

    
    private static final String PROPERTYSPECIFICTYPE = "PST";

    
    private static final String OPERATOR = "Operator";

    
    private static final String OPERATION = "Operation";

    
    private static final String ENUMERATION_LITERAL = "Enumeration Literal";

    
    private static final String ENUMERATION = "Enumeration";

    
    private static final String ACTOR = "Actor";

    
    private static final String USE_CASE = "Use Case";

    
    private static final String RETURN_PARAMETER = "Return Parameter";

    
    private static final String PARAMETER = "Parameter";

    
    private static final String FLOWPROPERTY = "fp";

    
    private static final String TRACE = "trace";

    
    private static final String MODELER_MODULE = "ModelerModule";

    
    private static final String DISCRETE = "discrete";

    
    private static final String TESTCASE = "TC";

    
    private static final String OPTIONAL = "opt";

    
    private static final String RATE = "rate";

    
    private static final String CONTINUOUS = "continuous";

    
    private static void setContinuousParameter(Parameter result) {
        IModelingSession modelingSession = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = modelingSession.getModel();
        
        result.setIsStream(true);
        result.getExtension().add (modelingSession.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME,SysMLStereotypes.CONTINUOUS_PARAMETER, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, CONTINUOUS);
    }

    
    private static void setRateParameter(Parameter result) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        result.setIsStream(true);
        result.getExtension().add (session.getMetamodelExtensions ().getStereotype (ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE_PARAMETER, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, RATE);
    }

    
    private static void setOptionalParameter(Parameter result) {
        IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();
        IUmlModel model = session.getModel();
        
        result.getExtension().add (session.getMetamodelExtensions ().getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.OPTIONAL, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(Parameter.class)));
        result.setType(model.getUmlTypes().getSTRING());
        model.getDefaultNameService().setDefaultName(result, OPTIONAL);
        result.setMultiplicityMin("0");
    }

    
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
    
    public static Operation createOperation(Classifier element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createOperation(OPERATION, element);
    }

    /**
     * Method createParameter
     * @author ebrosse
     * 
     * @return the created UML Parameter
     */
    
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
    
    public static UseCase createUseCase(NameSpace element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createUseCase(USE_CASE, element);
    }

    /**
     * Method createActor
     * @author ebrosse
     * 
     * @return the created UML Actor
     */
    
    public static Actor createActor(NameSpace element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createActor(ACTOR, element);
    }

    /**
     * Method createEnumeration
     * @author ebrosse
     * 
     * @return the created UML Enumeration
     */
    
    public static Enumeration createEnumeration(NameSpace element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createEnumeration(ENUMERATION, element);
    }

    /**
     * Method createEnumerationLiteral
     * @author ebrosse
     * 
     * @return the created UML Enumeration Literal
     */
    
    public static EnumerationLiteral createEnumerationLiteral(Enumeration element) {
        return  SysMLModule.getInstance().getModuleContext().getModelingSession().getModel().createEnumerationLiteral(ENUMERATION_LITERAL, element);
    }

    /**
     * Method createBlockDiagram
     * @author ebrosse
     * @return
     */
    
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
