package org.modelio.module.javadesigner.progress;

import java.util.Calendar;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.api.module.IModule;
import org.modelio.module.javadesigner.i18n.Messages;

public abstract class ProgressBar {
    protected static long startTime = 0L;

    protected static long startLastTask = 0L;

    private static int currentPosition = 0;

    private static int nbElements = 0;

    private static boolean displayRemainingTime = false;

    protected IModule module;

    protected static IProgressMonitor monitor;

    public ProgressBar(final IModule module, final int nb) {
        this.module = module;
        nbElements = nb;
    }

    protected void setMaximumValue(final int max) {
        nbElements = max;
    }

    protected void displayRemainingTime() {
        startLastTask = Calendar.getInstance ().getTimeInMillis ();
        nbElements = nbElements - currentPosition;
        currentPosition = 0;
        displayRemainingTime = true;
    }

    protected static String formatTime(final long timeInSeconds) {
        String res = ""; //$NON-NLS-1$
        long time = timeInSeconds;
        long hours = time / 3600L;
        
        time -= hours * 3600L;
        if (hours != 0L) {
            res = (new StringBuilder (String.valueOf (res))).append (hours).append (" h ").toString ();
        }
        long minutes = time / 60L;
        if (minutes != 0L) {
            res = (new StringBuilder (String.valueOf (res))).append (minutes).append (" m ").toString ();
        }
        time -= minutes * 60L;
        long seconds = time;
        if (seconds >= 0L) {
            res = (new StringBuilder (String.valueOf (res))).append (seconds).append (" s").toString ();
        } else {
            res = "0 s";
        }
        return res;
    }

    protected static long getElapsedTime() {
        return (Calendar.getInstance ().getTimeInMillis () - startTime) / 1000L;
    }

    protected static long getElapsedTimeForTask() {
        return (Calendar.getInstance ().getTimeInMillis () - startLastTask) / 1000L;
    }

    protected static long getRemainingTime(final long elapsedTime) {
        double averageTime = (double) elapsedTime / (double) currentPosition;
        long remainingTime = (long) (averageTime * (nbElements - currentPosition));
        return remainingTime;
    }

    /**
     * Code to update the progress bar (Time and +1)
     */
    public static boolean updateProgressBar(final String message) {
        currentPosition++;
        
        boolean ret = true;
        if (monitor != null) {
            String elapsed = (new StringBuilder (Messages.getString ("Info.ProgressBar.ElapsedTime"))).append (formatTime (getElapsedTime ())).toString (); //$NON-NLS-1$
            monitor.subTask (elapsed);
            if (!displayRemainingTime) {
                monitor.subTask (elapsed);
            } else {
                String estimated = (new StringBuilder (Messages.getString ("Info.ProgressBar.RemainingTime"))).append (formatTime (getRemainingTime (getElapsedTimeForTask ()))).toString (); //$NON-NLS-1$
                monitor.subTask (elapsed + "\n" + estimated); //$NON-NLS-1$
            }
            monitor.worked (1);
            ret = !monitor.isCanceled ();
        
            if (message != null) {
                monitor.setTaskName (message);
            }
        }
        return ret;
    }

    /**
     * Change the task name in the progress bar.
     * @param taskName The new name to display.
     * @return <code>true</code> when the progress monitor isn't canceled.
     */
    public static boolean setTaskName(final String taskName) {
        boolean ret = true;
        if (monitor != null) {
            monitor.setTaskName (taskName);
        
            ret = !monitor.isCanceled ();
        }
        return ret;
    }

    protected void init(final boolean withRemainingTime) {
        startTime = Calendar.getInstance ().getTimeInMillis ();
        startLastTask = startTime;
        currentPosition = 0;
        // TODO Check the test in all reverse (source and binary)
        displayRemainingTime = withRemainingTime;
    }

}
