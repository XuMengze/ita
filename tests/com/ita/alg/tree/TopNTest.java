package com.ita.alg.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TopNTest {
    @Test
    public void testTopN() {
        int n = 3;
        int[] in = new int[]{5, 2, 4, 6, 1, 2, 3, 7};
        TopN t = new TopN(n);
        for (Integer i : in) {
            t.push(i);
        }
        int[] res = t.getTopN();
        Set<Integer> set = Arrays.stream(res).boxed().collect(Collectors.toSet());
        assert set.size() == n;
        assert set.contains(7);
        assert set.contains(6);
        assert set.contains(5);
    }
}
