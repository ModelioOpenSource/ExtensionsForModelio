package org.modelio.module.javadesigner.reverse.newwizard.api;

import java.io.File;
import java.util.List;
import com.modelio.module.xmlreverse.model.IVisitorElement;
import com.modelio.module.xmlreverse.model.JaxbReversedData;
import org.modelio.module.javadesigner.reverse.ReverseConfig.GeneralReverseMode;

public interface IFileChooserModel {
    List<String> getValidExtensions();

    File getInitialDirectory();

    void setInitialDirectory(final File initialDirectory);

    List<File> getFilesToImport();

    void setFilesToImport(final List<File> filesToImport);

    GeneralReverseMode getGranularity();

    void setGranularity(final GeneralReverseMode value);

    JaxbReversedData getAssemblyContentModel();

    List<IVisitorElement> getResult();

    String getValidExtensionsList();

    List<File> getReverseRoots();

}
