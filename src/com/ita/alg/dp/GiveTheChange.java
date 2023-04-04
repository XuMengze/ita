package com.ita.alg.dp;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class GiveTheChange {
    /**
     * 给定数组arr，arr中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个aim，代表要找的钱数，求组成aim的最少货币数。
     * 如果无解，请返回-1.
     */
    public int minMoney(int[] arr, int aim) {
        if (arr.length < 1) {
            return -1;
        }
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i : arr) {
            if (i >= dp.length) {
                continue;
            }
            dp[i] = 1;
        }
        for (int i = 1; i <= aim; i++) {
            int min = Integer.MAX_VALUE;
            for (int amount : arr) {
                if (i - amount < 0) {
                    continue;
                }
                if (dp[i - amount] == -1) {
                    continue;
                } else {
                    if (dp[i - amount] < min) {
                        min = dp[i - amount];
                    }
                }
            }
            if (min != Integer.MAX_VALUE) {
                dp[i] = min + 1;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        new GiveTheChange().minMoney(new int[]{357, 322, 318, 181, 22, 158, 365}, 4976);
    }
}
