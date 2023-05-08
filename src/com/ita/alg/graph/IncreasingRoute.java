package com.ita.alg.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncreasingRoute {

    public static void main(String[] args) {
        new IncreasingRoute().solve(new int[][]{new int[]{1, 2, 3}, new int[]{1, 2, 3}, new int[]{1, 2, 3}});
    }

    int[][] dp;

    public int solve(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                int longest = reStart(matrix, visited, i, j, 1);
                dp[i][j] = longest;
                res.add(longest);
            }
        }
        return res.stream().mapToInt(i -> i).max().getAsInt();
    }

    private int reStart(int[][] matrix, boolean[][] visited, int i, int j, int acc) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j]) {
            return acc;
        }
        visited[i][j] = true;
        int[] candidate = new int[5];
        if (i + 1 < matrix.length && !visited[i + 1][j] && matrix[i + 1][j] > matrix[i][j]) {
            candidate[0] = dp[i + 1][j] > 0 ? acc + dp[i + 1][j] : reStart(matrix, visited, i + 1, j, acc + 1);
        }
        if (i - 1 >= 0 && !visited[i - 1][j] && matrix[i - 1][j] > matrix[i][j]) {
            candidate[1] = dp[i - 1][j] > 0 ? acc + dp[i - 1][j] : reStart(matrix, visited, i - 1, j, acc + 1);
        }
        if (j + 1 < matrix[0].length && !visited[i][j + 1] && matrix[i][j + 1] > matrix[i][j]) {
            candidate[2] = dp[i][j + 1] > 0 ? acc + dp[i][j + 1] : reStart(matrix, visited, i, j + 1, acc + 1);
        }
        if (j - 1 >= 0 && !visited[i][j - 1] && matrix[i][j - 1] > matrix[i][j]) {
            candidate[3] = dp[i][j - 1] > 0 ? acc + dp[i][j - 1] : reStart(matrix, visited, i, j - 1, acc + 1);
        }
        candidate[4] = acc;
        return Arrays.stream(candidate).max().getAsInt();
    }
}
