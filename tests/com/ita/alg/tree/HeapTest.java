package com.ita.alg.tree;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    Heap h;

    @Before
    public void setup() {
        h = new Heap(new int[]{5, 2, 4, 6, 1, 2, 3});
        validate();
    }

    @Test
    public void testExtract() {
        int originalSize = h.getSize();
        h.extract();
        assert originalSize == h.getSize() + 1;
        validate();
    }

    void validate() {
        for (int i = 0; i < h.getSize() / 2 - 1; i++) {
            int left = i * 2 + 1 >= h.getSize() ? -1 : h.getHeap()[i * 2 + 1];
            int right = i * 2 + 2 >= h.getSize() ? -1 : h.getHeap()[i * 2 + 2];
            assert h.getHeap()[i] > left;
            assert h.getHeap()[i] > right;
        }
    }
}
