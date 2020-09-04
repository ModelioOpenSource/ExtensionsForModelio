package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.antlr.runtime.tree.Tree;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.identification.Identified;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Type;

/**
 * XML generation of template parameter. Factorize the generation common to
 * class template parameter and to operation template parameter.
 */
public abstract class TemplateParameterXMLGenerator extends XMLGenerator {
    /**
     * Generate the template parameters for a classifier
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        assert ast.getType() == JavaParser.FORMAL_TYPE_PARAMS;
        // generate all the formal parameters in the ast
        for (ASTTree child : ast.getChildrenSafe()) {
            this.generateParameter(child, ctx);
        }
    }

    /**
     * Generate XML for one formal generic parameter
     * @throws IOException
     * @throws NameCollisionException
     */
    private void generateParameter(final ASTTree aParameter, final Context ctx) throws IOException, NameCollisionException {
        assert aParameter.getType() == JavaParser.TYPE_PARAM;
        
        XMLBuffer.model.write("<template-parameter"); //$NON-NLS-1$
        this.generateHeader(aParameter.getFirstChildWithType(JavaParser.IDENT),
                ctx);
        XMLBuffer.model.write(">\n"); //$NON-NLS-1$
        
        // generate annotations if any
        this.generateAnnotationsXMLTags(AnnotationAstServices.getAnnotations(aParameter));
        
        // generate extends if any
        ASTTree gextends = (ASTTree) aParameter.getFirstChildWithType(JavaParser.GENERIC_EXTENDS);
        if (gextends != null) {
           this.generateExtends(gextends, ctx);
        }
        
        // Don't forget to close the tag
        XMLBuffer.model.write("</template-parameter>\n"); //$NON-NLS-1$
    }

    /**
     * Generate the XML header, the name and the id of template parameter name
     * @throws IOException
     * @param paramIdent : ast node that carries the parameter name
     */
    private void generateHeader(final Tree paramIdent, final Context ctx) throws IOException {
        assert paramIdent.getType() == JavaParser.IDENT;
        
        // get the parameter name
        // (A generic parameter has always a simple name (no DOT) therefore
        // getText() is enough)
        String parname = paramIdent.getText();
        
        // Retrieve the template parameter from the structural model
        Identified tpar = getIdentifiedTemplateParameter(parname, ctx);
        String id = ctx.getIdManager().defineIdentifier(tpar);
        XMLBuffer.model.write(" id=\""); //$NON-NLS-1$
        XMLBuffer.model.write(id);
        XMLBuffer.model.write("\""); //$NON-NLS-1$
        XMLBuffer.model.write(" name=\""); //$NON-NLS-1$
        XMLBuffer.model.write(parname);
        XMLBuffer.model.write("\""); //$NON-NLS-1$
    }

    /**
     * generate XML tags for the extends part.
     * @param gextends
     * @throws IOException
     * @throws NameCollisionException
     */
    private void generateExtends(final ASTTree gextends, final Context ctx) throws IOException, NameCollisionException {
        assert gextends.getType() == JavaParser.GENERIC_EXTENDS;
        if (gextends.getChildCount() > 1) {
            // multiple bounds : dump all in a JavaExtends tagged value
            GeneratorUtils.generateTaggedValueTagWithParam("JavaExtends",
                    gextends.getSourceCode());
        } else {
            // one bound only
            ASTTree type = (ASTTree) gextends.getChild(0);
            if (TypeAstServices.isGenericType(type) || !AnnotationAstServices.getAnnotations(type).isEmpty()) {
                // extends of generic or annotated type : generate a JavaExtends tagged value
                // from Java source code
                // (no need to look for something in TypeFinder.getFullTypeName,
                // it doesn't handle generic names)
                GeneratorUtils.generateTaggedValueTagWithParam("JavaExtends",
                        gextends.getSourceCode());
            } else {
                // extend of a non generic type
                generateExtendsOfNonGeneric(type, ctx);
            }
        }
    }

    /**
     * Generate XML tags for the extends part of a non generic type
     * @param typeNameNode
     * : the AST node that is the name of the type
     * @throws IOException
     * @throws NameCollisionException
     */
    private void generateExtendsOfNonGeneric(final ASTTree extendedType, final Context ctx) throws IOException, NameCollisionException {
        String typeName = TypeAstServices.getTypeCanonicalIdentifier(extendedType);
        Type type = ctx.getTFinder().findType(typeName);
        if (type == null) {
            // Unknown type
            GeneratorUtils.generateTaggedValueTagWithParam("JavaExtends",
                    typeName);
        } else {
            // Known type 
            XMLBuffer.model.write("<default-type>\n"); //$NON-NLS-1$
            TypeGenerator.generateType(typeName, ctx);
            XMLBuffer.model.write("</default-type>\n"); //$NON-NLS-1$
        }
    }

    protected abstract Identified getIdentifiedTemplateParameter(final String templateName, final Context ctx);

}
