package com.ita.alg.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedMaxInterval {
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
        re(0, 0, input, res);
        return res.toArray(new int[0][0]);
    }

    private void re(int accumulativeWeight, int firstIndex, int[][] input, List<int[]> result) {
        int[] needAdd = new int[0];
        int maxAcc = accumulativeWeight;
        int pos = firstIndex;
        for (int i = firstIndex; i < input.length; i++) {
            if (!isCompatible(result.get(result.size() - 1), input[i])) {
                continue;
            }
            if (accumulativeWeight + input[i][2] > maxAcc) {
                needAdd = input[i];
                accumulativeWeight += input[i][2];
                pos = i;
            }
        }
        if (maxAcc == accumulativeWeight) {
            return;
        }
        result.add(needAdd);
        re(maxAcc, pos + 1, input, result);
    }

    static boolean isCompatible(int[] a, int[] b) {
        return a[1] <= b[0] || a[0] >= b[1];
    }

    public static void main(String[] args) {
        int[][] res = new WeightedMaxInterval().solution(new int[][]{
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
