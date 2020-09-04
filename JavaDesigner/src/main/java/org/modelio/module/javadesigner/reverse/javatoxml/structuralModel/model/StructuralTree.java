package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel.model;

import java.util.Map;
import java.util.TreeMap;

public abstract class StructuralTree extends StructuralElement {
    private String name;

    private Map<String, StructuralTree> owned;

    private StructuralTree owner;

    private static Map<String, StructuralTree> emptyOwned = new TreeMap<>();

    public StructuralTree(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Return the full qualified name of Java class (enum, annotation, etc) or Java package
     * @return
     */
    public String getFullQualifiedName() {
        if (this.owner != null) {
            String fullQualifiedName = this.owner.getFullQualifiedName ();
            return (fullQualifiedName.isEmpty()?"":fullQualifiedName + ".") + this.name; //$NON-NLS-1$
        } else {
            return this.name;
        }
    }

    public final Map<String, StructuralTree> getOwned() {
        return (this.owned != null)? this.owned : emptyOwned;
    }

    public void addOwned(final StructuralTree child) {
        if (this.owned == null) {
            this.owned = new TreeMap<>();
        }
        this.owned.put(child.getName(), child);
        child.setOwner(this);
    }

    public StructuralTree getOwner() {
        return this.owner;
    }

    public void setOwner(final StructuralTree def) {
        this.owner = def;
    }

    @Override
    public String toString() {
        return getFullQualifiedName();
    }

    public StructuralTree getOwned(final String aShortName) {
        return (this.owned == null)? null : this.owned.get(aShortName);
    }

}
