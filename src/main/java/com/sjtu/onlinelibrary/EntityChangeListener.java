package com.sjtu.onlinelibrary;

/**
 * Listens for registry based events.
 *
 */
public interface EntityChangeListener {

    void itemSaved(final Persistable item, boolean created);

    void itemDeleted(final Persistable item);

}
