package CollectionFramework.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        // Feature introduces in Java 8
        // Process collection of data in a functional and declarative manner.
        // Simplify data processing
        // Embrace functional programming.
        // Improve readability and maintainability
        // Enable easy parallelism

        // What is stream?
        // A sequence of elements supporting functional and declarative programming.

        // How to use stream?
        // Source, intermediate operation and terminal operation

        // Count the even numbers in the list
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // aam zindagi
        int count = 0;
        for (int x: numbers) {
            if (x%2==0) {
                count++;
            }
        }
        System.out.println(count);

        // mentos zindagi
        System.out.println(numbers.stream().filter(x->x%2==0).count());

        // Creating streams
        // 1. From collection
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4 ,5);
        Stream<Integer> stream1 = list1.stream();

        // 2. From Arrays
        String[] array = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(array);

        // 3. Using Stream.of
        Stream<String> stream3 = Stream.of("a", "bc");

        // 4. Infinite stream
        Stream<Integer> stream4 = Stream.generate(() -> 1); // Generate infinite 1s
        Stream<Integer> stream5 = Stream.iterate(1, x->x+1); // iterate takes one starting point as seed and a unarayoperator.
        // Above stream5 is a counting of infinite numbers starting from 1.


    }
}
