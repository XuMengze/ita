package com.ita.alg.divide_and_conquer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMedian {
    public int[] findMedian(int[] input) {
        List<Integer> inputList = Arrays.stream(input).boxed().toList();
        return new int[]{
                findRankK(Arrays.stream(input).boxed().toList(), (input.length - 1) / 2),
                findRankK(Arrays.stream(input).boxed().toList(), (input.length) / 2),
        };
    }

    // rank >= 0
    // rank + 1 must <= input.size()
    private int findRankK(List<Integer> input, int rank) {
        if (rank < 0 || rank + 1 > input.size() || input.size() < 1) {
            throw new RuntimeException("Invalid Input");
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int compare = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) > compare) {
                right.add(input.get(i));
            } else {
                left.add(input.get(i));
            }
        }
        if (left.size() == rank) {
            return compare;
        } else if (left.size() > rank) {
            return findRankK(left, rank);
        } else {
            return findRankK(right, rank - left.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindMedian().findMedian(new int[]{1, 2, 3, 4, 5, 6})));
    }
}
