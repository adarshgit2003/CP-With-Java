package CollectionFramework;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Java8Demo {
    public static void main(String[] args) {
        // Streams

        // Java 8 --> Minimal Coding, functional programming, lambda expressions, streams, data and time API

        // What is lambda ?
        // Lambda function is an anonymous function (no name, no return type, no access modifier)

        // Functional programming means a function is treated as a variable.


        MathOperation sumOperation = (a, b) -> a+b;
        MathOperation subtractOperation = (a, b) -> a-b;

        System.out.println(sumOperation.operate(3, 2));
        System.out.println(subtractOperation.operate(4, 2));

        // Predicate --> FunctionalInterface (Boolean valued function)

        Predicate<Integer> isEven = x -> x%2==0;
        System.out.println(isEven.test(2));
        // What are doing above?
        // You are storing a condition in a variable (isEven). How fancy it is?
        // This is called functional programming

        Predicate<String> isWordStartingWithA = x -> x.toLowerCase().startsWith("a");
        Predicate<String> isWordEndingWithH = x->x.toLowerCase().endsWith("h");
        Predicate<String> and = isWordStartingWithA.and(isWordEndingWithH); // Combining two predicate with and.
        System.out.println(and.test("Adarsh"));
        System.out.println(and.test("Harsh"));

        // Function -> FunctionalInterface (Takes one input, returns one output)

        Function<Integer, Integer> doubleIt = x -> 2*x;
        Function<Integer, Integer> tripleIt = x -> 3*x;
        System.out.println(doubleIt.apply(100));
        System.out.println(doubleIt.andThen(tripleIt).apply(20)); //first double and then triple
        System.out.println(doubleIt.compose(tripleIt).apply(30)); // first triple and then double.

        // static function in Function interface
        Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(4)); // what it takes, it returns.


        // Consumer -> Accept one input, but returns nothing.
        Consumer<Integer> print = x -> System.out.println(x);
        print.accept(40);
        List<Integer> list = Arrays.asList(1, 2, 3);
        Consumer<List<Integer>> printList = x -> {
            for (int i: x) {
                System.out.println(i);
            }
        };
        printList.accept(list);

        // Supplier --> Accepts nothing, but returns one output.
        Supplier<String> giveHelloWorld = () -> "Hello World";
        System.out.println(giveHelloWorld.get());

        // Combined example

        Predicate<Integer> predicate = x->x%2!=0;
        Function<Integer, Integer> function = x->x*x;
        Supplier<Integer> supplier = () -> 25;
        Consumer<Integer> consumer = x-> System.out.println(x);

        if (predicate.test(supplier.get())) {
            consumer.accept(function.apply(supplier.get()));
        }

        // BiPredicate, BiConsumer, BiFunction (All takes two arguments instead of one)

        BiPredicate<Integer, Integer> biPredicate = (x, y) -> (x+y)%2==0;
        System.out.println(biPredicate.test(5, 5));

        BiConsumer<Integer, Integer> biConsumer = (x, y) -> {
            System.out.println(x*y);
        };
        biConsumer.accept(3, 9);

        BiFunction<String, String, Integer> biFunction = (x, y) -> x.length() + y.length();
        System.out.println(biFunction.apply("ADa", "as"));


        // UnaryOperator -> It extends Function, it has both argument and return type of same data type.
        UnaryOperator<Integer> unaryOperator = x -> 4*x;
        System.out.println(unaryOperator.apply(3));

        // BinaryOperator -> It extends BiFunction, it has all three of same type (Two arguments and one return type)
        BinaryOperator<Integer> binaryOperator = (x, y) -> x+y;
        System.out.println(binaryOperator.apply(8, 9));


        // Method reference -> Use method without invoking and in place of lambda expression.
        List<String> students = Arrays.asList("Ram", "Shyam" ,"Ghanshyam");
        students.forEach(x -> System.out.println(x));
        students.forEach(System.out::println);  // method reference // used in place of lambda expression

        // Constructor reference
        List<String> names = Arrays.asList("Samsung", "Redmi", "Apple");
        List<MobilePhone> l1 = names.stream().map(x -> new MobilePhone(x)).collect(Collectors.toList());
        List<MobilePhone> l2 = names.stream().map(MobilePhone::new).collect(Collectors.toList()); // Constructor reference
    }
}

class MobilePhone {
    String name;

    public MobilePhone(String name) {
        this.name = name;
    }
}

interface MathOperation {
    int operate(int a, int b);
}