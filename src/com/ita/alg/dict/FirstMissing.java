package com.ita.alg.dict;

import java.util.*;
import java.util.stream.Collectors;

public class FirstMissing {

    public int minNumberDisappeared(int[] nums) {
        // write code here
        Map<Integer, Boolean> storage = Arrays.stream(nums).filter(num -> num > 0).boxed()
                .collect(Collectors.toMap(num -> num, num -> true, (k1, k2) -> k1, HashMap::new));
        for (int i = 1; i <= nums.length; i++) {
            if (!storage.containsKey(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }
}
