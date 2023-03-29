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

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, int leftBoundary, int rightBoundary) {
        if (root == null) {
            return true;
        }
        return root.key < rightBoundary && root.key > leftBoundary && isValidBST(root.left, leftBoundary, root.key) && isValidBST(root.right, root.key, rightBoundary);
    }

    public TreeNode Convert(TreeNode pRootOfTree) {
        return null;
    }
}
