package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedData {
    private Map<String, Object> sharedObjects;
    private List<String> notifications;
    private final ReentrantReadWriteLock lock;

    public SharedData() {
        sharedObjects = new ConcurrentHashMap<>();
        notifications = Collections.synchronizedList(new ArrayList<>());
        lock = new ReentrantReadWriteLock();
    }

    public void addSharedObject(String key, Object value) {
        lock.writeLock().lock();
        try {
            sharedObjects.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object getSharedObject(String key) {
        lock.readLock().lock();
        try {
            return sharedObjects.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

}
