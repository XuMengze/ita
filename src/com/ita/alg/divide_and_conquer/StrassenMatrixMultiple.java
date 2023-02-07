package com.ita.alg.divide_and_conquer;

public class StrassenMatrixMultiple {
    public StrassenMatrixMultiple() {
    }

    enum operation {PLUS, MINUS}

    int[][] matrixOperation(int[][] a, int[][] b, operation op) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                result[i][j] = op == operation.PLUS ? a[i][j] + b[i][j] : a[i][j] - b[i][j];
            }
        }
        return result;
    }

    int[][] matrixMultiple(int[][] a, int[][] b) {
        if (a.length == 1) {
            return new int[][]{new int[]{a[0][0] * b[0][0]}};
        }
        int[][][] splitResA = split(a); // a11, a12, a21, a22
        int[][][] splitResB = split(b); // b11, b12, b21, b22
        int[][] S1 = matrixOperation(splitResB[1], splitResB[3], operation.MINUS);
        int[][] S2 = matrixOperation(splitResA[0], splitResA[1], operation.PLUS);
        int[][] S3 = matrixOperation(splitResA[2], splitResA[3], operation.PLUS);
        int[][] S4 = matrixOperation(splitResB[2], splitResB[0], operation.MINUS);
        int[][] S5 = matrixOperation(splitResA[0], splitResA[3], operation.PLUS);
        int[][] S6 = matrixOperation(splitResB[0], splitResB[3], operation.PLUS);
        int[][] S7 = matrixOperation(splitResA[1], splitResA[3], operation.MINUS);
        int[][] S8 = matrixOperation(splitResB[2], splitResB[3], operation.PLUS);
        int[][] S9 = matrixOperation(splitResA[0], splitResA[2], operation.MINUS);
        int[][] S10 = matrixOperation(splitResB[0], splitResB[1], operation.PLUS);

        int[][] P1 = matrixMultiple(splitResA[0], S1);
        int[][] P2 = matrixMultiple(S2, splitResB[3]);
        int[][] P3 = matrixMultiple(S3, splitResB[0]);
        int[][] P4 = matrixMultiple(splitResA[3], S4);
        int[][] P5 = matrixMultiple(S5, S6);
        int[][] P6 = matrixMultiple(S7, S8);
        int[][] P7 = matrixMultiple(S9, S10);

        int[][] C11 = matrixOperation(matrixOperation(matrixOperation(P5, P4, operation.PLUS), P2, operation.MINUS), P6, operation.PLUS);
        int[][] C12 = matrixOperation(P1, P2, operation.PLUS);
        int[][] C21 = matrixOperation(P3, P4, operation.PLUS);
        int[][] C22 = matrixOperation(matrixOperation(matrixOperation(P5, P1, operation.PLUS), P3, operation.MINUS), P7, operation.MINUS);

        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < C11.length; i++) {
            for (int j = 0; j < C11[0].length; j++) {
                result[i][j] = C11[i][j];
                result[i + a.length / 2][j] = C21[i][j];
                result[i][j + a.length / 2] = C12[i][j];
                result[i + a.length / 2][j + a.length / 2] = C22[i][j];
            }
        }
        return result;
    }

    int[][][] split(int[][] a) {
        int mid = a.length / 2;
        int[][] a11 = new int[mid][mid];
        int[][] a12 = new int[mid][mid];
        int[][] a21 = new int[mid][mid];
        int[][] a22 = new int[mid][mid];

        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                a11[i][j] = a[i][j];
            }
        }
        for (int i = mid; i < a.length; i++) {
            for (int j = 0; j < mid; j++) {
                a21[i - mid][j] = a[i][j];
            }
        }
        for (int i = 0; i < mid; i++) {
            for (int j = mid; j < a[0].length; j++) {
                a12[i][j - mid] = a[i][j];
            }
        }
        for (int i = mid; i < a.length; i++) {
            for (int j = mid; j < a[0].length; j++) {
                a22[i - mid][j - mid] = a[i][j];
            }
        }
        return new int[][][]{a11, a12, a21, a22};
    }
}
