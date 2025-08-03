package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockGood implements SpinLock {
    private AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        while (true) {
            log("락 획득 시도");
            if(!lock.compareAndSet(false, true)) {
                sleep(100); // 락 획득 후 잠시 대기
                break;
            }
            log("락 획득 실패, 재시도");
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false); // 락을 해제
        log("락 반납");
    }
}
