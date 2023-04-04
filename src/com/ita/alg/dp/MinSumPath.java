package com.ita.alg.dp;

public class MinSumPath {

    /**
     * 给定一个 n * m 的矩阵 a
     * 从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和.
     * 输出所有的路径中最小的路径和。
     */
    public int minPathSum(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    matrix[0][j] = matrix[0][j] + matrix[0][j - 1];
                } else if (j == 0) {
                    matrix[i][0] = matrix[i][0] + matrix[i - 1][0];
                } else {
                    matrix[i][j] = matrix[i][j] + Math.min(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}
