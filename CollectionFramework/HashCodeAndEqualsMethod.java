package CollectionFramework;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashCodeAndEqualsMethod {
    public static void main(String[] args) {
        Map<Person, String> mp = new HashMap<>();
        Person p1 = new Person("Adarsh", 1);
        Person p2 = new Person("Kumar", 2);
        Person p3 = new Person("Adarsh", 1);
        mp.put(p1, "Engineer"); // hashcode1 --> index1
        mp.put(p2, "Designer"); // hashcode2 --> index2
        mp.put(p3, "Manager"); // hashcode3 --> index3
        // but p1 and p3 are same. It should not produce a different hashcode.
        // But it produces different hashcode because the internal implementation of
        // hashcode in object class depends on memory location.
        // so, we have to implement our hashcode and equals method.
        System.out.println("Hashmap Size: " + mp.size());
        System.out.println("Value for p1: " + mp.get(p1));
        System.out.println("Value for p3: " + mp.get(p3));


        // for primitive cases, it works fine
        Map<String, Integer> mp1 = new HashMap<>();
        mp1.put("Adarsh", 90); // hashcode1 --> index1
        mp1.put("Kumar", 92); // hashcode2 --> index2
        mp1.put("Adarsh", 99); // hashcode1 --> index1 --> equals() --> replace

    }
    private static class Person {
        private String name;
        private int id;

        public Person(String name, int id) {
            this.name = name;
            this.id = id;
        }

        private int getId(){
            return this.id;
        }

        private String getName(){
            return this.name;
        }

        @Override
        public String toString() {
            return name + ": " + id + " -> ";
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.name, this.id);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;
            Person other = (Person) obj;
            return this.id==other.getId() && Objects.equals(this.name, other.getName());
        }
    }


}
