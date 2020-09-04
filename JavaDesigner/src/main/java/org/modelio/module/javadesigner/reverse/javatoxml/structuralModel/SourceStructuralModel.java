package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.modelio.module.xmlreverse.IReportWriter;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.antlr.ASTTree;
import org.modelio.module.javadesigner.reverse.antlr.ASTTreeAdaptor;
import org.modelio.module.javadesigner.reverse.antlr.AstUtils;
import org.modelio.module.javadesigner.reverse.antlr.JavaLexer;
import org.modelio.module.javadesigner.reverse.antlr.JavaParser;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.ClassifierDefKind;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.Visibility;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;

/**
 * The structural model build from Java source file.
 * 
 * Uses BinaryAnalyzer because source files depend on jar.
 */
public class SourceStructuralModel {
     static final String JavaDesignerAnnotationsPackage = "com.modeliosoft.modelio.javadesigner.annotations.";

    private String encoding;

    /**
     * Cache of list of files to reverse
     */
    private List<File> sourceFileToReverseCache;

    /**
     * Virtual root package. Starting point of any lookup.
     */
    private PackageDef root = new PackageDef("");

    /**
     * Cache of ANTLR trees each resulting from the parsing of a java file (the key)
     */
    private HashMap<File, ASTTree> parseTrees = new HashMap<>();

    /**
     * Roots of source path. A list of file system directories from where to find source files
     */
    private List<File> sourcepath;

    /**
     * Already analyzed files, prevent to do it again.
     */
    private List<File> analyzedFiles = new ArrayList<>();

    /**
     * The binary analyzer instance
     */
    private BinaryAnalyzer binaryAnalyzer;

    /**
     * The report writer, to post parsing errors.
     */
    private IReportWriter report;

    /**
     * C'tor.
     * @param sourceFiles source files to reverse
     * @param aSourcepath source class path ?
     * @param classpath jars class path.
     * @param report error reporter
     * @param encoding java files encoding ?
     */
    public SourceStructuralModel(final List<File> sourceFiles, final List<File> aSourcepath, final List<File> classpath, final IReportWriter report, final String encoding) {
        this.sourceFileToReverseCache = sourceFiles;
        this.sourcepath = aSourcepath;
        this.binaryAnalyzer = new BinaryAnalyzer(classpath, report);
        this.report = report;
        this.encoding = encoding;
    }

    /**
     * Parse a source file and add its structural content to the repository of
     * reversed classes.
     * Source file is taken from the list passed to the constructor.
     * @param aFileIdx index of the file in the list passed to the constructor
     * @return the AST tree.
     * @throws java.io.IOException in case of I/O failure
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     */
    public ASTTree addFileToStructuralModel(final int aFileIdx) throws IOException, NameCollisionException {
        File file = this.sourceFileToReverseCache.get(aFileIdx);
        ASTTree parseRoot = this.parseTrees.get(file);
        if (parseRoot != null) {
            // Already parsed, remove it from cache and return it.
            this.parseTrees.remove(parseRoot);
        } else {
            parseRoot = parseFile(file);
        }
        return parseRoot;
    }

    /**
     * Find a ClassifierDef in the repository from its name and a parent. Parse file if required.
     * @param aName name of the type to be search, can be a short or a partial name
     * @param parent the parent from where to start the search (can be null but there exist specialized version to find from root)
     * @return the ClassifierDef matching or null if not found.
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     * @throws java.io.IOException in case of I/O failure
     */
    public ClassifierDef findClassifier(final String aName, final StructuralTree parent) throws IOException, NameCollisionException {
        StructuralTree realParent = getParent(parent);
        ClassifierDef elt = StructuralModelUtils.getClassifier(aName, realParent);
        if (elt == null) {
            StringBuilder fqn = new StringBuilder();
            if (parent != null) {
                fqn.append(parent.getFullQualifiedName());
                fqn.append(".");
            }
            fqn.append(aName);
            // A Class, try to update the structural model
            if (updateClassesFromFqn(fqn.toString())) {
                // structural model updated, let's make another try
                elt = StructuralModelUtils.getClassifier(aName, realParent);
            }
        }
        return elt;
    }

