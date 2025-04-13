package concurrency;

public class Subtractor implements Runnable{
    IntValue intValue;

    public Subtractor(IntValue intValue) {
        this.intValue = intValue;
    }


    @Override
    public void run() {
        for(int i=1;i<=10000;i++) {
            //this.intValue.value -= 1;  //- without locks, synchronization

//            lk.lock();
//            this.intValue.value -= 1; //- using locks
//            lk.unlock();


            //this.intValue.increment(); //- Synchronized method


            synchronized (intValue) { // -sychronized block
                this.intValue.value -= 1;
            }
        }
    }
}
