package cn.com.codingce.工具;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class listTool {

    public static void main(String[] args) {
        // 循环遍历List的4中方法
        List<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");
        strList.add("ccc");
        // 1. 普通遍历方式
        for (int i = 0; i < strList.size(); i++) {
            System.out.println(strList.get(i));
        }
        // 2.增强的for循环
        for (String str : strList) {
            System.out.println(str);
        }
        // 3. 使用Iterator迭代器
        Iterator<String> it = strList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            System.out.println(str);
        }
        // 4. java8 Lambda方式
        // strList.forEach(System.out::println);//和下面的写法等价
        strList.forEach(str -> {
            System.out.println(str);
        });
    }
}
