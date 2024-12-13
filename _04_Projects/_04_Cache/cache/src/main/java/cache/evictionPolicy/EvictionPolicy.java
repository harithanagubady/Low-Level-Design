package cache.evictionPolicy;

public interface EvictionPolicy<K> {

    void accessKey(K key);
    K evictKey();

    void reset();
}
