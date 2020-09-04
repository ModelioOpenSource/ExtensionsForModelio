package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.util.HashMap;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

public class XMLGeneratorFactory {
    private HashMap<String, XMLGenerator> generators = new HashMap<> ();

    public XMLGenerator getXMLGenerator(final ASTTree ast, final Context context) throws XMLGeneratorException {
        XMLGenerator xmlGenerator = null;
        
        switch (ast.getType ()) {
        case JavaParser.COMMENTS:
            if (context == Context.HEADER) {
                xmlGenerator = this.generators.get ("HeaderCommentXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new HeaderCommentXMLGenerator ();
                    this.generators.put ("HeaderCommentXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            } else if (context == Context.BOTTOM) {
                xmlGenerator = this.generators.get ("BottomCommentXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new BottomCommentXMLGenerator ();
                    this.generators.put ("BottomCommentXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            } else if (context == Context.CLASS || context == Context.ENUM || context == Context.INTERFACE) {
                xmlGenerator = this.generators.get ("CommentXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new CommentXMLGenerator ();
                    this.generators.put ("CommentXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            } else if (context == Context.MEMBER) {
                xmlGenerator = this.generators.get ("MemberCommentXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new MemberCommentXMLGenerator ();
                    this.generators.put ("MemberCommentXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            } else if (context == Context.ATTRIBUTE ||
                    context == Context.OPERATION) {
                xmlGenerator = this.generators.get ("CommentXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new CommentXMLGenerator ();
                    this.generators.put ("CommentXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            }
            return xmlGenerator;
        
        case JavaParser.PACKAGE_DEF:
            xmlGenerator = this.generators.get ("CompilationUnitXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new CompilationUnitXMLGenerator ();
                this.generators.put ("CompilationUnitXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.IMPORT:
            xmlGenerator = this.generators.get ("ImportXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ImportXMLGenerator ();
                this.generators.put ("ImportXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.CLASS_DEF:
            xmlGenerator = this.generators.get ("ClassXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ClassXMLGenerator ();
                this.generators.put ("ClassXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.INTERFACE_DEF:
            xmlGenerator = this.generators.get ("InterfaceXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new InterfaceXMLGenerator ();
                this.generators.put ("InterfaceXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.ENUM_DEF:
            xmlGenerator = this.generators.get ("EnumXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new EnumXMLGenerator ();
                this.generators.put ("EnumXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.ANNOTATION_DEF:
            xmlGenerator = this.generators.get ("AnnotationDefinitionXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new AnnotationDefinitionXMLGenerator ();
                this.generators.put ("AnnotationDefinitionXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.EXTENDS_CLAUSE:
            xmlGenerator = this.generators.get ("ExtendsXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ExtendsXMLGenerator ();
                this.generators.put ("ExtendsXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.IMPLEMENTS_CLAUSE:
            xmlGenerator = this.generators.get ("ImplementsXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ImplementsXMLGenerator ();
                this.generators.put ("ImplementsXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.OBJBLOCK:
            if (context == Context.ENUM) {
                xmlGenerator = this.generators.get ("EnumMemberXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new EnumMemberXMLGenerator ();
                    this.generators.put ("EnumMemberXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            } else {
                xmlGenerator = this.generators.get ("ClassMemberXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new ClassMemberXMLGenerator ();
                    this.generators.put ("ClassMemberXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            }
            return xmlGenerator;
        
        case JavaParser.VARIABLE_DEF:
            xmlGenerator = this.generators.get ("AttributeXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new AttributeXMLGenerator ();
                this.generators.put ("AttributeXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.ANNOTATION_MEMBER_DEF:
            xmlGenerator = this.generators.get ("AnnotationMemberXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new AnnotationMemberXMLGenerator ();
                this.generators.put ("AnnotationMemberXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
        
            return xmlGenerator;
        
        case JavaParser.METHOD_DEF:
            xmlGenerator = this.generators.get("OperationXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new OperationXMLGenerator();
                this.generators.put("OperationXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.STATIC_INIT:
            xmlGenerator = this.generators.get ("StaticXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new StaticXMLGenerator ();
                this.generators.put ("StaticXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.INSTANCE_INIT:
            xmlGenerator = this.generators.get ("InstanceXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new InstanceXMLGenerator ();
                this.generators.put ("InstanceXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.TYPE:
            if (context == Context.OPERATION) {
                xmlGenerator = this.generators.get ("ReturnParameterXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new ReturnParameterXMLGenerator ();
                    this.generators.put ("ReturnParameterXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            }
            return xmlGenerator;
        
        case JavaParser.ASSIGN:
            xmlGenerator = this.generators.get ("AssignXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new AssignXMLGenerator ();
                this.generators.put ("AssignXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.PARAMETERS:
            xmlGenerator = this.generators.get ("ParameterXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ParameterXMLGenerator ();
                this.generators.put ("ParameterXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.THROWS:
            xmlGenerator = this.generators.get ("ThrowsXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ThrowsXMLGenerator ();
                this.generators.put ("ThrowsXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.SLIST:
            if (context == Context.OPERATION) {
                xmlGenerator = this.generators.get ("OperationBodyXMLGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new OperationBodyXMLGenerator ();
                    this.generators.put ("OperationBodyXMLGenerator", xmlGenerator); //$NON-NLS-1$
                }
            }
            return xmlGenerator;
        
        case JavaParser.RETURN:
            xmlGenerator = this.generators.get ("ReturnStatementXMLGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new ReturnStatementXMLGenerator ();
                this.generators.put ("ReturnStatementXMLGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        case JavaParser.FORMAL_TYPE_PARAMS:
            if (context == Context.OPERATION) {
                xmlGenerator = this.generators.get ("OperationTemplateParameterGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new OperationTemplateParameterXMLGenerator ();
                    this.generators.put ("OperationTemplateParameterGenerator", xmlGenerator); //$NON-NLS-1$
                }
            } else {
                xmlGenerator = this.generators.get ("ClassTemplateParameterGenerator"); //$NON-NLS-1$
                if (xmlGenerator == null) {
                    xmlGenerator = new ClassTemplateParameterXMLGenerator ();
                    this.generators.put ("ClassTemplateParameterGenerator", xmlGenerator); //$NON-NLS-1$
                }
        
            }
            return xmlGenerator;
        
        case JavaParser.ENUM_CONST:
            xmlGenerator = this.generators.get ("EnumerationConstGenerator"); //$NON-NLS-1$
            if (xmlGenerator == null) {
                xmlGenerator = new EnumerationConstXMLGenerator ();
                this.generators.put ("EnumerationConstGenerator", xmlGenerator); //$NON-NLS-1$
            }
            return xmlGenerator;
        
        default:
            throw new BadNodeTypeException (ast);
        }
    }

    /**
     * Current AST context. Useful (?) to help XMLGeneratorFactory to determine the proper XML generator
     */
    public enum Context {
        HEADER,
        BOTTOM,
        CLASS,
        INTERFACE,
        MEMBER,
        ATTRIBUTE,
        OPERATION,
        PARAMETER,
        ENUM;
    }

}
