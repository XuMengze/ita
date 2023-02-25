package com.ita.alg.hodgepodge;

import com.ita.alg.model.TreeNode;

public class RunwayReservationSystem {
    int start;
    int timeShift;

    // in this tree we only use left and right.
    TreeNode root;

    static final String SUB_TREE_SIZE_INCLUDING_SELF = "SUB_TREE_SIZE_INCLUDING_SELF";

    public RunwayReservationSystem(int start, int timeShift) {
        this.start = start;
        this.timeShift = timeShift;
    }

    public int countLandBefore(int time) {
        return countLandBeforeRecursive(root, time, 0);
    }

    private int countLandBeforeRecursive(TreeNode node, int target, int count) {
        if (node == null) {
            return count;
        }
        if (target >= node.key) {
            return countLandBeforeRecursive(node.right, target, count + 1 + extractSubTreeSize(node.left));
        } else {
            return countLandBeforeRecursive(node.left, target, count);
        }

    }

    public boolean tryArrange(int time) {
        if (time < start) {
            return false;
        }
        if (root == null) {
            root = new TreeNode(time);
            root.extra.put(SUB_TREE_SIZE_INCLUDING_SELF, 1);
            return true;
        }
        if (!arrange(root, time)) {
            return false;
        }
        updateSubTreeSize(root, time);
        return true;
    }

    private boolean arrange(TreeNode node, int time) {
        if (Math.abs(node.key - time) < timeShift) {
            return false;
        }
        if (time > node.key) {
            if (node.right != null) {
                return arrange(node.right, time);
            }
            node.right = new TreeNode(time);
        } else {
            if (node.left != null) {
                return arrange(node.left, time);
            }
            node.left = new TreeNode(time);
        }
        return true;
    }

    private void updateSubTreeSize(TreeNode node, int target) {
        node.extra.put(SUB_TREE_SIZE_INCLUDING_SELF, extractSubTreeSize(node) + 1);
        if (node.key == target) {
            return;
        }
        if (node.key > target) {
            updateSubTreeSize(node.left, target);
        } else {
            updateSubTreeSize(node.right, target);
        }
    }

    private Integer extractSubTreeSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.extra.get(SUB_TREE_SIZE_INCLUDING_SELF) == null ? 0 : (Integer) (node.extra.get(SUB_TREE_SIZE_INCLUDING_SELF));
    }
}
