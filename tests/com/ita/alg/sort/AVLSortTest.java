package com.ita.alg.sort;

import org.junit.Test;

import java.util.Arrays;

public class AVLSortTest {
    @Test
    public void sortTest() {
        System.out.println(Arrays.toString(new AVLSort().sort(new int[]{5, 2, 4, 6, 1, 2, 3})));
    }
}
