package org.modelio.togaf.profile.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

public abstract class TogafDiagramWizardContributor implements ITogafDiagramWizardContributor {


	private String label;

	private String information;

	private String helpUrl;

	private String details;

	private Image icon;

	private List<ElementScope> scopes = new ArrayList<>();

	private Map<String, String> params = new HashMap<>();

	private IModule module;

	private ImageDescriptor previewImageDescriptor;

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getInformation() {
		return this.information;
	}

	@Override
	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public IModule getModule() {
		return this.module;
	}

	@Override
	public List<ElementScope> getScopes() {
		return this.scopes;
	}

	@Override
	public void setModule(IModule module) {
		this.module = module;
	}

	@Override
	public void setParameters(Map<String, String> params) {
		this.params = params;
	}

	public String getStyle(){
		return this.params.get("style");
	}

	@Override
	public void setScopes(List<ElementScope> scopes) {
		this.scopes = scopes;
	}


	@Override
	public String getHelpUrl() {
		return this.helpUrl;
	}

	@Override
	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	@Override
	public String getDetails() {
		return this.details;
	}

	@Override
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public Image getIcon() {
		return this.icon;
	}

	@Override
	public void setIcon(Image icon) {
		this.icon = icon;
	}

	@Override
	public abstract AbstractDiagram actionPerformed(ModelElement diagramContext, String diagramName, String diagramDescription);

	@Override
	public abstract boolean accept(MObject element);

	@Override
    public final ImageDescriptor getPreviewImage() {
        return this.previewImageDescriptor;
    }

    @Override
	public final void setPreviewImage(ImageDescriptor previewDescriptor) {
        this.previewImageDescriptor = previewDescriptor;
    }

	@Override
	public void dispose() {
		if (this.icon != null) {
			this.icon.dispose();
		}
	}

	@Override
	public IPanelProvider getWizardPanel() {
		return null;
	}

}
