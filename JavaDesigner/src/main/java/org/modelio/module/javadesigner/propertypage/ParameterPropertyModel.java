package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;

/**
 * This class provides the list of properties for the <i>Parameter</i> metaclass.
 */
class ParameterPropertyModel extends PropertyModel implements IPropertyModel {
    protected final String[] paramCollections = { "", //$NON-NLS-1$
            this.ARRAY,
            "List", //$NON-NLS-1$
            "Set", //$NON-NLS-1$
            "Queue", //$NON-NLS-1$
            "Map", //$NON-NLS-1$
    "SortedMap"                          }; // $NON-NLS-1$

    private Parameter parameter = null;

    @Override
    protected String getCollection(final ModelElement element) {
        String type = ModelUtils.getFirstTagParameter (element, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        String result = ""; //$NON-NLS-1$
        
        if (type.contentEquals ("")) { //$NON-NLS-1$
            result = ""; //$NON-NLS-1$
        } else {
            int nb = this.paramCollections.length;
            for (int i = 0 ; (i < nb) && (result.contentEquals ("")) ; i++) { //$NON-NLS-1$
                String collection = this.paramCollections[i];
                if (collection.contentEquals (type)) {
                    result = collection;
                }
            }
        
            if (result.contentEquals ("")) { //$NON-NLS-1$
                result = Messages.getString ("Gui.Property.Personalized"); //$NON-NLS-1$
            }
        }
        return result;
    }

    @Override
    protected void setCollection(final ModelElement element, final String value) {
        try {
            // Update the "type" property
            try {
                ModelUtils.setFirstTagParameter (getModule().getModuleContext().getModelingSession(), element, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, value);
            } catch (ExtensionNotFoundException|ElementNotUniqueException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", IOtherProfileElements.FEATURE_TYPE)); //$NON-NLS-1$
            }
        } catch (RuntimeException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    public ParameterPropertyModel(final IModule module, final Parameter parameter) {
        super (module);
        this.parameter = parameter;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        
        // Property "JavaWrapper"
        if (property.contentEquals (JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)) {
            changePropertyBooleanTaggedValue (this.parameter, property, IJavaDesignerPeerModule.MODULE_NAME, Boolean.parseBoolean (value));
        
        } else if (property.contentEquals (this.COLLECTION)) {
            setCollection (this.parameter, value);
        
        } else if (property.contentEquals (this.KEY)) {
            setKey (this.parameter, value);
        
        } else if (property.contentEquals (JavaDesignerTagTypes.PARAMETER_JAVABIND)) {
            changePropertyStringTaggedValue (this.parameter, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVABIND, value);
        }
    }

    /**
     * Properties to display for <i>Parameter</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>Java no code
     * <li>Wrapper for basic types (char, boolean, integer and real)
     * <li>Collection to use
     * <li>Key for MultiplicityMax != "", "0" and "1"
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        GeneralClass type = this.parameter.getType ();
        
        if (type != null) {
            String typeName = type.getName ();
            if (JavaDesignerUtils.isPredefinedType (typeName)) {
                properties.add (JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER);
            }
        }
        properties.add (this.COLLECTION);
        if (!this.parameter.getMultiplicityMax ().contentEquals ("") && !this.parameter.getMultiplicityMax ().contentEquals ("0") && !this.parameter.getMultiplicityMax ().contentEquals ("1")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            properties.add (this.KEY);
        }
        properties.add (JavaDesignerTagTypes.PARAMETER_JAVABIND);
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaWrapper"), this.parameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.COLLECTION)) {
                table.addProperty (Messages.getString ("Gui.Property.Collection"), getCollection (this.parameter), this.paramCollections); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.KEY)) {
                table.addProperty (Messages.getString ("Gui.Property.Key"), getKey (this.parameter)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.PARAMETER_JAVABIND)) {
                table.addProperty (Messages.getString ("Gui.Property.TemplateBinding"), ModelUtils.getFirstTagParameter (this.parameter, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVABIND)); //$NON-NLS-1$
            }
        }
    }

}
