package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

/**
 * This class provides the list of properties for the <i>DataType</i> metaclass.
 */
class DataTypePropertyModel extends PropertyModel implements IPropertyModel {
    private DataType type = null;

    public DataTypePropertyModel(final IModule module, final DataType type) {
        super (module);
        this.type = type;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        // Property "JavaNoCode", "JavaWrapper", "JavaFinal", "JavaVolatile", "JavaTransient", "JavaNoInitValue"
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE) ||
                property.contentEquals (JavaDesignerTagTypes.CLASS_JAVASTATIC)) {
            boolean isActive = Boolean.parseBoolean (value);
            changePropertyBooleanTaggedValue (this.type, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
        } else if (property.contentEquals (JavaDesignerStereotypes.JAVADATATYPE)) {
            changeStereotype (this.type, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        }
    }

    /**
     * Properties to display for <i>Class</i>.
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
        
        properties.add (JavaDesignerStereotypes.JAVADATATYPE);
        
        if (this.type.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADATATYPE)) {
            properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
            if (JavaDesignerUtils.isInner (this.type)) {
                properties.add (JavaDesignerTagTypes.DATATYPE_JAVASTATIC);
            }
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.type.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVADATATYPE)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaAutomation"), this.type.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADATATYPE)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.DATATYPE_JAVASTATIC)) {
                table.addProperty (Messages.getString ("Gui.Property.Static"), this.type.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.DATATYPE_JAVASTATIC)); //$NON-NLS-1$
            }
        }
    }

}
