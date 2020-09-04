package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class MemberCommentXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.COMMENTS) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_COMMENTS"), ast); //$NON-NLS-1$
        }
        
        for (ASTTree child : ast.getChildrenSafe()) {
            StringBuilder content = new StringBuilder(child.getText());
            GeneratorUtils.generateNoteTag("JavaMembers", content); //$NON-NLS-1$
        
        }
    }

}
