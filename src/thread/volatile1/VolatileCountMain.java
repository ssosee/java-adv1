package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();

        Thread thread1 = new Thread(myTask, "work-1");

        thread1.start();

        sleep(1000);
        myTask.flag = false;
        log("myTask.flag = " + myTask.flag + ", myTask.count = " + myTask.count + " in main()");
    }

    static class MyTask implements Runnable {

//        boolean flag = true;
//        long count = 0;

        volatile boolean flag = true;
        volatile long count = 0;

        @Override
        public void run() {
            while (flag) {
                count++;
                if (count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }
}
