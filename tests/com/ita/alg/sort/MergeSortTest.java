package com.ita.alg.sort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {
    @Test
    public void sortTest() {
        System.out.println(Arrays.toString(new MergeSort().sort(new int[]{5, 2, 4, 6, 1, 2, 3})));
    }
}
