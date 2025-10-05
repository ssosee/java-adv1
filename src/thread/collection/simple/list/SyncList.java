package thread.collection.simple.list;

import static util.ThreadUtils.sleep;

import java.util.Arrays;

public class SyncList implements SimpleList {

    private final int DEFAULT_CAPACITY = 5;
    private int size = 0;
    private Object[] elements;

    public SyncList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public synchronized void add(Object e) {
        elements[size] = e;
        sleep(100); // 멀티스레드 문제를 쉽게 확인하는 코드
        size++;
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized Object get(int index) {
        return elements[index];
    }

    @Override
    public synchronized String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) + " size=" + size +", capacity=" + elements.length;
    }
}
