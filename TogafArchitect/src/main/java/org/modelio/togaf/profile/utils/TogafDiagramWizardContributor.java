package org.modelio.togaf.profile.utils;

import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MStatus;

public abstract class TogafDiagramWizardContributor extends AbstractDiagramWizardContributor {

    protected String getStyle() {
        return getParameters().get("style");
    }

    @Override
    public void dispose() {
        // Nothing to do
    }

    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        MStatus status = owner.getStatus();
        return status.isModifiable() || (owner.getMClass().isCmsNode() && status.isCmsManaged());
    }

}
