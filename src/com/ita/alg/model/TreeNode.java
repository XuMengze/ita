package com.ita.alg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
    public int key;
    public Map<String, Object> extra = new HashMap<>();
    public TreeNode left, right, parent;

    public TreeNode(int val) {
        this.key = val;
    }

    public void print() {
        int treeHeight = height();
        print(new ArrayList<>() {{
            add(TreeNode.this);
        }}, treeHeight, (int) (Math.pow(2, treeHeight - 1)) * 3);
    }

    private void print(List<TreeNode> nodeList, int level, int maxLen) {
        if (level == 0) {
            return;
        }
        int singleSize = maxLen / nodeList.size();
        List<TreeNode> newNodeList = new ArrayList<>();
        for (TreeNode n : nodeList) {
            if (n == null) {
                newNodeList.add(null);
                newNodeList.add(null);
            } else {
                newNodeList.add(n.left);
                newNodeList.add(n.right);
            }
            for (int j = 0; j < singleSize / 2; j++) {
                System.out.print(" ");
            }
            System.out.print(n == null ? " " : n.key);
            for (int j = 0; j < singleSize / 2; j++) {
                System.out.print(" ");
            }

        }
        System.out.println();
        for (TreeNode n : nodeList) {
            for (int j = 0; j < singleSize / 2 - 1; j++) {
                System.out.print(" ");
            }
            if (n != null) {
                if (n.left == null && n.right == null) {
                    System.out.print("   ");
                } else if (n.left == null) {
                    System.out.print("  \\");
                } else if (n.right == null) {
                    System.out.print("/  ");
                } else {
                    System.out.print("/ \\");
                }
            }
            for (int j = 0; j < singleSize / 2 - 1; j++) {
                System.out.print(" ");
            }
        }
        System.out.println();
        print(newNodeList, level - 1, maxLen);
    }

    private int height() {
        return height(this);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}
