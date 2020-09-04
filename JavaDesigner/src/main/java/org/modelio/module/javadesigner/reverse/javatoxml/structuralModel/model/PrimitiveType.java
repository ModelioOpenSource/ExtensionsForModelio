package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;


/**
 * Java Primitive types (boolean, int, etc) but not String, Date
 */
public class PrimitiveType implements Type {
    private String name;

    public PrimitiveType(final String name) {
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
        return this.name;
    }

}
