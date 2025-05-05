package CollectionFramework.stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(numbers); // Primitive stream.
        Stream<Integer> boxed = stream.boxed(); // boxed convert it into stream
        System.out.println(IntStream.range(1, 5).boxed().collect(Collectors.toList())); // [1, 5)
        System.out.println(IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList())); // [1, 5]

        Integer[] num = {1, 2, 3, 4, 5};
        Stream<Integer> stream1 = Arrays.stream(num); // using wrapper class integer gives directly stream.

        IntStream.of(1, 2, 3);

        DoubleStream doubles = new Random().doubles(5);
//        System.out. println(doubles.sum());
//        System.out.println(doubles.min());
//        System.out.println(doubles.max());
//        System.out.println(doubles.average());
//        doubles.summaryStatistics()
        System.out.println(doubles.boxed().toList());

        IntStream ints = new Random().ints(5);
        System.out.println(ints.boxed().toList());

    }
}
