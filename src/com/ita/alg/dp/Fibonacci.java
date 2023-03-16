package com.ita.alg.dp;

public class Fibonacci {
    public static int fib(int n) {
        if (n <= 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fib3(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] memo = new int[n+1];
        memo[0] = 1;
        memo[1] = 1;

        return fib3R(n, memo);
    }

    private static int fib3R(int n, int[] memo) {
        int first = memo[n - 2] == 0 ? fib3R(n - 2, memo) : memo[n - 2];
        int second = memo[n - 1] == 0 ? fib3R(n - 1, memo) : memo[n - 1];
        memo[n - 2] = first;
        memo[n - 1] = second;
        memo[n] = first + second;
        return memo[n];
    }

    public static int fib2(int n) {
        if (n <= 1) {
            return 1;
        }
        int first = 1, second = 1;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }

}
