package com.ita.alg.backtrack;

import java.util.Arrays;

public class EightQueens {
    int solutions = 0;

    public int Nqueen(int n) {
        // write code here
        solutions = 0;
        setAndCheck("", n, 0);
        return solutions;
    }

    private void setAndCheck(String alreadyPlaced, int problemSpace, int step) {
        for (int i = 0; i < problemSpace; i++) {
            if (check(alreadyPlaced, i)) {
                if (step == problemSpace-1) {
                    solutions++;
                    return;
                }
                setAndCheck(alreadyPlaced + i, problemSpace, step + 1);
            }
        }
    }

    private boolean check(String alreadyPlaced, int n) {
        for (int i = 0; i < alreadyPlaced.length(); i++) {
            int checkInt = alreadyPlaced.charAt(i) - '0';
            if (n == checkInt) {
                return false;
            }
            if (Math.abs(alreadyPlaced.length() - i) == Math.abs(checkInt - n)) {
                return false;
            }
        }
        return true;
    }
}
