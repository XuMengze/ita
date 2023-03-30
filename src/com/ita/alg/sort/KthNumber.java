package com.ita.alg.sort;

import com.ita.alg.util.ArrayUtil;

public class KthNumber {

    // k max
    public int findKth(int[] a, int n, int K) {
        // write code here
        int partitionRes;
        int left = 0;
        int right = n - 1;
        do {
            partitionRes = partition(a, left, right);
            if (partitionRes > K) {
                right = partitionRes - 1;
            } else {
                left = partitionRes + 1;
            }
        } while (partitionRes != K);
        return a[partitionRes];
    }

    private int partition(int[] arr, int left, int right) {
        if (left >= right){
            return left;
        }
        int i = left + 1, j = right;
        while (i < j) {
            while (i <= right && arr[i] >= arr[left]) {
                i++;
            }
            while (j >= left && arr[j] <= arr[left]) {
                j--;
            }
            if (i < j) {
                ArrayUtil.swap(arr, i, j);
                i++;
                j--;
            }
        }
        ArrayUtil.swap(arr, left, j);
        return j;
    }

    public static void main(String[] args) {
        int res = new KthNumber().findKth(new int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663}, 49, 24);
        System.out.println(res);
    }
}
