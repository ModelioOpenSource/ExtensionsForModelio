package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.List;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.Modifiers;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.SimpleSymbol;

/**
 * Generate XML syntax for a list of formal parameters
 */
class ParameterXMLGenerator extends XMLGeneratorWithModifiers {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.PARAMETERS) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_PARAMETERS"), ast); //$NON-NLS-1$
        }
        // generate every parameters
        for (ASTTree child : ast.getChildrenSafe()) {
            getParameterDef(child, ctx);
        }
    }

    private void getParameterDef(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        assert (ast.getType() == JavaParser.PARAMETER_DEF) : "Internal error: unexpected input ast node type";
        
        // Process parameter name
        ASTTree IDENTChild = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT);
        if (IDENTChild == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.Parameter_name_not_found")); //$NON-NLS-1$
        }
        
        String eltName = IDENTChild.getText();
        ctx.getGStack().push(new SimpleSymbol(eltName));
        // TODO optionally register an id for the report system
        
        XMLBuffer.model.write("<parameter name=\""); //$NON-NLS-1$
        XMLBuffer.model.write(eltName);
        XMLBuffer.model.write("\""); //$NON-NLS-1$
        
        // Get the type of the parameter
        ASTTree type = (ASTTree) ast.getFirstChildWithType(JavaParser.TYPE);
        if (type == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.Can_not_find_TYPE_node")); //$NON-NLS-1$
        }
        
        // Get the multiplicity attributes according to the type of the
        // parameter
        XMLBuffer.model.write(TypeGenerator.getMultiplicityAttribute(type).toString());
        
        // Process the modifiers translated as XML attributes
        Modifiers mod = this.compileModifier(ast);
        this.generateModifierAttributes(mod);
        
        // Close the parameter tag
        XMLBuffer.model.write(">\n");
        
        // Generate modifier as tags
        this.generateModifierXMLTags(mod);
        
        
        // Generate  annotations
        List<ASTTree> annotations = AnnotationAstServices.getAnnotations(ast);
        this.generateAnnotationsXMLTags(annotations);
        
        // Generate the type part
        TypeGenerator.generateXMLType(type, false, ctx);
        
        // Look for an ellipsis
        if (ast.getFirstChildWithType(JavaParser.ELLIPSIS) != null) {
            GeneratorUtils.generateTaggedValueTag("JavaVarArgs");
        }
        
        // Process other child nodes
        for (ASTTree child : ast.getChildrenSafe()) {
            switch (child.getType()) {
            case JavaParser.IDENT:
            case JavaParser.MODIFIERS:
            case JavaParser.ANNOTATION:
            case JavaParser.TYPE:
            case JavaParser.ELLIPSIS:
                // Already processed node : skip it
                break;
            default:
                // Delegate the generation of the node to its specialized generator
                XMLGenerator xmlGenerator =  ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.PARAMETER);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(child, ctx);
                }
            }
        }
        
        XMLBuffer.model.write("</parameter>\n"); //$NON-NLS-1$
        ctx.getGStack().pop();
    }

    @Override
    protected String formatModifierAttributes(final Modifiers mods) {
        StringBuilder buf = new StringBuilder(" passing-mode="); //$NON-NLS-1$
        // Useless for the moment : reverse always consider parameter as 'in'
        if (mods.isFinal()) {
            buf.append("\"In\""); //$NON-NLS-1$
        } else {
            buf.append("\"InOut\""); //$NON-NLS-1$
        }
        return buf.toString();
    }

    @Override
    protected void generateModifierXMLTags(final Modifiers mods) throws IOException {
        if (mods.isFinal()) {
            GeneratorUtils.generateTaggedValueTag("JavaFinal"); //$NON-NLS-1$
        }
    }

}
