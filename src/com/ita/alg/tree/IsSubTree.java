package com.ita.alg.tree;

import com.ita.alg.model.TreeNode;

public class IsSubTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isSubtree(root, subRoot, true);
    }

    private boolean isSubtree(TreeNode root, TreeNode subRoot, boolean enableSkip) {
        if (subRoot == null && root == null) {
            return true;
        }

        if (root == null || subRoot == null) {
            return false;
        }
        if (root.key != subRoot.key) {
            if (!enableSkip) {
                return false;
            }
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        } else {
            boolean res =  isSubtree(root.left, subRoot.left, false) && isSubtree(root.right, subRoot.right, false);
            if (!enableSkip) {
                return res;
            }
            return res || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }
}
