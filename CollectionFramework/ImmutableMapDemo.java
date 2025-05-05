package CollectionFramework;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
public class ImmutableMapDemo {
    public static void main(String[] args) {

        Map<String, Integer> mp = new HashMap<>();
        mp.put("Ad", 1);
        mp.put("Er", 2);
        Map<String, Integer> ump = Collections.unmodifiableMap(mp);
        System.out.println(ump);
        // If I want to change ump, then it will throw exception.
        // But, I can modify initial map, and the change will also reflect in ump.
        mp.put("TR", 3);
        System.out.println(ump);

        Map<String, Integer> mp1 = Map.of("adarsh", 1, "shubham", 2);
        // Returns an unmodifiable map. Introduced since Java 9, and is only limited to 10 entries.
        // If try to modify this, then it will throw an exception.

        Map<String, Integer> map = Map.ofEntries(Map.entry("Adarsh", 1));
        // in this you can add any number of entries. This is also immutable.
        System.out.println(map);
//        map.put("Kevin", 2); // throw an error

        // to modify, you can do like this.
        Map<String, Integer> mp2 = new HashMap<>(Map.ofEntries(Map.entry("Adarsh", 1)));
        mp2.put("Kevin", 1);
        System.out.println(mp2);
    }
}
