package CollectionFramework;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

class Person implements Comparable<Person> {
    int age;
    String name;
    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return o.age - this.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
public class SortedMapDemo {
    public static void main(String[] args) {
        SortedMap<Integer, String> smp = new TreeMap<>(); // natural ordering based on keys. if want to apply custom sort, then apply on the basis of keys.
        smp.put(91, "Vivek");
        smp.put(99, "Shubham");
        smp.put(78, "Mohit");
        smp.put(77, "Vipul");
//        System.out.println(smp);
//        System.out.println(smp.firstKey());
//        System.out.println(smp.lastKey());
//        System.out.println(smp.headMap(91)); // exclude 91
//        System.out.println(smp.tailMap(91));
//        System.out.println(smp.subMap(78, 91));
        NavigableMap<Integer, String> navigableMap = new TreeMap<>();
        navigableMap.put(1, "One");
        navigableMap.put(5, "Five");
        navigableMap.put(3, "Three");
        System.out.println(navigableMap);
//        System.out.println(navigableMap.tailMap(1, false));
        System.out.println(navigableMap.tailMap(1));
        System.out.println(navigableMap.headMap(3));
//        System.out.println(navigableMap.headMap(3, true));
//        System.out.println(navigableMap.lowerKey(4)); // Returns the greatest key strictly less than the given key or null if no.
//        System.out.println(navigableMap.lowerEntry(4));
//        System.out.println(navigableMap.ceilingKey(3)); // Returns the least key greater than or equal to the given key or null if no.
//        System.out.println(navigableMap.ceilingEntry(3));
//        System.out.println(navigableMap.higherKey(1));
//        System.out.println(navigableMap.descendingMap());

//        SortedMap<Person, Integer> smp2 = new TreeMap<>();
//        Person p1 = new Person(23, "Adarsh");
//        Person p2 = new Person(24, "Manav");
//        Person p3 = new Person(25, "Rohit");
//        smp2.put(p1, 1);
//        smp2.put(p2, 2);
//        smp2.put(p3, 3);
//        System.out.println(smp2);
    }
}
