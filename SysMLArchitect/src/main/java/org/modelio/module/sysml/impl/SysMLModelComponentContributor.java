package org.modelio.module.sysml.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.mc.AbstractModelComponentContributor;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityPartition;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectNode;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Behavior;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLNoteTypes;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.api.SysMLTagTypes;
import org.modelio.vcore.smkernel.mapi.MObject;


public class SysMLModelComponentContributor extends AbstractModelComponentContributor {
    
    private IModelingSession session = SysMLModule.getInstance().getModuleContext().getModelingSession();

    
    public SysMLModelComponentContributor(IModule module) {
        super(module);
    }

    /**
     * Get a TagType from the metamodel extensions.
     */
    
    private TagType getTagType(java.lang.Class<? extends MObject> metaclass, String tagTypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getTagType(ISysMLPeerModule.MODULE_NAME, tagTypeName, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(metaclass));
    }

    /**
     * Get a Steretotype from the metamodel extensions.
     */
    
    private Stereotype getStereotype(java.lang.Class<? extends MObject> metaclass, String stereotypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getStereotype(ISysMLPeerModule.MODULE_NAME, stereotypeName, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(metaclass));
    }

    /**
     * Get a NoteType from the metamodel extensions.
     */
    
    private NoteType getNoteType(java.lang.Class<? extends MObject> metaclass, String noteTypeName) {
        IMetamodelExtensions metamodel = this.session.getMetamodelExtensions();
        return metamodel.getNoteType(ISysMLPeerModule.MODULE_NAME, noteTypeName, SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel().getMClass(metaclass));
    }

    
    @Override
    public Set<Stereotype> getDependencyStereotypes() {
        Set<Stereotype> stereotypes = new HashSet<>();

        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.ALLOCATE));
        stereotypes.add(getStereotype(ActivityPartition.class, SysMLStereotypes.ALLOCATEACTIVITYPARTITION));
        stereotypes.add(getStereotype(Connector.class, SysMLStereotypes.BINDINGCONNECTOR));
        stereotypes.add(getStereotype(Class.class, SysMLStereotypes.BLOCK));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.CONFORM));
        stereotypes.add(getStereotype(BindableInstance.class, SysMLStereotypes.CONNECTORPROPERTY));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.CONNECTORPROPERTYCONNECTOR));
        stereotypes.add(getStereotype(Class.class, SysMLStereotypes.CONSTRAINTBLOCK));
        stereotypes.add(getStereotype(BindableInstance.class, SysMLStereotypes.CONSTRAINTPROPERTY));
        stereotypes.add(getStereotype(Parameter.class, SysMLStereotypes.CONTINUOUS_PARAMETER));
        stereotypes.add(getStereotype(Behavior.class, SysMLStereotypes.CONTROLOPERATOR_BEHAVIOR));
        stereotypes.add(getStereotype(Operation.class, SysMLStereotypes.CONTROLOPERATOR_OPERATION));
        stereotypes.add(getStereotype(Instance.class, SysMLStereotypes.DIMENSION));
        stereotypes.add(getStereotype(ActivityEdge.class, SysMLStereotypes.DISCRETE_ACTIVITYEDGE));
        stereotypes.add(getStereotype(Parameter.class, SysMLStereotypes.DISCRETE_PARAMETER));
        stereotypes.add(getStereotype(Attribute.class, SysMLStereotypes.DISTRIBUTEDPROPERTY));
        stereotypes.add(getStereotype(Port.class, SysMLStereotypes.FLOWPORT));
        stereotypes.add(getStereotype(Interface.class, SysMLStereotypes.FLOWSPECIFICATION));
        stereotypes.add(getStereotype(InformationFlow.class, SysMLStereotypes.ITEMFLOW));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.ITEMPROPERTY));
        stereotypes.add(getStereotype(ConnectorEnd.class, SysMLStereotypes.NESTEDCONNECTOREND));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.NESTEDCONNECTORENDPROPERTYPATH));
        stereotypes.add(getStereotype(ObjectNode.class, SysMLStereotypes.NOBUFFER));
        stereotypes.add(getStereotype(Parameter.class, SysMLStereotypes.OPTIONAL));
        stereotypes.add(getStereotype(ObjectNode.class, SysMLStereotypes.OVERWRITE));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.PARTICIPANTPROPERTYEND));
        stereotypes.add(getStereotype(AssociationEnd.class, SysMLStereotypes.PARTICIPANTPROPERTY_ASSOCIATIONEND));
        stereotypes.add(getStereotype(BindableInstance.class, SysMLStereotypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE));
        stereotypes.add(getStereotype(ActivityEdge.class, SysMLStereotypes.PROBABILITY));
        stereotypes.add(getStereotype(Classifier.class, SysMLStereotypes.PROPERTYSPECIFICTYPE));
        stereotypes.add(getStereotype(Instance.class, SysMLStereotypes.QUANTITYKIND));
        stereotypes.add(getStereotype(ActivityEdge.class, SysMLStereotypes.RATE_ACTIVITYEDGE));
        stereotypes.add(getStereotype(Parameter.class, SysMLStereotypes.RATE_PARAMETER));
        //        stereotypes.add(getStereotype(ModelElement.class, SysMLStereotypes.REQUIREMENTRELATED));
        stereotypes.add(getStereotype(Behavior.class, SysMLStereotypes.TESTCASE_BEHAVIOR));
        stereotypes.add(getStereotype(Operation.class, SysMLStereotypes.TESTCASE_OPERATION));
        stereotypes.add(getStereotype(Instance.class, SysMLStereotypes.UNIT));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.UNITQUANTITYKIND));
        stereotypes.add(getStereotype(DataType.class, SysMLStereotypes.VALUETYPE));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.VALUETYPEQUANTITYKIND));
        stereotypes.add(getStereotype(Dependency.class, SysMLStereotypes.VALUETYPEUNIT));
        stereotypes.add(getStereotype(Package.class, SysMLStereotypes.VIEW));
        stereotypes.add(getStereotype(Class.class, SysMLStereotypes.VIEWPOINT));

        // Diagrams
        stereotypes.add(getStereotype(StaticDiagram.class, SysMLStereotypes.BLOCKDIAGRAM));
        stereotypes.add(getStereotype(StaticDiagram.class, SysMLStereotypes.INTERNALBLOCKDIAGRAM));
        stereotypes.add(getStereotype(StaticDiagram.class, SysMLStereotypes.PARAMETRICDIAGRAM));
        stereotypes.add(getStereotype(AbstractDiagram.class, SysMLStereotypes.SYSMLDIAGRAM));
        return stereotypes;
    }

    
    @Override
    public Set<MObject> getElements() {
        return Collections.emptySet();
    }

    
    @Override
    public Set<ExportedFileEntry> getFiles() {
        return Collections.emptySet();
    }

    
    @Override
    public Set<NoteType> getNoteTypes() {
        Set<NoteType> noteTypes = new HashSet<>();
        noteTypes.add(getNoteType(ModelElement.class, SysMLNoteTypes.MODELELEMENT_PROBLEM));
        noteTypes.add(getNoteType(ModelElement.class, SysMLNoteTypes.MODELELEMENT_RATIONALE));
        return noteTypes;
    }

    
    @Override
    public Set<TagType> getTagTypes() {
        Set<TagType> tagTypes = new HashSet<>();

        tagTypes.add(getTagType(Pin.class, SysMLTagTypes.PIN_ISSTREAM));
        //        tagTypes.add(getTagType(Port.class, SysMLTagTypes.PORT_ISCONJUGATED));
        //        tagTypes.add(getTagType(ModelElement.class, SysMLTagTypes.ALLOCATED_ALLOCATED_ALLOCATEDFROM));
        //        tagTypes.add(getTagType(ModelElement.class, SysMLTagTypes.ALLOCATED_ALLOCATED_ALLOCATEDTO));
        tagTypes.add(getTagType(Class.class, SysMLTagTypes.BLOCK_ISENCAPSULATED));
        tagTypes.add(getTagType(BindableInstance.class, SysMLTagTypes.CONNECTORPROPERTY_CONNECTOR));
        //        tagTypes.add(getTagType(Port.class, SysMLTagTypes.FLOWPORT_DIRECTION));
        tagTypes.add(getTagType(Port.class, SysMLTagTypes.FLOWPORT_ISATOMIC));
        tagTypes.add(getTagType(Feature.class, SysMLTagTypes.FLOWPROPERTY_DIRECTION));
        tagTypes.add(getTagType(InformationFlow.class, SysMLTagTypes.ITEMFLOW_ITEMPROPERTY));
        tagTypes.add(getTagType(ConnectorEnd.class, SysMLTagTypes.NESTEDCONNECTOREND_PROPERTYPATH));
        tagTypes.add(getTagType(AssociationEnd.class, SysMLTagTypes.PARTICIPANTPROPERTY_ASSOCIATIONEND_END));
        tagTypes.add(getTagType(BindableInstance.class, SysMLTagTypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE_END));
        tagTypes.add(getTagType(ActivityEdge.class, SysMLTagTypes.PROBABILITY_PROBABILITY));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.QUANTITYKIND_DESCRIPTION));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.QUANTITYKIND_DESCRIPTIONURI));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.QUANTITYKIND_SYMBOL));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.UNIT_DESCRIPTION));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.UNIT_DESCRIPTIONURI));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.UNIT_QUANTITYKIND));
        tagTypes.add(getTagType(Instance.class, SysMLTagTypes.UNIT_SYMBOL));
        tagTypes.add(getTagType(DataType.class, SysMLTagTypes.VALUETYPE_QUANTITYKIND));
        tagTypes.add(getTagType(DataType.class, SysMLTagTypes.VALUETYPE_UNIT));
        tagTypes.add(getTagType(Package.class, SysMLTagTypes.VIEW_VIEWPOINT));
        tagTypes.add(getTagType(Class.class, SysMLTagTypes.VIEWPOINT_CONCERNS));
        tagTypes.add(getTagType(Class.class, SysMLTagTypes.VIEWPOINT_LANGUAGES));
        tagTypes.add(getTagType(Class.class, SysMLTagTypes.VIEWPOINT_METHODS));
        tagTypes.add(getTagType(Class.class, SysMLTagTypes.VIEWPOINT_PURPOSE));
        tagTypes.add(getTagType(Class.class, SysMLTagTypes.VIEWPOINT_STAKEHOLDERS));
        return tagTypes;
    }

}
