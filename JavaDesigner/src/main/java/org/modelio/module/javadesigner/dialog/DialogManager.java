package org.modelio.module.javadesigner.dialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Class containing services to open simple dialog box
 */
public class DialogManager {
    @SuppressWarnings("unused")
    private DialogManager(final Display display, final String title, final String message) {
        // This constructor exists only for the sync exec.
    }

    private static Display getDisplay() {
        Display display = Display.getCurrent ();
        if (display == null) {
            display = Display.getDefault ();
        }
        return display;
    }

    /**
     * Display a Confirm dialog
     */
    public static boolean openConfirm(final String title, final String message) {
        Display display = getDisplay ();
        Shell shell = display.getActiveShell ();
        
        // TODO display.syncExec
        return MessageDialog.openConfirm (shell, title, message);
    }

    /**
     * Display an Error dialog
     */
    public static boolean openError(final String title, final String message) {
        final String messageToDisplay = message;
        final String titleToDisplay = title;
        final Display display = getDisplay ();
        
        display.syncExec (new Runnable () {
            @Override
            public void run ()
            {
                final Shell shell = display.getActiveShell ();
                MessageDialog.openError (shell, titleToDisplay, messageToDisplay);
            }
        });
        return true;
    }

    /**
     * Display an Information dialog
     */
    public static boolean openInformation(final String title, final String message) {
        final String messageToDisplay = message;
        final String titleToDisplay = title;
        final Display display = getDisplay ();
        display.syncExec (new Runnable () {
            @Override
            public void run ()
            {
                final Shell shell = display.getActiveShell ();
                MessageDialog.openInformation (shell, titleToDisplay, messageToDisplay);
            }
        });
        return true;
    }

    /**
     * Display a Question dialog
     */
    public static boolean openQuestion(final String title, final String message) {
        final String messageToDisplay = message;
        final String titleToDisplay = title;
        final Display display = getDisplay ();
        final Shell shell = display.getActiveShell ();
        
        // TODO display.syncExec
        return MessageDialog.openQuestion (shell, titleToDisplay, messageToDisplay);
    }

    /**
     * Display a Warning dialog
     */
    public static boolean openWarning(final String title, final String message) {
        final String messageToDisplay = message;
        final String titleToDisplay = title;
        final Display display = getDisplay ();
        
        display.syncExec (new Runnable () {
            @Override
            public void run ()
            {
                final Shell shell = display.getActiveShell ();
                MessageDialog.openWarning (shell, titleToDisplay, messageToDisplay);
            }
        });
        return true;
    }

}
