package org.modelio.module.javadesigner.reverse.javatoxml.source;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.NameCollisionException;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.SourceStructuralModel;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.StructuralModelUtils;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef.Visibility;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.ClassifierDef;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PrimitiveType;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.SimpleType;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree;
import org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.Type;

class TypeFinder {
    private SourceStructuralModel sModel;

    private GeneratorStack gStack;

    private Map<String, PrimitiveType> primitiveTypes = new TreeMap<>();

    /**
     * Operation Template parameter. Reset for each operations.
     */
    private Map<String, SimpleType> operationTemplateParameters = null;

    /**
     * Stack of imported packages (those who end with .* in java source file)
     * they have a lesser priority than classifier import in java semantic
     */
    private Deque<StructuralTree> packageImports = new ArrayDeque<>();

    /**
     * list of imported classifier
     * they have a greater priority than package import in java semantic
     */
    private Deque<ClassifierDef> classifierImports = new ArrayDeque<>();

    public TypeFinder(final SourceStructuralModel sm, final GeneratorStack gs) {
        this.sModel = sm;
        this.gStack = gs;
        // Initialize primitive list
        initPrimitiveType();
    }

    private void initPrimitiveType() {
        String[] prims = { "boolean", "int", "float", "long", "short", "double", "byte", "char" };
        for (String p : prims) {
            this.primitiveTypes.put(p, new PrimitiveType(p));
        }
    }

    private PrimitiveType findPrimitiveType(final String typename) {
        return this.primitiveTypes.get(typename);
    }

    /**
     * Retrieve a Type from a name and the current context using the same rules
     * (afaik) that the java compiler uses these priority rules to search :
     * 1) Primitive type
     * 2) Operation template parameter
     * 3) Inner Type including inside inherited classifiers
     * 4) Outer
     * 5) Class template parameter
     * 6) Class template parameter defined on outer classifier
     * 7) Classifier import (ie : import java.util.List)
     * 8) Full qualified name
     * 9) Same package
     * 10) Package import (ie : import java.util.*)
     * @throws IOException
     * @throws NameCollisionException
     * @param typeName type name can be short, partial or full qualified name
     * @return the ClassifierDef associated with typeName or null if not found
     */
    public Type findType(final String typeName) throws IOException, NameCollisionException {
        Type type = null;
        
        // Primitive type
        type = findPrimitiveType(typeName);
        if (type != null) {
            return type;
        }
        
        // Operation template parameter
        type = findOperationTemplateParameter(typeName);
        if (type != null) {
            return type;
        }
        
        // Inner including inside inherited classifiers
        type = this.sModel.findClassifier(typeName, this.gStack.getCurrentStructuralElement());
        if (type != null) {
            return type;
        }
        
        // Outer
        for (ClassifierDef cd : this.gStack.getCurrentTypeStack()) {
            type = this.sModel.findClassifier(typeName, cd);
            if (type != null) {
                return type;
            }
        
        }
        
        // Class template parameter
        type = StructuralModelUtils.getClassTemplateParameter(typeName, this.gStack.getCurrentStructuralElement());
        if (type != null) {
            return type;
        }
        
        // Class template parameter defined on outer
        for (ClassifierDef cd : this.gStack.getCurrentTypeStack()) {
            type = StructuralModelUtils.getClassTemplateParameter(typeName, cd);
            if (type != null) {
                return type;
            }
        }
        
        // classifier import
        type = findFromImport(typeName);
        if (type != null) {
            return type;
        }
        
        // full qualified name
        type = findFullQualifiedType(typeName);
        if (type != null) {
            return type;
        }
        
        // Same Package
        type = findInSamePackage(typeName);
        if (type != null) {
            return type;
        }
        
        // Package import
        type = findFromPackageImport(typeName);
        if (type != null) {
            return type;
        }
        return type;
    }

    /**
     * Retrieve a base type from a name and the current context using the same rules
     * (afaik) that the java compiler uses these priority rules to search :
     * 1) Outer Type
     * 2)  Classifier import (ie : import java.util.List)
     * 3) Full qualified name
     * 4) Same package
     * 5) Package import (ie : import java.util.*)
     * @throws IOException
     * @throws NameCollisionException
     * @param aBaseTypeName type name can be short, partial or full qualified name
     * @return the ClassifierDef associated with typeName or null if not found
     */
    public Type findBaseType(final String aBaseTypeName) throws IOException, NameCollisionException {
        Type type = null;
        // Outer
        for (ClassifierDef cd : this.gStack.getCurrentTypeStack()) {
            type = this.sModel.findClassifier(aBaseTypeName, cd);
            if (type != null) {
                return type;
            }
        
        }
        
        // classifier import
        type = findFromImport(aBaseTypeName);
        if (type != null) {
            return type;
        }
        
        // full qualified name
        type = findFullQualifiedType(aBaseTypeName);
        if (type != null) {
            return type;
        }
        
        // Same Package
        type = findInSamePackage(aBaseTypeName);
        if (type != null) {
            return type;
        }
        
        // Package import
        type = findFromPackageImport(aBaseTypeName);
        if (type != null) {
            return type;
        }
        return type;
    }

