package com.ita.alg.sort;

import com.ita.alg.tree.AVL;

import java.util.List;

public class AVLSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        AVL avlTree = new AVL();
        for (Integer i : arr
        ) {
            avlTree.insert(i);
        }
        List<Integer> r = avlTree.getRoot().getKeySequence();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.get(i);
        }
        return arr;
    }
}
