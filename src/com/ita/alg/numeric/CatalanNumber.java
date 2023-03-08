package com.ita.alg.numeric;

import com.ita.alg.util.NumericCalculationUtil;

import java.util.ArrayList;
import java.util.List;

public class CatalanNumber {
    private static List<String> result = new ArrayList<>();

    static {
        result.add("1");
        result.add("1");
    }

    public static String getN(int n) {
        if (result.size() >= n) {
            return result.get(n);
        }
        for (int i = result.size(); i <= n; i++) {
            String r = "";
            for (int j = 0; j < i; j++) {
                r = NumericCalculationUtil.intAdd(r, NumericCalculationUtil.intMultiple(result.get(j), result.get(i - j - 1)));
            }
            result.add(r);
        }
        return result.get(n);
    }

}