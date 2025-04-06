package concurrency;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SortArray implements Callable<int[]> {

    private int[] inputArray;
    private ExecutorService executorService;

    public SortArray(int[] inputArray, ExecutorService executorService) {
        this.inputArray = inputArray;
        this.executorService = executorService;
    }

    @Override
    public int[] call() throws Exception {
        if(inputArray.length <= 1)
            return inputArray;

        //Divide and Conquer Approach

        int size = inputArray.length;
        int mid = size/2;

        int[] leftArray = Arrays.copyOfRange(inputArray, 0, mid);
        int[] rightArray = Arrays.copyOfRange(inputArray, mid, size);

        SortArray leftSorted = new SortArray(leftArray, executorService);
        SortArray rightSorted = new SortArray(rightArray, executorService);

        Future<int[]> leftSortedFuture = executorService.submit(leftSorted);
        Future<int[]> rightSortedFuture = executorService.submit(rightSorted);

        int[] leftResult = leftSortedFuture.get();
        int[] rightResult = rightSortedFuture.get();

        return mergeLeftAndRight(leftResult, rightResult);
    }

    private int[] mergeLeftAndRight(int[] leftResult, int[] rightResult) {
        int[] result = new int[leftResult.length + rightResult.length];

        int index = 0;
        int start1 = 0;
        int start2 = 0;

        while(start1 < leftResult.length && start2 < rightResult.length) {
            if(leftResult[start1] < rightResult[start2]) {
                result[index++] = leftResult[start1++];
            }
            else {
                result[index++] = rightResult[start2++];
            }
        }

        while(start1 < leftResult.length) {
            result[index++] = leftResult[start1++];
        }

        while(start2 < rightResult.length) {
            result[index++] = rightResult[start2++];
        }

        return result;
    }
}
