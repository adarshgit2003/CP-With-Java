package CollectionFramework;

import java.util.HashMap;
import java.util.Hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
//        Hashtable<Integer, String> hashtable = new Hashtable<>();
        // Hashtable is synchronized
        // no null key or value
        // legacy class, replaced by ConcurrentHashMap
        // slower than HashMap
        // only linkedlist in case of collision
        // all methods are synchronized in hashtable. (including get) -> This is the reason for which concurrenthashmap had come.
//        hashtable.put(1, "Apple");
//        System.out.println(hashtable);

        // Hashmap is not thread safe.
        HashMap<Integer, String> hmp = new HashMap<>();
        Thread thread1 = new Thread(() -> {
            for (int i=0; i<1000; i++){
                hmp.put(i, "Thread1");
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i=1000; i<2000; i++){
                hmp.put(i, "Thread2");
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(hmp.size());

        // Hashtable is thread safe.
        Hashtable<Integer, String> hmp2 = new Hashtable<>();
        Thread thread3 = new Thread(() -> {
            for (int i=0; i<1000; i++){
                hmp2.put(i, "Thread1");
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i=1000; i<2000; i++){
                hmp2.put(i, "Thread2");
            }
        });
        thread3.start();
        thread4.start();
        try {
            thread3.join();
            thread4.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(hmp2.size());

    }
}
