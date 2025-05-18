package thread.start;

public class ManyThreadMainV1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ManyThreadRunnable());
        Thread thread2 = new Thread(new ManyThreadRunnable());
        Thread thread3 = new Thread(new ManyThreadRunnable());

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
