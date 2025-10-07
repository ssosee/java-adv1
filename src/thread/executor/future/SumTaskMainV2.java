package thread.executor.future;

import static util.MyLogger.log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import thread.executor.future.CallableMainV1.MyCallable;
import util.MyLogger;

public class SumTaskMainV2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);

        MyLogger.log("submit 호출 [논블로킹] 메소드");
        Future<Integer> future1 = es.submit(new SumTask(1, 50));
        Future<Integer> future2 = es.submit(new SumTask(51, 100));

        log("future1 = "+future1);
        log("future2 = "+future2);

        MyLogger.log("future.get() [블로킹] 메소드 호출 시작 -> main 스레드 WAITING");
        Integer result1 = future1.get();
        Integer result2 = future2.get();
        MyLogger.log("future.get() [블로킹] 메소드 호출 완료 -> main 스레드 RUNNABLE");

        log("result1 = "+result1);
        log("result2 = "+result2);

        int sumAll = result1 + result2;
        MyLogger.log("sumAll = " + sumAll);
        es.close();
    }

    static class SumTask implements Callable<Integer> {

        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws InterruptedException {
            MyLogger.log("작업 시작");
            Thread.sleep(2000);

            int sum = 0;
            for(int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            MyLogger.log("작업 완료");
            return sum;
        }
    }
}
