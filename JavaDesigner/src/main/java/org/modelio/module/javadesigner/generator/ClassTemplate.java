package org.modelio.module.javadesigner.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import com.modelio.module.xmlreverse.IReportWriter;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.DataType;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.metamodel.uml.statik.PassingMode;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.module.javadesigner.api.CustomException;
import org.modelio.module.javadesigner.api.IJavaDesignerPeerModule;
import org.modelio.module.javadesigner.api.JavaDesignerNoteTypes;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.automation.AccessorManager;
import org.modelio.module.javadesigner.custom.JavaTypeManager;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

class ClassTemplate {
    private static final String MarkerNew = "C";

    private static final String MarkerEnd = "E";

    private static final String MarkerBegin = "T";

    private static final String NL = System.getProperty ("line.separator"); // $NON-NLS-1$

    /**
     * Indentation level used during the generation
     */
    private int Val_indent = 0;

    /**
     * Indent constants, to avoid building multiple Strings.
     */
    private final String[] INDENT_VALUES = {
			"",
			"    ",
			"        ",
			"            ",
			"                ",
			"                    ",
			"                        ",
			"                            ",
			"                                ",
			"                                    ",
			"                                        "
	};

    private JavaConfiguration javaConfig;

    public JavaTypeManager typeManager;

    private IReportWriter report;

    private Map<String, StringBuilder> copyrightCache = new HashMap<>();

    private IModelingSession session;

    private StereotypedAnnotationsManager annotationManager;

    public ClassTemplate(final IReportWriter report, final IModelingSession session) {
        this.report = report;
        this.session = session;
        this.typeManager = JavaTypeManager.getInstance ();
        this.annotationManager = new StereotypedAnnotationsManager();
    }

    private CharSequence computeAnnotationDefaultValue(final AssociationEnd theAssociationEnd) {
        StringBuilder ret = new StringBuilder ();
        
        for (Note note : ModelUtils.getAllNotes (theAssociationEnd, JavaDesignerNoteTypes.ASSOCIATIONEND_JAVAINITVALUE)) {
            ret.append (" default ");
            ret.append (updateNewlines (note.getContent ()));
        
            // Remove ending newline
            ret.delete (ret.length () - NL.length(), ret.length ());
        
            // Remove ending ";" if present
            int length = ret.length ();
            while (length > 0 && ret.charAt (length - 1) == ';') {
                ret.delete (length - 1, length);
                length = ret.length ();
            }
        }
        return ret;
    }

    private CharSequence computeAnnotationDefaultValue(final Attribute theAttribute) {
        StringBuilder ret = new StringBuilder ();
        
        if (!theAttribute.isIsDerived ()) {
            if (theAttribute.getValue ().length () != 0) {
                ret.append (" default ");
                ret.append (theAttribute.getValue ());
        
                int length = ret.length ();
                while (length > 0 && ret.charAt (length - 1) == ';') {
                    ret.delete (length - 1, length);
                    length = ret.length ();
                }
        
            }
        }
        return ret;
    }

    private CharSequence computeAnnotationName(final GeneralClass theGeneralClass) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        String javaName = JavaDesignerUtils.getJavaName (theGeneralClass);
        
        ret.append ("@interface ");
        
        if (javaName.startsWith ("$") &&
                !JavaDesignerUtils.isInner (theGeneralClass)) {
            throw new TemplateException (Messages.getString ("Error.illegalChar", "$", javaName, "class"));
        }
        
