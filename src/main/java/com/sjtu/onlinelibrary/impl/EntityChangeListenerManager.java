package com.sjtu.onlinelibrary.impl;

import com.sjtu.onlinelibrary.EntityChangeListener;
import com.sjtu.onlinelibrary.Persistable;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to manage RegistryChangeListener, provide batch notification
 * to all listeners.
 *
 */
public final class EntityChangeListenerManager {
    private final Set<EntityChangeListener> _listeners = new HashSet<EntityChangeListener>();

    /**
     * Add listener to this manager for future registry change notifications.
     * @param listener The instance that will listen for events related to changes in the registry.
     */
    public void add(final EntityChangeListener listener) {
        _listeners.add(listener);
    }

    /**
     * Notify all listeners that a new registry item has been added or updated.
     * @param p the registry item that was just added or updated.
     * @param created "true" to indicate that the new item is created and added
     *                to registry, "false" for updating an existing item.
     */
    public void fireItemSaved(final Persistable p, final boolean created) {
        for (final EntityChangeListener l : _listeners) {
            l.itemSaved(p, created);
        }
    }

    /**
     * Notify all listeners that a registry item has been deleted.
     * @param p the registry item that was deleted.
     */
    public void fireItemDeleted(final Persistable p) {
        for (final EntityChangeListener l : _listeners) {
            l.itemDeleted(p);
        }
    }
}
