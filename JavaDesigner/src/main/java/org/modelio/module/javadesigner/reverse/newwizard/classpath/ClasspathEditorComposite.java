package org.modelio.module.javadesigner.reverse.newwizard.classpath;

import java.io.File;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.modelio.module.javadesigner.reverse.newwizard.ListContentProvider;
import org.modelio.module.javadesigner.reverse.newwizard.api.IClasspathModel;

public class ClasspathEditorComposite extends Composite {
    protected CheckboxTreeViewer treeViewer;

    protected IClasspathModel model;

    public ClasspathEditorComposite(final Composite parent, final IClasspathModel model) {
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
        
        final Tree tree = new Tree(modelElementsIncludedGroup, SWT.SINGLE | SWT.BORDER | SWT.CHECK);
        this.treeViewer = new CheckboxTreeViewer(tree);
        this.treeViewer.setContentProvider(new ListContentProvider());
        this.treeViewer.setLabelProvider(new ClasspathLabelProvider());
        
        final FormData fd_tree = new FormData();
        fd_tree.top = new FormAttachment(0, 5);
        fd_tree.right = new FormAttachment(100, -5);
        fd_tree.left = new FormAttachment(0, 5);
        fd_tree.bottom = new FormAttachment(100, -5);
        tree.setLayoutData(fd_tree);
        
        getDataFromModel();
    }

    public void getDataFromModel() {
        this.treeViewer.setInput(this.model.getClasspath());
        this.treeViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                Object element = event.getElement();
                
                final boolean isChecked = ClasspathEditorComposite.this.treeViewer.getChecked(element);
        
                if (isChecked) {
                    ClasspathEditorComposite.this.model.getClasspath().add((File) element);
                } else {
                    ClasspathEditorComposite.this.model.getClasspath().remove(element);
                }
            }
        });
        
        this.treeViewer.setCheckedElements(this.model.getClasspath().toArray());
    }

}
