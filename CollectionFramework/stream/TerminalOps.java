package CollectionFramework.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOps {
    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        // 1. collect
        List<Integer> collect1 = integers.stream().skip(1).collect(Collectors.toList());
        List<Integer> collect2 = integers.stream().skip(1).toList();

        // 2. forEach
        integers.stream().forEach(System.out::println);

        // 3. reduce: Combines elements to produce a single result.
        Optional<Integer> optionalInteger = integers.stream().reduce((x, y) -> x + y); // optional integer is like a box, an element can or cannot be in it.
        Optional<Integer> optionalInteger1 = integers.stream().reduce(Integer::sum); // method reference
        System.out.println(optionalInteger.get());

        // 4. count

        // 5. allMatch, anyMatch, noneMatch
        boolean b = integers.stream().anyMatch(x -> x % 2 == 0); // returns true if any of the element satisfies the condition
        System.out.println(b);
        boolean b1 = integers.stream().allMatch(x -> x>0);  // returns true if all of the element satisfies the condition
        System.out.println(b1);
        boolean b2 = integers.stream().noneMatch(x -> x < 0); // returns true if none of the element satisfies the condition
        System.out.println(b2);

        // 6. findFirst, findAny
        System.out.println(integers.stream().findFirst().get());
        System.out.println(integers.stream().findAny().get());

        // 7. toArray
        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min/max
        System.out.println("MAX : " + Stream.of(2, 44, 69).max((o1, o2)->o1-o2).get()); // orders in ascending order and then picks the last element.
        System.out.println("MAX : " + Stream.of(2, 44, 69).max((o1, o2)->o2-o1).get()); // this will give max as 2, as it is sorting in descending order.
        System.out.println("MIN : " + Stream.of(2, 44, 69).min(Comparator.naturalOrder()).get());

        // 9. forEachOrdered
        List<Integer> num = Arrays.asList(1, 2, 3 ,4 ,5, 6, 7, 8, 9, 10);
        System.out.println("Using forEach with parallel streams : ");
        num.parallelStream().forEach(System.out::println); // doesn't print in order
        System.out.println("Using forEachOrdered with parallel stream: ");
        num.parallelStream().forEachOrdered(System.out::println); // print in order


        // Examples

        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        // print those strings whose length is greater than 3.
        names.stream().filter(s -> s.length()>3).forEach(System.out::println);

        // Square and sort numbers in reverse order.
        List<Integer> list = integers.stream().map(x -> x * x).sorted((x, y)-> y-x).toList();
        System.out.println(list);

        // Example : summing values
        Integer sum = integers.stream().reduce((x, y) -> x + y).get();
        System.out.println(sum);

        // Count the number of character l in the string: "Hello world"
        String s = "Hello world";
        IntStream chars = s.chars(); // chars() give stream of ascii integers of the characters.
        System.out.println(s.chars().filter(c -> c == 'l').count());

        // Example
        // Streams cannot be used after a terminal operation has been called.
        Stream<String> st = names.stream();
        st.forEach(System.out::println);
//        List<String> list1 = st.map(String::toUpperCase).toList(); // give exception that stream already been consumed.


        // Stateful operation -> Which takes care of all elements in the stream
        // Stateless operation -> Which only care of the element on which it is currently.

    }
}
