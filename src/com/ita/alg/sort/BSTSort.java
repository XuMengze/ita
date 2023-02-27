package com.ita.alg.sort;

import com.ita.alg.tree.BST;

import java.util.List;

public class BSTSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        BST bst = new BST();
        for (int i = 0; i < arr.length; i++) {
            bst.insert(arr[i]);
        }
        bst.getRoot().print();
        List<Integer> r = bst.getKeySequence();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.get(i);
        }
        return arr;
    }
}
