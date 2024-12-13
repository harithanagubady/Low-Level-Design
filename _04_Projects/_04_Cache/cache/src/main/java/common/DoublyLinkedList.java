package common;

public class DoublyLinkedList<E> {

    DLLNode<E> dummyHead;
    DLLNode<E> dummyTail;
    private int size;

    public DoublyLinkedList() {
        this.dummyHead = new DLLNode<>(null);
        this.dummyTail = new DLLNode<>(null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        this.size = 0;
    }

    @Override
    public String toString() {
        DLLNode<E> ptr = dummyHead.next;
        StringBuilder s = new StringBuilder();
        s.append("Pairs: ");
        while (ptr != dummyTail) {
            s.append(ptr.toString()).append(",");
            ptr = ptr.next;
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public void remove (DLLNode<E> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        size--;
    }

    public void insert (DLLNode<E> node) {//insert at head
        if (node == null) {
            throw new IllegalStateException("Node is null");
        }
        dummyHead.next.prev = node;
        node.next = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
        size++;
    }

    public DLLNode<E> insert (E key) {//insert at head
        DLLNode<E> node = new DLLNode<>(key);
        insert(node);
        return node;
    }
    public DLLNode<E> removeLast() {
        if (size == 0) {
            return null;
        }
        DLLNode<E> lastNode = dummyTail.prev;
        remove(lastNode);
        return lastNode;
    }

    public int size() {
        return size;
    }

    public static class DLLNode<E> {

        public DLLNode<E> prev;
        public DLLNode<E> next;
        public E key;

        public DLLNode(E key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "[" +
                    this.key +
                    "]";
        }
    }
}
