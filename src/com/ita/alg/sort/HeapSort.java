package com.ita.alg.sort;

import com.ita.alg.tree.Heap;

import java.util.Arrays;

public class HeapSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        Heap h = new Heap(arr);
        while (h.getSize() > 1) {
            h.extract();
        }
        return Arrays.copyOf(h.getHeap(), h.getHeap().length);
    }
}
