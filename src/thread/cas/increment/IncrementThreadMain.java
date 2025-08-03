package thread.cas.increment;

import static util.ThreadUtils.sleep;

import java.util.ArrayList;

public class IncrementThreadMain {

    public static final int THREAD_COUNT = 1_000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger()); // 1000이 안나옴
        test(new VolatileInteger()); // 1000이 안나옴
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10); // 너무 빨리 실행되기 때문에, 다른 스레드와 동시 실행을 위해 작성
                incrementInteger.increment();
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(); // 모든 스레드가 종료 될 때 까지 기다림
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": " + result);
    }
}
