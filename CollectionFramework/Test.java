package CollectionFramework;

import java.util.*;

class Student implements Comparable<Student> {
    String name;
    Double grade;
    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return this.name;
    }

    public Double getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        return this.name + " " + this.grade;
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(o.getGrade(), this.getGrade());
    }
}
public class Test {

    public static void main(String[] args) {
//        List<Integer> list1 = new ArrayList<>();
//        System.out.println(list1.getClass().getName());
//        List<String> list2 = Arrays.asList("Apple", "Banana", "Mango");
//        System.out.println(list2.getClass().getName());
//        String[] array = {"Hello", "Hi", "Bye"};
//        List<String> list3 = Arrays.asList(array);
//        System.out.println(list3.getClass().getName());
//        List<Integer> integerList = List.of(1, 2, 3);
//
//        list1.add(1);
//        list1.add(2);
//        list1.add(3);
//        list1.remove(1);
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("Adarsh", 9.8));
//        students.add(new Student("Priyam", 7.9));
//        students.add(new Student("Sachin", 9.5));
//        students.add(new Student("Ankit", 9.5));

//        students.sort((a, b) -> {
//            if (a.getGrade() > b.getGrade()) {
//                return -1;
//            }
//            else if (a.getGrade() < b.getGrade()) {
//                return 1;
//            }
//            else {
//                return a.getName().compareTo(b.getName());
//            }
//        });

//        System.out.println("Sum = " + students.stream().map(student -> student.getGrade()).reduce(0.0, (sum, grade) -> sum+grade));
//        Comparator<Student> comparator = Comparator.comparing(Student::getGrade).reversed().thenComparing(Student::getName);
//
//        students.sort(comparator);
//        System.out.println(students);
//
//        BinaryOperator<Integer> biobj = (a,b)->a*b;
//        Function<Integer, Integer> fn = c->c/2;
//        System.out.println(biobj.andThen(fn).andThen(num -> num*num).apply(60, 10));
//
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        int result= numbers.stream()
//                .filter(n -> n % 2 == 0)
//                .reduce(0, (sum ,num) -> sum+num);
//        System.out.println(result);

//        List<Integer> linkedlist = new LinkedList<>();
//        linkedlist.add(1);
//        linkedlist.add(2);
//        linkedlist.add(3);
//        linkedlist.addLast(4);
//        linkedlist.addFirst(0);
//        System.out.println(linkedlist);
//        linkedlist.removeIf(x -> x%2==0);
//        System.out.println(linkedlist);

//        Map<Integer, String> mp = new HashMap<>();
//        mp.put(1, "Adarsh");
//        mp.put(2, "Kumar");
//        mp.put(3, "Shrivastav");
//        System.out.println(mp);
//        System.out.println(mp.get(3));
//        System.out.println(mp.containsKey(4));
//        System.out.println(mp.containsValue("Kumar"));
//
//        for (Integer i : mp.keySet()) {
//            System.out.println(mp.get(i));
//        }
//
//        Set<Integer> integers = mp.keySet();
//        for (Integer i: integers)
//            System.out.println(i + ": " + mp.get(i));
//
//        Set<Map.Entry<Integer, String>> entries = mp.entrySet();
//        for (Map.Entry<Integer, String> entry : mp.entrySet()) {
//            entry.setValue(entry.getValue().toUpperCase());
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//        System.out.println(mp);
//        mp.put(null, "Priyam");
//        mp.put(4, null);
//        System.out.println(mp);

//        List<Integer> li = new CopyOnWriteArrayList<>();
//        li.add(1);
//        li.add(2);
//        li.add(3);
//        Iterator<Integer> it = li.iterator();
//        while (it.hasNext()) {
//            li.add(1);
//            System.out.println(it);
//            it.next();
//        }
//        while (it.hasNext()) {
//            System.out.println(it);
//            it.next();
//        }
        HashMap<Integer, Integer> mp1 = new HashMap<>();
        mp1.put(1, 1);
        mp1.put(2, 2);
        HashMap<Integer, Integer> mp2 = new HashMap<>();
        mp2.put(3, 3);
        mp1 = mp2;
        System.out.println(mp1);
    }
}



