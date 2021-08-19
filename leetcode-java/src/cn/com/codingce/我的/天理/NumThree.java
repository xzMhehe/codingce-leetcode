package cn.com.codingce.我的.天理;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 中k华人民y共和y国abcdf合同y法
 * 要替换第二个y为MM那么思路
 * 1、记录要替换y的index位置，然后插入，replace执行，但是繁琐之处是对应的位置的记录index，涉及到[2.String拼接的思路完成:
 * String a="中k华人民y共和"; String b=“国abcdf合同y法”;  b的创建省去了第一个要替换的y字符串
 * 然后显示的结果是syso(a+"MM"+b); 也是完成了一个替换的过程，但是替换的操作中没有replace的。
 * 而且注意如果替换的位置是1，那么直接可以replaceFirst，无需记录位置了。
 *
 * @author mxz
 */
public class NumThree {
    public static void main(String[] args) {
        System.out.println("中k华人民y共和y国abcdf合同y法" + "\t\t\t原");
        replace2("中k华人民y共和y国abcdf合同y法");
        replace1("中k华人民y共和y国abcdf合同y法");
        replace3("中k华人民y共和y国abcdf合同y法");
        System.out.println();
        replace4("中k华人民y共和y国abcdf合同y法");
        System.out.println();
        replace5("中k华人民y共和y国abcdf合同y法");
    }

    public static void replace1(String inputStr) {
        StringBuffer str = new StringBuffer(inputStr);
        int num = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == 'y') {
                ++num;
            }
            if (num == 2) {
                str.replace(i, i + 1, "MM");
                break;
            }
        }
        System.out.println(str);
    }

    public static void replace2(String str) {
        String begin = "";
        String end = "";
        for (int i = 0; i < str.length(); ++i) {

            if (str.charAt(i) == 'y') {
                begin = str.substring(0, i + 1);
                end = str.substring(i + 1);
                break;
            }
        }
        System.out.println(new StringBuffer().append(begin).append(end.replaceFirst("y", "MM")));
    }

    public static void replace3(String str) {
        char[] charArray = str.toCharArray();
        ArrayList<Character> list = new ArrayList<>(str.length());
        for (char c : charArray) {
            list.add(c);
        }
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 'y') {
                ++num;
            }
            if (num == 2) {
                list.set(i, 'M');
                list.add(i, 'M');
                break;
            }
        }

        list.forEach(System.out::print);
    }

    public static void replace4(String str) {
        char[] charArray = str.toCharArray();
        ArrayList<Character> list = new ArrayList<>(str.length());
        for (char c : charArray) {
            list.add(c);
        }
        list.stream().filter(e -> 'y' != e
        ).collect(Collectors.toList()).forEach(System.out::print);
    }

    public static void replace5(String str) {
        Stack<Character> stack = new Stack<>();
        int control = 0;
        for (int i = 0; i < str.length(); i++) {
            if ('y' == stack.push(str.charAt(i))) {
                ++control;
            }
            if (control == 2) {
                stack.pop();
                stack.push('M');
                stack.push('M');
                ++control;
            }
        }
        stack.forEach(System.out::print);
    }

}
