package com.ita.alg.greedy;

import java.util.*;
import java.util.stream.Collectors;

public class Permutation {
    public static void main(String[] args) {
        System.out.println(new Permutation().generateParenthesis(3));
    }

    ArrayList<ArrayList<Integer>> res;
    Set<String> dup = new HashSet<>();

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        res = new ArrayList<>();
        dup = new HashSet<>();
        re(new ArrayList<>(), Arrays.stream(num).boxed().collect(Collectors.toList()));
        return res;
    }

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        res = new ArrayList<>();
        dup = new HashSet<>();
        re(new ArrayList<>(), Arrays.stream(num).boxed().collect(Collectors.toList()));
        return res;
    }

    private void re(ArrayList<Integer> already, List<Integer> remain) {
        if (remain.size() == 0) {
            ArrayList<Integer> tmpRes = (ArrayList<Integer>) already.clone();
            String tmpResString = tmpRes.stream().map(String::valueOf).collect(Collectors.joining(","));
            if (dup.contains(tmpResString)) {
                return;
            }
            dup.add(tmpResString);
            res.add(tmpRes);
        }
        for (int i = 0; i < remain.size(); i++) {
            int target = remain.get(i);
            already.add(remain.get(i));
            remain.remove(remain.get(i));
            re(already, remain);
            already.remove(already.size() - 1);
            remain.add(i, target);
        }
    }

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        Set<String> res = new HashSet<>();
        res.add("()");
        for (int i = 2; i <= n; i++) {
            Set<String> tmp = new HashSet<>();
            for (String seq : res) {
                for (int j = 0; j <= seq.length(); j++) {
                    tmp.add(seq.substring(0, j) + "()" + seq.substring(j));
                }
            }
            res = tmp;
        }
        return res.stream().toList();
    }
}
