package thread.start;

public class HelloThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() started");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": helloThread start 호출 전");
        helloThread.start();
        System.out.println(Thread.currentThread().getName() + ": helloThread start 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() ended");
    }
}
