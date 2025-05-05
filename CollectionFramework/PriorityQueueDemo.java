package CollectionFramework;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // Part of the queue interface
        // Orders elements based on their natural ordering (for primitives, lowest first)
        // custom comparator for customised ordering
        // does not allow null elements

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(35);
        pq.add(5);
        pq.add(100);
        System.out.println(pq); // not sorted, cares only about most priority element.
        while (!pq.isEmpty()){
            System.out.println(pq.poll());
        }

        // Internal working
        // Priority queue is implemented as a min-heap by default(for natural ordering).

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        pq2.add(1);
        pq2.add(35);
        pq2.add(5);
        pq2.add(100);
        while (!pq2.isEmpty()){
            System.out.println(pq2.poll());
        }
    }
}
