package com.ita.alg.numeric;

public class SquareRoot {
    private static final double threshold = 10e-7;

    public static double getSquareRoot(int a) {
        double init = 0;
        double begin = a;
        while (true) {
            double iterativeResult = (begin * begin + a) / (2 * begin);
            if (Math.abs(iterativeResult - init) < threshold) {
                return iterativeResult;
            }
            init = iterativeResult;
            begin = iterativeResult;
        }
    }
}
