package org.modelio.module.javadesigner.reverse.newwizard.filechooser;

import java.io.File;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.ReverseConfig.GeneralReverseMode;
import org.modelio.module.javadesigner.reverse.newwizard.ImageManager;
import org.modelio.module.javadesigner.reverse.newwizard.api.IFileChooserModel;
import org.modelio.ui.UIColor;

public class FileChooserComposite extends Composite implements Listener {
    public IFileChooserModel model;

    public CheckboxTreeViewer treeViewer;

    public Text addressText;

    public Button fileChooserButton;

    public Text previewText;

    public ComboViewer granularityCombo;

    public FileChooserComposite(final Composite parent, final IFileChooserModel model) {
        super(parent, SWT.NONE);
        
        this.model = model;
        this.createContent();
    }

    public void createContent() {
        this.setLayout(new GridLayout(2, false));
        
        this.addressText = new Text(this, SWT.BORDER);
        this.addressText.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
                true, false));
        this.addressText.setText(this.model.getInitialDirectory().getAbsolutePath());
        this.addressText.addListener(SWT.Modify, this);
        
        this.fileChooserButton = new Button(this, SWT.NONE);
        this.fileChooserButton.setImage(ImageManager.getInstance().getIcon("directory"));
        this.fileChooserButton.addListener(SWT.Selection, this);
        this.fileChooserButton.setLayoutData(new GridData(SWT.FILL,
                SWT.FILL, false, false));
        
        this.previewText = new Text(this, SWT.READ_ONLY);
        this.previewText.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
                true, false, 2, 1));
        this.previewText.setText(Messages.getString("Gui.JavaReverseWizardView.FileChooserTab.Preview", this.model.getInitialDirectory()));
        this.previewText.setForeground(UIColor.LABEL_TIP_FG);
        
        this.treeViewer = new CheckboxTreeViewer(this, SWT.SINGLE
                | SWT.BORDER | SWT.CHECK);
        this.treeViewer.setContentProvider(new FileContentProvider());
        this.treeViewer.setLabelProvider(new FileLabelProvider());
        this.treeViewer.setInput(this.model.getInitialDirectory());
        
        FileFilter[] filters = { new FileFilter(this.model.getValidExtensions()) };
        this.treeViewer.setFilters(filters);
        
        this.treeViewer.setSorter(new FileViewerSorter());
        
        TreeviewerListener hook = new TreeviewerListener(this.treeViewer, this.model.getFilesToImport());
        this.treeViewer.addTreeListener(hook);
        this.treeViewer.addCheckStateListener(hook);
        
        this.treeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        
        // Granularity
        Label granularityLabel = new Label(this, SWT.NONE);
        granularityLabel.setText(Messages.getString("Gui.JavaReverseWizardView.Granularity"));
        granularityLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
        
        this.granularityCombo = new ComboViewer(this, SWT.BORDER|SWT.READ_ONLY);
        this.granularityCombo.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
        this.granularityCombo.setContentProvider(new ArrayContentProvider());
        this.granularityCombo.setInput(GeneralReverseMode.values());
        this.granularityCombo.setSelection(new StructuredSelection(this.model.getGranularity()));
        this.granularityCombo.addSelectionChangedListener(new ISelectionChangedListener() {
        
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection sel = (IStructuredSelection) FileChooserComposite.this.granularityCombo.getSelection();
                FileChooserComposite.this.model.setGranularity((GeneralReverseMode) sel.getFirstElement());
            }
        });
    }

    @Override
    public void handleEvent(final Event event) {
        if (event.widget.equals(this.fileChooserButton)) {
            DirectoryDialog dialog = new DirectoryDialog(this.getShell());
            dialog.setFilterPath(this.model.getInitialDirectory().getAbsolutePath());
            String file = dialog.open();
            if (file != null) {
                this.addressText.setText(file);
            }
        } else if (event.widget.equals(this.addressText)) {
            Text textField = (Text) event.widget;
            String fileName = textField.getText();
            if (!fileName.endsWith("/")) {
                fileName += "/";
            }
            File newFile = new File(fileName);
            if (newFile.exists()) {
                this.model.setInitialDirectory(newFile);
                this.previewText.setText(Messages.getString("Gui.JavaReverseWizardView.FileChooserTab.Preview", fileName));
                this.previewText.redraw();
                this.treeViewer.setInput(newFile);
            }
        }
    }

}
