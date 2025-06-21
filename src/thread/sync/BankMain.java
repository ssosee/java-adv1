package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankMain {
    public static void main(String[] args) throws InterruptedException {
        // BankAccount bankAccount = new BankAccountV1(1000);
        // BankAccount bankAccount = new BankAccountV2(1000);
        // BankAccount bankAccount = new BankAccountV3(1000);
        // BankAccount bankAccount = new BankAccountV4(1000);
        // BankAccount bankAccount = new BankAccountV5(1000);
        BankAccount bankAccount = new BankAccountV6(1000);

        Thread thread1 = new Thread(new WithdrawTask(bankAccount, 800), "t1");
        Thread thread2 = new Thread(new WithdrawTask(bankAccount, 800), "t2");

        thread1.start();
        thread2.start();

        sleep(1000); // Simulate some delay before starting the second thread
        log("t1 status: " + thread1.getState());
        log("t2 status: " + thread2.getState());

        thread1.join();
        thread2.join();

        log("최종 잔액: " + bankAccount.getBalance());
    }
}
