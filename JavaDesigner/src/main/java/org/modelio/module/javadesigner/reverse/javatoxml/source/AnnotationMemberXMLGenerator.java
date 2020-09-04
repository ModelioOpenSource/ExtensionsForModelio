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
import org.modelio.module.javadesigner.reverse.javautil.XMLStringWriter;

class AnnotationMemberXMLGenerator extends XMLGeneratorWithModifiers {
    private void generateDefaultValue(final ASTTree ast) throws BadNodeTypeException, IOException {
        if (ast.getType() != JavaParser.ANNOTATION_DEFAULT) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_LITERAL_default"), ast); //$NON-NLS-1$
        }
        
        ASTTree defaultValue = (ASTTree)ast.getChild(0);
        String init = defaultValue.getSourceCode();
        XMLBuffer.model.write("<value><![CDATA["); //$NON-NLS-1$
        XMLStringWriter xw = new XMLStringWriter();
        XMLBuffer.model.write(xw.encodedata (init));
        XMLBuffer.model.write("]" + "]></value>\n"); //$NON-NLS-1$
    }

    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType () != JavaParser.ANNOTATION_MEMBER_DEF) {
            throw new BadNodeTypeException (Messages.getString ("reverse.Node_must_be_ANNOTATION_MEMBER_DEF"), ast); //$NON-NLS-1$
        }
        
        // Process method name
        ASTTree IDENTChild = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT);
        if (IDENTChild == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.Method_name_not_found")); //$NON-NLS-1$
        }
        
        String eltName = IDENTChild.getText();
        ctx.getGStack().push(new SimpleSymbol(eltName));
        // TODO optionally register an id for the report system
        
        XMLBuffer.model.write ("<attribute"); //$NON-NLS-1$
        XMLBuffer.model.write(" name=\""); //$NON-NLS-1$
        XMLBuffer.model.write (eltName);
        XMLBuffer.model.write("\"");
        
        List<ASTTree> annotations = AnnotationAstServices.getAnnotations(ast);
        // Write objid as an XML attribute
        String objid = AnnotationAstServices.extractObjidValue(annotations);
        if (objid != null) {
            XMLBuffer.model.write(" objid=");
            XMLBuffer.model.write(objid);
        }
        
        // Process the modifiers translated as XML attributes
        // (The only authorized modifier is 'public') 
        Modifiers mod = this.compileModifier(ast);
        this.generateModifierAttributes(mod);
        
        // Get the type of the annotation method
        ASTTree type = (ASTTree) ast.getFirstChildWithType(JavaParser.TYPE);
        if (type == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.Can_not_find_TYPE_node")); //$NON-NLS-1$
        }
        
        // Get the multiplicity attributes according to the type of the
        // method
        XMLBuffer.model.write(TypeGenerator.getMultiplicityAttribute(type).toString());
        
        XMLBuffer.model.write(">\n"); //$NON-NLS-1$
        
        // Process modifiers translated as XML tags
        this.generateModifierXMLTags(mod);
        
        // Generate remaining annotations
        this.generateAnnotationsXMLTags(annotations);
        
        // Generate the type part
        TypeGenerator.generateXMLType(type, false, ctx);
        
        // Generate the optional default value
        ASTTree defval = (ASTTree) ast.getFirstChildWithType(JavaParser.ANNOTATION_DEFAULT);
        if (defval != null) {
            this.generateDefaultValue(defval);
        }
        
        // Generate the optional trailing comment
        ASTTree trailcom = (ASTTree) ast.getFirstChildWithType(JavaParser.TRAILING_COMMENT);
        if (trailcom != null) {
            // Trailing comment
            StringBuilder sBuffer = new StringBuilder(trailcom.getText());
            GeneratorUtils.generateNoteTag("JavaInitValueComment", sBuffer);
        }
        
        // Process other child nodes
        for (ASTTree child : ast.getChildrenSafe()) {
            switch (child.getType()) {
            case JavaParser.IDENT:
            case JavaParser.MODIFIERS:
            case JavaParser.ANNOTATION:
            case JavaParser.TYPE:
            case JavaParser.ANNOTATION_DEFAULT:
            case JavaParser.TRAILING_COMMENT:
                // Already processed node : skip it
                break;
            default:
                // Delegate the generation of the node to its specialized generator
                XMLGenerator xmlGenerator =  ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.ATTRIBUTE);
                if (xmlGenerator != null) {
                    xmlGenerator.generateXML(child, ctx);
                }
            }
        }
        
        XMLBuffer.model.write ("</attribute>\n"); //$NON-NLS-1$
        ctx.getGStack().pop();
    }

    @Override
    protected String formatModifierAttributes(final Modifiers mods) {
        StringBuilder buf = new StringBuilder();
        mods.getXMLAttributeVisibility(buf);
        return buf.toString();
    }

    @Override
    protected void generateModifierXMLTags(final Modifiers mods) throws IOException {
        if (mods.isFinal()) {
            GeneratorUtils.generateTaggedValueTag("JavaFinal"); //$NON-NLS-1$
        }
        if (mods.isTransient()) {
            GeneratorUtils.generateTaggedValueTag("JavaTransient"); //$NON-NLS-1$
        }
        if (mods.isVolatile()) {
            GeneratorUtils.generateTaggedValueTag("JavaVolatile"); //$NON-NLS-1$
        }
    }

}
