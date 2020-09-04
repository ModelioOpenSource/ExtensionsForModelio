package org.modelio.module.javadesigner.dialog.modelselector;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.javadesigner.i18n.Messages;

public class ModelExplorer extends ModelioDialog {
    private TreeViewer treeViewer;

     Artifact result;

    protected ModelExplorer(final Shell parentShell) {
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

        this.treeViewer.setContentProvider (new SetContentProvider ());
        this.treeViewer.setLabelProvider (new ModelTreeLabelProvider ());
        this.treeViewer.setComparator (new ViewerComparator() {
            @Override
            public int compare (
                                Viewer viewer,
                                Object e1,
                                Object e2)
            {
                if (e1 instanceof String) {
                    return -1;
                } else if (e2 instanceof String) {
                    return 1;
                } else if (e1 instanceof ModelElement &&
                        e2 instanceof ModelElement) {
                    return super.compare (viewer, ((ModelElement) e1).getName (), ((ModelElement) e2).getName ());
                }

                return super.compare (viewer, e1, e2);
            }
        });

        this.treeViewer.addSelectionChangedListener (new ISelectionChangedListener () {

            @Override
            public void selectionChanged (
                                          SelectionChangedEvent evt)
            {
                StructuredSelection sel = ((StructuredSelection) evt.getSelection ());
                if (!sel.isEmpty ()) {
                    Object selectedElement = sel.getFirstElement ();
                    if (selectedElement instanceof Artifact) {
                        ModelExplorer.this.result = (Artifact) selectedElement;
                    } else {
                        ModelExplorer.this.result = null;
                    }
                }
            }

        });
        return parent;
    }

    private void setInput(final Set<Artifact> targets) {
        String defaultTarget = Messages.getString ("Gui.buildTargets.defaultTarget");
        Set<Object> inputs = new HashSet<> (targets);
        inputs.add (defaultTarget);

        this.treeViewer.setInput (inputs);
    }

    @Override
    public void init() {
        Shell shell = this.getShell ();
        shell.setText (Messages.getString ("Gui.buildTargets.shellTitle")); //$NON-NLS-1$
        setTitle (Messages.getString ("Gui.buildTargets.title")); //$NON-NLS-1$
        setMessage (Messages.getString ("Gui.buildTargets.message")); //$NON-NLS-1$
    }

    public static Artifact getBuildTarget(final Shell parent, final Set<Artifact> targets) throws InterruptedException {
        ModelExplorer l_Explorer = new ModelExplorer (parent);
        l_Explorer.create ();
        l_Explorer.setInput (targets);
        if (l_Explorer.open () == 0) {
            return l_Explorer.result;
        } else {
            throw new InterruptedException();
        }
    }

    @Override
    protected Point getInitialSize() {
        Point initial = super.getInitialSize ();
        initial.y = initial.y / 2;
        return initial;
    }

}
