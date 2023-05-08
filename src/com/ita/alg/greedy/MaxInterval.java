package com.ita.alg.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaxInterval {
    public int[][] solution(int[][] input) {
        if (input.length == 0) {
            return null;
        }
        List<int[]> sorted = Arrays.stream(input).sorted((i, j) -> {
            if (i[1] < j[1]) {
                return -1;
            } else if (i[1] > j[1]) {
                return 1;
            } else {
                return -Integer.compare(j[0], i[0]);
            }
        }).toList();
        List<int[]> res = new ArrayList<>();
        res.add(sorted.get(0));
        for (int i = 1; i < sorted.size(); i++) {
            if (isCompatible(res.get(res.size() - 1), sorted.get(i))) {
                res.add(sorted.get(i));
            }
        }
        return res.toArray(new int[0][0]);
    }

    static boolean isCompatible(int[] a, int[] b) {
        return a[1] <= b[0] || a[0] >= b[1];
    }

    public static void main(String[] args) {
        int[][] res = new MaxInterval().solution(new int[][]{
                new int[]{1, 3},
                new int[]{1, 2},
                new int[]{1, 2},
                new int[]{2, 4},
                new int[]{3, 4},
        });

        for (int[] ss : res) {
            System.out.println(Arrays.toString(ss));
        }
    }
}
