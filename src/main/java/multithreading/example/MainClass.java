package multithreading.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Все участники должны стартовать одновременно, несмотря на разное время  подготовки.
 * В тоннель не может одновременно заехать больше половины участников (условность).
 * Попробуйте все это синхронизировать.
 * Первый участник, пересекший финишную черту, объявляется победителем
 * (в момент пересечения этой самой черты). Победитель должен быть только один (ситуация с 0 или 2+ победителями недопустима).
 * Когда все завершат гонку, нужно выдать объявление об окончании.
 */
public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT + 1);
    public static final Semaphore smp = new Semaphore(CARS_COUNT / 2);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        System.out.println(">>> Ждем готовности всех участников");

        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            cb.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cb.await();
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
