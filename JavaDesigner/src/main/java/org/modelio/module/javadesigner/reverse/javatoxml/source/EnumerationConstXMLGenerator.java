package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.List;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

/**
 * Generate XML output for enumeration literal. If an initialization expression
 * is present, it is generated as a JavaArguments taggedValue and its content,
 * is retrieved directly from the Java source via ASTTree.getSourceCode service.
 */
class EnumerationConstXMLGenerator extends XMLGenerator {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        ASTTree ident = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT);
        if (ident == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.enum_const_name_not_found")); //$NON-NLS-1$            
        }
        XMLBuffer.model.write("<enumeration-literal name=\"");
        XMLBuffer.model.write(ident.getText());
        
        List<ASTTree> annotations = AnnotationAstServices.getAnnotations(ast);
          
          // Write objid as an XML attribute
        String objid = AnnotationAstServices.extractObjidValue(annotations);
        if (objid != null) {
            XMLBuffer.model.write(" objid=");
            XMLBuffer.model.write(objid);
        }
        
        XMLBuffer.model.write("\" >");
        
        // Generate remaining annotations
        generateAnnotationsXMLTags(annotations);
        
        StringBuilder trailingComment = null;
        for (ASTTree child : ast.getChildrenSafe()) {
            if (child.getType() == JavaParser.ELIST) {
                // Parameter list in enum contructor call
                GeneratorUtils.generateTaggedValueTagWithParam("JavaArguments", child.getSourceCode());
            }
            if (child.getType() == JavaParser.OBJBLOCK) {
                StringBuilder code = new StringBuilder(child.getSourceCode());
                // remove the enclosing { }
                code.deleteCharAt(code.indexOf("{"));
                code.deleteCharAt(code.lastIndexOf("}"));
                GeneratorUtils.generateNoteTag("JavaCode", code.toString());
            }
            if (child.getType() == JavaParser.TRAILING_COMMENT) {
                // Trailing comment
                if (trailingComment == null) {
                    trailingComment = new StringBuilder();
                } else {
                    trailingComment.append(' ');
                }
                trailingComment.append(child.getText());
            }
            if (child.getType() == JavaParser.COMMENTS) {
                XMLGenerator xmlGenerator = ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.ENUM);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(child, ctx);
                }
            }
        }
        if (trailingComment != null) {
            GeneratorUtils.generateNoteTag("JavaInitValueComment", trailingComment);
        }
        XMLBuffer.model.write("</enumeration-literal>");
    }

}
