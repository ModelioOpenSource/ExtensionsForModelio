package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javautil.XMLStringWriter;

class AssignXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML(ASTTree, Context)
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.ASSIGN) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_ASSIGN"), ast); //$NON-NLS-1$
        }
        
        XMLBuffer.model.write("<value><![CDATA["); //$NON-NLS-1$
        XMLStringWriter xw = new XMLStringWriter();
        XMLBuffer.model.write(xw.encodedata(ast.getSourceCode()));
        XMLBuffer.model.write("]" + "]></value>\n"); //$NON-NLS-1$
    }

}
