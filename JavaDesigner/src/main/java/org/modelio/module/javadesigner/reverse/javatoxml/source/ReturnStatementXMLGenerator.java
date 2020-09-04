package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class ReturnStatementXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.RETURN) {
            throw new BadNodeTypeException(
                    Messages.getString("reverse.OperationBodyXMLGenerator.Node_must_be_RETURN"), ast); //$NON-NLS-1$
        }
        StringBuilder returnContent = new StringBuilder(ast.getSourceCode());
        
        // add trailing and underneath comments if any
        for (ASTTree child : ast.getChildrenSafe()) {
            if (child.getType() == JavaParser.TRAILING_COMMENT) {
                // trailing comment
                returnContent.append(' ').append(child.getText());
            } else if (child.getType() == JavaParser.COMMENTS) {
                // comment line(s) after the return
                returnContent = getComments(child, returnContent);
            }
        }
        GeneratorUtils.generateNoteTag("JavaReturned", returnContent); //$NON-NLS-1$
    }

    private StringBuilder getComments(final ASTTree commentsast, final StringBuilder buffer) {
        for (ASTTree child : commentsast.getChildrenSafe()) {
            if (child.getType() == JavaParser.COMMENT || child.getType() == JavaParser.JAVADOC ) {
                if (buffer.length() > 0 && buffer.charAt(buffer.length()-1) != '\n' ) {
                    buffer.append('\n');
                }
                buffer.append(child.getText());
            }
        }
        return buffer;
    }

}
