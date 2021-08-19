package cn.com.codingce.我的.天理.juc;

import java.util.HashMap;
import java.util.Map;

public class TestOne {
    public static void main(String[] args) {
        // 获取cpu的核数
        // CPU 密集型，IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Test");
        System.out.println(map.get(1));
    }
}