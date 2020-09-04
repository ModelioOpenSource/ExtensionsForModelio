package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;

import org.modelio.module.javadesigner.reverse.javatoxml.identification.Identified;

/**
 * Simple type without children and we don't care about its parent.
 * 
 * Typically Template parameters for Operation
 */
public class SimpleType implements Type, Identified {
    private String name;

    private String packageName;

    public SimpleType(final String name) {
        this.name = name;
        this.packageName = "";
    }

    public SimpleType(final String name, final String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    @Override
    public String getTypeName() {
        return this.name;
    }

    @Override
    public String getPackageName() {
        return this.packageName;
    }

    @Override
    public String getFullQualifiedName() {
        if (!this.packageName.isEmpty()) {
            StringBuilder fqn = new StringBuilder(this.packageName);
            fqn.append('.').append(this.name);
            return fqn.toString();
        } else {
            return this.name;
        }
    }

    @Override
    public String toString() {
        return getFullQualifiedName();
    }

}
