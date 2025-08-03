package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("초기 값: " + atomicInteger.get());

        // 기대하는 값이 0일 때만 1로 변경
        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println("compareAndSet(0, 1) 결과: " + result1);
        System.out.println("현재 값: " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1);
        System.out.println("compareAndSet(0, 1) 결과: " + result2);
        System.out.println("현재 값: " + atomicInteger.get());
    }
}
