package com.ita.alg.dp;

public class LCS {
    public static int LCS(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= b.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                dp[i + 1][j + 1] = a.charAt(i) == b.charAt(j) ? dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[a.length()][b.length()];
    }

}
