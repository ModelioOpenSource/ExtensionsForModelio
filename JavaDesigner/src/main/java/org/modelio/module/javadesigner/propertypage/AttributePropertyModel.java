package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.automation.AccessorManager;
import org.modelio.module.javadesigner.dialog.DialogManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;

/**
 * This class provides the list of properties for the <i>Attribute</i> metaclass.
 */
class AttributePropertyModel extends PropertyModel implements IPropertyModel {
    private Attribute attribute = null;

    public AttributePropertyModel(final IModule module, final Attribute attribute) {
        super(module);
        this.attribute = attribute;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty(row);
        IModelingSession session = getModule().getModuleContext().getModelingSession();
        boolean result = false;
        
        // Property "JavaNoCode", "JavaWrapper", "JavaFinal", "JavaVolatile", "JavaTransient", "JavaNoInitValue"
        if(property.contentEquals(JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE) ||
                property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL) ||
                property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE) ||
                property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT) ||
                property.contentEquals(JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            boolean isActive = Boolean.parseBoolean(value);
            result = changePropertyBooleanTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
            if(result && isActive) {
                // JavaFinal and JavaVolatile are concurrent
                if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
                    result = changePropertyBooleanTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE, !isActive);
        
                } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)) {
                    result = changePropertyBooleanTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL, !isActive);
                }
            }
        } else if(property.contentEquals(this.GETTER)) {
            changePropertyGetter(Boolean.parseBoolean(value));
        
            try {
                updateAccessors(session);
                result = true;
            } catch(ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                result = false;
            } catch(CustomException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                result = false;
            }
        } else if(property.contentEquals(this.SETTER)) {
            changePropertySetter(Boolean.parseBoolean(value));
        
            try {
                updateAccessors(session);
                result = true;
            } catch(ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                result = false;
            } catch(CustomException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                result = false;
            }
        } else if(property.contentEquals(this.STATIC)) {
            changePropertyStatic(this.attribute, Boolean.parseBoolean(value));
            result = true;
        
        } else if(property.contentEquals(this.COLLECTION)) {
            setCollection(this.attribute, value);
            result = true;
        
        } else if(property.contentEquals(this.KEY)) {
            setKey(this.attribute, value);
            result = true;
        } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)) {
            if(!mustUseWrapper(this.attribute)) {
                boolean isActive = Boolean.parseBoolean(value);
                result = changePropertyBooleanTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
                if(result && isActive) {
                    // JavaFinal and JavaVolatile are concurrent
                    if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
                        result = changePropertyBooleanTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE, !isActive);
        
                    } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)) {
                        result = changePropertyBooleanTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL, !isActive);
                    }
                }
            } else {
                DialogManager.openInformation(Messages.getString("Gui.Property.AttributeBoxTitle"), Messages.getString("Error.AttributeWrapping")); //$NON-NLS-1$ //$NON-NLS-2$
            }
        
        } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)) {
            result = changePropertyStringTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABIND, value);
        } else if(property.contentEquals(JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
            result = changeStereotype(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean(value));
        } else if(property.contentEquals(JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY)) {
            result = changePropertyStringTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY, getPropertyVisibilityValue(value));
        } else if(property.contentEquals(JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY)) {
            result = changePropertyStringTaggedValue(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY, getPropertyVisibilityValue(value));
        }
    }

    /**
     * Properties to display for <i>Attribute</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>Java no code
     * <li>Wrapper for basic types(char, boolean, integer and real)
     * <li>Getter
     * <li>Setter
     * <li>Static
     * <li>Final
     * <li>Volatile
     * <li>Transient(not for Interface)
     * <li>Collection to use
     * <li>Key for MultiplicityMax != "", "0" and "1"
     * <li>No init value
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<>();
        GeneralClass type = this.attribute.getType();
        
        properties.add(JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        
        if(!this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            properties.add(JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY);
        
            if(type != null) {
                String typeName = type.getName();
                if(JavaDesignerUtils.isPredefinedType(typeName)) {
                    properties.add(JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER);
                }
            }
        
            properties.add(this.GETTER);
            if(this.attribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
                if(hasGetter()) {
                    properties.add(JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY);
                }
            }
        
            if(!this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
                properties.add(this.SETTER);
                if(this.attribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
                    if(hasSetter()) {
                        properties.add(JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY);
                    }
                }
            }
            properties.add(this.STATIC);
            properties.add(JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL);
            properties.add(JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE);
            if(!(this.attribute.getOwner() instanceof Interface)) {
                properties.add(JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT);
            }
            properties.add(this.COLLECTION);
            properties.add(this.KEY);
            properties.add(JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE);
            properties.add(JavaDesignerTagTypes.ATTRIBUTE_JAVABIND);
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for(String property : getProperties()) {
            if(property.contentEquals(JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty(Messages.getString("Gui.Property.NoCode"), this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)) {
                table.addProperty(Messages.getString("Gui.Property.JavaWrapper"), mustUseWrapper(this.attribute) || this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER)); //$NON-NLS-1$
        
            } else if(property.contentEquals(this.GETTER)) {
                table.addProperty(Messages.getString("Gui.Property.Getter"), hasGetter()); //$NON-NLS-1$
        
            } else if(property.contentEquals(this.SETTER)) {
                table.addProperty(Messages.getString("Gui.Property.Setter"), hasSetter()); //$NON-NLS-1$
        
            } else if(property.contentEquals(this.STATIC)) {
                table.addProperty(Messages.getString("Gui.Property.Static"), this.attribute.isIsClass()); //$NON-NLS-1$
        
            } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
                table.addProperty(Messages.getString("Gui.Property.Final"), this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)); //$NON-NLS-1$
        
            } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)) {
                table.addProperty(Messages.getString("Gui.Property.Volatile"), this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)); //$NON-NLS-1$
        
            } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT)) {
                table.addProperty(Messages.getString("Gui.Property.Transient"), this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT)); //$NON-NLS-1$
        
            } else if(property.contentEquals(this.COLLECTION)) {
                table.addProperty(Messages.getString("Gui.Property.Collection"), getCollection(this.attribute), this.collections); //$NON-NLS-1$
        
            } else if(property.contentEquals(this.KEY)) {
                table.addProperty(Messages.getString("Gui.Property.Key"), getKey(this.attribute)); //$NON-NLS-1$
        
            } else if(property.contentEquals(JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
                table.addProperty(Messages.getString("Gui.Property.NoInitValue"), this.attribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)); //$NON-NLS-1$
        
            } else if(property.contentEquals(JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)) {
                table.addProperty(Messages.getString("Gui.Property.TemplateBinding"), ModelUtils.getFirstTagParameter(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)); //$NON-NLS-1$
            } else if(property.contentEquals(JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
                table.addProperty(Messages.getString("Gui.Property.JavaProperty"), this.attribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)); //$NON-NLS-1$
            } else if(property.contentEquals(JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY)) {
                String value = getPropertyVisibilityLabel(ModelUtils.getFirstTagParameter(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY));
                table.addProperty(Messages.getString("Gui.Property.GetterVisibility"), value, this.propertyVisibility); //$NON-NLS-1$
            } else if(property.contentEquals(JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY)) {
                String value = getPropertyVisibilityLabel(ModelUtils.getFirstTagParameter(this.attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY));
                table.addProperty(Messages.getString("Gui.Property.SetterVisibility"), value, this.propertyVisibility); //$NON-NLS-1$
            }
        }
    }

    private void changePropertySetter(final boolean value) {
        if(value) {
            if(this.attribute.getChangeable() == KindOfAccess.READ) {
                this.attribute.setChangeable(KindOfAccess.READWRITE);
            } else {
                this.attribute.setChangeable(KindOfAccess.WRITE);
            }
        } else {
            if(this.attribute.getChangeable() == KindOfAccess.READWRITE) {
                this.attribute.setChangeable(KindOfAccess.READ);
            } else {
                this.attribute.setChangeable(KindOfAccess.ACCESNONE);
            }
        }
    }

    protected void changePropertyGetter(final boolean value) {
        if(value) {
            if(this.attribute.getChangeable() == KindOfAccess.WRITE) {
                this.attribute.setChangeable(KindOfAccess.READWRITE);
            } else {
                this.attribute.setChangeable(KindOfAccess.READ);
            }
        } else {
            if(this.attribute.getChangeable() == KindOfAccess.READWRITE) {
                this.attribute.setChangeable(KindOfAccess.WRITE);
            } else {
                this.attribute.setChangeable(KindOfAccess.ACCESNONE);
            }
        }
    }

    private boolean mustUseWrapper(final Attribute theAttribute) {
        String MultiplicityMin = theAttribute.getMultiplicityMin();
        String MultiplicityMax = theAttribute.getMultiplicityMax();
        return MultiplicityMin.equals("0") && MultiplicityMax.equals("1"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private boolean hasGetter() {
        if(this.attribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
            return this.attribute.getChangeable().equals(KindOfAccess.READ) | this.attribute.getChangeable().equals(KindOfAccess.READWRITE);
        } else {
            for(Dependency theDependency : this.attribute.getImpactedDependency()) {
                ModelElement impactedElement = theDependency.getImpacted();
        
                // Check stereotypes only for operations
                if(impactedElement instanceof Operation) {
                    if(theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private boolean hasSetter() {
        if(this.attribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
            return this.attribute.getChangeable().equals(KindOfAccess.WRITE) | this.attribute.getChangeable().equals(KindOfAccess.READWRITE);
        } else {
            for(Dependency theDependency : this.attribute.getImpactedDependency()) {
                ModelElement impactedElement = theDependency.getImpacted();
        
                // Check stereotypes only for operations
                if(impactedElement instanceof Operation) {
                    if(theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private boolean updateAccessors(final IModelingSession session) throws CustomException, ExtensionNotFoundException {
        if(!this.attribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
            IModuleUserConfiguration javaConfig = getModule().getModuleContext().getConfiguration();
        
            AccessorManager accessorManager = new AccessorManager(session);
            accessorManager.init(javaConfig);
            accessorManager.updateAccessors(this.attribute, true);
            return true;
        } else {
            return false;
        }
    }

}
