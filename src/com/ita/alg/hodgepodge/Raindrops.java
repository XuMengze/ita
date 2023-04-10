package com.ita.alg.hodgepodge;

public class Raindrops {
    public long maxWater(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        int leftMinMax = arr[0], rightMinMax = arr[arr.length - 1];
        for (int i = 1; i <= arr.length - 2; i++) {
            leftMax[i] = leftMinMax;
            leftMinMax = Math.max(leftMinMax, arr[i]);
        }
        for (int i = arr.length - 2; i >= 1; i--) {
            rightMax[i] = rightMinMax;
            rightMinMax = Math.max(rightMinMax, arr[i]);
        }
        int res = 0;
        for (int i = 1; i <= arr.length - 2; i++) {
            res += Math.max(Math.min(leftMax[i], rightMax[i]) - arr[i], 0);
        }
        return res;
    }
}
