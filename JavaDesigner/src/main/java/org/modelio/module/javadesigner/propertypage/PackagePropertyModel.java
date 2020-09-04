package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;

/**
 * This class provides the list of properties for the <i>Package</i> metaclass.
 */
class PackagePropertyModel extends PropertyModel implements IPropertyModel {
    private Package selectedPackage = null;

    public PackagePropertyModel(final IModule module, final Package selectedPackage) {
        super (module);
        this.selectedPackage = selectedPackage;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        // Property "JavaNoCode", "JavaNoPackage"
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            boolean isActive = Boolean.parseBoolean (value);
            changePropertyBooleanTaggedValue (this.selectedPackage, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
        } else if (property.contentEquals (JavaDesignerStereotypes.JAVAPACKAGE)) {
            changeStereotype (this.selectedPackage, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        }
    }

    /**
     * Properties to display for <i>Package</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>Java no code
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerStereotypes.JAVAPACKAGE);
        
        if (this.selectedPackage.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPACKAGE)) {
            properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.selectedPackage.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVAPACKAGE)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaAutomation"), this.selectedPackage.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPACKAGE)); //$NON-NLS-1$
            }
        }
    }

}
