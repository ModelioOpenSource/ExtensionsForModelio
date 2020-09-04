package org.modelio.module.javadesigner.propertypage;

import java.util.ArrayList;
import java.util.List;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.ModelUtils;

public abstract class PropertyModel {
    protected final String COLLECTION = "Collection"; // $NON-NLS-1$

    protected final String ARRAY = "Array"; // $NON-NLS-1$

    protected final String STATIC = "Static"; // $NON-NLS-1$

    protected final String KEY = "Key"; // $NON-NLS-1$

    protected final String GETTER = "Getter"; // $NON-NLS-1$

    protected final String SETTER = "Setter"; // $NON-NLS-1$

    protected final String[] collections = { "", //$NON-NLS-1$
            this.ARRAY,
            "ArrayList as List", //$NON-NLS-1$
            "LinkedList as List", //$NON-NLS-1$
            "Stack as List", //$NON-NLS-1$
            "Vector as List", //$NON-NLS-1$
            "HashSet as Set", //$NON-NLS-1$
            "LinkedHashSet as Set", //$NON-NLS-1$
            "TreeSet as Set", //$NON-NLS-1$
            "TreeSet as SortedSet", //$NON-NLS-1$
            "ConcurrentSkipListSet as SortedSet", //$NON-NLS-1$
            "PriorityQueue as Queue", //$NON-NLS-1$
            "HashMap as Map", //$NON-NLS-1$
            "Hashtable as Map", //$NON-NLS-1$
            "IdentityHashMap as Map", //$NON-NLS-1$
            "LinkedHashMap as Map", //$NON-NLS-1$
            "WeakHashMap as Map", //$NON-NLS-1$
            "TreeMap as SortedMap", //$NON-NLS-1$
            "ConcurrentSkipListMap as SortedMap", //$NON-NLS-1$
            "ArrayBlockingQueue as Queue", //$NON-NLS-1$
            "ConcurrentHashMap as Map", //$NON-NLS-1$
            "ConcurrentLinkedQueue as Queue", //$NON-NLS-1$
            "CopyOnWriteArrayList as List", //$NON-NLS-1$
            "CopyOnWriteArraySet as Set", //$NON-NLS-1$
            "LinkedBlockingQueue as Queue", //$NON-NLS-1$
            "PriorityBlockingQueue as Queue", //$NON-NLS-1$
            "SynchronousQueue as Queue",//$NON-NLS-1$
    "ArrayDeque as Deque"}; // $NON-NLS-1$

    protected final String[] propertyVisibility = {"Public", "Protected", "Friendly", "Private", "Default"};

    private IModule module = null;

    public PropertyModel(final IModule module) {
        this.module = module;
    }

    protected boolean changePropertyBooleanTaggedValue(final ModelElement element, final String moduleName, final String tagName, final boolean state) {
        try {
            ModelUtils.setTaggedValue (this.module.getModuleContext().getModelingSession(), element, moduleName, tagName, state);
            return true;
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", tagName)); //$NON-NLS-1$
            return false;
        }
    }

    protected void changePropertyStatic(final Feature feature, final boolean value) {
        feature.setIsClass (value);
        if (value) {
            feature.setIsAbstract (false);
        }
    }

    /**
     * This operation change the &lt;property&gt; property.
     * <p>
     * If the value is "", the &lt;tagName&gt; tagged value is removed.
     * @param property String containing the tagged value name
     * @param element ModelElement on which the property is changed.
     * @param value String containing the property value
     * @return <code>true</code> if a tagged value had been modified.
     */
    protected boolean changePropertyStringTaggedValue(final ModelElement element, final String moduleName, final String propertyName, final String value) {
        boolean result = true;
        
        try {
            ModelUtils.setFirstTagParameter (this.module.getModuleContext().getModelingSession(), element, moduleName, propertyName, value);
        } catch (ExtensionNotFoundException|ElementNotUniqueException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", propertyName)); //$NON-NLS-1$
            result = false;
        }
        return result;
    }

    protected boolean changeStereotype(final ModelElement element, final String moduleName, final String stereotypeName, final boolean add) {
        boolean result = true;
        boolean hasAlreadyThisStereotype = element.isStereotyped (moduleName, stereotypeName);
        
        Stereotype stereotype = getModule().getModuleContext().getModelingSession ().getMetamodelExtensions ().getStereotype (moduleName, stereotypeName, element.getMClass ());
        if (add) {
            if (!hasAlreadyThisStereotype) {
                // Create the tagged value
                element.getExtension().add(stereotype);
            }
        } else {
            if (hasAlreadyThisStereotype) {
                element.getExtension().remove(stereotype);
            }
        }
        return result;
    }