    /**
     * Find typename in the same package than the current analyzed element
     * @param typeName @return
     */
    private Type findInSamePackage(final String typeName) throws IOException, NameCollisionException {
        Type found = findTypeFromParent(typeName, this.gStack.getCurrentPackage());
        if (found instanceof ClassifierDef) {
            ClassifierDef cdf = (ClassifierDef) found;
            if (cdf.getVisibility() == Visibility.PROTECTED || cdf.getVisibility() == Visibility.PRIVATE) {
                found = null;
            }
        }
        return found;
    }

    private Type findFullQualifiedType(final String typeName) throws IOException, NameCollisionException {
        StructuralTree elt = this.sModel.findStructuralTree(typeName);
        return (elt instanceof Type)? (Type) elt : null;
    }

    private Type findTypeFromParent(final String typeName, final StructuralTree parent) throws IOException, NameCollisionException {
        ClassifierDef elt = this.sModel.findClassifier(typeName, parent);
        return  elt;
    }

    /**
     * Register an operation template parameter. It can be a Class parameter or an
     * Operation template parameter depending on the context given by the
     * existence of operationTemplateParameter.
     * @param parName
     */
    public SimpleType registerOperationTemplateParameter(final String parName) {
        SimpleType stype = new SimpleType(parName);
        this.operationTemplateParameters.put(parName, stype);
        return stype;
    }

    public void initOperationTemplateParametersContext() {
        assert this.operationTemplateParameters == null;
        this.operationTemplateParameters = new TreeMap<>();
    }

    public void clearOperationTemplateParametersContext() {
        this.operationTemplateParameters = null;
    }

    private Type findOperationTemplateParameter(final String typeName) {
        if (this.operationTemplateParameters != null) {
            return this.operationTemplateParameters.get(typeName);
        }
        return null;
    }

    /**
     * Register an import element into the proper list according to its nature (packageimport or not)
     * @param importNs
     * @param isPackageImport
     */
    public void registerImport(final StructuralTree importNs, final boolean isPackageImport) {
        if (isPackageImport) {
            this.packageImports.push(importNs);
        } else {
            this.classifierImports.push((ClassifierDef) importNs);
        }
    }

    /**
     * Search the type using the classifier import list
     * @throws IOException
     * @throws NameCollisionException
     * @param typeName : the partial name to search
     * @return the type found or null if none matches
     */
    private Type findFromImport(final String typeName) throws IOException, NameCollisionException {
        // 1) find from the classifier import list
        for(ClassifierDef it : this.classifierImports) {
            if (it.getName().equals(typeName)) {
                // perfect match !
                return it;
            } else {
                String itname = it.getName() + ".";
                if (typeName.startsWith(itname)) {
                    // same prefix : try among children of 'it'
                    String tn = typeName.replaceFirst(itname, "");
                    Type elt = findTypeFromParent(tn, it);
                    if (elt != null) {
                        return elt;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Search the type using the package import list
     * @throws IOException
     * @throws NameCollisionException
     * @param typeName : the partial name to search
     * @return the type found or null if none matches
     */
    private Type findFromPackageImport(final String typeName) throws IOException, NameCollisionException {
        for (StructuralTree pi : this.packageImports) {
            Type elt = findTypeFromParent(typeName, pi);
            if (elt != null) {
                return elt;
            }
        }
        return null;
    }

    public void clearImports() throws IOException, NameCollisionException {
        this.classifierImports.clear();
        this.packageImports.clear();
        // (re)load java.lang default package
        StructuralTree javalang = this.sModel.findStructuralTree("java.lang");
        if (javalang != null) {
            this.packageImports.push(javalang);
        }
    }

    /**
     * Clear the whole current instance to start a fresh analysis
     * @throws IOException
     * @throws NameCollisionException
     */
    public void clear() throws IOException, NameCollisionException {
        clearImports();
        this.operationTemplateParameters = null;
    }

}
