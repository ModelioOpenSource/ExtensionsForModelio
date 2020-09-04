package org.modelio.module.javadesigner.reverse.newwizard.externaljars;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;

public class ExternalJarsClasspathModel implements IExternalJarsModel {
    private List<File> classpath;


    public ExternalJarsClasspathModel() {
        this.classpath = new ArrayList<>();
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

}
