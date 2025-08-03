package thread.cas.spinlock;

public interface SpinLock {
    void lock();
    void unlock();
}
