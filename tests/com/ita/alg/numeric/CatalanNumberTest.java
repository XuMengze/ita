package com.ita.alg.numeric;

import org.junit.Test;

public class CatalanNumberTest {
    @Test
    public void testCatalanNumber() {
        assert "16796".equals(CatalanNumber.getN(10));
        assert "4861946401452".equals(CatalanNumber.getN(25));
        assert "896519947090131496687170070074100632420837521538745909320".equals(CatalanNumber.getN(100));
    }
}
