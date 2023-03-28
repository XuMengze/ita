package com.ita.alg.dp;

import java.util.Arrays;

public class CanPartition {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        boolean[][] dp = new boolean[sum / 2 + 1][nums.length];
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
