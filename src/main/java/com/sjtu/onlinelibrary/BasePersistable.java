package com.sjtu.onlinelibrary;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.PrePersist;

import java.util.Date;

/**
 * Base for Persistable classes in this package.
 *
 */
public abstract class BasePersistable implements Persistable {
    @Id
    private String _id;
    @Indexed
    private Date lastUpdatedOn;
    private Date createdOn;

    @PrePersist
    private void updateTimestamp() {
        lastUpdatedOn = new Date();
        if (createdOn == null) createdOn = lastUpdatedOn;
    }

    public String getId() {
        return _id;
    }

    public void setId(final String id) {
        _id = id;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setLastUpdatedOn(final Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public void validate(final ValidationOperation op, final DataAccess dataAccess) throws ValidationFailedException, DataAccessException {

    }
}

