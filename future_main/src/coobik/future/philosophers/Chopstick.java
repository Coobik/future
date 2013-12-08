package coobik.future.philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Chopstick {

    private boolean taken;
    private Philosopher owner;
    private final Lock takenLock = new ReentrantLock();
    protected final String name;

    public Chopstick(String name) {
        this.name = name;
    }

    public boolean take(Philosopher eater) {
        //this.takenLock.lock();
        if (this.takenLock.tryLock()) {
            this.owner = eater;
            this.taken = true;

            return true;
        }

        return false;
    }

    public void put() {
        this.takenLock.unlock();
        // TODO: toggle the flag with sync
    }

}
