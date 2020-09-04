package org.modelio.module.javadesigner.reverse.xmltomodel;

import java.util.ArrayList;
import java.util.List;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.IUMLTypes;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

public class JavaFeatureReverseUtils {
    private static JavaFeatureReverseUtils INSTANCE;

    private IModelingSession session;

    private IUmlModel model;

    private MMetamodel metamodel;

    private JavaFeatureReverseUtils() {
        this.session = JavaDesignerModule.getInstance().getModuleContext().getModelingSession();
        this.model = this.session.getModel();
        this.metamodel = JavaDesignerModule.getInstance().getModuleContext().getModelioServices().getMetamodelService().getMetamodel();
    }

    public static JavaFeatureReverseUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JavaFeatureReverseUtils();
        }
        return INSTANCE;
    }

    public boolean isAtomicType(final MObject type) {
        IUMLTypes utypes = this.model.getUmlTypes();
        return (utypes.getBOOLEAN().equals(type) || utypes.getBYTE().equals(type) || utypes.getCHAR().equals(type)
                                                        || utypes.getDOUBLE().equals(type) || utypes.getFLOAT().equals(type)
                                                        || utypes.getINTEGER().equals(type) || utypes.getLONG().equals(type) || utypes.getSHORT().equals(type));
    }

    public boolean isPrimitive(final MObject type) {
        if (type instanceof DataType) {
            return true;
        } else if (type instanceof Enumeration) {
            return true;
        } else if (type instanceof TemplateParameter) {
            return true;
        } else if (type instanceof GeneralClass) {
            if (JavaDesignerUtils.getFullJavaName(this.session, (GeneralClass) type).equals("java.util.Date")) {
                return true;
            }
            return ((GeneralClass) type).isIsElementary();
        }
        return false;
    }

    public void insertBefore(final AssociationEnd originalPlace, final Attribute toReplace) {
        boolean originalFound = false;
        Classifier owner = originalPlace.getSource();
        for (AssociationEnd current : owner.getOwnedEnd()) {
            if (current.equals(originalPlace)) {
                originalFound = true;
                toReplace.setOwner(null);
                toReplace.setOwner(owner);
            }
        
            if (originalFound) {
                current.setSource(null);
                current.setSource(owner);
            }
        }
    }

    public void insertBefore(final Attribute originalPlace, final AssociationEnd toReplace) {
        boolean originalFound = false;
        Classifier owner = originalPlace.getOwner();
        for (Attribute current : owner.getOwnedAttribute()) {
            if (current.equals(originalPlace)) {
                originalFound = true;
                toReplace.setSource(null);
                toReplace.setSource(owner);
            }
        
            if (originalFound) {
                current.setOwner(null);
                current.setOwner(owner);
            }
        }
    }

    public Attribute convertAssociationEndToAttribute(final AssociationEnd modelio_element, final MObject type) {
        Attribute newAttribute = this.model.createAttribute();
        // Set owner
        insertBefore(modelio_element, newAttribute);
        // update the attribute
        doCopyAssociationEndToAttribute(modelio_element, newAttribute, type);
        return newAttribute;
    }

    public AssociationEnd convertAttributeToAssociationEnd(final Attribute modelio_element, final Classifier type) {
        Association assoc = this.model.createAssociation();
        
        AssociationEnd end1 = this.model.createAssociationEnd();
        end1.setAssociation(assoc);
        
        AssociationEnd end2 = this.model.createAssociationEnd();
        end2.setAssociation(assoc);
        
        // Set opposite relations
        end1.setOpposite(end2);
        end2.setOpposite(end1);
        
        // Set owner
        insertBefore(modelio_element, end2);
        
        doCopyAttributeToAssociationEnd(modelio_element, end2);
        
        // Set type
        end2.setTarget(type, true);
        return end2;
    }

    public void copyAttributeToAssociationEnd(final Attribute modelio_element, final AssociationEnd newAssociationEnd, final Classifier type) {
        // if the associationEnd has been moved (with its objid) from one class
        // to another, attach it to the reversed class
        if (!modelio_element.getOwner().equals(newAssociationEnd.getSource())) {
            newAssociationEnd.setSource(modelio_element.getOwner());
        }
        
        doCopyAttributeToAssociationEnd(modelio_element, newAssociationEnd);
        
        // update the type of the association
        newAssociationEnd.setTarget(type, true);
    }

    private void doCopyAttributeToAssociationEnd(final Attribute modelio_element, final AssociationEnd newAssociationEnd) {
        // Initial value
        if (!modelio_element.getValue().isEmpty()) {
            try {
                newAssociationEnd.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE, modelio_element.getValue());
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        
        // Copy properties
        copyProperties(modelio_element, newAssociationEnd);
        
        // Copy notes
        copyNotes(modelio_element, newAssociationEnd);
        
        // Copy tag parameters
        copyTaggedValues(modelio_element, newAssociationEnd);
        
        // Copy stereotypes
        copyStereotype(modelio_element, newAssociationEnd);
        
        // Move constraints as all of them are defined on parent metaclasses...
        copyContraints(modelio_element, newAssociationEnd);
        
        // Update getter/setter links
        for (Dependency dep : modelio_element.getImpactedDependency()) {
            if (dep.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)
                    || dep.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                dep.setDependsOn(newAssociationEnd);
            }
        }
    }

    private void copyProperties(final Attribute modelio_element, final AssociationEnd newAssociationEnd) {
        newAssociationEnd.setName(modelio_element.getName());
        newAssociationEnd.setVisibility(modelio_element.getVisibility());
        newAssociationEnd.setIsClass(modelio_element.isIsClass());
        newAssociationEnd.setIsAbstract(modelio_element.isIsAbstract());
        newAssociationEnd.setIsDerived(modelio_element.isIsDerived());
        newAssociationEnd.setChangeable(modelio_element.getChangeable());
        newAssociationEnd.setMultiplicityMin(modelio_element.getMultiplicityMin());
        newAssociationEnd.setMultiplicityMax(modelio_element.getMultiplicityMax());
    }

    private void copyProperties(final AssociationEnd modelio_element, final Attribute newAttribute) {
        newAttribute.setName(modelio_element.getName());
        newAttribute.setVisibility(modelio_element.getVisibility());
        newAttribute.setIsClass(modelio_element.isIsClass());
        newAttribute.setIsAbstract(modelio_element.isIsAbstract());
        newAttribute.setIsDerived(modelio_element.isIsDerived());
        newAttribute.setChangeable(modelio_element.getChangeable());
        newAttribute.setMultiplicityMin(modelio_element.getMultiplicityMin());
        newAttribute.setMultiplicityMax(modelio_element.getMultiplicityMax());
    }

    private void copyContraints(final UmlModelElement source, final UmlModelElement destination) {
        for (Constraint oldConstraint : source.getConstraintDefinition()) {
            destination.getConstraintDefinition().add(oldConstraint);
        }
    }

    @SuppressWarnings("null")
    private void copyNotes(final ModelElement source, final ModelElement destination) {
        for (Note oldNote : source.getDescriptor()) {
            NoteType noteType = oldNote.getModel();
            if (noteType != null) {
                try {
                    if ((source instanceof Attribute)) {
                        noteType = convertNoteTypeFromAttribute(noteType);
                    } else if ((source instanceof AssociationEnd)) {
                        noteType = convertNoteTypeFromAssociation(noteType);
                    }
        
                    if (noteType != null) {
                        destination.putNoteContent(noteType.getModule().getName(), noteType.getName(), oldNote.getContent());
                    }
                } catch (ExtensionNotFoundException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString("Error.NoteTypeNotFound", noteType != null ? noteType.getName() : "")); //$NON-NLS-1$
                }
            }
        }
    }

    @SuppressWarnings("null")
    private void copyTaggedValues(final ModelElement source, final ModelElement destination) {
        // remove Java related tagged value on dest first
        for (TaggedValue tag : new ArrayList<>(destination.getTag())) {
            TagType tagType = tag.getDefinition();
            if (tagType != null) {
                String tagTypeName = tagType.getName();
                if (tagTypeName != null) {
                    if (tagTypeName.startsWith("Java") || tagTypeName.equals("type")) {
                        tag.delete();
                    }
                }
            }
        }
        
        // copy source tag to destination
        for (TaggedValue oldTag : new ArrayList<>(source.getTag())) {
            TagType tagType = oldTag.getDefinition();
            if (tagType != null) {
                try {
                    if ((source instanceof Attribute)) {
                        tagType = convertTagTypeFromAttribute(tagType);
                    } else if ((source instanceof AssociationEnd)) {
                        tagType = convertTagTypeFromAssociation(tagType);
                    }
        
                    if (tagType != null) {
                        TaggedValue newTag = org.modelio.module.javadesigner.utils.ModelUtils.setTaggedValue(this.session, destination, tagType.getModule().getName(), tagType.getName(), true);
        
                        // Move tag parameters
                        for (TagParameter tagParameter : oldTag.getActual()) {
                            this.model.createTagParameter(tagParameter.getValue(), newTag);
                        }
                    }
                } catch (ExtensionNotFoundException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString("Error.tagTypeNotFound", tagType != null ? tagType.getName() : null)); //$NON-NLS-1$
                }
            }
        }
    }

    private NoteType convertNoteTypeFromAssociation(final NoteType associationEndNoteType) {
        IMetamodelExtensions metamodelExtensions = this.session.getMetamodelExtensions();
        if (associationEndNoteType.equals(JavaDesignerNoteTypes.FEATURE_JAVADOC)) {
            return metamodelExtensions.getNoteType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVADOC, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndNoteType.equals(IOtherProfileElements.MODELELEMENT_DESCRIPTION)) {
            return metamodelExtensions.getNoteType(IOtherProfileElements.MODELELEMENT_DESCRIPTION, IOtherProfileElements.MODELELEMENT_DESCRIPTION, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndNoteType.equals(JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUECOMMENT)) {
            return metamodelExtensions.getNoteType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ATTRIBUTE_JAVAINITVALUECOMMENT, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndNoteType.equals(JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE)) {
            return null;
        } else if (associationEndNoteType.equals(JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION)) {
            return metamodelExtensions.getNoteType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION, this.metamodel.getMClass(Attribute.class));
        }
        return associationEndNoteType;
    }

    private TagType convertTagTypeFromAssociation(final TagType associationEndTagType) {
        IMetamodelExtensions metamodelExtensions = this.session.getMetamodelExtensions();
        if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAARRAYDIMENSION, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVABIND, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFULLNAME)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAIMPLEMENTATIONTYPE, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVAPUBLIC)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAPUBLIC, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.FEATURE_JAVANAME)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANAME, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.FEATURE_JAVANOINVARIANT)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINVARIANT, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE, this.metamodel.getMClass(Attribute.class));
        } else if (associationEndTagType.equals(IOtherProfileElements.MODELELEMENT_NOCODE)) {
            return metamodelExtensions.getTagType(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODELELEMENT_NOCODE, this.metamodel.getMClass(Attribute.class));
        }
        return associationEndTagType;
    }

    private NoteType convertNoteTypeFromAttribute(final NoteType attributeNoteType) {
        IMetamodelExtensions metamodelExtensions = this.session.getMetamodelExtensions();
        if (attributeNoteType.equals(JavaDesignerNoteTypes.FEATURE_JAVADOC)) {
            return metamodelExtensions.getNoteType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVADOC, this.metamodel.getMClass(Attribute.class));
        } else if (attributeNoteType.equals(IOtherProfileElements.MODELELEMENT_DESCRIPTION)) {
            return metamodelExtensions.getNoteType(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODELELEMENT_DESCRIPTION, this.metamodel.getMClass(Attribute.class));
        } else if (attributeNoteType.equals(JavaDesignerNoteTypes.ATTRIBUTE_JAVAINITVALUECOMMENT)) {
            return metamodelExtensions.getNoteType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUECOMMENT, this.metamodel.getMClass(Attribute.class));
        } else if (attributeNoteType.equals(JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION)) {
            return metamodelExtensions.getNoteType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION, this.metamodel.getMClass(Attribute.class));
        }
        return attributeNoteType;
    }

    private TagType convertTagTypeFromAttribute(final TagType attributeTagType) {
        IMetamodelExtensions metamodelExtensions = this.session.getMetamodelExtensions();
        if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVAARRAYDIMENSION)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAARRAYDIMENSION, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVABIND)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVABIND, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFINAL, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAFULLNAME, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVAIMPLEMENTATIONTYPE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAIMPLEMENTATIONTYPE, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVAPUBLIC)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAPUBLIC, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATRANSIENT, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVAVOLATILE, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.FEATURE_JAVANAME)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANAME, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINITVALUE, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.FEATURE_JAVANOINVARIANT)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINVARIANT, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE)) {
            return metamodelExtensions.getTagType(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.MODELELEMENT_JAVANOCODE, this.metamodel.getMClass(Attribute.class));
        } else if (attributeTagType.equals(IOtherProfileElements.MODELELEMENT_NOCODE)) {
            return metamodelExtensions.getTagType(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODELELEMENT_NOCODE, this.metamodel.getMClass(Attribute.class));
        }
        return attributeTagType;
    }

    private void copyStereotype(final AssociationEnd source, final Attribute destination) {
        if (source.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
            boolean hasAlreadyThisStereotype = destination.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY);
        
            if (!hasAlreadyThisStereotype) {
                // Add the stereotype
                destination.getExtension().add(this.session.getMetamodelExtensions().getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY, destination.getMClass()));
            }
        }
    }

    private void copyStereotype(final Attribute source, final AssociationEnd destination) {
        if (source.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
            boolean hasAlreadyThisStereotype = destination.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY);
            if (!hasAlreadyThisStereotype) {
                // Add the stereotype
                destination.getExtension().add(this.session.getMetamodelExtensions().getStereotype(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY, destination.getMClass()));
            }
        }
    }

    public void copyAssociationEndToAttribute(final AssociationEnd modelio_element, final Attribute att, final MObject type) {
        // if the attribute has been moved (with its objid) from one class to
        // another, attach it to the reversed class
        if (!modelio_element.getSource().equals(att.getOwner())) {
            att.setOwner(modelio_element.getSource());
        }
        doCopyAssociationEndToAttribute(modelio_element, att, type);
    }

    private void doCopyAssociationEndToAttribute(final AssociationEnd modelio_element, final Attribute att, final MObject type) {
        // SetType
        if (type != null && type instanceof GeneralClass) {
            att.setType((GeneralClass) type);
        }
        
        // Initial value
        Note initialValue = modelio_element.getNote(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE);
        if (initialValue != null) {
            att.setValue(initialValue.getContent());
        }
        
        // Copy properties
        copyProperties(modelio_element, att);
        
        // Copy notes
        copyNotes(modelio_element, att);
        
        // Copy tag parameters
        copyTaggedValues(modelio_element, att);
        
        // Copy stereotypes
        copyStereotype(modelio_element, att);
        
        // Move constraints as all of them are defined on parent metaclasses...
        copyContraints(modelio_element, att);
        
        JavaTypeManager typeManager = JavaTypeManager.getInstance();
        
        List<String> types = att.getTagValues(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE);
        if (types != null) {
            for (Attribute qualifier : modelio_element.getQualifier()) {
                try {
                    types.add(typeManager.getTypeDeclaration(this.session, qualifier, qualifier.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME)).toString());
                } catch (CustomException e) {
                    // Error in type computing, just set the type's name...
                    GeneralClass qualType = qualifier.getType();
                    if (qualType != null) {
                        types.add(JavaDesignerUtils.getJavaName(qualType));
                    }
                }
            }
        
            try {
                att.putTagValues(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE, types);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        
        // Update getter/setter links
        for (Dependency dep : modelio_element.getImpactedDependency()) {
            if (dep.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)
                    || dep.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                dep.setDependsOn(att);
            }
        }
    }

}
