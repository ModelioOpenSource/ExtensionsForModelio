/**
 * This class provides services on UML models.
 */
package org.modelio.module.javadesigner.utils;

import java.util.ArrayList;
import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * This class provides services on model. At the moment, services are about tagged values.
 */
public class ModelUtils {
    /**
     * This operation returns the parameter values of the first tagged value with the &lt;tagName&gt; name on the
     * &lt;element&gt; ModelElement. The tagged value must have a parameter.
     * @param element ModelElement on which the tagged value is search for.
     * @param tagName String containing the tagged value name
     * @return The parameter values
     */
    public static List<String> getTagParameters(final ModelElement element, final String tagName) {
        List<TaggedValue> tags = element.getTag ();
        ArrayList<String> parameters = null;
        
        for (TaggedValue tag : tags) {
            TagType definition = tag.getDefinition ();
            if (definition != null) {
                if (definition.getName ().contentEquals (tagName)) {
                    List<TagParameter> tagParameters = tag.getActual ();
                    parameters = new ArrayList<> (tagParameters.size ());
                    for (TagParameter parameter : tagParameters) {
                        parameters.add (parameter.getValue ());
                    }
                }
            }
        }
        return parameters;
    }

    /**
     * This operation returns the content of the first parameter of the first tagged value with the &lt;tagName&gt; name
     * on the &lt;element&gt; ModelElement. The tagged value must have a parameter.
     * @param element ModelElement on which the tagged value is search for.
     * @param tagName String containing the tagged value name
     * @return The value of the first tag parameter
     */
    public static String getFirstTagParameter(final ModelElement element, final String moduleName, final String tagName) {
        TaggedValue tag = element.getTag (moduleName, tagName);
        String value = ""; //$NON-NLS-1$
        if (tag != null) {
            List<TagParameter> tagParameters = tag.getActual ();
            if (tagParameters.size () > 0) {
                value = tagParameters.get (0).getValue ();
            }
        } else {
            value = ""; //$NON-NLS-1$
        }
        return value;
    }

    public static TaggedValue setTaggedValue(final IModelingSession session, final ModelElement element, final String moduleName, final String tagName, final boolean add) throws ExtensionNotFoundException {
        IUmlModel model = session.getModel ();
        
        TaggedValue tag = element.getTag (moduleName, tagName);
        if (!add) {
            if (tag != null) {
                tag.delete ();
            }
        } else {
            if (tag == null) {
                // Create the tagged value
                tag = model.createTaggedValue (moduleName, tagName, element);
            }
        }
        return tag;
    }

    /**
     * This operation sets the first parameter of the first tagged value with the &lt;tagName&gt; name on the
     * &lt;element&gt; ModelElement.<br/>
     * The tagged value and the parameter are created if they don't exist.
     * @throws ElementNotUniqueException
     * @param element ModelElement on which the tagged value is created or updated.
     * @param tagName String containing the tagged value name
     * @param value String containing the value to store on the parameter
     */
    public static TaggedValue setFirstTagParameter(final IModelingSession session, final ModelElement element, final String moduleName, final String tagName, final String value) throws ElementNotUniqueException, ExtensionNotFoundException {
        IUmlModel model = session.getModel ();
        
        TaggedValue tag = element.getTag (moduleName, tagName);
        
        if (value.contentEquals ("")) { //$NON-NLS-1$
            if (tag != null) {
                tag.delete ();
            }
        } else {
            if (tag == null) {
                // Create the tagged value
                tag = model.createTaggedValue (moduleName, tagName, element);
            }
        
            List<TagParameter> tagParameters = tag.getActual ();
            // Update or creation of the first parameter
            if (tagParameters.size () == 0) {
                model.createTagParameter (value, tag);
            } else {
                tagParameters.get (0).setValue (value);
            }
        
            // Remove the old parameters
            int i;
            int nb = tagParameters.size ();
            for (i = nb - 1 ; i > 0 ; i--) {
                TagParameter parameter = tagParameters.get (i);
                parameter.delete ();
            }
        }
        return tag;
    }

