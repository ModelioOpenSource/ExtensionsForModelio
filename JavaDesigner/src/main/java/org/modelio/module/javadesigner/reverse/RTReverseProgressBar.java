package org.modelio.module.javadesigner.reverse;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.modelio.api.module.IModule;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.progress.ProgressBar;
import org.modelio.module.javadesigner.reverse.common.WellKnownContainerServices;
import org.modelio.module.javadesigner.reverse.javatoxml.XMLBuffer;
import org.modelio.module.javadesigner.reverse.javatoxml.binary.XMLGeneratorFromBinary;
import org.modelio.module.javadesigner.reverse.javatoxml.source.XMLGeneratorFromSource;
import org.modelio.module.javadesigner.reverse.javautil.io.JavaFileFinder;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus;
import org.modelio.module.javadesigner.reverse.xmltomodel.JavaReverse;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

public class RTReverseProgressBar extends ProgressBar implements IRunnableWithProgress {
    private ReverseConfig config;

    public RTReverseProgressBar(final IModule module, final ReverseConfig config) {
        super (module, 0);
        this.config = config;
        startTime = Calendar.getInstance ().getTimeInMillis ();
    }

    @Override
    public void run(final IProgressMonitor localMonitor) throws InterruptedException, InvocationTargetException {
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Parsing start at " + LocalDateTime.now());
        monitor = localMonitor;
        try {
            if (this.config.getReverseType() == ReverseType.SOURCE) {
                // Source reverse
                launchSourceReverse ();
            } else {
                // Binary reverse : XML file has already been parsed.
                if (this.config.getModel () != null) {
                    reverseModel();
                }
            }
        } catch (Exception e) {
            // Store the stack trace in the error report
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
        
            this.config.getReport().addError(Messages.getString("Error.UnexpectedException"), null, errors.toString());
        
            // Log it too
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            throw e;
        } finally {
            if (this.config.getReport().hasErrors()) {
                this.config.getReport().addError(Messages.getString("Error.ReverseCanceled"), null, Messages.getString("Error.ReverseCanceled.Description"));
            }
        }
    }

    public void launchReverseFromJar() {
        ArrayList<String> compiledFilesToReverse = new ArrayList<> ();
        ElementStatus eStatus;
        
        for (Iterator<ElementStatus> i = this.config.getFilesToReverse().values().iterator() ; i.hasNext() ;) {
            eStatus = i.next ();
            if (eStatus.getReverseStatus () == ElementStatus.ReverseStatus.REVERSE && !eStatus.getValue ().endsWith (".jar") && (eStatus.getValue ().endsWith (".class") || eStatus.getType () == ElementStatus.ElementType.CLASS_FILE)) {
                compiledFilesToReverse.add (eStatus.getValue ());
            }
        }
        
        if (compiledFilesToReverse.size () > 0) {
            List<String> list = getClassNamesFromString (compiledFilesToReverse);
            reverseCompiledFiles (list);
        }
    }

    /**
     * Reverse from an already loaded XML file (ReversedData)
     */
    private void reverseModel() {
        // Launch the XML reverse
        JavaReverse reverse = new JavaReverse ();
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Reversing model into " + this.config.getReverseRoot ());
        reverse.reverseModel (this.module.getModuleContext().getModelingSession(), this.config.getModel(), this.config.getFilteredElements(), this.config.getReverseRoot (), this.config.getReport (), this.config.getStrategyConfiguration ());
    }

    /**
     * Reverse source files. This is a 2 steps process :
     * 1) parse java file into an XML intermediate structure (into a file)
     * 2) reverse
     * @throws InterruptedException
     */
    private void launchSourceReverse() throws InterruptedException {
        init (true);
        
        // 0) Load well known container set.
        WellKnownContainerServices.loadContainerConfiguration (this.config.getContainerFile());
        
        ArrayList<File> sourcesFilesToReverse = getSourcesFilesToReverse ();
        // 1) reverse source file into XML
        reverseSourcesfiles (sourcesFilesToReverse);
        
        if (this.config.getOutputFile().isFile() && this.config.getOutputFile().length() > 0) {
            // 2) reverse from XML file into the model
            reverseXMLFile(this.config.getOutputFile());
        }
        
        WellKnownContainerServices.clear();
        monitor.done ();
    }

