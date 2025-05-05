package CollectionFramework;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> chmp = new ConcurrentHashMap<>();
        // Java 7 -> segment based locking -> 16 segments --> smaller hashmaps
        // Only the segment being written to or read from is locked.
        // Read: Do not require locking unless there is a write operation happening on the same segment.
        // Write: Lock

        // Java 8 --> No segmentation
        //        --> Compare and swap approach --> no locking except resizing or collision.
        // ThreadA last saw --> x = 45
        // Thread A work --> x to 50
        // If x is still 45, then change it to 50 else don't change and retry.

        // MAP --> Sorted and thread safe --> ConcurrentSkipListMap (Thread safe tree map)
        // SkipList -> Probabilistic data structure that allows for efficient search, insertion and deletion operations.
        // It is similar to a sorted linked list but with multiple layers that skip over portions of the list to provide faster access to elements.
    }
}
