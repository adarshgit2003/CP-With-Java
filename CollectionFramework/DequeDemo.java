package CollectionFramework;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {
    public static void main(String[] args) {
        // double-ended queue
        // allows insertion and deletion at both ends.
        // versatile than regular queues and stacks because they support all operations of both

        Deque<Integer> dq = new ArrayDeque<>(); // faster iteration, low memory and no null allowed
        // circular, head and tail
        // no need to shift elements, just shift head and tail
        dq.addFirst(1);
        dq.offerFirst(2);
        dq.addLast(4);
        dq.offerLast(5);
        System.out.println(dq);

        System.out.println("First ele " + dq.peekFirst());
        System.out.println("Last ele " + dq.getLast());

        dq.removeFirst();
        dq.pollLast();

        // offer, peek and poll returns null when no elements found or no space left.
        // add, get and remove throws exception.

        // Deque can also be implemented using linked list. But ArrayDeque is faster as it has
        // two pointers: head and tail -> behaves circular while playing with indices.
        // get doubled when queue gets full.

        Deque<Integer> dq2 = new LinkedList<>(); // insertion, deletion somewhere in middle.

    }
}
