package cn.com.codingce.天理.juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MySet {
    public static void main(String[] args) {
        MySet mySet = new MySet();
//        mySet.listTest();
//        mySet.setTest();
        mySet.MapTest();
    }

    public void listTest() {
        ArrayList<String> list = new ArrayList<>();
        list.add("后端");
        list.add("码匠");
        list.add("你好");
        list.add("我是");
        list.add("果冻");
        list.add("校园");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void setTest() {
        Set<String> set = new HashSet<>();
        set.add("后端");
        set.add("码匠");
        set.add("你好");
        set.add("我是");
        set.add("果冻");
        set.add("校园");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void MapTest() {
        Map<Object, Object> map = new HashMap<>();
        map.put("你好", " 世界 ");
        map.put(null, "后端");
        Iterator<Object> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
//            System.out.println(iterator.next()); // 获取键
            System.out.println(map.get(iterator.next())); // 获取值
        }
    }
}
