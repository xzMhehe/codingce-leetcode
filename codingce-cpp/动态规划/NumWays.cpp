//
// Created by 24607 on 2022/12/8.
//
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        vector<int> v(n + 1, 0);
        v[0] = 1;
        v[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            v[i] = (v[i - 1] + v[i - 2]) % 1000000007;
        }
        return v[n];
    }

    int numWays2(int n) {
        if (n <= 1) return 1;
        int dp[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        //dp[2] = 2;

        for (int i = 2; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }
};

int main() {
    cout << (new Solution())->numWays(20);
    return 0;
}
