package concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {

    private Store store;
    private Lock lk;
    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;

    public Producer(Store store, Lock lk, Semaphore producerSemaphore, Semaphore consumerSemaphore) {
        this.store = store;
        this.lk = lk;

        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }

    @Override
    public void run() {

//        while(true) {
//            lk.lock();
//            if(store.items.size() < store.maxShelfs) {
//                System.out.println("Producer :"+store.items.size());
//                System.out.println("Producer :"+Thread.currentThread().getName());
//
//                store.items.add(new Object());
//            }
//            lk.unlock();
//        }

        while(true) {
            try {
                producerSemaphore.acquire();

                Thread.sleep(20);
                System.out.println("Producer :" + store.items.size());
                store.addItem();

                consumerSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
