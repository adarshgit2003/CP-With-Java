package CollectionFramework;

import java.util.concurrent.*;

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private int value = 0;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("Producer produced " + value);
                queue.put(value++);
                Thread.sleep(1000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }
        }
    }
}
class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Integer value = queue.take();
                System.out.println("Consumer consumed " + value);
                Thread.sleep(2000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        }
    }
}

public class BlockingQueueDemo {
    public static void main(String[] args) {
        // Thread safe queue
        // Wait for queue to become non-empty / wait for space.
        // Simplify problems like producer - consumer
        // Standard queue --> Immediately
            // empty --> remove (no waiting)
            // full --> add (no waiting)

        // Blocking queue
            // put -> Blocks if the queue is full until space becomes available
            // take --> Blocks if the queue is empty until an element becomes available
            // offer --> Waits for space to become available, up to the specified timeout


//        System.out.println("hello");
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);
        // A bounded, blocking queue backed by an array
        // low memory overhead
        // single lock
        // Uses a single lock for both enqueue and dequeue operations.
        // More threads ->> then it is a problem.

        Thread producer = new Thread(new Producer(q));
        Thread consumer = new Thread(new Consumer(q));
        producer.start();
        consumer.start();


        BlockingQueue<Integer> q2 = new LinkedBlockingQueue<>();
        // Optionally bounded backed by linked list
        // Uses two separate locks for enqueue and deque operations
        // Higher concurrency between producer and consumer.

        BlockingQueue<Integer> q3 = new PriorityBlockingQueue<>();
        // Creates a PriorityBlockingQueue with the default initial capacity (11)
        // that orders its elements according to their natural ordering.
        // Binary heap as array and can grow dynamically.


    }
}
