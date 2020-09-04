package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Type definition in the structural model
 * 
 * A Classifier parent is a PackageDef for top level classifier or Classifier for inner Classifier
 * (null parent is a special case of top classifier with no package)
 * 
 * @see org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.PackageDef
 * @see org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model.StructuralTree
 */
public class ClassifierDef extends StructuralTree implements Type {
    private ClassifierDefKind kind;

    private Visibility visibility;

    /**
     * Template parameters of the classifier
     */
    private Map<String, ClassTemplateParameter> templateParameters;

    /**
     * List of extends and implements
     */
    private List<ClassifierDef> parents;

    public ClassifierDef(final String name, ClassifierDefKind kind) {
        super(name);
        this.setKind(kind);
        this.setVisibility(Visibility.UNKNOWN);
    }

    /**
     * Return the type part without package :
     * example
     * com.acme.Clazz gives Clazz
     * com.acme.Clazz.Embed gives Clazz.Embed
     * @return
     */
    @Override
    public String getTypeName() {
        StructuralTree parent = this.getOwner();
        if (parent != null && parent instanceof ClassifierDef) {
            StringBuilder sb = new StringBuilder(((ClassifierDef)parent).getTypeName());
            return sb.append('.').append(getName()).toString();
        } else {
            return getName();
        }
    }

    /**
     * Return the complete package name without embedded classes
     * example:
     * com.acme.Clazz gives com.acme
     * com.acme.Clazz.Embed gives com.acme
     * @return
     */
    @Override
    public String getPackageName() {
        StructuralTree parent = this.getOwner();
        while (parent != null && !(parent instanceof PackageDef)) {
            parent = parent.getOwner();
        }
        if (parent == null) {
            return "";
        } else {
            return parent.getFullQualifiedName();
        }
    }

    /**
     * Tell if the current type at top level (ie not nested in another type)
     */
    public boolean isTopLevel() {
        return this.getOwner() == null || this.getOwner() instanceof PackageDef;
    }

    public Map<String, ClassTemplateParameter> getTemplateParameters() {
        return this.templateParameters;
    }

    public void addTemplateParameter(final ClassTemplateParameter aTmplPar) {
        if (this.templateParameters == null) {
            this.templateParameters = new TreeMap<>();
        }
        this.templateParameters.put(aTmplPar.getName(), aTmplPar);
    }

    public List<ClassifierDef> getInherits() {
        return this.parents;
    }

    public void addInherits(final ClassifierDef aInherit) {
        if (this.parents == null) {
            this.parents = new ArrayList<>();
        }
        this.parents.add(aInherit);
    }

    public PackageDef getPackage() {
        StructuralTree par = this.getOwner();
        while (par != null) {
            if (par instanceof PackageDef) {
                return (PackageDef) par;
            }
            par = par.getOwner();
        }
        return null;
    }

    public ClassifierDefKind getKind() {
        return this.kind;
    }

    public void setKind(final ClassifierDefKind kind) {
        this.kind = kind;
    }

    public Visibility getVisibility() {
        return this.visibility;
    }

    public void setVisibility(final Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isLooselyOwnedBy(final StructuralTree aRoot) {
        StructuralTree par = this.getOwner();
        while (par != null) {
            if (par == aRoot) {
                return true;
            }
            par = par.getOwner();
        }
        return false;
    }

    public boolean isHeirOf(final ClassifierDef aParent) {
        for (ClassifierDef par : this.parents) {
            if (par == aParent || par.isHeirOf(aParent)) {
                return true;
            }
        }
        return false;
    }

    public enum ClassifierDefKind {
        CLASS,
        INTERFACE,
        DATATYPE,
        ENUMERATION,
        UNKNOWN;
    }

    public enum Visibility {
        PUBLIC,
        PROTECTED,
        PACKAGE,
        PRIVATE,
        UNKNOWN;
    }

}
