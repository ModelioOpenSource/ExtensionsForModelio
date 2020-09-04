package org.modelio.module.javadesigner.reverse.ui;


public class ElementStatus {
    private String value;

    private ReverseStatus reverseStatus;

    private ElementType type;

    public ElementStatus(final String value, final ElementType type) {
        this (value, type, ReverseStatus.NO_REVERSE);
    }

    public ElementStatus(final String value, final ElementType type, final ReverseStatus reverseStatus) {
        super ();
        this.value = value;
        this.type = type;
        this.reverseStatus = reverseStatus;
    }

    public ReverseStatus getReverseStatus() {
        return this.reverseStatus;
    }

    public void setReverseStatus(final ReverseStatus reverseStatus) {
        this.reverseStatus = reverseStatus;
    }

    public ElementType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setType(final ElementType i) {
        this.type = i;
    }

    public void setValue(final String string) {
        this.value = string;
    }

    public enum ReverseStatus {
        NO_REVERSE,
        REVERSE_SOME_CHILDREN,
        REVERSE;
    }

    public enum ElementType {
        DIRECTORY,
        JAVA_FILE,
        CLASS_FILE;
    }

}
