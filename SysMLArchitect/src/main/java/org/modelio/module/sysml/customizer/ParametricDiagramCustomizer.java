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
 * This class handles the palette configuration of SysML parametric diagram
 * @author ebrosse
 */

public class ParametricDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        
        final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        commonGroup.add(new SelectionToolEntry());
        commonGroup.add(new MarqueeToolEntry());
        paletteRoot.add(commonGroup);
        
        paletteRoot.add(this.createInstanceGroup(toolRegistry));
        paletteRoot.add(this.createDefaultPortGroup(toolRegistry));
        paletteRoot.add(this.createInformationFlowGroup(toolRegistry));
        paletteRoot.add(this.createDefaultDependencyGroup(toolRegistry));
        paletteRoot.add(this.createDefaultNotesGroup(toolRegistry));
        paletteRoot.add(this.createDefaultFreeDrawingGroup(toolRegistry));
    }

    
    private PaletteEntry createInstanceGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Instance"), null);
        
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ConnectorProperty));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ConstraintProperty));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ParticipantPropertyBindableInstance));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Instance));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.DistributedProperty));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.FlowProperty));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.UMLAttribute));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AttributeLink));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DelegateLink));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InstanceLink));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    
    @Override
    public boolean keepBasePalette() {
        return false;
    }

    
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
