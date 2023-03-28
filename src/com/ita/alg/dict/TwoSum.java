package com.ita.alg.dict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, List<Integer>> store = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (!store.containsKey(numbers[i])) {
                store.put(numbers[i], new ArrayList<>());
            }
            store.get(numbers[i]).add(i);
        }
        for (int n : numbers) {
            int left = target - n;
            if (left == n) {
                if (store.get(left).size() >= 2) {
                    return new int[]{Math.min(store.get(left).get(0), store.get(left).get(1)) + 1, Math.max(store.get(left).get(0), store.get(left).get(1)) + 1};
                }
            } else {
                if (store.containsKey(left)) {
                    return new int[]{Math.min(store.get(n).get(0), store.get(left).get(0)) + 1, Math.max(store.get(n).get(0), store.get(left).get(0)) + 1};
                }
            }
        }
        return new int[]{};
    }
}
