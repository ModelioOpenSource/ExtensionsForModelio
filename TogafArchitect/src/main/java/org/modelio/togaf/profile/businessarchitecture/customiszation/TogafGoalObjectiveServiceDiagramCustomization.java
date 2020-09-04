package org.modelio.togaf.profile.businessarchitecture.customiszation;

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafGoalObjectiveServiceDiagramCustomization extends DefaultDiagramCustomizer {

	@Override
	public boolean keepBasePalette() {
		return false;
	}

	@Override
	public void fillPalette(PaletteRoot paletteRoot) {
		IDiagramService toolRegistry = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService();

		final PaletteDrawer commonGroup = new PaletteDrawer(Messages.getString("PALETTE_Default"), null);
		commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		commonGroup.add(new SelectionToolEntry());
		commonGroup.add(new MarqueeToolEntry());
		paletteRoot.add(commonGroup);

		final PaletteDrawer ProcessGroup = new PaletteDrawer(Messages.getString("PALETTE_Service"), null);
		ProcessGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ProcessGroup.add(toolRegistry.getRegisteredTool("TogafFunctionDiagramCommande"));
		ProcessGroup.add(toolRegistry.getRegisteredTool("TogafBusinessServiceDiagramCommande"));
		ProcessGroup.add(toolRegistry.getRegisteredTool("TogafBusinessCapabilityDiagramCommande"));
		ProcessGroup.add(toolRegistry.getRegisteredTool("TogafISServiceDiagramCommande"));
		ProcessGroup.add(toolRegistry.getRegisteredTool("TogafServiceContractDiagramCommande"));
		ProcessGroup.add(toolRegistry.getRegisteredTool("TogafBusinessOperationDiagramCommande"));
		paletteRoot.add(ProcessGroup);

		final PaletteDrawer LinkGroup = new PaletteDrawer(Messages.getString("PALETTE_Link"), null);
		LinkGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		LinkGroup.add(toolRegistry.getRegisteredTool("ContractOfDiagramCommand"));
		paletteRoot.add(LinkGroup);

		final PaletteDrawer AnnotationGroup = new PaletteDrawer(Messages.getString("PALETTE_Common"), null);
		AnnotationGroup.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		AnnotationGroup.add(toolRegistry.getRegisteredTool("CREATE_NOTE"));
		AnnotationGroup.add(toolRegistry.getRegisteredTool("CREATE_CONSTRAINT"));
		AnnotationGroup.add(toolRegistry.getRegisteredTool("CREATE_DEPENDENCY"));
		AnnotationGroup.add(toolRegistry.getRegisteredTool("CREATE_TRACEABILITY"));
		AnnotationGroup.add(toolRegistry.getRegisteredTool("CREATE_RELATED_DIAGRAM_LINK"));
		AnnotationGroup.add(toolRegistry.getRegisteredTool("MigrationDiagramCommand"));
		paletteRoot.add(AnnotationGroup);

    	final PaletteDrawer DrawingGroup = new PaletteDrawer(org.modelio.togaf.i18n.Messages.getString("Ui.Diagram.Drawing"));
		DrawingGroup.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_RECTANGLE"));
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_ELLIPSE"));
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_TEXT"));
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_LINE"));
		paletteRoot.add(DrawingGroup);

	}

}
