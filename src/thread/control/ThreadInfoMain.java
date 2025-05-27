package thread.control;

import static util.MyLogger.log;

import thread.start.HelloThread;

public class ThreadInfoMain {
    public static void main(String[] args) {
        // main thread
        Thread mainThread = Thread.currentThread();
        log("mainThread: " + mainThread);
        log("mainThread.threadId: " + mainThread.threadId());
        log("mainThread.getName: " + mainThread.getName());
        log("mainThread.getPriority: " + mainThread.getPriority());
        log("mainThread.threadGroup: " + mainThread.getThreadGroup());
        log("mainThread.getState: " + mainThread.getState());

        // myThread thread
        Thread myThread = new Thread(new HelloThread(), "myThread");
        log("myThread: " + myThread);
        log("myThread.threadId: " + myThread.threadId());
        log("myThread.getName: " + myThread.getName());
        log("myThread.getPriority: " + myThread.getPriority());
        log("myThread.threadGroup: " + myThread.getThreadGroup());
        log("myThread.getState: " + myThread.getState());
    }
}
