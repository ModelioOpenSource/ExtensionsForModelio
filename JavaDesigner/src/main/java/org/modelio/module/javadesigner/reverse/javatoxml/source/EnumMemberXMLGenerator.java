package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

class EnumMemberXMLGenerator extends XMLGenerator {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.OBJBLOCK) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_OBJBLOCK"), ast); //$NON-NLS-1$
        }
        
        StringBuilder javaCodeComment = null;
        for (ASTTree child : ast.getChildrenSafe()) {
            switch (child.getType()) {
            case JavaParser.COMMENTS:
                // Comment in enum blocks should be generated as JavaMembers
                javaCodeComment = generateComments(child, javaCodeComment);
                break;
        
            default:
                XMLGenerator xmlGenerator = ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.ENUM);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(child, ctx);
                }
            }
        }
        
        // Create JavaMembers note
        if (javaCodeComment != null) {
            GeneratorUtils.generateNoteTag("JavaMembers", javaCodeComment);
        }
    }

    /**
     * comments in enum block are generated as javaCode
     * @param comments
     * @param javaCode
     * @return
     */
    private StringBuilder generateComments(final ASTTree comments, final StringBuilder initialJavaCodeComment) {
        StringBuilder javaCodeComment = initialJavaCodeComment;
        if (javaCodeComment == null)
            javaCodeComment = new StringBuilder();
        for (ASTTree child : comments.getChildrenSafe()) {
            javaCodeComment.append(child.getText());
        }
        return javaCodeComment;
    }

}
