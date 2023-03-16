package com.ita.alg.sort;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Arrays;

public class PartitionSortTest {
    @Disabled
    @Test
    public void sortTest() {
        System.out.println(Arrays.toString(new PartitionSort().sort(new int[]{5, 2, 4, 6, 1, 2, 3})));
    }
}
