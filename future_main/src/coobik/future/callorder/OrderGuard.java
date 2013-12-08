package coobik.future.callorder;

import java.util.concurrent.Semaphore;


public class OrderGuard {

    private Semaphore sema1 = new Semaphore(1);
    private Semaphore sema2 = new Semaphore(1);
    private Semaphore sema3 = new Semaphore(1);

    public OrderGuard() throws InterruptedException {
        this.sema1.acquire();
        this.sema2.acquire();
        this.sema3.acquire();

    }

    public void first() {
        System.out.println(Thread.currentThread().getId() + ": " + "first");
        this.sema1.release();
    }

    public void second() {
        try {
            this.sema1.acquire();
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        System.out.println(Thread.currentThread().getId() + ": " + "second");
        this.sema2.release();
        this.sema1.release();

    }

    public void third() {
        try {
            this.sema2.acquire();
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        System.out.println(Thread.currentThread().getId() + ": " + "third");
        this.sema3.release();
        this.sema2.release();

    }

}
