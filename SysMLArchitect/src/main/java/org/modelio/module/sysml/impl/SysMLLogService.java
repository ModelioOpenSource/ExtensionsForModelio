package org.modelio.module.sysml.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.log.ILogService;

/**
 * Proxy for the Modelio {@link ILogService}, configuring the ModelingWizardMdac.
 */

public class SysMLLogService {
    
    private ILogService logService;

    /**
     * Default constructor.
     * 
     * @param logService the Modelio log service.
     * @param module the current instance of {@link SysMLModule}.
     */
    
    public SysMLLogService(ILogService logService, SysMLModule module) {
        this.logService = logService;
    }

    /**
     * Output an information message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * 
     * @param msg a message to be displayed as a log.
     */
    
    public void info(final String msg) {
        this.logService.warning(msg);
    }

    /**
     * Output a warning message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * 
     * @param msg a message to be displayed as a log.
     */
    
    public void warning(final String msg) {
        this.logService.warning(msg);
    }

    /**
     * Output an error message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * 
     * @param msg a message to be displayed as a log.
     */
    
    public void error(final String msg) {
        this.logService.error(msg);
    }

    /**
     * Log the given exception with its stack trace as an information.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param t an exception to be displayed as a log.
     */
    
    public void info(final Throwable e) {
        this.logService.info( e);
    }

    /**
     * Log the given exception with its stack trace as a warning.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param t an exception to be displayed as a log.
     */
    
    public void warning(final Throwable e) {
        this.logService.warning(e);
    }

    /**
     * Log the given exception with its stack trace as an error.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param t an exception to be displayed as a log.
     */
    
    public void error(final Throwable e) {
        this.logService.error(e);
    }

}
