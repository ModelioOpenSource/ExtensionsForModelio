package org.modelio.module.javadesigner.reverse.newwizard.api;

import java.io.File;
import java.util.List;

public interface IExternalJarsModel {

    List<File> getClasspath();

    List<String> getValidExtensions();

}
