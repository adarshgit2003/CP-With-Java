package CollectionFramework.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LazyEvaluationDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        Stream<String> stream = names.stream()
                .filter(name -> {
                    System.out.println("Filtering "+name);
                    return name.length()>3;
                });

        System.out.println("Before terminal operation");
        System.out.println(names);

        List<String> result = stream.collect(Collectors.toList());
        System.out.println("After terminal operation");
        System.out.println(result);

        // Before terminal operation will get printed first, it shows that the filtering is not done until we
        // did not specified the terminal operation.
    }
}
