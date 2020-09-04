package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.ModelUtils;

/**
 * This class provides the list of properties for the <i>Artifact</i> metaclass.
 */
class ArtifactPropertyModel extends PropertyModel implements IPropertyModel {
    private Artifact artifact = null;

    public ArtifactPropertyModel(final IModule module, final Artifact artifact) {
        super (module);
        this.artifact = artifact;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        boolean result = false;
        
        // Property "JarFile"
        if (property.contentEquals (JavaDesignerStereotypes.JARFILE)) {
            boolean toRemove = !Boolean.parseBoolean (value);
            result = changeStereotype (this.artifact, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
            // The tagged value containing the main class has to be removed.
            if (result &&
                    toRemove &&
                    this.artifact.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS)) {
                result = changePropertyStringTaggedValue (this.artifact, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS, ""); //$NON-NLS-1$
            }
        
            // Property "JavaMainClass"
        } else if (property.contentEquals (JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS)) {
            result = changePropertyStringTaggedValue (this.artifact, IJavaDesignerPeerModule.MODULE_NAME, property, value);
        }
    }

    /**
     * Properties to display for <i>Artifact</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>JarFile stereotype
     * <li>JavaMainClass if the artifact is stereotyped "JarFile"
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerStereotypes.JARFILE);
        if (this.artifact.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)) {
            properties.add (JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS);
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerStereotypes.JARFILE)) {
                table.addProperty (Messages.getString ("Gui.Property.JarFile"), this.artifact.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JARFILE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS)) {
                table.addProperty (Messages.getString ("Gui.Property.MainClass"), ModelUtils.getFirstTagParameter (this.artifact, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JARFILE_JAVAMAINCLASS)); //$NON-NLS-1$
            }
        }
    }

}
