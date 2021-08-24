package cn.com.codingce.我的.天理.juc;


import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalTest {
    public static void main(String[] args) {
        new AtomicInteger();
        List<Integer> list = new Stack<>();
        list = Collections.synchronizedList(list);
        list.add(1);
        Set<String> set = new LinkedHashSet<>();
        set.add("aaa");
        System.out.println(set.toString());
        new Hashtable<>();
        LongAdder longAdder = new LongAdder();
        List<Integer> list1 = new CopyOnWriteArrayList<>();
    }
}
