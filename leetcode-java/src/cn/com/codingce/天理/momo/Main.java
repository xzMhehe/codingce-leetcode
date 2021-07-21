package cn.com.codingce.天理.momo;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        String s = ",one&two,three%%%%%%aaaaa";

        Main main = new Main();
        System.out.println(main.split(s, new char[]{',', '&', '%'}));

    }

    public ArrayList<String> split(String source, char[] c) {
        ArrayList<String> list = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < source.length(); i++) {
            for (char one : c) {
                if (one == source.charAt(i) && i == 0) {
                    j++;
                    break;
                }

                if (one == source.charAt(i) && i < source.length() - 1) {
                    list.add(source.substring(j, i));
                    j = i + 1;
                }
            }
        }
        list.add(source.substring(j));

        list.removeAll(Collections.singleton(""));

        return list;
    }
}