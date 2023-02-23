package com.ita.alg.tree;

import com.ita.alg.util.ArrayUtil;

public class TopN {
    int index = 0;
    int[] heap;

    public TopN(int size) {
        heap = new int[size];
    }

    public void push(int item) {
        if (index < this.heap.length) {
            heap[index++] = item;
            if (index == this.heap.length) {
                heapify();
            }
        } else {
            if (item < heap[0]) {
                return;
            }
            heap[0] = item;
            minHeapify(0);
        }
    }

    private void heapify() {
        if (heap.length <= 1) {
            return;
        }
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    private void minHeapify(int index) {
        int left = index * 2 + 1 <= heap.length - 1 ? heap[index * 2 + 1] : Integer.MAX_VALUE;
        int right = index * 2 + 2 <= heap.length - 1 ? heap[index * 2 + 2] : Integer.MAX_VALUE;
        if (Math.min(left, right) == Integer.MIN_VALUE) {
            return;
        }
        if (heap[index] < Math.min(left, right)) {
            return;
        }
        int target = Math.min(left, right) == left ? index * 2 + 1 : index * 2 + 2;
        ArrayUtil.swap(heap, index, target);
        minHeapify(target);
    }

    public int[] getTopN() {
        return heap;
    }
}
