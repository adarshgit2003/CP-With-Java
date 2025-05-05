package CollectionFramework;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetDemo {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        ConcurrentSkipListSet<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>();
        for (int i=1; i<=5; i++) {
            copyOnWriteArraySet.add(i);
            concurrentSkipListSet.add(i);
        }
        System.out.println(concurrentSkipListSet);
        System.out.println(concurrentSkipListSet);

        System.out.println("Iterating and modifying copyonwritearrayset");
        for (Integer num: copyOnWriteArraySet) {
            System.out.println("Reading from copyonwritearrayset " + num);
            copyOnWriteArraySet.add(6);
        }
        // 6 won't be iterated, it is updated in a new copy of copyonwritearrayset.
        // Iteration is happening on previous snapshot.
        System.out.println(copyOnWriteArraySet);

        System.out.println("Iterating and modifiying concurrentskiplistset");
        for (Integer num: concurrentSkipListSet) {
            System.out.println("Reading from concurrentskiplistset " + num);
            concurrentSkipListSet.add(6);
        }
        System.out.println(concurrentSkipListSet);
        // Concurrentskiplistset is weakly consistent. It may or may not add the element 6.
    }
}
