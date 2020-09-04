package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;

/**
 * This class provides the list of properties for the <i>Class</i> metaclass.
 */
class EnumerationPropertyModel extends PropertyModel implements IPropertyModel {
    private Enumeration enumeration = null;

    public EnumerationPropertyModel(final IModule module, final Enumeration enumeration) {
        super (module);
        this.enumeration = enumeration;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE) ||
                property.contentEquals (JavaDesignerTagTypes.ENUMERATION_JAVASTATIC)) {
            changePropertyBooleanTaggedValue (this.enumeration, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        
        } else if (property.contentEquals (JavaDesignerStereotypes.JAVAENUMERATION)) {
            changeStereotype (this.enumeration, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        }
    }

    /**
     * Properties to display for <i>Enumeration</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>No code
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerStereotypes.JAVAENUMERATION);
        
        if (this.enumeration.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAENUMERATION)) {
            properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        
            if (!this.enumeration.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                properties.add (JavaDesignerTagTypes.ENUMERATION_JAVASTATIC);
            }
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.enumeration.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVAENUMERATION)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaAutomation"), this.enumeration.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAENUMERATION)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.ENUMERATION_JAVASTATIC)) {
                table.addProperty (Messages.getString ("Gui.Property.Static"), this.enumeration.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ENUMERATION_JAVASTATIC)); //$NON-NLS-1$
            }
        }
    }

}
