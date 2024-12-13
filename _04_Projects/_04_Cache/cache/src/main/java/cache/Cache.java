package cache;

import cache.evictionPolicy.EvictionPolicy;
import cache.storage.HashMapStorage;
import cache.storage.Storage;

import java.util.Optional;

public class Cache<K, V> {

    private static Cache<?, ?> INSTANCE;
    private final int capacity;
    private int size;
    private EvictionPolicy<K> evictionPolicy;
    private Storage<K, V> storage;

    private Cache(EvictionPolicy<K> evictionPolicy, int capacity) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.storage = new HashMapStorage<>();
    }

    @SuppressWarnings({"unchecked"})
    public static <K, V> Cache<K, V> getInstance (EvictionPolicy<K> evictionPolicy, int capacity) {
        if (INSTANCE == null) {
            INSTANCE = new Cache<>(evictionPolicy, capacity);
        }
        return (Cache<K, V>) INSTANCE;
    }

    public void setEvictionPolicy(EvictionPolicy<K> evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    public V get(K key) {
        if (storage.exists(key)) {
            evictionPolicy.accessKey(key);
            return storage.getValueByKey(key);
        } else return null;
    }

    public void put(K key, V value) {

        if (size == capacity) {
            K evictedKey = evictionPolicy.evictKey();
            storage.deleteByKey(evictedKey);
            size--;
        }
        evictionPolicy.accessKey(key);
        storage.update(key, value);
        size++;
    }

    public void clear() {
        storage = new HashMapStorage<>();
        size = 0;
        evictionPolicy.reset();
    }

    public int size() {
        return this.size;
    }
}
