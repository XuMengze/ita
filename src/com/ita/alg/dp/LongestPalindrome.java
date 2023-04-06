package com.ita.alg.dp;

import java.util.Arrays;

public class LongestPalindrome {
    /**
     * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
     */

    public int getLongestPalindrome(String A) {
        if (A.length() <= 1) {
            return A.length();
        }
        if (A.length() == 2) {
            return A.charAt(0) == A.charAt(1) ? 2 : 1;
        }
        boolean[][] dp = new boolean[A.length()][A.length()];
        for (int i = 0; i < A.length(); i++) {
            dp[i][i] = true;
        }
        int max = -1;
        for (int i = A.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < A.length(); j++) {
                if (A.charAt(i) == A.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }
        }
        for (int i = 0; i < A.length(); i++) {
            for (int j = i; j < A.length(); j++) {
                max = Math.max(max, dp[i][j] ? j - i + 1 : -1);
            }
        }
        return max;
    }

}
