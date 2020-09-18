package org.modelio.module.sysml.utils;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.module.sysml.api.SysMLNoteTypes;
import org.modelio.module.sysml.api.SysMLStereotypes;

/**
 * This class lists all predefined value of UML command name.
 * @author ebrosse
 */
@objid ("e86a09a6-cc32-4716-b7b8-aae69736dfd0")
public interface ISysMLCustomizerPredefinedField {
    @objid ("4f900fe0-0013-404f-bfb5-5d90b366314c")
    public static final String Prefix = "SysML";

    @objid ("45167396-d057-46b2-8984-91ee287fdcc9")
    public static final String Allocate = Prefix + SysMLStereotypes.ALLOCATE;

    @objid ("bdb23591-a655-4f54-8ddd-72604994575c")
    public static final String Block = Prefix + SysMLStereotypes.BLOCK;

    @objid ("5ea6eaeb-db85-44b8-94c7-b2eb1704490c")
    public static final String BindingConnector = Prefix + SysMLStereotypes.BINDINGCONNECTOR;

    @objid ("4a5865b7-efd0-43c9-aea6-1baa75db31d3")
    public static final String Conform = Prefix + SysMLStereotypes.CONFORM;

    @objid ("928b8c82-f2cb-4426-b6df-f574fd169563")
    public static final String ConnectorProperty = Prefix + SysMLStereotypes.CONNECTORPROPERTY;

    @objid ("6aa4d566-cc48-4872-8e78-8ba345b13cfa")
    public static final String ConstraintBlock = Prefix + SysMLStereotypes.CONSTRAINTBLOCK;

    @objid ("5685b408-31f5-4841-b476-29a204cb702c")
    public static final String ConstraintProperty = Prefix + SysMLStereotypes.CONSTRAINTPROPERTY;

    @objid ("707fd0e9-6ff3-4918-b6af-87f31505f5c7")
    public static final String ContinuousActivityEdge = Prefix + SysMLStereotypes.CONTINUOUS_ACTIVITYEDGE;

    @objid ("1d76be74-5a13-4747-b837-003361ba9e1a")
    public static final String ContinuousParameter = Prefix + SysMLStereotypes.CONTINUOUS_PARAMETER;

    @objid ("8c4727bd-a51b-4c42-873d-7ed8793c5a31")
    public static final String DiscreteActivityEdge = Prefix + SysMLStereotypes.DISCRETE_ACTIVITYEDGE;

    @objid ("6723a2aa-403b-431d-b5ac-195fe657b86f")
    public static final String DiscreteParameter = Prefix + SysMLStereotypes.DISCRETE_PARAMETER;

    @objid ("b9853596-473a-4f08-a307-d9d2d8ceb43b")
    public static final String DistributedProperty = Prefix + SysMLStereotypes.DISTRIBUTEDPROPERTY;

    @objid ("15ba9c70-88a3-4054-b061-79258bb36bc2")
    public static final String FlowPort = Prefix + SysMLStereotypes.FLOWPORT;

    @objid ("8729febf-bffe-4672-8251-c9328eea1b77")
    public static final String FlowProperty = Prefix + SysMLStereotypes.FLOWPROPERTY;

    @objid ("37fe4b57-ba13-470e-87a6-c0728d36aba3")
    public static final String FlowSpecification = Prefix + SysMLStereotypes.FLOWSPECIFICATION;

    @objid ("e13a9104-05f9-4b4f-9f57-739beee8a130")
    public static final String ItemFlow = Prefix + SysMLStereotypes.ITEMFLOW;

    @objid ("ae40826d-019b-420f-bc64-6d2696982507")
    public static final String Optional = Prefix + SysMLStereotypes.OPTIONAL;

    @objid ("00e192ce-e957-4ee0-bda0-c48eafed9ff9")
    public static final String Part = Prefix + "Part";

    @objid ("045b688d-c991-4283-9ba7-f5d019e7d414")
    public static final String ParticipantPropertyBindableInstance = Prefix + SysMLStereotypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE;

    @objid ("9a398832-728e-4953-b414-b5b848a36817")
    public static final String Probability = Prefix + SysMLStereotypes.PROBABILITY;

    @objid ("30eb172e-2660-4cb7-ba8e-78383f502c47")
    public static final String Problem = Prefix + SysMLNoteTypes.MODELELEMENT_PROBLEM;

    @objid ("e810f3d2-f08c-4ffa-8c47-f46ac94d2381")
    public static final String QuantityKind = Prefix + SysMLStereotypes.QUANTITYKIND;

    @objid ("4d69f8e2-be3c-48e0-8590-b6b388935ce3")
    public static final String RateActivityEdge = Prefix + SysMLStereotypes.RATE_ACTIVITYEDGE;

    @objid ("d7bdc184-4102-4ded-a70f-05aad977972d")
    public static final String RateParameter = Prefix + SysMLStereotypes.RATE_PARAMETER;

    @objid ("0bf0779a-f800-42ae-9210-0f5ffe190277")
    public static final String Rationale = Prefix + SysMLNoteTypes.MODELELEMENT_RATIONALE;

    @objid ("fb90924f-4036-4281-b95a-47f675222774")
    public static final String Trace = Prefix + "Trace";

    @objid ("05d6d836-4e8c-4762-aa56-1bfebcd1bccd")
    public static final String Unit = Prefix + SysMLStereotypes.UNIT;

    @objid ("559c1afd-95c8-40d2-a82a-c5c90f96c5e1")
    public static final String ValueType = Prefix + SysMLStereotypes.VALUETYPE;

    @objid ("757e1827-55a2-4f9f-913d-21765be94374")
    public static final String View = Prefix + SysMLStereotypes.VIEW;

    @objid ("baccee45-5dff-45b2-8b34-596344c46579")
    public static final String ViewPoint = Prefix + SysMLStereotypes.VIEWPOINT;

//
//    @objid ("57d6ba2e-9754-4167-a1a5-dfc0d94fdb06")
//    public static final String UMLAggregation = Prefix + "UMLAggregation";
//
//    @objid ("500443c6-1851-4b6c-9beb-994a658ca95a")
//    public static final String UMLAssociation = Prefix + "UMLAssociation";
    @objid ("a382cf29-e43e-46f1-b02f-88d13d3372e7")
    public static final String UMLAttribute = Prefix + "UMLAttribute";

//
//    @objid ("d31a0524-0f93-483e-9758-9419e2d2b41c")
//    public static final String UMLComposition = Prefix + "UMLComposition";
    @objid ("794f9bb0-b5b6-45a1-bcb7-52810d783778")
    public static final String UMLGeneralization = Prefix + "UMLGeneralization";

    @objid ("a21345e7-7d06-4194-8cf9-be92a7b98be3")
    public static final String UMLOperation = Prefix + "UMLOperation";

    @objid ("1cbea33f-4bfb-4104-ac3e-04eaa2edf23c")
    public static final String UMLSmartGeneralization = Prefix + "UMLSmartGeneralization";

}
