package org.modelio.module.javadesigner.ant;

import java.util.List;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.reverse.newwizard.ListContentProvider;

class AntTargetExplorer extends ModelioDialog {
     String result;

    private TreeViewer treeViewer;

    protected AntTargetExplorer(final Shell parentShell) {
        super (parentShell);
    }

    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        super.addDefaultButtons (parent);
    }

    @Override
    public Control createContentArea(final Composite parent) {
        this.treeViewer = new TreeViewer (parent, SWT.SINGLE | SWT.BORDER);
        this.treeViewer.getTree ().setLayoutData (new GridData (SWT.FILL, SWT.FILL, true, true));
        
        this.treeViewer.setContentProvider (new ListContentProvider ());
        this.treeViewer.setLabelProvider (new LabelProvider ());
        
        this.treeViewer.addSelectionChangedListener (new ISelectionChangedListener () {
            
            @Override
            public void selectionChanged (
                    SelectionChangedEvent evt)
            {
                StructuredSelection sel = ((StructuredSelection) evt.getSelection ());
                if (!sel.isEmpty ()) {
                    Object selectedElement = sel.getFirstElement ();
                    if (selectedElement instanceof String) {
                        AntTargetExplorer.this.result = (String) selectedElement;
                    } else {
                        AntTargetExplorer.this.result = null;
                    }
                }
            }
            
        });
        return parent;
    }

    @Override
    public void init() {
        Shell shell = this.getShell ();
        shell.setText (Messages.getString ("Gui.antSelector.shellTitle")); //$NON-NLS-1$
        setTitle (Messages.getString ("Gui.antSelector.title")); //$NON-NLS-1$
        setMessage (Messages.getString ("Gui.antSelector.message")); //$NON-NLS-1$
    }

    public static String getTarget(final Shell parent, final List<String> targets) throws InterruptedException {
        AntTargetExplorer l_Explorer = new AntTargetExplorer (parent);
        l_Explorer.create ();
        l_Explorer.treeViewer.setInput (targets);
        
        int ret = l_Explorer.open ();
        if (ret == 0) {
            return l_Explorer.result;
        } else {
            throw new InterruptedException();
        }
    }

    @Override
    protected Point getInitialSize() {
        Point initial = new Point(300, 350);
        return initial;
    }

}
