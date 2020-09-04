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
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;

/**
 * Generate XML syntax for Class or Datatype definition
 */
class ClassXMLGenerator extends XMLGeneratorWithModifiers {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.CLASS_DEF) {
            throw new BadNodeTypeException(
                    Messages.getString("reverse.Node_must_be_CLASS_DEF"), ast); //$NON-NLS-1$
        }
        
        // Process class name
        ASTTree IDENTChild = (ASTTree) ast
                .getFirstChildWithType(JavaParser.IDENT);
        if (IDENTChild == null) {
            throw new XMLGeneratorException(
                    Messages.getString("reverse.Class_name_not_found")); //$NON-NLS-1$
        }
        String eltName = IDENTChild.getText();
        ClassifierDef elt = ctx.getSModel().findClassifier(eltName, ctx.getGStack().getCurrentStructuralElement());
        ctx.getGStack().push(elt);
        
        List<ASTTree> annotations = AnnotationAstServices.getAnnotations(ast);
        
        // Set the XML tag to generate
        String XMLTag;
        if (AnnotationAstServices.extractDatatype(annotations)) {
            XMLTag = "data-type";
            elt.setKind(ClassifierDefKind.DATATYPE);
        } else {
            XMLTag = "class";
            elt.setKind(ClassifierDefKind.CLASS);
        }
        
        // Define the internal id
        String id = ctx.getIdManager().defineIdentifier(elt);
        
        // Write the XML tag
        XMLBuffer.model.write("<" + XMLTag + " id=\""); //$NON-NLS-1$
        XMLBuffer.model.write(id);
        XMLBuffer.model.write("\" name=\""); //$NON-NLS-1$
        XMLBuffer.model.write(eltName);
        XMLBuffer.model.write("\""); //$NON-NLS-1$
        
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
        
        // Process other child nodes
        for (ASTTree child : ast.getChildrenSafe()) {
            switch (child.getType()) {
            case JavaParser.IDENT:
            case JavaParser.MODIFIERS:
            case JavaParser.ANNOTATION:
                // Already processed node : skip it
                break;
            default:
                // Delegate the generation of the node to its specialized generator
                XMLGenerator xmlGenerator =  ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.CLASS);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(child, ctx);
                }
            }
        }
        
        XMLBuffer.model.write("</" + XMLTag + ">\n"); //$NON-NLS-1$
        if (elt.isTopLevel()) {
            // Clear the import context if at top level
            ctx.getTFinder().clearImports();
        }
        ctx.getGStack().pop();
    }

    @Override
    protected String formatModifierAttributes(final Modifiers mods) {
        StringBuilder buf = new StringBuilder();
        mods.getXMLAttributeVisibility(buf);
        buf.append(" is-leaf=\"").append(mods.isFinal()).append("\"");
        buf.append(" is-abstract=\"").append(mods.isAbstract()).append("\"");
        return buf.toString();
    }

    @Override
    protected void generateModifierXMLTags(final Modifiers mods) throws IOException {
        if (mods.isStatic()) {
            GeneratorUtils.generateTaggedValueTag("JavaStatic"); //$NON-NLS-1$
        }
    }

}
