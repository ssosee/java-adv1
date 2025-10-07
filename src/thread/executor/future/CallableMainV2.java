package thread.executor.future;

import static util.ThreadUtils.sleep;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import util.MyLogger;

public class CallableMainV2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable task = new MyCallable();
        ExecutorService es = Executors.newFixedThreadPool(1);

        MyLogger.log("submit 호출 [논블로킹] 메소드");
        Future<Integer> future = es.submit(task);
        MyLogger.log("future 즉시 반환, future = "+future); // java.util.concurrent.FutureTask@6576fe71[Not completed, task = thread.executor.future.CallableMainV2$MyCallable@7eda2dbb]

        MyLogger.log("future.get() [블로킹] 메소드 호출 시작 -> main 스레드 WAITING");
        Integer result = future.get();
        MyLogger.log("future.get() [블로킹] 메소드 호출 완료 -> main 스레드 RUNNABLE");

        MyLogger.log("result value = " + result);
        MyLogger.log("future 완료, future = "+future); // java.util.concurrent.FutureTask@6576fe71[Completed normally]
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
