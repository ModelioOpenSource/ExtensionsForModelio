package org.modelio.module.javadesigner.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

class InfoDialog {
    protected Shell shell;

    protected StyledText text;

    protected Image shellIcon;

    public InfoDialog(final Shell parent, final String label, final String title) {
        this.createContents (parent, label, title);
    }

    public void dispose() {
        if (this.shellIcon != null) {
            this.shellIcon.dispose ();
        }
        this.shell.dispose ();
    }

    /**
     * Create contents of the window
     */
    private void createContents(final Shell parent, final String label, final String title) {
        this.shell = new Shell (parent, SWT.TITLE | SWT.BORDER | SWT.CLOSE |
                                SWT.RESIZE );
        this.shell.setLayout (new FormLayout ());
        this.shell.setText (title);
        
        Composite buttonComposite = this.createButtons ();
        
        /* Anomalie #19: 
         * Description: Text component should be writable.
         */
        this.text = new StyledText (this.shell, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP);
        this.text.setBackground (Display.getCurrent ().getSystemColor (SWT.COLOR_WHITE));
        this.text.setText (label);
        final FormData fd_label = new FormData ();
        fd_label.bottom = new FormAttachment (buttonComposite, -10, SWT.TOP);
        fd_label.top = new FormAttachment (0, 10);
        fd_label.right = new FormAttachment (100, -5);
        fd_label.left = new FormAttachment (0, 5);
        this.text.setLayoutData (fd_label);
        
        try {
            this.shellIcon = new Image (null, this.getClass ().getResourceAsStream ("modelio.bmp"));
            this.shell.setImage (this.shellIcon);
        } catch (Exception e) {
            JavaDesignerModule.getInstance().getModuleContext().getLogService().error (e.getMessage ());
        }
        this.shell.pack ();
        this.shell.setSize (this.shell.getSize ().x + 30, this.shell.getSize ().y);
    }

    private Composite createButtons() {
        final Composite composite = new Composite (this.shell, SWT.NONE);
        final RowLayout rowLayout = new RowLayout ();
        rowLayout.justify = true;
        composite.setLayout (rowLayout);
        final FormData fd_composite_1 = new FormData ();
        fd_composite_1.left = new FormAttachment (0, 5);
        fd_composite_1.bottom = new FormAttachment (100, -5);
        fd_composite_1.right = new FormAttachment (100, -5);
        composite.setLayoutData (fd_composite_1);
        
        final Composite composite_2 = new Composite (composite, SWT.NONE);
        composite_2.setLayout (new FillLayout ());
        
        final Button okButton = new Button (composite_2, SWT.NONE);
        okButton.addSelectionListener (new SelectionAdapter () {
            @Override
            public void widgetSelected (
                    SelectionEvent arg0)
            {
                InfoDialog.this.dispose ();
            }
        });
        okButton.setText (Messages.getString ("Gui.CloseButton"));
        return composite;
    }

    public void open() {
        this.shell.setSize (600, 400);
        this.shell.layout ();
        centerOnMonitor ();
        this.shell.open ();
    }

    private void centerOnMonitor() {
        Rectangle bounds = this.shell.getMonitor ().getBounds ();
        Rectangle rect = this.shell.getBounds ();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        this.shell.setLocation (x, y);
    }

/* Anomalie #19: 
     * Description: getText returns reference to Text widget.
     *               THIS SHOULD BE CHECKED AGAINST OTHER CLASSES!
     */
    public StyledText getText() {
        return this.text;
    }

/* Anomalie #19: 
     * Description: getTextString returns the text reference from Text widget.     
     */
    public String getTextString() {
        return this.text.getText ();
    }

    public void setText(final String newText) {
        this.text.setText (newText);
    }

    public void addText(final String newText, final Color color) {
        final Display display = Display.getDefault();
        display.asyncExec(new Runnable() {
        
            @Override
            public void run() {
                if (!InfoDialog.this.shell.isVisible()) {
                    open();
                }
        
                if (color != null) {
                    StyleRange range = new StyleRange();
                    range.start = InfoDialog.this.text.getText().length();
                    range.length = newText.length();
                    range.foreground = color;
        
                    InfoDialog.this.text.append(newText);
                    InfoDialog.this.text.setStyleRange(range);
                } else {
                    InfoDialog.this.text.append(newText);
                }
                // Always show the last line when adding text
                InfoDialog.this.text.setTopIndex(InfoDialog.this.text.getLineCount() - 1);
            }
        
        });
    }

    public boolean isDisposed() {
        return (this.shell == null) || (this.shell.isDisposed ());
    }

}
