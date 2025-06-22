package thread.bounded;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueV3 implements BoundedQueue {
    private final Queue<String>  queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] queue is full, producer call wait()");
            try {
                wait(); // RUNNABLE -> WAITING (락 반납)
                log("[put] wake up producer");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("[put] producer saves data, call notify()");
        notify(); // WAITING -> BLOCKED
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] queue is empty, consumer call wait()");
            try {
                wait();
                log("[take] wake up consumer");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String data = queue.poll();
        log("[take] consumer get data, call notify()");
        notify(); // WAITING -> BLOCKED

        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
