package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class ClassMemberXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.OBJBLOCK) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_OBJBLOCK"), ast); //$NON-NLS-1$
        }
        
        for (ASTTree child : ast.getChildrenSafe()) {
            // Delegate the generation of the node to its specialized generator
            XMLGenerator xmlGenerator = ctx.getGeneratorFactory()
                    .getXMLGenerator(child, XMLGeneratorFactory.Context.MEMBER);
            if (xmlGenerator != null) {
                xmlGenerator.generateXML(child, ctx);
            }
        }
    }

}
