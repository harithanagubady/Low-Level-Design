package cache.storage;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<K, V> implements Storage<K, V> {
    @Getter private final Map<K, V> cache;

    public HashMapStorage() {
        this.cache = new HashMap<>();
    }

    @Override
    public boolean exists(K key) {
        return cache.containsKey(key);
    }

    @Override
    public V getValueByKey(K key) {
        return cache.get(key);
    }

    @Override
    public void deleteByKey(K key) {
        cache.remove(key);
    }

    @Override
    public void update(K key, V value) {
        cache.put(key, value);
    }
}
