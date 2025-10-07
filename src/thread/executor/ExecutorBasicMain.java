package thread.executor;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        ThreadPoolExecutor es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        log("== 초기 상태 ==");
        ExecutorUtils.printState(es); // [pool=0, active=0, queuedTask=0, completedTask=0 ]
        // 작업이 들어올 때 마다 corePoolSize(2) 만큼 스레드가 생성되고, 그 이후로는 스레드를 재사용한다.
        es.execute(new RunnableTask("taskA")); // 스레드 생성
        es.execute(new RunnableTask("taskB")); // 스레드 생성
        es.execute(new RunnableTask("taskC")); // 스레드 재사용
        es.execute(new RunnableTask("taskD")); // 스레드 재사용
        log("== 작업 수행 중 ==");
        ExecutorUtils.printState(es); // [pool=2, active=2, queuedTask=2, completedTask=0 ]

        sleep(3000);
        log("== 작업 수행 완료 ==");
        ExecutorUtils.printState(es); // [pool=2, active=0, queuedTask=0, completedTask=4 ]

        es.close();
        log("== shutdown 완료 ==");
        ExecutorUtils.printState(es); // [pool=0, active=0, queuedTask=0, completedTask=4 ]
    }
}
