package org.modelio.module.javadesigner.reverse.newwizard.api;

import java.io.File;
import java.util.List;

public interface IClasspathModel {

    List<File> getClasspath();

    List<String> getValidExtensions();

    File getInitialDirectory();

}
