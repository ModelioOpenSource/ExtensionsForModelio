package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class CommentXMLGenerator extends XMLGenerator {
    public static void getCommentDef(final ASTTree child) throws BadNodeTypeException, IOException {
        if (child.getType() != JavaParser.COMMENT) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_COMMENT")); //$NON-NLS-1$
        }
        
        StringBuilder content = new StringBuilder(child.getText());
        GeneratorUtils.generateNoteTag("JavaComment", content); //$NON-NLS-1$
    }

    public static void getJavadocDef(final ASTTree child) throws BadNodeTypeException, IOException {
        if (child.getType() != JavaParser.JAVADOC) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_JAVADOC_COMMENT")); //$NON-NLS-1$
        }
        
        StringBuilder content = new StringBuilder(child.getText());
        GeneratorUtils.generateNoteTag("Javadoc", content); //$NON-NLS-1$
    }

    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws BadNodeTypeException, IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.COMMENTS) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_COMMENTS"), ast); //$NON-NLS-1$
        }
        
        for (ASTTree child : ast.getChildrenSafe()) {
            if (child.getType() == JavaParser.COMMENT) {
                getCommentDef(child);
            } else if (child.getType() == JavaParser.JAVADOC) {
                getJavadocDef(child);
            }
        }
    }

}
