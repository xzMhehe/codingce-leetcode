# 算法题

## 二分查找

```java
package cn.com.codingce.二分查找;

public class Solution {

    public static void main(String[] args) {
        System.out.println(binSearchD(new int[]{2, 5, 6, 8, 10}, 0, 5, 1));
    }

    /**
     * 非递归
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binSearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ret = (l + r) >> 1;
        while (l < r) {
            if (nums[ret] == target) {
                return 1;
            } else if (nums[ret] > target) {
                r = ret;
                ret = (r + l) / 2;
            } else {
                l = ret;
                ret = (r + l) / 2;
            }
        }
        return 0;
    }

    /**
     * 递归
     *
     * @param nums
     * @param l
     * @param r
     * @param target
     * @return
     */
    public static int binSearchD(int[] nums, int l, int r, int target) {
        int ret = (l + r) >> 1;
        if (nums[ret] == target) {
            return 1;
        }
        if (l >= r) {
            return -1;
        }
        if (nums[ret] > target) {
            return binSearchD(nums, l, ret, target);
        } else {
            return binSearchD(nums, ret, r, target);
        }
    }
}
```



## 数组

### 最长公共前缀

编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。

```text
输入: ["flower","flow","flight"]
输出: "fl"
```

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
        return "";
    }
    String ret = strs[0];
    int len = strs.length;
    for (int i = 0; i < len; i++) {
        ret = longestCommonPrefix(ret, strs[i]);
        if (ret.length() == 0) {
            break;
        }
    }
    return ret;
}

public String longestCommonPrefix(String str1, String str2) {
    int len = Math.min(str1.length(), str2.length());
    int index = 0;
    while (index < len && str1.charAt(index) == str2.charAt(index)) {
        index++;
    }
    return str1.substring(0, index);
}
```



```java
public String replaceSpace(String[] strs) {
    StringBuilder ret = new StringBuilder();
  	// 排序！ ["flight", "flow", "flower"] 比较第一个和最后一个
    Arrays.sort(strs);
    int l = strs[0].length(), r = strs[strs.length - 1].length();
    int l1 = 0, r1  = 0;
    while (l1 < l && r1 < r) {
        if (strs[0].charAt(l1) == strs[strs.length - 1].charAt(l1)) {
            ret.append(strs[0].charAt(l1));
            l1++;
            r1++;
        } else {
            break;
        }
    }
    return ret.toString();
}
```

### 最长回文子串

最长回文子串 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。

```text
输入: "babad"
输出: "bab"
注意: "aba"也是一个有效答案。

输入: "cbbd"
输出: "bb"
```

以某个元素为中心，分别计算偶数长度的回文最大长度和奇数长度的回文最大长度。

```java
class Solution {
    private int index, len;

    public String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            PalindromeHelper(s, i, i);
            PalindromeHelper(s, i, i + 1);
        }
        return s.substring(index, index + len);
    }

    public void PalindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }
}
```



### 验证回文串

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 说明：本题中，我们将空字符串定义为有效的回文串。

```text
输入: "A man, a plan, a canal: Panama"
输出: true

输入: "race a car"
输出: false
```



```java
public boolean isPalindrome(String s) {
    if (s.length() == 0) {
        return true;
    }
    int l = 0, r = s.length() - 1;
    while (l < r) {
        // 字符不是字母和数字的情况
        if (!Character.isLetterOrDigit(s.charAt(l))) {
            l++;
        } else if (!Character.isLetterOrDigit(s.charAt(r))) {
            // 字符不是字母和数字的情况
            r--;
        } else {
            // 判断二者是否相等. toLowerCase 强转成小写字母
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                return false;
            l++;
            r--;
        }
    }

    return true;
}
```

### 括号匹配深度



### 二维数组查找

在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

这一道题还是比较简单的，我们需要考虑的是如何做，效率最快。这里有一种很好理解的思路：

矩阵是有序的，从左下角来看，**向上数字递减，向右数字递增**， 因此从左下角开始查找，当要查找数字比左下角数字大时。右移 要查找数字比左下角数字小时，上移。这样找的速度最快。

```java
package cn.com.codingce.数组与矩阵.二维数组中的查找;

/**
 * 4. 二维数组中的查找
 *
 * @author inke219223m
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().Find(2, new int[][]{{1, 1}}));
    }

    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int rows = array.length, cols = array[0].length;
        int r = 0, c = cols - 1;
        while(r <= rows - 1 && c >= 0) {
            if(target == array[r][c]) {
                return true;
            } else if(target > array[r][c]) {
                r++;
            } else {
                c--;
            }

        }
        return false;
    }
}
```



## 栈队列堆

### 检查符号是否成对出现

```java
/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断该字符串是否有效。有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。比如 "()"、"()[]{}"、"{[]}" 都是有效字符串，而 "(]" 、"([)]" 则不是。
 *
 * @param str
 * @return
 */
