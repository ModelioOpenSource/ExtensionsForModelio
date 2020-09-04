package org.modelio.module.javadesigner.reverse;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import com.modelio.module.xmlreverse.IReportWriter;
import com.modelio.module.xmlreverse.model.IVisitorElement;
import com.modelio.module.xmlreverse.model.JaxbReversedData;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus;

public class ReverseConfig {
    private ReverseType reverseType;

    private Map<String, ElementStatus> filesToReverse;

    private List<File> sourcepath;

    private List<File> classpath;

    private File containerFile;

    private File outputFile;

    private NameSpace reverseRoot;

    private ReverseStrategyConfiguration strategyConfiguration;

    private IReportWriter report;

    private JaxbReversedData model;

    private List<IVisitorElement> filteredElements;

    /**
     * @return the filesToReverse
     */
    public Map<String, ElementStatus> getFilesToReverse() {
        return this.filesToReverse;
    }

    /**
     * @param filesToReverse the filesToReverse to set
     */
    public void setFilesToReverse(final Map<String, ElementStatus> filesToReverse) {
        this.filesToReverse = filesToReverse;
    }

    public List<File> getSourcepath() {
        return this.sourcepath;
    }

    /**
     * @return the classpath
     */
    public List<File> getClasspath() {
        return this.classpath;
    }

    /**
     * @param classpath the classpath to set
     */
    public void setClasspath(final List<File> classpath) {
        this.classpath = classpath;
    }

    /**
     * @return the containerFile
     */
    public File getContainerFile() {
        return this.containerFile;
    }

    /**
     * @param containerFile the containerFile to set
     */
    public void setContainerFile(final File containerFile) {
        this.containerFile = containerFile;
    }

    /**
     * @return the outputFile
     */
    public File getOutputFile() {
        return this.outputFile;
    }

    /**
     * @return the reverseMode
     */
    public GeneralReverseMode getReverseMode() {
        return this.strategyConfiguration.reverseMode;
    }

    /**
     * @param reverseMode the reverseMode to set
     */
    public void setReverseMode(final GeneralReverseMode reverseMode) {
        this.strategyConfiguration.reverseMode = reverseMode;
    }

    /**
     * @return the reverseType
     */
    public ReverseType getReverseType() {
        return this.reverseType;
    }

    /**
     * @param reverseType the reverseType to set
     */
    public void setReverseType(final ReverseType reverseType) {
        this.reverseType = reverseType;
    }

    /**
     * @return the model
     */
    public JaxbReversedData getModel() {
        return this.model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(final JaxbReversedData model) {
        this.model = model;
    }

    /**
     * @return the filteredElements
     */
    public List<IVisitorElement> getFilteredElements() {
        return this.filteredElements;
    }

    /**
     * @param filteredElements the filteredElements to set
     */
    public void setFilteredElements(final List<IVisitorElement> filteredElements) {
        this.filteredElements = filteredElements;
    }

    public ReverseConfig(final Hashtable<String, ElementStatus> filesToReverse, final List<File> sourcepath, final List<File> classpath, final ReverseType reverseType, final File containerFile, final File outputFile) {
        this.filesToReverse = filesToReverse;
        this.sourcepath = sourcepath;
        this.classpath = classpath;
        this.reverseType = reverseType;
        this.containerFile = containerFile;
        this.outputFile = outputFile;
    }

    public void print() {
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("---------------");
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Nb files to reverse=" + //$NON-NLS-1$
                this.filesToReverse.size ());
        for (String file : this.filesToReverse.keySet ()) {
            ElementStatus status = this.filesToReverse.get(file);
            JavaDesignerModule.getInstance().getModuleContext().getLogService().info(status.getReverseStatus().toString());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().info("\t" + status.getType());
            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("\tfile=" + file); //$NON-NLS-1$
        }
        
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Nb sourcepath=" + //$NON-NLS-1$
                this.sourcepath.size ());
        for (File path : this.sourcepath) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("sourcepath=" + path); //$NON-NLS-1$
        }
        
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Nb classpath=" + //$NON-NLS-1$
                this.classpath.size ());
        for (File path : this.classpath) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("classpath=" + path); //$NON-NLS-1$
        }
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("reverseType=" + this.reverseType); //$NON-NLS-1$
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("containerFile=" + this.containerFile); //$NON-NLS-1$
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("outputFile=" + this.outputFile); //$NON-NLS-1$
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("---------------");
    }

    public void setReport(final IReportWriter report) {
        this.report = report;
    }

    public IReportWriter getReport() {
        return this.report;
    }

    public NameSpace getReverseRoot() {
        return this.reverseRoot;
    }

    public void setReverseRoot(final NameSpace reverseRoot) {
        this.reverseRoot = reverseRoot;
    }

    public void setStrategyConfiguration(final ReverseStrategyConfiguration strategyConfiguration) {
        this.strategyConfiguration = strategyConfiguration;
    }

    public ReverseStrategyConfiguration getStrategyConfiguration() {
        return this.strategyConfiguration;
    }

    public String getEncoding() {
        return this.strategyConfiguration.ENCODING;
    }

    public enum GeneralReverseMode {
        SIMPLE_STRUCTURAL_REVERSE,
        COMPLETE_STRUCTURAL_REVERSE,
        COMPLETE_REVERSE;

        @Override
        public String toString() {
            return Messages.getString("Gui.GeneralReverseMode." + this.name());
        }

    }

}
