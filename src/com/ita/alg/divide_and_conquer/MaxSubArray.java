package com.ita.alg.divide_and_conquer;

public class MaxSubArray {
    public MaxSubArray() {
    }

    int maxSubArray(int[] arr) {
        return maxSubArrayRecursive(arr, 0, arr.length - 1);
    }

    int maxSubArrayRecursive(int[] arr, int low, int high) {
        if (low >= high) {
            return arr[low];
        }
        int mid = (low + high) / 2;
        return Math.max(
                maxSubArrayAcrossMiddle(arr, low, mid, high),
                Math.max(
                        maxSubArrayRecursive(arr, low, mid - 1),
                        maxSubArrayRecursive(arr, mid + 1, high)
                )
        );
    }

    int maxSubArrayAcrossMiddle(int[] arr, int low, int mid, int high) {
        int leftAccumulative = 0, leftMax = Integer.MIN_VALUE;
        int rightAccumulative = 0, rightMax = Integer.MIN_VALUE;

        for (int i = mid; i >= low; i--) {
            leftAccumulative += arr[i];
            if (leftMax < leftAccumulative) {
                leftMax = leftAccumulative;
            }
        }

        for (int i = mid; i <= high; i++) {
            rightAccumulative += arr[i];
            if (rightMax < rightAccumulative) {
                rightMax = rightAccumulative;
            }
        }

        return leftMax + rightMax - arr[mid];
    }
}
