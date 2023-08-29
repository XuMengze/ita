package com.ita.alg.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

//823. 带因子的二叉树

public class CountBinaryTree {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        long[] dp = new long[arr.length];
        long res = 0, mod = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            for (int left = 0, right = i - 1; left <= right; left++) {
                while (right >= left && arr[right] * arr[left] > arr[i])
                    right--;
                if (right >= left && arr[right] * arr[left] == arr[i]) {
                    if (left == right) {
                        dp[i] = (1 + dp[left] * dp[right]) % mod;
                    } else {
                        dp[i] = (1 + dp[left] * dp[right] * 2) % mod;
                    }
                }
            }
            res = (res + dp[i]) % mod;
        }
        return (int) res;
    }
}
