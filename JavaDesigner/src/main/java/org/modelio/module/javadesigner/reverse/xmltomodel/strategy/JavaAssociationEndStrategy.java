package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.model.JaxbAssociationEnd;
import com.modelio.module.xmlreverse.model.JaxbClassType;
import com.modelio.module.xmlreverse.model.JaxbDestination;
import com.modelio.module.xmlreverse.model.JaxbTaggedValue;
import com.modelio.module.xmlreverse.model.JxbVisibilityMode;
import com.modelio.module.xmlreverse.strategy.AssociationEndStrategy;
import com.modelio.module.xmlreverse.utils.TypeConverter;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AggregationKind;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaFeatureReverseUtils;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaAssociationEndStrategy extends AssociationEndStrategy {
    public JavaAssociationEndStrategy(final IModelingSession session) {
        super(session);
    }

    @Override
    public AssociationEnd getCorrespondingElement(final JaxbAssociationEnd jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        Classifier classifierOwner = (Classifier) owner;
        
        for (AssociationEnd feat : classifierOwner.getOwnedEnd()) {
            if (feat.getName().equals(jaxb_element.getName()) && feat.getTarget() != null) {
                if (!repository.isRecordedElement(feat) && !JavaDesignerUtils.isNoCode(feat)) {
                    return feat;
                }
            }
        }
        
        AssociationEnd new_element = this.model.createAssociationEnd();
        return new_element;
    }

    @Override
    public List<MObject> updateProperties(final JaxbAssociationEnd jaxb_element, final AssociationEnd modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        modelio_element.setSource((Classifier) owner);
        
        List<MObject> ret = null;
        boolean willConvertToAttribute = false;
        // Is there an existing attribute with the same objid ?
        Attribute existingAtt = jaxb_element.getObjid() != null ? this.session.findElementById(Attribute.class, jaxb_element.getObjid()) : null;
        if (existingAtt != null && existingAtt.isValid()) {
            // Yes, keep it warm for the post treatment phase
            willConvertToAttribute = true;
            modelio_element.setMultiplicityMin(existingAtt.getMultiplicityMin());
            modelio_element.setMultiplicityMax(existingAtt.getMultiplicityMax());
            ret = new ArrayList<>();
            ret.add(existingAtt);
            // continue the update
        }
        String oldName = modelio_element.getName();
        boolean hasJavaName = modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANAME);
        
        // We must merge "type" tags into one tag with several parameters
        JaxbTaggedValue arrayTag = null;
        for (Object sub : new ArrayList<>(jaxb_element.getBaseTypeOrClassTypeOrNote())) {
            if (sub instanceof JaxbTaggedValue) {
                JaxbTaggedValue tag = (JaxbTaggedValue) sub;
                if (IOtherProfileElements.FEATURE_TYPE.equals(tag.getTagType())) {
                    if (arrayTag == null) {
                        arrayTag = tag;
                    } else {
                        arrayTag.getTagParameter().addAll(tag.getTagParameter());
                        jaxb_element.getBaseTypeOrClassTypeOrNote().remove(tag);
                    }
                }
            }
        }
        
        // If Array is one of the values, it must be the first one in the list
        if (arrayTag != null) {
            List<String> parameters = arrayTag.getTagParameter();
            for (int i = 1; i < parameters.size(); i++) {
                // Swap values
                if (parameters.get(i).equals("Array")) {
                    parameters.set(i, parameters.get(0));
                    parameters.set(0, "Array");
                }
            }
        }
        
        // Store old visibility to avoid problems with properties.
        VisibilityMode oldVisibility = modelio_element.getVisibility();
        
        String multMin = modelio_element.getMultiplicityMin();
        String multMax = modelio_element.getMultiplicityMax();
        
        List<MObject> ret2 = super.updateProperties(jaxb_element, modelio_element, owner, repository);
        if (ret == null) {
            ret = ret2;
        } else if (ret2 != null) {
            ret.addAll(ret2);
        }
        
        // Set multiplicity
        // undo super work except if multMin/multMax are empty.
        if (!multMin.isEmpty()) {
            modelio_element.setMultiplicityMin(multMin);
        }
        if (!multMax.isEmpty()) {
            modelio_element.setMultiplicityMax(multMax);
        }
        String jaxbMultiplicityMax = jaxb_element.getMultiplicityMax();
        // the only case is when the multiplicity max changes from 0,1 to * and
        // vice-versa
        // (Jaxb.getMultiplicityMin() is always 0 don't bother with it.)
        if (!"*".equals(jaxbMultiplicityMax)) {
            // jaxbmult is 0 or 1
            if (!"01".contains(multMax)) {
                // change from * to 0..1 (or 1..1 in case of an attribute)
                // (note if willConvertToAttribute is true, but in the end the
                // current assoc is not converted
                // to an attribute, it is not totally right to set mult. min to
                // "1" but it doesn't worth the work to change it)
                modelio_element.setMultiplicityMin(willConvertToAttribute ? "1" : "0");
                modelio_element.setMultiplicityMax("1");
            }
        } else {
            // jaxbmult is *
            if ("01".contains(multMax)) {
                // change from 0..1 to *
                modelio_element.setMultiplicityMin("0");
                modelio_element.setMultiplicityMax(jaxbMultiplicityMax);
            }
        }
        
        // Set the old visibility again. It will be replace in the post
        // treatment
        modelio_element.setVisibility(oldVisibility);
        
        handleMultipleTags(jaxb_element);
        
        modelio_element.setChangeable(KindOfAccess.ACCESNONE);
        
        if (hasJavaName) {
            try {
                modelio_element.setName(oldName);
        
                org.modelio.module.javadesigner.utils.ModelUtils.setFirstTagParameter(
                        this.session,
                        modelio_element,
                        IJavaDesignerPeerModule.MODULE_NAME,
                        JavaDesignerTagTypes.FEATURE_JAVANAME,
                        jaxb_element.getName());
                if (ret == null) {
                    ret = new ArrayList<>();
                }
                ret.add(modelio_element.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANAME));
            } catch (ExtensionNotFoundException | ElementNotUniqueException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        
        if (owner instanceof Interface) {
            // Change jaxb visibility, postTreatment sets the visibility on the modelio element
            jaxb_element.setVisibility(VisibilityMode.PUBLIC.name());
        
            modelio_element.setIsClass(true);
            try {
                TaggedValue newTag = org.modelio.module.javadesigner.utils.ModelUtils.setTaggedValue(this.session,
                        modelio_element,
                        IJavaDesignerPeerModule.MODULE_NAME,
                        JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL, true);
                if (ret == null) {
                    ret = new ArrayList<>();
                }
                ret.add(newTag);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        return ret;
    }

    /**
     * TODO this should be done in the ANTLR -> XML part...
     */
    private void handleMultipleTags(final JaxbAssociationEnd jaxb_element) {
        JaxbTaggedValue firstBindTag = null;
        
        List<JaxbTaggedValue> toRemove = new ArrayList<>();
        List<Object> sub_elements = jaxb_element.getBaseTypeOrClassTypeOrNote();
        for (Object sub_element : sub_elements) {
            if (sub_element instanceof JaxbTaggedValue) {
                JaxbTaggedValue currentTag = (JaxbTaggedValue) sub_element;
        
                if (currentTag.getTagType().equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND)) {
                    if (firstBindTag == null) {
                        firstBindTag = currentTag;
                    } else {
                        firstBindTag.getTagParameter().addAll(currentTag.getTagParameter());
                        toRemove.add(currentTag);
                    }
                }
            }
        }
        
        sub_elements.removeAll(toRemove);
    }

    @Override
    public void postTreatment(final JaxbAssociationEnd jaxb_element, final AssociationEnd modelio_element, final IReadOnlyRepository repository) {
        boolean hasNoInit = true;
        MObject type = null;
        List<String> typeExpr = null;
        
        if (modelio_element == null) {
            return;
        }
        
        // Typage de l'attribut
        for (Object sub : jaxb_element.getBaseTypeOrClassTypeOrNote()) {
            if (sub instanceof JAXBElement) {
                JAXBElement<?> jaxb_sub = (JAXBElement<?>) sub;
                if ("base-type".equals(jaxb_sub.getName().getLocalPart())) {
                    type = TypeConverter.getBaseType((String) jaxb_sub.getValue(), this.model);
        
                    AssociationEnd opposite = modelio_element.getOpposite();
                    if (opposite == null) {
                        opposite = this.model.createAssociationEnd();
                        opposite.setOpposite(modelio_element);
                        modelio_element.setOpposite(opposite);
                    }
                    modelio_element.setTarget((DataType) type, true);
                } else if ("value".equals(jaxb_sub.getName().getLocalPart())) {
                    String value = (String) jaxb_sub.getValue();
                    value = value.trim();
        
                    try {
                        modelio_element.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE, value);
                    } catch (ExtensionNotFoundException e) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                    }
                    hasNoInit = false;
        
                }
            } else if (sub instanceof JaxbClassType) {
                JaxbDestination jaxb_destination = ((JaxbClassType) sub).getDestination();
                type = repository.getElementById(jaxb_destination.getRefid());
                if (type == null) {
                    type = repository.getElementByNamespace(jaxb_destination,
                            Classifier.class, this.session);
                    if (type == null) {
                        type = repository.createNamespace(jaxb_destination, (ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                }
                // These two cases will be handled at the end of this method,
                // using the conversion to an attribute
                if (!(type instanceof Enumeration) && !(type instanceof TemplateParameter)) {
                    AssociationEnd opposite = modelio_element.getOpposite();
                    if (opposite == null) {
                        opposite = this.model.createAssociationEnd();
                        opposite.setOpposite(modelio_element);
                        modelio_element.setOpposite(opposite);
                    }
                    modelio_element.setTarget((Classifier) type, true);
                }
            } else if (sub instanceof JaxbTaggedValue) {
                JaxbTaggedValue sub_element = (JaxbTaggedValue) sub;
        
                // The type expr tag cannot be created on a real association
                // end... it will be converted into an
                // attribute later
                if (sub_element.getTagType().equals(JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR)) {
                    typeExpr = sub_element.getTagParameter();
                }
            }
        }
        
        Association new_assoc = modelio_element.getAssociation();
        if (new_assoc == null) {
            new_assoc = this.model.createAssociation();
            new_assoc.getEnd().add(modelio_element);
            if (modelio_element.getOpposite() != null) {
                new_assoc.getEnd().add(modelio_element.getOpposite());
            }
        }
        
        try {
            org.modelio.module.javadesigner.utils.ModelUtils.setTaggedValue(
                    this.session,
                    modelio_element,
                    IJavaDesignerPeerModule.MODULE_NAME,
                    JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE, hasNoInit);
            if (hasNoInit) {
                modelio_element.removeNotes(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE);
            }
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
        }
        
        VisibilityMode newVisibility = null;
        String visibility = jaxb_element.getVisibility();
        if (visibility != null) {
            if (visibility.equalsIgnoreCase(JxbVisibilityMode.Public.name())) {
                newVisibility = VisibilityMode.PUBLIC;
            } else if (visibility.equalsIgnoreCase(JxbVisibilityMode.Private.name())) {
                newVisibility = VisibilityMode.PRIVATE;
            } else if (visibility.equalsIgnoreCase(JxbVisibilityMode.Protected.name())) {
                newVisibility = VisibilityMode.PROTECTED;
            } else if (visibility.equalsIgnoreCase(JxbVisibilityMode.Package_Visibility.name())) {
                newVisibility = VisibilityMode.PACKAGEVISIBILITY;
            } else if (visibility.equalsIgnoreCase(JxbVisibilityMode.Visibility_Undefined.name())) {
                newVisibility = VisibilityMode.VISIBILITYUNDEFINED;
            }
        }
        
        // Handle Properties
        String annotations = modelio_element.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION);
        if (annotations != null && annotations.contains("@mdl.prop")) {
            try {
                // Remove this annotation from the note
                annotations = annotations.replace("@mdl.prop", "").trim();
                if (annotations.isEmpty()) {
                    modelio_element.removeNotes(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION);
                } else {
                    modelio_element.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION,annotations);
                }
        
                // Update visibility according to the model<==> code rules
                if (newVisibility == JavaTypeManager.getInstance().getPropertyCodeVisibility(modelio_element.getVisibility())) {
                    // The current visibility corresponds to a correct code
                    // visibility, no need to change the model
                    newVisibility = modelio_element.getVisibility();
                } else {
                    // Invalid current visibility, get the correct one from the
                    // config file
                    newVisibility = JavaTypeManager.getInstance().getPropertyModelVisibility(newVisibility);
                }
        
                // Add the stereotype
                org.modelio.module.javadesigner.utils.ModelUtils.addStereotype(modelio_element, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
            }
        }
        
        // Set the proper visibility
        if (newVisibility != null) {
            modelio_element.setVisibility(newVisibility);
        }
        // Is there an existing attribute with the same objid ?
        MObject existingElt = jaxb_element.getObjid() != null ? this.session.findElementById(Attribute.class, jaxb_element.getObjid()) : null;
        Attribute att = null;
        if (modelio_element.getOpposite() == null || type == null || JavaFeatureReverseUtils.getInstance().isPrimitive(type)) {
        
            // primitive type : change for an attribute
        
            if (existingElt != null && existingElt.isValid()) {
                // Attribute already exists, update it with the new modelio_element
                att = (Attribute) existingElt;
                // keep changeable of the att because in this case it is up to date (see JavaOperationStrategy.handleAccessorLink)
                KindOfAccess savedAccess = att.getChangeable();
                // update the existing attribute with the reversed associationEnd
                JavaFeatureReverseUtils.getInstance().copyAssociationEndToAttribute(modelio_element, att, type);
                // restore changeable
                att.setChangeable(savedAccess);
            } else {
                // No, convert the associationEnd into an attribute
                att = JavaFeatureReverseUtils.getInstance().convertAssociationEndToAttribute(modelio_element, type);
            }
        
            modelio_element.delete();
        } else if (existingElt != null && existingElt.isValid()) {
            if (type instanceof Interface) {
                // the type is an Interface, we can't decide if it should be an
                // association or attribute : keep the existing element
                att = (Attribute) existingElt;
                JavaFeatureReverseUtils.getInstance().copyAssociationEndToAttribute(modelio_element, att, type);
                modelio_element.delete();
            } else {
                // the association is kept : delete the existing attribute
                // Translation of an Attribute to an Association should produce
                // an aggregation
                modelio_element.setAggregation(AggregationKind.KINDISCOMPOSITION);
        
                existingElt.delete();
            }
        }
        
        if (typeExpr != null && att != null) {
            try {
                att.putTagValues(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR, typeExpr);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
            }
        }
    }

    @Override
    public void deleteSubElements(final JaxbAssociationEnd jaxb_element, final AssociationEnd modelio_element, final Collection<MObject> element_todelete, final IReadOnlyRepository repository) {
        for (MObject elt : element_todelete) {
            if (elt instanceof Note) {
                Note note = (Note) elt;
                NoteType noteModel = note.getModel();
                if (noteModel != null && noteModel.getName().equals(JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE)) {
                    element_todelete.remove(elt);
                    break;
                }
            }
        }
        
        super.deleteSubElements(modelio_element, element_todelete, repository);
    }

}
