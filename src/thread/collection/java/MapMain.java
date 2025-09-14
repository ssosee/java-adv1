package thread.collection.java;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMain {
    public static void main(String[] args) {
        Map<Integer, String> map1 = new ConcurrentHashMap<>();
        // 입력 순서 보장 X
        map1.put(3, "data3");
        map1.put(2, "data2");
        map1.put(1, "data1");
        System.out.println("map1 = " + map1);

        Map<Integer, String> map2 = new ConcurrentHashMap<>();
        // 순서 보장 O
        map2.put(2, "data2");
        map2.put(1, "data1");
        map2.put(3, "data3");
        System.out.println("map2 = " + map2);
    }
}
