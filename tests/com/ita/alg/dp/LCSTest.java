package com.ita.alg.dp;

import org.junit.Test;

public class LCSTest {
    @Test
    public void testLCS() {
        assert new LCS().LCSubSequence("HIEROGLYPHOLOGY", "MICHAELANGELO").equals("HELLO");
    }
}
