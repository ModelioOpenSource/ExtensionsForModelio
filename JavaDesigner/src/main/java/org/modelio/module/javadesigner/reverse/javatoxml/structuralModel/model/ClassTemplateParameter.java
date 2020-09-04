package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;


/**
 * Simple type without children and we don't care about its parent.
 * 
 * Typically Template parameters for Operation
 */
public class ClassTemplateParameter extends StructuralElement implements Type {
    private String name;

    public ClassTemplateParameter(final String name) {
        this.name = name;
    }

    @Override
    public String getTypeName() {
        return this.name;
    }

    @Override
    public String getPackageName() {
        return "";
    }

    @Override
    public String getFullQualifiedName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getFullQualifiedName();
    }

    @Override
    public String getName() {
        return this.name;
    }

}
