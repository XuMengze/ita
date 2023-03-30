package com.ita.alg.bit;

public class AppearOnce {

    public int[] FindNumsAppearOnce(int[] array) {
        // write code here
        int tmp = 0;
        for (int i : array
        ) {
            tmp ^= i;
        }

        int mask = 1;
        while ((tmp & mask) == 0) {
            mask <<= 1;
        }
        int a = 0;
        int b = 0;
        for (int num : array) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{Math.min(a, b), Math.max(a, b)};
    }
}
