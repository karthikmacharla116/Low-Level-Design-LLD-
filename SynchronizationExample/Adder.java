package concurrency;

import java.util.concurrent.locks.Lock;

public class Adder implements Runnable{
    IntValue intValue;
    Lock lk;

    public Adder(IntValue intValue, Lock lk) {
        this.intValue = intValue;
        this.lk = lk;
    }

    @Override
    public void run() {
        for(int i=1;i<=10000;i++) {
            //this.intValue.value += 1;  //- without locks, synchronization

//            lk.lock();
//            this.intValue.value += 1; //- using locks
//            lk.unlock();


            //this.intValue.increment(); //- Synchronized method

            synchronized (intValue) { //-Synchrnoized block
                this.intValue.value += 1;
            }
        }
    }
}
