/** Java 1.7 Recognizer
 * Contributing authors:
 *    John Mitchell     johnm@non.net
 *    Terence Parr      parrt@magelang.com
 *    John Lilley       jlilley@empathy.com
 *    Scott Stanchfield thetick@magelang.com
 *    Markus Mohnen     mohnen@informatik.rwth-aachen.de
 *    Peter Williams    pete.williams@sun.com
 *    Allan Jacobs      Allan.Jacobs@eng.sun.com
 *    Steve Messick     messick@redhills.com
 *    John Pybus        john@pybus.org
 *    Modelio development team
 * This grammar is in the PUBLIC DOMAIN
 */
grammar Java;

options {
  output       = AST;
  backtrack    = true;
  memoize      = true;
  ASTLabelType = ASTTree; // type of AST nodes id ASTTree
}

tokens {
  COMPILATION_UNIT;
  MODIFIERS;
  OBJBLOCK;
  SLIST;
  METHOD_DEF;
  VARIABLE_DEF;
  INSTANCE_INIT;
  STATIC_INIT;
  TYPE;
  CLASS_DEF;
  INTERFACE_DEF;
  PACKAGE_DEF;
  ARRAY_DECLARATOR;
  EXTENDS_CLAUSE;
  IMPLEMENTS_CLAUSE;
  PARAMETERS;
  PARAMETER_DEF;
  LABELED_STAT;
  TYPECAST;
  METHOD_CALL;
  EXPR;
  ARRAY_INIT;
  IMPORT;
  UNARY_MINUS;
  UNARY_PLUS;
  CASE_GROUP;
  ELIST;
  FOR_INIT;
  FOR_CONDITION;
  FOR_ITERATOR;
  EMPTY_STAT;
  SUPER_CTOR_CALL;
  CTOR_CALL;
  COMMENTS;
  JAVADOC;
  TRAILING_COMMENT;
  TYPE_ARGS;
  TYPE_PARAMS;
  WILDCARD;
  FORMAL_TYPE_PARAMS;
  TYPE_PARAM;
  ENUM_DEF;
  ENUM_CONST;
  ANNOTATION_DEF;
  ANNOTATION_MEMBER_DEF;
  ANNOTATION;
  ANNOTATION_DEFAULT;
  ANNOTATION_INIT_VALUE;
  ANNOTATION_INIT_LIST;
  ANNOTATION_INIT_MEMBER;
  ANNOTATION_ARRAY_INIT;
  SR_ASSIGN;
  BSR_ASSIGN;
  GE;
  SL_ASSIGN;
  LE;
  SL;
  SR;
  BSR;
  GENERIC_EXTENDS;
  STATIC;
  BUILT_IN;
  THROWS;
  RETURN = 'return';
  VOID;
}

@header {
package com.modeliosoft.modelio.javadesigner.reverse.antlr;
   import com.modeliosoft.modelio.javadesigner.reverse.antlr.ASTTree;
}

