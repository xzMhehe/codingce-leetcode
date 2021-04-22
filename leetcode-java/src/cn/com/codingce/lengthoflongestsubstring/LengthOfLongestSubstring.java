package cn.com.codingce.lengthoflongestsubstring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 3. 无重复字符的最长子串    leetcode - 3
 *
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 理解双指针/滑动窗口/移动队列
 *
 * @author mxz
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        // 数组元素顺序排列
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums2 = new int[]{3, 2, 4};
        System.out.println(binarySearch(nums, 5));
        System.out.println();
        Arrays.stream(twoNumAdd(nums, 6)).boxed().collect(Collectors.toList()).forEach(num -> System.out.print(num));
        System.out.println();
        System.out.println("长度：" + lengthOfLongestSubstringT( "a"));

        System.out.println(lengthOfLongestSubstring("aabscssd"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length < 2) return length;

        //双指针，将遇到的字符放进Map，看是不是重复的。
        Map<Character, Integer> map = new HashMap<>();
        char[] array = s.toCharArray();
        int size = 0;

        //窗口左指针
        int left = 0;

        for (int right = 0; right < length; right++) {
            //i是右指针
            if (map.containsKey(array[right])) {
                //如果包含了此元素，说明重复，需要移动左指针
                //窗口不能回退。
                left = Math.max(map.get(array[right]) + 1, left);
            }
            //修改当前元素索引
            map.put(array[right], right);
            //计算长度
            size = Math.max(size, right - left + 1);
        }
        return size;
    }

    public static int lengthOfLongestSubstringT(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static int lengthOfLongestSubstringOne(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int size = 0;
        // 双层for循环
        for (int i = 0; i < chars.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < chars.length; j++) {
                if (set.contains(chars[j])) {
                    break;
                }
                set.add(chars[j]);
                size = set.size() > size ? set.size() : size;
            }
        }

        return size;
    }

    /**
     * 二分查找
     *
     * @param nums   数组
     * @param target 目标值
     * @return 返回目标值位序
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        throw new IllegalArgumentException("没有目标值");
    }

    /**
     * 两数之和
     * 每个数值只能用一次, 所以 left < right 而不是小于等于
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoNumAdd(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target) {
                right -= 1;
            } else if (nums[left] + nums[right] < target) {
                left += 1;
            } else {
                return new int[]{left, right};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
