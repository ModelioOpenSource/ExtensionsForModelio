package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.module.IModule;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.automation.AccessorManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.ModelUtils;

/**
 * This class provides the list of properties for the <i>AssociationEnd</i> metaclass.
 */
class AssociationEndPropertyModel extends PropertyModel implements IPropertyModel {
    private AssociationEnd association = null;

    public AssociationEndPropertyModel(final IModule module, final AssociationEnd association) {
        super (module);
        this.association = association;
    }

    @Override
    public void changeProperty(final int row, final String value) {
        String property = getProperty (row);
        IModelingSession session = getModule().getModuleContext().getModelingSession ();
        
        boolean result = false;
        
        if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE) ||
                property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL) ||
                property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE) ||
                property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT) ||
                property.contentEquals (JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            boolean isActive = Boolean.parseBoolean (value);
            result = changePropertyBooleanTaggedValue (this.association, IJavaDesignerPeerModule.MODULE_NAME, property, isActive);
        
            if (result && isActive) {
                // JavaFinal and JavaVolatile are concurrent
                if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL)) {
                    result = changePropertyBooleanTaggedValue (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE, !isActive);
        
                } else if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE)) {
                    result = changePropertyBooleanTaggedValue (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL, !isActive);
                }
            }
        
        } else if (property.contentEquals (this.GETTER)) {
            changePropertyGetter (Boolean.parseBoolean (value));
            result = true;
        
            try {
                updateAccessors (session);
                result = true;
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
                result = false;
            } catch (CustomException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                result = false;
            }
        } else if (property.contentEquals (this.SETTER)) {
            changePropertySetter (Boolean.parseBoolean (value));
            result = true;
        
            try {
                updateAccessors (session);
                result = true;
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage ());
                result = false;
            } catch (CustomException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                result = false;
            }
        } else if (property.contentEquals (this.STATIC)) {
            changePropertyStatic (this.association, Boolean.parseBoolean (value));
            result = true;
        
        } else if (property.contentEquals (this.COLLECTION)) {
            setCollection (this.association, value);
            result = true;
        
        } else if (property.contentEquals (this.KEY)) {
            setKey (this.association, value);
            result = true;
        
        } else if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND)) {
            result = changePropertyStringTaggedValue (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND, value);
        } else if (property.contentEquals (JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
            result = changeStereotype (this.association, IJavaDesignerPeerModule.MODULE_NAME, property, Boolean.parseBoolean (value));
        } else if (property.contentEquals (JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVAGETTERVISIBILITY)) {
            result = changePropertyStringTaggedValue (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVAGETTERVISIBILITY, getPropertyVisibilityValue(value));
        } else if (property.contentEquals (JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVASETTERVISIBILITY)) {
            result = changePropertyStringTaggedValue (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVASETTERVISIBILITY, getPropertyVisibilityValue(value));
        }
    }

    /**
     * Properties to display for <i>AssociationEnd</i>.
     * <p>
     * This array contains the values:
     * <ul>
     * <li>Java no code
     * <li>Getter
     * <li>Setter
     * <li>Static
     * <li>Final
     * <li>Volatile
     * <li>Transient (not for Interface)
     * <li>Collection to use
     * <li>Key for MultiplicityMax != "", "0" and "1"
     * <li>No init value
     * </ul>
     */
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> properties = new ArrayList<> ();
        
        properties.add (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        
        if (!this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            properties.add (JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY);
            properties.add (this.GETTER);
            if (this.association.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
                if (hasGetter ()) {
                    properties.add (JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVAGETTERVISIBILITY);
                }
            }
        
            if (!this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL)) {
                properties.add (this.SETTER);
                if (this.association.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
                    if (hasSetter ()) {
                        properties.add (JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVASETTERVISIBILITY);
                    }
                }
            }
            properties.add (this.STATIC);
            properties.add (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL);
            properties.add (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE);
            if (!(this.association.getSource () instanceof Interface)) {
                properties.add (JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT);
            }
            properties.add (this.COLLECTION);
            properties.add (this.KEY);
            properties.add (JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE);
            properties.add (JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND);
        }
        return properties;
    }

    @Override
    public void update(final IModulePropertyTable table) {
        for (String property : getProperties ()) {
            if (property.contentEquals (JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoCode"), this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.GETTER)) {
                table.addProperty (Messages.getString ("Gui.Property.Getter"), hasGetter ()); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.SETTER)) {
                table.addProperty (Messages.getString ("Gui.Property.Setter"), hasSetter ()); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.STATIC)) {
                table.addProperty (Messages.getString ("Gui.Property.Static"), this.association.isIsClass ()); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL)) {
                table.addProperty (Messages.getString ("Gui.Property.Final"), this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE)) {
                table.addProperty (Messages.getString ("Gui.Property.Volatile"), this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT)) {
                table.addProperty (Messages.getString ("Gui.Property.Transient"), this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT)); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.COLLECTION)) {
                table.addProperty (Messages.getString ("Gui.Property.Collection"), getCollection (this.association), this.collections); //$NON-NLS-1$
        
            } else if (property.contentEquals (this.KEY)) {
                table.addProperty (Messages.getString ("Gui.Property.Key"), getKey (this.association)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
                table.addProperty (Messages.getString ("Gui.Property.NoInitValue"), this.association.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)); //$NON-NLS-1$
        
            } else if (property.contentEquals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND)) {
                table.addProperty (Messages.getString ("Gui.Property.TemplateBinding"), ModelUtils.getFirstTagParameter (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
                table.addProperty (Messages.getString ("Gui.Property.JavaProperty"), this.association.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVAGETTERVISIBILITY)) {
                String value = getPropertyVisibilityLabel(ModelUtils.getFirstTagParameter (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVAGETTERVISIBILITY));
                table.addProperty (Messages.getString ("Gui.Property.GetterVisibility"), value, this.propertyVisibility); //$NON-NLS-1$
            } else if (property.contentEquals (JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVASETTERVISIBILITY)) {
                String value = getPropertyVisibilityLabel(ModelUtils.getFirstTagParameter (this.association, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVASETTERVISIBILITY));
                table.addProperty (Messages.getString ("Gui.Property.SetterVisibility"), value, this.propertyVisibility); //$NON-NLS-1$
            }
        }
    }

    private void changePropertySetter(final boolean value) {
        if (value) {
            if (this.association.getChangeable () == KindOfAccess.READ) {
                this.association.setChangeable (KindOfAccess.READWRITE);
            } else {
                this.association.setChangeable (KindOfAccess.WRITE);
            }
        } else {
            if (this.association.getChangeable () == KindOfAccess.READWRITE) {
                this.association.setChangeable (KindOfAccess.READ);
            } else {
                this.association.setChangeable (KindOfAccess.ACCESNONE);
            }
        }
    }

    protected void changePropertyGetter(final boolean value) {
        if (value) {
            if (this.association.getChangeable () == KindOfAccess.WRITE) {
                this.association.setChangeable (KindOfAccess.READWRITE);
            } else {
                this.association.setChangeable (KindOfAccess.READ);
            }
        } else {
            if (this.association.getChangeable () == KindOfAccess.READWRITE) {
                this.association.setChangeable (KindOfAccess.WRITE);
            } else {
                this.association.setChangeable (KindOfAccess.ACCESNONE);
            }
        }
    }

    private boolean hasGetter() {
        if (this.association.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
            return this.association.getChangeable().equals(KindOfAccess.READ) | this.association.getChangeable().equals(KindOfAccess.READWRITE);
        } else {
            for (Dependency theDependency : this.association.getImpactedDependency ()) {
                ModelElement impactedElement = theDependency.getImpacted ();
        
                // Check stereotypes only for operations
                if (impactedElement instanceof Operation) {
                    if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private boolean hasSetter() {
        if (this.association.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
            return this.association.getChangeable().equals(KindOfAccess.WRITE) | this.association.getChangeable().equals(KindOfAccess.READWRITE);
        } else {
            for (Dependency theDependency : this.association.getImpactedDependency ()) {
                ModelElement impactedElement = theDependency.getImpacted ();
        
                // Check stereotypes only for operations
                if (impactedElement instanceof Operation) {
                    if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private boolean updateAccessors(final IModelingSession session) throws CustomException, ExtensionNotFoundException {
        if (!this.association.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
            IModuleUserConfiguration javaConfig = getModule ().getModuleContext().getConfiguration ();
        
            AccessorManager accessorManager = new AccessorManager (session);
            accessorManager.init (javaConfig);
            accessorManager.updateAccessors (this.association, true);
            return true;
        } else {
            return false;
        }
    }

}
