package com.ita.alg.dp;

public class StealMoney {
    /**
     * 你是一个经验丰富的小偷，准备偷沿街的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，即，如果偷了第一家，就不能再偷第二家；如果偷了第二家，那么就不能偷第一家和第三家。
     * 给定一个整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
     */

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dp[i] = Math.max(dp[i - 1], nums[i]);
                continue;
            }
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 你是一个经验丰富的小偷，准备偷沿湖的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，即，如果偷了第一家，就不能再偷第二家，如果偷了第二家，那么就不能偷第一家和第三家。
     * 沿湖的房间组成一个闭合的圆形，即第一个房间和最后一个房间视为相邻。
     * 给定一个长度为n的整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额。
     */
    public int robCircle(int[] nums) {
        // write code here
        int[] dpRobFirst = new int[nums.length];
        dpRobFirst[0] = nums[0];
        int[] dpRobLast = new int[nums.length];
        dpRobLast[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dpRobFirst[i] = Math.max(dpRobFirst[i - 1], nums[i]);
                dpRobLast[i] = Math.max(dpRobLast[i - 1], nums[i]);
                continue;
            }
            dpRobFirst[i] = Math.max(dpRobFirst[i - 1], nums[i] + dpRobFirst[i - 2]);
            dpRobLast[i] = Math.max(dpRobLast[i - 1], nums[i] + dpRobLast[i - 2]);
        }
        return Math.max(dpRobFirst[nums.length - 2], dpRobLast[nums.length - 1]);
    }
}
