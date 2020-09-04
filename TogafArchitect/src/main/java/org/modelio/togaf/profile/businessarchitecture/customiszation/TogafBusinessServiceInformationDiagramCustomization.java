package org.modelio.togaf.profile.businessarchitecture.customiszation;

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafBusinessServiceInformationDiagramCustomization extends DefaultDiagramCustomizer {

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

		final PaletteDrawer ServiceGroup = new PaletteDrawer(Messages.getString("PALETTE_Service"), null);
		ServiceGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ServiceGroup.add(toolRegistry.getRegisteredTool("TogafFunctionDiagramCommande"));
		ServiceGroup.add(toolRegistry.getRegisteredTool("TogafBusinessServiceDiagramCommande"));
		ServiceGroup.add(toolRegistry.getRegisteredTool("TogafBusinessCapabilityDiagramCommande"));
		ServiceGroup.add(toolRegistry.getRegisteredTool("TogafServiceContractDiagramCommande"));
		ServiceGroup.add(toolRegistry.getRegisteredTool("TogafBusinessOperationDiagramCommande"));
		ServiceGroup.add(toolRegistry.getRegisteredTool("TogafProductDiagramCommande"));
		paletteRoot.add(ServiceGroup);

		final PaletteDrawer serviceDataGroup = new PaletteDrawer(Messages.getString("PALETTE_ServiceData"), null);
		serviceDataGroup.add(toolRegistry.getRegisteredTool("ServiceDataDiagramCommande"));
		serviceDataGroup.add(toolRegistry.getRegisteredTool("ServiceDataFragmentDiagramCommande"));
		serviceDataGroup.add(toolRegistry.getRegisteredTool("CREATE_ATTRIBUTE"));
		paletteRoot.add(serviceDataGroup);

		final PaletteDrawer LinkGroup = new PaletteDrawer(Messages.getString("PALETTE_Link"), null);
		LinkGroup.add(toolRegistry.getRegisteredTool("ContractOfDiagramCommand"));
		LinkGroup.add(toolRegistry.getRegisteredTool("IOFlowDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_ASSOCIATION"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_COMPOSITION"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_SMARTGENERALIZATION"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_INFORMATIONFLOW"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_INFORMATIONFLOWNODE"));
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
