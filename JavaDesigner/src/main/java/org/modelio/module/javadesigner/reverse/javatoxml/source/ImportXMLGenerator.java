package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import org.modelio.module.javadesigner.api.JavaDesignerStereotypes;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;

class ImportXMLGenerator extends XMLGenerator {
     static final String JavaDesignerAnnotationsPackage = "com.modeliosoft.modelio.javadesigner.annotations.";

    /**
     * (non-Javadoc)
     * @see org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGenerator#generateXML
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType () != JavaParser.IMPORT) {
            throw new BadNodeTypeException (Messages.getString ("reverse.Node_must_be_IMPORT"), ast); //$NON-NLS-1$
        }
        
        StringBuilder commentBuffer = new StringBuilder ();
        
        boolean isStatic = false;
        boolean isStarImport = false;
        for (ASTTree childImport : ast.getChildrenSafe()) {
            if (childImport.getType () == JavaParser.COMMENTS) {
                commentBuffer.append (getCommentsDef (childImport, true));
            } else if (childImport.getType () == JavaParser.STATIC) {
                // this import is a static import. We keep the information,
                // for the generation of the tagged value at the end of the
                // method.
                // Rq : import static doesn't apply to packages and requires to point at fields either explicitly (sqrt) or as
                // a whole (*)
                isStatic = true;
            } else {
                // child = Import name
                String importStr = AstUtils.getCanonicalIdentifier(childImport);
                // Skip Java Designer own annotations
                if (!importStr.startsWith(JavaDesignerAnnotationsPackage)) {
                    String typeName;
                    if (importStr.endsWith (".*")) {
                        // Package import
                        typeName = importStr.substring (0, importStr.length () - 2);
                        isStarImport = true;
                    } else {
                        typeName = importStr;
                    }
                    StructuralTree importNs = ctx.getSModel().findStructuralTree(typeName);
                    if (importNs != null) {
                        ctx.getTFinder().registerImport(importNs, isStarImport);
                        if (importNs instanceof PackageDef) {
                            generatePackageImport (ctx, (PackageDef)importNs, commentBuffer);
                        } else if (importNs instanceof ClassifierDef) {
                            // 3 cases here :
                            // import org.acme.SimpleClass;
                            // import static org.acme.SomeEnumOrMathClass.*;
                            // The first case is the main classic case
                            // In the second case, generate an import with a JAVASTATIC stereotype
                            // while in the third case no JAVASTAR exists so generate an import tag
                            if (isStarImport && !isStatic) {
                                generateJavaImportTag (importStr, commentBuffer, isStatic);
                            } else {
                                generateClassImport (ctx, (ClassifierDef)importNs, commentBuffer, isStatic);
                            }
                        } else {
                            // type mismatch : a strutural type is expected but another type found
                            // TODO better error handling
                            throw new XMLGeneratorException(importNs.getFullQualifiedName() + " is expected to be a package or a classifier.");
                        }
                    } else {
                        // In the case :
                        // import static com.acme.util.Math.mySqrt
                        // mySqrt is neither a package nor a class so StructuralModel can't handle it and the import is generated as
                        // an import tag.
                        generateJavaImportTag (importStr, commentBuffer, isStatic);
                    }
                }
            }
        }
    }

    private void generatePackageImport(final Context ctx, final PackageDef importNs, final StringBuilder commentBuffer) throws IOException {
        XMLBuffer.model.write ("<package-import>\n"); //$NON-NLS-1$
        XMLBuffer.model.write ("<used-package>\n"); //$NON-NLS-1$
        
        GeneratorUtils.generateDestination(ctx.getIdManager().declareReferenceIdentifier(importNs));
        
        XMLBuffer.model.write ("</used-package>\n"); //$NON-NLS-1$
        XMLBuffer.model.write (commentBuffer.toString ());
        XMLBuffer.model.write ("</package-import>\n"); //$NON-NLS-1$
    }

    private void generateClassImport(final Context ctx, final ClassifierDef elt, final StringBuilder commentBuffer, final boolean isStatic) throws IOException {
        XMLBuffer.model.write ("<element-import>\n"); //$NON-NLS-1$
        XMLBuffer.model.write ("<used-class>\n"); //$NON-NLS-1$
        
        GeneratorUtils.generateDestination(ctx.getIdManager().declareReferenceIdentifier(elt));
        
        XMLBuffer.model.write ("</used-class>\n"); //$NON-NLS-1$
        XMLBuffer.model.write (commentBuffer.toString ());
        if (isStatic) {
            GeneratorUtils.generateStereotypeTag (JavaDesignerStereotypes.JAVASTATIC);
        }
        XMLBuffer.model.write ("</element-import>\n"); //$NON-NLS-1$
    }

    private void generateJavaImportTag(final String importStr, final StringBuilder commentBuffer, final boolean isStatic) throws IOException {
        // TODO : generate something for commentBuffer
        if (isStatic) {
            GeneratorUtils.generateTaggedValueTagWithParam ("JavaImport", "static " +
                    importStr);
        } else {
            GeneratorUtils.generateTaggedValueTagWithParam ("JavaImport", importStr);
            XMLBuffer.model.write (commentBuffer.toString ());
        }
    }

    private StringBuilder getCommentDef(final ASTTree child, final boolean returnWithXML) throws BadNodeTypeException {
        if (child.getType () != JavaParser.COMMENT &&
                child.getType () != JavaParser.JAVADOC) {
            throw new BadNodeTypeException (Messages.getString ("reverse.Node_must_be_COMMENT_or_JAVADOC_COMMENT")); //$NON-NLS-1$
        }
        StringBuilder sBuffer = new StringBuilder ();
        if (returnWithXML) {
            StringBuilder content = new StringBuilder (child.getText ());
            sBuffer.append (GeneratorUtils.getNoteTag ("description", content)); //$NON-NLS-1$
        } else {
            sBuffer.append (child.getText () + "\n"); //$NON-NLS-1$
        }
        return sBuffer;
    }

    private StringBuilder getCommentsDef(final ASTTree ast, final boolean returnWithXML) throws BadNodeTypeException {
        if (ast.getType () != JavaParser.COMMENTS) {
            throw new BadNodeTypeException (Messages.getString ("reverse.Node_must_be_COMMENTS")); //$NON-NLS-1$
        }
        
        StringBuilder sBuffer = new StringBuilder ();
        for (ASTTree child : ast.getChildrenSafe()) {
            sBuffer.append (getCommentDef (child, returnWithXML));
        }
        return sBuffer;
    }

}
