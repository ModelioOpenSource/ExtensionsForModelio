package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelio.module.javadesigner.api.JavaDesignerTagTypes;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.common.WellKnownContainerServices;
import org.modelio.module.javadesigner.reverse.javatoxml.GeneratorUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;
import org.modelio.module.javadesigner.reverse.javatoxml.identification.Identified;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PrimitiveType;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Type;
import org.modelio.module.javadesigner.reverse.javautil.StringUtil;

class TypeGenerator {
    private static Map<String, String> javaWrapperMap = new HashMap<>(16, 1);

    private static Map<String, String> javaModelioPredefinedTypePackageMap = new HashMap<>(10, 1);

    /**
     * Return the XML multiplicity attribute
     * @param type
     * @param nature
     * @return
     */
    static StringBuilder getMultiplicityAttribute(final ASTTree type) {
        StringBuilder retbuf = new StringBuilder();
        String multmax = TypeAstServices.getMultiplicityMax(type);
        
        retbuf.append(" multiplicity=\"");
        retbuf.append(multmax);
        retbuf.append("\"");
        return retbuf;
    }

    /**
     * Generate XML tags for a whole type
     * @param type
     * @param sModel
     * @throws XMLGeneratorException
     * @throws IOException
     * @see AttributeXMLGenerator#generateXML(ASTTree type, StructuralModel sModel)
     * @param isAssoc : indicates if the type is generated for an assoc, in which case a qualifier might be created for GWKC with key
     */
    static void generateXMLType(final ASTTree type, final boolean isAssoc, final Context ctx) throws IOException, XMLGeneratorException {
        assert (type.getType() == JavaParser.TYPE) : "Internal error: unexpected input ast node type";
        
        ArrayList<String> typeParams = new ArrayList<>(2);
        
        // 1) is it an array ?
        int dim = TypeAstServices.getArrayDimensions(type);
        ASTTree realType = type;
        if (dim > 0) {
            // Yes, does the array have annotations ?
            if (!TypeAstServices.isArrayAnnotated(type)) {
                // No, add the keyword as the first parameter of "type" tagged value
                typeParams.add("Array");
            } else {
                // Yes, generate a sad JavaTypeExpr for the whole type
                // TODO : Think to a better reverse because declaration like
                // "String @NonNull [] message;" which means a non null array of String doesn't deserve a poor JavaTypeExpr.
                GeneratorUtils.generateTaggedValueTagWithParam("JavaTypeExpr", type.getSourceCode());
                return;
            }
        }
        
        // 2) is it a generic ?
        List<String> bindParam = null;
        if (TypeAstServices.isGenericType(realType)) {
            String realTypeName = TypeAstServices.getTypeCanonicalIdentifier(realType);
            // is it a WKC ?
            // (but not an array of WKC that are process as normal generic
            // types)
            if (WellKnownContainerServices.isWellKnownContainer(realTypeName) && dim == 0) {
                // It is a generic well known container (GWKC)
                ASTTree wkcType = realType;
                String wkcTypeName = realTypeName;
        
                // Get the bind type from the real destination in the GWKC
                ASTTree destType = WellKnownContainerServicesWithAST.getRealDestinationType(wkcType);
                ASTTree keyType = WellKnownContainerServicesWithAST.getQualifierType(wkcType);
        
                if (!TypeAstServices.isUMLMappable(destType)) {
                    // NON UML mappable type : the real type is the container, everything
                    // else goes to {JavaBind}
                    realType = wkcType;
                    bindParam = TypeAstServices.getBoundTypesSourceCode(wkcType);
                } else {
                    // UML mappable : the wkc goes into {type}, ditto for the
                    // key and the realType is the destination
                    // Add WKC name to type parameter
                    realType = destType;
                    typeParams.add(wkcTypeName);
                    if (keyType != null) {
                        if (isAssoc) {
                            // assoc, the key should be generated as a
                            // qualifier for the key named "1"
                            generateXMLForAssociationQualifier(keyType, "1", ctx);
                        } else {
                            //  the key should go into {type}
                            typeParams.add(keyType.getSourceCode());
                        }
                    }
                    // if the destination type is generic, the generic parameters goes into {JavaBind}
                    if (TypeAstServices.isGenericType(destType)) {
                        bindParam = TypeAstServices.getBoundTypesSourceCode(destType);
                    }
                }
            } else {
                // generic but not a WKC : bound type(s) goes into {JavaBind}
                bindParam = TypeAstServices.getBoundTypesSourceCode(realType);
            }
        }
        // 3) generate XML tags for array or generic
        if (!typeParams.isEmpty()) {
            GeneratorUtils.generateTaggedValueTagWithParams("type", typeParams);
            if (dim > 1) {
                // Store the number of dimensions of the array if greater than 1
                GeneratorUtils.generateTaggedValueTagWithParam("JavaArrayDimension", String.valueOf(dim));
            }
        }
        if (bindParam != null) {
            GeneratorUtils.generateTaggedValueTagWithParams("JavaBind", bindParam);
        }
        
        // 4) generate the tags for the real type
        if (realType == null) {
            // The real type is in {JavaBind}, generate 'null' for the base type
            TypeGenerator.generateBaseType("null");
        } else {
            // generate the real type
            String realTypeName = TypeAstServices.getTypeCanonicalIdentifier(realType);
            TypeGenerator.generateType(realTypeName, ctx);
        }
    }