    /**
     * This operation returns all tagged values with the &lt;tagName&gt; name on the &lt;element&gt; ModelElement.
     * @param element ModelElement on which the tagged value is search for.
     * @param tagName String containing the tagged value name
     * @return List of all the TaggedValue
     */
    public static List<TaggedValue> getAllTaggedValues(final ModelElement element, final String tagName) {
        List<TaggedValue> retTags = new ArrayList<> ();
        int i;
        List<TaggedValue> tags = element.getTag ();
        for (i = 0 ; (i < tags.size ()) ; i++) {
            TaggedValue localTag = tags.get (i);
            TagType type = localTag.getDefinition ();
            if (type != null && type.getName ().contentEquals (tagName)) {
                retTags.add (localTag);
            }
        }
        return retTags;
    }

    /**
     * This operation returns all notes with the &lt;tagName&gt; name on the &lt;element&gt; ModelElement.
     * @param element ModelElement on which the note is search for.
     * @param noteName String containing the note name
     * @return List of all the TaggedValue
     */
    public static List<Note> getAllNotes(final ModelElement element, final String noteName) {
        ArrayList<Note> retNotes = new ArrayList<> ();
        int i;
        List<Note> notes = element.getDescriptor ();
        for (i = 0 ; (i < notes.size ()) ; i++) {
            Note localNote = notes.get (i);
            NoteType model = localNote.getModel ();
            if (model != null && model.getName ().contentEquals (noteName)) {
                retNotes.add (localNote);
            }
        }
        return retNotes;
    }

    public static void setTagParameterAt(final IModelingSession session, final ModelElement element, final String moduleName, final String tagName, final String value, final int index) throws ExtensionNotFoundException {
        IUmlModel model = session.getModel ();
        
        TaggedValue tag = element.getTag (moduleName, tagName);
        
        if (tag == null) {
            // Create the tagged value
            tag = model.createTaggedValue (moduleName, tagName, element);
        }
        
        List<TagParameter> parameters = new ArrayList<>(tag.getActual ());
        
        if (value.contentEquals ("")) { //$NON-NLS-1$
            // The parameter at index is removed
            if (parameters.size () == index) {
                tag.getActual ().get (index - 1).delete ();
            } else if (parameters.size () > index) {
                parameters.get (index - 1).setValue (value);
            }
        } else {
            // Update or creation of parameter at index
            while (parameters.size () < index) {
                model.createTagParameter ("", tag);
                parameters = tag.getActual ();
            }
            parameters.get (index - 1).setValue (value);
        }
    }

    /**
     * This operation returns 'true' if the element has a tagged value which the name is defined with a parameter having
     * the correct value.
     * @return
     * @param element ModelElement on which the tagged value is search for.
     * @param tagName String containing the tagged value name
     * @param value String containing the value to find in the parameters
     */
    public static boolean hasTagParameter(final ModelElement element, final String tagName, final String value) {
        List<String> parameters = getTagParameters (element, tagName);
        return (parameters != null && parameters.contains (value));
    }

    /**
     * Add a value to the tagged value 'name' to 'elt'. If 'value' is not empty it is added
     * to the parameters list of the tagged value. 'value' is already in the
     * parameter list, it is not duplicated.
     */
    public static void addTaggedValue(final ModelElement elt, final String moduleName, final String name, final String value) {
        TagPair pair = findTaggedValue(elt, name, value);
        if (pair == null) {
            // tagged value doesn't exist : create it
            doAddTaggedValue(elt, moduleName, name, value);
        } else if (pair.tp == null) {
            // tagged value exists but none of its parameter has 'values' : add
            // the parameter
            IUmlModel model = JavaDesignerModule.getInstance().getModuleContext().getModelingSession().getModel();
            model.createTagParameter(value, pair.tv);
        }
    }

