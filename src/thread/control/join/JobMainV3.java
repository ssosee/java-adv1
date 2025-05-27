package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JobMainV3 {
    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        log("Waiting for thread-1 and thread-2 to finish...");
        thread1.join();
        thread2.join();
        log("thread-1 and thread-2 finished");

        log("task1.result = "+task1.result);
        log("task2.result = "+task2.result);

        int sumAll = task1.result + task2.result;
        log("sumAll = " + sumAll);

        log("End");
    }

    static class SumTask implements Runnable {
        private int startValue;
        private int endValue;
        private int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("SumTask start");

            sleep(2000);

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            result = sum;

            log("SumTask End");
        }
    }
}
