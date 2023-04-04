package com.ita.alg.dp;

public class JumpFloor {
    /**
     * 描述
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 要求：时间复杂度：O(n) ，空间复杂度：O(1)
     */
    public int jumpFloor(int target) {
        // dp[i] = dp[i-1] + dp[i-2]
        // dp[1] = 1
        // dp[2] = 2
        int[] dp = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            if (i == 0) {
                continue;
            } else if (i == 1) {
                dp[i] = 1;
            } else if (i == 2) {
                dp[i] = 2;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[target];
    }
}