    /**
     * Find a StructuralTree in the structural model given its full qualified name. This service may parse unvisited java file(s)
     * to update the structural model. Because it takes a full qualified name, there no need to take inheritance into account.
     * @param aFullQualifiedName Name to search.
     * @return found NameSpacedef or null if not found.
     * @throws java.io.IOException in case of I/O failure
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     */
    public StructuralTree findStructuralTree(final String aFullQualifiedName) throws IOException, NameCollisionException {
        StructuralTree foundNs = StructuralModelUtils.getStructuralTree(aFullQualifiedName, this.root);
        if (foundNs == null) {
            // Then try to update the structural model with a class
            if (updateClassesFromFqn(aFullQualifiedName)) {
                // structural model updated, let's make another try
                foundNs = StructuralModelUtils.getStructuralTree(aFullQualifiedName, this.root);
            }
        }
        if (foundNs == null) {
            // the corresponding file may not have been parsed already. try it now
            // First, try to update the structural model with a package
            foundNs = updatePackagesFromFqn(aFullQualifiedName);
        }
        return foundNs;
    }

    /**
     * Get a package definition.
     * @param aPackageShortName the short package name
     * @param aParent the parent package to look from
     * @return the found package or <i>null</i>.
     */
    public PackageDef getPackage(final String aPackageShortName, final PackageDef aParent) {
        return StructuralModelUtils.getPackage(aPackageShortName, getParent(aParent));
    }

    private List<File> getSourceFiles(final String typeName) {
        List<File> retval = new ArrayList<>();
        String filePath = typeName.replace('.', '/'); //$NON-NLS-1$
        
        List<File> srcpath = new ArrayList<>(this.sourcepath);
        boolean goOn = true;
        while (goOn) {
            // look for matching java files in the source path
            List<File> toDelete = new ArrayList<>(); // list of eliminated
            // element of source
            // path
            for (File path : srcpath) {
                File candidatePackage = new File(path, filePath);
                File candidateFile = new File(candidatePackage + ".java");
                if (candidateFile.isFile()) {
                    // a match
                    if (!this.analyzedFiles.contains(candidateFile)) {
                        retval.add(candidateFile);
                    }
                }
            }
            srcpath.removeAll(toDelete);
            if (!srcpath.isEmpty()) {
                int innerIndex = filePath.lastIndexOf('/');
                if (innerIndex > 0) {
                    // Remove the last part of the filePath and restart a
                    // search.
                    // Useful if typename contains subtypes to find the java
                    // file.
                    // Example (com.acme.Clazz.Subtype is defined in
                    // <root>/com/acme/Clazz.java)
                    // Moreover the loop can't be stopped as soon as a java file
                    // is found because there can be some ambiguous situation
                    // Example : typeName = 'a.b.c' sourcepath={root1, root2}
                    // and there is :
                    // root1/a/b/c.java and root2/a.java with a.java defining
                    // inner b and b.c.
                    filePath = filePath.substring(0, innerIndex); //$NON-NLS-1$
                } else {
                    goOn = false;
                }
            } else {
                goOn = false;
            }
        }
        return retval;
    }

    private ClassifierDef defineClassDef(final ASTTree ast, final StructuralTree parent) throws IOException, NameCollisionException {
        assert (AstUtils.isGeneralClass(ast)) : "Internal error: unexpected node type in StructuralModel.defineClassDef";
        assert (parent != null);
        
        // Get the class name from ast
        ASTTree ident = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT);
        assert (ident != null);
        String  className = ident.getText();
        