    /**
     * This operation returns the used collection
     * @param element The ModelElement on which the collection is defined.
     * @return The collection
     */
    protected String getCollection(final ModelElement element) {
        String result = ""; //$NON-NLS-1$
        String collectionInterface = ModelUtils.getFirstTagParameter (element, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        String collectionImplementation = ModelUtils.getFirstTagParameter (element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE);
        
        if (collectionInterface.contentEquals ("") && collectionImplementation.contentEquals ("")) { //$NON-NLS-1$ //$NON-NLS-2$
            result = ""; //$NON-NLS-1$
        } else {
            StringBuilder myString;
        
            // For compatibility with old type package, the first character is
            // set in upper case
            if (!collectionInterface.contentEquals ("")) { //$NON-NLS-1$
                myString = new StringBuilder (collectionInterface);
                myString.replace (0, 1, myString.substring (0, 1).toUpperCase ());
                collectionInterface = myString.toString ();
            }
        
            if (!collectionImplementation.contentEquals ("")) { //$NON-NLS-1$
                myString = new StringBuilder (collectionImplementation);
                myString.replace (0, 1, myString.substring (0, 1).toUpperCase ());
                collectionImplementation = myString.toString ();
            }
        
            // Does the collection defined by the properties exist in the
            // collections?
            String completeType = collectionImplementation +
                    " as " + collectionInterface; //$NON-NLS-1$
        
            int nb = this.collections.length;
            for (int i = 0 ; (i < nb) && (result.equals ("")) ; i++) { //$NON-NLS-1$
                String collection = this.collections[i];
                if (collection.contentEquals (completeType)) {
                    result = collection;
                }
            }
        
            if (result.contentEquals ("")) { //$NON-NLS-1$
                if (collectionInterface.contentEquals (this.ARRAY)) {
                    result = this.ARRAY;
                }
            }
        
            if (result.contentEquals ("")) { //$NON-NLS-1$
                result = Messages.getString ("Gui.Property.Personalized"); //$NON-NLS-1$
            }
        }
        return result;
    }

    protected IModule getModule() {
        return this.module;
    }

    /**
     * This operation returns the key used for a collection
     * @param element on which the Key is required
     * @return The second parameter of the "type" tagged value
     */
    protected String getKey(final ModelElement element) {
        String result = ""; //$NON-NLS-1$
        
        List<String> parameters = ModelUtils.getTagParameters (element, IOtherProfileElements.FEATURE_TYPE);
        if (parameters != null) {
            if (parameters.size () >= 2) {
                result = parameters.get (1);
            }
        }
        return result;
    }

    public ArrayList<String> getProperties() {
        return null;
    }

    /**
     * This operation returns the property name defined in the 'row' row.
     * @param row : The row in the property table
     * @return The property name
     */
    protected String getProperty(final int row) {
        return getProperties ().get (row - 1);
    }

    /**
     * This operation changes the used collection
     * @param element The ModelElement on which the collection is defined.
     * @param value The new collection
     */
    protected void setCollection(final ModelElement element, final String value) {
        try {
            String interfaceValue = ""; //$NON-NLS-1$
            String implementationValue = ""; //$NON-NLS-1$
            String[] type;
        
            if (value.contentEquals (this.ARRAY)) {
                interfaceValue = this.ARRAY;
            } else if (value.contentEquals (Messages.getString ("Gui.Property.Personalized"))) {
                return;
            } else {
                type = value.split (" "); //$NON-NLS-1$
        
                // "ArrayList as List"
                // type --> ArrayList
                // JavaImplementationList --> List
                if (type.length == 3) {
                    implementationValue = type[0];
                    interfaceValue = type[2];
                }
            }
        
            // Update the "type" and "JavaImplementationType" property
            try {
                ModelUtils.setTagParameterAt (this.module.getModuleContext().getModelingSession(), element, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, interfaceValue, 1);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", IOtherProfileElements.FEATURE_TYPE)); //$NON-NLS-1$
            }
        
            try {
                ModelUtils.setFirstTagParameter (this.module.getModuleContext().getModelingSession(), element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE, implementationValue);
            } catch (ExtensionNotFoundException|ElementNotUniqueException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE)); //$NON-NLS-1$
            }
        } catch (RuntimeException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    /**
     * This operation sets the key used for a collection
     * @param element on which the Key is set
     * @param value of the Key
     */
    protected void setKey(final ModelElement element, final String value) {
        try {
            ModelUtils.setTagParameterAt (this.module.getModuleContext().getModelingSession(), element, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, value, 2);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", IOtherProfileElements.FEATURE_TYPE)); //$NON-NLS-1$
        }
    }

    protected boolean addPropertyValue(final ModelElement element, final String moduleName, final String propertyName, final String value) {
        boolean result = true;
        
        try {
            List<String> values = element.getTagValues (moduleName, propertyName);
            if (values == null) {
                values = new ArrayList<> ();
            }
        
            if (!values.contains (value)) {
                values.add (value);
            }
        
            element.putTagValues (moduleName, propertyName, values);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", propertyName)); //$NON-NLS-1$
            result = false;
        }
        return result;
    }

    protected boolean removePropertyValue(final ModelElement element, final String moduleName, final String propertyName, final String value) {
        boolean result = true;
        
        try {
            List<String> values = element.getTagValues (moduleName, propertyName);
            if (values == null) {
                values = new ArrayList<> ();
            }
        
            if (values.contains (value)) {
                values.remove (value);
            }
        
            element.putTagValues (moduleName, propertyName, values);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.TagTypeNotFoundWithName", propertyName)); //$NON-NLS-1$
            result = false;
        }
        return result;
    }

    protected String getPropertyVisibilityLabel(final String visibility) {
        if (visibility.equalsIgnoreCase(VisibilityMode.PUBLIC.name())) {
            return "Public";
        } else if (visibility.equalsIgnoreCase(VisibilityMode.PRIVATE.name())) {
            return "Private";
        } else if (visibility.equalsIgnoreCase(VisibilityMode.PROTECTED.name())) {
            return "Protected";
        } else if (visibility.equalsIgnoreCase(VisibilityMode.PACKAGEVISIBILITY.name())) {
            return "Friendly";
        } else {
            return "Default";
        }
    }

    protected String getPropertyVisibilityValue(final String visibility) {
        if (visibility.equalsIgnoreCase("Public")) {
            return VisibilityMode.PUBLIC.name();
        } else if (visibility.equalsIgnoreCase("Private")) {
            return VisibilityMode.PRIVATE.name();
        } else if (visibility.equalsIgnoreCase("Protected")) {
            return VisibilityMode.PROTECTED.name();
        } else if (visibility.equalsIgnoreCase("Friendly")) {
            return VisibilityMode.PACKAGEVISIBILITY.name();
        } else {
            return "";
        }
    }

}
