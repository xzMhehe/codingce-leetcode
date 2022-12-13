//
// Created by inke on 2022/12/9.
//
#include <iostream>
#include <vector>
#include <set>

using namespace std;

class Solution {
public:
    /**
     * 有序集合
     *
     * @param nums
     * @return
     */
    int thirdMax(vector<int> &nums) {
        /// 能比较大小的set集合 默认从小到大
        set<int> s;
        for (int num : nums) {
            s.insert(num);
            if (s.size() > 3) {
                /// 移除最小的
                s.erase(s.begin());
            }
        }
        return s.size() == 3 ? *s.begin() : *s.rbegin();
    }

    /**
     * 排序
     *
     * @param nums
     * @return
     */
    int thirdMax2(vector<int> &nums) {
        /// 数组从大到小排序
        sort(nums.begin(), nums.end(), greater<int>());
        /// 数组从小到大
        //sort(nums.begin(), nums.end(), less<int>());
        /// 从头开始遍历数组，1，2，5，6，8，9
        for (int i = 1, diff = 1; i < nums.size(); ++i) {
            if (nums[i] != nums[i - 1] && ++diff == 3) { // 此时 nums[i] 就是第三大的数
                return nums[i];
            }
        }
        return nums[0];
    }
};

int main() {

    vector<int> temp = {1, 2, 8, 5, 6, 9};

    cout << (new Solution())->thirdMax2(temp) << endl;

    return 0;
}
