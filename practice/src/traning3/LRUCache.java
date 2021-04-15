package traning3;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    class Node {
        Integer key;
        Integer value;
        Node last;
        Node next;
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    int size;
    Map<Integer, Node> map = new HashMap();
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            remove(node);
            insertToTail(node);
            return node.value;
        }
        return -1;
    }

    private void insertToTail(Node node) {
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = tail;
            tail.last = node;
            tail = node;
        }
        size++;
    }

    private void remove(Node node) {
        if (node == head && node == tail) {
            head = null; tail = null;
            size--;
        } else if (node == head && node != tail) {
            Node lastNode = head.last;
            lastNode.next = null;
            head.last = null;
            if (tail == null) {
                tail = lastNode;
            }
            head = lastNode;
            size--;
        } else if (node != head && node == tail) {
            Node nextNode = tail.next;
            nextNode.last = null;
            tail.next = null;
            if (head == null) {
                head = nextNode;
            }
            tail = nextNode;
            size--;
        } else if (node != head && node != tail) {
            Node lastNode = node.last;
            Node nextNode = node.next;
            lastNode.next = nextNode;
            nextNode.last = lastNode;
            node.last = null;
            node.next = null;
            size--;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (capacity == size) {
                map.remove(head.key);
                remove(head);
            }
            node = new Node(key, value);
            map.put(key, node);
            insertToTail(node);
        } else {
            node.value = value;
            remove(node);
            insertToTail(node);
        }
    }

//    public static void main(String[] args) {
//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1, 1);
//        lruCache.put(2, 2);
//        System.out.println(lruCache.get(1));
//        lruCache.put(3, 3);
//        System.out.println(lruCache.get(2));
//        lruCache.put(4, 4);
//        System.out.println(lruCache.get(1));
//        System.out.println(lruCache.get(3));
//        System.out.println(lruCache.get(4));
//    }

}
