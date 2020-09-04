package org.modelio.module.javadesigner.reverse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import com.modelio.module.xmlreverse.IReportWriter;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.IRefreshService;
import org.modelio.module.javadesigner.api.ISessionWithHandler;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.reverse.newwizard.ImageManager;
import org.modelio.module.javadesigner.reverse.newwizard.api.IClasspathModel;
import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;
import org.modelio.module.javadesigner.reverse.newwizard.api.IFileChooserModel;
import org.modelio.module.javadesigner.reverse.newwizard.classpath.JavaClasspathModel;
import org.modelio.module.javadesigner.reverse.newwizard.externaljars.ExternalJarsClasspathModel;
import org.modelio.module.javadesigner.reverse.newwizard.filechooser.JavaFileChooserModel;
import org.modelio.module.javadesigner.reverse.newwizard.wizard.JavaReverseWizardView;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus.ElementType;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus.ReverseStatus;
import org.modelio.module.javadesigner.reverse.ui.ElementStatus;
import org.modelio.module.javadesigner.utils.IOtherProfileElements;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.module.javadesigner.utils.ModelUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

public class Reversor {
    private IReportWriter report;

    private IModule module;

    private ISessionWithHandler javaDesignerSession;

    private Collection<NameSpace> elementsToReverse;

    public Reversor(final IModule module, final IReportWriter report) {
        this.module = module;
        this.report = report;
        
        IModuleLifeCycleHandler moduleSession = module.getLifeCycleHandler();
        if (moduleSession instanceof ISessionWithHandler) {
            this.javaDesignerSession = (ISessionWithHandler) moduleSession;
        } else {
            this.javaDesignerSession = null;
        }
    }

    public void reverseWizard(final ReverseType reverseType, final NameSpace reverseRoot) {
        removeModelHandler();
        
        this.elementsToReverse = new HashSet<> ();
        this.elementsToReverse.add (reverseRoot);
        reverseInRoundtripMode (true, reverseType);
        
        restoreModelhandler();
    }

    public void update(final Collection<NameSpace> elements, final IRefreshService refreshService) {
        boolean isRoundTripMode = JavaDesignerUtils.isRoundtripMode (this.module);
        
        removeModelHandler();
        
        this.elementsToReverse = elements;
        
        if (!this.elementsToReverse.isEmpty ()) {
            if (isRoundTripMode) {
                reverseInRoundtripMode (false, ReverseType.SOURCE);
            } else {
                // Model driven: only reverse between identifiers
                reverseInModelDrivenMode ();
            }
        }
        
        // Refresh the editors
        if (refreshService != null) {
            refreshService.refresh (this.elementsToReverse);
        }
        
        restoreModelhandler();
    }

    private ArrayList<File> getClassPath() throws IOException {
        ArrayList<File> classpath = new ArrayList<> ();
        StringTokenizer st;
        
        String separator = System.getProperty ("os.name").startsWith ("Windows") ? ";" : ":";
        
        st = new StringTokenizer (this.module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.ACCESSIBLECLASSES), separator); //$NON-NLS-1$
        while (st.hasMoreTokens ()) {
            File file = new File (st.nextToken ());
            if (file.exists() && !isInFileList (classpath, file)) {
                classpath.add (file);
            }
        }
        
