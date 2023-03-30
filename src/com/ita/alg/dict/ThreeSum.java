package com.ita.alg.dict;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num.length < 3) {
            return res;
        }
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int target = -num[i];
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                if (num[start] + num[end] == target) {
                    ArrayList<Integer> middle = new ArrayList<>(Arrays.asList(num[i], num[start], num[end]));
                    res.add(middle);
                    start++;
                    end--;
                } else if (num[start] + num[end] < target) {
                    start++;
                } else {
                    end--;
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = new ThreeSum().threeSum(new int[]{-10,0,10,20,-10,-40});
        System.out.println(res);
    }
}
