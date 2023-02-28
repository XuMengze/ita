package com.ita.alg.tree;

import com.ita.alg.model.TreeNode;
import lombok.Getter;

public class AVL {
    public AVL() {
    }

    private static final String HEIGHT = "height";
    @Getter
    private TreeNode root;

    public void insert(int item) {
        TreeNode node = newNodeWithHeight(item);
        if (root == null) {
            root = node;
            return;
        }
        insertRecursive(root, item);
    }

    private void insertRecursive(TreeNode node, int item) {
        if (node.key >= item) {
            if (node.left == null) {
                node.left = newNodeWithHeight(item);
                node.left.parent = node;
                updateHeight(node);
                balanceTree(node);
            } else {
                insertRecursive(node.left, item);
            }
        } else {
            if (node.right == null) {
                node.right = newNodeWithHeight(item);
                node.right.parent = node;
                updateHeight(node);
                balanceTree(node);
            } else {
                insertRecursive(node.right, item);
            }
        }
    }

    private void updateHeight(TreeNode node) {
        if (node == null) {
            return;
        }
        node.extra.put(HEIGHT, Math.max(getHeight(node.left), getHeight(node.right)) + 1);
        updateHeight(node.parent);
    }

    private void balanceTree(TreeNode node) {
        if (node == null) {
            return;
        }
        switch (BalanceStatus.getNodeBalanceStatus(node)) {
            case BALANCED -> balanceTree(node.parent);
            case LEFT_HEAVY -> {
                BalanceStatus leftNodeBalancedStatus = BalanceStatus.getNodeBalanceStatus(node.left);
                if (leftNodeBalancedStatus == BalanceStatus.BALANCED || leftNodeBalancedStatus == BalanceStatus.LEFT_HEAVY) {
                    TreeNode result = rotateRight(node);
                    balanceTree(result.parent);
                } else {
                    node.left = rotateLeft(node.left);
                    TreeNode result = rotateRight(node);
                    balanceTree(result.parent);
                }
            }
            case RIGHT_HEAVY -> {
                BalanceStatus rightNodeBalancedStatus = BalanceStatus.getNodeBalanceStatus(node.right);
                if (rightNodeBalancedStatus == BalanceStatus.BALANCED || rightNodeBalancedStatus == BalanceStatus.RIGHT_HEAVY) {
                    TreeNode result = rotateLeft(node);
                    balanceTree(result.parent);
                } else {
                    node.right = rotateRight(node.right);
                    TreeNode result = rotateLeft(node);
                    balanceTree(result.parent);
                }
            }
        }
    }

    private TreeNode rotateLeft(TreeNode node) {
        TreeNode originalParent = node.parent;
        TreeNode right = node.right;
        node.right = right.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        right.left = node;
        node.parent = right;
        right.parent = originalParent;
        if (node == root) {
            root = right;
        } else {
            if (node == originalParent.left) {
                originalParent.left = right;
            } else {
                originalParent.right = right;
            }
        }
        node.extra.put(HEIGHT, Math.max(getHeight(node.left), getHeight(node.right)) + 1);
        right.extra.put(HEIGHT, Math.max(getHeight(right.left), getHeight(right.right)) + 1);
        return right;
    }

    private TreeNode rotateRight(TreeNode node) {
        TreeNode originalParent = node.parent;
        TreeNode left = node.left;
        node.left = left.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        left.right = node;
        node.parent = left;
        left.parent = originalParent;
        if (node == root) {
            root = left;
        } else {
            if (node == originalParent.left) {
                originalParent.left = left;
            } else {
                originalParent.right = left;
            }
        }
        node.extra.put(HEIGHT, Math.max(getHeight(node.left), getHeight(node.right)) + 1);
        left.extra.put(HEIGHT, Math.max(getHeight(left.left), getHeight(left.right)) + 1);
        return left;

    }

    private TreeNode newNodeWithHeight(int item) {
        TreeNode node = new TreeNode(item);
        node.extra.put(HEIGHT, 0);
        return node;
    }

    private static int getHeight(TreeNode node) {
        return node == null ? -1 : (Integer) node.extra.get(HEIGHT);
    }

