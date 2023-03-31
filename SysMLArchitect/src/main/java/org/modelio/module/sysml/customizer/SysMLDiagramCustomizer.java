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

public class SysMLDiagramCustomizer {
    
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

    
    protected PaletteEntry createDefaultFreeDrawingGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Freedrawing"), null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingRectangle));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingEllipse));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingText));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.DrawingLine));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        return group;
    }

    
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

    
    protected PaletteEntry createDefaultImportGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.Import"), null);
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.Use));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.ElementImport));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.PackageImport));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.PackageMerge));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    
    protected PaletteEntry createDefaultInformationFlowGroup(final IDiagramService toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(I18nMessageService.getString("SysMLPaletteGroup.InformationFlow"),
                null);
        
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationFlow));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationFlowRealized));
        group.add(toolRegistry.getRegisteredTool(IDiagramCustomizerPredefinedField.InformationItem));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
        return group;
    }

    
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
