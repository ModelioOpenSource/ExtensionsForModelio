package org.modelio.togaf.profile.applicationarchitecture.customization;

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafApplicationCommunicationDiagramCustomization extends DefaultDiagramCustomizer {

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

		final PaletteDrawer systemGroup = new PaletteDrawer(Messages.getString("PALETTE_System"), null);
		systemGroup.add(toolRegistry.getRegisteredTool("SystemApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("TogafSystemFederationDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("TogafEnterpriseSystemDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("TogafApplicationDiagramCommande"));

		systemGroup.add(toolRegistry.getRegisteredTool("EntityApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("InteractionApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("ProcessApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("ServiceApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("PublicApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("IntermediaryApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("DataBaseApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("UtilityApplicationComponentDiagramCommande"));
		systemGroup.add(toolRegistry.getRegisteredTool("TogafApplicationComponentInstanceDiagramCommande"));

		paletteRoot.add(systemGroup);

		final PaletteDrawer collGroup = new PaletteDrawer("Collaboration", null);
		collGroup.add(toolRegistry.getRegisteredTool("TogafApplicationCollaborationDiagramCommande"));
		collGroup.add(toolRegistry.getRegisteredTool("CREATE_BINDABLEINSTANCE"));
		paletteRoot.add(collGroup);

		final PaletteDrawer ServiceGroup = new PaletteDrawer(Messages.getString("PALETTE_Service"), null);
		ServiceGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ServiceGroup.add(toolRegistry.getRegisteredTool("ProvidedServiceAccessDiagramCommande"));
		ServiceGroup.add(toolRegistry.getRegisteredTool("RequiredServiceAccessDiagramCommande"));
		paletteRoot.add(ServiceGroup);

		final PaletteDrawer DataGroup = new PaletteDrawer(Messages.getString("PALETTE_Data"), null);
		DataGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);

		DataGroup.add(toolRegistry.getRegisteredTool("ServiceDataDiagramCommande"));
		DataGroup.add(toolRegistry.getRegisteredTool("ServiceDataFragmentDiagramCommande"));
		DataGroup.add(toolRegistry.getRegisteredTool("CREATE_ATTRIBUTE"));
		DataGroup.add(toolRegistry.getRegisteredTool("CREATE_OPERATION"));

		paletteRoot.add(DataGroup);

		final PaletteDrawer linkGroup = new PaletteDrawer(Messages.getString("PALETTE_Link"), null);
		linkGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_ASSOCIATION"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_COMPOSITION"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_CONNECTOR"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_SMARTGENERALIZATION"));
		linkGroup.add(toolRegistry.getRegisteredTool("TogafConsumesDiagramCommande"));
		linkGroup.add(toolRegistry.getRegisteredTool("ContractOfDiagramCommand"));
		linkGroup.add(toolRegistry.getRegisteredTool("IOFlowDiagramCommande"));
		linkGroup.add(toolRegistry.getRegisteredTool("ComponentRealizationDiagramCommande"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_INFORMATIONFLOW"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_INFORMATIONFLOWNODE"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_ELEMENTIMPORT"));
		paletteRoot.add(linkGroup);

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