public boolean isValid(String str) {
    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put('}', '{');
    map.put(']', '[');
    Stack<Character> stack = new Stack<>();
    char[] chars = str.toCharArray();
    for (Character c : chars) {
        if (map.containsKey(c)) {
            char topElement = stack.empty() ? '#' : stack.pop();
            if (topElement != map.get(c)) {
                return false;
            }
        } else {
            stack.push(c);
        }
    }
    return stack.isEmpty();
}
```



### 反转字符串

```java
public String reverseStr(String str) {
    Stack<Character> stack = new Stack<>();
    char[] chars = str.toCharArray();
    for (Character c : chars) {
        stack.push(c);
    }
    StringBuilder ret = new StringBuilder();
    int k = 0;
    while (!stack.isEmpty()) {
        char c = stack.pop();
        ret.append(c);
        chars[k++] = c;
    }

    //return ret.toString();
    return String.copyValueOf(chars);
}
```



## 最小的K个数

```java
public int[] getLeastNumbers(int[] arr, int k) {
    if (k > arr.length || k == 0) {
        return new int[]{};
    }
    // 初始化时使用 Lambda 表达式 (o1, o2) -> o2 - o1 来实现大顶堆
    Queue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
    for (int num : arr) {
        priorityQueue.add(num);
        if (priorityQueue.size() > k) {
            priorityQueue.poll();
        }
    }
    return priorityQueue.stream().mapToInt(Integer::valueOf).toArray();
}
```

PriorityQueue常用API：

- add(E e)：添加元素，如果超过队列长度，抛出异常；
- offer(E e)：添加元素，如果超过队列长度返回false；
- remove()：获取下个元素，如果没有抛出异常；
- poll()：获取下个元素，如果没有返回null；
- element()：查看下个元素的内容，如果没有抛异常；
- peek()：查看下个元素的内容，如果没有返回null；







## 链表

### 合并两个排序的链表

剑指offer：输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。

```java
package cn.com.codingce.链表.合并两个排序的链表;

import cn.com.codingce.链表.ListNode;

public class Solution {
    public static void main(String[] args) {

    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val <= list2.val) {
            list1.next = Merge(list1.next, list2);
            return list1;
        }
        list2.next = Merge(list1, list2.next);
        return list2;
    }

    public ListNode Merge1(ListNode list1, ListNode list2) {
        ListNode ret = new ListNode(-1);
        ListNode cur = ret;
        // 条件
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return ret;
    }
}
```

## 反转区间节点

将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转

例如：
给出的链表为 

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202301221510923.png)

返回

![](https://cdn.jsdelivr.net/gh/xzMhehe/StaticFile_CDN/static/img202301221510202.png)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        for(int i=1;i<m;i++) {
            p = p.next;
        }
        
        ListNode pm = p.next;
       
        for(int i=m;i<n;i++) {
            ListNode temp = pm.next;
            pm.next = temp.next;
            temp.next = p.next;
            p.next = temp;
        }
        
        return dummy.next;
    }
}
```



## 动态规划

### 斐波那契数列

```java
package cn.com.codingce.动态规划.斐波那契数列;

/**
 * 求斐波那契数列的第 n 项，n <= 39。
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().Fibonacci2(10000));
    }

    public int Fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public int Fibonacci2(int n) {
        if (n <= 2) {
            return 1;
        }
        int pre1 = 1, pre2 = 2, result = 3;
        for (int i = 3; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }
}
```



### 青蛙跳台阶

```java
package cn.com.codingce.动态规划.跳台阶;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * @author maxinze
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().jumpFloor(5));
    }

    public int jumpFloor(int target) {
        if (target <= 2)
            return target;
        int pre1 = 1, pre2 = 2, result = 0;
        for (int i = 3; i < target; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }
}
```



## 树

### 合并二叉树

已知两颗二叉树，将它们合并成一颗二叉树。合并规则是：都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替。

```java
package cn.com.codingce.树.合并二叉树;

import cn.com.codingce.树.TreeNode;

/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 * }
 */

public class Solution {
    /**
     * @param t1 TreeNode类
     * @param t2 TreeNode类
     * @return TreeNode类
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null || t2 == null) return t1 == null ? t2 : t1;
        // 此时 t1、t2 均不为 null
        // 合并节点的值
        t1.val = t1.val + t2.val;
        // 合并左子树
        t1.left = mergeTrees(t1.left, t2.left);
        // 合并右子树
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
```



