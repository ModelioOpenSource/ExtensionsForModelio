package org.modelio.module.javadesigner.impl;

import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeListener;
import org.modelio.module.javadesigner.editor.EditorManager;

public class ModelChangeListener implements IModelChangeListener {
    @Override
    public void modelChanged(final IModelingSession session, final IModelChangeEvent event) {
        // Update editors if necessary
        if(event.getUpdateEvents().size() > 0 || event.getDeleteEvents().size() > 0) {
            EditorManager.getInstance ().updateEditorsFromElements ();
        }

    }

}
