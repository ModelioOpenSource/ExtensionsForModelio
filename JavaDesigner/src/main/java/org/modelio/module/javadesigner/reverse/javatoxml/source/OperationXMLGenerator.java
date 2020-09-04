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
 * Generate XML syntax for Operation definition
 */
class OperationXMLGenerator extends XMLGeneratorWithModifiers {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.METHOD_DEF) {
            throw new BadNodeTypeException(
                    Messages.getString("reverse.Node_must_be_METHOD_DEF_or_CTOR_DEF"), ast); //$NON-NLS-1$
        }
        // Process class name
        ASTTree IDENTChild = (ASTTree) ast
                .getFirstChildWithType(JavaParser.IDENT);
        if (IDENTChild == null) {
            throw new XMLGeneratorException(
                    Messages.getString("reverse.Method_name_not_found")); //$NON-NLS-1$
        }
        
        String eltName = IDENTChild.getText();
        ctx.getGStack().push(new SimpleSymbol(eltName));
        // TODO optionally register an id for the report system
        
        XMLBuffer.model.write("<operation name=\""); //$NON-NLS-1$
        XMLBuffer.model.write(eltName);
        XMLBuffer.model.write("\""); //$NON-NLS-1$
        
        List<ASTTree> annotations = AnnotationAstServices.getAnnotations(ast);
        
        // Write objid as an XML attribute
        String objid = AnnotationAstServices.extractObjidValue(annotations);
        if (objid != null) {
            XMLBuffer.model.write(" objid=");
            XMLBuffer.model.write(objid);
        }
        
        // Process the modifiers translated as XML attributes
        Modifiers mod = this.compileModifier(ast);
        this.generateModifierAttributes(mod);
        
        XMLBuffer.model.write(">\n"); //$NON-NLS-1$
        
        // Generate modifier as tags
        this.generateModifierXMLTags(mod);
        
        // Generate remaining annotations
        this.generateAnnotationsXMLTags(annotations);
        
        // Prepare to receive template parameter
        ctx.getTFinder().initOperationTemplateParametersContext();
        
        // Process other child nodes    
        for (ASTTree child : ast.getChildrenSafe()) {
            switch (child.getType()) {
            case JavaParser.IDENT:
            case JavaParser.MODIFIERS:
            case JavaParser.ANNOTATION:
                // Already processed node : skip it
                break;
            case JavaParser.VOID:
                // Ignored node
                break;
            default:
                // Delegate the generation of the node to its specialized generator
                XMLGenerator xmlGenerator =  ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.OPERATION);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(child, ctx);
                }
            }
        }
        
        XMLBuffer.model.write("</operation>\n"); //$NON-NLS-1$
        // Remove the template parameters context
        ctx.getTFinder().clearOperationTemplateParametersContext();
        ctx.getGStack().pop();
    }

    @Override
    protected String formatModifierAttributes(final Modifiers mods) {
        StringBuilder buf = new StringBuilder();
        mods.getXMLAttributeVisibility(buf);
        buf.append(" final=\"").append(mods.isFinal()).append("\"");
        buf.append(" is-abstract=\"").append(mods.isAbstract()).append("\"");
        buf.append(" is-class=\"").append(mods.isStatic()).append("\"");
        return buf.toString();
    }

    @Override
    protected void generateModifierXMLTags(final Modifiers mods) throws IOException {
        if (mods.isSynchronized()) {
            GeneratorUtils.generateTaggedValueTag ("JavaSynchronized"); //$NON-NLS-1$
        }
        if (mods.isNative()) {
            GeneratorUtils.generateTaggedValueTag ("JavaNative"); //$NON-NLS-1$
        }
        if (mods.isStrictfp()) {
            GeneratorUtils.generateTaggedValueTag ("JavaStrict"); //$NON-NLS-1$
        }
    }

}
