package org.modelio.module.javadesigner.reverse.newwizard.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.modelio.module.javadesigner.reverse.newwizard.api.IClasspathModel;

public class JavaClasspathModel implements IClasspathModel {
    private List<File> classpath;

    private File initialDirectory;

    public JavaClasspathModel(final File initialDirectory) {
        this.classpath = new ArrayList<>();
        this.initialDirectory = initialDirectory;
    }

    @Override
    public List<File> getClasspath() {
        return this.classpath;
    }

    @Override
    public List<String> getValidExtensions() {
        List<String> extensions = new ArrayList<>();
        
        extensions.add("*.jar");
        return extensions;
    }

    @Override
    public File getInitialDirectory() {
        return this.initialDirectory;
    }

}
