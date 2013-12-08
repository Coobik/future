package coobik.future.philosophers;

public class Dinner {

    // TODO introduce waiter

    public static void main(String[] args) {
        System.out.println("Dining philosophers");

        int numberOfEaters = 5;
        Chopstick[] chopsticks = new Chopstick[numberOfEaters];
        Philosopher[] eaters = new Philosopher[numberOfEaters];

        for (int i = 0; i < numberOfEaters; i++) {
            chopsticks[i] = new Chopstick("chopstick #" + i);
        }

        for (int i = 0; i < numberOfEaters; i++) {
            Chopstick leftChopstick = chopsticks[i];
            int rightChopstickIndex = i + 1;

            if (rightChopstickIndex == numberOfEaters) {
                rightChopstickIndex = 0;
            }

            Chopstick rightChopstick = chopsticks[rightChopstickIndex];
            eaters[i] = new Philosopher(leftChopstick, rightChopstick,
                    "Philosopher #" + i);
        }

        for (int i = 0; i < numberOfEaters; i++) {
            eaters[i].start();
        }

        for (int i = 0; i < numberOfEaters; i++) {
            try {
                eaters[i].join();
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("complete");

    }

}
