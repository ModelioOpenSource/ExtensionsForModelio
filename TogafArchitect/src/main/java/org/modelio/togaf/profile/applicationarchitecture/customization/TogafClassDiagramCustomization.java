package org.modelio.togaf.profile.applicationarchitecture.customization;

import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.api.modelio.diagram.DefaultDiagramCustomizer;

public class TogafClassDiagramCustomization extends DefaultDiagramCustomizer {

	@Override
	public boolean keepBasePalette() {
		return true;
	}

	@Override
	public void fillPalette(PaletteRoot paletteRoot) {

	}

}
