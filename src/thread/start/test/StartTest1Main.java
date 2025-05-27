package thread.start.test;

import static util.MyLogger.log;

public class StartTest1Main {
    public static void main(String[] args) {
        CountThread countThread = new CountThread();
        countThread.start();
    }

    static class CountThread extends Thread {
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
    }
}
