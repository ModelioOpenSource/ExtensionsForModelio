package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javautil.XMLStringWriter;

public abstract class XMLGenerator {
    public static final String ANNOTATION_PARAMETER_SEPARATOR = "#";

    public abstract void generateXML(final ASTTree ast, final Context ctx) throws IOException, XMLGeneratorException;

    protected void generateAnnotationsXMLTags(final List<ASTTree> annotations) throws IOException {
        for (ASTTree annotation : annotations) {
            String stereotypeName = AstUtils.getSimpleName(annotation);
            Map<String, String> parameters = new HashMap<>();
        
            ASTTree parameterList = (ASTTree) annotation.getFirstChildWithType(JavaParser.ANNOTATION_INIT_LIST);
            if (parameterList != null) {
                // for example @GeneratedValue(strategy = GenerationType.IDENTITY)
                for (ASTTree astTree : parameterList.getAllChildrenWithType(JavaParser.ANNOTATION_INIT_MEMBER)) {
                    ASTTree ident = (ASTTree) astTree.getFirstChildWithType(JavaParser.IDENT);
                    if (ident  != null) {
                        ASTTree value = (ASTTree) astTree.getChild(1);
                        if (value != null) {
                            parameters.put(ident.getText(), value.getSourceCode());
                        } else {
                            parameters.put(ident.getText(), null);
                        }
                    }
                }
            } else {
                // for example @SuppressWarnings("restriction")
                ASTTree initValue = (ASTTree) annotation.getFirstChildWithType(JavaParser.ANNOTATION_INIT_VALUE);
                if (initValue != null) {
                    ASTTree value = (ASTTree) initValue.getFirstChildWithType(JavaParser.EXPR);
                    if (value != null) {
                        // It is permissible to omit the element name and equals sign (=) in a single-element annotation whose element name is value
                        parameters.put("value", value.getSourceCode());
                    }
                }
            }
        
            generateAnnotation(annotation.getSourceCode(), stereotypeName, parameters);
        }
    }

    public static void generateAnnotation(final String content, final String stereotypeName, final Map<String, String> parameters) throws IOException {
        // Add note "JavaAnnotation"
        XMLBuffer.model.write ("<note note-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write ("JavaAnnotation");
        XMLBuffer.model.write ("\">\n"); //$NON-NLS-1$
        XMLStringWriter xw = new XMLStringWriter ();
        XMLBuffer.model.write ("<content><![CDATA["); //$NON-NLS-1$
        XMLBuffer.model.write (xw.encodedata (content.toString ()));
        XMLBuffer.model.write ("]" + "]></content>\n"); //$NON-NLS-1$
        
        // Add stereotype
        XMLBuffer.model.write ("<stereotype stereotype-type=\""); //$NON-NLS-1$
        XMLBuffer.model.write (stereotypeName);
        XMLBuffer.model.write ("\"/>\n"); //$NON-NLS-1$
        
        // Add tag parameters
        for (Entry<String, String> parameter: parameters.entrySet()) {
            String key = stereotypeName + ANNOTATION_PARAMETER_SEPARATOR + parameter.getKey();
            String value = parameter.getValue();
            if (value != null) {
                GeneratorUtils.generateTaggedValueTagWithParam(key, value);
            } else {
                GeneratorUtils.generateTaggedValueTag(key);
            }
        }
        
        // Close note
        XMLBuffer.model.write ("</note>\n"); //$NON-NLS-1$
    }

}
