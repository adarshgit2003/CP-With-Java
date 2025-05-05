package CollectionFramework.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOps {
    public static void main(String[] args) {
        // Intermediate operations transform a stream into another stream
        // They are lazy, meaning they don't execute until a termination operation is invoked.

        // 1. filter
        List<String> list = Arrays.asList("Ram", "Shyam", "Adarsh", "Ajay", "Adarsh");
        Stream<String> filteredStream = list.stream().filter(x -> x.startsWith("A"));
        // no filtration is done at above point, because we don't used a terminal operation.
        long a = list.stream().filter(x -> x.startsWith("A")).count(); // now filtration is done as count is a termjnal operation
        System.out.println(a);

        // 2. map
        Stream<String> stringStream = list.stream().map(x -> x.toUpperCase());
        Stream<String> stringStream2 = list.stream().map(String::toUpperCase); // method reference

        // 3. sorted
        Stream<String> sortedStream = list.stream().sorted();
        Stream<String> sortedStreamUsingComparator = list.stream().sorted((x, y) -> x.length()-y.length());
        Stream<String> sortedStreamUsingComparator2 = list.stream().sorted(Comparator.comparingInt(String::length));

        // 4. distinct
        long a1 = list.stream().filter(x -> x.startsWith("A")).distinct().count();

        // 5. limit
        List<Integer> list1 = Stream.iterate(1, x -> x + 1).limit(100).toList();
        System.out.println(list1);

        // 6. skip
        List<Integer> list2 = Stream.iterate(1, x -> x + 1).skip(10).limit(100).toList();
        System.out.println(list2);

        // 7. peek
        // Performs an action on each element as it is consumed. (Same like forEach, but forEach is terminal ops and peek is sequential ops)
        Stream.iterate(1, x->x+1).limit(100).peek(System.out::println).count();

        // 8. flatMap
        // Handle stream of collections, list or arrays where each element is itself a collection.
        // Flatten nested structure (e.g. lists within list) so that they can be processed as a single sequence of elements.
        // Transform and flatten elements at the same time.

        List<List<String>> listofLists = Arrays.asList(
                Arrays.asList("Apple", "Orange"),
                Arrays.asList("Banana", "Kiwi"),
                Arrays.asList("pear", "grape")
        );

        System.out.println(listofLists.stream()
                .flatMap(oneList -> oneList.stream())
                .map(s -> s.toUpperCase())
                .toList());

        List<String> sentences = Arrays.asList(
                "Hello world" ,
                "Java streams are powerful",
                "flatMap is useful");
        System.out.println(sentences
                .stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .map(String::toUpperCase)
                .toList());
    }
}
