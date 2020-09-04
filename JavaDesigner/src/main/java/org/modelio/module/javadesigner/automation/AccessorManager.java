package org.modelio.module.javadesigner.automation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;

public class AccessorManager {
    public boolean FULLNAMEGENERATION;

    public String PUBLICACCESSORVISIBILITY;

    public String PROTECTEDACCESSORVISIBILITY;

    public String PRIVATEACCESSORVISIBILITY;

    public String FRIENDLYACCESSORVISIBILITY;

    public String PUBLICMODIFIERVISIBILITY;

    public String PROTECTEDMODIFIERVISIBILITY;

    public String PRIVATEMODIFIERVISIBILITY;

    public String FRIENDLYMODIFIERVISIBILITY;

    public final String AUTOMATIC_UPDATE_COMMENT = "// Automatically generated method. Please delete this comment before entering specific code.";

    private static final String NL = System.getProperty ("line.separator"); // $NON-NLS-1$

    public final String PROPERTY_COMMENT = "// Automatically generated method. Please do not modify this code.";

    private IModelingSession session;

    private IUmlModel model;

    public AccessorManager(final IModelingSession session) {
        this.session = session;
        this.model = session.getModel();
    }

    private String getDefaultGetterCode(final String featureName, final Attribute feature) {
        if (feature.isIsDerived () || feature.isIsAbstract ()) {
            return "return null;";
        } else if (feature.isIsClass ()) {
            return "return " + featureName + ";";
        } else {
            return "return this." + featureName + ";";
        }
    }

    private String getDefaultGetterCode(final String featureName, final AssociationEnd feature) {
        if (feature.isIsDerived () || feature.isIsAbstract ()) {
            return "return null;";
        } else if (feature.isIsClass ()) {
            return "return " + featureName + ";";
        } else {
            return "return this." + featureName + ";";
        }
    }

    private String getDefaultSetterCode(final String featureName, final String parameterName, final AssociationEnd feature) {
        if (feature.isIsDerived () || feature.isIsAbstract ()) {
            return "";
        } else if (feature.isIsClass ()) {
            return featureName + " = " + parameterName + ";";
        } else {
            return "this." + featureName + " = " + parameterName + ";";
        }
    }

    private String getDefaultSetterCode(final String featureName, final String parameterName, final Attribute feature) {
        if (feature.isIsDerived () || feature.isIsAbstract ()) {
            return "";
        } else if (feature.isIsClass ()) {
            return featureName + " = " + parameterName + ";";
        } else {
            return "this." + featureName + " = " + parameterName + ";";
        }
    }

    private VisibilityMode getGetterVisibility(final Feature theFeature) {
        VisibilityMode ret;
        String param;
        
        // Operations in Interfaces should always be public
        if (theFeature.getCompositionOwner() instanceof Interface) {
            return VisibilityMode.PUBLIC;
        }
        
        switch (theFeature.getVisibility ()) {
        case PUBLIC:
            param = this.PUBLICACCESSORVISIBILITY;
            break;
        case PROTECTED:
            param = this.PROTECTEDACCESSORVISIBILITY;
            break;
        case PRIVATE:
            param = this.PRIVATEACCESSORVISIBILITY;
            break;
        default:
            param = this.FRIENDLYACCESSORVISIBILITY;
        }
        
        if (param.equals (JavaDesignerParameters.AccessorVisibility.Private.toString ())) {
            ret = VisibilityMode.PRIVATE;
        } else if (param.equals (JavaDesignerParameters.AccessorVisibility.Protected.toString ())) {
            ret = VisibilityMode.PROTECTED;
        } else if (param.equals (JavaDesignerParameters.AccessorVisibility.Public.toString ())) {
            ret = VisibilityMode.PUBLIC;
        } else {
            // AccessorVisibility.Friendly
            ret = VisibilityMode.PACKAGEVISIBILITY;
        }
        return ret;
    }

    private VisibilityMode getSetterVisibility(final Feature theFeature) {
        VisibilityMode ret;
        String param;
        
        // Operations in Interfaces should always be public
        if (theFeature.getCompositionOwner() instanceof Interface) {
            return VisibilityMode.PUBLIC;
        }
        
        switch (theFeature.getVisibility ()) {
        case PUBLIC:
            param = this.PUBLICMODIFIERVISIBILITY;
            break;
        case PROTECTED:
            param = this.PROTECTEDMODIFIERVISIBILITY;
            break;
        case PRIVATE:
            param = this.PRIVATEMODIFIERVISIBILITY;
            break;
        default:
            param = this.FRIENDLYMODIFIERVISIBILITY;
        }
        
        if (param.equals (JavaDesignerParameters.AccessorVisibility.Private.toString ())) {
            ret = VisibilityMode.PRIVATE;
        } else if (param.equals (JavaDesignerParameters.AccessorVisibility.Protected.toString ())) {
            ret = VisibilityMode.PROTECTED;
        } else if (param.equals (JavaDesignerParameters.AccessorVisibility.Public.toString ())) {
            ret = VisibilityMode.PUBLIC;
        } else {
            // AccessorVisibility.Friendly
            ret = VisibilityMode.PACKAGEVISIBILITY;
        }
        return ret;
    }

