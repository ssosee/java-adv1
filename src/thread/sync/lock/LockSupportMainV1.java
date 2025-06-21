package thread.sync.lock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.locks.LockSupport;

public class LockSupportMainV1 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        sleep(100); // 잠시 대기하여 thread1이 park 상태에 들어가도록 함
        log("Thread-1 상태: " + thread1.getState());
        // LockSupport.unpark(thread1);
        thread1.interrupt();
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태:"+ Thread.currentThread().isInterrupted());
        }
    }
}
