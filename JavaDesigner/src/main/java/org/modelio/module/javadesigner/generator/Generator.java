package org.modelio.module.javadesigner.generator;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.modelio.api.modelio.editor.IMDATextEditor;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.api.module.IModule;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.javadesigner.api.ISessionWithHandler;
import org.modelio.module.javadesigner.editor.EditorManager;
import org.modelio.module.javadesigner.file.FileModificationAnalyzer;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.report.ReportModel;
import org.modelio.module.javadesigner.reverse.Reversor;
import org.modelio.module.javadesigner.reverse.ui.ReverseException;
import org.modelio.module.javadesigner.utils.JavaDesignerUtils;

public class Generator {
    private IModule module = null;

    private Collection<NameSpace> elementsToGenerate;

    public Generator(final Collection<NameSpace> elements, final IModule module) {
        this.module = module;
        this.elementsToGenerate = elements;
    }

    public void generate(final ReportModel report) {
        boolean cancel = false;
        
        // Handle dirty editors on elements to be generated
        for (IMDATextEditor editor : EditorManager.getInstance ().getEditors ()) {
            if (editor.isDirty () && this.elementsToGenerate.contains (editor.getElement ())) {
                // Ask the user what to do
                MessageDialog dialog = new MessageDialog (Display.getCurrent ().getActiveShell (), Messages.getString ("Info.SaveFileBeforeGeneration.Title"), null, Messages.getString ("Info.SaveFileBeforeGeneration.Label", editor.getFile ().getName ()), MessageDialog.QUESTION, new String[] { //$NON-NLS-1$ //$NON-NLS-2$
                        IDialogConstants.YES_LABEL,
                        IDialogConstants.NO_LABEL,
                        Messages.getString ("Gui.CancelButton") }, 0); //$NON-NLS-1$
                int res = dialog.open ();
                if (res == Window.OK) {
                    // Yes button => save the editor
                    editor.save ();
                } else if (res == 1) {
                    // No button => discard changes and reload the editor
                    editor.reload();
                } else if (res == 2) {
                    // Cancel button => cancel generation without modifying the editor
                    cancel = true;
                }
            }
        }
        
        if (!cancel) {
            // Open transaction
            IModelingSession session = this.module.getModuleContext().getModelingSession ();
        
            try (ITransaction transaction = session.createTransaction (Messages.getString ("Info.Session.Generate"))) {
                // Updating model
                Reversor reversor = new Reversor (this.module, report);
                FileModificationAnalyzer fileAnalyzer = new FileModificationAnalyzer(this.module);
        
                // Remove all packages and java components from the element list to avoid reversing classes after choosing "keep" in the
                // retrieve behavior box
                Set<NameSpace> classesToGenerate = new HashSet<> ();
                for (NameSpace element : this.elementsToGenerate) {
                    if ((!(element instanceof Package) || JavaDesignerUtils.isPackageAnnotated ((Package) element))
                            && !JavaDesignerUtils.isAJavaComponent(element)) {
                        classesToGenerate.add (element);
                    }
                }
                this.elementsToGenerate = classesToGenerate;
        
                try {
                    // Analyze generated files
                    fileAnalyzer.analyzeFiles(this.elementsToGenerate);
        
                    // Update some files if necessary
                    if (!fileAnalyzer.getOutdatedElements().isEmpty()) {
                        reversor.update (fileAnalyzer.getOutdatedElements(), EditorManager.getInstance ());
                    }
                    
                    if (report.getErrors ().isEmpty ()) {
                        ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
                        dialog.open();
                        dialog.run(true, true, new GenerateProgressBar (this.module, this.elementsToGenerate, report));
                    } else {
                        cancel = true;
                        report.addError (Messages.getString ("Error.GenerationCanceled"), null, Messages.getString ("Error.GenerationCanceled.InitialReverse")); //$NON-NLS-1$  //$NON-NLS-2$
                    }
                } catch (InvocationTargetException e) {
                    cancel = true;
                } catch (InterruptedException e) {
                    cancel = true;
                } catch (ReverseException e) {
                    cancel = true;
                    // TODO add message
                }
        
                // Refresh the editor not dirty and regenerated
                EditorManager.getInstance ().refresh (this.elementsToGenerate);
        
                if (cancel) {
                    transaction.rollback();
                } else {
                    // Remove the model handler to deactivate java automation during
                    // the commit
                    IModuleLifeCycleHandler javaDesignerSession = this.module.getLifeCycleHandler();
                    IModelChangeHandler handler = null;
                    if (javaDesignerSession != null && javaDesignerSession instanceof ISessionWithHandler) {
                        handler = ((ISessionWithHandler) javaDesignerSession).getModelChangeHandler ();
                    }
        
                    if (handler != null) {
                        session.removeModelHandler (handler);
                    }
        
                    transaction.commit();
        
                    // Put back the model handler
                    if (handler != null) {
                        session.addModelHandler (handler);
                    }
                }
            }
        }
    }

}
