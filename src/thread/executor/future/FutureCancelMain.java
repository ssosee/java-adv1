package thread.executor.future;

import static util.ThreadUtils.sleep;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import util.MyLogger;

public class FutureCancelMain {
    private static boolean mayInterruptIfRunning = true;
    // private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        MyLogger.log("future.state: " + future.state());

        sleep(3000);

        MyLogger.log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        MyLogger.log("cancel(" + mayInterruptIfRunning + ") result: " + cancelResult);

        try {
            MyLogger.log("Future result: "+ future.get());
        } catch (CancellationException e) {
            MyLogger.log("Future는 이미 취소되었습니다.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() {
            try {
                for (int i = 0; i < 10; i++) {
                    MyLogger.log("i = " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                MyLogger.log("인터럽트 발생");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}
