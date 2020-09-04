package org.modelio.module.javadesigner.reverse.xmltomodel.strategy;

import java.util.ArrayList;
import java.util.List;
import com.modelio.module.xmlreverse.IReadOnlyRepository;
import com.modelio.module.xmlreverse.model.JaxbOperation;
import com.modelio.module.xmlreverse.model.JaxbTaggedValue;
import com.modelio.module.xmlreverse.strategy.OperationStrategy;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.KindOfAccess;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.ReverseStrategyConfiguration;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaModelElementDeleteStrategy;
import org.modelio.module.javadesigner.reverse.xmltomodel.NoteReverseUtils;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;


public class JavaOperationStrategy extends OperationStrategy {
    public ReverseStrategyConfiguration reverseConfig;

    private JavaModelElementDeleteStrategy deleteStrategy;

    public JavaOperationStrategy(final IModelingSession session, final ReverseStrategyConfiguration reverseConfig, final JavaModelElementDeleteStrategy deleteStrategy) {
        super (session);
        this.reverseConfig = reverseConfig;
        this.deleteStrategy = deleteStrategy;
    }

    @Override
    public List<MObject> updateProperties(final JaxbOperation jaxb_element, final Operation modelio_element, final MObject owner, final IReadOnlyRepository repository) {
        String oldName = modelio_element.getName ();
        boolean hasJavaName = modelio_element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME);

        List<MObject> ret = super.updateProperties (jaxb_element, modelio_element, owner, repository);

        if (modelio_element.getOwner () instanceof Interface) {
            // jdk8 modelio_element.setIsAbstract (true);
            modelio_element.setVisibility (VisibilityMode.PUBLIC);
        }

        String name = jaxb_element.getName ();
        if (name != null && !JavaDesignerUtils.getJavaName (modelio_element).equals (name)) {
            modelio_element.setName (name);
        }

        handleMultipleTags (jaxb_element);

        // Is this operation a getter/setter
        Dependency link = handleAccessorLink (modelio_element);
        if (link != null) {
            if (ret == null) {
                ret = new ArrayList<> ();
            }
            ret.add (link);
            for (Stereotype s : modelio_element.getExtension()) {
                if (s.getName().equals(JavaDesignerStereotypes.JAVAGETTER)
                        || s.getName().equals(JavaDesignerStereotypes.JAVASETTER)) {
                    this.deleteStrategy.putJavaStereotypeUsage(modelio_element, s);
                    break;
                }
            }
            if (owner instanceof Interface) {
                ret.add (link.getDependsOn());
            }
        }

