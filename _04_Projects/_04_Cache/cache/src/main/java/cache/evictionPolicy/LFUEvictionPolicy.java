package cache.evictionPolicy;

import lombok.Getter;
import common.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LFUEvictionPolicy<K> implements EvictionPolicy<K> {

    @Getter
    private int min;
    private Map<K, DoublyLinkedList.DLLNode<K>> keyToNodeMap; //<key, Node>
    private Map<Integer, DoublyLinkedList<K>> freqGroup; //<freq, list<Node>>
    private Map<K, Integer> frequencies; //<key, frequency>

    public LFUEvictionPolicy() {
        reset();
    }

    @Override
    public void accessKey(K key) {
        int frequency;
        DoublyLinkedList.DLLNode<K> node;
        if (keyToNodeMap.containsKey(key)) {
            frequency = frequencies.get(key);
            frequencies.remove(key);
            DoublyLinkedList<K> old = freqGroup.get(frequency);
            node = keyToNodeMap.get(key);
            //remove node
            old.remove(node);
            if (old.size() == 0 && min == frequency) {
                min++;
            }
            freqGroup.put(frequency, old);
            keyToNodeMap.remove(key);
        } else {
            frequency = 0;
            node = new DoublyLinkedList.DLLNode<>(key);
            min = 1;
        }
        //update frequency
        frequency++;
        DoublyLinkedList<K> _new = freqGroup.getOrDefault(frequency, new DoublyLinkedList<>());
        _new.insert(node);
        freqGroup.put(frequency, _new);
        frequencies.put(key, frequency);
        keyToNodeMap.put(key, node);
    }

    @Override
    public K evictKey() {
        DoublyLinkedList<K> curr = freqGroup.get(min);
        DoublyLinkedList.DLLNode<K> node = curr.removeLast();
        K remKey = node.key;
        freqGroup.put(min, curr);
        return remKey;
    }

    @Override
    public void reset() {
        this.min = 0;
        keyToNodeMap = new HashMap<>();
        freqGroup = new HashMap<>();
        frequencies = new HashMap<>();
    }
}
