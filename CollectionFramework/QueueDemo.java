package CollectionFramework;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueDemo {
    public static void main(String[] args) {
        // Queue is an interface,
        // LinkedList is one of its implementation class.

        Queue<Integer> q = new LinkedList<>();
        q.add(1); // add at last
        q.add(2); // add at last
        q.add(3); // add at last
        q.remove();  // remove first
        System.out.println(q);

        Queue<Integer> q1 = new LinkedList<>();

//        System.out.println(q1.remove()); // throws exception - NoSuchElementException
        System.out.println(q1.poll()); // work is same as remove, but it doesn't throw exception if queue is empty.

//        System.out.println(q1.element()); // throws exception
        System.out.println(q1.peek()); // no exception

        Queue<Integer> q2 = new ArrayBlockingQueue<>(2);
        System.out.println(q2.add(1)); // true
        System.out.println(q2.offer(2)); // same work as add.  return true.

//        System.out.println(q2.add(3)); // th,rows exception - Queue full
        System.out.println(q2.offer(3)); // false
    }
}
