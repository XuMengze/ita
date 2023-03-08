package com.ita.alg.numeric;

import org.junit.Test;

public class CatalanNumberTest {
    @Test
    public void testCatalanNumber() {
        assert "16796".equals(CatalanNumber.getN(10));
        assert "4861946401452".equals(CatalanNumber.getN(25));
    }
}
