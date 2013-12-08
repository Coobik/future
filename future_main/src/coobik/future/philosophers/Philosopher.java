package coobik.future.philosophers;

import java.util.Random;


public class Philosopher extends Thread {

    public static final int NUMBER_OF_MEALS = 10;

    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    private final String name;
    private final Random randomizer = new Random();

    public Philosopher(Chopstick leftChopstick, Chopstick rightChopstick, String name) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.name = name;
    }

    public void eat() {
        if (takeChopsticks()) {
            chew();

            putChopsticks();
        }
    }

    private void chew() {
        System.out.println(name + " eating");

        try {
            Thread.sleep(this.randomizer.nextInt(100));
        }
        catch (InterruptedException e) {

        }
    }

    private boolean takeChopsticks() {
        System.out.println(name + " wants left " + this.leftChopstick.name);

        if (!this.leftChopstick.take(this)) {
            System.out.println(name + " cannot take left "
                    + this.leftChopstick.name);
            return false;
        }

        System.out.println(name + " takes left " + this.leftChopstick.name);
        System.out.println(name + " wants right " + this.rightChopstick.name);

        if (!this.rightChopstick.take(this)) {
            System.out.println(name + " cannot take right "
                    + this.rightChopstick.name);

            System.out.println(name + " puts left " + this.leftChopstick.name);
            this.leftChopstick.put();

            return false;
        }

        System.out.println(name + " takes right " + this.rightChopstick.name);

        return true;
    }

    private void putChopsticks() {
        System.out.println(name + " puts right " + this.rightChopstick.name);
        this.rightChopstick.put();

        System.out.println(name + " puts left " + this.leftChopstick.name);
        this.leftChopstick.put();
    }

    private void think() {
        System.out.println(name + " thinking");

        try {
            Thread.sleep(this.randomizer.nextInt(100));
        }
        catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        for (int i = 0; i < NUMBER_OF_MEALS; i++) {
            // TODO: no thinking leads to deadlock :)
            // think();
            eat();
        }

    }

}
