package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {
    private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작: "+ getClass().getSimpleName());

        log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
        // 잔고가 출금 금액보다 적으면
        if(balance < amount) {
            log("[검증 실패] 출금액:"+ amount + ", 잔액:" + balance);
            return false; // 잔고 부족
        }

        log("[검증 성공] 출금액: " + amount + ", 잔액: " + balance);
        sleep(1000); // Simulate transaction delay
        balance -= amount; // 출금
        log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);

        log("거래 종료 ");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return this.balance;
    }
}
