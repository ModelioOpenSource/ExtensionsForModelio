package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;

class CompilationUnitXMLGenerator extends XMLGenerator {
    /**
     * Generate the whole XML tags for a compilation unit
     */
    @Override
    public void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException {
        if (ast.getType() != JavaParser.COMPILATION_UNIT) {
            throw new BadNodeTypeException(Messages.getString("reverse.Node_must_be_COMPILATION_UNIT"), ast); //$NON-NLS-1$
        }
        assert ctx.getGStack().isEmpty();
        
        // 1) Generate XML tags for the package hierarchy
        ASTTree pack = (ASTTree) ast.getFirstChildWithType(JavaParser.PACKAGE_DEF);
        int packPushedCount;
        if (pack != null) {
            Pair<Integer, PackageDef> genres = generateXMLPackage((ASTTree) pack.getChild(0), null, ctx);
            packPushedCount = genres.getValue0().intValue();
            ctx.getGStack().setCurrentPackage(genres.getValue1());
        } else {
            packPushedCount = 0;
            ctx.getGStack().setCurrentPackage(null);
        }
        
        // 2) Generate XML tag for the group
        XMLBuffer.model.write("<group name=\""); //$NON-NLS-1$
        XMLBuffer.model.write(ctx.getGStack().getCurrentFile().getAbsolutePath());
        XMLBuffer.model.write("\">\n"); //$NON-NLS-1$
        
        // 3) Generate XML tags for all the children
        generateChild(ast, ctx);
        
        // 4) Close all tags in order
        XMLBuffer.model.write("</group>\n"); //$NON-NLS-1$
        
        // generate all the </package> tags
        for (int i = 0; i < packPushedCount; i++) {
            XMLBuffer.model.write("</package>\n"); //$NON-NLS-1$
        }
        
        // clear the generator stack (for next file)
        ctx.getGStack().clear();
    }

    /**
     * Generate XML tags for a package hierarchy starting at root down to a leaf
     * package Push also all the package hierarchy context in generator stack.
     * @throws IOException
     * @param packContent leaf package, the last XML tag to generate
     * @param parent parent of packContent
     */
    private Pair<Integer,PackageDef> generateXMLPackage(final ASTTree packContent, final PackageDef parent, final Context ctx) throws IOException {
        int pushCount = 0;
        PackageDef newParent = null;
        
        switch (packContent.getType()) {
        case JavaParser.IDENT:
            // retrieve the corresponding packageDef that can't be null
            PackageDef pack = ctx.getSModel().getPackage(packContent.getText(), parent);
            // generate the XML tag for it
            generatePackageTag(pack, ctx);
            // current package becomes the new parent.
            newParent = pack;
            pushCount = 1;
            break;
        case JavaParser.DOT:
            // create parent packages on the left
            Pair<Integer, PackageDef> genres = generateXMLPackage((ASTTree) packContent.getChild(0), parent, ctx);
            pushCount = genres.getValue0().intValue();
            // create packages on the right with left created packages as parent
            genres = generateXMLPackage((ASTTree) packContent.getChild(1), genres.getValue1(), ctx);
            // pushCount is the sum of pushed packages in the left side and in the right side (obviously 1 with a right recursive grammar as with ANTLR3)
            pushCount += genres.getValue0().intValue();
            // update the new parent package
            newParent = genres.getValue1();
            break;
        default:
        }
        return new Pair<>(Integer.valueOf(pushCount), newParent);
    }

    private void generatePackageTag(final PackageDef aPackage, final Context ctx) throws IOException {
        // generate package XML tag and push package on the stack
        final String id = ctx.getIdManager().defineIdentifier(aPackage);
        final String fqname = aPackage.getFullQualifiedName();
        XMLBuffer.model.write("<package id=\"" + id + "\" name=\""); //$NON-NLS-1$
        XMLBuffer.model.write(fqname);
        XMLBuffer.model.write("\">\n"); //$NON-NLS-1$
    }

    private void generateChild(final ASTTree ast, final Context ctx) throws IOException {
        boolean isHeader = true;
        
        for (ASTTree child : sortClassifiers(ast.getChildrenSafe(), ctx)) {
            switch (child.getType()) {
            case JavaParser.PACKAGE_DEF:
                // Already processed
                break;
            default:
                try {
                    /*
                     * Top level COMMENTS nodes processing depend on their
                     * position in file (header or bottom) Other node types
                     * don't care.
                     */
                    XMLGenerator xmlGenerator;
                    if (isHeader) {
                        xmlGenerator = ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.HEADER);
                        isHeader = false;
                    } else {
                        xmlGenerator = ctx.getGeneratorFactory().getXMLGenerator(child, XMLGeneratorFactory.Context.BOTTOM);
                    }
                    if (xmlGenerator != null) {
                        xmlGenerator.generateXML(child, ctx);
                    }
                } catch (XMLGeneratorException e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
                }
            }
        }
    }

    /**
     * The classifier whose name matches the file name should be the first
     * generated
     * @param children @return
     */
    private List<ASTTree> sortClassifiers(final List<? extends ASTTree> children, final Context ctx) {
        List<ASTTree> sortedChild = new ArrayList<>();
        if (!children.isEmpty()) {
            List<ASTTree> sortedClassifiers = new ArrayList<>();
            String javaFile = ctx.getGStack().getCurrentFile().getName();
            for (ASTTree child : children) {
                if (AstUtils.isGeneralClass(child)) {
                    if (javaFile.equals(AstUtils.getSimpleName(child) + ".java")) {
                        sortedClassifiers.add(0, child);
                    } else {
                        sortedClassifiers.add(child);
                    }
                } else {
                    sortedChild.add(child);
                }
            }
            // ensure that the classifiers are after IMPORT nodes
            sortedChild.addAll(sortedClassifiers);
        }
        return sortedChild;
    }

}
