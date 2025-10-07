package thread.executor.future;

import static util.MyLogger.log;

import util.MyLogger;

public class SumTaskMainV1 {
    public static void main(String[] args) throws InterruptedException {
        SumTask sumTask1 = new SumTask(1, 50);
        SumTask sumTask2 = new SumTask(51, 100);

        Thread thread1 = new Thread(sumTask1, "thread-1");
        Thread thread2 = new Thread(sumTask2, "thread-2");
        thread1.start();
        thread2.start();

        log("join() - main 스레드가 thread-1, thread-2의 작업이 끝날 때까지 기다림");
        thread1.join();
        thread2.join();
        log("thread-1, thread-2 대기 완료");

        log("task1.result = "+sumTask1.result);
        log("task2.result = "+sumTask2.result);

        int sumAll = sumTask1.result + sumTask2.result;
        log("sumAll = " + sumAll);
        log("main 종료");
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }


        @Override
        public void run() {
            log("작업 시작");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;

            log("작업 완료");
        }
    }
}