        if (name != null && hasJavaName) {
            try {
                modelio_element.setName (oldName);

                ModelUtils.setFirstTagParameter (this.session, modelio_element, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME, name);
                if (ret == null) {
                    ret = new ArrayList<> ();
                }
                ret.add (modelio_element.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVANAME));
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            } catch (ElementNotUniqueException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
        }
        return ret;
    }

    /**
     * TODO this should be done in the ANTLR -> XML part...
     */
    private void handleMultipleTags(final JaxbOperation jaxb_element) {
        JaxbTaggedValue firstTemplateParameterTag = null;
        JaxbTaggedValue firstThrownTag = null;

        List<JaxbTaggedValue> toRemove = new ArrayList<> ();
        List<Object> sub_elements = jaxb_element.getParameterOrTemplateParameterOrReturnParameter ();
        for (Object sub_element : sub_elements) {
            if (sub_element instanceof JaxbTaggedValue) {
                JaxbTaggedValue currentTag = (JaxbTaggedValue) sub_element;

                if (currentTag.getTagType ().equals (JavaDesignerTagTypes.OPERATION_JAVATEMPLATEPARAMETERS)) {
                    if (firstTemplateParameterTag == null) {
                        firstTemplateParameterTag = currentTag;
                    } else {
                        firstTemplateParameterTag.getTagParameter ().addAll (currentTag.getTagParameter ());
                        toRemove.add (currentTag);
                    }
                } else if (currentTag.getTagType ().equals (JavaDesignerTagTypes.OPERATION_JAVATHROWNEXCEPTION)) {
                    if (firstThrownTag == null) {
                        firstThrownTag = currentTag;
                    } else {
                        firstThrownTag.getTagParameter ().addAll (currentTag.getTagParameter ());
                        toRemove.add (currentTag);
                    }
                }
            }
        }

        sub_elements.removeAll (toRemove);
    }

    @Override
    public void postTreatment(final JaxbOperation jaxb_element, final Operation modelio_element, final IReadOnlyRepository repository) {
        super.postTreatment (jaxb_element, modelio_element, repository);

        if (this.reverseConfig.GENERATEINVARIANTS &&
                jaxb_element.getName ().equals (this.reverseConfig.INVARIANTSNAME)) {
            computeInvariantMethod (modelio_element);

            modelio_element.delete();
        } else {
            computeConstraints (modelio_element);

            try {
                computeJavaDoc (modelio_element, repository);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.SEEJAVADOC)); //$NON-NLS-1$
            }

            // Check if this is a constructor or a destructor
            if (modelio_element.getReturn () == null) {
                String ownerName = JavaDesignerUtils.getJavaName (modelio_element.getOwner ());
                String operationName = JavaDesignerUtils.getJavaName (modelio_element);

                String stereotypeName = "";
                try {
                    if (ownerName.equals (operationName)) {
                        if (!modelio_element.isTagged("JavaDesigner", "JavaNative")) {
                            stereotypeName = "create";
                            ModelUtils.addStereotype(modelio_element, stereotypeName);
                        }
                    } else if ("finalize".equals (operationName)) {
                        stereotypeName = "destroy";
                        ModelUtils.addStereotype(modelio_element, stereotypeName);
                    }
                } catch (ExtensionNotFoundException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", stereotypeName)); //$NON-NLS-1$
                }
            }

            // Check if this is a property accessor
            String annotations = modelio_element.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVAANNOTATION);
            if (annotations != null) {
                if (annotations.contains("@mdl.propgetter")) {
                    // This is a generated property getter.

                    for (Attribute attribute : modelio_element.getOwner().getOwnedAttribute()) {
                        String setterName = JavaTypeManager.getInstance().getDefaultGetterName(this.session, attribute, false);
                        if (setterName.equals(jaxb_element.getName())) {
                            // Report the access mode on the attribute
                            if (attribute.getChangeable () == KindOfAccess.WRITE) {
                                attribute.setChangeable (KindOfAccess.READWRITE);
                            } else {
                                attribute.setChangeable (KindOfAccess.READ);
                            }

                            // Report the visibility on the attribute
                            try {
                                ModelUtils.setFirstTagParameter(this.session, attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY, modelio_element.getVisibility().name());
                            } catch (ExtensionNotFoundException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            } catch (ElementNotUniqueException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            }
                        }
                    }

                    for (AssociationEnd associationEnd : modelio_element.getOwner().getOwnedEnd()) {
                        String setterName = JavaTypeManager.getInstance().getDefaultGetterName(this.session, associationEnd, false);
                        if (setterName.equals(jaxb_element.getName())) {
                            // Report the access mode on the attribute
                            if (associationEnd.getChangeable () == KindOfAccess.WRITE) {
                                associationEnd.setChangeable (KindOfAccess.READWRITE);
                            } else {
                                associationEnd.setChangeable (KindOfAccess.READ);
                            }

                            // Report the visibility on the attribute
                            try {
                                ModelUtils.setFirstTagParameter(this.session, associationEnd, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVAGETTERVISIBILITY, modelio_element.getVisibility().name());
                            } catch (ExtensionNotFoundException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            } catch (ElementNotUniqueException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            }
                        }
                    }

                    // We're done with this getter
                    modelio_element.delete();
                } else if (annotations.contains("@mdl.propsetter")) {
                    // This is a generated property setter.

                    for (Attribute attribute : modelio_element.getOwner().getOwnedAttribute()) {
                        String setterName = JavaTypeManager.getInstance().getDefaultSetterName(this.session, attribute, false);
                        if (setterName.equals(jaxb_element.getName())) {
                            // Report the access mode on the attribute
                            if (attribute.getChangeable () == KindOfAccess.READ) {
                                attribute.setChangeable (KindOfAccess.READWRITE);
                            } else {
                                attribute.setChangeable (KindOfAccess.WRITE);
                            }

                            // Report the visibility on the attribute
                            try {
                                ModelUtils.setFirstTagParameter(this.session, attribute, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY, modelio_element.getVisibility().name());
                            } catch (ExtensionNotFoundException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            } catch (ElementNotUniqueException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            }
                        }
                    }

                    for (AssociationEnd associationEnd : modelio_element.getOwner().getOwnedEnd()) {
                        String setterName = JavaTypeManager.getInstance().getDefaultSetterName(this.session, associationEnd, false);
                        if (setterName.equals(jaxb_element.getName())) {
                            // Report the access mode on the attribute
                            if (associationEnd.getChangeable () == KindOfAccess.READ) {
                                associationEnd.setChangeable (KindOfAccess.READWRITE);
                            } else {
                                associationEnd.setChangeable (KindOfAccess.WRITE);
                            }

                            // Report the visibility on the attribute
                            try {
                                ModelUtils.setFirstTagParameter(this.session, associationEnd, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAASSOCIATIONENDPROPERTY_JAVASETTERVISIBILITY, modelio_element.getVisibility().name());
                            } catch (ExtensionNotFoundException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            } catch (ElementNotUniqueException e) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e.getMessage());
                            }
                        }
                    }

                    // We're done with this setter
                    modelio_element.delete();
                }
            }
        }
    }

    private void computeJavaDoc(final Operation modelio_element, final IReadOnlyRepository repository) throws ExtensionNotFoundException {
        String noteType;
        String moduleName;
        if (!this.reverseConfig.DESCRIPTIONASJAVADOC) {
            noteType = JavaDesignerNoteTypes.CLASS_JAVADOC;
            moduleName = IJavaDesignerPeerModule.MODULE_NAME;
        } else {
            noteType = IOtherProfileElements.MODELELEMENT_DESCRIPTION;
            moduleName = IOtherProfileElements.MODULE_NAME;
        }

        for (Note note : ModelUtils.getAllNotes (modelio_element, noteType)) {
            String tempContent = note.getContent ();
            tempContent = NoteReverseUtils.getInstance ().reverseJavadoc (this.session, tempContent, modelio_element, moduleName, noteType, repository);
            if (tempContent.isEmpty ()) {
                note.delete();
            } else {
                note.setContent (tempContent.trim ());
            }
        }
    }

    private void computeConstraints(final Operation modelio_element) {
        for (Note note : ModelUtils.getAllNotes (modelio_element, JavaDesignerNoteTypes.OPERATION_JAVACODE)) {
            String bodyCode = note.getContent ();

            // Filters invariants
            if (this.reverseConfig.GENERATEINVARIANTS) {
                bodyCode = filterInvariantsCall (bodyCode).trim ();
            }

            // Filters pre/post conditions
            if (this.reverseConfig.GENERATEPREPOSTCONDITIONS) {
                bodyCode = filterPreConditions (modelio_element, bodyCode).trim ();
                bodyCode = filterPostConditions (modelio_element, bodyCode).trim ();
            }

            note.setContent (bodyCode);
        }
    }

    private String filterPreConditions(final Operation modelio_element, final String bodyCode) {
        String startMarker = "// Begin of pre conditions";
        String endMarker = "// End of pre conditions";

        int startIndex = bodyCode.indexOf (startMarker);
        if (startIndex == 0) {
            int endIndex = bodyCode.indexOf (endMarker);

            if (endIndex > -1 && startIndex < endIndex) {
                String operationCode = bodyCode.substring (endIndex +
                        endMarker.length ());

                String preConditionCode = bodyCode.substring (startIndex +
                        startMarker.length (), endIndex);
                preConditionCode = preConditionCode.trim ();

                Constraint preConditionConstraint = this.model.createConstraint ();
                preConditionConstraint.setBody (preConditionCode);
                preConditionConstraint.setName ("Pre Conditions");
                preConditionConstraint.getConstrainedElement().add(modelio_element);
                try {
                    ModelUtils.addStereotype(preConditionConstraint, JavaDesignerStereotypes.JAVAPRECONDITION);
                } catch (ExtensionNotFoundException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVAPRECONDITION)); //$NON-NLS-1$
                }

                return operationCode;
            }
        }
        return bodyCode;
    }

    private String filterInvariantsCall(final String code) {
        String ret;
        String toRemove = this.reverseConfig.INVARIANTSNAME + "();";
        int index = code.indexOf (toRemove);

        if (index > -1) {
            ret = code.substring (index + toRemove.length ());
            ret = ret.trim ();
        } else {
            ret = code;
        }

        index = ret.lastIndexOf (toRemove);

        if (index > -1) {
            ret = ret.substring (0, index);
        }
        return ret;
    }

    private void computeInvariantMethod(final Operation modelio_element) {
        Classifier ownerClass = modelio_element.getOwner ();

        // JavaCode note is reversed in the JavaInvariants constraint
        String code = modelio_element.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
        if (code != null && !code.isEmpty ()) {
            Constraint preConditionConstraint = this.model.createConstraint ();
            preConditionConstraint.getConstrainedElement().add(ownerClass);
            preConditionConstraint.setBody (code);
            preConditionConstraint.setName ("Invariants");
            try {
                ModelUtils.addStereotype(preConditionConstraint, JavaDesignerStereotypes.JAVAINVARIANT);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVAINVARIANT)); //$NON-NLS-1$
            }
        }

        // JavaDoc note is reversed in the JavaDocInvariants or Invariants constraint
        String doc = modelio_element.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.FEATURE_JAVADOC);
        if (doc != null && !doc.isEmpty ()) {
            Constraint preConditionConstraint = this.model.createConstraint ();
            preConditionConstraint.setBody (doc);
            preConditionConstraint.getConstrainedElement().add(ownerClass);

            preConditionConstraint.setName ("Javadoc Invariant");
            try {
                ModelUtils.addStereotype(preConditionConstraint, JavaDesignerStereotypes.JAVADOCINVARIANT);
            } catch (ExtensionNotFoundException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVADOCINVARIANT)); //$NON-NLS-1$
            }
        }
    }

    private String filterPostConditions(final Operation modelio_element, final String bodyCode) {
        String startMarker = "// Begin of post conditions";
        String endMarker = "// End of post conditions";

        int startIndex = bodyCode.indexOf (startMarker);
        if (startIndex > -1) {
            int endIndex = bodyCode.indexOf (endMarker);

            if (endIndex + endMarker.length () == bodyCode.length ()) {
                String operationCode = bodyCode.substring (0, startIndex);

                String postConditionCode = bodyCode.substring (startIndex +
                        startMarker.length (), endIndex);
                postConditionCode = postConditionCode.trim ();

                Constraint postConditionConstraint = this.model.createConstraint ();
                postConditionConstraint.setBody (postConditionCode);
                postConditionConstraint.setName ("Post Conditions");
                postConditionConstraint.getConstrainedElement().add(modelio_element);
                try {
                    ModelUtils.addStereotype(postConditionConstraint, JavaDesignerStereotypes.JAVAPOSTCONDITION);
                } catch (ExtensionNotFoundException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.StereotypeNotFound", JavaDesignerStereotypes.JAVAPOSTCONDITION)); //$NON-NLS-1$
                }

                return operationCode;
            }
        }
        return bodyCode;
    }

    /**
     * Checks if the operation is a getter/setter, and updates the attribute's access mode.
     * @param modelio_element The operation to check.
     * @return The dependency linking the accessor to its attribute, or null if the element isn't an accessor.
     */
    private Dependency handleAccessorLink(final Operation modelio_element) {
        boolean isGetter = false;
        boolean isSetter = false;
        if (modelio_element.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
            isGetter = true;
        } else if (modelio_element.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
            isSetter = true;
        }

        if (isGetter || isSetter) {
            // Look for the link
            for (Dependency link : modelio_element.getDependsOnDependency ()) {
                if ((isGetter && link.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) ||
                        (isSetter && link.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER))) {
                    ModelElement dependentElement = link.getDependsOn ();

                    // Update the attribute or assoc end access mode
                    if (dependentElement instanceof Attribute) {
                        Attribute theAttribute = (Attribute) dependentElement;

                        switch (theAttribute.getChangeable ()) {
                        case ACCESNONE:
                            if (isGetter) {
                                theAttribute.setChangeable (KindOfAccess.READ);
                            } else {
                                theAttribute.setChangeable (KindOfAccess.WRITE);
                            }
                            break;
                        case READ:
                            if (isSetter) {
                                theAttribute.setChangeable (KindOfAccess.READWRITE);
                            }
                            break;
                        case WRITE:
                            if (isGetter) {
                                theAttribute.setChangeable (KindOfAccess.READ);
                            }
                            break;
                        case READWRITE:
                        default:
                            // Nothing to do
                            break;
                        }

                        // Return the link to avoid deleting it in the next part of the reverse
                        return link;
                    } else if (dependentElement instanceof AssociationEnd) {
                        AssociationEnd theAssocEnd = (AssociationEnd) dependentElement;

                        switch (theAssocEnd.getChangeable ()) {
                        case ACCESNONE:
                            if (isGetter) {
                                theAssocEnd.setChangeable (KindOfAccess.READ);
                            } else {
                                theAssocEnd.setChangeable (KindOfAccess.WRITE);
                            }
                            break;
                        case READ:
                            if (isSetter) {
                                theAssocEnd.setChangeable (KindOfAccess.READWRITE);
                            }
                            break;
                        case WRITE:
                            if (isGetter) {
                                theAssocEnd.setChangeable (KindOfAccess.READ);
                            }
                            break;
                        case READWRITE:
                        default:
                            // Nothing to do
                            break;
                        }

                        // Return the link to avoid deleting it in the next part of the reverse
                        return link;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Operation getCorrespondingElement(final JaxbOperation jaxb_element, final MObject owner, final IReadOnlyRepository repository) {
        if(owner instanceof Classifier){
            Classifier treeOwner = (Classifier) owner;
            for(Feature sub_tree : treeOwner.getOwnedOperation()){
                if(sub_tree.getName().equals(jaxb_element.getName())){
                    if (!repository.isRecordedElement(sub_tree) && !JavaDesignerUtils.isNoCode(sub_tree)) {
                        return (Operation)sub_tree;
                    }
                }
            }
        }


        Operation new_element = this.model.createOperation();
        return new_element;
    }
}
