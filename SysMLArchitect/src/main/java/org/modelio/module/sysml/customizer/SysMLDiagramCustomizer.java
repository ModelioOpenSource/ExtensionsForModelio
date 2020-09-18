package org.modelio.module.sysml.customizer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.module.sysml.i18n.I18nMessageService;
import org.modelio.module.sysml.utils.IDiagramCustomizerPredefinedField;
import org.modelio.module.sysml.utils.ISysMLCustomizerPredefinedField;

/**
 * This class handles the common palette configuration of all SysML diagrams
 * @author ebrosse
 */
@objid ("80264859-a5a6-4827-b40f-2c640ae9b469")
public class SysMLDiagramCustomizer {
    @objid ("9995616c-ab85-4ed4-a1fd-618f50837fc6")
    protected PaletteEntry createDefaultDependencyGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Dependency"), null);
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Allocate));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Conform));
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Dependency));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Traceability));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.RelatedDiagramLink));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("a1543168-3cb8-49d4-9fdf-306513ea8c37")
    protected PaletteEntry createDefaultFreeDrawingGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Freedrawing"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingRectangle));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingEllipse));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingText));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingLine));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        return group;
    }

    @objid ("a957e243-a4bc-4e80-a0fa-32c785bc2b97")
    protected PaletteEntry createDefaultNotesGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.NotesAndConstraints"), null);
        
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Problem));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.Rationale));
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Note));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Constraint));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ExternDocument));
        group.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        return group;
    }

    @objid ("4dd79d63-c345-4c0b-9308-9f0f30b6f38e")
    protected PaletteEntry createDefaultInstanceGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Instance"),
                null);
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Instance));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.AttributeLink));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DelegateLink));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InstanceLink));
        
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("ad1e0d7f-8bd8-4f46-9d28-2ebe493e4b61")
    protected PaletteEntry createDefaultImportGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Import"), null);
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Use));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ElementImport));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.PackageImport));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.PackageMerge));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("f356a493-c95a-4c43-97da-4c1703944a1b")
    protected PaletteEntry createDefaultInformationFlowGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.InformationFlow"),
                null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationFlow));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationFlowRealized));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationItem));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("bbe4650f-6783-41e7-a248-5b4a8de10672")
    protected PaletteEntry createDefaultPortGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Port"),
                null);
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.FlowPort));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Port));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ProvidedInterface));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.RequiredInterface));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    @objid ("36d7839b-e68d-4201-aa06-af8bec5311c5")
    protected PaletteEntry createInformationFlowGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.InformationFlow"),
                null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationFlow));
        group.add(toolRegistry.getRegisteredTool(ISysMLCustomizerPredefinedField.ItemFlow));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationFlowRealized));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationItem));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

}
