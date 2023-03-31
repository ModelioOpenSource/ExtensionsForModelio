package org.modelio.module.sysml.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.module.sysml.api.SysMLNoteTypes;
import org.modelio.module.sysml.api.SysMLStereotypes;

/**
 * This class lists all predefined value of UML command name.
 * @author ebrosse
 */

public interface ISysMLCustomizerPredefinedField {
    
    public static final String Prefix = "SysML";

    
    public static final String Allocate = Prefix + SysMLStereotypes.ALLOCATE;

    
    public static final String Block = Prefix + SysMLStereotypes.BLOCK;

    
    public static final String BindingConnector = Prefix + SysMLStereotypes.BINDINGCONNECTOR;

    
    public static final String Conform = Prefix + SysMLStereotypes.CONFORM;

    
    public static final String ConnectorProperty = Prefix + SysMLStereotypes.CONNECTORPROPERTY;

    
    public static final String ConstraintBlock = Prefix + SysMLStereotypes.CONSTRAINTBLOCK;

    
    public static final String ConstraintProperty = Prefix + SysMLStereotypes.CONSTRAINTPROPERTY;

    
    public static final String ContinuousActivityEdge = Prefix + SysMLStereotypes.CONTINUOUS_ACTIVITYEDGE;

    
    public static final String ContinuousParameter = Prefix + SysMLStereotypes.CONTINUOUS_PARAMETER;

    
    public static final String DiscreteActivityEdge = Prefix + SysMLStereotypes.DISCRETE_ACTIVITYEDGE;

    
    public static final String DiscreteParameter = Prefix + SysMLStereotypes.DISCRETE_PARAMETER;

    
    public static final String DistributedProperty = Prefix + SysMLStereotypes.DISTRIBUTEDPROPERTY;

    
    public static final String FlowPort = Prefix + SysMLStereotypes.FLOWPORT;

    
    public static final String FlowProperty = Prefix + SysMLStereotypes.FLOWPROPERTY;

    
    public static final String FlowSpecification = Prefix + SysMLStereotypes.FLOWSPECIFICATION;

    
    public static final String ItemFlow = Prefix + SysMLStereotypes.ITEMFLOW;

    
    public static final String Optional = Prefix + SysMLStereotypes.OPTIONAL;

    
    public static final String Part = Prefix + "Part";

    
    public static final String ParticipantPropertyBindableInstance = Prefix + SysMLStereotypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE;

    
    public static final String Probability = Prefix + SysMLStereotypes.PROBABILITY;

    
    public static final String Problem = Prefix + SysMLNoteTypes.MODELELEMENT_PROBLEM;

    
    public static final String QuantityKind = Prefix + SysMLStereotypes.QUANTITYKIND;

    
    public static final String RateActivityEdge = Prefix + SysMLStereotypes.RATE_ACTIVITYEDGE;

    
    public static final String RateParameter = Prefix + SysMLStereotypes.RATE_PARAMETER;

    
    public static final String Rationale = Prefix + SysMLNoteTypes.MODELELEMENT_RATIONALE;

    
    public static final String Trace = Prefix + "Trace";

    
    public static final String Unit = Prefix + SysMLStereotypes.UNIT;

    
    public static final String ValueType = Prefix + SysMLStereotypes.VALUETYPE;

    
    public static final String View = Prefix + SysMLStereotypes.VIEW;

    
    public static final String ViewPoint = Prefix + SysMLStereotypes.VIEWPOINT;

//
//    
//    public static final String UMLAggregation = Prefix + "UMLAggregation";
//
//    
//    public static final String UMLAssociation = Prefix + "UMLAssociation";
    
    public static final String UMLAttribute = Prefix + "UMLAttribute";

//
//    
//    public static final String UMLComposition = Prefix + "UMLComposition";
    
    public static final String UMLGeneralization = Prefix + "UMLGeneralization";

    
    public static final String UMLOperation = Prefix + "UMLOperation";

    
    public static final String UMLSmartGeneralization = Prefix + "UMLSmartGeneralization";

}
