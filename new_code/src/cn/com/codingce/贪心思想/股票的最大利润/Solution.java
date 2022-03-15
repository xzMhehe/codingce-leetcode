package cn.com.codingce.贪心思想.股票的最大利润;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{2, 5, 9, 20, 1}));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int soFarMin = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            soFarMin = Math.min(soFarMin, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
        }
        return maxProfit;
    }
}
