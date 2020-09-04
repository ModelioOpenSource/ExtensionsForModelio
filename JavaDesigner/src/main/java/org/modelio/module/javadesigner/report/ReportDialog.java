package org.modelio.module.javadesigner.report;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.module.javadesigner.i18n.Messages;
import org.modelio.module.javadesigner.report.ReportModel.ElementMessage;
import org.modelio.ui.UIColor;

class ReportDialog extends ModelioDialog {
    private ReportModel model;

    private Image warningImage;

    private Image errorImage;

     Table table;

    private Image infoImage;

     Text descriptionText;

     INavigationService navigationService;

    public ReportDialog(final Shell parentShell, final INavigationService navigationService) {
        super (parentShell);
        setShellStyle (SWT.RESIZE | SWT.DIALOG_TRIM | getDefaultOrientation ());
        this.navigationService = navigationService;
    }

    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton (parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }

    @Override
    public Control createContentArea(final Composite parent) {
        this.warningImage = JFaceResources.getImage ("dialog_messasge_warning_image"); //$NON-NLS-1$
        this.errorImage = JFaceResources.getImage ("dialog_message_error_image"); //$NON-NLS-1$
        this.infoImage = JFaceResources.getImage ("dialog_messasge_info_image"); //$NON-NLS-1$
        
        SashForm form = new SashForm(parent, SWT.VERTICAL);
        GridData data = new GridData (SWT.FILL, SWT.FILL, true, true);
        form.setLayoutData(data);
        
        this.table = new Table (form, SWT.MULTI | SWT.BORDER |
                SWT.FULL_SELECTION);
        //this.table.setLayoutData (data);
        this.table.setLinesVisible (true);
        
        TableColumn column = new TableColumn (this.table, SWT.NONE);
        column.setText ("Message");
        
        updateViewFromModel ();
        
        Point s = getInitialSize ();
        column.setWidth (s.x);
        
        this.table.addMouseListener (new MouseListener () {
            @Override
            public void mouseDoubleClick (MouseEvent e) {
                TableItem item = ReportDialog.this.table.getItem (new Point (e.x, e.y));
                if (item != null) {
                    if (item.getData () instanceof ElementMessage) {
                        ElementMessage theElement = (ElementMessage) item.getData ();
        
                        String desc = theElement.description;
                        ReportDialog.this.descriptionText.setText (desc);
        
                        // On a double clic, select the element
                        if (theElement.element != null && !theElement.element.isDeleted ()) {
                            ReportDialog.this.navigationService.fireNavigate (theElement.element);
                        }
                    }
                }
            }
        
            @Override
            public void mouseDown (MouseEvent e) {
                // Nothing to do
            }
        
            @Override
            public void mouseUp (MouseEvent e) {
                TableItem item = ReportDialog.this.table.getItem (new Point (e.x, e.y));
                if (item != null) {
                    if (item.getData () instanceof ElementMessage) {
                        ElementMessage theElement = (ElementMessage) item.getData ();
        
                        String desc = theElement.description;
                        ReportDialog.this.descriptionText.setText (desc != null ? desc : "");
        
                        // On a CTRL + clic, select the element
                        if ((e.stateMask & SWT.CTRL) != 0) {
                            if (theElement.element != null && !theElement.element.isDeleted ()) {
                                ReportDialog.this.navigationService.fireNavigate (theElement.element);
                            }
                        }
                    }
                }
            }
        });
        
        this.descriptionText = new Text (form, SWT.V_SCROLL | SWT.BORDER | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
        this.descriptionText.setBackground (UIColor.TEXT_READONLY_BG);
        
        // Add a listener that shows/hide the vertical scroll bar as needed.
        Listener scrollBarListener = new Listener (){
            @Override
            public void handleEvent(Event event) {
                Text t = (Text)event.widget;
                Rectangle r1 = t.getClientArea();
                Rectangle r2 = t.computeTrim(r1.x, r1.y, r1.width, r1.height);
                Point p = t.computeSize(r1.width,  SWT.DEFAULT,  true);
                
                t.getVerticalBar().setVisible(r2.height <= p.y);
                
                if (event.type == SWT.Modify){
                    t.getParent().layout(true);
                    t.showSelection();
                }
            }
        };
        this.descriptionText.addListener(SWT.Resize, scrollBarListener);
        this.descriptionText.addListener(SWT.Modify, scrollBarListener);
        
        form.setWeights(new int[]{70,30});
        return parent;
    }

    private void updateViewFromModel() {
        if (this.table != null) {
            this.table.removeAll ();
        
            if (this.model != null) {
                for (ElementMessage error : this.model.getErrors ()) {
                    TableItem item = new TableItem (this.table, SWT.NONE);
                    item.setImage (0, this.errorImage);
                    item.setText (0, error.message);
                    item.setData (error);
                }
        
                for (ElementMessage warning : this.model.getWarnings ()) {
                    TableItem item = new TableItem (this.table, SWT.NONE);
                    item.setImage (0, this.warningImage);
                    item.setText (0, warning.message);
                    item.setData (warning);
                }
        
                for (ElementMessage info : this.model.getInfos ()) {
                    TableItem item = new TableItem (this.table, SWT.NONE);
                    item.setImage (0, this.infoImage);
                    item.setText (0, info.message);
                    item.setData (info);
                }
            }
        
            this.table.getColumn (0).pack ();
        }
    }

    public void setModel(final ReportModel model) {
        this.model = model;
        updateViewFromModel ();
    }

    public boolean isDisposed() {
        Shell s = getShell ();
        return s == null || s.isDisposed ();
    }

    @Override
    public void init() {
        Shell shell = getShell ();
        
        // Put the messages in the banner area
        setLogoImage (null);
        shell.setText (Messages.getString ("Gui.Generation.ReportDialogTitle")); //$NON-NLS-1$
        setTitle (Messages.getString ("Gui.Generation.ReportDialogTitle")); //$NON-NLS-1$
        setMessage (Messages.getString ("Gui.Generation.ReportDialogMessage")); //$NON-NLS-1$
    }

}
