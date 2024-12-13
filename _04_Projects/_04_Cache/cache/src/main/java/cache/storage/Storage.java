package cache.storage;

public interface Storage<K, V> {

    boolean exists(K key);
    V getValueByKey(K key);
    void deleteByKey(K key);
    void update(K key, V value);
}
