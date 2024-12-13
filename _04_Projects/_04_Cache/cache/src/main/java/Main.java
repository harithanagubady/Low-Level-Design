import cache.Cache;
import cache.enums.EvictionType;
import cache.evictionPolicy.LFUEvictionPolicy;
import cache.factory.CacheFactory;

public class Main {

    public static void main(String[] args) {
        Cache<Integer, Integer> cache = CacheFactory.generateCache(EvictionType.LRU, 5);
        cache.put(1, 2); //size 1
        cache.put(1, 3); //size 1 (1)
        cache.put(2, 3); //size 2 (2, 1)
        Integer val = cache.get(3); // returns null (2, 1)
        System.out.println(val);
        cache.put(3, 3); //size 3 (3, 2, 1)
        cache.put(2, 5); //size 3 (2, 3, 1)
        cache.put(4, 5); //size 4 (4, 2, 3, 1)
        val = cache.get(1); // returns 3 (1, 4, 2, 3)
        System.out.println(val);
        cache.put(5, 2); //size 5 (5, 1, 4, 2, 3)
        System.out.println("cache size: " + cache.size());
        cache.put(6, 3); //size 5 (6, 5, 1, 4, 2)
        val = cache.get(3); // returns null (6, 5, 1, 4, 2)
        System.out.println(val);

        cache.clear();
        System.out.println("cache size: " + cache.size());


        System.out.println("LFU");
        cache.setEvictionPolicy(new LFUEvictionPolicy<>());
        cache.put(1, 2); //size 1
        cache.put(1, 3); //size 1 (1)
        cache.put(2, 3); //size 2 (1, 2)
        val = cache.get(3); // returns null (1, 2)
        System.out.println(val);
        cache.put(3, 3); //size 3 (1, 3, 2)
        cache.put(2, 5); //size 3 (2, 1, 3)
        cache.put(4, 5); //size 4 (2, 1, 4, 3)
        val = cache.get(1); // returns 3 (1, 2, 4, 3)
        System.out.println(val);
        cache.put(5, 2); //size 5 (1, 2, 5, 4, 3)
        System.out.println("cache size: " + cache.size());
        cache.put(6, 3); //size 5 (1, 2, 6, 5, 4)
        val = cache.get(3); // returns null (1, 2, 6, 5, 4)
        System.out.println(val);
    }
}
