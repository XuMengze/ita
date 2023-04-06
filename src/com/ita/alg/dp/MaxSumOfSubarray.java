package com.ita.alg.dp;

import java.util.Arrays;

public class MaxSumOfSubarray {
    /**
     * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return -1;
        }
        int[] dp = new int[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
