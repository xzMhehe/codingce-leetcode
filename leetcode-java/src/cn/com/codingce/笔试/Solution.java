package cn.com.codingce.笔试;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.NNAplusB(2, 3));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param a int整型
     * @param b int整型
     * @return long长整型
     */
    public long NNAplusB(int a, int b) {
        // write code here
        StringBuffer stringBufferA = new StringBuffer(a);
        for (int i = 0; i < b; i++) {
            stringBufferA.append(a);
        }

        StringBuffer stringBufferB = new StringBuffer(b);
        for (int i = 0; i < a; i++) {
            stringBufferB.append(b);
        }

        return Integer.parseUnsignedInt(String.valueOf(stringBufferA)) + Integer.parseUnsignedInt(String.valueOf(stringBufferB));
    }

}
