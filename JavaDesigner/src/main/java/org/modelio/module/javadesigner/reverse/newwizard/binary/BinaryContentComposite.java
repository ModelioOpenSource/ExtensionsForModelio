package org.modelio.module.javadesigner.reverse.newwizard.binary;

import com.modelio.module.xmlreverse.model.JaxbModel;
import com.modelio.module.xmlreverse.model.JaxbReversedData;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.module.javadesigner.reverse.newwizard.api.IFileChooserModel;

public class BinaryContentComposite extends Composite {
    public IFileChooserModel model;

    public CheckboxTreeViewer treeViewer;

    public BinaryContentComposite(final Composite parent, final IFileChooserModel model) {
        super(parent, SWT.NONE);
        
        this.model = model;
        this.createContent();
    }

    public void createContent() {
        this.setLayout(new GridLayout(1, false));
        
        this.treeViewer = new CheckboxTreeViewer(this, SWT.SINGLE | SWT.BORDER | SWT.CHECK);
        this.treeViewer.setContentProvider(new BinaryContentProvider());
        this.treeViewer.setLabelProvider(new BinaryContentLabelProvider());
        this.treeViewer.setSorter(new BinaryTreeSorter());
        
        TreeviewerListener hook = new TreeviewerListener(this.treeViewer, this.model.getResult());
        this.treeViewer.addTreeListener(hook);
        this.treeViewer.addCheckStateListener(hook);
        
        this.treeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            JaxbReversedData assemblyContentModel = BinaryContentComposite.this.model.getAssemblyContentModel();
            if (assemblyContentModel != null) {
                JaxbModel mdl = assemblyContentModel.getModel();
                if (!mdl.equals(BinaryContentComposite.this.treeViewer.getInput())) {
                    BinaryContentComposite.this.treeViewer.setInput(mdl);
                }
            } else {
                BinaryContentComposite.this.treeViewer.setInput(null);
            }
        }
    }

}
