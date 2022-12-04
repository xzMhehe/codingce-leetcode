//
// Created by mxz on 2022/12/4.
//
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int findMaxConsecutiveOnes(vector<int> &nums) {
        int s = nums.size();
        int c = 0;
        int r = 0;
        for (int i = 0; i < s; ++i) {
            if (nums[i] == 1) {
                c++;
            }
            if (nums[i] == 0 || i == s - 1) {
                r = max(c, r);
                c = 0;
            }
        }

        return r;
    }

    /**
     * 设定连个指针first和second.为了确定什么时候开始计数，需要定义一个标志位flag；
     * 先保持first不变，移动second,当遇到第一个1的时候，将second的下标赋值给first,并将标志位设置为1,即fisrt=second,flag= 1;
     * 当Second遇到0并且flag = 1 的时候，flag=1是为了保证是在元素为1的滑动窗口中。此时，计算窗口中元素1的个数，然后将flag = 0;
     * 当遍历完元素的时候，为了避免最后一个元素没有被处理，因此在循环结束是需要在计算下元素的个数。
     * @param nums
     * @return
     */
    int findMaxConsecutiveOnes1(vector<int> &nums) {
        int first = 0, second = 0, ans = 0, flag = 0;
        /// 0, 1, 1, 0
        while (second < nums.size()) {
            if (nums[second] == 1 && flag == 0) {
                first = second;
                flag = 1;
            } else if (nums[second] == 0 && flag) {
                ans = max(ans, second - first);
                flag = 0;
            }
            second++;
        }
        if (nums[first] == 1 && flag) {
            ans = max(ans, second - first);
        }

        return ans;
    }
};

int main() {
    Solution solution;
    /// vector 向量（Vector）是一个封装了动态大小数组的顺序容器（Sequence Container），顺序序列，动态数组，能够感知内存分配器的（Allocator-aware）
    /// vector<int> list;  vector<int> list1 = list2, vector<int> list2(list); vector<int> list = {1, 2, 3};
    /// vector<int> list2(list.begin()+2,list.end()-1); vector<int> list(7); vector<int> list5(7, 3);
    vector<int> vecTemp = {1, 1, 0, 1, 1, 1};
    vector<int> list2(vecTemp.begin() + 2, vecTemp.end() - 1);
    vector<int>::iterator it;
    for (it = list2.begin(); it != list2.end(); it++) {
        cout << *it << endl;
    }
    for (int i = 0; i < list2.size(); ++i) {

    }
    cout << "result: " << solution.findMaxConsecutiveOnes1(list2) << endl;
    return 0;
}