    /**
     * Generate XML tags for an association qualifier : an attribute tag.
     * @param qualType
     * @param qualName
     * @param sModel
     * @throws IOException
     * @throws XMLGeneratorException
     */
    private static void generateXMLForAssociationQualifier(final ASTTree qualType, final String qualName, final Context ctx) throws IOException, XMLGeneratorException {
        XMLBuffer.model.write("<attribute name=\"");
        XMLBuffer.model.write(qualName);
        XMLBuffer.model.write("\" >");
        if (TypeAstServices.isWildCard(qualType)) {
            // The wildcard goes to the {type} tagged value (Yes it's weird)
            GeneratorUtils.generateTaggedValueTagWithParam("type", qualType.getSourceCode());
        } else {
            for (ASTTree annotation : AnnotationAstServices.getAnnotations(qualType)) {
                GeneratorUtils.generateNoteTag("JavaAnnotation", annotation.getSourceCode());
            }
            TypeGenerator.generateXMLType(qualType, false, ctx);
        }
        XMLBuffer.model.write("</attribute>");
    }

    /**
     * Generate the final XML tag for a type
     * @param typeName
     * @param foundTypes
     * @param sModel
     * @throws IOException
     * @throws NameCollisionException
     */
    static void generateType(final String typeName, final Context ctx) throws IOException, NameCollisionException {
        Type type = ctx.getTFinder().findType(typeName);
        
        if (type != null) {
            if (type instanceof PrimitiveType) {
                // Java primitive
                TypeGenerator.generateBaseType(typeName);
            } else {
                String fqntype = type.getFullQualifiedName();
                if (isJavaNonPrimitiveModelioPredefinedType(fqntype)) {
                    // Non Java primitive but Modelio predefined type
                    TypeGenerator.generateCondJavaFullName(typeName);
                    TypeGenerator.generateBaseType(type.getTypeName());
                } else if (isJavaWrapper(fqntype)) {
                    GeneratorUtils.generateTaggedValueTag(JavaDesignerTagTypes.ATTRIBUTE_JAVAWRAPPER);
                    TypeGenerator.generateCondJavaFullName(typeName);
                    TypeGenerator.generateBaseType(getJavaTypeFromWrapper(typeName));
                } else {
                    // Other found type
                    assert type instanceof Identified;
                    XMLBuffer.model.write("<class-type>"); //$NON-NLS-1$
        
                    GeneratorUtils.generateDestination(ctx.getIdManager().declareReferenceIdentifier((Identified)type));
        
                    XMLBuffer.model.write("</class-type>\n"); //$NON-NLS-1$
                    TypeGenerator.generateCondJavaFullName(typeName);
                }
            }
        } else {
            // No type found for typeName : generate a {JavaTypeExpr}
            GeneratorUtils.generateTaggedValueTagWithParam("JavaTypeExpr", typeName); //$NON-NLS-1$
        }
    }

    /**
     * Generate XML {JavaFullName} tag if typeName is a full name
     * @throws IOException
     * @param typeName the type name to check for full name
     */
    static void generateCondJavaFullName(final String typeName) throws IOException {
        if (typeName.contains(".")) {
            GeneratorUtils.generateTaggedValueTag(JavaDesignerTagTypes.ATTRIBUTE_JAVAFULLNAME);
        }
    }

