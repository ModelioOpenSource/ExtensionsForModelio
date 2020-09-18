package org.modelio.module.sysml.customizer;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.IDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.module.IModule;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.impl.SysMLModule;
import org.modelio.module.sysml.utils.IDiagramCustomizerPredefinedField;
import org.modelio.module.sysml.utils.ISysMLCustomizerPredefinedField;

/**
 * Activity diagram palette configurer.
 */
@objid ("fa636582-392b-4d0f-aa28-3c36bd731092")
public class SysMLActivityDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    @objid ("7f575a0b-ca1e-439c-9fbd-6f9ec1138a5f")
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);       
        commonGroup.add(new SelectionToolEntry());
        commonGroup.add(new MarqueeToolEntry());     
        paletteRoot.add(commonGroup);
        
        paletteRoot.add(this.createControlGroup(toolRegistry));
        paletteRoot.add(this.createEventGroup(toolRegistry));
        paletteRoot.add(this.createDataGroup(toolRegistry));
        paletteRoot.add(this.createFlowGroup(toolRegistry));
        paletteRoot.add(this.createPartitionGroup(toolRegistry));
        paletteRoot.add(this.createDefaultInformationFlowGroup(toolRegistry));
        paletteRoot.add(this.createDependencyGroup(toolRegistry));
        paletteRoot.add(this.createDefaultNotesGroup(toolRegistry));
        paletteRoot.add(this.createDefaultFreeDrawingGroup(toolRegistry));
    }

    @objid ("12d8e37e-a0b7-4d3f-8f5f-2e3f7a51388e")
    private PaletteEntry createPartitionGroup(final IDiagramService toolRegistry) {
        PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("ActivityPaletteGroup.Partition"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ActivityPartitionVerticalContainer));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ActivityPartitionHorizontalContainer));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ActivityPartitionSibling));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ActivityPartitionInner));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    /**
     * Creates the note and constraint and dependency group.
     * @param imageService
     * service used to get metaclasses bitmaps.
     * 
     * @return The created group.
     */
    @objid ("0c9b8fef-bb46-4f20-b635-0f72a5023b68")
    private PaletteDrawer createDependencyGroup(final IDiagramService toolRegistry) {
        // common group
        PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("ActivityPaletteGroup.Common"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Dependency));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Traceability));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.RelatedDiagramLink));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("26c2a201-dade-46b5-a4f5-b3e8030e0980")
    private PaletteEntry createEventGroup(final IDiagramService toolRegistry) {
        PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("ActivityPaletteGroup.Event"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.SendSignalAction));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AcceptCallEventAction));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AcceptChangeEventAction));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AcceptSignalAction));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AcceptTimeEventAction));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("a79100e9-0ed6-46b0-b7a1-488d5e094282")
    private PaletteEntry createFlowGroup(final IDiagramService toolRegistry) {
        PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("ActivityPaletteGroup.Flow"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.SmartFlow));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ContinuousActivityEdge));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.DiscreteActivityEdge));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Probability));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.RateActivityEdge));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ControlFlow));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ObjectFlow));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ExceptionHandler));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("5374542f-11ad-4e15-9653-c7b26187cb52")
    private PaletteEntry createDataGroup(final IDiagramService toolRegistry) {
        PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("ActivityPaletteGroup.Data"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InstanceNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DataStoreNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.CentralBufferNode));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ContinuousParameter));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.DiscreteParameter));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Optional));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.RateParameter));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ActivityParameterNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InputPin));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.OutputPin));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ValuePin));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ExpansionNodeInput));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ExpansionNodeOutput));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("afeb25b4-e27f-4d4b-b8de-71bff39eff14")
    private PaletteEntry createControlGroup(final IDiagramService toolRegistry) {
        PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("ActivityPaletteGroup.Control"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.OpaqueAction));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.CallBehaviorAction));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.CallOperationAction));
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ConditionalNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Clause));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.LoopNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.StructuredNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ExpansionRegion));
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InitialNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ActivityFinalNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.FlowFinalNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ForkJoinNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DecisionMergeNode));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InterruptibleActivityRegion));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("3cd9010e-87d0-4ef8-8282-2bcb4802e338")
    @Override
    public boolean keepBasePalette() {
        return false;
    }

    @objid ("ebb75955-8e29-4971-9aa0-2f1f4cb7a6a1")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("b703bea2-f21a-4ffe-94ee-46127b33a7db")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
