package com.ita.alg.greedy;

import java.util.Arrays;

public class Candies {
    public int candy(int[] arr) {
        // write code here
        int n = arr.length;
        if (n <= 1)
            return n;
        int[] nums = new int[n];
        Arrays.fill(nums, 1);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1])
                nums[i] = nums[i - 1] + 1;
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1] && nums[i] <= nums[i + 1])
                nums[i] = nums[i + 1] + 1;
        }
        return Arrays.stream(nums).sum();
    }
}
