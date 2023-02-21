package com.ita.alg.divide_and_conquer;


public class PeakFinding {
    public PeakFinding() {
    }

    int OneDimensionalGreedy(int[] arr) {
        checkArrValid(arr);
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] >= arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] >= arr[arr.length - 2]) {
            return arr.length - 1;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    int OneDimensionalDivideAndConquer(int[] arr) {
        checkArrValid(arr);
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] >= arr[1]) {
            return 0;
        }
        return OneDimensionalDivideAndConquerRecursive(arr, 0, arr.length - 1);
    }

    int OneDimensionalDivideAndConquerRecursive(int[] arr, int start, int end) {
        if (start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        if (mid == start) {
            if (arr[mid] >= arr[mid + 1]) {
                return start;
            } else {
                return end;
            }
        }
        if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) {
            return mid;
        }
        return arr[mid] < arr[mid - 1] ? OneDimensionalDivideAndConquerRecursive(arr, start, mid) : OneDimensionalDivideAndConquerRecursive(arr, mid, end);
    }

    Coordinate twoDimensionalGreedy(int[][] arr) {
        checkArrValid(arr);
        Coordinate res = returnIfSingular(arr);
        if (res != null) {
            return res;
        }
        boolean[][] visited = new boolean[arr.length][arr[0].length];
        return twoDimensionalGreedyTraverse(arr, visited, 0, 0);
    }

    Coordinate twoDimensionalGreedyTraverse(int[][] arr, boolean[][] visited, int i, int j) {
        if (visited[i][j]) {
            return null;
        }
        if ((i == 0 || arr[i][j] >= arr[i - 1][j]) && (i == arr.length - 1 || arr[i][j] >= arr[i + 1][j]) && (j == 0 || arr[i][j] >= arr[i][j - 1]) && (j == arr[0].length - 1 || arr[i][j] >= arr[i][j + 1])) {
            return new Coordinate(i, j);
        }
        visited[i][j] = true;
        if (i != 0) {
            Coordinate r = twoDimensionalGreedyTraverse(arr, visited, i - 1, j);
            if (r != null) {
                return r;
            }
        }
        if (i != arr.length - 1) {
            Coordinate r = twoDimensionalGreedyTraverse(arr, visited, i + 1, j);
            if (r != null) {
                return r;
            }
        }
        if (j != 0) {
            Coordinate r = twoDimensionalGreedyTraverse(arr, visited, i, j - 1);
            if (r != null) {
                return r;
            }
        }
        if (j != arr[0].length - 1) {
            Coordinate r = twoDimensionalGreedyTraverse(arr, visited, i, j + 1);
            if (r != null) {
                return r;
            }
        }
        return null;
    }

    Coordinate twoDimensionalDivideAndConquer(int[][] arr) {
        checkArrValid(arr);
        Coordinate r = returnIfSingular(arr);
        return r != null ? r : twoDimensionalDivideAndConquerRecursive(arr, 0, arr[0].length - 1);
    }

    Coordinate twoDimensionalDivideAndConquerRecursive(int[][] arr, int colStart, int colEnd) {
        if (colStart == colEnd) {
            int[] col = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                col[i] = arr[i][colStart];
            }
            return new Coordinate(OneDimensionalDivideAndConquer(col), colStart);
        }
        int mid = (colStart + colEnd) / 2;
        if (mid == colStart) {
            int maxIndexColStart = findMaxInACol(arr, colStart);
            int maxIndexColEnd = findMaxInACol(arr, colEnd);
            return arr[maxIndexColStart][colStart] > arr[maxIndexColEnd][colEnd] ?
                    new Coordinate(maxIndexColStart, colStart) : new Coordinate(maxIndexColEnd, colEnd);
        }

        int maxIndex = findMaxInACol(arr, mid);
        if (arr[maxIndex][mid] >= arr[maxIndex][mid - 1] && arr[maxIndex][mid] >= arr[maxIndex][mid + 1]) {
            return new Coordinate(maxIndex, mid);
        }
        return arr[maxIndex][mid] < arr[maxIndex][mid - 1] ?
                twoDimensionalDivideAndConquerRecursive(arr, colStart, mid) :
                twoDimensionalDivideAndConquerRecursive(arr, mid, colEnd);
    }

    private int findMaxInACol(int[][] arr, int ColIndex) {
        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][ColIndex] > maxValue) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void checkArrValid(int[][] arr) {
        if (arr.length == 0 || arr[0].length == 0) {
            throw new RuntimeException("invalid input");
        }
        int cols = arr[0].length;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != cols) {
                throw new RuntimeException("invalid input");
            }
        }
    }

    private Coordinate returnIfSingular(int[][] arr) {
        if (arr.length == 1) {
            return new Coordinate(0, OneDimensionalDivideAndConquer(arr[0]));
        }

        if (arr[0].length == 1) {
            int[] col = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                col[i] = arr[i][0];
            }
            return new Coordinate(OneDimensionalDivideAndConquer(col), 0);
        }
        return null;
    }

    private void checkArrValid(int[] arr) {
        if (arr.length == 0) {
            throw new RuntimeException("invalid input");
        }
    }
}

class Coordinate {

    public Coordinate(int x, int y) {
        X = x;
        Y = y;
    }

    int X;
    int Y;

    @Override
    public String toString() {
        return "(" + X + ", " + Y + ")";
    }
}