        // Get Classifier kind
        ClassifierDefKind kind;
        switch (ast.getType()) {
        case JavaParser.ANNOTATION_DEF:
        case JavaParser.CLASS_DEF:
            kind = ClassifierDefKind.CLASS;
            break;
        case JavaParser.INTERFACE_DEF:
            kind = ClassifierDefKind.INTERFACE;
            break;
        case JavaParser.ENUM_DEF:
            kind = ClassifierDefKind.ENUMERATION;
            break;
        default:
            kind = ClassifierDefKind.UNKNOWN;
        }
        
        // Create the ClassifierDef entry
        ClassifierDef cDef = StructuralModelUtils.addClass(className, kind, parent);
        
        // Analyze the visibility
        Modifiers mod = new Modifiers((ASTTree)ast.getFirstChildWithType(JavaParser.MODIFIERS));
        // Set visibility of classifierDef
        if (mod.isPublic()) {
            cDef.setVisibility(Visibility.PUBLIC);
        } else if (mod.isProtected()) {
            cDef.setVisibility(Visibility.PROTECTED);
        } else if (mod.isPrivate()) {
            cDef.setVisibility(Visibility.PRIVATE);
        } else {
            if (parent instanceof ClassifierDef && ((ClassifierDef) parent).getKind() == ClassifierDefKind.INTERFACE) {
                // Inner classifiers of an Interface are always  public
                cDef.setVisibility(Visibility.PUBLIC);
            } else {
                cDef.setVisibility(Visibility.PACKAGE);
            }
        }
        
        
        // Analyze template parameters
        ASTTree tmplpar = (ASTTree) ast.getFirstChildWithType(JavaParser.FORMAL_TYPE_PARAMS);
        if (tmplpar != null) {
            defineTemplateParameters(tmplpar, cDef);
        }
        
