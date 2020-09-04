package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.tree.Tree;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.common.WellKnownContainerServices;

/**
 * Services to analyze TYPE ast node
 */
class TypeAstServices {
    /**
     * @see AstUtils#getCanonicalIdentifier for details.
     * @param typeAst The tree to get the identifier from.
     * @return the actual name of a TYPE node.
     */
    public static String getTypeCanonicalIdentifier(final Tree typeAst) {
        assert typeAst.getType() == JavaParser.TYPE : "Internal error: unexpected node type";
        return AstUtils.getCanonicalIdentifier(typeAst.getChild(0));
    }

    /**
     * Tell if a type is an array
     * @param type the type that might be an array
     * @return true it the type is an array, false otherwise
     */
    public static boolean isArray(final ASTTree type) {
        return type.getFirstChildWithType(JavaParser.ARRAY_DECLARATOR) != null;
    }

    /**
     * Get array dimension of a TYPE ast node.
     * @param type TYPE ast node that may carry an array.
     * @return the number of array dimensions or 0 if type is not an array
     */
    public static int getArrayDimensions(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        return type.getAllChildrenWithType(JavaParser.ARRAY_DECLARATOR).size();
    }

    /**
     * Tells if a type is an annotated array
     * @param type the type that might be an array that might have annotation
     * @return true if type is an array that have one annotation or more. No if type is neither an array nor the array
     * have any annotation at all
     */
    public static boolean isArrayAnnotated(final ASTTree type) {
        boolean ret = false;
        for (ASTTree ad :type.getAllChildrenWithType(JavaParser.ARRAY_DECLARATOR) ) {
            if (ad.getFirstChildWithType(JavaParser.ANNOTATION) != null) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * Tells if a node is a generic type (ie with '<>'). An array type will
     * always return false even if it is an array of a generic type.
     * 
     * Example List -> false List<String> -> true List<String>[] -> false
     * @param type TYPE ast node that may be generic
     * @return true if generic, false otherwise
     */
    public static boolean isGenericType(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        return getTypeArgs(type) != null;
    }

    public static boolean isFullQualifiedNamed(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        return type.getChild(0).getType() == JavaParser.DOT;
    }

    public static List<String> getBoundTypesSourceCode(final ASTTree genericType) {
        assert (isGenericType(genericType)) : "Internal error: unexpected input ast node type";
        List<String> ret = new ArrayList<>();
        for (ASTTree child : getTypeArgs(genericType).getChildrenSafe()) {
            ret.add(child.getSourceCode());
        }
        return ret;
    }

    static ASTTree getTypeArgs(final ASTTree type) {
        ASTTree typeArgs = (ASTTree) type.getFirstChildWithType(JavaParser.TYPE_ARGS);
        if (typeArgs == null) {
            ASTTree subtype = (ASTTree) type.getChild(0);
            if (subtype.getType() == JavaParser.DOT) {
                // in case of full qualified name, recurse on the rhs part of
                // the dot
                return getTypeArgs((ASTTree) subtype.getChild(1));
            }
        }
        return typeArgs;
    }

    /**
     * Tell if 'type' is mappable to an UML element such as a class, interface,
     * enum or instanciated generic class (thanks to {JavaBind}. Non mappable
     * are arrays, wildcards and (for the moment) annotated type.
     * @param type TYPE ast node.
     * @return <code>true</code> if 'type' is mappable to an UML element.
     */
    public static boolean isUMLMappable(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        int childType = type.getChild(0).getType();
        return !AnnotationAstServices.isAnnotated(type) && !isArray(type) &&
                                (childType == JavaParser.IDENT || childType == JavaParser.BUILT_IN || childType == JavaParser.DOT);
    }

    public static boolean isWildCard(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        int childType = type.getChild(0).getType();
        return childType == JavaParser.WILDCARD;
    }

    public static String getMultiplicityMax(final ASTTree type) {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        String multmax;
        if (isArray(type) && !isArrayAnnotated(type)) {
            // Array of stuff
            multmax = "*";
        } else if (isGenericType(type)) {
            if (WellKnownContainerServices.isWellKnownContainer(getTypeCanonicalIdentifier(type))) {
                // Ask the WKC services for the multiplicity
                multmax = WellKnownContainerServices.getWellKnownContainerMultiplicityMax(TypeAstServices
                        .getTypeCanonicalIdentifier(type));
                // Not enough : will we be able to map the pay load to UML ?
                if (!isUMLMappable(WellKnownContainerServicesWithAST.getRealDestinationType(type))) {
                    // no, the WKC will be the real target type, therefore the
                    // multiplicity is "1"
                    multmax = "1";
                }
            } else {
                // Unknown generic
                multmax = "1";
            }
        
        } else {
            // Non generic, non array = "1"
            // (Note : include non generic WKC which are generated as
            // assoc[0..1] toward the WKC)
            multmax = "1";
        }
        return multmax;
    }

}
