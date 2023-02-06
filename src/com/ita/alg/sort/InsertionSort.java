package com.ita.alg.sort;

public class InsertionSort implements Sort {
    public InsertionSort() {
    }

    public int[] sort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
