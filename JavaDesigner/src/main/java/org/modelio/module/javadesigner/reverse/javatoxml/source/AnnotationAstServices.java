package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.util.ArrayList;
import java.util.List;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javautil.StringUtil;

/**
 * Help to parse Annotation ast
 */
class AnnotationAstServices {
    private AnnotationAstServices() {
    }

    /**
     * Tell if a root node has ANNOTATION nodes
     * @return true if root node has one or more annotations, false if it has no annotations at all
     */
    public static boolean isAnnotated(final ASTTree rootNode) {
        return rootNode.getFirstChildWithType(JavaParser.ANNOTATION) != null;
    }

    /**
     * Retrieve the ANNOTATION nodes from a root node (ie a class, an attribute,
     * an operation, etc)
     * @param rootNode the node to get the annotations from.
     * @return a list of annotation nodes possibly empty if no annotation on
     * root node.
     */
    public static List<ASTTree> getAnnotations(final ASTTree rootNode) {
        return rootNode.getAllChildrenWithType(JavaParser.ANNOTATION);
    }

    /**
     * Find all annotations that match a full qualified name. Ideally name
     * should be checked according to Java resolution rules (import, etc) but
     * for now we only check that 'fqname' ends with annotation name.
     * @param annotations : set of annotation (typically return of getAnnotations(final
     * ASTTree rootNode))
     * @param fqname : annotation name to search. A full qualified name
     * @return all annotations matching name or an empty list
     */
    public static List<ASTTree> findAnnotationsByName(final List<ASTTree> annotations, final String fqname) {
        // TODO : replace 'endswith' with a real Java name resolver
        List<ASTTree> retlist = new ArrayList<>();
        for (ASTTree annotation : annotations) {
            if (fqname.endsWith(getName(annotation))) {
                retlist.add(annotation);
            }
        }
        return retlist;
    }

    /**
     * Return the name of an annotation
     * @param annotation the AST Tree to analyze.
     * @return The name of this annotation
     */
    public static String getName(final ASTTree annotation) {
        return AstUtils.getCanonicalIdentifier(annotation.getChild(0));
    }

    /**
     * Retrieve the first parameter of an annotation
     * @param annotation the AST Tree to analyze.
     * @return ASTTree that is the first parameter or null if not found
     */
    public static ASTTree getFirstParameter(final ASTTree annotation) {
        ASTTree retval = null;
        if (annotation.getChildCount() == 2) {
            ASTTree p1 = (ASTTree) annotation.getChild(1);
            switch (p1.getType()) {
            case JavaParser.ANNOTATION_INIT_VALUE:
                // the parameter value is the source code of the only child of
                // p1
                return (ASTTree) p1.getChild(0);
            case JavaParser.ANNOTATION_INIT_LIST:
                // the identifier of annotationMemberInit should be skipped to
                // get the value
                // the situation is :
                // (ANNOTATION_INIT_LIST (ANNOTATION_INIT_MEMBER IDENT
                // annotationMemberValue))
                // ^p1 ^child(0) ^child(1)
                return (ASTTree) p1.getChild(0).getChild(1);
            default:
                break;
            }
        }
        return retval;
    }

    /**
     * Retrieve the first parameter value of an annotation
     * @param annotation the AST Tree to analyze.
     * @return a String containing the raw value of the 1st parameter or "" if
     * no parameter
     */
    public static String getFirstParameterValue(final ASTTree annotation) {
        ASTTree par = getFirstParameter(annotation);
        return (par != null) ? par.getSourceCode() : "";
    }

    /**
     * Extract objid annotation of 'annotations' and return its value. If objid
     * occurs more than once, the last value is returned. Every objid annotation
     * is removed from 'annotations'
     * @param annotations : the list of annotations to search, modified on return
     * @return the string value of objid or null if not found
     */
    public static String extractObjidValue(final List<ASTTree> annotations) {
        List<ASTTree> objid = AnnotationAstServices.findAnnotationsByName(annotations,
                "com.modeliosoft.modelio.javadesigner.annotations.objid");
        if (!objid.isEmpty()) {
            annotations.removeAll(objid);
            return AnnotationAstServices.getFirstParameterValue(objid.get(objid.size() - 1));
        }
        return null;
    }

    /**
     * Extract DataType annotation of 'annotations' and indicates if it has been
     * found.
     * @param annotations : the list of annotations to search, modified on return
     * @return true if DataType has been found.
     */
    public static boolean extractDatatype(final List<ASTTree> annotations) {
        List<ASTTree> datatype = findAnnotationsByName(annotations,
                "com.modeliosoft.modelio.javadesigner.annotations.DataType");
        if (!datatype.isEmpty()) {
            annotations.removeAll(datatype);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Extract Documented annotation of 'annotations' and indicates if it has
     * been found.
     * @param annotations : the list of annotations to search, modified on return
     * @return true if Documented has been found.
     */
    public static boolean extractDocumented(final List<ASTTree> annotations) {
        List<ASTTree> documented = findAnnotationsByName(annotations, "java.lang.annotation.Documented");
        if (!documented.isEmpty()) {
            annotations.removeAll(documented);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Extract Inherited annotation of 'annotations' and indicates if it has
     * been found.
     * @param annotations : the list of annotations to search, modified on return
     * @return true if Inherited has been found.
     */
    public static boolean extractInherited(final List<ASTTree> annotations) {
        List<ASTTree> documented = findAnnotationsByName(annotations, "java.lang.annotation.Inherited");
        if (!documented.isEmpty()) {
            annotations.removeAll(documented);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Extract Retention annotation of 'annotations' and return its value. Every
     * Retention annotation is removed from 'annotations'
     * @param annotations : the list of annotations to search, modified on return
     * @return the string value of Retention or null if not found
     */
    public static String extractRetentionValue(final List<ASTTree> annotations) {
        List<ASTTree> retention = AnnotationAstServices.findAnnotationsByName(annotations,
                "java.lang.annotation.Retention");
        if (!retention.isEmpty()) {
            annotations.removeAll(retention);
            String pval = AnnotationAstServices.getFirstParameterValue(retention.get(retention.size() - 1));
            return StringUtil.shortenFullName(pval);
        }
        return "";
    }

    /**
     * Extract Target annotation of 'annotations' and return its value. Every
     * Target annotation is removed from 'annotations'
     * @param annotations : the list of annotations to search, modified on return
     * @return the string value of Retention or null if not found
     */
    public static List<String> extractTargetValues(final List<ASTTree> annotations) {
        List<String> retval = new ArrayList<>();
        List<ASTTree> target = AnnotationAstServices.findAnnotationsByName(annotations, "java.lang.annotation.Target");
        if (!target.isEmpty()) {
            annotations.removeAll(target);
            ASTTree fpar = AnnotationAstServices.getFirstParameter(target.get(target.size() - 1));
            if (fpar.getType() == JavaParser.ANNOTATION_ARRAY_INIT) {
                // the parameter is an array initialization as expected for
                // @Target
                // loop on array parameters
                for (ASTTree p2 : fpar.getChildrenSafe()) {
                    if (p2.getType() == JavaParser.EXPR) {
                        retval.add(StringUtil.shortenFullName(p2.getSourceCode()));
                    }
                }
            } else if (fpar.getType() == JavaParser.EXPR) {
                // an array with a single value can be passed as the value
                // itself (no need for a singleton array)
                // example : @Target(ElementType.ANNOTATION_TYPE) is possible
                // although target expect an array of ElementType)
                retval.add(StringUtil.shortenFullName(fpar.getSourceCode()));
            }
        }
        return retval;
    }

}
