package concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AdderSubtractorMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        IntValue intValue = new IntValue();

        Lock lk = new ReentrantLock();
        Adder adder = new Adder(intValue, lk);
        Subtractor subtractor = new Subtractor(intValue);

        ExecutorService es = Executors.newCachedThreadPool();
        Future<?> adderFuture = es.submit(adder);
        Future<?> subtractorFuture = es.submit(subtractor);

        adderFuture.get();
        subtractorFuture.get();

        System.out.println(intValue.value);
    }
}
