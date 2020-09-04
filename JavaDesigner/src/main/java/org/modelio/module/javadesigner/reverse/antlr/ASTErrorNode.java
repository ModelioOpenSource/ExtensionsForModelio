package org.modelio.module.javadesigner.reverse.antlr;

import org.antlr.runtime.tree.CommonErrorNode;

/**
 * Enclosing class for the CommonErrorNode.
 */
public class ASTErrorNode extends ASTTree {
    private CommonErrorNode errorNode;

    public ASTErrorNode(final CommonErrorNode errorNode) {
        this.errorNode = errorNode;
    }

    public CommonErrorNode getErrorNode() {
        return this.errorNode;
    }

}
