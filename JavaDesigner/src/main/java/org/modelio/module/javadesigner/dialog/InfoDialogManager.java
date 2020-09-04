package org.modelio.module.javadesigner.dialog;

import org.eclipse.swt.widgets.Display;
import org.modelio.module.javadesigner.i18n.Messages;

public class InfoDialogManager {
    private static InfoDialog antDialog = null;

    private static InfoDialog javadocDialog = null;


    /**
     * Returns the info dialog used to display ant related messages. The same dialog box is returned each time if it
     * isn't closed.
     */
    public static InfoDialog getExecuteAntTargetDialog() {
        if (InfoDialogManager.antDialog == null ||
                InfoDialogManager.antDialog.isDisposed ()) {
            InfoDialogManager.antDialog = new InfoDialog (Display.getDefault ().getActiveShell (), "", Messages.getString ("Gui.Command.ExecuteAntTarget.ConsoleTitle"));
        }
        return InfoDialogManager.antDialog;
    }

    /**
     * Returns the info dialog used to display Javadoc related messages. The same dialog box is returned each time if it
     * isn't closed.
     */
    public static InfoDialog getJavaDocDialog() {
        if (InfoDialogManager.javadocDialog == null ||
                InfoDialogManager.javadocDialog.isDisposed ()) {
            InfoDialogManager.javadocDialog = new InfoDialog (Display.getDefault ().getActiveShell (), "", Messages.getString ("Gui.Command.JavaDocGeneration.ConsoleTitle"));
        }
        return InfoDialogManager.javadocDialog;
    }

    /**
     * Returns the info dialog used to display ant related messages. A new dialog is returned each time.
     */
    public static InfoDialog getExecutionDialog() {
        return new InfoDialog (Display.getDefault ().getActiveShell (), "", Messages.getString ("Gui.Command.Execution.ConsoleTitle"));
    }

}
