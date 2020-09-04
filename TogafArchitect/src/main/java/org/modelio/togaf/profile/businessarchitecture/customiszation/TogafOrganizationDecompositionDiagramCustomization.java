package org.modelio.togaf.profile.businessarchitecture.customiszation;

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafOrganizationDecompositionDiagramCustomization extends DefaultDiagramCustomizer {

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

		final PaletteDrawer ContainerGroup = new PaletteDrawer(Messages.getString("PALETTE_Structure"), null);
		ContainerGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ContainerGroup.add(toolRegistry.getRegisteredTool("BusinessOrganizationDomainDiagramCommande"));
		ContainerGroup.add(toolRegistry.getRegisteredTool("TogafOrganizationUnitDiagramCommande"));
		paletteRoot.add(ContainerGroup);

		final PaletteDrawer LocationGroup = new PaletteDrawer(Messages.getString("PALETTE_Location"), null);
		LocationGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		LocationGroup.add(toolRegistry.getRegisteredTool("HeadquarterLocationDiagramCommande"));
		LocationGroup.add(toolRegistry.getRegisteredTool("TogafLocationDiagramCommande"));
		paletteRoot.add(LocationGroup);

		final PaletteDrawer ActorGroup = new PaletteDrawer(Messages.getString("PALETTE_Actor_Role"), null);
		ActorGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafExternalActorDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafInternalActorDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafExternalRoleDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafInternalRoleDiagramCommande"));
		paletteRoot.add(ActorGroup);

		final PaletteDrawer LinkGroup = new PaletteDrawer(Messages.getString("PALETTE_Link"), null);
		LinkGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		LinkGroup.add(toolRegistry.getRegisteredTool("TogafParticipantDecomposition"));
		LinkGroup.add(toolRegistry.getRegisteredTool("OwnerDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("AssumesDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("ParticipatesDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("ResponsibleDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("InitiatorDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CommunicatesDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("TogafConsumesDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("TogafLocalizationDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("TogafParticipantAllocationDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("IOFlowDiagramCommande"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_ASSOCIATION"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_COMPOSITION"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_INFORMATIONFLOW"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_INFORMATIONFLOWNODE"));
		LinkGroup.add(toolRegistry.getRegisteredTool("CREATE_PACKAGEIMPORT"));
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
