package cn.com.codingce.doublepointer.翻转单词顺序列;

/**
 * 先翻转每个单词，再翻转整个字符串。
 * <p>
 * 题目应该有一个隐含条件，就是不能用额外的空间。虽然 Java 的题目输入参数为 String 类型，
 * 需要先创建一个字符数组使得空间复杂度为 O(N)，但是正确的参数类型应该和原来一样，为字符数组，
 * 并且只能使用该字符数组的空间。任何使用了额外空间的解法在面试时都会大打折扣，包括递归解法。
 *
 * @author maxinze
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().ReverseSentence("nowcoder. a am I"));

    }

    public String ReverseSentence(String str) {
        int n = str.length();
        int j = 0, i = 0;
        char[] chars = str.toCharArray();
        while(j <= n) {
            //这条件是 chars[j] == ' '
            if(j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(chars, 0, n - 1);
        return new String(chars);
    }
    private void reverse(char[] c, int i, int j) {
        while (i < j)
            swap(c, i++, j--);
    }

    private void swap(char[] c, int i, int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
