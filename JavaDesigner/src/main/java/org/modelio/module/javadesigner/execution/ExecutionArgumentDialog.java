package org.modelio.module.javadesigner.execution;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.module.javadesigner.i18n.Messages;

public class ExecutionArgumentDialog extends ModelioDialog {
     Text argumentsText;

     Text vmArgumentsText;

     ExecutionArgumentModel model;

    protected ExecutionArgumentDialog(final Shell parentShell, final ExecutionArgumentModel model) {
        super (parentShell);
        this.model = model;
    }

    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        super.addDefaultButtons (parent);
    }

    @Override
    public Control createContentArea(final Composite parent) {
        Composite container = new Composite (parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));
        
        final Label argumentsLabel = new Label(container, SWT.NONE);
        argumentsLabel.setText(Messages.getString("Gui.execution.arguments")); //$NON-NLS-1$
        
        this.argumentsText = new Text(container, SWT.BORDER);
        final GridData gd_argumentsText = new GridData(SWT.FILL, SWT.FILL, true, false);
        this.argumentsText.setLayoutData(gd_argumentsText);
        
        final Label vmArgumentsLabel = new Label(container, SWT.NONE);
        vmArgumentsLabel.setText(Messages.getString("Gui.execution.vmArguments")); //$NON-NLS-1$
        
        this.vmArgumentsText = new Text(container, SWT.BORDER);
        final GridData gd_vmArgumentsText = new GridData(SWT.FILL, SWT.FILL, true, false);
        this.vmArgumentsText.setLayoutData(gd_vmArgumentsText);
        
        addListeners();
        return parent;
    }

    private void addListeners() {
        ModifyListener listener = new ModifyListener() {
            
            @Override
            public void modifyText(ModifyEvent evt) {
                if (evt.widget.equals(ExecutionArgumentDialog.this.argumentsText)) {
                    ExecutionArgumentDialog.this.model.setArguments(ExecutionArgumentDialog.this.argumentsText.getText());
                } else if (evt.widget.equals(ExecutionArgumentDialog.this.vmArgumentsText)) {
                    ExecutionArgumentDialog.this.model.setVmArguments(ExecutionArgumentDialog.this.vmArgumentsText.getText());
                }
            }
            
        };
        
        this.argumentsText.addModifyListener(listener);
        this.vmArgumentsText.addModifyListener(listener);
    }

    @Override
    public void init() {
        Shell shell = this.getShell ();
        shell.setText (Messages.getString ("Gui.execution.shellTitle")); //$NON-NLS-1$
        setTitle (Messages.getString ("Gui.execution.title")); //$NON-NLS-1$
        setMessage (Messages.getString ("Gui.execution.message")); //$NON-NLS-1$
        
        this.argumentsText.setText(this.model.getArguments());
        this.vmArgumentsText.setText(this.model.getVmArguments());
    }

    public static ExecutionArgumentModel getExecutionArguments(final Shell parent, final String initialArguments, final String initialVmArguments) throws InterruptedException {
        ExecutionArgumentModel model = new ExecutionArgumentModel(initialArguments, initialVmArguments);
        
        ExecutionArgumentDialog l_Explorer = new ExecutionArgumentDialog (parent, model);
        l_Explorer.create ();
        
        int ret = l_Explorer.open ();
        if (ret == 0) {
            return l_Explorer.model;
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
