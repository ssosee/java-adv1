package thread.executor.future;

import static util.ThreadUtils.sleep;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import thread.executor.ExecutorUtils;
import util.MyLogger;

public class CallableMainV1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable task = new MyCallable();
        ExecutorService es = Executors.newFixedThreadPool(1);

        Future<Integer> future = es.submit(task);
        Integer result = future.get();

        MyLogger.log("result value = " + result);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            MyLogger.log("Callable 실행");
            sleep(2000);

            int value = new Random().nextInt(10);

            MyLogger.log("create value = " + value);
            MyLogger.log("Callable 완료");

            return value;
        }
    }
}
