package CollectionFramework;

import java.util.ArrayList;
import java.util.List;


public class ComparableDemo {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(1);
        numbers.add(30);
        numbers.sort(null); // natural ordering sort
        System.out.println(numbers);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Adarsh", 9.8));
        students.add(new Student("Priyam", 7.9));
        students.add(new Student("Sachin", 9.5));
        students.add(new Student("Ankit", 9.5));
        students.sort(null);
        // If not implemented comparable then this error comes: class Student cannot be cast to class java.lang.Comparable
        System.out.println(students);



    }
}
