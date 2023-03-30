package com.ita.alg.tree;

import com.ita.alg.util.ArrayUtil;

import java.util.*;

public class MinK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        maxHeapify(input, 0, k - 1);
        for (int i = k; i < input.length; i++) {
            if (input[i] < input[0]) {
                ArrayUtil.swap(input, i, 0);
                maxHeapify(input, 0, k - 1, 0);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    private void maxHeapify(int[] arr, int left, int right) {
        for (int i = (right + left) / 2; i >= left; i--) {
            maxHeapify(arr, left, right, i);
        }
    }

    private void maxHeapify(int[] arr, int left, int right, int position) {
        int leftChildPosition = (position - left) * 2 + 1 + left;
        int rightChildPosition = (position - left) * 2 + 2 + left;
        int leftChildVal = leftChildPosition > right ? -1 : arr[leftChildPosition];
        int rightChildVal = rightChildPosition > right ? -1 : arr[rightChildPosition];
        if (leftChildVal == -1 && rightChildVal == -1) {
            return;
        }
        int swapPosition = Math.max(leftChildVal, rightChildVal) == leftChildVal ? leftChildPosition : rightChildPosition;
        if (arr[position] > arr[swapPosition]) {
            return;
        }
        ArrayUtil.swap(arr, position, swapPosition);
        maxHeapify(arr, left, right, swapPosition);
    }

    public static void main(String[] args) {
        System.out.println(new MinK().GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8}, 4));
    }
}
