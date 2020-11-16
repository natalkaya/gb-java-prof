package multithreading;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier выполняет синхронизацию заданного количества потоков в одной точке.
 * Как только заданное количество потоков заблокировалось (вызовами метода await()),
 * с них одновременно снимается блокировка.
 */
public class BarrierExample {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            final int w = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + w + " готовится");
                    Thread.sleep(100 + (int) (3000 * Math.random()));
                    System.out.println("Поток " + w + " готов");
                    cb.await();
                    System.out.println("Поток " + w + " запустился");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
