package com.ita.alg.sort;

public class BubbleSort implements Sort {
    public BubbleSort() {
    }

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    SortUtil.swap(arr, j, j - 1);
                }
            }
        }
        return arr;
    }
}
