/**
 * Java Class : SysMLTagTypes.java
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
 * List of all SysML Tag Types
 * @author ebrosse
 */
@objid ("95b170f0-6f7c-43ed-9d7b-f0a849c40a18")
public interface SysMLTagTypes {
    /**
     * AllocatedTo Tag Type from Allocated Stereotype
     */
    @objid ("f9eed397-165e-4cf9-8c05-ca7326a4f03e")
    public static final String ALLOCATED_ALLOCATEDTO = "Allocated_allocatedTo";

    /**
     * AllocatedFrom Tag Type from Allocated Stereotype
     */
    @objid ("177684a0-164a-4628-a27a-0d342fdb24c8")
    public static final String ALLOCATED_ALLOCATEDFROM = "Allocated_allocatedFrom";

    /**
     * IsEncapsulated Tag Type from Block Stereotype
     */
    @objid ("f20d09d7-93e2-41f0-abf6-34fa6ea82615")
    public static final String BLOCK_ISENCAPSULATED = "Block_isEncapsulated";

    /**
     * Connector Tag Type from ConnectorProperty Stereotype
     */
    @objid ("52a74d26-871a-4aba-8355-c1deef34b9ba")
    public static final String CONNECTORPROPERTY_CONNECTOR = "ConnectorProperty_connector";

    /**
     * IsAtomic Tag Type from Flow Port Stereotype
     */
    @objid ("15532a14-d778-4733-9382-4ff6494491c3")
    public static final String FLOWPORT_ISATOMIC = "FlowPort_isAtomic";

    /**
     * IsConjugated Tag Type from Flow Port Stereotype
     */
    @objid ("6dedcf25-4093-4234-b27b-83668ed49f24")
    public static final String FLOWPORT_ISCONJUGATED = "FlowPort_isConjugated";

    /**
     * Direction Tag Type from Flow Property Stereotype
     */
    @objid ("4ec30667-8d0e-470f-bec0-806ce97800bb")
    public static final String FLOWPROPERTY_DIRECTION = "FlowProperty_direction";

    /**
     * Item Property Tag Type from Item Flow Stereotype
     */
    @objid ("21745f5f-5203-4e4d-83ca-13a2845b1776")
    public static final String ITEMFLOW_ITEMPROPERTY = "ItemFlow_itemProperty";

    /**
     * Property Path Tag Type from Nested Connector End Stereotype
     */
    @objid ("6bc15c91-0872-4d99-a45a-27de2b2cd92e")
    public static final String NESTEDCONNECTOREND_PROPERTYPATH = "NestedConnectorEnd_propertyPath";

    /**
     * End Tag Type from Participant Property Stereotype
     */
    @objid ("92474bf9-6f9d-42dd-bf1d-5124b94321c6")
    public static final String PARTICIPANTPROPERTY_ASSOCIATIONEND_END = "ParticipantProperty_AssociationEnd_end";

    /**
     * End Tag Type from Participant Property Stereotype
     */
    @objid ("d37ee41e-ac16-4e08-8a74-b1295aa4cdab")
    public static final String PARTICIPANTPROPERTY_BINDABLEINSTANCE_END = "ParticipantProperty_BindableInstance_end";

    /**
     * IsStream Tag Type from Pin Stereotype
     */
    @objid ("c0ca4712-79bd-4eab-9519-392937dd538d")
    public static final String PIN_ISSTREAM = "isStream";

    /**
     * Probability Tag Type from Probability Stereotype
     */
    @objid ("bc610d61-cbcb-4943-96d6-106101a52d88")
    public static final String PROBABILITY_PROBABILITY = "Probability_probability";

    /**
     * Symbol Tag Type from QuantityKind Stereotype
     */
    @objid ("9b396271-3242-4c24-b6b7-02ebfd12120f")
    public static final String QUANTITYKIND_SYMBOL = "QuantityKind_symbol";