    /**
     * Generate XML base-type tag.
     * @throws IOException
     * @param baseType The name of the base type as it should appear in the xml tag
     */
    static void generateBaseType(final String baseType) throws IOException {
        XMLBuffer.model.write("<base-type>"); //$NON-NLS-1$
        XMLBuffer.model.write(baseType);
        XMLBuffer.model.write("</base-type>\n"); //$NON-NLS-1$
    }

    /**
     * return the Java primitive type associated with a wrapper type
     * @param str The wrapper type. it could be a simple name or a fully
     * qualified name (Integer or java.lang.Integer)
     * @return The corresponding primitive type (int for Integer)
     */
    private static String getJavaTypeFromWrapper(final String str) {
        return javaWrapperMap.get(str);
    }

    /**
     * Indicates if a string is a Java wrapper. Handles both short names
     * ("Integer") and full qualified names ("java.lang.Integer")
     * @param str string to check against Java wrappers
     * @return True if str is a Java wrapper
     */
    static boolean isJavaWrapper(final String str) {
        for (String jwrap : javaWrapperMap.keySet()) {
            if (jwrap.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is 'str' a Java type of a Modelio predefined type. Those types are :
     * boolean, int, float, long, short, double, byte, char, String and Date,
     * including the full name version of String and Date (ie java.lang.String)
     * @param str The string to check
     * @return True if str is a type of a Modelio predefined type.
     */
    static boolean isJavaModelioPredefinedType(final String str) {
        return isJavaPrimitiveType(str) || isJavaNonPrimitiveModelioPredefinedType(str);
    }

    /**
     * Is 'str' a Java primitive type. Java primitive types are : boolean, int,
     * float, long, short, double, byte and char.
     * @param str The string to check
     * @return True if str is a Java primitive type.
     */
    static boolean isJavaPrimitiveType(final String str) {
        return str.equals("boolean") || str.equals("int") || str.equals("float") || str.equals("long")
                                || str.equals("short") || str.equals("double") || str.equals("byte") || str.equals("char");
    }

    /**
     * Is 'str' a Modelio predefined type which is not a Java primitive type.
     * Those types are : String and Date including the full name version (ie
     * java.lang.String)
     * @param str The string to check
     * @return True if str is a type of a Modelio predefined type which is not a
     * Java primitive type.
     */
    static boolean isJavaNonPrimitiveModelioPredefinedType(final String str) {
        return str.equals("String") || str.equals("Date") || str.equals("java.lang.String") || str.equals("java.util.Date");
    }

    /**
     * Return the Java package of a non primitive Java type that is a Modelio
     * predefined type or a wrapper type
     * @param str The type. Can be either a simple type or a full name type
     * @return The package of
     */
    static String getPackageOfAModelioPredefinedType(final String str) {
        String shortname = StringUtil.shortenFullName(str);
        return javaModelioPredefinedTypePackageMap.get(shortname);
    }


static {
        javaWrapperMap.put("Integer", "int");
        javaWrapperMap.put("Boolean", "boolean");
        javaWrapperMap.put("Float", "float");
        javaWrapperMap.put("Double", "double");
        javaWrapperMap.put("Short", "short");
        javaWrapperMap.put("Long", "long");
        javaWrapperMap.put("Character", "char");
        javaWrapperMap.put("Byte", "byte");

        javaWrapperMap.put("java.lang.Integer", "int");
        javaWrapperMap.put("java.lang.Boolean", "boolean");
        javaWrapperMap.put("java.lang.Float", "float");
        javaWrapperMap.put("java.lang.Double", "double");
        javaWrapperMap.put("java.lang.Short", "short");
        javaWrapperMap.put("java.lang.Long", "long");
        javaWrapperMap.put("java.lang.Character", "char");
        javaWrapperMap.put("java.lang.Byte", "byte");

        javaModelioPredefinedTypePackageMap.put("String", "java.lang");
        javaModelioPredefinedTypePackageMap.put("Date", "java.util");
        javaModelioPredefinedTypePackageMap.put("Integer", "java.util");
        javaModelioPredefinedTypePackageMap.put("Boolean", "java.util");
        javaModelioPredefinedTypePackageMap.put("Float", "java.util");
        javaModelioPredefinedTypePackageMap.put("Double", "java.util");
        javaModelioPredefinedTypePackageMap.put("Short", "java.util");
        javaModelioPredefinedTypePackageMap.put("Long", "java.util");
        javaModelioPredefinedTypePackageMap.put("Character", "java.util");
        javaModelioPredefinedTypePackageMap.put("Byte", "java.util");
    }
}
