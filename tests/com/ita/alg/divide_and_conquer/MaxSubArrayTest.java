package com.ita.alg.divide_and_conquer;

import com.ita.alg.sort.BubbleSort;
import org.junit.Test;

import java.util.Arrays;

public class MaxSubArrayTest {
    @Test
    public void sortTest() {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{
                13, -3, -25, 20, -3,
                -16, -23, 18, 20, -7,
                12, -5, -22, 15, -4,
                7}));
    }
}