    private void createGetter(final Attribute theAttribute) throws CustomException, ExtensionNotFoundException {
        // Create the operation and the dependency between the attribute and
        // the operation
        Operation theGetter = this.model.createOperation ();
        Dependency dep = this.model.createDependency (theGetter, theAttribute, null);
        ModelUtils.addStereotype(dep, JavaDesignerStereotypes.JAVAGETTER);
        
        // Set visibility
        VisibilityMode newVisibility = getGetterVisibility (theAttribute);
        theGetter.setVisibility (newVisibility);
        
        // let the update synchronize everything else
        updateGetter (theAttribute, theGetter);
    }

    private void createGetter(final AssociationEnd theAssociationEnd) throws CustomException, ExtensionNotFoundException {
        // Create the operation and the dependency between the attribute and
        // the operation
        Operation theGetter = this.model.createOperation ();
        Dependency dep = this.model.createDependency (theGetter, theAssociationEnd, null);
        ModelUtils.addStereotype(dep, JavaDesignerStereotypes.JAVAGETTER);
        
        // Set visibility
        VisibilityMode newVisibility = getGetterVisibility (theAssociationEnd);
        theGetter.setVisibility (newVisibility);
        
        // let the update synchronize everything else
        updateGetter (theAssociationEnd, theGetter);
    }

    private void createSetter(final Attribute theAttribute) throws CustomException, ExtensionNotFoundException {
        // Create the operation and the dependency between the attribute and
        // the operation
        Operation theSetter = this.model.createOperation ();
        Dependency dep = this.model.createDependency (theSetter, theAttribute, null);
        ModelUtils.addStereotype(dep, JavaDesignerStereotypes.JAVASETTER);
        
        // Set visibility
        VisibilityMode newVisibility = getSetterVisibility (theAttribute);
        theSetter.setVisibility (newVisibility);
        
        // let the update synchronize everything else
        updateSetter (theAttribute, theSetter);
    }

    private void createSetter(final AssociationEnd theAssociationEnd) throws CustomException, ExtensionNotFoundException {
        // Create the operation and the dependency between the attribute and
        // the operation
        Operation theSetter = this.model.createOperation ();
        Dependency dep = this.model.createDependency (theSetter, theAssociationEnd, null);
        ModelUtils.addStereotype(dep, JavaDesignerStereotypes.JAVASETTER);
        
        // Set visibility
        VisibilityMode newVisibility = getSetterVisibility (theAssociationEnd);
        theSetter.setVisibility (newVisibility);
        
        // let the update synchronize everything else
        updateSetter (theAssociationEnd, theSetter);
    }

