package org.modelio.module.javadesigner.reverse.antlr;

import org.antlr.runtime.tree.Tree;

/**
 * General purpose utility services on ast
 */
public class AstUtils {
    /**
     * Given an ast representing an identifier, returns its canonical name either qualified or simple depending
     * on the input ast.
     * Arrays or generic notation ([], <>) are skipped and don't appear in the returned identifier
     * Examples :
     * java.util.List<String> -> java.util.List
     * String[][]             -> String
     */
    public static String getCanonicalIdentifier(final Tree tree) {
        StringBuilder result = new StringBuilder();
        getCanonicalIdentifierHelper(tree, result);
        return result.toString();
    }

    private static void getCanonicalIdentifierHelper(final Tree ast, final StringBuilder acc) {
        Tree curnode = ast;
        switch (curnode.getType()) {
        case JavaParser.IDENT:
        case JavaParser.BUILT_IN:
        case JavaParser.WILDCARD:
        case JavaParser.STAR:
        case JavaParser.VOID:
            // atomic name or end of dot chain descent or Java build in type
            acc.append(curnode.getText());
            break;
        case JavaParser.DOT:
            // dot, concatenate the result of its two children
            getCanonicalIdentifierHelper(curnode.getChild(0), acc);
            acc.append(curnode.getText());
            getCanonicalIdentifierHelper(curnode.getChild(1), acc);
            break;
        case JavaParser.ARRAY_DECLARATOR:
            // Array declarator ('[]'), skip it and continue with its child
        case JavaParser.TYPE:
            // Type node (can be found behind an array), skip it and continue with its child
            getCanonicalIdentifierHelper(curnode.getChild(0), acc);
            break;
        case JavaParser.TYPE_ARGS:
            // Generic Parameter : ignore it
            break;
            
        default:
            assert false : curnode.getType(); // unexpected ast node
        }
    }

    /**
     * Return the simple name (getText) of the first IDENT child type of a given node
     * @param ast @return
     */
    public static String getSimpleName(final ASTTree ast) {
        String sname;
        ASTTree ident = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT); 
        if (ident != null) {
            sname = ident.getText();
        } else {
            sname = "";
        }
        return sname;
    }

    /**
     * Tell if a node belongs to the UML classifier category.
     * @param ast @return
     */
    public static boolean isGeneralClass(final ASTTree ast) {
        return ast.getType() == JavaParser.CLASS_DEF || ast.getType() == JavaParser.INTERFACE_DEF
                                || ast.getType() == JavaParser.ANNOTATION_DEF || ast.getType() == JavaParser.ENUM_DEF;
    }

}
