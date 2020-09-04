package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;

/**
 * This class provides the list of properties for the <i>Operation</i> metaclass.
 */
class OperationPropertyModel extends PropertyModel implements IPropertyModel {
    private Operation selectedOperation = null;

    public OperationPropertyModel(final IModule module, final Operation selectedOperation) {
        super (module);
        this.selectedOperation = selectedOperation;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        // Property "JavaNoCode", "JavaWrapper", "JavaFinal", "JavaVolatile", "JavaTransient", "JavaNoInitValue"
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE) ||
                property.contentEquals (JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED) ||
                property.contentEquals (JavaDesignerTagTypes.OPERATION_JAVANATIVE)) {
            boolean isActive = Boolean.parseBoolean (value);
            changePropertyBooleanTaggedValue (this.selectedOperation, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        } else if (property.contentEquals (JavaDesignerTagTypes.CLASS_JAVASTATIC)) {
            boolean isActive = Boolean.parseBoolean (value);
            this.selectedOperation.setIsClass (isActive);
        }
    }

    /**
     * Properties to display for <i>Operation</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>Java no code
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        
        if (!this.selectedOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            properties.add (JavaDesignerTagTypes.CLASS_JAVASTATIC);
            properties.add (JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED);
            properties.add (JavaDesignerTagTypes.OPERATION_JAVANATIVE);
            properties.add ("OperationType");
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.selectedOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.CLASS_JAVASTATIC)) {
                table.addProperty (Messages.getString ("Gui.Property.Static"), this.selectedOperation.isIsClass ()); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED)) {
                table.addProperty (Messages.getString ("Gui.Property.Synchronized"), this.selectedOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.OPERATION_JAVANATIVE)) {
                table.addProperty (Messages.getString ("Gui.Property.Native"), this.selectedOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVANATIVE)); //$NON-NLS-1$
            }
        }
    }

}
