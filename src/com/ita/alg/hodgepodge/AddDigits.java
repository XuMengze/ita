package com.ita.alg.hodgepodge;

public class AddDigits {
    public int addDigits(int num) {
        var res = 0;
        while (num != 0) {
            res += num % 10;
            num /= 10;
            if (num == 0 && res > 10) {
                num = res;
                res = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new AddDigits().addDigits(38));
    }
}
