package com.sjtu.onlinelibrary;

/**
 * The minimum needed to be persisted.
 *
 */
public interface Persistable {

    public enum ValidationOperation {
        ADD,
        UPDATE,
        DELETE
    }


    void setId(final String id);

    String getId();

    void validate(final ValidationOperation op, final DataAccess baseDataAccess) throws ValidationFailedException, DataAccessException;

}