        ret.append (javaName);
        ret.append (" ");
        return ret;
    }

    private CharSequence computeAssociationEndInitialValueComment(final AssociationEnd theAssociationEnd) {
        String comment = theAssociationEnd.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ATTRIBUTE_JAVAINITVALUECOMMENT);
        
        if (comment != null && comment.length () > 0) {
            comment = updateNewlinesExceptLast(comment).toString();
            if (comment.contains (NL)) {
                /* multiline comment */
                return " /* " + comment + " */";
            } else {
                // one line comment
                return " // " + comment;
            }
        }
        return "";
    }

    private CharSequence computeAttributeInitialValueComment(final Attribute theAttribute) {
        String comment = theAttribute.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ATTRIBUTE_JAVAINITVALUECOMMENT);
        
        if (comment != null && comment.length () > 0) {
            comment = updateNewlinesExceptLast(comment).toString();
            if (comment.contains (NL)) {
                /* multiline comment */
                return " /* " + comment + " */";
            } else {
                // one line comment
                return " // " + comment;
            }
        }
        return "";
    }

    private CharSequence computeLiteralValueComment(final EnumerationLiteral theLiteral) {
        String comment = theLiteral.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ENUMERATIONLITERAL_JAVAINITVALUECOMMENT);
        
        if (comment != null && comment.length () > 0) {
            comment = updateNewlinesExceptLast(comment).toString();
            if (comment.contains (NL)) {
                /* multiline comment */
                return " /* " + comment + " */";
            } else {
                // one line comment
                return " // " + comment;
            }
        }
        return "";
    }

    private CharSequence computeBodyBottom(final Operation theOperation) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getTransformedOneNoteOfType (theOperation, "JavaReturned", this.javaConfig.GENERATIONMODE_MODELDRIVEN));
        
        Parameter returnParam = theOperation.getReturn ();
        if (ret.length () == 0 && returnParam!= null) {
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (computeEmptyMarker (theOperation, "JavaReturned"));
            } else if (theOperation.getNote(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE) == null && this.javaConfig.GENERATEDEFAULTRETURN) {
                GeneralClass returnType = returnParam.getType();
                String returnName = JavaDesignerUtils.getJavaName(returnType);
        
                if (!this.typeManager.isArray(returnParam)) {
                    ret.append (getCurrentIndent ());
                    ret.append("// TODO Auto-generated return");
                    ret.append(NL);
                    ret.append (getCurrentIndent ());
        
                    switch (returnName) {
                    case JavaDesignerUtils.BOOLEAN:
                        ret.append("return false;");
                        break;
                    case JavaDesignerUtils.BYTE:
                    case JavaDesignerUtils.CHAR:
                    case JavaDesignerUtils.INTEGER:
                    case JavaDesignerUtils.DOUBLE:
                    case JavaDesignerUtils.LONG:
                    case JavaDesignerUtils.SHORT:
                        ret.append("return 0;");
                        break;
                    case JavaDesignerUtils.FLOAT:
                        ret.append("return 0f;");
                        break;
                    case JavaDesignerUtils.DATE:
                    case JavaDesignerUtils.STRING:
                    default:
                        ret.append("return null;");
                    }
        
                    ret.append(NL);
        
                    this.report.addWarning (Messages.getString ("Warning.ReturnCodeMissing", JavaDesignerUtils.getJavaName (theOperation)), theOperation, Messages.getString ("Warning.ReturnCodeMissingDescription"));
                }
            }
        }
        return ret;
    }

    private CharSequence computeBodyHeader(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        boolean found = false;
        
        for (Note note : ModelUtils.getAllNotes (theOperation, JavaDesignerNoteTypes.OPERATION_JAVASUPER)) {
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (getIdBegin (note));
                ret.append (updateNewlines (note.getContent ()));
                ret.append (NL);
                ret.append (getIdEnd (note));
            } else {
                ret.append (updateNewlines (note.getContent ()));
                ret.append (NL);
            }
            found = true;
        }
        
        if (isCreateMethod (theOperation)) {
            if (!found) {
                if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                    ret.append (computeEmptyMarker (theOperation, JavaDesignerNoteTypes.OPERATION_JAVASUPER));
                }
                found = true;
            }
        }
        return ret;
    }

    private CharSequence computePostCondition(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        
        if (this.javaConfig.GENERATEPREPOSTCONDITIONS) {
            List<Constraint> postConditions = new ArrayList<>();
            for (Constraint postCondition : theOperation.getConstraintDefinition ()) {
                if (postCondition.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPOSTCONDITION)) {
                    postConditions.add(postCondition);
                }
            }
        
            if (postConditions.size () == 0) {
                // Nothing to add
                return ret;
            } else if (postConditions.size () > 1) {
                this.report.addWarning (Messages.getString ("Warning.warnMultiplePostConditions", JavaDesignerUtils.getFullJavaName (this.session, theOperation)), theOperation, "");
            }
        
            // Generate the first post condition
            Constraint firstPostCondition = postConditions.get (0);
            ret.append (getCurrentIndent ());
            ret.append ("// Begin of post conditions");
            ret.append (NL);
            ret.append (computeFormattedCode (firstPostCondition.getBody ()));
            ret.append (getCurrentIndent ());
            ret.append ("// End of post conditions");
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computePreCondition(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        
        List<Constraint> preConditions = new ArrayList<>();
        if (this.javaConfig.GENERATEPREPOSTCONDITIONS) {
            for (Constraint preCondition : theOperation.getConstraintDefinition ()) {
                if (preCondition.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPRECONDITION)) {
                    preConditions.add(preCondition);
                }
            }
        
            if (preConditions.size () == 0) {
                // Nothing to add
                return ret;
            } else if (preConditions.size () > 1) {
                this.report.addWarning (Messages.getString ("Warning.warnMultiplePreConditions", JavaDesignerUtils.getFullJavaName (this.session, theOperation)), theOperation, "");
            }
        
            // Generate the first pre condition
            Constraint firstPreCondition = preConditions.get (0);
            ret.append (getCurrentIndent ());
            ret.append ("// Begin of pre conditions");
            ret.append (NL);
            ret.append (computeFormattedCode (firstPreCondition.getBody ()));
            ret.append (getCurrentIndent ());
            ret.append ("// End of pre conditions");
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computeBottomText(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        for (Note note : ModelUtils.getAllNotes (theGeneralClass, JavaDesignerNoteTypes.CLASS_JAVABOTTOM)) {
            CharSequence content = updateNewlines (note.getContent ());
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (getIdBegin (note));
                ret.append (content);
                ret.append (getIdEnd (note));
            } else {
                ret.append (content);
            }
        }
        return ret;
    }

    private CharSequence computeCallInvariant(final Operation theOperation) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        if (!theOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.FEATURE_JAVANOINVARIANT) &&
                isClassWithInvariant (theOperation)) {
            if (isCreateMethod (theOperation) || isDeleteMethod (theOperation)) {
                NameSpace operationOwner = theOperation.getOwner ();
                if (operationOwner instanceof GeneralClass) {
                    ret.append (getInvariantBody ((GeneralClass) operationOwner));
                }
            } else {
                ret.append (getCurrentIndent ());
                ret.append (this.javaConfig.INVARIANTSNAME);
                ret.append ("(); ");
                ret.append (NL);
            }
        }
        return ret;
    }

    private CharSequence computeClassDeclaration(final GeneralClass theGeneralClass) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        String javaName = JavaDesignerUtils.getJavaName (theGeneralClass);
        
        if (isInterface (theGeneralClass)) {
            ret.append ("interface ");
        } else {
            ret.append ("class ");
        }
        
        if (javaName.startsWith ("$") &&
                !JavaDesignerUtils.isInner (theGeneralClass)) {
            throw new TemplateException (Messages.getString ("Error.illegalChar", "$", javaName, "class"));
        }
        
        ret.append (javaName);
        return ret;
    }

    private boolean isClassWithInvariant(final Operation theOperation) throws CustomException, TemplateException {
        if (theOperation.getOwner () instanceof GeneralClass) {
            GeneralClass ownerGeneralClass = (GeneralClass) theOperation.getOwner ();
            if (hasInvariant (ownerGeneralClass)) {
                return true;
            } else if (isParentWithInvariant (ownerGeneralClass)) {
                return true;
            }
        }
        return false;
    }

    private CharSequence computeDataTypeInheritance(final DataType theDataType) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        List<Generalization> parents = theDataType.getParent ();
        
        if (parents.size () > 1) {
            if (this.javaConfig.ERRORONFIRSTWARNING) {
                throw new TemplateException (Messages.getString ("Error.DataTypeMultipleInheritance", JavaDesignerUtils.getJavaName (theDataType)));
            } else {
                this.report.addWarning (Messages.getString ("Warning.DataTypeMultipleInheritance", JavaDesignerUtils.getJavaName (theDataType)), theDataType, "");
            }
        }
        
        for (Generalization theGeneralization : parents) {
            if (!JavaDesignerUtils.isNoCode (theGeneralization)) {
                NameSpace superType = theGeneralization.getSuperType ();
                if (superType instanceof DataType && !JavaDesignerUtils.isNoCode (superType)) {
                    String UMLType = JavaDesignerUtils.getJavaName (superType);
        
                    ret.append ("\t");
                    ret.append ("extends ");
                    ret.append (JavaDesignerUtils.getFullJavaName (this.session, superType));
        
                    if (JavaDesignerUtils.isPredefinedType (UMLType)) {
                        throw new TemplateException (Messages.getString ("Error.ExtendTypeNotAllowed", JavaDesignerUtils.getJavaName (theDataType), UMLType));
                    }
                }
            }
        }
        return ret;
    }

    private void decreaseIndentLevel() {
        this.Val_indent--;
    }

    private CharSequence computeDocumentedAnnotation(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        if (theGeneralClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVADOCUMENTEDANNOTATION)) {
            ret.append (getCurrentIndent ());
            ret.append ("@java.lang.annotation.Documented");
            ret.append (NL);
        }
        return ret;
    }

    private String computeExceptions(final Operation theOperation) {
        Set<String> thrownTypes = new TreeSet<>();
        
        getThrownExceptions(theOperation, thrownTypes);
        
        StringBuilder ret = new StringBuilder ();
        if (!thrownTypes.isEmpty()) {
            ret.append (" throws ");
        
            for (Iterator<String> iterator = thrownTypes.iterator(); iterator.hasNext();) {
                ret.append (iterator.next());
        
                if (iterator.hasNext()) {
                    ret.append (", ");
                }
            }
        }
        return ret.toString();
    }

    private void getThrownExceptions(final Operation theOperation, final Set<String> thrownTypes) {
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theOperation, JavaDesignerTagTypes.OPERATION_JAVATHROWNEXCEPTION)) {
            for (TagParameter parameter : tag.getActual ()) {
                thrownTypes.add (parameter.getValue ());
            }
        }
        
        for (ElementImport theElementImport : theOperation.getOwnedImport ()) {
            if (theElementImport.isStereotyped(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.ELEMENTIMPORT_THROW)) {
                NameSpace importedElement = theElementImport.getImportedElement ();
                if (importedElement instanceof Class) {
                    thrownTypes.add (computeName (importedElement, isFullNameGeneration (theElementImport)));
                }
        
                if (importedElement instanceof Signal && importedElement.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.ELEMENTIMPORT_EXCEPTION)) {
                    thrownTypes.add (JavaDesignerUtils.getJavaName (importedElement));
                }
            }
        }
        
        for (RaisedException theException : theOperation.getThrown ()) {
            Classifier thrownType = theException.getThrownType ();
        
            StringBuilder tt = new StringBuilder();
            if (isJDK8Compatible()) {
                tt.append(this.typeManager.computeAnnotations(theException, false, true));
            }
            tt.append(computeName (thrownType, isFullNameGeneration (theException)));
            thrownTypes.add (tt.toString());
        }
    }

    private CharSequence computeExtends(final GeneralClass theGeneralClass) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        int lCount;
        List<Generalization> lParentLinks;
        
        lParentLinks = getParentInheritances (theGeneralClass);
        lCount = lParentLinks.size ();
        
        if (lCount > 1) {
            if (this.javaConfig.ERRORONFIRSTWARNING) {
                throw new TemplateException (Messages.getString ("Error.ClassMultipleInheritance", JavaDesignerUtils.getJavaName (theGeneralClass)));
            } else {
                this.report.addWarning (Messages.getString ("Warning.ClassMultipleInheritance", JavaDesignerUtils.getJavaName (theGeneralClass)), theGeneralClass, "");
            }
        }
        
        if (lCount >= 1) {
            // if multiple inheritance, take only the first into account
        
            Generalization firstParent = lParentLinks.get (0);
            if (firstParent.getSuperType () instanceof Class) {
                Class superTypeClass = (Class) firstParent.getSuperType ();
                boolean generateFullName = false;
        
                /* Java rules are more complex than that.
                 *  example :
                 *  -----------------------------------------
                 *  package somewhere;
                 *  public class A {
                 *      protected class InnerA {
                 *      }
                 *  }
                 *  ----------------------------------------
                 *  package togenerate;
                 *  import somewhere.A;
                 *  class FailToGenerateButShouldNot extends A {
                 *     class InnerOfFail extends InnerA <-- extends is ok because FailToGenerateButShouldNot extends A but check on visibility raises an error
                 *     {
                 *     }
                 *  }
                 *  ----------------------------------------
                if ((!superTypeClass.getVisibility ().equals (VisibilityMode.PUBLIC)) &&
                        (!(isinTheSamePackage (superTypeClass, theGeneralClass)))) {
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.ExternClassInheritance", JavaDesignerUtils.getJavaName (theGeneralClass), JavaDesignerUtils.getJavaName (superTypeClass)));
                    } else {
                        this.report.addWarning (Messages.getString ("Warning.ExternClassInheritance", JavaDesignerUtils.getJavaName (theGeneralClass), JavaDesignerUtils.getJavaName (superTypeClass)), theGeneralClass, "");
                    }
        
                } else */
                if (superTypeClass.isIsLeaf ()) {
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.NonFinalClassInheritance", JavaDesignerUtils.getJavaName (theGeneralClass), JavaDesignerUtils.getJavaName (superTypeClass)));
                    } else {
                        this.report.addWarning (Messages.getString ("Warning.NonFinalClassInheritance", JavaDesignerUtils.getJavaName (theGeneralClass), JavaDesignerUtils.getJavaName (superTypeClass)), theGeneralClass, "");
                    }
                }
        
                // generate annotations
                if (isJDK8Compatible()) {
                    ret.append(this.typeManager.computeAnnotations(firstParent, true, false));
                }
        
                if (isFullNameGeneration (firstParent)) {
                    generateFullName = true;
                }
        
                ret.append (" ");
                ret.append (computeName (superTypeClass, generateFullName));
                ret.append (getBindingParameters (firstParent));
            }
        }
        return ret;
    }

    private CharSequence computeExtendsWithTaggedValue(final DataType theDataType) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        CharSequence lParentLinks = computeDataTypeInheritance (theDataType);
        int lCount = lParentLinks.length ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theDataType, JavaDesignerTagTypes.DATATYPE_JAVAEXTENDS)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String value = tagParameter.getValue ();
                if (!value.isEmpty()) {
                    if (lCount == 0) {
                        ret.append (" ");
                        ret.append (value);
                        lCount++;
                    } else {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.DataTypeMultipleInheritance", JavaDesignerUtils.getJavaName (theDataType)));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.DataTypeMultipleInheritance", JavaDesignerUtils.getJavaName (theDataType)), theDataType, "");
                        }
                    }
                }
            }
        }
        return ret;
    }

    private CharSequence computeExtendsWithTaggedValue(final GeneralClass theGeneralClass) throws TemplateException {
        if (theGeneralClass instanceof DataType) {
            return computeExtendsWithTaggedValue ((DataType) theGeneralClass);
        }
        
        StringBuilder ret = new StringBuilder ();
        int parentCount = getParentInheritances (theGeneralClass).size ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theGeneralClass, JavaDesignerTagTypes.CLASS_JAVAEXTENDS)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String value = tagParameter.getValue ();
                if (!value.isEmpty()) {
                    if (parentCount == 0) {
                        ret.append (" ");
                        ret.append (value);
                        parentCount++;
                    } else {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.ClassMultipleInheritance", JavaDesignerUtils.getJavaName (theGeneralClass)));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.ClassMultipleInheritance", JavaDesignerUtils.getJavaName (theGeneralClass)), theGeneralClass, "");
                        }
                    }
                }
            }
        }
        return ret;
    }

    private CharSequence computeFieldModifiers(final Feature theFeature) {
        StringBuilder ret = new StringBuilder ();
        
        Classifier lComposedClass = (Classifier) theFeature.getCompositionOwner ();
        
        if (isInterface (lComposedClass)) {
            // must be public, static and final
            ret.append ("public static final ");
        
            VisibilityMode visibility = theFeature.getVisibility ();
        
            // Do not check property's visibilities, they are always public in interfaces
            if (!theFeature.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY) && !theFeature.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)){
                switch (visibility) {
                case PUBLIC:
                case VISIBILITYUNDEFINED:
                    // Nothing to do
                        break;
                default:
                    this.report.addWarning (Messages.getString ("Warning.InterfaceNonPublicAttribute", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theFeature)), theFeature, "");
                }
            }
        
            if (!(theFeature.isIsClass ())) {
                this.report.addWarning (Messages.getString ("Warning.InterfaceNonStaticAttribute", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theFeature)), theFeature, "");
            }
        
            if (!(theFeature.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL))) {
                this.report.addWarning (Messages.getString ("Warning.InterfaceNonFinalAttribute", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theFeature)), theFeature, "");
            }
        } else { // Not an Interface
            ret.append (getJavaVisibility (theFeature));
            if ((theFeature.isIsClass ())) {
                ret.append ("static ");
            }
        
            if (theFeature.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAFINAL)) {
                ret.append ("final ");
                if (theFeature.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)) {
                    this.report.addWarning (Messages.getString ("Warning.VolatileFinalAttribute", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theFeature)), theFeature, "");
                }
            } else if (theFeature.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVAVOLATILE)) {
                ret.append ("volatile ");
            }
        
            if (theFeature.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATRANSIENT)) {
                ret.append ("transient ");
            }
        }
        return ret;
    }

    private CharSequence updateNewlines(final String text) {
        StringBuilder ret = new StringBuilder ();
        String simpleNL = "\n";
        
        for (String line : text.split (simpleNL)) {
            // remove indentation from previous generation
            ret.append (line);
            if (line.length () > 0) {
                if (line.charAt (line.length () - 1) != '\r') {
                    ret.append (NL);
                } else {
                    ret.append (simpleNL);
                }
            } else {
                ret.append (NL);
            }
        }
        return ret;
    }

    private CharSequence computeFormattedCode(final String text) {
        StringBuilder ret = new StringBuilder ();
        String lIndent = getCurrentIndent ().toString ();
        String indentPattern = "^" + lIndent;
        
        String simpleNL = "\n";
        
        boolean removeIndent = true;
        for (String line : text.split (simpleNL)) {
            // remove indentation from previous generation
            String formatedLine;
            if (removeIndent) {
                formatedLine = line.replaceAll (indentPattern, "");
                if (line.length () == formatedLine.length ()) {
                    removeIndent = false;
                }
            } else {
                formatedLine = line;
            }
        
            ret.append (lIndent);
            ret.append (formatedLine);
        
            if (formatedLine.length () > 0) {
                if (formatedLine.charAt (formatedLine.length () - 1) != '\r') {
                    ret.append (NL);
                } else {
                    ret.append (simpleNL);
                }
            } else {
                ret.append (NL);
            }
        }
        return ret;
    }

    private CharSequence computeFormattedJavaDoc(final String text) {
        CharSequence lIndent = getCurrentIndent ();
        StringBuilder ret = new StringBuilder ();
        
        if (text.length () != 0) {
            ret.append (lIndent);
            ret.append ("/**");
            ret.append (NL);
        
            String simpleNL = "\n";
        
            for (String line : text.split (simpleNL)) {
                ret.append (lIndent);
                ret.append (" * ");
                ret.append (line);
        
                if (line.length () > 0) {
                    if (line.charAt (line.length () - 1) != '\r') {
                        ret.append (NL);
                    } else {
                        ret.append (simpleNL);
                    }
                } else {
                    ret.append (NL);
                }
            }
        
            ret.append (lIndent);
            ret.append (" */");
            ret.append (NL);
        }
        return ret;
    }

    /**
     * Main generation method. Generates the code for this in the output PrintStream.<BR>
     * The element can be a Class, a DataType, an Interface, an Enumeration or a Package.
     * @param element The model element to generate the code from.
     * @param out The output to generate into.
     * @param config Cache for all parameters used for the generation.
     * @throws org.modelio.module.javadesigner.api.CustomException Thrown if an error occurred during type computing.
     * @throws org.modelio.module.javadesigner.generator.TemplateException Thrown if an error occurred during the generation.
     */
    public void generate(final MObject element, final PrintStream out, final JavaConfiguration config) throws CustomException, TemplateException {
        this.javaConfig = config;
        
        generateCopyright(element, out);
        
        if (element instanceof Class) {
            generate ((Class) element, out);
        } else if (element instanceof Interface) {
            generate ((Interface) element, out);
        } else if (element instanceof Enumeration) {
            generate ((Enumeration) element, out);
        } else if (element instanceof DataType) {
            generate ((DataType) element, out);
        } else if (element instanceof Package) {
            generate ((Package) element, out);
        } else {
            throw new TemplateException ("Invalid element");
        }
    }

    private void generate(final GeneralClass classToGenerate, final PrintStream out) throws CustomException, TemplateException {
        out.append (computeTopText (classToGenerate));
        
        Package ownerPackage = getOwnerPackage (classToGenerate);
        if (ownerPackage != null) {
            out.append (computePackageDeclaration (ownerPackage));
        }
        
        /* Add all the imports */
        out.append (computeImports (classToGenerate));
        
        /* Add the class header */
        generateClassHeader (classToGenerate, out);
        
        /* Add the class body */
        generateClassBody (classToGenerate, out);
        
        /* Get all the other classes in the same file */
        for (GeneralClass theClass : getInternalClasses (classToGenerate)) {
            out.append (NL);
            generateClassHeader (theClass, out);
            generateClassBody (theClass, out);
            out.append (computeBottomText (theClass));
        }
        
        out.append (computeBottomText (classToGenerate));
    }

    private void generate(final Package thePackage, final PrintStream out) {
        out.println (getTextualsAnnotations (thePackage));
        out.println (computePackageDeclaration (thePackage));
    }

    private void generateClassBody(final GeneralClass classToGenerate, final PrintStream out) throws CustomException, TemplateException {
        out.append (computeOpenBlock ());
        
        /* Add enum literals */
        if (classToGenerate instanceof Enumeration) {
            out.append (computeEnumerationLitterals15 ((Enumeration) classToGenerate));
        }
        
        /* Add all Attributes */
        for (Attribute feature : classToGenerate.getOwnedAttribute ()) {
            if (!isExtern (feature)) {
                /* Attribute or AssociationEnd static */
                generateFields (out, feature);
            }
        }
        
        /* Add all AssociationEnds*/
        for (AssociationEnd feature : classToGenerate.getOwnedEnd ()) {
            if (!isExtern (feature)) {
                /* Attribute or AssociationEnd static */
                generateFields (out, feature);
            }
        }
        
        /* Add all operations */
        for (Operation theOperation : classToGenerate.getOwnedOperation()) {
            // First, check if the element is "nocode"
            if (!isExtern (theOperation)) {
                generateOperation(classToGenerate, out, theOperation);
            }
        }
        
        if (hasInvariant (classToGenerate)) {
            out.append (getInvariant (classToGenerate));
        }
        
        out.append (computeMembersText (classToGenerate));
        
        
        /* Add all inner Classes */
        for (GeneralClass innerClass : getInnerClasses (classToGenerate)) {
            generateClassHeader (innerClass, out);
            generateClassBody (innerClass, out);
            out.append (computeBottomText (innerClass));
            out.append (NL);
        }
        
        /* Add all inner Enumerations */
        for (Enumeration enumeration : getEnumerations (classToGenerate)) {
            generateClassHeader (enumeration, out);
            generateClassBody (enumeration, out);
            out.append (computeBottomText (enumeration));
            out.append (NL);
        }
        
        /* Add all inner DataTypes */
        for (DataType dataType : getDataTypes (classToGenerate)) {
            if (!isExtern (dataType)) {
                out.append (computeJavaComment (dataType));
                out.append (computeJavaDoc (dataType));
                out.append (getTextualsAnnotations (dataType));
                out.append (getCurrentIndent ());
                out.append (getDataTypeDeclaration (dataType));
                out.append (computeOpenBlock ());
                for (Attribute feature : dataType.getOwnedAttribute()) {
                    generateFields (out, feature);
                }
        
                for (AssociationEnd feature : dataType.getOwnedEnd()) {
                    generateFields (out, feature);
                }
        
                for (Operation theOperation : dataType.getOwnedOperation()) {
                    if (isCreateMethod (theOperation)) {
                        generateConstructor (out, theOperation);
                    } else if (isDeleteMethod (theOperation)) {
                        generateDestructor (out, theOperation);
                    } else {
                        out.append(computeMethod (theOperation));
                    }
                }
        
                out.append (computeCloseBlock ());
                out.append (NL);
            }
        }
        
        out.append (computeCloseBlock ());
    }

    protected void generateOperation(final GeneralClass classToGenerate, final PrintStream out, final Operation theOperation) throws CustomException, TemplateException {
        if (!isInterface (classToGenerate) &&
                isCreateMethod (theOperation) &&
                isNotUndefinedTypeParameter (theOperation)) {
            generateConstructor (out, theOperation);
        } else if (isDeleteMethod (theOperation)) {
            generateDestructor (out, theOperation);
        } else {
            out.append (computeMethod (theOperation));
        }
    }

    private void generateClassHeader(final GeneralClass classToGenerate, final PrintStream out) throws CustomException, TemplateException {
        /* Add the doc and annotations */
        out.append (computeJavaComment (classToGenerate));
        out.append (computeHeaderText (classToGenerate));
        out.append (computeJavaDoc (classToGenerate));
        
        out.append (getTextualsAnnotations (classToGenerate));
        
        if (isJavaAnnotation (classToGenerate)) {
            out.append (computeDocumentedAnnotation (classToGenerate));
            out.append (computeInheritedAnnotation (classToGenerate));
            out.append (computeRetentionAnnotation (classToGenerate));
            out.append (computeTargetAnnotation (classToGenerate));
            out.append (computeModifiers (classToGenerate));
            out.append (computeAnnotationName (classToGenerate));
        } else if (classToGenerate instanceof Enumeration) {
            out.append (computeEnumerationDeclaration ((Enumeration) classToGenerate));
        } else {
            out.append (computeModifiers (classToGenerate));
            out.append (computeClassDeclaration (classToGenerate));
        }
        
        out.append (getClassGenericParameters (classToGenerate));
        
        /* Add the extends */
        if (hasExtends (classToGenerate)) {
            out.append (" extends");
            if (isInterface (classToGenerate)) {
                out.append (computeInterfaceInheritance (classToGenerate));
                out.append (computeInterfaceExtendsWithTaggedValue (classToGenerate));
            } else {
                out.append (computeExtends (classToGenerate));
                out.append (computeExtendsWithTaggedValue (classToGenerate));
            }
        }
        
        /* Add the implements */
        if (hasImplements (classToGenerate) && !isInterface (classToGenerate)) {
            out.append (" implements");
            out.append (computeImplements (classToGenerate));
        }
    }

    private void generateConstructor(final PrintStream out, final Operation theOperation) throws CustomException, TemplateException {
        out.append (computeJavaComment (theOperation));
        out.append (computeJavaDoc (theOperation));
        out.append (getTextualsAnnotations (theOperation));
        out.append (getCurrentIndent ());
        out.append (computeMethodModifiers (theOperation));
        if (isJDK8Compatible()) {
            out.append (getJavaTemplateParameters (theOperation));
        }
        out.append (JavaDesignerUtils.getJavaName (theOperation.getOwner ()));
        out.append (computeMethodParameters (theOperation));
        out.append (computeExceptions (theOperation));
        out.append (computeMethodContent (theOperation));
        out.append (NL);
    }

    private void generateDestructor(final PrintStream out, final Operation theOperation) throws CustomException, TemplateException {
        out.append (computeJavaComment (theOperation));
        out.append (computeJavaDoc (theOperation));
        out.append (getTextualsAnnotations (theOperation));
        out.append (getCurrentIndent ());
        out.append (computeMethodModifiers (theOperation));
        out.append ("void ");
        out.append ("finalize");
        out.append (computeMethodParameters (theOperation));
        out.append (computeExceptions (theOperation));
        out.append (computeMethodContent (theOperation));
        out.append (NL);
    }

    private void generateFields(final PrintStream out, final Feature feature) throws CustomException, TemplateException {
        if (feature instanceof Attribute) {
            Attribute theAttribute = (Attribute) feature;
            if (isNotUndefinedType (theAttribute)) {
                if (isNotClassMemberInAnInterfaceWithoutInit (theAttribute)) {
                    if (!theAttribute.isIsAbstract () && !theAttribute.isIsDerived ()) {
                        out.append (computeJavaComment (theAttribute));
                        out.append (computeJavaDoc (theAttribute));
                        if (isAnnotationAttribute (theAttribute)) {
                            out.append (getTextualsAnnotations (theAttribute));
                            out.append (computeAnnotationDeclaration (theAttribute));
                        } else if (theAttribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)){
                            out.append (computePropertyDefinitionAnnotation ());
                            out.append (getTextualsAnnotations (theAttribute));
                            out.append (getPropertyDeclaration (theAttribute));
                        } else {
                            out.append (getTextualsAnnotations (theAttribute));
                            out.append (getDeclaration (theAttribute));
                        }
                    }
                } else if (theAttribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)){
                    out.append (getPropertyDeclaration (theAttribute));
                }
            }
        } else if (feature instanceof AssociationEnd) {
            AssociationEnd theAssociationEnd = (AssociationEnd) feature;
            if (theAssociationEnd.isNavigable() &&
                    isNotBadInnerClassAssociation (theAssociationEnd)) {
                if (JavaDesignerUtils.getJavaName (theAssociationEnd).equals ("class")) {
                    throw new TemplateException (Messages.getString ("Error.AssociationEndBadName", JavaDesignerUtils.getJavaName ((ModelElement) feature.getCompositionOwner ()), JavaDesignerUtils.getJavaName (feature)));
                }
        
                if (isNotUndefinedType (theAssociationEnd)) {
                    if (!theAssociationEnd.isIsAbstract () && !theAssociationEnd.isIsDerived ()) {
                        out.append (computeJavaComment (theAssociationEnd));
                        out.append (computeJavaDoc (theAssociationEnd));
                        if (isAnnotationAttribute (theAssociationEnd)) {
                            out.append (getTextualsAnnotations (theAssociationEnd));
                            out.append (computeAnnotationDeclaration (theAssociationEnd));
                        } else if (theAssociationEnd.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)){
                            if (theAssociationEnd.isIsClass() || !(theAssociationEnd.getSource() instanceof Interface)) {
                                out.append (computePropertyDefinitionAnnotation ());
                                out.append (getTextualsAnnotations (theAssociationEnd));
                            }
                            out.append (getPropertyDeclaration (theAssociationEnd));
                        } else if (theAssociationEnd.isIsClass() || !(theAssociationEnd.getSource() instanceof Interface)) {
                            out.append (getTextualsAnnotations (theAssociationEnd));
                            out.append (getDeclaration (theAssociationEnd));
                        }
                    }
                }
            }
        }
    }

    private Set<String> getImportsFromTypesPackages(final GeneralClass element) throws CustomException, TemplateException {
        Set<String> ret = new TreeSet<> ();
        Set<String> imports = getNeededJavaUtilImports (element);
        
        for (String theImport : imports) {
            switch (theImport) {
            case "ArrayBlockingQueue":
            case "ConcurrentHashMap":
            case "ConcurrentLinkedQueue":
            case "CopyOnWriteArrayList":
            case "CopyOnWriteArraySet":
            case "LinkedBlockingQueue":
            case "PriorityBlockingQueue":
            case "SynchronousQueue":
            case "ConcurrentSkipListMap":
            case "ConcurrentSkipListSet":
                ret.add ("import java.util.concurrent." + theImport + ";");
                break;
            case "AbstractCollection":
            case "AbstractList":
            case "AbstractMap":
            case "AbstractQueue":
            case "AbstractSequentialList":
            case "AbstractSet":
            case "ArrayDeque":
            case "ArrayList":
            case "Collection":
            case "Deque":
            case "EnumMap":
            case "EnumSet":
            case "HashMap":
            case "HashSet":
            case "Hashtable":
            case "IdentityHashMap":
            case "LinkedHashMap":
            case "LinkedHashSet":
            case "LinkedList":
            case "List":
            case "Map":
            case "NavigableMap":
            case "NavigableSet":
            case "PriorityQueue":
            case "Queue":
            case "Set":
            case "SortedMap":
            case "SortedSet":
            case "Stack":
            case "TreeMap":
            case "TreeSet":
            case "Vector":
            case "WeakHashMap":
                ret.add ("import java.util." + theImport + ";");
                break;
            case "PhantomReference":
            case "Reference":
            case "SoftReference":
            case "WeakReference":
                ret.add ("import java.lang.ref." + theImport + ";");
                break;
            default:
                break;
            }
        }
        return ret;
    }

    private CharSequence computeJavaCode(final EnumerationLiteral literal) {
        StringBuilder ret = new StringBuilder ();
        
        String javaCode = literal.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.ENUMERATIONLITERAL_JAVACODE);
        
        if (javaCode != null && javaCode.length () != 0) {
            ret.append (computeOpenBlock ());
            ret.append (getCurrentIndent ());
            ret.append (updateNewlines (javaCode));
            ret.append (NL);
            decreaseIndentLevel ();
            ret.append (getCurrentIndent ());
            ret.append ("}");
            }
        return ret;
    }

    private CharSequence computeMethod(final Operation theOperation) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder();
        
        if (isNotRedefinedFinalMethod (theOperation) &&
                isNotUndefinedTypeParameter (theOperation)) {
            ret.append (computeJavaComment (theOperation));
            ret.append (computeJavaDoc (theOperation));
            ret.append (getTextualsAnnotations (theOperation));
        
            ret.append (getCurrentIndent ());
            ret.append (computeMethodModifiers (theOperation));
            ret.append (getJavaTemplateParameters (theOperation));
        
            Parameter returnParameter = theOperation.getReturn ();
            if (returnParameter == null) {
                ret.append ("void ");
            } else {
                ret.append (getDeclaration (returnParameter));
            }
            ret.append (JavaDesignerUtils.getJavaName (theOperation));
        
            ret.append (computeMethodParameters (theOperation));
            ret.append (computeExceptions (theOperation));
            ret.append (computeMethodContent (theOperation));
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computeMethodContent(final Operation theOperation) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder();
        
        GeneralClass operationOwner = (GeneralClass) theOperation.getOwner ();
        if (isAbstract (theOperation) || isNative (theOperation) || (isInterface (operationOwner) && !theOperation.isIsClass () && !isImplemented(theOperation))) {
            ret.append(";");
            ret.append(NL);
        } else {
            ret.append (computeOpenBlock ());
            ret.append (computeBodyHeader (theOperation));
            if (!isCreateMethod (theOperation) && !theOperation.isIsClass ()) {
                ret.append (computeCallInvariant (theOperation));
            }
            ret.append (computePreCondition (theOperation));
            ret.append (computeMethodBody (theOperation));
            ret.append (computePostCondition (theOperation));
            if (!isDeleteMethod (theOperation) && !theOperation.isIsClass ()) {
                ret.append (computeCallInvariant (theOperation));
            }
            ret.append (computeBodyBottom (theOperation));
            ret.append (computeCloseBlock ());
        }
        return ret;
    }

    private CharSequence computeMethodParameters(final Operation theOperation) throws CustomException {
        StringBuilder ret = new StringBuilder();
        
        List<Parameter> operationParameters = theOperation.getIO ();
        final int lastparpos = operationParameters.size();
        int curparpos = 1;
        ret.append("(");
        for (Parameter parameter : operationParameters) {
            ret.append(getDeclaration(parameter));
            if (curparpos++ < lastparpos) {
                ret.append(", ");
            }
        }
        ret.append(")");
        return ret;
    }

    private boolean isFullNameGeneration(final ModelElement theModelElement) {
        boolean value = this.javaConfig.FULLNAMEGENERATION;
        return value ||
                                        theModelElement.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAFULLNAME);
    }

    private CharSequence computeAnnotationDeclaration(final AssociationEnd theAssociationEnd) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getCurrentIndent ());
        ret.append (this.typeManager.getTypeDeclaration(this.session, theAssociationEnd, isFullNameGeneration (theAssociationEnd)));
        ret.append (JavaDesignerUtils.getJavaName (theAssociationEnd));
        ret.append ("()");
        ret.append (computeAnnotationDefaultValue (theAssociationEnd));
        ret.append (";");
        ret.append (computeAssociationEndInitialValueComment (theAssociationEnd));
        ret.append (NL);
        ret.append (NL);
        return ret;
    }

    private CharSequence computeAnnotationDeclaration(final Attribute theAttribute) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getCurrentIndent ());
        ret.append (this.typeManager.getTypeDeclaration (this.session, theAttribute, isFullNameGeneration (theAttribute)));
        ret.append (JavaDesignerUtils.getJavaName (theAttribute));
        ret.append ("()");
        ret.append (computeAnnotationDefaultValue (theAttribute));
        ret.append (";");
        ret.append (computeAttributeInitialValueComment (theAttribute));
        ret.append (NL);
        ret.append (NL);
        return ret;
    }

    private CharSequence getBindingParameters(final Generalization theGeneralization) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theGeneralization, JavaDesignerTagTypes.GENERALIZATION_JAVABIND)) {
            for (Iterator<TagParameter> iterator = tag.getActual ().iterator () ; iterator.hasNext () ;) {
                TagParameter theTagParameter = iterator.next ();
                ret.append (theTagParameter.getValue ());
        
                if (iterator.hasNext ()) {
                    ret.append (",");
                }
            }
        
            if (ret.length () > 0) {
                ret.insert (0, "<");
                ret.append (">");
            }
        }
        return ret;
    }

    private CharSequence getBindingParameters(final InterfaceRealization theRealization) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theRealization, JavaDesignerTagTypes.INTERFACEREALIZATION_JAVABIND)) {
            for (Iterator<TagParameter> iterator = tag.getActual ().iterator () ; iterator.hasNext () ;) {
                TagParameter theTagParameter = iterator.next ();
                ret.append (theTagParameter.getValue ());
        
                if (iterator.hasNext ()) {
                    ret.append (",");
                }
            }
        
            if (ret.length () > 0) {
                ret.insert (0, "<");
                ret.append (">");
            }
        }
        return ret;
    }

    private CharSequence getClassGenericParameters(final GeneralClass theGeneralClass) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ("<");
        CharSequence typeName;
        
        for (Iterator<TemplateParameter> iterator = theGeneralClass.getTemplate ().iterator () ; iterator.hasNext () ;) {
            TemplateParameter theTemplateParameter = iterator.next ();
        
            if (isJDK8Compatible()) {
                ret.append(this.typeManager.computeAnnotations(theTemplateParameter, false, true));
            }
        
            ret.append (JavaDesignerUtils.getJavaName (theTemplateParameter));
        
            /* gestion extends */
            // TODO template 'type'/'default type' management is messy, we need to homogenize it
            ModelElement theType = theTemplateParameter.getType ();
            if (theType != null && theType instanceof GeneralClass) {
                typeName = JavaDesignerUtils.getJavaName (theType);
                if (JavaDesignerUtils.isPredefinedType (typeName.toString ())) {
                    typeName = this.typeManager.getJavaType(this.session, (GeneralClass) theType, true, true);
                } else {
                    typeName = computeName(theType, isFullNameGeneration(theTemplateParameter));
                }
        
                ret.append (" extends ");
                ret.append (typeName);
            } else {
                typeName = computeExtendsWithTaggedValue (theTemplateParameter);
                if (typeName.length () > 0) {
                    ret.append (" extends");
                    ret.append (typeName);
                }
            }
        
            if (iterator.hasNext ()) {
                ret.append (", ");
            }
        }
        
        if (ret.length () > 1) {
            ret.append (">");
        } else {
            ret.delete (0, 1);
        }
        return ret;
    }

    private Set<String> getClassImport(final GeneralClass theGeneralClass, final Set<NameSpace> notImportedElements) {
        Set<String> imports = new TreeSet<> ();
        Set<NameSpace> importedElements = new TreeSet<> ();
        
        
        // import DataType annotation if required (only in roundTrip mode)
        if (this.javaConfig.GENERATIONMODE_ROUNDTRIP) {
            if (theGeneralClass instanceof DataType && !JavaDesignerUtils.getJavaName (theGeneralClass).equals ("DataType")) {
                imports.add ("import com.modeliosoft.modelio.javadesigner.annotations.DataType;");
            }
        }
        
        // Collect implicitly imported classes & interfaces for template parameters, generalization and interface realization part
        collectImplicitlyImported(theGeneralClass, notImportedElements);
        
        // Template Parameter
        for (TemplateParameter templateParameter : theGeneralClass.getTemplate ()) {
            // TODO template 'type'/'default type' management is messy, we need to homogenize it
            if (!isFullNameGeneration(templateParameter)) {
                ModelElement defaultType = templateParameter.getType ();
                if (defaultType != null && defaultType instanceof GeneralClass) {
                    imports.add(getImportLine((GeneralClass)defaultType, notImportedElements));
                }
            }
        }
        
        // Inheritance
        for (Generalization theGeneralization : theGeneralClass.getParent ()) {
            if (!JavaDesignerUtils.isNoCode(theGeneralization) && !isFullNameGeneration(theGeneralization)) {
                NameSpace superType = theGeneralization.getSuperType ();
                if (superType instanceof GeneralClass) {
                    imports.add(getImportLine(superType, notImportedElements));
                }
            }
        }
        
        // Implementations
        for (InterfaceRealization theRealization : theGeneralClass.getRealized ()) {
            if (!JavaDesignerUtils.isNoCode(theRealization) && !isFullNameGeneration(theRealization)) {
                imports.add(getImportLine(theRealization.getImplemented(), notImportedElements));
            }
        }
        
        // Imported elements
        for (ElementImport theImport : theGeneralClass.getOwnedImport ()) {
            NameSpace importedNameSpace = theImport.getImportedElement ();
            if (theImport.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASTATIC)) {
                // static dependencies are directly added to classes setOf.
                if (importedNameSpace instanceof GeneralClass) {
                    imports.add ("import static " +
                            JavaDesignerUtils.getFullJavaName (this.session, importedNameSpace) +
                            ".*;");
                }
            } else {
                // Non static Dependencies
                // (explicit import in the model shouldn't be 'censored' by Java implicit import rules)
                imports.add(getImportLine(importedNameSpace, Collections.<NameSpace>emptySet()));
            }
        }
        
        // Package Imports
        for (PackageImport theImport : theGeneralClass.getOwnedPackageImport ()) {
            // (explicit import in the model shouldn't be 'censored' by Java implicit import rules)
            imports.add(getImportLine(theImport.getImportedPackage(), Collections.<NameSpace>emptySet()));
        }
        
        // Collect implicitly imported element for class member
        collectImplicitlyImportedInner(theGeneralClass, notImportedElements);
        
        // Operations
        for (Operation theOperation : theGeneralClass.getOwnedOperation()) {
            if (!JavaDesignerUtils.isNoCode (theOperation)) { // Ignore no code
                // Returned parameter
                Parameter returnedParameter = theOperation.getReturn ();
                if (returnedParameter != null &&
                        !isFullNameGeneration (returnedParameter) &&
                        !returnedParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR)) {
                    GeneralClass returnedType = returnedParameter.getType ();
                    importedElements.add (returnedType);
                }
        
                // IO parameter
                for (Parameter theParameter : theOperation.getIO ()) {
                    if (!isFullNameGeneration (theParameter) &&
                            !theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR)) {
                        GeneralClass parameterType = theParameter.getType ();
                        importedElements.add (parameterType);
                    }
                }
        
                // Template parameters
                for (TemplateParameter templateParameter : theOperation.getTemplate ()) {
                    // TODO template 'type'/'default type' management is messy, we need to homogenize it
                    if (!isFullNameGeneration(templateParameter)) {
                        ModelElement defaultType = templateParameter.getDefaultType ();
                        if (defaultType != null && defaultType instanceof GeneralClass) {
                            importedElements.add((GeneralClass) defaultType);
                        }
                    }
                }
        
                // Raised exception
                for (RaisedException theException : theOperation.getThrown ()) {
                    if (!isFullNameGeneration (theException)) {
                        Classifier thrownType = theException.getThrownType ();
                        importedElements.add (thrownType);
                    }
                }
        
                // Element Imports
                for (ElementImport theImport : theOperation.getOwnedImport ()) {
                    NameSpace importedNameSpace = theImport.getImportedElement ();
                    if (importedNameSpace instanceof Class || importedNameSpace instanceof Interface
                            || importedNameSpace instanceof Package) {
                        // (explicit import in the model shouldn't be
                        // 'censored' by Java implicit import rules)
                        imports.add(getImportLine(importedNameSpace, Collections.<NameSpace>emptySet()));
                    } else if (importedNameSpace instanceof Signal) {
                        // Link to exception stereotyped signal
                        if (importedNameSpace.isStereotyped(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.ELEMENTIMPORT_EXCEPTION) && theImport.isStereotyped(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.ELEMENTIMPORT_THROW)) {
                            importedElements.add (importedNameSpace);
                        }
                    }
                }
            }
        }
        
        // Attributes
        for (Attribute theAttribute : theGeneralClass.getOwnedAttribute ()) {
            if (!JavaDesignerUtils.isNoCode (theAttribute)) { // Ignore no code
                if (theAttribute.isIsClass() || !isInterface(theAttribute.getOwner())) {
                    getAttributeImport(theAttribute, importedElements, imports);
                }
            }
        }
        
        // AssociationEnds
        for (AssociationEnd theAssociationEnd : theGeneralClass.getOwnedEnd ()) {
            if (!JavaDesignerUtils.isNoCode (theAssociationEnd)) { // Ignore no code
                if (!isFullNameGeneration (theAssociationEnd) && !theAssociationEnd.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR)) {
                    if (theAssociationEnd.isNavigable ()) {
                        if (theAssociationEnd.isIsClass() || !isInterface(theAssociationEnd.getSource())) {
                            Classifier destination = theAssociationEnd.getTarget();
                            if (destination != null) {
                                importedElements.add (destination);
                            }
                            // qualifiers
                            for (Attribute qual : theAssociationEnd.getQualifier()) {
                                if (!JavaDesignerUtils.isNoCode (qual)) {
                                    getAttributeImport(qual, importedElements, imports);
                                }
                            }
                        }
                    }
                }
        
                if (this.javaConfig.GENERATIONMODE_ROUNDTRIP) {
                    if (theAssociationEnd.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)) {
                        imports.add ("import com.modeliosoft.modelio.javadesigner.annotations.mdl;");
                    }
                }
            }
        }
        
        // import generation from elements
        for (NameSpace importedNameSpace : importedElements) {
            imports.add(getImportLine(importedNameSpace, notImportedElements));
        }
        
        // Inner classes
        for (ModelTree ownedElements : theGeneralClass.getOwnedElement ()) {
            if (ownedElements instanceof GeneralClass) {
                imports.addAll (getClassImport ((GeneralClass) ownedElements, new TreeSet<>(notImportedElements)));
            }
        }
        
        // Add all other imports from tags
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theGeneralClass, JavaDesignerTagTypes.CLASS_JAVAIMPORT)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String val = tagParameter.getValue ();
                if (val.length () != 0) {
                    imports.add ("import " + val + ";");
                }
            }
        }
        return imports;
    }

    private String getImportLine(final NameSpace importedNameSpace, final Set<NameSpace> notImportedElements) {
        String importLine = "";
        if (importedNameSpace != null && !JavaDesignerUtils.isNoCode (importedNameSpace)) {
            if (importedNameSpace instanceof GeneralClass) {
                GeneralClass importedClass = (GeneralClass) importedNameSpace;
                // Check internal files
                if (!notImportedElements.contains(importedClass)) {
                    String importName = JavaDesignerUtils.getFullJavaName (this.session, importedClass);
                    if (importedClass instanceof DataType) {
        
                        if (!JavaDesignerUtils.isPredefinedType (importedClass.getName ())) {
                            // Not predefined Datatype
                            importLine = "import " + importName + ";";
                        } else {
                            // predefined Datatype
                            if (importedClass.getName ().equals ("date")) {
                                importLine = "import java.util.Date;";
                            }
                        }
                    } else if (importedClass instanceof Class ||
                            importedClass instanceof Interface ||
                            importedClass instanceof Enumeration) {
                        importLine = "import " + importName + ";";
                    }
                }
            } else if (importedNameSpace instanceof Package) {
                Package importedPackage = (Package)importedNameSpace;
                importLine = "import " +
                        JavaDesignerUtils.getFullJavaName(this.session, importedPackage) + ".*;";
            }
        }
        return importLine;
    }

    private void getAttributeImport(final Attribute anAttribute, final Set<NameSpace> importedElements, final Set<String> imports) {
        if (!isFullNameGeneration (anAttribute) &&
                !anAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR)) {
            GeneralClass theType = anAttribute.getType ();
            importedElements.add (theType);
        }
        
        if (this.javaConfig.GENERATIONMODE_ROUNDTRIP) {
            if (anAttribute.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY)) {
                imports.add ("import com.modeliosoft.modelio.javadesigner.annotations.mdl;");
            }
        }
    }

    /**
     * Collect implicitly imported classifiers that can be used as type reference for anything declared INSIDE a classifier
     * @param aGeneralClass : the classifier for which the collect is made
     * @param aImplicitImportSet : inout : the set that receive the collected classifier.
     */
    private void collectImplicitlyImported(final GeneralClass aGeneralClass, final Set<NameSpace> aImplicitImportSet) {
        ModelTree outer = aGeneralClass.getOwner();
        if (outer instanceof Package) {
            // Top level classifier : add all classifiers that belong to the same package (including current class)
            // Note : no special treatment for <<JavaFileGroup>> because it is assumed that such a class belong to the
            // same package as the current (although it is not enforced by the <<JavaFileGroup>> semantic)
            for (GeneralClass buddy : outer.getOwnedElement(GeneralClass.class)) {
                aImplicitImportSet.add(buddy);
            }
        }
    }

    /**
     * Should be a nested method of collectImplicitlyImported
     * @param aGeneralClass
     * @param aImplicitImportSet
     */
    private void collectImplicitlyImportedInner(final GeneralClass aGeneralClass, final Set<NameSpace> aImplicitImportSet) {
        // Inheritance
        for (Generalization gene : aGeneralClass.getParent()) {
            if (!JavaDesignerUtils.isNoCode(gene)) {
                NameSpace superType = gene.getSuperType();
                if (superType instanceof GeneralClass) {
                    collectImplicitlyImportedInner((GeneralClass) superType, aImplicitImportSet);
                }
            }
        }
        
        // Implementations
        for (InterfaceRealization real : aGeneralClass.getRealized()) {
            if (!JavaDesignerUtils.isNoCode(real)) {
                Interface superType = real.getImplemented();
                if (superType != null) {
                    collectImplicitlyImportedInner(superType, aImplicitImportSet);
                }
            }
        }
        
        // 1st level inner classes are implicitly imported
        for (ModelTree mt : aGeneralClass.getOwnedElement(GeneralClass.class)) {
            aImplicitImportSet.add((GeneralClass) mt);
        }
    }

    private CharSequence getDataTypeDeclaration(final DataType dataType) {
        StringBuilder ret = new StringBuilder ();
        
        ret.append ("class ");
        ret.append (JavaDesignerUtils.getJavaName (dataType));
        return ret;
    }

    private List<DataType> getDataTypes(final GeneralClass currentClass) {
        List<DataType> ret = new ArrayList<> ();
        
        for (DataType child : currentClass.getOwnedElement (DataType.class)) {
            ret.add (child);
        }
        return ret;
    }

    private CharSequence getDeclaration(final AssociationEnd theAssociationEnd) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getCurrentIndent ());
        ret.append (computeFieldModifiers (theAssociationEnd));
        ret.append (this.typeManager.getTypeDeclaration (this.session, theAssociationEnd, isFullNameGeneration (theAssociationEnd)));
        ret.append (JavaDesignerUtils.getJavaName (theAssociationEnd));
        ret.append (updateNewlinesExceptLast (this.typeManager.computeInitialValue (this.session, theAssociationEnd, isFullNameGeneration (theAssociationEnd))));
        ret.append (";");
        ret.append (computeAssociationEndInitialValueComment (theAssociationEnd));
        ret.append (NL);
        
        ret.append (NL);
        return ret;
    }

    private CharSequence getDeclaration(final Attribute theAttribute) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getCurrentIndent ());
        ret.append (computeFieldModifiers (theAttribute));
        ret.append (this.typeManager.getTypeDeclaration (this.session, theAttribute, isFullNameGeneration (theAttribute)));
        ret.append (JavaDesignerUtils.getJavaName (theAttribute));
        ret.append (updateNewlinesExceptLast (this.typeManager.computeInitialValue (this.session, theAttribute, isFullNameGeneration (theAttribute))));
        ret.append (";");
        ret.append (computeAttributeInitialValueComment (theAttribute));
        ret.append (NL);
        ret.append (NL);
        return ret;
    }

    private CharSequence getDeclaration(final Parameter theParameter) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        /* Insert the parameter itself */
        if (theParameter.getReturned () == null) {
            ret.append(this.typeManager.computeAnnotations(theParameter, false, true));
            ret.append (computeParameterModifiers (theParameter));
            ret.append (this.typeManager.getTypeDeclaration (this.session, theParameter, isFullNameGeneration (theParameter)));
            ret.append (" ");
            ret.append (JavaDesignerUtils.getJavaName (theParameter));
        } else {
            ret.append(this.typeManager.computeAnnotations(theParameter, false, true));
            ret.append (this.typeManager.getTypeDeclaration (this.session, theParameter, isFullNameGeneration (theParameter)));
            ret.append (" ");
        }
        return ret;
    }

    private GeneralClass getEnclosingClass(final GeneralClass theGeneralClass) {
        ModelTree parent = theGeneralClass.getOwner ();
        if (parent instanceof Interface) {
            return getEnclosingClass ((Interface) parent);
        } else if (parent instanceof Class &&
                !(parent instanceof Component)) {
            return getEnclosingClass ((Class) parent);
        }
        return theGeneralClass;
    }

    private CharSequence computeEnumerationDeclaration(final Enumeration element) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        ret.append (getCurrentIndent ());
        
        String visibility = ""; //$NON-NLS-1$
        if (element.getOwner () instanceof Interface) {
            switch (element.getVisibility ()) {
            case PUBLIC:
            case VISIBILITYUNDEFINED:
                // Nothing to do
                    break;
            default:
                this.report.addWarning (Messages.getString ("Warning.InterfaceNonPublicEnum", JavaDesignerUtils.getJavaName (element.getOwner ()), JavaDesignerUtils.getJavaName (element)), element, "");
            }
        } else {
            switch (element.getVisibility ()) {
            case PUBLIC: {
                visibility = "public "; //$NON-NLS-1$
                break;
            }
            case PROTECTED: {
                if (element.getOwner () instanceof Package) {
                    // enumeration in a package, visibility protected and private
                    // are not allowed
                    visibility = ""; //$NON-NLS-1$
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.InvalidEnumerationVisibility", JavaDesignerUtils.getJavaName (element)));
                    } else {
                        String msg = Messages.getString ("Warning.InvalidEnumerationVisibility", JavaDesignerUtils.getJavaName (element));
                        this.report.addWarning (msg, element, "");
                    }
                } else {
                    visibility = "protected "; //$NON-NLS-1$
                }
                break;
            }
            case PRIVATE: {
                if (element.getOwner () instanceof Package) {
                    // enumeration in a package, visibility protected and private
                    // are not allowed
                    visibility = ""; //$NON-NLS-1$
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.InvalidEnumerationVisibility", JavaDesignerUtils.getJavaName (element)));
                    } else {
                        String msg = Messages.getString ("Warning.InvalidEnumerationVisibility", JavaDesignerUtils.getJavaName (element));
                        this.report.addWarning (msg, element, "");
                    }
                } else {
                    visibility = "private "; //$NON-NLS-1$
                }
                break;
            }
            case VISIBILITYUNDEFINED: {
                visibility = ""; //$NON-NLS-1$
        
                if (this.javaConfig.ERRORONFIRSTWARNING) {
                    throw new TemplateException (Messages.getString ("Error.InvalidEnumerationVisibility", JavaDesignerUtils.getJavaName (element)));
                } else {
                    String msg = Messages.getString ("Warning.InvalidEnumerationVisibility", JavaDesignerUtils.getJavaName (element));
                    this.report.addWarning (msg, element, "");
                }
                break;
            }
            case PACKAGEVISIBILITY: {
                visibility = ""; //$NON-NLS-1$
                break;
            }
            default:
                visibility = "";
            }
        }
        
        ret.append (visibility);
        
        if (element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ENUMERATION_JAVASTATIC)) {
            ret.append ("static ");
        }
        
        ret.append ("enum "); //$NON-NLS-1$
        ret.append (JavaDesignerUtils.getJavaName (element));
        return ret;
    }

    private CharSequence computeEnumerationLitterals15(final Enumeration element) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        List<EnumerationLiteral> value = element.getValue ();
        for (int i = 0; i < value.size(); i++) {
            EnumerationLiteral literal = value.get(i);
        
            StringBuilder arguments = new StringBuilder ();
        
            // Inserting javadoc
            ret.append (computeJavaComment (literal));
            ret.append (computeJavaDoc (literal));
        
            ret.append (getTextualsAnnotations (literal));
        
            // Add arguments
            for (TaggedValue tag : ModelUtils.getAllTaggedValues (literal, JavaDesignerTagTypes.ENUMERATIONLITERAL_JAVAARGUMENTS)) {
                for (TagParameter param : tag.getActual ()) {
                    arguments.append (param.getValue ());
                    arguments.append (", "); //$NON-NLS-1$
                }
            }
        
            if (arguments.length () > 2) {
                arguments.delete (arguments.length () - 2, arguments.length ());
            }
        
            if (arguments.length () > 0) {
                arguments.insert (0, " ("); //$NON-NLS-1$
                arguments.append (")"); //$NON-NLS-1$
            }
        
            // Generate EnumerationLiteral value
            ret.append (getCurrentIndent ());
            ret.append (JavaDesignerUtils.getJavaName (literal));
            ret.append (arguments);
            ret.append (computeJavaCode (literal));
        
            if (i < value.size() - 1) {
                ret.append (",");
            } else {
                ret.append (";");
            }
        
            // Generate comment on this value
            ret.append (computeLiteralValueComment (literal));
        
            ret.append (NL);
        }
        
        if (value.size() == 0) {
            // if no enumeration literal but part or inner stuff, the ";" is mandatory
            // no ";" if JAVAMEMBERS because for upward compatibility it is assumed that the required ";" is in JAVAMEMBERS notes
            if (ModelUtils.getAllNotes (element, JavaDesignerNoteTypes.CLASS_JAVAMEMBERS).isEmpty()) {
                ret.append (getCurrentIndent ());
                ret.append (";");
                ret.append (NL);
            }
        }
        
        if (element.getOwnedAttribute().size() > 0 || element.getOwnedEnd().size() > 0 || element.getOwnedOperation().size() > 0) {
            ret.append (NL);
        }
        return ret;
    }

    private List<Enumeration> getEnumerations(final GeneralClass currentClass) {
        List<Enumeration> ret = new ArrayList<> ();
        
        for (Enumeration child : currentClass.getOwnedElement (Enumeration.class)) {
            if (!JavaDesignerUtils.isNoCode (child)) {
                ret.add (child);
            }
        }
        return ret;
    }

    private String getIdLineBegin() {
        return "//begin of modifiable zone";
    }

    private String getIdLineEnd() {
        return "//end of modifiable zone";
    }

    private List<GeneralClass> getInnerClasses(final GeneralClass currentClass) {
        List<GeneralClass> ret = new ArrayList<> ();
        
        for (ModelTree child : currentClass.getOwnedElement ()) {
            if (child instanceof Class || child instanceof Interface) {
                if (!JavaDesignerUtils.isNoCode (child)) {
                    ret.add ((GeneralClass) child);
                }
            }
        }
        return ret;
    }

    private List<GeneralClass> getInternalClasses(final GeneralClass generalClass) {
        List<GeneralClass> ret = new ArrayList<> ();
        
        /* UML2, un elementImport peut relier vers un enum?r? */
        for (ElementImport elementImport : generalClass.getOwnedImport ()) {
            if (elementImport.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAFILEGROUP)) {
                NameSpace importedElement = elementImport.getImportedElement ();
                if (importedElement instanceof GeneralClass) {
                    ret.add ((GeneralClass) importedElement);
                }
            }
        }
        return ret;
    }

    private CharSequence getInvariant(final GeneralClass theGeneralClass) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getClassInvariantJavadoc (theGeneralClass));
        ret.append (getCurrentIndent ());
        ret.append ("protected void ");
        ret.append (this.javaConfig.INVARIANTSNAME);
        ret.append ("() ");
        ret.append (computeOpenBlock ());
        ret.append (getInvariantBody (theGeneralClass));
        ret.append (computeCloseBlock ());
        ret.append (NL);
        return ret;
    }

    private CharSequence getInvariantBody(final GeneralClass theGeneralClass) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        // Generate invariant from parent class
        if (isParentWithInvariant (theGeneralClass)) {
            ret.append (getCurrentIndent ());
            ret.append ("super.");
            ret.append (this.javaConfig.INVARIANTSNAME);
            ret.append ("();");
            ret.append (NL);
        }
        
        int invariantCount = 0;
        for (Constraint theConstraint : theGeneralClass.getConstraintDefinition ()) {
            if (theConstraint.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINVARIANT)) {
                // Only the first invariant is generated
                if (invariantCount == 0) {
                    ret.append (computeFormattedCode (theConstraint.getBody ()));
                }
                invariantCount++;
            }
        }
        
        if (invariantCount > 1) {
            this.report.addWarning (Messages.getString ("Warning.warnMultipleInvariants", JavaDesignerUtils.getFullJavaName (this.session, theGeneralClass)), theGeneralClass, "");
        }
        return ret;
    }

    private CharSequence getClassInvariantJavadoc(final GeneralClass theGeneralClass) {
        StringBuilder res = new StringBuilder ();
        
        // Add all <<JavaDocInvariant>> constraints
        for (Constraint theConstraint : theGeneralClass.getConstraintDefinition ()) {
            if (theConstraint.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVADOCINVARIANT)
                    || theConstraint.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.CONSTRAINT_INVARIANT)) {
                if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                    res.append (getIdBegin (theConstraint));
                    res.append (computeFormattedJavaDoc (theConstraint.getBody ()));
                    res.append (getIdEnd (theConstraint));
                } else {
                    res.append (computeFormattedJavaDoc (theConstraint.getBody ()));
                }
            }
        }
        return res;
    }

    private CharSequence computeJavaComment(final ModelElement theModelElement) {
        String comment = theModelElement.getNoteContent (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVACOMMENT);
        
        if (comment != null && comment.length () > 0) {
            comment = updateNewlinesExceptLast(comment).toString();
            //            if (comment.contains (NL)) {
            //                /* multiline comment */
            //                comment = " /* " + comment + " */";
            //            } else {
            //                // one line comment
            //                comment = " // " + comment;
            //            }
            comment = comment + NL;
            return comment;
        }
        return "";
    }

    private CharSequence computeJavaDoc(final ModelElement theModelElement) throws CustomException {
        if (theModelElement instanceof Operation) {
            return computeJavaDoc ((Operation) theModelElement);
        }
        
        StringBuilder ret = new StringBuilder ();
        
        if (this.javaConfig.GENERATEJAVADOC) {
            String seeText = "";
        
            if ((theModelElement instanceof Class && !(theModelElement instanceof Component)) ||
                    theModelElement instanceof Attribute ||
                    theModelElement instanceof AssociationEnd) {
                seeText += getSeeJavadocContents (theModelElement);
            }
        
            List<Note> docNotes = ModelUtils.getAllNotes (theModelElement, JavaDesignerNoteTypes.CLASS_JAVADOC);
        
            if (this.javaConfig.DESCRIPTIONASJAVADOC) {
                docNotes.addAll (ModelUtils.getAllNotes (theModelElement, "description"));
            }
        
            if (docNotes.isEmpty ()) {
                // text.strip(NL);
                // text.substitute(NL + NL + NL, NL + NL);
        
                // TAS: generates JavaDoc with NO markers in MODEL_DRIVEN.
                if (this.javaConfig.GENERATIONMODE_MODELDRIVEN && this.javaConfig.GENERATEJAVADOC_MARKERS) {
                    ret.append (computeMarkersWithText (theModelElement, "Javadoc", computeFormattedJavaDoc (seeText).toString ()));
                } else {
                    ret.append (computeFormattedJavaDoc (seeText));
                }
            } else {
                if (docNotes.size () > 1) {
                    this.report.addWarning (Messages.getString ("Warning.warnMultipleDocumentationNotes", JavaDesignerUtils.getFullJavaName (this.session, theModelElement), "Operation"), theModelElement, "");
                }
        
                Note docNote = docNotes.get (0);
                String text = docNote.getContent ();
                text += seeText;
        
                if (this.javaConfig.GENERATIONMODE_MODELDRIVEN && this.javaConfig.GENERATEJAVADOC_MARKERS) {
                    ret.append (getIdBegin (docNote));
                    ret.append (computeFormattedJavaDoc (text));
                    ret.append (getIdEnd (docNote));
                } else {
                    ret.append (computeFormattedJavaDoc (text));
                }
            }
        }
        return ret;
    }

    private CharSequence computeJavaDoc(final Operation theOperation) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        if (this.javaConfig.GENERATEJAVADOC) {
            String text = "";
        
            List<Note> docNotes = ModelUtils.getAllNotes (theOperation, JavaDesignerNoteTypes.CLASS_JAVADOC);
            if (this.javaConfig.DESCRIPTIONASJAVADOC) {
                docNotes.addAll (ModelUtils.getAllNotes (theOperation, "description"));
            }
        
            if (docNotes.isEmpty ()) {
                // no notes
        
                // see http://java.sun.com/j2se/javadoc/writingdoccomments/#tag
        
                text += getParamJavadocContents (theOperation);
                text += getReturnJavadocContents (theOperation);
                text += getThrowsJavadocContents (theOperation);
                text += getSeeJavadocContents (theOperation);
        
                // TAS: generates JavaDoc with NO markers in MODEL_DRIVEN.
                if (this.javaConfig.GENERATIONMODE_MODELDRIVEN && this.javaConfig.GENERATEJAVADOC_MARKERS) {
                    ret.append (computeMarkersWithText (theOperation, "Javadoc", computeFormattedJavaDoc (text).toString ()));
                } else {
                    ret.append (computeFormattedJavaDoc (text));
                }
            } else {
                if (docNotes.size () > 1) {
                    this.report.addWarning (Messages.getString ("Warning.warnMultipleDocumentationNotes", JavaDesignerUtils.getFullJavaName (this.session, theOperation), "Operation"), theOperation, "");
                }
        
                // see http://java.sun.com/j2se/javadoc/writingdoccomments/#tag
        
                Note docNote = docNotes.get (0);
                text = docNote.getContent ();
                text += (NL);
                text += getParamJavadocContents (theOperation);
                text += getReturnJavadocContents (theOperation);
                text += getThrowsJavadocContents (theOperation);
                text += getSeeJavadocContents (theOperation);
        
                // text.strip(NL);
                // text.substitute(NL + NL + NL, NL + NL);
        
                // TAS: generates JavaDoc with NO markers in MODEL_DRIVEN.
                if (this.javaConfig.GENERATIONMODE_MODELDRIVEN && this.javaConfig.GENERATEJAVADOC_MARKERS) {
                    ret.append (getIdBegin (docNote));
                    ret.append (computeFormattedJavaDoc (text));
                    ret.append (getIdEnd (docNote));
                } else {
                    ret.append (computeFormattedJavaDoc (text));
                }
            }
        }
        return ret;
    }

    private CharSequence getJavaTemplateParameters(final Operation theOperation) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        /* compat */
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theOperation, JavaDesignerTagTypes.OPERATION_JAVATEMPLATEPARAMETERS)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                ret.append (tagParameter.getValue ());
                ret.append (",");
            }
        }
        
        /* MM UML2 */
        for (TemplateParameter templateParameter : theOperation.getTemplate ()) {
            if (isJDK8Compatible()) {
                ret.append(this.typeManager.computeAnnotations(templateParameter, false, true));
            }
            ret.append (JavaDesignerUtils.getJavaName (templateParameter));
        
            // TODO template 'type'/'default type' management is messy, we need to homogenize it
            ModelElement defaultType = templateParameter.getDefaultType ();
            if (defaultType != null && defaultType instanceof GeneralClass) {
                CharSequence typeName = JavaDesignerUtils.getJavaName (defaultType);
                if (JavaDesignerUtils.isPredefinedType (typeName.toString ())) {
                    typeName = this.typeManager.getJavaType(this.session, (GeneralClass) defaultType, true, true);
                } else {
                    typeName = computeName(defaultType, isFullNameGeneration(templateParameter));
                }
        
                ret.append (" extends ");
                ret.append (typeName);
            } else {
                CharSequence typeName = computeExtendsWithTaggedValue (templateParameter);
                if (typeName.length () > 0) {
                    ret.append (" extends ");
                    ret.append (typeName);
                }
            }
            ret.append (",");
        }
        
        int length = ret.length ();
        if (length > 0) {
            ret.delete (length - 1, length);
            length--;
        }
        
        if (length != 0) {
            ret.insert (0, "<");
            ret.append ("> ");
        }
        return ret;
    }

    private CharSequence getJavaVisibility(final Feature theFeature) {
        VisibilityMode visibility = theFeature.getVisibility ();
        
        // Set property's encapsulating visibility as defined in the custom file
        if (theFeature.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAATTRIBUTEPROPERTY) ||
                theFeature.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAASSOCIATIONENDPROPERTY)){
            visibility = this.typeManager.getPropertyCodeVisibility(visibility);
        }
        
        StringBuilder ret = new StringBuilder ();
        
        // the visibility for the declaration of variable for Attribute or
        // AssociationEnd
        switch (visibility) {
        case PUBLIC:
            ret.append ("public");
            break;
        case PROTECTED:
            ret.append ("protected");
            break;
        case PRIVATE:
            ret.append ("private");
            break;
        default:
            // No modifier to add
        }
        
        ret.append (" ");
        return ret;
    }

    private Note getOneTextOfType(final ModelElement theModelElement, final String noteType) {
        String textName = JavaDesignerUtils.getJavaName (theModelElement);
        
        List<Note> allNotes = ModelUtils.getAllNotes (theModelElement, noteType);
        if (allNotes.size () == 1) {
            return allNotes.get (0);
        } else if (allNotes.size () > 1) {
            this.report.addWarning (Messages.getString ("Error.Generation_error_external_DuplicateText", textName, noteType), theModelElement, "");
            return allNotes.get (0);
        }
        
        // return null if no note is found
        return null;
    }

    private Package getOwnerPackage(final ModelTree element) {
        ModelTree parent = element.getOwner ();
        
        if (parent == null) {
            // Should not happen, the worst case returns the root package
            return null;
        }
        if (parent instanceof Package) {
            return (Package) parent;
        }
        return getOwnerPackage (parent);
    }

    private Set<String> getPackageImport(final Package thePackage) {
        Set<String> imports = new TreeSet<> ();
        
        // Imported elements
        for (ElementImport theImport : thePackage.getOwnedImport ()) {
            NameSpace importedNameSpace = theImport.getImportedElement ();
            if (!theImport.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAFILEGROUP)) {
                if (importedNameSpace instanceof Class) {
                    GeneralClass c = getEnclosingClass ((Class) importedNameSpace);
                    imports.add ("import " + JavaDesignerUtils.getFullJavaName (this.session, c) +
                            ";");
                } else if (importedNameSpace instanceof Interface) {
                    GeneralClass i = getEnclosingClass ((Interface) importedNameSpace);
                    imports.add ("import " + JavaDesignerUtils.getFullJavaName (this.session, i) +
                            ";");
                } else if (importedNameSpace instanceof Package) {
                    Package p = (Package) importedNameSpace;
                    imports.add ("import " + JavaDesignerUtils.getFullJavaName (this.session, p) +
                            ".*;");
                } else if (importedNameSpace instanceof DataType) {
                    GeneralClass d = getEnclosingClass ((DataType) importedNameSpace);
                    imports.add ("import " + JavaDesignerUtils.getFullJavaName (this.session, d) +
                            ";");
                }
            }
        }
        
        // Package Imports
        for (PackageImport theImport : thePackage.getOwnedPackageImport ()) {
            Package importedPackage = theImport.getImportedPackage ();
            imports.add ("import " +
                    JavaDesignerUtils.getFullJavaName (this.session, importedPackage) + ".*;");
        }
        
        // Add all other imports from tags
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (thePackage, JavaDesignerTagTypes.CLASS_JAVAIMPORT)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String val = tagParameter.getValue ();
                if (val.length () != 0) {
                    imports.add ("import " + val + ";");
                }
            }
        }
        return imports;
    }

    private CharSequence getParamJavadocContents(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        
        boolean generateDescriptionAsJavadoc = this.javaConfig.DESCRIPTIONASJAVADOC;
        
        for (Parameter theParamter : theOperation.getIO ()) {
            if (generateDescriptionAsJavadoc) {
                for (Note descriptionNote : ModelUtils.getAllNotes (theParamter, IOtherProfileElements.MODELELEMENT_DESCRIPTION)) {
                    ret.append ("@param ");
                    ret.append (JavaDesignerUtils.getJavaName (theParamter));
                    ret.append (" ");
                    ret.append (descriptionNote.getContent ());
                    ret.append (NL);
                }
            }
        
            for (Note descriptionNote : ModelUtils.getAllNotes (theParamter, JavaDesignerNoteTypes.PARAMETER_JAVADOC)) {
                ret.append ("@param ");
                ret.append (JavaDesignerUtils.getJavaName (theParamter));
                ret.append (" ");
                ret.append (descriptionNote.getContent ());
                ret.append (NL);
            }
        }
        return ret;
    }

    private CharSequence getReturnJavadocContents(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        
        Parameter returnedParameter = theOperation.getReturn ();
        if (returnedParameter != null) {
            if (this.javaConfig.DESCRIPTIONASJAVADOC) {
                for (Note descriptionNote : ModelUtils.getAllNotes (returnedParameter, IOtherProfileElements.MODELELEMENT_DESCRIPTION)) {
                    ret.append ("@return ");
                    ret.append (descriptionNote.getContent ());
                    ret.append (NL);
                }
            }
        
            for (Note descriptionNote : ModelUtils.getAllNotes (returnedParameter, JavaDesignerNoteTypes.PARAMETER_JAVADOC)) {
                ret.append ("@return ");
                ret.append (descriptionNote.getContent ());
                ret.append (NL);
            }
        }
        return ret;
    }

    private CharSequence getSeeFormat(final ModelElement theModelElement, final String type, final String text) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        // Compute the element java name
        String modelElementName = JavaDesignerUtils.getJavaName (theModelElement);
        
        // See format for an Operation
        if (theModelElement instanceof Operation) {
            Operation op = (Operation) theModelElement;
            String str = "";
        
            String opat = " [op]";
            if (text.length () == 0) {
                opat += " " + modelElementName;
            }
        
            ret.append (JavaDesignerUtils.getFullJavaName (this.session, op.getOwner ()));
            ret.append ("#");
            ret.append (modelElementName);
            if (type.equals ("Generate the object name with argument types")) {
                ret.append ("(");
                List<Parameter> parameters = op.getIO ();
                for (Iterator<Parameter> iterator = parameters.iterator () ; iterator.hasNext () ;) {
                    Parameter theParameter = iterator.next ();
        
                    // Insert only the type
                    str += this.typeManager.getTypeDeclaration (this.session, theParameter, isFullNameGeneration (theParameter));
                    if (iterator.hasNext ()) {
                        str += ", ";
                    }
                }
        
                ret.append (str);
                ret.append (")");
            } else if (type.equals ("Generate the object name with argument types and names")) {
                ret.append ("(");
                List<Parameter> parameters = op.getIO ();
                for (Iterator<Parameter> iterator = parameters.iterator () ; iterator.hasNext () ;) {
                    Parameter theParameter = iterator.next ();
        
                    // Insert the type and the name
                    str += getDeclaration (theParameter);
                    if (iterator.hasNext ()) {
                        str += ", ";
                    }
                }
        
                ret.append (str);
                ret.append (")");
            }
        
            ret.append (opat);
        } else if (theModelElement instanceof Feature) {
            // See format for an Attribute and AssociationEnd
            Feature at = (Feature) theModelElement;
            String opat = " [at]";
            if (text.length () == 0) {
                opat += " " + modelElementName;
            }
        
            ret.append (JavaDesignerUtils.getFullJavaName (this.session, (ModelElement) at.getCompositionOwner ()));
            ret.append ("#");
            ret.append (modelElementName);
            ret.append (opat);
        } else { // See format for Package, Class, Interface, DataType,
            // Enumeration
            ret.append (JavaDesignerUtils.getFullJavaName (this.session, theModelElement));
        }
        
        // Text added in all cases
        ret.append (" ");
        ret.append (text);
        return ret;
    }

    private CharSequence getSeeJavadocContents(final ModelElement theModelElement) throws CustomException {
        StringBuilder ret = new StringBuilder ();
        
        for (Dependency theDependency : theModelElement.getDependsOnDependency ()) {
            if (theDependency.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.SEEJAVADOC)) {
                String noteContent;
                Note descriptionNote = theDependency.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.DEPENDENCY_SEEJAVADOC);
                if (descriptionNote != null) {
                    noteContent = descriptionNote.getContent ();
                } else {
                    noteContent = "";
                }
        
                String typeOfLink = "";
                String annotationContent = "";
                String[] lines = noteContent.split (NL);
                for (int i = 0 ; i < lines.length ; i++) {
                    String line = lines[i];
                    if (i == 0) { // The first line of the note contains the type of
                        // annotation created
                        typeOfLink = line;
                    } else { // Other lines contains the real annotation content
                        annotationContent += line;
                        if (i == lines.length - 1) { // Add a space to separate
                            // elements
                            annotationContent += " ";
                        }
                    }
                }
        
                ret.append ("@see ");
                ret.append (getSeeFormat (theDependency.getDependsOn (), typeOfLink, annotationContent));
                ret.append (NL);
            }
        }
        
        if (ret.length () > 0) {
            ret.insert (0, NL);
            ret.insert (0, NL);
        }
        return ret;
    }

    private CharSequence getTextualsAnnotations(final ModelElement element) {
        StringBuilder ret = new StringBuilder ();
        
        if (this.javaConfig.GENERATIONMODE_ROUNDTRIP) {
            if (element instanceof DataType) {
                if ("DataType".equals (JavaDesignerUtils.getJavaName (element))) {
                    ret.append (getCurrentIndent ());
                    ret.append ("@com.modeliosoft.modelio.javadesigner.annotations.DataType");
                    ret.append (NL);
                } else {
                    ret.append (getCurrentIndent ());
                    ret.append ("@DataType");
                    ret.append (NL);
                }
            }
        
            // Generation of the "Identifier" annotation
            if ((element instanceof Class) ||
                    (element instanceof Interface) ||
                    (element instanceof Attribute) ||
                    (element instanceof Operation) ||
                    (element instanceof AssociationEnd) ||
                    (element instanceof DataType) ||
                    (element instanceof Enumeration)) {
                ret.append (getCurrentIndent ());
                ret.append ("@objid (\""); //$NON-NLS-1$
                ret.append (element.getUuid());
                ret.append ("\")"); //$NON-NLS-1$
                ret.append (NL);
            }
        }
        
        for (Note note : ModelUtils.getAllNotes (element, JavaDesignerNoteTypes.PACKAGE_JAVAANNOTATION)) {
            for (String annot : note.getContent().split("\\n")) {
                if (annot.startsWith("@")) {
                    // The line contains a new annotation : indent the output in
                    // order to align all the annotations.
                    ret.append(getCurrentIndent());
                }
                ret.append(updateNewlines(annot));
            }
        }
        
        ret.append(this.annotationManager.computeStereotypeAnnotations(element, getCurrentIndent()));
        return ret;
    }

    private CharSequence getThrowsJavadocContents(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        
        boolean descriptionAsJavadoc = this.javaConfig.DESCRIPTIONASJAVADOC;
        
        for (ElementImport theElementImport : theOperation.getOwnedImport ()) {
            if (theElementImport.isStereotyped(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.ELEMENTIMPORT_THROW)) {
                NameSpace importedNameSpace = theElementImport.getImportedElement ();
                if (importedNameSpace instanceof GeneralClass) {
                    Note descriptionNote;
                    if (descriptionAsJavadoc) {
                        descriptionNote = theElementImport.getNote (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODELELEMENT_DESCRIPTION);
                    } else {
                        descriptionNote = theElementImport.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVADOC);
                    }
        
                    if (descriptionNote != null) {
                        ret.append ("@throws ");
                        ret.append (JavaDesignerUtils.getFullJavaName (this.session, importedNameSpace));
                        ret.append (" ");
                        ret.append (descriptionNote.getContent ());
                        ret.append (NL);
                    }
                }
            }
        }
        
        for (RaisedException raisedException : theOperation.getThrown ()) {
            Classifier importedNameSpace = raisedException.getThrownType ();
            if (importedNameSpace instanceof GeneralClass) {
                Note descriptionNote;
                if (descriptionAsJavadoc) {
                    descriptionNote = raisedException.getNote (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODELELEMENT_DESCRIPTION);
                } else {
                    descriptionNote = raisedException.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.CLASS_JAVADOC);
                }
        
                if (descriptionNote != null) {
                    ret.append ("@throws ");
                    ret.append (JavaDesignerUtils.getFullJavaName (this.session, importedNameSpace));
                    ret.append (" ");
                    ret.append (descriptionNote.getContent ());
                    ret.append (NL);
                }
            }
        }
        return ret;
    }

    private CharSequence getTransformedOneNoteOfType(final ModelElement theModelElement, final String noteType, final boolean genBalise) {
        StringBuilder ret = new StringBuilder ();
        
        Note theNote = getOneTextOfType (theModelElement, noteType);
        if (theNote != null) {
            String noteContent = theNote.getContent ();
            if (genBalise) {
                ret.append (getIdBegin (theNote));
                ret.append (computeFormattedCode (noteContent));
                ret.append (getIdEnd (theNote));
            } else {
                ret.append (computeFormattedCode (noteContent));
            }
        }
        return ret;
    }

    private boolean hasExtends(final GeneralClass theGeneralClass) throws TemplateException {
        return (!getParentInheritances (theGeneralClass).isEmpty () || !ModelUtils.getFirstTagParameter (theGeneralClass, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTENDS).isEmpty());
    }

    private boolean hasImplements(final GeneralClass theGeneralClass) throws TemplateException {
        // Mantis 5251
        for (InterfaceRealization interfaceRealization : theGeneralClass.getRealized ()) {
            if (!JavaDesignerUtils.isNoCode (interfaceRealization)) {
                Interface implemented = interfaceRealization.getImplemented ();
        
                if (implemented != null) {
                    if (JavaDesignerUtils.isNoCode (implemented)) {
                        String intrefaceName = JavaDesignerUtils.getJavaName (implemented);
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.InterfaceRealizedNoCode", JavaDesignerUtils.getJavaName (theGeneralClass), intrefaceName));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.InterfaceRealizedNoCode", JavaDesignerUtils.getJavaName (theGeneralClass), intrefaceName), theGeneralClass, "");
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        
        // Tags
        if (theGeneralClass.getTag (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAIMPLEMENTS) != null) {
            return true;
        }
        
        // Classes stereotyp?es interface
        for (Generalization parentGeneralization : theGeneralClass.getParent ()) {
            if (!JavaDesignerUtils.isNoCode (parentGeneralization)) {
                NameSpace parentNameSpace = parentGeneralization.getSuperType ();
                if (parentNameSpace instanceof Class && isInterface ((Class) parentNameSpace) && !JavaDesignerUtils.isNoCode (parentNameSpace)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasInvariant(final GeneralClass theGeneralClass) {
        // No invariants for interfaces
        if (isInterface (theGeneralClass)) {
            return false;
        }
        
        String className = JavaDesignerUtils.getJavaName (theGeneralClass);
        
        if (this.javaConfig.GENERATEINVARIANTS) {
            for (Constraint theConstraint : theGeneralClass.getConstraintDefinition ()) {
                if (theConstraint.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPRECONDITION) ||
                        theConstraint.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAPOSTCONDITION)) {
                    this.report.addWarning (Messages.getString ("Warning.PrePostOnClass", className), theGeneralClass, "");
                }
            }
        
            for (Constraint theConstraint : theGeneralClass.getConstraintDefinition ()) {
                if (theConstraint.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAINVARIANT)) {
                    return true;
                }
            }
        }
        return false;
    }

    private CharSequence computeHeaderText(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        for (Note note : ModelUtils.getAllNotes (theGeneralClass, JavaDesignerNoteTypes.CLASS_JAVAHEADER)) {
            CharSequence content = updateNewlines (note.getContent ());
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (getIdBegin (note));
                ret.append (content);
                ret.append (getIdEnd (note));
            } else {
                ret.append (content);
            }
        }
        return ret;
    }

    private CharSequence getId(final MObject element, final String style) {
        StringBuilder ret = new StringBuilder ();
        String comment = "";
        
        if ((style.equals (MarkerBegin)) || (style.equals (MarkerNew))) {
            comment = getIdLineBegin ();
        } else if (style == MarkerEnd) {
            comment = getIdLineEnd ();
        }
        ret.append (comment);
        
        // Get the id
        String identifier = element.getUuid().toString();
        
        String shortIds = style + "/" + identifier;
        
        // Add "." to complete the 79 char line
        for (int i = 0 ; i < 80 - comment.length () - shortIds.length () ; i++) {
            ret.append (".");
        }
        
        ret.append (shortIds);
        ret.append (NL);
        return ret;
    }

    private CharSequence getIdBegin(final MObject element) {
        return getId (element, MarkerBegin);
    }

    private CharSequence getIdEnd(final MObject element) {
        return getId (element, MarkerEnd);
    }

    private CharSequence computeImplements(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        // Compute interfaces realizations
        for (InterfaceRealization interfaceRealization : theGeneralClass.getRealized ()) {
            if (!JavaDesignerUtils.isNoCode (interfaceRealization)) {
                // generate annotations
                if (isJDK8Compatible()) {
                    ret.append(this.typeManager.computeAnnotations(interfaceRealization, true, false));
                }
        
                boolean generateFullName = false;
                if (isFullNameGeneration (interfaceRealization)) {
                    generateFullName = true;
                }
        
                ret.append (" ");
                ret.append (computeName (interfaceRealization.getImplemented (), generateFullName));
                ret.append (getBindingParameters (interfaceRealization));
                ret.append (",");
            }
        }
        
        // Compute tags
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theGeneralClass, JavaDesignerTagTypes.CLASS_JAVAIMPLEMENTS)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String value = tagParameter.getValue ();
                if (!value.isEmpty()) {
                    ret.append (" ");
                    ret.append (value);
                    ret.append (",");
                }
            }
        }
        
        if (ret.length () > 1) {
            ret.delete (ret.length () - 1, ret.length ());
        }
        return ret;
    }

    private CharSequence computeImports(final GeneralClass theGeneralClass) throws CustomException, TemplateException {
        StringBuilder javaImports = new StringBuilder ();
        StringBuilder ret = new StringBuilder ();
        Set<String> imports = new TreeSet<> ();
        
        if (this.javaConfig.GENERATIONMODE_ROUNDTRIP) {
            // Import de l'annotation stockant les identifiants Modelio
            imports.add ("import com.modeliosoft.modelio.javadesigner.annotations.objid;");
        }
        
        // Add imports from type packages
        imports.addAll (getImportsFromTypesPackages (theGeneralClass));
        
        // Imports from the classifier
        imports.addAll (getClassImport (theGeneralClass, new TreeSet<NameSpace>()));
        
        // Imports from the owner package
        Package parentPackage = getOwnerPackage (theGeneralClass);
        if (parentPackage != null) {
            imports.addAll (getPackageImport (parentPackage));
        }
        
        // Remove invalid imports
        imports.remove ("import .*;");
        imports.remove ("import ;");
        imports.remove("");
        
        // Format result
        for (String theImport : imports) {
            if (theImport.startsWith ("import java")) {
                // Ignore classes in java.lang, they automatically imported, bue not subpackages like "java.lang.reflect"
                if (!theImport.startsWith ("import java.lang.") || (theImport.lastIndexOf(".") != 16)) {
                    javaImports.append (theImport);
                    javaImports.append (NL);
                }
            } else {
                ret.append (theImport);
                ret.append (NL);
            }
        }
        
        ret.insert (0, javaImports);
        ret.append (NL);
        return ret;
    }

    private void increaseIndentLevel() {
        this.Val_indent++;
    }

    private CharSequence getCurrentIndent() {
        if (this.Val_indent < this.INDENT_VALUES.length) {
            return this.INDENT_VALUES[this.Val_indent];
        } else {
            return this.INDENT_VALUES[this.INDENT_VALUES.length - 1];
        }
    }

    private CharSequence computeInheritedAnnotation(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        if (theGeneralClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAANNOTATION_JAVAINHERITEDANNOTATION)) {
            ret.append (getCurrentIndent ());
            ret.append ("@java.lang.annotation.Inherited");
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computeInterfaceExtendsWithTaggedValue(final GeneralClass theGeneralClass) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        List<Generalization> lParentLinks = getParentInheritances (theGeneralClass);
        int lCount = lParentLinks.size ();
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theGeneralClass, JavaDesignerTagTypes.CLASS_JAVAEXTENDS)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String value = tagParameter.getValue ();
                if (!value.isEmpty()) {
                    if (lCount == 0) {
                        ret.append (" ");
                        ret.append (value);
                        lCount++;
                    } else {
                        ret.append (", ");
                        ret.append (value);
                    }
                }
            }
        }
        return ret;
    }

    private CharSequence computeInterfaceInheritance(final GeneralClass theGeneralClass) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        List<Generalization> lParentLinks = getParentInheritances (theGeneralClass);
        boolean lIsFirst = true;
        
        for (Generalization obGeneralization : lParentLinks) {
            NameSpace superType = obGeneralization.getSuperType ();
            if (superType instanceof GeneralClass) {
                GeneralClass parentGeneralClass = (GeneralClass) superType;
                boolean generateFullName = false;
                if (!isInterface (parentGeneralClass)) {
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.InterfaceInheritClass", JavaDesignerUtils.getJavaName (theGeneralClass), JavaDesignerUtils.getJavaName (parentGeneralClass)) +
                                NL);
                    } else {
                        this.report.addWarning (Messages.getString ("Warning.InterfaceInheritClass", JavaDesignerUtils.getJavaName (theGeneralClass), JavaDesignerUtils.getJavaName (parentGeneralClass)), theGeneralClass, "");
                    }
                } else {
                    if (isJDK8Compatible()) {
                        // generate annotations
                        ret.append(this.typeManager.computeAnnotations(obGeneralization, true, false));
                    }
        
                    if (isFullNameGeneration (obGeneralization)) {
                        generateFullName = true;
                    }
        
                    if (lIsFirst) {
                        ret.append (" ");
                        lIsFirst = false;
                    } else {
                        ret.append (", ");
                    }
        
                    ret.append (computeName (parentGeneralClass, generateFullName));
                    ret.append (getBindingParameters (obGeneralization));
                }
            }
        }
        return ret;
    }

    private boolean isInTheSameCompilUnit(final Classifier destOwner, final Classifier dest) {
        if (JavaDesignerUtils.isInner (destOwner)) {
            return isInTheSameCompilUnit ((Classifier) destOwner.getOwner (), dest);
        } else if (JavaDesignerUtils.isInner (dest)) {
            return isInTheSameCompilUnit (destOwner, (Classifier) dest.getOwner ());
        } else {
            return destOwner.equals (dest);
        }
    }

    private boolean isAnnotationAttribute(final Feature feature) {
        Classifier parent = (Classifier) feature.getCompositionOwner ();
        return (parent != null && parent.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAANNOTATION) && !feature.isIsClass());
    }

    private boolean isCreateMethod(final Operation operation) {
        return operation.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.OPERATION_CREATE);
    }

    private boolean isDeleteMethod(final Operation operation) {
        return operation.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.OPERATION_DESTROY);
    }

    private boolean isExtern(final ModelElement element) {
        return JavaDesignerUtils.isNoCode (element) ||
                                        element.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTERN);
    }

    private boolean isInterface(final Classifier theGeneralClass) {
        return (theGeneralClass instanceof Interface);
    }

    private boolean isJavaAnnotation(final GeneralClass theGeneralClass) {
        return theGeneralClass.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAANNOTATION);
    }

    private boolean isAbstract(final Operation theOperation) throws TemplateException {
        String ownerClassName = JavaDesignerUtils.getJavaName (theOperation.getOwner ());
        String operationName = JavaDesignerUtils.getJavaName (theOperation);
        
        if (theOperation.isIsAbstract ()) {
            if (theOperation.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE) != null) {
                if (this.javaConfig.ERRORONFIRSTWARNING) {
                    throw new TemplateException (Messages.getString ("Error.ImplementedAbstractMethod", ownerClassName, operationName));
                } else {
                    this.report.addWarning (Messages.getString ("Warning.ImplementedAbstractMethod", ownerClassName, operationName), theOperation, "");
                }
            }
            return true;
        } else if (!isJDK8Compatible() && isInterface (theOperation.getOwner ())) {
            if (theOperation.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE) != null
                    || theOperation.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED) != null) {
                if (this.javaConfig.ERRORONFIRSTWARNING) {
                    throw new TemplateException (Messages.getString ("Error.InterfaceBodyMethod", ownerClassName, operationName));
                } else {
                    this.report.addWarning (Messages.getString ("Warning.InterfaceBodyMethod", ownerClassName, operationName), theOperation, "");
                }
            }
            return true;
        }
        return false;
    }

    private boolean isNative(final Operation theOperation) throws TemplateException {
        Classifier operationOwner = theOperation.getOwner ();
        
        boolean ret = theOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVANATIVE);
        if (ret && theOperation.getNote (IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE) != null) {
            if (this.javaConfig.ERRORONFIRSTWARNING) {
                throw new TemplateException (Messages.getString ("Error.ClassImplementedNativeMethod", JavaDesignerUtils.getJavaName (operationOwner), JavaDesignerUtils.getJavaName (theOperation)));
            } else {
                this.report.addWarning (Messages.getString ("Warning.ClassImplementedNativeMethod", JavaDesignerUtils.getJavaName (operationOwner), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
            }
        }
        return ret;
    }

    private boolean isNotRedefinedFinalMethod(final Operation theOperation) throws TemplateException {
        boolean ret = true;
        
        Operation theParentOperation = theOperation.getRedefines ();
        
        if (theParentOperation != null && theParentOperation.isFinal ()) {
            if (this.javaConfig.ERRORONFIRSTWARNING) {
                throw new TemplateException (Messages.getString ("Error.FinalRedefinedMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)));
            } else {
                this.report.addWarning (Messages.getString ("Warning.FinalRedefinedMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
            }
        
            ret = false;
        }
        return ret;
    }

    private boolean isNotUndefinedTypeParameter(final Operation theOperation) throws TemplateException {
        Classifier lComposedClass = theOperation.getOwner ();
        
        boolean ret = true;
        
        // Check IO parameters
        for (Parameter theParameter : theOperation.getIO ()) {
            if (!theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR) &&
                    !theParameter.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE)) {
                GeneralClass theType = theParameter.getType ();
                if (theType != null) {
                    if (JavaDesignerUtils.getJavaName (theType).equals ("undefined") || JavaDesignerUtils.isNoCode(theType)) {
                        ret = false;
                    }
                } else {
                    ret = false;
                }
            }
        }
        
        // Check returned parameters
        Parameter returnParameter = theOperation.getReturn ();
        if (returnParameter != null) {
            if (!returnParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVATYPEEXPR) &&
                    !returnParameter.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE)) {
                GeneralClass returnedType = returnParameter.getType ();
                if (returnedType != null) {
                    if (JavaDesignerUtils.getJavaName (returnedType).equals ("undefined") || JavaDesignerUtils.isNoCode(returnedType)) {
                        ret = false;
                    }
                } else {
                    ret = false;
                }
            }
        }
        
        // Display an error if necessary
        if (!ret) {
            if (this.javaConfig.ERRORONFIRSTWARNING) {
                if (isInterface (lComposedClass)) {
                    throw new TemplateException (Messages.getString ("Error.InterfaceUndefinedTypeParameter", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theOperation)));
                } else {
                    throw new TemplateException (Messages.getString ("Error.ClassUndefinedTypeParameter", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theOperation)));
                }
            } else {
                if (isInterface (lComposedClass)) {
                    this.report.addWarning (Messages.getString ("Error.InterfaceUndefinedTypeParameter", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                } else {
                    this.report.addWarning (Messages.getString ("Error.ClassUndefinedTypeParameter", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                }
            }
        }
        return ret;
    }

    private CharSequence computeEmptyMarker(final MObject element, final String noteType) {
        return computeMarkersWithText (element, noteType, "");
    }

    private CharSequence computeMarkersWithText(final MObject element, final String noteType, final String text) {
        StringBuilder ret = new StringBuilder ();
        
        // On retourne le r?sultat (commentaire, points et identifiants courts)
        // si on est en model driven
        if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
            // Get the id
            String identifier = element.getUuid().toString();
        
            // Ligne de d?but
            String firstLine = getIdLineBegin () + "(" + noteType + ")";
            ret.append (firstLine);
        
            String shortIdsFirstLine = MarkerNew + "/" + identifier;
        
            // Ajout de "." pour que que la premi?re ligne fasse 79 caract?res
            for (int i = 0 ; i < 80 - firstLine.length () -
                    shortIdsFirstLine.length () ; i++) {
                ret.append (".");
            }
        
            ret.append (shortIdsFirstLine);
            ret.append (NL);
        
            ret.append (text);
            ret.append (NL);
        
            // Seconde ligne
            String secondLine = getIdLineEnd () + "(" + noteType + ")";
            ret.append (secondLine);
        
            String shortIdsSecondLine = MarkerEnd + "/" + identifier;
        
            // Ajout de "." pour que que la seconde ligne fasse 79 caract?res
            for (int i = 0 ; i < 80 - secondLine.length () -
                    shortIdsSecondLine.length () ; i++) {
                ret.append (".");
            }
        
            ret.append (shortIdsSecondLine);
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computeMembersText(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        for (Note note : ModelUtils.getAllNotes (theGeneralClass, JavaDesignerNoteTypes.CLASS_JAVAMEMBERS)) {
            CharSequence content = updateNewlines (note.getContent ());
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (getIdBegin (note));
                ret.append (content);
                ret.append (getIdEnd (note));
            } else {
                ret.append (content);
            }
        }
        
        if (ret.length () > 0) {
            ret.insert (0, NL);
        }
        return ret;
    }

    private CharSequence computeMethodBody(final Operation theOperation) {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getTransformedOneNoteOfType (theOperation, JavaDesignerNoteTypes.OPERATION_JAVACODE, this.javaConfig.GENERATIONMODE_MODELDRIVEN));
        
        if (ret.length () == 0) {
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (computeEmptyMarker (theOperation, JavaDesignerNoteTypes.OPERATION_JAVACODE));
            }
        }
        return ret;
    }

    private CharSequence computeOpenBlock() {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (" {");
        ret.append (NL);
        increaseIndentLevel ();
        return ret;
    }

    private CharSequence computeCloseBlock() {
        StringBuilder ret = new StringBuilder ();
        decreaseIndentLevel ();
        ret.append (getCurrentIndent ());
        ret.append ("}");
        ret.append (NL);
        return ret;
    }

    private CharSequence computeMethodModifiers(final Operation theOperation) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        GeneralClass operationOwner = (GeneralClass) theOperation.getOwner ();
        if (isInterface (operationOwner)) {
            if (!theOperation.getVisibility ().equals (VisibilityMode.PUBLIC)) {
                this.report.addWarning (Messages.getString ("Warning.invalidVisibilityInterfaceMethod", JavaDesignerUtils.getFullJavaName (this.session, theOperation)), theOperation, "");
            }
        
            if (theOperation.isFinal ()) {
                if (this.javaConfig.ERRORONFIRSTWARNING) {
                    throw new TemplateException (Messages.getString ("Error.InterfaceFinalMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)));
                } else {
                    this.report.addWarning (Messages.getString ("Warning.InterfaceFinalMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                }
            }
        
            if (isJDK8Compatible()) {
                if (theOperation.isIsClass ()) {
                    if (isDeleteMethod (theOperation)) {
                        this.report.addWarning (Messages.getString ("Warning.invalidStaticDestructor", JavaDesignerUtils.getFullJavaName (this.session, theOperation)), theOperation, "");
                    } else {
                        ret.append ("static ");
                    }
                }
        
                // jdk8 : automatically prepend 'default' in front of interface operation with implementation
                if (!theOperation.isIsClass () && isImplemented(theOperation)) {
                    ret.append("default ");
                }
            } else {
                if (theOperation.isIsClass ()) {
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.InterfaceStaticMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)));
                    } else {
                        this.report.addWarning (Messages.getString ("Warning.InterfaceStaticMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                    }
                }
            }
        
            if (theOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED)) {
                if (this.javaConfig.ERRORONFIRSTWARNING) {
                    throw new TemplateException (Messages.getString ("Error.InterfaceSynchronizedMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)));
                } else {
                    this.report.addWarning (Messages.getString ("Warning.InterfaceSynchronizedMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                }
            }
        } else {
            if (isDeleteMethod (theOperation)) {
                if (!theOperation.getVisibility ().equals (VisibilityMode.PROTECTED)) {
                    this.report.addWarning (Messages.getString ("Warning.invalidDestructorVisibility", JavaDesignerUtils.getFullJavaName (this.session, theOperation)), theOperation, "");
                }
                ret.append ("protected ");
            } else {
                switch (theOperation.getVisibility ()) {
                case PUBLIC:
                    if ((JavaDesignerUtils.getJavaName (theOperation).equals ("finalize"))) {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.FinalizeMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ())));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.FinalizeMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ())), theOperation, "");
                        }
                    } else {
                        ret.append ("public ");
                    }
                    break;
                case PROTECTED:
                    ret.append ("protected ");
                    break;
                case PRIVATE:
                    if ((JavaDesignerUtils.getJavaName (theOperation).equals ("finalize"))) {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.FinalizeMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ())));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.FinalizeMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ())), theOperation, "");
                        }
                    } else {
                        ret.append ("private ");
                    }
                    break;
                default: // nothing to add
                    break;
                }
            }
        
            if (!isCreateMethod (theOperation)) {
                if (theOperation.isIsAbstract ()) {
                    ret.append ("abstract ");
                }
        
                if (theOperation.isIsClass ()) {
                    if (isDeleteMethod (theOperation)) {
                        this.report.addWarning (Messages.getString ("Warning.invalidStaticDestructor", JavaDesignerUtils.getFullJavaName (this.session, theOperation)), theOperation, "");
                    } else {
                        ret.append ("static ");
                    }
                }
        
                if (theOperation.isFinal ()) {
                    if (theOperation.isIsAbstract ()) {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.FinalAbstractMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.FinalAbstractMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                        }
                    } else {
                        ret.append ("final ");
                    }
                }
        
                if (theOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVASYNCHRONIZED)) {
                    if (theOperation.isIsAbstract ()) {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.AbstractSynchronizedMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.AbstractSynchronizedMethod", JavaDesignerUtils.getJavaName (theOperation.getOwner ()), JavaDesignerUtils.getJavaName (theOperation)), theOperation, "");
                        }
                    } else {
                        ret.append ("synchronized ");
                    }
                }
            }
        
            if (isNative(theOperation)) {
                ret.append ("native ");
            }
        
            if (theOperation.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.OPERATION_JAVASTRICT)) {
                ret.append ("strictfp ");
            }
        }
        return ret;
    }

    private CharSequence computeModifiers(final GeneralClass theGeneralClass) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        ret.append (getCurrentIndent ());
        
        if (isInterface (theGeneralClass)) {
            if (JavaDesignerUtils.isInner (theGeneralClass)) {
                if (theGeneralClass.getOwner () instanceof Interface) {
                    switch (theGeneralClass.getVisibility ()) {
                    case PUBLIC:
                    case VISIBILITYUNDEFINED:
                        // Nothing to do
                            break;
                    default:
                        this.report.addWarning (Messages.getString ("Warning.InterfaceNonPublicInterface", JavaDesignerUtils.getJavaName (theGeneralClass.getOwner ()), JavaDesignerUtils.getJavaName (theGeneralClass)), theGeneralClass, "");
                    }
                } else {
                    switch (theGeneralClass.getVisibility ()) {
                    case PUBLIC:
                        ret.append ("public ");
                        break;
                    case PROTECTED:
                        ret.append ("protected ");
                        break;
                    case PRIVATE:
                        ret.append ("private ");
                        break;
                    case PACKAGEVISIBILITY:
                        break;
                    default:
                        // Package visibility
                        break;
                    }
                }
        
                if (theGeneralClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVASTATIC)) {
                    ret.append ("static ");
                }
            } else {
                switch (theGeneralClass.getVisibility ()) {
                case PUBLIC:
                    ret.append ("public ");
                    break;
                case PACKAGEVISIBILITY:
                    // Package visibility
                    break;
                default:
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.InvalidInterfaceVisibility", JavaDesignerUtils.getJavaName (theGeneralClass)));
                    } else {
                        String msg = Messages.getString ("Warning.InvalidInterfaceVisibility", JavaDesignerUtils.getJavaName (theGeneralClass));
                        this.report.addWarning (msg, theGeneralClass, "");
                    }
                    break;
                }
            }
        } else { // This is a Class
            if (JavaDesignerUtils.isInner (theGeneralClass)) {
                if (theGeneralClass.getOwner () instanceof Interface) {
                    switch (theGeneralClass.getVisibility ()) {
                    case PUBLIC:
                    case VISIBILITYUNDEFINED:
                        // Nothing to do
                            break;
                    default:
                        this.report.addWarning (Messages.getString ("Warning.InterfaceNonPublicClass", JavaDesignerUtils.getJavaName (theGeneralClass.getOwner ()), JavaDesignerUtils.getJavaName (theGeneralClass)), theGeneralClass, "");
                    }
                } else {
                    switch (theGeneralClass.getVisibility ()) {
                    case PUBLIC:
                        ret.append ("public ");
                        break;
                    case PROTECTED:
                        ret.append ("protected ");
                        break;
                    case PRIVATE:
                        ret.append ("private ");
                        break;
                    case PACKAGEVISIBILITY:
                        break;
                    default:
                        // Package visibility
                        break;
                    }
                }
        
                if (theGeneralClass.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVASTATIC)) {
                    ret.append ("static ");
                }
            } else {
                switch (theGeneralClass.getVisibility ()) {
                case PUBLIC:
                    ret.append ("public ");
                    break;
                case PACKAGEVISIBILITY:
                    break;
                default:
                    // Package visibility
                    if (this.javaConfig.ERRORONFIRSTWARNING) {
                        throw new TemplateException (Messages.getString ("Error.InvalidClassVisibility", JavaDesignerUtils.getJavaName (theGeneralClass)));
                    } else {
                        String msg = Messages.getString ("Warning.InvalidClassVisibility", JavaDesignerUtils.getJavaName (theGeneralClass));
                        this.report.addWarning (msg, theGeneralClass, "");
                    }
                    break;
                }
        
                // otherwise the class is friendly, FA 7496
            }
        
            if (theGeneralClass.isIsAbstract ()) {
                ret.append ("abstract ");
            }
            if (theGeneralClass.isIsLeaf ()) {
                ret.append ("final ");
            }
        }
        return ret;
    }

    private String computeName(final ModelElement theElement, final boolean generateFullName) {
        if (!generateFullName) {
            return JavaDesignerUtils.getJavaName (theElement);
        } else {
            return JavaDesignerUtils.getFullJavaName (this.session, theElement);
        }
    }

    private Set<String> getNeededJavaUtilImports(final GeneralClass theGeneralClass) throws CustomException, TemplateException {
        Set<String> ret = new TreeSet<> ();
        
        for (Attribute theAttribute : theGeneralClass.getOwnedAttribute()) {
            if (theAttribute.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE) ||
                    (theAttribute.getMultiplicityMax ().equals ("*"))) {
                if (theAttribute.isIsClass() || !isInterface(theAttribute.getOwner())) {
                    String iType = this.typeManager.getInterfaceType (theAttribute);
                    String cType = this.typeManager.getImplementationType (theAttribute, iType);
        
                    if (iType.length () != 0 && !iType.equals ("Array")) {
                        // No type added for Array
                        ret.add (iType);
                    }
        
                    if (cType.length () != 0 && !cType.equals ("Array")) {
                        // No type added for Array
                        ret.add (cType);
                    }
                }
            }
        }
        
        for (AssociationEnd theAssociationEnd : theGeneralClass.getOwnedEnd()) {
            if (theAssociationEnd.isNavigable()) {
                if (isNotBadInnerClassAssociation (theAssociationEnd)) {
                    if (theAssociationEnd.isIsClass() || !isInterface(theAssociationEnd.getSource())) {
                        String iType = this.typeManager.getInterfaceType (theAssociationEnd);
                        String cType = this.typeManager.getImplementationType (theAssociationEnd, iType);
        
                        if (iType.length () != 0 && !iType.equals ("Array")) {
                            // No type added for Array
                            ret.add (iType);
                        }
        
                        if (cType.length () != 0 && !cType.equals ("Array")) {
                            // No type added for Array
                            ret.add (cType);
                        }
                    }
                }
            }
        }
        
        for (Operation theOperation : theGeneralClass.getOwnedOperation()) {
            for (Parameter theParameter : theOperation.getIO ()) {
                if (theParameter.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE) ||
                        theParameter.getMultiplicityMax ().equals ("*")) {
                    String iType = this.typeManager.getInterfaceType (theParameter);
        
                    if (iType.length () != 0 && !iType.equals ("Array")) {
                        // No type added for Array
                        ret.add (iType);
                    }
                }
            }
        
            Parameter returnParameter = theOperation.getReturn ();
            if (returnParameter != null &&
                    (returnParameter.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE) || returnParameter.getMultiplicityMax ().equals ("*"))) { // PAN
                String iType = this.typeManager.getInterfaceType (returnParameter);
        
                if (iType.length () != 0 && !iType.equals ("Array")) {
                    // No type added for Array
                    ret.add (iType);
                }
            }
        }
        
        for (GeneralClass theInnerClass : getInnerClasses (theGeneralClass)) {
            for (String newImport : getNeededJavaUtilImports (theInnerClass)) {
                ret.add (newImport);
            }
        }
        
        for (GeneralClass theInnerClass : getInternalClasses (theGeneralClass)) {
            for (String newImport : getNeededJavaUtilImports (theInnerClass)) {
                ret.add (newImport);
            }
        }
        return ret;
    }

    private boolean isNotBadInnerClassAssociation(final AssociationEnd theAssociationEnd) throws TemplateException {
        boolean ret;
        Classifier dest = theAssociationEnd.getTarget();
        if (dest != null) {
            ret = true;
            if (JavaDesignerUtils.isInner (dest)) {
                ModelTree destOwner = dest.getOwner ();
                if (destOwner instanceof GeneralClass) {
                    if (!isInTheSameCompilUnit ((GeneralClass) destOwner, dest)) {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.BadInnerClassAssociation", JavaDesignerUtils.getJavaName (destOwner), JavaDesignerUtils.getJavaName (dest)));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.BadInnerClassAssociation", JavaDesignerUtils.getJavaName (destOwner), JavaDesignerUtils.getJavaName (dest)), theAssociationEnd, "");
                        }
                        ret = false;
                    }
                }
            }
        } else {
            ret = false;
        }
        return ret;
    }

    private boolean isNotClassMemberInAnInterfaceWithoutInit(final Attribute theAttribute) throws TemplateException {
        boolean ret = true;
        
        Classifier owner = theAttribute.getOwner ();
        Interface ownerInterface;
        if (owner instanceof Interface) {
            ownerInterface = (Interface) owner;
        } else {
            ownerInterface = null;
        }
        
        if (ownerInterface != null) {
            if (!theAttribute.isIsClass()) {
                // No generation on instance attribute in an interface but it is not an error
                // since this model declaration will be used on class that implements this interface
                ret = false;
            } else if (theAttribute.getValue().length() == 0) {
                // No initial value on class attribute, this is an error
                ret = false;
        
                if (this.javaConfig.ERRORONFIRSTWARNING) {
                    throw new TemplateException(Messages.getString("Error.InitInterfaceAttribute", JavaDesignerUtils.getJavaName(ownerInterface), JavaDesignerUtils.getJavaName(theAttribute)));
                } else {
                    this.report.addWarning(Messages.getString("Warning.InitInterfaceAttribute", JavaDesignerUtils.getJavaName(ownerInterface), JavaDesignerUtils.getJavaName(theAttribute)), theAttribute, "");
                }
            }
        }
        return ret;
    }

    private boolean isNotUndefinedType(final AssociationEnd theAssociationEnd) {
        if (theAssociationEnd.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ASSOCIATIONEND_JAVATYPEEXPR) ||
                (theAssociationEnd.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE))) {
            return true;
        }
        
        Classifier owner = theAssociationEnd.getSource ();
        GeneralClass lComposedClass;
        if (owner instanceof GeneralClass) {
            lComposedClass = (GeneralClass) owner;
        } else {
            lComposedClass = null;
        }
        
        Classifier typeGeneralClass = theAssociationEnd.getTarget();
        if (typeGeneralClass == null || JavaDesignerUtils.getJavaName (typeGeneralClass).equals ("undefined") || JavaDesignerUtils.isNoCode(typeGeneralClass)) {
            if (lComposedClass != null) {
                if (isInterface (lComposedClass)) {
                    this.report.addWarning (Messages.getString ("Warning.InterfaceUndefinedTypeAssociationEnd", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theAssociationEnd)), theAssociationEnd, "");
                } else {
                    this.report.addWarning (Messages.getString ("Warning.ClassUndefinedTypeAssociationEnd", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theAssociationEnd)), theAssociationEnd, "");
                }
            }
            return false;
        } else {
            return true;
        }
    }

    private boolean isNotUndefinedType(final Attribute theAttribute) {
        if (theAttribute.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.ATTRIBUTE_JAVATYPEEXPR) ||
                (theAttribute.isTagged(IOtherProfileElements.MODULE_NAME, IOtherProfileElements.FEATURE_TYPE))) {
            return true;
        }
        
        Classifier owner = theAttribute.getOwner ();
        GeneralClass lComposedClass;
        if (owner instanceof GeneralClass) {
            lComposedClass = (GeneralClass) owner;
        } else {
            lComposedClass = null;
        }
        GeneralClass typeGeneralClass = theAttribute.getType ();
        
        if (typeGeneralClass == null || JavaDesignerUtils.getJavaName (typeGeneralClass).equals ("undefined") || JavaDesignerUtils.isNoCode(typeGeneralClass)) {
            if (lComposedClass != null) {
                if (isInterface (lComposedClass)) {
                    this.report.addWarning (Messages.getString ("Warning.InterfaceUndefinedTypeAttribute", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theAttribute)), theAttribute, "");
                } else {
                    this.report.addWarning (Messages.getString ("Warning.ClassUndefinedTypeAttribute", JavaDesignerUtils.getJavaName (lComposedClass), JavaDesignerUtils.getJavaName (theAttribute)), theAttribute, "");
                }
            }
            return false;
        } else {
            return true;
        }
    }

    private String computePackageDeclaration(final Package element) {
        String lName = JavaDesignerUtils.getFullJavaName (this.session, element);
        
        if (!lName.equals ("")) { //$NON-NLS-1$
            return "package " + lName + ";" + NL + NL; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return ""; //$NON-NLS-1$
    }

    private String computeParameterModifiers(final Parameter theParameter) {
        if (theParameter.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.PARAMETER_JAVAFINAL)) {
            return "final ";
        } else if (this.javaConfig.GENERATEFINALPARAMETERS && (theParameter.getParameterPassing () == PassingMode.IN)) {
            return "final ";
        }
        return "";
    }

    private boolean isParentWithInvariant(final GeneralClass ownerGeneralClass) throws CustomException, TemplateException {
        Set<GeneralClass> alreadyScannedElements = new TreeSet<> ();
        return isParentWithInvariant (ownerGeneralClass, alreadyScannedElements);
    }

    private boolean isParentWithInvariant(final GeneralClass ownerGeneralClass, final Set<GeneralClass> alreadyScannedElements) throws CustomException, TemplateException {
        alreadyScannedElements.add (ownerGeneralClass);
        
        for (Generalization theGeneralization : getParentInheritances (ownerGeneralClass)) {
            NameSpace theSuperType = theGeneralization.getSuperType ();
        
            if (theSuperType instanceof Class &&
                    !alreadyScannedElements.contains (theSuperType)) {
                Class superTypeClass = (Class) theSuperType;
                if (!theSuperType.isTagged(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.CLASS_JAVAEXTERN)) {
                    if (hasInvariant (superTypeClass)) {
                        return true;
                    } else {
                        return isParentWithInvariant (superTypeClass, alreadyScannedElements);
                    }
                }
            }
        }
        return false;
    }

    private List<Generalization> getParentInheritances(final GeneralClass theGeneralClass) throws TemplateException {
        List<Generalization> ret = new ArrayList<> ();
        String superClassName;
        
        if (isInterface (theGeneralClass)) { // Start from an interface
            for (Generalization generalization : theGeneralClass.getParent ()) {
                NameSpace superType = generalization.getSuperType ();
                if (!JavaDesignerUtils.isNoCode (generalization)) {
                    if (superType instanceof GeneralClass) {
                        GeneralClass superTypeGeneralClass = (GeneralClass) superType;
        
                        if (!isInterface (superTypeGeneralClass)) { // Error, this is not an interface
                            superClassName = JavaDesignerUtils.getJavaName (superTypeGeneralClass);
                            if (this.javaConfig.ERRORONFIRSTWARNING) {
                                throw new TemplateException (Messages.getString ("Error.InterfaceInheritClass", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName));
                            } else {
                                this.report.addWarning (Messages.getString ("Warning.InterfaceInheritClass", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName), theGeneralClass, "");
                            }
                        } else if (JavaDesignerUtils.isNoCode (superTypeGeneralClass)) { // Error, inherited type is noCode
                            superClassName = JavaDesignerUtils.getJavaName (superTypeGeneralClass);
                            if (this.javaConfig.ERRORONFIRSTWARNING) {
                                throw new TemplateException (Messages.getString ("Error.InterfaceInheritNoCode", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName));
                            } else {
                                this.report.addWarning (Messages.getString ("Warning.InterfaceInheritNoCode", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName), theGeneralClass, "");
                            }
                        } else {
                            ret.add (generalization);
                        }
                    }
                }
            }
        } else { // Start from a class
            for (Generalization generalization : theGeneralClass.getParent ()) {
                NameSpace superType = generalization.getSuperType ();
                if (!JavaDesignerUtils.isNoCode (generalization)) {
                    if (superType instanceof GeneralClass) {
                        GeneralClass superTypeGeneralClass = (GeneralClass) superType;
                        if (isInterface (superTypeGeneralClass)) { // Error, this is an interface
                            superClassName = JavaDesignerUtils.getJavaName (superTypeGeneralClass);
                            if (this.javaConfig.ERRORONFIRSTWARNING) {
                                throw new TemplateException (Messages.getString ("Error.ClassInheritInterface", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName));
                            } else {
                                this.report.addWarning (Messages.getString ("Warning.ClassInheritInterface", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName), theGeneralClass, "");
                            }
                        } else if (JavaDesignerUtils.isNoCode (superTypeGeneralClass)) { // Error, inherited type is noCode
                            superClassName = JavaDesignerUtils.getJavaName (superTypeGeneralClass);
                            if (this.javaConfig.ERRORONFIRSTWARNING) {
                                throw new TemplateException (Messages.getString ("Error.ClassInheritNoCode", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName));
                            } else {
                                this.report.addWarning (Messages.getString ("Warning.ClassInheritNoCode", JavaDesignerUtils.getJavaName (theGeneralClass), superClassName), theGeneralClass, "");
                            }
                        } else {
                            ret.add (generalization);
                        }
                    }
                }
            }
        }
        return ret;
    }

    private CharSequence computeRetentionAnnotation(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue theTag : ModelUtils.getAllTaggedValues (theGeneralClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVARETENTIONANNOTATION)) {
            ret.append (getCurrentIndent ());
            ret.append ("@java.lang.annotation.Retention(");
        
            for (TagParameter theTagParameter : theTag.getActual ()) {
                String value = theTagParameter.getValue ();
        
                int pos = value.indexOf ("java.lang.annotation.RetentionPolicy.");
                if (pos != 0) {
                    pos = value.indexOf ("RetentionPolicy.", 0);
                    if (pos == 0) {
                        ret.append ("java.lang.annotation.");
                    } else {
                        ret.append ("java.lang.annotation.RetentionPolicy.");
                    }
                }
        
                ret.append (value);
            }
        
            ret.append (")");
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computeTargetAnnotation(final GeneralClass theGeneralClass) {
        StringBuilder ret = new StringBuilder ();
        
        for (TaggedValue theTag : ModelUtils.getAllTaggedValues (theGeneralClass, JavaDesignerTagTypes.JAVAANNOTATION_JAVATARGETANNOTATION)) {
            ret.append (getCurrentIndent ());
            ret.append ("@java.lang.annotation.Target({");
        
            for (Iterator<TagParameter> iterator = theTag.getActual ().iterator () ; iterator.hasNext () ;) {
                TagParameter theTagParameter = iterator.next ();
                String value = theTagParameter.getValue ();
        
                int pos = value.indexOf ("java.lang.annotation.ElementType.");
                if (pos != 0) {
                    pos = value.indexOf ("ElementType.", 0);
                    if (pos == 0) {
                        ret.append ("java.lang.annotation.");
                    } else {
                        ret.append ("java.lang.annotation.ElementType.");
                    }
                }
        
                ret.append (value);
                if (iterator.hasNext ()) {
                    ret.append (", ");
                }
            }
            ret.append ("})");
            ret.append (NL);
        }
        return ret;
    }

    private CharSequence computeTopText(final ModelElement element) {
        StringBuilder ret = new StringBuilder ();
        
        List<Note> notes = ModelUtils.getAllNotes (element, JavaDesignerNoteTypes.CLASS_JAVATOP);
        for (Note note : notes) {
            CharSequence content = updateNewlines (note.getContent ());
            if (this.javaConfig.GENERATIONMODE_MODELDRIVEN) {
                ret.append (getIdBegin (element));
                ret.append (content);
                ret.append (getIdEnd (element));
            } else {
                ret.append (content);
            }
        }
        return ret;
    }

    private CharSequence updateNewlinesExceptLast(final String text) {
        StringBuilder ret = new StringBuilder ();
        String simpleNL = "\n";
        
        String[] lines = text.split (simpleNL);
        for (int i = 0 ; i < lines.length ; i++) {
            String line = lines[i];
        
            ret.append (line);
            if (i != lines.length - 1 && line.length () > 0) {
                if (line.charAt (line.length () - 1) != '\r') {
                    ret.append (NL);
                } else {
                    ret.append (simpleNL);
                }
            }
        }
        return ret;
    }

    private CharSequence computeExtendsWithTaggedValue(final TemplateParameter theTemplateParameter) throws TemplateException {
        StringBuilder ret = new StringBuilder ();
        int parentCount = 0;
        
        for (TaggedValue tag : ModelUtils.getAllTaggedValues (theTemplateParameter, JavaDesignerTagTypes.TEMPLATEPARAMETER_JAVAEXTENDS)) {
            for (TagParameter tagParameter : tag.getActual ()) {
                String value = tagParameter.getValue ();
                if (!value.isEmpty()) {
                    if (parentCount == 0) {
                        ret.append (" ");
                        ret.append (value);
                        parentCount++;
                    } else {
                        if (this.javaConfig.ERRORONFIRSTWARNING) {
                            throw new TemplateException (Messages.getString ("Error.TemplateParameterMultipleInheritance", JavaDesignerUtils.getJavaName (theTemplateParameter)));
                        } else {
                            this.report.addWarning (Messages.getString ("Warning.TemplateParameterMultipleInheritance", JavaDesignerUtils.getJavaName (theTemplateParameter)), theTemplateParameter, "");
                        }
                    }
                }
            }
        }
        return ret;
    }

    private CharSequence getPropertyDeclaration(final AssociationEnd theAssociationEnd) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        boolean oldModelDriven = this.javaConfig.GENERATIONMODE_MODELDRIVEN;
        this.javaConfig.GENERATIONMODE_MODELDRIVEN = false;
        
        boolean oldRoundTrip = this.javaConfig.GENERATIONMODE_ROUNDTRIP;
        this.javaConfig.GENERATIONMODE_ROUNDTRIP = false;
        
        try {
            if (theAssociationEnd.isIsClass() || !(theAssociationEnd.getSource() instanceof Interface)) {
                ret.append (getCurrentIndent ());
                ret.append (computeFieldModifiers (theAssociationEnd));
                ret.append (this.typeManager.getTypeDeclaration (this.session, theAssociationEnd, isFullNameGeneration (theAssociationEnd)));
                ret.append (JavaDesignerUtils.getJavaName (theAssociationEnd));
                ret.append (updateNewlinesExceptLast (this.typeManager.computeInitialValue (this.session, theAssociationEnd, isFullNameGeneration (theAssociationEnd))));
                ret.append (";");
                ret.append (computeAssociationEndInitialValueComment (theAssociationEnd));
                ret.append (NL);
                ret.append (NL);
            }
        
            // Add accessors in the model, they will be rollbacked
            AccessorManager accessorManager = new AccessorManager (this.session);
            accessorManager.init (this.javaConfig.getJavaConfiguration());
        
            for (Operation tempAccessor : accessorManager.updateAccessors (theAssociationEnd, true)) {
                // Check stereotypes only for operations
                if (tempAccessor.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    Operation getter = tempAccessor;
        
                    // Replace visibility with the good one
                    getter.setVisibility(getPropertyGetterVisibility(theAssociationEnd));
        
                    // Replace comment in the generated code
                    String code = getter.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
                    if (code != null) {
                        code = code.replace(accessorManager.AUTOMATIC_UPDATE_COMMENT, accessorManager.PROPERTY_COMMENT);
                        getter.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE, code);
                    }
        
                    if (oldRoundTrip) {
                        ret.append (computePropertyGetterAnnotation());
                    }
                    ret.append(computeMethod((getter)));
                } else if (tempAccessor.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    Operation setter = tempAccessor;
        
                    // Replace visibility with the good one
                    setter.setVisibility(getPropertySetterVisibility(theAssociationEnd));
        
                    // Replace comment in the generated code
                    String code = setter.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
                    if (code != null) {
                        code = code.replace(accessorManager.AUTOMATIC_UPDATE_COMMENT, accessorManager.PROPERTY_COMMENT);
                        setter.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE, code);
                    }
        
                    if (oldRoundTrip) {
                        ret.append (computePropertySetterAnnotation());
                    }
                    ret.append(computeMethod((setter)));
                }
        
                // Delete the temporary created accessor
                tempAccessor.delete();
            }
        } catch (ExtensionNotFoundException e) {
            throw new CustomException(e.getMessage());
        } finally {
            this.javaConfig.GENERATIONMODE_MODELDRIVEN = oldModelDriven;
            this.javaConfig.GENERATIONMODE_ROUNDTRIP = oldRoundTrip;
        }
        return ret;
    }

    private CharSequence getPropertyDeclaration(final Attribute theAttribute) throws CustomException, TemplateException {
        StringBuilder ret = new StringBuilder ();
        
        boolean oldModelDriven = this.javaConfig.GENERATIONMODE_MODELDRIVEN;
        this.javaConfig.GENERATIONMODE_MODELDRIVEN = false;
        
        boolean oldRoundTrip = this.javaConfig.GENERATIONMODE_ROUNDTRIP;
        this.javaConfig.GENERATIONMODE_ROUNDTRIP = false;
        
        
        try {
            if (isNotClassMemberInAnInterfaceWithoutInit (theAttribute)) {
                ret.append (getCurrentIndent ());
                ret.append (computeFieldModifiers (theAttribute));
                ret.append (this.typeManager.getTypeDeclaration (this.session, theAttribute, isFullNameGeneration (theAttribute)));
                ret.append (JavaDesignerUtils.getJavaName (theAttribute));
                ret.append (updateNewlinesExceptLast (this.typeManager.computeInitialValue (this.session, theAttribute, isFullNameGeneration (theAttribute))));
                ret.append (";");
                ret.append (computeAttributeInitialValueComment (theAttribute));
                ret.append (NL);
                ret.append (NL);
            }
        
            // Reset attribute's visibility
            //theAttribute.setVisibility(oldVisibility);
        
            // Add accessors in the model, they will be rollbacked
            AccessorManager accessorManager = new AccessorManager (this.session);
            accessorManager.init (this.javaConfig.getJavaConfiguration());
        
            for (Operation tempAccessor : accessorManager.updateAccessors (theAttribute, true)) {
                // Check stereotypes only for operations
                if (tempAccessor.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVAGETTER)) {
                    Operation getter = tempAccessor;
        
                    // Replace visibility with the good one
                    getter.setVisibility(getPropertyGetterVisibility(theAttribute));
        
                    // Replace comment in the generated code
                    String code = getter.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
                    if (code != null) {
                        code = code.replace(accessorManager.AUTOMATIC_UPDATE_COMMENT, accessorManager.PROPERTY_COMMENT);
                        getter.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE, code);
                    }
        
                    if (oldRoundTrip) {
                        ret.append (computePropertyGetterAnnotation());
                    }
                    ret.append(computeMethod((getter)));
                } else if (tempAccessor.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVASETTER)) {
                    Operation setter = tempAccessor;
        
                    // Replace visibility with the good one
                    setter.setVisibility(getPropertySetterVisibility(theAttribute));
        
                    // Replace comment in the generated code
                    String code = setter.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE);
                    if (code != null) {
                        code = code.replace(accessorManager.AUTOMATIC_UPDATE_COMMENT, accessorManager.PROPERTY_COMMENT);
                        setter.putNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE, code);
                    }
        
                    if (oldRoundTrip) {
                        ret.append (computePropertySetterAnnotation());
                    }
                    ret.append(computeMethod((setter)));
                }
        
                // Delete the temporary created accessor
                tempAccessor.delete();
            }
        } catch (ExtensionNotFoundException e) {
            throw new CustomException(e.getMessage());
        } finally {
            this.javaConfig.GENERATIONMODE_MODELDRIVEN = oldModelDriven;
            this.javaConfig.GENERATIONMODE_ROUNDTRIP = oldRoundTrip;
        }
        return ret;
    }

    public CharSequence computePropertyDefinitionAnnotation() {
        StringBuilder ret = new StringBuilder();
        if (this.javaConfig.GENERATIONMODE_ROUNDTRIP) {
            ret.append (getCurrentIndent ());
            ret.append ("@mdl.prop");
            ret.append (NL);
        }
        return ret;
    }

    public CharSequence computePropertyGetterAnnotation() {
        StringBuilder ret = new StringBuilder();
        ret.append (getCurrentIndent ());
        ret.append ("@mdl.propgetter");
        ret.append (NL);
        return ret;
    }

    public CharSequence computePropertySetterAnnotation() {
        StringBuilder ret = new StringBuilder();
        ret.append (getCurrentIndent ());
        ret.append ("@mdl.propsetter");
        ret.append (NL);
        return ret;
    }

    private VisibilityMode getPropertyGetterVisibility(final Feature accessor) {
        VisibilityMode visibility;
        
        try {
            String code = ModelUtils.getFirstTagParameter(accessor, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVAGETTERVISIBILITY);
            visibility = VisibilityMode.valueOf(code.toUpperCase());
        } catch (Exception e) {
            visibility = accessor.getVisibility();
        }
        return visibility;
    }

    private VisibilityMode getPropertySetterVisibility(final Feature accessor) {
        VisibilityMode visibility;
        
        try {
            String code = ModelUtils.getFirstTagParameter(accessor, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVAATTRIBUTEPROPERTY_JAVASETTERVISIBILITY);
            visibility = VisibilityMode.valueOf(code.toUpperCase());
        } catch (Exception e) {
            visibility = accessor.getVisibility();
        }
        return visibility;
    }

    private File getCopyrightFile(final MObject element) {
        ModelElement parent = (ModelElement) element.getCompositionOwner();
        while (parent != null && !(parent instanceof Component) && !parent.isStereotyped(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerStereotypes.JAVACOMPONENT)) {
            parent = (ModelElement) parent.getCompositionOwner();
        }
        
        String path = null;
        if (parent != null) {
            path = ModelUtils.getFirstTagParameter(parent, IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerTagTypes.JAVACOMPONENT_COPYRIGHTFILE);
        }
        
        if (path == null || path.isEmpty()) {
            path = this.javaConfig.COPYRIGHTFILE;
        }
        
        File copyrightFile = new File(path);
        if (copyrightFile.isFile() && copyrightFile.canRead()) {
            return copyrightFile;
        }
        
        // Try relative path
        copyrightFile = new File(JavaDesignerModule.getInstance().getModuleContext().getProjectStructure().getPath().toFile(), path);
        if (copyrightFile.isFile() && copyrightFile.canRead()) {
            return copyrightFile;
        }
        return null;
    }

    private void generateCopyright(final MObject element, final PrintStream out) {
        File copyrightFile = getCopyrightFile (element);
        
        if (copyrightFile != null) {
            StringBuilder content = computeCopyrightContent(copyrightFile);
        
            if (content.length() > 0) {
                out.println(content);
            }
        }
    }

    private StringBuilder computeCopyrightContent(final File copyrightFile) {
        StringBuilder content = this.copyrightCache.get(copyrightFile.getAbsolutePath());
        
        if (content == null) {
            content = new StringBuilder();
        
            try (InputStream ips = new FileInputStream(copyrightFile);
                    InputStreamReader ipsr = new InputStreamReader(ips);
                    BufferedReader br = new BufferedReader(ipsr)) {
        
                int len;
                char[] cbuf = new char[2048];
                while ( (len = br.read(cbuf)) > 0 ) {
                    content.append(cbuf, 0, len);
                }
        
                br.close();
            } catch (IOException e) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            }
            this.copyrightCache.put(copyrightFile.getAbsolutePath(), content);
        }
        return content;
    }

    /**
     * tell if a method is implemented
     */
    private boolean isImplemented(final Operation theOperation) {
        return (theOperation.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVACODE) != null) || (theOperation.getNoteContent(IJavaDesignerPeerModule.MODULE_NAME, JavaDesignerNoteTypes.OPERATION_JAVARETURNED) != null);
    }

    private boolean isJDK8Compatible() {
        return this.javaConfig.JAVA8COMPATIBILITY;
    }

}
