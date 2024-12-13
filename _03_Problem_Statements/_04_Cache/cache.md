## Requirements
1. The cache should be of a fixed capacity
2. cache should have get(), put(), clear() methods
3. get(key) - user queries with key, user should get value for the specific key
4. put(key, value) - user queries with key, value, user should get value based on key if key exists, otherwise inserts new data in cache
5. While inserting a key, if cache is full, cache should be evicted
6. Eviction is based on multiple eviction policies such as LRU and LFU
7. If LRU, least recently used data should be evicted 
8. If LFU, least frequently used data should be evicted

## Details
1. Singleton Pattern: for Cache instance
2. Factory Pattern: for generate cache object based on eviction policy
3. Strategy Pattern: to switch between strategies at run time