package com.sjtu.onlinelibrary;

/**
 * A data access that can be used to saved modified objects.
 *
 */
public interface MutableDataAccess extends DataAccess {

    /**
     * Saves the state of the given object. If the object is a new object (has no Id set),
     * it will be assigned a random UUID ID
     *
     * @param p the persistable object to save.
     * @throws DataAccessException thrown if the object could not be saved.
     */
    void save(final Persistable p) throws DataAccessException;

    /**
     * Deletes a single instance of the type of Persistable provided, given the id.
     *
     * @param type The type of the object to delete.
     * @param id   The id for the object.
     * @throws DataAccessException thrown if the object could not be deleted.
     */
    void delete(final Class<? extends Persistable> type, final String id) throws DataAccessException;

    /**
     * @param listener The instance that will listen for events related to changes in the registry.
     */
    void addListener(final EntityChangeListener listener);

}

