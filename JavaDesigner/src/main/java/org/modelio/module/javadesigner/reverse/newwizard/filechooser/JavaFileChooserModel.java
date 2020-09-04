package org.modelio.module.javadesigner.reverse.newwizard.filechooser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modelio.module.xmlreverse.model.IVisitorElement;
import com.modelio.module.xmlreverse.model.JaxbReversedData;
import org.modelio.module.javadesigner.reverse.ReverseConfig.GeneralReverseMode;
import org.modelio.module.javadesigner.reverse.ReverseConfig;
import org.modelio.module.javadesigner.reverse.javautil.io.JavaFileFinder;
import org.modelio.module.javadesigner.reverse.newwizard.api.IFileChooserModel;
import org.modelio.module.javadesigner.reverse.newwizard.binary.JarContentPreview;

public class JavaFileChooserModel implements IFileChooserModel {
    private List<String> extensions;

    private GeneralReverseMode granularity;

    private List<File> filesToImport;

    private File initialDirectory;

    private List<IVisitorElement> result;

    private JaxbReversedData cachedModel;

    private ReverseConfig config;

    private Set<File> cachedFiles;

    public JavaFileChooserModel(final File initialDirectory, final List<String> extensions, final ReverseConfig config) {
        this.initialDirectory = initialDirectory;
        this.filesToImport = new ArrayList<>();
        this.granularity = GeneralReverseMode.COMPLETE_REVERSE;
        this.extensions = extensions;
        this.result = new ArrayList<>();
        this.cachedFiles = new HashSet<>();
        this.config = config;
    }

    @Override
    public List<String> getValidExtensions() {
        return this.extensions;
    }

    @Override
    public File getInitialDirectory() {
        return this.initialDirectory;
    }

    @Override
    public List<File> getFilesToImport() {
        return this.filesToImport;
    }

    @Override
    public void setFilesToImport(final List<File> filesToImport) {
        this.filesToImport = filesToImport;
    }

    @Override
    public void setInitialDirectory(final File initialDirectory) {
        this.initialDirectory = initialDirectory;
    }

    @Override
    public GeneralReverseMode getGranularity() {
        return this.granularity;
    }

    @Override
    public void setGranularity(final GeneralReverseMode value) {
        this.granularity = value;
    }

    @Override
    public JaxbReversedData getAssemblyContentModel() {
        JarContentPreview previewer = new JarContentPreview();
        Set<File> files = new HashSet<>();
        
        // Get all files recursively
        for (File f : getFilesToImport()) {
            if (f.isDirectory()) {
                files.addAll(JavaFileFinder.listJarFilesRec(f));
            } else {
                files.add(f);
            }
        }
        
        if (this.cachedModel == null || !files.equals(this.cachedFiles)) {
            this.cachedFiles.clear();
            this.cachedFiles.addAll(files);
        
            this.cachedModel = previewer.computePreview(files, this.config);
        }
        return this.cachedModel;
    }

    @Override
    public List<IVisitorElement> getResult() {
        return this.result;
    }

    @Override
    public String getValidExtensionsList() {
        // Build the extension list
        StringBuilder extensionsList = new StringBuilder();
        
        for (String extension : this.getValidExtensions()) {
            extensionsList.append(extension);
            extensionsList.append(", ");
        }
        extensionsList.delete(extensionsList.length() - 2, extensionsList.length());
        return extensionsList.toString();
    }

    @Override
    public List<File> getReverseRoots() {
        List<File> ret = new ArrayList<>();
        for (File f : getFilesToImport()) {
            if (f.isDirectory()) {
                ret.add(f);
            } else if (f.isFile()) {
                final File parentFile = f.getParentFile();
                if (!ret.contains(parentFile)) {
                    ret.add(parentFile);
                }
            }
        }
        return ret;
    }

}
