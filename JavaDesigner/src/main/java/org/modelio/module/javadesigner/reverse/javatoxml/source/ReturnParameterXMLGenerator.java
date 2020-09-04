package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.List;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class ReturnParameterXMLGenerator extends XMLGenerator {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.TYPE) {
            throw new BadNodeTypeException(
                    Messages.getString("reverse.ReturnParameterXMLGenerator.Node_must_be_TYPE"), ast); //$NON-NLS-1$
        }
        
        XMLBuffer.model.write("<return-parameter passing-mode=\"Out\""); //$NON-NLS-1$
        
        // Get the multiplicity attributes according to the type of the
        // parameter
        XMLBuffer.model.write(TypeGenerator.getMultiplicityAttribute(ast).toString());
        
        XMLBuffer.model.write(">"); //$NON-NLS-1$
        
        // Generate  annotations
        // TODO check if meaningful
        List<ASTTree> annotations = AnnotationAstServices.getAnnotations(ast);
        this.generateAnnotationsXMLTags(annotations);
        
        // Generate the type part
        TypeGenerator.generateXMLType(ast, false, ctx);
        
        // Close the tag
        XMLBuffer.model.write("</return-parameter>\n"); //$NON-NLS-1$
    }

}
