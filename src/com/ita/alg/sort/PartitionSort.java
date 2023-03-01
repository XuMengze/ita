package com.ita.alg.sort;

import java.util.Random;

public class PartitionSort implements Sort {
    public PartitionSort() {
    }

    @Override
    public int[] sort(int[] arr) {
        partition(arr, 0, arr.length - 1);
        return arr;
    }

    public void partition(int[] arr, int start, int end) {
        if (end - start == 0) {
            return;
        }
        if (end - start == 1) {
            if (arr[start] > arr[end]) {
                SortUtil.swap(arr, start, end);
            }
            return;
        }
        int partition = getPartition(arr, start, end);
        partition(arr, start, partition - 1);
        partition(arr, partition + 1, end);


    }

    public int getPartition(int[] arr, int start, int end) {
        int randomPosition = new Random().nextInt(end - start) + start;
        SortUtil.swap(arr, start, randomPosition);
        int i = start + 1, j = end;
        while (i < j) {
            while (j >= start && arr[j] > arr[start]) {
                j--;
            }
            while (i <= end && arr[i] < arr[start]) {
                i++;
            }
            if (i < j) {
                SortUtil.swap(arr, i, j);
                i++;
                j--;
            }
        }
        SortUtil.swap(arr, start, j);
        return j;
    }

}
