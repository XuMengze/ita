package com.ita.alg.dict;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxNonDupSubArray {
    public int maxLength(int[] arr) {
        // write code here
        if (arr.length == 0) {
            return 0;
        }
        int res = 1;
        int start = 0;
        int end = 0;
        Set<Integer> storage = new HashSet<>();
        storage.add(arr[0]);
        while (end <= arr.length - 1) {
            if (start == end) {
                end++;
                continue;
            }
            if (!storage.contains(arr[end])) {
                storage.add(arr[end++]);
            } else {
                while (start <= end) {
                    storage.remove(arr[start++]);
                    if (!storage.contains(arr[end])) {
                        break;
                    }
                }
                storage.add(arr[end++]);
            }
            res = Math.max(res, storage.size());
        }
        return res;
    }

}