    /**
     * Add the tagged value 'name' to 'elt'. If 'value' is not empty it is added
     * to the parameters list of the tagged value. 'value' is already in the
     * parameter list, it is not duplicated.
     */
    public static void removeTaggedValue(final ModelElement elt, final String name, final String value) {
        TagPair tag = findTaggedValue(elt, name, value);
        if (tag != null) {
            if (value.isEmpty()) {
                // No parameters: delete tag
                tag.tv.delete();
            } else if (tag.tp != null && value.equals(tag.tp.getValue())) {
                // Some parameters: empty it
                removeTagParameter(tag);
            }
        }
    }

    private static void doAddTaggedValue(final ModelElement elt, final String moduleName, final String name, final String value) {
        if (elt == null) {
            return;
        }
        
        try {
            IUmlModel model = JavaDesignerModule.getInstance().getModuleContext().getModelingSession().getModel();
            TaggedValue taggedValue = model.createTaggedValue(moduleName, name, elt);
            if (!value.isEmpty()) {
                model.createTagParameter(value, taggedValue);
            }
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    private static void removeTagParameter(final TagPair tag) {
        tag.tp.delete();
        // Remove the tag if no more parameter
        if (tag.tv.getActual().size() == 0) {
            tag.tv.delete();
        }
    }

    private static TagPair findTaggedValue(final ModelElement elt, final String name, final String values) {
        for (TaggedValue tv : elt.getTag()) {
            TagType type = tv.getDefinition();
            if (type != null && type.getName().equals(name)) {
                if (values.isEmpty()) {
                    return new TagPair(tv);
                }
                List<TagParameter> params = tv.getActual();
                for (TagParameter tp : params) {
                    if (tp.getValue().equals(values)) {
                        return new TagPair(tv, tp);
                    }
                }
                return new TagPair(tv);
            }
        }
        return null;
    }

    public static void addStereotype(final ModelElement element, final String stereotypeName) throws ExtensionNotFoundException {
        if (!element.isStereotyped (IJavaDesignerPeerModule.MODULE_NAME, stereotypeName)) {
            Stereotype stereotype = JavaDesignerModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions ().getStereotype (IJavaDesignerPeerModule.MODULE_NAME, stereotypeName, element.getMClass ());
            if (stereotype == null) {
                stereotype = JavaDesignerModule.getInstance().getModuleContext().getModelingSession().getMetamodelExtensions ().getStereotype (IOtherProfileElements.MODULE_NAME, stereotypeName, element.getMClass ());
            }
        
            if (stereotype != null) {
                element.getExtension().add(stereotype);
            } else {
                throw new ExtensionNotFoundException("stereotype " + stereotypeName + " not found");
            }
        }
    }

    public static String getLocalProperty(final ModelElement element, final String propertyName) {
        LocalPropertyTable localProperties = element.getLocalProperties();
        if (localProperties != null) {
            String value = localProperties.getProperty(propertyName);
            if (value != null) {
                return value;
            }
        }
        return "";
    }

    public static void setLocalProperty(final IModelingSession session, final ModelElement element, final String propertyName, final String value) {
        LocalPropertyTable localProperties = element.getLocalProperties();
        if (localProperties == null) {
            // Init the local property table
            localProperties = session.getModel().createLocalPropertyTable();
            element.setLocalProperties(localProperties);
        }
        
        localProperties.setProperty(propertyName, value);
    }

    public static boolean isLibrary(final MObject element) {
        MStatus status = element.getStatus();
        // Model components are libraries
        if (status.isRamc()) {
            return true;
        }
        // Modifiable elements are not libraries
        if (status.isModifiable()) {
            return false;
        }
        // Read only elements that are cms are not libraries
        return !status.isCmsManaged();
    }

    private static class TagPair {
        public TaggedValue tv;

        public TagParameter tp;

        public TagPair(final TaggedValue ptv) {
            this.tv = ptv;
            this.tp = null;
        }

        public TagPair(final TaggedValue ptv, final TagParameter ptp) {
            this.tv = ptv;
            this.tp = ptp;
        }

    }

}
