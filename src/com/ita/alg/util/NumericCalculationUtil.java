package com.ita.alg.util;

public class NumericCalculationUtil {

    public static String intAdd(String n1, String n2) {
        return intAdd(n1, n2, 0);
    }

    public static String intAdd(String n1, String n2, int carry) {
        if (n1.length() < n2.length()) {
            String tmp = n2;
            n2 = n1;
            n1 = tmp;
        }
        n1 = new StringBuilder(n1).reverse().toString();
        n2 = new StringBuilder(n2).reverse().toString();
        StringBuilder res = new StringBuilder();
        // int carry = carry;
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

    public static String doubleAdd(String n1, String n2) {
        String[] number1 = n1.split("\\.");
        String[] number2 = n2.split("\\.");
        if (number1.length == 2 && number2.length == 2) {
            String number1Decimal = number1[1];
            String number2Decimal = number2[1];
            int zeroNumber = Math.abs(number1Decimal.length() - number2Decimal.length());
            String zeroes = "";
            for (int i = 0; i < zeroNumber; i++) {
                zeroes += "0";
            }
            if (number1Decimal.length() < number2Decimal.length()) {
                number1Decimal += zeroes;
            } else {
                number2Decimal += zeroes;
            }
            String decimalSum = intAdd(number1Decimal, number2Decimal);
            if (decimalSum.length() > number1Decimal.length()) {
                return intAdd(number1[0], number2[0], 1) + "." + decimalSum.substring(1);
            } else {
                return intAdd(number1[0], number2[0], 0) + "." + decimalSum;
            }
        } else if (number1.length == 2) {
            return intAdd(number1[0], number2[0], 0) + "." + number1[1];
        } else if (number2.length == 2) {
            return intAdd(number1[0], number2[0], 0) + "." + number2[1];
        } else {
            return intAdd(number1[0], number2[0], 0);
        }
    }
}
