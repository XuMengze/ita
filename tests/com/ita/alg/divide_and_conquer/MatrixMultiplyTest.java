package com.ita.alg.divide_and_conquer;

import org.junit.Test;

import java.util.Arrays;

public class MatrixMultiplyTest {
    @Test
    public void multiplySingleTest() {
        System.out.println(Arrays.deepToString(new StrassenMatrixMultiple().matrixMultiple(new int[][]{new int[]{1}}, new int[][]{new int[]{2}})));
    }

    @Test
    public void multiply2M2StrassenTest() {
        System.out.println(Arrays.deepToString(
                new StrassenMatrixMultiple().matrixMultiple(
                        new int[][]{new int[]{1, 3}, new int[]{7, 5}},
                        new int[][]{new int[]{6, 8}, new int[]{4, 2}})));
    }

    @Test
    public void multiply2M2NormalTest() {
        System.out.println(Arrays.deepToString(
                new NormalMatrixMultiple().matrixMultiple(
                        new int[][]{new int[]{1, 3}, new int[]{7, 5}},
                        new int[][]{new int[]{6, 8}, new int[]{4, 2}})));
    }
}
