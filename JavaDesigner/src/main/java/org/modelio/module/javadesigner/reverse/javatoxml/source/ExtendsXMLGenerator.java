package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.List;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Type;

class ExtendsXMLGenerator extends XMLGenerator {
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType () != JavaParser.EXTENDS_CLAUSE) {
            throw new BadNodeTypeException (Messages.getString ("reverse.Node_must_be_EXTENDS_CLAUSE"), ast); //$NON-NLS-1$
        }
        for (ASTTree type : ast.getChildrenSafe()) {
            String typeName = AstUtils.getCanonicalIdentifier(type);
            Type foundType = ctx.getTFinder().findBaseType(typeName);
            if (foundType == null) {
                // unknown type => let's go for {JavaExtends}
                generateExtendsClause(type.getSourceCode());
            } else if (TypeAstServices.isGenericType(type)) {
                // known generic type => generate a generalization with
                // {JavaBind}
                List<String> genericCode = TypeAstServices
                        .getBoundTypesSourceCode(type);
                generateExtendsClause(typeName, foundType, ctx, genericCode, AnnotationAstServices.getAnnotations(type));
            } else {
                // known non generic type => generate a generalization
                generateExtendsClause(typeName, foundType, ctx, null, AnnotationAstServices.getAnnotations(type));
            }
        }
    }

    private void generateExtendsClause(final String typeName, final Type aFoundType, final Context ctx, final List<String> genericCode, final List<ASTTree> annotations) throws IOException {
        ClassifierDef sType = (ClassifierDef) aFoundType;
        if (ctx.getGStack().peekType().getKind() == ClassifierDefKind.INTERFACE) {
            sType.setKind(ClassifierDefKind.INTERFACE);
        }
        
        XMLBuffer.model.write("<generalization>\n"); //$NON-NLS-1$
        XMLBuffer.model.write("<super-type>"); //$NON-NLS-1$
        
        GeneratorUtils.generateDestination(ctx.getIdManager().declareReferenceIdentifier(sType));
        
        XMLBuffer.model.write("</super-type>\n"); //$NON-NLS-1$
        if (typeName.contains(".")) { //$NON-NLS-1$
            // typeName is a fqn or a partial qualified name. In either case generate fqn because it is the only way to remove ambiguities
            GeneratorUtils
            .generateTaggedValueTag(JavaDesignerTagTypes.GENERALIZATION_JAVAFULLNAME); //$NON-NLS-1$
        }
        if (genericCode != null && genericCode.size() > 0) {
            GeneratorUtils.generateTaggedValueTagWithParams(
                    JavaDesignerTagTypes.GENERALIZATION_JAVABIND, genericCode);
        }
        
        this.generateAnnotationsXMLTags(annotations);
        
        XMLBuffer.model.write("</generalization>\n"); //$NON-NLS-1$
    }

    private void generateExtendsClause(final String typeSource) throws IOException {
        GeneratorUtils.generateTaggedValueTagWithParam (JavaDesignerTagTypes.CLASS_JAVAEXTENDS, typeSource); //$NON-NLS-1$
    }

}
