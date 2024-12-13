package cache.factory;

import cache.Cache;
import cache.enums.EvictionType;
import cache.evictionPolicy.LFUEvictionPolicy;
import cache.evictionPolicy.LRUEvictionPolicy;

public class CacheFactory {

    public static <K, V> Cache<K, V> generateCache (EvictionType type, int capacity) {
        switch (type) {
            case LFU:
                return Cache.getInstance(new LRUEvictionPolicy<>(), capacity);
            case LRU:
            default:
                return Cache.getInstance(new LFUEvictionPolicy<>(), capacity);
        }
    }
}
