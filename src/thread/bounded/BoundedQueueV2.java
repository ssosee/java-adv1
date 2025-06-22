package thread.bounded;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueV2 implements BoundedQueue {
    private final Queue<String>  queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] queue is full, wait producer");
            sleep(1000);
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] queue is empty, wait producer");
            sleep(1000);
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
