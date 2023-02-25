package com.ita.alg.model;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    public int key;
    public Map<String, Object> extra = new HashMap<>();
    public TreeNode left, right, parent;

    public TreeNode(int val) {
        this.key = val;
    }
}
