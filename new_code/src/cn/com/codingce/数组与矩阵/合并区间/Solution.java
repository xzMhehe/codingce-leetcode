package cn.com.codingce.数组与矩阵.合并区间;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.nowcoder.com/practice/69f4e5b7ad284a478777cb2a17fb5e6a
 * <p>
 * 输入：
 * [[10,30],[20,60],[80,100],[150,180]]
 * 返回值：
 * [[10,60],[80,100],[150,180]]
 * <p>
 * 输入：
 * [[0,10],[10,20]]
 * 返回值：
 * [[0,20]]
 *
 * @author mxz
 */
public class Solution {

    public static void main(String[] args) {

    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>(); //结果集
        // 1. 首先对每个区间的起点进行排序(递增)
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        int len = intervals.size();
        if (len == 0) return res;

        // [[10,30],[20,60],[80,100],[150,180]]
        // 2. 遍历每一个区间。遍历过程中合并所有重合的区间
        for (int i = 0; i < len; i++) {
            int start = intervals.get(i).start;
            int end = intervals.get(i).end;
            // 将所有重叠的区间合并(即下一区间的开始点，在上一区间内)
            while (i < len - 1 && intervals.get(i + 1).start <= end) {
                // 更新开始和结束点
                start = Math.min(start, intervals.get(i + 1).start); // 开始取最小
                end = Math.max(end, intervals.get(i + 1).end);// 结束取最大
                i++; //继续遍历下一个区间是否重叠
            }
            res.add(new Interval(start, end)); // 将合并后的区间加入结果集
        }
        return res;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

}
