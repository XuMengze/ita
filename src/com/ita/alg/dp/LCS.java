package com.ita.alg.dp;

public class LCS {
    private final char left = '←';
    private final char up = '↑';
    private final char leftUp = '↖';

    /**
     * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。
     */
    public String LCSubSequence(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        char[][] directions = new char[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    directions[i + 1][j + 1] = leftUp;
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                    if (dp[i + 1][j] >= dp[i][j + 1]) {
                        directions[i + 1][j + 1] = left;
                    } else {
                        directions[i + 1][j + 1] = up;
                    }
                }
            }
        }
        int beginRow = a.length(), beginCol = b.length();
        StringBuilder res = new StringBuilder();
        while (beginRow > 0 && beginCol > 0) {
            if (directions[beginRow][beginCol] == leftUp) {
                res.append(a.charAt(beginRow - 1));
                beginRow--;
                beginCol--;
            } else if (directions[beginRow][beginCol] == left) {
                beginCol--;
            } else {
                beginRow--;
            }
        }
        return res.isEmpty() ? "-1" : res.reverse().toString();
    }

    /**
     * 给定两个字符串str1和str2，输出两个字符串的最长公共子串。如果最长公共子序列为空，则返回"-1"。
     */
    public String LCSubString(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        char[][] directions = new char[a.length() + 1][b.length() + 1];
        int maxRow = 0, maxCol = 0, maxLen = -1;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    directions[i + 1][j + 1] = leftUp;
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > maxLen) {
                        maxLen = dp[i + 1][j + 1];
                        maxRow = i + 1;
                        maxCol = j + 1;
                    }
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (directions[maxRow][maxCol] == leftUp) {
            res.append(a.charAt(maxRow - 1));
            maxRow--;
            maxCol--;
        }
        return res.reverse().toString();
    }
}
