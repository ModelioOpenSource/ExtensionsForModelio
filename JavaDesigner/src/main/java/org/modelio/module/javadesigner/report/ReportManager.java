package org.modelio.module.javadesigner.report;

import org.eclipse.swt.widgets.Display;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;
import org.modelio.module.javadesigner.report.ReportModel.ElementMessage;

public class ReportManager {
    private static ReportDialog dialog;

    public static void showGenerationReport(final ReportModel report) {
        if (report == null || report.isEmpty ()) {
            if (ReportManager.dialog != null &&
                    !ReportManager.dialog.isDisposed ()) {
                ReportManager.dialog.close ();
            }
        } else {
            if (ReportManager.dialog == null ||
                    ReportManager.dialog.isDisposed ()) {
                // Get the current display
                Display display = Display.getCurrent ();
                if (display == null) {
                    display = Display.getDefault ();
                }
        
                ReportManager.dialog = new ReportDialog (display.getActiveShell (), JavaDesignerModule.getInstance().getModuleContext().getModelioServices().getNavigationService());
            }
        
            ReportManager.dialog.setModel (report);
            ReportManager.dialog.open ();
        }
    }

    public static ReportModel getNewReport() {
        return new ReportModel ();
    }

    public static void printGenerationReport(final ReportModel report) {
        if (report != null && !report.isEmpty ()) {
            for (ElementMessage errorMsg : report.getErrors ()) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().error(errorMsg.message);
            }
        
            for (ElementMessage warningMsg : report.getWarnings ()) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().info (warningMsg.message);
            }
        
            for (ElementMessage infoMsg : report.getInfos ()) {
                JavaDesignerModule.getInstance().getModuleContext().getLogService().info (infoMsg.message);
            }
        }
    }

}
