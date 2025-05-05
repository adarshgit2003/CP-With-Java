package CollectionFramework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorDemo {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        for (int i: list1) {
            System.out.println(i);
        }

        // Internally above for each loop works as
        Iterator<Integer> iterator = list1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

//        for (Integer number: list1) {
//            if (number % 2 == 0) {
//                list1.remove(number);
//            }
//        }

        // Above for loop gives ConcurrentModificationException, because we are modifying loop while iterating.
        // If we use Copyonwritearraylist, then it will iterate on snapshot and remove the element after iterating.

        // but, we can remove while iterating by using iterator object.

        Iterator<Integer> itr = list1.iterator();
        while (itr.hasNext()) {
            Integer number = itr.next();
            if (number % 2 == 0) {
                itr.remove();
            }
        }
        System.out.println(list1);

        ListIterator<Integer> listIterator = list1.listIterator(list1.size()); // by giving list.size() it will iterate from back side.

        while (listIterator.hasPrevious()) {
            Integer number = listIterator.previous();
            System.out.println(number);
            listIterator.set(number+1); // we can set also while iterating.
        }
        System.out.println(list1);
    }
}
