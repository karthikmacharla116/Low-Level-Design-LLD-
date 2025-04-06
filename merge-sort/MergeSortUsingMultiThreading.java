package concurrency;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSortUsingMultiThreading {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] input = {8, 1, 6, 2, 9, 4, 2, 7, 3, 5};

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        SortArray sortArray = new SortArray(input, executorService);
        Future<int[]> sortedArrayFuture = executorService.submit(sortArray);
        int[] sortedResult = sortedArrayFuture.get();

        System.out.println(Arrays.toString(sortedResult));
    }
}
