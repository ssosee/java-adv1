package thread.bounded;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayList;

public class BoundedMain {
    public static void main(String[] args) {
        // 1. BoundedQueue 선택
        //BoundedQueue queue = new BoundedQueueV1(2);
        //BoundedQueue queue = new BoundedQueueV2(2);
        BoundedQueue queue = new BoundedQueueV3(2);

        // 2. 생산자, 소비자 실행 순서 선택
        // produceFirst(queue);
        consumeFirst(queue);
    }

    private static void consumeFirst(BoundedQueue queue) {
        log("=== [run consume first] start "+ queue.getClass().getSimpleName()+" ===");
        ArrayList<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("=== [run consume first] finish "+ queue.getClass().getSimpleName()+" ===");
    }

    private static void produceFirst(BoundedQueue queue) {
        log("=== [run produce first] start "+ queue.getClass().getSimpleName()+" ===");
        ArrayList<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        log("=== [run produce first] finish "+ queue.getClass().getSimpleName()+" ===");
    }

    private static void startConsumer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("start consumer");
        for(int i = 1; i < 4; i++) {
            Thread thread = new Thread(new ConsumerTask(queue), "CONSUMER-"+i);
            threads.add(thread);
            thread.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("current state, queue data: "  + queue);
        for(Thread thread : threads) {
            log(thread.getName() + ": "+thread.getState());
        }
    }

    private static void startProducer(BoundedQueue queue, ArrayList<Thread> threads) {
        System.out.println();
        log("strat producer");
        for (int i = 1; i < 4; i++) {
            Thread thread = new Thread(new ProducerTask(queue, "data" + i), "PRODUCER-" + i);
            threads.add(thread);
            thread.start();
            sleep(100);
        }
    }
}
