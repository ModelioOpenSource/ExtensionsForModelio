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
@objid ("3f9340d8-a0a5-49f0-9eeb-479de83afe43")
public class ParametricDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    @objid ("2c4fb794-0269-403f-a571-e4a12f9a7dba")
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

    @objid ("82a7780a-d179-49b2-ba9c-d688c357949c")
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

    @objid ("9c3f4109-5ea4-4334-90d5-102e662c6906")
    @Override
    public boolean keepBasePalette() {
        return false;
    }

    @objid ("b3aa0851-47b7-4d34-b5a7-362a51c3b065")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("8273585b-299f-4e94-bfa2-3cfe65573dc9")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
