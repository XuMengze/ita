package com.ita.alg.graph;

public class Island {
    public int solve(char[][] grid) {
        // write code here
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    rewriteIsland(grid, i, j);
                }
            }
        }
        return res;
    }

    private void rewriteIsland(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        rewriteIsland(grid, i - 1, j);
        rewriteIsland(grid, i + 1, j);
        rewriteIsland(grid, i, j - 1);
        rewriteIsland(grid, i, j + 1);
    }
}
