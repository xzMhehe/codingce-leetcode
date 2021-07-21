package cn.com.codingce.数组.ssz;

/**
 * @author williamma
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maximumSwap(2736));

    }


    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        char[] charArray = s.toCharArray();
        int max = num;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                swap(charArray, i, j);
                max = Math.max(max, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return max;
    }

    private void swap(char[] charArray, int index1, int index2) {
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
    }
}