    private void reverseSourcesfiles(final List<File> sourcesFilesToReverse) {
        try {
            Date timeToRun = new Date ();
        
            // Create the generator that will produce the XML intermediate file
            XMLGeneratorFromSource XMLGeneratorFromSource = new XMLGeneratorFromSource(sourcesFilesToReverse, this.config.getSourcepath(), this.config.getClasspath(), this.config.getReport(), this.config.getEncoding());
        
            int nb = sourcesFilesToReverse.size()*6;
            setMaximumValue (nb);
            monitor.beginTask ("Reversing", nb);
        
            timeToRun.setTime (System.currentTimeMillis ());
            setTaskName (Messages.getString ("Gui.Reverse.ParsingFiles")); //$NON-NLS-1$
        
            XMLGeneratorFromSource.generateModel(sourcesFilesToReverse, this.config.getOutputFile(), this.config.getEncoding());
        
            timeToRun.setTime (System.currentTimeMillis ());
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.Exception", e.getMessage ())); //$NON-NLS-1$
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e); // so we can get stack trace
            this.config.getOutputFile().delete ();
        }
    }

    private List<String> getClassNamesFromString(final List<String> compiledFilesToReverse) {
        List<String> list = new ArrayList<> ();
        String fileName;
        int index;
        StringTokenizer st;
        
        for (Iterator<String> i = compiledFilesToReverse.iterator () ; i.hasNext () ;) {
            fileName = i.next ();
        
            if (fileName.indexOf ("\\") == 0) {
                fileName = fileName.substring (1);
            }
        
            if (fileName.indexOf ("/") == 0) {
                fileName = fileName.substring (0);
            }
        
            index = fileName.indexOf (".class");
            if (index != -1) {
                fileName = fileName.substring (0, index);
            }
        
            st = new StringTokenizer (fileName, "\\");
            fileName = "";
            while (st.hasMoreTokens ()) {
                fileName = fileName + st.nextToken ();
                if (st.hasMoreTokens ()) {
                    fileName = fileName + ".";
                }
            }
        
            fileName = fileName.replace ('/', '.');
            list.add (fileName);
        }
        return list;
    }

    private void reverseCompiledFiles(final List<String> compiledFilesToReverse) {
        Date timeToRun = new Date ();
        
        if (compiledFilesToReverse.size () == 0) {
            this.config.getReport ().addError (Messages.getString ("Error.EmptyBinaryList"), null, Messages.getString ("Error.EmptyBinaryList.Description"));
            return;
        }
        
        try {
            setMaximumValue (compiledFilesToReverse.size () * 5);
            if (monitor != null) {
                monitor.beginTask ("Reversing", compiledFilesToReverse.size () * 5);
            }
            setTaskName (Messages.getString ("Gui.Reverse.AnalysingStructure")); //$NON-NLS-1$
        
            // Create model structure
            timeToRun.setTime (System.currentTimeMillis ());
        
            XMLBuffer.open (this.config.getOutputFile(), this.config.getEncoding());
        
            XMLGeneratorFromBinary bGenerator = new XMLGeneratorFromBinary (this.config.getClasspath(), this.config.getReport());
            bGenerator.printClasses (compiledFilesToReverse);
            timeToRun.setTime (System.currentTimeMillis ());
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(Messages.getString ("Error.Exception", e.getMessage ())); //$NON-NLS-1$
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e); // so we can get stack trace
        }
    }

    private ArrayList<File> getSourcesFilesToReverse() {
        ArrayList<File> list = new ArrayList<> ();
        ArrayList<ElementStatus> listToReverse = new ArrayList<> (this.config.getFilesToReverse().values ());
        ElementStatus eStatus;
        File fileToAdd;
        
        List<File> tmpList;
        File tmpFile;
        
        for (Iterator<ElementStatus> i = listToReverse.iterator () ; i.hasNext () ;) {
            eStatus = i.next ();
            if (eStatus.getReverseStatus () == ElementStatus.ReverseStatus.REVERSE && !eStatus.getValue ().endsWith (".class")) {
                fileToAdd = new File (eStatus.getValue ());
                if (fileToAdd.isFile ()) {
                    if (!isInFileList (list, fileToAdd)) {
                        list.add (fileToAdd);
                    }
                } else {
                    tmpList = JavaFileFinder.listJavaFilesRec (fileToAdd);
                    for (Iterator<File> j = tmpList.iterator () ; j.hasNext () ;) {
                        tmpFile = j.next ();
                        if (!isInFileList (list, tmpFile)) {
                            list.add (tmpFile);
                        }
                    }
                }
            }
        }
        return list;
    }

    private boolean isInFileList(final List<File> list, final File file) {
        File tmpFile;
        for (Iterator<File> i = list.iterator () ; i.hasNext () ;) {
            tmpFile = i.next ();
        
            if (tmpFile.getAbsolutePath ().equals (file.getAbsolutePath ())) {
                return true;
            }
        }
        return false;
    }

    private void reverseXMLFile(final File outputFile) {
        copyXSD (outputFile.getParentFile ());
        
        // Launch the XML reverse
        JavaReverse reverse = new JavaReverse ();
        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Reversing " + outputFile + " into " + this.config.getReverseRoot ());
        reverse.reverseModel (this.module.getModuleContext().getModelingSession(), outputFile, this.config.getReverseRoot (), this.config.getReport (), this.config.getStrategyConfiguration ());
    }

    private void copyXSD(final File directory) {
        String dtdFile = "rev-modele.xsd"; //$NON-NLS-1$
        File source = new File (this.module.getModuleContext().getConfiguration ().getModuleResourcesPath ().toString () +
                File.separatorChar + "bin" + File.separatorChar + dtdFile); //$NON-NLS-1$
        File target = new File (directory.toString () + File.separatorChar +
                dtdFile);
        
        JavaDesignerUtils.copyFile (source, target);
    }

}
