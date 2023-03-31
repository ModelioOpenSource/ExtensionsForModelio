package org.modelio.module.javadesigner.xmlreverse;

import org.modelio.vcore.smkernel.mapi.MObject;


public interface IReportWriter {
    
    void addWarning(final String message, final MObject element, final String description);

    
    void addError(final String message, final MObject element, final String description);

    
    void addInfo(final String message, final MObject element, final String description);

    
    boolean isEmpty();

    
    boolean hasErrors();

    
    boolean hasWarnings();

    
    boolean hasInfos();

    
    void addTrace(final String message);

}
