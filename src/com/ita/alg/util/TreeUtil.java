package com.ita.alg.util;

import com.ita.alg.model.TreeNode;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

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
                if (!"null".equals(marshalledTree[index])) {
                    n.right = new TreeNode(Integer.parseInt(marshalledTree[index]));
                    tmp.add(n.right);
                }
                index++;
            }
            watcher = tmp;
        }
        return root;
    }
}
