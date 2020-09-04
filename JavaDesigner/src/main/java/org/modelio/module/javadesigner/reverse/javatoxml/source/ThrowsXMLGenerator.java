package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.identification.Identified;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Type;

class ThrowsXMLGenerator extends XMLGenerator {
    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.THROWS) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_LITERAL_throws"), ast); //$NON-NLS-1$
        }
        
        /*
         * A Java (1.7) exception can't have template parameters but it can be a template parameter of the throwing method
         * or any accessible template parameter.
         */
        
        for (ASTTree child : ast.getChildrenSafe()) {
            if (child.getType() == JavaParser.THROWN_EXCEPTION) {
                generateThrowsClause(child, ctx);
            }
        }
    }

    private void generateThrowsClause(final ASTTree thrownException, final Context ctx) throws IOException, NameCollisionException {
        String anOriginalName = AstUtils.getCanonicalIdentifier(thrownException.getChild(0));
        Type aThrownType = ctx.getTFinder().findType(anOriginalName);
        if (aThrownType == null) {
            GeneratorUtils.generateTaggedValueTagWithParam("JavaThrownException", thrownException.getSourceCode()); //$NON-NLS-1$
            ctx.getReport().addWarning(Messages.getString("reverse.Throw_warning.title", anOriginalName), null, Messages.getString("reverse.Throw_warning.description", anOriginalName)); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            XMLBuffer.model.write("<raised-exception>\n"); //$NON-NLS-1$
            XMLBuffer.model.write("<used-class>"); //$NON-NLS-1$
        
            GeneratorUtils.generateDestination(ctx.getIdManager().declareReferenceIdentifier((Identified)aThrownType));
        
            XMLBuffer.model.write("</used-class>\n"); //$NON-NLS-1$
            TypeGenerator.generateCondJavaFullName(anOriginalName);
            generateAnnotationsXMLTags(AnnotationAstServices.getAnnotations(thrownException));
            XMLBuffer.model.write("</raised-exception>\n"); //$NON-NLS-1$
        }
    }

}
