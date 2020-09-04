package org.modelio.module.javadesigner.reverse.newwizard.wizard;

import java.util.ArrayList;
import java.util.List;
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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;
import org.modelio.module.javadesigner.reverse.newwizard.externaljars.ExternalJarEditorComposite;

public class ClassPathEditionWizardView extends ModelioDialog {
     List<String> titles = new ArrayList<>();

    public Button okButton;

    public Button cancel;

    public TabFolder tabFolder;

    public IExternalJarsModel externalJarsClasspathModel;

    public ClassPathEditionWizardView(final Shell parentShell, final IExternalJarsModel externalJarsClasspathModel) {
        super(parentShell);
        this.externalJarsClasspathModel = externalJarsClasspathModel;
    }

    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        this.okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        this.cancel = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
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
        
        this.tabFolder = new TabFolder(root_composite, SWT.NONE);
        
        // Create external tab
        TabItem externalTab = new TabItem(this.tabFolder, SWT.NONE);
        this.titles.add(Messages
                .getString("Gui.ClassPathEditionWizardView.ExternalJarsTab.Title"));
        externalTab.setText(Messages
                .getString("Gui.ClassPathEditionWizardView.ExternalJarsTab.Name"));
        externalTab.setControl(new ExternalJarEditorComposite(this.tabFolder,
                this.externalJarsClasspathModel));
        return root_composite;
    }

    @Override
    public void init() {
        Shell shell = getShell();
        shell.setMinimumSize(450, 450);
        
        shell.setText(Messages
                .getString("Gui.ClassPathEditionWizardView.WindowName"));
        setTitle(Messages.getString("Gui.ClassPathEditionWizardView.Title"));
        setMessage(this.titles.get(this.tabFolder.getSelectionIndex()));
    }

}
