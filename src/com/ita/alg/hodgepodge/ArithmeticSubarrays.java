package com.ita.alg.hodgepodge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArithmeticSubarrays {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            res.add(checkArithmeticSubarrays(nums, l[i], r[i]));
        }
        return res;
    }

    private boolean checkArithmeticSubarrays(int[] nums, int l, int r) {
        if (r - l == 1) {
            return true;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<Integer> store = new HashSet<>();
        for (int i = l; i <= r; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
            store.add(nums[i]);
        }
        if (max - min == 0 && store.size() == 1) {
            return true;
        }
        int d = (max - min) / (r - l);
        for (int i = 1; i <= r - l; i++) {
            if (!store.contains(min + d * i)) {
                return false;
            }
        }
        return true;
    }
}
