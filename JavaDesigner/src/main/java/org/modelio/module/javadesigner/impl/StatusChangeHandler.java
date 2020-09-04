package org.modelio.module.javadesigner.impl;

import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.event.IStatusChangeEvent;
import org.modelio.api.modelio.model.event.IStatusChangeListener;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.editor.EditorManager;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class StatusChangeHandler implements IStatusChangeListener {
    @Override
    public void statusChanged(final IModelingSession session, final IStatusChangeEvent event) {
        JavaDesignerModule module = JavaDesignerModule.getInstance();
        final EditorManager editorManager = EditorManager.getInstance ();
        
        // In release mode, all editors are read only
        final boolean isReleaseMode = JavaDesignerUtils.isReleaseMode(module);
        
        // Get the current encoding
        final String encoding = module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.ENCODING);
        
        Display.getDefault().asyncExec (new Runnable () {
            @Override
            public void run () {
                for (MObject element : event.getStatusChanged()) {
                    boolean isReadOnly = isReleaseMode || !element.getStatus ().isModifiable ();
                    editorManager.updateStatusForElement (element, isReadOnly, encoding);
                }
            }
        });
    }

}
