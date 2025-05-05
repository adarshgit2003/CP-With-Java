package CollectionFramework;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;
    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size()>capacity;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>(3);
        lruCache.put("Bob", 99);
        lruCache.put("Alice", 92);
        lruCache.put("Charlie", 94);
        lruCache.put("Duke", 98); // bob will get removed here
        System.out.println(lruCache);
        lruCache.get("Alice");
        lruCache.put("Einstein", 100); // Charlie will get removed here.
        System.out.println(lruCache);
    }
}
