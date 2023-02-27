package com.ita.alg.tree;

import com.ita.alg.model.TreeNode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// BinarySearchTree
public class BST {
    @Getter
    private TreeNode root;

    public BST() {

    }

    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }
        insertRecursive(root, key);
    }

    private void insertRecursive(TreeNode node, int key) {
        if (key <= node.key) {
            if (node.left == null) {
                node.left = new TreeNode(key);
            } else {
                insertRecursive(node.left, key);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(key);
            } else {
                insertRecursive(node.right, key);
            }
        }
    }

    public List<Integer> getKeySequence() {
        List<Integer> result = new ArrayList<>();
        middleOrderTraverse(root, result);
        return result;
    }

    private void middleOrderTraverse(TreeNode node, List<Integer> elements) {
        if (node == null){
            return;
        }
        middleOrderTraverse(node.left, elements);
        elements.add(node.key);
        middleOrderTraverse(node.right, elements);
    }
}