    @SuppressWarnings("deprecation")
    private void updateParameterType(final Parameter theParameter, final Attribute theAttribute) throws CustomException, ExtensionNotFoundException {
        theParameter.setMultiplicityMin (theAttribute.getMultiplicityMin ());
        theParameter.setMultiplicityMax (theAttribute.getMultiplicityMax ());
        theParameter.setType (theAttribute.getType ());
        
        // Remove all tags on the parameter
        List<TaggedValue> parameterTags = new ArrayList<> (theParameter.getTag ());
        for (TaggedValue theTag : parameterTags) {
            TagType tagType = theTag.getDefinition ();
            String tagTypeName = tagType.getName ();
            if (tagTypeName != null) {
                if (tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAARRAYDIMENSION) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVABIND) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVALONG) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER) ||
                        tagTypeName.equals (IOtherProfileElements.FEATURE_TYPE)) {
                    theTag.delete();
                }
            }
        }
        
        // Recover all tags from the attribute
        for (TaggedValue theTag : theAttribute.getTag ()) {
            TagType attributeTagType = theTag.getDefinition ();
            String tagTypeName = attributeTagType != null ? attributeTagType.getName () : null;
            if (tagTypeName != null) {
                if (tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAARRAYDIMENSION) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVABIND) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVABYTE) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVALONG) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVASHORT) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER) ||
                        tagTypeName.equals (IOtherProfileElements.FEATURE_TYPE)) {
                    String moduleName = tagTypeName.equals (IOtherProfileElements.FEATURE_TYPE) ? IOtherProfileElements.MODULE_NAME : IJavaDesignerPeerModule.MODULE_NAME;
                    TaggedValue newTag = this.model.createTaggedValue (moduleName, tagTypeName, theParameter);
        
                    for (TagParameter theTagParameter : theTag.getActual ()) {
                        this.model.createTagParameter (theTagParameter.getValue (), newTag);
                    }
                }
            }
        }
        
        // The customization file might contain incoherent default collections for Attributes & Parameters:
        // Set or remove {type} of the parameter according to the Attribute settings.
        String type = JavaTypeManager.getInstance().getInterfaceType(theAttribute);
        if (type == null || type.isEmpty()) {
            if (JavaTypeManager.getInstance().isArray(theAttribute)) {
                type = "Array";
            }
        }
        if (type != null && !type.isEmpty()) {
            ModelUtils.setTagParameterAt(this.session, theParameter, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, type, 1);
        }
    }

    private void updateParameterType(final Parameter theParameter, final AssociationEnd theAssociationEnd) throws CustomException, ExtensionNotFoundException {
        Classifier target = theAssociationEnd.getTarget();
        if (! (target instanceof GeneralClass)) {
            return;
        }
        
        GeneralClass associationEndType = (GeneralClass) target;
        
        theParameter.setMultiplicityMin (theAssociationEnd.getMultiplicityMin ());
        theParameter.setMultiplicityMax (theAssociationEnd.getMultiplicityMax ());
        theParameter.setType (associationEndType);
        
        // Remove all tags on the parameter
        List<TaggedValue> parameterTags = new ArrayList<> (theParameter.getTag ());
        for (TaggedValue theTag : parameterTags) {
            TagType tagType = theTag.getDefinition ();
            String tagTypeName = tagType.getName ();
            if (tagTypeName != null) {
                if (tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFULLNAME) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR) ||
                        tagTypeName.equals (IOtherProfileElements.FEATURE_TYPE)) {
                    theTag.delete();
                }
            }
        }
        
        // Recover all tags from the association end
        for (TaggedValue theTag : theAssociationEnd.getTag ()) {
            TagType assocTagType = theTag.getDefinition ();
            String tagTypeName = assocTagType != null ? assocTagType.getName () : null;
            if (tagTypeName != null) {
                if (tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFULLNAME) ||
                        tagTypeName.equals (JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR) ||
                        tagTypeName.equals (IOtherProfileElements.FEATURE_TYPE)) {
                    String moduleName = tagTypeName.equals (IOtherProfileElements.FEATURE_TYPE) ? IOtherProfileElements.MODULE_NAME : IJavaDesignerPeerModule.MODULE_NAME;
                    TaggedValue newTag = this.model.createTaggedValue (moduleName, tagTypeName, theParameter);
                    for (TagParameter theTagParameter : theTag.getActual ()) {
                        this.model.createTagParameter (theTagParameter.getValue (), newTag);
                    }
                }
            }
        }
        
        // The customization file might contain incoherent default collections for AssociationEnd & Parameters:
        // Set or remove {type} of the parameter according to the AssociationEnd settings.
        String type = JavaTypeManager.getInstance().getInterfaceType(theAssociationEnd);
        if (type == null || type.isEmpty()) {
            if (JavaTypeManager.getInstance().isArray(theAssociationEnd)) {
                type = "Array";
            }
        }
        if (type != null && !type.isEmpty()) {
            // Set the type in the type tag
            ModelUtils.setTagParameterAt(this.session, theParameter, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, type, 1);
        }
        
        if (theAssociationEnd.getQualifier().size() > 0) {
            // Get the last qualifier name, to update the type tag
            for (Attribute qualifier : theAssociationEnd.getQualifier ()) {
                String qualifierType = null;
        
                try {
                    qualifierType = JavaTypeManager.getInstance ().computeSimpleType (this.session, qualifier, genFullName (qualifier)).toString();
        
                    String bindingParameters = "";
        
                    for (TaggedValue tag : ModelUtils.getAllTaggedValues (qualifier, JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)) {
                        for (Iterator<TagParameter> iterator = tag.getActual ().iterator () ; iterator.hasNext () ;) {
                            TagParameter theTagParameter = iterator.next ();
                            bindingParameters += theTagParameter.getValue ();
        
                            if (iterator.hasNext ()) {
                                bindingParameters += ",";
                            }
                        }
                    }
        
                    if (bindingParameters.length () > 0) {
                        qualifierType += "<";
                        qualifierType += bindingParameters;
                        qualifierType += ">";
                    }
                } catch (CustomException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
                }
        
                if (qualifierType != null) {
                    // Set the qualifier name in the type tag
                    ModelUtils.setTagParameterAt(this.session, theParameter, IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, qualifierType.toString(), 2);
                }
            }
        }
    }

    private void updateGetter(final Attribute theAttribute, final Operation theGetter) throws CustomException, ExtensionNotFoundException {
        // Set the correct owner if necessary
        Classifier correctOwner = theAttribute.getOwner ();
        if (!correctOwner.equals (theGetter.getOwner ()) && correctOwner.getStatus().isModifiable()) {
            theGetter.setOwner (correctOwner);
        }
        
        String attributeName = JavaDesignerUtils.getJavaName (theAttribute);
        String newGetterName = JavaTypeManager.getInstance ().getDefaultGetterName (this.session, theAttribute, false);
        theGetter.setName (newGetterName);
        
        // Update visibility
        //VisibilityMode newVisibility = getGetterVisibility (theAttribute);
        //theGetter.setVisibility (newVisibility);
        
        // Update static modifier
        boolean isIsClass = theAttribute.isIsClass () && !(correctOwner instanceof Interface);
        theGetter.setIsClass (isIsClass);
        
        // Create the return parameter if necessary
        Parameter returnParameter = theGetter.getReturn ();
        if (returnParameter == null) {
            returnParameter = this.model.createParameter ();
            theGetter.setReturn (returnParameter);
        }
        
        // Update the output type with the attribute type
        updateParameterType (returnParameter, theAttribute);
        
        if (!(correctOwner instanceof Interface)) {
            // Update the code
            Note codeNote = theGetter.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
            Note returnNote = theGetter.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED);
            if (codeNote == null && returnNote == null) { // If no note exists,
                // create it
                this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE, theGetter, this.AUTOMATIC_UPDATE_COMMENT);
                this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED, theGetter, getDefaultGetterCode (attributeName, theAttribute));
            } else {
                if (codeNote != null) {
                    String codeNoteContent = codeNote.getContent ();
                    if (codeNoteContent.contains (this.AUTOMATIC_UPDATE_COMMENT)) {
                        // automagic comment found, update both codeNote and returnNote to default
                        codeNote.setContent (this.AUTOMATIC_UPDATE_COMMENT);
                        if (returnNote == null) {
                            returnNote = this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED, theGetter,getDefaultGetterCode (attributeName, theAttribute));
                        } else {
                            returnNote.setContent (getDefaultGetterCode (attributeName, theAttribute));
                        }
                    }
                }
            }
        } else {
            // Java operations in interfaces should always be abstract
            theGetter.setIsAbstract(true);
        }
        
        // Add the stereotype if necessary
        if (!theGetter.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
            Stereotype obStereotype = this.session.getMetamodelExtensions ().getStereotype (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER, theGetter.getMClass().getMetamodel().getMClass(Operation.class));
            theGetter.getExtension().add (obStereotype);
        }
        
        // Deal with NoCode tag
        if (JavaDesignerUtils.isNoCode (theAttribute)) {
            this.model.createTaggedValue (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE, theGetter);
        } else {
            theGetter.removeTags(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        }
    }

    private void updateGetter(final AssociationEnd theAssociationEnd, final Operation theGetter) throws CustomException, ExtensionNotFoundException {
        // Set the correct owner if necessary
        Classifier correctOwner = theAssociationEnd.getSource ();
        if (!correctOwner.equals (theGetter.getOwner ()) && correctOwner.getStatus().isModifiable()) {
            theGetter.setOwner (correctOwner);
        }
        
        String associationEndName = JavaDesignerUtils.getJavaName (theAssociationEnd);
        String newGetterName = JavaTypeManager.getInstance ().getDefaultGetterName (this.session, theAssociationEnd, false);
        theGetter.setName (newGetterName);
        
        // Update visibility
        //VisibilityMode newVisibility = getGetterVisibility (theAssociationEnd);
        //theGetter.setVisibility (newVisibility);
        
        // Update static modifier
        boolean isIsClass = theAssociationEnd.isIsClass () && !(correctOwner instanceof Interface);
        theGetter.setIsClass (isIsClass);
        
        // Create the return parameter if necessary
        Parameter returnParameter = theGetter.getReturn ();
        if (returnParameter == null) {
            returnParameter = this.model.createParameter ();
            theGetter.setReturn (returnParameter);
        }
        
        // Update the output type with the attribute type
        updateParameterType (returnParameter, theAssociationEnd);
        
        if (!(correctOwner instanceof Interface)) {
            // Update the code
            Note codeNote = theGetter.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
            Note returnNote = theGetter.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED);
            if (codeNote == null && returnNote == null) { // If no note exists,
                // create it
                this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE, theGetter, this.AUTOMATIC_UPDATE_COMMENT);
                this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED, theGetter, getDefaultGetterCode (associationEndName, theAssociationEnd));
            } else {
                if (codeNote != null) {
                    String codeNoteContent = codeNote.getContent ();
                    if (codeNoteContent.contains (this.AUTOMATIC_UPDATE_COMMENT)) {
                        // automagic comment found, update both codeNote and returnNote to default
                        codeNote.setContent (this.AUTOMATIC_UPDATE_COMMENT);
                        if (returnNote == null) {
                            returnNote = this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED, theGetter,getDefaultGetterCode (associationEndName, theAssociationEnd));
                        } else {
                            returnNote.setContent (getDefaultGetterCode (associationEndName, theAssociationEnd));
                        }
                    }
                }
            }
        } else {
            // Java operations in interfaces should always be abstract
            theGetter.setIsAbstract(true);
        }
        
        // Add the stereotype if necessary
        if (!theGetter.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
            Stereotype obStereotype = this.session.getMetamodelExtensions ().getStereotype (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER, theGetter.getMClass().getMetamodel().getMClass(Operation.class));
            theGetter.getExtension().add (obStereotype);
        }
        
        // Deal with NoCode tag
        if (JavaDesignerUtils.isNoCode (theAssociationEnd)) {
            this.model.createTaggedValue (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE, theGetter);
        } else {
            theGetter.removeTags(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        }
    }

    private void updateSetter(final Attribute theAttribute, final Operation theSetter) throws CustomException, ExtensionNotFoundException {
        // Set the correct owner if necessary
        Classifier correctOwner = theAttribute.getOwner ();
        if (!correctOwner.equals (theSetter.getOwner ()) && correctOwner.getStatus().isModifiable()) {
            theSetter.setOwner (correctOwner);
        }
        
        String attributeName = JavaDesignerUtils.getJavaName (theAttribute);
        String newSetterName = JavaTypeManager.getInstance ().getDefaultSetterName (this.session, theAttribute, false);
        theSetter.setName (newSetterName);
        
        // Update visibility
        //VisibilityMode newVisibility = getSetterVisibility (theAttribute);
        //theSetter.setVisibility (newVisibility);
        
        // Update static modifier
        boolean isIsClass = theAttribute.isIsClass () && !(correctOwner instanceof Interface);
        theSetter.setIsClass (isIsClass);
        
        // Delete the return parameter if necessary
        Parameter returnParameter = theSetter.getReturn ();
        if (returnParameter != null) {
            returnParameter.delete ();
        }
        
        List<Parameter> IOParameters = theSetter.getIO ();
        Parameter firstParameter;
        if (IOParameters.size () == 0) {
            // Create and add the first parameter of the getter
            firstParameter = this.model.createParameter ();
            firstParameter.setName ("value");
            theSetter.getIO().add (firstParameter);
        } else {
            firstParameter = IOParameters.get (0);
        }
        
        // Update the first parameter of the setter only
        updateParameterType (firstParameter, theAttribute);
        
        // Keep the firstParameterName
        String firstParameterName = firstParameter.getName ();
        
        if (!(correctOwner instanceof Interface)) {
            // Update the code
            String defaultCodeNoteContent = this.AUTOMATIC_UPDATE_COMMENT +
                    NL +
                    getDefaultSetterCode (attributeName, firstParameterName, theAttribute);
            Note codeNote = theSetter.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVACODE);
            if (codeNote == null) { // If no note exists, create it
                codeNote = this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVACODE, theSetter, defaultCodeNoteContent);
            } else {
                String codeNoteContent = codeNote.getContent ();
        
                // If the java code is empty, put the default setter code
                if (codeNoteContent.isEmpty () ||
                        codeNoteContent.contains (this.AUTOMATIC_UPDATE_COMMENT)) {
                    // Check if the code is the default code
                    codeNote.setContent (defaultCodeNoteContent);
                }
            }
        } else {
            // Java operations in interfaces should always be abstract
            theSetter.setIsAbstract(true);
        }
        
        // Add the stereotype if necessary
        if (!theSetter.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
            Stereotype obStereotype = this.session.getMetamodelExtensions ().getStereotype (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER, theSetter.getMClass().getMetamodel().getMClass(Operation.class));
            theSetter.getExtension().add (obStereotype);
        }
        
        // Deal with NoCode tag
        if (JavaDesignerUtils.isNoCode (theAttribute)) {
            this.model.createTaggedValue (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE, theSetter);
        } else {
            theSetter.removeTags(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        }
    }

    private void updateSetter(final AssociationEnd theAssociationEnd, final Operation theSetter) throws CustomException, ExtensionNotFoundException {
        // Set the correct owner if necessary
        Classifier correctOwner = theAssociationEnd.getSource ();
        if (!correctOwner.equals (theSetter.getOwner ()) && correctOwner.getStatus().isModifiable()) {
            theSetter.setOwner (correctOwner);
        }
        
        String associationEndName = JavaDesignerUtils.getJavaName (theAssociationEnd);
        String newSetterName = JavaTypeManager.getInstance ().getDefaultSetterName (this.session, theAssociationEnd, false);
        theSetter.setName (newSetterName);
        
        // Update visibility
        //VisibilityMode newVisibility = getSetterVisibility (theAssociationEnd);
        //theSetter.setVisibility (newVisibility);
        
        // Update static modifier
        boolean isIsClass = theAssociationEnd.isIsClass () && !(correctOwner instanceof Interface);
        theSetter.setIsClass (isIsClass);
        
        // Delete the return parameter if necessary
        Parameter returnParameter = theSetter.getReturn ();
        if (returnParameter != null) {
            returnParameter.delete ();
        }
        
        List<Parameter> IOParameters = theSetter.getIO ();
        Parameter firstParameter;
        if (IOParameters.size () == 0) {
            // Create and add the first parameter of the getter
            firstParameter = this.model.createParameter ();
            firstParameter.setName ("value");
            theSetter.getIO().add (firstParameter);
        } else {
            firstParameter = IOParameters.get (0);
        
            // Delete other IOParameters if they exist
            for (int i = 1 ; i < IOParameters.size () ; i++) {
                IOParameters.get (i).delete();
            }
        }
        
        // Update the first parameter of the setter
        updateParameterType (firstParameter, theAssociationEnd);
        
        // Keep the firstParameterName
        String firstParameterName = firstParameter.getName ();
        
        if (!(correctOwner instanceof Interface)) {
            // Update the code
            String defaultCodeNoteContent = this.AUTOMATIC_UPDATE_COMMENT +
                    NL +
                    getDefaultSetterCode (associationEndName, firstParameterName, theAssociationEnd);
            Note codeNote = theSetter.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVACODE);
            if (codeNote == null) { // If no note exists, create it
                codeNote = this.model.createNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVACODE, theSetter, defaultCodeNoteContent);
            } else {
                String codeNoteContent = codeNote.getContent ();
        
                // If the java code is empty, put the default setter code
                if (codeNoteContent.isEmpty () ||
                        codeNoteContent.contains (this.AUTOMATIC_UPDATE_COMMENT)) {
                    // Check if the code is the default code
                    codeNote.setContent (defaultCodeNoteContent);
                }
            }
        } else {
            // Java operations in interfaces should always be abstract
            theSetter.setIsAbstract(true);
        }
        
        // Add the stereotype if necessary
        if (!theSetter.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
            Stereotype obStereotype = this.session.getMetamodelExtensions ().getStereotype (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER, theSetter.getMClass().getMetamodel().getMClass(Operation.class));
            theSetter.getExtension().add (obStereotype);
        }
        
        // Deal with NoCode tag
        if (JavaDesignerUtils.isNoCode (theAssociationEnd)) {
            this.model.createTaggedValue (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE, theSetter);
        } else {
            theSetter.removeTags(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE);
        }
    }

    public List<Operation> updateAccessors(final Attribute theAttribute, final boolean createNewAccessors) throws CustomException, ExtensionNotFoundException {
        List<Operation> getters = new ArrayList<> ();
        List<Operation> setters = new ArrayList<> ();
        List<Operation> ret = new ArrayList<>();
        
        for (Dependency theDependency : theAttribute.getImpactedDependency ()) {
            ModelElement impactedElement = theDependency.getImpacted ();
        
            // Check stereotypes only for operations
            if (impactedElement instanceof Operation) {
                if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    getters.add ((Operation) impactedElement);
                } else if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    setters.add ((Operation) impactedElement);
                }
            }
        }
        
        KindOfAccess changeable = theAttribute.getChangeable ();
        if (JavaDesignerUtils.getNearestNameSpace (theAttribute).isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAANNOTATION)) {
            changeable = KindOfAccess.ACCESNONE; // TODO display warning
        } else if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
            if (changeable == KindOfAccess.READWRITE) {
                changeable = KindOfAccess.READ;
            } else if (changeable == KindOfAccess.WRITE) {
                changeable = KindOfAccess.ACCESNONE;
            }
        }
        
        switch (changeable) {
        case READ:
            // If no getter exists, create a new one
            if (getters.isEmpty ()) {
                if (createNewAccessors) {
                    createGetter (theAttribute);
                }
            } else { // Else update them
                for (Operation theGetter : getters) {
                    updateGetter (theAttribute, theGetter);
                }
            }
        
            // If some setter exists, destroy them
            if (!setters.isEmpty () && createNewAccessors) {
                for (Operation theSetter : setters) {
                    theSetter.delete ();
                }
            }
            break;
        case WRITE:
            // If some getter exists, destroy them
            if (!getters.isEmpty () && createNewAccessors) {
                for (Operation theGetter : getters) {
                    theGetter.delete();
                }
            }
        
            // If no setter exists, create a new one
            if (setters.isEmpty ()) {
                if (createNewAccessors) {
                    createSetter (theAttribute);
                }
            } else { // Else update them
                for (Operation theSetter : setters) {
                    updateSetter (theAttribute, theSetter);
                }
            }
        
            break;
        case READWRITE:
            // If no getter exists, create a new one
            if (getters.isEmpty ()) {
                if (createNewAccessors) {
                    createGetter (theAttribute);
                }
            } else { // Else update them
                for (Operation theGetter : getters) {
                    updateGetter (theAttribute, theGetter);
                }
            }
        
            // If no setter exists, create a new one
            if (setters.isEmpty ()) {
                if (createNewAccessors) {
                    createSetter (theAttribute);
                }
            } else { // Else update them
                for (Operation theSetter : setters) {
                    updateSetter (theAttribute, theSetter);
                }
            }
        
            break;
        default: { // In other case, delete all accessors
        
            // If some getter exists, destroy them
            if (!getters.isEmpty () && createNewAccessors) {
                for (Operation theGetter : getters) {
                    theGetter.delete ();
                }
            }
        
            // If some setter exists, destroy them
            if (!setters.isEmpty () && createNewAccessors) {
                for (Operation theSetter : setters) {
                    theSetter.delete ();
                }
            }
        }
        }
        
        for (Dependency theDependency : theAttribute.getImpactedDependency ()) {
            ModelElement impactedElement = theDependency.getImpacted ();
        
            // Check stereotypes only for operations
            if (impactedElement instanceof Operation) {
                if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    ret.add ((Operation) impactedElement);
                } else if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    ret.add ((Operation) impactedElement);
                }
            }
        }
        return ret;
    }

    public List<Operation> updateAccessors(final AssociationEnd theAssociationEnd, final boolean createNewAccessors) throws CustomException, ExtensionNotFoundException {
        List<Operation> getters = new ArrayList<> ();
        List<Operation> setters = new ArrayList<> ();
        
        for (Dependency theDependency : theAssociationEnd.getImpactedDependency ()) {
            ModelElement impactedElement = theDependency.getImpacted ();
        
            // Check stereotypes only for operations
            if (impactedElement instanceof Operation) {
                if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    getters.add ((Operation) impactedElement);
                } else if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    setters.add ((Operation) impactedElement);
                }
            }
        }
        
        if (theAssociationEnd.getTarget() != null) {
            KindOfAccess changeable = theAssociationEnd.getChangeable ();
            if (JavaDesignerUtils.getNearestNameSpace (theAssociationEnd).isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAANNOTATION)) {
                changeable = KindOfAccess.ACCESNONE;
            } else if (theAssociationEnd.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL)) {
                if (changeable == KindOfAccess.READWRITE) {
                    changeable = KindOfAccess.READ;
                } else if (changeable == KindOfAccess.WRITE) {
                    changeable = KindOfAccess.ACCESNONE;
                }
            }
        
            switch (changeable) {
            case READ:
                // If no getter exists, create a new one
                if (getters.isEmpty ()) {
                    if (createNewAccessors) {
                        createGetter (theAssociationEnd);
                    }
                } else { // Else update them
                    for (Operation theGetter : getters) {
                        updateGetter (theAssociationEnd, theGetter);
                    }
                }
        
                // If some setter exists, destroy them
                if (!setters.isEmpty () && createNewAccessors) {
                    for (Operation theSetter : setters) {
                        theSetter.delete ();
                    }
                }
                break;
            case WRITE:
                // If some getter exists, destroy them
                if (!getters.isEmpty () && createNewAccessors) {
                    for (Operation theGetter : getters) {
                        theGetter.delete ();
                    }
                }
        
                // If no setter exists, create a new one
                if (setters.isEmpty ()) {
                    if (createNewAccessors) {
                        createSetter (theAssociationEnd);
                    }
                } else { // Else update them
                    for (Operation theSetter : setters) {
                        updateSetter (theAssociationEnd, theSetter);
                    }
                }
        
                break;
            case READWRITE:
                // If no getter exists, create a new one
                if (getters.isEmpty ()) {
                    if (createNewAccessors) {
                        createGetter (theAssociationEnd);
                    }
                } else { // Else update them
                    for (Operation theGetter : getters) {
                        updateGetter (theAssociationEnd, theGetter);
                    }
                }
        
                // If no setter exists, create a new one
                if (setters.isEmpty ()) {
                    if (createNewAccessors) {
                        createSetter (theAssociationEnd);
                    }
                } else { // Else update them
                    for (Operation theSetter : setters) {
                        updateSetter (theAssociationEnd, theSetter);
                    }
                }
        
                break;
            default: { // In other case, delete all accessors
        
                // If some getter exists, destroy them
                if (!getters.isEmpty () && createNewAccessors) {
                    for (Operation theGetter : getters) {
                        theGetter.delete ();
                    }
                }
        
                // If some setter exists, destroy them
                if (!setters.isEmpty () && createNewAccessors) {
                    for (Operation theSetter : setters) {
                        theSetter.delete ();
                    }
                }
            }
            }
        } else {
            // If some getter exists, destroy them
            if (!getters.isEmpty () && createNewAccessors) {
                for (Operation theGetter : getters) {
                    theGetter.delete ();
                }
            }
        
            // If some setter exists, destroy them
            if (!setters.isEmpty () && createNewAccessors) {
                for (Operation theSetter : setters) {
                    theSetter.delete ();
                }
            }
        }
        
        List<Operation> ret = new ArrayList<>();
        for (Dependency theDependency : theAssociationEnd.getImpactedDependency ()) {
            ModelElement impactedElement = theDependency.getImpacted ();
        
            // Check stereotypes only for operations
            if (impactedElement instanceof Operation) {
                if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    ret.add ((Operation) impactedElement);
                } else if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    ret.add ((Operation) impactedElement);
                }
            }
        }
        return ret;
    }

    /**
     * Check all operations, and deletes getters and setters that are no more linked to Attributes/AssociationEnds.
     * @return True if at least one operation was deleted.
     */
    public boolean deleteAccessors(final Classifier theClassifier) {
        boolean ret = false;
        
        for (Operation theOperation : theClassifier.getOwnedOperation()) {
            if (theOperation.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                boolean isStereotyped = false;
                for (Dependency dep : theOperation.getDependsOnDependency ()) {
                    if (dep.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                        isStereotyped = true;
                        break;
                    }
                }
                if (!isStereotyped) {
                    theOperation.delete ();
                    ret = true;
                }
            } else if (theOperation.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                boolean isStereotyped = false;
                for (Dependency dep : theOperation.getDependsOnDependency ()) {
                    if (dep.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                        isStereotyped = true;
                        break;
                    }
                }
                if (!isStereotyped) {
                    theOperation.delete ();
                    ret = true;
                }
            }
        }
        return ret;
    }

    private boolean genFullName(final ModelElement theModelElement) {
        boolean value = this.FULLNAMEGENERATION;
        return value ||
                                                theModelElement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAFULLNAME);
    }

    /**
     * Initialize the parameter values in the accessor manager.
     * @param javaConfig The module configuration containing the real values of these parameters.
     */
    public void init(final IModuleUserConfiguration javaConfig) {
        this.FULLNAMEGENERATION = javaConfig.getParameterValue (JavaDesignerParameters.FULLNAMEGENERATION).equalsIgnoreCase ("TRUE");
        this.PUBLICACCESSORVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.PUBLICACCESSORVISIBILITY);
        this.PROTECTEDACCESSORVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.PROTECTEDACCESSORVISIBILITY);
        this.PRIVATEACCESSORVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.PRIVATEACCESSORVISIBILITY);
        this.FRIENDLYACCESSORVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.FRIENDLYACCESSORVISIBILITY);
        this.PUBLICMODIFIERVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.PUBLICMODIFIERVISIBILITY);
        this.PROTECTEDMODIFIERVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.PROTECTEDMODIFIERVISIBILITY);
        this.PRIVATEMODIFIERVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.PRIVATEMODIFIERVISIBILITY);
        this.FRIENDLYMODIFIERVISIBILITY = javaConfig.getParameterValue (JavaDesignerParameters.FRIENDLYMODIFIERVISIBILITY);
    }

    public boolean deleteAccessors(final Feature feature) {
        boolean ret = false;
        
        for (Dependency theDependency : feature.getImpactedDependency ()) {
            ModelElement impactedElement = theDependency.getImpacted ();
        
            // Check stereotypes only for operations
            if (impactedElement instanceof Operation) {
                if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    impactedElement.delete();
                    ret = true;
                } else if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    impactedElement.delete ();
                    ret = true;
                }
            }
        }
        return ret;
    }

}
