package org.modelio.togaf.profile.applicationarchitecture.customization;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.togaf.i18n.Messages;
import org.modelio.togaf.impl.TogafArchitectModule;

public class TogafSystemUseCaseDiagramCustomization extends DefaultDiagramCustomizer {

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
		paletteRoot.add(1,ActorGroup);
		
	}

}
