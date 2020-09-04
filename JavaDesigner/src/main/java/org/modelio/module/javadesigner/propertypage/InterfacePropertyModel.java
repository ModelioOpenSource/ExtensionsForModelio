package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

/**
 * This class provides the list of properties for the <i>Interface</i> metaclass.
 */
class InterfacePropertyModel extends PropertyModel implements IPropertyModel {
    private Interface selectedInterface = null;

    public InterfacePropertyModel(final IModule module, final Interface selectedInterface) {
        super (module);
        this.selectedInterface = selectedInterface;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        // Property "JavaNoCode", "JavaWrapper", "JavaFinal", "JavaVolatile", "JavaTransient", "JavaNoInitValue"
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            boolean isActive = Boolean.parseBoolean (value);
            changePropertyBooleanTaggedValue (this.selectedInterface, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
        } else if (property.contentEquals (JavaDesignerStereotypes.JAVAINTERFACE)) {
            changeStereotype (this.selectedInterface, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        } else if (property.contentEquals (JavaDesignerTagTypes.INTERFACE_JAVASTATIC)) {
            boolean isActive = Boolean.parseBoolean (value);
            changePropertyBooleanTaggedValue (this.selectedInterface, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        }
    }

    /**
     * Properties to display for <i>Interface</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>Java no code
     * <li>JavaStatic: only for inner classes
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerStereotypes.JAVAINTERFACE);
        
        if (this.selectedInterface.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINTERFACE)) {
            properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        
            if (!this.selectedInterface.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                if (JavaDesignerUtils.isInner (this.selectedInterface)) {
                    properties.add (JavaDesignerTagTypes.INTERFACE_JAVASTATIC);
                }
            }
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.selectedInterface.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVAINTERFACE)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaAutomation"), this.selectedInterface.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINTERFACE)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.INTERFACE_JAVASTATIC)) {
                table.addProperty (Messages.getString ("Gui.Property.Static"), this.selectedInterface.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.INTERFACE_JAVASTATIC)); //$NON-NLS-1$
            }
        }
    }

}
