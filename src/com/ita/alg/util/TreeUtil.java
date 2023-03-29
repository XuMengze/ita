package com.ita.alg.util;

import com.ita.alg.model.TreeNode;

import java.util.*;

public class TreeUtil {

    public static String[] marshalWithKey(TreeNode root) {
        if (root == null) {
            return new String[]{};
        }
        List<String> res = new ArrayList<>() {{
            add(root.key + "");
        }};
        List<TreeNode> watcher = new ArrayList<>() {{
            add(root);
        }};
        while (watcher.size() > 0) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode n : watcher) {
                if (n == null) {
                    continue;
                }
                tmp.add(n.left);
                tmp.add(n.right);
                res.add(n.left == null ? "null" : n.left.key + "");
                res.add(n.right == null ? "null" : n.right.key + "");
            }
            watcher = tmp;
        }
        return res.toArray(new String[0]);
    }

    public static TreeNode unmarshalWithKey(String[] marshalledTree) {
        if (marshalledTree.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(marshalledTree[0]));
        List<TreeNode> watcher = new ArrayList<>() {{
            add(root);
        }};
        int index = 1;
        while (index < marshalledTree.length) {
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode n : watcher) {
                if (!"null".equals(marshalledTree[index])) {
                    n.left = new TreeNode(Integer.parseInt(marshalledTree[index]));
                    tmp.add(n.left);
                }
                index++;
                if (index >= marshalledTree.length) {
                    break;
                }
                if (!"null".equals(marshalledTree[index])) {
                    n.right = new TreeNode(Integer.parseInt(marshalledTree[index]));
                    tmp.add(n.right);
                }
                index++;
                if (index >= marshalledTree.length) {
                    break;
                }
            }
            watcher = tmp;
        }
        return root;
    }

    public static int[] traversePreOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>() {{
            push(root);
        }};
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            res.add(node.key);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        int[] intRes = new int[res.size()];
        int index = 0;
        for (Integer i : res) {
            intRes[index++] = i;
        }
        return intRes;
    }

    public static int[] traversePostOrder(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>() {{
            push(root);
        }};
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            res.add(node.key);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        int[] intRes = new int[res.size()];
        int index = 0;
        for (Integer i : res) {
            intRes[index++] = i;
        }
        return intRes;
    }

    public static List<List<Integer>> traverseLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> levelTraverse = new ArrayList<>();
        levelTraverse.add(root);
        while (levelTraverse.size() != 0) {
            List<TreeNode> tmp = new ArrayList<>();
            List<Integer> thisLevelTraverse = new ArrayList<>();
            for (TreeNode n : levelTraverse) {
                thisLevelTraverse.add(n.key);
                if (n.left != null) {
                    tmp.add(n.left);
                }
                if (n.right != null) {
                    tmp.add(n.right);
                }
            }
            res.add(thisLevelTraverse);
            levelTraverse = tmp;
        }
        return res;
    }

    public boolean isCompleteTree(TreeNode root) {
        // write code here
        if (root == null) {
            return true;
        }
        int level = 0;
        List<TreeNode> n = new ArrayList<>() {{
            add(root);
        }};

        while (true) {
            if (n.size() == 1 << level++) {
                List<TreeNode> tmp = new ArrayList<>();
                for (TreeNode node : n) {
                    tmp.add(node.left);
                    tmp.add(node.right);
                }
                boolean canBeNull = true;
                for (int i = tmp.size() - 1; i >= 0; i--) {
                    if (tmp.get(i) == null) {
                        if (canBeNull) {
                            tmp.remove(i);
                        } else {
                            return false;
                        }
                    } else {
                        canBeNull = false;
                    }
                }
                n = tmp;
            } else {
                for (TreeNode node : n) {
                    if (!(node.left == null && node.right == null))
                        return false;
                }
                break;
            }
        }
        return true;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        return root == null || (Math.abs(height(root.left) - height(root.right)) <= 1 && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right));
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public int[] solve(int[] xianxu, int[] zhongxu) {
        TreeNode root = reconstructTree(xianxu, zhongxu, 0, xianxu.length - 1, 0, zhongxu.length - 1);
        List<List<Integer>> traverse = traverseLevelOrder(root);
        assert traverse != null;
        int[] res = new int[traverse.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = traverse.get(i).get(traverse.get(i).size() - 1);
        }
        return res;
    }

    private TreeNode reconstructTree(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart >= preOrder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preStart]);
        if (preEnd - preStart < 0) {
            return null;
        }
        if (preEnd - preStart == 0) {
            return root;
        }

        int index = inStart;
        while (inOrder[index] != preOrder[preStart]) {
            index++;
        }
        root.left = reconstructTree(preOrder, inOrder, preStart + 1, preStart + index - inStart, inStart, inStart + index - 1);
        root.right = reconstructTree(preOrder, inOrder, preStart + index - inStart + 1, preEnd, index + 1, inEnd);
        return root;
    }
}
