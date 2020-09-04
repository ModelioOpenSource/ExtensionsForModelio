package org.modelio.module.javadesigner.dialog.modelselector;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.ModelUtils;

public class ModelTreeLabelProvider implements ILabelProvider {
    private Image javaImage;

    private Image buildTargetImage;

    public ModelTreeLabelProvider() {
        try {
            this.javaImage = new Image (null, ModelTreeLabelProvider.class.getResourceAsStream ("java.png"));
            this.buildTargetImage = new Image (null, ModelTreeLabelProvider.class.getResourceAsStream ("jarfile.png"));
        } catch (Exception e) {
            // error during image loading...
            this.javaImage = null;
            this.buildTargetImage = null;
        }
    }

    @Override
    public void addListener(final ILabelProviderListener arg0) {
        // No listener
    }

    @Override
    public void dispose() {
        if (this.javaImage != null) {
            this.javaImage.dispose ();
        }
        if (this.buildTargetImage != null) {
            this.buildTargetImage.dispose ();
        }
    }

    @Override
    public Image getImage(final Object p_Object) {
        if (p_Object instanceof Artifact) {
            return this.buildTargetImage;
        } else if (p_Object instanceof String) {
            return this.javaImage;
        }
        return null;
    }

    @Override
    public String getText(final Object p_Object) {
        if (p_Object instanceof Artifact) {
            return Messages.getString ("Gui.buildTargets.treeItem", ((ModelElement) p_Object).getName (), ModelUtils.getFirstTagParameter ((ModelElement) p_Object, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVASOURCESPATH));
        } else if (p_Object instanceof String) {
            return Messages.getString ("Gui.buildTargets.treeItem", p_Object, "module gen root");
        }
        return ""; //$NON-NLS-1$
    }

    @Override
    public boolean isLabelProperty(final Object arg0, final String arg1) {
        // Nothing to do
        return false;
    }

    @Override
    public void removeListener(final ILabelProviderListener arg0) {
        // No listener
    }

}
