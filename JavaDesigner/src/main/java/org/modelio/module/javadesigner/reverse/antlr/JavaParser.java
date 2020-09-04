// $ANTLR 3.5.2 com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g 2014-10-16 11:23:31
package org.modelio.module.javadesigner.reverse.antlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.antlr.runtime.*;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.*;
import org.antlr.runtime.tree.TreeAdaptor;

/**
 * Java 1.8 Recognizer
 * Contributing authors:
 * John Mitchell     johnm@non.net
 * Terence Parr      parrt@magelang.com
 * John Lilley       jlilley@empathy.com
 * Scott Stanchfield thetick@magelang.com
 * Markus Mohnen     mohnen@informatik.rwth-aachen.de
 * Peter Williams    pete.williams@sun.com
 * Allan Jacobs      Allan.Jacobs@eng.sun.com
 * Steve Messick     messick@redhills.com
 * John Pybus        john@pybus.org
 * Modelio development team
 * This grammar is in the PUBLIC DOMAIN
 * 
 * jdk8 :
 * - type annotations : http://java.dzone.com/articles/java-8-type-annotations
 */
@SuppressWarnings("all")
public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ANNOTATION", "ANNOTATION_ARRAY_INIT", 
		"ANNOTATION_DEF", "ANNOTATION_DEFAULT", "ANNOTATION_INIT_LIST", "ANNOTATION_INIT_MEMBER", 
		"ANNOTATION_INIT_VALUE", "ANNOTATION_MEMBER_DEF", "ARRAY_DECLARATOR", 
		"ARRAY_INIT", "ASSIGN", "AT", "BAND", "BAND_ASSIGN", "BIN_DIGIT", "BIN_NUM", 
		"BNOT", "BOR", "BOR_ASSIGN", "BSR", "BSR_ASSIGN", "BUILT_IN", "BXOR", 
		"BXOR_ASSIGN", "CASE_GROUP", "CHAR_LITERAL", "CLASS_DEF", "COLON", "COMMA", 
		"COMMENT", "COMMENTS", "COMPILATION_UNIT", "CTOR_CALL", "DEC", "DEC_DIGIT", 
		"DEC_END", "DEC_NUM", "DEFAULT", "DIV", "DIV_ASSIGN", "DOT", "ELIST", 
		"ELLIPSIS", "EMPTY_STAT", "ENUM_CONST", "ENUM_DEF", "EQUAL", "ESC", "EXPONENT", 
		"EXPR", "EXTENDS_CLAUSE", "FLOAT_SUFFIX", "FORMAL_TYPE_PARAMS", "FOR_CONDITION", 
		"FOR_INIT", "FOR_ITERATOR", "GE", "GENERIC_EXTENDS", "GT", "HEX_DIGIT", 
		"HEX_NUM", "IDENT", "IMPLEMENTS_CLAUSE", "IMPORT", "INC", "INSTANCE_INIT", 
		"INTERFACE_DEF", "JAVADOC", "LABELED_STAT", "LAMBDA", "LAND", "LBRACK", 
		"LCURLY", "LE", "LNOT", "LONG_SUFFIX", "LOR", "LPAREN", "LT", "METHOD_CALL", 
		"METHOD_DEF", "MINUS", "MINUS_ASSIGN", "MOD", "MODIFIERS", "MOD_ASSIGN", 
		"NEW", "NL", "NOT_EQUAL", "NUM_LITERAL", "OBJBLOCK", "PACKAGE_DEF", "PARAMETERS", 
		"PARAMETER_DEF", "PLUS", "PLUS_ASSIGN", "QUESTION", "RBRACK", "RCURLY", 
		"REF", "RETURN", "RPAREN", "SEMI", "SL", "SLIST", "SL_ASSIGN", "SR", "SR_ASSIGN", 
		"STAR", "STAR_ASSIGN", "STATIC", "STATIC_INIT", "STRING_LITERAL", "SUPER", 
		"SUPER_CTOR_CALL", "THROWN_EXCEPTION", "THROWS", "TRAILING_COMMENT", "TYPE", 
		"TYPECAST", "TYPE_ARGS", "TYPE_PARAM", "TYPE_PARAMS", "UNARY_MINUS", "UNARY_PLUS", 
		"VARIABLE_DEF", "VOID", "WILDCARD", "WS", "'abstract'", "'assert'", "'boolean'", 
		"'break'", "'byte'", "'case'", "'catch'", "'char'", "'class'", "'continue'", 
		"'do'", "'double'", "'else'", "'enum'", "'extends'", "'false'", "'final'", 
		"'finally'", "'float'", "'for'", "'if'", "'implements'", "'import'", "'instanceof'", 
		"'int'", "'interface'", "'long'", "'native'", "'null'", "'package'", "'private'", 
		"'protected'", "'public'", "'short'", "'static'", "'strictfp'", "'switch'", 
		"'synchronized'", "'this'", "'threadsafe'", "'throw'", "'throws'", "'transient'", 
		"'true'", "'try'", "'void'", "'volatile'", "'while'"
	};

    public static final int EOF = -1;

    public static final int T__133 = 133;

    public static final int T__134 = 134;

    public static final int T__135 = 135;

    public static final int T__136 = 136;

    public static final int T__137 = 137;

    public static final int T__138 = 138;

    public static final int T__139 = 139;

    public static final int T__140 = 140;

    public static final int T__141 = 141;

    public static final int T__142 = 142;

    public static final int T__143 = 143;

    public static final int T__144 = 144;

    public static final int T__145 = 145;

    public static final int T__146 = 146;

    public static final int T__147 = 147;

    public static final int T__148 = 148;

    public static final int T__149 = 149;

    public static final int T__150 = 150;

    public static final int T__151 = 151;

    public static final int T__152 = 152;

    public static final int T__153 = 153;

    public static final int T__154 = 154;

    public static final int T__155 = 155;

    public static final int T__156 = 156;

    public static final int T__157 = 157;

    public static final int T__158 = 158;

    public static final int T__159 = 159;

    public static final int T__160 = 160;

    public static final int T__161 = 161;

    public static final int T__162 = 162;

    public static final int T__163 = 163;

    public static final int T__164 = 164;

    public static final int T__165 = 165;

    public static final int T__166 = 166;

    public static final int T__167 = 167;

    public static final int T__168 = 168;

    public static final int T__169 = 169;

    public static final int T__170 = 170;

    public static final int T__171 = 171;

    public static final int T__172 = 172;

    public static final int T__173 = 173;

    public static final int T__174 = 174;

    public static final int T__175 = 175;

    public static final int T__176 = 176;

    public static final int T__177 = 177;

    public static final int T__178 = 178;

    public static final int T__179 = 179;

    public static final int T__180 = 180;

    public static final int ANNOTATION = 4;

    public static final int ANNOTATION_ARRAY_INIT = 5;

    public static final int ANNOTATION_DEF = 6;

    public static final int ANNOTATION_DEFAULT = 7;

    public static final int ANNOTATION_INIT_LIST = 8;

    public static final int ANNOTATION_INIT_MEMBER = 9;

    public static final int ANNOTATION_INIT_VALUE = 10;

    public static final int ANNOTATION_MEMBER_DEF = 11;

    public static final int ARRAY_DECLARATOR = 12;

    public static final int ARRAY_INIT = 13;

    public static final int ASSIGN = 14;

    public static final int AT = 15;

    public static final int BAND = 16;

    public static final int BAND_ASSIGN = 17;

    public static final int BIN_DIGIT = 18;

    public static final int BIN_NUM = 19;

    public static final int BNOT = 20;

    public static final int BOR = 21;

    public static final int BOR_ASSIGN = 22;

    public static final int BSR = 23;

    public static final int BSR_ASSIGN = 24;

    public static final int BUILT_IN = 25;

    public static final int BXOR = 26;

    public static final int BXOR_ASSIGN = 27;

    public static final int CASE_GROUP = 28;

    public static final int CHAR_LITERAL = 29;

    public static final int CLASS_DEF = 30;

    public static final int COLON = 31;

    public static final int COMMA = 32;

    public static final int COMMENT = 33;

    public static final int COMMENTS = 34;

    public static final int COMPILATION_UNIT = 35;

    public static final int CTOR_CALL = 36;

    public static final int DEC = 37;

    public static final int DEC_DIGIT = 38;

    public static final int DEC_END = 39;

    public static final int DEC_NUM = 40;

    public static final int DEFAULT = 41;

    public static final int DIV = 42;

    public static final int DIV_ASSIGN = 43;

    public static final int DOT = 44;

    public static final int ELIST = 45;

    public static final int ELLIPSIS = 46;

    public static final int EMPTY_STAT = 47;

    public static final int ENUM_CONST = 48;

    public static final int ENUM_DEF = 49;

    public static final int EQUAL = 50;

    public static final int ESC = 51;

    public static final int EXPONENT = 52;

    public static final int EXPR = 53;

    public static final int EXTENDS_CLAUSE = 54;

    public static final int FLOAT_SUFFIX = 55;

    public static final int FORMAL_TYPE_PARAMS = 56;

    public static final int FOR_CONDITION = 57;

    public static final int FOR_INIT = 58;

    public static final int FOR_ITERATOR = 59;

    public static final int GE = 60;

    public static final int GENERIC_EXTENDS = 61;

    public static final int GT = 62;

    public static final int HEX_DIGIT = 63;

    public static final int HEX_NUM = 64;

    public static final int IDENT = 65;

    public static final int IMPLEMENTS_CLAUSE = 66;

    public static final int IMPORT = 67;

    public static final int INC = 68;

    public static final int INSTANCE_INIT = 69;

    public static final int INTERFACE_DEF = 70;

    public static final int JAVADOC = 71;

    public static final int LABELED_STAT = 72;

    public static final int LAMBDA = 73;

    public static final int LAND = 74;

    public static final int LBRACK = 75;

    public static final int LCURLY = 76;

    public static final int LE = 77;

    public static final int LNOT = 78;

    public static final int LONG_SUFFIX = 79;

    public static final int LOR = 80;

    public static final int LPAREN = 81;

    public static final int LT = 82;

    public static final int METHOD_CALL = 83;

    public static final int METHOD_DEF = 84;

    public static final int MINUS = 85;

    public static final int MINUS_ASSIGN = 86;

    public static final int MOD = 87;

    public static final int MODIFIERS = 88;

    public static final int MOD_ASSIGN = 89;

    public static final int NEW = 90;

    public static final int NL = 91;

    public static final int NOT_EQUAL = 92;

    public static final int NUM_LITERAL = 93;

    public static final int OBJBLOCK = 94;

    public static final int PACKAGE_DEF = 95;

    public static final int PARAMETERS = 96;

    public static final int PARAMETER_DEF = 97;

    public static final int PLUS = 98;

    public static final int PLUS_ASSIGN = 99;

    public static final int QUESTION = 100;

    public static final int RBRACK = 101;

    public static final int RCURLY = 102;

    public static final int REF = 103;

    public static final int RETURN = 104;

    public static final int RPAREN = 105;

    public static final int SEMI = 106;

    public static final int SL = 107;

    public static final int SLIST = 108;

    public static final int SL_ASSIGN = 109;

    public static final int SR = 110;

    public static final int SR_ASSIGN = 111;

    public static final int STAR = 112;

    public static final int STAR_ASSIGN = 113;

    public static final int STATIC = 114;

    public static final int STATIC_INIT = 115;

    public static final int STRING_LITERAL = 116;

    public static final int SUPER = 117;

    public static final int SUPER_CTOR_CALL = 118;

    public static final int THROWN_EXCEPTION = 119;

    public static final int THROWS = 120;

    public static final int TRAILING_COMMENT = 121;

    public static final int TYPE = 122;

    public static final int TYPECAST = 123;

    public static final int TYPE_ARGS = 124;

    public static final int TYPE_PARAM = 125;

    public static final int TYPE_PARAMS = 126;

    public static final int UNARY_MINUS = 127;

    public static final int UNARY_PLUS = 128;

    public static final int VARIABLE_DEF = 129;

    public static final int VOID = 130;

    public static final int WILDCARD = 131;

    public static final int WS = 132;

    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public static final BitSet FOLLOW_packageDefinition_in_compilationUnit459 = new BitSet(new long[]{0x0000020000008002L,0x0000000000000000L,0x000895B948242020L});

    public static final BitSet FOLLOW_importDefinition_in_compilationUnit464 = new BitSet(new long[]{0x0000020000008002L,0x0000000000000000L,0x000895B948242020L});

    public static final BitSet FOLLOW_typeDefinition_in_compilationUnit469 = new BitSet(new long[]{0x0000020000008002L,0x0000000000000000L,0x000895B940242020L});

    public static final BitSet FOLLOW_AT_in_annotationTypeDefinition511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000040000000L});

    public static final BitSet FOLLOW_158_in_annotationTypeDefinition513 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_annotationTypeDefinition515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_annotationBlock_in_annotationTypeDefinition519 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LCURLY_in_annotationBlock560 = new BitSet(new long[]{0x0000020000008000L,0x0000044000000002L,0x000895F9E0A532A0L});

    public static final BitSet FOLLOW_annotationField_in_annotationBlock570 = new BitSet(new long[]{0x0000020000008000L,0x0000044000000002L,0x000895F9E0A532A0L});

    public static final BitSet FOLLOW_SEMI_in_annotationBlock578 = new BitSet(new long[]{0x0000020000008000L,0x0000044000000002L,0x000895F9E0A532A0L});

    public static final BitSet FOLLOW_RCURLY_in_annotationBlock589 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotmodannot_in_annotationField635 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040E0853280L});

    public static final BitSet FOLLOW_enumDefinition_in_annotationField651 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classDefinition_in_annotationField665 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_interfaceDefinition_in_annotationField679 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotationTypeDefinition_in_annotationField700 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotationMethod_in_annotationField721 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_attributeDefinitions_in_annotationField735 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_typeSpec_in_annotationMethod779 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_annotationMethod783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_annotationMethod785 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_annotationMethod787 = new BitSet(new long[]{0x0000020000008000L,0x0000040000000800L});

    public static final BitSet FOLLOW_declaratorBrackets_in_annotationMethod791 = new BitSet(new long[]{0x0000020000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_defaultValue_in_annotationMethod796 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_annotationMethod801 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_DEFAULT_in_defaultValue843 = new BitSet(new long[]{0x0000002020108000L,0x0030000424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_annotationMemberValue_in_defaultValue847 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_annotmodannot940 = new BitSet(new long[]{0x0000020000008000L,0x0000000000000000L,0x000895B900200020L});

    public static final BitSet FOLLOW_modifier_in_annotmodannot943 = new BitSet(new long[]{0x0000020000008002L,0x0000000000000000L,0x000895B900200020L});

    public static final BitSet FOLLOW_annotation_in_annotmodannot1243 = new BitSet(new long[]{0x0000000000008002L});

    public static final BitSet FOLLOW_AT_in_annotation1275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_identifier_in_annotation1277 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_annotationInit_in_annotation1281 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_annotationInit1324 = new BitSet(new long[]{0x0000002020108000L,0x0030020424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_annotationMemberInit_in_annotationInit1338 = new BitSet(new long[]{0x0000000100000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_COMMA_in_annotationInit1341 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_annotationMemberInit_in_annotationInit1343 = new BitSet(new long[]{0x0000000100000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_annotationMemberValue_in_annotationInit1374 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_annotationInit1418 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_annotationMemberInit1445 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_ASSIGN_in_annotationMemberInit1447 = new BitSet(new long[]{0x0000002020108000L,0x0030000424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_annotationMemberValue_in_annotationMemberInit1449 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_annotationMemberValue1486 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_conditionalExpression_in_annotationMemberValue1492 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotationMemberArrayInitializer_in_annotationMemberValue1508 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LCURLY_in_annotationMemberArrayInitializer1528 = new BitSet(new long[]{0x0000002120108000L,0x0030004424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_annotationMemberValue_in_annotationMemberArrayInitializer1531 = new BitSet(new long[]{0x0000000100000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_COMMA_in_annotationMemberArrayInitializer1534 = new BitSet(new long[]{0x0000002020108000L,0x0030000424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_annotationMemberValue_in_annotationMemberArrayInitializer1536 = new BitSet(new long[]{0x0000000100000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_COMMA_in_annotationMemberArrayInitializer1542 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_RCURLY_in_annotationMemberArrayInitializer1545 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_146_in_enumDefinition1576 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_enumDefinition1580 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x0000000004000000L});

    public static final BitSet FOLLOW_implementsClause_in_enumDefinition1591 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_enumBlock_in_enumDefinition1603 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LCURLY_in_enumBlock1651 = new BitSet(new long[]{0x0000000100008000L,0x0000044000000002L});

    public static final BitSet FOLLOW_enumConst_in_enumBlock1656 = new BitSet(new long[]{0x0000000100000000L,0x0000044000000000L});

    public static final BitSet FOLLOW_COMMA_in_enumBlock1661 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_enumConst_in_enumBlock1705 = new BitSet(new long[]{0x0000000100000000L,0x0000044000000000L});

    public static final BitSet FOLLOW_COMMA_in_enumBlock1714 = new BitSet(new long[]{0x0000000000000000L,0x0000044000000000L});

    public static final BitSet FOLLOW_SEMI_in_enumBlock1764 = new BitSet(new long[]{0x0000020000008000L,0x0000044000041002L,0x000C95F9E0A532A0L});

    public static final BitSet FOLLOW_field_in_enumBlock1780 = new BitSet(new long[]{0x0000020000008000L,0x0000044000041002L,0x000C95F9E0A532A0L});

    public static final BitSet FOLLOW_SEMI_in_enumBlock1791 = new BitSet(new long[]{0x0000020000008000L,0x0000044000041002L,0x000C95F9E0A532A0L});

    public static final BitSet FOLLOW_RCURLY_in_enumBlock1809 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_enumConst1849 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_enumConst1852 = new BitSet(new long[]{0x0000000000000002L,0x0000000000021000L});

    public static final BitSet FOLLOW_enumConstInit_in_enumConst1854 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});

    public static final BitSet FOLLOW_classBlock_in_enumConst1857 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_enumConstInit1905 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_enumConstInit1909 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_enumConstInit1913 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_packageDefinition1937 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x0000000400000000L});

    public static final BitSet FOLLOW_162_in_packageDefinition1942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_identifier_in_packageDefinition1944 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_packageDefinition1946 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_155_in_importDefinition1978 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L,0x0000008000000000L});

    public static final BitSet FOLLOW_167_in_importDefinition1983 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_identifierStar_in_importDefinition2015 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_importDefinition2017 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_annotmodannot_in_typeDefinition2060 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x0000000040042000L});

    public static final BitSet FOLLOW_classDefinition_in_typeDefinition2072 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_interfaceDefinition_in_typeDefinition2083 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_annotationTypeDefinition_in_typeDefinition2094 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_enumDefinition_in_typeDefinition2105 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_typeDefinition2112 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_annotmodannot_in_declaration2141 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_declaration2145 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_variableDefinitions_in_declaration2149 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classTypeSpec_in_typeSpec2183 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_builtInTypeSpec_in_typeSpec2189 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_classTypeSpec2205 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000800L});

    public static final BitSet FOLLOW_declaratorBrackets_in_classTypeSpec2208 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_arrayDeclarator_in_declaratorBrackets2225 = new BitSet(new long[]{0x0000000000008002L,0x0000000000000800L});

    public static final BitSet FOLLOW_annotation_in_arrayDeclarator2251 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000800L});

    public static final BitSet FOLLOW_LBRACK_in_arrayDeclarator2256 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});

    public static final BitSet FOLLOW_RBRACK_in_arrayDeclarator2258 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_builtInTypeSpec2284 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x00000040A0811280L});

    public static final BitSet FOLLOW_builtInType_in_builtInTypeSpec2287 = new BitSet(new long[]{0x0000000000008002L,0x0000000000000800L});

    public static final BitSet FOLLOW_arrayDeclarator_in_builtInTypeSpec2289 = new BitSet(new long[]{0x0000000000008002L,0x0000000000000800L});

    public static final BitSet FOLLOW_simpleClassOrInterfaceType_in_classOrInterfaceType2332 = new BitSet(new long[]{0x0000100000000002L});

    public static final BitSet FOLLOW_DOT_in_classOrInterfaceType2358 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_simpleClassOrInterfaceType_in_classOrInterfaceType2368 = new BitSet(new long[]{0x0000100000000002L});

    public static final BitSet FOLLOW_annotation_in_simpleClassOrInterfaceType2423 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_simpleClassOrInterfaceType2426 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});

    public static final BitSet FOLLOW_typeArguments_in_simpleClassOrInterfaceType2428 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_135_in_builtInType2465 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_137_in_builtInType2467 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_140_in_builtInType2469 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_166_in_builtInType2471 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_157_in_builtInType2473 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_151_in_builtInType2475 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_159_in_builtInType2477 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_144_in_builtInType2479 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_178_in_voidkw2504 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_identifier_in_identifierStar2528 = new BitSet(new long[]{0x0000100000000002L});

    public static final BitSet FOLLOW_DOT_in_identifierStar2531 = new BitSet(new long[]{0x0000000000000000L,0x0001000000000000L});

    public static final BitSet FOLLOW_STAR_in_identifierStar2534 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_identifier2554 = new BitSet(new long[]{0x0000100000000002L});

    public static final BitSet FOLLOW_DOT_in_identifier2557 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_identifier2560 = new BitSet(new long[]{0x0000100000000002L});

    public static final BitSet FOLLOW_141_in_classDefinition2669 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_classDefinition2673 = new BitSet(new long[]{0x0000000000000000L,0x0000000000041000L,0x0000000004080000L});

    public static final BitSet FOLLOW_formalTypeParameters_in_classDefinition2677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x0000000004080000L});

    public static final BitSet FOLLOW_superClassClause_in_classDefinition2685 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x0000000004000000L});

    public static final BitSet FOLLOW_implementsClause_in_classDefinition2693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_classBlock_in_classDefinition2701 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LT_in_formalTypeParameters2753 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_typeParameter_in_formalTypeParameters2755 = new BitSet(new long[]{0x4000000100000000L});

    public static final BitSet FOLLOW_COMMA_in_formalTypeParameters2758 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_typeParameter_in_formalTypeParameters2760 = new BitSet(new long[]{0x4000000100000000L});

    public static final BitSet FOLLOW_GT_in_formalTypeParameters2764 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_typeParameter2797 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_typeParameter2802 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000080000L});

    public static final BitSet FOLLOW_typeParameterBound_in_typeParameter2806 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_147_in_typeParameterBound2846 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_typeParameterBoundRest_in_typeParameterBound2850 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_typeParameterBoundRest2881 = new BitSet(new long[]{0x0000000000010002L});

    public static final BitSet FOLLOW_BAND_in_typeParameterBoundRest2884 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_typeParameterBoundRest2886 = new BitSet(new long[]{0x0000000000010002L});

    public static final BitSet FOLLOW_LT_in_typeArguments2907 = new BitSet(new long[]{0x4000000100008000L,0x0000001000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeArgument_in_typeArguments2909 = new BitSet(new long[]{0x4000000100000000L});

    public static final BitSet FOLLOW_COMMA_in_typeArguments2913 = new BitSet(new long[]{0x0000000000008000L,0x0000001000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeArgument_in_typeArguments2915 = new BitSet(new long[]{0x4000000100000000L});

    public static final BitSet FOLLOW_GT_in_typeArguments2919 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_typeArgument2955 = new BitSet(new long[]{0x0000000000008000L,0x0000001000000000L});

    public static final BitSet FOLLOW_QUESTION_in_typeArgument2960 = new BitSet(new long[]{0x0000000000000002L,0x0020000000000000L,0x0000000000080000L});

    public static final BitSet FOLLOW_147_in_typeArgument2967 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_SUPER_in_typeArgument2969 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_typeArgument2972 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_typeSpec_in_typeArgument3006 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_147_in_superClassClause3026 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_superClassClause3030 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_158_in_interfaceDefinition3062 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_interfaceDefinition3066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000041000L,0x0000000000080000L});

    public static final BitSet FOLLOW_formalTypeParameters_in_interfaceDefinition3070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x0000000000080000L});

    public static final BitSet FOLLOW_interfaceExtends_in_interfaceDefinition3078 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_classBlock_in_interfaceDefinition3086 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LCURLY_in_classBlock3139 = new BitSet(new long[]{0x0000020000008000L,0x0000044000041002L,0x000C95F9E0A532A0L});

    public static final BitSet FOLLOW_field_in_classBlock3149 = new BitSet(new long[]{0x0000020000008000L,0x0000044000041002L,0x000C95F9E0A532A0L});

    public static final BitSet FOLLOW_SEMI_in_classBlock3158 = new BitSet(new long[]{0x0000020000008000L,0x0000044000041002L,0x000C95F9E0A532A0L});

    public static final BitSet FOLLOW_RCURLY_in_classBlock3169 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_147_in_interfaceExtends3200 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_interfaceExtends3202 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_interfaceExtends3205 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_interfaceExtends3207 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_154_in_implementsClause3240 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_implementsClause3242 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_implementsClause3245 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_implementsClause3247 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_annotmodannot_in_field3311 = new BitSet(new long[]{0x0000000000008000L,0x0000000000040002L,0x00040040E0853280L});

    public static final BitSet FOLLOW_attributeDefinitions_in_field3336 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_constructorDefinition_in_field3357 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_operationDefinition_in_field3378 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classDefinition_in_field3392 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_interfaceDefinition_in_field3406 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_enumDefinition_in_field3420 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotationTypeDefinition_in_field3434 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_staticInitializer_in_field3452 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_instanceInitializer_in_field3463 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_formalTypeParameters_in_constructorDefinition3495 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_constructorDefinition3503 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_constructorDefinition3508 = new BitSet(new long[]{0x0000000000008000L,0x0000020000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_parameterDeclarationList_in_constructorDefinition3512 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_constructorDefinition3515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L,0x0000400000000000L});

    public static final BitSet FOLLOW_throwsClause_in_constructorDefinition3522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_constructorDefinition3530 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_formalTypeParameters_in_operationDefinition3582 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00040040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_operationDefinition3591 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_voidkw_in_operationDefinition3595 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_operationDefinition3603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_operationDefinition3608 = new BitSet(new long[]{0x0000000000008000L,0x0000020000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_parameterDeclarationList_in_operationDefinition3612 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_operationDefinition3615 = new BitSet(new long[]{0x0000000000008000L,0x0000040000001800L,0x0000400000000000L});

    public static final BitSet FOLLOW_declaratorBrackets_in_operationDefinition3622 = new BitSet(new long[]{0x0000000000000000L,0x0000040000001000L,0x0000400000000000L});

    public static final BitSet FOLLOW_throwsClause_in_operationDefinition3630 = new BitSet(new long[]{0x0000000000000000L,0x0000040000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_operationDefinition3644 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_SEMI_in_operationDefinition3653 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_typeSpec_in_attributeDefinitions3717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_variableDefinitions_in_attributeDefinitions3724 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_attributeDefinitions3729 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_167_in_staticInitializer3758 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_staticInitializer3762 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_compoundStatement_in_instanceInitializer3799 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_variableDeclarator_in_variableDefinitions3832 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_variableDefinitions3836 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_variableDeclarator_in_variableDefinitions3840 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_IDENT_in_variableDeclarator3873 = new BitSet(new long[]{0x000000000000C000L,0x0000000000000800L});

    public static final BitSet FOLLOW_declaratorBrackets_in_variableDeclarator3877 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_varInitializer_in_variableDeclarator3882 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_ASSIGN_in_varInitializer3923 = new BitSet(new long[]{0x0000002020108000L,0x0030000424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_initializer_in_varInitializer3928 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LCURLY_in_arrayInitializer3977 = new BitSet(new long[]{0x0000002020108000L,0x0030004424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_initializer_in_arrayInitializer3980 = new BitSet(new long[]{0x0000000100000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_COMMA_in_arrayInitializer4008 = new BitSet(new long[]{0x0000002020108000L,0x0030000424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_initializer_in_arrayInitializer4010 = new BitSet(new long[]{0x0000000100000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_COMMA_in_arrayInitializer4014 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_RCURLY_in_arrayInitializer4019 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expression_in_initializer4052 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_arrayInitializer_in_initializer4058 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_174_in_throwsClause4075 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_thrownException_in_throwsClause4077 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_throwsClause4080 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_thrownException_in_throwsClause4082 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_annotation_in_thrownException4115 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_identifier_in_thrownException4118 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_parameterDeclaration_in_parameterDeclarationList4146 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_parameterDeclarationList4149 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_parameterDeclaration_in_parameterDeclarationList4151 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_annotation_in_parameterDeclaration4180 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_parameterModifier_in_parameterDeclaration4185 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_parameterDeclaration4190 = new BitSet(new long[]{0x0000400000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_ELLIPSIS_in_parameterDeclaration4194 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_parameterDeclaration4199 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000800L});

    public static final BitSet FOLLOW_declaratorBrackets_in_parameterDeclaration4203 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_149_in_parameterModifier4249 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LCURLY_in_compoundStatement4293 = new BitSet(new long[]{0x0000022020108000L,0x0030054424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statementList_in_compoundStatement4304 = new BitSet(new long[]{0x0000000000000000L,0x0000004000000000L});

    public static final BitSet FOLLOW_RCURLY_in_compoundStatement4306 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_explicitConstructorInvocation_in_statementList4336 = new BitSet(new long[]{0x0000022020108002L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statementList4339 = new BitSet(new long[]{0x0000022020108002L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_171_in_explicitConstructorInvocation4357 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_explicitConstructorInvocation4361 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_explicitConstructorInvocation4363 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_explicitConstructorInvocation4365 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation4367 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_SUPER_in_explicitConstructorInvocation4386 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_explicitConstructorInvocation4390 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_explicitConstructorInvocation4392 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_explicitConstructorInvocation4394 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation4396 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_compoundStatement_in_statement4429 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_declaration_in_statement4462 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4464 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expression_in_statement4488 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4490 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotmodannot_in_statement4510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});

    public static final BitSet FOLLOW_classDefinition_in_statement4512 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_statement4531 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_statement4533 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statement4535 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_153_in_statement4560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_statement4562 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4564 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_statement4566 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statement4570 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_145_in_statement4590 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statement4594 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_152_in_statement4629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_statement4631 = new BitSet(new long[]{0x0000022020108000L,0x0030040424224012L,0x000D9DFBA0B112A0L});

    public static final BitSet FOLLOW_parameterDeclaration_in_statement4649 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_statement4651 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4653 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_forInit_in_statement4661 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4663 = new BitSet(new long[]{0x0000002020108000L,0x0030040424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_forCond_in_statement4670 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4672 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_forIter_in_statement4679 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_statement4688 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statement4690 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_180_in_statement4729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_statement4731 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4733 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_statement4735 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statement4737 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_143_in_statement4762 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_statement4764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0010000000000000L});

    public static final BitSet FOLLOW_180_in_statement4766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_statement4768 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4770 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_statement4772 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4774 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_136_in_statement4799 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000002L});

    public static final BitSet FOLLOW_IDENT_in_statement4801 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4804 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_142_in_statement4828 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000002L});

    public static final BitSet FOLLOW_IDENT_in_statement4830 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4833 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_returnExpression_in_statement4857 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_169_in_statement4871 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_statement4873 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4875 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_statement4877 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_LCURLY_in_statement4879 = new BitSet(new long[]{0x0000020000000000L,0x0000004000000000L,0x0000000000000400L});

    public static final BitSet FOLLOW_casesGroup_in_statement4881 = new BitSet(new long[]{0x0000020000000000L,0x0000004000000000L,0x0000000000000400L});

    public static final BitSet FOLLOW_RCURLY_in_statement4884 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_tryBlock_in_statement4910 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_173_in_statement4928 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4930 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement4932 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_170_in_statement4955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_statement4957 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4959 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_statement4961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_statement4963 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_134_in_statement4989 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4991 = new BitSet(new long[]{0x0000000080000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_COLON_in_statement4994 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_statement4996 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_statement5000 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_SEMI_in_statement5026 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_aCase_in_casesGroup5071 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F7E0L});

    public static final BitSet FOLLOW_caseSList_in_casesGroup5075 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_138_in_aCase5112 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_aCase5115 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_DEFAULT_in_aCase5123 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_aCase5131 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_statement_in_caseSList5147 = new BitSet(new long[]{0x0000022020108002L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_declaration_in_forInit5194 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expressionList_in_forInit5207 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expression_in_forCond5244 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expressionList_in_forIter5274 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_177_in_tryBlock5306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000021000L});

    public static final BitSet FOLLOW_resourceDeclarations_in_tryBlock5309 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_tryBlock5312 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000400800L});

    public static final BitSet FOLLOW_handler_in_tryBlock5315 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000400800L});

    public static final BitSet FOLLOW_finallyClause_in_tryBlock5318 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_resourceDeclarations5335 = new BitSet(new long[]{0x0000020000008000L,0x0000060000000002L,0x000895F9A0A112A0L});

    public static final BitSet FOLLOW_declaration_in_resourceDeclarations5337 = new BitSet(new long[]{0x0000000000000000L,0x0000060000000000L});

    public static final BitSet FOLLOW_SEMI_in_resourceDeclarations5341 = new BitSet(new long[]{0x0000020000008000L,0x0000060000000002L,0x000895F9A0A112A0L});

    public static final BitSet FOLLOW_declaration_in_resourceDeclarations5343 = new BitSet(new long[]{0x0000000000000000L,0x0000060000000000L});

    public static final BitSet FOLLOW_RPAREN_in_resourceDeclarations5348 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_150_in_finallyClause5363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_finallyClause5366 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_139_in_handler5384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_handler5387 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_caughtExceptionDeclarations_in_handler5390 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_handler5392 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_handler5394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_handler5397 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_caughtExceptionDeclaration_in_caughtExceptionDeclarations5413 = new BitSet(new long[]{0x0000000000200002L});

    public static final BitSet FOLLOW_BOR_in_caughtExceptionDeclarations5416 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_caughtExceptionDeclaration_in_caughtExceptionDeclarations5419 = new BitSet(new long[]{0x0000000000200002L});

    public static final BitSet FOLLOW_annotation_in_caughtExceptionDeclaration5437 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_parameterModifier_in_caughtExceptionDeclaration5440 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_caughtExceptionDeclaration5443 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_RETURN_in_returnExpression5467 = new BitSet(new long[]{0x0000002020108000L,0x0030040424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_returnExpression5469 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_returnExpression5472 = new BitSet(new long[]{0x0000000000000002L,0x0000040000000000L});

    public static final BitSet FOLLOW_expression_in_expressionList5501 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_expressionList5504 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_expressionList5506 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_assignmentExpression_in_expression5573 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_conditionalExpression_in_assignmentExpression5604 = new BitSet(new long[]{0x4000080008424002L,0x0002000802440000L});

    public static final BitSet FOLLOW_assignmentOperator_in_assignmentExpression5608 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_assignmentExpression_in_assignmentExpression5610 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_ASSIGN_in_assignmentOperator5627 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_PLUS_ASSIGN_in_assignmentOperator5641 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_MINUS_ASSIGN_in_assignmentOperator5655 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_STAR_ASSIGN_in_assignmentOperator5669 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_DIV_ASSIGN_in_assignmentOperator5683 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_MOD_ASSIGN_in_assignmentOperator5697 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_GT_in_assignmentOperator5711 = new BitSet(new long[]{0x4000000000000000L});

    public static final BitSet FOLLOW_GT_in_assignmentOperator5713 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_ASSIGN_in_assignmentOperator5715 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_GT_in_assignmentOperator5729 = new BitSet(new long[]{0x4000000000000000L});

    public static final BitSet FOLLOW_GT_in_assignmentOperator5731 = new BitSet(new long[]{0x4000000000000000L});

    public static final BitSet FOLLOW_GT_in_assignmentOperator5733 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_ASSIGN_in_assignmentOperator5735 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LT_in_assignmentOperator5749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});

    public static final BitSet FOLLOW_LT_in_assignmentOperator5751 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_ASSIGN_in_assignmentOperator5753 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BAND_ASSIGN_in_assignmentOperator5767 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BXOR_ASSIGN_in_assignmentOperator5781 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BOR_ASSIGN_in_assignmentOperator5795 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_logicalOrExpression_in_conditionalExpression5828 = new BitSet(new long[]{0x0000000000000002L,0x0000001000000000L});

    public static final BitSet FOLLOW_QUESTION_in_conditionalExpression5831 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_assignmentExpression_in_conditionalExpression5834 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_conditionalExpression5836 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_conditionalExpression_in_conditionalExpression5839 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression5859 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});

    public static final BitSet FOLLOW_LOR_in_logicalOrExpression5862 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_logicalAndExpression_in_logicalOrExpression5865 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});

    public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression5884 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});

    public static final BitSet FOLLOW_LAND_in_logicalAndExpression5887 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_inclusiveOrExpression_in_logicalAndExpression5890 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});

    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5909 = new BitSet(new long[]{0x0000000000200002L});

    public static final BitSet FOLLOW_BOR_in_inclusiveOrExpression5912 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5915 = new BitSet(new long[]{0x0000000000200002L});

    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression5934 = new BitSet(new long[]{0x0000000004000002L});

    public static final BitSet FOLLOW_BXOR_in_exclusiveOrExpression5937 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression5940 = new BitSet(new long[]{0x0000000004000002L});

    public static final BitSet FOLLOW_equalityExpression_in_andExpression5959 = new BitSet(new long[]{0x0000000000010002L});

    public static final BitSet FOLLOW_BAND_in_andExpression5962 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_equalityExpression_in_andExpression5965 = new BitSet(new long[]{0x0000000000010002L});

    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression5984 = new BitSet(new long[]{0x0004000000000002L,0x0000000010000000L});

    public static final BitSet FOLLOW_NOT_EQUAL_in_equalityExpression6002 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_EQUAL_in_equalityExpression6013 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression6026 = new BitSet(new long[]{0x0004000000000002L,0x0000000010000000L});

    public static final BitSet FOLLOW_relationalExpression_in_instanceOfExpression6048 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000010000000L});

    public static final BitSet FOLLOW_156_in_instanceOfExpression6051 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_instanceOfExpression6054 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression6073 = new BitSet(new long[]{0x4000000000000002L,0x0000000000040000L});

    public static final BitSet FOLLOW_relationalOperator_in_relationalExpression6076 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_additiveExpression_in_relationalExpression6079 = new BitSet(new long[]{0x4000000000000002L,0x0000000000040000L});

    public static final BitSet FOLLOW_LT_in_relationalOperator6096 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LT_in_relationalOperator6110 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});

    public static final BitSet FOLLOW_LT_in_relationalOperator6112 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LT_in_relationalOperator6126 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_ASSIGN_in_relationalOperator6128 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6142 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6156 = new BitSet(new long[]{0x4000000000000000L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6158 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6172 = new BitSet(new long[]{0x4000000000000000L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6174 = new BitSet(new long[]{0x4000000000000000L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6176 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_GT_in_relationalOperator6190 = new BitSet(new long[]{0x0000000000004000L});

    public static final BitSet FOLLOW_ASSIGN_in_relationalOperator6192 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression6216 = new BitSet(new long[]{0x0000000000000002L,0x0000000400200000L});

    public static final BitSet FOLLOW_PLUS_in_additiveExpression6234 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_MINUS_in_additiveExpression6245 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression6258 = new BitSet(new long[]{0x0000000000000002L,0x0000000400200000L});

    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression6280 = new BitSet(new long[]{0x0000040000000002L,0x0001000000800000L});

    public static final BitSet FOLLOW_STAR_in_multiplicativeExpression6298 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_DIV_in_multiplicativeExpression6309 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_MOD_in_multiplicativeExpression6320 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression6333 = new BitSet(new long[]{0x0000040000000002L,0x0001000000800000L});

    public static final BitSet FOLLOW_INC_in_unaryExpression6353 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6356 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_DEC_in_unaryExpression6362 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6365 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_MINUS_in_unaryExpression6371 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6391 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_PLUS_in_unaryExpression6397 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression6414 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression6420 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BNOT_in_unaryExpressionNotPlusMinus6433 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6435 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LNOT_in_unaryExpressionNotPlusMinus6454 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6456 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus6532 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x00000040A0811280L});

    public static final BitSet FOLLOW_builtInTypeSpec_in_unaryExpressionNotPlusMinus6534 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus6536 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6538 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus6597 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classTypeSpec_in_unaryExpressionNotPlusMinus6599 = new BitSet(new long[]{0x0000000000010000L,0x0000020000000000L});

    public static final BitSet FOLLOW_BAND_in_unaryExpressionNotPlusMinus6602 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classTypeSpec_in_unaryExpressionNotPlusMinus6604 = new BitSet(new long[]{0x0000000000010000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus6608 = new BitSet(new long[]{0x0000000020108000L,0x0030000024024002L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpressionNotPlusMinus6610 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_methodReference_in_unaryExpressionNotPlusMinus6636 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_typeSpec_in_methodReference6670 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});

    public static final BitSet FOLLOW_voidkw_in_methodReference6672 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});

    public static final BitSet FOLLOW_REF_in_methodReference6675 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040002L});

    public static final BitSet FOLLOW_typeArguments_in_methodReference6677 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000002L});

    public static final BitSet FOLLOW_set_in_methodReference6680 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_lambdaExpression_in_methodReference6694 = new BitSet(new long[]{0x0000000000000002L,0x0000008000000000L});

    public static final BitSet FOLLOW_REF_in_methodReference6697 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040002L});

    public static final BitSet FOLLOW_typeArguments_in_methodReference6699 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000002L});

    public static final BitSet FOLLOW_set_in_methodReference6702 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_lambdaParameters_in_lambdaExpression6733 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});

    public static final BitSet FOLLOW_LAMBDA_in_lambdaExpression6735 = new BitSet(new long[]{0x0000002020108000L,0x0030000424225012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_lambdaBody_in_lambdaExpression6737 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_postfixExpression_in_lambdaExpression6743 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_lambdaParameters6758 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_lambdaParameters6764 = new BitSet(new long[]{0x0000000000008000L,0x0000020000000002L,0x00000040A0A11280L});

    public static final BitSet FOLLOW_parameterDeclarationList_in_lambdaParameters6767 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_inferredFormalParameters_in_lambdaParameters6771 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_lambdaParameters6775 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_inferredFormalParameters6792 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_COMMA_in_inferredFormalParameters6795 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_inferredFormalParameters6797 = new BitSet(new long[]{0x0000000100000002L});

    public static final BitSet FOLLOW_expression_in_lambdaBody6815 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_compoundStatement_in_lambdaBody6821 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_primaryExpression_in_postfixExpression6839 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_DOT_in_postfixExpression6849 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040002L});

    public static final BitSet FOLLOW_typeArguments_in_postfixExpression6851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_postfixExpression6854 = new BitSet(new long[]{0x0000102000000002L,0x0000000000020810L});

    public static final BitSet FOLLOW_LPAREN_in_postfixExpression6857 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_postfixExpression6859 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_postfixExpression6861 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_DOT_in_postfixExpression6871 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000080000000000L});

    public static final BitSet FOLLOW_171_in_postfixExpression6873 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_DOT_in_postfixExpression6881 = new BitSet(new long[]{0x0000000000000000L,0x0020000000000000L});

    public static final BitSet FOLLOW_SUPER_in_postfixExpression6883 = new BitSet(new long[]{0x0000100000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_postfixExpression6898 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_postfixExpression6900 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_postfixExpression6902 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_DOT_in_postfixExpression6912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_postfixExpression6914 = new BitSet(new long[]{0x0000102000000002L,0x0000000000020810L});

    public static final BitSet FOLLOW_LPAREN_in_postfixExpression6917 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_postfixExpression6919 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_postfixExpression6921 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_DOT_in_postfixExpression6937 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});

    public static final BitSet FOLLOW_newExpression_in_postfixExpression6939 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_LBRACK_in_postfixExpression6949 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_postfixExpression6951 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});

    public static final BitSet FOLLOW_RBRACK_in_postfixExpression6953 = new BitSet(new long[]{0x0000102000000002L,0x0000000000000810L});

    public static final BitSet FOLLOW_identPrimary_in_primaryExpression6992 = new BitSet(new long[]{0x0000100000000002L});

    public static final BitSet FOLLOW_DOT_in_primaryExpression7003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});

    public static final BitSet FOLLOW_141_in_primaryExpression7006 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_constant_in_primaryExpression7014 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_176_in_primaryExpression7020 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_148_in_primaryExpression7026 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_161_in_primaryExpression7032 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_newExpression_in_primaryExpression7038 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_171_in_primaryExpression7044 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_SUPER_in_primaryExpression7050 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_primaryExpression7056 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_assignmentExpression_in_primaryExpression7059 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_primaryExpression7061 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_builtInType_in_primaryExpression7074 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000800L});

    public static final BitSet FOLLOW_voidkw_in_primaryExpression7076 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000800L});

    public static final BitSet FOLLOW_LBRACK_in_primaryExpression7086 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});

    public static final BitSet FOLLOW_RBRACK_in_primaryExpression7110 = new BitSet(new long[]{0x0000100000000000L,0x0000000000000800L});

    public static final BitSet FOLLOW_DOT_in_primaryExpression7115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});

    public static final BitSet FOLLOW_141_in_primaryExpression7118 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_identPrimary7135 = new BitSet(new long[]{0x0000100000000002L,0x0000000000020800L});

    public static final BitSet FOLLOW_DOT_in_identPrimary7146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_identPrimary7149 = new BitSet(new long[]{0x0000100000000002L,0x0000000000020800L});

    public static final BitSet FOLLOW_LPAREN_in_identPrimary7168 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_identPrimary7219 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_identPrimary7221 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LBRACK_in_identPrimary7242 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});

    public static final BitSet FOLLOW_RBRACK_in_identPrimary7296 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});

    public static final BitSet FOLLOW_NEW_in_newExpression7319 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_newExpression7329 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});

    public static final BitSet FOLLOW_annotation_in_newExpression7337 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x00000040A0811280L});

    public static final BitSet FOLLOW_builtInType_in_newExpression7340 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020800L});

    public static final BitSet FOLLOW_LPAREN_in_newExpression7354 = new BitSet(new long[]{0x0000002020108000L,0x0030020424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_argList_in_newExpression7356 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_newExpression7358 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});

    public static final BitSet FOLLOW_classBlock_in_newExpression7360 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_newArrayDeclarator_in_newExpression7370 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});

    public static final BitSet FOLLOW_arrayInitializer_in_newExpression7372 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expressionList_in_argList7398 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LBRACK_in_newArrayDeclarator7498 = new BitSet(new long[]{0x0000002020108000L,0x0030002424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_newArrayDeclarator7546 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});

    public static final BitSet FOLLOW_RBRACK_in_newArrayDeclarator7550 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});

    public static final BitSet FOLLOW_packageDefinition_in_synpred1_Java459 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotationTypeDefinition_in_synpred9_Java692 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotationMethod_in_synpred10_Java713 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred13_Java931 = new BitSet(new long[]{0x0000020000008000L,0x0000000000000000L,0x000895B900200020L});

    public static final BitSet FOLLOW_modifier_in_synpred13_Java934 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred15_Java940 = new BitSet(new long[]{0x0000020000008000L,0x0000000000000000L,0x000895B900200020L});

    public static final BitSet FOLLOW_modifier_in_synpred15_Java943 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred16_Java1243 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred21_Java1486 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_conditionalExpression_in_synpred22_Java1492 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classTypeSpec_in_synpred44_Java2183 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_typeArguments_in_synpred51_Java2428 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred85_Java2955 = new BitSet(new long[]{0x0000000000008000L,0x0000001000000000L});

    public static final BitSet FOLLOW_QUESTION_in_synpred85_Java2960 = new BitSet(new long[]{0x0000000000000002L,0x0020000000000000L,0x0000000000080000L});

    public static final BitSet FOLLOW_set_in_synpred85_Java2966 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_synpred85_Java2972 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_attributeDefinitions_in_synpred92_Java3328 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_constructorDefinition_in_synpred93_Java3349 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_operationDefinition_in_synpred94_Java3370 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred117_Java4180 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_declaration_in_synpred124_Java4457 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expression_in_synpred125_Java4488 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});

    public static final BitSet FOLLOW_SEMI_in_synpred125_Java4490 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotmodannot_in_synpred126_Java4510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});

    public static final BitSet FOLLOW_classDefinition_in_synpred126_Java4512 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_synpred127_Java4531 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_synpred127_Java4533 = new BitSet(new long[]{0x0000022020108000L,0x0030050424225012L,0x001FBFFBA3B1F3E0L});

    public static final BitSet FOLLOW_statement_in_synpred127_Java4535 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_145_in_synpred128_Java4585 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_parameterDeclaration_in_synpred130_Java4642 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_synpred130_Java4644 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_170_in_synpred143_Java4955 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});

    public static final BitSet FOLLOW_LPAREN_in_synpred143_Java4957 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_synpred143_Java4959 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_synpred143_Java4961 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});

    public static final BitSet FOLLOW_compoundStatement_in_synpred143_Java4963 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_aCase_in_synpred146_Java5071 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_declaration_in_synpred149_Java5189 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_expressionList_in_synpred150_Java5207 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_annotation_in_synpred160_Java5437 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_SEMI_in_synpred163_Java5472 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_assignmentOperator_in_synpred165_Java5608 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_assignmentExpression_in_synpred165_Java5610 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_QUESTION_in_synpred177_Java5831 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_assignmentExpression_in_synpred177_Java5834 = new BitSet(new long[]{0x0000000080000000L});

    public static final BitSet FOLLOW_COLON_in_synpred177_Java5836 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_conditionalExpression_in_synpred177_Java5839 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LOR_in_synpred178_Java5862 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_logicalAndExpression_in_synpred178_Java5865 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LAND_in_synpred179_Java5887 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_inclusiveOrExpression_in_synpred179_Java5890 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BOR_in_synpred180_Java5912 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_exclusiveOrExpression_in_synpred180_Java5915 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BXOR_in_synpred181_Java5937 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_andExpression_in_synpred181_Java5940 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_BAND_in_synpred182_Java5962 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_equalityExpression_in_synpred182_Java5965 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_set_in_synpred184_Java5994 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_instanceOfExpression_in_synpred184_Java6026 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_156_in_synpred185_Java6051 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L,0x00000040A0811280L});

    public static final BitSet FOLLOW_typeSpec_in_synpred185_Java6054 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_relationalOperator_in_synpred186_Java6076 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_additiveExpression_in_synpred186_Java6079 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_set_in_synpred194_Java6226 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_multiplicativeExpression_in_synpred194_Java6258 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_set_in_synpred197_Java6290 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_synpred197_Java6333 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_synpred204_Java6532 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000000L,0x00000040A0811280L});

    public static final BitSet FOLLOW_builtInTypeSpec_in_synpred204_Java6534 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_synpred204_Java6536 = new BitSet(new long[]{0x0000002020108000L,0x0030000424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpression_in_synpred204_Java6538 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LPAREN_in_synpred206_Java6579 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classTypeSpec_in_synpred206_Java6581 = new BitSet(new long[]{0x0000000000010000L,0x0000020000000000L});

    public static final BitSet FOLLOW_BAND_in_synpred206_Java6584 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000002L});

    public static final BitSet FOLLOW_classTypeSpec_in_synpred206_Java6586 = new BitSet(new long[]{0x0000000000010000L,0x0000020000000000L});

    public static final BitSet FOLLOW_RPAREN_in_synpred206_Java6590 = new BitSet(new long[]{0x0000000020108000L,0x0030000024024002L,0x00050842A0911280L});

    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_synpred206_Java6592 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_typeSpec_in_synpred211_Java6670 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});

    public static final BitSet FOLLOW_voidkw_in_synpred211_Java6672 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});

    public static final BitSet FOLLOW_REF_in_synpred211_Java6675 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040002L});

    public static final BitSet FOLLOW_typeArguments_in_synpred211_Java6677 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000002L});

    public static final BitSet FOLLOW_set_in_synpred211_Java6680 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_REF_in_synpred214_Java6697 = new BitSet(new long[]{0x0000000000000000L,0x0000000004040002L});

    public static final BitSet FOLLOW_typeArguments_in_synpred214_Java6699 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000002L});

    public static final BitSet FOLLOW_set_in_synpred214_Java6702 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_lambdaParameters_in_synpred215_Java6726 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});

    public static final BitSet FOLLOW_LAMBDA_in_synpred215_Java6728 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_DOT_in_synpred244_Java7146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});

    public static final BitSet FOLLOW_IDENT_in_synpred244_Java7149 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_classOrInterfaceType_in_synpred248_Java7329 = new BitSet(new long[]{0x0000000000000002L});

    public static final BitSet FOLLOW_LBRACK_in_synpred255_Java7498 = new BitSet(new long[]{0x0000002020108000L,0x0030002424224012L,0x00050842A0911280L});

    public static final BitSet FOLLOW_expression_in_synpred255_Java7546 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});

    public static final BitSet FOLLOW_RBRACK_in_synpred255_Java7550 = new BitSet(new long[]{0x0000000000000002L});

// delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

// delegators
    public JavaParser(final TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public JavaParser(final TokenStream input, final RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[364+1];
    }

    public void setTreeAdaptor(final TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    @Override
    public String[] getTokenNames() {
        return JavaParser.tokenNames;
    }

    @Override
    public String getGrammarFileName() {
        return "com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g";
    }

    /**
     * Look for comments tokens above 'start' token and attach them to 'node'
     * Existing comments tokens attached to 'node' are kept.
     */
    private void attachAboveComments(final ASTTree node, final Token start) {
        if (node == null) return;
          ASTTree commentsNode = new ASTTree(createToken(COMMENTS));
          List<? extends Token> tokens = ((CommonTokenStream) input).getTokens();
          int tokenPos = start.getTokenIndex() - 1;
          // step back the token stream, searching for javadocs 
          while (tokenPos >= 0) {
              Token curToken = tokens.get(tokenPos);
              if (curToken.getType() == JAVADOC || curToken.getType() == COMMENT) {
                  // Comment found
                  commentsNode.insertInFront(new ASTTree(curToken));
              } else if (curToken.getChannel() == Token.DEFAULT_CHANNEL) {
                  // we have stepped back through a non hidden token : exit the loop
                  // (the previous test is required because hidden tokens other than javadoc should be ignored.)
                  break;
              }
              tokenPos--;
          }
          if (commentsNode.getChildCount() != 0) {
              attachToNodeBefore(node, commentsNode);
          }
    }

    /**
     * Look for comment tokens after 'start' token but on the same line and attach them to 'node'
     * Existing comment tokens attached to 'node' are kept.
     */
    private void attachTrailingComments(final ASTTree node, final Token start) {
        if (node == null) return;
          ASTTree commentsNode = new ASTTree(); // nil temporary node
          List<? extends Token> tokens = ((CommonTokenStream) input).getTokens();
          int tokenPos = start.getTokenIndex() + 1;
          // Step forward on the same line, searching for comments
          while (tokenPos < tokens.size()) {
              Token curToken = tokens.get(tokenPos);
              if ((curToken.getType() == COMMENT || curToken.getType() == JAVADOC)
                      && curToken.getLine() == start.getLine()) {
                  // comment found : change its type and store it
                  // Note : it's because the token type is changed that it will not be
                  // found by 'attachAboveComments' for the next element (attribute, etc)
                  curToken.setType(TRAILING_COMMENT);
                  commentsNode.addChild(new ASTTree(curToken));
              } else if (curToken.getChannel() == Token.DEFAULT_CHANNEL
                      || curToken.getLine() > start.getLine()) {
                  // non hidden token or change of line : exit the loop
                  break;
              }
              tokenPos++;
          }
          if (commentsNode.getChildCount() != 0) {
              attachToNode(node, commentsNode);
          }
    }

    /**
     * Look for comment tokens underneath 'start' token and attach them to 'node'
     * Existing comment tokens attached to 'node' are kept.
     */
    private void attachUnderneathComments(final ASTTree node, final Token start) {
        if (node == null) return;
          ASTTree commentsNode = new ASTTree(createToken(COMMENTS));
          List<? extends Token> tokens = ((CommonTokenStream) input).getTokens();
          int tokenPos = start.getTokenIndex() + 1;
          // step back the token stream, searching for javadocs 
          while (tokenPos < tokens.size()) {
              Token curToken = tokens.get(tokenPos);
              if (curToken.getType() == JAVADOC || curToken.getType() == COMMENT) {
                  // comment found
                  commentsNode.addChild(new ASTTree(curToken));
              } else if (curToken.getChannel() == Token.DEFAULT_CHANNEL) {
                  // we have stepped forward through a non hidden token : exit the loop
                  // (the previous test is required because hidden tokens other than javadoc or comment should be ignored.)
                  break;
              }
              tokenPos++;
          }
          if (commentsNode.getChildCount() != 0) {
              attachToNode(node, commentsNode);
          }
    }

    private void attachToNode(final ASTTree node, final ASTTree commentsNode) {
        ASTTree anchor = node;
        while (anchor.isNil()) {
            // flat list : attach to the first non nil child
            anchor = (ASTTree) anchor.getChild(0);
            if (anchor == null)
                return;
        }
        anchor.addChild(commentsNode);
    }

    private void attachToNodeBefore(final ASTTree node, final ASTTree commentsNode) {
        ASTTree anchor = node;
        while (anchor.isNil()) {
            // flat list : attach to the first non nil child
            anchor = (ASTTree) anchor.getChild(0);
            if (anchor == null)
                return;
        }
        try {
            if (anchor.getChildren() != null) {
              anchor.insertInFront(commentsNode);
            } else {
                // anchor has no child, add its first
                anchor.addChild(commentsNode);
            }
            // should be: anchor.insertChild(0, commentsNode);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a token with an identifiable text for debug purpose
     * (Note : prefer not use the constructor CommonToken(type, text) because
     * it set the channel to the default one)
     */
    private Token createToken(final int type) {
        Token com = new CommonToken(type);
        com.setText(tokenNames[type]);
        return com;
    }

    /**
     * Adjust the start token in order to include all comments on top of the given 'start' as well as
     * the white space on the same line than the given 'start'
     */
    private Token adjustStartToken(final Token start) {
        List<? extends Token> tokens = ((CommonTokenStream) input).getTokens();
        int tokenpos = start.getTokenIndex() - 1;
        // step back to the first meaningful token (or begining of token stream)
        // Trailing_comments are stoppers because they are somehow meaningful
        while (tokenpos >= 0
                && tokens.get(tokenpos).getChannel() != Token.DEFAULT_CHANNEL && tokens.get(tokenpos).getType() != TRAILING_COMMENT) {
            tokenpos--;
        }
        tokenpos++;
        // skip white space on the first line
        int stoppos = start.getTokenIndex();
        int adjstartpos = tokenpos;
        while (tokenpos < stoppos) {
            Token curtoken = tokens.get(tokenpos);
        
            int curtype = curtoken.getType();
            if (curtype == NL) {
                // 1st new line after 'from' the adjusted token is what follows NL  
                adjstartpos = tokenpos + 1;
                break;
            } else if (curtype != WS) {
                // 1st token which is not a NL or a WS. This is the adjusted token.
                adjstartpos = tokenpos;
                break;
            }
            tokenpos++;
        }
        return tokens.get(adjstartpos);
    }

    /**
     * Adjust the stop token in order to catch comments
     * The goal is to catch all comments between 'from' and 'to'
     */
    private Token adjustStopToken(final Token stop) {
        List<? extends Token> tokens = ((CommonTokenStream) input).getTokens();
        int tokenpos = stop.getTokenIndex() + 1;
        int adjstartpos = tokenpos - 1;
        while (tokenpos < tokens.size()
                && tokens.get(tokenpos).getChannel() != Token.DEFAULT_CHANNEL) {
            Token curtoken = tokens.get(tokenpos);
            int curtype = curtoken.getType();
            if (curtype != NL && curtype != WS) {
                // We are at a useful hidden token (not to say a comment), this is the new adjusted token so far 
                adjstartpos = tokenpos;
            }
            tokenpos++;
        }
        return tokens.get(adjstartpos);
    }

    private String getSource(final Token start, final Token stop) {
        return this.input.toString(start, stop);
    }

// $ANTLR start "compilationUnit"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:311:1: compilationUnit : (pd= packageDefinition )? (impd+= importDefinition )* (td+= typeDefinition )* -> ^( COMPILATION_UNIT ( $pd)? ( $impd)* ( $td)* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.compilationUnit_return compilationUnit() throws RecognitionException {
        JavaParser.compilationUnit_return retval = new JavaParser.compilationUnit_return();
        retval.start = input.LT(1);
        int compilationUnit_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        List<Object> list_impd=null;
        List<Object> list_td=null;
        ParserRuleReturnScope pd =null;
        RuleReturnScope impd = null;
        RuleReturnScope td = null;
        RewriteRuleSubtreeStream stream_importDefinition=new RewriteRuleSubtreeStream(adaptor,"rule importDefinition");
        RewriteRuleSubtreeStream stream_typeDefinition=new RewriteRuleSubtreeStream(adaptor,"rule typeDefinition");
        RewriteRuleSubtreeStream stream_packageDefinition=new RewriteRuleSubtreeStream(adaptor,"rule packageDefinition");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:318:3: ( (pd= packageDefinition )? (impd+= importDefinition )* (td+= typeDefinition )* -> ^( COMPILATION_UNIT ( $pd)? ( $impd)* ( $td)* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:3: (pd= packageDefinition )? (impd+= importDefinition )* (td+= typeDefinition )*
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:5: (pd= packageDefinition )?
            int alt1=2;
            int LA1_0 = input.LA(1);
            if ( (LA1_0==AT) ) {
                int LA1_1 = input.LA(2);
                if ( (synpred1_Java()) ) {
                    alt1=1;
                }
            }
            else if ( (LA1_0==162) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:5: pd= packageDefinition
                    {
                    pushFollow(FOLLOW_packageDefinition_in_compilationUnit459);
                    pd=packageDefinition();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_packageDefinition.add(pd.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:29: (impd+= importDefinition )*
            loop2:
            while (true) {
                int alt2=2;
                int LA2_0 = input.LA(1);
                if ( (LA2_0==155) ) {
                    alt2=1;
                }
        
                switch (alt2) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:29: impd+= importDefinition
                    {
                    pushFollow(FOLLOW_importDefinition_in_compilationUnit464);
                    impd=importDefinition();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_importDefinition.add(impd.getTree());
                    if (list_impd==null) list_impd=new ArrayList<Object>();
                    list_impd.add(impd.getTree());
                    }
                    break;
        
                default :
                    break loop2;
                }
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:51: (td+= typeDefinition )*
            loop3:
            while (true) {
                int alt3=2;
                int LA3_0 = input.LA(1);
                if ( (LA3_0==AT||LA3_0==DEFAULT||LA3_0==133||LA3_0==141||LA3_0==146||LA3_0==149||LA3_0==158||LA3_0==160||(LA3_0 >= 163 && LA3_0 <= 165)||(LA3_0 >= 167 && LA3_0 <= 168)||LA3_0==170||LA3_0==172||LA3_0==175||LA3_0==179) ) {
                    alt3=1;
                }
        
                switch (alt3) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:51: td+= typeDefinition
                    {
                    pushFollow(FOLLOW_typeDefinition_in_compilationUnit469);
                    td=typeDefinition();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeDefinition.add(td.getTree());
                    if (list_td==null) list_td=new ArrayList<Object>();
                    list_td.add(td.getTree());
                    }
                    break;
        
                default :
                    break loop3;
                }
            }
        
            // AST REWRITE
            // elements: td, pd, impd
            // token labels: 
            // rule labels: pd, retval
            // token list labels: 
            // rule list labels: td, impd
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_pd=new RewriteRuleSubtreeStream(adaptor,"rule pd",pd!=null?pd.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_td=new RewriteRuleSubtreeStream(adaptor,"token td",list_td);
            RewriteRuleSubtreeStream stream_impd=new RewriteRuleSubtreeStream(adaptor,"token impd",list_impd);
            root_0 = (ASTTree)adaptor.nil();
            // 320:5: -> ^( COMPILATION_UNIT ( $pd)? ( $impd)* ( $td)* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:320:8: ^( COMPILATION_UNIT ( $pd)? ( $impd)* ( $td)* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(COMPILATION_UNIT, "COMPILATION_UNIT"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:320:28: ( $pd)?
                if ( stream_pd.hasNext() ) {
                    adaptor.addChild(root_1, stream_pd.nextTree());
                }
                stream_pd.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:320:33: ( $impd)*
                while ( stream_impd.hasNext() ) {
                    adaptor.addChild(root_1, stream_impd.nextTree());
                }
                stream_impd.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:320:40: ( $td)*
                while ( stream_td.hasNext() ) {
                    adaptor.addChild(root_1, stream_td.nextTree());
                }
                stream_td.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            // JavaTop
            attachAboveComments(retval.tree, (retval.start));
            // JavaBottom
            attachUnderneathComments(retval.tree, (retval.stop));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, compilationUnit_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationTypeDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:325:1: annotationTypeDefinition[Object ama] : AT 'interface' IDENT ab= annotationBlock -> ^( ANNOTATION_DEF IDENT $ab) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationTypeDefinition_return annotationTypeDefinition(final Object ama) throws RecognitionException {
        JavaParser.annotationTypeDefinition_return retval = new JavaParser.annotationTypeDefinition_return();
        retval.start = input.LT(1);
        int annotationTypeDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token AT1=null;
        Token string_literal2=null;
        Token IDENT3=null;
        ParserRuleReturnScope ab =null;
        
        ASTTree AT1_tree=null;
        ASTTree string_literal2_tree=null;
        ASTTree IDENT3_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_158=new RewriteRuleTokenStream(adaptor,"token 158");
        RewriteRuleSubtreeStream stream_annotationBlock=new RewriteRuleSubtreeStream(adaptor,"rule annotationBlock");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:326:3: ( AT 'interface' IDENT ab= annotationBlock -> ^( ANNOTATION_DEF IDENT $ab) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:327:3: AT 'interface' IDENT ab= annotationBlock
            {
            AT1=(Token)match(input,AT,FOLLOW_AT_in_annotationTypeDefinition511); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_AT.add(AT1);
        
            string_literal2=(Token)match(input,158,FOLLOW_158_in_annotationTypeDefinition513); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_158.add(string_literal2);
        
            IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_annotationTypeDefinition515); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(IDENT3);
        
            pushFollow(FOLLOW_annotationBlock_in_annotationTypeDefinition519);
            ab=annotationBlock();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotationBlock.add(ab.getTree());
            // AST REWRITE
            // elements: IDENT, ab
            // token labels: 
            // rule labels: retval, ab
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_ab=new RewriteRuleSubtreeStream(adaptor,"rule ab",ab!=null?ab.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 328:5: -> ^( ANNOTATION_DEF IDENT $ab)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:328:8: ^( ANNOTATION_DEF IDENT $ab)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_DEF, "ANNOTATION_DEF"), root_1);
                adaptor.addChild(root_1, stream_IDENT.nextNode());
                adaptor.addChild(root_1, ama);
                adaptor.addChild(root_1, stream_ab.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, annotationTypeDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationBlock"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:334:1: annotationBlock : LCURLY ( annotationField | SEMI )* rc= RCURLY -> ^( OBJBLOCK ( annotationField )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationBlock_return annotationBlock() throws RecognitionException {
        JavaParser.annotationBlock_return retval = new JavaParser.annotationBlock_return();
        retval.start = input.LT(1);
        int annotationBlock_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token rc=null;
        Token LCURLY4=null;
        Token SEMI6=null;
        ParserRuleReturnScope annotationField5 =null;
        
        ASTTree rc_tree=null;
        ASTTree LCURLY4_tree=null;
        ASTTree SEMI6_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_annotationField=new RewriteRuleSubtreeStream(adaptor,"rule annotationField");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:338:3: ( LCURLY ( annotationField | SEMI )* rc= RCURLY -> ^( OBJBLOCK ( annotationField )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:339:3: LCURLY ( annotationField | SEMI )* rc= RCURLY
            {
            LCURLY4=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_annotationBlock560); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY4);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:340:3: ( annotationField | SEMI )*
            loop4:
            while (true) {
                int alt4=3;
                int LA4_0 = input.LA(1);
                if ( (LA4_0==AT||LA4_0==DEFAULT||LA4_0==IDENT||LA4_0==133||LA4_0==135||LA4_0==137||(LA4_0 >= 140 && LA4_0 <= 141)||LA4_0==144||LA4_0==146||LA4_0==149||LA4_0==151||(LA4_0 >= 157 && LA4_0 <= 160)||(LA4_0 >= 163 && LA4_0 <= 168)||LA4_0==170||LA4_0==172||LA4_0==175||LA4_0==179) ) {
                    alt4=1;
                }
                else if ( (LA4_0==SEMI) ) {
                    alt4=2;
                }
        
                switch (alt4) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:341:5: annotationField
                    {
                    pushFollow(FOLLOW_annotationField_in_annotationBlock570);
                    annotationField5=annotationField();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotationField.add(annotationField5.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:342:7: SEMI
                    {
                    SEMI6=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationBlock578); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI6);
        
                    }
                    break;
        
                default :
                    break loop4;
                }
            }
        
            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_annotationBlock589); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(rc);
        
            // AST REWRITE
            // elements: annotationField
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 345:5: -> ^( OBJBLOCK ( annotationField )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:345:8: ^( OBJBLOCK ( annotationField )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(OBJBLOCK, "OBJBLOCK"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:345:19: ( annotationField )*
                while ( stream_annotationField.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotationField.nextTree());
                }
                stream_annotationField.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachAboveComments(retval.tree, rc);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, annotationBlock_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationField"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:352:1: annotationField : (ama= annotmodannot (def= enumDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] | ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree] | ( annotationMethod[null] )=>def= annotationMethod[$ama.tree] |def= attributeDefinitions[$ama.tree] ) ) -> $def;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationField_return annotationField() throws RecognitionException {
        JavaParser.annotationField_return retval = new JavaParser.annotationField_return();
        retval.start = input.LT(1);
        int annotationField_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope ama =null;
        ParserRuleReturnScope def =null;
        
        RewriteRuleSubtreeStream stream_annotmodannot=new RewriteRuleSubtreeStream(adaptor,"rule annotmodannot");
        RewriteRuleSubtreeStream stream_annotationMethod=new RewriteRuleSubtreeStream(adaptor,"rule annotationMethod");
        RewriteRuleSubtreeStream stream_attributeDefinitions=new RewriteRuleSubtreeStream(adaptor,"rule attributeDefinitions");
        RewriteRuleSubtreeStream stream_interfaceDefinition=new RewriteRuleSubtreeStream(adaptor,"rule interfaceDefinition");
        RewriteRuleSubtreeStream stream_annotationTypeDefinition=new RewriteRuleSubtreeStream(adaptor,"rule annotationTypeDefinition");
        RewriteRuleSubtreeStream stream_classDefinition=new RewriteRuleSubtreeStream(adaptor,"rule classDefinition");
        RewriteRuleSubtreeStream stream_enumDefinition=new RewriteRuleSubtreeStream(adaptor,"rule enumDefinition");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:358:3: ( (ama= annotmodannot (def= enumDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] | ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree] | ( annotationMethod[null] )=>def= annotationMethod[$ama.tree] |def= attributeDefinitions[$ama.tree] ) ) -> $def)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:359:3: (ama= annotmodannot (def= enumDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] | ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree] | ( annotationMethod[null] )=>def= annotationMethod[$ama.tree] |def= attributeDefinitions[$ama.tree] ) )
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:359:3: (ama= annotmodannot (def= enumDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] | ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree] | ( annotationMethod[null] )=>def= annotationMethod[$ama.tree] |def= attributeDefinitions[$ama.tree] ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:360:5: ama= annotmodannot (def= enumDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] | ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree] | ( annotationMethod[null] )=>def= annotationMethod[$ama.tree] |def= attributeDefinitions[$ama.tree] )
            {
            pushFollow(FOLLOW_annotmodannot_in_annotationField635);
            ama=annotmodannot();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotmodannot.add(ama.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:361:5: (def= enumDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] | ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree] | ( annotationMethod[null] )=>def= annotationMethod[$ama.tree] |def= attributeDefinitions[$ama.tree] )
            int alt5=6;
            switch ( input.LA(1) ) {
            case 146:
                {
                alt5=1;
                }
                break;
            case 141:
                {
                alt5=2;
                }
                break;
            case 158:
                {
                alt5=3;
                }
                break;
            case AT:
                {
                int LA5_4 = input.LA(2);
                if ( (synpred9_Java()) ) {
                    alt5=4;
                }
                else if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case IDENT:
                {
                int LA5_5 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 135:
                {
                int LA5_6 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 137:
                {
                int LA5_7 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 140:
                {
                int LA5_8 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 166:
                {
                int LA5_9 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 157:
                {
                int LA5_10 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 151:
                {
                int LA5_11 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 159:
                {
                int LA5_12 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            case 144:
                {
                int LA5_13 = input.LA(2);
                if ( (synpred10_Java()) ) {
                    alt5=5;
                }
                else if ( (true) ) {
                    alt5=6;
                }
        
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);
                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:362:7: def= enumDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_enumDefinition_in_annotationField651);
                    def=enumDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumDefinition.add(def.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:363:9: def= classDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_classDefinition_in_annotationField665);
                    def=classDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classDefinition.add(def.getTree());
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:364:9: def= interfaceDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_interfaceDefinition_in_annotationField679);
                    def=interfaceDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_interfaceDefinition.add(def.getTree());
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:365:9: ( annotationTypeDefinition[null] )=>def= annotationTypeDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_annotationTypeDefinition_in_annotationField700);
                    def=annotationTypeDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotationTypeDefinition.add(def.getTree());
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:366:9: ( annotationMethod[null] )=>def= annotationMethod[$ama.tree]
                    {
                    pushFollow(FOLLOW_annotationMethod_in_annotationField721);
                    def=annotationMethod((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotationMethod.add(def.getTree());
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:367:9: def= attributeDefinitions[$ama.tree]
                    {
                    pushFollow(FOLLOW_attributeDefinitions_in_annotationField735);
                    def=attributeDefinitions((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_attributeDefinitions.add(def.getTree());
                    }
                    break;
        
            }
        
            }
        
            // AST REWRITE
            // elements: def
            // token labels: 
            // rule labels: retval, def
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_def=new RewriteRuleSubtreeStream(adaptor,"rule def",def!=null?def.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 370:5: -> $def
            {
                adaptor.addChild(root_0, stream_def.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            // attach comments & javadoc above the annotation.
            // note eventhough there is no annotation in source, ann is still valid.
            attachAboveComments(retval.tree, (retval.start));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, annotationField_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationMethod"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:373:1: annotationMethod[Object ama] : ts= typeSpec id= IDENT LPAREN RPAREN rt= declaratorBrackets[$ts.tree] (dv= defaultValue )? sm= SEMI -> ^( ANNOTATION_MEMBER_DEF $rt $id ( $dv)? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationMethod_return annotationMethod(final Object ama) throws RecognitionException {
        JavaParser.annotationMethod_return retval = new JavaParser.annotationMethod_return();
        retval.start = input.LT(1);
        int annotationMethod_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        Token sm=null;
        Token LPAREN7=null;
        Token RPAREN8=null;
        ParserRuleReturnScope ts =null;
        ParserRuleReturnScope rt =null;
        ParserRuleReturnScope dv =null;
        
        ASTTree id_tree=null;
        ASTTree sm_tree=null;
        ASTTree LPAREN7_tree=null;
        ASTTree RPAREN8_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_declaratorBrackets=new RewriteRuleSubtreeStream(adaptor,"rule declaratorBrackets");
        RewriteRuleSubtreeStream stream_typeSpec=new RewriteRuleSubtreeStream(adaptor,"rule typeSpec");
        RewriteRuleSubtreeStream stream_defaultValue=new RewriteRuleSubtreeStream(adaptor,"rule defaultValue");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:377:3: (ts= typeSpec id= IDENT LPAREN RPAREN rt= declaratorBrackets[$ts.tree] (dv= defaultValue )? sm= SEMI -> ^( ANNOTATION_MEMBER_DEF $rt $id ( $dv)? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:378:3: ts= typeSpec id= IDENT LPAREN RPAREN rt= declaratorBrackets[$ts.tree] (dv= defaultValue )? sm= SEMI
            {
            pushFollow(FOLLOW_typeSpec_in_annotationMethod779);
            ts=typeSpec();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeSpec.add(ts.getTree());
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_annotationMethod783); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            LPAREN7=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_annotationMethod785); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN7);
        
            RPAREN8=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_annotationMethod787); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN8);
        
            pushFollow(FOLLOW_declaratorBrackets_in_annotationMethod791);
            rt=declaratorBrackets((ts!=null?((ASTTree)ts.getTree()):null));
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaratorBrackets.add(rt.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:378:72: (dv= defaultValue )?
            int alt6=2;
            int LA6_0 = input.LA(1);
            if ( (LA6_0==DEFAULT) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:378:72: dv= defaultValue
                    {
                    pushFollow(FOLLOW_defaultValue_in_annotationMethod796);
                    dv=defaultValue();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_defaultValue.add(dv.getTree());
                    }
                    break;
        
            }
        
            sm=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationMethod801); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(sm);
        
            // AST REWRITE
            // elements: rt, dv, id
            // token labels: id
            // rule labels: retval, dv, rt
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_dv=new RewriteRuleSubtreeStream(adaptor,"rule dv",dv!=null?dv.getTree():null);
            RewriteRuleSubtreeStream stream_rt=new RewriteRuleSubtreeStream(adaptor,"rule rt",rt!=null?rt.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 379:5: -> ^( ANNOTATION_MEMBER_DEF $rt $id ( $dv)? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:379:8: ^( ANNOTATION_MEMBER_DEF $rt $id ( $dv)? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_MEMBER_DEF, "ANNOTATION_MEMBER_DEF"), root_1);
                adaptor.addChild(root_1, ama);
                adaptor.addChild(root_1, stream_rt.nextTree());
                adaptor.addChild(root_1, stream_id.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:379:48: ( $dv)?
                if ( stream_dv.hasNext() ) {
                    adaptor.addChild(root_1, stream_dv.nextTree());
                }
                stream_dv.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachTrailingComments(retval.tree, sm);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, annotationMethod_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "defaultValue"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:384:1: defaultValue : dflt= DEFAULT anv= annotationMemberValue -> ^( ANNOTATION_DEFAULT annotationMemberValue ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.defaultValue_return defaultValue() throws RecognitionException {
        JavaParser.defaultValue_return retval = new JavaParser.defaultValue_return();
        retval.start = input.LT(1);
        int defaultValue_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token dflt=null;
        ParserRuleReturnScope anv =null;
        
        ASTTree dflt_tree=null;
        RewriteRuleTokenStream stream_DEFAULT=new RewriteRuleTokenStream(adaptor,"token DEFAULT");
        RewriteRuleSubtreeStream stream_annotationMemberValue=new RewriteRuleSubtreeStream(adaptor,"rule annotationMemberValue");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:385:3: (dflt= DEFAULT anv= annotationMemberValue -> ^( ANNOTATION_DEFAULT annotationMemberValue ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:386:3: dflt= DEFAULT anv= annotationMemberValue
            {
            dflt=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_defaultValue843); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DEFAULT.add(dflt);
        
            pushFollow(FOLLOW_annotationMemberValue_in_defaultValue847);
            anv=annotationMemberValue();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotationMemberValue.add(anv.getTree());
            if ( state.backtracking==0 ) {
                                                       (anv!=null?((ASTTree)anv.getTree()):null).setSourceCode(this.getSource(this.adjustStartToken((anv!=null?(anv.start):null)),
                                                               (anv!=null?(anv.stop):null)));
                                                      }
            // AST REWRITE
            // elements: annotationMemberValue
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 391:5: -> ^( ANNOTATION_DEFAULT annotationMemberValue )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:391:8: ^( ANNOTATION_DEFAULT annotationMemberValue )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_DEFAULT, "ANNOTATION_DEFAULT"), root_1);
                adaptor.addChild(root_1, stream_annotationMemberValue.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, defaultValue_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotmodannot"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:407:1: annotmodannot : ( ( ( annotation )* modifier )=> ( ( annotation )* modifier )+ -> ^( MODIFIERS ( modifier )+ ) ( annotation )* | ( annotation )* -> ( annotation )* );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotmodannot_return annotmodannot() throws RecognitionException {
        JavaParser.annotmodannot_return retval = new JavaParser.annotmodannot_return();
        retval.start = input.LT(1);
        int annotmodannot_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope annotation9 =null;
        ParserRuleReturnScope modifier10 =null;
        ParserRuleReturnScope annotation11 =null;
        
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_modifier=new RewriteRuleSubtreeStream(adaptor,"rule modifier");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:2: ( ( ( annotation )* modifier )=> ( ( annotation )* modifier )+ -> ^( MODIFIERS ( modifier )+ ) ( annotation )* | ( annotation )* -> ( annotation )* )
            int alt10=2;
            int LA10_0 = input.LA(1);
            if ( (LA10_0==AT) ) {
                int LA10_1 = input.LA(2);
                if ( (synpred13_Java()) ) {
                    alt10=1;
                }
                else if ( (true) ) {
                    alt10=2;
                }
        
            }
            else if ( (LA10_0==DEFAULT||LA10_0==133||LA10_0==149||LA10_0==160||(LA10_0 >= 163 && LA10_0 <= 165)||(LA10_0 >= 167 && LA10_0 <= 168)||LA10_0==170||LA10_0==172||LA10_0==175||LA10_0==179) && (synpred13_Java())) {
                alt10=1;
            }
            else if ( (LA10_0==IDENT||LA10_0==LT||LA10_0==135||LA10_0==137||(LA10_0 >= 140 && LA10_0 <= 141)||LA10_0==144||LA10_0==146||LA10_0==151||(LA10_0 >= 157 && LA10_0 <= 159)||LA10_0==166||LA10_0==178) ) {
                alt10=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);
                throw nvae;
            }
        
            switch (alt10) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:4: ( ( annotation )* modifier )=> ( ( annotation )* modifier )+
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:30: ( ( annotation )* modifier )+
                    int cnt8=0;
                    loop8:
                    while (true) {
                        int alt8=2;
                        int LA8_0 = input.LA(1);
                        if ( (LA8_0==AT) ) {
                            int LA8_4 = input.LA(2);
                            if ( (synpred15_Java()) ) {
                                alt8=1;
                            }
        
                        }
                        else if ( (LA8_0==DEFAULT||LA8_0==133||LA8_0==149||LA8_0==160||(LA8_0 >= 163 && LA8_0 <= 165)||(LA8_0 >= 167 && LA8_0 <= 168)||LA8_0==170||LA8_0==172||LA8_0==175||LA8_0==179) ) {
                            alt8=1;
                        }
        
                        switch (alt8) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: ( annotation )* modifier
                            {
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: ( annotation )*
                            loop7:
                            while (true) {
                                int alt7=2;
                                int LA7_0 = input.LA(1);
                                if ( (LA7_0==AT) ) {
                                    alt7=1;
                                }
        
                                switch (alt7) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: annotation
                                    {
                                    pushFollow(FOLLOW_annotation_in_annotmodannot940);
                                    annotation9=annotation();
                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_annotation.add(annotation9.getTree());
                                    }
                                    break;
        
                                default :
                                    break loop7;
                                }
                            }
        
                            pushFollow(FOLLOW_modifier_in_annotmodannot943);
                            modifier10=modifier();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_modifier.add(modifier10.getTree());
                            }
                            break;
        
                        default :
                            if ( cnt8 >= 1 ) break loop8;
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            EarlyExitException eee = new EarlyExitException(8, input);
                            throw eee;
                        }
                        cnt8++;
                    }
        
                    // AST REWRITE
                    // elements: annotation, modifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 408:54: -> ^( MODIFIERS ( modifier )+ ) ( annotation )*
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:57: ^( MODIFIERS ( modifier )+ )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(MODIFIERS, "MODIFIERS"), root_1);
                        if ( !(stream_modifier.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_modifier.hasNext() ) {
                            adaptor.addChild(root_1, stream_modifier.nextTree());
                        }
                        stream_modifier.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:80: ( annotation )*
                        while ( stream_annotation.hasNext() ) {
                            adaptor.addChild(root_0, stream_annotation.nextTree());
                        }
                        stream_annotation.reset();
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:412:4: ( annotation )*
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:412:4: ( annotation )*
                    loop9:
                    while (true) {
                        int alt9=2;
                        int LA9_0 = input.LA(1);
                        if ( (LA9_0==AT) ) {
                            int LA9_2 = input.LA(2);
                            if ( (LA9_2==IDENT) ) {
                                int LA9_3 = input.LA(3);
                                if ( (synpred16_Java()) ) {
                                    alt9=1;
                                }
        
                            }
        
                        }
        
                        switch (alt9) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:412:4: annotation
                            {
                            pushFollow(FOLLOW_annotation_in_annotmodannot1243);
                            annotation11=annotation();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_annotation.add(annotation11.getTree());
                            }
                            break;
        
                        default :
                            break loop9;
                        }
                    }
        
                    // AST REWRITE
                    // elements: annotation
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 412:16: -> ( annotation )*
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:412:19: ( annotation )*
                        while ( stream_annotation.hasNext() ) {
                            adaptor.addChild(root_0, stream_annotation.nextTree());
                        }
                        stream_annotation.reset();
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, annotmodannot_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotation"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:415:1: annotation : at= AT identifier ani= annotationInit -> ^( ANNOTATION[$at] identifier ( $ani)? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotation_return annotation() throws RecognitionException {
        JavaParser.annotation_return retval = new JavaParser.annotation_return();
        retval.start = input.LT(1);
        int annotation_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token at=null;
        ParserRuleReturnScope ani =null;
        ParserRuleReturnScope identifier12 =null;
        
        ASTTree at_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleSubtreeStream stream_annotationInit=new RewriteRuleSubtreeStream(adaptor,"rule annotationInit");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:419:3: (at= AT identifier ani= annotationInit -> ^( ANNOTATION[$at] identifier ( $ani)? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:420:3: at= AT identifier ani= annotationInit
            {
            at=(Token)match(input,AT,FOLLOW_AT_in_annotation1275); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_AT.add(at);
        
            pushFollow(FOLLOW_identifier_in_annotation1277);
            identifier12=identifier();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifier.add(identifier12.getTree());
            pushFollow(FOLLOW_annotationInit_in_annotation1281);
            ani=annotationInit();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotationInit.add(ani.getTree());
            // AST REWRITE
            // elements: ani, identifier
            // token labels: 
            // rule labels: retval, ani
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_ani=new RewriteRuleSubtreeStream(adaptor,"rule ani",ani!=null?ani.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 421:5: -> ^( ANNOTATION[$at] identifier ( $ani)? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:421:8: ^( ANNOTATION[$at] identifier ( $ani)? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION, at), root_1);
                adaptor.addChild(root_1, stream_identifier.nextTree());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:421:38: ( $ani)?
                if ( stream_ani.hasNext() ) {
                    adaptor.addChild(root_1, stream_ani.nextTree());
                }
                stream_ani.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, annotation_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationInit"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:426:1: annotationInit : ( (lp= LPAREN ( annotationMemberInit ( COMMA annotationMemberInit )* -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* ) | annotationMemberValue -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue ) |) RPAREN ) |);
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationInit_return annotationInit() throws RecognitionException {
        JavaParser.annotationInit_return retval = new JavaParser.annotationInit_return();
        retval.start = input.LT(1);
        int annotationInit_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lp=null;
        Token COMMA14=null;
        Token RPAREN17=null;
        ParserRuleReturnScope annotationMemberInit13 =null;
        ParserRuleReturnScope annotationMemberInit15 =null;
        ParserRuleReturnScope annotationMemberValue16 =null;
        
        ASTTree lp_tree=null;
        ASTTree COMMA14_tree=null;
        ASTTree RPAREN17_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_annotationMemberInit=new RewriteRuleSubtreeStream(adaptor,"rule annotationMemberInit");
        RewriteRuleSubtreeStream stream_annotationMemberValue=new RewriteRuleSubtreeStream(adaptor,"rule annotationMemberValue");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:427:3: ( (lp= LPAREN ( annotationMemberInit ( COMMA annotationMemberInit )* -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* ) | annotationMemberValue -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue ) |) RPAREN ) |)
            int alt13=2;
            int LA13_0 = input.LA(1);
            if ( (LA13_0==LPAREN) ) {
                alt13=1;
            }
            else if ( (LA13_0==EOF||LA13_0==AT||LA13_0==COMMA||LA13_0==DEFAULT||LA13_0==IDENT||LA13_0==LBRACK||LA13_0==LT||LA13_0==QUESTION||LA13_0==RCURLY||(LA13_0 >= RPAREN && LA13_0 <= SEMI)||LA13_0==133||LA13_0==135||LA13_0==137||(LA13_0 >= 140 && LA13_0 <= 141)||LA13_0==144||LA13_0==146||LA13_0==149||LA13_0==151||(LA13_0 >= 157 && LA13_0 <= 160)||(LA13_0 >= 162 && LA13_0 <= 168)||LA13_0==170||LA13_0==172||LA13_0==175||(LA13_0 >= 178 && LA13_0 <= 179)) ) {
                alt13=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);
                throw nvae;
            }
        
            switch (alt13) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:428:3: (lp= LPAREN ( annotationMemberInit ( COMMA annotationMemberInit )* -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* ) | annotationMemberValue -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue ) |) RPAREN )
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:428:3: (lp= LPAREN ( annotationMemberInit ( COMMA annotationMemberInit )* -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* ) | annotationMemberValue -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue ) |) RPAREN )
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:429:5: lp= LPAREN ( annotationMemberInit ( COMMA annotationMemberInit )* -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* ) | annotationMemberValue -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue ) |) RPAREN
                    {
                    lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_annotationInit1324); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(lp);
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:430:5: ( annotationMemberInit ( COMMA annotationMemberInit )* -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* ) | annotationMemberValue -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue ) |)
                    int alt12=3;
                    switch ( input.LA(1) ) {
                    case IDENT:
                        {
                        int LA12_1 = input.LA(2);
                        if ( (LA12_1==ASSIGN) ) {
                            alt12=1;
                        }
                        else if ( ((LA12_1 >= AT && LA12_1 <= BAND)||LA12_1==BOR||LA12_1==BXOR||LA12_1==DEC||LA12_1==DIV||LA12_1==DOT||LA12_1==EQUAL||LA12_1==GT||LA12_1==INC||(LA12_1 >= LAMBDA && LA12_1 <= LBRACK)||(LA12_1 >= LOR && LA12_1 <= LT)||LA12_1==MINUS||LA12_1==MOD||LA12_1==NOT_EQUAL||LA12_1==PLUS||LA12_1==QUESTION||LA12_1==REF||LA12_1==RPAREN||LA12_1==STAR||LA12_1==156) ) {
                            alt12=2;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 12, 1, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                        }
                        break;
                    case AT:
                    case BNOT:
                    case CHAR_LITERAL:
                    case DEC:
                    case INC:
                    case LCURLY:
                    case LNOT:
                    case LPAREN:
                    case MINUS:
                    case NEW:
                    case NUM_LITERAL:
                    case PLUS:
                    case STRING_LITERAL:
                    case SUPER:
                    case 135:
                    case 137:
                    case 140:
                    case 144:
                    case 148:
                    case 151:
                    case 157:
                    case 159:
                    case 161:
                    case 166:
                    case 171:
                    case 176:
                    case 178:
                        {
                        alt12=2;
                        }
                        break;
                    case RPAREN:
                        {
                        alt12=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);
                        throw nvae;
                    }
                    switch (alt12) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:431:7: annotationMemberInit ( COMMA annotationMemberInit )*
                            {
                            pushFollow(FOLLOW_annotationMemberInit_in_annotationInit1338);
                            annotationMemberInit13=annotationMemberInit();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_annotationMemberInit.add(annotationMemberInit13.getTree());
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:431:28: ( COMMA annotationMemberInit )*
                            loop11:
                            while (true) {
                                int alt11=2;
                                int LA11_0 = input.LA(1);
                                if ( (LA11_0==COMMA) ) {
                                    alt11=1;
                                }
        
                                switch (alt11) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:431:29: COMMA annotationMemberInit
                                    {
                                    COMMA14=(Token)match(input,COMMA,FOLLOW_COMMA_in_annotationInit1341); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA14);
        
                                    pushFollow(FOLLOW_annotationMemberInit_in_annotationInit1343);
                                    annotationMemberInit15=annotationMemberInit();
                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_annotationMemberInit.add(annotationMemberInit15.getTree());
                                    }
                                    break;
        
                                default :
                                    break loop11;
                                }
                            }
        
                            // AST REWRITE
                            // elements: annotationMemberInit
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                            root_0 = (ASTTree)adaptor.nil();
                            // 432:9: -> ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* )
                            {
                                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:432:12: ^( ANNOTATION_INIT_LIST[$lp] ( annotationMemberInit )* )
                                {
                                ASTTree root_1 = (ASTTree)adaptor.nil();
                                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_INIT_LIST, lp), root_1);
                                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:432:40: ( annotationMemberInit )*
                                while ( stream_annotationMemberInit.hasNext() ) {
                                    adaptor.addChild(root_1, stream_annotationMemberInit.nextTree());
                                }
                                stream_annotationMemberInit.reset();
        
                                adaptor.addChild(root_0, root_1);
                                }
        
                            }
        
        
                            retval.tree = root_0;
                            }
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:433:9: annotationMemberValue
                            {
                            pushFollow(FOLLOW_annotationMemberValue_in_annotationInit1374);
                            annotationMemberValue16=annotationMemberValue();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_annotationMemberValue.add(annotationMemberValue16.getTree());
                            // AST REWRITE
                            // elements: annotationMemberValue
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                            root_0 = (ASTTree)adaptor.nil();
                            // 434:9: -> ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue )
                            {
                                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:434:12: ^( ANNOTATION_INIT_VALUE[$lp] annotationMemberValue )
                                {
                                ASTTree root_1 = (ASTTree)adaptor.nil();
                                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_INIT_VALUE, lp), root_1);
                                adaptor.addChild(root_1, stream_annotationMemberValue.nextTree());
                                adaptor.addChild(root_0, root_1);
                                }
        
                            }
        
        
                            retval.tree = root_0;
                            }
        
                            }
                            break;
                        case 3 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:437:5: 
                            {
                            }
                            break;
        
                    }
        
                    RPAREN17=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_annotationInit1418); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN17);
        
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:442:3: 
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, annotationInit_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationMemberInit"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:444:1: annotationMemberInit : IDENT ASSIGN annotationMemberValue -> ^( ANNOTATION_INIT_MEMBER IDENT annotationMemberValue ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationMemberInit_return annotationMemberInit() throws RecognitionException {
        JavaParser.annotationMemberInit_return retval = new JavaParser.annotationMemberInit_return();
        retval.start = input.LT(1);
        int annotationMemberInit_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token IDENT18=null;
        Token ASSIGN19=null;
        ParserRuleReturnScope annotationMemberValue20 =null;
        
        ASTTree IDENT18_tree=null;
        ASTTree ASSIGN19_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_annotationMemberValue=new RewriteRuleSubtreeStream(adaptor,"rule annotationMemberValue");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:445:3: ( IDENT ASSIGN annotationMemberValue -> ^( ANNOTATION_INIT_MEMBER IDENT annotationMemberValue ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:446:3: IDENT ASSIGN annotationMemberValue
            {
            IDENT18=(Token)match(input,IDENT,FOLLOW_IDENT_in_annotationMemberInit1445); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(IDENT18);
        
            ASSIGN19=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_annotationMemberInit1447); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN19);
        
            pushFollow(FOLLOW_annotationMemberValue_in_annotationMemberInit1449);
            annotationMemberValue20=annotationMemberValue();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotationMemberValue.add(annotationMemberValue20.getTree());
            // AST REWRITE
            // elements: annotationMemberValue, IDENT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 447:5: -> ^( ANNOTATION_INIT_MEMBER IDENT annotationMemberValue )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:447:8: ^( ANNOTATION_INIT_MEMBER IDENT annotationMemberValue )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_INIT_MEMBER, "ANNOTATION_INIT_MEMBER"), root_1);
                adaptor.addChild(root_1, stream_IDENT.nextNode());
                adaptor.addChild(root_1, stream_annotationMemberValue.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, annotationMemberInit_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationMemberValue"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:450:1: annotationMemberValue : ( annotation | conditionalExpression[true] -> ^( EXPR conditionalExpression ) | annotationMemberArrayInitializer );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationMemberValue_return annotationMemberValue() throws RecognitionException {
        JavaParser.annotationMemberValue_return retval = new JavaParser.annotationMemberValue_return();
        retval.start = input.LT(1);
        int annotationMemberValue_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope annotation21 =null;
        ParserRuleReturnScope conditionalExpression22 =null;
        ParserRuleReturnScope annotationMemberArrayInitializer23 =null;
        
        RewriteRuleSubtreeStream stream_conditionalExpression=new RewriteRuleSubtreeStream(adaptor,"rule conditionalExpression");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:454:3: ( annotation | conditionalExpression[true] -> ^( EXPR conditionalExpression ) | annotationMemberArrayInitializer )
            int alt14=3;
            switch ( input.LA(1) ) {
            case AT:
                {
                int LA14_1 = input.LA(2);
                if ( (synpred21_Java()) ) {
                    alt14=1;
                }
                else if ( (synpred22_Java()) ) {
                    alt14=2;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case BNOT:
            case CHAR_LITERAL:
            case DEC:
            case IDENT:
            case INC:
            case LNOT:
            case LPAREN:
            case MINUS:
            case NEW:
            case NUM_LITERAL:
            case PLUS:
            case STRING_LITERAL:
            case SUPER:
            case 135:
            case 137:
            case 140:
            case 144:
            case 148:
            case 151:
            case 157:
            case 159:
            case 161:
            case 166:
            case 171:
            case 176:
            case 178:
                {
                alt14=2;
                }
                break;
            case LCURLY:
                {
                alt14=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);
                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:455:5: annotation
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_annotation_in_annotationMemberValue1486);
                    annotation21=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation21.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:456:5: conditionalExpression[true]
                    {
                    pushFollow(FOLLOW_conditionalExpression_in_annotationMemberValue1492);
                    conditionalExpression22=conditionalExpression(true);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_conditionalExpression.add(conditionalExpression22.getTree());
                    // AST REWRITE
                    // elements: conditionalExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 456:33: -> ^( EXPR conditionalExpression )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:456:36: ^( EXPR conditionalExpression )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(EXPR, "EXPR"), root_1);
                        adaptor.addChild(root_1, stream_conditionalExpression.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:457:5: annotationMemberArrayInitializer
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_annotationMemberArrayInitializer_in_annotationMemberValue1508);
                    annotationMemberArrayInitializer23=annotationMemberArrayInitializer();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationMemberArrayInitializer23.getTree());
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, annotationMemberValue_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "annotationMemberArrayInitializer"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:463:1: annotationMemberArrayInitializer : lc= LCURLY ( annotationMemberValue ( COMMA annotationMemberValue )* )? ( COMMA )? RCURLY -> ^( ANNOTATION_ARRAY_INIT[$lc] ( annotationMemberValue )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.annotationMemberArrayInitializer_return annotationMemberArrayInitializer() throws RecognitionException {
        JavaParser.annotationMemberArrayInitializer_return retval = new JavaParser.annotationMemberArrayInitializer_return();
        retval.start = input.LT(1);
        int annotationMemberArrayInitializer_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lc=null;
        Token COMMA25=null;
        Token COMMA27=null;
        Token RCURLY28=null;
        ParserRuleReturnScope annotationMemberValue24 =null;
        ParserRuleReturnScope annotationMemberValue26 =null;
        
        ASTTree lc_tree=null;
        ASTTree COMMA25_tree=null;
        ASTTree COMMA27_tree=null;
        ASTTree RCURLY28_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_annotationMemberValue=new RewriteRuleSubtreeStream(adaptor,"rule annotationMemberValue");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:464:3: (lc= LCURLY ( annotationMemberValue ( COMMA annotationMemberValue )* )? ( COMMA )? RCURLY -> ^( ANNOTATION_ARRAY_INIT[$lc] ( annotationMemberValue )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:3: lc= LCURLY ( annotationMemberValue ( COMMA annotationMemberValue )* )? ( COMMA )? RCURLY
            {
            lc=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_annotationMemberArrayInitializer1528); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(lc);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:13: ( annotationMemberValue ( COMMA annotationMemberValue )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);
            if ( (LA16_0==AT||LA16_0==BNOT||LA16_0==CHAR_LITERAL||LA16_0==DEC||LA16_0==IDENT||LA16_0==INC||LA16_0==LCURLY||LA16_0==LNOT||LA16_0==LPAREN||LA16_0==MINUS||LA16_0==NEW||LA16_0==NUM_LITERAL||LA16_0==PLUS||(LA16_0 >= STRING_LITERAL && LA16_0 <= SUPER)||LA16_0==135||LA16_0==137||LA16_0==140||LA16_0==144||LA16_0==148||LA16_0==151||LA16_0==157||LA16_0==159||LA16_0==161||LA16_0==166||LA16_0==171||LA16_0==176||LA16_0==178) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:14: annotationMemberValue ( COMMA annotationMemberValue )*
                    {
                    pushFollow(FOLLOW_annotationMemberValue_in_annotationMemberArrayInitializer1531);
                    annotationMemberValue24=annotationMemberValue();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotationMemberValue.add(annotationMemberValue24.getTree());
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:36: ( COMMA annotationMemberValue )*
                    loop15:
                    while (true) {
                        int alt15=2;
                        int LA15_0 = input.LA(1);
                        if ( (LA15_0==COMMA) ) {
                            int LA15_1 = input.LA(2);
                            if ( (LA15_1==AT||LA15_1==BNOT||LA15_1==CHAR_LITERAL||LA15_1==DEC||LA15_1==IDENT||LA15_1==INC||LA15_1==LCURLY||LA15_1==LNOT||LA15_1==LPAREN||LA15_1==MINUS||LA15_1==NEW||LA15_1==NUM_LITERAL||LA15_1==PLUS||(LA15_1 >= STRING_LITERAL && LA15_1 <= SUPER)||LA15_1==135||LA15_1==137||LA15_1==140||LA15_1==144||LA15_1==148||LA15_1==151||LA15_1==157||LA15_1==159||LA15_1==161||LA15_1==166||LA15_1==171||LA15_1==176||LA15_1==178) ) {
                                alt15=1;
                            }
        
                        }
        
                        switch (alt15) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:37: COMMA annotationMemberValue
                            {
                            COMMA25=(Token)match(input,COMMA,FOLLOW_COMMA_in_annotationMemberArrayInitializer1534); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA25);
        
                            pushFollow(FOLLOW_annotationMemberValue_in_annotationMemberArrayInitializer1536);
                            annotationMemberValue26=annotationMemberValue();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_annotationMemberValue.add(annotationMemberValue26.getTree());
                            }
                            break;
        
                        default :
                            break loop15;
                        }
                    }
        
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:69: ( COMMA )?
            int alt17=2;
            int LA17_0 = input.LA(1);
            if ( (LA17_0==COMMA) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:465:69: COMMA
                    {
                    COMMA27=(Token)match(input,COMMA,FOLLOW_COMMA_in_annotationMemberArrayInitializer1542); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA27);
        
                    }
                    break;
        
            }
        
            RCURLY28=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_annotationMemberArrayInitializer1545); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY28);
        
            // AST REWRITE
            // elements: annotationMemberValue
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 466:5: -> ^( ANNOTATION_ARRAY_INIT[$lc] ( annotationMemberValue )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:466:8: ^( ANNOTATION_ARRAY_INIT[$lc] ( annotationMemberValue )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ANNOTATION_ARRAY_INIT, lc), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:466:37: ( annotationMemberValue )*
                while ( stream_annotationMemberValue.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotationMemberValue.nextTree());
                }
                stream_annotationMemberValue.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, annotationMemberArrayInitializer_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "enumDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:469:1: enumDefinition[Object ama] : 'enum' id= IDENT (ic= implementsClause )? eb= enumBlock -> ^( ENUM_DEF $id ( $ic)? $eb) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.enumDefinition_return enumDefinition(final Object ama) throws RecognitionException {
        JavaParser.enumDefinition_return retval = new JavaParser.enumDefinition_return();
        retval.start = input.LT(1);
        int enumDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        Token string_literal29=null;
        ParserRuleReturnScope ic =null;
        ParserRuleReturnScope eb =null;
        
        ASTTree id_tree=null;
        ASTTree string_literal29_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_146=new RewriteRuleTokenStream(adaptor,"token 146");
        RewriteRuleSubtreeStream stream_implementsClause=new RewriteRuleSubtreeStream(adaptor,"rule implementsClause");
        RewriteRuleSubtreeStream stream_enumBlock=new RewriteRuleSubtreeStream(adaptor,"rule enumBlock");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:470:3: ( 'enum' id= IDENT (ic= implementsClause )? eb= enumBlock -> ^( ENUM_DEF $id ( $ic)? $eb) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:471:3: 'enum' id= IDENT (ic= implementsClause )? eb= enumBlock
            {
            string_literal29=(Token)match(input,146,FOLLOW_146_in_enumDefinition1576); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_146.add(string_literal29);
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_enumDefinition1580); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:473:5: (ic= implementsClause )?
            int alt18=2;
            int LA18_0 = input.LA(1);
            if ( (LA18_0==154) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:473:5: ic= implementsClause
                    {
                    pushFollow(FOLLOW_implementsClause_in_enumDefinition1591);
                    ic=implementsClause();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_implementsClause.add(ic.getTree());
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_enumBlock_in_enumDefinition1603);
            eb=enumBlock();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_enumBlock.add(eb.getTree());
            // AST REWRITE
            // elements: id, ic, eb
            // token labels: id
            // rule labels: ic, retval, eb
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_ic=new RewriteRuleSubtreeStream(adaptor,"rule ic",ic!=null?ic.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_eb=new RewriteRuleSubtreeStream(adaptor,"rule eb",eb!=null?eb.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 476:5: -> ^( ENUM_DEF $id ( $ic)? $eb)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:476:8: ^( ENUM_DEF $id ( $ic)? $eb)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ENUM_DEF, "ENUM_DEF"), root_1);
                adaptor.addChild(root_1, stream_id.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:476:24: ( $ic)?
                if ( stream_ic.hasNext() ) {
                    adaptor.addChild(root_1, stream_ic.nextTree());
                }
                stream_ic.reset();
        
                adaptor.addChild(root_1, ama);
                adaptor.addChild(root_1, stream_eb.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, enumDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "enumBlock"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:485:1: enumBlock : LCURLY (ec= enumConst (c= COMMA ec= enumConst )* )? (c2= COMMA )? (sem= SEMI ( field[true] | SEMI )* )? rc= RCURLY -> ^( OBJBLOCK ( enumConst )* ( field )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.enumBlock_return enumBlock() throws RecognitionException {
        JavaParser.enumBlock_return retval = new JavaParser.enumBlock_return();
        retval.start = input.LT(1);
        int enumBlock_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token c=null;
        Token c2=null;
        Token sem=null;
        Token rc=null;
        Token LCURLY30=null;
        Token SEMI32=null;
        ParserRuleReturnScope ec =null;
        ParserRuleReturnScope field31 =null;
        
        ASTTree c_tree=null;
        ASTTree c2_tree=null;
        ASTTree sem_tree=null;
        ASTTree rc_tree=null;
        ASTTree LCURLY30_tree=null;
        ASTTree SEMI32_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
        RewriteRuleSubtreeStream stream_enumConst=new RewriteRuleSubtreeStream(adaptor,"rule enumConst");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:489:3: ( LCURLY (ec= enumConst (c= COMMA ec= enumConst )* )? (c2= COMMA )? (sem= SEMI ( field[true] | SEMI )* )? rc= RCURLY -> ^( OBJBLOCK ( enumConst )* ( field )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:490:3: LCURLY (ec= enumConst (c= COMMA ec= enumConst )* )? (c2= COMMA )? (sem= SEMI ( field[true] | SEMI )* )? rc= RCURLY
            {
            LCURLY30=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_enumBlock1651); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY30);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:490:10: (ec= enumConst (c= COMMA ec= enumConst )* )?
            int alt20=2;
            int LA20_0 = input.LA(1);
            if ( (LA20_0==AT||LA20_0==IDENT) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:490:11: ec= enumConst (c= COMMA ec= enumConst )*
                    {
                    pushFollow(FOLLOW_enumConst_in_enumBlock1656);
                    ec=enumConst();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumConst.add(ec.getTree());
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:490:24: (c= COMMA ec= enumConst )*
                    loop19:
                    while (true) {
                        int alt19=2;
                        int LA19_0 = input.LA(1);
                        if ( (LA19_0==COMMA) ) {
                            int LA19_1 = input.LA(2);
                            if ( (LA19_1==AT||LA19_1==IDENT) ) {
                                alt19=1;
                            }
        
                        }
        
                        switch (alt19) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:490:25: c= COMMA ec= enumConst
                            {
                            c=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumBlock1661); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(c);
        
                            if ( state.backtracking==0 ) {
                                                            attachTrailingComments((ec!=null?((ASTTree)ec.getTree()):null), c);
                                                           }
                            pushFollow(FOLLOW_enumConst_in_enumBlock1705);
                            ec=enumConst();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumConst.add(ec.getTree());
                            }
                            break;
        
                        default :
                            break loop19;
                        }
                    }
        
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:494:24: (c2= COMMA )?
            int alt21=2;
            int LA21_0 = input.LA(1);
            if ( (LA21_0==COMMA) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:494:25: c2= COMMA
                    {
                    c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_enumBlock1714); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(c2);
        
                    if ( state.backtracking==0 ) {
                                                     attachTrailingComments((ec!=null?((ASTTree)ec.getTree()):null), c2);
                                                    }
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:498:3: (sem= SEMI ( field[true] | SEMI )* )?
            int alt23=2;
            int LA23_0 = input.LA(1);
            if ( (LA23_0==SEMI) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:499:5: sem= SEMI ( field[true] | SEMI )*
                    {
                    sem=(Token)match(input,SEMI,FOLLOW_SEMI_in_enumBlock1764); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(sem);
        
                    if ( state.backtracking==0 ) {attachTrailingComments((ec!=null?((ASTTree)ec.getTree()):null), sem);}
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:500:5: ( field[true] | SEMI )*
                    loop22:
                    while (true) {
                        int alt22=3;
                        int LA22_0 = input.LA(1);
                        if ( (LA22_0==AT||LA22_0==DEFAULT||LA22_0==IDENT||LA22_0==LCURLY||LA22_0==LT||LA22_0==133||LA22_0==135||LA22_0==137||(LA22_0 >= 140 && LA22_0 <= 141)||LA22_0==144||LA22_0==146||LA22_0==149||LA22_0==151||(LA22_0 >= 157 && LA22_0 <= 160)||(LA22_0 >= 163 && LA22_0 <= 168)||LA22_0==170||LA22_0==172||LA22_0==175||(LA22_0 >= 178 && LA22_0 <= 179)) ) {
                            alt22=1;
                        }
                        else if ( (LA22_0==SEMI) ) {
                            alt22=2;
                        }
        
                        switch (alt22) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:501:7: field[true]
                            {
                            pushFollow(FOLLOW_field_in_enumBlock1780);
                            field31=field(true);
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_field.add(field31.getTree());
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:502:9: SEMI
                            {
                            SEMI32=(Token)match(input,SEMI,FOLLOW_SEMI_in_enumBlock1791); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_SEMI.add(SEMI32);
        
                            }
                            break;
        
                        default :
                            break loop22;
                        }
                    }
        
                    }
                    break;
        
            }
        
            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_enumBlock1809); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(rc);
        
            // AST REWRITE
            // elements: field, enumConst
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 506:5: -> ^( OBJBLOCK ( enumConst )* ( field )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:506:8: ^( OBJBLOCK ( enumConst )* ( field )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(OBJBLOCK, "OBJBLOCK"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:506:19: ( enumConst )*
                while ( stream_enumConst.hasNext() ) {
                    adaptor.addChild(root_1, stream_enumConst.nextTree());
                }
                stream_enumConst.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:506:30: ( field )*
                while ( stream_field.hasNext() ) {
                    adaptor.addChild(root_1, stream_field.nextTree());
                }
                stream_field.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachAboveComments(retval.tree, rc);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, enumBlock_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "enumConst"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:512:1: enumConst : ( annotation )* IDENT ( enumConstInit )? ( classBlock[true] )? -> ^( ENUM_CONST ( annotation )* IDENT ( enumConstInit )? ( classBlock )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.enumConst_return enumConst() throws RecognitionException {
        JavaParser.enumConst_return retval = new JavaParser.enumConst_return();
        retval.start = input.LT(1);
        int enumConst_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token IDENT34=null;
        ParserRuleReturnScope annotation33 =null;
        ParserRuleReturnScope enumConstInit35 =null;
        ParserRuleReturnScope classBlock36 =null;
        
        ASTTree IDENT34_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_enumConstInit=new RewriteRuleSubtreeStream(adaptor,"rule enumConstInit");
        RewriteRuleSubtreeStream stream_classBlock=new RewriteRuleSubtreeStream(adaptor,"rule classBlock");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:517:3: ( ( annotation )* IDENT ( enumConstInit )? ( classBlock[true] )? -> ^( ENUM_CONST ( annotation )* IDENT ( enumConstInit )? ( classBlock )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:3: ( annotation )* IDENT ( enumConstInit )? ( classBlock[true] )?
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:3: ( annotation )*
            loop24:
            while (true) {
                int alt24=2;
                int LA24_0 = input.LA(1);
                if ( (LA24_0==AT) ) {
                    alt24=1;
                }
        
                switch (alt24) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:3: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_enumConst1849);
                    annotation33=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation33.getTree());
                    }
                    break;
        
                default :
                    break loop24;
                }
            }
        
            IDENT34=(Token)match(input,IDENT,FOLLOW_IDENT_in_enumConst1852); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(IDENT34);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:21: ( enumConstInit )?
            int alt25=2;
            int LA25_0 = input.LA(1);
            if ( (LA25_0==LPAREN) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:21: enumConstInit
                    {
                    pushFollow(FOLLOW_enumConstInit_in_enumConst1854);
                    enumConstInit35=enumConstInit();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumConstInit.add(enumConstInit35.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:36: ( classBlock[true] )?
            int alt26=2;
            int LA26_0 = input.LA(1);
            if ( (LA26_0==LCURLY) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:518:36: classBlock[true]
                    {
                    pushFollow(FOLLOW_classBlock_in_enumConst1857);
                    classBlock36=classBlock(true);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classBlock.add(classBlock36.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: classBlock, enumConstInit, IDENT, annotation
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 519:5: -> ^( ENUM_CONST ( annotation )* IDENT ( enumConstInit )? ( classBlock )? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:519:8: ^( ENUM_CONST ( annotation )* IDENT ( enumConstInit )? ( classBlock )? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ENUM_CONST, "ENUM_CONST"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:519:21: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                adaptor.addChild(root_1, stream_IDENT.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:519:39: ( enumConstInit )?
                if ( stream_enumConstInit.hasNext() ) {
                    adaptor.addChild(root_1, stream_enumConstInit.nextTree());
                }
                stream_enumConstInit.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:519:54: ( classBlock )?
                if ( stream_classBlock.hasNext() ) {
                    adaptor.addChild(root_1, stream_classBlock.nextTree());
                }
                stream_classBlock.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachAboveComments(retval.tree, (retval.start));
            attachTrailingComments(retval.tree, (retval.stop));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, enumConst_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "enumConstInit"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:524:1: enumConstInit : lp= LPAREN al= argList rp= RPAREN -> $al;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.enumConstInit_return enumConstInit() throws RecognitionException {
        JavaParser.enumConstInit_return retval = new JavaParser.enumConstInit_return();
        retval.start = input.LT(1);
        int enumConstInit_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lp=null;
        Token rp=null;
        ParserRuleReturnScope al =null;
        
        ASTTree lp_tree=null;
        ASTTree rp_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_argList=new RewriteRuleSubtreeStream(adaptor,"rule argList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:529:3: (lp= LPAREN al= argList rp= RPAREN -> $al)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:530:3: lp= LPAREN al= argList rp= RPAREN
            {
            lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_enumConstInit1905); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(lp);
        
            pushFollow(FOLLOW_argList_in_enumConstInit1909);
            al=argList();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_argList.add(al.getTree());
            rp=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_enumConstInit1913); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(rp);
        
            // AST REWRITE
            // elements: al
            // token labels: 
            // rule labels: al, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_al=new RewriteRuleSubtreeStream(adaptor,"rule al",al!=null?al.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 531:5: -> $al
            {
                adaptor.addChild(root_0, stream_al.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(this.getSource(
                    this.adjustStartToken((al!=null?(al.start):null)), this.adjustStopToken((al!=null?(al.stop):null))));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, enumConstInit_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "packageDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:536:1: packageDefinition : ( annotation )* p= 'package' identifier ( SEMI )+ -> ^( PACKAGE_DEF[$p] identifier ( annotation )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.packageDefinition_return packageDefinition() throws RecognitionException {
        JavaParser.packageDefinition_return retval = new JavaParser.packageDefinition_return();
        retval.start = input.LT(1);
        int packageDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token p=null;
        Token SEMI39=null;
        ParserRuleReturnScope annotation37 =null;
        ParserRuleReturnScope identifier38 =null;
        
        ASTTree p_tree=null;
        ASTTree SEMI39_tree=null;
        RewriteRuleTokenStream stream_162=new RewriteRuleTokenStream(adaptor,"token 162");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:3: ( ( annotation )* p= 'package' identifier ( SEMI )+ -> ^( PACKAGE_DEF[$p] identifier ( annotation )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:5: ( annotation )* p= 'package' identifier ( SEMI )+
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:5: ( annotation )*
            loop27:
            while (true) {
                int alt27=2;
                int LA27_0 = input.LA(1);
                if ( (LA27_0==AT) ) {
                    alt27=1;
                }
        
                switch (alt27) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:5: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_packageDefinition1937);
                    annotation37=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation37.getTree());
                    }
                    break;
        
                default :
                    break loop27;
                }
            }
        
            p=(Token)match(input,162,FOLLOW_162_in_packageDefinition1942); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_162.add(p);
        
            pushFollow(FOLLOW_identifier_in_packageDefinition1944);
            identifier38=identifier();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifier.add(identifier38.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:40: ( SEMI )+
            int cnt28=0;
            loop28:
            while (true) {
                int alt28=2;
                int LA28_0 = input.LA(1);
                if ( (LA28_0==SEMI) ) {
                    alt28=1;
                }
        
                switch (alt28) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:40: SEMI
                    {
                    SEMI39=(Token)match(input,SEMI,FOLLOW_SEMI_in_packageDefinition1946); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI39);
        
                    }
                    break;
        
                default :
                    if ( cnt28 >= 1 ) break loop28;
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    EarlyExitException eee = new EarlyExitException(28, input);
                    throw eee;
                }
                cnt28++;
            }
        
            // AST REWRITE
            // elements: annotation, identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 537:46: -> ^( PACKAGE_DEF[$p] identifier ( annotation )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:49: ^( PACKAGE_DEF[$p] identifier ( annotation )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(PACKAGE_DEF, p), root_1);
                adaptor.addChild(root_1, stream_identifier.nextTree());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:537:78: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, packageDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "importDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:542:1: importDefinition : i= 'import' (st= 'static' )? identifierStar ( SEMI )+ -> ^( IMPORT[$i] ( $st)? identifierStar ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.importDefinition_return importDefinition() throws RecognitionException {
        JavaParser.importDefinition_return retval = new JavaParser.importDefinition_return();
        retval.start = input.LT(1);
        int importDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token i=null;
        Token st=null;
        Token SEMI41=null;
        ParserRuleReturnScope identifierStar40 =null;
        
        ASTTree i_tree=null;
        ASTTree st_tree=null;
        ASTTree SEMI41_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_155=new RewriteRuleTokenStream(adaptor,"token 155");
        RewriteRuleTokenStream stream_167=new RewriteRuleTokenStream(adaptor,"token 167");
        RewriteRuleSubtreeStream stream_identifierStar=new RewriteRuleSubtreeStream(adaptor,"rule identifierStar");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:543:3: (i= 'import' (st= 'static' )? identifierStar ( SEMI )+ -> ^( IMPORT[$i] ( $st)? identifierStar ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:544:3: i= 'import' (st= 'static' )? identifierStar ( SEMI )+
            {
            i=(Token)match(input,155,FOLLOW_155_in_importDefinition1978); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_155.add(i);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:544:14: (st= 'static' )?
            int alt29=2;
            int LA29_0 = input.LA(1);
            if ( (LA29_0==167) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:544:15: st= 'static'
                    {
                    st=(Token)match(input,167,FOLLOW_167_in_importDefinition1983); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_167.add(st);
        
                    if ( state.backtracking==0 ) {
                                              st.setType(STATIC);
                                             }
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_identifierStar_in_importDefinition2015);
            identifierStar40=identifierStar();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifierStar.add(identifierStar40.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:547:45: ( SEMI )+
            int cnt30=0;
            loop30:
            while (true) {
                int alt30=2;
                int LA30_0 = input.LA(1);
                if ( (LA30_0==SEMI) ) {
                    alt30=1;
                }
        
                switch (alt30) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:547:45: SEMI
                    {
                    SEMI41=(Token)match(input,SEMI,FOLLOW_SEMI_in_importDefinition2017); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI41);
        
                    }
                    break;
        
                default :
                    if ( cnt30 >= 1 ) break loop30;
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    EarlyExitException eee = new EarlyExitException(30, input);
                    throw eee;
                }
                cnt30++;
            }
        
            // AST REWRITE
            // elements: st, identifierStar
            // token labels: st
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_st=new RewriteRuleTokenStream(adaptor,"token st",st);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 548:5: -> ^( IMPORT[$i] ( $st)? identifierStar )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:548:8: ^( IMPORT[$i] ( $st)? identifierStar )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(IMPORT, i), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:548:22: ( $st)?
                if ( stream_st.hasNext() ) {
                    adaptor.addChild(root_1, stream_st.nextNode());
                }
                stream_st.reset();
        
                adaptor.addChild(root_1, stream_identifierStar.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, importDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:553:1: typeDefinition : ama= annotmodannot (def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] |def= enumDefinition[$ama.tree] ) ( SEMI )* -> $def;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeDefinition_return typeDefinition() throws RecognitionException {
        JavaParser.typeDefinition_return retval = new JavaParser.typeDefinition_return();
        retval.start = input.LT(1);
        int typeDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token SEMI42=null;
        ParserRuleReturnScope ama =null;
        ParserRuleReturnScope def =null;
        
        ASTTree SEMI42_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_annotmodannot=new RewriteRuleSubtreeStream(adaptor,"rule annotmodannot");
        RewriteRuleSubtreeStream stream_interfaceDefinition=new RewriteRuleSubtreeStream(adaptor,"rule interfaceDefinition");
        RewriteRuleSubtreeStream stream_annotationTypeDefinition=new RewriteRuleSubtreeStream(adaptor,"rule annotationTypeDefinition");
        RewriteRuleSubtreeStream stream_classDefinition=new RewriteRuleSubtreeStream(adaptor,"rule classDefinition");
        RewriteRuleSubtreeStream stream_enumDefinition=new RewriteRuleSubtreeStream(adaptor,"rule enumDefinition");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:557:3: (ama= annotmodannot (def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] |def= enumDefinition[$ama.tree] ) ( SEMI )* -> $def)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:558:3: ama= annotmodannot (def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] |def= enumDefinition[$ama.tree] ) ( SEMI )*
            {
            pushFollow(FOLLOW_annotmodannot_in_typeDefinition2060);
            ama=annotmodannot();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotmodannot.add(ama.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:559:3: (def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] |def= enumDefinition[$ama.tree] )
            int alt31=4;
            switch ( input.LA(1) ) {
            case 141:
                {
                alt31=1;
                }
                break;
            case 158:
                {
                alt31=2;
                }
                break;
            case AT:
                {
                alt31=3;
                }
                break;
            case 146:
                {
                alt31=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);
                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:560:5: def= classDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_classDefinition_in_typeDefinition2072);
                    def=classDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classDefinition.add(def.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:561:7: def= interfaceDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_interfaceDefinition_in_typeDefinition2083);
                    def=interfaceDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_interfaceDefinition.add(def.getTree());
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:562:7: def= annotationTypeDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_annotationTypeDefinition_in_typeDefinition2094);
                    def=annotationTypeDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotationTypeDefinition.add(def.getTree());
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:563:7: def= enumDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_enumDefinition_in_typeDefinition2105);
                    def=enumDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_enumDefinition.add(def.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:564:5: ( SEMI )*
            loop32:
            while (true) {
                int alt32=2;
                int LA32_0 = input.LA(1);
                if ( (LA32_0==SEMI) ) {
                    alt32=1;
                }
        
                switch (alt32) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:564:5: SEMI
                    {
                    SEMI42=(Token)match(input,SEMI,FOLLOW_SEMI_in_typeDefinition2112); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI42);
        
                    }
                    break;
        
                default :
                    break loop32;
                }
            }
        
            // AST REWRITE
            // elements: def
            // token labels: 
            // rule labels: retval, def
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_def=new RewriteRuleSubtreeStream(adaptor,"rule def",def!=null?def.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 565:5: -> $def
            {
                adaptor.addChild(root_0, stream_def.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachAboveComments(retval.tree, (retval.start));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, typeDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "declaration"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:571:1: declaration : ama= annotmodannot t= typeSpec v= variableDefinitions[$ama.tree, $t.tree] -> $v;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.declaration_return declaration() throws RecognitionException {
        JavaParser.declaration_return retval = new JavaParser.declaration_return();
        retval.start = input.LT(1);
        int declaration_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope ama =null;
        ParserRuleReturnScope t =null;
        ParserRuleReturnScope v =null;
        
        RewriteRuleSubtreeStream stream_annotmodannot=new RewriteRuleSubtreeStream(adaptor,"rule annotmodannot");
        RewriteRuleSubtreeStream stream_typeSpec=new RewriteRuleSubtreeStream(adaptor,"rule typeSpec");
        RewriteRuleSubtreeStream stream_variableDefinitions=new RewriteRuleSubtreeStream(adaptor,"rule variableDefinitions");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:572:3: (ama= annotmodannot t= typeSpec v= variableDefinitions[$ama.tree, $t.tree] -> $v)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:573:3: ama= annotmodannot t= typeSpec v= variableDefinitions[$ama.tree, $t.tree]
            {
            pushFollow(FOLLOW_annotmodannot_in_declaration2141);
            ama=annotmodannot();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotmodannot.add(ama.getTree());
            pushFollow(FOLLOW_typeSpec_in_declaration2145);
            t=typeSpec();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeSpec.add(t.getTree());
            pushFollow(FOLLOW_variableDefinitions_in_declaration2149);
            v=variableDefinitions((ama!=null?((ASTTree)ama.getTree()):null), (t!=null?((ASTTree)t.getTree()):null));
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableDefinitions.add(v.getTree());
            // AST REWRITE
            // elements: v
            // token labels: 
            // rule labels: v, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_v=new RewriteRuleSubtreeStream(adaptor,"rule v",v!=null?v.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 574:5: -> $v
            {
                adaptor.addChild(root_0, stream_v.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, declaration_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeSpec"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:580:1: typeSpec : ( classTypeSpec | builtInTypeSpec );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeSpec_return typeSpec() throws RecognitionException {
        JavaParser.typeSpec_return retval = new JavaParser.typeSpec_return();
        retval.start = input.LT(1);
        int typeSpec_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope classTypeSpec43 =null;
        ParserRuleReturnScope builtInTypeSpec44 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:586:3: ( classTypeSpec | builtInTypeSpec )
            int alt33=2;
            switch ( input.LA(1) ) {
            case AT:
                {
                int LA33_1 = input.LA(2);
                if ( (synpred44_Java()) ) {
                    alt33=1;
                }
                else if ( (true) ) {
                    alt33=2;
                }
        
                }
                break;
            case IDENT:
                {
                alt33=1;
                }
                break;
            case 135:
            case 137:
            case 140:
            case 144:
            case 151:
            case 157:
            case 159:
            case 166:
                {
                alt33=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);
                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:587:3: classTypeSpec
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_classTypeSpec_in_typeSpec2183);
                    classTypeSpec43=classTypeSpec();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classTypeSpec43.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:588:5: builtInTypeSpec
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_builtInTypeSpec_in_typeSpec2189);
                    builtInTypeSpec44=builtInTypeSpec();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, builtInTypeSpec44.getTree());
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            // Get the source code
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, typeSpec_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "classTypeSpec"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:592:1: classTypeSpec : coi= classOrInterfaceType ! declaratorBrackets[$coi.tree] ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.classTypeSpec_return classTypeSpec() throws RecognitionException {
        JavaParser.classTypeSpec_return retval = new JavaParser.classTypeSpec_return();
        retval.start = input.LT(1);
        int classTypeSpec_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope coi =null;
        ParserRuleReturnScope declaratorBrackets45 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:593:3: (coi= classOrInterfaceType ! declaratorBrackets[$coi.tree] )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:593:5: coi= classOrInterfaceType ! declaratorBrackets[$coi.tree]
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_classOrInterfaceType_in_classTypeSpec2205);
            coi=classOrInterfaceType();
            state._fsp--;
            if (state.failed) return retval;
            pushFollow(FOLLOW_declaratorBrackets_in_classTypeSpec2208);
            declaratorBrackets45=declaratorBrackets((coi!=null?((ASTTree)coi.getTree()):null));
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, declaratorBrackets45.getTree());
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, classTypeSpec_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "declaratorBrackets"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:596:1: declaratorBrackets[Object typ] : (ad+= arrayDeclarator )* -> ^( ( $ad)* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.declaratorBrackets_return declaratorBrackets(final Object typ) throws RecognitionException {
        JavaParser.declaratorBrackets_return retval = new JavaParser.declaratorBrackets_return();
        retval.start = input.LT(1);
        int declaratorBrackets_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        List<Object> list_ad=null;
        RuleReturnScope ad = null;
        RewriteRuleSubtreeStream stream_arrayDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule arrayDeclarator");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:597:3: ( (ad+= arrayDeclarator )* -> ^( ( $ad)* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:597:5: (ad+= arrayDeclarator )*
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:597:7: (ad+= arrayDeclarator )*
            loop34:
            while (true) {
                int alt34=2;
                int LA34_0 = input.LA(1);
                if ( (LA34_0==AT||LA34_0==LBRACK) ) {
                    alt34=1;
                }
        
                switch (alt34) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:597:7: ad+= arrayDeclarator
                    {
                    pushFollow(FOLLOW_arrayDeclarator_in_declaratorBrackets2225);
                    ad=arrayDeclarator();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arrayDeclarator.add(ad.getTree());
                    if (list_ad==null) list_ad=new ArrayList<Object>();
                    list_ad.add(ad.getTree());
                    }
                    break;
        
                default :
                    break loop34;
                }
            }
        
            // AST REWRITE
            // elements: ad
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: ad
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_ad=new RewriteRuleSubtreeStream(adaptor,"token ad",list_ad);
            root_0 = (ASTTree)adaptor.nil();
            // 597:26: -> ^( ( $ad)* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:597:29: ^( ( $ad)* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot(typ, root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:597:39: ( $ad)*
                while ( stream_ad.hasNext() ) {
                    adaptor.addChild(root_1, stream_ad.nextTree());
                }
                stream_ad.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, declaratorBrackets_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "arrayDeclarator"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:600:1: arrayDeclarator : ( annotation )* lb= LBRACK RBRACK -> ^( ARRAY_DECLARATOR[$lb] ( annotation )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.arrayDeclarator_return arrayDeclarator() throws RecognitionException {
        JavaParser.arrayDeclarator_return retval = new JavaParser.arrayDeclarator_return();
        retval.start = input.LT(1);
        int arrayDeclarator_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lb=null;
        Token RBRACK47=null;
        ParserRuleReturnScope annotation46 =null;
        
        ASTTree lb_tree=null;
        ASTTree RBRACK47_tree=null;
        RewriteRuleTokenStream stream_RBRACK=new RewriteRuleTokenStream(adaptor,"token RBRACK");
        RewriteRuleTokenStream stream_LBRACK=new RewriteRuleTokenStream(adaptor,"token LBRACK");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:601:3: ( ( annotation )* lb= LBRACK RBRACK -> ^( ARRAY_DECLARATOR[$lb] ( annotation )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:601:5: ( annotation )* lb= LBRACK RBRACK
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:601:5: ( annotation )*
            loop35:
            while (true) {
                int alt35=2;
                int LA35_0 = input.LA(1);
                if ( (LA35_0==AT) ) {
                    alt35=1;
                }
        
                switch (alt35) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:601:5: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_arrayDeclarator2251);
                    annotation46=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation46.getTree());
                    }
                    break;
        
                default :
                    break loop35;
                }
            }
        
            lb=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_arrayDeclarator2256); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LBRACK.add(lb);
        
            RBRACK47=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_arrayDeclarator2258); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK47);
        
            // AST REWRITE
            // elements: annotation
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 601:34: -> ^( ARRAY_DECLARATOR[$lb] ( annotation )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:601:37: ^( ARRAY_DECLARATOR[$lb] ( annotation )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ARRAY_DECLARATOR, lb), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:601:61: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 24, arrayDeclarator_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "builtInTypeSpec"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:605:1: builtInTypeSpec : ( annotation )* builtInType ( arrayDeclarator )* -> ^( TYPE builtInType ( annotation )* ( arrayDeclarator )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.builtInTypeSpec_return builtInTypeSpec() throws RecognitionException {
        JavaParser.builtInTypeSpec_return retval = new JavaParser.builtInTypeSpec_return();
        retval.start = input.LT(1);
        int builtInTypeSpec_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope annotation48 =null;
        ParserRuleReturnScope builtInType49 =null;
        ParserRuleReturnScope arrayDeclarator50 =null;
        
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_builtInType=new RewriteRuleSubtreeStream(adaptor,"rule builtInType");
        RewriteRuleSubtreeStream stream_arrayDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule arrayDeclarator");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:3: ( ( annotation )* builtInType ( arrayDeclarator )* -> ^( TYPE builtInType ( annotation )* ( arrayDeclarator )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:5: ( annotation )* builtInType ( arrayDeclarator )*
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:5: ( annotation )*
            loop36:
            while (true) {
                int alt36=2;
                int LA36_0 = input.LA(1);
                if ( (LA36_0==AT) ) {
                    alt36=1;
                }
        
                switch (alt36) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:5: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_builtInTypeSpec2284);
                    annotation48=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation48.getTree());
                    }
                    break;
        
                default :
                    break loop36;
                }
            }
        
            pushFollow(FOLLOW_builtInType_in_builtInTypeSpec2287);
            builtInType49=builtInType();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_builtInType.add(builtInType49.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:29: ( arrayDeclarator )*
            loop37:
            while (true) {
                int alt37=2;
                int LA37_0 = input.LA(1);
                if ( (LA37_0==AT||LA37_0==LBRACK) ) {
                    alt37=1;
                }
        
                switch (alt37) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:29: arrayDeclarator
                    {
                    pushFollow(FOLLOW_arrayDeclarator_in_builtInTypeSpec2289);
                    arrayDeclarator50=arrayDeclarator();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arrayDeclarator.add(arrayDeclarator50.getTree());
                    }
                    break;
        
                default :
                    break loop37;
                }
            }
        
            // AST REWRITE
            // elements: arrayDeclarator, builtInType, annotation
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 606:46: -> ^( TYPE builtInType ( annotation )* ( arrayDeclarator )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:49: ^( TYPE builtInType ( annotation )* ( arrayDeclarator )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPE, "TYPE"), root_1);
                adaptor.addChild(root_1, stream_builtInType.nextTree());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:68: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:606:80: ( arrayDeclarator )*
                while ( stream_arrayDeclarator.hasNext() ) {
                    adaptor.addChild(root_1, stream_arrayDeclarator.nextTree());
                }
                stream_arrayDeclarator.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 25, builtInTypeSpec_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "classOrInterfaceType"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:610:1: classOrInterfaceType : ( simpleClassOrInterfaceType -> simpleClassOrInterfaceType ) ( options {greedy=true; } :d= DOT scoi= simpleClassOrInterfaceType -> ^( DOT[$d] $classOrInterfaceType $scoi) )* -> {dotSeen}? ^( TYPE $classOrInterfaceType) -> $classOrInterfaceType;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.classOrInterfaceType_return classOrInterfaceType() throws RecognitionException {
        JavaParser.classOrInterfaceType_return retval = new JavaParser.classOrInterfaceType_return();
        retval.start = input.LT(1);
        int classOrInterfaceType_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token d=null;
        ParserRuleReturnScope scoi =null;
        ParserRuleReturnScope simpleClassOrInterfaceType51 =null;
        
        ASTTree d_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_simpleClassOrInterfaceType=new RewriteRuleSubtreeStream(adaptor,"rule simpleClassOrInterfaceType");
        
        
        // add TYPE node if a DOT has been seen to be sure to return a TYPE node but not a (TYPE (TYPE ident))
        boolean dotSeen = false;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:618:3: ( ( simpleClassOrInterfaceType -> simpleClassOrInterfaceType ) ( options {greedy=true; } :d= DOT scoi= simpleClassOrInterfaceType -> ^( DOT[$d] $classOrInterfaceType $scoi) )* -> {dotSeen}? ^( TYPE $classOrInterfaceType) -> $classOrInterfaceType)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:619:3: ( simpleClassOrInterfaceType -> simpleClassOrInterfaceType ) ( options {greedy=true; } :d= DOT scoi= simpleClassOrInterfaceType -> ^( DOT[$d] $classOrInterfaceType $scoi) )*
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:619:3: ( simpleClassOrInterfaceType -> simpleClassOrInterfaceType )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:619:4: simpleClassOrInterfaceType
            {
            pushFollow(FOLLOW_simpleClassOrInterfaceType_in_classOrInterfaceType2332);
            simpleClassOrInterfaceType51=simpleClassOrInterfaceType();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_simpleClassOrInterfaceType.add(simpleClassOrInterfaceType51.getTree());
            // AST REWRITE
            // elements: simpleClassOrInterfaceType
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 619:31: -> simpleClassOrInterfaceType
            {
                adaptor.addChild(root_0, stream_simpleClassOrInterfaceType.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:620:3: ( options {greedy=true; } :d= DOT scoi= simpleClassOrInterfaceType -> ^( DOT[$d] $classOrInterfaceType $scoi) )*
            loop38:
            while (true) {
                int alt38=2;
                int LA38_0 = input.LA(1);
                if ( (LA38_0==DOT) ) {
                    alt38=1;
                }
        
                switch (alt38) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:621:5: d= DOT scoi= simpleClassOrInterfaceType
                    {
                    d=(Token)match(input,DOT,FOLLOW_DOT_in_classOrInterfaceType2358); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT.add(d);
        
                    if ( state.backtracking==0 ) { dotSeen = true; }
                    pushFollow(FOLLOW_simpleClassOrInterfaceType_in_classOrInterfaceType2368);
                    scoi=simpleClassOrInterfaceType();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_simpleClassOrInterfaceType.add(scoi.getTree());
                    // AST REWRITE
                    // elements: DOT, classOrInterfaceType, scoi
                    // token labels: 
                    // rule labels: retval, scoi
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
                    RewriteRuleSubtreeStream stream_scoi=new RewriteRuleSubtreeStream(adaptor,"rule scoi",scoi!=null?scoi.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 622:37: -> ^( DOT[$d] $classOrInterfaceType $scoi)
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:622:40: ^( DOT[$d] $classOrInterfaceType $scoi)
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(DOT, d), root_1);
                        adaptor.addChild(root_1, stream_retval.nextTree());
                        adaptor.addChild(root_1, stream_scoi.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
                default :
                    break loop38;
                }
            }
        
            // AST REWRITE
            // elements: classOrInterfaceType, classOrInterfaceType
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 623:5: -> {dotSeen}? ^( TYPE $classOrInterfaceType)
            if (dotSeen) {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:623:19: ^( TYPE $classOrInterfaceType)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPE, "TYPE"), root_1);
                adaptor.addChild(root_1, stream_retval.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
            else // 624:5: -> $classOrInterfaceType
            {
                adaptor.addChild(root_0, stream_retval.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 26, classOrInterfaceType_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "simpleClassOrInterfaceType"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:627:1: simpleClassOrInterfaceType : ( annotation )* IDENT ( typeArguments )? -> ^( TYPE IDENT ( annotation )* ( typeArguments )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.simpleClassOrInterfaceType_return simpleClassOrInterfaceType() throws RecognitionException {
        JavaParser.simpleClassOrInterfaceType_return retval = new JavaParser.simpleClassOrInterfaceType_return();
        retval.start = input.LT(1);
        int simpleClassOrInterfaceType_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token IDENT53=null;
        ParserRuleReturnScope annotation52 =null;
        ParserRuleReturnScope typeArguments54 =null;
        
        ASTTree IDENT53_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_typeArguments=new RewriteRuleSubtreeStream(adaptor,"rule typeArguments");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:628:3: ( ( annotation )* IDENT ( typeArguments )? -> ^( TYPE IDENT ( annotation )* ( typeArguments )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:3: ( annotation )* IDENT ( typeArguments )?
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:3: ( annotation )*
            loop39:
            while (true) {
                int alt39=2;
                int LA39_0 = input.LA(1);
                if ( (LA39_0==AT) ) {
                    alt39=1;
                }
        
                switch (alt39) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:3: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_simpleClassOrInterfaceType2423);
                    annotation52=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation52.getTree());
                    }
                    break;
        
                default :
                    break loop39;
                }
            }
        
            IDENT53=(Token)match(input,IDENT,FOLLOW_IDENT_in_simpleClassOrInterfaceType2426); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(IDENT53);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:21: ( typeArguments )?
            int alt40=2;
            int LA40_0 = input.LA(1);
            if ( (LA40_0==LT) ) {
                int LA40_1 = input.LA(2);
                if ( (synpred51_Java()) ) {
                    alt40=1;
                }
            }
            switch (alt40) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:21: typeArguments
                    {
                    pushFollow(FOLLOW_typeArguments_in_simpleClassOrInterfaceType2428);
                    typeArguments54=typeArguments();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeArguments.add(typeArguments54.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: annotation, IDENT, typeArguments
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 630:5: -> ^( TYPE IDENT ( annotation )* ( typeArguments )? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:630:8: ^( TYPE IDENT ( annotation )* ( typeArguments )? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPE, "TYPE"), root_1);
                adaptor.addChild(root_1, stream_IDENT.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:630:21: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:630:33: ( typeArguments )?
                if ( stream_typeArguments.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeArguments.nextTree());
                }
                stream_typeArguments.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 27, simpleClassOrInterfaceType_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "builtInType"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:634:1: builtInType : t= ( 'boolean' | 'byte' | 'char' | 'short' | 'int' | 'float' | 'long' | 'double' ) -> ^( BUILT_IN[$t] ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.builtInType_return builtInType() throws RecognitionException {
        JavaParser.builtInType_return retval = new JavaParser.builtInType_return();
        retval.start = input.LT(1);
        int builtInType_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token t=null;
        
        ASTTree t_tree=null;
        RewriteRuleTokenStream stream_144=new RewriteRuleTokenStream(adaptor,"token 144");
        RewriteRuleTokenStream stream_135=new RewriteRuleTokenStream(adaptor,"token 135");
        RewriteRuleTokenStream stream_151=new RewriteRuleTokenStream(adaptor,"token 151");
        RewriteRuleTokenStream stream_137=new RewriteRuleTokenStream(adaptor,"token 137");
        RewriteRuleTokenStream stream_159=new RewriteRuleTokenStream(adaptor,"token 159");
        RewriteRuleTokenStream stream_166=new RewriteRuleTokenStream(adaptor,"token 166");
        RewriteRuleTokenStream stream_157=new RewriteRuleTokenStream(adaptor,"token 157");
        RewriteRuleTokenStream stream_140=new RewriteRuleTokenStream(adaptor,"token 140");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:3: (t= ( 'boolean' | 'byte' | 'char' | 'short' | 'int' | 'float' | 'long' | 'double' ) -> ^( BUILT_IN[$t] ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:5: t= ( 'boolean' | 'byte' | 'char' | 'short' | 'int' | 'float' | 'long' | 'double' )
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:7: ( 'boolean' | 'byte' | 'char' | 'short' | 'int' | 'float' | 'long' | 'double' )
            int alt41=8;
            switch ( input.LA(1) ) {
            case 135:
                {
                alt41=1;
                }
                break;
            case 137:
                {
                alt41=2;
                }
                break;
            case 140:
                {
                alt41=3;
                }
                break;
            case 166:
                {
                alt41=4;
                }
                break;
            case 157:
                {
                alt41=5;
                }
                break;
            case 151:
                {
                alt41=6;
                }
                break;
            case 159:
                {
                alt41=7;
                }
                break;
            case 144:
                {
                alt41=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);
                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:8: 'boolean'
                    {
                    t=(Token)match(input,135,FOLLOW_135_in_builtInType2465); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_135.add(t);
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:18: 'byte'
                    {
                    t=(Token)match(input,137,FOLLOW_137_in_builtInType2467); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_137.add(t);
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:25: 'char'
                    {
                    t=(Token)match(input,140,FOLLOW_140_in_builtInType2469); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_140.add(t);
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:32: 'short'
                    {
                    t=(Token)match(input,166,FOLLOW_166_in_builtInType2471); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_166.add(t);
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:40: 'int'
                    {
                    t=(Token)match(input,157,FOLLOW_157_in_builtInType2473); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_157.add(t);
        
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:46: 'float'
                    {
                    t=(Token)match(input,151,FOLLOW_151_in_builtInType2475); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_151.add(t);
        
                    }
                    break;
                case 7 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:54: 'long'
                    {
                    t=(Token)match(input,159,FOLLOW_159_in_builtInType2477); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_159.add(t);
        
                    }
                    break;
                case 8 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:61: 'double'
                    {
                    t=(Token)match(input,144,FOLLOW_144_in_builtInType2479); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_144.add(t);
        
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 635:71: -> ^( BUILT_IN[$t] )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:635:74: ^( BUILT_IN[$t] )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(BUILT_IN, t), root_1);
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 28, builtInType_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "voidkw"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:638:1: voidkw : v= 'void' -> ^( VOID[$v] ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.voidkw_return voidkw() throws RecognitionException {
        JavaParser.voidkw_return retval = new JavaParser.voidkw_return();
        retval.start = input.LT(1);
        int voidkw_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token v=null;
        
        ASTTree v_tree=null;
        RewriteRuleTokenStream stream_178=new RewriteRuleTokenStream(adaptor,"token 178");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:639:3: (v= 'void' -> ^( VOID[$v] ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:639:5: v= 'void'
            {
            v=(Token)match(input,178,FOLLOW_178_in_voidkw2504); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_178.add(v);
        
            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 639:14: -> ^( VOID[$v] )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:639:17: ^( VOID[$v] )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(VOID, v), root_1);
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 29, voidkw_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "identifierStar"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:644:1: identifierStar : identifier ( DOT ^ STAR )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.identifierStar_return identifierStar() throws RecognitionException {
        JavaParser.identifierStar_return retval = new JavaParser.identifierStar_return();
        retval.start = input.LT(1);
        int identifierStar_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token DOT56=null;
        Token STAR57=null;
        ParserRuleReturnScope identifier55 =null;
        
        ASTTree DOT56_tree=null;
        ASTTree STAR57_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:645:3: ( identifier ( DOT ^ STAR )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:646:3: identifier ( DOT ^ STAR )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_identifier_in_identifierStar2528);
            identifier55=identifier();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, identifier55.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:646:14: ( DOT ^ STAR )?
            int alt42=2;
            int LA42_0 = input.LA(1);
            if ( (LA42_0==DOT) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:646:15: DOT ^ STAR
                    {
                    DOT56=(Token)match(input,DOT,FOLLOW_DOT_in_identifierStar2531); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT56_tree = (ASTTree)adaptor.create(DOT56);
                    root_0 = (ASTTree)adaptor.becomeRoot(DOT56_tree, root_0);
                    }
        
                    STAR57=(Token)match(input,STAR,FOLLOW_STAR_in_identifierStar2534); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STAR57_tree = (ASTTree)adaptor.create(STAR57);
                    adaptor.addChild(root_0, STAR57_tree);
                    }
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 30, identifierStar_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "identifier"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:652:1: identifier : IDENT ( DOT ^ IDENT )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.identifier_return identifier() throws RecognitionException {
        JavaParser.identifier_return retval = new JavaParser.identifier_return();
        retval.start = input.LT(1);
        int identifier_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token IDENT58=null;
        Token DOT59=null;
        Token IDENT60=null;
        
        ASTTree IDENT58_tree=null;
        ASTTree DOT59_tree=null;
        ASTTree IDENT60_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:653:3: ( IDENT ( DOT ^ IDENT )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:654:3: IDENT ( DOT ^ IDENT )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            IDENT58=(Token)match(input,IDENT,FOLLOW_IDENT_in_identifier2554); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENT58_tree = (ASTTree)adaptor.create(IDENT58);
            adaptor.addChild(root_0, IDENT58_tree);
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:654:9: ( DOT ^ IDENT )*
            loop43:
            while (true) {
                int alt43=2;
                int LA43_0 = input.LA(1);
                if ( (LA43_0==DOT) ) {
                    int LA43_2 = input.LA(2);
                    if ( (LA43_2==IDENT) ) {
                        alt43=1;
                    }
        
                }
        
                switch (alt43) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:654:10: DOT ^ IDENT
                    {
                    DOT59=(Token)match(input,DOT,FOLLOW_DOT_in_identifier2557); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT59_tree = (ASTTree)adaptor.create(DOT59);
                    root_0 = (ASTTree)adaptor.becomeRoot(DOT59_tree, root_0);
                    }
        
                    IDENT60=(Token)match(input,IDENT,FOLLOW_IDENT_in_identifier2560); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENT60_tree = (ASTTree)adaptor.create(IDENT60);
                    adaptor.addChild(root_0, IDENT60_tree);
                    }
        
                    }
                    break;
        
                default :
                    break loop43;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 31, identifier_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "modifier"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:659:1: modifier : ( 'private' | 'public' | 'protected' | 'static' | 'transient' | 'final' | 'abstract' | 'native' | 'threadsafe' | 'synchronized' | 'volatile' | 'strictfp' | DEFAULT );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.modifier_return modifier() throws RecognitionException {
        JavaParser.modifier_return retval = new JavaParser.modifier_return();
        retval.start = input.LT(1);
        int modifier_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token set61=null;
        
        ASTTree set61_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:660:3: ( 'private' | 'public' | 'protected' | 'static' | 'transient' | 'final' | 'abstract' | 'native' | 'threadsafe' | 'synchronized' | 'volatile' | 'strictfp' | DEFAULT )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            set61=input.LT(1);
            if ( input.LA(1)==DEFAULT||input.LA(1)==133||input.LA(1)==149||input.LA(1)==160||(input.LA(1) >= 163 && input.LA(1) <= 165)||(input.LA(1) >= 167 && input.LA(1) <= 168)||input.LA(1)==170||input.LA(1)==172||input.LA(1)==175||input.LA(1)==179 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (ASTTree)adaptor.create(set61));
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 32, modifier_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "classDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:678:1: classDefinition[Object ama] : 'class' id= IDENT (fp= formalTypeParameters )? (sc= superClassClause )? (ic= implementsClause )? cb= classBlock[false] -> ^( CLASS_DEF $id ( $fp)? ( $sc)? ( $ic)? $cb) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.classDefinition_return classDefinition(final Object ama) throws RecognitionException {
        JavaParser.classDefinition_return retval = new JavaParser.classDefinition_return();
        retval.start = input.LT(1);
        int classDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        Token string_literal62=null;
        ParserRuleReturnScope fp =null;
        ParserRuleReturnScope sc =null;
        ParserRuleReturnScope ic =null;
        ParserRuleReturnScope cb =null;
        
        ASTTree id_tree=null;
        ASTTree string_literal62_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_141=new RewriteRuleTokenStream(adaptor,"token 141");
        RewriteRuleSubtreeStream stream_classBlock=new RewriteRuleSubtreeStream(adaptor,"rule classBlock");
        RewriteRuleSubtreeStream stream_superClassClause=new RewriteRuleSubtreeStream(adaptor,"rule superClassClause");
        RewriteRuleSubtreeStream stream_implementsClause=new RewriteRuleSubtreeStream(adaptor,"rule implementsClause");
        RewriteRuleSubtreeStream stream_formalTypeParameters=new RewriteRuleSubtreeStream(adaptor,"rule formalTypeParameters");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:679:3: ( 'class' id= IDENT (fp= formalTypeParameters )? (sc= superClassClause )? (ic= implementsClause )? cb= classBlock[false] -> ^( CLASS_DEF $id ( $fp)? ( $sc)? ( $ic)? $cb) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:680:3: 'class' id= IDENT (fp= formalTypeParameters )? (sc= superClassClause )? (ic= implementsClause )? cb= classBlock[false]
            {
            string_literal62=(Token)match(input,141,FOLLOW_141_in_classDefinition2669); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_141.add(string_literal62);
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDefinition2673); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:680:22: (fp= formalTypeParameters )?
            int alt44=2;
            int LA44_0 = input.LA(1);
            if ( (LA44_0==LT) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:680:22: fp= formalTypeParameters
                    {
                    pushFollow(FOLLOW_formalTypeParameters_in_classDefinition2677);
                    fp=formalTypeParameters();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_formalTypeParameters.add(fp.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:681:5: (sc= superClassClause )?
            int alt45=2;
            int LA45_0 = input.LA(1);
            if ( (LA45_0==147) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:681:5: sc= superClassClause
                    {
                    pushFollow(FOLLOW_superClassClause_in_classDefinition2685);
                    sc=superClassClause();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_superClassClause.add(sc.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:682:5: (ic= implementsClause )?
            int alt46=2;
            int LA46_0 = input.LA(1);
            if ( (LA46_0==154) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:682:5: ic= implementsClause
                    {
                    pushFollow(FOLLOW_implementsClause_in_classDefinition2693);
                    ic=implementsClause();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_implementsClause.add(ic.getTree());
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_classBlock_in_classDefinition2701);
            cb=classBlock(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_classBlock.add(cb.getTree());
            // AST REWRITE
            // elements: sc, fp, id, cb, ic
            // token labels: id
            // rule labels: ic, retval, cb, sc, fp
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_ic=new RewriteRuleSubtreeStream(adaptor,"rule ic",ic!=null?ic.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_cb=new RewriteRuleSubtreeStream(adaptor,"rule cb",cb!=null?cb.getTree():null);
            RewriteRuleSubtreeStream stream_sc=new RewriteRuleSubtreeStream(adaptor,"rule sc",sc!=null?sc.getTree():null);
            RewriteRuleSubtreeStream stream_fp=new RewriteRuleSubtreeStream(adaptor,"rule fp",fp!=null?fp.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 684:5: -> ^( CLASS_DEF $id ( $fp)? ( $sc)? ( $ic)? $cb)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:684:8: ^( CLASS_DEF $id ( $fp)? ( $sc)? ( $ic)? $cb)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(CLASS_DEF, "CLASS_DEF"), root_1);
                adaptor.addChild(root_1, ama);
                adaptor.addChild(root_1, stream_id.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:684:32: ( $fp)?
                if ( stream_fp.hasNext() ) {
                    adaptor.addChild(root_1, stream_fp.nextTree());
                }
                stream_fp.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:684:37: ( $sc)?
                if ( stream_sc.hasNext() ) {
                    adaptor.addChild(root_1, stream_sc.nextTree());
                }
                stream_sc.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:684:42: ( $ic)?
                if ( stream_ic.hasNext() ) {
                    adaptor.addChild(root_1, stream_ic.nextTree());
                }
                stream_ic.reset();
        
                adaptor.addChild(root_1, stream_cb.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 33, classDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "formalTypeParameters"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:689:1: formalTypeParameters : lt= LT typeParameter ( COMMA typeParameter )* GT -> ^( FORMAL_TYPE_PARAMS[$lt] ( typeParameter )+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.formalTypeParameters_return formalTypeParameters() throws RecognitionException {
        JavaParser.formalTypeParameters_return retval = new JavaParser.formalTypeParameters_return();
        retval.start = input.LT(1);
        int formalTypeParameters_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lt=null;
        Token COMMA64=null;
        Token GT66=null;
        ParserRuleReturnScope typeParameter63 =null;
        ParserRuleReturnScope typeParameter65 =null;
        
        ASTTree lt_tree=null;
        ASTTree COMMA64_tree=null;
        ASTTree GT66_tree=null;
        RewriteRuleTokenStream stream_GT=new RewriteRuleTokenStream(adaptor,"token GT");
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeParameter=new RewriteRuleSubtreeStream(adaptor,"rule typeParameter");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:690:3: (lt= LT typeParameter ( COMMA typeParameter )* GT -> ^( FORMAL_TYPE_PARAMS[$lt] ( typeParameter )+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:691:3: lt= LT typeParameter ( COMMA typeParameter )* GT
            {
            lt=(Token)match(input,LT,FOLLOW_LT_in_formalTypeParameters2753); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LT.add(lt);
        
            pushFollow(FOLLOW_typeParameter_in_formalTypeParameters2755);
            typeParameter63=typeParameter();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeParameter.add(typeParameter63.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:691:23: ( COMMA typeParameter )*
            loop47:
            while (true) {
                int alt47=2;
                int LA47_0 = input.LA(1);
                if ( (LA47_0==COMMA) ) {
                    alt47=1;
                }
        
                switch (alt47) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:691:24: COMMA typeParameter
                    {
                    COMMA64=(Token)match(input,COMMA,FOLLOW_COMMA_in_formalTypeParameters2758); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA64);
        
                    pushFollow(FOLLOW_typeParameter_in_formalTypeParameters2760);
                    typeParameter65=typeParameter();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeParameter.add(typeParameter65.getTree());
                    }
                    break;
        
                default :
                    break loop47;
                }
            }
        
            GT66=(Token)match(input,GT,FOLLOW_GT_in_formalTypeParameters2764); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_GT.add(GT66);
        
            // AST REWRITE
            // elements: typeParameter
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 692:5: -> ^( FORMAL_TYPE_PARAMS[$lt] ( typeParameter )+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:692:8: ^( FORMAL_TYPE_PARAMS[$lt] ( typeParameter )+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(FORMAL_TYPE_PARAMS, lt), root_1);
                if ( !(stream_typeParameter.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_typeParameter.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeParameter.nextTree());
                }
                stream_typeParameter.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 34, formalTypeParameters_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeParameter"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:695:1: typeParameter : ( annotation )* id= IDENT (tp= typeParameterBound )? -> ^( TYPE_PARAM[$id] $id ( $tp)? ( annotation )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeParameter_return typeParameter() throws RecognitionException {
        JavaParser.typeParameter_return retval = new JavaParser.typeParameter_return();
        retval.start = input.LT(1);
        int typeParameter_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        ParserRuleReturnScope tp =null;
        ParserRuleReturnScope annotation67 =null;
        
        ASTTree id_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_typeParameterBound=new RewriteRuleSubtreeStream(adaptor,"rule typeParameterBound");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:3: ( ( annotation )* id= IDENT (tp= typeParameterBound )? -> ^( TYPE_PARAM[$id] $id ( $tp)? ( annotation )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:5: ( annotation )* id= IDENT (tp= typeParameterBound )?
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:5: ( annotation )*
            loop48:
            while (true) {
                int alt48=2;
                int LA48_0 = input.LA(1);
                if ( (LA48_0==AT) ) {
                    alt48=1;
                }
        
                switch (alt48) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:5: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_typeParameter2797);
                    annotation67=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation67.getTree());
                    }
                    break;
        
                default :
                    break loop48;
                }
            }
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_typeParameter2802); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:28: (tp= typeParameterBound )?
            int alt49=2;
            int LA49_0 = input.LA(1);
            if ( (LA49_0==147) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:28: tp= typeParameterBound
                    {
                    pushFollow(FOLLOW_typeParameterBound_in_typeParameter2806);
                    tp=typeParameterBound();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeParameterBound.add(tp.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: tp, annotation, id
            // token labels: id
            // rule labels: retval, tp
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_tp=new RewriteRuleSubtreeStream(adaptor,"rule tp",tp!=null?tp.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 700:49: -> ^( TYPE_PARAM[$id] $id ( $tp)? ( annotation )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:52: ^( TYPE_PARAM[$id] $id ( $tp)? ( annotation )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPE_PARAM, id), root_1);
                adaptor.addChild(root_1, stream_id.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:75: ( $tp)?
                if ( stream_tp.hasNext() ) {
                    adaptor.addChild(root_1, stream_tp.nextTree());
                }
                stream_tp.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:700:79: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            // Get the source code
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 35, typeParameter_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeParameterBound"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:703:1: typeParameterBound : e= 'extends' tpbr= typeParameterBoundRest -> ^( GENERIC_EXTENDS[$e] ( $tpbr)+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeParameterBound_return typeParameterBound() throws RecognitionException {
        JavaParser.typeParameterBound_return retval = new JavaParser.typeParameterBound_return();
        retval.start = input.LT(1);
        int typeParameterBound_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token e=null;
        ParserRuleReturnScope tpbr =null;
        
        ASTTree e_tree=null;
        RewriteRuleTokenStream stream_147=new RewriteRuleTokenStream(adaptor,"token 147");
        RewriteRuleSubtreeStream stream_typeParameterBoundRest=new RewriteRuleSubtreeStream(adaptor,"rule typeParameterBoundRest");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:708:3: (e= 'extends' tpbr= typeParameterBoundRest -> ^( GENERIC_EXTENDS[$e] ( $tpbr)+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:709:3: e= 'extends' tpbr= typeParameterBoundRest
            {
            e=(Token)match(input,147,FOLLOW_147_in_typeParameterBound2846); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_147.add(e);
        
            pushFollow(FOLLOW_typeParameterBoundRest_in_typeParameterBound2850);
            tpbr=typeParameterBoundRest();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeParameterBoundRest.add(tpbr.getTree());
            // AST REWRITE
            // elements: tpbr
            // token labels: 
            // rule labels: retval, tpbr
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_tpbr=new RewriteRuleSubtreeStream(adaptor,"rule tpbr",tpbr!=null?tpbr.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 710:5: -> ^( GENERIC_EXTENDS[$e] ( $tpbr)+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:710:8: ^( GENERIC_EXTENDS[$e] ( $tpbr)+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(GENERIC_EXTENDS, e), root_1);
                if ( !(stream_tpbr.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_tpbr.hasNext() ) {
                    adaptor.addChild(root_1, stream_tpbr.nextTree());
                }
                stream_tpbr.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            // Get the source code escaping 'extends' keyword
            retval.tree.setSourceCode((tpbr!=null?input.toString(tpbr.start,tpbr.stop):null));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 36, typeParameterBound_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeParameterBoundRest"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:713:1: typeParameterBoundRest : classOrInterfaceType ( BAND classOrInterfaceType )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeParameterBoundRest_return typeParameterBoundRest() throws RecognitionException {
        JavaParser.typeParameterBoundRest_return retval = new JavaParser.typeParameterBoundRest_return();
        retval.start = input.LT(1);
        int typeParameterBoundRest_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token BAND69=null;
        ParserRuleReturnScope classOrInterfaceType68 =null;
        ParserRuleReturnScope classOrInterfaceType70 =null;
        
        ASTTree BAND69_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:714:3: ( classOrInterfaceType ( BAND classOrInterfaceType )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:715:3: classOrInterfaceType ( BAND classOrInterfaceType )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_classOrInterfaceType_in_typeParameterBoundRest2881);
            classOrInterfaceType68=classOrInterfaceType();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType68.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:715:24: ( BAND classOrInterfaceType )*
            loop50:
            while (true) {
                int alt50=2;
                int LA50_0 = input.LA(1);
                if ( (LA50_0==BAND) ) {
                    alt50=1;
                }
        
                switch (alt50) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:715:25: BAND classOrInterfaceType
                    {
                    BAND69=(Token)match(input,BAND,FOLLOW_BAND_in_typeParameterBoundRest2884); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BAND69_tree = (ASTTree)adaptor.create(BAND69);
                    adaptor.addChild(root_0, BAND69_tree);
                    }
        
                    pushFollow(FOLLOW_classOrInterfaceType_in_typeParameterBoundRest2886);
                    classOrInterfaceType70=classOrInterfaceType();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType70.getTree());
        
                    }
                    break;
        
                default :
                    break loop50;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 37, typeParameterBoundRest_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeArguments"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:720:1: typeArguments : lt= LT ( typeArgument )? ( COMMA typeArgument )* GT -> ^( TYPE_ARGS[$lt] ( typeArgument )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeArguments_return typeArguments() throws RecognitionException {
        JavaParser.typeArguments_return retval = new JavaParser.typeArguments_return();
        retval.start = input.LT(1);
        int typeArguments_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lt=null;
        Token COMMA72=null;
        Token GT74=null;
        ParserRuleReturnScope typeArgument71 =null;
        ParserRuleReturnScope typeArgument73 =null;
        
        ASTTree lt_tree=null;
        ASTTree COMMA72_tree=null;
        ASTTree GT74_tree=null;
        RewriteRuleTokenStream stream_GT=new RewriteRuleTokenStream(adaptor,"token GT");
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_typeArgument=new RewriteRuleSubtreeStream(adaptor,"rule typeArgument");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:721:3: (lt= LT ( typeArgument )? ( COMMA typeArgument )* GT -> ^( TYPE_ARGS[$lt] ( typeArgument )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:722:3: lt= LT ( typeArgument )? ( COMMA typeArgument )* GT
            {
            lt=(Token)match(input,LT,FOLLOW_LT_in_typeArguments2907); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LT.add(lt);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:722:9: ( typeArgument )?
            int alt51=2;
            int LA51_0 = input.LA(1);
            if ( (LA51_0==AT||LA51_0==IDENT||LA51_0==QUESTION||LA51_0==135||LA51_0==137||LA51_0==140||LA51_0==144||LA51_0==151||LA51_0==157||LA51_0==159||LA51_0==166) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:722:9: typeArgument
                    {
                    pushFollow(FOLLOW_typeArgument_in_typeArguments2909);
                    typeArgument71=typeArgument();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeArgument.add(typeArgument71.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:722:23: ( COMMA typeArgument )*
            loop52:
            while (true) {
                int alt52=2;
                int LA52_0 = input.LA(1);
                if ( (LA52_0==COMMA) ) {
                    alt52=1;
                }
        
                switch (alt52) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:722:24: COMMA typeArgument
                    {
                    COMMA72=(Token)match(input,COMMA,FOLLOW_COMMA_in_typeArguments2913); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA72);
        
                    pushFollow(FOLLOW_typeArgument_in_typeArguments2915);
                    typeArgument73=typeArgument();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeArgument.add(typeArgument73.getTree());
                    }
                    break;
        
                default :
                    break loop52;
                }
            }
        
            GT74=(Token)match(input,GT,FOLLOW_GT_in_typeArguments2919); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_GT.add(GT74);
        
            // AST REWRITE
            // elements: typeArgument
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 723:5: -> ^( TYPE_ARGS[$lt] ( typeArgument )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:723:8: ^( TYPE_ARGS[$lt] ( typeArgument )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPE_ARGS, lt), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:723:25: ( typeArgument )*
                while ( stream_typeArgument.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeArgument.nextTree());
                }
                stream_typeArgument.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 38, typeArguments_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "typeArgument"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:728:1: typeArgument : ( ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? ) -> ^( TYPE ^( WILDCARD[$q] ( ^( $m typeSpec ) )? ) ( annotation )* ) | typeSpec -> typeSpec );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.typeArgument_return typeArgument() throws RecognitionException {
        JavaParser.typeArgument_return retval = new JavaParser.typeArgument_return();
        retval.start = input.LT(1);
        int typeArgument_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token q=null;
        Token m=null;
        ParserRuleReturnScope annotation75 =null;
        ParserRuleReturnScope typeSpec76 =null;
        ParserRuleReturnScope typeSpec77 =null;
        
        ASTTree q_tree=null;
        ASTTree m_tree=null;
        RewriteRuleTokenStream stream_147=new RewriteRuleTokenStream(adaptor,"token 147");
        RewriteRuleTokenStream stream_QUESTION=new RewriteRuleTokenStream(adaptor,"token QUESTION");
        RewriteRuleTokenStream stream_SUPER=new RewriteRuleTokenStream(adaptor,"token SUPER");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_typeSpec=new RewriteRuleSubtreeStream(adaptor,"rule typeSpec");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:3: ( ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? ) -> ^( TYPE ^( WILDCARD[$q] ( ^( $m typeSpec ) )? ) ( annotation )* ) | typeSpec -> typeSpec )
            int alt56=2;
            switch ( input.LA(1) ) {
            case AT:
                {
                int LA56_1 = input.LA(2);
                if ( (synpred85_Java()) ) {
                    alt56=1;
                }
                else if ( (true) ) {
                    alt56=2;
                }
        
                }
                break;
            case QUESTION:
                {
                alt56=1;
                }
                break;
            case IDENT:
            case 135:
            case 137:
            case 140:
            case 144:
            case 151:
            case 157:
            case 159:
            case 166:
                {
                alt56=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);
                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:5: ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? )
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:5: ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? )
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:6: ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )?
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:6: ( annotation )*
                    loop53:
                    while (true) {
                        int alt53=2;
                        int LA53_0 = input.LA(1);
                        if ( (LA53_0==AT) ) {
                            alt53=1;
                        }
        
                        switch (alt53) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:6: annotation
                            {
                            pushFollow(FOLLOW_annotation_in_typeArgument2955);
                            annotation75=annotation();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_annotation.add(annotation75.getTree());
                            }
                            break;
        
                        default :
                            break loop53;
                        }
                    }
        
                    q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_typeArgument2960); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_QUESTION.add(q);
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:29: (m= ( 'extends' | SUPER ) typeSpec )?
                    int alt55=2;
                    int LA55_0 = input.LA(1);
                    if ( (LA55_0==SUPER||LA55_0==147) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:31: m= ( 'extends' | SUPER ) typeSpec
                            {
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:33: ( 'extends' | SUPER )
                            int alt54=2;
                            int LA54_0 = input.LA(1);
                            if ( (LA54_0==147) ) {
                                alt54=1;
                            }
                            else if ( (LA54_0==SUPER) ) {
                                alt54=2;
                            }
        
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 54, 0, input);
                                throw nvae;
                            }
        
                            switch (alt54) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:34: 'extends'
                                    {
                                    m=(Token)match(input,147,FOLLOW_147_in_typeArgument2967); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_147.add(m);
        
                                    }
                                    break;
                                case 2 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:44: SUPER
                                    {
                                    m=(Token)match(input,SUPER,FOLLOW_SUPER_in_typeArgument2969); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_SUPER.add(m);
        
                                    }
                                    break;
        
                            }
        
                            pushFollow(FOLLOW_typeSpec_in_typeArgument2972);
                            typeSpec76=typeSpec();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_typeSpec.add(typeSpec76.getTree());
                            }
                            break;
        
                    }
        
                    }
        
                    // AST REWRITE
                    // elements: m, typeSpec, annotation
                    // token labels: m
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_m=new RewriteRuleTokenStream(adaptor,"token m",m);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 732:65: -> ^( TYPE ^( WILDCARD[$q] ( ^( $m typeSpec ) )? ) ( annotation )* )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:68: ^( TYPE ^( WILDCARD[$q] ( ^( $m typeSpec ) )? ) ( annotation )* )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPE, "TYPE"), root_1);
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:75: ^( WILDCARD[$q] ( ^( $m typeSpec ) )? )
                        {
                        ASTTree root_2 = (ASTTree)adaptor.nil();
                        root_2 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(WILDCARD, q), root_2);
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:90: ( ^( $m typeSpec ) )?
                        if ( stream_m.hasNext()||stream_typeSpec.hasNext() ) {
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:90: ^( $m typeSpec )
                            {
                            ASTTree root_3 = (ASTTree)adaptor.nil();
                            root_3 = (ASTTree)adaptor.becomeRoot(stream_m.nextNode(), root_3);
                            adaptor.addChild(root_3, stream_typeSpec.nextTree());
                            adaptor.addChild(root_2, root_3);
                            }
        
                        }
                        stream_m.reset();
                        stream_typeSpec.reset();
        
                        adaptor.addChild(root_1, root_2);
                        }
        
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:108: ( annotation )*
                        while ( stream_annotation.hasNext() ) {
                            adaptor.addChild(root_1, stream_annotation.nextTree());
                        }
                        stream_annotation.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:733:5: typeSpec
                    {
                    pushFollow(FOLLOW_typeSpec_in_typeArgument3006);
                    typeSpec77=typeSpec();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeSpec.add(typeSpec77.getTree());
                    // AST REWRITE
                    // elements: typeSpec
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 733:14: -> typeSpec
                    {
                        adaptor.addChild(root_0, stream_typeSpec.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 39, typeArgument_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "superClassClause"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:736:1: superClassClause : ( 'extends' id= classOrInterfaceType ) -> ^( EXTENDS_CLAUSE $id) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.superClassClause_return superClassClause() throws RecognitionException {
        JavaParser.superClassClause_return retval = new JavaParser.superClassClause_return();
        retval.start = input.LT(1);
        int superClassClause_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal78=null;
        ParserRuleReturnScope id =null;
        
        ASTTree string_literal78_tree=null;
        RewriteRuleTokenStream stream_147=new RewriteRuleTokenStream(adaptor,"token 147");
        RewriteRuleSubtreeStream stream_classOrInterfaceType=new RewriteRuleSubtreeStream(adaptor,"rule classOrInterfaceType");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:737:3: ( ( 'extends' id= classOrInterfaceType ) -> ^( EXTENDS_CLAUSE $id) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:738:3: ( 'extends' id= classOrInterfaceType )
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:738:3: ( 'extends' id= classOrInterfaceType )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:738:4: 'extends' id= classOrInterfaceType
            {
            string_literal78=(Token)match(input,147,FOLLOW_147_in_superClassClause3026); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_147.add(string_literal78);
        
            pushFollow(FOLLOW_classOrInterfaceType_in_superClassClause3030);
            id=classOrInterfaceType();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_classOrInterfaceType.add(id.getTree());
            }
        
            // AST REWRITE
            // elements: id
            // token labels: 
            // rule labels: id, retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id",id!=null?id.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 739:5: -> ^( EXTENDS_CLAUSE $id)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:739:8: ^( EXTENDS_CLAUSE $id)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(EXTENDS_CLAUSE, "EXTENDS_CLAUSE"), root_1);
                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 40, superClassClause_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "interfaceDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:744:1: interfaceDefinition[Object ama] : 'interface' id= IDENT (fp= formalTypeParameters )? (ie= interfaceExtends )? cb= classBlock[false] -> ^( INTERFACE_DEF $id ( $fp)? ( $ie)? $cb) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.interfaceDefinition_return interfaceDefinition(final Object ama) throws RecognitionException {
        JavaParser.interfaceDefinition_return retval = new JavaParser.interfaceDefinition_return();
        retval.start = input.LT(1);
        int interfaceDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        Token string_literal79=null;
        ParserRuleReturnScope fp =null;
        ParserRuleReturnScope ie =null;
        ParserRuleReturnScope cb =null;
        
        ASTTree id_tree=null;
        ASTTree string_literal79_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_158=new RewriteRuleTokenStream(adaptor,"token 158");
        RewriteRuleSubtreeStream stream_classBlock=new RewriteRuleSubtreeStream(adaptor,"rule classBlock");
        RewriteRuleSubtreeStream stream_formalTypeParameters=new RewriteRuleSubtreeStream(adaptor,"rule formalTypeParameters");
        RewriteRuleSubtreeStream stream_interfaceExtends=new RewriteRuleSubtreeStream(adaptor,"rule interfaceExtends");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:745:3: ( 'interface' id= IDENT (fp= formalTypeParameters )? (ie= interfaceExtends )? cb= classBlock[false] -> ^( INTERFACE_DEF $id ( $fp)? ( $ie)? $cb) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:746:3: 'interface' id= IDENT (fp= formalTypeParameters )? (ie= interfaceExtends )? cb= classBlock[false]
            {
            string_literal79=(Token)match(input,158,FOLLOW_158_in_interfaceDefinition3062); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_158.add(string_literal79);
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_interfaceDefinition3066); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:746:26: (fp= formalTypeParameters )?
            int alt57=2;
            int LA57_0 = input.LA(1);
            if ( (LA57_0==LT) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:746:26: fp= formalTypeParameters
                    {
                    pushFollow(FOLLOW_formalTypeParameters_in_interfaceDefinition3070);
                    fp=formalTypeParameters();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_formalTypeParameters.add(fp.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:747:5: (ie= interfaceExtends )?
            int alt58=2;
            int LA58_0 = input.LA(1);
            if ( (LA58_0==147) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:747:5: ie= interfaceExtends
                    {
                    pushFollow(FOLLOW_interfaceExtends_in_interfaceDefinition3078);
                    ie=interfaceExtends();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_interfaceExtends.add(ie.getTree());
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_classBlock_in_interfaceDefinition3086);
            cb=classBlock(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_classBlock.add(cb.getTree());
            // AST REWRITE
            // elements: ie, fp, id, cb
            // token labels: id
            // rule labels: retval, cb, ie, fp
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_cb=new RewriteRuleSubtreeStream(adaptor,"rule cb",cb!=null?cb.getTree():null);
            RewriteRuleSubtreeStream stream_ie=new RewriteRuleSubtreeStream(adaptor,"rule ie",ie!=null?ie.getTree():null);
            RewriteRuleSubtreeStream stream_fp=new RewriteRuleSubtreeStream(adaptor,"rule fp",fp!=null?fp.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 749:5: -> ^( INTERFACE_DEF $id ( $fp)? ( $ie)? $cb)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:749:8: ^( INTERFACE_DEF $id ( $fp)? ( $ie)? $cb)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(INTERFACE_DEF, "INTERFACE_DEF"), root_1);
                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_1, ama);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:749:36: ( $fp)?
                if ( stream_fp.hasNext() ) {
                    adaptor.addChild(root_1, stream_fp.nextTree());
                }
                stream_fp.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:749:41: ( $ie)?
                if ( stream_ie.hasNext() ) {
                    adaptor.addChild(root_1, stream_ie.nextTree());
                }
                stream_ie.reset();
        
                adaptor.addChild(root_1, stream_cb.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 41, interfaceDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "classBlock"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:755:1: classBlock[boolean attachSource] : LCURLY ( field[false] | SEMI )* rc= RCURLY -> ^( OBJBLOCK ( field )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.classBlock_return classBlock(final boolean attachSource) throws RecognitionException {
        JavaParser.classBlock_return retval = new JavaParser.classBlock_return();
        retval.start = input.LT(1);
        int classBlock_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token rc=null;
        Token LCURLY80=null;
        Token SEMI82=null;
        ParserRuleReturnScope field81 =null;
        
        ASTTree rc_tree=null;
        ASTTree LCURLY80_tree=null;
        ASTTree SEMI82_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:762:3: ( LCURLY ( field[false] | SEMI )* rc= RCURLY -> ^( OBJBLOCK ( field )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:763:3: LCURLY ( field[false] | SEMI )* rc= RCURLY
            {
            LCURLY80=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_classBlock3139); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY80);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:764:3: ( field[false] | SEMI )*
            loop59:
            while (true) {
                int alt59=3;
                int LA59_0 = input.LA(1);
                if ( (LA59_0==AT||LA59_0==DEFAULT||LA59_0==IDENT||LA59_0==LCURLY||LA59_0==LT||LA59_0==133||LA59_0==135||LA59_0==137||(LA59_0 >= 140 && LA59_0 <= 141)||LA59_0==144||LA59_0==146||LA59_0==149||LA59_0==151||(LA59_0 >= 157 && LA59_0 <= 160)||(LA59_0 >= 163 && LA59_0 <= 168)||LA59_0==170||LA59_0==172||LA59_0==175||(LA59_0 >= 178 && LA59_0 <= 179)) ) {
                    alt59=1;
                }
                else if ( (LA59_0==SEMI) ) {
                    alt59=2;
                }
        
                switch (alt59) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:765:5: field[false]
                    {
                    pushFollow(FOLLOW_field_in_classBlock3149);
                    field81=field(false);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_field.add(field81.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:766:7: SEMI
                    {
                    SEMI82=(Token)match(input,SEMI,FOLLOW_SEMI_in_classBlock3158); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI82);
        
                    }
                    break;
        
                default :
                    break loop59;
                }
            }
        
            rc=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_classBlock3169); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(rc);
        
            // AST REWRITE
            // elements: field
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 769:5: -> ^( OBJBLOCK ( field )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:769:8: ^( OBJBLOCK ( field )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(OBJBLOCK, "OBJBLOCK"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:769:19: ( field )*
                while ( stream_field.hasNext() ) {
                    adaptor.addChild(root_1, stream_field.nextTree());
                }
                stream_field.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachAboveComments(retval.tree, rc);
            if (attachSource) {
               retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 42, classBlock_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "interfaceExtends"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:774:1: interfaceExtends : 'extends' classOrInterfaceType ( COMMA classOrInterfaceType )* -> ^( EXTENDS_CLAUSE ( classOrInterfaceType )+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.interfaceExtends_return interfaceExtends() throws RecognitionException {
        JavaParser.interfaceExtends_return retval = new JavaParser.interfaceExtends_return();
        retval.start = input.LT(1);
        int interfaceExtends_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal83=null;
        Token COMMA85=null;
        ParserRuleReturnScope classOrInterfaceType84 =null;
        ParserRuleReturnScope classOrInterfaceType86 =null;
        
        ASTTree string_literal83_tree=null;
        ASTTree COMMA85_tree=null;
        RewriteRuleTokenStream stream_147=new RewriteRuleTokenStream(adaptor,"token 147");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_classOrInterfaceType=new RewriteRuleSubtreeStream(adaptor,"rule classOrInterfaceType");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:775:3: ( 'extends' classOrInterfaceType ( COMMA classOrInterfaceType )* -> ^( EXTENDS_CLAUSE ( classOrInterfaceType )+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:776:3: 'extends' classOrInterfaceType ( COMMA classOrInterfaceType )*
            {
            string_literal83=(Token)match(input,147,FOLLOW_147_in_interfaceExtends3200); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_147.add(string_literal83);
        
            pushFollow(FOLLOW_classOrInterfaceType_in_interfaceExtends3202);
            classOrInterfaceType84=classOrInterfaceType();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_classOrInterfaceType.add(classOrInterfaceType84.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:776:34: ( COMMA classOrInterfaceType )*
            loop60:
            while (true) {
                int alt60=2;
                int LA60_0 = input.LA(1);
                if ( (LA60_0==COMMA) ) {
                    alt60=1;
                }
        
                switch (alt60) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:776:35: COMMA classOrInterfaceType
                    {
                    COMMA85=(Token)match(input,COMMA,FOLLOW_COMMA_in_interfaceExtends3205); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA85);
        
                    pushFollow(FOLLOW_classOrInterfaceType_in_interfaceExtends3207);
                    classOrInterfaceType86=classOrInterfaceType();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classOrInterfaceType.add(classOrInterfaceType86.getTree());
                    }
                    break;
        
                default :
                    break loop60;
                }
            }
        
            // AST REWRITE
            // elements: classOrInterfaceType
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 777:5: -> ^( EXTENDS_CLAUSE ( classOrInterfaceType )+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:777:8: ^( EXTENDS_CLAUSE ( classOrInterfaceType )+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(EXTENDS_CLAUSE, "EXTENDS_CLAUSE"), root_1);
                if ( !(stream_classOrInterfaceType.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_classOrInterfaceType.hasNext() ) {
                    adaptor.addChild(root_1, stream_classOrInterfaceType.nextTree());
                }
                stream_classOrInterfaceType.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 43, interfaceExtends_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "implementsClause"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:782:1: implementsClause : 'implements' classOrInterfaceType ( COMMA classOrInterfaceType )* -> ^( IMPLEMENTS_CLAUSE ( classOrInterfaceType )+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.implementsClause_return implementsClause() throws RecognitionException {
        JavaParser.implementsClause_return retval = new JavaParser.implementsClause_return();
        retval.start = input.LT(1);
        int implementsClause_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal87=null;
        Token COMMA89=null;
        ParserRuleReturnScope classOrInterfaceType88 =null;
        ParserRuleReturnScope classOrInterfaceType90 =null;
        
        ASTTree string_literal87_tree=null;
        ASTTree COMMA89_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_154=new RewriteRuleTokenStream(adaptor,"token 154");
        RewriteRuleSubtreeStream stream_classOrInterfaceType=new RewriteRuleSubtreeStream(adaptor,"rule classOrInterfaceType");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:783:3: ( 'implements' classOrInterfaceType ( COMMA classOrInterfaceType )* -> ^( IMPLEMENTS_CLAUSE ( classOrInterfaceType )+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:784:3: 'implements' classOrInterfaceType ( COMMA classOrInterfaceType )*
            {
            string_literal87=(Token)match(input,154,FOLLOW_154_in_implementsClause3240); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_154.add(string_literal87);
        
            pushFollow(FOLLOW_classOrInterfaceType_in_implementsClause3242);
            classOrInterfaceType88=classOrInterfaceType();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_classOrInterfaceType.add(classOrInterfaceType88.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:784:37: ( COMMA classOrInterfaceType )*
            loop61:
            while (true) {
                int alt61=2;
                int LA61_0 = input.LA(1);
                if ( (LA61_0==COMMA) ) {
                    alt61=1;
                }
        
                switch (alt61) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:784:38: COMMA classOrInterfaceType
                    {
                    COMMA89=(Token)match(input,COMMA,FOLLOW_COMMA_in_implementsClause3245); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA89);
        
                    pushFollow(FOLLOW_classOrInterfaceType_in_implementsClause3247);
                    classOrInterfaceType90=classOrInterfaceType();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classOrInterfaceType.add(classOrInterfaceType90.getTree());
                    }
                    break;
        
                default :
                    break loop61;
                }
            }
        
            // AST REWRITE
            // elements: classOrInterfaceType
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 785:5: -> ^( IMPLEMENTS_CLAUSE ( classOrInterfaceType )+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:785:8: ^( IMPLEMENTS_CLAUSE ( classOrInterfaceType )+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(IMPLEMENTS_CLAUSE, "IMPLEMENTS_CLAUSE"), root_1);
                if ( !(stream_classOrInterfaceType.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_classOrInterfaceType.hasNext() ) {
                    adaptor.addChild(root_1, stream_classOrInterfaceType.nextTree());
                }
                stream_classOrInterfaceType.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 44, implementsClause_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "field"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:793:1: field[boolean attachSource] : (ama= annotmodannot ( ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree] | ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree] | ( operationDefinition[null] )=>def= operationDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= enumDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] ) |def= staticInitializer |def= instanceInitializer ) -> $def;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.field_return field(final boolean attachSource) throws RecognitionException {
        JavaParser.field_return retval = new JavaParser.field_return();
        retval.start = input.LT(1);
        int field_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope ama =null;
        ParserRuleReturnScope def =null;
        
        RewriteRuleSubtreeStream stream_annotmodannot=new RewriteRuleSubtreeStream(adaptor,"rule annotmodannot");
        RewriteRuleSubtreeStream stream_attributeDefinitions=new RewriteRuleSubtreeStream(adaptor,"rule attributeDefinitions");
        RewriteRuleSubtreeStream stream_interfaceDefinition=new RewriteRuleSubtreeStream(adaptor,"rule interfaceDefinition");
        RewriteRuleSubtreeStream stream_instanceInitializer=new RewriteRuleSubtreeStream(adaptor,"rule instanceInitializer");
        RewriteRuleSubtreeStream stream_annotationTypeDefinition=new RewriteRuleSubtreeStream(adaptor,"rule annotationTypeDefinition");
        RewriteRuleSubtreeStream stream_constructorDefinition=new RewriteRuleSubtreeStream(adaptor,"rule constructorDefinition");
        RewriteRuleSubtreeStream stream_classDefinition=new RewriteRuleSubtreeStream(adaptor,"rule classDefinition");
        RewriteRuleSubtreeStream stream_enumDefinition=new RewriteRuleSubtreeStream(adaptor,"rule enumDefinition");
        RewriteRuleSubtreeStream stream_operationDefinition=new RewriteRuleSubtreeStream(adaptor,"rule operationDefinition");
        RewriteRuleSubtreeStream stream_staticInitializer=new RewriteRuleSubtreeStream(adaptor,"rule staticInitializer");
        
        
           ASTTree annotationsNode = null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:804:3: ( (ama= annotmodannot ( ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree] | ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree] | ( operationDefinition[null] )=>def= operationDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= enumDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] ) |def= staticInitializer |def= instanceInitializer ) -> $def)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:805:3: (ama= annotmodannot ( ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree] | ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree] | ( operationDefinition[null] )=>def= operationDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= enumDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] ) |def= staticInitializer |def= instanceInitializer )
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:805:3: (ama= annotmodannot ( ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree] | ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree] | ( operationDefinition[null] )=>def= operationDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= enumDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] ) |def= staticInitializer |def= instanceInitializer )
            int alt63=3;
            switch ( input.LA(1) ) {
            case AT:
            case DEFAULT:
            case IDENT:
            case LT:
            case 133:
            case 135:
            case 137:
            case 140:
            case 141:
            case 144:
            case 146:
            case 149:
            case 151:
            case 157:
            case 158:
            case 159:
            case 160:
            case 163:
            case 164:
            case 165:
            case 166:
            case 168:
            case 170:
            case 172:
            case 175:
            case 178:
            case 179:
                {
                alt63=1;
                }
                break;
            case 167:
                {
                int LA63_2 = input.LA(2);
                if ( (LA63_2==AT||LA63_2==DEFAULT||LA63_2==IDENT||LA63_2==LT||LA63_2==133||LA63_2==135||LA63_2==137||(LA63_2 >= 140 && LA63_2 <= 141)||LA63_2==144||LA63_2==146||LA63_2==149||LA63_2==151||(LA63_2 >= 157 && LA63_2 <= 160)||(LA63_2 >= 163 && LA63_2 <= 168)||LA63_2==170||LA63_2==172||LA63_2==175||(LA63_2 >= 178 && LA63_2 <= 179)) ) {
                    alt63=1;
                }
                else if ( (LA63_2==LCURLY) ) {
                    alt63=2;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 63, 2, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case LCURLY:
                {
                alt63=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);
                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:808:5: ama= annotmodannot ( ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree] | ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree] | ( operationDefinition[null] )=>def= operationDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= enumDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] )
                    {
                    pushFollow(FOLLOW_annotmodannot_in_field3311);
                    ama=annotmodannot();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotmodannot.add(ama.getTree());
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:809:5: ( ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree] | ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree] | ( operationDefinition[null] )=>def= operationDefinition[$ama.tree] |def= classDefinition[$ama.tree] |def= interfaceDefinition[$ama.tree] |def= enumDefinition[$ama.tree] |def= annotationTypeDefinition[$ama.tree] )
                    int alt62=7;
                    int LA62_0 = input.LA(1);
                    if ( (LA62_0==AT) ) {
                        int LA62_1 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
                        else if ( (true) ) {
                            alt62=7;
                        }
        
                    }
                    else if ( (LA62_0==IDENT) ) {
                        int LA62_2 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred93_Java()) ) {
                            alt62=2;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 2, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==135) ) {
                        int LA62_3 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 3, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==137) ) {
                        int LA62_4 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 4, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==140) ) {
                        int LA62_5 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 5, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==166) ) {
                        int LA62_6 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 6, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==157) ) {
                        int LA62_7 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 7, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==151) ) {
                        int LA62_8 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 8, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==159) ) {
                        int LA62_9 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 9, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==144) ) {
                        int LA62_10 = input.LA(2);
                        if ( (synpred92_Java()) ) {
                            alt62=1;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 10, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==LT) ) {
                        int LA62_11 = input.LA(2);
                        if ( (synpred93_Java()) ) {
                            alt62=2;
                        }
                        else if ( (synpred94_Java()) ) {
                            alt62=3;
                        }
        
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 62, 11, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
                    else if ( (LA62_0==178) && (synpred94_Java())) {
                        alt62=3;
                    }
                    else if ( (LA62_0==141) ) {
                        alt62=4;
                    }
                    else if ( (LA62_0==158) ) {
                        alt62=5;
                    }
                    else if ( (LA62_0==146) ) {
                        alt62=6;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 62, 0, input);
                        throw nvae;
                    }
        
                    switch (alt62) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:810:9: ( attributeDefinitions[null] )=>def= attributeDefinitions[$ama.tree]
                            {
                            pushFollow(FOLLOW_attributeDefinitions_in_field3336);
                            def=attributeDefinitions((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_attributeDefinitions.add(def.getTree());
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:811:9: ( constructorDefinition[null] )=>def= constructorDefinition[$ama.tree]
                            {
                            pushFollow(FOLLOW_constructorDefinition_in_field3357);
                            def=constructorDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_constructorDefinition.add(def.getTree());
                            }
                            break;
                        case 3 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:812:9: ( operationDefinition[null] )=>def= operationDefinition[$ama.tree]
                            {
                            pushFollow(FOLLOW_operationDefinition_in_field3378);
                            def=operationDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_operationDefinition.add(def.getTree());
                            }
                            break;
                        case 4 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:813:9: def= classDefinition[$ama.tree]
                            {
                            pushFollow(FOLLOW_classDefinition_in_field3392);
                            def=classDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_classDefinition.add(def.getTree());
                            }
                            break;
                        case 5 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:814:9: def= interfaceDefinition[$ama.tree]
                            {
                            pushFollow(FOLLOW_interfaceDefinition_in_field3406);
                            def=interfaceDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_interfaceDefinition.add(def.getTree());
                            }
                            break;
                        case 6 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:815:9: def= enumDefinition[$ama.tree]
                            {
                            pushFollow(FOLLOW_enumDefinition_in_field3420);
                            def=enumDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_enumDefinition.add(def.getTree());
                            }
                            break;
                        case 7 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:816:9: def= annotationTypeDefinition[$ama.tree]
                            {
                            pushFollow(FOLLOW_annotationTypeDefinition_in_field3434);
                            def=annotationTypeDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_annotationTypeDefinition.add(def.getTree());
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:818:7: def= staticInitializer
                    {
                    pushFollow(FOLLOW_staticInitializer_in_field3452);
                    def=staticInitializer();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_staticInitializer.add(def.getTree());
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:819:7: def= instanceInitializer
                    {
                    pushFollow(FOLLOW_instanceInitializer_in_field3463);
                    def=instanceInitializer();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_instanceInitializer.add(def.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: def
            // token labels: 
            // rule labels: retval, def
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_def=new RewriteRuleSubtreeStream(adaptor,"rule def",def!=null?def.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 821:5: -> $def
            {
                adaptor.addChild(root_0, stream_def.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachAboveComments(retval.tree, (retval.start));
            if (attachSource) {
               retval.tree.setSourceCode(this.getSource(
                        this.adjustStartToken((retval.start)), (retval.stop)));
            }
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 45, field_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "constructorDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:824:1: constructorDefinition[Object ama] : (fp= formalTypeParameters )? id= IDENT LPAREN (pa= parameterDeclarationList )? RPAREN (tc= throwsClause )? st= compoundStatement[true] -> ^( METHOD_DEF $id ( $fp)? ( $pa)? ( $tc)? ( $st)? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.constructorDefinition_return constructorDefinition(final Object ama) throws RecognitionException {
        JavaParser.constructorDefinition_return retval = new JavaParser.constructorDefinition_return();
        retval.start = input.LT(1);
        int constructorDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        Token LPAREN91=null;
        Token RPAREN92=null;
        ParserRuleReturnScope fp =null;
        ParserRuleReturnScope pa =null;
        ParserRuleReturnScope tc =null;
        ParserRuleReturnScope st =null;
        
        ASTTree id_tree=null;
        ASTTree LPAREN91_tree=null;
        ASTTree RPAREN92_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_throwsClause=new RewriteRuleSubtreeStream(adaptor,"rule throwsClause");
        RewriteRuleSubtreeStream stream_compoundStatement=new RewriteRuleSubtreeStream(adaptor,"rule compoundStatement");
        RewriteRuleSubtreeStream stream_formalTypeParameters=new RewriteRuleSubtreeStream(adaptor,"rule formalTypeParameters");
        RewriteRuleSubtreeStream stream_parameterDeclarationList=new RewriteRuleSubtreeStream(adaptor,"rule parameterDeclarationList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:825:3: ( (fp= formalTypeParameters )? id= IDENT LPAREN (pa= parameterDeclarationList )? RPAREN (tc= throwsClause )? st= compoundStatement[true] -> ^( METHOD_DEF $id ( $fp)? ( $pa)? ( $tc)? ( $st)? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:826:3: (fp= formalTypeParameters )? id= IDENT LPAREN (pa= parameterDeclarationList )? RPAREN (tc= throwsClause )? st= compoundStatement[true]
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:826:5: (fp= formalTypeParameters )?
            int alt64=2;
            int LA64_0 = input.LA(1);
            if ( (LA64_0==LT) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:826:5: fp= formalTypeParameters
                    {
                    pushFollow(FOLLOW_formalTypeParameters_in_constructorDefinition3495);
                    fp=formalTypeParameters();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_formalTypeParameters.add(fp.getTree());
                    }
                    break;
        
            }
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_constructorDefinition3503); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            LPAREN91=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_constructorDefinition3508); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN91);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:828:12: (pa= parameterDeclarationList )?
            int alt65=2;
            int LA65_0 = input.LA(1);
            if ( (LA65_0==AT||LA65_0==IDENT||LA65_0==135||LA65_0==137||LA65_0==140||LA65_0==144||LA65_0==149||LA65_0==151||LA65_0==157||LA65_0==159||LA65_0==166) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:828:12: pa= parameterDeclarationList
                    {
                    pushFollow(FOLLOW_parameterDeclarationList_in_constructorDefinition3512);
                    pa=parameterDeclarationList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_parameterDeclarationList.add(pa.getTree());
                    }
                    break;
        
            }
        
            RPAREN92=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_constructorDefinition3515); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN92);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:829:5: (tc= throwsClause )?
            int alt66=2;
            int LA66_0 = input.LA(1);
            if ( (LA66_0==174) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:829:5: tc= throwsClause
                    {
                    pushFollow(FOLLOW_throwsClause_in_constructorDefinition3522);
                    tc=throwsClause();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_throwsClause.add(tc.getTree());
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_compoundStatement_in_constructorDefinition3530);
            st=compoundStatement(true);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_compoundStatement.add(st.getTree());
            // AST REWRITE
            // elements: id, pa, tc, fp, st
            // token labels: id
            // rule labels: retval, tc, pa, st, fp
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_tc=new RewriteRuleSubtreeStream(adaptor,"rule tc",tc!=null?tc.getTree():null);
            RewriteRuleSubtreeStream stream_pa=new RewriteRuleSubtreeStream(adaptor,"rule pa",pa!=null?pa.getTree():null);
            RewriteRuleSubtreeStream stream_st=new RewriteRuleSubtreeStream(adaptor,"rule st",st!=null?st.getTree():null);
            RewriteRuleSubtreeStream stream_fp=new RewriteRuleSubtreeStream(adaptor,"rule fp",fp!=null?fp.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 831:5: -> ^( METHOD_DEF $id ( $fp)? ( $pa)? ( $tc)? ( $st)? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:831:8: ^( METHOD_DEF $id ( $fp)? ( $pa)? ( $tc)? ( $st)? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(METHOD_DEF, "METHOD_DEF"), root_1);
                adaptor.addChild(root_1, stream_id.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:831:26: ( $fp)?
                if ( stream_fp.hasNext() ) {
                    adaptor.addChild(root_1, stream_fp.nextTree());
                }
                stream_fp.reset();
        
                adaptor.addChild(root_1, ama);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:831:38: ( $pa)?
                if ( stream_pa.hasNext() ) {
                    adaptor.addChild(root_1, stream_pa.nextTree());
                }
                stream_pa.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:831:43: ( $tc)?
                if ( stream_tc.hasNext() ) {
                    adaptor.addChild(root_1, stream_tc.nextTree());
                }
                stream_tc.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:831:48: ( $st)?
                if ( stream_st.hasNext() ) {
                    adaptor.addChild(root_1, stream_st.nextTree());
                }
                stream_st.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 46, constructorDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "operationDefinition"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:834:1: operationDefinition[Object ama] : (fp= formalTypeParameters )? (ty= typeSpec |ty= voidkw ) id= IDENT LPAREN (pa= parameterDeclarationList )? RPAREN rt= declaratorBrackets[$ty.tree] (tc= throwsClause )? (st= compoundStatement[true] | SEMI ) -> ^( METHOD_DEF $id ( $fp)? ( $rt)? ( $pa)? ( $tc)? ( $st)? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.operationDefinition_return operationDefinition(final Object ama) throws RecognitionException {
        JavaParser.operationDefinition_return retval = new JavaParser.operationDefinition_return();
        retval.start = input.LT(1);
        int operationDefinition_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        Token LPAREN93=null;
        Token RPAREN94=null;
        Token SEMI95=null;
        ParserRuleReturnScope fp =null;
        ParserRuleReturnScope ty =null;
        ParserRuleReturnScope pa =null;
        ParserRuleReturnScope rt =null;
        ParserRuleReturnScope tc =null;
        ParserRuleReturnScope st =null;
        
        ASTTree id_tree=null;
        ASTTree LPAREN93_tree=null;
        ASTTree RPAREN94_tree=null;
        ASTTree SEMI95_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_voidkw=new RewriteRuleSubtreeStream(adaptor,"rule voidkw");
        RewriteRuleSubtreeStream stream_throwsClause=new RewriteRuleSubtreeStream(adaptor,"rule throwsClause");
        RewriteRuleSubtreeStream stream_compoundStatement=new RewriteRuleSubtreeStream(adaptor,"rule compoundStatement");
        RewriteRuleSubtreeStream stream_declaratorBrackets=new RewriteRuleSubtreeStream(adaptor,"rule declaratorBrackets");
        RewriteRuleSubtreeStream stream_typeSpec=new RewriteRuleSubtreeStream(adaptor,"rule typeSpec");
        RewriteRuleSubtreeStream stream_formalTypeParameters=new RewriteRuleSubtreeStream(adaptor,"rule formalTypeParameters");
        RewriteRuleSubtreeStream stream_parameterDeclarationList=new RewriteRuleSubtreeStream(adaptor,"rule parameterDeclarationList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:835:3: ( (fp= formalTypeParameters )? (ty= typeSpec |ty= voidkw ) id= IDENT LPAREN (pa= parameterDeclarationList )? RPAREN rt= declaratorBrackets[$ty.tree] (tc= throwsClause )? (st= compoundStatement[true] | SEMI ) -> ^( METHOD_DEF $id ( $fp)? ( $rt)? ( $pa)? ( $tc)? ( $st)? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:836:3: (fp= formalTypeParameters )? (ty= typeSpec |ty= voidkw ) id= IDENT LPAREN (pa= parameterDeclarationList )? RPAREN rt= declaratorBrackets[$ty.tree] (tc= throwsClause )? (st= compoundStatement[true] | SEMI )
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:836:5: (fp= formalTypeParameters )?
            int alt67=2;
            int LA67_0 = input.LA(1);
            if ( (LA67_0==LT) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:836:5: fp= formalTypeParameters
                    {
                    pushFollow(FOLLOW_formalTypeParameters_in_operationDefinition3582);
                    fp=formalTypeParameters();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_formalTypeParameters.add(fp.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:837:3: (ty= typeSpec |ty= voidkw )
            int alt68=2;
            int LA68_0 = input.LA(1);
            if ( (LA68_0==AT||LA68_0==IDENT||LA68_0==135||LA68_0==137||LA68_0==140||LA68_0==144||LA68_0==151||LA68_0==157||LA68_0==159||LA68_0==166) ) {
                alt68=1;
            }
            else if ( (LA68_0==178) ) {
                alt68=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);
                throw nvae;
            }
        
            switch (alt68) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:837:4: ty= typeSpec
                    {
                    pushFollow(FOLLOW_typeSpec_in_operationDefinition3591);
                    ty=typeSpec();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeSpec.add(ty.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:837:16: ty= voidkw
                    {
                    pushFollow(FOLLOW_voidkw_in_operationDefinition3595);
                    ty=voidkw();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_voidkw.add(ty.getTree());
                    }
                    break;
        
            }
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_operationDefinition3603); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            LPAREN93=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_operationDefinition3608); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN93);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:839:12: (pa= parameterDeclarationList )?
            int alt69=2;
            int LA69_0 = input.LA(1);
            if ( (LA69_0==AT||LA69_0==IDENT||LA69_0==135||LA69_0==137||LA69_0==140||LA69_0==144||LA69_0==149||LA69_0==151||LA69_0==157||LA69_0==159||LA69_0==166) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:839:12: pa= parameterDeclarationList
                    {
                    pushFollow(FOLLOW_parameterDeclarationList_in_operationDefinition3612);
                    pa=parameterDeclarationList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_parameterDeclarationList.add(pa.getTree());
                    }
                    break;
        
            }
        
            RPAREN94=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_operationDefinition3615); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN94);
        
            pushFollow(FOLLOW_declaratorBrackets_in_operationDefinition3622);
            rt=declaratorBrackets((ty!=null?((ASTTree)ty.getTree()):null));
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaratorBrackets.add(rt.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:841:5: (tc= throwsClause )?
            int alt70=2;
            int LA70_0 = input.LA(1);
            if ( (LA70_0==174) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:841:5: tc= throwsClause
                    {
                    pushFollow(FOLLOW_throwsClause_in_operationDefinition3630);
                    tc=throwsClause();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_throwsClause.add(tc.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:842:3: (st= compoundStatement[true] | SEMI )
            int alt71=2;
            int LA71_0 = input.LA(1);
            if ( (LA71_0==LCURLY) ) {
                alt71=1;
            }
            else if ( (LA71_0==SEMI) ) {
                alt71=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);
                throw nvae;
            }
        
            switch (alt71) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:843:5: st= compoundStatement[true]
                    {
                    pushFollow(FOLLOW_compoundStatement_in_operationDefinition3644);
                    st=compoundStatement(true);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_compoundStatement.add(st.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:844:7: SEMI
                    {
                    SEMI95=(Token)match(input,SEMI,FOLLOW_SEMI_in_operationDefinition3653); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI95);
        
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: st, fp, id, pa, tc, rt
            // token labels: id
            // rule labels: retval, tc, pa, rt, st, fp
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_tc=new RewriteRuleSubtreeStream(adaptor,"rule tc",tc!=null?tc.getTree():null);
            RewriteRuleSubtreeStream stream_pa=new RewriteRuleSubtreeStream(adaptor,"rule pa",pa!=null?pa.getTree():null);
            RewriteRuleSubtreeStream stream_rt=new RewriteRuleSubtreeStream(adaptor,"rule rt",rt!=null?rt.getTree():null);
            RewriteRuleSubtreeStream stream_st=new RewriteRuleSubtreeStream(adaptor,"rule st",st!=null?st.getTree():null);
            RewriteRuleSubtreeStream stream_fp=new RewriteRuleSubtreeStream(adaptor,"rule fp",fp!=null?fp.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 846:5: -> ^( METHOD_DEF $id ( $fp)? ( $rt)? ( $pa)? ( $tc)? ( $st)? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:846:8: ^( METHOD_DEF $id ( $fp)? ( $rt)? ( $pa)? ( $tc)? ( $st)? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(METHOD_DEF, "METHOD_DEF"), root_1);
                adaptor.addChild(root_1, stream_id.nextNode());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:846:26: ( $fp)?
                if ( stream_fp.hasNext() ) {
                    adaptor.addChild(root_1, stream_fp.nextTree());
                }
                stream_fp.reset();
        
                adaptor.addChild(root_1, ama);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:846:38: ( $rt)?
                if ( stream_rt.hasNext() ) {
                    adaptor.addChild(root_1, stream_rt.nextTree());
                }
                stream_rt.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:846:43: ( $pa)?
                if ( stream_pa.hasNext() ) {
                    adaptor.addChild(root_1, stream_pa.nextTree());
                }
                stream_pa.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:846:48: ( $tc)?
                if ( stream_tc.hasNext() ) {
                    adaptor.addChild(root_1, stream_tc.nextTree());
                }
                stream_tc.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:846:53: ( $st)?
                if ( stream_st.hasNext() ) {
                    adaptor.addChild(root_1, stream_st.nextTree());
                }
                stream_st.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 47, operationDefinition_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "attributeDefinitions"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:849:1: attributeDefinitions[Object ama] : ty= typeSpec va= variableDefinitions[ama, $ty.tree] sm= SEMI -> $va;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.attributeDefinitions_return attributeDefinitions(final Object ama) throws RecognitionException {
        JavaParser.attributeDefinitions_return retval = new JavaParser.attributeDefinitions_return();
        retval.start = input.LT(1);
        int attributeDefinitions_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token sm=null;
        ParserRuleReturnScope ty =null;
        ParserRuleReturnScope va =null;
        
        ASTTree sm_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_typeSpec=new RewriteRuleSubtreeStream(adaptor,"rule typeSpec");
        RewriteRuleSubtreeStream stream_variableDefinitions=new RewriteRuleSubtreeStream(adaptor,"rule variableDefinitions");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:853:3: (ty= typeSpec va= variableDefinitions[ama, $ty.tree] sm= SEMI -> $va)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:854:3: ty= typeSpec va= variableDefinitions[ama, $ty.tree] sm= SEMI
            {
            pushFollow(FOLLOW_typeSpec_in_attributeDefinitions3717);
            ty=typeSpec();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeSpec.add(ty.getTree());
            pushFollow(FOLLOW_variableDefinitions_in_attributeDefinitions3724);
            va=variableDefinitions(ama, (ty!=null?((ASTTree)ty.getTree()):null));
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableDefinitions.add(va.getTree());
            sm=(Token)match(input,SEMI,FOLLOW_SEMI_in_attributeDefinitions3729); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SEMI.add(sm);
        
            // AST REWRITE
            // elements: va
            // token labels: 
            // rule labels: retval, va
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_va=new RewriteRuleSubtreeStream(adaptor,"rule va",va!=null?va.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 856:5: -> $va
            {
                adaptor.addChild(root_0, stream_va.nextTree());
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            attachTrailingComments(retval.tree, sm);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 48, attributeDefinitions_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "staticInitializer"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:859:1: staticInitializer : 'static' cs= compoundStatement[false] -> ^( STATIC_INIT $cs) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.staticInitializer_return staticInitializer() throws RecognitionException {
        JavaParser.staticInitializer_return retval = new JavaParser.staticInitializer_return();
        retval.start = input.LT(1);
        int staticInitializer_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal96=null;
        ParserRuleReturnScope cs =null;
        
        ASTTree string_literal96_tree=null;
        RewriteRuleTokenStream stream_167=new RewriteRuleTokenStream(adaptor,"token 167");
        RewriteRuleSubtreeStream stream_compoundStatement=new RewriteRuleSubtreeStream(adaptor,"rule compoundStatement");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:863:3: ( 'static' cs= compoundStatement[false] -> ^( STATIC_INIT $cs) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:864:3: 'static' cs= compoundStatement[false]
            {
            string_literal96=(Token)match(input,167,FOLLOW_167_in_staticInitializer3758); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_167.add(string_literal96);
        
            pushFollow(FOLLOW_compoundStatement_in_staticInitializer3762);
            cs=compoundStatement(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_compoundStatement.add(cs.getTree());
            // AST REWRITE
            // elements: cs
            // token labels: 
            // rule labels: retval, cs
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_cs=new RewriteRuleSubtreeStream(adaptor,"rule cs",cs!=null?cs.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 865:5: -> ^( STATIC_INIT $cs)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:865:8: ^( STATIC_INIT $cs)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(STATIC_INIT, "STATIC_INIT"), root_1);
                adaptor.addChild(root_1, stream_cs.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 49, staticInitializer_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "instanceInitializer"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:868:1: instanceInitializer : cs= compoundStatement[false] -> ^( INSTANCE_INIT $cs) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.instanceInitializer_return instanceInitializer() throws RecognitionException {
        JavaParser.instanceInitializer_return retval = new JavaParser.instanceInitializer_return();
        retval.start = input.LT(1);
        int instanceInitializer_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope cs =null;
        
        RewriteRuleSubtreeStream stream_compoundStatement=new RewriteRuleSubtreeStream(adaptor,"rule compoundStatement");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:872:3: (cs= compoundStatement[false] -> ^( INSTANCE_INIT $cs) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:873:3: cs= compoundStatement[false]
            {
            pushFollow(FOLLOW_compoundStatement_in_instanceInitializer3799);
            cs=compoundStatement(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_compoundStatement.add(cs.getTree());
            // AST REWRITE
            // elements: cs
            // token labels: 
            // rule labels: retval, cs
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_cs=new RewriteRuleSubtreeStream(adaptor,"rule cs",cs!=null?cs.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 874:5: -> ^( INSTANCE_INIT $cs)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:874:8: ^( INSTANCE_INIT $cs)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(INSTANCE_INIT, "INSTANCE_INIT"), root_1);
                adaptor.addChild(root_1, stream_cs.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 50, instanceInitializer_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "variableDefinitions"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:877:1: variableDefinitions[Object ama, Object t] :va+= variableDeclarator[ama, t] ( COMMA va+= variableDeclarator[ama, t] )* -> ( $va)+ ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.variableDefinitions_return variableDefinitions(final Object ama, final Object t) throws RecognitionException {
        JavaParser.variableDefinitions_return retval = new JavaParser.variableDefinitions_return();
        retval.start = input.LT(1);
        int variableDefinitions_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token COMMA97=null;
        List<Object> list_va=null;
        RuleReturnScope va = null;
        ASTTree COMMA97_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_variableDeclarator=new RewriteRuleSubtreeStream(adaptor,"rule variableDeclarator");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:878:3: (va+= variableDeclarator[ama, t] ( COMMA va+= variableDeclarator[ama, t] )* -> ( $va)+ )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:879:3: va+= variableDeclarator[ama, t] ( COMMA va+= variableDeclarator[ama, t] )*
            {
            pushFollow(FOLLOW_variableDeclarator_in_variableDefinitions3832);
            va=variableDeclarator(ama, t);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableDeclarator.add(va.getTree());
            if (list_va==null) list_va=new ArrayList<Object>();
            list_va.add(va.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:879:34: ( COMMA va+= variableDeclarator[ama, t] )*
            loop72:
            while (true) {
                int alt72=2;
                int LA72_0 = input.LA(1);
                if ( (LA72_0==COMMA) ) {
                    alt72=1;
                }
        
                switch (alt72) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:879:35: COMMA va+= variableDeclarator[ama, t]
                    {
                    COMMA97=(Token)match(input,COMMA,FOLLOW_COMMA_in_variableDefinitions3836); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA97);
        
                    pushFollow(FOLLOW_variableDeclarator_in_variableDefinitions3840);
                    va=variableDeclarator(ama, t);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variableDeclarator.add(va.getTree());
                    if (list_va==null) list_va=new ArrayList<Object>();
                    list_va.add(va.getTree());
                    }
                    break;
        
                default :
                    break loop72;
                }
            }
        
            // AST REWRITE
            // elements: va
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: va
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_va=new RewriteRuleSubtreeStream(adaptor,"token va",list_va);
            root_0 = (ASTTree)adaptor.nil();
            // 880:5: -> ( $va)+
            {
                if ( !(stream_va.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_va.hasNext() ) {
                    adaptor.addChild(root_0, stream_va.nextTree());
                }
                stream_va.reset();
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 51, variableDefinitions_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "variableDeclarator"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:887:1: variableDeclarator[Object ama, Object t] : id= IDENT d= declaratorBrackets[t] v= varInitializer -> ^( VARIABLE_DEF $id $d ( $v)? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.variableDeclarator_return variableDeclarator(final Object ama, final Object t) throws RecognitionException {
        JavaParser.variableDeclarator_return retval = new JavaParser.variableDeclarator_return();
        retval.start = input.LT(1);
        int variableDeclarator_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token id=null;
        ParserRuleReturnScope d =null;
        ParserRuleReturnScope v =null;
        
        ASTTree id_tree=null;
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_declaratorBrackets=new RewriteRuleSubtreeStream(adaptor,"rule declaratorBrackets");
        RewriteRuleSubtreeStream stream_varInitializer=new RewriteRuleSubtreeStream(adaptor,"rule varInitializer");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:888:3: (id= IDENT d= declaratorBrackets[t] v= varInitializer -> ^( VARIABLE_DEF $id $d ( $v)? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:889:3: id= IDENT d= declaratorBrackets[t] v= varInitializer
            {
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_variableDeclarator3873); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            pushFollow(FOLLOW_declaratorBrackets_in_variableDeclarator3877);
            d=declaratorBrackets(t);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaratorBrackets.add(d.getTree());
            pushFollow(FOLLOW_varInitializer_in_variableDeclarator3882);
            v=varInitializer();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_varInitializer.add(v.getTree());
            // AST REWRITE
            // elements: v, d, id
            // token labels: id
            // rule labels: v, retval, d
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleSubtreeStream stream_v=new RewriteRuleSubtreeStream(adaptor,"rule v",v!=null?v.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_d=new RewriteRuleSubtreeStream(adaptor,"rule d",d!=null?d.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 890:5: -> ^( VARIABLE_DEF $id $d ( $v)? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:890:8: ^( VARIABLE_DEF $id $d ( $v)? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(VARIABLE_DEF, "VARIABLE_DEF"), root_1);
                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_1, ama);
                adaptor.addChild(root_1, stream_d.nextTree());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:890:38: ( $v)?
                if ( stream_v.hasNext() ) {
                    adaptor.addChild(root_1, stream_v.nextTree());
                }
                stream_v.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 52, variableDeclarator_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "varInitializer"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:893:1: varInitializer : (as= ASSIGN ^i= initializer )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.varInitializer_return varInitializer() throws RecognitionException {
        JavaParser.varInitializer_return retval = new JavaParser.varInitializer_return();
        retval.start = input.LT(1);
        int varInitializer_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token as=null;
        ParserRuleReturnScope i =null;
        
        ASTTree as_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:894:3: ( (as= ASSIGN ^i= initializer )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:895:3: (as= ASSIGN ^i= initializer )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:895:3: (as= ASSIGN ^i= initializer )?
            int alt73=2;
            int LA73_0 = input.LA(1);
            if ( (LA73_0==ASSIGN) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:895:4: as= ASSIGN ^i= initializer
                    {
                    as=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_varInitializer3923); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    as_tree = (ASTTree)adaptor.create(as);
                    root_0 = (ASTTree)adaptor.becomeRoot(as_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_initializer_in_varInitializer3928);
                    i=initializer();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, i.getTree());
        
                    if ( state.backtracking==0 ) {
                                                as_tree.setSourceCode(this.getSource(this.adjustStartToken((i!=null?(i.start):null)), (i!=null?(i.stop):null)));
                                               }
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 53, varInitializer_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "arrayInitializer"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:901:1: arrayInitializer : lc= LCURLY ( initializer ( options {greedy=true; } : COMMA initializer )* ( COMMA )? )? RCURLY -> ^( ARRAY_INIT[$lc] ( initializer )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.arrayInitializer_return arrayInitializer() throws RecognitionException {
        JavaParser.arrayInitializer_return retval = new JavaParser.arrayInitializer_return();
        retval.start = input.LT(1);
        int arrayInitializer_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lc=null;
        Token COMMA99=null;
        Token COMMA101=null;
        Token RCURLY102=null;
        ParserRuleReturnScope initializer98 =null;
        ParserRuleReturnScope initializer100 =null;
        
        ASTTree lc_tree=null;
        ASTTree COMMA99_tree=null;
        ASTTree COMMA101_tree=null;
        ASTTree RCURLY102_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_initializer=new RewriteRuleSubtreeStream(adaptor,"rule initializer");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:902:3: (lc= LCURLY ( initializer ( options {greedy=true; } : COMMA initializer )* ( COMMA )? )? RCURLY -> ^( ARRAY_INIT[$lc] ( initializer )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:903:3: lc= LCURLY ( initializer ( options {greedy=true; } : COMMA initializer )* ( COMMA )? )? RCURLY
            {
            lc=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_arrayInitializer3977); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(lc);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:903:13: ( initializer ( options {greedy=true; } : COMMA initializer )* ( COMMA )? )?
            int alt76=2;
            int LA76_0 = input.LA(1);
            if ( (LA76_0==AT||LA76_0==BNOT||LA76_0==CHAR_LITERAL||LA76_0==DEC||LA76_0==IDENT||LA76_0==INC||LA76_0==LCURLY||LA76_0==LNOT||LA76_0==LPAREN||LA76_0==MINUS||LA76_0==NEW||LA76_0==NUM_LITERAL||LA76_0==PLUS||(LA76_0 >= STRING_LITERAL && LA76_0 <= SUPER)||LA76_0==135||LA76_0==137||LA76_0==140||LA76_0==144||LA76_0==148||LA76_0==151||LA76_0==157||LA76_0==159||LA76_0==161||LA76_0==166||LA76_0==171||LA76_0==176||LA76_0==178) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:903:14: initializer ( options {greedy=true; } : COMMA initializer )* ( COMMA )?
                    {
                    pushFollow(FOLLOW_initializer_in_arrayInitializer3980);
                    initializer98=initializer();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_initializer.add(initializer98.getTree());
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:903:26: ( options {greedy=true; } : COMMA initializer )*
                    loop74:
                    while (true) {
                        int alt74=2;
                        int LA74_0 = input.LA(1);
                        if ( (LA74_0==COMMA) ) {
                            int LA74_1 = input.LA(2);
                            if ( (LA74_1==AT||LA74_1==BNOT||LA74_1==CHAR_LITERAL||LA74_1==DEC||LA74_1==IDENT||LA74_1==INC||LA74_1==LCURLY||LA74_1==LNOT||LA74_1==LPAREN||LA74_1==MINUS||LA74_1==NEW||LA74_1==NUM_LITERAL||LA74_1==PLUS||(LA74_1 >= STRING_LITERAL && LA74_1 <= SUPER)||LA74_1==135||LA74_1==137||LA74_1==140||LA74_1==144||LA74_1==148||LA74_1==151||LA74_1==157||LA74_1==159||LA74_1==161||LA74_1==166||LA74_1==171||LA74_1==176||LA74_1==178) ) {
                                alt74=1;
                            }
        
                        }
        
                        switch (alt74) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:908:27: COMMA initializer
                            {
                            COMMA99=(Token)match(input,COMMA,FOLLOW_COMMA_in_arrayInitializer4008); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA99);
        
                            pushFollow(FOLLOW_initializer_in_arrayInitializer4010);
                            initializer100=initializer();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_initializer.add(initializer100.getTree());
                            }
                            break;
        
                        default :
                            break loop74;
                        }
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:908:47: ( COMMA )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);
                    if ( (LA75_0==COMMA) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:908:47: COMMA
                            {
                            COMMA101=(Token)match(input,COMMA,FOLLOW_COMMA_in_arrayInitializer4014); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(COMMA101);
        
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
        
            RCURLY102=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_arrayInitializer4019); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY102);
        
            // AST REWRITE
            // elements: initializer
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 909:5: -> ^( ARRAY_INIT[$lc] ( initializer )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:909:8: ^( ARRAY_INIT[$lc] ( initializer )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ARRAY_INIT, lc), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:909:26: ( initializer )*
                while ( stream_initializer.hasNext() ) {
                    adaptor.addChild(root_1, stream_initializer.nextTree());
                }
                stream_initializer.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 54, arrayInitializer_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "initializer"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:915:1: initializer : ( expression | arrayInitializer );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.initializer_return initializer() throws RecognitionException {
        JavaParser.initializer_return retval = new JavaParser.initializer_return();
        retval.start = input.LT(1);
        int initializer_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope expression103 =null;
        ParserRuleReturnScope arrayInitializer104 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:916:3: ( expression | arrayInitializer )
            int alt77=2;
            int LA77_0 = input.LA(1);
            if ( (LA77_0==AT||LA77_0==BNOT||LA77_0==CHAR_LITERAL||LA77_0==DEC||LA77_0==IDENT||LA77_0==INC||LA77_0==LNOT||LA77_0==LPAREN||LA77_0==MINUS||LA77_0==NEW||LA77_0==NUM_LITERAL||LA77_0==PLUS||(LA77_0 >= STRING_LITERAL && LA77_0 <= SUPER)||LA77_0==135||LA77_0==137||LA77_0==140||LA77_0==144||LA77_0==148||LA77_0==151||LA77_0==157||LA77_0==159||LA77_0==161||LA77_0==166||LA77_0==171||LA77_0==176||LA77_0==178) ) {
                alt77=1;
            }
            else if ( (LA77_0==LCURLY) ) {
                alt77=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);
                throw nvae;
            }
        
            switch (alt77) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:917:3: expression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_expression_in_initializer4052);
                    expression103=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression103.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:918:5: arrayInitializer
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_arrayInitializer_in_initializer4058);
                    arrayInitializer104=arrayInitializer();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayInitializer104.getTree());
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 55, initializer_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "throwsClause"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:923:1: throwsClause : 'throws' thrownException ( COMMA thrownException )* -> ^( THROWS ( thrownException )+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.throwsClause_return throwsClause() throws RecognitionException {
        JavaParser.throwsClause_return retval = new JavaParser.throwsClause_return();
        retval.start = input.LT(1);
        int throwsClause_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal105=null;
        Token COMMA107=null;
        ParserRuleReturnScope thrownException106 =null;
        ParserRuleReturnScope thrownException108 =null;
        
        ASTTree string_literal105_tree=null;
        ASTTree COMMA107_tree=null;
        RewriteRuleTokenStream stream_174=new RewriteRuleTokenStream(adaptor,"token 174");
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_thrownException=new RewriteRuleSubtreeStream(adaptor,"rule thrownException");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:924:3: ( 'throws' thrownException ( COMMA thrownException )* -> ^( THROWS ( thrownException )+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:925:3: 'throws' thrownException ( COMMA thrownException )*
            {
            string_literal105=(Token)match(input,174,FOLLOW_174_in_throwsClause4075); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_174.add(string_literal105);
        
            pushFollow(FOLLOW_thrownException_in_throwsClause4077);
            thrownException106=thrownException();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_thrownException.add(thrownException106.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:925:28: ( COMMA thrownException )*
            loop78:
            while (true) {
                int alt78=2;
                int LA78_0 = input.LA(1);
                if ( (LA78_0==COMMA) ) {
                    alt78=1;
                }
        
                switch (alt78) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:925:29: COMMA thrownException
                    {
                    COMMA107=(Token)match(input,COMMA,FOLLOW_COMMA_in_throwsClause4080); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA107);
        
                    pushFollow(FOLLOW_thrownException_in_throwsClause4082);
                    thrownException108=thrownException();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_thrownException.add(thrownException108.getTree());
                    }
                    break;
        
                default :
                    break loop78;
                }
            }
        
            // AST REWRITE
            // elements: thrownException
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 926:5: -> ^( THROWS ( thrownException )+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:926:8: ^( THROWS ( thrownException )+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(THROWS, "THROWS"), root_1);
                if ( !(stream_thrownException.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_thrownException.hasNext() ) {
                    adaptor.addChild(root_1, stream_thrownException.nextTree());
                }
                stream_thrownException.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 56, throwsClause_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "thrownException"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:929:1: thrownException : ( annotation )* identifier -> ^( THROWN_EXCEPTION identifier ( annotation )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.thrownException_return thrownException() throws RecognitionException {
        JavaParser.thrownException_return retval = new JavaParser.thrownException_return();
        retval.start = input.LT(1);
        int thrownException_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope annotation109 =null;
        ParserRuleReturnScope identifier110 =null;
        
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_identifier=new RewriteRuleSubtreeStream(adaptor,"rule identifier");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:933:3: ( ( annotation )* identifier -> ^( THROWN_EXCEPTION identifier ( annotation )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:933:5: ( annotation )* identifier
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:933:5: ( annotation )*
            loop79:
            while (true) {
                int alt79=2;
                int LA79_0 = input.LA(1);
                if ( (LA79_0==AT) ) {
                    alt79=1;
                }
        
                switch (alt79) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:933:5: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_thrownException4115);
                    annotation109=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation109.getTree());
                    }
                    break;
        
                default :
                    break loop79;
                }
            }
        
            pushFollow(FOLLOW_identifier_in_thrownException4118);
            identifier110=identifier();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_identifier.add(identifier110.getTree());
            // AST REWRITE
            // elements: annotation, identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 933:28: -> ^( THROWN_EXCEPTION identifier ( annotation )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:933:31: ^( THROWN_EXCEPTION identifier ( annotation )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(THROWN_EXCEPTION, "THROWN_EXCEPTION"), root_1);
                adaptor.addChild(root_1, stream_identifier.nextTree());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:933:61: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 57, thrownException_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "parameterDeclarationList"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:938:1: parameterDeclarationList : parameterDeclaration ( COMMA parameterDeclaration )* -> ^( PARAMETERS ( parameterDeclaration )+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.parameterDeclarationList_return parameterDeclarationList() throws RecognitionException {
        JavaParser.parameterDeclarationList_return retval = new JavaParser.parameterDeclarationList_return();
        retval.start = input.LT(1);
        int parameterDeclarationList_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token COMMA112=null;
        ParserRuleReturnScope parameterDeclaration111 =null;
        ParserRuleReturnScope parameterDeclaration113 =null;
        
        ASTTree COMMA112_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_parameterDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule parameterDeclaration");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:939:3: ( parameterDeclaration ( COMMA parameterDeclaration )* -> ^( PARAMETERS ( parameterDeclaration )+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:940:3: parameterDeclaration ( COMMA parameterDeclaration )*
            {
            pushFollow(FOLLOW_parameterDeclaration_in_parameterDeclarationList4146);
            parameterDeclaration111=parameterDeclaration();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_parameterDeclaration.add(parameterDeclaration111.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:940:24: ( COMMA parameterDeclaration )*
            loop80:
            while (true) {
                int alt80=2;
                int LA80_0 = input.LA(1);
                if ( (LA80_0==COMMA) ) {
                    alt80=1;
                }
        
                switch (alt80) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:940:25: COMMA parameterDeclaration
                    {
                    COMMA112=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameterDeclarationList4149); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA112);
        
                    pushFollow(FOLLOW_parameterDeclaration_in_parameterDeclarationList4151);
                    parameterDeclaration113=parameterDeclaration();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_parameterDeclaration.add(parameterDeclaration113.getTree());
                    }
                    break;
        
                default :
                    break loop80;
                }
            }
        
            // AST REWRITE
            // elements: parameterDeclaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 940:54: -> ^( PARAMETERS ( parameterDeclaration )+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:940:57: ^( PARAMETERS ( parameterDeclaration )+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(PARAMETERS, "PARAMETERS"), root_1);
                if ( !(stream_parameterDeclaration.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_parameterDeclaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameterDeclaration.nextTree());
                }
                stream_parameterDeclaration.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 58, parameterDeclarationList_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "parameterDeclaration"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:948:1: parameterDeclaration : ( annotation )* (pm= parameterModifier )? t= typeSpec (el= ELLIPSIS )? id= IDENT pd= declaratorBrackets[$t.tree] -> ^( PARAMETER_DEF ( $pm)? ( annotation )* $pd ( $el)? $id) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.parameterDeclaration_return parameterDeclaration() throws RecognitionException {
        JavaParser.parameterDeclaration_return retval = new JavaParser.parameterDeclaration_return();
        retval.start = input.LT(1);
        int parameterDeclaration_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token el=null;
        Token id=null;
        ParserRuleReturnScope pm =null;
        ParserRuleReturnScope t =null;
        ParserRuleReturnScope pd =null;
        ParserRuleReturnScope annotation114 =null;
        
        ASTTree el_tree=null;
        ASTTree id_tree=null;
        RewriteRuleTokenStream stream_ELLIPSIS=new RewriteRuleTokenStream(adaptor,"token ELLIPSIS");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
        RewriteRuleSubtreeStream stream_parameterModifier=new RewriteRuleSubtreeStream(adaptor,"rule parameterModifier");
        RewriteRuleSubtreeStream stream_declaratorBrackets=new RewriteRuleSubtreeStream(adaptor,"rule declaratorBrackets");
        RewriteRuleSubtreeStream stream_typeSpec=new RewriteRuleSubtreeStream(adaptor,"rule typeSpec");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:3: ( ( annotation )* (pm= parameterModifier )? t= typeSpec (el= ELLIPSIS )? id= IDENT pd= declaratorBrackets[$t.tree] -> ^( PARAMETER_DEF ( $pm)? ( annotation )* $pd ( $el)? $id) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:5: ( annotation )* (pm= parameterModifier )? t= typeSpec (el= ELLIPSIS )? id= IDENT pd= declaratorBrackets[$t.tree]
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:5: ( annotation )*
            loop81:
            while (true) {
                int alt81=2;
                int LA81_0 = input.LA(1);
                if ( (LA81_0==AT) ) {
                    int LA81_2 = input.LA(2);
                    if ( (LA81_2==IDENT) ) {
                        int LA81_3 = input.LA(3);
                        if ( (synpred117_Java()) ) {
                            alt81=1;
                        }
        
                    }
        
                }
        
                switch (alt81) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:5: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_parameterDeclaration4180);
                    annotation114=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotation.add(annotation114.getTree());
                    }
                    break;
        
                default :
                    break loop81;
                }
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:19: (pm= parameterModifier )?
            int alt82=2;
            int LA82_0 = input.LA(1);
            if ( (LA82_0==149) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:19: pm= parameterModifier
                    {
                    pushFollow(FOLLOW_parameterModifier_in_parameterDeclaration4185);
                    pm=parameterModifier();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_parameterModifier.add(pm.getTree());
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_typeSpec_in_parameterDeclaration4190);
            t=typeSpec();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeSpec.add(t.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:52: (el= ELLIPSIS )?
            int alt83=2;
            int LA83_0 = input.LA(1);
            if ( (LA83_0==ELLIPSIS) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:52: el= ELLIPSIS
                    {
                    el=(Token)match(input,ELLIPSIS,FOLLOW_ELLIPSIS_in_parameterDeclaration4194); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ELLIPSIS.add(el);
        
                    }
                    break;
        
            }
        
            id=(Token)match(input,IDENT,FOLLOW_IDENT_in_parameterDeclaration4199); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IDENT.add(id);
        
            pushFollow(FOLLOW_declaratorBrackets_in_parameterDeclaration4203);
            pd=declaratorBrackets((t!=null?((ASTTree)t.getTree()):null));
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declaratorBrackets.add(pd.getTree());
            // AST REWRITE
            // elements: pd, id, pm, annotation, el
            // token labels: id, el
            // rule labels: pd, retval, pm
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id=new RewriteRuleTokenStream(adaptor,"token id",id);
            RewriteRuleTokenStream stream_el=new RewriteRuleTokenStream(adaptor,"token el",el);
            RewriteRuleSubtreeStream stream_pd=new RewriteRuleSubtreeStream(adaptor,"rule pd",pd!=null?pd.getTree():null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_pm=new RewriteRuleSubtreeStream(adaptor,"rule pm",pm!=null?pm.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 950:5: -> ^( PARAMETER_DEF ( $pm)? ( annotation )* $pd ( $el)? $id)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:950:8: ^( PARAMETER_DEF ( $pm)? ( annotation )* $pd ( $el)? $id)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(PARAMETER_DEF, "PARAMETER_DEF"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:950:25: ( $pm)?
                if ( stream_pm.hasNext() ) {
                    adaptor.addChild(root_1, stream_pm.nextTree());
                }
                stream_pm.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:950:29: ( annotation )*
                while ( stream_annotation.hasNext() ) {
                    adaptor.addChild(root_1, stream_annotation.nextTree());
                }
                stream_annotation.reset();
        
                adaptor.addChild(root_1, stream_pd.nextTree());
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:950:46: ( $el)?
                if ( stream_el.hasNext() ) {
                    adaptor.addChild(root_1, stream_el.nextNode());
                }
                stream_el.reset();
        
                adaptor.addChild(root_1, stream_id.nextNode());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 59, parameterDeclaration_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "parameterModifier"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:953:1: parameterModifier : f= 'final' -> ^( MODIFIERS $f) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.parameterModifier_return parameterModifier() throws RecognitionException {
        JavaParser.parameterModifier_return retval = new JavaParser.parameterModifier_return();
        retval.start = input.LT(1);
        int parameterModifier_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token f=null;
        
        ASTTree f_tree=null;
        RewriteRuleTokenStream stream_149=new RewriteRuleTokenStream(adaptor,"token 149");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:954:3: (f= 'final' -> ^( MODIFIERS $f) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:955:3: f= 'final'
            {
            f=(Token)match(input,149,FOLLOW_149_in_parameterModifier4249); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_149.add(f);
        
            // AST REWRITE
            // elements: f
            // token labels: f
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_f=new RewriteRuleTokenStream(adaptor,"token f",f);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 956:5: -> ^( MODIFIERS $f)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:956:8: ^( MODIFIERS $f)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(MODIFIERS, "MODIFIERS"), root_1);
                adaptor.addChild(root_1, stream_f.nextNode());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 60, parameterModifier_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "compoundStatement"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:968:1: compoundStatement[boolean top] : LCURLY stmtl= statementList RCURLY -> ^( SLIST ( $stmtl)? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.compoundStatement_return compoundStatement(final boolean top) throws RecognitionException {
        JavaParser.compoundStatement_return retval = new JavaParser.compoundStatement_return();
        retval.start = input.LT(1);
        int compoundStatement_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token LCURLY115=null;
        Token RCURLY116=null;
        ParserRuleReturnScope stmtl =null;
        
        ASTTree LCURLY115_tree=null;
        ASTTree RCURLY116_tree=null;
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleSubtreeStream stream_statementList=new RewriteRuleSubtreeStream(adaptor,"rule statementList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:976:3: ( LCURLY stmtl= statementList RCURLY -> ^( SLIST ( $stmtl)? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:977:3: LCURLY stmtl= statementList RCURLY
            {
            LCURLY115=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_compoundStatement4293); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY115);
        
            pushFollow(FOLLOW_statementList_in_compoundStatement4304);
            stmtl=statementList();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_statementList.add(stmtl.getTree());
            RCURLY116=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_compoundStatement4306); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY116);
        
            // AST REWRITE
            // elements: stmtl
            // token labels: 
            // rule labels: retval, stmtl
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_stmtl=new RewriteRuleSubtreeStream(adaptor,"rule stmtl",stmtl!=null?stmtl.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 980:5: -> ^( SLIST ( $stmtl)? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:980:8: ^( SLIST ( $stmtl)? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(SLIST, "SLIST"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:980:17: ( $stmtl)?
                if ( stream_stmtl.hasNext() ) {
                    adaptor.addChild(root_1, stream_stmtl.nextTree());
                }
                stream_stmtl.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            if (top) {
                retval.tree.setSourceCode(this.getSource(
                        this.adjustStartToken((stmtl!=null?(stmtl.start):null)),
                        this.adjustStopToken((stmtl!=null?(stmtl.stop):null))));
            }
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 61, compoundStatement_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "statementList"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:983:1: statementList : ( explicitConstructorInvocation )? ( statement )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.statementList_return statementList() throws RecognitionException {
        JavaParser.statementList_return retval = new JavaParser.statementList_return();
        retval.start = input.LT(1);
        int statementList_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope explicitConstructorInvocation117 =null;
        ParserRuleReturnScope statement118 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:984:3: ( ( explicitConstructorInvocation )? ( statement )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:985:3: ( explicitConstructorInvocation )? ( statement )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:985:3: ( explicitConstructorInvocation )?
            int alt84=2;
            int LA84_0 = input.LA(1);
            if ( (LA84_0==171) ) {
                int LA84_1 = input.LA(2);
                if ( (LA84_1==LPAREN) ) {
                    alt84=1;
                }
            }
            else if ( (LA84_0==SUPER) ) {
                int LA84_2 = input.LA(2);
                if ( (LA84_2==LPAREN) ) {
                    alt84=1;
                }
            }
            switch (alt84) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:985:3: explicitConstructorInvocation
                    {
                    pushFollow(FOLLOW_explicitConstructorInvocation_in_statementList4336);
                    explicitConstructorInvocation117=explicitConstructorInvocation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, explicitConstructorInvocation117.getTree());
        
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:985:34: ( statement )*
            loop85:
            while (true) {
                int alt85=2;
                int LA85_0 = input.LA(1);
                if ( (LA85_0==AT||LA85_0==BNOT||LA85_0==CHAR_LITERAL||LA85_0==DEC||LA85_0==DEFAULT||LA85_0==IDENT||LA85_0==INC||LA85_0==LCURLY||LA85_0==LNOT||LA85_0==LPAREN||LA85_0==MINUS||LA85_0==NEW||LA85_0==NUM_LITERAL||LA85_0==PLUS||LA85_0==RETURN||LA85_0==SEMI||(LA85_0 >= STRING_LITERAL && LA85_0 <= SUPER)||(LA85_0 >= 133 && LA85_0 <= 137)||(LA85_0 >= 140 && LA85_0 <= 144)||(LA85_0 >= 148 && LA85_0 <= 149)||(LA85_0 >= 151 && LA85_0 <= 153)||LA85_0==157||(LA85_0 >= 159 && LA85_0 <= 161)||(LA85_0 >= 163 && LA85_0 <= 173)||(LA85_0 >= 175 && LA85_0 <= 180)) ) {
                    alt85=1;
                }
        
                switch (alt85) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:985:34: statement
                    {
                    pushFollow(FOLLOW_statement_in_statementList4339);
                    statement118=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement118.getTree());
        
                    }
                    break;
        
                default :
                    break loop85;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 62, statementList_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "explicitConstructorInvocation"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:989:1: explicitConstructorInvocation : ( 'this' lp1= LPAREN argList RPAREN SEMI -> ^( CTOR_CALL argList ) | SUPER lp2= LPAREN argList RPAREN SEMI -> ^( SUPER_CTOR_CALL argList ) );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation() throws RecognitionException {
        JavaParser.explicitConstructorInvocation_return retval = new JavaParser.explicitConstructorInvocation_return();
        retval.start = input.LT(1);
        int explicitConstructorInvocation_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lp1=null;
        Token lp2=null;
        Token string_literal119=null;
        Token RPAREN121=null;
        Token SEMI122=null;
        Token SUPER123=null;
        Token RPAREN125=null;
        Token SEMI126=null;
        ParserRuleReturnScope argList120 =null;
        ParserRuleReturnScope argList124 =null;
        
        ASTTree lp1_tree=null;
        ASTTree lp2_tree=null;
        ASTTree string_literal119_tree=null;
        ASTTree RPAREN121_tree=null;
        ASTTree SEMI122_tree=null;
        ASTTree SUPER123_tree=null;
        ASTTree RPAREN125_tree=null;
        ASTTree SEMI126_tree=null;
        RewriteRuleTokenStream stream_171=new RewriteRuleTokenStream(adaptor,"token 171");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_SUPER=new RewriteRuleTokenStream(adaptor,"token SUPER");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_argList=new RewriteRuleSubtreeStream(adaptor,"rule argList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:990:3: ( 'this' lp1= LPAREN argList RPAREN SEMI -> ^( CTOR_CALL argList ) | SUPER lp2= LPAREN argList RPAREN SEMI -> ^( SUPER_CTOR_CALL argList ) )
            int alt86=2;
            int LA86_0 = input.LA(1);
            if ( (LA86_0==171) ) {
                alt86=1;
            }
            else if ( (LA86_0==SUPER) ) {
                alt86=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);
                throw nvae;
            }
        
            switch (alt86) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:991:3: 'this' lp1= LPAREN argList RPAREN SEMI
                    {
                    string_literal119=(Token)match(input,171,FOLLOW_171_in_explicitConstructorInvocation4357); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_171.add(string_literal119);
        
                    lp1=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_explicitConstructorInvocation4361); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(lp1);
        
                    pushFollow(FOLLOW_argList_in_explicitConstructorInvocation4363);
                    argList120=argList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_argList.add(argList120.getTree());
                    RPAREN121=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_explicitConstructorInvocation4365); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN121);
        
                    SEMI122=(Token)match(input,SEMI,FOLLOW_SEMI_in_explicitConstructorInvocation4367); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI122);
        
                    // AST REWRITE
                    // elements: argList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 992:5: -> ^( CTOR_CALL argList )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:992:8: ^( CTOR_CALL argList )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(CTOR_CALL, "CTOR_CALL"), root_1);
                        adaptor.addChild(root_1, stream_argList.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:993:5: SUPER lp2= LPAREN argList RPAREN SEMI
                    {
                    SUPER123=(Token)match(input,SUPER,FOLLOW_SUPER_in_explicitConstructorInvocation4386); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SUPER.add(SUPER123);
        
                    lp2=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_explicitConstructorInvocation4390); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(lp2);
        
                    pushFollow(FOLLOW_argList_in_explicitConstructorInvocation4392);
                    argList124=argList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_argList.add(argList124.getTree());
                    RPAREN125=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_explicitConstructorInvocation4394); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN125);
        
                    SEMI126=(Token)match(input,SEMI,FOLLOW_SEMI_in_explicitConstructorInvocation4396); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI126);
        
                    // AST REWRITE
                    // elements: argList
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 994:5: -> ^( SUPER_CTOR_CALL argList )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:994:8: ^( SUPER_CTOR_CALL argList )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(SUPER_CTOR_CALL, "SUPER_CTOR_CALL"), root_1);
                        adaptor.addChild(root_1, stream_argList.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 63, explicitConstructorInvocation_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "statement"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:999:1: statement : ( compoundStatement[false] -> compoundStatement | ( declaration )=> declaration SEMI -> declaration | expression SEMI -> expression |ama= annotmodannot classDefinition[$ama.tree] -> classDefinition | IDENT COLON statement -> ^( LABELED_STAT IDENT statement ) | 'if' LPAREN expression RPAREN ths= statement ( options {k=1; } : ( 'else' )=> 'else' els= statement )? -> ^( 'if' expression $ths ( $els)? ) | 'for' LPAREN ( ( parameterDeclaration COLON )=> parameterDeclaration COLON expression | forInit SEMI forCond SEMI forIter ) RPAREN statement -> ^( 'for' ( parameterDeclaration )? ( expression )? ( forInit )? ( forCond )? ( forIter )? statement ) | 'while' LPAREN expression RPAREN statement -> ^( 'while' expression statement ) | 'do' statement 'while' LPAREN expression RPAREN SEMI -> ^( 'do' statement expression ) | 'break' ( IDENT )? SEMI -> ^( 'break' ( IDENT )? ) | 'continue' ( IDENT )? SEMI -> ^( 'continue' ( IDENT )? ) | returnExpression -> returnExpression | 'switch' LPAREN expression RPAREN LCURLY ( casesGroup )* RCURLY -> ^( 'switch' expression ( casesGroup )* ) | tryBlock -> tryBlock | 'throw' expression SEMI -> ^( 'throw' expression ) | 'synchronized' LPAREN expression RPAREN compoundStatement[false] -> ^( 'synchronized' expression compoundStatement ) | 'assert' expression ( COLON expression )? SEMI -> ^( 'assert' expression ( expression )? ) | SEMI -> EMPTY_STAT );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.statement_return statement() throws RecognitionException {
        JavaParser.statement_return retval = new JavaParser.statement_return();
        retval.start = input.LT(1);
        int statement_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token SEMI129=null;
        Token SEMI131=null;
        Token IDENT133=null;
        Token COLON134=null;
        Token string_literal136=null;
        Token LPAREN137=null;
        Token RPAREN139=null;
        Token string_literal140=null;
        Token string_literal141=null;
        Token LPAREN142=null;
        Token COLON144=null;
        Token SEMI147=null;
        Token SEMI149=null;
        Token RPAREN151=null;
        Token string_literal153=null;
        Token LPAREN154=null;
        Token RPAREN156=null;
        Token string_literal158=null;
        Token string_literal160=null;
        Token LPAREN161=null;
        Token RPAREN163=null;
        Token SEMI164=null;
        Token string_literal165=null;
        Token IDENT166=null;
        Token SEMI167=null;
        Token string_literal168=null;
        Token IDENT169=null;
        Token SEMI170=null;
        Token string_literal172=null;
        Token LPAREN173=null;
        Token RPAREN175=null;
        Token LCURLY176=null;
        Token RCURLY178=null;
        Token string_literal180=null;
        Token SEMI182=null;
        Token string_literal183=null;
        Token LPAREN184=null;
        Token RPAREN186=null;
        Token string_literal188=null;
        Token COLON190=null;
        Token SEMI192=null;
        Token SEMI193=null;
        ParserRuleReturnScope ama =null;
        ParserRuleReturnScope ths =null;
        ParserRuleReturnScope els =null;
        ParserRuleReturnScope compoundStatement127 =null;
        ParserRuleReturnScope declaration128 =null;
        ParserRuleReturnScope expression130 =null;
        ParserRuleReturnScope classDefinition132 =null;
        ParserRuleReturnScope statement135 =null;
        ParserRuleReturnScope expression138 =null;
        ParserRuleReturnScope parameterDeclaration143 =null;
        ParserRuleReturnScope expression145 =null;
        ParserRuleReturnScope forInit146 =null;
        ParserRuleReturnScope forCond148 =null;
        ParserRuleReturnScope forIter150 =null;
        ParserRuleReturnScope statement152 =null;
        ParserRuleReturnScope expression155 =null;
        ParserRuleReturnScope statement157 =null;
        ParserRuleReturnScope statement159 =null;
        ParserRuleReturnScope expression162 =null;
        ParserRuleReturnScope returnExpression171 =null;
        ParserRuleReturnScope expression174 =null;
        ParserRuleReturnScope casesGroup177 =null;
        ParserRuleReturnScope tryBlock179 =null;
        ParserRuleReturnScope expression181 =null;
        ParserRuleReturnScope expression185 =null;
        ParserRuleReturnScope compoundStatement187 =null;
        ParserRuleReturnScope expression189 =null;
        ParserRuleReturnScope expression191 =null;
        
        ASTTree SEMI129_tree=null;
        ASTTree SEMI131_tree=null;
        ASTTree IDENT133_tree=null;
        ASTTree COLON134_tree=null;
        ASTTree string_literal136_tree=null;
        ASTTree LPAREN137_tree=null;
        ASTTree RPAREN139_tree=null;
        ASTTree string_literal140_tree=null;
        ASTTree string_literal141_tree=null;
        ASTTree LPAREN142_tree=null;
        ASTTree COLON144_tree=null;
        ASTTree SEMI147_tree=null;
        ASTTree SEMI149_tree=null;
        ASTTree RPAREN151_tree=null;
        ASTTree string_literal153_tree=null;
        ASTTree LPAREN154_tree=null;
        ASTTree RPAREN156_tree=null;
        ASTTree string_literal158_tree=null;
        ASTTree string_literal160_tree=null;
        ASTTree LPAREN161_tree=null;
        ASTTree RPAREN163_tree=null;
        ASTTree SEMI164_tree=null;
        ASTTree string_literal165_tree=null;
        ASTTree IDENT166_tree=null;
        ASTTree SEMI167_tree=null;
        ASTTree string_literal168_tree=null;
        ASTTree IDENT169_tree=null;
        ASTTree SEMI170_tree=null;
        ASTTree string_literal172_tree=null;
        ASTTree LPAREN173_tree=null;
        ASTTree RPAREN175_tree=null;
        ASTTree LCURLY176_tree=null;
        ASTTree RCURLY178_tree=null;
        ASTTree string_literal180_tree=null;
        ASTTree SEMI182_tree=null;
        ASTTree string_literal183_tree=null;
        ASTTree LPAREN184_tree=null;
        ASTTree RPAREN186_tree=null;
        ASTTree string_literal188_tree=null;
        ASTTree COLON190_tree=null;
        ASTTree SEMI192_tree=null;
        ASTTree SEMI193_tree=null;
        RewriteRuleTokenStream stream_134=new RewriteRuleTokenStream(adaptor,"token 134");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_143=new RewriteRuleTokenStream(adaptor,"token 143");
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_145=new RewriteRuleTokenStream(adaptor,"token 145");
        RewriteRuleTokenStream stream_136=new RewriteRuleTokenStream(adaptor,"token 136");
        RewriteRuleTokenStream stream_169=new RewriteRuleTokenStream(adaptor,"token 169");
        RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
        RewriteRuleTokenStream stream_170=new RewriteRuleTokenStream(adaptor,"token 170");
        RewriteRuleTokenStream stream_152=new RewriteRuleTokenStream(adaptor,"token 152");
        RewriteRuleTokenStream stream_153=new RewriteRuleTokenStream(adaptor,"token 153");
        RewriteRuleTokenStream stream_180=new RewriteRuleTokenStream(adaptor,"token 180");
        RewriteRuleTokenStream stream_IDENT=new RewriteRuleTokenStream(adaptor,"token IDENT");
        RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
        RewriteRuleTokenStream stream_173=new RewriteRuleTokenStream(adaptor,"token 173");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleTokenStream stream_142=new RewriteRuleTokenStream(adaptor,"token 142");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        RewriteRuleSubtreeStream stream_annotmodannot=new RewriteRuleSubtreeStream(adaptor,"rule annotmodannot");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_returnExpression=new RewriteRuleSubtreeStream(adaptor,"rule returnExpression");
        RewriteRuleSubtreeStream stream_forIter=new RewriteRuleSubtreeStream(adaptor,"rule forIter");
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        RewriteRuleSubtreeStream stream_compoundStatement=new RewriteRuleSubtreeStream(adaptor,"rule compoundStatement");
        RewriteRuleSubtreeStream stream_parameterDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule parameterDeclaration");
        RewriteRuleSubtreeStream stream_classDefinition=new RewriteRuleSubtreeStream(adaptor,"rule classDefinition");
        RewriteRuleSubtreeStream stream_casesGroup=new RewriteRuleSubtreeStream(adaptor,"rule casesGroup");
        RewriteRuleSubtreeStream stream_forInit=new RewriteRuleSubtreeStream(adaptor,"rule forInit");
        RewriteRuleSubtreeStream stream_tryBlock=new RewriteRuleSubtreeStream(adaptor,"rule tryBlock");
        RewriteRuleSubtreeStream stream_forCond=new RewriteRuleSubtreeStream(adaptor,"rule forCond");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1001:3: ( compoundStatement[false] -> compoundStatement | ( declaration )=> declaration SEMI -> declaration | expression SEMI -> expression |ama= annotmodannot classDefinition[$ama.tree] -> classDefinition | IDENT COLON statement -> ^( LABELED_STAT IDENT statement ) | 'if' LPAREN expression RPAREN ths= statement ( options {k=1; } : ( 'else' )=> 'else' els= statement )? -> ^( 'if' expression $ths ( $els)? ) | 'for' LPAREN ( ( parameterDeclaration COLON )=> parameterDeclaration COLON expression | forInit SEMI forCond SEMI forIter ) RPAREN statement -> ^( 'for' ( parameterDeclaration )? ( expression )? ( forInit )? ( forCond )? ( forIter )? statement ) | 'while' LPAREN expression RPAREN statement -> ^( 'while' expression statement ) | 'do' statement 'while' LPAREN expression RPAREN SEMI -> ^( 'do' statement expression ) | 'break' ( IDENT )? SEMI -> ^( 'break' ( IDENT )? ) | 'continue' ( IDENT )? SEMI -> ^( 'continue' ( IDENT )? ) | returnExpression -> returnExpression | 'switch' LPAREN expression RPAREN LCURLY ( casesGroup )* RCURLY -> ^( 'switch' expression ( casesGroup )* ) | tryBlock -> tryBlock | 'throw' expression SEMI -> ^( 'throw' expression ) | 'synchronized' LPAREN expression RPAREN compoundStatement[false] -> ^( 'synchronized' expression compoundStatement ) | 'assert' expression ( COLON expression )? SEMI -> ^( 'assert' expression ( expression )? ) | SEMI -> EMPTY_STAT )
            int alt93=18;
            switch ( input.LA(1) ) {
            case LCURLY:
                {
                alt93=1;
                }
                break;
            case AT:
                {
                int LA93_2 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
                else if ( (synpred126_Java()) ) {
                    alt93=4;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 2, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 170:
                {
                int LA93_3 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred126_Java()) ) {
                    alt93=4;
                }
                else if ( (synpred143_Java()) ) {
                    alt93=16;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 3, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case IDENT:
                {
                int LA93_4 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
                else if ( (synpred127_Java()) ) {
                    alt93=5;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 4, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 135:
                {
                int LA93_5 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 5, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 137:
                {
                int LA93_6 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 6, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 140:
                {
                int LA93_7 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 7, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 166:
                {
                int LA93_8 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 8, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 157:
                {
                int LA93_9 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 9, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 151:
                {
                int LA93_10 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 10, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 159:
                {
                int LA93_11 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 11, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 144:
                {
                int LA93_12 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred125_Java()) ) {
                    alt93=3;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 12, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case BNOT:
            case CHAR_LITERAL:
            case DEC:
            case INC:
            case LNOT:
            case LPAREN:
            case MINUS:
            case NEW:
            case NUM_LITERAL:
            case PLUS:
            case STRING_LITERAL:
            case SUPER:
            case 148:
            case 161:
            case 171:
            case 176:
            case 178:
                {
                alt93=3;
                }
                break;
            case 141:
                {
                alt93=4;
                }
                break;
            case 153:
                {
                alt93=6;
                }
                break;
            case 152:
                {
                alt93=7;
                }
                break;
            case 180:
                {
                alt93=8;
                }
                break;
            case 143:
                {
                alt93=9;
                }
                break;
            case 136:
                {
                alt93=10;
                }
                break;
            case 142:
                {
                alt93=11;
                }
                break;
            case RETURN:
                {
                alt93=12;
                }
                break;
            case 169:
                {
                alt93=13;
                }
                break;
            case 177:
                {
                alt93=14;
                }
                break;
            case 173:
                {
                alt93=15;
                }
                break;
            case DEFAULT:
            case 133:
            case 149:
            case 160:
            case 163:
            case 164:
            case 165:
            case 167:
            case 168:
            case 172:
            case 175:
            case 179:
                {
                int LA93_39 = input.LA(2);
                if ( (synpred124_Java()) ) {
                    alt93=2;
                }
                else if ( (synpred126_Java()) ) {
                    alt93=4;
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 93, 39, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case 134:
                {
                alt93=17;
                }
                break;
            case SEMI:
                {
                alt93=18;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);
                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1002:3: compoundStatement[false]
                    {
                    pushFollow(FOLLOW_compoundStatement_in_statement4429);
                    compoundStatement127=compoundStatement(false);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_compoundStatement.add(compoundStatement127.getTree());
                    // AST REWRITE
                    // elements: compoundStatement
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1003:5: -> compoundStatement
                    {
                        adaptor.addChild(root_0, stream_compoundStatement.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1008:5: ( declaration )=> declaration SEMI
                    {
                    pushFollow(FOLLOW_declaration_in_statement4462);
                    declaration128=declaration();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declaration.add(declaration128.getTree());
                    SEMI129=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4464); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI129);
        
                    // AST REWRITE
                    // elements: declaration
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1009:5: -> declaration
                    {
                        adaptor.addChild(root_0, stream_declaration.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1014:5: expression SEMI
                    {
                    pushFollow(FOLLOW_expression_in_statement4488);
                    expression130=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression130.getTree());
                    SEMI131=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4490); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI131);
        
                    // AST REWRITE
                    // elements: expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1015:5: -> expression
                    {
                        adaptor.addChild(root_0, stream_expression.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1018:5: ama= annotmodannot classDefinition[$ama.tree]
                    {
                    pushFollow(FOLLOW_annotmodannot_in_statement4510);
                    ama=annotmodannot();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_annotmodannot.add(ama.getTree());
                    pushFollow(FOLLOW_classDefinition_in_statement4512);
                    classDefinition132=classDefinition((ama!=null?((ASTTree)ama.getTree()):null));
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classDefinition.add(classDefinition132.getTree());
                    // AST REWRITE
                    // elements: classDefinition
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1019:5: -> classDefinition
                    {
                        adaptor.addChild(root_0, stream_classDefinition.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1022:5: IDENT COLON statement
                    {
                    IDENT133=(Token)match(input,IDENT,FOLLOW_IDENT_in_statement4531); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IDENT.add(IDENT133);
        
                    COLON134=(Token)match(input,COLON,FOLLOW_COLON_in_statement4533); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON134);
        
                    pushFollow(FOLLOW_statement_in_statement4535);
                    statement135=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement135.getTree());
                    // AST REWRITE
                    // elements: statement, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1023:5: -> ^( LABELED_STAT IDENT statement )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1023:8: ^( LABELED_STAT IDENT statement )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(LABELED_STAT, "LABELED_STAT"), root_1);
                        adaptor.addChild(root_1, stream_IDENT.nextNode());
                        adaptor.addChild(root_1, stream_statement.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1026:5: 'if' LPAREN expression RPAREN ths= statement ( options {k=1; } : ( 'else' )=> 'else' els= statement )?
                    {
                    string_literal136=(Token)match(input,153,FOLLOW_153_in_statement4560); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_153.add(string_literal136);
        
                    LPAREN137=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_statement4562); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN137);
        
                    pushFollow(FOLLOW_expression_in_statement4564);
                    expression138=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression138.getTree());
                    RPAREN139=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_statement4566); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN139);
        
                    pushFollow(FOLLOW_statement_in_statement4570);
                    ths=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(ths.getTree());
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1027:3: ( options {k=1; } : ( 'else' )=> 'else' els= statement )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);
                    if ( (LA87_0==145) ) {
                        int LA87_1 = input.LA(2);
                        if ( (synpred128_Java()) ) {
                            alt87=1;
                        }
                    }
                    switch (alt87) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1027:21: ( 'else' )=> 'else' els= statement
                            {
                            string_literal140=(Token)match(input,145,FOLLOW_145_in_statement4590); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_145.add(string_literal140);
        
                            pushFollow(FOLLOW_statement_in_statement4594);
                            els=statement();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_statement.add(els.getTree());
                            }
                            break;
        
                    }
        
                    // AST REWRITE
                    // elements: 153, expression, ths, els
                    // token labels: 
                    // rule labels: retval, ths, els
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
                    RewriteRuleSubtreeStream stream_ths=new RewriteRuleSubtreeStream(adaptor,"rule ths",ths!=null?ths.getTree():null);
                    RewriteRuleSubtreeStream stream_els=new RewriteRuleSubtreeStream(adaptor,"rule els",els!=null?els.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1029:5: -> ^( 'if' expression $ths ( $els)? )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1029:8: ^( 'if' expression $ths ( $els)? )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_153.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        adaptor.addChild(root_1, stream_ths.nextTree());
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1029:32: ( $els)?
                        if ( stream_els.hasNext() ) {
                            adaptor.addChild(root_1, stream_els.nextTree());
                        }
                        stream_els.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 7 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1032:5: 'for' LPAREN ( ( parameterDeclaration COLON )=> parameterDeclaration COLON expression | forInit SEMI forCond SEMI forIter ) RPAREN statement
                    {
                    string_literal141=(Token)match(input,152,FOLLOW_152_in_statement4629); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_152.add(string_literal141);
        
                    LPAREN142=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_statement4631); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN142);
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1033:3: ( ( parameterDeclaration COLON )=> parameterDeclaration COLON expression | forInit SEMI forCond SEMI forIter )
                    int alt88=2;
                    switch ( input.LA(1) ) {
                    case AT:
                        {
                        int LA88_1 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 149:
                        {
                        int LA88_2 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case IDENT:
                        {
                        int LA88_3 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 135:
                        {
                        int LA88_4 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 137:
                        {
                        int LA88_5 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 140:
                        {
                        int LA88_6 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 166:
                        {
                        int LA88_7 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 157:
                        {
                        int LA88_8 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 151:
                        {
                        int LA88_9 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 159:
                        {
                        int LA88_10 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case 144:
                        {
                        int LA88_11 = input.LA(2);
                        if ( (synpred130_Java()) ) {
                            alt88=1;
                        }
                        else if ( (true) ) {
                            alt88=2;
                        }
        
                        }
                        break;
                    case BNOT:
                    case CHAR_LITERAL:
                    case DEC:
                    case DEFAULT:
                    case INC:
                    case LNOT:
                    case LPAREN:
                    case MINUS:
                    case NEW:
                    case NUM_LITERAL:
                    case PLUS:
                    case SEMI:
                    case STRING_LITERAL:
                    case SUPER:
                    case 133:
                    case 148:
                    case 160:
                    case 161:
                    case 163:
                    case 164:
                    case 165:
                    case 167:
                    case 168:
                    case 170:
                    case 171:
                    case 172:
                    case 175:
                    case 176:
                    case 178:
                    case 179:
                        {
                        alt88=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 88, 0, input);
                        throw nvae;
                    }
                    switch (alt88) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1034:5: ( parameterDeclaration COLON )=> parameterDeclaration COLON expression
                            {
                            pushFollow(FOLLOW_parameterDeclaration_in_statement4649);
                            parameterDeclaration143=parameterDeclaration();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_parameterDeclaration.add(parameterDeclaration143.getTree());
                            COLON144=(Token)match(input,COLON,FOLLOW_COLON_in_statement4651); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COLON.add(COLON144);
        
                            pushFollow(FOLLOW_expression_in_statement4653);
                            expression145=expression();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression145.getTree());
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1035:7: forInit SEMI forCond SEMI forIter
                            {
                            pushFollow(FOLLOW_forInit_in_statement4661);
                            forInit146=forInit();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_forInit.add(forInit146.getTree());
                            SEMI147=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4663); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_SEMI.add(SEMI147);
        
                            pushFollow(FOLLOW_forCond_in_statement4670);
                            forCond148=forCond();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_forCond.add(forCond148.getTree());
                            SEMI149=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4672); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_SEMI.add(SEMI149);
        
                            pushFollow(FOLLOW_forIter_in_statement4679);
                            forIter150=forIter();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_forIter.add(forIter150.getTree());
                            }
                            break;
        
                    }
        
                    RPAREN151=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_statement4688); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN151);
        
                    pushFollow(FOLLOW_statement_in_statement4690);
                    statement152=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement152.getTree());
                    // AST REWRITE
                    // elements: forCond, 152, forInit, forIter, expression, parameterDeclaration, statement
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1040:5: -> ^( 'for' ( parameterDeclaration )? ( expression )? ( forInit )? ( forCond )? ( forIter )? statement )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1040:8: ^( 'for' ( parameterDeclaration )? ( expression )? ( forInit )? ( forCond )? ( forIter )? statement )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_152.nextNode(), root_1);
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1040:16: ( parameterDeclaration )?
                        if ( stream_parameterDeclaration.hasNext() ) {
                            adaptor.addChild(root_1, stream_parameterDeclaration.nextTree());
                        }
                        stream_parameterDeclaration.reset();
        
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1040:38: ( expression )?
                        if ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());
                        }
                        stream_expression.reset();
        
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1040:50: ( forInit )?
                        if ( stream_forInit.hasNext() ) {
                            adaptor.addChild(root_1, stream_forInit.nextTree());
                        }
                        stream_forInit.reset();
        
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1040:59: ( forCond )?
                        if ( stream_forCond.hasNext() ) {
                            adaptor.addChild(root_1, stream_forCond.nextTree());
                        }
                        stream_forCond.reset();
        
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1040:68: ( forIter )?
                        if ( stream_forIter.hasNext() ) {
                            adaptor.addChild(root_1, stream_forIter.nextTree());
                        }
                        stream_forIter.reset();
        
                        adaptor.addChild(root_1, stream_statement.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 8 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1043:5: 'while' LPAREN expression RPAREN statement
                    {
                    string_literal153=(Token)match(input,180,FOLLOW_180_in_statement4729); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_180.add(string_literal153);
        
                    LPAREN154=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_statement4731); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN154);
        
                    pushFollow(FOLLOW_expression_in_statement4733);
                    expression155=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression155.getTree());
                    RPAREN156=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_statement4735); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN156);
        
                    pushFollow(FOLLOW_statement_in_statement4737);
                    statement157=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement157.getTree());
                    // AST REWRITE
                    // elements: expression, statement, 180
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1044:5: -> ^( 'while' expression statement )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1044:8: ^( 'while' expression statement )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_180.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        adaptor.addChild(root_1, stream_statement.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 9 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1047:5: 'do' statement 'while' LPAREN expression RPAREN SEMI
                    {
                    string_literal158=(Token)match(input,143,FOLLOW_143_in_statement4762); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_143.add(string_literal158);
        
                    pushFollow(FOLLOW_statement_in_statement4764);
                    statement159=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement159.getTree());
                    string_literal160=(Token)match(input,180,FOLLOW_180_in_statement4766); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_180.add(string_literal160);
        
                    LPAREN161=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_statement4768); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN161);
        
                    pushFollow(FOLLOW_expression_in_statement4770);
                    expression162=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression162.getTree());
                    RPAREN163=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_statement4772); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN163);
        
                    SEMI164=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4774); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI164);
        
                    // AST REWRITE
                    // elements: 143, statement, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1048:5: -> ^( 'do' statement expression )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1048:8: ^( 'do' statement expression )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_143.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_statement.nextTree());
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 10 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1051:5: 'break' ( IDENT )? SEMI
                    {
                    string_literal165=(Token)match(input,136,FOLLOW_136_in_statement4799); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_136.add(string_literal165);
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1051:13: ( IDENT )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);
                    if ( (LA89_0==IDENT) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1051:13: IDENT
                            {
                            IDENT166=(Token)match(input,IDENT,FOLLOW_IDENT_in_statement4801); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_IDENT.add(IDENT166);
        
                            }
                            break;
        
                    }
        
                    SEMI167=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4804); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI167);
        
                    // AST REWRITE
                    // elements: IDENT, 136
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1052:5: -> ^( 'break' ( IDENT )? )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1052:8: ^( 'break' ( IDENT )? )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_136.nextNode(), root_1);
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1052:18: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_1, stream_IDENT.nextNode());
                        }
                        stream_IDENT.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 11 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1055:5: 'continue' ( IDENT )? SEMI
                    {
                    string_literal168=(Token)match(input,142,FOLLOW_142_in_statement4828); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_142.add(string_literal168);
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1055:16: ( IDENT )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);
                    if ( (LA90_0==IDENT) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1055:16: IDENT
                            {
                            IDENT169=(Token)match(input,IDENT,FOLLOW_IDENT_in_statement4830); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_IDENT.add(IDENT169);
        
                            }
                            break;
        
                    }
        
                    SEMI170=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4833); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI170);
        
                    // AST REWRITE
                    // elements: 142, IDENT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1056:5: -> ^( 'continue' ( IDENT )? )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1056:8: ^( 'continue' ( IDENT )? )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_142.nextNode(), root_1);
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1056:21: ( IDENT )?
                        if ( stream_IDENT.hasNext() ) {
                            adaptor.addChild(root_1, stream_IDENT.nextNode());
                        }
                        stream_IDENT.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 12 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1059:5: returnExpression
                    {
                    pushFollow(FOLLOW_returnExpression_in_statement4857);
                    returnExpression171=returnExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_returnExpression.add(returnExpression171.getTree());
                    // AST REWRITE
                    // elements: returnExpression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1059:22: -> returnExpression
                    {
                        adaptor.addChild(root_0, stream_returnExpression.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 13 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1062:5: 'switch' LPAREN expression RPAREN LCURLY ( casesGroup )* RCURLY
                    {
                    string_literal172=(Token)match(input,169,FOLLOW_169_in_statement4871); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_169.add(string_literal172);
        
                    LPAREN173=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_statement4873); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN173);
        
                    pushFollow(FOLLOW_expression_in_statement4875);
                    expression174=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression174.getTree());
                    RPAREN175=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_statement4877); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN175);
        
                    LCURLY176=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_statement4879); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY176);
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1062:46: ( casesGroup )*
                    loop91:
                    while (true) {
                        int alt91=2;
                        int LA91_0 = input.LA(1);
                        if ( (LA91_0==DEFAULT||LA91_0==138) ) {
                            alt91=1;
                        }
        
                        switch (alt91) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1062:46: casesGroup
                            {
                            pushFollow(FOLLOW_casesGroup_in_statement4881);
                            casesGroup177=casesGroup();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_casesGroup.add(casesGroup177.getTree());
                            }
                            break;
        
                        default :
                            break loop91;
                        }
                    }
        
                    RCURLY178=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_statement4884); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY178);
        
                    // AST REWRITE
                    // elements: 169, casesGroup, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1063:5: -> ^( 'switch' expression ( casesGroup )* )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1063:8: ^( 'switch' expression ( casesGroup )* )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_169.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1063:30: ( casesGroup )*
                        while ( stream_casesGroup.hasNext() ) {
                            adaptor.addChild(root_1, stream_casesGroup.nextTree());
                        }
                        stream_casesGroup.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 14 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1066:5: tryBlock
                    {
                    pushFollow(FOLLOW_tryBlock_in_statement4910);
                    tryBlock179=tryBlock();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_tryBlock.add(tryBlock179.getTree());
                    // AST REWRITE
                    // elements: tryBlock
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1067:5: -> tryBlock
                    {
                        adaptor.addChild(root_0, stream_tryBlock.nextTree());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 15 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1070:5: 'throw' expression SEMI
                    {
                    string_literal180=(Token)match(input,173,FOLLOW_173_in_statement4928); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_173.add(string_literal180);
        
                    pushFollow(FOLLOW_expression_in_statement4930);
                    expression181=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression181.getTree());
                    SEMI182=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4932); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI182);
        
                    // AST REWRITE
                    // elements: 173, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1071:5: -> ^( 'throw' expression )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1071:8: ^( 'throw' expression )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_173.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 16 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1074:5: 'synchronized' LPAREN expression RPAREN compoundStatement[false]
                    {
                    string_literal183=(Token)match(input,170,FOLLOW_170_in_statement4955); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_170.add(string_literal183);
        
                    LPAREN184=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_statement4957); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN184);
        
                    pushFollow(FOLLOW_expression_in_statement4959);
                    expression185=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression185.getTree());
                    RPAREN186=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_statement4961); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN186);
        
                    pushFollow(FOLLOW_compoundStatement_in_statement4963);
                    compoundStatement187=compoundStatement(false);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_compoundStatement.add(compoundStatement187.getTree());
                    // AST REWRITE
                    // elements: expression, compoundStatement, 170
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1075:5: -> ^( 'synchronized' expression compoundStatement )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1075:8: ^( 'synchronized' expression compoundStatement )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_170.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        adaptor.addChild(root_1, stream_compoundStatement.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 17 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1078:5: 'assert' expression ( COLON expression )? SEMI
                    {
                    string_literal188=(Token)match(input,134,FOLLOW_134_in_statement4989); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_134.add(string_literal188);
        
                    pushFollow(FOLLOW_expression_in_statement4991);
                    expression189=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression189.getTree());
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1078:25: ( COLON expression )?
                    int alt92=2;
                    int LA92_0 = input.LA(1);
                    if ( (LA92_0==COLON) ) {
                        alt92=1;
                    }
                    switch (alt92) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1078:26: COLON expression
                            {
                            COLON190=(Token)match(input,COLON,FOLLOW_COLON_in_statement4994); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COLON.add(COLON190);
        
                            pushFollow(FOLLOW_expression_in_statement4996);
                            expression191=expression();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_expression.add(expression191.getTree());
                            }
                            break;
        
                    }
        
                    SEMI192=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement5000); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI192);
        
                    // AST REWRITE
                    // elements: expression, 134, expression
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1079:5: -> ^( 'assert' expression ( expression )? )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1079:8: ^( 'assert' expression ( expression )? )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_134.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_expression.nextTree());
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1079:30: ( expression )?
                        if ( stream_expression.hasNext() ) {
                            adaptor.addChild(root_1, stream_expression.nextTree());
                        }
                        stream_expression.reset();
        
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 18 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1082:5: SEMI
                    {
                    SEMI193=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement5026); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI193);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1083:5: -> EMPTY_STAT
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(EMPTY_STAT, "EMPTY_STAT"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 64, statement_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "casesGroup"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1086:1: casesGroup : ( options {greedy=true; } : aCase )+ caseSList -> ^( CASE_GROUP ( aCase )+ caseSList ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.casesGroup_return casesGroup() throws RecognitionException {
        JavaParser.casesGroup_return retval = new JavaParser.casesGroup_return();
        retval.start = input.LT(1);
        int casesGroup_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope aCase194 =null;
        ParserRuleReturnScope caseSList195 =null;
        
        RewriteRuleSubtreeStream stream_aCase=new RewriteRuleSubtreeStream(adaptor,"rule aCase");
        RewriteRuleSubtreeStream stream_caseSList=new RewriteRuleSubtreeStream(adaptor,"rule caseSList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1087:3: ( ( options {greedy=true; } : aCase )+ caseSList -> ^( CASE_GROUP ( aCase )+ caseSList ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1088:3: ( options {greedy=true; } : aCase )+ caseSList
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1088:3: ( options {greedy=true; } : aCase )+
            int cnt94=0;
            loop94:
            while (true) {
                int alt94=2;
                int LA94_0 = input.LA(1);
                if ( (LA94_0==DEFAULT) ) {
                    int LA94_2 = input.LA(2);
                    if ( (synpred146_Java()) ) {
                        alt94=1;
                    }
        
                }
                else if ( (LA94_0==138) ) {
                    int LA94_3 = input.LA(2);
                    if ( (synpred146_Java()) ) {
                        alt94=1;
                    }
        
                }
        
                switch (alt94) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1092:27: aCase
                    {
                    pushFollow(FOLLOW_aCase_in_casesGroup5071);
                    aCase194=aCase();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_aCase.add(aCase194.getTree());
                    }
                    break;
        
                default :
                    if ( cnt94 >= 1 ) break loop94;
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    EarlyExitException eee = new EarlyExitException(94, input);
                    throw eee;
                }
                cnt94++;
            }
        
            pushFollow(FOLLOW_caseSList_in_casesGroup5075);
            caseSList195=caseSList();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_caseSList.add(caseSList195.getTree());
            // AST REWRITE
            // elements: caseSList, aCase
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1093:5: -> ^( CASE_GROUP ( aCase )+ caseSList )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1093:8: ^( CASE_GROUP ( aCase )+ caseSList )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(CASE_GROUP, "CASE_GROUP"), root_1);
                if ( !(stream_aCase.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_aCase.hasNext() ) {
                    adaptor.addChild(root_1, stream_aCase.nextTree());
                }
                stream_aCase.reset();
        
                adaptor.addChild(root_1, stream_caseSList.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 65, casesGroup_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "aCase"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1096:1: aCase : ( 'case' ^ expression | DEFAULT ) COLON !;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.aCase_return aCase() throws RecognitionException {
        JavaParser.aCase_return retval = new JavaParser.aCase_return();
        retval.start = input.LT(1);
        int aCase_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal196=null;
        Token DEFAULT198=null;
        Token COLON199=null;
        ParserRuleReturnScope expression197 =null;
        
        ASTTree string_literal196_tree=null;
        ASTTree DEFAULT198_tree=null;
        ASTTree COLON199_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1097:3: ( ( 'case' ^ expression | DEFAULT ) COLON !)
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1098:3: ( 'case' ^ expression | DEFAULT ) COLON !
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1098:3: ( 'case' ^ expression | DEFAULT )
            int alt95=2;
            int LA95_0 = input.LA(1);
            if ( (LA95_0==138) ) {
                alt95=1;
            }
            else if ( (LA95_0==DEFAULT) ) {
                alt95=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);
                throw nvae;
            }
        
            switch (alt95) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1099:5: 'case' ^ expression
                    {
                    string_literal196=(Token)match(input,138,FOLLOW_138_in_aCase5112); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal196_tree = (ASTTree)adaptor.create(string_literal196);
                    root_0 = (ASTTree)adaptor.becomeRoot(string_literal196_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_expression_in_aCase5115);
                    expression197=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression197.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1100:7: DEFAULT
                    {
                    DEFAULT198=(Token)match(input,DEFAULT,FOLLOW_DEFAULT_in_aCase5123); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DEFAULT198_tree = (ASTTree)adaptor.create(DEFAULT198);
                    adaptor.addChild(root_0, DEFAULT198_tree);
                    }
        
                    }
                    break;
        
            }
        
            COLON199=(Token)match(input,COLON,FOLLOW_COLON_in_aCase5131); if (state.failed) return retval;
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 66, aCase_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "caseSList"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1105:1: caseSList : ( statement )* -> ^( SLIST ( statement )* ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.caseSList_return caseSList() throws RecognitionException {
        JavaParser.caseSList_return retval = new JavaParser.caseSList_return();
        retval.start = input.LT(1);
        int caseSList_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope statement200 =null;
        
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1106:3: ( ( statement )* -> ^( SLIST ( statement )* ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1107:3: ( statement )*
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1107:3: ( statement )*
            loop96:
            while (true) {
                int alt96=2;
                int LA96_0 = input.LA(1);
                if ( (LA96_0==DEFAULT) ) {
                    int LA96_2 = input.LA(2);
                    if ( (LA96_2==AT||LA96_2==DEFAULT||LA96_2==IDENT||LA96_2==133||LA96_2==135||LA96_2==137||(LA96_2 >= 140 && LA96_2 <= 141)||LA96_2==144||LA96_2==149||LA96_2==151||LA96_2==157||(LA96_2 >= 159 && LA96_2 <= 160)||(LA96_2 >= 163 && LA96_2 <= 168)||LA96_2==170||LA96_2==172||LA96_2==175||LA96_2==179) ) {
                        alt96=1;
                    }
        
                }
                else if ( (LA96_0==AT||LA96_0==BNOT||LA96_0==CHAR_LITERAL||LA96_0==DEC||LA96_0==IDENT||LA96_0==INC||LA96_0==LCURLY||LA96_0==LNOT||LA96_0==LPAREN||LA96_0==MINUS||LA96_0==NEW||LA96_0==NUM_LITERAL||LA96_0==PLUS||LA96_0==RETURN||LA96_0==SEMI||(LA96_0 >= STRING_LITERAL && LA96_0 <= SUPER)||(LA96_0 >= 133 && LA96_0 <= 137)||(LA96_0 >= 140 && LA96_0 <= 144)||(LA96_0 >= 148 && LA96_0 <= 149)||(LA96_0 >= 151 && LA96_0 <= 153)||LA96_0==157||(LA96_0 >= 159 && LA96_0 <= 161)||(LA96_0 >= 163 && LA96_0 <= 173)||(LA96_0 >= 175 && LA96_0 <= 180)) ) {
                    alt96=1;
                }
        
                switch (alt96) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1107:3: statement
                    {
                    pushFollow(FOLLOW_statement_in_caseSList5147);
                    statement200=statement();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_statement.add(statement200.getTree());
                    }
                    break;
        
                default :
                    break loop96;
                }
            }
        
            // AST REWRITE
            // elements: statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1108:5: -> ^( SLIST ( statement )* )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1108:8: ^( SLIST ( statement )* )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(SLIST, "SLIST"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1108:16: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());
                }
                stream_statement.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 67, caseSList_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "forInit"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1113:1: forInit : ( ( declaration )=> declaration | expressionList )? -> ^( FOR_INIT ( declaration )? ( expressionList )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.forInit_return forInit() throws RecognitionException {
        JavaParser.forInit_return retval = new JavaParser.forInit_return();
        retval.start = input.LT(1);
        int forInit_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope declaration201 =null;
        ParserRuleReturnScope expressionList202 =null;
        
        RewriteRuleSubtreeStream stream_declaration=new RewriteRuleSubtreeStream(adaptor,"rule declaration");
        RewriteRuleSubtreeStream stream_expressionList=new RewriteRuleSubtreeStream(adaptor,"rule expressionList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1115:3: ( ( ( declaration )=> declaration | expressionList )? -> ^( FOR_INIT ( declaration )? ( expressionList )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1116:3: ( ( declaration )=> declaration | expressionList )?
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1116:3: ( ( declaration )=> declaration | expressionList )?
            int alt97=3;
            int LA97_0 = input.LA(1);
            if ( (LA97_0==AT) ) {
                int LA97_1 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==DEFAULT||LA97_0==133||LA97_0==149||LA97_0==160||(LA97_0 >= 163 && LA97_0 <= 165)||(LA97_0 >= 167 && LA97_0 <= 168)||LA97_0==170||LA97_0==172||LA97_0==175||LA97_0==179) && (synpred149_Java())) {
                alt97=1;
            }
            else if ( (LA97_0==IDENT) ) {
                int LA97_3 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==135) ) {
                int LA97_4 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==137) ) {
                int LA97_5 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==140) ) {
                int LA97_6 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==166) ) {
                int LA97_7 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==157) ) {
                int LA97_8 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==151) ) {
                int LA97_9 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==159) ) {
                int LA97_10 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==144) ) {
                int LA97_11 = input.LA(2);
                if ( (synpred149_Java()) ) {
                    alt97=1;
                }
                else if ( (synpred150_Java()) ) {
                    alt97=2;
                }
            }
            else if ( (LA97_0==BNOT||LA97_0==CHAR_LITERAL||LA97_0==DEC||LA97_0==INC||LA97_0==LNOT||LA97_0==LPAREN||LA97_0==MINUS||LA97_0==NEW||LA97_0==NUM_LITERAL||LA97_0==PLUS||(LA97_0 >= STRING_LITERAL && LA97_0 <= SUPER)||LA97_0==148||LA97_0==161||LA97_0==171||LA97_0==176||LA97_0==178) ) {
                alt97=2;
            }
            switch (alt97) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1117:5: ( declaration )=> declaration
                    {
                    pushFollow(FOLLOW_declaration_in_forInit5194);
                    declaration201=declaration();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_declaration.add(declaration201.getTree());
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1119:7: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_forInit5207);
                    expressionList202=expressionList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expressionList.add(expressionList202.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: declaration, expressionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1121:5: -> ^( FOR_INIT ( declaration )? ( expressionList )? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1121:8: ^( FOR_INIT ( declaration )? ( expressionList )? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(FOR_INIT, "FOR_INIT"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1121:19: ( declaration )?
                if ( stream_declaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_declaration.nextTree());
                }
                stream_declaration.reset();
        
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1121:32: ( expressionList )?
                if ( stream_expressionList.hasNext() ) {
                    adaptor.addChild(root_1, stream_expressionList.nextTree());
                }
                stream_expressionList.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 68, forInit_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "forCond"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1124:1: forCond : ( expression )? -> ^( FOR_CONDITION ( expression )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.forCond_return forCond() throws RecognitionException {
        JavaParser.forCond_return retval = new JavaParser.forCond_return();
        retval.start = input.LT(1);
        int forCond_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope expression203 =null;
        
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1125:3: ( ( expression )? -> ^( FOR_CONDITION ( expression )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1126:3: ( expression )?
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1126:3: ( expression )?
            int alt98=2;
            int LA98_0 = input.LA(1);
            if ( (LA98_0==AT||LA98_0==BNOT||LA98_0==CHAR_LITERAL||LA98_0==DEC||LA98_0==IDENT||LA98_0==INC||LA98_0==LNOT||LA98_0==LPAREN||LA98_0==MINUS||LA98_0==NEW||LA98_0==NUM_LITERAL||LA98_0==PLUS||(LA98_0 >= STRING_LITERAL && LA98_0 <= SUPER)||LA98_0==135||LA98_0==137||LA98_0==140||LA98_0==144||LA98_0==148||LA98_0==151||LA98_0==157||LA98_0==159||LA98_0==161||LA98_0==166||LA98_0==171||LA98_0==176||LA98_0==178) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1126:3: expression
                    {
                    pushFollow(FOLLOW_expression_in_forCond5244);
                    expression203=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression203.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1127:5: -> ^( FOR_CONDITION ( expression )? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1127:8: ^( FOR_CONDITION ( expression )? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(FOR_CONDITION, "FOR_CONDITION"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1127:24: ( expression )?
                if ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());
                }
                stream_expression.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 69, forCond_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "forIter"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1130:1: forIter : ( expressionList )? -> ^( FOR_ITERATOR ( expressionList )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.forIter_return forIter() throws RecognitionException {
        JavaParser.forIter_return retval = new JavaParser.forIter_return();
        retval.start = input.LT(1);
        int forIter_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope expressionList204 =null;
        
        RewriteRuleSubtreeStream stream_expressionList=new RewriteRuleSubtreeStream(adaptor,"rule expressionList");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1131:3: ( ( expressionList )? -> ^( FOR_ITERATOR ( expressionList )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1132:3: ( expressionList )?
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1132:3: ( expressionList )?
            int alt99=2;
            int LA99_0 = input.LA(1);
            if ( (LA99_0==AT||LA99_0==BNOT||LA99_0==CHAR_LITERAL||LA99_0==DEC||LA99_0==IDENT||LA99_0==INC||LA99_0==LNOT||LA99_0==LPAREN||LA99_0==MINUS||LA99_0==NEW||LA99_0==NUM_LITERAL||LA99_0==PLUS||(LA99_0 >= STRING_LITERAL && LA99_0 <= SUPER)||LA99_0==135||LA99_0==137||LA99_0==140||LA99_0==144||LA99_0==148||LA99_0==151||LA99_0==157||LA99_0==159||LA99_0==161||LA99_0==166||LA99_0==171||LA99_0==176||LA99_0==178) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1132:3: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_forIter5274);
                    expressionList204=expressionList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expressionList.add(expressionList204.getTree());
                    }
                    break;
        
            }
        
            // AST REWRITE
            // elements: expressionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1133:5: -> ^( FOR_ITERATOR ( expressionList )? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1133:8: ^( FOR_ITERATOR ( expressionList )? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(FOR_ITERATOR, "FOR_ITERATOR"), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1133:23: ( expressionList )?
                if ( stream_expressionList.hasNext() ) {
                    adaptor.addChild(root_1, stream_expressionList.nextTree());
                }
                stream_expressionList.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 70, forIter_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "tryBlock"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1138:1: tryBlock : 'try' ^ ( resourceDeclarations )? compoundStatement[false] ( handler )* ( finallyClause )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.tryBlock_return tryBlock() throws RecognitionException {
        JavaParser.tryBlock_return retval = new JavaParser.tryBlock_return();
        retval.start = input.LT(1);
        int tryBlock_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal205=null;
        ParserRuleReturnScope resourceDeclarations206 =null;
        ParserRuleReturnScope compoundStatement207 =null;
        ParserRuleReturnScope handler208 =null;
        ParserRuleReturnScope finallyClause209 =null;
        
        ASTTree string_literal205_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1139:3: ( 'try' ^ ( resourceDeclarations )? compoundStatement[false] ( handler )* ( finallyClause )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:3: 'try' ^ ( resourceDeclarations )? compoundStatement[false] ( handler )* ( finallyClause )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            string_literal205=(Token)match(input,177,FOLLOW_177_in_tryBlock5306); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal205_tree = (ASTTree)adaptor.create(string_literal205);
            root_0 = (ASTTree)adaptor.becomeRoot(string_literal205_tree, root_0);
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:10: ( resourceDeclarations )?
            int alt100=2;
            int LA100_0 = input.LA(1);
            if ( (LA100_0==LPAREN) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:10: resourceDeclarations
                    {
                    pushFollow(FOLLOW_resourceDeclarations_in_tryBlock5309);
                    resourceDeclarations206=resourceDeclarations();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, resourceDeclarations206.getTree());
        
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_compoundStatement_in_tryBlock5312);
            compoundStatement207=compoundStatement(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, compoundStatement207.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:57: ( handler )*
            loop101:
            while (true) {
                int alt101=2;
                int LA101_0 = input.LA(1);
                if ( (LA101_0==139) ) {
                    alt101=1;
                }
        
                switch (alt101) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:57: handler
                    {
                    pushFollow(FOLLOW_handler_in_tryBlock5315);
                    handler208=handler();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, handler208.getTree());
        
                    }
                    break;
        
                default :
                    break loop101;
                }
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:66: ( finallyClause )?
            int alt102=2;
            int LA102_0 = input.LA(1);
            if ( (LA102_0==150) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1140:66: finallyClause
                    {
                    pushFollow(FOLLOW_finallyClause_in_tryBlock5318);
                    finallyClause209=finallyClause();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause209.getTree());
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 71, tryBlock_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "resourceDeclarations"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1144:1: resourceDeclarations : LPAREN ( declaration )? ( SEMI ( declaration )? )* RPAREN ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.resourceDeclarations_return resourceDeclarations() throws RecognitionException {
        JavaParser.resourceDeclarations_return retval = new JavaParser.resourceDeclarations_return();
        retval.start = input.LT(1);
        int resourceDeclarations_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token LPAREN210=null;
        Token SEMI212=null;
        Token RPAREN214=null;
        ParserRuleReturnScope declaration211 =null;
        ParserRuleReturnScope declaration213 =null;
        
        ASTTree LPAREN210_tree=null;
        ASTTree SEMI212_tree=null;
        ASTTree RPAREN214_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1145:3: ( LPAREN ( declaration )? ( SEMI ( declaration )? )* RPAREN )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:3: LPAREN ( declaration )? ( SEMI ( declaration )? )* RPAREN
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            LPAREN210=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_resourceDeclarations5335); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LPAREN210_tree = (ASTTree)adaptor.create(LPAREN210);
            adaptor.addChild(root_0, LPAREN210_tree);
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:10: ( declaration )?
            int alt103=2;
            int LA103_0 = input.LA(1);
            if ( (LA103_0==AT||LA103_0==DEFAULT||LA103_0==IDENT||LA103_0==133||LA103_0==135||LA103_0==137||LA103_0==140||LA103_0==144||LA103_0==149||LA103_0==151||LA103_0==157||(LA103_0 >= 159 && LA103_0 <= 160)||(LA103_0 >= 163 && LA103_0 <= 168)||LA103_0==170||LA103_0==172||LA103_0==175||LA103_0==179) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:10: declaration
                    {
                    pushFollow(FOLLOW_declaration_in_resourceDeclarations5337);
                    declaration211=declaration();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, declaration211.getTree());
        
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:23: ( SEMI ( declaration )? )*
            loop105:
            while (true) {
                int alt105=2;
                int LA105_0 = input.LA(1);
                if ( (LA105_0==SEMI) ) {
                    alt105=1;
                }
        
                switch (alt105) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:24: SEMI ( declaration )?
                    {
                    SEMI212=(Token)match(input,SEMI,FOLLOW_SEMI_in_resourceDeclarations5341); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI212_tree = (ASTTree)adaptor.create(SEMI212);
                    adaptor.addChild(root_0, SEMI212_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:29: ( declaration )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);
                    if ( (LA104_0==AT||LA104_0==DEFAULT||LA104_0==IDENT||LA104_0==133||LA104_0==135||LA104_0==137||LA104_0==140||LA104_0==144||LA104_0==149||LA104_0==151||LA104_0==157||(LA104_0 >= 159 && LA104_0 <= 160)||(LA104_0 >= 163 && LA104_0 <= 168)||LA104_0==170||LA104_0==172||LA104_0==175||LA104_0==179) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1146:29: declaration
                            {
                            pushFollow(FOLLOW_declaration_in_resourceDeclarations5343);
                            declaration213=declaration();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, declaration213.getTree());
        
                            }
                            break;
        
                    }
        
                    }
                    break;
        
                default :
                    break loop105;
                }
            }
        
            RPAREN214=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_resourceDeclarations5348); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RPAREN214_tree = (ASTTree)adaptor.create(RPAREN214);
            adaptor.addChild(root_0, RPAREN214_tree);
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 72, resourceDeclarations_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "finallyClause"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1149:1: finallyClause : 'finally' ^ compoundStatement[false] ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.finallyClause_return finallyClause() throws RecognitionException {
        JavaParser.finallyClause_return retval = new JavaParser.finallyClause_return();
        retval.start = input.LT(1);
        int finallyClause_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal215=null;
        ParserRuleReturnScope compoundStatement216 =null;
        
        ASTTree string_literal215_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1150:3: ( 'finally' ^ compoundStatement[false] )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1151:3: 'finally' ^ compoundStatement[false]
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            string_literal215=(Token)match(input,150,FOLLOW_150_in_finallyClause5363); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal215_tree = (ASTTree)adaptor.create(string_literal215);
            root_0 = (ASTTree)adaptor.becomeRoot(string_literal215_tree, root_0);
            }
        
            pushFollow(FOLLOW_compoundStatement_in_finallyClause5366);
            compoundStatement216=compoundStatement(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, compoundStatement216.getTree());
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 73, finallyClause_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "handler"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1156:1: handler : 'catch' ^ LPAREN ! caughtExceptionDeclarations IDENT RPAREN ! compoundStatement[false] ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.handler_return handler() throws RecognitionException {
        JavaParser.handler_return retval = new JavaParser.handler_return();
        retval.start = input.LT(1);
        int handler_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal217=null;
        Token LPAREN218=null;
        Token IDENT220=null;
        Token RPAREN221=null;
        ParserRuleReturnScope caughtExceptionDeclarations219 =null;
        ParserRuleReturnScope compoundStatement222 =null;
        
        ASTTree string_literal217_tree=null;
        ASTTree LPAREN218_tree=null;
        ASTTree IDENT220_tree=null;
        ASTTree RPAREN221_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1157:3: ( 'catch' ^ LPAREN ! caughtExceptionDeclarations IDENT RPAREN ! compoundStatement[false] )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1158:3: 'catch' ^ LPAREN ! caughtExceptionDeclarations IDENT RPAREN ! compoundStatement[false]
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            string_literal217=(Token)match(input,139,FOLLOW_139_in_handler5384); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal217_tree = (ASTTree)adaptor.create(string_literal217);
            root_0 = (ASTTree)adaptor.becomeRoot(string_literal217_tree, root_0);
            }
        
            LPAREN218=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_handler5387); if (state.failed) return retval;
            pushFollow(FOLLOW_caughtExceptionDeclarations_in_handler5390);
            caughtExceptionDeclarations219=caughtExceptionDeclarations();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, caughtExceptionDeclarations219.getTree());
        
            IDENT220=(Token)match(input,IDENT,FOLLOW_IDENT_in_handler5392); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENT220_tree = (ASTTree)adaptor.create(IDENT220);
            adaptor.addChild(root_0, IDENT220_tree);
            }
        
            RPAREN221=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_handler5394); if (state.failed) return retval;
            pushFollow(FOLLOW_compoundStatement_in_handler5397);
            compoundStatement222=compoundStatement(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, compoundStatement222.getTree());
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 74, handler_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "caughtExceptionDeclarations"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1161:1: caughtExceptionDeclarations : caughtExceptionDeclaration ( BOR ! caughtExceptionDeclaration )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.caughtExceptionDeclarations_return caughtExceptionDeclarations() throws RecognitionException {
        JavaParser.caughtExceptionDeclarations_return retval = new JavaParser.caughtExceptionDeclarations_return();
        retval.start = input.LT(1);
        int caughtExceptionDeclarations_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token BOR224=null;
        ParserRuleReturnScope caughtExceptionDeclaration223 =null;
        ParserRuleReturnScope caughtExceptionDeclaration225 =null;
        
        ASTTree BOR224_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1162:3: ( caughtExceptionDeclaration ( BOR ! caughtExceptionDeclaration )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1163:3: caughtExceptionDeclaration ( BOR ! caughtExceptionDeclaration )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_caughtExceptionDeclaration_in_caughtExceptionDeclarations5413);
            caughtExceptionDeclaration223=caughtExceptionDeclaration();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, caughtExceptionDeclaration223.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1163:30: ( BOR ! caughtExceptionDeclaration )*
            loop106:
            while (true) {
                int alt106=2;
                int LA106_0 = input.LA(1);
                if ( (LA106_0==BOR) ) {
                    alt106=1;
                }
        
                switch (alt106) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1163:31: BOR ! caughtExceptionDeclaration
                    {
                    BOR224=(Token)match(input,BOR,FOLLOW_BOR_in_caughtExceptionDeclarations5416); if (state.failed) return retval;
                    pushFollow(FOLLOW_caughtExceptionDeclaration_in_caughtExceptionDeclarations5419);
                    caughtExceptionDeclaration225=caughtExceptionDeclaration();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, caughtExceptionDeclaration225.getTree());
        
                    }
                    break;
        
                default :
                    break loop106;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 75, caughtExceptionDeclarations_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "caughtExceptionDeclaration"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1166:1: caughtExceptionDeclaration : ( annotation )* ( parameterModifier )? typeSpec ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.caughtExceptionDeclaration_return caughtExceptionDeclaration() throws RecognitionException {
        JavaParser.caughtExceptionDeclaration_return retval = new JavaParser.caughtExceptionDeclaration_return();
        retval.start = input.LT(1);
        int caughtExceptionDeclaration_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope annotation226 =null;
        ParserRuleReturnScope parameterModifier227 =null;
        ParserRuleReturnScope typeSpec228 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1167:3: ( ( annotation )* ( parameterModifier )? typeSpec )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:3: ( annotation )* ( parameterModifier )? typeSpec
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:3: ( annotation )*
            loop107:
            while (true) {
                int alt107=2;
                int LA107_0 = input.LA(1);
                if ( (LA107_0==AT) ) {
                    int LA107_2 = input.LA(2);
                    if ( (LA107_2==IDENT) ) {
                        int LA107_3 = input.LA(3);
                        if ( (synpred160_Java()) ) {
                            alt107=1;
                        }
        
                    }
        
                }
        
                switch (alt107) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:3: annotation
                    {
                    pushFollow(FOLLOW_annotation_in_caughtExceptionDeclaration5437);
                    annotation226=annotation();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation226.getTree());
        
                    }
                    break;
        
                default :
                    break loop107;
                }
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:15: ( parameterModifier )?
            int alt108=2;
            int LA108_0 = input.LA(1);
            if ( (LA108_0==149) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:15: parameterModifier
                    {
                    pushFollow(FOLLOW_parameterModifier_in_caughtExceptionDeclaration5440);
                    parameterModifier227=parameterModifier();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameterModifier227.getTree());
        
                    }
                    break;
        
            }
        
            pushFollow(FOLLOW_typeSpec_in_caughtExceptionDeclaration5443);
            typeSpec228=typeSpec();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSpec228.getTree());
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 76, caughtExceptionDeclaration_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "returnExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1171:1: returnExpression : ret= 'return' ( expression )? ( SEMI )+ -> ^( RETURN[$ret] ( expression )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.returnExpression_return returnExpression() throws RecognitionException {
        JavaParser.returnExpression_return retval = new JavaParser.returnExpression_return();
        retval.start = input.LT(1);
        int returnExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token ret=null;
        Token SEMI230=null;
        ParserRuleReturnScope expression229 =null;
        
        ASTTree ret_tree=null;
        ASTTree SEMI230_tree=null;
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleTokenStream stream_RETURN=new RewriteRuleTokenStream(adaptor,"token RETURN");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1177:3: (ret= 'return' ( expression )? ( SEMI )+ -> ^( RETURN[$ret] ( expression )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:3: ret= 'return' ( expression )? ( SEMI )+
            {
            ret=(Token)match(input,RETURN,FOLLOW_RETURN_in_returnExpression5467); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RETURN.add(ret);
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:16: ( expression )?
            int alt109=2;
            int LA109_0 = input.LA(1);
            if ( (LA109_0==AT||LA109_0==BNOT||LA109_0==CHAR_LITERAL||LA109_0==DEC||LA109_0==IDENT||LA109_0==INC||LA109_0==LNOT||LA109_0==LPAREN||LA109_0==MINUS||LA109_0==NEW||LA109_0==NUM_LITERAL||LA109_0==PLUS||(LA109_0 >= STRING_LITERAL && LA109_0 <= SUPER)||LA109_0==135||LA109_0==137||LA109_0==140||LA109_0==144||LA109_0==148||LA109_0==151||LA109_0==157||LA109_0==159||LA109_0==161||LA109_0==166||LA109_0==171||LA109_0==176||LA109_0==178) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:16: expression
                    {
                    pushFollow(FOLLOW_expression_in_returnExpression5469);
                    expression229=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression229.getTree());
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:28: ( SEMI )+
            int cnt110=0;
            loop110:
            while (true) {
                int alt110=2;
                int LA110_0 = input.LA(1);
                if ( (LA110_0==SEMI) ) {
                    int LA110_2 = input.LA(2);
                    if ( (synpred163_Java()) ) {
                        alt110=1;
                    }
        
                }
        
                switch (alt110) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:28: SEMI
                    {
                    SEMI230=(Token)match(input,SEMI,FOLLOW_SEMI_in_returnExpression5472); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI230);
        
                    }
                    break;
        
                default :
                    if ( cnt110 >= 1 ) break loop110;
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    EarlyExitException eee = new EarlyExitException(110, input);
                    throw eee;
                }
                cnt110++;
            }
        
            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1178:34: -> ^( RETURN[$ret] ( expression )? )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:37: ^( RETURN[$ret] ( expression )? )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(RETURN, ret), root_1);
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:52: ( expression )?
                if ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());
                }
                stream_expression.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
              attachTrailingComments(retval.tree, (retval.stop));
              attachUnderneathComments(retval.tree, (retval.stop));
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 77, returnExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "expressionList"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1183:1: expressionList : expression ( COMMA expression )* -> ^( ELIST ( expression )+ ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.expressionList_return expressionList() throws RecognitionException {
        JavaParser.expressionList_return retval = new JavaParser.expressionList_return();
        retval.start = input.LT(1);
        int expressionList_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token COMMA232=null;
        ParserRuleReturnScope expression231 =null;
        ParserRuleReturnScope expression233 =null;
        
        ASTTree COMMA232_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1184:3: ( expression ( COMMA expression )* -> ^( ELIST ( expression )+ ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1185:3: expression ( COMMA expression )*
            {
            pushFollow(FOLLOW_expression_in_expressionList5501);
            expression231=expression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression231.getTree());
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1185:14: ( COMMA expression )*
            loop111:
            while (true) {
                int alt111=2;
                int LA111_0 = input.LA(1);
                if ( (LA111_0==COMMA) ) {
                    alt111=1;
                }
        
                switch (alt111) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1185:15: COMMA expression
                    {
                    COMMA232=(Token)match(input,COMMA,FOLLOW_COMMA_in_expressionList5504); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA232);
        
                    pushFollow(FOLLOW_expression_in_expressionList5506);
                    expression233=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression233.getTree());
                    }
                    break;
        
                default :
                    break loop111;
                }
            }
        
            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1186:5: -> ^( ELIST ( expression )+ )
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1186:8: ^( ELIST ( expression )+ )
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ELIST, "ELIST"), root_1);
                if ( !(stream_expression.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression.nextTree());
                }
                stream_expression.reset();
        
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 78, expressionList_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "expression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1223:1: expression : as= assignmentExpression -> ^( EXPR $as) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.expression_return expression() throws RecognitionException {
        JavaParser.expression_return retval = new JavaParser.expression_return();
        retval.start = input.LT(1);
        int expression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope as =null;
        
        RewriteRuleSubtreeStream stream_assignmentExpression=new RewriteRuleSubtreeStream(adaptor,"rule assignmentExpression");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1224:3: (as= assignmentExpression -> ^( EXPR $as) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1225:3: as= assignmentExpression
            {
            pushFollow(FOLLOW_assignmentExpression_in_expression5573);
            as=assignmentExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_assignmentExpression.add(as.getTree());
            // AST REWRITE
            // elements: as
            // token labels: 
            // rule labels: retval, as
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
            RewriteRuleSubtreeStream stream_as=new RewriteRuleSubtreeStream(adaptor,"rule as",as!=null?as.getTree():null);
        
            root_0 = (ASTTree)adaptor.nil();
            // 1226:5: -> ^( EXPR $as)
            {
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1226:8: ^( EXPR $as)
                {
                ASTTree root_1 = (ASTTree)adaptor.nil();
                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(EXPR, "EXPR"), root_1);
                adaptor.addChild(root_1, stream_as.nextTree());
                adaptor.addChild(root_0, root_1);
                }
        
            }
        
        
            retval.tree = root_0;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 79, expression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "assignmentExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1231:1: assignmentExpression : conditionalExpression[false] ( assignmentOperator assignmentExpression )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.assignmentExpression_return assignmentExpression() throws RecognitionException {
        JavaParser.assignmentExpression_return retval = new JavaParser.assignmentExpression_return();
        retval.start = input.LT(1);
        int assignmentExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope conditionalExpression234 =null;
        ParserRuleReturnScope assignmentOperator235 =null;
        ParserRuleReturnScope assignmentExpression236 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 80) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1232:3: ( conditionalExpression[false] ( assignmentOperator assignmentExpression )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1233:3: conditionalExpression[false] ( assignmentOperator assignmentExpression )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_conditionalExpression_in_assignmentExpression5604);
            conditionalExpression234=conditionalExpression(false);
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression234.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1233:32: ( assignmentOperator assignmentExpression )?
            int alt112=2;
            switch ( input.LA(1) ) {
                case ASSIGN:
                    {
                    int LA112_1 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case PLUS_ASSIGN:
                    {
                    int LA112_2 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case MINUS_ASSIGN:
                    {
                    int LA112_3 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case STAR_ASSIGN:
                    {
                    int LA112_4 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case DIV_ASSIGN:
                    {
                    int LA112_5 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case MOD_ASSIGN:
                    {
                    int LA112_6 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case GT:
                    {
                    int LA112_7 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case LT:
                    {
                    int LA112_8 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case BAND_ASSIGN:
                    {
                    int LA112_9 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case BXOR_ASSIGN:
                    {
                    int LA112_10 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
                case BOR_ASSIGN:
                    {
                    int LA112_11 = input.LA(2);
                    if ( (synpred165_Java()) ) {
                        alt112=1;
                    }
                    }
                    break;
            }
            switch (alt112) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1233:33: assignmentOperator assignmentExpression
                    {
                    pushFollow(FOLLOW_assignmentOperator_in_assignmentExpression5608);
                    assignmentOperator235=assignmentOperator();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentOperator235.getTree());
        
                    pushFollow(FOLLOW_assignmentExpression_in_assignmentExpression5610);
                    assignmentExpression236=assignmentExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression236.getTree());
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 80, assignmentExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "assignmentOperator"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1236:1: assignmentOperator : ( ASSIGN -> ASSIGN | PLUS_ASSIGN -> PLUS_ASSIGN | MINUS_ASSIGN -> MINUS_ASSIGN | STAR_ASSIGN -> STAR_ASSIGN | DIV_ASSIGN -> DIV_ASSIGN | MOD_ASSIGN -> MOD_ASSIGN | GT GT '=' -> SR_ASSIGN | GT GT GT '=' -> BSR_ASSIGN | LT LT '=' -> SL_ASSIGN | BAND_ASSIGN -> BAND_ASSIGN | BXOR_ASSIGN -> BXOR_ASSIGN | BOR_ASSIGN -> BOR_ASSIGN );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.assignmentOperator_return assignmentOperator() throws RecognitionException {
        JavaParser.assignmentOperator_return retval = new JavaParser.assignmentOperator_return();
        retval.start = input.LT(1);
        int assignmentOperator_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token ASSIGN237=null;
        Token PLUS_ASSIGN238=null;
        Token MINUS_ASSIGN239=null;
        Token STAR_ASSIGN240=null;
        Token DIV_ASSIGN241=null;
        Token MOD_ASSIGN242=null;
        Token GT243=null;
        Token GT244=null;
        Token char_literal245=null;
        Token GT246=null;
        Token GT247=null;
        Token GT248=null;
        Token char_literal249=null;
        Token LT250=null;
        Token LT251=null;
        Token char_literal252=null;
        Token BAND_ASSIGN253=null;
        Token BXOR_ASSIGN254=null;
        Token BOR_ASSIGN255=null;
        
        ASTTree ASSIGN237_tree=null;
        ASTTree PLUS_ASSIGN238_tree=null;
        ASTTree MINUS_ASSIGN239_tree=null;
        ASTTree STAR_ASSIGN240_tree=null;
        ASTTree DIV_ASSIGN241_tree=null;
        ASTTree MOD_ASSIGN242_tree=null;
        ASTTree GT243_tree=null;
        ASTTree GT244_tree=null;
        ASTTree char_literal245_tree=null;
        ASTTree GT246_tree=null;
        ASTTree GT247_tree=null;
        ASTTree GT248_tree=null;
        ASTTree char_literal249_tree=null;
        ASTTree LT250_tree=null;
        ASTTree LT251_tree=null;
        ASTTree char_literal252_tree=null;
        ASTTree BAND_ASSIGN253_tree=null;
        ASTTree BXOR_ASSIGN254_tree=null;
        ASTTree BOR_ASSIGN255_tree=null;
        RewriteRuleTokenStream stream_GT=new RewriteRuleTokenStream(adaptor,"token GT");
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_BAND_ASSIGN=new RewriteRuleTokenStream(adaptor,"token BAND_ASSIGN");
        RewriteRuleTokenStream stream_BOR_ASSIGN=new RewriteRuleTokenStream(adaptor,"token BOR_ASSIGN");
        RewriteRuleTokenStream stream_BXOR_ASSIGN=new RewriteRuleTokenStream(adaptor,"token BXOR_ASSIGN");
        RewriteRuleTokenStream stream_DIV_ASSIGN=new RewriteRuleTokenStream(adaptor,"token DIV_ASSIGN");
        RewriteRuleTokenStream stream_STAR_ASSIGN=new RewriteRuleTokenStream(adaptor,"token STAR_ASSIGN");
        RewriteRuleTokenStream stream_MOD_ASSIGN=new RewriteRuleTokenStream(adaptor,"token MOD_ASSIGN");
        RewriteRuleTokenStream stream_MINUS_ASSIGN=new RewriteRuleTokenStream(adaptor,"token MINUS_ASSIGN");
        RewriteRuleTokenStream stream_PLUS_ASSIGN=new RewriteRuleTokenStream(adaptor,"token PLUS_ASSIGN");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 81) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1237:3: ( ASSIGN -> ASSIGN | PLUS_ASSIGN -> PLUS_ASSIGN | MINUS_ASSIGN -> MINUS_ASSIGN | STAR_ASSIGN -> STAR_ASSIGN | DIV_ASSIGN -> DIV_ASSIGN | MOD_ASSIGN -> MOD_ASSIGN | GT GT '=' -> SR_ASSIGN | GT GT GT '=' -> BSR_ASSIGN | LT LT '=' -> SL_ASSIGN | BAND_ASSIGN -> BAND_ASSIGN | BXOR_ASSIGN -> BXOR_ASSIGN | BOR_ASSIGN -> BOR_ASSIGN )
            int alt113=12;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt113=1;
                }
                break;
            case PLUS_ASSIGN:
                {
                alt113=2;
                }
                break;
            case MINUS_ASSIGN:
                {
                alt113=3;
                }
                break;
            case STAR_ASSIGN:
                {
                alt113=4;
                }
                break;
            case DIV_ASSIGN:
                {
                alt113=5;
                }
                break;
            case MOD_ASSIGN:
                {
                alt113=6;
                }
                break;
            case GT:
                {
                int LA113_7 = input.LA(2);
                if ( (LA113_7==GT) ) {
                    int LA113_12 = input.LA(3);
                    if ( (LA113_12==ASSIGN) ) {
                        alt113=7;
                    }
                    else if ( (LA113_12==GT) ) {
                        alt113=8;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        int nvaeMark = input.mark();
                        try {
                            for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                input.consume();
                            }
                            NoViableAltException nvae =
                                new NoViableAltException("", 113, 12, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                    }
        
                }
        
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 113, 7, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
                }
                break;
            case LT:
                {
                alt113=9;
                }
                break;
            case BAND_ASSIGN:
                {
                alt113=10;
                }
                break;
            case BXOR_ASSIGN:
                {
                alt113=11;
                }
                break;
            case BOR_ASSIGN:
                {
                alt113=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 113, 0, input);
                throw nvae;
            }
            switch (alt113) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1238:3: ASSIGN
                    {
                    ASSIGN237=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignmentOperator5627); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(ASSIGN237);
        
                    // AST REWRITE
                    // elements: ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1239:5: -> ASSIGN
                    {
                        adaptor.addChild(root_0, stream_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1240:5: PLUS_ASSIGN
                    {
                    PLUS_ASSIGN238=(Token)match(input,PLUS_ASSIGN,FOLLOW_PLUS_ASSIGN_in_assignmentOperator5641); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_PLUS_ASSIGN.add(PLUS_ASSIGN238);
        
                    // AST REWRITE
                    // elements: PLUS_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1241:5: -> PLUS_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_PLUS_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1242:5: MINUS_ASSIGN
                    {
                    MINUS_ASSIGN239=(Token)match(input,MINUS_ASSIGN,FOLLOW_MINUS_ASSIGN_in_assignmentOperator5655); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MINUS_ASSIGN.add(MINUS_ASSIGN239);
        
                    // AST REWRITE
                    // elements: MINUS_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1243:5: -> MINUS_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_MINUS_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1244:5: STAR_ASSIGN
                    {
                    STAR_ASSIGN240=(Token)match(input,STAR_ASSIGN,FOLLOW_STAR_ASSIGN_in_assignmentOperator5669); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STAR_ASSIGN.add(STAR_ASSIGN240);
        
                    // AST REWRITE
                    // elements: STAR_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1245:5: -> STAR_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_STAR_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1246:5: DIV_ASSIGN
                    {
                    DIV_ASSIGN241=(Token)match(input,DIV_ASSIGN,FOLLOW_DIV_ASSIGN_in_assignmentOperator5683); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DIV_ASSIGN.add(DIV_ASSIGN241);
        
                    // AST REWRITE
                    // elements: DIV_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1247:5: -> DIV_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_DIV_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1248:5: MOD_ASSIGN
                    {
                    MOD_ASSIGN242=(Token)match(input,MOD_ASSIGN,FOLLOW_MOD_ASSIGN_in_assignmentOperator5697); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_MOD_ASSIGN.add(MOD_ASSIGN242);
        
                    // AST REWRITE
                    // elements: MOD_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1249:5: -> MOD_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_MOD_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 7 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1250:5: GT GT '='
                    {
                    GT243=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5711); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT243);
        
                    GT244=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5713); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT244);
        
                    char_literal245=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignmentOperator5715); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal245);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1251:5: -> SR_ASSIGN
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(SR_ASSIGN, "SR_ASSIGN"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 8 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1252:5: GT GT GT '='
                    {
                    GT246=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5729); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT246);
        
                    GT247=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5731); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT247);
        
                    GT248=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator5733); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT248);
        
                    char_literal249=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignmentOperator5735); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal249);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1253:5: -> BSR_ASSIGN
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(BSR_ASSIGN, "BSR_ASSIGN"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 9 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1254:5: LT LT '='
                    {
                    LT250=(Token)match(input,LT,FOLLOW_LT_in_assignmentOperator5749); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LT.add(LT250);
        
                    LT251=(Token)match(input,LT,FOLLOW_LT_in_assignmentOperator5751); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LT.add(LT251);
        
                    char_literal252=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignmentOperator5753); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal252);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1255:5: -> SL_ASSIGN
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(SL_ASSIGN, "SL_ASSIGN"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 10 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1256:5: BAND_ASSIGN
                    {
                    BAND_ASSIGN253=(Token)match(input,BAND_ASSIGN,FOLLOW_BAND_ASSIGN_in_assignmentOperator5767); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BAND_ASSIGN.add(BAND_ASSIGN253);
        
                    // AST REWRITE
                    // elements: BAND_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1257:5: -> BAND_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_BAND_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 11 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1258:5: BXOR_ASSIGN
                    {
                    BXOR_ASSIGN254=(Token)match(input,BXOR_ASSIGN,FOLLOW_BXOR_ASSIGN_in_assignmentOperator5781); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BXOR_ASSIGN.add(BXOR_ASSIGN254);
        
                    // AST REWRITE
                    // elements: BXOR_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1259:5: -> BXOR_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_BXOR_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 12 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1260:5: BOR_ASSIGN
                    {
                    BOR_ASSIGN255=(Token)match(input,BOR_ASSIGN,FOLLOW_BOR_ASSIGN_in_assignmentOperator5795); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BOR_ASSIGN.add(BOR_ASSIGN255);
        
                    // AST REWRITE
                    // elements: BOR_ASSIGN
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1261:5: -> BOR_ASSIGN
                    {
                        adaptor.addChild(root_0, stream_BOR_ASSIGN.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 81, assignmentOperator_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "conditionalExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1266:1: conditionalExpression[boolean storeSrcCode] : logicalOrExpression ( QUESTION ^ assignmentExpression COLON ! conditionalExpression[false] )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.conditionalExpression_return conditionalExpression(final boolean storeSrcCode) throws RecognitionException {
        JavaParser.conditionalExpression_return retval = new JavaParser.conditionalExpression_return();
        retval.start = input.LT(1);
        int conditionalExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token QUESTION257=null;
        Token COLON259=null;
        ParserRuleReturnScope logicalOrExpression256 =null;
        ParserRuleReturnScope assignmentExpression258 =null;
        ParserRuleReturnScope conditionalExpression260 =null;
        
        ASTTree QUESTION257_tree=null;
        ASTTree COLON259_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 82) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1272:3: ( logicalOrExpression ( QUESTION ^ assignmentExpression COLON ! conditionalExpression[false] )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1273:3: logicalOrExpression ( QUESTION ^ assignmentExpression COLON ! conditionalExpression[false] )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_logicalOrExpression_in_conditionalExpression5828);
            logicalOrExpression256=logicalOrExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalOrExpression256.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1273:23: ( QUESTION ^ assignmentExpression COLON ! conditionalExpression[false] )?
            int alt114=2;
            int LA114_0 = input.LA(1);
            if ( (LA114_0==QUESTION) ) {
                int LA114_1 = input.LA(2);
                if ( (synpred177_Java()) ) {
                    alt114=1;
                }
            }
            switch (alt114) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1273:24: QUESTION ^ assignmentExpression COLON ! conditionalExpression[false]
                    {
                    QUESTION257=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_conditionalExpression5831); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    QUESTION257_tree = (ASTTree)adaptor.create(QUESTION257);
                    root_0 = (ASTTree)adaptor.becomeRoot(QUESTION257_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_assignmentExpression_in_conditionalExpression5834);
                    assignmentExpression258=assignmentExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression258.getTree());
        
                    COLON259=(Token)match(input,COLON,FOLLOW_COLON_in_conditionalExpression5836); if (state.failed) return retval;
                    pushFollow(FOLLOW_conditionalExpression_in_conditionalExpression5839);
                    conditionalExpression260=conditionalExpression(false);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression260.getTree());
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
            if (storeSrcCode) {
              retval.tree.setSourceCode(input.toString(retval.start,input.LT(-1)));
            }
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 82, conditionalExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "logicalOrExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1278:1: logicalOrExpression : logicalAndExpression ( LOR ^ logicalAndExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.logicalOrExpression_return logicalOrExpression() throws RecognitionException {
        JavaParser.logicalOrExpression_return retval = new JavaParser.logicalOrExpression_return();
        retval.start = input.LT(1);
        int logicalOrExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token LOR262=null;
        ParserRuleReturnScope logicalAndExpression261 =null;
        ParserRuleReturnScope logicalAndExpression263 =null;
        
        ASTTree LOR262_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 83) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1279:3: ( logicalAndExpression ( LOR ^ logicalAndExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1280:3: logicalAndExpression ( LOR ^ logicalAndExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression5859);
            logicalAndExpression261=logicalAndExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalAndExpression261.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1280:24: ( LOR ^ logicalAndExpression )*
            loop115:
            while (true) {
                int alt115=2;
                int LA115_0 = input.LA(1);
                if ( (LA115_0==LOR) ) {
                    int LA115_2 = input.LA(2);
                    if ( (synpred178_Java()) ) {
                        alt115=1;
                    }
        
                }
        
                switch (alt115) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1280:25: LOR ^ logicalAndExpression
                    {
                    LOR262=(Token)match(input,LOR,FOLLOW_LOR_in_logicalOrExpression5862); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOR262_tree = (ASTTree)adaptor.create(LOR262);
                    root_0 = (ASTTree)adaptor.becomeRoot(LOR262_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_logicalAndExpression_in_logicalOrExpression5865);
                    logicalAndExpression263=logicalAndExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, logicalAndExpression263.getTree());
        
                    }
                    break;
        
                default :
                    break loop115;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 83, logicalOrExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "logicalAndExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1285:1: logicalAndExpression : inclusiveOrExpression ( LAND ^ inclusiveOrExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.logicalAndExpression_return logicalAndExpression() throws RecognitionException {
        JavaParser.logicalAndExpression_return retval = new JavaParser.logicalAndExpression_return();
        retval.start = input.LT(1);
        int logicalAndExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token LAND265=null;
        ParserRuleReturnScope inclusiveOrExpression264 =null;
        ParserRuleReturnScope inclusiveOrExpression266 =null;
        
        ASTTree LAND265_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 84) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1286:3: ( inclusiveOrExpression ( LAND ^ inclusiveOrExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1287:3: inclusiveOrExpression ( LAND ^ inclusiveOrExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_inclusiveOrExpression_in_logicalAndExpression5884);
            inclusiveOrExpression264=inclusiveOrExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusiveOrExpression264.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1287:25: ( LAND ^ inclusiveOrExpression )*
            loop116:
            while (true) {
                int alt116=2;
                int LA116_0 = input.LA(1);
                if ( (LA116_0==LAND) ) {
                    int LA116_2 = input.LA(2);
                    if ( (synpred179_Java()) ) {
                        alt116=1;
                    }
        
                }
        
                switch (alt116) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1287:26: LAND ^ inclusiveOrExpression
                    {
                    LAND265=(Token)match(input,LAND,FOLLOW_LAND_in_logicalAndExpression5887); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LAND265_tree = (ASTTree)adaptor.create(LAND265);
                    root_0 = (ASTTree)adaptor.becomeRoot(LAND265_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_inclusiveOrExpression_in_logicalAndExpression5890);
                    inclusiveOrExpression266=inclusiveOrExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusiveOrExpression266.getTree());
        
                    }
                    break;
        
                default :
                    break loop116;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 84, logicalAndExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "inclusiveOrExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1292:1: inclusiveOrExpression : exclusiveOrExpression ( BOR ^ exclusiveOrExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException {
        JavaParser.inclusiveOrExpression_return retval = new JavaParser.inclusiveOrExpression_return();
        retval.start = input.LT(1);
        int inclusiveOrExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token BOR268=null;
        ParserRuleReturnScope exclusiveOrExpression267 =null;
        ParserRuleReturnScope exclusiveOrExpression269 =null;
        
        ASTTree BOR268_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 85) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1293:3: ( exclusiveOrExpression ( BOR ^ exclusiveOrExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1294:3: exclusiveOrExpression ( BOR ^ exclusiveOrExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5909);
            exclusiveOrExpression267=exclusiveOrExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exclusiveOrExpression267.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1294:25: ( BOR ^ exclusiveOrExpression )*
            loop117:
            while (true) {
                int alt117=2;
                int LA117_0 = input.LA(1);
                if ( (LA117_0==BOR) ) {
                    int LA117_2 = input.LA(2);
                    if ( (synpred180_Java()) ) {
                        alt117=1;
                    }
        
                }
        
                switch (alt117) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1294:26: BOR ^ exclusiveOrExpression
                    {
                    BOR268=(Token)match(input,BOR,FOLLOW_BOR_in_inclusiveOrExpression5912); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOR268_tree = (ASTTree)adaptor.create(BOR268);
                    root_0 = (ASTTree)adaptor.becomeRoot(BOR268_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5915);
                    exclusiveOrExpression269=exclusiveOrExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, exclusiveOrExpression269.getTree());
        
                    }
                    break;
        
                default :
                    break loop117;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 85, inclusiveOrExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "exclusiveOrExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1299:1: exclusiveOrExpression : andExpression ( BXOR ^ andExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException {
        JavaParser.exclusiveOrExpression_return retval = new JavaParser.exclusiveOrExpression_return();
        retval.start = input.LT(1);
        int exclusiveOrExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token BXOR271=null;
        ParserRuleReturnScope andExpression270 =null;
        ParserRuleReturnScope andExpression272 =null;
        
        ASTTree BXOR271_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 86) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1300:3: ( andExpression ( BXOR ^ andExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1301:3: andExpression ( BXOR ^ andExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression5934);
            andExpression270=andExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpression270.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1301:17: ( BXOR ^ andExpression )*
            loop118:
            while (true) {
                int alt118=2;
                int LA118_0 = input.LA(1);
                if ( (LA118_0==BXOR) ) {
                    int LA118_2 = input.LA(2);
                    if ( (synpred181_Java()) ) {
                        alt118=1;
                    }
        
                }
        
                switch (alt118) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1301:18: BXOR ^ andExpression
                    {
                    BXOR271=(Token)match(input,BXOR,FOLLOW_BXOR_in_exclusiveOrExpression5937); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BXOR271_tree = (ASTTree)adaptor.create(BXOR271);
                    root_0 = (ASTTree)adaptor.becomeRoot(BXOR271_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression5940);
                    andExpression272=andExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpression272.getTree());
        
                    }
                    break;
        
                default :
                    break loop118;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 86, exclusiveOrExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "andExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1306:1: andExpression : equalityExpression ( BAND ^ equalityExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.andExpression_return andExpression() throws RecognitionException {
        JavaParser.andExpression_return retval = new JavaParser.andExpression_return();
        retval.start = input.LT(1);
        int andExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token BAND274=null;
        ParserRuleReturnScope equalityExpression273 =null;
        ParserRuleReturnScope equalityExpression275 =null;
        
        ASTTree BAND274_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 87) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1307:3: ( equalityExpression ( BAND ^ equalityExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1308:3: equalityExpression ( BAND ^ equalityExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_equalityExpression_in_andExpression5959);
            equalityExpression273=equalityExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression273.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1308:22: ( BAND ^ equalityExpression )*
            loop119:
            while (true) {
                int alt119=2;
                int LA119_0 = input.LA(1);
                if ( (LA119_0==BAND) ) {
                    int LA119_2 = input.LA(2);
                    if ( (synpred182_Java()) ) {
                        alt119=1;
                    }
        
                }
        
                switch (alt119) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1308:23: BAND ^ equalityExpression
                    {
                    BAND274=(Token)match(input,BAND,FOLLOW_BAND_in_andExpression5962); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BAND274_tree = (ASTTree)adaptor.create(BAND274);
                    root_0 = (ASTTree)adaptor.becomeRoot(BAND274_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_equalityExpression_in_andExpression5965);
                    equalityExpression275=equalityExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression275.getTree());
        
                    }
                    break;
        
                default :
                    break loop119;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 87, andExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "equalityExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1313:1: equalityExpression : instanceOfExpression ( ( NOT_EQUAL ^| EQUAL ^) instanceOfExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.equalityExpression_return equalityExpression() throws RecognitionException {
        JavaParser.equalityExpression_return retval = new JavaParser.equalityExpression_return();
        retval.start = input.LT(1);
        int equalityExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token NOT_EQUAL277=null;
        Token EQUAL278=null;
        ParserRuleReturnScope instanceOfExpression276 =null;
        ParserRuleReturnScope instanceOfExpression279 =null;
        
        ASTTree NOT_EQUAL277_tree=null;
        ASTTree EQUAL278_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 88) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1314:3: ( instanceOfExpression ( ( NOT_EQUAL ^| EQUAL ^) instanceOfExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1315:3: instanceOfExpression ( ( NOT_EQUAL ^| EQUAL ^) instanceOfExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression5984);
            instanceOfExpression276=instanceOfExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceOfExpression276.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1316:3: ( ( NOT_EQUAL ^| EQUAL ^) instanceOfExpression )*
            loop121:
            while (true) {
                int alt121=2;
                int LA121_0 = input.LA(1);
                if ( (LA121_0==NOT_EQUAL) ) {
                    int LA121_2 = input.LA(2);
                    if ( (synpred184_Java()) ) {
                        alt121=1;
                    }
        
                }
                else if ( (LA121_0==EQUAL) ) {
                    int LA121_3 = input.LA(2);
                    if ( (synpred184_Java()) ) {
                        alt121=1;
                    }
        
                }
        
                switch (alt121) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1317:5: ( NOT_EQUAL ^| EQUAL ^) instanceOfExpression
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1317:5: ( NOT_EQUAL ^| EQUAL ^)
                    int alt120=2;
                    int LA120_0 = input.LA(1);
                    if ( (LA120_0==NOT_EQUAL) ) {
                        alt120=1;
                    }
                    else if ( (LA120_0==EQUAL) ) {
                        alt120=2;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 120, 0, input);
                        throw nvae;
                    }
        
                    switch (alt120) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1318:7: NOT_EQUAL ^
                            {
                            NOT_EQUAL277=(Token)match(input,NOT_EQUAL,FOLLOW_NOT_EQUAL_in_equalityExpression6002); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            NOT_EQUAL277_tree = (ASTTree)adaptor.create(NOT_EQUAL277);
                            root_0 = (ASTTree)adaptor.becomeRoot(NOT_EQUAL277_tree, root_0);
                            }
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1319:9: EQUAL ^
                            {
                            EQUAL278=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_equalityExpression6013); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            EQUAL278_tree = (ASTTree)adaptor.create(EQUAL278);
                            root_0 = (ASTTree)adaptor.becomeRoot(EQUAL278_tree, root_0);
                            }
        
                            }
                            break;
        
                    }
        
                    pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression6026);
                    instanceOfExpression279=instanceOfExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceOfExpression279.getTree());
        
                    }
                    break;
        
                default :
                    break loop121;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 88, equalityExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "instanceOfExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1327:1: instanceOfExpression : relationalExpression ( 'instanceof' ^ typeSpec )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.instanceOfExpression_return instanceOfExpression() throws RecognitionException {
        JavaParser.instanceOfExpression_return retval = new JavaParser.instanceOfExpression_return();
        retval.start = input.LT(1);
        int instanceOfExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token string_literal281=null;
        ParserRuleReturnScope relationalExpression280 =null;
        ParserRuleReturnScope typeSpec282 =null;
        
        ASTTree string_literal281_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 89) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1328:3: ( relationalExpression ( 'instanceof' ^ typeSpec )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1329:3: relationalExpression ( 'instanceof' ^ typeSpec )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_relationalExpression_in_instanceOfExpression6048);
            relationalExpression280=relationalExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpression280.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1329:24: ( 'instanceof' ^ typeSpec )?
            int alt122=2;
            int LA122_0 = input.LA(1);
            if ( (LA122_0==156) ) {
                int LA122_1 = input.LA(2);
                if ( (synpred185_Java()) ) {
                    alt122=1;
                }
            }
            switch (alt122) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1329:25: 'instanceof' ^ typeSpec
                    {
                    string_literal281=(Token)match(input,156,FOLLOW_156_in_instanceOfExpression6051); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal281_tree = (ASTTree)adaptor.create(string_literal281);
                    root_0 = (ASTTree)adaptor.becomeRoot(string_literal281_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_typeSpec_in_instanceOfExpression6054);
                    typeSpec282=typeSpec();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSpec282.getTree());
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 89, instanceOfExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "relationalExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1334:1: relationalExpression : additiveExpression ( relationalOperator ^ additiveExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.relationalExpression_return relationalExpression() throws RecognitionException {
        JavaParser.relationalExpression_return retval = new JavaParser.relationalExpression_return();
        retval.start = input.LT(1);
        int relationalExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope additiveExpression283 =null;
        ParserRuleReturnScope relationalOperator284 =null;
        ParserRuleReturnScope additiveExpression285 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 90) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1335:3: ( additiveExpression ( relationalOperator ^ additiveExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1336:3: additiveExpression ( relationalOperator ^ additiveExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_additiveExpression_in_relationalExpression6073);
            additiveExpression283=additiveExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression283.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1336:22: ( relationalOperator ^ additiveExpression )*
            loop123:
            while (true) {
                int alt123=2;
                int LA123_0 = input.LA(1);
                if ( (LA123_0==GT) ) {
                    int LA123_2 = input.LA(2);
                    if ( (synpred186_Java()) ) {
                        alt123=1;
                    }
        
                }
                else if ( (LA123_0==LT) ) {
                    int LA123_3 = input.LA(2);
                    if ( (synpred186_Java()) ) {
                        alt123=1;
                    }
        
                }
        
                switch (alt123) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1336:23: relationalOperator ^ additiveExpression
                    {
                    pushFollow(FOLLOW_relationalOperator_in_relationalExpression6076);
                    relationalOperator284=relationalOperator();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (ASTTree)adaptor.becomeRoot(relationalOperator284.getTree(), root_0);
                    pushFollow(FOLLOW_additiveExpression_in_relationalExpression6079);
                    additiveExpression285=additiveExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression285.getTree());
        
                    }
                    break;
        
                default :
                    break loop123;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 90, relationalExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "relationalOperator"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1339:1: relationalOperator : ( LT -> LT | LT LT -> SL | LT '=' -> LE | GT -> GT | GT GT -> SR | GT GT GT -> BSR | GT '=' -> GE );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.relationalOperator_return relationalOperator() throws RecognitionException {
        JavaParser.relationalOperator_return retval = new JavaParser.relationalOperator_return();
        retval.start = input.LT(1);
        int relationalOperator_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token LT286=null;
        Token LT287=null;
        Token LT288=null;
        Token LT289=null;
        Token char_literal290=null;
        Token GT291=null;
        Token GT292=null;
        Token GT293=null;
        Token GT294=null;
        Token GT295=null;
        Token GT296=null;
        Token GT297=null;
        Token char_literal298=null;
        
        ASTTree LT286_tree=null;
        ASTTree LT287_tree=null;
        ASTTree LT288_tree=null;
        ASTTree LT289_tree=null;
        ASTTree char_literal290_tree=null;
        ASTTree GT291_tree=null;
        ASTTree GT292_tree=null;
        ASTTree GT293_tree=null;
        ASTTree GT294_tree=null;
        ASTTree GT295_tree=null;
        ASTTree GT296_tree=null;
        ASTTree GT297_tree=null;
        ASTTree char_literal298_tree=null;
        RewriteRuleTokenStream stream_GT=new RewriteRuleTokenStream(adaptor,"token GT");
        RewriteRuleTokenStream stream_LT=new RewriteRuleTokenStream(adaptor,"token LT");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 91) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1340:3: ( LT -> LT | LT LT -> SL | LT '=' -> LE | GT -> GT | GT GT -> SR | GT GT GT -> BSR | GT '=' -> GE )
            int alt124=7;
            int LA124_0 = input.LA(1);
            if ( (LA124_0==LT) ) {
                switch ( input.LA(2) ) {
                case LT:
                    {
                    alt124=2;
                    }
                    break;
                case ASSIGN:
                    {
                    alt124=3;
                    }
                    break;
                case AT:
                case BNOT:
                case CHAR_LITERAL:
                case DEC:
                case IDENT:
                case INC:
                case LNOT:
                case LPAREN:
                case MINUS:
                case NEW:
                case NUM_LITERAL:
                case PLUS:
                case STRING_LITERAL:
                case SUPER:
                case 135:
                case 137:
                case 140:
                case 144:
                case 148:
                case 151:
                case 157:
                case 159:
                case 161:
                case 166:
                case 171:
                case 176:
                case 178:
                    {
                    alt124=1;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 124, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
            }
            else if ( (LA124_0==GT) ) {
                switch ( input.LA(2) ) {
                case GT:
                    {
                    int LA124_6 = input.LA(3);
                    if ( (LA124_6==GT) ) {
                        alt124=6;
                    }
                    else if ( (LA124_6==AT||LA124_6==BNOT||LA124_6==CHAR_LITERAL||LA124_6==DEC||LA124_6==IDENT||LA124_6==INC||LA124_6==LNOT||LA124_6==LPAREN||LA124_6==MINUS||LA124_6==NEW||LA124_6==NUM_LITERAL||LA124_6==PLUS||(LA124_6 >= STRING_LITERAL && LA124_6 <= SUPER)||LA124_6==135||LA124_6==137||LA124_6==140||LA124_6==144||LA124_6==148||LA124_6==151||LA124_6==157||LA124_6==159||LA124_6==161||LA124_6==166||LA124_6==171||LA124_6==176||LA124_6==178) ) {
                        alt124=5;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        int nvaeMark = input.mark();
                        try {
                            for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
                                input.consume();
                            }
                            NoViableAltException nvae =
                                new NoViableAltException("", 124, 6, input);
                            throw nvae;
                        } finally {
                            input.rewind(nvaeMark);
                        }
                    }
        
                    }
                    break;
                case ASSIGN:
                    {
                    alt124=7;
                    }
                    break;
                case AT:
                case BNOT:
                case CHAR_LITERAL:
                case DEC:
                case IDENT:
                case INC:
                case LNOT:
                case LPAREN:
                case MINUS:
                case NEW:
                case NUM_LITERAL:
                case PLUS:
                case STRING_LITERAL:
                case SUPER:
                case 135:
                case 137:
                case 140:
                case 144:
                case 148:
                case 151:
                case 157:
                case 159:
                case 161:
                case 166:
                case 171:
                case 176:
                case 178:
                    {
                    alt124=4;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 124, 2, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 124, 0, input);
                throw nvae;
            }
        
            switch (alt124) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1341:3: LT
                    {
                    LT286=(Token)match(input,LT,FOLLOW_LT_in_relationalOperator6096); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LT.add(LT286);
        
                    // AST REWRITE
                    // elements: LT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1342:5: -> LT
                    {
                        adaptor.addChild(root_0, stream_LT.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1343:5: LT LT
                    {
                    LT287=(Token)match(input,LT,FOLLOW_LT_in_relationalOperator6110); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LT.add(LT287);
        
                    LT288=(Token)match(input,LT,FOLLOW_LT_in_relationalOperator6112); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LT.add(LT288);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1344:5: -> SL
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(SL, "SL"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1345:5: LT '='
                    {
                    LT289=(Token)match(input,LT,FOLLOW_LT_in_relationalOperator6126); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LT.add(LT289);
        
                    char_literal290=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_relationalOperator6128); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal290);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1346:5: -> LE
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(LE, "LE"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1347:5: GT
                    {
                    GT291=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6142); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT291);
        
                    // AST REWRITE
                    // elements: GT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1348:5: -> GT
                    {
                        adaptor.addChild(root_0, stream_GT.nextNode());
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1349:5: GT GT
                    {
                    GT292=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6156); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT292);
        
                    GT293=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6158); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT293);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1350:5: -> SR
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(SR, "SR"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1351:5: GT GT GT
                    {
                    GT294=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6172); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT294);
        
                    GT295=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6174); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT295);
        
                    GT296=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6176); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT296);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1352:5: -> BSR
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(BSR, "BSR"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 7 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1353:5: GT '='
                    {
                    GT297=(Token)match(input,GT,FOLLOW_GT_in_relationalOperator6190); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_GT.add(GT297);
        
                    char_literal298=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_relationalOperator6192); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ASSIGN.add(char_literal298);
        
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1354:5: -> GE
                    {
                        adaptor.addChild(root_0, (ASTTree)adaptor.create(GE, "GE"));
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 91, relationalOperator_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "additiveExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1358:1: additiveExpression : multiplicativeExpression ( ( PLUS ^| MINUS ^) multiplicativeExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.additiveExpression_return additiveExpression() throws RecognitionException {
        JavaParser.additiveExpression_return retval = new JavaParser.additiveExpression_return();
        retval.start = input.LT(1);
        int additiveExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token PLUS300=null;
        Token MINUS301=null;
        ParserRuleReturnScope multiplicativeExpression299 =null;
        ParserRuleReturnScope multiplicativeExpression302 =null;
        
        ASTTree PLUS300_tree=null;
        ASTTree MINUS301_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 92) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1359:3: ( multiplicativeExpression ( ( PLUS ^| MINUS ^) multiplicativeExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1360:3: multiplicativeExpression ( ( PLUS ^| MINUS ^) multiplicativeExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression6216);
            multiplicativeExpression299=multiplicativeExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression299.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1361:3: ( ( PLUS ^| MINUS ^) multiplicativeExpression )*
            loop126:
            while (true) {
                int alt126=2;
                int LA126_0 = input.LA(1);
                if ( (LA126_0==PLUS) ) {
                    int LA126_2 = input.LA(2);
                    if ( (synpred194_Java()) ) {
                        alt126=1;
                    }
        
                }
                else if ( (LA126_0==MINUS) ) {
                    int LA126_3 = input.LA(2);
                    if ( (synpred194_Java()) ) {
                        alt126=1;
                    }
        
                }
        
                switch (alt126) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1362:5: ( PLUS ^| MINUS ^) multiplicativeExpression
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1362:5: ( PLUS ^| MINUS ^)
                    int alt125=2;
                    int LA125_0 = input.LA(1);
                    if ( (LA125_0==PLUS) ) {
                        alt125=1;
                    }
                    else if ( (LA125_0==MINUS) ) {
                        alt125=2;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 125, 0, input);
                        throw nvae;
                    }
        
                    switch (alt125) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1363:7: PLUS ^
                            {
                            PLUS300=(Token)match(input,PLUS,FOLLOW_PLUS_in_additiveExpression6234); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            PLUS300_tree = (ASTTree)adaptor.create(PLUS300);
                            root_0 = (ASTTree)adaptor.becomeRoot(PLUS300_tree, root_0);
                            }
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1364:9: MINUS ^
                            {
                            MINUS301=(Token)match(input,MINUS,FOLLOW_MINUS_in_additiveExpression6245); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            MINUS301_tree = (ASTTree)adaptor.create(MINUS301);
                            root_0 = (ASTTree)adaptor.becomeRoot(MINUS301_tree, root_0);
                            }
        
                            }
                            break;
        
                    }
        
                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression6258);
                    multiplicativeExpression302=multiplicativeExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression302.getTree());
        
                    }
                    break;
        
                default :
                    break loop126;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 92, additiveExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "multiplicativeExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1372:1: multiplicativeExpression : unaryExpression ( ( STAR ^| DIV ^| MOD ^) unaryExpression )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        JavaParser.multiplicativeExpression_return retval = new JavaParser.multiplicativeExpression_return();
        retval.start = input.LT(1);
        int multiplicativeExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token STAR304=null;
        Token DIV305=null;
        Token MOD306=null;
        ParserRuleReturnScope unaryExpression303 =null;
        ParserRuleReturnScope unaryExpression307 =null;
        
        ASTTree STAR304_tree=null;
        ASTTree DIV305_tree=null;
        ASTTree MOD306_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 93) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1373:3: ( unaryExpression ( ( STAR ^| DIV ^| MOD ^) unaryExpression )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1374:3: unaryExpression ( ( STAR ^| DIV ^| MOD ^) unaryExpression )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression6280);
            unaryExpression303=unaryExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression303.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1375:3: ( ( STAR ^| DIV ^| MOD ^) unaryExpression )*
            loop128:
            while (true) {
                int alt128=2;
                switch ( input.LA(1) ) {
                case STAR:
                    {
                    int LA128_2 = input.LA(2);
                    if ( (synpred197_Java()) ) {
                        alt128=1;
                    }
        
                    }
                    break;
                case DIV:
                    {
                    int LA128_3 = input.LA(2);
                    if ( (synpred197_Java()) ) {
                        alt128=1;
                    }
        
                    }
                    break;
                case MOD:
                    {
                    int LA128_4 = input.LA(2);
                    if ( (synpred197_Java()) ) {
                        alt128=1;
                    }
        
                    }
                    break;
                }
                switch (alt128) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1376:5: ( STAR ^| DIV ^| MOD ^) unaryExpression
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1376:5: ( STAR ^| DIV ^| MOD ^)
                    int alt127=3;
                    switch ( input.LA(1) ) {
                    case STAR:
                        {
                        alt127=1;
                        }
                        break;
                    case DIV:
                        {
                        alt127=2;
                        }
                        break;
                    case MOD:
                        {
                        alt127=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 127, 0, input);
                        throw nvae;
                    }
                    switch (alt127) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1377:7: STAR ^
                            {
                            STAR304=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicativeExpression6298); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            STAR304_tree = (ASTTree)adaptor.create(STAR304);
                            root_0 = (ASTTree)adaptor.becomeRoot(STAR304_tree, root_0);
                            }
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1378:9: DIV ^
                            {
                            DIV305=(Token)match(input,DIV,FOLLOW_DIV_in_multiplicativeExpression6309); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DIV305_tree = (ASTTree)adaptor.create(DIV305);
                            root_0 = (ASTTree)adaptor.becomeRoot(DIV305_tree, root_0);
                            }
        
                            }
                            break;
                        case 3 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1379:9: MOD ^
                            {
                            MOD306=(Token)match(input,MOD,FOLLOW_MOD_in_multiplicativeExpression6320); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            MOD306_tree = (ASTTree)adaptor.create(MOD306);
                            root_0 = (ASTTree)adaptor.becomeRoot(MOD306_tree, root_0);
                            }
        
                            }
                            break;
        
                    }
        
                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression6333);
                    unaryExpression307=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression307.getTree());
        
                    }
                    break;
        
                default :
                    break loop128;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 93, multiplicativeExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "unaryExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1385:1: unaryExpression : ( INC ^ unaryExpression | DEC ^ unaryExpression | MINUS ^ unaryExpression | PLUS ^ unaryExpression | unaryExpressionNotPlusMinus );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.unaryExpression_return unaryExpression() throws RecognitionException {
        JavaParser.unaryExpression_return retval = new JavaParser.unaryExpression_return();
        retval.start = input.LT(1);
        int unaryExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token INC308=null;
        Token DEC310=null;
        Token MINUS312=null;
        Token PLUS314=null;
        ParserRuleReturnScope unaryExpression309 =null;
        ParserRuleReturnScope unaryExpression311 =null;
        ParserRuleReturnScope unaryExpression313 =null;
        ParserRuleReturnScope unaryExpression315 =null;
        ParserRuleReturnScope unaryExpressionNotPlusMinus316 =null;
        
        ASTTree INC308_tree=null;
        ASTTree DEC310_tree=null;
        ASTTree MINUS312_tree=null;
        ASTTree PLUS314_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 94) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1386:3: ( INC ^ unaryExpression | DEC ^ unaryExpression | MINUS ^ unaryExpression | PLUS ^ unaryExpression | unaryExpressionNotPlusMinus )
            int alt129=5;
            switch ( input.LA(1) ) {
            case INC:
                {
                alt129=1;
                }
                break;
            case DEC:
                {
                alt129=2;
                }
                break;
            case MINUS:
                {
                alt129=3;
                }
                break;
            case PLUS:
                {
                alt129=4;
                }
                break;
            case AT:
            case BNOT:
            case CHAR_LITERAL:
            case IDENT:
            case LNOT:
            case LPAREN:
            case NEW:
            case NUM_LITERAL:
            case STRING_LITERAL:
            case SUPER:
            case 135:
            case 137:
            case 140:
            case 144:
            case 148:
            case 151:
            case 157:
            case 159:
            case 161:
            case 166:
            case 171:
            case 176:
            case 178:
                {
                alt129=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 129, 0, input);
                throw nvae;
            }
            switch (alt129) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1387:3: INC ^ unaryExpression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    INC308=(Token)match(input,INC,FOLLOW_INC_in_unaryExpression6353); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INC308_tree = (ASTTree)adaptor.create(INC308);
                    root_0 = (ASTTree)adaptor.becomeRoot(INC308_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6356);
                    unaryExpression309=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression309.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1388:5: DEC ^ unaryExpression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    DEC310=(Token)match(input,DEC,FOLLOW_DEC_in_unaryExpression6362); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DEC310_tree = (ASTTree)adaptor.create(DEC310);
                    root_0 = (ASTTree)adaptor.becomeRoot(DEC310_tree, root_0);
                    }
        
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6365);
                    unaryExpression311=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression311.getTree());
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1389:5: MINUS ^ unaryExpression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    MINUS312=(Token)match(input,MINUS,FOLLOW_MINUS_in_unaryExpression6371); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS312_tree = (ASTTree)adaptor.create(MINUS312);
                    root_0 = (ASTTree)adaptor.becomeRoot(MINUS312_tree, root_0);
                    }
        
                    if ( state.backtracking==0 ) {
                               MINUS312.setType(UNARY_MINUS);
                              }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6391);
                    unaryExpression313=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression313.getTree());
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1394:5: PLUS ^ unaryExpression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    PLUS314=(Token)match(input,PLUS,FOLLOW_PLUS_in_unaryExpression6397); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUS314_tree = (ASTTree)adaptor.create(PLUS314);
                    root_0 = (ASTTree)adaptor.becomeRoot(PLUS314_tree, root_0);
                    }
        
                    if ( state.backtracking==0 ) {
                              PLUS314.setType(UNARY_PLUS);
                             }
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression6414);
                    unaryExpression315=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression315.getTree());
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1399:5: unaryExpressionNotPlusMinus
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression6420);
                    unaryExpressionNotPlusMinus316=unaryExpressionNotPlusMinus();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpressionNotPlusMinus316.getTree());
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 94, unaryExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "unaryExpressionNotPlusMinus"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1402:1: unaryExpressionNotPlusMinus : ( BNOT unaryExpression -> ^( BNOT unaryExpression ) | LNOT unaryExpression -> ^( LNOT unaryExpression ) | ( options {greedy=true; } : LPAREN builtInTypeSpec RPAREN unaryExpression -> ^( TYPECAST builtInTypeSpec unaryExpression ) | ( LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus )=> LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus -> ^( TYPECAST ( classTypeSpec )+ unaryExpressionNotPlusMinus ) | methodReference -> methodReference ) );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus() throws RecognitionException {
        JavaParser.unaryExpressionNotPlusMinus_return retval = new JavaParser.unaryExpressionNotPlusMinus_return();
        retval.start = input.LT(1);
        int unaryExpressionNotPlusMinus_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token BNOT317=null;
        Token LNOT319=null;
        Token LPAREN321=null;
        Token RPAREN323=null;
        Token LPAREN325=null;
        Token BAND327=null;
        Token RPAREN329=null;
        ParserRuleReturnScope unaryExpression318 =null;
        ParserRuleReturnScope unaryExpression320 =null;
        ParserRuleReturnScope builtInTypeSpec322 =null;
        ParserRuleReturnScope unaryExpression324 =null;
        ParserRuleReturnScope classTypeSpec326 =null;
        ParserRuleReturnScope classTypeSpec328 =null;
        ParserRuleReturnScope unaryExpressionNotPlusMinus330 =null;
        ParserRuleReturnScope methodReference331 =null;
        
        ASTTree BNOT317_tree=null;
        ASTTree LNOT319_tree=null;
        ASTTree LPAREN321_tree=null;
        ASTTree RPAREN323_tree=null;
        ASTTree LPAREN325_tree=null;
        ASTTree BAND327_tree=null;
        ASTTree RPAREN329_tree=null;
        RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
        RewriteRuleTokenStream stream_LNOT=new RewriteRuleTokenStream(adaptor,"token LNOT");
        RewriteRuleTokenStream stream_BNOT=new RewriteRuleTokenStream(adaptor,"token BNOT");
        RewriteRuleTokenStream stream_BAND=new RewriteRuleTokenStream(adaptor,"token BAND");
        RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
        RewriteRuleSubtreeStream stream_methodReference=new RewriteRuleSubtreeStream(adaptor,"rule methodReference");
        RewriteRuleSubtreeStream stream_unaryExpression=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpression");
        RewriteRuleSubtreeStream stream_classTypeSpec=new RewriteRuleSubtreeStream(adaptor,"rule classTypeSpec");
        RewriteRuleSubtreeStream stream_unaryExpressionNotPlusMinus=new RewriteRuleSubtreeStream(adaptor,"rule unaryExpressionNotPlusMinus");
        RewriteRuleSubtreeStream stream_builtInTypeSpec=new RewriteRuleSubtreeStream(adaptor,"rule builtInTypeSpec");
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 95) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1403:3: ( BNOT unaryExpression -> ^( BNOT unaryExpression ) | LNOT unaryExpression -> ^( LNOT unaryExpression ) | ( options {greedy=true; } : LPAREN builtInTypeSpec RPAREN unaryExpression -> ^( TYPECAST builtInTypeSpec unaryExpression ) | ( LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus )=> LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus -> ^( TYPECAST ( classTypeSpec )+ unaryExpressionNotPlusMinus ) | methodReference -> methodReference ) )
            int alt132=3;
            switch ( input.LA(1) ) {
            case BNOT:
                {
                alt132=1;
                }
                break;
            case LNOT:
                {
                alt132=2;
                }
                break;
            case AT:
            case CHAR_LITERAL:
            case IDENT:
            case LPAREN:
            case NEW:
            case NUM_LITERAL:
            case STRING_LITERAL:
            case SUPER:
            case 135:
            case 137:
            case 140:
            case 144:
            case 148:
            case 151:
            case 157:
            case 159:
            case 161:
            case 166:
            case 171:
            case 176:
            case 178:
                {
                alt132=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 132, 0, input);
                throw nvae;
            }
            switch (alt132) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1403:5: BNOT unaryExpression
                    {
                    BNOT317=(Token)match(input,BNOT,FOLLOW_BNOT_in_unaryExpressionNotPlusMinus6433); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BNOT.add(BNOT317);
        
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6435);
                    unaryExpression318=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression318.getTree());
                    // AST REWRITE
                    // elements: unaryExpression, BNOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1404:5: -> ^( BNOT unaryExpression )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1404:8: ^( BNOT unaryExpression )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_BNOT.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_unaryExpression.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1405:5: LNOT unaryExpression
                    {
                    LNOT319=(Token)match(input,LNOT,FOLLOW_LNOT_in_unaryExpressionNotPlusMinus6454); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LNOT.add(LNOT319);
        
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6456);
                    unaryExpression320=unaryExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression320.getTree());
                    // AST REWRITE
                    // elements: unaryExpression, LNOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1406:5: -> ^( LNOT unaryExpression )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1406:8: ^( LNOT unaryExpression )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot(stream_LNOT.nextNode(), root_1);
                        adaptor.addChild(root_1, stream_unaryExpression.nextTree());
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1408:3: ( options {greedy=true; } : LPAREN builtInTypeSpec RPAREN unaryExpression -> ^( TYPECAST builtInTypeSpec unaryExpression ) | ( LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus )=> LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus -> ^( TYPECAST ( classTypeSpec )+ unaryExpressionNotPlusMinus ) | methodReference -> methodReference )
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1408:3: ( options {greedy=true; } : LPAREN builtInTypeSpec RPAREN unaryExpression -> ^( TYPECAST builtInTypeSpec unaryExpression ) | ( LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus )=> LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus -> ^( TYPECAST ( classTypeSpec )+ unaryExpressionNotPlusMinus ) | methodReference -> methodReference )
                    int alt131=3;
                    int LA131_0 = input.LA(1);
                    if ( (LA131_0==LPAREN) ) {
                        int LA131_1 = input.LA(2);
                        if ( (synpred204_Java()) ) {
                            alt131=1;
                        }
                        else if ( (synpred206_Java()) ) {
                            alt131=2;
                        }
                        else if ( (true) ) {
                            alt131=3;
                        }
        
                    }
                    else if ( (LA131_0==AT||LA131_0==CHAR_LITERAL||LA131_0==IDENT||LA131_0==NEW||LA131_0==NUM_LITERAL||(LA131_0 >= STRING_LITERAL && LA131_0 <= SUPER)||LA131_0==135||LA131_0==137||LA131_0==140||LA131_0==144||LA131_0==148||LA131_0==151||LA131_0==157||LA131_0==159||LA131_0==161||LA131_0==166||LA131_0==171||LA131_0==176||LA131_0==178) ) {
                        alt131=3;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 131, 0, input);
                        throw nvae;
                    }
        
                    switch (alt131) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1416:5: LPAREN builtInTypeSpec RPAREN unaryExpression
                            {
                            LPAREN321=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus6532); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN321);
        
                            pushFollow(FOLLOW_builtInTypeSpec_in_unaryExpressionNotPlusMinus6534);
                            builtInTypeSpec322=builtInTypeSpec();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_builtInTypeSpec.add(builtInTypeSpec322.getTree());
                            RPAREN323=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus6536); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN323);
        
                            pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus6538);
                            unaryExpression324=unaryExpression();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_unaryExpression.add(unaryExpression324.getTree());
                            // AST REWRITE
                            // elements: unaryExpression, builtInTypeSpec
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                            root_0 = (ASTTree)adaptor.nil();
                            // 1417:7: -> ^( TYPECAST builtInTypeSpec unaryExpression )
                            {
                                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1417:10: ^( TYPECAST builtInTypeSpec unaryExpression )
                                {
                                ASTTree root_1 = (ASTTree)adaptor.nil();
                                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPECAST, "TYPECAST"), root_1);
                                adaptor.addChild(root_1, stream_builtInTypeSpec.nextTree());
                                adaptor.addChild(root_1, stream_unaryExpression.nextTree());
                                adaptor.addChild(root_0, root_1);
                                }
        
                            }
        
        
                            retval.tree = root_0;
                            }
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:7: ( LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus )=> LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus
                            {
                            LPAREN325=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_unaryExpressionNotPlusMinus6597); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN325);
        
                            pushFollow(FOLLOW_classTypeSpec_in_unaryExpressionNotPlusMinus6599);
                            classTypeSpec326=classTypeSpec();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_classTypeSpec.add(classTypeSpec326.getTree());
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:111: ( BAND classTypeSpec )*
                            loop130:
                            while (true) {
                                int alt130=2;
                                int LA130_0 = input.LA(1);
                                if ( (LA130_0==BAND) ) {
                                    alt130=1;
                                }
        
                                switch (alt130) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:112: BAND classTypeSpec
                                    {
                                    BAND327=(Token)match(input,BAND,FOLLOW_BAND_in_unaryExpressionNotPlusMinus6602); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BAND.add(BAND327);
        
                                    pushFollow(FOLLOW_classTypeSpec_in_unaryExpressionNotPlusMinus6604);
                                    classTypeSpec328=classTypeSpec();
                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_classTypeSpec.add(classTypeSpec328.getTree());
                                    }
                                    break;
        
                                default :
                                    break loop130;
                                }
                            }
        
                            RPAREN329=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_unaryExpressionNotPlusMinus6608); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN329);
        
                            pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpressionNotPlusMinus6610);
                            unaryExpressionNotPlusMinus330=unaryExpressionNotPlusMinus();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_unaryExpressionNotPlusMinus.add(unaryExpressionNotPlusMinus330.getTree());
                            // AST REWRITE
                            // elements: unaryExpressionNotPlusMinus, classTypeSpec
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                            root_0 = (ASTTree)adaptor.nil();
                            // 1422:7: -> ^( TYPECAST ( classTypeSpec )+ unaryExpressionNotPlusMinus )
                            {
                                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1422:10: ^( TYPECAST ( classTypeSpec )+ unaryExpressionNotPlusMinus )
                                {
                                ASTTree root_1 = (ASTTree)adaptor.nil();
                                root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(TYPECAST, "TYPECAST"), root_1);
                                if ( !(stream_classTypeSpec.hasNext()) ) {
                                    throw new RewriteEarlyExitException();
                                }
                                while ( stream_classTypeSpec.hasNext() ) {
                                    adaptor.addChild(root_1, stream_classTypeSpec.nextTree());
                                }
                                stream_classTypeSpec.reset();
        
                                adaptor.addChild(root_1, stream_unaryExpressionNotPlusMinus.nextTree());
                                adaptor.addChild(root_0, root_1);
                                }
        
                            }
        
        
                            retval.tree = root_0;
                            }
        
                            }
                            break;
                        case 3 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1423:7: methodReference
                            {
                            pushFollow(FOLLOW_methodReference_in_unaryExpressionNotPlusMinus6636);
                            methodReference331=methodReference();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_methodReference.add(methodReference331.getTree());
                            // AST REWRITE
                            // elements: methodReference
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                            root_0 = (ASTTree)adaptor.nil();
                            // 1424:7: -> methodReference
                            {
                                adaptor.addChild(root_0, stream_methodReference.nextTree());
                            }
        
        
                            retval.tree = root_0;
                            }
        
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 95, unaryExpressionNotPlusMinus_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "methodReference"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1428:1: methodReference : ( ( typeSpec | voidkw ) REF ( typeArguments )? ( IDENT | NEW ) | lambdaExpression ( REF ( typeArguments )? ( IDENT | NEW ) )? );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.methodReference_return methodReference() throws RecognitionException {
        JavaParser.methodReference_return retval = new JavaParser.methodReference_return();
        retval.start = input.LT(1);
        int methodReference_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token REF334=null;
        Token set336=null;
        Token REF338=null;
        Token set340=null;
        ParserRuleReturnScope typeSpec332 =null;
        ParserRuleReturnScope voidkw333 =null;
        ParserRuleReturnScope typeArguments335 =null;
        ParserRuleReturnScope lambdaExpression337 =null;
        ParserRuleReturnScope typeArguments339 =null;
        
        ASTTree REF334_tree=null;
        ASTTree set336_tree=null;
        ASTTree REF338_tree=null;
        ASTTree set340_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 96) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:5: ( ( typeSpec | voidkw ) REF ( typeArguments )? ( IDENT | NEW ) | lambdaExpression ( REF ( typeArguments )? ( IDENT | NEW ) )? )
            int alt137=2;
            switch ( input.LA(1) ) {
            case AT:
                {
                alt137=1;
                }
                break;
            case IDENT:
                {
                int LA137_2 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 135:
                {
                int LA137_3 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 137:
                {
                int LA137_4 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 140:
                {
                int LA137_5 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 166:
                {
                int LA137_6 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 157:
                {
                int LA137_7 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 151:
                {
                int LA137_8 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 159:
                {
                int LA137_9 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 144:
                {
                int LA137_10 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case 178:
                {
                int LA137_11 = input.LA(2);
                if ( (synpred211_Java()) ) {
                    alt137=1;
                }
                else if ( (true) ) {
                    alt137=2;
                }
        
                }
                break;
            case CHAR_LITERAL:
            case LPAREN:
            case NEW:
            case NUM_LITERAL:
            case STRING_LITERAL:
            case SUPER:
            case 148:
            case 161:
            case 171:
            case 176:
                {
                alt137=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 137, 0, input);
                throw nvae;
            }
            switch (alt137) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:9: ( typeSpec | voidkw ) REF ( typeArguments )? ( IDENT | NEW )
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:9: ( typeSpec | voidkw )
                    int alt133=2;
                    int LA133_0 = input.LA(1);
                    if ( (LA133_0==AT||LA133_0==IDENT||LA133_0==135||LA133_0==137||LA133_0==140||LA133_0==144||LA133_0==151||LA133_0==157||LA133_0==159||LA133_0==166) ) {
                        alt133=1;
                    }
                    else if ( (LA133_0==178) ) {
                        alt133=2;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 133, 0, input);
                        throw nvae;
                    }
        
                    switch (alt133) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:10: typeSpec
                            {
                            pushFollow(FOLLOW_typeSpec_in_methodReference6670);
                            typeSpec332=typeSpec();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeSpec332.getTree());
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:19: voidkw
                            {
                            pushFollow(FOLLOW_voidkw_in_methodReference6672);
                            voidkw333=voidkw();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, voidkw333.getTree());
        
                            }
                            break;
        
                    }
        
                    REF334=(Token)match(input,REF,FOLLOW_REF_in_methodReference6675); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    REF334_tree = (ASTTree)adaptor.create(REF334);
                    adaptor.addChild(root_0, REF334_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:31: ( typeArguments )?
                    int alt134=2;
                    int LA134_0 = input.LA(1);
                    if ( (LA134_0==LT) ) {
                        alt134=1;
                    }
                    switch (alt134) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:31: typeArguments
                            {
                            pushFollow(FOLLOW_typeArguments_in_methodReference6677);
                            typeArguments335=typeArguments();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments335.getTree());
        
                            }
                            break;
        
                    }
        
                    set336=input.LT(1);
                    if ( input.LA(1)==IDENT||input.LA(1)==NEW ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (ASTTree)adaptor.create(set336));
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:9: lambdaExpression ( REF ( typeArguments )? ( IDENT | NEW ) )?
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_lambdaExpression_in_methodReference6694);
                    lambdaExpression337=lambdaExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdaExpression337.getTree());
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:26: ( REF ( typeArguments )? ( IDENT | NEW ) )?
                    int alt136=2;
                    int LA136_0 = input.LA(1);
                    if ( (LA136_0==REF) ) {
                        int LA136_1 = input.LA(2);
                        if ( (synpred214_Java()) ) {
                            alt136=1;
                        }
                    }
                    switch (alt136) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:27: REF ( typeArguments )? ( IDENT | NEW )
                            {
                            REF338=(Token)match(input,REF,FOLLOW_REF_in_methodReference6697); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            REF338_tree = (ASTTree)adaptor.create(REF338);
                            adaptor.addChild(root_0, REF338_tree);
                            }
        
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:31: ( typeArguments )?
                            int alt135=2;
                            int LA135_0 = input.LA(1);
                            if ( (LA135_0==LT) ) {
                                alt135=1;
                            }
                            switch (alt135) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:31: typeArguments
                                    {
                                    pushFollow(FOLLOW_typeArguments_in_methodReference6699);
                                    typeArguments339=typeArguments();
                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments339.getTree());
        
                                    }
                                    break;
        
                            }
        
                            set340=input.LT(1);
                            if ( input.LA(1)==IDENT||input.LA(1)==NEW ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, (ASTTree)adaptor.create(set340));
                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 96, methodReference_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "lambdaExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1433:1: lambdaExpression : ( ( lambdaParameters LAMBDA )=> lambdaParameters LAMBDA lambdaBody | postfixExpression );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.lambdaExpression_return lambdaExpression() throws RecognitionException {
        JavaParser.lambdaExpression_return retval = new JavaParser.lambdaExpression_return();
        retval.start = input.LT(1);
        int lambdaExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token LAMBDA342=null;
        ParserRuleReturnScope lambdaParameters341 =null;
        ParserRuleReturnScope lambdaBody343 =null;
        ParserRuleReturnScope postfixExpression344 =null;
        
        ASTTree LAMBDA342_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 97) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1434:3: ( ( lambdaParameters LAMBDA )=> lambdaParameters LAMBDA lambdaBody | postfixExpression )
            int alt138=2;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                int LA138_1 = input.LA(2);
                if ( (synpred215_Java()) ) {
                    alt138=1;
                }
                else if ( (true) ) {
                    alt138=2;
                }
        
                }
                break;
            case LPAREN:
                {
                int LA138_2 = input.LA(2);
                if ( (synpred215_Java()) ) {
                    alt138=1;
                }
                else if ( (true) ) {
                    alt138=2;
                }
        
                }
                break;
            case CHAR_LITERAL:
            case NEW:
            case NUM_LITERAL:
            case STRING_LITERAL:
            case SUPER:
            case 135:
            case 137:
            case 140:
            case 144:
            case 148:
            case 151:
            case 157:
            case 159:
            case 161:
            case 166:
            case 171:
            case 176:
            case 178:
                {
                alt138=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 138, 0, input);
                throw nvae;
            }
            switch (alt138) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1435:3: ( lambdaParameters LAMBDA )=> lambdaParameters LAMBDA lambdaBody
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_lambdaParameters_in_lambdaExpression6733);
                    lambdaParameters341=lambdaParameters();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdaParameters341.getTree());
        
                    LAMBDA342=(Token)match(input,LAMBDA,FOLLOW_LAMBDA_in_lambdaExpression6735); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LAMBDA342_tree = (ASTTree)adaptor.create(LAMBDA342);
                    adaptor.addChild(root_0, LAMBDA342_tree);
                    }
        
                    pushFollow(FOLLOW_lambdaBody_in_lambdaExpression6737);
                    lambdaBody343=lambdaBody();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdaBody343.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1436:5: postfixExpression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_postfixExpression_in_lambdaExpression6743);
                    postfixExpression344=postfixExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, postfixExpression344.getTree());
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 97, lambdaExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "lambdaParameters"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1439:1: lambdaParameters : ( IDENT | LPAREN ( parameterDeclarationList | inferredFormalParameters )? RPAREN );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.lambdaParameters_return lambdaParameters() throws RecognitionException {
        JavaParser.lambdaParameters_return retval = new JavaParser.lambdaParameters_return();
        retval.start = input.LT(1);
        int lambdaParameters_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token IDENT345=null;
        Token LPAREN346=null;
        Token RPAREN349=null;
        ParserRuleReturnScope parameterDeclarationList347 =null;
        ParserRuleReturnScope inferredFormalParameters348 =null;
        
        ASTTree IDENT345_tree=null;
        ASTTree LPAREN346_tree=null;
        ASTTree RPAREN349_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 98) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1440:3: ( IDENT | LPAREN ( parameterDeclarationList | inferredFormalParameters )? RPAREN )
            int alt140=2;
            int LA140_0 = input.LA(1);
            if ( (LA140_0==IDENT) ) {
                alt140=1;
            }
            else if ( (LA140_0==LPAREN) ) {
                alt140=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 140, 0, input);
                throw nvae;
            }
        
            switch (alt140) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1440:5: IDENT
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    IDENT345=(Token)match(input,IDENT,FOLLOW_IDENT_in_lambdaParameters6758); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENT345_tree = (ASTTree)adaptor.create(IDENT345);
                    adaptor.addChild(root_0, IDENT345_tree);
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1441:5: LPAREN ( parameterDeclarationList | inferredFormalParameters )? RPAREN
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    LPAREN346=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_lambdaParameters6764); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN346_tree = (ASTTree)adaptor.create(LPAREN346);
                    adaptor.addChild(root_0, LPAREN346_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1441:12: ( parameterDeclarationList | inferredFormalParameters )?
                    int alt139=3;
                    int LA139_0 = input.LA(1);
                    if ( (LA139_0==AT||LA139_0==135||LA139_0==137||LA139_0==140||LA139_0==144||LA139_0==149||LA139_0==151||LA139_0==157||LA139_0==159||LA139_0==166) ) {
                        alt139=1;
                    }
                    else if ( (LA139_0==IDENT) ) {
                        int LA139_2 = input.LA(2);
                        if ( (LA139_2==AT||LA139_2==DOT||LA139_2==ELLIPSIS||LA139_2==IDENT||LA139_2==LBRACK||LA139_2==LT) ) {
                            alt139=1;
                        }
                        else if ( (LA139_2==COMMA||LA139_2==RPAREN) ) {
                            alt139=2;
                        }
                    }
                    switch (alt139) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1441:13: parameterDeclarationList
                            {
                            pushFollow(FOLLOW_parameterDeclarationList_in_lambdaParameters6767);
                            parameterDeclarationList347=parameterDeclarationList();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, parameterDeclarationList347.getTree());
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1441:40: inferredFormalParameters
                            {
                            pushFollow(FOLLOW_inferredFormalParameters_in_lambdaParameters6771);
                            inferredFormalParameters348=inferredFormalParameters();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, inferredFormalParameters348.getTree());
        
                            }
                            break;
        
                    }
        
                    RPAREN349=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_lambdaParameters6775); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN349_tree = (ASTTree)adaptor.create(RPAREN349);
                    adaptor.addChild(root_0, RPAREN349_tree);
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 98, lambdaParameters_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "inferredFormalParameters"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1444:1: inferredFormalParameters : IDENT ( COMMA IDENT )* ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.inferredFormalParameters_return inferredFormalParameters() throws RecognitionException {
        JavaParser.inferredFormalParameters_return retval = new JavaParser.inferredFormalParameters_return();
        retval.start = input.LT(1);
        int inferredFormalParameters_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token IDENT350=null;
        Token COMMA351=null;
        Token IDENT352=null;
        
        ASTTree IDENT350_tree=null;
        ASTTree COMMA351_tree=null;
        ASTTree IDENT352_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 99) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1445:3: ( IDENT ( COMMA IDENT )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1446:3: IDENT ( COMMA IDENT )*
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            IDENT350=(Token)match(input,IDENT,FOLLOW_IDENT_in_inferredFormalParameters6792); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENT350_tree = (ASTTree)adaptor.create(IDENT350);
            adaptor.addChild(root_0, IDENT350_tree);
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1446:9: ( COMMA IDENT )*
            loop141:
            while (true) {
                int alt141=2;
                int LA141_0 = input.LA(1);
                if ( (LA141_0==COMMA) ) {
                    alt141=1;
                }
        
                switch (alt141) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1446:10: COMMA IDENT
                    {
                    COMMA351=(Token)match(input,COMMA,FOLLOW_COMMA_in_inferredFormalParameters6795); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    COMMA351_tree = (ASTTree)adaptor.create(COMMA351);
                    adaptor.addChild(root_0, COMMA351_tree);
                    }
        
                    IDENT352=(Token)match(input,IDENT,FOLLOW_IDENT_in_inferredFormalParameters6797); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENT352_tree = (ASTTree)adaptor.create(IDENT352);
                    adaptor.addChild(root_0, IDENT352_tree);
                    }
        
                    }
                    break;
        
                default :
                    break loop141;
                }
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 99, inferredFormalParameters_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "lambdaBody"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1449:1: lambdaBody : ( expression | compoundStatement[true] );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.lambdaBody_return lambdaBody() throws RecognitionException {
        JavaParser.lambdaBody_return retval = new JavaParser.lambdaBody_return();
        retval.start = input.LT(1);
        int lambdaBody_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope expression353 =null;
        ParserRuleReturnScope compoundStatement354 =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 100) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1450:3: ( expression | compoundStatement[true] )
            int alt142=2;
            int LA142_0 = input.LA(1);
            if ( (LA142_0==AT||LA142_0==BNOT||LA142_0==CHAR_LITERAL||LA142_0==DEC||LA142_0==IDENT||LA142_0==INC||LA142_0==LNOT||LA142_0==LPAREN||LA142_0==MINUS||LA142_0==NEW||LA142_0==NUM_LITERAL||LA142_0==PLUS||(LA142_0 >= STRING_LITERAL && LA142_0 <= SUPER)||LA142_0==135||LA142_0==137||LA142_0==140||LA142_0==144||LA142_0==148||LA142_0==151||LA142_0==157||LA142_0==159||LA142_0==161||LA142_0==166||LA142_0==171||LA142_0==176||LA142_0==178) ) {
                alt142=1;
            }
            else if ( (LA142_0==LCURLY) ) {
                alt142=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 142, 0, input);
                throw nvae;
            }
        
            switch (alt142) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1451:3: expression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_expression_in_lambdaBody6815);
                    expression353=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression353.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1452:5: compoundStatement[true]
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_compoundStatement_in_lambdaBody6821);
                    compoundStatement354=compoundStatement(true);
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compoundStatement354.getTree());
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 100, lambdaBody_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "postfixExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1457:1: postfixExpression : primaryExpression ( DOT ( typeArguments )? IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT SUPER ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression |lb= LBRACK expression RBRACK )* ( INC | DEC )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.postfixExpression_return postfixExpression() throws RecognitionException {
        JavaParser.postfixExpression_return retval = new JavaParser.postfixExpression_return();
        retval.start = input.LT(1);
        int postfixExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lb=null;
        Token DOT356=null;
        Token IDENT358=null;
        Token LPAREN359=null;
        Token RPAREN361=null;
        Token DOT362=null;
        Token string_literal363=null;
        Token DOT364=null;
        Token SUPER365=null;
        Token LPAREN366=null;
        Token RPAREN368=null;
        Token DOT369=null;
        Token IDENT370=null;
        Token LPAREN371=null;
        Token RPAREN373=null;
        Token DOT374=null;
        Token RBRACK377=null;
        Token set378=null;
        ParserRuleReturnScope primaryExpression355 =null;
        ParserRuleReturnScope typeArguments357 =null;
        ParserRuleReturnScope argList360 =null;
        ParserRuleReturnScope argList367 =null;
        ParserRuleReturnScope argList372 =null;
        ParserRuleReturnScope newExpression375 =null;
        ParserRuleReturnScope expression376 =null;
        
        ASTTree lb_tree=null;
        ASTTree DOT356_tree=null;
        ASTTree IDENT358_tree=null;
        ASTTree LPAREN359_tree=null;
        ASTTree RPAREN361_tree=null;
        ASTTree DOT362_tree=null;
        ASTTree string_literal363_tree=null;
        ASTTree DOT364_tree=null;
        ASTTree SUPER365_tree=null;
        ASTTree LPAREN366_tree=null;
        ASTTree RPAREN368_tree=null;
        ASTTree DOT369_tree=null;
        ASTTree IDENT370_tree=null;
        ASTTree LPAREN371_tree=null;
        ASTTree RPAREN373_tree=null;
        ASTTree DOT374_tree=null;
        ASTTree RBRACK377_tree=null;
        ASTTree set378_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 101) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1458:3: ( primaryExpression ( DOT ( typeArguments )? IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT SUPER ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression |lb= LBRACK expression RBRACK )* ( INC | DEC )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1459:3: primaryExpression ( DOT ( typeArguments )? IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT SUPER ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression |lb= LBRACK expression RBRACK )* ( INC | DEC )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            pushFollow(FOLLOW_primaryExpression_in_postfixExpression6839);
            primaryExpression355=primaryExpression();
            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, primaryExpression355.getTree());
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1460:3: ( DOT ( typeArguments )? IDENT ( LPAREN argList RPAREN )? | DOT 'this' | DOT SUPER ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? ) | DOT newExpression |lb= LBRACK expression RBRACK )*
            loop147:
            while (true) {
                int alt147=6;
                int LA147_0 = input.LA(1);
                if ( (LA147_0==DOT) ) {
                    switch ( input.LA(2) ) {
                    case 171:
                        {
                        alt147=2;
                        }
                        break;
                    case SUPER:
                        {
                        alt147=3;
                        }
                        break;
                    case IDENT:
                    case LT:
                        {
                        alt147=1;
                        }
                        break;
                    case NEW:
                        {
                        alt147=4;
                        }
                        break;
                    }
                }
                else if ( (LA147_0==LBRACK) ) {
                    alt147=5;
                }
        
                switch (alt147) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1461:5: DOT ( typeArguments )? IDENT ( LPAREN argList RPAREN )?
                    {
                    DOT356=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression6849); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT356_tree = (ASTTree)adaptor.create(DOT356);
                    adaptor.addChild(root_0, DOT356_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1461:9: ( typeArguments )?
                    int alt143=2;
                    int LA143_0 = input.LA(1);
                    if ( (LA143_0==LT) ) {
                        alt143=1;
                    }
                    switch (alt143) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1461:9: typeArguments
                            {
                            pushFollow(FOLLOW_typeArguments_in_postfixExpression6851);
                            typeArguments357=typeArguments();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArguments357.getTree());
        
                            }
                            break;
        
                    }
        
                    IDENT358=(Token)match(input,IDENT,FOLLOW_IDENT_in_postfixExpression6854); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENT358_tree = (ASTTree)adaptor.create(IDENT358);
                    adaptor.addChild(root_0, IDENT358_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1461:30: ( LPAREN argList RPAREN )?
                    int alt144=2;
                    int LA144_0 = input.LA(1);
                    if ( (LA144_0==LPAREN) ) {
                        alt144=1;
                    }
                    switch (alt144) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1461:31: LPAREN argList RPAREN
                            {
                            LPAREN359=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_postfixExpression6857); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LPAREN359_tree = (ASTTree)adaptor.create(LPAREN359);
                            adaptor.addChild(root_0, LPAREN359_tree);
                            }
        
                            pushFollow(FOLLOW_argList_in_postfixExpression6859);
                            argList360=argList();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, argList360.getTree());
        
                            RPAREN361=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_postfixExpression6861); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            RPAREN361_tree = (ASTTree)adaptor.create(RPAREN361);
                            adaptor.addChild(root_0, RPAREN361_tree);
                            }
        
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1462:7: DOT 'this'
                    {
                    DOT362=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression6871); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT362_tree = (ASTTree)adaptor.create(DOT362);
                    adaptor.addChild(root_0, DOT362_tree);
                    }
        
                    string_literal363=(Token)match(input,171,FOLLOW_171_in_postfixExpression6873); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal363_tree = (ASTTree)adaptor.create(string_literal363);
                    adaptor.addChild(root_0, string_literal363_tree);
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1463:7: DOT SUPER ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? )
                    {
                    DOT364=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression6881); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT364_tree = (ASTTree)adaptor.create(DOT364);
                    adaptor.addChild(root_0, DOT364_tree);
                    }
        
                    SUPER365=(Token)match(input,SUPER,FOLLOW_SUPER_in_postfixExpression6883); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SUPER365_tree = (ASTTree)adaptor.create(SUPER365);
                    adaptor.addChild(root_0, SUPER365_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1464:5: ( LPAREN argList RPAREN | DOT IDENT ( LPAREN argList RPAREN )? )
                    int alt146=2;
                    int LA146_0 = input.LA(1);
                    if ( (LA146_0==LPAREN) ) {
                        alt146=1;
                    }
                    else if ( (LA146_0==DOT) ) {
                        alt146=2;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 146, 0, input);
                        throw nvae;
                    }
        
                    switch (alt146) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1465:7: LPAREN argList RPAREN
                            {
                            LPAREN366=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_postfixExpression6898); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            LPAREN366_tree = (ASTTree)adaptor.create(LPAREN366);
                            adaptor.addChild(root_0, LPAREN366_tree);
                            }
        
                            pushFollow(FOLLOW_argList_in_postfixExpression6900);
                            argList367=argList();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, argList367.getTree());
        
                            RPAREN368=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_postfixExpression6902); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            RPAREN368_tree = (ASTTree)adaptor.create(RPAREN368);
                            adaptor.addChild(root_0, RPAREN368_tree);
                            }
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1466:9: DOT IDENT ( LPAREN argList RPAREN )?
                            {
                            DOT369=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression6912); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DOT369_tree = (ASTTree)adaptor.create(DOT369);
                            adaptor.addChild(root_0, DOT369_tree);
                            }
        
                            IDENT370=(Token)match(input,IDENT,FOLLOW_IDENT_in_postfixExpression6914); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            IDENT370_tree = (ASTTree)adaptor.create(IDENT370);
                            adaptor.addChild(root_0, IDENT370_tree);
                            }
        
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1466:19: ( LPAREN argList RPAREN )?
                            int alt145=2;
                            int LA145_0 = input.LA(1);
                            if ( (LA145_0==LPAREN) ) {
                                alt145=1;
                            }
                            switch (alt145) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1466:20: LPAREN argList RPAREN
                                    {
                                    LPAREN371=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_postfixExpression6917); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    LPAREN371_tree = (ASTTree)adaptor.create(LPAREN371);
                                    adaptor.addChild(root_0, LPAREN371_tree);
                                    }
        
                                    pushFollow(FOLLOW_argList_in_postfixExpression6919);
                                    argList372=argList();
                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) adaptor.addChild(root_0, argList372.getTree());
        
                                    RPAREN373=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_postfixExpression6921); if (state.failed) return retval;
                                    if ( state.backtracking==0 ) {
                                    RPAREN373_tree = (ASTTree)adaptor.create(RPAREN373);
                                    adaptor.addChild(root_0, RPAREN373_tree);
                                    }
        
                                    }
                                    break;
        
                            }
        
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1468:7: DOT newExpression
                    {
                    DOT374=(Token)match(input,DOT,FOLLOW_DOT_in_postfixExpression6937); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT374_tree = (ASTTree)adaptor.create(DOT374);
                    adaptor.addChild(root_0, DOT374_tree);
                    }
        
                    pushFollow(FOLLOW_newExpression_in_postfixExpression6939);
                    newExpression375=newExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, newExpression375.getTree());
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1469:7: lb= LBRACK expression RBRACK
                    {
                    lb=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_postfixExpression6949); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    lb_tree = (ASTTree)adaptor.create(lb);
                    adaptor.addChild(root_0, lb_tree);
                    }
        
                    pushFollow(FOLLOW_expression_in_postfixExpression6951);
                    expression376=expression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression376.getTree());
        
                    RBRACK377=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_postfixExpression6953); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACK377_tree = (ASTTree)adaptor.create(RBRACK377);
                    adaptor.addChild(root_0, RBRACK377_tree);
                    }
        
                    }
                    break;
        
                default :
                    break loop147;
                }
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1473:3: ( INC | DEC )?
            int alt148=2;
            int LA148_0 = input.LA(1);
            if ( (LA148_0==DEC||LA148_0==INC) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                    {
                    set378=input.LT(1);
                    if ( input.LA(1)==DEC||input.LA(1)==INC ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (ASTTree)adaptor.create(set378));
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 101, postfixExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "primaryExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1478:1: primaryExpression : ( identPrimary ( options {greedy=true; } : DOT ^ 'class' )? | constant | 'true' | 'false' | 'null' | newExpression | 'this' | SUPER | LPAREN ! assignmentExpression RPAREN !| ( builtInType | voidkw ) (lbt= LBRACK ^ RBRACK !)* DOT ^ 'class' );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.primaryExpression_return primaryExpression() throws RecognitionException {
        JavaParser.primaryExpression_return retval = new JavaParser.primaryExpression_return();
        retval.start = input.LT(1);
        int primaryExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lbt=null;
        Token DOT380=null;
        Token string_literal381=null;
        Token string_literal383=null;
        Token string_literal384=null;
        Token string_literal385=null;
        Token string_literal387=null;
        Token SUPER388=null;
        Token LPAREN389=null;
        Token RPAREN391=null;
        Token RBRACK394=null;
        Token DOT395=null;
        Token string_literal396=null;
        ParserRuleReturnScope identPrimary379 =null;
        ParserRuleReturnScope constant382 =null;
        ParserRuleReturnScope newExpression386 =null;
        ParserRuleReturnScope assignmentExpression390 =null;
        ParserRuleReturnScope builtInType392 =null;
        ParserRuleReturnScope voidkw393 =null;
        
        ASTTree lbt_tree=null;
        ASTTree DOT380_tree=null;
        ASTTree string_literal381_tree=null;
        ASTTree string_literal383_tree=null;
        ASTTree string_literal384_tree=null;
        ASTTree string_literal385_tree=null;
        ASTTree string_literal387_tree=null;
        ASTTree SUPER388_tree=null;
        ASTTree LPAREN389_tree=null;
        ASTTree RPAREN391_tree=null;
        ASTTree RBRACK394_tree=null;
        ASTTree DOT395_tree=null;
        ASTTree string_literal396_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 102) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1479:3: ( identPrimary ( options {greedy=true; } : DOT ^ 'class' )? | constant | 'true' | 'false' | 'null' | newExpression | 'this' | SUPER | LPAREN ! assignmentExpression RPAREN !| ( builtInType | voidkw ) (lbt= LBRACK ^ RBRACK !)* DOT ^ 'class' )
            int alt152=10;
            switch ( input.LA(1) ) {
            case IDENT:
                {
                alt152=1;
                }
                break;
            case CHAR_LITERAL:
            case NUM_LITERAL:
            case STRING_LITERAL:
                {
                alt152=2;
                }
                break;
            case 176:
                {
                alt152=3;
                }
                break;
            case 148:
                {
                alt152=4;
                }
                break;
            case 161:
                {
                alt152=5;
                }
                break;
            case NEW:
                {
                alt152=6;
                }
                break;
            case 171:
                {
                alt152=7;
                }
                break;
            case SUPER:
                {
                alt152=8;
                }
                break;
            case LPAREN:
                {
                alt152=9;
                }
                break;
            case 135:
            case 137:
            case 140:
            case 144:
            case 151:
            case 157:
            case 159:
            case 166:
            case 178:
                {
                alt152=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 152, 0, input);
                throw nvae;
            }
            switch (alt152) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1479:5: identPrimary ( options {greedy=true; } : DOT ^ 'class' )?
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_identPrimary_in_primaryExpression6992);
                    identPrimary379=identPrimary();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, identPrimary379.getTree());
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1479:18: ( options {greedy=true; } : DOT ^ 'class' )?
                    int alt149=2;
                    int LA149_0 = input.LA(1);
                    if ( (LA149_0==DOT) ) {
                        int LA149_1 = input.LA(2);
                        if ( (LA149_1==141) ) {
                            alt149=1;
                        }
                    }
                    switch (alt149) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1479:43: DOT ^ 'class'
                            {
                            DOT380=(Token)match(input,DOT,FOLLOW_DOT_in_primaryExpression7003); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            DOT380_tree = (ASTTree)adaptor.create(DOT380);
                            root_0 = (ASTTree)adaptor.becomeRoot(DOT380_tree, root_0);
                            }
        
                            string_literal381=(Token)match(input,141,FOLLOW_141_in_primaryExpression7006); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal381_tree = (ASTTree)adaptor.create(string_literal381);
                            adaptor.addChild(root_0, string_literal381_tree);
                            }
        
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1480:5: constant
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_constant_in_primaryExpression7014);
                    constant382=constant();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constant382.getTree());
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1481:5: 'true'
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    string_literal383=(Token)match(input,176,FOLLOW_176_in_primaryExpression7020); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal383_tree = (ASTTree)adaptor.create(string_literal383);
                    adaptor.addChild(root_0, string_literal383_tree);
                    }
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1482:5: 'false'
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    string_literal384=(Token)match(input,148,FOLLOW_148_in_primaryExpression7026); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal384_tree = (ASTTree)adaptor.create(string_literal384);
                    adaptor.addChild(root_0, string_literal384_tree);
                    }
        
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1483:5: 'null'
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    string_literal385=(Token)match(input,161,FOLLOW_161_in_primaryExpression7032); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal385_tree = (ASTTree)adaptor.create(string_literal385);
                    adaptor.addChild(root_0, string_literal385_tree);
                    }
        
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1484:5: newExpression
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_newExpression_in_primaryExpression7038);
                    newExpression386=newExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, newExpression386.getTree());
        
                    }
                    break;
                case 7 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1485:5: 'this'
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    string_literal387=(Token)match(input,171,FOLLOW_171_in_primaryExpression7044); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal387_tree = (ASTTree)adaptor.create(string_literal387);
                    adaptor.addChild(root_0, string_literal387_tree);
                    }
        
                    }
                    break;
                case 8 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1486:5: SUPER
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    SUPER388=(Token)match(input,SUPER,FOLLOW_SUPER_in_primaryExpression7050); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SUPER388_tree = (ASTTree)adaptor.create(SUPER388);
                    adaptor.addChild(root_0, SUPER388_tree);
                    }
        
                    }
                    break;
                case 9 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1487:5: LPAREN ! assignmentExpression RPAREN !
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    LPAREN389=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_primaryExpression7056); if (state.failed) return retval;
                    pushFollow(FOLLOW_assignmentExpression_in_primaryExpression7059);
                    assignmentExpression390=assignmentExpression();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentExpression390.getTree());
        
                    RPAREN391=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_primaryExpression7061); if (state.failed) return retval;
                    }
                    break;
                case 10 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1489:5: ( builtInType | voidkw ) (lbt= LBRACK ^ RBRACK !)* DOT ^ 'class'
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1489:5: ( builtInType | voidkw )
                    int alt150=2;
                    int LA150_0 = input.LA(1);
                    if ( (LA150_0==135||LA150_0==137||LA150_0==140||LA150_0==144||LA150_0==151||LA150_0==157||LA150_0==159||LA150_0==166) ) {
                        alt150=1;
                    }
                    else if ( (LA150_0==178) ) {
                        alt150=2;
                    }
        
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 150, 0, input);
                        throw nvae;
                    }
        
                    switch (alt150) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1489:6: builtInType
                            {
                            pushFollow(FOLLOW_builtInType_in_primaryExpression7074);
                            builtInType392=builtInType();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, builtInType392.getTree());
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1489:18: voidkw
                            {
                            pushFollow(FOLLOW_voidkw_in_primaryExpression7076);
                            voidkw393=voidkw();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, voidkw393.getTree());
        
                            }
                            break;
        
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1490:5: (lbt= LBRACK ^ RBRACK !)*
                    loop151:
                    while (true) {
                        int alt151=2;
                        int LA151_0 = input.LA(1);
                        if ( (LA151_0==LBRACK) ) {
                            alt151=1;
                        }
        
                        switch (alt151) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1490:6: lbt= LBRACK ^ RBRACK !
                            {
                            lbt=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_primaryExpression7086); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            lbt_tree = (ASTTree)adaptor.create(lbt);
                            root_0 = (ASTTree)adaptor.becomeRoot(lbt_tree, root_0);
                            }
        
                            if ( state.backtracking==0 ) {
                                           lbt.setType(ARRAY_DECLARATOR);
                                          }
                            RBRACK394=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_primaryExpression7110); if (state.failed) return retval;
                            }
                            break;
        
                        default :
                            break loop151;
                        }
                    }
        
                    DOT395=(Token)match(input,DOT,FOLLOW_DOT_in_primaryExpression7115); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT395_tree = (ASTTree)adaptor.create(DOT395);
                    root_0 = (ASTTree)adaptor.becomeRoot(DOT395_tree, root_0);
                    }
        
                    string_literal396=(Token)match(input,141,FOLLOW_141_in_primaryExpression7118); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal396_tree = (ASTTree)adaptor.create(string_literal396);
                    adaptor.addChild(root_0, string_literal396_tree);
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 102, primaryExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "identPrimary"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1501:1: identPrimary : IDENT ( options {greedy=true; } : DOT ^ IDENT )* ( options {greedy=true; } : (lp= LPAREN ^ argList RPAREN !) | ( options {greedy=true; } :lbc= LBRACK ^ RBRACK !)+ )? ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.identPrimary_return identPrimary() throws RecognitionException {
        JavaParser.identPrimary_return retval = new JavaParser.identPrimary_return();
        retval.start = input.LT(1);
        int identPrimary_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lp=null;
        Token lbc=null;
        Token IDENT397=null;
        Token DOT398=null;
        Token IDENT399=null;
        Token RPAREN401=null;
        Token RBRACK402=null;
        ParserRuleReturnScope argList400 =null;
        
        ASTTree lp_tree=null;
        ASTTree lbc_tree=null;
        ASTTree IDENT397_tree=null;
        ASTTree DOT398_tree=null;
        ASTTree IDENT399_tree=null;
        ASTTree RPAREN401_tree=null;
        ASTTree RBRACK402_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 103) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1502:3: ( IDENT ( options {greedy=true; } : DOT ^ IDENT )* ( options {greedy=true; } : (lp= LPAREN ^ argList RPAREN !) | ( options {greedy=true; } :lbc= LBRACK ^ RBRACK !)+ )? )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1503:3: IDENT ( options {greedy=true; } : DOT ^ IDENT )* ( options {greedy=true; } : (lp= LPAREN ^ argList RPAREN !) | ( options {greedy=true; } :lbc= LBRACK ^ RBRACK !)+ )?
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            IDENT397=(Token)match(input,IDENT,FOLLOW_IDENT_in_identPrimary7135); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IDENT397_tree = (ASTTree)adaptor.create(IDENT397);
            adaptor.addChild(root_0, IDENT397_tree);
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1503:9: ( options {greedy=true; } : DOT ^ IDENT )*
            loop153:
            while (true) {
                int alt153=2;
                int LA153_0 = input.LA(1);
                if ( (LA153_0==DOT) ) {
                    int LA153_2 = input.LA(2);
                    if ( (LA153_2==IDENT) ) {
                        int LA153_3 = input.LA(3);
                        if ( (synpred244_Java()) ) {
                            alt153=1;
                        }
        
                    }
        
                }
        
                switch (alt153) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1503:34: DOT ^ IDENT
                    {
                    DOT398=(Token)match(input,DOT,FOLLOW_DOT_in_identPrimary7146); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    DOT398_tree = (ASTTree)adaptor.create(DOT398);
                    root_0 = (ASTTree)adaptor.becomeRoot(DOT398_tree, root_0);
                    }
        
                    IDENT399=(Token)match(input,IDENT,FOLLOW_IDENT_in_identPrimary7149); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IDENT399_tree = (ASTTree)adaptor.create(IDENT399);
                    adaptor.addChild(root_0, IDENT399_tree);
                    }
        
                    }
                    break;
        
                default :
                    break loop153;
                }
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1504:3: ( options {greedy=true; } : (lp= LPAREN ^ argList RPAREN !) | ( options {greedy=true; } :lbc= LBRACK ^ RBRACK !)+ )?
            int alt155=3;
            int LA155_0 = input.LA(1);
            if ( (LA155_0==LPAREN) ) {
                alt155=1;
            }
            else if ( (LA155_0==LBRACK) ) {
                int LA155_2 = input.LA(2);
                if ( (LA155_2==RBRACK) ) {
                    alt155=2;
                }
            }
            switch (alt155) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1504:29: (lp= LPAREN ^ argList RPAREN !)
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1504:29: (lp= LPAREN ^ argList RPAREN !)
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1504:30: lp= LPAREN ^ argList RPAREN !
                    {
                    lp=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_identPrimary7168); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    lp_tree = (ASTTree)adaptor.create(lp);
                    root_0 = (ASTTree)adaptor.becomeRoot(lp_tree, root_0);
                    }
        
                    if ( state.backtracking==0 ) {
                                                            lp.setType(METHOD_CALL);
                                                           }
                    pushFollow(FOLLOW_argList_in_identPrimary7219);
                    argList400=argList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, argList400.getTree());
        
                    RPAREN401=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_identPrimary7221); if (state.failed) return retval;
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1509:7: ( options {greedy=true; } :lbc= LBRACK ^ RBRACK !)+
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1509:7: ( options {greedy=true; } :lbc= LBRACK ^ RBRACK !)+
                    int cnt154=0;
                    loop154:
                    while (true) {
                        int alt154=2;
                        int LA154_0 = input.LA(1);
                        if ( (LA154_0==LBRACK) ) {
                            int LA154_2 = input.LA(2);
                            if ( (LA154_2==RBRACK) ) {
                                alt154=1;
                            }
        
                        }
        
                        switch (alt154) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1509:32: lbc= LBRACK ^ RBRACK !
                            {
                            lbc=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_identPrimary7242); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            lbc_tree = (ASTTree)adaptor.create(lbc);
                            root_0 = (ASTTree)adaptor.becomeRoot(lbc_tree, root_0);
                            }
        
                            if ( state.backtracking==0 ) {
                                                                       lbc.setType(ARRAY_DECLARATOR);
                                                                      }
                            RBRACK402=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_identPrimary7296); if (state.failed) return retval;
                            }
                            break;
        
                        default :
                            if ( cnt154 >= 1 ) break loop154;
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            EarlyExitException eee = new EarlyExitException(154, input);
                            throw eee;
                        }
                        cnt154++;
                    }
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 103, identPrimary_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "newExpression"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1517:1: newExpression : NEW ( classOrInterfaceType | ( annotation )* builtInType ) ( LPAREN argList RPAREN ( classBlock[false] )? | newArrayDeclarator ( arrayInitializer )? ) ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.newExpression_return newExpression() throws RecognitionException {
        JavaParser.newExpression_return retval = new JavaParser.newExpression_return();
        retval.start = input.LT(1);
        int newExpression_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token NEW403=null;
        Token LPAREN407=null;
        Token RPAREN409=null;
        ParserRuleReturnScope classOrInterfaceType404 =null;
        ParserRuleReturnScope annotation405 =null;
        ParserRuleReturnScope builtInType406 =null;
        ParserRuleReturnScope argList408 =null;
        ParserRuleReturnScope classBlock410 =null;
        ParserRuleReturnScope newArrayDeclarator411 =null;
        ParserRuleReturnScope arrayInitializer412 =null;
        
        ASTTree NEW403_tree=null;
        ASTTree LPAREN407_tree=null;
        ASTTree RPAREN409_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 104) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1518:3: ( NEW ( classOrInterfaceType | ( annotation )* builtInType ) ( LPAREN argList RPAREN ( classBlock[false] )? | newArrayDeclarator ( arrayInitializer )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1519:3: NEW ( classOrInterfaceType | ( annotation )* builtInType ) ( LPAREN argList RPAREN ( classBlock[false] )? | newArrayDeclarator ( arrayInitializer )? )
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            NEW403=(Token)match(input,NEW,FOLLOW_NEW_in_newExpression7319); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            NEW403_tree = (ASTTree)adaptor.create(NEW403);
            adaptor.addChild(root_0, NEW403_tree);
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1520:3: ( classOrInterfaceType | ( annotation )* builtInType )
            int alt157=2;
            switch ( input.LA(1) ) {
            case AT:
                {
                int LA157_1 = input.LA(2);
                if ( (synpred248_Java()) ) {
                    alt157=1;
                }
                else if ( (true) ) {
                    alt157=2;
                }
        
                }
                break;
            case IDENT:
                {
                alt157=1;
                }
                break;
            case 135:
            case 137:
            case 140:
            case 144:
            case 151:
            case 157:
            case 159:
            case 166:
                {
                alt157=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 157, 0, input);
                throw nvae;
            }
            switch (alt157) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1521:5: classOrInterfaceType
                    {
                    pushFollow(FOLLOW_classOrInterfaceType_in_newExpression7329);
                    classOrInterfaceType404=classOrInterfaceType();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType404.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1522:7: ( annotation )* builtInType
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1522:7: ( annotation )*
                    loop156:
                    while (true) {
                        int alt156=2;
                        int LA156_0 = input.LA(1);
                        if ( (LA156_0==AT) ) {
                            alt156=1;
                        }
        
                        switch (alt156) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1522:7: annotation
                            {
                            pushFollow(FOLLOW_annotation_in_newExpression7337);
                            annotation405=annotation();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation405.getTree());
        
                            }
                            break;
        
                        default :
                            break loop156;
                        }
                    }
        
                    pushFollow(FOLLOW_builtInType_in_newExpression7340);
                    builtInType406=builtInType();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, builtInType406.getTree());
        
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1524:3: ( LPAREN argList RPAREN ( classBlock[false] )? | newArrayDeclarator ( arrayInitializer )? )
            int alt160=2;
            int LA160_0 = input.LA(1);
            if ( (LA160_0==LPAREN) ) {
                alt160=1;
            }
            else if ( (LA160_0==LBRACK) ) {
                alt160=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 160, 0, input);
                throw nvae;
            }
        
            switch (alt160) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1525:5: LPAREN argList RPAREN ( classBlock[false] )?
                    {
                    LPAREN407=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_newExpression7354); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN407_tree = (ASTTree)adaptor.create(LPAREN407);
                    adaptor.addChild(root_0, LPAREN407_tree);
                    }
        
                    pushFollow(FOLLOW_argList_in_newExpression7356);
                    argList408=argList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, argList408.getTree());
        
                    RPAREN409=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_newExpression7358); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN409_tree = (ASTTree)adaptor.create(RPAREN409);
                    adaptor.addChild(root_0, RPAREN409_tree);
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1525:27: ( classBlock[false] )?
                    int alt158=2;
                    int LA158_0 = input.LA(1);
                    if ( (LA158_0==LCURLY) ) {
                        alt158=1;
                    }
                    switch (alt158) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1525:27: classBlock[false]
                            {
                            pushFollow(FOLLOW_classBlock_in_newExpression7360);
                            classBlock410=classBlock(false);
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, classBlock410.getTree());
        
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1526:7: newArrayDeclarator ( arrayInitializer )?
                    {
                    pushFollow(FOLLOW_newArrayDeclarator_in_newExpression7370);
                    newArrayDeclarator411=newArrayDeclarator();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, newArrayDeclarator411.getTree());
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1526:26: ( arrayInitializer )?
                    int alt159=2;
                    int LA159_0 = input.LA(1);
                    if ( (LA159_0==LCURLY) ) {
                        alt159=1;
                    }
                    switch (alt159) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1526:26: arrayInitializer
                            {
                            pushFollow(FOLLOW_arrayInitializer_in_newExpression7372);
                            arrayInitializer412=arrayInitializer();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayInitializer412.getTree());
        
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 104, newExpression_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "argList"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1531:1: argList : (el= expressionList | -> ^( ELIST ) );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.argList_return argList() throws RecognitionException {
        JavaParser.argList_return retval = new JavaParser.argList_return();
        retval.start = input.LT(1);
        int argList_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        ParserRuleReturnScope el =null;
        
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 105) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1532:3: (el= expressionList | -> ^( ELIST ) )
            int alt161=2;
            int LA161_0 = input.LA(1);
            if ( (LA161_0==AT||LA161_0==BNOT||LA161_0==CHAR_LITERAL||LA161_0==DEC||LA161_0==IDENT||LA161_0==INC||LA161_0==LNOT||LA161_0==LPAREN||LA161_0==MINUS||LA161_0==NEW||LA161_0==NUM_LITERAL||LA161_0==PLUS||(LA161_0 >= STRING_LITERAL && LA161_0 <= SUPER)||LA161_0==135||LA161_0==137||LA161_0==140||LA161_0==144||LA161_0==148||LA161_0==151||LA161_0==157||LA161_0==159||LA161_0==161||LA161_0==166||LA161_0==171||LA161_0==176||LA161_0==178) ) {
                alt161=1;
            }
            else if ( (LA161_0==RPAREN) ) {
                alt161=2;
            }
        
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 161, 0, input);
                throw nvae;
            }
        
            switch (alt161) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1533:3: el= expressionList
                    {
                    root_0 = (ASTTree)adaptor.nil();
        
        
                    pushFollow(FOLLOW_expressionList_in_argList7398);
                    el=expressionList();
                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, el.getTree());
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1536:5: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);
        
                    root_0 = (ASTTree)adaptor.nil();
                    // 1536:5: -> ^( ELIST )
                    {
                        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1536:8: ^( ELIST )
                        {
                        ASTTree root_1 = (ASTTree)adaptor.nil();
                        root_1 = (ASTTree)adaptor.becomeRoot((ASTTree)adaptor.create(ELIST, "ELIST"), root_1);
                        adaptor.addChild(root_0, root_1);
                        }
        
                    }
        
        
                    retval.tree = root_0;
                    }
        
                    }
                    break;
        
            }
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 105, argList_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "newArrayDeclarator"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1539:1: newArrayDeclarator : ( options {greedy=true; } :lb= LBRACK ^ ( expression )? RBRACK !)+ ;
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.newArrayDeclarator_return newArrayDeclarator() throws RecognitionException {
        JavaParser.newArrayDeclarator_return retval = new JavaParser.newArrayDeclarator_return();
        retval.start = input.LT(1);
        int newArrayDeclarator_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token lb=null;
        Token RBRACK414=null;
        ParserRuleReturnScope expression413 =null;
        
        ASTTree lb_tree=null;
        ASTTree RBRACK414_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 106) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1540:3: ( ( options {greedy=true; } :lb= LBRACK ^ ( expression )? RBRACK !)+ )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1541:3: ( options {greedy=true; } :lb= LBRACK ^ ( expression )? RBRACK !)+
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1541:3: ( options {greedy=true; } :lb= LBRACK ^ ( expression )? RBRACK !)+
            int cnt163=0;
            loop163:
            while (true) {
                int alt163=2;
                int LA163_0 = input.LA(1);
                if ( (LA163_0==LBRACK) ) {
                    int LA163_37 = input.LA(2);
                    if ( (synpred255_Java()) ) {
                        alt163=1;
                    }
        
                }
        
                switch (alt163) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1547:27: lb= LBRACK ^ ( expression )? RBRACK !
                    {
                    lb=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_newArrayDeclarator7498); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    lb_tree = (ASTTree)adaptor.create(lb);
                    root_0 = (ASTTree)adaptor.becomeRoot(lb_tree, root_0);
                    }
        
                    if ( state.backtracking==0 ) {
                                                          lb.setType(ARRAY_DECLARATOR);
                                                         }
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1551:5: ( expression )?
                    int alt162=2;
                    int LA162_0 = input.LA(1);
                    if ( (LA162_0==AT||LA162_0==BNOT||LA162_0==CHAR_LITERAL||LA162_0==DEC||LA162_0==IDENT||LA162_0==INC||LA162_0==LNOT||LA162_0==LPAREN||LA162_0==MINUS||LA162_0==NEW||LA162_0==NUM_LITERAL||LA162_0==PLUS||(LA162_0 >= STRING_LITERAL && LA162_0 <= SUPER)||LA162_0==135||LA162_0==137||LA162_0==140||LA162_0==144||LA162_0==148||LA162_0==151||LA162_0==157||LA162_0==159||LA162_0==161||LA162_0==166||LA162_0==171||LA162_0==176||LA162_0==178) ) {
                        alt162=1;
                    }
                    switch (alt162) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1551:6: expression
                            {
                            pushFollow(FOLLOW_expression_in_newArrayDeclarator7546);
                            expression413=expression();
                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression413.getTree());
        
                            }
                            break;
        
                    }
        
                    RBRACK414=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_newArrayDeclarator7550); if (state.failed) return retval;
                    }
                    break;
        
                default :
                    if ( cnt163 >= 1 ) break loop163;
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    EarlyExitException eee = new EarlyExitException(163, input);
                    throw eee;
                }
                cnt163++;
            }
        
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 106, newArrayDeclarator_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR start "constant"
// com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1554:1: constant : ( NUM_LITERAL | CHAR_LITERAL | STRING_LITERAL );
    public final org.modelio.module.javadesigner.reverse.antlr.JavaParser.constant_return constant() throws RecognitionException {
        JavaParser.constant_return retval = new JavaParser.constant_return();
        retval.start = input.LT(1);
        int constant_StartIndex = input.index();
        
        ASTTree root_0 = null;
        
        Token set415=null;
        
        ASTTree set415_tree=null;
        
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 107) ) { return retval; }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1555:3: ( NUM_LITERAL | CHAR_LITERAL | STRING_LITERAL )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            root_0 = (ASTTree)adaptor.nil();
        
        
            set415=input.LT(1);
            if ( input.LA(1)==CHAR_LITERAL||input.LA(1)==NUM_LITERAL||input.LA(1)==STRING_LITERAL ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (ASTTree)adaptor.create(set415));
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }
            }
        
            retval.stop = input.LT(-1);
        
            if ( state.backtracking==0 ) {
            retval.tree = (ASTTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
            retval.tree = (ASTTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
        }
        finally {
            // do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 107, constant_StartIndex); }
        
        }
        return retval;
    }

// $ANTLR end "constant"
// $ANTLR start synpred1_Java
    public final void synpred1_Java_fragment() throws RecognitionException {
        ParserRuleReturnScope pd =null;
        
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:5: (pd= packageDefinition )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:319:5: pd= packageDefinition
        {
        pushFollow(FOLLOW_packageDefinition_in_synpred1_Java459);
        pd=packageDefinition();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred1_Java
// $ANTLR start synpred9_Java
    public final void synpred9_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:365:9: ( annotationTypeDefinition[null] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:365:10: annotationTypeDefinition[null]
        {
        pushFollow(FOLLOW_annotationTypeDefinition_in_synpred9_Java692);
        annotationTypeDefinition(null);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred9_Java
// $ANTLR start synpred10_Java
    public final void synpred10_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:366:9: ( annotationMethod[null] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:366:10: annotationMethod[null]
        {
        pushFollow(FOLLOW_annotationMethod_in_synpred10_Java713);
        annotationMethod(null);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred10_Java
// $ANTLR start synpred13_Java
    public final void synpred13_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:4: ( ( annotation )* modifier )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:5: ( annotation )* modifier
        {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:5: ( annotation )*
        loop164:
        while (true) {
            int alt164=2;
            int LA164_0 = input.LA(1);
            if ( (LA164_0==AT) ) {
                alt164=1;
            }
        
            switch (alt164) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:5: annotation
                {
                pushFollow(FOLLOW_annotation_in_synpred13_Java931);
                annotation();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
            default :
                break loop164;
            }
        }
        
        pushFollow(FOLLOW_modifier_in_synpred13_Java934);
        modifier();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred13_Java
// $ANTLR start synpred15_Java
    public final void synpred15_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: ( ( annotation )* modifier )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: ( annotation )* modifier
        {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: ( annotation )*
        loop165:
        while (true) {
            int alt165=2;
            int LA165_0 = input.LA(1);
            if ( (LA165_0==AT) ) {
                alt165=1;
            }
        
            switch (alt165) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:408:31: annotation
                {
                pushFollow(FOLLOW_annotation_in_synpred15_Java940);
                annotation();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
            default :
                break loop165;
            }
        }
        
        pushFollow(FOLLOW_modifier_in_synpred15_Java943);
        modifier();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred15_Java
// $ANTLR start synpred16_Java
    public final void synpred16_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:412:4: ( annotation )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:412:4: annotation
        {
        pushFollow(FOLLOW_annotation_in_synpred16_Java1243);
        annotation();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred16_Java
// $ANTLR start synpred21_Java
    public final void synpred21_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:455:5: ( annotation )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:455:5: annotation
        {
        pushFollow(FOLLOW_annotation_in_synpred21_Java1486);
        annotation();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred21_Java
// $ANTLR start synpred22_Java
    public final void synpred22_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:456:5: ( conditionalExpression[true] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:456:5: conditionalExpression[true]
        {
        pushFollow(FOLLOW_conditionalExpression_in_synpred22_Java1492);
        conditionalExpression(true);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred22_Java
// $ANTLR start synpred44_Java
    public final void synpred44_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:587:3: ( classTypeSpec )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:587:3: classTypeSpec
        {
        pushFollow(FOLLOW_classTypeSpec_in_synpred44_Java2183);
        classTypeSpec();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred44_Java
// $ANTLR start synpred51_Java
    public final void synpred51_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:21: ( typeArguments )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:629:21: typeArguments
        {
        pushFollow(FOLLOW_typeArguments_in_synpred51_Java2428);
        typeArguments();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred51_Java
// $ANTLR start synpred85_Java
    public final void synpred85_Java_fragment() throws RecognitionException {
        Token q=null;
        Token m=null;
        
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:5: ( ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? ) )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:5: ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? )
        {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:5: ( ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )? )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:6: ( annotation )* q= QUESTION (m= ( 'extends' | SUPER ) typeSpec )?
        {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:6: ( annotation )*
        loop172:
        while (true) {
            int alt172=2;
            int LA172_0 = input.LA(1);
            if ( (LA172_0==AT) ) {
                alt172=1;
            }
        
            switch (alt172) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:6: annotation
                {
                pushFollow(FOLLOW_annotation_in_synpred85_Java2955);
                annotation();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
            default :
                break loop172;
            }
        }
        
        q=(Token)match(input,QUESTION,FOLLOW_QUESTION_in_synpred85_Java2960); if (state.failed) return;
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:29: (m= ( 'extends' | SUPER ) typeSpec )?
        int alt173=2;
        int LA173_0 = input.LA(1);
        if ( (LA173_0==SUPER||LA173_0==147) ) {
            alt173=1;
        }
        switch (alt173) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:732:31: m= ( 'extends' | SUPER ) typeSpec
                {
                m=input.LT(1);
                if ( input.LA(1)==SUPER||input.LA(1)==147 ) {
                    input.consume();
                    state.errorRecovery=false;
                    state.failed=false;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return;}
                    MismatchedSetException mse = new MismatchedSetException(null,input);
                    throw mse;
                }
                pushFollow(FOLLOW_typeSpec_in_synpred85_Java2972);
                typeSpec();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
        }
        
        }
        
        }
    }

// $ANTLR end synpred85_Java
// $ANTLR start synpred92_Java
    public final void synpred92_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:810:9: ( attributeDefinitions[null] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:810:10: attributeDefinitions[null]
        {
        pushFollow(FOLLOW_attributeDefinitions_in_synpred92_Java3328);
        attributeDefinitions(null);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred92_Java
// $ANTLR start synpred93_Java
    public final void synpred93_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:811:9: ( constructorDefinition[null] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:811:10: constructorDefinition[null]
        {
        pushFollow(FOLLOW_constructorDefinition_in_synpred93_Java3349);
        constructorDefinition(null);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred93_Java
// $ANTLR start synpred94_Java
    public final void synpred94_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:812:9: ( operationDefinition[null] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:812:10: operationDefinition[null]
        {
        pushFollow(FOLLOW_operationDefinition_in_synpred94_Java3370);
        operationDefinition(null);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred94_Java
// $ANTLR start synpred117_Java
    public final void synpred117_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:5: ( annotation )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:949:5: annotation
        {
        pushFollow(FOLLOW_annotation_in_synpred117_Java4180);
        annotation();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred117_Java
// $ANTLR start synpred124_Java
    public final void synpred124_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1008:5: ( declaration )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1008:6: declaration
        {
        pushFollow(FOLLOW_declaration_in_synpred124_Java4457);
        declaration();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred124_Java
// $ANTLR start synpred125_Java
    public final void synpred125_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1014:5: ( expression SEMI )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1014:5: expression SEMI
        {
        pushFollow(FOLLOW_expression_in_synpred125_Java4488);
        expression();
        state._fsp--;
        if (state.failed) return;
        
        match(input,SEMI,FOLLOW_SEMI_in_synpred125_Java4490); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred125_Java
// $ANTLR start synpred126_Java
    public final void synpred126_Java_fragment() throws RecognitionException {
        ParserRuleReturnScope ama =null;
        
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1018:5: (ama= annotmodannot classDefinition[$ama.tree] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1018:5: ama= annotmodannot classDefinition[$ama.tree]
        {
        pushFollow(FOLLOW_annotmodannot_in_synpred126_Java4510);
        ama=annotmodannot();
        state._fsp--;
        if (state.failed) return;
        
        pushFollow(FOLLOW_classDefinition_in_synpred126_Java4512);
        classDefinition((ama!=null?((ASTTree)ama.getTree()):null));
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred126_Java
// $ANTLR start synpred127_Java
    public final void synpred127_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1022:5: ( IDENT COLON statement )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1022:5: IDENT COLON statement
        {
        match(input,IDENT,FOLLOW_IDENT_in_synpred127_Java4531); if (state.failed) return;
        
        match(input,COLON,FOLLOW_COLON_in_synpred127_Java4533); if (state.failed) return;
        
        pushFollow(FOLLOW_statement_in_synpred127_Java4535);
        statement();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred127_Java
// $ANTLR start synpred128_Java
    public final void synpred128_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1027:21: ( 'else' )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1027:22: 'else'
        {
        match(input,145,FOLLOW_145_in_synpred128_Java4585); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred128_Java
// $ANTLR start synpred130_Java
    public final void synpred130_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1034:5: ( parameterDeclaration COLON )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1034:6: parameterDeclaration COLON
        {
        pushFollow(FOLLOW_parameterDeclaration_in_synpred130_Java4642);
        parameterDeclaration();
        state._fsp--;
        if (state.failed) return;
        
        match(input,COLON,FOLLOW_COLON_in_synpred130_Java4644); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred130_Java
// $ANTLR start synpred143_Java
    public final void synpred143_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1074:5: ( 'synchronized' LPAREN expression RPAREN compoundStatement[false] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1074:5: 'synchronized' LPAREN expression RPAREN compoundStatement[false]
        {
        match(input,170,FOLLOW_170_in_synpred143_Java4955); if (state.failed) return;
        
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred143_Java4957); if (state.failed) return;
        
        pushFollow(FOLLOW_expression_in_synpred143_Java4959);
        expression();
        state._fsp--;
        if (state.failed) return;
        
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred143_Java4961); if (state.failed) return;
        
        pushFollow(FOLLOW_compoundStatement_in_synpred143_Java4963);
        compoundStatement(false);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred143_Java
// $ANTLR start synpred146_Java
    public final void synpred146_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1092:27: ( aCase )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1092:27: aCase
        {
        pushFollow(FOLLOW_aCase_in_synpred146_Java5071);
        aCase();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred146_Java
// $ANTLR start synpred149_Java
    public final void synpred149_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1117:5: ( declaration )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1117:6: declaration
        {
        pushFollow(FOLLOW_declaration_in_synpred149_Java5189);
        declaration();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred149_Java
// $ANTLR start synpred150_Java
    public final void synpred150_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1119:7: ( expressionList )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1119:7: expressionList
        {
        pushFollow(FOLLOW_expressionList_in_synpred150_Java5207);
        expressionList();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred150_Java
// $ANTLR start synpred160_Java
    public final void synpred160_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:3: ( annotation )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1168:3: annotation
        {
        pushFollow(FOLLOW_annotation_in_synpred160_Java5437);
        annotation();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred160_Java
// $ANTLR start synpred163_Java
    public final void synpred163_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:28: ( SEMI )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1178:28: SEMI
        {
        match(input,SEMI,FOLLOW_SEMI_in_synpred163_Java5472); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred163_Java
// $ANTLR start synpred165_Java
    public final void synpred165_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1233:33: ( assignmentOperator assignmentExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1233:33: assignmentOperator assignmentExpression
        {
        pushFollow(FOLLOW_assignmentOperator_in_synpred165_Java5608);
        assignmentOperator();
        state._fsp--;
        if (state.failed) return;
        
        pushFollow(FOLLOW_assignmentExpression_in_synpred165_Java5610);
        assignmentExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred165_Java
// $ANTLR start synpred177_Java
    public final void synpred177_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1273:24: ( QUESTION assignmentExpression COLON conditionalExpression[false] )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1273:24: QUESTION assignmentExpression COLON conditionalExpression[false]
        {
        match(input,QUESTION,FOLLOW_QUESTION_in_synpred177_Java5831); if (state.failed) return;
        
        pushFollow(FOLLOW_assignmentExpression_in_synpred177_Java5834);
        assignmentExpression();
        state._fsp--;
        if (state.failed) return;
        
        match(input,COLON,FOLLOW_COLON_in_synpred177_Java5836); if (state.failed) return;
        
        pushFollow(FOLLOW_conditionalExpression_in_synpred177_Java5839);
        conditionalExpression(false);
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred177_Java
// $ANTLR start synpred178_Java
    public final void synpred178_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1280:25: ( LOR logicalAndExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1280:25: LOR logicalAndExpression
        {
        match(input,LOR,FOLLOW_LOR_in_synpred178_Java5862); if (state.failed) return;
        
        pushFollow(FOLLOW_logicalAndExpression_in_synpred178_Java5865);
        logicalAndExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred178_Java
// $ANTLR start synpred179_Java
    public final void synpred179_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1287:26: ( LAND inclusiveOrExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1287:26: LAND inclusiveOrExpression
        {
        match(input,LAND,FOLLOW_LAND_in_synpred179_Java5887); if (state.failed) return;
        
        pushFollow(FOLLOW_inclusiveOrExpression_in_synpred179_Java5890);
        inclusiveOrExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred179_Java
// $ANTLR start synpred180_Java
    public final void synpred180_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1294:26: ( BOR exclusiveOrExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1294:26: BOR exclusiveOrExpression
        {
        match(input,BOR,FOLLOW_BOR_in_synpred180_Java5912); if (state.failed) return;
        
        pushFollow(FOLLOW_exclusiveOrExpression_in_synpred180_Java5915);
        exclusiveOrExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred180_Java
// $ANTLR start synpred181_Java
    public final void synpred181_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1301:18: ( BXOR andExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1301:18: BXOR andExpression
        {
        match(input,BXOR,FOLLOW_BXOR_in_synpred181_Java5937); if (state.failed) return;
        
        pushFollow(FOLLOW_andExpression_in_synpred181_Java5940);
        andExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred181_Java
// $ANTLR start synpred182_Java
    public final void synpred182_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1308:23: ( BAND equalityExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1308:23: BAND equalityExpression
        {
        match(input,BAND,FOLLOW_BAND_in_synpred182_Java5962); if (state.failed) return;
        
        pushFollow(FOLLOW_equalityExpression_in_synpred182_Java5965);
        equalityExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred182_Java
// $ANTLR start synpred184_Java
    public final void synpred184_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1317:5: ( ( NOT_EQUAL | EQUAL ) instanceOfExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1317:5: ( NOT_EQUAL | EQUAL ) instanceOfExpression
        {
        if ( input.LA(1)==EQUAL||input.LA(1)==NOT_EQUAL ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }
        pushFollow(FOLLOW_instanceOfExpression_in_synpred184_Java6026);
        instanceOfExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred184_Java
// $ANTLR start synpred185_Java
    public final void synpred185_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1329:25: ( 'instanceof' typeSpec )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1329:25: 'instanceof' typeSpec
        {
        match(input,156,FOLLOW_156_in_synpred185_Java6051); if (state.failed) return;
        
        pushFollow(FOLLOW_typeSpec_in_synpred185_Java6054);
        typeSpec();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred185_Java
// $ANTLR start synpred186_Java
    public final void synpred186_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1336:23: ( relationalOperator additiveExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1336:23: relationalOperator additiveExpression
        {
        pushFollow(FOLLOW_relationalOperator_in_synpred186_Java6076);
        relationalOperator();
        state._fsp--;
        if (state.failed) return;
        
        pushFollow(FOLLOW_additiveExpression_in_synpred186_Java6079);
        additiveExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred186_Java
// $ANTLR start synpred194_Java
    public final void synpred194_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1362:5: ( ( PLUS | MINUS ) multiplicativeExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1362:5: ( PLUS | MINUS ) multiplicativeExpression
        {
        if ( input.LA(1)==MINUS||input.LA(1)==PLUS ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }
        pushFollow(FOLLOW_multiplicativeExpression_in_synpred194_Java6258);
        multiplicativeExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred194_Java
// $ANTLR start synpred197_Java
    public final void synpred197_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1376:5: ( ( STAR | DIV | MOD ) unaryExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1376:5: ( STAR | DIV | MOD ) unaryExpression
        {
        if ( input.LA(1)==DIV||input.LA(1)==MOD||input.LA(1)==STAR ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }
        pushFollow(FOLLOW_unaryExpression_in_synpred197_Java6333);
        unaryExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred197_Java
// $ANTLR start synpred204_Java
    public final void synpred204_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1416:5: ( LPAREN builtInTypeSpec RPAREN unaryExpression )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1416:5: LPAREN builtInTypeSpec RPAREN unaryExpression
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred204_Java6532); if (state.failed) return;
        
        pushFollow(FOLLOW_builtInTypeSpec_in_synpred204_Java6534);
        builtInTypeSpec();
        state._fsp--;
        if (state.failed) return;
        
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred204_Java6536); if (state.failed) return;
        
        pushFollow(FOLLOW_unaryExpression_in_synpred204_Java6538);
        unaryExpression();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred204_Java
// $ANTLR start synpred206_Java
    public final void synpred206_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:7: ( LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:8: LPAREN classTypeSpec ( BAND classTypeSpec )* RPAREN unaryExpressionNotPlusMinus
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred206_Java6579); if (state.failed) return;
        
        pushFollow(FOLLOW_classTypeSpec_in_synpred206_Java6581);
        classTypeSpec();
        state._fsp--;
        if (state.failed) return;
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:29: ( BAND classTypeSpec )*
        loop184:
        while (true) {
            int alt184=2;
            int LA184_0 = input.LA(1);
            if ( (LA184_0==BAND) ) {
                alt184=1;
            }
        
            switch (alt184) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1421:30: BAND classTypeSpec
                {
                match(input,BAND,FOLLOW_BAND_in_synpred206_Java6584); if (state.failed) return;
        
                pushFollow(FOLLOW_classTypeSpec_in_synpred206_Java6586);
                classTypeSpec();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
            default :
                break loop184;
            }
        }
        
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred206_Java6590); if (state.failed) return;
        
        pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_synpred206_Java6592);
        unaryExpressionNotPlusMinus();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred206_Java
// $ANTLR start synpred211_Java
    public final void synpred211_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:9: ( ( typeSpec | voidkw ) REF ( typeArguments )? ( IDENT | NEW ) )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:9: ( typeSpec | voidkw ) REF ( typeArguments )? ( IDENT | NEW )
        {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:9: ( typeSpec | voidkw )
        int alt185=2;
        int LA185_0 = input.LA(1);
        if ( (LA185_0==AT||LA185_0==IDENT||LA185_0==135||LA185_0==137||LA185_0==140||LA185_0==144||LA185_0==151||LA185_0==157||LA185_0==159||LA185_0==166) ) {
            alt185=1;
        }
        else if ( (LA185_0==178) ) {
            alt185=2;
        }
        
        else {
            if (state.backtracking>0) {state.failed=true; return;}
            NoViableAltException nvae =
                new NoViableAltException("", 185, 0, input);
            throw nvae;
        }
        
        switch (alt185) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:10: typeSpec
                {
                pushFollow(FOLLOW_typeSpec_in_synpred211_Java6670);
                typeSpec();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
            case 2 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:19: voidkw
                {
                pushFollow(FOLLOW_voidkw_in_synpred211_Java6672);
                voidkw();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
        }
        
        match(input,REF,FOLLOW_REF_in_synpred211_Java6675); if (state.failed) return;
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:31: ( typeArguments )?
        int alt186=2;
        int LA186_0 = input.LA(1);
        if ( (LA186_0==LT) ) {
            alt186=1;
        }
        switch (alt186) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1429:31: typeArguments
                {
                pushFollow(FOLLOW_typeArguments_in_synpred211_Java6677);
                typeArguments();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
        }
        
        if ( input.LA(1)==IDENT||input.LA(1)==NEW ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }
        }
    }

// $ANTLR end synpred211_Java
// $ANTLR start synpred214_Java
    public final void synpred214_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:27: ( REF ( typeArguments )? ( IDENT | NEW ) )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:27: REF ( typeArguments )? ( IDENT | NEW )
        {
        match(input,REF,FOLLOW_REF_in_synpred214_Java6697); if (state.failed) return;
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:31: ( typeArguments )?
        int alt187=2;
        int LA187_0 = input.LA(1);
        if ( (LA187_0==LT) ) {
            alt187=1;
        }
        switch (alt187) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1430:31: typeArguments
                {
                pushFollow(FOLLOW_typeArguments_in_synpred214_Java6699);
                typeArguments();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
        }
        
        if ( input.LA(1)==IDENT||input.LA(1)==NEW ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }
        }
    }

// $ANTLR end synpred214_Java
// $ANTLR start synpred215_Java
    public final void synpred215_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1435:3: ( lambdaParameters LAMBDA )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1435:4: lambdaParameters LAMBDA
        {
        pushFollow(FOLLOW_lambdaParameters_in_synpred215_Java6726);
        lambdaParameters();
        state._fsp--;
        if (state.failed) return;
        
        match(input,LAMBDA,FOLLOW_LAMBDA_in_synpred215_Java6728); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred215_Java
// $ANTLR start synpred244_Java
    public final void synpred244_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1503:34: ( DOT IDENT )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1503:34: DOT IDENT
        {
        match(input,DOT,FOLLOW_DOT_in_synpred244_Java7146); if (state.failed) return;
        
        match(input,IDENT,FOLLOW_IDENT_in_synpred244_Java7149); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred244_Java
// $ANTLR start synpred248_Java
    public final void synpred248_Java_fragment() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1521:5: ( classOrInterfaceType )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1521:5: classOrInterfaceType
        {
        pushFollow(FOLLOW_classOrInterfaceType_in_synpred248_Java7329);
        classOrInterfaceType();
        state._fsp--;
        if (state.failed) return;
        
        }
    }

// $ANTLR end synpred248_Java
// $ANTLR start synpred255_Java
    public final void synpred255_Java_fragment() throws RecognitionException {
        Token lb=null;
        
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1547:27: (lb= LBRACK ( expression )? RBRACK )
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1547:27: lb= LBRACK ( expression )? RBRACK
        {
        lb=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_synpred255_Java7498); if (state.failed) return;
        
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1551:5: ( expression )?
        int alt195=2;
        int LA195_0 = input.LA(1);
        if ( (LA195_0==AT||LA195_0==BNOT||LA195_0==CHAR_LITERAL||LA195_0==DEC||LA195_0==IDENT||LA195_0==INC||LA195_0==LNOT||LA195_0==LPAREN||LA195_0==MINUS||LA195_0==NEW||LA195_0==NUM_LITERAL||LA195_0==PLUS||(LA195_0 >= STRING_LITERAL && LA195_0 <= SUPER)||LA195_0==135||LA195_0==137||LA195_0==140||LA195_0==144||LA195_0==148||LA195_0==151||LA195_0==157||LA195_0==159||LA195_0==161||LA195_0==166||LA195_0==171||LA195_0==176||LA195_0==178) ) {
            alt195=1;
        }
        switch (alt195) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1551:6: expression
                {
                pushFollow(FOLLOW_expression_in_synpred255_Java7546);
                expression();
                state._fsp--;
                if (state.failed) return;
        
                }
                break;
        
        }
        
        match(input,RBRACK,FOLLOW_RBRACK_in_synpred255_Java7550); if (state.failed) return;
        
        }
    }

// $ANTLR end synpred255_Java
// Delegated rules
    public final boolean synpred255_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred255_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred211_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred211_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred204_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred204_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred214_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred214_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred178_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred178_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred1_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred125_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred125_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred177_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred177_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred215_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred215_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred117_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred117_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred185_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred185_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred16_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred94_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred94_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred130_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred130_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred194_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred194_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred21_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred186_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred186_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred184_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred184_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred126_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred126_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred143_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred143_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred163_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred163_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred51_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred51_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred9_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred124_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred124_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred197_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred197_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred165_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred165_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred15_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred13_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred92_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred92_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred149_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred149_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred150_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred150_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred206_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred206_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred85_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred85_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred93_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred93_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred127_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred127_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred180_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred180_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred244_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred244_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred128_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred128_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred179_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred179_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred248_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred248_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred10_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred160_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred160_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred22_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred146_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred146_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred182_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred182_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred181_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred181_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public final boolean synpred44_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }

    public static class compilationUnit_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "compilationUnit"
    public static class annotationTypeDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationTypeDefinition"
    public static class annotationBlock_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationBlock"
    public static class annotationField_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationField"
    public static class annotationMethod_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationMethod"
    public static class defaultValue_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "defaultValue"
    public static class annotmodannot_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotmodannot"
    public static class annotation_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotation"
    public static class annotationInit_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationInit"
    public static class annotationMemberInit_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationMemberInit"
    public static class annotationMemberValue_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationMemberValue"
    public static class annotationMemberArrayInitializer_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "annotationMemberArrayInitializer"
    public static class enumDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "enumDefinition"
    public static class enumBlock_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "enumBlock"
    public static class enumConst_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "enumConst"
    public static class enumConstInit_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "enumConstInit"
    public static class packageDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "packageDefinition"
    public static class importDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "importDefinition"
    public static class typeDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeDefinition"
    public static class declaration_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "declaration"
    public static class typeSpec_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeSpec"
    public static class classTypeSpec_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "classTypeSpec"
    public static class declaratorBrackets_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "declaratorBrackets"
    public static class arrayDeclarator_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "arrayDeclarator"
    public static class builtInTypeSpec_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "builtInTypeSpec"
    public static class classOrInterfaceType_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "classOrInterfaceType"
    public static class simpleClassOrInterfaceType_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "simpleClassOrInterfaceType"
    public static class builtInType_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "builtInType"
    public static class voidkw_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "voidkw"
    public static class identifierStar_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "identifierStar"
    public static class identifier_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "identifier"
    public static class modifier_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "modifier"
    public static class classDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "classDefinition"
    public static class formalTypeParameters_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "formalTypeParameters"
    public static class typeParameter_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeParameter"
    public static class typeParameterBound_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeParameterBound"
    public static class typeParameterBoundRest_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeParameterBoundRest"
    public static class typeArguments_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeArguments"
    public static class typeArgument_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "typeArgument"
    public static class superClassClause_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "superClassClause"
    public static class interfaceDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "interfaceDefinition"
    public static class classBlock_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "classBlock"
    public static class interfaceExtends_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "interfaceExtends"
    public static class implementsClause_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "implementsClause"
    public static class field_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "field"
    public static class constructorDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "constructorDefinition"
    public static class operationDefinition_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "operationDefinition"
    public static class attributeDefinitions_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "attributeDefinitions"
    public static class staticInitializer_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "staticInitializer"
    public static class instanceInitializer_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "instanceInitializer"
    public static class variableDefinitions_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "variableDefinitions"
    public static class variableDeclarator_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "variableDeclarator"
    public static class varInitializer_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "varInitializer"
    public static class arrayInitializer_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "arrayInitializer"
    public static class initializer_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "initializer"
    public static class throwsClause_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "throwsClause"
    public static class thrownException_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "thrownException"
    public static class parameterDeclarationList_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "parameterDeclarationList"
    public static class parameterDeclaration_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "parameterDeclaration"
    public static class parameterModifier_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "parameterModifier"
    public static class compoundStatement_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "compoundStatement"
    public static class statementList_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "statementList"
    public static class explicitConstructorInvocation_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "explicitConstructorInvocation"
    public static class statement_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "statement"
    public static class casesGroup_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "casesGroup"
    public static class aCase_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "aCase"
    public static class caseSList_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "caseSList"
    public static class forInit_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "forInit"
    public static class forCond_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "forCond"
    public static class forIter_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "forIter"
    public static class tryBlock_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "tryBlock"
    public static class resourceDeclarations_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "resourceDeclarations"
    public static class finallyClause_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "finallyClause"
    public static class handler_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "handler"
    public static class caughtExceptionDeclarations_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "caughtExceptionDeclarations"
    public static class caughtExceptionDeclaration_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "caughtExceptionDeclaration"
    public static class returnExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "returnExpression"
    public static class expressionList_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "expressionList"
    public static class expression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "expression"
    public static class assignmentExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "assignmentExpression"
    public static class assignmentOperator_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "assignmentOperator"
    public static class conditionalExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "conditionalExpression"
    public static class logicalOrExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "logicalOrExpression"
    public static class logicalAndExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "logicalAndExpression"
    public static class inclusiveOrExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "inclusiveOrExpression"
    public static class exclusiveOrExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "exclusiveOrExpression"
    public static class andExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "andExpression"
    public static class equalityExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "equalityExpression"
    public static class instanceOfExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "instanceOfExpression"
    public static class relationalExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "relationalExpression"
    public static class relationalOperator_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "relationalOperator"
    public static class additiveExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "additiveExpression"
    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "multiplicativeExpression"
    public static class unaryExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "unaryExpression"
    public static class unaryExpressionNotPlusMinus_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "unaryExpressionNotPlusMinus"
    public static class methodReference_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "methodReference"
    public static class lambdaExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "lambdaExpression"
    public static class lambdaParameters_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "lambdaParameters"
    public static class inferredFormalParameters_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "inferredFormalParameters"
    public static class lambdaBody_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "lambdaBody"
    public static class postfixExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "postfixExpression"
    public static class primaryExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "primaryExpression"
    public static class identPrimary_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "identPrimary"
    public static class newExpression_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "newExpression"
    public static class argList_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "argList"
    public static class newArrayDeclarator_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

// $ANTLR end "newArrayDeclarator"
    public static class constant_return extends ParserRuleReturnScope {
         ASTTree tree;

        @Override
        public ASTTree getTree() {
            return tree;
        }

    }

}
