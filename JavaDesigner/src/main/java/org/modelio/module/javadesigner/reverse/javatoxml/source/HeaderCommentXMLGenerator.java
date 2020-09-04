package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class HeaderCommentXMLGenerator extends XMLGenerator {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.COMMENTS) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_COMMENTS"), ast); //$NON-NLS-1$
        }
        
        for (ASTTree child : ast.getChildrenSafe()) {
            StringBuilder content = new StringBuilder(child.getText());
            if (!content.toString().contains("Copyright")) {
                GeneratorUtils.generateNoteTag("JavaTop", content); //$NON-NLS-1$
            }
        
        }
    }

}
