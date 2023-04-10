package com.ita.alg.greedy;

public class Bucket {
    public int maxArea(int[] height) {
        if (height.length < 1) {
            return 0;
        }
        int start = 0, end = height.length - 1;
        int res = 0;
        while (start < end) {
            res = Math.max(res, (end - start) * Math.min(height[start], height[end]));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return res;
    }
}
