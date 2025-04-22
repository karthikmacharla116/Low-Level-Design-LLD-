package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {

    public int maxShelfs;
    public Queue<Object> items;

    public Store(int maxShelfs) {
        this.maxShelfs = maxShelfs;
        this.items = new ConcurrentLinkedDeque<>();
    }

    public void addItem(){
        items.add(new Object());
    }

    public void removeItem() {
        //items.remove(items.size()-1);
        items.remove();
    }
}
