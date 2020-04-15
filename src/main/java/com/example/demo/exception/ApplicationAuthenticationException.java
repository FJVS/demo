package com.example.demo.exception;

public class ApplicationAuthenticationException extends Exception {

    /**
     * Serialization UID for {@link ApplicationAuthenticationException} class.
     */
    private static final long serialVersionUID = -1040999639243319707L;

    public ApplicationAuthenticationException(String message) {
        super(message);
    }

}
