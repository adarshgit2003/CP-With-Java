package CollectionFramework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> q = new SynchronousQueue<>();
        // Each insert operation must wait for a corresponding remove operation by another thread and vice versa.
        // It cannot store elements, capacity of at most one element.

        Thread producer = new Thread(() -> {
            try{
                System.out.println("Producer is waiting for transfer");
                q.put("Hello from producer");
                System.out.println("Producer has transferred the message");
            }
            catch (Exception e) {
                Thread.currentThread().interrupt();
                System.err.println("Producer has interrupted");
            }
        });

        Thread consumer = new Thread(() -> {
            try{
                System.out.println("Consumer is waiting to receive");
                String message = q.take();
                System.out.println("Consumer received : " + message);
            }
            catch (Exception e) {
                Thread.currentThread().interrupt();
                System.err.println("Consumer has interrupted");
            }
        });

        producer.start();
        consumer.start();
    }
}
