package com.ita.alg.sort;

import com.ita.alg.model.BinarySearchTree;

import java.util.List;

public class BSTSort implements Sort {
    @Override
    public int[] sort(int[] arr) {
        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < arr.length; i++) {
            bst.insert(arr[i]);
        }
        List<Integer> r = bst.getKeySequence();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.get(i);
        }
        return arr;
    }
}
