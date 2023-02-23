package com.ita.alg.tree;

import com.ita.alg.util.ArrayUtil;
import lombok.Getter;

import java.util.Arrays;

public class Heap {

    @Getter
    private int[] heap;
    @Getter
    private int size;

    public Heap(int[] heap) {
        this.heap = Arrays.copyOf(heap, heap.length);
        this.size = heap.length;
        heapify();
    }

    private void heapify() {
        if (heap.length <= 1) {
            return;
        }
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    private void maxHeapify(int index) {
        int left = index * 2 + 1 <= size - 1 ? heap[index * 2 + 1] : -1;
        int right = index * 2 + 2 <= size - 1 ? heap[index * 2 + 2] : -1;
        int max = Math.max(left, right);
        if (max == -1) {
            return;
        }
        if (heap[index] > max) {
            return;
        }
        int target = max == left ? index * 2 + 1 : index * 2 + 2;
        ArrayUtil.swap(heap, index, target);
        maxHeapify(target);
    }

    public void extract() {
        ArrayUtil.swap(heap, 0, size - 1);
        size--;
        maxHeapify(0);
    }
}
