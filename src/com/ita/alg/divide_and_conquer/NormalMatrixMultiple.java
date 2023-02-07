package com.ita.alg.divide_and_conquer;

public class NormalMatrixMultiple {
    public NormalMatrixMultiple() {
    }

    int[][] matrixMultiple(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < a.length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}
