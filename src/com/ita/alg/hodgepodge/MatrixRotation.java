package com.ita.alg.hodgepodge;

import com.ita.alg.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixRotation {

    public int[][] rotateMatrix(int[][] mat) {
        // write code here
        int[][] res = new int[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            res[i] = new int[mat.length];
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                res[j][mat.length - 1 - i] = mat[i][j];
            }
        }
        return res;
    }

    private boolean checkShouldGo(boolean[][] visited, int row, int col) {
        return row >= 0 && row <= visited.length - 1 && col >= 0 && col <= visited[0].length - 1 && !visited[row][col];
    }

    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        boolean[][] visited = new boolean[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            visited[i] = new boolean[matrix[0].length];
        }
        int[][] directions = new int[][]{new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0}};
        int directionIndex = 0;
        ArrayList<Integer> res = new ArrayList<>();
        int row = 0;
        int col = 0;
        while (true) {
            res.add(matrix[row][col]);
            visited[row][col] = true;
            int nextRow = row + directions[directionIndex % 4][0];
            int nextCol = col + directions[directionIndex % 4][1];
            if (checkShouldGo(visited, nextRow, nextCol)) {
                row = nextRow;
                col = nextCol;
            } else {
                boolean shouldGoFlag = false;
                for (int i = 0; i < 3; i++) {
                    directionIndex++;
                    nextRow = row + directions[directionIndex % 4][0];
                    nextCol = col + directions[directionIndex % 4][1];
                    if (checkShouldGo(visited, nextRow, nextCol)) {
                        row = nextRow;
                        col = nextCol;
                        shouldGoFlag = true;
                        break;
                    }
                }
                if (!shouldGoFlag) {
                    break;
                }
            }
        }
        return res;
    }

    public int[] rightMove(int n, int m, int[] a) {
        m = m % a.length;
        // write code here
        reverseRange(a, a.length - m, a.length - 1);
        reverseRange(a, 0, a.length - m - 1);
        reverseRange(a, 0, a.length - 1);
        return a;
    }

    private void reverseRange(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        while (left < right) {
            ArrayUtil.swap(a, left++, right--);
        }
    }
}
