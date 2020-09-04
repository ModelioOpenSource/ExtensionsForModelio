// $ANTLR 3.5.2 com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g 2014-10-16 11:23:32
package org.modelio.module.javadesigner.reverse.antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.runtime.*;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

@SuppressWarnings("all")
public class JavaLexer extends Lexer {
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

     static final String DFA8_eotS = "\2\uffff\2\5\2\uffff";

     static final String DFA8_eofS = "\6\uffff";

     static final String DFA8_minS = "\2\57\2\0\2\uffff";

     static final String DFA8_maxS = "\2\57\2\uffff\2\uffff";

     static final String DFA8_acceptS = "\4\uffff\1\1\1\2";

     static final String DFA8_specialS = "\2\uffff\1\1\1\0\2\uffff}>";

     static final String[] DFA8_transitionS = {
			"\1\1",
			"\1\2",
			"\12\3\1\4\2\3\1\4\ufff2\3",
			"\12\3\1\4\2\3\1\4\ufff2\3",
			"",
			""
	};

     static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);

     static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);

     static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);

     static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);

     static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);

     static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);

     static final short[][] DFA8_transition;

     static final String DFA39_eotS = "\1\uffff\17\55\7\uffff\1\126\1\uffff\1\130\1\132\1\134\1\uffff\1\137\1"+
		"\142\1\146\1\150\1\152\2\uffff\1\154\1\157\1\162\10\uffff\12\55\1\177"+
		"\10\55\1\u0088\22\55\36\uffff\14\55\1\uffff\7\55\1\u00b5\1\uffff\2\55"+
		"\1\u00ba\3\55\1\u00be\16\55\1\u00ce\10\55\1\u00d7\1\u00d8\1\55\1\u00da"+
		"\4\55\1\u00df\1\u00e0\4\55\1\uffff\4\55\1\uffff\1\u00e9\1\55\1\u00eb\1"+
		"\uffff\12\55\1\u00f6\3\55\1\u00fa\1\uffff\1\u00fb\6\55\1\u0102\2\uffff"+
		"\1\u0103\1\uffff\1\u0104\3\55\2\uffff\1\55\1\u0109\1\u010b\1\u010c\4\55"+
		"\1\uffff\1\55\1\uffff\4\55\1\u0116\4\55\1\u011b\1\uffff\1\55\1\u011e\1"+
		"\55\2\uffff\1\55\1\u0121\1\u0122\1\55\1\u0124\1\55\3\uffff\1\55\1\u0127"+
		"\2\55\1\uffff\1\55\2\uffff\1\55\1\u012c\2\55\1\u012f\3\55\1\u0133\1\uffff"+
		"\1\u0134\1\55\1\u0136\1\55\1\uffff\1\55\1\u0139\1\uffff\2\55\2\uffff\1"+
		"\55\1\uffff\1\u013d\1\55\1\uffff\1\u013f\1\u0140\1\u0141\1\55\1\uffff"+
		"\2\55\1\uffff\1\u0145\1\u0146\1\55\2\uffff\1\55\1\uffff\2\55\1\uffff\2"+
		"\55\1\u014d\1\uffff\1\u014e\3\uffff\3\55\2\uffff\1\55\1\u0153\3\55\1\u0157"+
		"\2\uffff\2\55\1\u015a\1\u015b\1\uffff\2\55\1\u015e\1\uffff\1\u015f\1\u0160"+
		"\2\uffff\1\55\1\u0162\3\uffff\1\55\1\uffff\1\u0164\1\uffff";

     static final String DFA39_eofS = "\u0165\uffff";

     static final String DFA39_minS = "\1\11\1\145\1\142\1\157\1\141\1\145\1\154\1\141\1\146\1\157\2\141\2\150"+
		"\1\157\1\150\7\uffff\1\72\1\uffff\1\56\2\75\1\uffff\1\52\1\53\1\55\2\75"+
		"\2\uffff\2\75\1\46\10\uffff\1\164\2\163\1\157\1\145\1\164\1\163\2\141"+
		"\1\156\1\44\1\146\1\163\1\165\1\164\1\154\1\156\1\157\1\162\1\44\1\160"+
		"\1\163\1\156\1\164\1\154\1\167\1\143\1\151\1\142\1\157\1\141\1\151\1\156"+
		"\1\160\1\151\1\141\2\151\36\uffff\1\165\1\164\1\145\1\154\1\141\2\145"+
		"\1\143\1\162\1\163\1\164\1\142\1\uffff\1\141\1\145\1\155\1\145\1\163\2"+
		"\141\1\44\1\uffff\1\154\1\164\1\44\1\147\1\151\1\154\1\44\1\153\1\166"+
		"\1\164\1\154\1\162\1\164\1\151\1\164\1\143\1\145\1\163\1\145\1\156\1\145"+
		"\1\44\1\144\1\141\1\154\3\162\1\145\1\153\2\44\1\150\1\44\1\163\1\151"+
		"\1\154\1\165\2\44\1\156\1\145\1\154\1\164\1\uffff\1\145\1\162\1\141\1"+
		"\162\1\uffff\1\44\1\166\1\44\1\uffff\2\141\1\145\1\151\1\164\1\151\2\143"+
		"\1\150\1\162\1\44\1\141\1\167\1\163\1\44\1\uffff\1\44\1\164\1\145\1\156"+
		"\1\141\1\164\1\141\1\44\2\uffff\1\44\1\uffff\1\44\1\156\1\145\1\154\2"+
		"\uffff\1\144\3\44\1\155\1\164\1\156\1\146\1\uffff\1\145\1\uffff\1\147"+
		"\1\164\2\143\1\44\1\143\1\164\1\150\1\162\1\44\1\uffff\1\144\1\44\1\151"+
		"\2\uffff\1\151\2\44\1\143\1\44\1\156\3\uffff\1\165\1\44\1\164\1\163\1"+
		"\uffff\1\171\2\uffff\1\145\1\44\1\143\1\141\1\44\2\145\1\164\1\44\1\uffff"+
		"\1\44\1\146\1\44\1\157\1\uffff\1\163\1\44\1\uffff\1\145\1\154\2\uffff"+
		"\1\164\1\uffff\1\44\1\145\1\uffff\3\44\1\156\1\uffff\1\145\1\143\1\uffff"+
		"\2\44\1\145\2\uffff\1\160\1\uffff\1\156\1\141\1\uffff\1\156\1\145\1\44"+
		"\1\uffff\1\44\3\uffff\1\164\1\157\1\145\2\uffff\1\144\1\44\1\151\1\146"+
		"\1\164\1\44\2\uffff\1\163\1\146\2\44\1\uffff\1\172\1\145\1\44\1\uffff"+
		"\2\44\2\uffff\1\145\1\44\3\uffff\1\144\1\uffff\1\44\1\uffff";

     static final String DFA39_maxS = "\1\u00ff\1\145\1\163\1\171\2\157\1\170\1\157\1\156\1\157\2\165\1\171\1"+
		"\162\1\157\1\150\7\uffff\1\72\1\uffff\1\71\2\75\1\uffff\2\75\1\76\2\75"+
		"\2\uffff\1\75\1\174\1\75\10\uffff\1\164\2\163\1\157\1\145\2\164\2\141"+
		"\1\156\1\u00ff\1\146\1\163\1\165\1\164\1\154\1\156\1\157\1\162\1\u00ff"+
		"\1\160\1\164\1\156\1\164\1\154\1\167\1\143\1\157\1\142\1\157\1\162\1\151"+
		"\1\156\1\160\1\162\1\171\1\154\1\151\36\uffff\1\165\1\164\1\145\1\154"+
		"\1\141\2\145\1\143\1\162\1\163\1\164\1\142\1\uffff\1\141\1\145\1\155\1"+
		"\145\1\163\2\141\1\u00ff\1\uffff\1\157\1\164\1\u00ff\1\147\1\151\1\154"+
		"\1\u00ff\1\153\1\166\1\164\1\154\1\162\1\164\1\151\1\164\1\143\1\145\1"+
		"\163\1\157\1\156\1\145\1\u00ff\1\144\1\141\1\154\3\162\1\145\1\153\2\u00ff"+
		"\1\150\1\u00ff\1\163\1\151\1\154\1\165\2\u00ff\1\156\1\145\1\154\1\164"+
		"\1\uffff\1\145\1\162\1\141\1\162\1\uffff\1\u00ff\1\166\1\u00ff\1\uffff"+
		"\2\141\1\145\1\151\1\164\1\151\2\143\1\150\1\162\1\u00ff\1\141\1\167\1"+
		"\163\1\u00ff\1\uffff\1\u00ff\1\164\1\145\1\156\1\141\1\164\1\141\1\u00ff"+
		"\2\uffff\1\u00ff\1\uffff\1\u00ff\1\156\1\145\1\154\2\uffff\1\144\3\u00ff"+
		"\1\155\1\164\1\156\1\146\1\uffff\1\145\1\uffff\1\147\1\164\2\143\1\u00ff"+
		"\1\143\1\164\1\150\1\162\1\u00ff\1\uffff\1\144\1\u00ff\1\151\2\uffff\1"+
		"\151\2\u00ff\1\143\1\u00ff\1\156\3\uffff\1\165\1\u00ff\1\164\1\163\1\uffff"+
		"\1\171\2\uffff\1\145\1\u00ff\1\143\1\141\1\u00ff\2\145\1\164\1\u00ff\1"+
		"\uffff\1\u00ff\1\146\1\u00ff\1\157\1\uffff\1\163\1\u00ff\1\uffff\1\145"+
		"\1\154\2\uffff\1\164\1\uffff\1\u00ff\1\145\1\uffff\3\u00ff\1\156\1\uffff"+
		"\1\145\1\143\1\uffff\2\u00ff\1\145\2\uffff\1\160\1\uffff\1\156\1\141\1"+
		"\uffff\1\156\1\145\1\u00ff\1\uffff\1\u00ff\3\uffff\1\164\1\157\1\145\2"+
		"\uffff\1\144\1\u00ff\1\151\1\146\1\164\1\u00ff\2\uffff\1\163\1\146\2\u00ff"+
		"\1\uffff\1\172\1\145\1\u00ff\1\uffff\2\u00ff\2\uffff\1\145\1\u00ff\3\uffff"+
		"\1\144\1\uffff\1\u00ff\1\uffff";

     static final String DFA39_acceptS = "\20\uffff\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\uffff\1\72\3\uffff\1\77"+
		"\5\uffff\1\116\1\117\3\uffff\1\130\1\131\1\137\1\140\1\142\1\143\1\144"+
		"\1\145\46\uffff\1\133\1\71\1\132\1\73\1\75\1\74\1\100\1\76\1\102\1\141"+
		"\1\101\1\104\1\105\1\103\1\106\1\110\1\111\1\107\1\113\1\112\1\115\1\114"+
		"\1\121\1\120\1\123\1\124\1\122\1\126\1\127\1\125\14\uffff\1\14\10\uffff"+
		"\1\26\54\uffff\1\25\4\uffff\1\32\3\uffff\1\134\17\uffff\1\56\10\uffff"+
		"\1\6\1\7\1\uffff\1\11\4\uffff\1\16\1\17\10\uffff\1\34\1\uffff\1\36\12"+
		"\uffff\1\50\3\uffff\1\55\1\57\6\uffff\1\5\1\10\1\12\4\uffff\1\21\1\uffff"+
		"\1\22\1\24\11\uffff\1\43\4\uffff\1\135\2\uffff\1\52\2\uffff\1\61\1\1\1"+
		"\uffff\1\3\2\uffff\1\15\4\uffff\1\30\2\uffff\1\35\3\uffff\1\42\1\44\1"+
		"\uffff\1\46\2\uffff\1\53\3\uffff\1\4\1\uffff\1\136\1\20\1\23\3\uffff\1"+
		"\37\1\40\6\uffff\1\2\1\13\4\uffff\1\45\3\uffff\1\60\2\uffff\1\33\1\41"+
		"\2\uffff\1\54\1\27\1\31\1\uffff\1\51\1\uffff\1\47";

     static final String DFA39_specialS = "\u0165\uffff}>";

     static final String[] DFA39_transitionS = {
			"\1\51\1\52\1\uffff\1\51\1\52\22\uffff\1\51\1\33\1\54\1\uffff\1\55\1\41"+
			"\1\46\1\53\1\21\1\22\1\40\1\36\1\30\1\37\1\31\1\35\12\56\1\27\1\47\1"+
			"\43\1\32\1\42\1\20\1\50\32\55\1\23\1\uffff\1\24\1\44\1\55\1\uffff\1\2"+
			"\1\3\1\4\1\5\1\6\1\7\2\55\1\10\2\55\1\11\1\55\1\12\1\55\1\13\1\55\1\1"+
			"\1\14\1\15\1\55\1\16\1\17\3\55\1\25\1\45\1\26\1\34\101\uffff\27\55\1"+
			"\uffff\37\55\1\uffff\10\55",
			"\1\57",
			"\1\60\20\uffff\1\61",
			"\1\62\2\uffff\1\63\6\uffff\1\64",
			"\1\65\6\uffff\1\66\3\uffff\1\67\2\uffff\1\70",
			"\1\72\11\uffff\1\71",
			"\1\73\1\uffff\1\74\11\uffff\1\75",
			"\1\76\7\uffff\1\77\2\uffff\1\100\2\uffff\1\101",
			"\1\102\6\uffff\1\103\1\104",
			"\1\105",
			"\1\106\3\uffff\1\110\17\uffff\1\107",
			"\1\111\20\uffff\1\112\2\uffff\1\113",
			"\1\114\13\uffff\1\115\1\120\1\uffff\1\116\1\uffff\1\117",
			"\1\121\11\uffff\1\122",
			"\1\123",
			"\1\124",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\125",
			"",
			"\1\127\1\uffff\12\56",
			"\1\131",
			"\1\133",
			"",
			"\1\136\4\uffff\1\136\15\uffff\1\135",
			"\1\141\21\uffff\1\140",
			"\1\145\17\uffff\1\144\1\143",
			"\1\147",
			"\1\151",
			"",
			"",
			"\1\153",
			"\1\155\76\uffff\1\156",
			"\1\161\26\uffff\1\160",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\163",
			"\1\164",
			"\1\165",
			"\1\166",
			"\1\167",
			"\1\170",
			"\1\171\1\172",
			"\1\173",
			"\1\174",
			"\1\175",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\24\55\1\176"+
			"\5\55\105\uffff\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"\1\u0084",
			"\1\u0085",
			"\1\u0086",
			"\1\u0087",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0089",
			"\1\u008a\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\1\u0090",
			"\1\u0091\5\uffff\1\u0092",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095\20\uffff\1\u0096",
			"\1\u0097",
			"\1\u0098",
			"\1\u0099",
			"\1\u009a\10\uffff\1\u009b",
			"\1\u009c\23\uffff\1\u009d\3\uffff\1\u009e",
			"\1\u009f\2\uffff\1\u00a0",
			"\1\u00a1",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"\1\u00ad",
			"",
			"\1\u00ae",
			"\1\u00af",
			"\1\u00b0",
			"\1\u00b1",
			"\1\u00b2",
			"\1\u00b3",
			"\1\u00b4",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\u00b6\2\uffff\1\u00b7",
			"\1\u00b8",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\4\55\1\u00b9"+
			"\25\55\105\uffff\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00bb",
			"\1\u00bc",
			"\1\u00bd",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00bf",
			"\1\u00c0",
			"\1\u00c1",
			"\1\u00c2",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca\11\uffff\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00cf",
			"\1\u00d0",
			"\1\u00d1",
			"\1\u00d2",
			"\1\u00d3",
			"\1\u00d4",
			"\1\u00d5",
			"\1\u00d6",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00d9",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00db",
			"\1\u00dc",
			"\1\u00dd",
			"\1\u00de",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00e1",
			"\1\u00e2",
			"\1\u00e3",
			"\1\u00e4",
			"",
			"\1\u00e5",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e8",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00ea",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\u00ec",
			"\1\u00ed",
			"\1\u00ee",
			"\1\u00ef",
			"\1\u00f0",
			"\1\u00f1",
			"\1\u00f2",
			"\1\u00f3",
			"\1\u00f4",
			"\1\u00f5",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00f7",
			"\1\u00f8",
			"\1\u00f9",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\u00ff",
			"\1\u0100",
			"\1\u0101",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0105",
			"\1\u0106",
			"\1\u0107",
			"",
			"",
			"\1\u0108",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\13\55\1\u010a"+
			"\16\55\105\uffff\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u010d",
			"\1\u010e",
			"\1\u010f",
			"\1\u0110",
			"",
			"\1\u0111",
			"",
			"\1\u0112",
			"\1\u0113",
			"\1\u0114",
			"\1\u0115",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0117",
			"\1\u0118",
			"\1\u0119",
			"\1\u011a",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\u011c",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\22\55\1\u011d"+
			"\7\55\105\uffff\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u011f",
			"",
			"",
			"\1\u0120",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0123",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0125",
			"",
			"",
			"",
			"\1\u0126",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0128",
			"\1\u0129",
			"",
			"\1\u012a",
			"",
			"",
			"\1\u012b",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u012d",
			"\1\u012e",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0130",
			"\1\u0131",
			"\1\u0132",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0135",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0137",
			"",
			"\1\u0138",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\u013a",
			"\1\u013b",
			"",
			"",
			"\1\u013c",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u013e",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0142",
			"",
			"\1\u0143",
			"\1\u0144",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0147",
			"",
			"",
			"\1\u0148",
			"",
			"\1\u0149",
			"\1\u014a",
			"",
			"\1\u014b",
			"\1\u014c",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"",
			"",
			"\1\u014f",
			"\1\u0150",
			"\1\u0151",
			"",
			"",
			"\1\u0152",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\u0154",
			"\1\u0155",
			"\1\u0156",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"",
			"\1\u0158",
			"\1\u0159",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\u015c",
			"\1\u015d",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"",
			"\1\u0161",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			"",
			"",
			"",
			"\1\u0163",
			"",
			"\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32\55\105\uffff"+
			"\27\55\1\uffff\37\55\1\uffff\10\55",
			""
	};

     static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);

     static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);

     static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);

     static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);

     static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);

     static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);

     static final short[][] DFA39_transition;

    protected DFA8 dfa8 = new DFA8(this);

    protected DFA39 dfa39 = new DFA39(this);

