package concurrency;

public class IntValue {
    int value = 0;

    synchronized public void increment() {
        value += 1;
    }

    synchronized public void decrement() {
        value -= 1;
    }
}
