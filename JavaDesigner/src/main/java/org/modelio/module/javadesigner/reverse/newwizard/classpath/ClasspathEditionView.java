package org.modelio.module.javadesigner.reverse.newwizard.classpath;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;

public class ClasspathEditionView extends ModelioDialog {
    public Button okButton;

    public Button cancel;

    public IExternalJarsModel classpathModel;

    public ClasspathEditionView(final Shell parentShell, final IExternalJarsModel classpathModel) {
        super(parentShell);
        this.classpathModel = classpathModel;
    }

    @Override
    protected void okPressed() {
        super.okPressed();
    }

    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        this.okButton = createButton(parent, IDialogConstants.OK_ID, Messages
                .getString("Gui.JavaReverseWizardView.OkButton"), true);
        
        this.cancel = createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
    }

    @Override
    public Control createContentArea(final Composite parent) {
        // Avoid box closing when pressing enter
        parent.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(TraverseEvent event) {
                if ((event.character == SWT.CR)
                        || (event.character == SWT.KEYPAD_CR)) {
                    event.doit = false;
                }
            }
        });
        
        Composite root_composite = new Composite(parent, SWT.NONE);
        root_composite.setLayout(new FillLayout(SWT.HORIZONTAL));
        root_composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        // Create external tab
        //new ExternalJarEditorComposite(root_composite, this.classpathModel);
        return root_composite;
    }

    @Override
    public void init() {
        Shell shell = getShell();
        shell.setMinimumSize(450, 450);
        
        shell.setText(Messages
                .getString("Gui.JavaReverseWizardView.WindowName"));
        setTitle(Messages.getString("Gui.JavaReverseWizardView.Title"));
        
        setMessage(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.Title"));
    }

}
