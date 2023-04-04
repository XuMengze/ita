package com.ita.alg.dp;

import java.util.Arrays;

public class Translate {
    /**
     * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'
     * 现在给一串数字，返回有多少种可能的译码结果
     */
    public int solve(String nums) {
        int[] dp = new int[nums.length()];
        for (int i = 0; i < nums.length(); i++) {
            if (i == 0) {
                if (nums.charAt(0) == '0') {
                    return 0;
                }
                dp[0] = 1;
            } else if (i == 1) {
                if (nums.charAt(1) == '0') {
                    if (nums.charAt(0) != '1' && nums.charAt(0) != '2') {
                        return 0;
                    }
                    dp[1] = 1;
                } else {
                    dp[1] = stringLessThan26(nums, 1) ? 2 : 1;
                }
            } else {
                if (nums.charAt(i) == '0') {
                    if (nums.charAt(i - 1) != '1' && nums.charAt(i - 1) != '2') {
                        return 0;
                    }
                    dp[i] = dp[i - 1];
                } else {
                    if (nums.charAt(i - 1) == '0') {
                        dp[i] = dp[i - 1];
                    } else {
                        dp[i] = dp[i - 1] + (stringLessThan26(nums, i) ? dp[i - 2] : 0);
                    }
                }
            }
        }
        return dp[nums.length() - 1];
    }

    private boolean stringLessThan26(String nums, int index) {
        return Integer.parseInt(nums.substring(index - 1, index + 1)) <= 26;
    }

    public static void main(String[] args) {
        System.out.println(new Translate().solve("72910721221427251718216239162221131917242"));
    }
}