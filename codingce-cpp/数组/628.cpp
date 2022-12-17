//
// Created by inke on 2022/12/9.
//

/**
 * 628. 三个数的最大乘积
 * 给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 项目地址：
 * [https://gitee.com/codingce/codingce-leetcode](https://gitee.com/codingce/codingce-leetcode)
 * [https://github.com/xzMhehe/codingce-leetcode](https://github.com/xzMhehe/codingce-leetcode)
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    /**
     * 排序
     * 全是负数；全是正数；有负数有正数
     * 最大是最大的三个数；最大的三个数；最大的三个数、最小的两个负数*最大的数
     *
     * 时间复杂度：O(NlogN) 其中 NN 为数组长度。排序需要 O(NlogN) 的时间。
     * 空间复杂度：O(logN)，主要为排序的空间开销。
     * @param nums
     * @return
     */
    int maximumProduct(vector<int> &nums) {
        sort(nums.begin(), nums.end());

        int n = nums.size();

        return max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /**
     * 线性扫描
     * 只要求出数组中最大的三个数以及最小的两个数，可以不用排序，用线性扫描直接得出这五个数。
     * 时间复杂度：O(N)，其中 NN 为数组长度。我们仅需遍历数组一次。
     * 空间复杂度：O(1)。
     *
     * @param nums
     * @return
     */
    int maximumProduct2(vector<int> &nums) {
        // 最小和第二小的 INT_MAX
        int min1 = INT_MAX, min2 = INT_MAX;
        // 最大的、第二大的和第三大的 INT_MIN
        int max1 = INT_MIN, max2 = INT_MIN, max3 = INT_MIN;
        for (int num : nums) {
            // 处理最小的
            if (num < min1) {
                // 顺序不要错了
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }

            // 处理最大的
            if (num > max1) {
                // 顺序不要错了
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                // 顺序不要错了
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }

        return max(min1 * min2 * max1, max1 * max2 * max3);
    }

};

int main() {
    vector<int> temp = {2, -2, 1, 3, 8, -9, -10, 10};
    ///
    cout << (new Solution())->maximumProduct2(temp) << endl;

    return 0;
}