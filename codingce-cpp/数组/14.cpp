//
// Created by mxz on 2022/12/25.
//
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    string longestCommonPrefix(vector<string> &strs) {
        if (strs.empty()) {
            return "";
        }
        string prefix = strs[0];
        int count = strs.size();
        for (int i = 1; i < count; ++i) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.empty()) {
                break;
            }
        }
        return prefix;
    }

    string longestCommonPrefix(const string &str1, const string &str2) {
        int length = min(str1.size(), str2.size());
        int index = 0;
        while (index < length && str1[index] == str2[index]) {
            ++index;
        }
        return str1.substr(0, index);
    }
};

int main() {
    vector<string> strs = {"flower", "flow", "flight"};
    cout << (new Solution())->longestCommonPrefix(strs) << endl;
    return 0;
}