package org.modelio.togaf.profile.businessarchitecture.customiszation;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafUseCaseDiagramCustomization extends DefaultDiagramCustomizer {

	@Override
	public boolean keepBasePalette() {
		return true;
	}

	@Override
	public void fillPalette(PaletteRoot paletteRoot) {
		IDiagramService toolRegistry = TogafArchitectModule.getInstance().getModuleContext().getModelioServices().getDiagramService();

		final PaletteDrawer ActorGroup = new PaletteDrawer(Messages.getString("PALETTE_Actor_Role"), null);
		ActorGroup.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafExternalActorDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafInternalActorDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafExternalRoleDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("TogafInternalRoleDiagramCommande"));
		ActorGroup.add(toolRegistry.getRegisteredTool("ComponentRealizationDiagramCommande"));
		paletteRoot.add(ActorGroup);
		
    	final PaletteDrawer DrawingGroup = new PaletteDrawer(org.modelio.togaf.i18n.Messages.getString("Ui.Diagram.Drawing"));
		DrawingGroup.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_RECTANGLE"));
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_ELLIPSE"));
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_TEXT"));
		DrawingGroup.add(toolRegistry.getRegisteredTool("CREATE_DRAWING_LINE"));
		paletteRoot.add(DrawingGroup);
	}

}
