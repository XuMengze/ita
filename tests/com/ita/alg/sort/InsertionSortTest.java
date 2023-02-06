package com.ita.alg.sort;

import org.junit.Test;

import java.util.Arrays;

public class InsertionSortTest {
    @Test
    public void sortTest() {
        System.out.println(Arrays.toString(new InsertionSort().sort(new int[]{5, 2, 4, 6, 1, 2, 3})));
    }
}
