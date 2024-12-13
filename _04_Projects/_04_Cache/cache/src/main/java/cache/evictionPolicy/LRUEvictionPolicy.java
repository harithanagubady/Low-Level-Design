package cache.evictionPolicy;

import common.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private Map<K, DoublyLinkedList.DLLNode<K>> keyToNodeMap;
    private DoublyLinkedList<K> lruMap;

    public LRUEvictionPolicy() {
        reset();
    }

    @Override
    public void accessKey(K key) {
        if (keyToNodeMap.containsKey(key)) {
            lruMap.remove(keyToNodeMap.get(key));
        }
        keyToNodeMap.put(key, lruMap.insert(key));
    }

    @Override
    public K evictKey() {
        DoublyLinkedList.DLLNode<K> evictedNode = lruMap.removeLast();
        if (evictedNode != null) {
            keyToNodeMap.remove(evictedNode.key);
            return evictedNode.key;
        }
        return null;
    }

    @Override
    public void reset() {
        keyToNodeMap = new HashMap<>();
        lruMap = new DoublyLinkedList<>();
    }

}
