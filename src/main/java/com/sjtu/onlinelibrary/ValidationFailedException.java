package com.sjtu.onlinelibrary;

/**
 * Thrown when a validation fails. It will contain specifc message about how to fix the error.
 *
 */
public final class ValidationFailedException extends DataAccessException {

    public ValidationFailedException(final String message) {
        super(message);
    }


}

