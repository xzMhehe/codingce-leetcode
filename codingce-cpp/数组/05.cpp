//
// Created by mxz on 2022/12/25.
//

#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    /**
     * 在 C++ 语言中， string 被设计成「可变」的类型，因此可以在不新建字符串的情况下实现原地修改。
     * 由于需要将空格替换为 "%20" ，字符串的总字符数增加，因此需要扩展原字符串 s 的长度，
     * 计算公式为：新字符串长度 = 原字符串长度 + 2 * 空格个数
     *
     * @param s
     * @return
     */
    string replaceSpace(string s) {
        int count = 0, len = s.size();
        // 统计空格数量
        for (char c: s) {
            if (c == ' ') count++;
        }
        // 修改 s 长度
        s.resize(len + 2 * count);
        // 倒序遍历修改
        for (int i = len - 1, j = s.size() - 1; i >= 0 && i < j; i--, j--) {
            if (s[i] != ' ')
                s[j] = s[i];
            else {
                s[j - 2] = '%';
                s[j - 1] = '2';
                s[j] = '0';
                j -= 2;
            }
        }
        return s;
    }
};

int main() {
    string str = "1 2 3 3";
    cout << (new Solution())->replaceSpace(str) << endl;

    return 0;
}