        for (File file : getRamcClasspath ()) {
            if (!isInFileList (classpath, file)) {
                classpath.add (file);
            }
        }
        return classpath;
    }

    private ArrayList<File> getCompilationPaths() throws IOException {
        ArrayList<File> compilationpath = new ArrayList<> ();
        
        for (Project project : this.module.getModuleContext().getModelingSession().findByClass(Project.class)) {
            for (Package model : project.getModel()) {
                StringTokenizer st;
                String mainCompilationPath = JavaDesignerUtils.getCompilationPath(model, this.module).getAbsolutePath();
                st = new StringTokenizer(mainCompilationPath, ";"); //$NON-NLS-1$
                while (st.hasMoreTokens ()) {
                    File file = new File (st.nextToken ());
                    if (file.exists() && !isInFileList (compilationpath, file)) {
                        compilationpath.add (file);
                    }
                }
            }
        }
        return compilationpath;
    }

    private ElementType getTypePath(final File file) {
        ElementStatus.ElementType type;
        
        if (file.getAbsolutePath ().endsWith (".java")) { //$NON-NLS-1$
            type = ElementStatus.ElementType.JAVA_FILE;
        } else if (file.getAbsolutePath ().endsWith (".class")) { //$NON-NLS-1$
            type = ElementStatus.ElementType.CLASS_FILE;
        } else {
            type = ElementStatus.ElementType.DIRECTORY;
        }
        return type;
    }

    private boolean isInFileList(final List<File> list, final File file) throws IOException {
        String filename;
        filename = file.getCanonicalPath();
        for (File f : list) {
            if (f.getCanonicalPath().equals(filename)) {
                return true;
            }
        }
        return false;
    }

    private void reverseInModelDrivenMode() {
        IModelingSession session = this.module.getModuleContext().getModelingSession ();
        try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.Reverse"))) {
            ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
            dialog.open();
            dialog.run(true, true, new MDReverseProgressBar (this.module, this.elementsToReverse, this.report));
            transaction.commit();
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    private void reverseInRoundtripMode(final boolean withWizard, final ReverseType reverseType) {
        final IModelingSession session = this.module.getModuleContext().getModelingSession ();
        
        boolean error = false;
        File file;
        
        File containerFile = new File (this.module.getModuleContext().getConfiguration ().getModuleResourcesPath () + File.separator + "bin" + File.separator + "containers.xml"); //$NON-NLS-1$//$NON-NLS-2$
        
        try {
            File outputFile = File.createTempFile ("java", ".xml"); //$NON-NLS-1$//$NON-NLS-2$
            outputFile.deleteOnExit ();
            if (withWizard) {
                // Reverse with wizard
                // Build the elements to reverse
                try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.Reverse"))) {
        
                    ReverseConfig config = new ReverseConfig (new Hashtable<String, ElementStatus> (), new ArrayList<File>(), new ArrayList<File>(), reverseType, containerFile, outputFile);
                    config.setReport (this.report);
                    config.setStrategyConfiguration (new ReverseStrategyConfiguration (this.module.getModuleContext().getConfiguration ()));
        
                    // Store the reverse root
                    if (this.elementsToReverse.size () == 1) {
                        config.setReverseRoot (this.elementsToReverse.iterator ().next ());
                    } else {
                        config.setReverseRoot (getFirstRootPackage());
                    }
        
                    List<String> extensions = new ArrayList<>();
        
                    String modulePath = this.module.getModuleContext().getConfiguration().getModuleResourcesPath().toAbsolutePath().toString();
                    ImageManager.setModulePath(modulePath);
        
                    IClasspathModel classpathModel = createClasspathModel();
                    IExternalJarsModel externalJarsClasspathModel = new ExternalJarsClasspathModel();
        
                    if (reverseType == ReverseType.SOURCE) {
                        // Source reverse with wizard
                        extensions.add(".java");
        
                        IFileChooserModel fileChooserModel = new JavaFileChooserModel(this.module.getModuleContext().getProjectStructure().getPath().toFile(), extensions, config);
        
                        JavaReverseWizardView rw = new JavaReverseWizardView(Display.getDefault().getActiveShell(),
                                fileChooserModel,
                                classpathModel,
                                externalJarsClasspathModel,
                                false);
                        int ret = rw.open();
                        if (ret == 0) {
                            config.setReverseMode(fileChooserModel.getGranularity());
        
                            // Set up classpath
                            config.getClasspath().addAll (classpathModel.getClasspath());
                            config.getClasspath().addAll (externalJarsClasspathModel.getClasspath());
                            config.getClasspath().addAll (getCompilationPaths ());
        
                            // Set up source path for namespace lookup
                            config.getSourcepath().add(fileChooserModel.getInitialDirectory());
                            config.getSourcepath().addAll(fileChooserModel.getReverseRoots());
        
                            config.setFilesToReverse(new Hashtable<String, ElementStatus>());
                            for (File f : fileChooserModel.getFilesToImport()) {
                                if (f.isDirectory()) {
                                    config.getFilesToReverse().put (f.getAbsolutePath(), new ElementStatus(f.getAbsolutePath(), ElementType.DIRECTORY, ReverseStatus.REVERSE));
                                } else {
                                    config.getFilesToReverse().put (f.getAbsolutePath(), new ElementStatus(f.getAbsolutePath(), ElementType.JAVA_FILE, ReverseStatus.REVERSE));
                                }
                            }
        
                            if (processRun (config)) {
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Commit start at " + LocalDateTime.now());
                                transaction.commit();
                                JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Commit end at " + LocalDateTime.now());
                            }
                        }
                    } else {
                        // Binary reverse from wizard
        
                        // Set up classpath
                        config.getClasspath().addAll (getClassPath());
        
                        extensions.add(".jar");
        
                        IFileChooserModel fileChooserModel = new JavaFileChooserModel(this.module.getModuleContext().getProjectStructure().getPath().toFile(), extensions, config);
        
                        JavaReverseWizardView rw = new JavaReverseWizardView(Display.getDefault().getActiveShell(),
                                fileChooserModel,
                                classpathModel,
                                externalJarsClasspathModel,
                                true);
                        int ret = rw.open();
                        if (ret == 0) {
                            // Do reverse
                            config.setReverseMode(fileChooserModel.getGranularity());
        
                            config.setModel (fileChooserModel.getAssemblyContentModel());
                            config.setFilteredElements (fileChooserModel.getResult());
        
                            processRun (config);
        
                            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Commit start at " + LocalDateTime.now());
                            transaction.commit();
                            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Commit end at " + LocalDateTime.now());
                        }
                    }
                } catch (Exception e) {
                    JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
                }
            } else {
                // Real round trip mode (no wizard)
                if (!this.elementsToReverse.isEmpty ()) {
                    Hashtable<String, ElementStatus> filesToReverse = new Hashtable<> ();
                    List<File> classpath = getClassPath ();
                    List<File> sourcepath = new ArrayList<>();
        
                    for (Project project : this.module.getModuleContext().getModelingSession().findByClass(Project.class)) {
                        for (Package model : project.getModel()) {
                            File applicationPath = JavaDesignerUtils.getGenerationPath (model, this.module);
                            sourcepath.add(applicationPath);
                        }
                    }
        
                    // Add all components
                    for (Component javaComponent : JavaDesignerUtils.getJavaComponents (this.module.getModuleContext().getModelingSession())) {
                        File path = JavaDesignerUtils.getGenerationPath (javaComponent, this.module);
                        if (path.exists() && !isInFileList (sourcepath, path)) {
                            sourcepath.add (path);
                        }
                    }
        
                    // Build the elements to reverse
                    try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.Reverse"))) {
                        Set<NameSpace> reverseRoot = new HashSet<> ();
        
                        for (NameSpace element : this.elementsToReverse) {
                            // Compute the reverse root for this element, can be the root package, a "simple" package or a component
                            ModelTree namespaceRoot;
                            if (element.getOwner() == null  || JavaDesignerUtils.isAJavaComponent(element) || JavaDesignerUtils.isAModule (element)) {
                                // Element is already a root
                                namespaceRoot = element;
                            } else {
                                // Look for a root through composition ownership
                                namespaceRoot = element.getOwner();
                                while (namespaceRoot != null &&
                                        namespaceRoot.getOwner() != null &&
                                        JavaDesignerUtils.isJavaElement (namespaceRoot) &&
                                        !JavaDesignerUtils.isAJavaComponent (namespaceRoot) &&
                                        !JavaDesignerUtils.isAModule (namespaceRoot)) {
                                    namespaceRoot = namespaceRoot.getOwner ();
                                }
                            }
        
                            if (namespaceRoot != null) {
                                reverseRoot.add ((NameSpace) namespaceRoot);
                            }
        
                            file = JavaDesignerUtils.getFilename (element, this.module);
                            ElementStatus eStatus = filesToReverse.get (file.getAbsolutePath ());
                            if (eStatus == null) {
                                eStatus = new ElementStatus (file.getAbsolutePath (), getTypePath (file), ElementStatus.ReverseStatus.REVERSE);
                                filesToReverse.put (file.getAbsolutePath (), eStatus);
                            }
        
                            JavaDesignerUtils.updateDate (this.module.getModuleContext().getModelingSession(), element, Calendar.getInstance ().getTimeInMillis ());
                        }
                        ReverseConfig config = new ReverseConfig (filesToReverse,
                                sourcepath,
                                classpath,
                                reverseType,
                                containerFile,
                                outputFile);
        
                        config.setReport (this.report);
                        config.setStrategyConfiguration (new ReverseStrategyConfiguration (this.module.getModuleContext().getConfiguration ()));
        
                        // Store the reverse root
                        if (reverseRoot.size () == 1) {
                            config.setReverseRoot (reverseRoot.iterator ().next ());
                        } else {
                            config.setReverseRoot (getFirstRootPackage());
                        }
        
                        final RTReverseProgressBar progressBar = new RTReverseProgressBar (this.module, config);
        
                        class JavaRunnable
                        implements Runnable
                        {
                            public Exception lastException = null;
        
                            @Override
                            public void run ()
                            {
                                try {
                                    ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
                                    dialog.open();
                                    dialog.run(true, true, progressBar);
                                } catch (Exception e) {
                                    this.lastException = e;
                                }
                            }
        
                        }
        
        
                        JavaRunnable r = new JavaRunnable ();
                        Display.getDefault ().syncExec (r);
        
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Commit start at " + LocalDateTime.now());
        
                        if (r.lastException != null) {
                            error = true;
                        }
        
                        if (!error) {
                            transaction.commit();
                            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Commit end at " + LocalDateTime.now());
                        } else {
                            transaction.rollback();
                            JavaDesignerModule.getInstance().getModuleContext().getLogService().info ("Transaction rollbacked at " + LocalDateTime.now());
                        }
                    }
                }
            }
        } catch (IOException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
        }
    }

    /**
     * Puts back the model change handler.
     */
    private void restoreModelhandler() {
        if (this.javaDesignerSession != null) {
            IModelingSession session = this.module.getModuleContext().getModelingSession();
            session.addModelHandler (this.javaDesignerSession.getModelChangeHandler());
        }
    }

    /**
     * Removes the model change handler for the reverse to avoid attribute visibility to be changed.
     */
    private void removeModelHandler() {
        if (this.javaDesignerSession != null) {
            IModelingSession session = this.module.getModuleContext().getModelingSession();
            session.removeModelHandler (this.javaDesignerSession.getModelChangeHandler());
        }
    }

    /**
     * This operation returns the class path for all deployed ramcs
     */
    private Vector<File> getRamcClasspath() {
        Vector<File> classpath = new Vector<> ();
        File compilationPath;
        File completeCompilationPath;
        
        // Retrieve all ramc artifact
        for (ModelTree modeltree : this.module.getModuleContext().getModelingSession().findByClass(Component.class)) {
            Component component = (Component) modeltree;
            if (component.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODEL_COMPONENT)) { //$NON-NLS-1$
                for (ModelTree obArtifact : component.getOwnedElement (Artifact.class)) {
                    Artifact artifact = (Artifact) obArtifact;
                    if (artifact.isStereotyped (IOtherProfileElements.MODULE_NAME, IOtherProfileElements.MODEL_COMPONENT_ARCHIVE) && ModelUtils.isLibrary(artifact)) { //$NON-NLS-1$
                        compilationPath = JavaDesignerUtils.getCompilationPath (artifact, this.module);
        
                        completeCompilationPath = new File (compilationPath +
                                File.separator +
                                ".." + File.separator + JavaDesignerUtils.getJavaName (artifact) + File.separator + "bin" + File.separator + JavaDesignerUtils.getJavaName (artifact) + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        if (completeCompilationPath.exists ()) {
                            classpath.add (completeCompilationPath);
                        } else {
                            completeCompilationPath = new File (compilationPath +
                                    File.separator +
                                    ".." + File.separator + JavaDesignerUtils.getJavaName (artifact) + File.separator + "lib" + File.separator + JavaDesignerUtils.getJavaName (artifact) + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            if (completeCompilationPath.exists ()) {
                                classpath.add (completeCompilationPath);
                            } else {
                                completeCompilationPath = new File (JavaDesignerUtils.getGenRoot (this.module), File.separator + "lib" + File.separator + JavaDesignerUtils.getJavaName (artifact) + ".jar"); //$NON-NLS-1$ //$NON-NLS-2$
                                if (completeCompilationPath.exists ()) {
                                    classpath.add (completeCompilationPath);
                                } else {
                                    // Ignore missing jars
                                }
                            }
                        }
                    }
                }
            }
        }
        return classpath;
    }

    private IClasspathModel createClasspathModel() throws IOException {
        IClasspathModel classpathModel = new JavaClasspathModel(new File (this.module.getModuleContext().getProjectStructure().getPath().toFile(), "lib"));
        
        List<File> currentClasspath = classpathModel.getClasspath();
        currentClasspath.addAll(getClassPath());
        return classpathModel;
    }

    private boolean processRun(final ReverseConfig config) {
        try {
            ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
            dialog.open();
            dialog.run(true, true, new RTReverseProgressBar (this.module, config));
            return true;
        } catch (InvocationTargetException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            return false;
        } catch (InterruptedException e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error(e);
            return false;
        }
    }

    private Package getFirstRootPackage() {
        for (MObject modelRoot : this.module.getModuleContext().getModelingSession().getModel().getModelRoots()) {
            if (modelRoot instanceof Project) {
                for (Package rootPackage : ((Project)modelRoot).getModel()) {
                    return rootPackage;
                }
            }
        }
        return null;
    }

}
