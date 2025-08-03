package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {
    public static void main(String[] args) {
        //SpinLock spinLock = new SpinlockBad();
        SpinLock spinLock = new SpinLockGood();

        Runnable runnable = () -> {
            spinLock.lock();
            try {
                log("비지니스 로직 수행");
            } finally {
                spinLock.unlock();
            }
        };

        Thread thread1 = new Thread(runnable, "Thread-1");
        Thread thread2 = new Thread(runnable, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
