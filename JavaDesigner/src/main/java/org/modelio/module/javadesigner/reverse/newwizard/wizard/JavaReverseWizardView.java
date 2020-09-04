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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.newwizard.api.IClasspathModel;
import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;
import org.modelio.module.javadesigner.reverse.newwizard.api.IFileChooserModel;
import org.modelio.module.javadesigner.reverse.newwizard.binary.BinaryContentComposite;
import org.modelio.module.javadesigner.reverse.newwizard.classpath.ClasspathEditorComposite;
import org.modelio.module.javadesigner.reverse.newwizard.externaljars.ExternalJarEditorComposite;
import org.modelio.module.javadesigner.reverse.newwizard.filechooser.FileChooserComposite;

public class JavaReverseWizardView extends ModelioDialog implements Listener {
     List<String> titles = new ArrayList<>();

    private boolean isBinary;

    public Button okButton;

    public Button cancel;

    public Button next;

    public Button previous;

    public TabFolder tabFolder;

    public IFileChooserModel fileChooserModel;

    public IClasspathModel classpathModel;

    public IExternalJarsModel externalJarsClasspathModel;

    public JavaReverseWizardView(final Shell parentShell, final IFileChooserModel fileChooserModel, final IClasspathModel classpathModel, final IExternalJarsModel externalJarsClasspathModel, final boolean isBinary) {
        super(parentShell);
        this.fileChooserModel = fileChooserModel;
        this.classpathModel = classpathModel;
        this.externalJarsClasspathModel = externalJarsClasspathModel;
        this.isBinary = isBinary;
    }

    @Override
    public void handleEvent(final Event event) {
        if (event.widget.equals(this.next)) {
            this.tabFolder.setSelection(this.tabFolder
                    .getSelectionIndex() + 1);
        } else if (event.widget.equals(this.previous)) {
            this.tabFolder.setSelection(this.tabFolder
                    .getSelectionIndex() - 1);
        }
        
        updateButtonStatus();
        setMessage(this.titles.get(this.tabFolder.getSelectionIndex()));
    }

    private void updateButtonStatus() {
        if (this.tabFolder.getSelectionIndex() == 0) {
            this.previous.setEnabled(false);
            this.next.setEnabled(true);
        } else if (this.tabFolder.getSelectionIndex() < this.tabFolder
                .getItemCount() - 1) {
            this.previous.setEnabled(true);
            this.next.setEnabled(true);
        } else {
            this.previous.setEnabled(true);
            this.next.setEnabled(false);
        }
        
        this.okButton.setEnabled(isValid());
    }

    private boolean isValid() {
        return this.tabFolder.getSelectionIndex() == this.tabFolder.getItemCount() - 1;
    }

    @Override
    protected void okPressed() {
        super.okPressed();
    }

    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        this.previous = createButton(parent, IDialogConstants.PROCEED_ID,
                Messages.getString("Gui.JavaReverseWizardView.PreviousButton"),
                false);
        this.previous.setEnabled(false); // At creation, the first page is
                                                // selected
        this.next = createButton(parent, IDialogConstants.NEXT_ID,
                Messages.getString("Gui.JavaReverseWizardView.NextButton"), true);
        this.okButton = createButton(parent, IDialogConstants.OK_ID,
                Messages.getString("Gui.JavaReverseWizardView.OkButton"), false);
        this.okButton.setEnabled(false); // At creation, the wizard isn't
                                                // complete
        this.cancel = createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
    }

    private void addListeners() {
        this.next.addListener(SWT.Selection, this);
        this.previous.addListener(SWT.Selection, this);
        this.okButton.addListener(OK, this);
        this.cancel.addListener(CANCEL, this);
        this.tabFolder.addListener(SWT.Selection, this);
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
        
        
        // Create file chooser tab
        TabItem filechooserTab = new TabItem(this.tabFolder, SWT.NONE);
        this.titles.add(Messages.getString("Gui.JavaReverseWizardView.FileChooserTab.Title", this.fileChooserModel.getValidExtensionsList()));
        filechooserTab.setText(Messages.getString("Gui.JavaReverseWizardView.FileChooserTab.Name"));
        filechooserTab.setControl(new FileChooserComposite(this.tabFolder, this.fileChooserModel));
        
        // Create classpath tab
        TabItem classpathTab = new TabItem(this.tabFolder, SWT.NONE);
        this.titles.add(Messages.getString("Gui.JavaReverseWizardView.ClasspathTab.Title"));
        classpathTab.setText(Messages.getString("Gui.JavaReverseWizardView.ClasspathTab.Name"));
        classpathTab.setControl(new ClasspathEditorComposite(this.tabFolder, this.classpathModel));
        
        // Create external tab
        TabItem externalTab = new TabItem(this.tabFolder, SWT.NONE);
        this.titles.add(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.Title"));
        externalTab.setText(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.Name"));
        externalTab.setControl(new ExternalJarEditorComposite(this.tabFolder, this.externalJarsClasspathModel));
        
        if (this.isBinary) {
            // Create class chooser tab
            TabItem assemblyContentTab = new TabItem(this.tabFolder, SWT.NONE);
            this.titles.add(Messages.getString("Gui.JavaReverseWizardView.BinaryContentTab.Title"));
            assemblyContentTab.setText(Messages.getString("Gui.JavaReverseWizardView.BinaryContentTab.Name"));
            assemblyContentTab.setControl(new BinaryContentComposite(this.tabFolder, this.fileChooserModel));
        }
        return root_composite;
    }

    @Override
    public void init() {
        Shell shell = getShell();
        shell.setMinimumSize(450, 450);
        
        shell
                .setText(Messages
                        .getString("Gui.JavaReverseWizardView.WindowName"));
        setTitle(Messages.getString("Gui.JavaReverseWizardView.Title"));
        setMessage(this.titles.get(this.tabFolder.getSelectionIndex()));
        
        addListeners();
    }

}
