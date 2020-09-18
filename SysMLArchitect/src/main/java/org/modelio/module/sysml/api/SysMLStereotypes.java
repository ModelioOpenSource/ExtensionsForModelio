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
@objid ("eb796851-bcc0-49fb-80d2-597c4392c2f6")
public interface SysMLStereotypes {
    @objid ("6378a132-93a1-4fba-9dc7-e8acf8f0ab49")
    public static final String ALLOCATE = "Allocate";

    @objid ("3bee9d74-8f84-4bd9-9260-96d15d4234b3")
    public static final String ALLOCATEACTIVITYPARTITION = "AllocateActivityPartition";

    @objid ("10e7c69d-b1a9-4c12-bfaf-2cb28698c64a")
    public static final String ALLOCATED = "Allocated";

    @objid ("87f60f5b-4902-4774-9609-e6cd8b826c50")
    public static final String BINDINGCONNECTOR = "BindingConnector";

    @objid ("dae5007a-585f-406e-bef1-971499714b9f")
    public static final String BLOCK = "Block";

    @objid ("0b3c5e84-ea2b-4158-b6ba-8d6a10584539")
    public static final String CONFORM = "Conform";

    @objid ("fd1bb5c5-a6c5-43a4-86e7-5340ead5f32a")
    public static final String CONNECTORPROPERTY = "ConnectorProperty";

    @objid ("e4db1519-c632-4858-93fe-296e02e209ed")
    public static final String CONNECTORPROPERTYCONNECTOR = "ConnectorPropertyConnector";

    @objid ("cbd3b9de-7feb-41db-b23d-0e72a81b072e")
    public static final String CONSTRAINTBLOCK = "ConstraintBlock";

    @objid ("9870d5ba-1fdb-4eb8-91b3-2e18ceabd3b4")
    public static final String CONSTRAINTPROPERTY = "ConstraintProperty";

    @objid ("38e39ca3-b0b9-4c54-8651-708a4a96847a")
    public static final String CONTINUOUS_ACTIVITYEDGE = "Continuous_ActivityEdge";

    @objid ("df5f2ca4-5e12-4ea3-b785-ea720f7275bf")
    public static final String CONTINUOUS_PARAMETER = "Continuous_Parameter";

    @objid ("7493c597-6357-49c1-aded-a5f4d272037c")
    public static final String CONTROLOPERATOR_BEHAVIOR = "ControlOperator_Behavior";

    @objid ("f261c694-ea75-4fd5-a9bf-5f69fa1cfdcf")
    public static final String CONTROLOPERATOR_OPERATION = "ControlOperator_Operation";

    @objid ("927e0934-97bc-49a8-bcee-c57ad0de9409")
    public static final String DIMENSION = "Dimension";

    @objid ("a5ec5d38-84ff-4222-b387-00781e398d10")
    public static final String DISCRETE_ACTIVITYEDGE = "Discrete_ActivityEdge";

    @objid ("6f335896-db80-4166-b67b-3a62c904d9c7")
    public static final String DISCRETE_PARAMETER = "Discrete_Parameter";

    @objid ("1ae26ec9-818e-4a16-a466-a60298d6ae72")
    public static final String DISTRIBUTEDPROPERTY = "DistributedProperty";

    @objid ("2c62a171-4678-49fd-8264-083d6adf2de8")
    public static final String FLOWPORT = "FlowPort";

    @objid ("2114ddb7-cb3a-40f5-a1cb-35e26936e9b5")
    public static final String FLOWIN = "FlowPort_In";

    @objid ("25bb222d-acd4-4f41-b3ae-210aff43c482")
    public static final String FLOWOUT = "FlowPort_Out";

    @objid ("d57b6830-8070-4d6d-9af4-c858eca5e848")
    public static final String FLOWINOUT = "FlowPort_INOUT";

    @objid ("b63096a6-fd93-4bc3-bed9-3f72d1be4157")
    public static final String FLOWPROPERTY = "FlowProperty";

    @objid ("b222831d-4b0f-4bf0-9d2a-6ec2551dcd53")
    public static final String FLOWSPECIFICATION = "FlowSpecification";

    @objid ("d3f22f05-5e8b-4e7e-9fea-d3ca2a52731c")
    public static final String ITEMFLOW = "ItemFlow";

    @objid ("13f565ca-d131-4322-88a7-88a6e962936a")
    public static final String ITEMPROPERTY = "ItemProperty";

    @objid ("181ebe17-9b4e-4eeb-90df-598f214d344f")
    public static final String NESTEDCONNECTOREND = "NestedConnectorEnd";

    @objid ("e090b3a4-a3c0-4782-a18a-2cad41f0491d")
    public static final String NESTEDCONNECTORENDPROPERTYPATH = "NestedConnectorEndPropertyPath";

    @objid ("14dfa97f-461e-4da2-8b19-5b0802d7b566")
    public static final String NOBUFFER = "NoBuffer";

    @objid ("799e58f7-2a30-4e89-b99a-9a660d1f14c0")
    public static final String OPTIONAL = "Optional";

