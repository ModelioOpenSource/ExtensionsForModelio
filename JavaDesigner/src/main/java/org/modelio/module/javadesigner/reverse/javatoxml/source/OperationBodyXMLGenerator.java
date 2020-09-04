package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class OperationBodyXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.SLIST) {
            throw new BadNodeTypeException(
                    Messages.getString("reverse.OperationBodyXMLGenerator.Node_must_be_SLIST"), ast); //$NON-NLS-1$
        }
        
        StringBuilder methodContent = new StringBuilder(ast.getSourceCode());
        
        // Look for the final 'return' to transfer it to a JavaReturned note
        int childCount = ast.getChildCount();
        if (childCount > 0) {
            ASTTree lastStatement = (ASTTree) ast.getChild(childCount - 1);
            if (lastStatement.getType() == JavaParser.RETURN) {
                // 'return' found.
                methodContent.delete(methodContent.lastIndexOf(lastStatement.getSourceCode()), methodContent.length());
                XMLGenerator xmlGenerator = ctx.getGeneratorFactory().getXMLGenerator(lastStatement, XMLGeneratorFactory.Context.OPERATION);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(lastStatement, ctx);
                }
            }
        }
        GeneratorUtils.generateNoteTag("JavaCode", methodContent); //$NON-NLS-1$
    }

}