    enum BalanceStatus {
        LEFT_HEAVY, RIGHT_HEAVY, BALANCED;

        public static BalanceStatus getNodeBalanceStatus(TreeNode node) {
            if (getHeight(node.right) - getHeight(node.left) >= 2) {
                return RIGHT_HEAVY;
            }
            if (getHeight(node.left) - getHeight(node.right) >= 2) {
                return LEFT_HEAVY;
            }
            return BALANCED;
        }
    }

    enum ChildStatus {
        BOTH, BOTH_NULL, LEFT_ONLY, RIGHT_ONLY;

        public static ChildStatus getChildStatus(TreeNode node) {
            if (node.left == null && node.right == null) {
                return BOTH_NULL;
            } else if (node.left == null) {
                return RIGHT_ONLY;
            } else if (node.right == null) {
                return LEFT_ONLY;
            } else {
                return BOTH;
            }
        }
    }

    public TreeNode prev(TreeNode node) {
        if (node.left != null) {
            TreeNode leftBiggest = node.left;
            while (leftBiggest.right != null) {
                leftBiggest = leftBiggest.right;
            }
            return leftBiggest;
        } else {
            if (node == node.parent.right) {
                return node.parent;
            } else {
                TreeNode p = node.parent;
                while (p.parent != null) {
                    if (p == p.parent.right) {
                        return p.parent;
                    } else {
                        p = p.parent;
                    }
                }
                return null;
            }
        }
    }

    public TreeNode next(TreeNode node) {
        if (node.right != null) {
            TreeNode rightSmallest = node.right;
            while (rightSmallest.left != null) {
                rightSmallest = rightSmallest.left;
            }
            return rightSmallest;
        } else {
            if (node.parent.left == node) {
                return node.parent;
            } else {
                TreeNode p = node.parent;
                while (p.parent != null) {
                    if (p == p.parent.left) {
                        return p;
                    } else {
                        p = p.parent;
                    }
                }
            }
            return null;
        }
    }

    public void delete(int item) {
        TreeNode node = findNode(item);
        if (node == null) {
            return;
        }
        delete(node);
    }

    public void delete(TreeNode node) {
        if (node == root) {
            switch (ChildStatus.getChildStatus(root)) {
                case BOTH_NULL -> root = null;
                case LEFT_ONLY -> root = root.left;
                case RIGHT_ONLY -> root = root.right;
                case BOTH -> {
                    TreeNode prev = prev(root);
                    Integer originalHeight = (Integer) root.extra.get(HEIGHT);
                    root.extra = prev.extra;
                    root.key = prev.key;
                    root.extra.put(HEIGHT, originalHeight);
                    delete(prev);
                }
            }
        }
        switch (ChildStatus.getChildStatus(node)) {
            case BOTH_NULL -> {
                TreeNode tmp = node.parent;
                if (node.parent.left == node) {
                    tmp.left = null;
                } else {
                    tmp.right = null;
                }
                updateHeight(tmp);
                balanceTree(tmp);
            }
            case LEFT_ONLY -> {
                TreeNode tmp = node.parent;
                if (node.parent.left == node) {
                    tmp.left = node.left;
                } else {
                    tmp.right = node.left;
                }
                updateHeight(tmp);
                balanceTree(tmp);
            }
            case RIGHT_ONLY -> {
                TreeNode tmp = node.parent;
                if (node.parent.left == node) {
                    tmp.left = node.right;
                } else {
                    tmp.right = node.right;
                }
                updateHeight(tmp);
                balanceTree(tmp);
            }
            case BOTH -> {
                TreeNode prev = prev(node);
                Integer originalHeight = (Integer) node.extra.get(HEIGHT);
                node.key = prev.key;
                node.extra = prev.extra;
                node.extra.put(HEIGHT, originalHeight);
                delete(prev);
            }
        }
    }

    private TreeNode findNode(int item) {
        return findNodeRecursive(root, item);
    }

    private TreeNode findNodeRecursive(TreeNode node, int item) {
        if (node == null) {
            return null;
        }
        if (node.key == item) {
            return node;
        } else if (item > node.key) {
            return findNodeRecursive(node.right, item);
        } else {
            return findNodeRecursive(node.left, item);
        }
    }
}
