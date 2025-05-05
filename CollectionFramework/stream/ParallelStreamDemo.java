package CollectionFramework.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        // A type of stream that enables parallel processing of elements
        // Allowing multiple threads to process different parts of the stream simultaneously.
        // this can significantly improve performance on large data sets.
        // Workload is distributed among multiple threads.

        long startTime = System.currentTimeMillis();
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000).toList();
        List<Long> factorialsList = list.stream().map(ParallelStreamDemo::factorial).toList();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with sequential stram: " + (endTime - startTime) + " ms");

        long startTime2 = System.currentTimeMillis();
        List<Long> factorialsList2 = list.parallelStream().map(ParallelStreamDemo::factorial).toList();
        long endTime2 = System.currentTimeMillis();
        System.out.println("Time taken with parallel stram: " + (endTime2 - startTime2) + " ms");

        // Parallel streams are most effective for CPU-intensive or large datasets where tasks are independent.
        // they may add overhead for simple tasks or small datasets.

        // Cumulative sum
        // [1, 2, 3, 4, 5] -> [1, 3, 6, 10, 15]

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> list1 = numbers.parallelStream().map(x -> sum.addAndGet(x)).toList();
        System.out.println(list1); //  not as expected because we used parallel stream.

        sum.set(0);
        numbers.stream().map(sum::addAndGet).toList().forEach(System.out::println); // this gives correct output


        // Convert parallelstream to sequentialstream:
//        numbers.parallelStream().sequential();
    }
    private static long factorial(int n) {
        long result = 1;
        Integer i = Stream.iterate(1, x -> x + 1).limit(n).reduce((x, y) -> x * y).get();
        return i;
    }
}
