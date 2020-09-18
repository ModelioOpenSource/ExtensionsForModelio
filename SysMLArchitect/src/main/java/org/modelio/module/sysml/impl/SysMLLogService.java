package org.modelio.module.sysml.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.log.ILogService;

/**
 * Proxy for the Modelio {@link ILogService}, configuring the ModelingWizardMdac.
 */
@objid ("8879a912-9dc4-463a-a375-ebbf09857a74")
public class SysMLLogService {
    @objid ("35980154-9b17-4de6-8470-360fa34873e8")
    private ILogService logService;

    /**
     * Default constructor.
     * 
     * @param logService the Modelio log service.
     * @param module the current instance of {@link SysMLModule}.
     */
    @objid ("964c8572-3e63-48d7-a5c9-5da52239c293")
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
    @objid ("e4f6b78a-0e20-414e-beb7-562b54d1ef87")
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
    @objid ("4f454bf5-0443-45e5-9d91-42f7ae452981")
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
    @objid ("e6c32c7e-b836-4167-a21b-b0cb3809ffa4")
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
    @objid ("c55445ac-255b-431d-a41a-af5a49ece977")
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
    @objid ("1cabbb32-d481-41c1-8cfa-8145c6fcb22e")
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
    @objid ("80ef866a-fa62-47a0-a6f4-1661ce732c61")
    public void error(final Throwable e) {
        this.logService.error(e);
    }

}
