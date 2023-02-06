package com.ita.alg.sort;

public class MergeSort implements Sort {
    public MergeSort() {
    }

    @Override
    public int[] sort(int[] arr) {
        mergeRecursive(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeRecursive(int[] arr, int p, int q) {
        if (p >= q) {
            return;
        }
        int middle = (q + p) / 2;
        mergeRecursive(arr, p, middle);
        mergeRecursive(arr, middle + 1, q);
        merge(arr, p, middle, q);
    }

    // arr[p...q] and arr[q+1...r] is sequential separately, make arr[p...r] sequential
    private void merge(int[] arr, int p, int q, int r) {

        // this part could be easier if assist is an arraylist
        int[] assist = new int[r - p + 1];
        int i = p, j = q + 1;
        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                assist[i + j - p - q - 1] = arr[i];
                i++;
            } else {
                assist[i + j - p - q - 1] = arr[j];
                j++;
            }
        }
        if (i == q + 1) {
            for (int k = j; k <= r; k++) {
                // i reach the end, q-p+1 is left part arranged number
                // (k-1)-(q+1)+1 is right part arranged number
                // plus this two parts happens to be the index of next val
                assist[q - p + 1 + k - 1 - q] = arr[k];
            }
        } else {
            for (int k = i; k <= q; k++) {
                assist[r - q + k - p] = arr[k];
            }
        }

        for (int k = 0; k < assist.length; k++) {
            arr[p + k] = assist[k];
        }
    }
}
