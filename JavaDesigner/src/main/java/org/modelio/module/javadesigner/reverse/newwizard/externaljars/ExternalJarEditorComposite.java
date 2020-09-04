package org.modelio.module.javadesigner.reverse.newwizard.externaljars;

import java.io.File;
import java.util.List;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.newwizard.ImageManager;
import org.modelio.module.javadesigner.reverse.newwizard.ListContentProvider;
import org.modelio.module.javadesigner.reverse.newwizard.api.IExternalJarsModel;

public class ExternalJarEditorComposite extends Composite implements Listener {
    private TreeViewer treeViewer;

    private ToolItem dropButton;

    private ToolItem deleteButton;

    private ToolItem downButton;

    private ToolItem upButton;

    private IExternalJarsModel model;

    public ExternalJarEditorComposite(final Composite parent, final IExternalJarsModel model) {
        super(parent, SWT.NONE);
        this.model = model;
        this.createContent();
    }

    public void createContent() {
        this.setLayout(new FormLayout());
        
        final Group modelElementsIncludedGroup = new Group(this, SWT.NONE);
        modelElementsIncludedGroup.setLayout(new FormLayout());
        final FormData fd_modelElementsIncludedGroup = new FormData();
        fd_modelElementsIncludedGroup.bottom = new FormAttachment(100, -5);
        fd_modelElementsIncludedGroup.right = new FormAttachment(100, -5);
        fd_modelElementsIncludedGroup.top = new FormAttachment(0, 5);
        fd_modelElementsIncludedGroup.left = new FormAttachment(0, 5);
        modelElementsIncludedGroup.setLayoutData(fd_modelElementsIncludedGroup);
        
        final Tree tree = new Tree(modelElementsIncludedGroup, SWT.BORDER|SWT.MULTI);
        this.treeViewer = new TreeViewer(tree);
        this.treeViewer.setContentProvider(new ListContentProvider());
        this.treeViewer.setLabelProvider(new ExternalJarsLabelProvider());
        
        final FormData fd_tree = new FormData();
        fd_tree.right = new FormAttachment(100, -5);
        fd_tree.left = new FormAttachment(0, 5);
        fd_tree.bottom = new FormAttachment(100, -5);
        tree.setLayoutData(fd_tree);
        
        ToolBar toolBar_1;
        toolBar_1 = new ToolBar(modelElementsIncludedGroup, SWT.NONE);
        fd_tree.top = new FormAttachment(toolBar_1, 5, SWT.DEFAULT);
        final FormData fd_toolBar_1 = new FormData();
        fd_toolBar_1.right = new FormAttachment(100, -5);
        fd_toolBar_1.top = new FormAttachment(0, 5);
        fd_toolBar_1.left = new FormAttachment(0, 5);
        toolBar_1.setLayoutData(fd_toolBar_1);
        
        this.dropButton = new ToolItem(toolBar_1, SWT.NONE);
        this.dropButton.setImage(ImageManager.getInstance().getIcon("jarfile")); //$NON-NLS-1$
        this.dropButton.setToolTipText(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.add"));
        
        this.deleteButton = new ToolItem(toolBar_1, SWT.NONE);
        this.deleteButton.setImage(ImageManager.getInstance().getIcon("delete")); //$NON-NLS-1$
        this.deleteButton.setToolTipText(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.delete"));
        
        this.upButton = new ToolItem(toolBar_1, SWT.PUSH);
        this.upButton.setImage(ImageManager.getInstance().getIcon("up")); //$NON-NLS-1$
        this.upButton.setToolTipText(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.up"));
        
        this.downButton = new ToolItem(toolBar_1, SWT.PUSH);
        this.downButton.setImage(ImageManager.getInstance().getIcon("down")); //$NON-NLS-1$
        this.downButton.setToolTipText(Messages.getString("Gui.JavaReverseWizardView.ExternalJarsTab.down"));
        
        getDataFromModel();
        addListeners();
    }

    @Override
    public void handleEvent(final Event event) {
        TreeSelection selection = (TreeSelection) this.treeViewer
                .getSelection();
        if (event.widget == this.dropButton) {
            FileDialog dialog = new FileDialog(this.getShell(), SWT.MULTI);
        
            dialog.setFilterExtensions(this.model.getValidExtensions().toArray(new String[0]));
            if (dialog.open() != null) {
                String path = dialog.getFilterPath();
                String[] names = dialog.getFileNames();
        
                for (int i = 0; i < names.length; i++) {
                    add(new File(path, names[i]));
                }
        
                this.treeViewer.refresh();
            }
        } else if (event.widget == this.deleteButton) {
            if (!selection.isEmpty()) {
                List<?> selectedElements = selection.toList();
                for (int i = 0; i < selectedElements.size(); i++) {
                    remove((File) selectedElements.get(i));
                }
                this.treeViewer.refresh();
            }
        } else if (event.widget == this.upButton) {
            if (!selection.isEmpty()) {
                List<?> selectedElements = selection.toList();
                for (int i = 0; i < selectedElements.size(); i++) {
                    if (!moveElementUp((File) selectedElements.get(i))) {
                        break;
                    }
                }
                this.treeViewer.refresh();
            }
        } else if (event.widget == this.downButton) {
            if (!selection.isEmpty()) {
                List<?> selectedElements = selection.toList();
                for (int i = selectedElements.size() - 1; i >= 0 ; i--) {
                    if (!moveElementDown((File) selectedElements.get(i))) {
                        break;
                    }
                }
                this.treeViewer.refresh();
            }
        } else if (event.widget == this.treeViewer.getTree()) {
            if (!selection.isEmpty()) {
                if (existsInProjectContent(new File(selection.getFirstElement().toString()))) {
                    this.deleteButton.setEnabled(true);
                    this.upButton.setEnabled(true);
                    this.downButton.setEnabled(true);
                } else {
                    this.deleteButton.setEnabled(false);
                    this.upButton.setEnabled(false);
                    this.downButton.setEnabled(false);
        
                }
            }
        }
    }

    private void addListeners() {
        this.dropButton.addListener(SWT.Selection, this);
        this.deleteButton.addListener(SWT.Selection, this);
        this.upButton.addListener(SWT.Selection, this);
        this.downButton.addListener(SWT.Selection, this);
        this.treeViewer.getTree().addListener(SWT.Selection, this);
    }

    public void getDataFromModel() {
        this.treeViewer.setInput(this.model.getClasspath());
    }

    private boolean moveElementDown(final File elt) {
        List<File> classpath = this.model.getClasspath();
        
        int index = classpath.indexOf(elt);
        
        if ((index > -1) && (index + 1 < classpath.size())) {
            classpath.set(index, classpath.get(index + 1));
            classpath.set(index + 1, elt);
            return true;
        }
        return false;
    }

    private boolean moveElementUp(final File elt) {
        List<File> classpath = this.model.getClasspath();
        
        int index = classpath.indexOf(elt);
        
        if (index > 0) {
            classpath.set(index, classpath.get(index - 1));
            classpath.set(index - 1, elt);
            return true;
        }
        return false;
    }

    private void remove(final File elt) {
        this.model.getClasspath().remove(elt);
    }

    private void add(final File elt) {
        List<File> classpath = this.model.getClasspath();
        if (!classpath.contains(elt)) {
            classpath.add(elt);
        }
    }

    public boolean existsInProjectContent(final File elt) {
        return this.model.getClasspath().contains(elt);
    }

}
