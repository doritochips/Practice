import java.util.HashMap;
import java.util.Map;

/* Least Recently Used Cache for integers */
/* Implemented Using double linked list and HashMap */
/**
 *  @Author: David Zhou
 *  @Date: 2019-06-07 */
public interface LRU {
    void put (int key, int value);
    int find(int key);
}


class LRUCache implements  LRU{

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    @Override
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).value = value;
            this.moveToFront(key);
        } else {
            // drop last if exits
            if(capacity == map.size()) dropLast();

            Node n = new Node(key, value);
            map.put(key, n);
            // put at the beginning
            n.next = head.next;
            n.prev = head;
            head.next.prev = n;
            head.next = n;
        }
    }

    @Override
    public int find(int key) {
        if(map.containsKey(key)){
            moveToFront(key);
            return map.get(key).value;
        }
        return -1;
    }

    private void moveToFront(final int key) {
        Node n = map.get(key);
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void dropLast(){
        Node last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;
        map.remove(last.key);
    }
}

class Test {
    public static final String template = "expecting: %d, received: %d";
    public static void main(String[] args) {
        LRU cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 3);

        assert cache.find(1) == 1;
        cache.put(3, 4);
        assert cache.find(2) == -1;
        cache.put(4, 5);
        assert cache.find(1) == -1;
        assert cache.find(3) == 4;
        assert cache.find(4) == 5;

        System.out.println("Success!");
    }
}