    @objid ("9e76d84d-42e7-4f1e-bab2-5e50f2b8f8ef")
    public static final String OVERWRITE = "Overwrite";

    @objid ("c63502ae-d844-4536-b97b-f7bcaff03be4")
    public static final String PARTICIPANTPROPERTYEND = "ParticipantPropertyEnd";

    @objid ("b0ae40cb-9625-4a82-90ca-fe410d106b3d")
    public static final String PARTICIPANTPROPERTY_ASSOCIATIONEND = "ParticipantProperty_AssociationEnd";

    @objid ("5dc22737-89b5-438a-865a-343d64190a2f")
    public static final String PARTICIPANTPROPERTY_BINDABLEINSTANCE = "ParticipantProperty_BindableInstance";

    @objid ("1828091f-bd95-4a29-9857-a8766ebb609c")
    public static final String PROBABILITY = "Probability";

    @objid ("c8e5442b-bc8a-4be3-ba65-b82ad90e2a43")
    public static final String PROPERTYSPECIFICTYPE = "PropertySpecificType";

    @objid ("e7df0561-dc10-430f-bfd2-946dc7566854")
    public static final String QUANTITYKIND = "QuantityKind";

    @objid ("dd029dcf-c747-46dc-9e31-d50da7aebbbe")
    public static final String RATE = "Rate";

    @objid ("26028372-c2b8-4dcb-9f49-0b49154d1cd0")
    public static final String RATE_ACTIVITYEDGE = "Rate_ActivityEdge";

    @objid ("9dd4026f-b3af-4187-a673-d3d97eded2a9")
    public static final String RATE_PARAMETER = "Rate_Parameter";

    @objid ("939c5fea-fd3c-4deb-a759-efd713da8654")
    public static final String REQUIREMENTRELATED = "RequirementRelated";

    @objid ("ec43f46b-25ff-40a9-9eb2-bd5ef971babd")
    public static final String TESTCASE_BEHAVIOR = "TestCase_Behavior";

    @objid ("bcb45b71-f544-49f8-9479-14d6d4eb7226")
    public static final String TESTCASE_OPERATION = "TestCase_Operation";

    @objid ("288f0ba5-33da-4a83-8f95-ec455f7dcefd")
    public static final String UNIT = "Unit";

    @objid ("1d3ac4c8-cc3a-422b-98ac-1433adba969e")
    public static final String UNITQUANTITYKIND = "UnitQuantityKind";

    @objid ("cf4c41fe-dae9-4906-a156-bdf3709dd25b")
    public static final String VALUETYPE = "ValueType";

    @objid ("a3f9aa5c-dda7-4c22-a02a-bc9383e0852a")
    public static final String VALUETYPEUNIT = "ValueTypeUnit";

    @objid ("b53ea5af-3df4-47e6-ba7c-85385885ac29")
    public static final String VALUETYPEQUANTITYKIND = "ValueTypeQuantityKind";

    @objid ("7421dd75-66b8-4a48-affd-45a966abe273")
    public static final String VIEW = "View";

    @objid ("2a646136-8e28-458a-9095-85352f5e1e0c")
    public static final String VIEWPOINT = "Viewpoint";

    @objid ("3aa97e20-a778-47d8-a0f0-99444469706e")
    public static final String BLOCKDIAGRAM = "BlockDiagram";

    @objid ("8e9f30c5-e2e1-44ed-b1a0-5d09ba0f7785")
    public static final String INTERNALBLOCKDIAGRAM = "InternalBlockDiagram";

    @objid ("2a9e93f0-29bc-4841-9726-eb7a66a9253f")
    public static final String PACKAGEDIAGRAM = "PackageDiagram";

    @objid ("b8875d43-de63-44be-be53-3269d1832a77")
    public static final String PARAMETRICDIAGRAM = "ParametricDiagram";

    @objid ("8cc7ffde-bfe0-4384-b05c-5904bd2385ff")
    public static final String SYSMLDIAGRAM = "SysMLDiagram";

    @objid ("c5a7561e-380e-4e5d-9ae1-577b7224579e")
    public static final String SYSMLACTIVITYDIAGRAM = "SysMLActivityDiagram";

    @objid ("423d99eb-1681-46d8-ac85-6cf4cd6f673b")
    public static final String SYSMLSEQUENCEDIAGRAM = "SysMLSequenceDiagram";

    @objid ("d99076f4-3195-45c5-b90c-a66105dcdf07")
    public static final String SYSMLSTATEMACHINEDIAGRAM = "SysMLStateMachineDiagram";

    @objid ("97c0e8ba-1bb8-43ab-ad6d-cec163e161ef")
    public static final String SYSMLREQUIREMENTDIAGRAM = "SysMLRequirementDiagram";

    @objid ("e02f939e-f1ba-483d-966b-c7eb087b0d54")
    public static final String SYSMLUSECASEDIAGRAM = "SysMLUseCaseDiagram";

}
