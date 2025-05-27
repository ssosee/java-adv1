package thread.control.join.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinTest1Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");

        t1.start();
        t1.join(); // wait for t1 to finish

        t2.start();
        t2.join(); // wait for t2 to finish

        t3.start();
        t3.join(); // wait for t3 to finish

        System.out.println("All tasks completed.");
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            for(int i = 1; i <= 3; i++) {
                log(i);
                sleep(1000);
            }
        }
    }
}
