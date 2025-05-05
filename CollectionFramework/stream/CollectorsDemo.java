package CollectionFramework.stream;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class CollectorsDemo {
    public static void main(String[] args) {
        // 1. collecting to a list
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> a = names.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());

        // 2. collecting to a set
        List<Integer> integers = Arrays.asList(1, 2, 2, 3, 4,4,4,5);
        Set<Integer> b = integers.stream().collect(Collectors.toSet());
        System.out.println(b);

        // 3. Collecting to a specified collection
        ArrayDeque<Integer> c = integers.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));
        // you can collect in any collection by above.

        // 4. Joining strings
        // Concatenates stream elements into a single String
        String concatenatedNames = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(concatenatedNames);

        // 5. Summarizing Data
        // Generates statistical summary (count, sum, min, max, avg)

        IntSummaryStatistics stats = integers.stream().collect(Collectors.summarizingInt(x -> x));
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum ());
        System.out.println("Min: " + stats. getMin());
        System.out.println("Average: " + stats. getAverage());
        System.out.println("Max: " + stats.getMax());

        // 6. Calculating Averages
        Double avg = integers.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println(avg);

        // 7. Counting elements.
        Long count = integers.stream().collect(Collectors.counting());
        System.out.println("cc "+count);

        // 8. Grouping elements.

        List<String> words = Arrays.asList("hello", "world", "java", "streams", "collecting");
        Map<Integer, List<String>> collect3 = words.stream().collect(Collectors.groupingBy(x -> x.length()));
        Map<Integer, String> collect4 = words.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(", ")));
        System.out.println(words.stream().collect(Collectors.groupingBy(x -> x.length()))); // Group the elements on the basis of length, and length will be the key.
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(", ")))); // 2nd parameter is collector, it will do some collection on each group.
        System.out.println(words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()))); // 2nd parameter is collector, it will do some collection on each group.
        TreeMap<Integer, Long> collect = words.stream().collect(Collectors.groupingBy(String::length, () -> new TreeMap<>(), Collectors.counting()));
        TreeMap<Integer, Long> collect2 = words.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        // in the three parameters of grouping by, the 2nd parameter is the specific implementation of map, you want.


        // 9. Partitioning elements
        // Partition elements into two groups (true and false) based on a predicate
        System.out.println(words.stream().collect(Collectors.partitioningBy(x -> x.length()>5)));

        // 10. Mapping and collecting
        // Applies a mapping before collecting
        System.out.println(words.stream().collect(Collectors.mapping(String::toUpperCase, Collectors.toList())));

        // Example 1. Collecting names by length
        List<String> l1 = Arrays.asList("Anna", "Bob", "Alexander", "Brian", "Alice");
        System.out.println(l1.stream().collect(Collectors.groupingBy(String::length)));

        // Example 2. Counting word occurrences
        String sentence = "hello world hello java world";
        System.out.println(Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        // Example 3: Partitioning Even and Odd Numbers
        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5, 6) ;
        Map<Boolean, List<Integer>> collect1 = l2.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(collect1);

        // Example 4: Summing Values in a Map
        Map<String, Integer> items = new HashMap<>() ;
        items.put ("'Apple", 10);
        items.put ("Banana", 20) ;
        items.put("Orange", 15);

        Optional<Integer> optionalInteger = items.values().stream().reduce(Integer::sum);
        System.out.println(optionalInteger.get());
        // or
        System.out.println(items.values().stream().collect(Collectors.summingInt(x ->x)));


        // Example 5: Creating a Map from Stream Elements on the basis of length
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");

        System.out.println(fruits.stream().collect(Collectors.toMap(String::toUpperCase, String::length))); // toMap is a collector which takes key and value as a function

        // Example 6: Find frequency of words using toMap
        List<String> words2 = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        System.out.println(words2.stream().collect(Collectors.toMap(key -> key, value -> 1, (x, y)->x+y))); // used merge function as sum (initialized first value as 1)
    }
}
