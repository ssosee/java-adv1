package thread.start.test;

import static util.MyLogger.log;

import thread.start.test.StartTest2Main.CountRunnable;

public class StartTest3Main {
    public static void main(String[] args) {
        Runnable countRunnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i < 6; i++) {
                    try {
                        log("value: " + i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread thread = new Thread(countRunnable, "counter");
        thread.start();
    }
}
