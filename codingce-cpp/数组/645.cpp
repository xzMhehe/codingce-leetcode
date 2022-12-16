//
// Created by mxz on 2022/12/16.
//
#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

/**
 * 集合 s 包含从 1 到n的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 */
class Solution {
public:
    /**
     * 排序
     * @param nums
     * @return
     */
    vector<int> findErrorNums(vector<int> &nums) {
        vector<int> errorNums(2);
        int n = nums.size();
        sort(nums.begin(), nums.end());
        int prev = 0;
        for (int i = 0; i < n; ++i) {
            int curr = nums[i];
            if (curr == prev) {
                errorNums[0] = prev;
            } else if (curr - prev > 1) {
                errorNums[1] = prev + 1;
            }
            prev = curr;
        }
        if (nums[n - 1] != n) {
            errorNums[1] = n;
        }

        return errorNums;
    }

    vector<int> findErrorNums2(vector<int> &nums) {
        vector<int> errorNums(2);
        int n = nums.size();
        unordered_map<int, int> mp;
        for (auto &num: nums) {
            mp[num]++;
        }
        for (int i = 1; i <= n; i++) {
            int count = mp[i];
            if (count == 2) {
                errorNums[0] = i;
            } else if (count == 0) {
                errorNums[1] = i;
            }
        }
        return errorNums;
    }
};

int main() {
    vector<int> v = {1, 2, 4, 5, 6, 7, 2};
    vector<int> r = (new Solution())->findErrorNums2(v);

    for (int num: r) {
        cout << "r:" << num << endl;
    }

    return 0;
}