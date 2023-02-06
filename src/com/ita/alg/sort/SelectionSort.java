package com.ita.alg.sort;

public class SelectionSort implements Sort {
    public SelectionSort() {
    }

    @Override
    public int[] sort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            int compareValueIdx = i - 1;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] <= arr[compareValueIdx]) {
                    compareValueIdx = j;
                }
            }
            SortUtil.swap(arr, i - 1, compareValueIdx);
        }
        return arr;
    }
}
