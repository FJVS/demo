package com.example.demo.exception;

public class NotFoundException extends Exception {

    /**
     * Exception to throw when data is not found in database
     *
     * @param message to be thrown
     */
    public NotFoundException(String message) {
        super(message);
    }
}