        // Analyze inner classifiers
        ASTTree block = (ASTTree) ast.getFirstChildWithType(JavaParser.OBJBLOCK);
        if (block != null) {
            for (ASTTree child : block.getChildrenSafe()) {
                if (AstUtils.isGeneralClass(child)) {
                    // recurse on defineClassDef (no import attached to inner classifiers)
                    defineClassDef(child, cDef);
                }
            }
        }
        return cDef;
    }

    /**
     * Resolve inheritance of 'ast' class.
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     * @throws java.io.IOException in case of I/O failure
     */
    private ClassifierDef resolveInheritance(final ASTTree ast, final StructuralTree parent, final List<String> unaryImports, final List<String> starImports) throws IOException, NameCollisionException {
        assert (AstUtils.isGeneralClass(ast)) : "Internal error: unexpected node type in StructuralModel.defineClassDef";
        assert parent != null;
        
        // Get the class name from ast
        ASTTree ident = (ASTTree) ast.getFirstChildWithType(JavaParser.IDENT);
        assert (ident != null);
        String  className = ident.getText();
        
        // get the classifier from its parent
        ClassifierDef cDef = StructuralModelUtils.getClassifier(className, parent);
        assert cDef != null;
        
        // Analyze extends and implements node
        defineInheritance(ast, unaryImports, starImports, cDef);
        
        // Analyze inner classifiers
        ASTTree block = (ASTTree) ast.getFirstChildWithType(JavaParser.OBJBLOCK);
        if (block != null) {
            for (ASTTree child : block.getChildrenSafe()) {
                if (AstUtils.isGeneralClass(child)) {
                    // recurse on defineClassDef (no import attached to
                    // inner classifiers)
                    resolveInheritance(child, cDef, unaryImports, starImports);
                }
            }
        }
        return cDef;
    }

    /**
     * Add inheritance stuff from to the cDef from the ast and imports list.
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     * @throws java.io.IOException in case of I/O failure
     */
    private void defineInheritance(final ASTTree ast, final List<String> unaryImports, final List<String> starImports, final ClassifierDef cDef) throws IOException, NameCollisionException {
        ASTTree extendsNode = (ASTTree) ast.getFirstChildWithType(JavaParser.EXTENDS_CLAUSE);
        if (extendsNode != null) {
            // Interfaces may extend several other interfaces
            for (ASTTree extType : extendsNode.getAllChildrenWithType(JavaParser.TYPE)) {
                addInheritance(extType, unaryImports, starImports, cDef);
            }
        }
        ASTTree implementsNode = (ASTTree) ast.getFirstChildWithType(JavaParser.IMPLEMENTS_CLAUSE);
        if (implementsNode != null) {
            for (ASTTree implType : implementsNode.getAllChildrenWithType(JavaParser.TYPE)) {
                addInheritance(implType, unaryImports, starImports, cDef);
        
            }
        }
    }

    private void addInheritance(final ASTTree inheritedType, final List<String> unaryImports, final List<String> starImports, final ClassifierDef cDef) throws IOException, NameCollisionException {
        String iname = AstUtils.getCanonicalIdentifier (inheritedType);
        ClassifierDef icdef = null;
        
        // 1 look for iname in outers and their parent
        StructuralTree cur = cDef.getOwner();
        ClassifierDef toplvl = null;
        while (cur != null && cur instanceof ClassifierDef) {
            icdef = this.findClassifier(iname, cur);
            if (icdef != null) {
                cDef.addInherits(icdef);
                return;
            }
            toplvl = (ClassifierDef) cur;
            cur = cur.getOwner();
        }
        // 1 bis special case iname is the Toplevel classifier of cDef
        if (toplvl != null && iname.equals(toplvl.getName())) {
            cDef.addInherits(toplvl);
            return;
        }
        
        // 2 try with classifier imports
        icdef = findFromImport(iname, unaryImports);
        if (icdef != null) {
            cDef.addInherits(icdef);
            return;
        }
        
        // 3 same package
        icdef = findClassifier(iname, cDef.getPackage());
        if (icdef != null) {
            cDef.addInherits(icdef);
            return;
        }
        
        // 4 try with package imports
        icdef = findFromPackageImport(iname, starImports);
        if (icdef != null) {
            cDef.addInherits(icdef);
            return;
        }
    }

    /**
     * ALMOST DUPLICATE FROM TypeFinder !!
     * Search the type using the classifier import list
     * @param typeName : the partial name to search
     * @return the type found or null if none matches
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     * @throws java.io.IOException in case of I/O failure
     */
    private ClassifierDef findFromImport(final String typeName, final List<String> unaryImports) throws IOException, NameCollisionException {
        if (unaryImports != null) {
            for(String it : unaryImports) {
                if (it.endsWith("." + typeName)) {
                    // perfect match !
                    final StructuralTree def = findStructuralTree(it);
                    if (def instanceof ClassifierDef) {
                        return (ClassifierDef) def;
                    }
                } else {
                    String itname = it + ".";
                    if (typeName.startsWith(itname)) {
                        // same prefix : try to find 'it' element
                        ClassifierDef par = (ClassifierDef) findStructuralTree(typeName);
                        if (par != null) {
                            // Now look into the parent
                            String cn = typeName.replaceFirst(itname, "");
                            ClassifierDef elt = findClassifier(cn, par);
                            if (elt != null) {
                                return elt;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * ALMOST DUPLICATE FROM TypeFinder !!
     * Search the type using the package import list
     * @param typeName : the partial name to search
     * @return the type found or null if none matches
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     * @throws java.io.IOException in case of I/O failure
     */
    private ClassifierDef findFromPackageImport(final String typeName, final List<String> starImports) throws IOException, NameCollisionException {
        if (starImports != null) {
            for (String pi : starImports) {
                // find the element matching pi
                StructuralTree par = findStructuralTree(pi);
                if (par != null) {
                    ClassifierDef elt = findClassifier(typeName, par);
                    if (elt != null) {
                        return elt;
                    }
                }
            }
        }
        return null;
    }

    private PackageDef definePackageDef(final ASTTree ast, final PackageDef parent) throws NameCollisionException {
        assert parent != null;
        PackageDef retval = null;
        switch (ast.getType()) {
        case JavaParser.IDENT:
            retval = StructuralModelUtils.addPackage(ast.getText(), parent);
            break;
        case JavaParser.DOT:
            // create parent packages on the left
            PackageDef pack = definePackageDef((ASTTree) ast.getChild(0), parent);
            // create packages on the right with left created packages as parent
            retval = definePackageDef((ASTTree) ast.getChild(1), pack);
            break;
        default:
        }
        return retval;
    }

    /**
     * Extract template parameters from the ast class definition and add them to
     * the structural model
     * @param params the root ast, must be of type FORMAL_TYPE_PARAMS parameters
     * @param def the parent definition
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException It is useless to store these parameters in the global structure because they can't be acceded outside
     * the method context.
     */
    private void defineTemplateParameters(final ASTTree params, final ClassifierDef def) throws NameCollisionException {
        assert params.getType() == JavaParser.FORMAL_TYPE_PARAMS;
        for (ASTTree typepar : params.getChildrenSafe()) {
            assert typepar.getType() == JavaParser.TYPE_PARAM;
            // Extract the name of the parameter (no dot allowed here by Java syntax)
            String parname = typepar.getFirstChildWithType(JavaParser.IDENT).getText();
            StructuralModelUtils.addClassTemplateParameterDef(parname, def);
        }
    }

    private ASTTree parseFile(final File aFile) throws IOException, NameCollisionException {
        // mark the file as analyzed.
        this.analyzedFiles.add(aFile);
        // build the input and token streams, the lexer and the parser
        ANTLRFileStream input = new ANTLRFileStream(aFile.getAbsolutePath(), this.encoding);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        // Declare our customized tree adaptor
        ASTTreeAdaptor treeadaptor = new ASTTreeAdaptor(this.report);
        parser.setTreeAdaptor(treeadaptor);
        // Parse the file starting at the compilationUnit rule
        try {
            JavaParser.compilationUnit_return result;
            result = parser.compilationUnit();
        
            // Analyze the resulting AST
            ASTTree astRoot = result.getTree();
            if (astRoot != null) {
                List<? extends ASTTree> children = astRoot.getChildrenSafe();
                if (children != null) {
                    PackageDef pack = this.root;
                    List<String> unaryImports = new ArrayList<>();
                    List<String> starImports = new ArrayList<>();
                    starImports.add("java.lang");  //$NON-NLS-1$
                    for (ASTTree ast : children) {
                        if (ast.getType() == JavaParser.PACKAGE_DEF) {
                            pack = definePackageDef((ASTTree) ast.getChild(0), this.root);
                        } else if (ast.getType() == JavaParser.IMPORT) {
                            analyzeImport(ast, unaryImports, starImports);
                        } else if (AstUtils.isGeneralClass(ast)) {
                            defineClassDef(ast, pack);
                        }
                    }
        
                    // Second pass to resolve inheritance that requires that all classes of the file are defined.
                    for (Object child : children) {
                        ASTTree ast = (ASTTree) child;
                        if (AstUtils.isGeneralClass(ast)) {
                            if (unaryImports != null && unaryImports.isEmpty()) {
                                unaryImports = null;
                            }
                            if (starImports != null && starImports.isEmpty()) {
                                starImports = null;
                            }
                            resolveInheritance(ast, pack, unaryImports, starImports);
                        }
                    }
                }
            }
            return astRoot;
        } catch (RecognitionException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            return null;
        } catch (NameCollisionException e) {
            e.setSecondFile(aFile);
            throw e;
        }
    }

    /**
     * Analyze import node
     * @param ast : import ast node
     * @param unaryImports : unary import list to fill in. Shouldn't be null
     * @param starImports : star import list to fill in. Shouldn't be null
     */
    private void analyzeImport(final ASTTree ast, final List<String> unaryImports, final List<String> starImports) {
        for (ASTTree child : ast.getChildrenSafe()) {
            if (child.getType() == JavaParser.DOT || child.getType() == JavaParser.IDENT) {
                // child = Import name
                String importStr = AstUtils.getCanonicalIdentifier(child);
                String importName;
                boolean starImport = false;
                // Skip Java Designer own annotations
                if (!importStr.startsWith(JavaDesignerAnnotationsPackage)) {
                    if (importStr.endsWith (".*")) {
                        // Star import
                        importName = importStr.substring (0, importStr.length () - 2);
                        starImport = true;
                    } else {
                        importName = importStr;
                    }
                    if (starImport) {
                        starImports.add(importName);
                    } else {
                        unaryImports.add(importName);
                    }
                }
            }
        }
    }

    /**
     * Tell if a aFullQualifiedName is a source package. A source package is a directory among sourcepath
     * @param aFullQualifiedName a full java name
     * @return <i>true</i> if a aFullQualifiedName is a source package.
     */
    private boolean isSourcePackage(final String aFullQualifiedName) {
        String filePath = aFullQualifiedName.replace('.', '/');
        for (File path : this.sourcepath) {
            File candidatePackage = new File(path, filePath);
            if (candidatePackage.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update the Used Class repository cache. Find all Java files that match
     * aFullQualifiedName taking into account the source path and the classpath.
     * Parse the resulting files to create Structural model elements
     * (StructuralTree heirs)
     * @param aFullQualifiedName a fully qualified java name.
     * @throws java.io.IOException in case of I/O failure
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     */
    private boolean updateClassesFromFqn(final String aFullQualifiedName) throws IOException, NameCollisionException {
        List<File> candidates = getSourceFiles(aFullQualifiedName);
        for (File candidate : candidates) {
            addUsedFileToStructuralModel(candidate);
        }
        if (candidates.isEmpty()) {
            // try binary files
            return this.binaryAnalyzer.addBinaryEntryToStructuralModel(aFullQualifiedName, this.root);
        } else {
            return true;
        }
    }

    /**
     * Update the structural model with a new package, if a corresponding source directory exists.
     * @param aFullQualifiedName : full qualified name of the package to add
     * @return the new PackageDef or null if the name doesn't match with a source directory
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     */
    private PackageDef updatePackagesFromFqn(final String aFullQualifiedName) throws NameCollisionException {
        PackageDef pDef = null;
        if (isSourcePackage(aFullQualifiedName) || this.binaryAnalyzer.isBinaryPackage(aFullQualifiedName)) {
            pDef = StructuralModelUtils.addPackageHierarchy(aFullQualifiedName, this.root);
        }
        return pDef;
    }

    /**
     * Parse 'aFile' and add its structural content to the repository of
     * reversed classes.
     * @throws java.io.IOException in case of I/O failure
     * @throws org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException in case of name collision
     */
    private ASTTree addUsedFileToStructuralModel(final File aFile) throws IOException, NameCollisionException {
        ASTTree astRoot = this.parseFile(aFile);
        
        if (isInFileList(this.sourceFileToReverseCache, aFile)) {
            // The file belongs to the set of file to reverse, cache its AST
            // until it is analyzed on its own
            this.parseTrees.put(aFile, astRoot);
        }
        return astRoot;
    }

    private boolean isInFileList(final List<File> sourceFileToReverseCache2, final File file) {
        for (File f : sourceFileToReverseCache2) {
            if (f.getAbsolutePath().equals(file.getAbsolutePath())) {
                return true;
            }
        }
        return false;
    }

    private PackageDef getParent(final PackageDef aParent) {
        return (aParent != null)? aParent : this.root;
    }

    private StructuralTree getParent(final StructuralTree aParent) {
        return (aParent != null)? aParent : this.root;
    }

}
