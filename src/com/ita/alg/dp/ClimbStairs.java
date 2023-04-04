package com.ita.alg.dp;

public class ClimbStairs {

    /**
     * 给定一个整数数组 cost 其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用，下标从0开始。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        // write code here
        int[] dp = new int[cost.length + 1];
        for (int i = 0; i <= cost.length; i++) {
            if (i <= 1) {
                dp[i] = 0;
            } else {
                dp[i] = Math.min(cost[i - 1] + dp[i - 1], cost[i - 2] + dp[i - 2]);
            }
        }
        return dp[cost.length];
    }
}
