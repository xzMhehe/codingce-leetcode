package cn.com.codingce.数组与矩阵.杨辉三角t;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * 数学
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret.get(rowIndex);
    }
}