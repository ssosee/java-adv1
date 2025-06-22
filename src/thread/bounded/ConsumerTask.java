package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable {

    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[try consume]     ? <- " + queue);
        String data = queue.take();
        log("[complete consume]     " + data + " <- " + queue);
    }
}
