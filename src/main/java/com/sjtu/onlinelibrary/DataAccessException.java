package com.sjtu.onlinelibrary;

/**
 * Exceptions related to the registry.
 */
public class DataAccessException extends Exception {

    public DataAccessException(final String message) {
        super(message);
    }

    public DataAccessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

