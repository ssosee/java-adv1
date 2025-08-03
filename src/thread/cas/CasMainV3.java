package thread.cas;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV3 {

    private static final int THREAD_COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int value = incrementAndGet(atomicInteger);
                log("value: " + value);
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();

        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    /**
     * CAS 연산의 원자성은 compareAndSet 호출 자체에만 적용되며, 그 이후의 get() 호출은 별도의 연산입니다. 따라서 두 연산 사이에 다른 스레드가 개입할 수 있어서 일관성이 깨집니다.
     * getValue + 1을 사용하면 CAS 연산 시점의 값을 기준으로 계산하므로, 이 메서드가 실제로 생성한 값을 정확히 반환할 수 있습니다.
     * @param atomicInteger
     * @return getValue + 1
     */
    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get();
            // sleep(100);
            log("getValue: " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result: " + result);
        }
        while (!result); // CAS 연산이 성공할 때까지 반복

        // return atomicInteger.get(); // atomicInteger.get() 하지 않는 이유 <- 다른 스레드가 값을 변경할 수 있기 때문
        return getValue + 1;
    }
}
