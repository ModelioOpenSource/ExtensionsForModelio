package org.modelio.module.javadesigner.api;


public class CustomException extends Exception {
    private static final long serialVersionUID = -7541197429555898819L;

    /**
     * Default constructor.
     * @param message Describes the exception causes
     */
    public CustomException(final String message) {
        super (message);
    }

}