@members {
/**
 *  Look for comments tokens above 'start' token and attach them to 'node'
 *  Existing comments tokens attached to 'node' are kept.
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
 *  Look for comment tokens after 'start' token but on the same line and attach them to 'node'
 *  Existing comment tokens attached to 'node' are kept.
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
 *  Look for comment tokens underneath 'start' token and attach them to 'node'
 *  Existing comment tokens attached to 'node' are kept.
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
 *  Create a token with an identifiable text for debug purpose
 *  (Note : prefer not use the constructor CommonToken(type, text) because
 *   it set the channel to the default one)
 */
private Token createToken(int type) {
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
}
/* @rulecatch { }  // previously  options { defaultErrorHandler = false} */

/* Declarations for the lexer */

@lexer::header {
package com.modeliosoft.modelio.javadesigner.reverse.antlr;
}

@lexer::members {
//  @Override
//  public String getErrorMessage(RecognitionException e, String[] tokenNames) {
//     return super.getErrorMessage(e, tokenNames);
//  }
}

/* End of lexer declarations */

// Compilation Unit: In Java, this is a single file.  This is the start
//   rule for this parser

compilationUnit
@after {
// JavaTop
attachAboveComments($compilationUnit.tree, $compilationUnit.start);
// JavaBottom
attachUnderneathComments($compilationUnit.tree, $compilationUnit.stop);
}
  :
  pd=packageDefinition? impd+=importDefinition* td+=typeDefinition*
    -> ^(COMPILATION_UNIT $pd? $impd* $td* )
  ;

// Definition of an annotation type (JSR 175)

annotationTypeDefinition[Object ama]
  :
  AT 'interface' IDENT ab=annotationBlock
    -> ^(ANNOTATION_DEF IDENT {$ama} $ab )
  ;

// This is the body of an annotation type. Only inner type definitions and
// members (which use a notation similar to methods) are allowed.

annotationBlock
@after {
attachAboveComments($annotationBlock.tree, $rc);
}
  :
  LCURLY
  (
    annotationField
    | SEMI
  )*
  rc=RCURLY
    -> ^(OBJBLOCK annotationField* )
  ;

// Field in an annotation definition
// (Note : the syntaxic predicate above is to work around
// an antlr3.4.1 generation error that produces non compilable code)

annotationField
@after {
// attach comments & javadoc above the annotation.
// note eventhough there is no annotation in source, ann is still valid.
attachAboveComments($annotationField.tree, $annotationField.start);
}
  :
  (
    ama=annotmodannot
    (
      def=enumDefinition[$ama.tree] // inner enum
      | def=classDefinition[$ama.tree] // inner class
      | def=interfaceDefinition[$ama.tree] // inner interface
      | def=annotationTypeDefinition[$ama.tree] // inner annotation type
      | (annotationMethod[null]) => def=annotationMethod[$ama.tree] // annotation method
      | def=attributeDefinitions[$ama.tree] // attribute
    )
  )
    -> $def
  ;

annotationMethod[Object ama]
@after {
attachTrailingComments($annotationMethod.tree, $sm);
}
  :
  ts=typeSpec id=IDENT LPAREN RPAREN rt=declaratorBrackets[$ts.tree] dv=defaultValue? sm=SEMI
    -> ^(ANNOTATION_MEMBER_DEF {$ama} $rt $id $dv? )
  ;

// Annotation method may have optional default values.

defaultValue
  :
  dflt='default' anv=annotationMemberValue 
                                          {
                                           $anv.tree.setSourceCode(this.getSource(this.adjustStartToken($anv.start),
                                           		$anv.stop));
                                          }
    -> ^(ANNOTATION_DEFAULT annotationMemberValue )
  ;

// annotations can appear before or after modifiers
annotmodannot
   :
   annotation* modifiers? annotation*
   ;
   
annotation
@after {
$annotation.tree.setSourceCode($text);
}
  :
  at=AT identifier ani=annotationInit
    -> ^(ANNOTATION[$at] identifier $ani? )
  ;

// The initialization (list of assignments, single value, or nothing).

annotationInit
  :
  (
    lp=LPAREN
    (
      annotationMemberInit (COMMA annotationMemberInit)*
        -> ^(ANNOTATION_INIT_LIST[$lp] annotationMemberInit* )
      | annotationMemberValue
        -> ^(ANNOTATION_INIT_VALUE[$lp] annotationMemberValue )
      |
    /* empty */
    )
    RPAREN
  )
  |
  /* empty */
  ;

annotationMemberInit
  :
  IDENT ASSIGN annotationMemberValue
    -> ^(ANNOTATION_INIT_MEMBER IDENT annotationMemberValue )
  ;

annotationMemberValue
@after {
$annotationMemberValue.tree.setSourceCode($text);
}
  :
    annotation
  | conditionalExpression[true] -> ^(EXPR conditionalExpression )
  | annotationMemberArrayInitializer
  ;

// This is an initializer used to set up an array as an annotation member
// value.

annotationMemberArrayInitializer
  :
  lc=LCURLY (annotationMemberValue (COMMA annotationMemberValue)*)? COMMA? RCURLY
    -> ^(ANNOTATION_ARRAY_INIT[$lc] annotationMemberValue* )
  ;

enumDefinition[Object ama]
  :
  'enum' id=IDENT
    // it might implement some interfaces...
  ic=implementsClause?
    // now parse the body of the enum
  eb=enumBlock
    -> ^(ENUM_DEF $id $ic? {$ama} $eb)
  ;

// This is the body of an enumeration.  It can contain a list of comma
// separated identifiers (the enum values), and optionally, seperated by a
// semicolon, some declarations like in a class at the end.
// The values of the enumeration may be annotated.
// Note : weird enum block are accepted (checked with eclipse) like {;} or {,;}

enumBlock
@after {
attachAboveComments($enumBlock.tree, $rc);
}
  :
  LCURLY (ec=enumConst (c=COMMA 
                               {
                                attachTrailingComments($ec.tree, $c);
                               }
      ec=enumConst)*)? (c2=COMMA 
                                {
                                 attachTrailingComments($ec.tree, $c2);
                                })? // optional comma at end of value list
  (
    sem=SEMI {attachTrailingComments($ec.tree, $sem);}
    (
      field[true]
      | SEMI
    )*
  )?
  rc=RCURLY
    -> ^(OBJBLOCK enumConst* field* )
  ;

// Each enum value is in fact a class instance, and can be followed by the
// usual class declarations.

enumConst
@after {
attachAboveComments($enumConst.tree, $enumConst.start);
attachTrailingComments($enumConst.tree, $enumConst.stop);
}
  :
  ann=annotation* IDENT enumConstInit? classBlock?
    -> ^(ENUM_CONST $ann* IDENT enumConstInit? classBlock? )
  ;

// This is really a constructor invocation.

enumConstInit
@after {
$enumConstInit.tree.setSourceCode(this.getSource(
		this.adjustStartToken($al.start), this.adjustStopToken($al.stop)));
}
  :
  lp=LPAREN al=argList rp=RPAREN
    -> $al
  ;

// Package statement: 'package' followed by an identifier.

packageDefinition
  :
  an=annotation* p='package' identifier SEMI
    -> ^(PACKAGE_DEF[$p] identifier $an*)
  ;

// Import statement: import followed by a package or class name

importDefinition
  :
  i='import' (st='static' 
                         {
                          st.setType(STATIC);
                         })? identifierStar SEMI
    -> ^(IMPORT[$i] $st? identifierStar )
  ;

// A type definition in a file is either a class, an interface and annotation or an enum definition.

typeDefinition
@after {
attachAboveComments($typeDefinition.tree, $typeDefinition.start);
}
  :
  ama=annotmodannot
  (
    def=classDefinition[$ama.tree]
    | def=interfaceDefinition[$ama.tree]
    | def=annotationTypeDefinition[$ama.tree]
    | def=enumDefinition[$ama.tree]
  ) SEMI*
    -> $def
  ;

/** A declaration is the creation of a reference or primitive-type variable
 *  Create a separate Type/Var tree for each var in the var list.
 */
declaration
  :
  ama=annotmodannot t=typeSpec v=variableDefinitions[$ama.tree, $t.tree]
    -> $v
  ;

// A type specification is a type name with possible brackets afterwards
// (which would make it an array type).

typeSpec
  :
  classTypeSpec
  | builtInTypeSpec
  ;

// A class type specification is a class type with possible brackets afterwards

classTypeSpec
@init {
boolean arraySeen = false;
}
  :
  (coi=classOrInterfaceType -> $coi) 
  (lb=LBRACK RBRACK {arraySeen = true;} -> ^(ARRAY_DECLARATOR[$lb] $classTypeSpec ))*
  -> {arraySeen}? ^(TYPE $classTypeSpec)
  -> $classTypeSpec
  ;

// A builtin type specification is a builtin type with possible brackets
builtInTypeSpec
@init {
boolean arraySeen = false;
}
  :
  (bit=builtInType -> {$bit.tree.getType() != VOID}? ^(TYPE $bit)
                   -> $bit
  ) 
  (lb=LBRACK RBRACK {arraySeen = true;}  -> ^(ARRAY_DECLARATOR[$lb] $builtInTypeSpec))*
  -> {arraySeen}? ^(TYPE $builtInTypeSpec)
  -> $builtInTypeSpec
  ;

// Something simple as Klazz or complicated as Klazz<?>.SubKlazz<?>
classOrInterfaceType
@init {
// add TYPE node if a DOT has been seen to be sure to return a TYPE node but not a (TYPE (TYPE ident))
boolean dotSeen = false;
}
@after {
$classOrInterfaceType.tree.setSourceCode($text);
}
  :
  (simpleClassOrInterfaceType -> simpleClassOrInterfaceType) 
  (options {greedy=true;}: 
    d=DOT { dotSeen = true; }
    scoi=simpleClassOrInterfaceType -> ^(DOT[$d] $classOrInterfaceType $scoi ))*
    -> {dotSeen}? ^(TYPE $classOrInterfaceType)
    -> $classOrInterfaceType
  ;

simpleClassOrInterfaceType
  :
  IDENT typeArguments?
    -> ^(TYPE IDENT typeArguments? )
  ;

// The primitive types.
builtInType
  :
  (
    t='boolean'
    | t='byte'
    | t='char'
    | t='short'
    | t='int'
    | t='float'
    | t='long'
    | t='double'
  ) -> ^(BUILT_IN[$t])
  | v='void' -> ^(VOID[$v])
  ;

// a (possibly-qualified) java identifier possibly terminated by '.*'

identifierStar
  :
  identifier (DOT^ STAR)?
  ;

// A (possibly-qualified) java identifier.  We start with the first IDENT
//   and expand its name by adding dots and following IDENTS

identifier
  :
  IDENT (DOT^ IDENT)*
  ;

// A list of one or more modifiers.

modifiers
  :
  modifier+
    -> ^(MODIFIERS modifier+ )
  ;

// modifiers for Java classes, interfaces, class/instance vars and methods

modifier
  :
  'private'
  | 'public'
  | 'protected'
  | 'static'
  | 'transient'
  | 'final'
  | 'abstract'
  | 'native'
  | 'threadsafe'
  | 'synchronized'
  | 'volatile'
  | 'strictfp'
  ;

// Definition of a Java class

classDefinition[Object ama]
  :
  'class' id=IDENT fp=formalTypeParameters? // types parameters for generics
  sc=superClassClause? // optional extends clause
  ic=implementsClause? // optional implement clause
  cb=classBlock // body of the class
    -> ^(CLASS_DEF {$ama} $id $fp? $sc? $ic? $cb )
  ;

// Generic formal parameters for class, interface and operation

formalTypeParameters
  :
  lt=LT typeParameter (COMMA typeParameter)* GT
    -> ^(FORMAL_TYPE_PARAMS[$lt] typeParameter+ )
  ;

typeParameter
@after {
// Get the source code
$typeParameter.tree.setSourceCode($text);
}
  :
  id=IDENT tp=typeParameterBound?
    -> ^(TYPE_PARAM[$id] $id $tp? )
  ;

typeParameterBound
@after {
// Get the source code escaping 'extends' keyword
$typeParameterBound.tree.setSourceCode($tpbr.text);
}
  :
  e='extends' tpbr=typeParameterBoundRest
    -> ^(GENERIC_EXTENDS[$e] $tpbr+ )
  ;

typeParameterBoundRest
  :
  classOrInterfaceType (BAND classOrInterfaceType)*
  ;

// actual type argument for generic parameters

typeArguments
  :
  lt=LT typeArgument? (COMMA typeArgument)* GT
    -> ^(TYPE_ARGS[$lt] typeArgument* )
  ;

// either reference type or wildcard type with optional lower or upper bound

typeArgument
@after {
$typeArgument.tree.setSourceCode($typeArgument.text);
}
  :
  (
    q=QUESTION
    (
      (
        m='extends'
        | m='super'
      )
      typeSpec
    )?
  )
    -> ^(
        TYPE
        ^(
          WILDCARD[$q]
          ^($m typeSpec )?
          )
        )
  | rts=typeSpec
  ;

superClassClause
  :
  ('extends' id=classOrInterfaceType)
    -> ^(EXTENDS_CLAUSE $id )
  ;

// Definition of a Java Interface

interfaceDefinition[Object ama]
  :
  'interface' id=IDENT fp=formalTypeParameters? // optional generic parameters
  ie=interfaceExtends? // optional extends clause
  cb=classBlock // interface body
    -> ^(INTERFACE_DEF $id {$ama} $fp? $ie? $cb )
  ;

// This is the body of a class.  You can have fields and extra semicolons,
// That's about it (until you see what a field is...)

classBlock
@after {
attachAboveComments($classBlock.tree, $rc);
}
  :
  LCURLY
  (
    field[false]
    | SEMI
  )*
  rc=RCURLY
    -> ^(OBJBLOCK field* )
  ;

// An interface can extend several other interfaces...

interfaceExtends
  :
  'extends' classOrInterfaceType (COMMA classOrInterfaceType)*
    -> ^(EXTENDS_CLAUSE classOrInterfaceType+ )
  ;

// A class can implement several interfaces...

implementsClause
  :
  'implements' classOrInterfaceType (COMMA classOrInterfaceType)*
    -> ^(IMPLEMENTS_CLAUSE classOrInterfaceType+)
  ;

// field defined inside an interface, class or enumeration
// attachSource tells if the source code should be attached to the field
// TODO : remove attachSource as soon as EnumMemberXMLGenerator is corrected to handle attribute/assoc
// and sub-stuff in enumeration as model element

field[boolean attachSource]
@init {
   ASTTree annotationsNode = null;
}
@after {
attachAboveComments($field.tree, $field.start);
if (attachSource) {
   $field.tree.setSourceCode(this.getSource(
			this.adjustStartToken($field.start), $field.stop));
}
}
  :
  (
    // (Note : the syntaxic predicates are to work around
    // an antlr3.4.1 generation error that produces non compilable code)
    ama=annotmodannot
    (
      (attributeDefinitions[null]) => def=attributeDefinitions[$ama.tree] // attribute definition
      | def=classDefinition[$ama.tree] // inner class
      | def=interfaceDefinition[$ama.tree] // inner interface
      | def=enumDefinition[$ama.tree] // inner enumeration
      | def=annotationTypeDefinition[$ama.tree] // inner annotation definition
      | (constructorDefinition[null]) => def=constructorDefinition[$ama.tree] // constructor
      | (operationDefinition[null]) => def=operationDefinition[$ama.tree] // operation definition
    )
    | def=staticInitializer // 'static { ... }' class initializer
    | def=instanceInitializer // '{ ... }' instance initializer
  )
    -> $def
  ;

constructorDefinition[Object ama]
  :
  fp=formalTypeParameters? // generic parameter
  id=IDENT // method name
  LPAREN pa=parameterDeclarationList? RPAREN // parameters list
  tc=throwsClause? // thrown exceptions
  st=compoundStatement[true] // operation body or final semi
    -> ^(METHOD_DEF $id $fp? {$ama} $pa? $tc? $st? )
  ;

operationDefinition[Object ama]
  :
  fp=formalTypeParameters? // generic parameter
  ty=typeSpec // method return type
  id=IDENT // method name
  LPAREN pa=parameterDeclarationList? RPAREN // parameters list
  rt=declaratorBrackets[$ty.tree] // return type can be an array (yes it's weird here !)
  tc=throwsClause? // thrown exceptions
  (
    st=compoundStatement[true]
    | SEMI
  ) // operation body or final semi
    -> ^(METHOD_DEF $id $fp? {$ama} $rt? $pa? $tc? $st? )
  ;

attributeDefinitions[Object ama]
@after {
attachTrailingComments($attributeDefinitions.tree, $sm);
}
  :
  ty=typeSpec // type
  va=variableDefinitions[ama, $ty.tree] sm=SEMI
    -> $va
  ;

staticInitializer
@after {
$staticInitializer.tree.setSourceCode($text);
}
  :
  'static' cs=compoundStatement[false]
    -> ^(STATIC_INIT $cs )
  ;

instanceInitializer
@after {
$instanceInitializer.tree.setSourceCode($text);
}
  :
  cs=compoundStatement[false]
    -> ^(INSTANCE_INIT $cs )
  ;

variableDefinitions[Object ama, Object t]
  :
  va+=variableDeclarator[ama, t] (COMMA va+=variableDeclarator[ama, t])*
    -> $va+
  ;

/** Declaration of a variable.  This can be a class/instance variable,
 *   or a local variable in a method
 * It can also include possible initialization.
 */
variableDeclarator[Object ama, Object t]
  :
  id=IDENT d=declaratorBrackets[t] v=varInitializer
    -> ^(VARIABLE_DEF $id {$ama} $d $v? )
  ;

declaratorBrackets[Object typ]
@init {
boolean arraySeen = false;
}
  :
  ( -> {$typ})
  (lb=LBRACK RBRACK {arraySeen = true;}
    -> ^(ARRAY_DECLARATOR[$lb] $declaratorBrackets ))*
  -> {arraySeen}? ^(TYPE $declaratorBrackets )
  -> $declaratorBrackets
  ;

varInitializer
  :
  (as=ASSIGN^ i=initializer 
                           {
                            $as.tree.setSourceCode(this.getSource(this.adjustStartToken($i.start), $i.stop));
                           })?
  ;

arrayInitializer
  :
  lc=LCURLY (initializer (
    // CONFLICT: does a COMMA after an initializer start a new
  //           initializer or start the option ',' at end?
  //           ANTLR generates proper code by matching
  //       the comma as soon as possible.
  options {greedy=true;}: COMMA initializer)* COMMA?)? RCURLY
    -> ^(ARRAY_INIT[$lc] initializer* )
  ;

// The two 'things' that can initialize an array element are an expression
//   and another (nested) array initializer.

initializer
  :
  expression
  | arrayInitializer
  ;

// This is a list of exception classes that the method is declared to throw

throwsClause
  :
  'throws' identifier (COMMA identifier)*
    -> ^(THROWS identifier+ )
  ;

// A list of formal parameters

parameterDeclarationList
  :
  (parameterDeclaration (COMMA parameterDeclaration)*)
    -> ^(PARAMETERS parameterDeclaration+ )
  ;

// A formal parameter.
// The ellipsis is the support for varargs (JSR 201)
// This rule allows ellipsis on any parameter, not just the last (as specified
// by JSR 201), so a semantic check is needed for that.

parameterDeclaration
  :
  ann+=annotation* pm=parameterModifier? ann+=annotation* t=typeSpec el=ELLIPSIS? id=IDENT pd=declaratorBrackets[$t.tree]
    -> ^(PARAMETER_DEF $pm? $ann* $pd $el? $id )
  ;

parameterModifier
  :
  f='final'
    -> ^(MODIFIERS $f )
  ;

// Compound statement.  This is used in many contexts:
//   Inside a class definition prefixed with 'static':
//      it is a class initializer
//   Inside a class definition without 'static':
//      it is an instance initializer
//   As the body of a method
//   As a completely independent braced block of code inside a method
//      it starts a new scope for variable definitions

compoundStatement[boolean top]
@after {
if (top) {
	$compoundStatement.tree.setSourceCode(this.getSource(
			this.adjustStartToken($stmtl.start),
			this.adjustStopToken($stmtl.stop)));
}
}
  :
  LCURLY
    // include the (possibly-empty) list of statements
  stmtl=statementList RCURLY
    -> ^(SLIST $stmtl? )
  ;

statementList
  :
  explicitConstructorInvocation? statement*
  ;

/** Catch obvious constructor calls, but not the expr.super(...) calls */
explicitConstructorInvocation
  :
  'this' lp1=LPAREN argList RPAREN SEMI
    -> ^(CTOR_CALL argList )
  | 'super' lp2=LPAREN argList RPAREN SEMI
    -> ^(SUPER_CTOR_CALL argList )
  ;

//statement

statement
  // A list of statements in curly braces -- start a new scope!
  :
  compoundStatement[false]
    -> compoundStatement
  // declarations are ambiguous with 'ID DOT' relative to expression
  // statements.  Must backtrack to be sure.  Could use a semantic
  // predicate to test symbol table to see what the type was coming
  // up, but that's pretty hard without a symbol table ;)
  | (declaration) => declaration SEMI
    -> declaration

  // An expression statement.  This could be a method call,
  // assignment statement, or any other expression evaluated for
  // side-effects.
  | expression SEMI
    -> expression

  // class definition
  | ama=annotmodannot classDefinition[$ama.tree]
    -> classDefinition

  // Attach a label to the front of a statement
  | IDENT COLON statement
    -> ^(LABELED_STAT IDENT statement )

  // If-else statement
  | 'if' LPAREN expression RPAREN ths=statement
  ( options {k=1;}: ('else') => 'else' els=statement
  )?
    -> ^('if' expression $ths $els? )

  // For statement
  | 'for' LPAREN
  (
    (parameterDeclaration COLON) => parameterDeclaration COLON expression
    | forInit SEMI // initializer
    forCond SEMI // condition test
    forIter // updater
  )
  RPAREN statement // statement to loop over
    -> ^('for' parameterDeclaration? expression? forInit? forCond? forIter? statement )

  // While statement
  | 'while' LPAREN expression RPAREN statement
    -> ^('while' expression statement )

  // do-while statement
  | 'do' statement 'while' LPAREN expression RPAREN SEMI
    -> ^('do' statement expression )

  // get out of a loop (or switch)
  | 'break' IDENT? SEMI
    -> ^('break' IDENT? )

  // do next iteration of a loop
  | 'continue' IDENT? SEMI
    -> ^('continue' IDENT? )

  // Return an expression
  | ret='return' expression? SEMI
    -> ^(RETURN[$ret] expression? )

  // switch/case statement
  | 'switch' LPAREN expression RPAREN LCURLY casesGroup* RCURLY
    -> ^('switch' expression casesGroup* )

  // exception try-catch block
  | tryBlock
    -> tryBlock

  // throw an exception
  | 'throw' expression SEMI
    -> ^('throw' expression )

  // synchronize a statement
  | 'synchronized' LPAREN expression RPAREN compoundStatement[false]
    -> ^('synchronized' expression compoundStatement )

  // asserts (uncomment if you want 1.4 compatibility)
  | 'assert' expression (COLON expression)? SEMI
    -> ^('assert' expression expression? )

  // empty statement
  | SEMI
    -> EMPTY_STAT
  ;

casesGroup
  :
  ( // CONFLICT: to which case group do the statements bind?
  //           ANTLR generates proper code: it groups the
  //           many 'case'/'default' labels together then
  //           follows them with the statements
  options {greedy=true;}: aCase)+ caseSList
    -> ^(CASE_GROUP aCase+ caseSList )
  ;

aCase
  :
  (
    'case'^ expression
    | 'default'
  )
  COLON!
  ;

caseSList
  :
  statement*
    -> ^(SLIST statement* )
  ;

// The initializer for a for loop

forInit
  // if it looks like a declaration, it is
  :
  (
    (declaration) => declaration
    // otherwise it could be an expression list...
    | expressionList
  )?
    -> ^(FOR_INIT declaration? expressionList? )
  ;

forCond
  :
  expression?
    -> ^(FOR_CONDITION expression? )
  ;

forIter
  :
  expressionList?
    -> ^(FOR_ITERATOR expressionList? )
  ;

// an exception handler try/catch block

tryBlock
  :
  'try'^ resourceDeclarations? compoundStatement[false] handler* finallyClause?
  ;

// Java7 resourceStatement in try-block
resourceDeclarations
  :
  LPAREN declaration? (SEMI declaration?)* RPAREN
  ;

finallyClause
  :
  'finally'^ compoundStatement[false]
  ;

// an exception handler

handler
  :
  'catch'^ LPAREN! caughtExceptionDeclarations IDENT RPAREN! compoundStatement[false]
  ;

caughtExceptionDeclarations
  :
  caughtExceptionDeclaration (BOR! caughtExceptionDeclaration)*
  ;

caughtExceptionDeclaration!
  :
  annotation* parameterModifier? annotation* typeSpec
  ;

// This is a list of expressions.

expressionList
  :
  expression (COMMA expression)*
    -> ^(ELIST expression+ )
  ;

// expressions
// Note that most of these expressions follow the pattern
//   thisLevelExpression :
//       nextHigherPrecedenceExpression
//           (OPERATOR nextHigherPrecedenceExpression)*
// which is a standard recursive definition for a parsing an expression.
// The operators in java have the following precedences:
//    lowest  (13)  = *= /= %= += -= <<= >>= >>>= &= ^= |=
//            (12)  ?:
//            (11)  ||
//            (10)  &&
//            ( 9)  |
//            ( 8)  ^
//            ( 7)  &
//            ( 6)  == !=
//            ( 5)  < <= > >=
//            ( 4)  << >>
//            ( 3)  +(binary) -(binary)
//            ( 2)  * / %
//            ( 1)  ++ -- +(unary) -(unary)  ~  !  (type)
//                  []   () (method call)  . (dot -- identifier qualification)
//                  new   ()  (explicit parenthesis)
//
// the last two are not usually on a precedence chart; I put them in
// to point out that new has a higher precedence than '.', so you
// can validy use
//     new Frame().show()
//
// Note that the above precedence levels map to the rules below...
// Once you have a precedence chart, writing the appropriate rules as below
//   is usually very straightfoward

// the mother of all expressions

expression
  :
  as=assignmentExpression
    -> ^(EXPR $as )
  ;

// assignment expression (level 13)

assignmentExpression
  :
  conditionalExpression[false] (assignmentOperator assignmentExpression)?
  ;

assignmentOperator
  :
  ASSIGN
    -> ASSIGN
  | PLUS_ASSIGN
    -> PLUS_ASSIGN
  | MINUS_ASSIGN
    -> MINUS_ASSIGN
  | STAR_ASSIGN
    -> STAR_ASSIGN
  | DIV_ASSIGN
    -> DIV_ASSIGN
  | MOD_ASSIGN
    -> MOD_ASSIGN
  | GT GT '='
    -> SR_ASSIGN
  | GT GT GT '='
    -> BSR_ASSIGN
  | LT LT '='
    -> SL_ASSIGN
  | BAND_ASSIGN
    -> BAND_ASSIGN
  | BXOR_ASSIGN
    -> BXOR_ASSIGN
  | BOR_ASSIGN
    -> BOR_ASSIGN
  ;

// conditional test (level 12)

conditionalExpression[boolean storeSrcCode]
@after {
if (storeSrcCode) {
  $conditionalExpression.tree.setSourceCode($text);
}
}
  :
  logicalOrExpression (QUESTION^ assignmentExpression COLON! conditionalExpression[false])?
  ;

// logical or (||)  (level 11)

logicalOrExpression
  :
  logicalAndExpression (LOR^ logicalAndExpression)*
  ;

// logical and (&&)  (level 10)

logicalAndExpression
  :
  inclusiveOrExpression (LAND^ inclusiveOrExpression)*
  ;

// bitwise or non-short-circuiting or (|)  (level 9)

inclusiveOrExpression
  :
  exclusiveOrExpression (BOR^ exclusiveOrExpression)*
  ;

// exclusive or (^)  (level 8)

exclusiveOrExpression
  :
  andExpression (BXOR^ andExpression)*
  ;

// bitwise or non-short-circuiting and (&)  (level 7)

andExpression
  :
  equalityExpression (BAND^ equalityExpression)*
  ;

// equality/inequality (==/!=) (level 6)

equalityExpression
  :
  instanceOfExpression
  (
    (
      NOT_EQUAL^
      | EQUAL^
    )
    instanceOfExpression
  )*
  ;

// 'instanceof' Expression(level 5)

instanceOfExpression
  :
  relationalExpression ('instanceof'^ typeSpec)?
  ;

// boolean relational expressions (level 5bis)

relationalExpression
  :
  additiveExpression (relationalOperator^ additiveExpression)*
  ;

relationalOperator
  :
  LT
    -> LT
  | LT LT
    -> SL
  | LT '='
    -> LE
  | GT
    -> GT
  | GT GT
    -> SR
  | GT GT GT
    -> BSR
  | GT '='
    -> GE
  ;
// binary addition/subtraction (level 3)

additiveExpression
  :
  multiplicativeExpression
  (
    (
      PLUS^
      | MINUS^
    )
    multiplicativeExpression
  )*
  ;

// multiplication/division/modulo (level 2)

multiplicativeExpression
  :
  unaryExpression
  (
    (
      STAR^
      | DIV^
      | MOD^
    )
    unaryExpression
  )*
  ;

unaryExpression
  :
  INC^ unaryExpression
  | DEC^ unaryExpression
  | MINUS^ 
          {
           $MINUS.setType(UNARY_MINUS);
          }
  unaryExpression
  | PLUS^ 
         {
          $PLUS.setType(UNARY_PLUS);
         }
  unaryExpression
  | unaryExpressionNotPlusMinus
  ;

unaryExpressionNotPlusMinus
  :
  BNOT unaryExpression
    -> ^(BNOT unaryExpression )
  | LNOT unaryExpression
    -> ^(LNOT unaryExpression )
  |
  ( // subrule allows option to shut off warnings
  options {
      // '(int' ambig with postfixExpr due to lack of sequence
  // info in linear approximate LL(k).  It's ok.  Shut up.
  greedy=true;}:
      // If typecast is built in type, must be numeric operand
  // Also, no reason to backtrack if type keyword like int, float...
  LPAREN builtInTypeSpec RPAREN unaryExpression
      -> ^(TYPECAST builtInTypeSpec unaryExpression )
    // Have to backtrack to see if operator follows.  If no operator
    // follows, it's a typecast.  No semantic checking needed to parse.
    // if it _looks_ like a cast, it _is_ a cast; else it's a '(expr)'
    | (LPAREN classTypeSpec RPAREN unaryExpressionNotPlusMinus) => LPAREN classTypeSpec RPAREN unaryExpressionNotPlusMinus
      -> ^(TYPECAST classTypeSpec unaryExpressionNotPlusMinus )
    | postfixExpression
      -> postfixExpression
  )
  ;

// qualified names, array expressions, method invocation, post inc/dec

postfixExpression!
  :
  primaryExpression
  (
    DOT typeArguments? IDENT (LPAREN argList RPAREN)?
    | DOT 'this'
    | DOT 'super'
    ( // (new Outer()).super()  (create enclosing instance)
      LPAREN argList RPAREN
      | DOT IDENT (LPAREN argList RPAREN)?
    )
    | DOT newExpression
    | lb=LBRACK expression RBRACK
  )*
  ( // possibly add on a post-increment or post-decrement.
    // allows INC/DEC on too much, but semantics can check
    INC
    | DEC
  )?
  ;

// the basic element of an expression

primaryExpression
  :
  identPrimary (options {greedy=true;}: DOT^ 'class')?
  | constant
  | 'true'
  | 'false'
  | 'null'
  | newExpression
  | 'this'
  | 'super'
  | LPAREN! assignmentExpression RPAREN!
  // look for int.class and int[].class
  |
  builtInType
  (lbt=LBRACK^ 
              {
               $lbt.setType(ARRAY_DECLARATOR);
              }
    RBRACK!)* DOT^ 'class'
  ;

/** Match a, a.b.c refs, a.b.c(...) refs, a.b.c[], a.b.c[].class,
 *  and a.b.c.class refs.  Also this(...) and super(...).  Match
 *  this or super.
 */
identPrimary
  :
  IDENT (options {greedy=true;}: DOT^ IDENT)*
  ( options {greedy=true;}: (lp=LPAREN^ 
                                       {
                                        $lp.setType(METHOD_CALL);
                                       }
      argList RPAREN!)
    | (options {greedy=true;}: lbc=LBRACK^ 
                                          {
                                           $lbc.setType(ARRAY_DECLARATOR);
                                          }
      RBRACK!)+
  )?
  ;

newExpression!
  :
  'new'
  (
    classOrInterfaceType
    | builtInType
  )
  (
    LPAREN argList RPAREN classBlock?
    | newArrayDeclarator arrayInitializer?
  )
  ;

argList
  :
  el=expressionList
  |
    /* nothing */
    -> ^(ELIST )
  ;

newArrayDeclarator
  :
  (
                                     // CONFLICT:
  // newExpression is a primaryExpression which can be
  // followed by an array index reference.  This is ok,
  // as the generated code will stay in this loop as
  // long as it sees an LBRACK (proper behavior)
  options {greedy=true;}: lb=LBRACK^ 
                                     {
                                      $lb.setType(ARRAY_DECLARATOR);
                                     }
    (expression)? RBRACK!)+
  ;

constant
  :
  NUM_LITERAL
  | CHAR_LITERAL
  | STRING_LITERAL
  ;

//----------------------------------------------------------------------------
// The Java scanner
//----------------------------------------------------------------------------

// OPERATORS

QUESTION
  :
  '?'
  ;

LPAREN
  :
  '('
  ;

RPAREN
  :
  ')'
  ;

LBRACK
  :
  '['
  ;

RBRACK
  :
  ']'
  ;

LCURLY
  :
  '{'
  ;

RCURLY
  :
  '}'
  ;

COLON
  :
  ':'
  ;

COMMA
  :
  ','
  ;

DOT
  :
  '.'
  ;

ASSIGN
  :
  '='
  ;

EQUAL
  :
  '=='
  ;

LNOT
  :
  '!'
  ;

BNOT
  :
  '~'
  ;

NOT_EQUAL
  :
  '!='
  ;

DIV
  :
  '/'
  ;

DIV_ASSIGN
  :
  '/='
  ;

PLUS
  :
  '+'
  ;

PLUS_ASSIGN
  :
  '+='
  ;

INC
  :
  '++'
  ;

MINUS
  :
  '-'
  ;

MINUS_ASSIGN
  :
  '-='
  ;

DEC
  :
  '--'
  ;

STAR
  :
  '*'
  ;

STAR_ASSIGN
  :
  '*='
  ;

MOD
  :
  '%'
  ;

MOD_ASSIGN
  :
  '%='
  ;

GT
  :
  '>'
  ;

LT
  :
  '<'
  ;

BXOR
  :
  '^'
  ;

BXOR_ASSIGN
  :
  '^='
  ;

BOR
  :
  '|'
  ;

BOR_ASSIGN
  :
  '|='
  ;

LOR
  :
  '||'
  ;

BAND
  :
  '&'
  ;

BAND_ASSIGN
  :
  '&='
  ;

LAND
  :
  '&&'
  ;

SEMI
  :
  ';'
  ;

AT
  :
  '@'
  ;

ELLIPSIS
  :
  '...'
  ;

// Whitespace -- hidden

WS
  :
  (
    ' '
    | '\t'
    | '\f'
  )+
  
  {
   $channel = HIDDEN;
  }
  ;

// Newlines -- hidden
// (Distinguishing whitespace from newline produces more tokens but gives more control
// in functions that recognize comments and javadocs)

NL
  :
  (
    ( options {greedy=true;}: '\r\n' // Evil DOS
      | '\r' // Macintosh
      | '\n' // Unix (the right way)
    )
  )+
  
  {
   $channel = HIDDEN;
  }
  ;

COMMENT
@init {
boolean isJavaDoc = false;
}
  :
  ('/*' 
       {
        if ((char) input.LA(1) == '*') {
        	isJavaDoc = true;
        }
       }
    (options {greedy=false;}: .)* '*/' 
                                      {
                                       if (isJavaDoc == true) {
                                       	$type = JAVADOC;
                                       }
                                       $channel = HIDDEN;
                                      })
  |
  (
    '//'
    ~(
      '\n'
      | '\r'
     )*
    (
      '\r\n'
      | '\r'
      | '\n'
    )
    
    {
     $channel = HIDDEN;
    }
    | '//'
    ~(
      '\n'
      | '\r'
     )* // a line comment could appear at the end of the file without CR/LF
    
    {
     $channel = HIDDEN;
    }
  )
  ;

// character literals

CHAR_LITERAL
  :
  '\''
  (
    ESC
    | ~'\''
  )
  '\''
  ;

// string literals

STRING_LITERAL
  :
  '"'
  (
    ESC
    |
    ~(
      '"'
      | '\\'
     )
  )*
  '"'
  ;

// escape sequence
// There are various ambiguities hushed in this rule.  The optional
// '0'...'9' digit matches should be matched here rather than letting
// them go back to STRING_LITERAL to be matched.  ANTLR does the
// right thing by matching immediately; hence, it's ok to shut off
// the FOLLOW ambig warnings.

fragment
ESC
  :
  '\\'
  (
    'n'
    | 'r'
    | 't'
    | 'b'
    | 'f'
    | '\''
    | '"'
    | '\\'
    | ('u')+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    | '0'..'3' (options {greedy=true;}: '0'..'7' (options {greedy=true;}: '0'..'7')?)?
    | '4'..'7' (options {greedy=true;}: '0'..'7')?
  )
  ;

// binary digit

fragment
BIN_DIGIT
  :
  '0'..'1'
  ;

fragment
BIN_NUM
  :
  BIN_DIGIT
  ( options {greedy=true;}: BIN_DIGIT
    | '_'
  )*
  ;

// decimal digit

fragment
DEC_DIGIT
  :
  '0'..'9'
  ;

fragment
DEC_NUM
  :
  DEC_DIGIT
  ( options {greedy=true;}: DEC_DIGIT
    | '_'
  )*
  ;

// hexadecimal digit (again, note it's fragment!)

fragment
HEX_DIGIT
  :
  (
    '0'..'9'
    | 'A'..'F'
    | 'a'..'f'
  )
  ;

fragment
HEX_NUM
  :
  HEX_DIGIT
  ( options {greedy=true;}: HEX_DIGIT
    | '_'
  )*
  ;

// an identifier

IDENT
  :
  (
    'a'..'z'
    | 'A'..'Z'
    | '\u00C0'..'\u00D6'
    | '\u00D8'..'\u00F6'
    | '\u00F8'..'\u00FF'
    | '_'
    | '$'
  )
  (
    'a'..'z'
    | 'A'..'Z'
    | '\u00C0'..'\u00D6'
    | '\u00D8'..'\u00F6'
    | '\u00F8'..'\u00FF'
    | '_'
    | '0'..'9'
    | '$'
  )*
  ;

// a numeric literal

NUM_LITERAL
  :
  '.' DEC_NUM EXPONENT? FLOAT_SUFFIX? // Note DEC_NUM behind '.' is mandatory
  |
  (
    '0x'
    | '0X'
  )
  HEX_NUM*
  (
    LONG_SUFFIX
    | '.' HEX_NUM*
    (
      'p'
      | 'P'
    )
    (
      '+'
      | '-'
    )?
    DEC_NUM FLOAT_SUFFIX?
  )?
  |
  (
    '0b'
    | '0B'
  )
  BIN_NUM
  | DEC_NUM DEC_END?
  ;

fragment
DEC_END
  :
  LONG_SUFFIX
  | '.' DEC_NUM? EXPONENT? FLOAT_SUFFIX? // Note DEC_NUM behind '.' is optionnal
  | EXPONENT FLOAT_SUFFIX?
  | FLOAT_SUFFIX
  ;

fragment
EXPONENT
  :
  (
    'e'
    | 'E'
  )
  (
    '+'
    | '-'
  )?
  ('0'..'9')+
  ;

fragment
FLOAT_SUFFIX
  :
  'f'
  | 'F'
  | 'd'
  | 'D'
  ;

fragment
LONG_SUFFIX
  :
  'l'
  | 'L'
  ;
