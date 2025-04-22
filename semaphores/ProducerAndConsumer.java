package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {

    public static void main(String[] args) {

        Store store = new Store(5);

        // Stage 1: Without Locks, Semaphores

        //Stage 2: Using Locks
        Lock lock = new ReentrantLock();

        //stage 3: Using Semaphores
        Semaphore producerSemaphore = new Semaphore(5);
        Semaphore consumerSemaphore = new Semaphore(0);

        Producer producer = new Producer(store, lock, producerSemaphore, consumerSemaphore);
        Consumer consumer = new Consumer(store, lock, producerSemaphore, consumerSemaphore);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=1;i<=10;i++) {
            executorService.execute(producer);
        }

        for(int i=1;i<=20;i++) {
            executorService.execute(consumer);
        }
    }
}
