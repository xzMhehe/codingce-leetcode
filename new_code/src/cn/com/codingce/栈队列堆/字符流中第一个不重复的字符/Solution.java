package cn.com.codingce.栈队列堆.字符流中第一个不重复的字符;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "aancbd";
        for(int i = 0; i < str.length(); i++) {
            solution.Insert(str.charAt(i));
            System.out.print(solution.FirstAppearingOnce());
        }
    }

    private int[]cnts = new int[128];
    private Queue<Character> queue = new LinkedList<>();


    //Insert one char from stringstream
    public void Insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        //非空&&数量大于1
        //那么就移除
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}


