package com.ita.alg.numeric;

import com.ita.alg.util.NumericCalculationUtil;
import org.junit.Test;

public class NumericCalculationUtilTest {
    @Test
    public void testAdd() {
        System.out.println(NumericCalculationUtil.intAdd("99", "1"));
    }
    @Test
    public void testMultiple() {
        System.out.println(NumericCalculationUtil.intMultiple("99", "1"));
    }
    @Test
    public void testAddDouble() {
        System.out.println(NumericCalculationUtil.doubleAdd("1", "1.6"));
    }
}