//  @Override
//  public String getErrorMessage(RecognitionException e, String[] tokenNames) {
//     return super.getErrorMessage(e, tokenNames);
//  }
// delegates
// delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public JavaLexer() {
    }

    public JavaLexer(final CharStream input) {
        this(input, new RecognizerSharedState());
    }

    public JavaLexer(final CharStream input, final RecognizerSharedState state) {
        super(input,state);
    }

    @Override
    public String getGrammarFileName() {
        return "com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g";
    }

// $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:12:8: ( 'return' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:12:10: 'return'
            {
            match("return"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "RETURN"
// $ANTLR start "T__133"
    public final void mT__133() throws RecognitionException {
        try {
            int _type = T__133;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:13:8: ( 'abstract' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:13:10: 'abstract'
            {
            match("abstract"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__133"
// $ANTLR start "T__134"
    public final void mT__134() throws RecognitionException {
        try {
            int _type = T__134;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:14:8: ( 'assert' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:14:10: 'assert'
            {
            match("assert"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__134"
// $ANTLR start "T__135"
    public final void mT__135() throws RecognitionException {
        try {
            int _type = T__135;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:15:8: ( 'boolean' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:15:10: 'boolean'
            {
            match("boolean"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__135"
// $ANTLR start "T__136"
    public final void mT__136() throws RecognitionException {
        try {
            int _type = T__136;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:16:8: ( 'break' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:16:10: 'break'
            {
            match("break"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__136"
// $ANTLR start "T__137"
    public final void mT__137() throws RecognitionException {
        try {
            int _type = T__137;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:17:8: ( 'byte' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:17:10: 'byte'
            {
            match("byte"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__137"
// $ANTLR start "T__138"
    public final void mT__138() throws RecognitionException {
        try {
            int _type = T__138;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:18:8: ( 'case' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:18:10: 'case'
            {
            match("case"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__138"
// $ANTLR start "T__139"
    public final void mT__139() throws RecognitionException {
        try {
            int _type = T__139;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:19:8: ( 'catch' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:19:10: 'catch'
            {
            match("catch"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__139"
// $ANTLR start "T__140"
    public final void mT__140() throws RecognitionException {
        try {
            int _type = T__140;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:20:8: ( 'char' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:20:10: 'char'
            {
            match("char"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__140"
// $ANTLR start "T__141"
    public final void mT__141() throws RecognitionException {
        try {
            int _type = T__141;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:21:8: ( 'class' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:21:10: 'class'
            {
            match("class"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__141"
// $ANTLR start "T__142"
    public final void mT__142() throws RecognitionException {
        try {
            int _type = T__142;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:22:8: ( 'continue' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:22:10: 'continue'
            {
            match("continue"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__142"
// $ANTLR start "T__143"
    public final void mT__143() throws RecognitionException {
        try {
            int _type = T__143;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:23:8: ( 'do' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:23:10: 'do'
            {
            match("do"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__143"
// $ANTLR start "T__144"
    public final void mT__144() throws RecognitionException {
        try {
            int _type = T__144;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:24:8: ( 'double' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:24:10: 'double'
            {
            match("double"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__144"
// $ANTLR start "T__145"
    public final void mT__145() throws RecognitionException {
        try {
            int _type = T__145;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:25:8: ( 'else' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:25:10: 'else'
            {
            match("else"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__145"
// $ANTLR start "T__146"
    public final void mT__146() throws RecognitionException {
        try {
            int _type = T__146;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:26:8: ( 'enum' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:26:10: 'enum'
            {
            match("enum"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__146"
// $ANTLR start "T__147"
    public final void mT__147() throws RecognitionException {
        try {
            int _type = T__147;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:27:8: ( 'extends' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:27:10: 'extends'
            {
            match("extends"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__147"
// $ANTLR start "T__148"
    public final void mT__148() throws RecognitionException {
        try {
            int _type = T__148;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:28:8: ( 'false' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:28:10: 'false'
            {
            match("false"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__148"
// $ANTLR start "T__149"
    public final void mT__149() throws RecognitionException {
        try {
            int _type = T__149;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:29:8: ( 'final' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:29:10: 'final'
            {
            match("final"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__149"
// $ANTLR start "T__150"
    public final void mT__150() throws RecognitionException {
        try {
            int _type = T__150;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:30:8: ( 'finally' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:30:10: 'finally'
            {
            match("finally"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__150"
// $ANTLR start "T__151"
    public final void mT__151() throws RecognitionException {
        try {
            int _type = T__151;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:31:8: ( 'float' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:31:10: 'float'
            {
            match("float"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__151"
// $ANTLR start "T__152"
    public final void mT__152() throws RecognitionException {
        try {
            int _type = T__152;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:32:8: ( 'for' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:32:10: 'for'
            {
            match("for"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__152"
// $ANTLR start "T__153"
    public final void mT__153() throws RecognitionException {
        try {
            int _type = T__153;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:33:8: ( 'if' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:33:10: 'if'
            {
            match("if"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__153"
// $ANTLR start "T__154"
    public final void mT__154() throws RecognitionException {
        try {
            int _type = T__154;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:34:8: ( 'implements' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:34:10: 'implements'
            {
            match("implements"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__154"
// $ANTLR start "T__155"
    public final void mT__155() throws RecognitionException {
        try {
            int _type = T__155;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:35:8: ( 'import' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:35:10: 'import'
            {
            match("import"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__155"
// $ANTLR start "T__156"
    public final void mT__156() throws RecognitionException {
        try {
            int _type = T__156;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:36:8: ( 'instanceof' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:36:10: 'instanceof'
            {
            match("instanceof"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__156"
// $ANTLR start "T__157"
    public final void mT__157() throws RecognitionException {
        try {
            int _type = T__157;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:37:8: ( 'int' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:37:10: 'int'
            {
            match("int"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__157"
// $ANTLR start "T__158"
    public final void mT__158() throws RecognitionException {
        try {
            int _type = T__158;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:38:8: ( 'interface' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:38:10: 'interface'
            {
            match("interface"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__158"
// $ANTLR start "T__159"
    public final void mT__159() throws RecognitionException {
        try {
            int _type = T__159;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:39:8: ( 'long' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:39:10: 'long'
            {
            match("long"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__159"
// $ANTLR start "T__160"
    public final void mT__160() throws RecognitionException {
        try {
            int _type = T__160;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:40:8: ( 'native' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:40:10: 'native'
            {
            match("native"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__160"
// $ANTLR start "T__161"
    public final void mT__161() throws RecognitionException {
        try {
            int _type = T__161;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:41:8: ( 'null' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:41:10: 'null'
            {
            match("null"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__161"
// $ANTLR start "T__162"
    public final void mT__162() throws RecognitionException {
        try {
            int _type = T__162;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:42:8: ( 'package' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:42:10: 'package'
            {
            match("package"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__162"
// $ANTLR start "T__163"
    public final void mT__163() throws RecognitionException {
        try {
            int _type = T__163;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:43:8: ( 'private' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:43:10: 'private'
            {
            match("private"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__163"
// $ANTLR start "T__164"
    public final void mT__164() throws RecognitionException {
        try {
            int _type = T__164;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:44:8: ( 'protected' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:44:10: 'protected'
            {
            match("protected"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__164"
// $ANTLR start "T__165"
    public final void mT__165() throws RecognitionException {
        try {
            int _type = T__165;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:45:8: ( 'public' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:45:10: 'public'
            {
            match("public"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__165"
// $ANTLR start "T__166"
    public final void mT__166() throws RecognitionException {
        try {
            int _type = T__166;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:46:8: ( 'short' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:46:10: 'short'
            {
            match("short"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__166"
// $ANTLR start "T__167"
    public final void mT__167() throws RecognitionException {
        try {
            int _type = T__167;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:47:8: ( 'static' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:47:10: 'static'
            {
            match("static"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__167"
// $ANTLR start "T__168"
    public final void mT__168() throws RecognitionException {
        try {
            int _type = T__168;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:48:8: ( 'strictfp' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:48:10: 'strictfp'
            {
            match("strictfp"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__168"
// $ANTLR start "T__169"
    public final void mT__169() throws RecognitionException {
        try {
            int _type = T__169;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:49:8: ( 'switch' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:49:10: 'switch'
            {
            match("switch"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__169"
// $ANTLR start "T__170"
    public final void mT__170() throws RecognitionException {
        try {
            int _type = T__170;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:50:8: ( 'synchronized' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:50:10: 'synchronized'
            {
            match("synchronized"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__170"
// $ANTLR start "T__171"
    public final void mT__171() throws RecognitionException {
        try {
            int _type = T__171;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:51:8: ( 'this' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:51:10: 'this'
            {
            match("this"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__171"
// $ANTLR start "T__172"
    public final void mT__172() throws RecognitionException {
        try {
            int _type = T__172;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:52:8: ( 'threadsafe' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:52:10: 'threadsafe'
            {
            match("threadsafe"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__172"
// $ANTLR start "T__173"
    public final void mT__173() throws RecognitionException {
        try {
            int _type = T__173;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:53:8: ( 'throw' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:53:10: 'throw'
            {
            match("throw"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__173"
// $ANTLR start "T__174"
    public final void mT__174() throws RecognitionException {
        try {
            int _type = T__174;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:54:8: ( 'throws' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:54:10: 'throws'
            {
            match("throws"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__174"
// $ANTLR start "T__175"
    public final void mT__175() throws RecognitionException {
        try {
            int _type = T__175;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:55:8: ( 'transient' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:55:10: 'transient'
            {
            match("transient"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__175"
// $ANTLR start "T__176"
    public final void mT__176() throws RecognitionException {
        try {
            int _type = T__176;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:56:8: ( 'true' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:56:10: 'true'
            {
            match("true"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__176"
// $ANTLR start "T__177"
    public final void mT__177() throws RecognitionException {
        try {
            int _type = T__177;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:57:8: ( 'try' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:57:10: 'try'
            {
            match("try"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__177"
// $ANTLR start "T__178"
    public final void mT__178() throws RecognitionException {
        try {
            int _type = T__178;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:58:8: ( 'void' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:58:10: 'void'
            {
            match("void"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__178"
// $ANTLR start "T__179"
    public final void mT__179() throws RecognitionException {
        try {
            int _type = T__179;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:59:8: ( 'volatile' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:59:10: 'volatile'
            {
            match("volatile"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__179"
// $ANTLR start "T__180"
    public final void mT__180() throws RecognitionException {
        try {
            int _type = T__180;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:60:8: ( 'while' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:60:10: 'while'
            {
            match("while"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "T__180"
// $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1568:3: ( '?' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1569:3: '?'
            {
            match('?'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "QUESTION"
// $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1573:3: ( '(' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1574:3: '('
            {
            match('('); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LPAREN"
// $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1578:3: ( ')' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1579:3: ')'
            {
            match(')'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "RPAREN"
// $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1583:3: ( '[' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1584:3: '['
            {
            match('['); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LBRACK"
// $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1588:3: ( ']' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1589:3: ']'
            {
            match(']'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "RBRACK"
// $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1593:3: ( '{' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1594:3: '{'
            {
            match('{'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LCURLY"
// $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1598:3: ( '}' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1599:3: '}'
            {
            match('}'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "RCURLY"
// $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1603:3: ( ':' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1604:3: ':'
            {
            match(':'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "COLON"
// $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1608:3: ( ',' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1609:3: ','
            {
            match(','); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "COMMA"
// $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1613:3: ( '.' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1614:3: '.'
            {
            match('.'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DOT"
// $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1618:3: ( '=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1619:3: '='
            {
            match('='); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "ASSIGN"
// $ANTLR start "EQUAL"
    public final void mEQUAL() throws RecognitionException {
        try {
            int _type = EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1623:3: ( '==' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1624:3: '=='
            {
            match("=="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "EQUAL"
// $ANTLR start "LNOT"
    public final void mLNOT() throws RecognitionException {
        try {
            int _type = LNOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1628:3: ( '!' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1629:3: '!'
            {
            match('!'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LNOT"
// $ANTLR start "BNOT"
    public final void mBNOT() throws RecognitionException {
        try {
            int _type = BNOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1633:3: ( '~' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1634:3: '~'
            {
            match('~'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BNOT"
// $ANTLR start "NOT_EQUAL"
    public final void mNOT_EQUAL() throws RecognitionException {
        try {
            int _type = NOT_EQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1638:3: ( '!=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1639:3: '!='
            {
            match("!="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "NOT_EQUAL"
// $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1643:3: ( '/' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1644:3: '/'
            {
            match('/'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DIV"
// $ANTLR start "DIV_ASSIGN"
    public final void mDIV_ASSIGN() throws RecognitionException {
        try {
            int _type = DIV_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1648:3: ( '/=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1649:3: '/='
            {
            match("/="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DIV_ASSIGN"
// $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1653:3: ( '+' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1654:3: '+'
            {
            match('+'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "PLUS"
// $ANTLR start "PLUS_ASSIGN"
    public final void mPLUS_ASSIGN() throws RecognitionException {
        try {
            int _type = PLUS_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1658:3: ( '+=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1659:3: '+='
            {
            match("+="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "PLUS_ASSIGN"
// $ANTLR start "INC"
    public final void mINC() throws RecognitionException {
        try {
            int _type = INC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1663:3: ( '++' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1664:3: '++'
            {
            match("++"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "INC"
// $ANTLR start "LAMBDA"
    public final void mLAMBDA() throws RecognitionException {
        try {
            int _type = LAMBDA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1668:3: ( '->' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1669:3: '->'
            {
            match("->"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LAMBDA"
// $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1673:3: ( '-' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1674:3: '-'
            {
            match('-'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "MINUS"
// $ANTLR start "MINUS_ASSIGN"
    public final void mMINUS_ASSIGN() throws RecognitionException {
        try {
            int _type = MINUS_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1678:3: ( '-=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1679:3: '-='
            {
            match("-="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "MINUS_ASSIGN"
// $ANTLR start "DEC"
    public final void mDEC() throws RecognitionException {
        try {
            int _type = DEC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1683:3: ( '--' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1684:3: '--'
            {
            match("--"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DEC"
// $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1688:3: ( '*' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1689:3: '*'
            {
            match('*'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "STAR"
// $ANTLR start "STAR_ASSIGN"
    public final void mSTAR_ASSIGN() throws RecognitionException {
        try {
            int _type = STAR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1693:3: ( '*=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1694:3: '*='
            {
            match("*="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "STAR_ASSIGN"
// $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1698:3: ( '%' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1699:3: '%'
            {
            match('%'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "MOD"
// $ANTLR start "MOD_ASSIGN"
    public final void mMOD_ASSIGN() throws RecognitionException {
        try {
            int _type = MOD_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1703:3: ( '%=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1704:3: '%='
            {
            match("%="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "MOD_ASSIGN"
// $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1708:3: ( '>' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1709:3: '>'
            {
            match('>'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "GT"
// $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1713:3: ( '<' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1714:3: '<'
            {
            match('<'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LT"
// $ANTLR start "BXOR"
    public final void mBXOR() throws RecognitionException {
        try {
            int _type = BXOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1718:3: ( '^' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1719:3: '^'
            {
            match('^'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BXOR"
// $ANTLR start "BXOR_ASSIGN"
    public final void mBXOR_ASSIGN() throws RecognitionException {
        try {
            int _type = BXOR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1723:3: ( '^=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1724:3: '^='
            {
            match("^="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BXOR_ASSIGN"
// $ANTLR start "BOR"
    public final void mBOR() throws RecognitionException {
        try {
            int _type = BOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1728:3: ( '|' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1729:3: '|'
            {
            match('|'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BOR"
// $ANTLR start "BOR_ASSIGN"
    public final void mBOR_ASSIGN() throws RecognitionException {
        try {
            int _type = BOR_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1733:3: ( '|=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1734:3: '|='
            {
            match("|="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BOR_ASSIGN"
// $ANTLR start "LOR"
    public final void mLOR() throws RecognitionException {
        try {
            int _type = LOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1738:3: ( '||' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1739:3: '||'
            {
            match("||"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LOR"
// $ANTLR start "BAND"
    public final void mBAND() throws RecognitionException {
        try {
            int _type = BAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1743:3: ( '&' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1744:3: '&'
            {
            match('&'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BAND"
// $ANTLR start "BAND_ASSIGN"
    public final void mBAND_ASSIGN() throws RecognitionException {
        try {
            int _type = BAND_ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1748:3: ( '&=' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1749:3: '&='
            {
            match("&="); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BAND_ASSIGN"
// $ANTLR start "LAND"
    public final void mLAND() throws RecognitionException {
        try {
            int _type = LAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1753:3: ( '&&' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1754:3: '&&'
            {
            match("&&"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LAND"
// $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1758:3: ( ';' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1759:3: ';'
            {
            match(';'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "SEMI"
// $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1763:3: ( '@' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1764:3: '@'
            {
            match('@'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "AT"
// $ANTLR start "ELLIPSIS"
    public final void mELLIPSIS() throws RecognitionException {
        try {
            int _type = ELLIPSIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1768:3: ( '...' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1769:3: '...'
            {
            match("..."); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "ELLIPSIS"
// $ANTLR start "REF"
    public final void mREF() throws RecognitionException {
        try {
            int _type = REF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1773:3: ( '::' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1774:3: '::'
            {
            match("::"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "REF"
// $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1778:3: ( 'new' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1779:3: 'new'
            {
            match("new"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "NEW"
// $ANTLR start "SUPER"
    public final void mSUPER() throws RecognitionException {
        try {
            int _type = SUPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1783:3: ( 'super' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1784:3: 'super'
            {
            match("super"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "SUPER"
// $ANTLR start "DEFAULT"
    public final void mDEFAULT() throws RecognitionException {
        try {
            int _type = DEFAULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1788:3: ( 'default' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1789:3: 'default'
            {
            match("default"); 
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DEFAULT"
// $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1795:3: ( ( ' ' | '\\t' | '\\f' )+ )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1796:3: ( ' ' | '\\t' | '\\f' )+
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1796:3: ( ' ' | '\\t' | '\\f' )+
            int cnt1=0;
            loop1:
            while (true) {
                int alt1=2;
                int LA1_0 = input.LA(1);
                if ( (LA1_0=='\t'||LA1_0=='\f'||LA1_0==' ') ) {
                    alt1=1;
                }
        
                switch (alt1) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                    {
                    if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }
                    }
                    break;
        
                default :
                    if ( cnt1 >= 1 ) break loop1;
                    EarlyExitException eee = new EarlyExitException(1, input);
                    throw eee;
                }
                cnt1++;
            }
        
        
               _channel = HIDDEN;
              
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "WS"
// $ANTLR start "NL"
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1812:3: ( ( ( options {greedy=true; } : '\\r\\n' | '\\r' | '\\n' ) )+ )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1813:3: ( ( options {greedy=true; } : '\\r\\n' | '\\r' | '\\n' ) )+
            {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1813:3: ( ( options {greedy=true; } : '\\r\\n' | '\\r' | '\\n' ) )+
            int cnt3=0;
            loop3:
            while (true) {
                int alt3=2;
                int LA3_0 = input.LA(1);
                if ( (LA3_0=='\n'||LA3_0=='\r') ) {
                    alt3=1;
                }
        
                switch (alt3) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1814:5: ( options {greedy=true; } : '\\r\\n' | '\\r' | '\\n' )
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1814:5: ( options {greedy=true; } : '\\r\\n' | '\\r' | '\\n' )
                    int alt2=3;
                    int LA2_0 = input.LA(1);
                    if ( (LA2_0=='\r') ) {
                        int LA2_1 = input.LA(2);
                        if ( (LA2_1=='\n') ) {
                            alt2=1;
                        }
        
                        else {
                            alt2=2;
                        }
        
                    }
                    else if ( (LA2_0=='\n') ) {
                        alt2=3;
                    }
        
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 0, input);
                        throw nvae;
                    }
        
                    switch (alt2) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1814:31: '\\r\\n'
                            {
                            match("\r\n"); 
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1815:9: '\\r'
                            {
                            match('\r'); 
                            }
                            break;
                        case 3 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1816:9: '\\n'
                            {
                            match('\n'); 
                            }
                            break;
        
                    }
        
                    }
                    break;
        
                default :
                    if ( cnt3 >= 1 ) break loop3;
                    EarlyExitException eee = new EarlyExitException(3, input);
                    throw eee;
                }
                cnt3++;
            }
        
        
               _channel = HIDDEN;
              
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "NL"
// $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
        
            boolean isJavaDoc = false;
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1829:3: ( ( '/*' ( options {greedy=false; } : . )* '*/' ) | ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' ) | '//' (~ ( '\\n' | '\\r' ) )* ) )
            int alt9=2;
            int LA9_0 = input.LA(1);
            if ( (LA9_0=='/') ) {
                int LA9_1 = input.LA(2);
                if ( (LA9_1=='*') ) {
                    alt9=1;
                }
                else if ( (LA9_1=='/') ) {
                    alt9=2;
                }
        
                else {
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
            }
        
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);
                throw nvae;
            }
        
            switch (alt9) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1830:3: ( '/*' ( options {greedy=false; } : . )* '*/' )
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1830:3: ( '/*' ( options {greedy=false; } : . )* '*/' )
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1830:4: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 
        
        
                            if ((char) input.LA(1) == '*') {
                                isJavaDoc = true;
                            }
                           
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1836:5: ( options {greedy=false; } : . )*
                    loop4:
                    while (true) {
                        int alt4=2;
                        int LA4_0 = input.LA(1);
                        if ( (LA4_0=='*') ) {
                            int LA4_1 = input.LA(2);
                            if ( (LA4_1=='/') ) {
                                alt4=2;
                            }
                            else if ( ((LA4_1 >= '\u0000' && LA4_1 <= '.')||(LA4_1 >= '0' && LA4_1 <= '\uFFFF')) ) {
                                alt4=1;
                            }
        
                        }
                        else if ( ((LA4_0 >= '\u0000' && LA4_0 <= ')')||(LA4_0 >= '+' && LA4_0 <= '\uFFFF')) ) {
                            alt4=1;
                        }
        
                        switch (alt4) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1836:31: .
                            {
                            matchAny(); 
                            }
                            break;
        
                        default :
                            break loop4;
                        }
                    }
        
                    match("*/"); 
        
        
                                                           if (isJavaDoc == true) {
                                                               _type = JAVADOC;
                                                           }
                                                           _channel = HIDDEN;
                                                          
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1844:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' ) | '//' (~ ( '\\n' | '\\r' ) )* )
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1844:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' ) | '//' (~ ( '\\n' | '\\r' ) )* )
                    int alt8=2;
                    alt8 = dfa8.predict(input);
                    switch (alt8) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1845:5: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' )
                            {
                            match("//"); 
        
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1846:5: (~ ( '\\n' | '\\r' ) )*
                            loop5:
                            while (true) {
                                int alt5=2;
                                int LA5_0 = input.LA(1);
                                if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\t')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\uFFFF')) ) {
                                    alt5=1;
                                }
        
                                switch (alt5) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                                    {
                                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                                        input.consume();
                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;
                                    }
                                    }
                                    break;
        
                                default :
                                    break loop5;
                                }
                            }
        
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1850:5: ( '\\r\\n' | '\\r' | '\\n' )
                            int alt6=3;
                            int LA6_0 = input.LA(1);
                            if ( (LA6_0=='\r') ) {
                                int LA6_1 = input.LA(2);
                                if ( (LA6_1=='\n') ) {
                                    alt6=1;
                                }
        
                                else {
                                    alt6=2;
                                }
        
                            }
                            else if ( (LA6_0=='\n') ) {
                                alt6=3;
                            }
        
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 0, input);
                                throw nvae;
                            }
        
                            switch (alt6) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1851:7: '\\r\\n'
                                    {
                                    match("\r\n"); 
        
                                    }
                                    break;
                                case 2 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1852:9: '\\r'
                                    {
                                    match('\r'); 
                                    }
                                    break;
                                case 3 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1853:9: '\\n'
                                    {
                                    match('\n'); 
                                    }
                                    break;
        
                            }
        
        
                                 _channel = HIDDEN;
                                
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1859:7: '//' (~ ( '\\n' | '\\r' ) )*
                            {
                            match("//"); 
        
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1860:5: (~ ( '\\n' | '\\r' ) )*
                            loop7:
                            while (true) {
                                int alt7=2;
                                int LA7_0 = input.LA(1);
                                if ( ((LA7_0 >= '\u0000' && LA7_0 <= '\t')||(LA7_0 >= '\u000B' && LA7_0 <= '\f')||(LA7_0 >= '\u000E' && LA7_0 <= '\uFFFF')) ) {
                                    alt7=1;
                                }
        
                                switch (alt7) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                                    {
                                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                                        input.consume();
                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;
                                    }
                                    }
                                    break;
        
                                default :
                                    break loop7;
                                }
                            }
        
        
                                 _channel = HIDDEN;
                                
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "COMMENT"
// $ANTLR start "CHAR_LITERAL"
    public final void mCHAR_LITERAL() throws RecognitionException {
        try {
            int _type = CHAR_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1874:3: ( '\\'' ( ESC |~ '\\'' ) '\\'' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1875:3: '\\'' ( ESC |~ '\\'' ) '\\''
            {
            match('\''); 
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1876:3: ( ESC |~ '\\'' )
            int alt10=2;
            int LA10_0 = input.LA(1);
            if ( (LA10_0=='\\') ) {
                int LA10_1 = input.LA(2);
                if ( (LA10_1=='\"'||(LA10_1 >= '0' && LA10_1 <= '7')||LA10_1=='\\'||LA10_1=='b'||LA10_1=='f'||LA10_1=='n'||LA10_1=='r'||(LA10_1 >= 't' && LA10_1 <= 'u')) ) {
                    alt10=1;
                }
                else if ( (LA10_1=='\'') ) {
                    int LA10_4 = input.LA(3);
                    if ( (LA10_4=='\'') ) {
                        alt10=1;
                    }
        
                    else {
                        alt10=2;
                    }
        
                }
        
                else {
                    int nvaeMark = input.mark();
                    try {
                        input.consume();
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 1, input);
                        throw nvae;
                    } finally {
                        input.rewind(nvaeMark);
                    }
                }
        
            }
            else if ( ((LA10_0 >= '\u0000' && LA10_0 <= '&')||(LA10_0 >= '(' && LA10_0 <= '[')||(LA10_0 >= ']' && LA10_0 <= '\uFFFF')) ) {
                alt10=2;
            }
        
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);
                throw nvae;
            }
        
            switch (alt10) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1877:5: ESC
                    {
                    mESC(); 
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1878:7: ~ '\\''
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }
                    }
                    break;
        
            }
        
            match('\''); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "CHAR_LITERAL"
// $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1886:3: ( '\"' ( ESC |~ ( '\"' | '\\\\' ) )* '\"' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1887:3: '\"' ( ESC |~ ( '\"' | '\\\\' ) )* '\"'
            {
            match('\"'); 
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1888:3: ( ESC |~ ( '\"' | '\\\\' ) )*
            loop11:
            while (true) {
                int alt11=3;
                int LA11_0 = input.LA(1);
                if ( (LA11_0=='\\') ) {
                    alt11=1;
                }
                else if ( ((LA11_0 >= '\u0000' && LA11_0 <= '!')||(LA11_0 >= '#' && LA11_0 <= '[')||(LA11_0 >= ']' && LA11_0 <= '\uFFFF')) ) {
                    alt11=2;
                }
        
                switch (alt11) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1889:5: ESC
                    {
                    mESC(); 
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1891:5: ~ ( '\"' | '\\\\' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }
                    }
                    break;
        
                default :
                    break loop11;
                }
            }
        
            match('\"'); 
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "STRING_LITERAL"
// $ANTLR start "ESC"
    public final void mESC() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1909:3: ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\\'' | '\"' | '\\\\' | ( 'u' )+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT | '0' .. '3' ( options {greedy=true; } : '0' .. '7' ( options {greedy=true; } : '0' .. '7' )? )? | '4' .. '7' ( options {greedy=true; } : '0' .. '7' )? ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1910:3: '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\\'' | '\"' | '\\\\' | ( 'u' )+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT | '0' .. '3' ( options {greedy=true; } : '0' .. '7' ( options {greedy=true; } : '0' .. '7' )? )? | '4' .. '7' ( options {greedy=true; } : '0' .. '7' )? )
            {
            match('\\'); 
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1911:3: ( 'n' | 'r' | 't' | 'b' | 'f' | '\\'' | '\"' | '\\\\' | ( 'u' )+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT | '0' .. '3' ( options {greedy=true; } : '0' .. '7' ( options {greedy=true; } : '0' .. '7' )? )? | '4' .. '7' ( options {greedy=true; } : '0' .. '7' )? )
            int alt16=11;
            switch ( input.LA(1) ) {
            case 'n':
                {
                alt16=1;
                }
                break;
            case 'r':
                {
                alt16=2;
                }
                break;
            case 't':
                {
                alt16=3;
                }
                break;
            case 'b':
                {
                alt16=4;
                }
                break;
            case 'f':
                {
                alt16=5;
                }
                break;
            case '\'':
                {
                alt16=6;
                }
                break;
            case '\"':
                {
                alt16=7;
                }
                break;
            case '\\':
                {
                alt16=8;
                }
                break;
            case 'u':
                {
                alt16=9;
                }
                break;
            case '0':
            case '1':
            case '2':
            case '3':
                {
                alt16=10;
                }
                break;
            case '4':
            case '5':
            case '6':
            case '7':
                {
                alt16=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);
                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1912:5: 'n'
                    {
                    match('n'); 
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1913:7: 'r'
                    {
                    match('r'); 
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1914:7: 't'
                    {
                    match('t'); 
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1915:7: 'b'
                    {
                    match('b'); 
                    }
                    break;
                case 5 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1916:7: 'f'
                    {
                    match('f'); 
                    }
                    break;
                case 6 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1917:7: '\\''
                    {
                    match('\''); 
                    }
                    break;
                case 7 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1918:7: '\"'
                    {
                    match('\"'); 
                    }
                    break;
                case 8 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1919:7: '\\\\'
                    {
                    match('\\'); 
                    }
                    break;
                case 9 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1920:7: ( 'u' )+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1920:7: ( 'u' )+
                    int cnt12=0;
                    loop12:
                    while (true) {
                        int alt12=2;
                        int LA12_0 = input.LA(1);
                        if ( (LA12_0=='u') ) {
                            alt12=1;
                        }
        
                        switch (alt12) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1920:8: 'u'
                            {
                            match('u'); 
                            }
                            break;
        
                        default :
                            if ( cnt12 >= 1 ) break loop12;
                            EarlyExitException eee = new EarlyExitException(12, input);
                            throw eee;
                        }
                        cnt12++;
                    }
        
                    mHEX_DIGIT(); 
        
                    mHEX_DIGIT(); 
        
                    mHEX_DIGIT(); 
        
                    mHEX_DIGIT(); 
        
                    }
                    break;
                case 10 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1921:7: '0' .. '3' ( options {greedy=true; } : '0' .. '7' ( options {greedy=true; } : '0' .. '7' )? )?
                    {
                    matchRange('0','3'); 
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1921:16: ( options {greedy=true; } : '0' .. '7' ( options {greedy=true; } : '0' .. '7' )? )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);
                    if ( ((LA14_0 >= '0' && LA14_0 <= '7')) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1921:41: '0' .. '7' ( options {greedy=true; } : '0' .. '7' )?
                            {
                            matchRange('0','7'); 
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1921:50: ( options {greedy=true; } : '0' .. '7' )?
                            int alt13=2;
                            int LA13_0 = input.LA(1);
                            if ( ((LA13_0 >= '0' && LA13_0 <= '7')) ) {
                                alt13=1;
                            }
                            switch (alt13) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1921:75: '0' .. '7'
                                    {
                                    matchRange('0','7'); 
                                    }
                                    break;
        
                            }
        
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 11 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1922:7: '4' .. '7' ( options {greedy=true; } : '0' .. '7' )?
                    {
                    matchRange('4','7'); 
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1922:16: ( options {greedy=true; } : '0' .. '7' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);
                    if ( ((LA15_0 >= '0' && LA15_0 <= '7')) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1922:41: '0' .. '7'
                            {
                            matchRange('0','7'); 
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
        
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "ESC"
// $ANTLR start "BIN_DIGIT"
    public final void mBIN_DIGIT() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1930:3: ( '0' .. '1' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '1') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BIN_DIGIT"
// $ANTLR start "BIN_NUM"
    public final void mBIN_NUM() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1936:3: ( BIN_DIGIT ( options {greedy=true; } : BIN_DIGIT | '_' )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1937:3: BIN_DIGIT ( options {greedy=true; } : BIN_DIGIT | '_' )*
            {
            mBIN_DIGIT(); 
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1938:3: ( options {greedy=true; } : BIN_DIGIT | '_' )*
            loop17:
            while (true) {
                int alt17=3;
                int LA17_0 = input.LA(1);
                if ( ((LA17_0 >= '0' && LA17_0 <= '1')) ) {
                    alt17=1;
                }
                else if ( (LA17_0=='_') ) {
                    alt17=2;
                }
        
                switch (alt17) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1938:29: BIN_DIGIT
                    {
                    mBIN_DIGIT(); 
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1939:7: '_'
                    {
                    match('_'); 
                    }
                    break;
        
                default :
                    break loop17;
                }
            }
        
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "BIN_NUM"
// $ANTLR start "DEC_DIGIT"
    public final void mDEC_DIGIT() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1947:3: ( '0' .. '9' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DEC_DIGIT"
// $ANTLR start "DEC_NUM"
    public final void mDEC_NUM() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1953:3: ( DEC_DIGIT ( options {greedy=true; } : DEC_DIGIT | '_' )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1954:3: DEC_DIGIT ( options {greedy=true; } : DEC_DIGIT | '_' )*
            {
            mDEC_DIGIT(); 
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1955:3: ( options {greedy=true; } : DEC_DIGIT | '_' )*
            loop18:
            while (true) {
                int alt18=3;
                int LA18_0 = input.LA(1);
                if ( ((LA18_0 >= '0' && LA18_0 <= '9')) ) {
                    alt18=1;
                }
                else if ( (LA18_0=='_') ) {
                    alt18=2;
                }
        
                switch (alt18) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1955:29: DEC_DIGIT
                    {
                    mDEC_DIGIT(); 
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1956:7: '_'
                    {
                    match('_'); 
                    }
                    break;
        
                default :
                    break loop18;
                }
            }
        
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DEC_NUM"
// $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1964:3: ( ( '0' .. '9' | 'A' .. 'F' | 'a' .. 'f' ) )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "HEX_DIGIT"
// $ANTLR start "HEX_NUM"
    public final void mHEX_NUM() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1974:3: ( HEX_DIGIT ( options {greedy=true; } : HEX_DIGIT | '_' )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1975:3: HEX_DIGIT ( options {greedy=true; } : HEX_DIGIT | '_' )*
            {
            mHEX_DIGIT(); 
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1976:3: ( options {greedy=true; } : HEX_DIGIT | '_' )*
            loop19:
            while (true) {
                int alt19=3;
                int LA19_0 = input.LA(1);
                if ( ((LA19_0 >= '0' && LA19_0 <= '9')||(LA19_0 >= 'A' && LA19_0 <= 'F')||(LA19_0 >= 'a' && LA19_0 <= 'f')) ) {
                    alt19=1;
                }
                else if ( (LA19_0=='_') ) {
                    alt19=2;
                }
        
                switch (alt19) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1976:29: HEX_DIGIT
                    {
                    mHEX_DIGIT(); 
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1977:7: '_'
                    {
                    match('_'); 
                    }
                    break;
        
                default :
                    break loop19;
                }
            }
        
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "HEX_NUM"
// $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1983:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u00FF' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u00FF' | '_' | '0' .. '9' | '$' )* )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1984:3: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u00FF' | '_' | '$' ) ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u00FF' | '_' | '0' .. '9' | '$' )*
            {
            if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u00FF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1993:3: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u00FF' | '_' | '0' .. '9' | '$' )*
            loop20:
            while (true) {
                int alt20=2;
                int LA20_0 = input.LA(1);
                if ( (LA20_0=='$'||(LA20_0 >= '0' && LA20_0 <= '9')||(LA20_0 >= 'A' && LA20_0 <= 'Z')||LA20_0=='_'||(LA20_0 >= 'a' && LA20_0 <= 'z')||(LA20_0 >= '\u00C0' && LA20_0 <= '\u00D6')||(LA20_0 >= '\u00D8' && LA20_0 <= '\u00F6')||(LA20_0 >= '\u00F8' && LA20_0 <= '\u00FF')) ) {
                    alt20=1;
                }
        
                switch (alt20) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                    {
                    if ( input.LA(1)=='$'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u00FF') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }
                    }
                    break;
        
                default :
                    break loop20;
                }
            }
        
            }
        
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "IDENT"
// $ANTLR start "NUM_LITERAL"
    public final void mNUM_LITERAL() throws RecognitionException {
        try {
            int _type = NUM_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2008:3: ( '.' DEC_NUM ( EXPONENT )? ( FLOAT_SUFFIX )? | ( '0x' | '0X' ) ( HEX_NUM )* ( LONG_SUFFIX | '.' ( HEX_NUM )* ( 'p' | 'P' ) ( '+' | '-' )? DEC_NUM ( FLOAT_SUFFIX )? )? | ( '0b' | '0B' ) BIN_NUM | DEC_NUM ( DEC_END )? )
            int alt31=4;
            switch ( input.LA(1) ) {
            case '.':
                {
                alt31=1;
                }
                break;
            case '0':
                {
                switch ( input.LA(2) ) {
                case 'X':
                case 'x':
                    {
                    alt31=2;
                    }
                    break;
                case 'B':
                case 'b':
                    {
                    alt31=3;
                    }
                    break;
                default:
                    alt31=4;
                }
                }
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                alt31=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);
                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2009:3: '.' DEC_NUM ( EXPONENT )? ( FLOAT_SUFFIX )?
                    {
                    match('.'); 
                    mDEC_NUM(); 
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2009:15: ( EXPONENT )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);
                    if ( (LA21_0=='E'||LA21_0=='e') ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2009:15: EXPONENT
                            {
                            mEXPONENT(); 
        
                            }
                            break;
        
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2009:25: ( FLOAT_SUFFIX )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);
                    if ( (LA22_0=='D'||LA22_0=='F'||LA22_0=='d'||LA22_0=='f') ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2011:3: ( '0x' | '0X' ) ( HEX_NUM )* ( LONG_SUFFIX | '.' ( HEX_NUM )* ( 'p' | 'P' ) ( '+' | '-' )? DEC_NUM ( FLOAT_SUFFIX )? )?
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2011:3: ( '0x' | '0X' )
                    int alt23=2;
                    int LA23_0 = input.LA(1);
                    if ( (LA23_0=='0') ) {
                        int LA23_1 = input.LA(2);
                        if ( (LA23_1=='x') ) {
                            alt23=1;
                        }
                        else if ( (LA23_1=='X') ) {
                            alt23=2;
                        }
        
                        else {
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 23, 1, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
        
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);
                        throw nvae;
                    }
        
                    switch (alt23) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2012:5: '0x'
                            {
                            match("0x"); 
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2013:7: '0X'
                            {
                            match("0X"); 
        
                            }
                            break;
        
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2015:3: ( HEX_NUM )*
                    loop24:
                    while (true) {
                        int alt24=2;
                        int LA24_0 = input.LA(1);
                        if ( ((LA24_0 >= '0' && LA24_0 <= '9')||(LA24_0 >= 'A' && LA24_0 <= 'F')||(LA24_0 >= 'a' && LA24_0 <= 'f')) ) {
                            alt24=1;
                        }
        
                        switch (alt24) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2015:3: HEX_NUM
                            {
                            mHEX_NUM(); 
        
                            }
                            break;
        
                        default :
                            break loop24;
                        }
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2016:3: ( LONG_SUFFIX | '.' ( HEX_NUM )* ( 'p' | 'P' ) ( '+' | '-' )? DEC_NUM ( FLOAT_SUFFIX )? )?
                    int alt28=3;
                    int LA28_0 = input.LA(1);
                    if ( (LA28_0=='L'||LA28_0=='l') ) {
                        alt28=1;
                    }
                    else if ( (LA28_0=='.') ) {
                        alt28=2;
                    }
                    switch (alt28) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2017:5: LONG_SUFFIX
                            {
                            mLONG_SUFFIX(); 
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2018:7: '.' ( HEX_NUM )* ( 'p' | 'P' ) ( '+' | '-' )? DEC_NUM ( FLOAT_SUFFIX )?
                            {
                            match('.'); 
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2018:11: ( HEX_NUM )*
                            loop25:
                            while (true) {
                                int alt25=2;
                                int LA25_0 = input.LA(1);
                                if ( ((LA25_0 >= '0' && LA25_0 <= '9')||(LA25_0 >= 'A' && LA25_0 <= 'F')||(LA25_0 >= 'a' && LA25_0 <= 'f')) ) {
                                    alt25=1;
                                }
        
                                switch (alt25) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2018:11: HEX_NUM
                                    {
                                    mHEX_NUM(); 
        
                                    }
                                    break;
        
                                default :
                                    break loop25;
                                }
                            }
        
                            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2023:5: ( '+' | '-' )?
                            int alt26=2;
                            int LA26_0 = input.LA(1);
                            if ( (LA26_0=='+'||LA26_0=='-') ) {
                                alt26=1;
                            }
                            switch (alt26) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                                    {
                                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                        input.consume();
                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;
                                    }
                                    }
                                    break;
        
                            }
        
                            mDEC_NUM(); 
        
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2027:13: ( FLOAT_SUFFIX )?
                            int alt27=2;
                            int LA27_0 = input.LA(1);
                            if ( (LA27_0=='D'||LA27_0=='F'||LA27_0=='d'||LA27_0=='f') ) {
                                alt27=1;
                            }
                            switch (alt27) {
                                case 1 :
                                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                                    {
                                    if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                        input.consume();
                                    }
                                    else {
                                        MismatchedSetException mse = new MismatchedSetException(null,input);
                                        recover(mse);
                                        throw mse;
                                    }
                                    }
                                    break;
        
                            }
        
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2030:3: ( '0b' | '0B' ) BIN_NUM
                    {
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2030:3: ( '0b' | '0B' )
                    int alt29=2;
                    int LA29_0 = input.LA(1);
                    if ( (LA29_0=='0') ) {
                        int LA29_1 = input.LA(2);
                        if ( (LA29_1=='b') ) {
                            alt29=1;
                        }
                        else if ( (LA29_1=='B') ) {
                            alt29=2;
                        }
        
                        else {
                            int nvaeMark = input.mark();
                            try {
                                input.consume();
                                NoViableAltException nvae =
                                    new NoViableAltException("", 29, 1, input);
                                throw nvae;
                            } finally {
                                input.rewind(nvaeMark);
                            }
                        }
        
                    }
        
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 0, input);
                        throw nvae;
                    }
        
                    switch (alt29) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2031:5: '0b'
                            {
                            match("0b"); 
        
                            }
                            break;
                        case 2 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2032:7: '0B'
                            {
                            match("0B"); 
        
                            }
                            break;
        
                    }
        
                    mBIN_NUM(); 
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2035:5: DEC_NUM ( DEC_END )?
                    {
                    mDEC_NUM(); 
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2035:13: ( DEC_END )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);
                    if ( (LA30_0=='.'||(LA30_0 >= 'D' && LA30_0 <= 'F')||LA30_0=='L'||(LA30_0 >= 'd' && LA30_0 <= 'f')||LA30_0=='l') ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2035:13: DEC_END
                            {
                            mDEC_END(); 
        
                            }
                            break;
        
                    }
        
                    }
                    break;
        
            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "NUM_LITERAL"
// $ANTLR start "DEC_END"
    public final void mDEC_END() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2041:3: ( LONG_SUFFIX | '.' ( DEC_NUM )? ( EXPONENT )? ( FLOAT_SUFFIX )? | EXPONENT ( FLOAT_SUFFIX )? | FLOAT_SUFFIX )
            int alt36=4;
            switch ( input.LA(1) ) {
            case 'L':
            case 'l':
                {
                alt36=1;
                }
                break;
            case '.':
                {
                alt36=2;
                }
                break;
            case 'E':
            case 'e':
                {
                alt36=3;
                }
                break;
            case 'D':
            case 'F':
            case 'd':
            case 'f':
                {
                alt36=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);
                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2042:3: LONG_SUFFIX
                    {
                    mLONG_SUFFIX(); 
        
                    }
                    break;
                case 2 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2043:5: '.' ( DEC_NUM )? ( EXPONENT )? ( FLOAT_SUFFIX )?
                    {
                    match('.'); 
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2043:9: ( DEC_NUM )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);
                    if ( ((LA32_0 >= '0' && LA32_0 <= '9')) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2043:9: DEC_NUM
                            {
                            mDEC_NUM(); 
        
                            }
                            break;
        
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2043:18: ( EXPONENT )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);
                    if ( (LA33_0=='E'||LA33_0=='e') ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2043:18: EXPONENT
                            {
                            mEXPONENT(); 
        
                            }
                            break;
        
                    }
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2043:28: ( FLOAT_SUFFIX )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);
                    if ( (LA34_0=='D'||LA34_0=='F'||LA34_0=='d'||LA34_0=='f') ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 3 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2044:5: EXPONENT ( FLOAT_SUFFIX )?
                    {
                    mEXPONENT(); 
        
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2044:14: ( FLOAT_SUFFIX )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);
                    if ( (LA35_0=='D'||LA35_0=='F'||LA35_0=='d'||LA35_0=='f') ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }
                            }
                            break;
        
                    }
        
                    }
                    break;
                case 4 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2045:5: FLOAT_SUFFIX
                    {
                    mFLOAT_SUFFIX(); 
        
                    }
                    break;
        
            }
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "DEC_END"
// $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2050:3: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2051:3: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2055:3: ( '+' | '-' )?
            int alt37=2;
            int LA37_0 = input.LA(1);
            if ( (LA37_0=='+'||LA37_0=='-') ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }
                    }
                    break;
        
            }
        
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2059:3: ( '0' .. '9' )+
            int cnt38=0;
            loop38:
            while (true) {
                int alt38=2;
                int LA38_0 = input.LA(1);
                if ( ((LA38_0 >= '0' && LA38_0 <= '9')) ) {
                    alt38=1;
                }
        
                switch (alt38) {
                case 1 :
                    // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
                    {
                    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }
                    }
                    break;
        
                default :
                    if ( cnt38 >= 1 ) break loop38;
                    EarlyExitException eee = new EarlyExitException(38, input);
                    throw eee;
                }
                cnt38++;
            }
        
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "EXPONENT"
// $ANTLR start "FLOAT_SUFFIX"
    public final void mFLOAT_SUFFIX() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2064:3: ( 'f' | 'F' | 'd' | 'D' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "FLOAT_SUFFIX"
// $ANTLR start "LONG_SUFFIX"
    public final void mLONG_SUFFIX() throws RecognitionException {
        try {
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:2073:3: ( 'l' | 'L' )
            // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }
            }
        
        }
        finally {
            // do for sure before leaving
        }
    }

// $ANTLR end "LONG_SUFFIX"
    @Override
    public void mTokens() throws RecognitionException {
        // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:8: ( RETURN | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | T__161 | T__162 | T__163 | T__164 | T__165 | T__166 | T__167 | T__168 | T__169 | T__170 | T__171 | T__172 | T__173 | T__174 | T__175 | T__176 | T__177 | T__178 | T__179 | T__180 | QUESTION | LPAREN | RPAREN | LBRACK | RBRACK | LCURLY | RCURLY | COLON | COMMA | DOT | ASSIGN | EQUAL | LNOT | BNOT | NOT_EQUAL | DIV | DIV_ASSIGN | PLUS | PLUS_ASSIGN | INC | LAMBDA | MINUS | MINUS_ASSIGN | DEC | STAR | STAR_ASSIGN | MOD | MOD_ASSIGN | GT | LT | BXOR | BXOR_ASSIGN | BOR | BOR_ASSIGN | LOR | BAND | BAND_ASSIGN | LAND | SEMI | AT | ELLIPSIS | REF | NEW | SUPER | DEFAULT | WS | NL | COMMENT | CHAR_LITERAL | STRING_LITERAL | IDENT | NUM_LITERAL )
        int alt39=101;
        alt39 = dfa39.predict(input);
        switch (alt39) {
            case 1 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:10: RETURN
                {
                mRETURN(); 
        
                }
                break;
            case 2 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:17: T__133
                {
                mT__133(); 
        
                }
                break;
            case 3 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:24: T__134
                {
                mT__134(); 
        
                }
                break;
            case 4 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:31: T__135
                {
                mT__135(); 
        
                }
                break;
            case 5 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:38: T__136
                {
                mT__136(); 
        
                }
                break;
            case 6 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:45: T__137
                {
                mT__137(); 
        
                }
                break;
            case 7 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:52: T__138
                {
                mT__138(); 
        
                }
                break;
            case 8 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:59: T__139
                {
                mT__139(); 
        
                }
                break;
            case 9 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:66: T__140
                {
                mT__140(); 
        
                }
                break;
            case 10 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:73: T__141
                {
                mT__141(); 
        
                }
                break;
            case 11 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:80: T__142
                {
                mT__142(); 
        
                }
                break;
            case 12 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:87: T__143
                {
                mT__143(); 
        
                }
                break;
            case 13 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:94: T__144
                {
                mT__144(); 
        
                }
                break;
            case 14 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:101: T__145
                {
                mT__145(); 
        
                }
                break;
            case 15 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:108: T__146
                {
                mT__146(); 
        
                }
                break;
            case 16 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:115: T__147
                {
                mT__147(); 
        
                }
                break;
            case 17 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:122: T__148
                {
                mT__148(); 
        
                }
                break;
            case 18 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:129: T__149
                {
                mT__149(); 
        
                }
                break;
            case 19 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:136: T__150
                {
                mT__150(); 
        
                }
                break;
            case 20 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:143: T__151
                {
                mT__151(); 
        
                }
                break;
            case 21 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:150: T__152
                {
                mT__152(); 
        
                }
                break;
            case 22 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:157: T__153
                {
                mT__153(); 
        
                }
                break;
            case 23 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:164: T__154
                {
                mT__154(); 
        
                }
                break;
            case 24 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:171: T__155
                {
                mT__155(); 
        
                }
                break;
            case 25 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:178: T__156
                {
                mT__156(); 
        
                }
                break;
            case 26 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:185: T__157
                {
                mT__157(); 
        
                }
                break;
            case 27 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:192: T__158
                {
                mT__158(); 
        
                }
                break;
            case 28 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:199: T__159
                {
                mT__159(); 
        
                }
                break;
            case 29 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:206: T__160
                {
                mT__160(); 
        
                }
                break;
            case 30 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:213: T__161
                {
                mT__161(); 
        
                }
                break;
            case 31 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:220: T__162
                {
                mT__162(); 
        
                }
                break;
            case 32 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:227: T__163
                {
                mT__163(); 
        
                }
                break;
            case 33 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:234: T__164
                {
                mT__164(); 
        
                }
                break;
            case 34 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:241: T__165
                {
                mT__165(); 
        
                }
                break;
            case 35 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:248: T__166
                {
                mT__166(); 
        
                }
                break;
            case 36 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:255: T__167
                {
                mT__167(); 
        
                }
                break;
            case 37 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:262: T__168
                {
                mT__168(); 
        
                }
                break;
            case 38 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:269: T__169
                {
                mT__169(); 
        
                }
                break;
            case 39 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:276: T__170
                {
                mT__170(); 
        
                }
                break;
            case 40 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:283: T__171
                {
                mT__171(); 
        
                }
                break;
            case 41 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:290: T__172
                {
                mT__172(); 
        
                }
                break;
            case 42 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:297: T__173
                {
                mT__173(); 
        
                }
                break;
            case 43 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:304: T__174
                {
                mT__174(); 
        
                }
                break;
            case 44 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:311: T__175
                {
                mT__175(); 
        
                }
                break;
            case 45 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:318: T__176
                {
                mT__176(); 
        
                }
                break;
            case 46 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:325: T__177
                {
                mT__177(); 
        
                }
                break;
            case 47 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:332: T__178
                {
                mT__178(); 
        
                }
                break;
            case 48 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:339: T__179
                {
                mT__179(); 
        
                }
                break;
            case 49 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:346: T__180
                {
                mT__180(); 
        
                }
                break;
            case 50 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:353: QUESTION
                {
                mQUESTION(); 
        
                }
                break;
            case 51 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:362: LPAREN
                {
                mLPAREN(); 
        
                }
                break;
            case 52 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:369: RPAREN
                {
                mRPAREN(); 
        
                }
                break;
            case 53 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:376: LBRACK
                {
                mLBRACK(); 
        
                }
                break;
            case 54 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:383: RBRACK
                {
                mRBRACK(); 
        
                }
                break;
            case 55 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:390: LCURLY
                {
                mLCURLY(); 
        
                }
                break;
            case 56 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:397: RCURLY
                {
                mRCURLY(); 
        
                }
                break;
            case 57 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:404: COLON
                {
                mCOLON(); 
        
                }
                break;
            case 58 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:410: COMMA
                {
                mCOMMA(); 
        
                }
                break;
            case 59 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:416: DOT
                {
                mDOT(); 
        
                }
                break;
            case 60 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:420: ASSIGN
                {
                mASSIGN(); 
        
                }
                break;
            case 61 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:427: EQUAL
                {
                mEQUAL(); 
        
                }
                break;
            case 62 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:433: LNOT
                {
                mLNOT(); 
        
                }
                break;
            case 63 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:438: BNOT
                {
                mBNOT(); 
        
                }
                break;
            case 64 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:443: NOT_EQUAL
                {
                mNOT_EQUAL(); 
        
                }
                break;
            case 65 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:453: DIV
                {
                mDIV(); 
        
                }
                break;
            case 66 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:457: DIV_ASSIGN
                {
                mDIV_ASSIGN(); 
        
                }
                break;
            case 67 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:468: PLUS
                {
                mPLUS(); 
        
                }
                break;
            case 68 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:473: PLUS_ASSIGN
                {
                mPLUS_ASSIGN(); 
        
                }
                break;
            case 69 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:485: INC
                {
                mINC(); 
        
                }
                break;
            case 70 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:489: LAMBDA
                {
                mLAMBDA(); 
        
                }
                break;
            case 71 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:496: MINUS
                {
                mMINUS(); 
        
                }
                break;
            case 72 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:502: MINUS_ASSIGN
                {
                mMINUS_ASSIGN(); 
        
                }
                break;
            case 73 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:515: DEC
                {
                mDEC(); 
        
                }
                break;
            case 74 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:519: STAR
                {
                mSTAR(); 
        
                }
                break;
            case 75 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:524: STAR_ASSIGN
                {
                mSTAR_ASSIGN(); 
        
                }
                break;
            case 76 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:536: MOD
                {
                mMOD(); 
        
                }
                break;
            case 77 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:540: MOD_ASSIGN
                {
                mMOD_ASSIGN(); 
        
                }
                break;
            case 78 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:551: GT
                {
                mGT(); 
        
                }
                break;
            case 79 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:554: LT
                {
                mLT(); 
        
                }
                break;
            case 80 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:557: BXOR
                {
                mBXOR(); 
        
                }
                break;
            case 81 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:562: BXOR_ASSIGN
                {
                mBXOR_ASSIGN(); 
        
                }
                break;
            case 82 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:574: BOR
                {
                mBOR(); 
        
                }
                break;
            case 83 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:578: BOR_ASSIGN
                {
                mBOR_ASSIGN(); 
        
                }
                break;
            case 84 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:589: LOR
                {
                mLOR(); 
        
                }
                break;
            case 85 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:593: BAND
                {
                mBAND(); 
        
                }
                break;
            case 86 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:598: BAND_ASSIGN
                {
                mBAND_ASSIGN(); 
        
                }
                break;
            case 87 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:610: LAND
                {
                mLAND(); 
        
                }
                break;
            case 88 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:615: SEMI
                {
                mSEMI(); 
        
                }
                break;
            case 89 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:620: AT
                {
                mAT(); 
        
                }
                break;
            case 90 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:623: ELLIPSIS
                {
                mELLIPSIS(); 
        
                }
                break;
            case 91 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:632: REF
                {
                mREF(); 
        
                }
                break;
            case 92 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:636: NEW
                {
                mNEW(); 
        
                }
                break;
            case 93 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:640: SUPER
                {
                mSUPER(); 
        
                }
                break;
            case 94 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:646: DEFAULT
                {
                mDEFAULT(); 
        
                }
                break;
            case 95 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:654: WS
                {
                mWS(); 
        
                }
                break;
            case 96 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:657: NL
                {
                mNL(); 
        
                }
                break;
            case 97 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:660: COMMENT
                {
                mCOMMENT(); 
        
                }
                break;
            case 98 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:668: CHAR_LITERAL
                {
                mCHAR_LITERAL(); 
        
                }
                break;
            case 99 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:681: STRING_LITERAL
                {
                mSTRING_LITERAL(); 
        
                }
                break;
            case 100 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:696: IDENT
                {
                mIDENT(); 
        
                }
                break;
            case 101 :
                // com\\modeliosoft\\modelio\\javadesigner\\reverse\\antlr\\Java.g:1:702: NUM_LITERAL
                {
                mNUM_LITERAL(); 
        
                }
                break;
        
        }
    }


static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }
static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }
    protected class DFA8 extends DFA {
        public DFA8(final BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }

        @Override
        public String getDescription() {
            return "1844:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r\\n' | '\\r' | '\\n' ) | '//' (~ ( '\\n' | '\\r' ) )* )";
        }

        @Override
        public int specialStateTransition(int s, final IntStream _input) throws NoViableAltException {
            IntStream input = _input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_3 = input.LA(1);
                        s = -1;
                        if ( (LA8_3=='\n'||LA8_3=='\r') ) {s = 4;}
                        else if ( ((LA8_3 >= '\u0000' && LA8_3 <= '\t')||(LA8_3 >= '\u000B' && LA8_3 <= '\f')||(LA8_3 >= '\u000E' && LA8_3 <= '\uFFFF')) ) {s = 3;}
                        else s = 5;
                        if ( s>=0 ) return s;
                        break;
            
                    case 1 : 
                        int LA8_2 = input.LA(1);
                        s = -1;
                        if ( ((LA8_2 >= '\u0000' && LA8_2 <= '\t')||(LA8_2 >= '\u000B' && LA8_2 <= '\f')||(LA8_2 >= '\u000E' && LA8_2 <= '\uFFFF')) ) {s = 3;}
                        else if ( (LA8_2=='\n'||LA8_2=='\r') ) {s = 4;}
                        else s = 5;
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }

    }

    protected class DFA39 extends DFA {
        public DFA39(final BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }

        @Override
        public String getDescription() {
            return "1:1: Tokens : ( RETURN | T__133 | T__134 | T__135 | T__136 | T__137 | T__138 | T__139 | T__140 | T__141 | T__142 | T__143 | T__144 | T__145 | T__146 | T__147 | T__148 | T__149 | T__150 | T__151 | T__152 | T__153 | T__154 | T__155 | T__156 | T__157 | T__158 | T__159 | T__160 | T__161 | T__162 | T__163 | T__164 | T__165 | T__166 | T__167 | T__168 | T__169 | T__170 | T__171 | T__172 | T__173 | T__174 | T__175 | T__176 | T__177 | T__178 | T__179 | T__180 | QUESTION | LPAREN | RPAREN | LBRACK | RBRACK | LCURLY | RCURLY | COLON | COMMA | DOT | ASSIGN | EQUAL | LNOT | BNOT | NOT_EQUAL | DIV | DIV_ASSIGN | PLUS | PLUS_ASSIGN | INC | LAMBDA | MINUS | MINUS_ASSIGN | DEC | STAR | STAR_ASSIGN | MOD | MOD_ASSIGN | GT | LT | BXOR | BXOR_ASSIGN | BOR | BOR_ASSIGN | LOR | BAND | BAND_ASSIGN | LAND | SEMI | AT | ELLIPSIS | REF | NEW | SUPER | DEFAULT | WS | NL | COMMENT | CHAR_LITERAL | STRING_LITERAL | IDENT | NUM_LITERAL );";
        }

    }

}
