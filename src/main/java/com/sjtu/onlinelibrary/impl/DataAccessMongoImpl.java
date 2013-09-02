package com.sjtu.onlinelibrary.impl;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import com.sjtu.onlinelibrary.DataAccessException;
import com.sjtu.onlinelibrary.EntityChangeListener;
import com.sjtu.onlinelibrary.MutableDataAccess;
import com.sjtu.onlinelibrary.Persistable;
import com.sjtu.onlinelibrary.util.LangUtil;
import com.sjtu.onlinelibrary.util.MongoConfig;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Mongo version of the registry
 */
public final class DataAccessMongoImpl implements MutableDataAccess {

    private final Datastore _ds;
    private final EntityChangeListenerManager _listeners = new EntityChangeListenerManager();

    public DataAccessMongoImpl(final Datastore ds) {
        _ds = ds;
    }

    public DataAccessMongoImpl(final MongoConfig mongoConfig) {
        this(mongoConfig.newMongoInstance(), mongoConfig.getDbName(), mongoConfig.getUserName(), mongoConfig.getPassword());
    }

    public DataAccessMongoImpl(final Mongo mongo, final String dbName, final String username, final String password) {
        final Morphia m = new Morphia();
        m.mapPackage(MutableDataAccess.class.getPackage().getName());
        _ds=m.createDatastore(mongo,dbName);
//        _ds = m.createDatastore(mongo, dbName, username, password == null ? null : password.toCharArray());
        _ds.ensureIndexes(); //creates all defined with @Indexed
        _ds.ensureCaps(); //creates all collections for @Entity(cap=@CappedAt(...))
    }


    @Override
    public <T extends Persistable> List<T> listAll(final Class<T> clazz) throws DataAccessException {
        try {
            return _ds.find(clazz).asList();
        } catch (Exception e) {
            throw new DataAccessException("Could not list " + clazz.getSimpleName() + " entities", e);
        }
    }

    @Override
    public <T extends Persistable> T findById(final Class<T> clazz, final String id) throws DataAccessException {
        try {
            return _ds.get(clazz, id);
        } catch (Exception e) {
            throw new DataAccessException("Could not lookup entity " + clazz.getSimpleName() + " with id " + id, e);
        }
    }

    @Override
    public <T extends Persistable> List<T> listByFilter(final Class<T> clazz, final Map<String, Object> conditionMap) throws DataAccessException {
        return listByFilter(clazz, conditionMap, "createdOn");
    }

    @Override
    public <T extends Persistable> List<T> listByFilter(Class<T> clazz, Map<String, Object> conditionMap, String orderFields) throws DataAccessException {
        try {
            Query<T> qry = _ds.createQuery(clazz);
            if (conditionMap != null) {
                for (final String key : conditionMap.keySet()) {
                    qry = qry.filter(key, conditionMap.get(key));
                }
            }
            if (!LangUtil.isNullOrEmpty(orderFields)) {
                qry = qry.order(orderFields);
            }
            return qry.asList();
        } catch (Exception e) {
            throw new DataAccessException("Could not lookup " + clazz.getName(), e);
        }
    }

    @Override
    public <T extends Persistable> List<T> paging(final Class<T> clazz, final int pageIndex, final int pageSize) throws DataAccessException {
        return paging(clazz, pageIndex, pageSize, null);
    }

    @Override
    public <T extends Persistable> List<T> paging(Class<T> clazz, int pageIndex, int pageSize, Map<String, Object> conditionMap) throws DataAccessException {
        return paging(clazz, pageIndex, pageSize, conditionMap, "createdOn");
    }

    @Override
    public <T extends Persistable> List<T> paging(Class<T> clazz, int pageIndex, int pageSize, Map<String, Object> conditionMap, String orderFields) throws DataAccessException {
        try {
            Query<T> qry = _ds.createQuery(clazz);
            if (conditionMap != null) {
                for (final String key : conditionMap.keySet()) {
                    qry = qry.filter(key, conditionMap.get(key));
                }
            }

            if (!LangUtil.isNullOrEmpty(orderFields)) {
                qry = qry.order(orderFields);
            }
            qry = qry.limit(pageSize).offset((pageIndex - 1) * pageSize);
            return qry.asList();
        } catch (Exception e) {
            throw new DataAccessException("Could not lookup " + clazz.getName(), e);
        }
    }

    @Override
    public <T extends Persistable> int count(final Class<T> clazz) {
        return (int) _ds.getCount(clazz);
    }

    @Override
    public <T extends Persistable> int count(Class<T> clazz, Map<String, Object> conditionMap) {
        if (conditionMap == null) return (int) _ds.getCount(clazz);
        Query<T> qry = _ds.createQuery(clazz);
        for (final String key : conditionMap.keySet()) {
            qry = qry.filter(key, conditionMap.get(key));
        }
        return (int) _ds.getCount(qry);
    }

    @Override
    public boolean exists(final Class type, final String id) throws DataAccessException {
        try {
            return _ds.find(type, "_id", id).countAll() != 0;
        } catch (Exception e) { // for some reason mongo and morphia chose to throw only runtime exceptions - uhg!
            throw new DataAccessException("Could determine existence of " + type.getName() + " with id " + id, e);
        }
    }

    @Override
    public void save(final Persistable p) throws DataAccessException {
        try {
            final boolean exists;
            if (LangUtil.isNullOrEmpty(p.getId())) {
                p.setId(UUID.randomUUID().toString());
                exists = false;
            } else {
                exists = exists(p.getClass(), p.getId());
            }
            p.validate(exists ? Persistable.ValidationOperation.UPDATE : Persistable.ValidationOperation.ADD, this);
            _ds.save(p);
            _listeners.fireItemSaved(p, !exists);
        } catch (DataAccessException re) {
            throw re;
        } catch (Exception e) { // for some reason mongo and morphia chose to throw only runtime exceptions - uhg!
            throw new DataAccessException("Failed to save entity " + p, e);
        }
    }

    @Override
    public void delete(final Class<? extends Persistable> type, final String id) throws DataAccessException {
        try {
            final Persistable oldObject = findById(type, id);
            if (oldObject != null) {
                oldObject.validate(Persistable.ValidationOperation.DELETE, this);
                _ds.delete(type, id);
                _listeners.fireItemDeleted(oldObject);
            }
        } catch (DataAccessException re) {
            throw re;
        } catch (Exception e) { // for some reason mongo and morphia chose to throw only runtime exceptions - uhg!
            throw new DataAccessException("Failed to delete entity " + type.getSimpleName() + " with id " + id, e);
        }
    }

    @Override
    public void addListener(EntityChangeListener listener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}

