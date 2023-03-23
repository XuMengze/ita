package com.ita.alg.hodgepodge;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArithmeticSubarraysTest {
    @Test
    public void testArithmeticSubarrays() {
        List<Boolean> res = new ArithmeticSubarrays().checkArithmeticSubarrays(
                new int[]{-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10},
                new int[]{0, 1, 6, 4, 8, 7},
                new int[]{4, 4, 9, 7, 9, 10});
        Assert.assertArrayEquals(new Boolean[]{false, true, false, false, true, true}, res.toArray(new Boolean[0]));
    }
}
