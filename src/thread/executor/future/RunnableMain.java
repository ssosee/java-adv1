package thread.executor.future;

import static util.ThreadUtils.sleep;

import java.util.Random;
import util.MyLogger;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int value = task.value;
        MyLogger.log("result value = " + value);
    }

    static class MyRunnable implements Runnable {

        int value;

        @Override
        public void run() {
            MyLogger.log("Runnable 실행");
            sleep(2000);
            value = new Random().nextInt(10);
            MyLogger.log("value = " + value);
            MyLogger.log("Runnable 완료");
        }
    }
}
