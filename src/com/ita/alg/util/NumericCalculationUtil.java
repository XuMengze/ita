package com.ita.alg.util;

public class NumericCalculationUtil {
    public static String intAdd(String n1, String n2) {
        if (n1.length() < n2.length()) {
            String tmp = n2;
            n2 = n1;
            n1 = tmp;
        }
        n1 = new StringBuilder(n1).reverse().toString();
        n2 = new StringBuilder(n2).reverse().toString();
        StringBuilder res = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < n2.length(); i++) {
            int digit = Integer.parseInt(n1.charAt(i) + "") + Integer.parseInt(n2.charAt(i) + "") + carry;
            carry = digit / 10;
            res.append(digit % 10);
        }
        for (int i = n2.length(); i < n1.length(); i++) {
            int digit = Integer.parseInt(n1.charAt(i) + "") + carry;
            carry = digit / 10;
            res.append(digit % 10);
        }
        if (carry != 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public static String intMultiple(String n1, String n2) {
        if (n1.length() < n2.length()) {
            String tmp = n2;
            n2 = n1;
            n1 = tmp;
        }
        n2 = new StringBuilder(n2).reverse().toString();
        String res = "0";
        for (int i = 0; i < n2.length(); i++) {
            res = intAdd(res, singleBitMultiple(n1, Integer.parseInt(n2.charAt(i) + ""), i));
        }
        return res;
    }

    private static String singleBitMultiple(String n, int times, int leadingZero) {
        if (times == 0) {
            return "0";
        }
        n = new StringBuilder(n).reverse().toString();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < leadingZero; i++) {
            res.append("0");
        }
        int carry = 0;
        for (int i = 0; i < n.length(); i++) {
            int digit = Integer.parseInt(n.charAt(i) + "") * times + carry;
            res.append(digit % 10);
            carry = digit / 10;
        }
        if (carry != 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }
}
