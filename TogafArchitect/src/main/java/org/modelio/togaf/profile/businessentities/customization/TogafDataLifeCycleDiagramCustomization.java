package org.modelio.togaf.profile.businessentities.customization;

import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;

public class TogafDataLifeCycleDiagramCustomization extends DefaultDiagramCustomizer {

	@Override
	public boolean keepBasePalette() {
		return true;
	}

	@Override
	public void fillPalette(PaletteRoot paletteRoot) {

	}

}
