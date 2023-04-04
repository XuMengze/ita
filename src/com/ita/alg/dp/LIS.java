package com.ita.alg.dp;

import java.util.Arrays;

public class LIS {
    /**
     * 给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
     * 所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
     * 我们定义一个序列是 严格上升 的，当且仅当该序列不存在两个下标 i 和 j 满足 i<j 且 arr[i]≥arr[j]
     */
    public int LISubSequence(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < arr.length; i++) {
            int maxLength = -1;
            for (int j = 0; j < i; j++) {
                int compare = arr[i] > arr[j] ? dp[j] + 1 : 1;
                if (compare > maxLength) {
                    maxLength = compare;
                }
            }
            dp[i] = maxLength;
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
