package com.sjtu.onlinelibrary;


import java.util.List;
import java.util.Map;

/**
 * Provides some higher level operations for finding and saving the registry entities.
 * <p/>
 * NOTE: To cut down on code bloat, ONLY the methods currently needed have been added. This means that
 * CRUD may not be complete for all entities, and should be added only as needed.
 */
public interface DataAccess {

    /**
     * Lists all of the objects with the given type.
     *
     * @param clazz The class of the objects to list.
     * @return a possibly empty Iterable of all objects of the specified type registered in the system
     * @throws DataAccessException thrown if the projects could not be looked up.
     */
    <T extends Persistable> List<T> listAll(Class<T> clazz) throws DataAccessException;

    /**
     * Finds and returns an instance of the given type by looking for it by id.
     *
     * @param clazz The type of object you want to look up.
     * @param id    The id for the object.
     * @return The object instance or null if one with the provided id could not be found.
     * @throws DataAccessException thrown if the object could not be looked up, usually due to a storage layer error.
     */
    <T extends Persistable> T findById(Class<T> clazz, final String id) throws DataAccessException;


    /**
     * Finds and returns an instance of the given type by looking for it by id.
     *
     * @param clazz        The type of object you want to look up.
     * @param conditionMap the condition map : the key is the condition tag like :'name' or 'age >='
     * @return a possibly empty Iterable of all objects of the specified type registered in the system
     * @throws DataAccessException thrown if the object could not be looked up, usually due to a storage layer error.
     */
    <T extends Persistable> List<T> listByFilter(final Class<T> clazz, final Map<String, Object> conditionMap) throws DataAccessException;

    <T extends Persistable> List<T> paging(final Class<T> clazz, final int pageIndex, final int pageSize) throws DataAccessException;

    <T extends Persistable> int count(final Class<T> clazz);

    /**
     * An efficient way to find whether an item exists already.
     *
     * @param type The type of object you want to look up.
     * @param id   The id for the object.
     * @return true if the object alreact exists, false otherwise.
     * @throws DataAccessException thrown if the object could not be looked up, usually due to a storage layer error.
     */
    boolean exists(final Class type, final String id) throws DataAccessException;


}
