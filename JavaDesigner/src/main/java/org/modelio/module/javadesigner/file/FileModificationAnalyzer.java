package org.modelio.module.javadesigner.file;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.module.IModule;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.JavaDesignerParameters.RetrieveMode;
import org.modelio.module.javadesigner.api.JavaDesignerParameters;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.ui.ReverseException;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * This class is in charge of computing sets of outdated and dirty elements  from their corresponding source files.
 * <p>
 * An <b>outdated element</b> is a <i>modifiable<i> element which generated file has been modified since last generation,
 * and is marked for retrieve.
 * </p>
 * <p>
 * A <b>dirty element</b> is a <i>read only<i> element which generated file has been modified since last generation.
 * </p>
 */
public class FileModificationAnalyzer {
    private RetrieveMode lastRetrieveMode = RetrieveMode.Ask;

    private IModule module;

    private Set<NameSpace> outdatedElements;

    public FileModificationAnalyzer(final IModule module) {
        this.module = module;
        
        // Set the mode initial value, from the module parameters
        this.lastRetrieveMode = RetrieveMode.fromString(this.module.getModuleContext().getConfiguration ().getParameterValue (JavaDesignerParameters.RETRIEVEDEFAULTBEHAVIOUR));
    }

    /**
     * Analyze java files associated with the given elements to initialize <b>outdated</b> and <b>dirty</b> elements sets.
     */
    public void analyzeFiles(final Collection<NameSpace> elements) throws ReverseException {
        boolean isRoundTripMode = JavaDesignerUtils.isRoundtripMode (this.module);
        boolean confirmBox = true;
        LocalRetrieveMode retrieveBehavior = LocalRetrieveMode.RETRIEVE;
        switch (this.lastRetrieveMode) {
        case Ask :
            confirmBox = true;
            break;
        case Retrieve:
            confirmBox = false;
            retrieveBehavior = LocalRetrieveMode.RETRIEVE_ALL;
            break;
        case Keep:
            confirmBox = false;
            retrieveBehavior = LocalRetrieveMode.KEEP_ALL;
            break;
        }
        
        this.outdatedElements = new HashSet<> ();
        
        for (NameSpace element : elements) {
            if ((element instanceof Package && !JavaDesignerUtils.isPackageAnnotated ((Package) element))
                    || JavaDesignerUtils.isAJavaComponent(element)) {
                if (isRoundTripMode && this.lastRetrieveMode == RetrieveMode.Retrieve) {
                    if (retrieveBehavior != LocalRetrieveMode.KEEP_ALL) {
                        // Only modifiable elements can be added for retrieve...
                        this.outdatedElements.add (element);
                    }
                }
            } else {
                File currentFile = JavaDesignerUtils.getFilename (element, this.module);
                if (isGeneratedFileModified(currentFile, element)) {
                    MStatus status = element.getStatus ();
                    if (status.isModifiable ()) {
                        // Only modifiable elements can be added for retrieve...
                        if (retrieveBehavior != LocalRetrieveMode.KEEP_ALL) {
                            if (confirmBox) {
                                retrieveBehavior = getRetrieveBehavior (currentFile);
        
                                if ((retrieveBehavior == LocalRetrieveMode.RETRIEVE_ALL) ||
                                        (retrieveBehavior == LocalRetrieveMode.KEEP_ALL)) {
                                    // No box anymore
                                    confirmBox = false;
                                } else if (retrieveBehavior == LocalRetrieveMode.CANCEL) {
                                    // Set cancel to avoid continuing
                                    throw new ReverseException ("Reverse canceled");
                                }
                            }
        
                            if (retrieveBehavior == LocalRetrieveMode.RETRIEVE) {
                                this.outdatedElements.add (element);
                            } else if (retrieveBehavior == LocalRetrieveMode.RETRIEVE_ALL) {
                                this.outdatedElements.add (element);
                                this.lastRetrieveMode = RetrieveMode.Retrieve;
                            } else if (retrieveBehavior == LocalRetrieveMode.KEEP_ALL) {
                                this.lastRetrieveMode = RetrieveMode.Keep;
                            }
                        }
                    }
                }
            }
        }
    }

    public Set<NameSpace> getOutdatedElements() {
        return this.outdatedElements;
    }

    /**
     * Returns <code>true</code> if current file's modification date is more recent than the {@link JavaDesignerProperties#JAVALASTGENERATED} value.
     */
    private boolean isGeneratedFileModified(File currentFile, NameSpace element) {
        String lastGenerated = JavaDesignerUtils.getDate(element);
        long elementTime;
        if (lastGenerated.length () > 0) {
            elementTime = Long.parseLong (lastGenerated);
        } else {
            elementTime = 0L;
        }
        
        long fileTime = currentFile.lastModified ();
        return fileTime > elementTime;
    }

    /**
     * Ask if current file is supposed to be kept or retrieved.
     */
    private LocalRetrieveMode getRetrieveBehavior(final File currentFile) {
        ReverseDialogRunnable r = new ReverseDialogRunnable (currentFile.getAbsolutePath ());
        Display.getDefault ().syncExec (r);
        return r.getResult ();
    }

    private static class ReverseDialogRunnable implements Runnable {
        private int result = 0;

        private String path;

        public ReverseDialogRunnable(final String path) {
            this.path = path;
        }

        @Override
        public void run() {
            MessageDialog dialog = new MessageDialog (Display.getDefault ().getActiveShell (), Messages.getString ("Gui.AskForReverseBoxTitle"), null, Messages.getString ("Gui.AskForReverseBoxLabel", this.path), MessageDialog.QUESTION, new String[] { //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString ("Gui.RetrieveButton"), //$NON-NLS-1$
                    Messages.getString ("Gui.RetrieveAllButton"), //$NON-NLS-1$
                    Messages.getString ("Gui.KeepButton"), //$NON-NLS-1$
                    Messages.getString ("Gui.KeepAllButton"),
                    Messages.getString ("Gui.CancelButton")}, 0); //$NON-NLS-1$
            this.result = dialog.open ();
        }

        public LocalRetrieveMode getResult() {
            switch (this.result) {
            case 0:
                return LocalRetrieveMode.RETRIEVE;
            case 1:
                return LocalRetrieveMode.RETRIEVE_ALL;
            case 2:
                return LocalRetrieveMode.KEEP;
            case 3:
                return LocalRetrieveMode.KEEP_ALL;
            default:
                return LocalRetrieveMode.CANCEL;
            }
        }

    }

    private enum LocalRetrieveMode {
        RETRIEVE,
        RETRIEVE_ALL,
        KEEP,
        KEEP_ALL,
        CANCEL;
    }

}
