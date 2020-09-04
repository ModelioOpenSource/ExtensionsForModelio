package org.modelio.module.javadesigner.reverse.javatoxml.structuralModel;

import java.io.File;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLGeneratorException;

/**
 * A Name collision between two types or type and package
 */
public class NameCollisionException extends XMLGeneratorException {
    private String typeName;

    private static final long serialVersionUID = -7206891207541212780L;

    private File secondFile;

    NameCollisionException(final String typeName) {
        super();
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setSecondFile(final File aFile) {
        this.secondFile = aFile;
    }

    public File getSecondFile() {
        return this.secondFile;
    }

}
