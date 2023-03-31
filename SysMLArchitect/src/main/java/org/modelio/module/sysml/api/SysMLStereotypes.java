/**
 * Java Class : SysMLStereotypes.java
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
 * @category   Modelio API
 * @package    org.modelio.module.sysml.api
 * @author     Modelio
 * @license    http://www.apache.org/licenses/LICENSE-2.0
 * @version    2.0.08
 **/
package org.modelio.module.sysml.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * @author ebrosse
 */

public interface SysMLStereotypes {
    
    public static final String ALLOCATE = "Allocate";

    
    public static final String ALLOCATEACTIVITYPARTITION = "AllocateActivityPartition";

    
    public static final String ALLOCATED = "Allocated";

    
    public static final String BINDINGCONNECTOR = "BindingConnector";

    
    public static final String BLOCK = "Block";

    
    public static final String CONFORM = "Conform";

    
    public static final String CONNECTORPROPERTY = "ConnectorProperty";

    
    public static final String CONNECTORPROPERTYCONNECTOR = "ConnectorPropertyConnector";

    
    public static final String CONSTRAINTBLOCK = "ConstraintBlock";

    
    public static final String CONSTRAINTPROPERTY = "ConstraintProperty";

    
    public static final String CONTINUOUS_ACTIVITYEDGE = "Continuous_ActivityEdge";

    
    public static final String CONTINUOUS_PARAMETER = "Continuous_Parameter";

    
    public static final String CONTROLOPERATOR_BEHAVIOR = "ControlOperator_Behavior";

    
    public static final String CONTROLOPERATOR_OPERATION = "ControlOperator_Operation";

    
    public static final String DIMENSION = "Dimension";

    
    public static final String DISCRETE_ACTIVITYEDGE = "Discrete_ActivityEdge";

    
    public static final String DISCRETE_PARAMETER = "Discrete_Parameter";

    
    public static final String DISTRIBUTEDPROPERTY = "DistributedProperty";

    
    public static final String FLOWPORT = "FlowPort";

    
    public static final String FLOWIN = "FlowPort_In";

    
    public static final String FLOWOUT = "FlowPort_Out";

    
    public static final String FLOWINOUT = "FlowPort_INOUT";

    
    public static final String FLOWPROPERTY = "FlowProperty";

    
    public static final String FLOWSPECIFICATION = "FlowSpecification";

    
    public static final String ITEMFLOW = "ItemFlow";

    
    public static final String ITEMPROPERTY = "ItemProperty";

    
    public static final String NESTEDCONNECTOREND = "NestedConnectorEnd";

    
    public static final String NESTEDCONNECTORENDPROPERTYPATH = "NestedConnectorEndPropertyPath";

    
    public static final String NOBUFFER = "NoBuffer";

    
    public static final String OPTIONAL = "Optional";

    
    public static final String OVERWRITE = "Overwrite";

    
    public static final String PARTICIPANTPROPERTYEND = "ParticipantPropertyEnd";

    
    public static final String PARTICIPANTPROPERTY_ASSOCIATIONEND = "ParticipantProperty_AssociationEnd";

    
    public static final String PARTICIPANTPROPERTY_BINDABLEINSTANCE = "ParticipantProperty_BindableInstance";

    
    public static final String PROBABILITY = "Probability";

    
    public static final String PROPERTYSPECIFICTYPE = "PropertySpecificType";

    
    public static final String QUANTITYKIND = "QuantityKind";

    
    public static final String RATE = "Rate";

    
    public static final String RATE_ACTIVITYEDGE = "Rate_ActivityEdge";

    
    public static final String RATE_PARAMETER = "Rate_Parameter";

    
    public static final String REQUIREMENTRELATED = "RequirementRelated";

    
    public static final String TESTCASE_BEHAVIOR = "TestCase_Behavior";

    
    public static final String TESTCASE_OPERATION = "TestCase_Operation";

    
    public static final String UNIT = "Unit";

    
    public static final String UNITQUANTITYKIND = "UnitQuantityKind";

    
    public static final String VALUETYPE = "ValueType";

    
    public static final String VALUETYPEUNIT = "ValueTypeUnit";

    
    public static final String VALUETYPEQUANTITYKIND = "ValueTypeQuantityKind";

    
    public static final String VIEW = "View";

    
    public static final String VIEWPOINT = "Viewpoint";

    
    public static final String BLOCKDIAGRAM = "BlockDiagram";

    
    public static final String INTERNALBLOCKDIAGRAM = "InternalBlockDiagram";

    
    public static final String PACKAGEDIAGRAM = "PackageDiagram";

    
    public static final String PARAMETRICDIAGRAM = "ParametricDiagram";

    
    public static final String SYSMLDIAGRAM = "SysMLDiagram";

    
    public static final String SYSMLACTIVITYDIAGRAM = "SysMLActivityDiagram";

    
    public static final String SYSMLSEQUENCEDIAGRAM = "SysMLSequenceDiagram";

    
    public static final String SYSMLSTATEMACHINEDIAGRAM = "SysMLStateMachineDiagram";

    
    public static final String SYSMLREQUIREMENTDIAGRAM = "SysMLRequirementDiagram";

    
    public static final String SYSMLUSECASEDIAGRAM = "SysMLUseCaseDiagram";

}
