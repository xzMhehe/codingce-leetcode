package cn.com.codingce.数组与矩阵.随做;


public class Solution {

    public void reverseArr(char[] arr, int i, int j) {
        // 双指针左右滑动
        int left = i;
        int right = j;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public String trans(String s, int n) {
        char[] str = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char temp = str[i];
            if (temp >= 'A' && temp <= 'Z') str[i] = (char) (str[i] - 'A' + 'a');
            if (temp >= 'a' && temp <= 'z') str[i] = (char) (str[i] - 'a' + 'A');
        }

        int l = 0, r = n;
        char temp = 0;
        // 整体翻转
        reverseArr(str, 0, n - 1);
        // 此时每个单词也逆序了，需要根据空格将每个单元重新反转回去
        for (int i = 0; i < n; i++) {
            if (str[i] == ' ') continue;
            // 双指针，左指针的单词起始位置，右指针为单词末后一个位置
            int j = i;
            while (j < n && str[j] != ' ') j++;
            reverseArr(str, i, j - 1);
            i = j; // 更新左边界值
        }
        return new String(str);
    }
}
