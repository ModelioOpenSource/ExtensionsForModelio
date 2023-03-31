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

public interface SysMLTagTypes {
    /**
     * AllocatedTo Tag Type from Allocated Stereotype
     */
    
    public static final String ALLOCATED_ALLOCATEDTO = "Allocated_allocatedTo";

    /**
     * AllocatedFrom Tag Type from Allocated Stereotype
     */
    
    public static final String ALLOCATED_ALLOCATEDFROM = "Allocated_allocatedFrom";

    /**
     * IsEncapsulated Tag Type from Block Stereotype
     */
    
    public static final String BLOCK_ISENCAPSULATED = "Block_isEncapsulated";

    /**
     * Connector Tag Type from ConnectorProperty Stereotype
     */
    
    public static final String CONNECTORPROPERTY_CONNECTOR = "ConnectorProperty_connector";

    /**
     * IsAtomic Tag Type from Flow Port Stereotype
     */
    
    public static final String FLOWPORT_ISATOMIC = "FlowPort_isAtomic";

    /**
     * IsConjugated Tag Type from Flow Port Stereotype
     */
    
    public static final String FLOWPORT_ISCONJUGATED = "FlowPort_isConjugated";

    /**
     * Direction Tag Type from Flow Property Stereotype
     */
    
    public static final String FLOWPROPERTY_DIRECTION = "FlowProperty_direction";

    /**
     * Item Property Tag Type from Item Flow Stereotype
     */
    
    public static final String ITEMFLOW_ITEMPROPERTY = "ItemFlow_itemProperty";

    /**
     * Property Path Tag Type from Nested Connector End Stereotype
     */
    
    public static final String NESTEDCONNECTOREND_PROPERTYPATH = "NestedConnectorEnd_propertyPath";

    /**
     * End Tag Type from Participant Property Stereotype
     */
    
    public static final String PARTICIPANTPROPERTY_ASSOCIATIONEND_END = "ParticipantProperty_AssociationEnd_end";

    /**
     * End Tag Type from Participant Property Stereotype
     */
    
    public static final String PARTICIPANTPROPERTY_BINDABLEINSTANCE_END = "ParticipantProperty_BindableInstance_end";

    /**
     * IsStream Tag Type from Pin Stereotype
     */
    
    public static final String PIN_ISSTREAM = "isStream";

    /**
     * Probability Tag Type from Probability Stereotype
     */
    
    public static final String PROBABILITY_PROBABILITY = "Probability_probability";

    /**
     * Symbol Tag Type from QuantityKind Stereotype
     */
    
    public static final String QUANTITYKIND_SYMBOL = "QuantityKind_symbol";

    /**
     * Description Tag Type from QuantityKind Stereotype
     */
    
    public static final String QUANTITYKIND_DESCRIPTION = "QuantityKind_description";

    /**
     * Description URI Tag Type from QuantityKind Stereotype
     */
    
    public static final String QUANTITYKIND_DESCRIPTIONURI = "QuantityKind_descriptionURI";

    /**
     * Rate Tag Type from Rate Stereotype
     */
    
    public static final String RATE_ACTIVITYEDGE_RATE = "Rate_ActivityEdge_rate";

    /**
     * Rate Tag Type from Rate Stereotype
     */
    
    public static final String RATE_PARAMETER_RATE = "Rate_Parameter_rate";

    /**
     * Symbol Tag Type from Unit Stereotype
     */
    
    public static final String UNIT_SYMBOL = "Unit_symbol";

    /**
     * Description Tag Type from Unit Stereotype
     */
    
    public static final String UNIT_DESCRIPTION = "Unit_description";

    /**
     * Description URI Tag Type from Unit Stereotype
     */
    
    public static final String UNIT_DESCRIPTIONURI = "Unit_descriptionURI";

    /**
     * Quantity Kind Tag Type from Unit Stereotype
     */
    
    public static final String UNIT_QUANTITYKIND = "Unit_quantityKind";

    /**
     * Unit Tag Type from Value Type Stereotype
     */
    
    public static final String VALUETYPE_UNIT = "ValueType_unit";

    /**
     * Quantity Kind Tag Type from Value Type Stereotype
     */
    
    public static final String VALUETYPE_QUANTITYKIND = "ValueType_quantityKind";

    /**
     * View Point Tag Type from View Stereotype
     */
    
    public static final String VIEW_VIEWPOINT = "View_viewpoint";

    /**
     * Concerns Tag Type from View Point Stereotype
     */
    
    public static final String VIEWPOINT_CONCERNS = "Viewpoint_concerns";

    /**
     * Languages Tag Type from View Point Stereotype
     */
    
    public static final String VIEWPOINT_LANGUAGES = "Viewpoint_languages";

    /**
     * Methods Tag Type from View Point Stereotype
     */
    
    public static final String VIEWPOINT_METHODS = "Viewpoint_methods";

    /**
     * Purpose Tag Type from View Point Stereotype
     */
    
    public static final String VIEWPOINT_PURPOSE = "Viewpoint_purpose";

    /**
     * StakeHolders Tag Type from View Point Stereotype
     */
    
    public static final String VIEWPOINT_STAKEHOLDERS = "Viewpoint_stakeholders";

}
