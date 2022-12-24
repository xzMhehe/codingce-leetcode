//
// Created by mxz on 2022/12/24.
//
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> maxSubsequence(vector<int> &nums, int k) {
        vector<pair<int, int>> idx;
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i) {
            const auto &num = nums[i];
            //idx.push_back(pair<int, int>(i, num));
            idx.emplace_back(i, num);
        }
        // 按照数值nums[idx]从大到小排序
        sort(idx.begin(), idx.end(), [](pair<int, int> a, pair<int, int> b) { return a.second > b.second; });
        // 按照索引idx从小到大进行排列
        sort(idx.begin(), idx.begin() + k, [](pair<int, int> a, pair<int, int> b) { return a.first < b.first; });
        // 复制结果
        for (int i = 0; i < k; ++i) {
            ans.push_back(idx[i].second);
        }
        return ans;
    }
};


int main() {
    vector<int> ret = {2, 3, -1, 4};
    for (const auto &item: (new Solution)->maxSubsequence(ret, 2)) {
        cout << item << endl;
    }

}