package org.modelio.module.sysml.propertypage;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.sysml.api.ISysMLPeerModule;
import org.modelio.module.sysml.api.SysMLStereotypes;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.ModelUtils;
import org.modelio.module.sysml.utils.Utils;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * @author ebrosse
 */
@objid ("e395d0d5-2e97-4651-88c4-cf4b95ecc47d")
public class SysMLPropertyManager {
    /**
     * @param MObject
     * : the selected MObject
     * 
     * @param row : the row of the property
     * @param value : the new value of the prpoperty
     * @return the new value of the row
     */
    @objid ("5b6aae85-6d2f-493d-a4e2-d6d6df8b531c")
    public int changeProperty(ModelElement element, int row, String value) {
        IPropertyContent propertypage = null;
        IMetamodelExtensions extensions = SysMLModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions();
        
        int currentRow = row;
        
        List<Stereotype> sterList = Utils.computePropertyList(element);
        MMetamodel metamodel = SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel();
        
        for (Stereotype ster : sterList) {
        
            // Block property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BLOCK,
                    metamodel.getMClass(Class.class)))) {
                propertypage = new BlockPropertyPage();
            }
        
            // ConnectorProperty property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONNECTORPROPERTY,
                    metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new ConnectorPropertyPropertyPage();
            }
        
            // FlowPort property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPORT,
                    metamodel.getMClass(Port.class)))) {
                propertypage = new FlowPortPropertyPage();
            }
        
            // FlowProperty property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPROPERTY,
                    metamodel.getMClass(Feature.class)))) {
                propertypage = new FlowPropertyPropertyPage();
            }
        
            // ItemFlow property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ITEMFLOW,
                    metamodel.getMClass(InformationFlow.class)))) {
                propertypage = new ItemFlowPropertyPage();
            }
        
            // NestedConnector property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.NESTEDCONNECTOREND,
                    metamodel.getMClass(ConnectorEnd.class)))) {
                propertypage = new NestedConnectorEndPropertyPage();
            }
        
            // Participant bindable instance property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME,
                    SysMLStereotypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE, metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new ParticipantPropertyPage();
        
            }
        
            // Probability property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PROBABILITY,
                    metamodel.getMClass(ActivityEdge.class)))) {
                propertypage = new ProbabilityPropertyPage();
        
            }
        
            // QuantityKind property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.QUANTITYKIND,
                    metamodel.getMClass(Instance.class)))) {
                propertypage = new QuantityKindPropertyPage();
        
            }
        
            // RateActivityEdge property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE_ACTIVITYEDGE,
                    metamodel.getMClass(ActivityEdge.class)))) {
                propertypage = new RateActivityEdgePropertyPage();
            }
        
            // RateActivityEdge property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE_PARAMETER,
                    metamodel.getMClass(Parameter.class)))) {
                propertypage = new RateParameterPropertyPage();
            }
        
            // Unit property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNIT,
                    metamodel.getMClass(Instance.class)))) {
                propertypage = new UnitPropertyPage();
            }
        
            // Value type property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VALUETYPE,
                    metamodel.getMClass(DataType.class)))) {
                propertypage = new ValueTypePropertyPage();
            }
        
            // View property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEW,
                    metamodel.getMClass(Package.class)))) {
                propertypage = new ViewPropertyPage();
        
            }
        
            // Viewpoint PropertyDefinition page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEWPOINT,
                    metamodel.getMClass(Class.class)))) {
                propertypage = new ViewpointPropertyPage();
        
            }
        
            // Constraintproperty property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONSTRAINTPROPERTY,
                    metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new ConstraintPropertyPropertyPage();
            }
        
            if (propertypage != null) {
                propertypage.changeProperty(element, currentRow, value);
                currentRow = currentRow - ster.getDefinedTagType().size();
                propertypage = null;
            }
        }
        // Allocated property page
        if (ModelUtils.isAllocated(element)) {
            currentRow = currentRow - 2;
        }
        
        if (element instanceof Pin) {
            // Pin property page
            propertypage = new PinPropertyPage();
            propertypage.changeProperty(element, currentRow, value);
            currentRow = currentRow - 1;
        }
        
        else if (element instanceof Port) {
            // Port property page
            propertypage = new PortPropertyPage();
            propertypage.changeProperty(element, currentRow, value);
            currentRow = currentRow - 1;
        }
        return currentRow;
    }

    /**
     * build the property table of the selected Elements
     * @param MObject
     * : the selected element
     * 
     * @param table : the property table
     */
    @objid ("43e3c9f8-93ae-4d8f-857d-cac725f7c1eb")
    public void update(ModelElement element, IModulePropertyTable table) {
        IMetamodelExtensions extensions = SysMLModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions();
        IPropertyContent propertypage = null;
        MMetamodel metamodel = SysMLModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel();
        
        List<Stereotype> sterList = Utils.computePropertyList(element);
        
        for (Stereotype ster : sterList) {
        
            // Block property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.BLOCK,
                    metamodel.getMClass(Class.class)))) {
                propertypage = new BlockPropertyPage();
            }
        
            // ConnectorProperty property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONNECTORPROPERTY,
                    metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new ConnectorPropertyPropertyPage();
            }
        
            // FlowPort property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPORT,
                    metamodel.getMClass(Port.class)))) {
                propertypage = new FlowPortPropertyPage();
            }
        
            // FlowProperty property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.FLOWPROPERTY,
                    metamodel.getMClass(Feature.class)))) {
                propertypage = new FlowPropertyPropertyPage();
            }
        
            // ItemFlow property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.ITEMFLOW,
                    metamodel.getMClass(InformationFlow.class)))) {
                propertypage = new ItemFlowPropertyPage();
            }
        
            // NestedConnector property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.NESTEDCONNECTOREND,
                    metamodel.getMClass(ConnectorEnd.class)))) {
                propertypage = new NestedConnectorEndPropertyPage();
            }
        
            // Participant bindable instance property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME,
                    SysMLStereotypes.PARTICIPANTPROPERTY_BINDABLEINSTANCE, metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new ParticipantPropertyPage();
        
            }
        
            // Probability property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.PROBABILITY,
                    metamodel.getMClass(ActivityEdge.class)))) {
                propertypage = new ProbabilityPropertyPage();
        
            }
        
            // QuantityKind property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.QUANTITYKIND,
                    metamodel.getMClass(Instance.class)))) {
                propertypage = new QuantityKindPropertyPage();
        
            }
        
            // RateActivityEdge property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE_ACTIVITYEDGE,
                    metamodel.getMClass(ActivityEdge.class)))) {
                propertypage = new RateActivityEdgePropertyPage();
            }
        
            // RateActivityEdge property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.RATE_PARAMETER,
                    metamodel.getMClass(Parameter.class)))) {
                propertypage = new RateParameterPropertyPage();
            }
        
            // Unit property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.UNIT,
                    metamodel.getMClass(Instance.class)))) {
                propertypage = new UnitPropertyPage();
            }
        
            // Value type property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VALUETYPE,
                    metamodel.getMClass(DataType.class)))) {
                propertypage = new ValueTypePropertyPage();
            }
        
            // View property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEW,
                    metamodel.getMClass(Package.class)))) {
                propertypage = new ViewPropertyPage();
        
            }
        
            // Viewpoint PropertyDefinition page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.VIEWPOINT,
                    metamodel.getMClass(Class.class)))) {
                propertypage = new ViewpointPropertyPage();
        
            }
        
            // Constraintproperty property page
            if (ster.equals(extensions.getStereotype(ISysMLPeerModule.MODULE_NAME, SysMLStereotypes.CONSTRAINTPROPERTY,
                    metamodel.getMClass(BindableInstance.class)))) {
                propertypage = new ConstraintPropertyPropertyPage();
            }
        
        
            if (propertypage != null) {
                propertypage.update(element, table);
                propertypage = null;
            }
        
        }
        
        
        // Allocated property page
        if (ModelUtils.isAllocated(element)) {
            propertypage = new AllocatedPropertyPage();
            propertypage.update(element, table);
        }
        
        
        // Pin property page
        if (element instanceof Pin) {
            propertypage = new PinPropertyPage();
            propertypage.update(element, table);
        }
        
        // Port property page
        else if (element instanceof Port) {
            propertypage = new PortPropertyPage();
            propertypage.update(element, table);
        }
        
        propertypage = new CommonPropertyPage();
        propertypage.update(element, table);
    }

}
