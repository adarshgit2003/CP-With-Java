package CollectionFramework;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class IteratorHashmap {
    public static void main(String[] args) {
        Map<String, Integer> mp = new ConcurrentSkipListMap<>();
        mp.put("Apple", 10);
        Iterator<Map.Entry<String, Integer>> it = mp.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println("Hi");
            mp.put("Banana", 1);
            mp.put("Grapes", 2);
            mp.put("Mango", 3);
            Map.Entry<String, Integer> entry = it.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
//        for (int i=0; i<mp.size(); i++) {
//            mp.put("Banana",1);
//            mp.put("Grapes", 2);
//            System.out.println(mp.size());
//        }
        System.out.println(mp);
    }
}
