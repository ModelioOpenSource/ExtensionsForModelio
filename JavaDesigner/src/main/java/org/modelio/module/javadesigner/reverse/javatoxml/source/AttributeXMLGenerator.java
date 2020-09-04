package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.List;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.common.WellKnownContainerServices;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.Modifiers;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassTemplateParameter;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.SimpleSymbol;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Type;

/**
 * Generate XML syntax for an attribute definition
 */
class AttributeXMLGenerator extends XMLGeneratorWithModifiers {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.VARIABLE_DEF) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_VARIABLE_DEF"), ast); //$NON-NLS-1$
        }
        
        // Get the type of the attribute
        ASTTree type = (ASTTree) ast.getFirstChildWithType(JavaParser.TYPE);
        if (type == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.Can_not_find_TYPE_node")); //$NON-NLS-1$
        }
        
        // Process attribute name
        ASTTree IDENTChild = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT);
        if (IDENTChild == null) {
            throw new XMLGeneratorException(Messages.getString("reverse.Attribute_name_not_found")); //$NON-NLS-1$
        }
        
        String eltName = IDENTChild.getText();
        ctx.getGStack().push(new SimpleSymbol(eltName));
        // TODO optionally register an id for the report system
        
        Nature nature = getUMLPropertyNature(type, ctx);
        
        XMLBuffer.model.write("<"); //$NON-NLS-1$
        XMLBuffer.model.write(nature.getXmlTag());
        XMLBuffer.model.write(" name=\""); //$NON-NLS-1$
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
        
        // Get the multiplicity attributes according to the type of the
        // attribute and its nature
        XMLBuffer.model.write(this.getMultiplicityAttribute(type, nature).toString());
        
        XMLBuffer.model.write(">\n"); //$NON-NLS-1$
        
        // Process modifiers translated as XML tags
        this.generateModifierXMLTags(mod);
        
        // add the annotations found before the type
        // TODO : discriminate between annotations on the field (those before the modifiers) and those before the type of the field
        //        Afaik Java 8 doesn't make semantic difference between them but code writers do !
        //        maybe add a JavaAnnotationTypeUse note type on Attribute and AssociationEnd to process type use association
        annotations.addAll(AnnotationAstServices.getAnnotations(type));
        
        // Generate remaining annotations
        this.generateAnnotationsXMLTags(annotations);
        
        // Generate the type part
        TypeGenerator.generateXMLType(type, (nature == Nature.ASSOCIATION_END), ctx);
        
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
        
        XMLBuffer.model.write("</"); //$NON-NLS-1$
        XMLBuffer.model.write(nature.getXmlTag());
        XMLBuffer.model.write(">\n"); //$NON-NLS-1$
        ctx.getGStack().pop();
    }

    /**
     * @throws XMLGeneratorException
     * @throws IOException
     * @param typeNode @return
     */
    private Nature getUMLPropertyNature(final ASTTree typeNode, final Context ctx) throws IOException, XMLGeneratorException {
        ASTTree realTypeNode = typeNode;
        boolean validGWKC = false;
        if (TypeAstServices.isGenericType(realTypeNode)) {
            // Look for the real destination type assuming that
            // typeNode is a well known generic container (WKC)
            ASTTree tmpNode = WellKnownContainerServicesWithAST.getRealDestinationType(realTypeNode);
            if (tmpNode != null) {
                validGWKC = true;
                realTypeNode = tmpNode;
            }
        }
        // Check if the real type is a predefined type
        String typeName = TypeAstServices.getTypeCanonicalIdentifier(realTypeNode);
        Nature type = Nature.ASSOCIATION_END; // by default
        // Check for basic type.
        // The test on non generic WKC is for Java < 1.5 for which an attribute should be generated.
        if (TypeGenerator.isJavaModelioPredefinedType(typeName) || TypeGenerator.isJavaWrapper(typeName)
                || (!validGWKC && WellKnownContainerServices.isWellKnownContainer(typeName))) {
            // Yes it is, generate an attribute
            type = Nature.ATTRIBUTE;
        } else {
           // refines further based on the type
           Type typ = ctx.getTFinder().findType(typeName);
            // If the type is unknown, an attribute should also be generated
           if (typ == null) {
               type = Nature.ATTRIBUTE;
           } else if (typ instanceof ClassifierDef) {
               ClassifierDef cd = (ClassifierDef)typ;
               if (cd.getKind() == ClassifierDefKind.ENUMERATION || cd.getKind() == ClassifierDefKind.DATATYPE) {
                   type = Nature.ATTRIBUTE;
               }
           } else if (typ instanceof ClassTemplateParameter) {
               type = Nature.ATTRIBUTE;
           }           
        }
        return type;
    }

    @Override
    protected String formatModifierAttributes(final Modifiers mods) {
        StringBuilder buf = new StringBuilder();
        mods.getXMLAttributeVisibility(buf);
        buf.append(" is-class=\"").append(mods.isStatic()).append("\"");
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

    /**
     * Return the multiplicity attribute
     * @param type
     * @param nature @return
     */
    private StringBuilder getMultiplicityAttribute(final ASTTree type, final Nature nature) {
        StringBuilder retbuf = null;
        
        if (nature == Nature.ASSOCIATION_END) {
            retbuf = new StringBuilder();
            retbuf.append(" multiplicity-min=\"0\"");
            retbuf.append(" multiplicity-max=\"").append(TypeAstServices.getMultiplicityMax(type)).append("\"");
        } else {
            retbuf = TypeGenerator.getMultiplicityAttribute(type);
        }
        return retbuf;
    }

    private enum Nature {
        ATTRIBUTE ("attribute"),
        ASSOCIATION_END ("association-end");

         String tag;

        private Nature(final String tag) {
            this.tag = tag;
        }

        String getXmlTag() {
            return this.tag;
        }

    }

}
