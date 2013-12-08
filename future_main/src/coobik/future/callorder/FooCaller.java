package coobik.future.callorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FooCaller {

    private static OrderGuard GUARD;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("order of calls must be preserved");

        GUARD = new OrderGuard();

        Random random = new Random();

        List<CallerBase> callers = new ArrayList<CallerBase>();
        callers.add(new FirstCaller(random.nextInt(200), GUARD));
        callers.add(new SecondCaller(random.nextInt(200), GUARD));
        callers.add(new ThirdCaller(random.nextInt(200), GUARD));

        for (CallerBase caller : callers) {
            caller.start();
        }

        for (CallerBase caller : callers) {
            try {
                caller.join();
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("complete");
    }

    public static abstract class CallerBase extends Thread {

        protected int delay;
        protected OrderGuard guard;

        public CallerBase(int delay, OrderGuard guard) {
            this.delay = delay;
            this.guard = guard;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println(getClass().getSimpleName() + ": " + this.delay + " ms");

            runInternal();
        }

        protected abstract void runInternal();

    }

    public static class FirstCaller extends CallerBase {

        public FirstCaller(int delay, OrderGuard guard) {
            super(delay, guard);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void runInternal() {
            guard.first();
        }
    }

    public static class SecondCaller extends CallerBase {

        public SecondCaller(int delay, OrderGuard guard) {
            super(delay, guard);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void runInternal() {
            guard.second();
        }
    }

    public static class ThirdCaller extends CallerBase {

        public ThirdCaller(int delay, OrderGuard guard) {
            super(delay, guard);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void runInternal() {
            guard.third();
        }
    }

}
