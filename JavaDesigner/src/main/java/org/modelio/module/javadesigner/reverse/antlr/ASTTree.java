package org.modelio.module.javadesigner.reverse.antlr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

/**
 * AST node with added source code that produced the node. Only few nodes type
 * manages the sourceCode like method body or attribute initialization to avoid
 * excessive memory consumption.
 */
public class ASTTree extends CommonTree {
    /**
     * The source code that correspond to the current node. May be null.
     */
    private String sourceCode;

    public String getSourceCode() {
        return this.sourceCode;
    }

    public void setSourceCode(final String aSourceCode) {
        this.sourceCode = aSourceCode;
    }

    public ASTTree() {
        super();
    }

    public ASTTree(final CommonTree node) {
        super(node);
        if (node instanceof ASTTree) {
            this.sourceCode = ((ASTTree) node).sourceCode;
        }
    }

    public ASTTree(final Token t) {
        super(t);
    }

    public ASTTree(final List<? extends Tree> children) {
        super();
        if (children != null) {
            addChildren(children);
        }
    }

    /**
     * duplicate current node See ASTAntlr2.addChild for details To be removed
     * once ASTAntlr2 is.
     */
    @Override
    public ASTTree dupNode() {
        ASTTree dup = new ASTTree(this);
        // duplicate the children
        List<? extends Object> asts = this.getChildren();
        if (asts != null) {
            for (Object co : this.getChildren()) {
                dup.addChild((ASTTree) co); // will trigger duplication of co
            }
        }
        return dup;
    }

    @SuppressWarnings("unchecked")
    public void insertInFront(final ASTTree subtree) {
        // TODO anchor.insertChild(0, commentsNode) would be better but produces
        // a NoSuchMethod exception
        @SuppressWarnings("rawtypes")
        List asts = getChildren();
        if (asts == null) {
            // first child
            addChild(subtree);
        } else {
            asts.add(0, subtree);
        }
        freshenParentAndChildIndexes(0);
    }

    /**
     * return all the direct children that share a given node type
     * @param type node type to select the children
     * @return the list of children whose type is 'type' or an empty list if none found.
     */
    public List<ASTTree> getAllChildrenWithType(final int type) {
        List<ASTTree> retval = new ArrayList<>();
        for (ASTTree child : this.getChildrenSafe()) {
            if (child.getType() == type) {
                retval.add(child);
            }
        }
        return retval;
    }

    /**
     * Safe version of CommonTree::getChildren.
     * Never return null but a non mutable emptyList if the ASTTree has no children
     */
    public List<? extends ASTTree> getChildrenSafe() {
        @SuppressWarnings("unchecked")
        List<? extends ASTTree> asts = (List<? extends ASTTree>) super.getChildren();
        return asts == null ? Collections.<ASTTree>emptyList() : asts;
    }

}
