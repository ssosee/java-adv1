package thread.bounded;

import static util.MyLogger.log;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition();
    private final Condition consumerCond = lock.newCondition();

    private final Queue<String>  queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) {
                log("[put] queue is full, producerCond call await()");
                try {
                    producerCond.await(); // RUNNABLE -> WAITING (락 반납)
                    log("[put] wake up producer");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("[put] producer saves data, consumerCond call signal()");
            consumerCond.signal(); // 대기 중인 소비자 깨움
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] queue is empty, consumerCond call await()");
                try {
                    consumerCond.await(); // RUNNABLE -> WAITING (락 반납)
                    log("[take] wake up consumer");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] consumer get data, producerCond call signal()");
            producerCond.signal(); // 대기 중인 생산자 깨움

            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
