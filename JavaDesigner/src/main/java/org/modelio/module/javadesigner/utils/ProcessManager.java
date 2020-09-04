package org.modelio.module.javadesigner.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.modelio.module.javadesigner.dialog.JConsoleWithDialog;
import org.modelio.module.javadesigner.impl.JavaDesignerModule;

public class ProcessManager {
    protected JConsoleWithDialog console;

    public ProcessManager(final JConsoleWithDialog console) {
        this.console = console;
    }

    public int execute(final String initialCommand, final boolean wait) {
        Runtime runtime = Runtime.getRuntime();
        try {
            String command;
            // Avoids an error when executing a command under linux...
            if (!System.getProperty ("os.name").startsWith ("Windows")) {
                command = initialCommand.replace("\"", "");
            } else {
                command = initialCommand;
            }
        
            final Process process = runtime.exec(command);
        
            /* 
             * Anomalie #19: Forward characters from console dialog to running process.
             */
            Thread readOutputThread = new Thread () {
                @Override
                public void run() {
                    try (OutputStream os = process.getOutputStream()) {
                        int c = -1;
                        while ((c = ProcessManager.this.console.readInt()) != -1 ) {
                            os.write(c);
                            os.flush();
                        }
                        os.close();
                    } catch (Exception ioe) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(ioe);
                    }
                }
            };
            readOutputThread.start();
        
            // Console use process instance to terminate process on when dialog is closed.
            addProcessToClose(process, readOutputThread);
        
            /* 
             * Anomalie #19: END
             */
        
            new Thread () {
                @Override
                public void run() {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            ProcessManager.this.console.writeInfo(line + System.getProperty("line.separator"));
                        }
                        reader.close();
                    } catch (IOException ioe) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(ioe);
                    }
                    ProcessManager.this.console.writeInfo("\nExecution done\n\n");
                }
            }.start();
        
            new Thread () {
                @Override
                public void run() {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            // Traitement du flux d'erreur
                            ProcessManager.this.console.writeError(line + System.getProperty("line.separator"));
                        }
                        reader.close();
                    } catch (IOException ioe) {
                        JavaDesignerModule.getInstance().getModuleContext().getLogService().error(ioe);
                    }
                }
            }.start();
        
            if (wait) {
                process.waitFor();
                return process.exitValue();
            }
        
            // Asynchronous execution, assume everything is ok...
            return 0;
        } catch (IOException e) {
            ProcessManager.this.console.writeError(e.getMessage());
            return -1;
        } catch (InterruptedException e) {
            ProcessManager.this.console.writeError(e.getMessage());
            return -1;
        }
    }

    /**
     * Add a dispose listener to the console to destroy a Process and its output reader.
     * @param process the process to monitor.
     * @param readOutputThread the output reader thread.
     */
    public void addProcessToClose(final Process process, final Thread readOutputThread) {
        if (this.console != null) {
            /* 
             * Anomalie #19:
             * Description: Define dispose listener so that the process is terminated when the dialog closes.
             */
            this.console.addDisposeListener( new DisposeListener() {
                @Override
                public void widgetDisposed(DisposeEvent de) {                
                    if( process != null) {
                        process.destroy();
        
                        // Force all threads waiting on the lineQueue to exit
                        if (readOutputThread.isAlive()) {
                            readOutputThread.interrupt();
                        }
                    }
                }
            });
        }
    }

}
