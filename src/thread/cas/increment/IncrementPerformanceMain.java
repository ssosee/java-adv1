package thread.cas.increment;

import static util.ThreadUtils.sleep;

import java.util.ArrayList;

public class IncrementPerformanceMain {

    public static final int COUNT = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        long startMs = System.currentTimeMillis();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < COUNT; i++) {
                    incrementInteger.increment();
                }
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(); // 모든 스레드가 종료 될 때 까지 기다림
        }

        long endMs = System.currentTimeMillis();

        System.out.println(
                incrementInteger.getClass().getSimpleName() + ": ms=" + (endMs - startMs) + ", result=" + incrementInteger.get());
    }
}