    /**
     * Description Tag Type from QuantityKind Stereotype
     */
    @objid ("8f3b5bf2-9b93-44a4-bbf5-bd0bbf1651c9")
    public static final String QUANTITYKIND_DESCRIPTION = "QuantityKind_description";

    /**
     * Description URI Tag Type from QuantityKind Stereotype
     */
    @objid ("cb820804-1a09-40f7-b08f-889cbebe7397")
    public static final String QUANTITYKIND_DESCRIPTIONURI = "QuantityKind_descriptionURI";

    /**
     * Rate Tag Type from Rate Stereotype
     */
    @objid ("51288df8-d770-474d-a999-bf6a86ae2e04")
    public static final String RATE_ACTIVITYEDGE_RATE = "Rate_ActivityEdge_rate";

    /**
     * Rate Tag Type from Rate Stereotype
     */
    @objid ("abcfbdb2-789a-432e-af66-4688212c2790")
    public static final String RATE_PARAMETER_RATE = "Rate_Parameter_rate";

    /**
     * Symbol Tag Type from Unit Stereotype
     */
    @objid ("96bcefa9-20e8-47c3-b783-a72ec83f7549")
    public static final String UNIT_SYMBOL = "Unit_symbol";

    /**
     * Description Tag Type from Unit Stereotype
     */
    @objid ("21fcf8f4-11d7-411d-8c00-5827def1a19a")
    public static final String UNIT_DESCRIPTION = "Unit_description";

    /**
     * Description URI Tag Type from Unit Stereotype
     */
    @objid ("d185e695-4e83-45c7-99d9-5d272f4ecf69")
    public static final String UNIT_DESCRIPTIONURI = "Unit_descriptionURI";

    /**
     * Quantity Kind Tag Type from Unit Stereotype
     */
    @objid ("ef6769f6-c983-4ce6-afcc-2c50ae013913")
    public static final String UNIT_QUANTITYKIND = "Unit_quantityKind";

    /**
     * Unit Tag Type from Value Type Stereotype
     */
    @objid ("157e6b54-196a-4b55-8bbc-753f08be1cb8")
    public static final String VALUETYPE_UNIT = "ValueType_unit";

    /**
     * Quantity Kind Tag Type from Value Type Stereotype
     */
    @objid ("0f1f8836-d849-44ed-9449-f8f63602f866")
    public static final String VALUETYPE_QUANTITYKIND = "ValueType_quantityKind";

    /**
     * View Point Tag Type from View Stereotype
     */
    @objid ("b07e59cd-19e8-464c-9990-2edafd5fc4de")
    public static final String VIEW_VIEWPOINT = "View_viewpoint";

    /**
     * Concerns Tag Type from View Point Stereotype
     */
    @objid ("58105049-f3d2-4bf2-bdf0-d9cc80762bde")
    public static final String VIEWPOINT_CONCERNS = "Viewpoint_concerns";

    /**
     * Languages Tag Type from View Point Stereotype
     */
    @objid ("60c0e7b1-9415-4516-8731-8678d2d9de8c")
    public static final String VIEWPOINT_LANGUAGES = "Viewpoint_languages";

    /**
     * Methods Tag Type from View Point Stereotype
     */
    @objid ("7c377d04-bf21-4c7b-8bc8-d58b5a0f8a99")
    public static final String VIEWPOINT_METHODS = "Viewpoint_methods";

    /**
     * Purpose Tag Type from View Point Stereotype
     */
    @objid ("673ba99e-6147-4f91-a438-5d1df72022b8")
    public static final String VIEWPOINT_PURPOSE = "Viewpoint_purpose";

    /**
     * StakeHolders Tag Type from View Point Stereotype
     */
    @objid ("48f9858c-5e5e-476b-b047-0b30f684df66")
    public static final String VIEWPOINT_STAKEHOLDERS = "Viewpoint_stakeholders";

}
