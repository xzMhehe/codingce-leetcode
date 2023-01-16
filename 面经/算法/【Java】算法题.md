# 算法题

## 数组

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



