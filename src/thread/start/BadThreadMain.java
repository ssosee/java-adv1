package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() started");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": helloThread start 호출 전");
        helloThread.run(); // run() 메서드를 직접 호출 -> Main Thread에서 실행됨
        System.out.println(Thread.currentThread().getName() + ": helloThread start 호출 후");

        System.out.println(Thread.currentThread().getName() + ": main() ended");
    }
}
