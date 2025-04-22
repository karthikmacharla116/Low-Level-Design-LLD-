package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable {

    private Store store;
    private Lock lk;
    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;

    public Consumer(Store store, Lock lk, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
        this.store = store;
        this.lk = lk;

        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }

    @Override
    public void run() {

//        while(true) {
//            lk.lock();
//            if(!store.items.isEmpty()) {
//                System.out.println("Consumer :"+store.items.size());
//                System.out.println("Consumer :"+Thread.currentThread().getName());
//
//                store.items.remove(store.items.size() - 1);
//            }
//            lk.unlock();
//        }

        while(true) {
            try {
                consumerSemaphore.acquire();

                System.out.println("Consumer :" + store.items.size());
                System.out.println("Consumer :" + Thread.currentThread().getName());
                store.items.remove();

                producerSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
