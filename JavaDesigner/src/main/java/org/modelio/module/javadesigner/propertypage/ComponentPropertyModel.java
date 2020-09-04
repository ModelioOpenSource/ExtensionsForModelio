package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.ModelUtils;

/**
 * This class provides the list of properties for the <i>Component</i> metaclass.
 */
class ComponentPropertyModel extends PropertyModel implements IPropertyModel {
    private Component component = null;

    public ComponentPropertyModel(final IModule module, final Component javaComponent) {
        super (module);
        this.component = javaComponent;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        boolean result = false;
        
        // Property "JavaComponent"
        if (property.contentEquals (JavaDesignerStereotypes.JAVACOMPONENT)) {
            boolean toRemove = !Boolean.parseBoolean (value);
            result = changeStereotype (this.component, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
            // The tagged value containing the java component path has to be removed.
            if (result &&
                    toRemove &&
                    this.component.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH)) {
                result = changePropertyStringTaggedValue (this.component, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH, ""); //$NON-NLS-1$
            }
        
            // Property "Plugin path"
        } else if (property.contentEquals (JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH) ||
                property.contentEquals (JavaDesignerTagTypes.JAVACOMPONENT_COPYRIGHTFILE)) {
            result = changePropertyStringTaggedValue (this.component, IJavaDesignerPeerModule.MODULE_NAME, property, value);
        }
    }

    /**
     * Properties to display for <i>JavaComponent</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>JavaComponent
     * <li>Path
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerStereotypes.JAVACOMPONENT);
        if (this.component.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACOMPONENT)) {
            properties.add (JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH);
            properties.add (JavaDesignerTagTypes.JAVACOMPONENT_COPYRIGHTFILE);
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerStereotypes.JAVACOMPONENT)) {
                table.addProperty (Messages.getString ("Gui.Property.Plugin"), this.component.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACOMPONENT)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH)) {
                table.addProperty (Messages.getString ("Gui.Property.PluginPath"), ModelUtils.getFirstTagParameter (this.component, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_GENERATIONPATH)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVACOMPONENT_COPYRIGHTFILE)) {
                table.addProperty (Messages.getString ("Gui.Property.CopyrightFile"), ModelUtils.getFirstTagParameter (this.component, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_COPYRIGHTFILE)); //$NON-NLS-1$
            }
        }
    }

}
