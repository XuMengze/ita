package com.ita.alg.strings;

import org.junit.Test;

public class StringMatchTest {

    private static final String text1 = "babababababb";
    private static final String pattern1 = "bababb";
    private static final int expectReturn1 = 6;

    private static final String text2 = "babababababb";
    private static final String pattern2 = "aba";
    private static final int expectReturn2 = 1;


    private static final String text3 = "babababababb";
    private static final String pattern3 = "bababa";
    private static final int expectReturn3 = 0;

    int notFind = -1;

    @Test
    public void testCornerCase(){
        assert notFind == StringMatch.getFirst(Method.TEDIOUS, "", "1");
        assert notFind == StringMatch.getFirst(Method.TEDIOUS, "abcf", "");
        assert notFind == StringMatch.getFirst(Method.TEDIOUS, "", "");
    }
    @Test
    public void testMatchTedious() {
        assert expectReturn1 == StringMatch.getFirst(Method.TEDIOUS, text1, pattern1);
        assert expectReturn2 == StringMatch.getFirst(Method.TEDIOUS, text2, pattern2);
        assert expectReturn3 == StringMatch.getFirst(Method.TEDIOUS, text3, pattern3);
    }
    @Test
    public void testMatchKarpRabin() {
        assert expectReturn1 == StringMatch.getFirst(Method.KARP_RABIN, text1, pattern1);
        assert expectReturn2 == StringMatch.getFirst(Method.KARP_RABIN, text2, pattern2);
        assert expectReturn3 == StringMatch.getFirst(Method.KARP_RABIN, text3, pattern3);
    }
}
