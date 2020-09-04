package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.JaxbAttribute;
import com.modelio.module.xmlreverse.model.JaxbClassType;
import com.modelio.module.xmlreverse.model.JaxbDestination;
import com.modelio.module.xmlreverse.model.JaxbTaggedValue;
import com.modelio.module.xmlreverse.model.JxbVisibilityMode;
import com.modelio.module.xmlreverse.strategy.AttributStrategy;
import com.modelio.module.xmlreverse.utils.TypeConverter;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaFeatureReverseUtils;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaAttributeStrategy extends AttributStrategy {
    public JavaAttributeStrategy(final IModelingSession session) {
        super(session);
    }

    @Override
    public Attribute getCorrespondingElement(final JaxbAttribute jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if (owner instanceof Classifier) {
            Classifier treeOwner = (Classifier) owner;
            for (Attribute sub_tree : treeOwner.getOwnedAttribute()) {
                if (sub_tree.getName().equals(jaxb_element.getName())) {
                    if (!repository.isRecordedElement(sub_tree) && !JavaDesignerUtils.isNoCode(sub_tree)) {
                        return sub_tree;
                    }
                }
            }
        } else if (owner instanceof AssociationEnd) {
            AssociationEnd treeOwner = (AssociationEnd) owner;
            try {
                // jaxb qualifier name contains the rank of qualifier
                int jQualRank = Integer.parseInt(jaxb_element.getName());
                int mQualRank = 0;
                for (Attribute sub_tree : treeOwner.getQualifier()) {
                    if (++mQualRank == jQualRank) {
                        if (!repository.isRecordedElement(sub_tree) && !JavaDesignerUtils.isNoCode(sub_tree)) {
                            return sub_tree;
                        }
                    }
                }
            } catch (NumberFormatException nfe) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(nfe);
            }
        }
        
        Attribute new_element = this.model.createAttribute();
        if (owner instanceof AssociationEnd) {
            new_element.setName("key");
        }
        return new_element;
    }

    @Override
    public List<MObject> updateProperties(final JaxbAttribute jaxb_element, final Attribute modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        List<MObject> ret = null;
        // Is there an existing associationEnd with the same objid ?
        AssociationEnd existingAssoc = jaxb_element.getObjid() != null ? this.session.findElementById(AssociationEnd.class, jaxb_element.getObjid()): null;
        if (existingAssoc != null && existingAssoc.isValid()) {
            // Yes, keep it warm for the post treatment phase
            modelio_element.setMultiplicityMin(existingAssoc
                    .getMultiplicityMin());
            modelio_element.setMultiplicityMax(existingAssoc
                    .getMultiplicityMax());
            ret = new ArrayList<>();
            ret.add(existingAssoc);
            // continue the update
        }
        
        String oldName = modelio_element.getName();
        boolean hasJavaName = modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANAME);
        
        // We must merge "type" tags into one tag with several parameters
        JaxbTaggedValue arrayTag = null;
        for (Object sub : new ArrayList<>(jaxb_element.getValueOrBaseTypeOrClassType())) {
            if (sub instanceof TaggedValue) {
                JaxbTaggedValue tag = (JaxbTaggedValue) sub;
                if (IOtherProfileElements.FEATURE_TYPE.equals(tag.getTagType())) {
                    if (arrayTag == null) {
                        arrayTag = tag;
                    } else {
                        arrayTag.getTagParameter().addAll(tag.getTagParameter());
                        jaxb_element.getValueOrBaseTypeOrClassType().remove(tag);
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
        
        // If current element is an assocation qualifier, restore its name
        // after super call because jaxb element name contains the rank of the
        // qualifier.
        if (modelio_element.getCompositionOwner() instanceof AssociationEnd) {
            modelio_element.setName(oldName);
        }
        
        // Set the old visibility again. It will be replace in the post treatment
        modelio_element.setVisibility(oldVisibility);
        
        // Set multiplicity
        // undo super work.
        modelio_element.setMultiplicityMin(multMin);
        modelio_element.setMultiplicityMax(multMax);
        String jaxbAttMultiplicity = jaxb_element.getMultiplicity();
        // the only case is when the multiplicity max changes from 0,1 to * and vice-versa
        if (jaxbAttMultiplicity == null || "1".equals(jaxbAttMultiplicity)) {
            // jaxbmult is 0 or 1
            if (!"01".contains(multMax)) {
                modelio_element.setMultiplicityMin("1");
                modelio_element.setMultiplicityMax("1");
            }
        } else {
            // jaxbmult is *
            if ("01".contains(multMax)) {
                modelio_element.setMultiplicityMin("0");
                modelio_element.setMultiplicityMax(jaxbAttMultiplicity);
            }
        }
        
        handleMultipleTags(jaxb_element);
        
        modelio_element.setType(null);
        
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
    private void handleMultipleTags(final JaxbAttribute jaxb_element) {
        JaxbTaggedValue firstBindTag = null;
        
        List<JaxbTaggedValue> toRemove = new ArrayList<>();
        List<Object> sub_elements = jaxb_element
                .getValueOrBaseTypeOrClassType();
        for (Object sub_element : sub_elements) {
            if (sub_element instanceof JaxbTaggedValue) {
                JaxbTaggedValue currentTag = (JaxbTaggedValue) sub_element;
        
                if (currentTag.getTagType().equals(
                        JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)) {
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
    public void postTreatment(final JaxbAttribute jaxb_element, final Attribute modelio_element, final IReadOnlyRepository repository) {
        boolean hasNoInit = true;
        MObject type = null;
        
        // Typage de l'attribut
        for (Object sub : jaxb_element.getValueOrBaseTypeOrClassType()) {
            if (sub instanceof JAXBElement) {
                JAXBElement<?> jaxb_sub = (JAXBElement<?>) sub;
                if ("base-type".equals(jaxb_sub.getName().getLocalPart())) {
                    String baseType = (String) jaxb_sub.getValue();
                    type = TypeConverter.getBaseType(baseType, this.model);
                    modelio_element.setType((DataType) type);
                } else if ("value".equals(jaxb_sub.getName().getLocalPart())) {
                    String value = (String) jaxb_sub.getValue();
                    value = value.trim();
                    modelio_element.setValue(value);
                    hasNoInit = false;
                }
            } else if (sub instanceof JaxbClassType) {
                JaxbDestination jaxb_destination = ((JaxbClassType) sub).getDestination();
        
                type = repository.getElementById(jaxb_destination.getRefid());
                if (type == null) {
                    type = repository.getElementByNamespace(jaxb_destination, GeneralClass.class, this.session);
                    if (type == null) {
                        type = repository.createNamespace(jaxb_destination, (ModelTree) repository.getRoot(), org.modelio.metamodel.uml.statik.Class.class, this.session);
                    }
                }
        
                modelio_element.setType((GeneralClass) type);
            }
        }
        
        try {
            org.modelio.module.javadesigner.utils.ModelUtils.setTaggedValue(
                    this.session,
                    modelio_element,
                    IJavaDesignerPeerModule.MODULE_NAME,
                    JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE, hasNoInit);
        } catch (ExtensionNotFoundException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
        }
        
        // Remove type if there is a type expr
        if (modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR)) {
            modelio_element.setType(null);
            type = null;
        
            // Emit the warning
            String typexpr = modelio_element.getTagValue(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR);
            IReportWriter report = repository.getReportWriter ();
            report.addWarning (Messages.getString("reverse.Type_not_found.title", typexpr), modelio_element, Messages.getString("reverse.Type_not_found.description", typexpr)); //$NON-NLS-1$ //$NON-NLS-2$
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
                    modelio_element.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION, annotations);
                }
        
                // Update visibility according to the model<==> code rules
                if (newVisibility == JavaTypeManager.getInstance().getPropertyCodeVisibility(modelio_element.getVisibility())) {
                    // The current visibility corresponds to a correct code
                    // visibility, no need to change the model
                    newVisibility = modelio_element.getVisibility();
                } else {
                    // Invalid current visibility, get the correct one from the config file
                    newVisibility = JavaTypeManager.getInstance().getPropertyModelVisibility(newVisibility);
                }
        
                // Add the stereotype
                org.modelio.module.javadesigner.utils.ModelUtils.addStereotype(modelio_element, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
            }
        }
        
        // Set the proper visibility
        if (newVisibility != null) {
            modelio_element.setVisibility(newVisibility);
        }
        
        AssociationEnd existingElt = jaxb_element.getObjid() != null ? this.session.findElementById(AssociationEnd.class, jaxb_element.getObjid()) : null;
        if (type != null
                && !JavaFeatureReverseUtils.getInstance().isPrimitive(type)
                && type.getStatus().isModifiable()
                && !(modelio_element.getCompositionOwner() instanceof AssociationEnd)) {
        
            // non primitive type : change for an association
        
            if (existingElt != null && existingElt.isValid()) {
                // AssociationEnd already exists, update it with the new modelio_element
        
                // keep changeable of the existingElt because in this case it is up to date (see JavaOperationStrategy.handleAccessorLink)
                // (don't know if this trick is useful in this case but it is symmetric with JavaAssociationEndStrategy)
                KindOfAccess savedAccess = existingElt.getChangeable();
                // update the existing associationEnd with the reversed attribute
                JavaFeatureReverseUtils.getInstance().copyAttributeToAssociationEnd(modelio_element, existingElt, (Classifier) type);
                // restore changeable
                existingElt.setChangeable(savedAccess);
        
            } else {
                // No, convert the attribute into an associationEnd
                existingElt = JavaFeatureReverseUtils.getInstance().convertAttributeToAssociationEnd(modelio_element, (Classifier) type);
            }
        
            modelio_element.delete();
        } else {
            if (existingElt != null && existingElt.isValid()) {
                // The existing attribute is no more needed (it is now an
                // association) : remove it
                existingElt.delete();
            }
            if (type != null) {
                // Change multiplicity for predefined types
                if (!(JavaDesignerUtils.getJavaName((ModelElement) type).equals("String")) && !((ModelElement) type).getName().equals("Date")) {
                    String modelioParameterMultiplicityMin = modelio_element.getMultiplicityMin();
                    String modelioParameterMultiplicityMax = modelio_element.getMultiplicityMax();
        
                    if (modelioParameterMultiplicityMin.equals("0") && modelioParameterMultiplicityMax.equals("1")) {
                        modelio_element.setMultiplicityMin("1");
                    }
                }
            }
        }
    }

}
