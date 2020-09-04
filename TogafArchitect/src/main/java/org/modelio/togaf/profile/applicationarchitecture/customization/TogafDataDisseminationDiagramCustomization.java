package org.modelio.togaf.profile.applicationarchitecture.customization;

import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafDataDisseminationDiagramCustomization extends DefaultDiagramCustomizer {

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

		final PaletteDrawer StructureGroup = new PaletteDrawer(Messages.getString("PALETTE_Structure"), null);
		StructureGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		StructureGroup.add(toolRegistry.getRegisteredTool("InformationDomainDiagramCommande"));
		paletteRoot.add(StructureGroup);

		final PaletteDrawer ActorGroup = new PaletteDrawer(Messages.getString("PALETTE_Entity"), null);
		ActorGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ActorGroup.add(toolRegistry.getRegisteredTool("BusinessEntityDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("BusinessAttributeDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("BusinessOperationDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("BusinessDataTypeDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafEnumerationDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("UMLEnumerationLitteralDiagramCommande"));
		paletteRoot.add(ActorGroup);

		final PaletteDrawer linkGroup = new PaletteDrawer(Messages.getString("PALETTE_Link"), null);
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_ASSOCIATION"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_COMPOSITION"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_SMARTGENERALIZATION"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_ELEMENTIMPORT"));
		linkGroup.add(toolRegistry.getRegisteredTool("CREATE_PACKAGEIMPORT"));
		linkGroup.add(toolRegistry.getRegisteredTool("IOFlowDiagramCommande"));
		linkGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
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
