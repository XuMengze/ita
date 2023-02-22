package com.ita.alg.divide_and_conquer;

import org.junit.Test;

public class PeakFindingTest {
    PeakFinding pkf = new PeakFinding();

    @Test
    public void TestOneDimensionalGreedy() {
        assert 4 == pkf.OneDimensionalGreedy(new int[]{1, 2, 3, 4, 5});
        assert 0 == pkf.OneDimensionalGreedy(new int[]{5, 4, 3, 2, 1});
        assert 3 == pkf.OneDimensionalGreedy(new int[]{1, 2, 4, 5, 3, 1});
    }

    @Test
    public void TestOneDimensionalDivideAndConquer() {
        assert 4 == pkf.OneDimensionalDivideAndConquer(new int[]{1, 2, 3, 4, 5});
        assert 0 == pkf.OneDimensionalDivideAndConquer(new int[]{5, 4, 3, 2, 1});
        assert 3 == pkf.OneDimensionalDivideAndConquer(new int[]{1, 2, 4, 5, 3, 1});
    }

    @Test
    public void TestTwoDimensionalGreedy() {
        Coordinate res = pkf.twoDimensionalGreedy(new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{14, 13, 12, 11},
                new int[]{15, 9, 11, 17},
                new int[]{16, 17, 19, 20}
        });
        assert res.equals(new Coordinate(3,3));
    }
    @Test
    public void TestTwoDimensionalDivideAndConquer() {
        Coordinate res = pkf.twoDimensionalDivideAndConquer(new int[][]{
                new int[]{1, 2, 3, 4},
                new int[]{14, 13, 12, 11},
                new int[]{15, 9, 11, 17},
                new int[]{16, 17, 19, 20}
        });
        assert res.equals(new Coordinate(3,3));
    }

}
