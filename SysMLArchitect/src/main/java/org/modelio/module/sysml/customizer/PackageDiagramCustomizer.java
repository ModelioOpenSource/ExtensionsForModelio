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
 * Configures the diagram palette.
 */
@objid ("404a827b-9378-4a34-8f64-06536c96d719")
public class PackageDiagramCustomizer extends SysMLDiagramCustomizer implements IDiagramCustomizer {
    @objid ("d7fa2c87-20b7-47df-bced-e02373e0ae35")
    private PaletteEntry createDependencyGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("StatikPaletteGroup.Common"), null);
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Conform));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Allocate));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Dependency));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Traceability));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.RelatedDiagramLink));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("e473ff22-95fd-4af3-add9-21d5aa8c09a7")
    private PaletteEntry createInstanceGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("StatikPaletteGroup.Instance"),
                null);
        
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ValueType));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Datatype));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Enumeration));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.EnumerationLiteral));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.QuantityKind));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Unit));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Instance));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AttributeLink));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DelegateLink));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InstanceLink));
        
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("53e6db21-6b78-4928-b6ce-6869e2edfcd0")
    private PaletteEntry createImportGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("StatikPaletteGroup.Import"), null);
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Use));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ElementImport));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.PackageImport));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.PackageMerge));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("1c1498a4-445e-42bd-af16-e9494bd7144d")
    private PaletteEntry createPackageGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("StatikPaletteGroup.Package"), null);
        
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.View)); 
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Package)); 
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ViewPoint));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Block));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ConstraintBlock));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Class));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.FlowSpecification));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Interface));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.UMLAttribute));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Operation));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Association));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Aggregation));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Composition));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.UMLSmartGeneralization));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.UMLGeneralization));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InterfaceRealization));
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("cdd5a4fa-6794-4d78-be46-149501ebc137")
    @Override
    public void fillPalette(PaletteRoot paletteRoot) {
        IDiagramService toolRegistry = SysMLModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
        
        final PaletteDrawer commonGroup = new PaletteDrawer("Default", null);
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);       
        commonGroup.add(new SelectionToolEntry());
        commonGroup.add(new MarqueeToolEntry());     
        paletteRoot.add(commonGroup);
        
        paletteRoot.add(this.createPackageGroup(toolRegistry));
        paletteRoot.add(this.createInstanceGroup(toolRegistry));
        paletteRoot.add(this.createImportGroup(toolRegistry));
        paletteRoot.add(this.createInformationFlowGroup(toolRegistry));
        paletteRoot.add(this.createDependencyGroup(toolRegistry));
        paletteRoot.add(this.createDefaultNotesGroup(toolRegistry));
        paletteRoot.add(this.createDefaultFreeDrawingGroup(toolRegistry));
    }

    @objid ("acc771a9-3375-4d04-8534-51323687e03f")
    @Override
    public boolean keepBasePalette() {
        return false;
    }

    @objid ("abc292a7-7244-4d1e-b44f-614e14e6efb0")
    @Override
    public void initialize(IModule module, List<org.modelio.api.modelio.diagram.tools.PaletteEntry> tools, Map<String, String> hParameters, boolean keepBasePalette) {
    }

    @objid ("c49b2596-acc6-4b04-b267-b8dcd820ca9d")
    @Override
    public Map<String, String> getParameters() {
        return null;
    }

}
