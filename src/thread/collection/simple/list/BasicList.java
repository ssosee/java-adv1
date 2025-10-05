package thread.collection.simple.list;

import static util.ThreadUtils.sleep;

import java.util.Arrays;

public class BasicList implements SimpleList {

    private final int DEFAULT_CAPACITY = 5;
    private int size = 0;
    private Object[] elements;

    public BasicList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(Object e) {
        elements[size] = e;
        sleep(100); // 멀티스레드 문제를 쉽게 확인하는 코드
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size)) + " size=" + size +", capacity=" + elements.length;
    }
}
