package com.ita.alg.util;

import com.ita.alg.model.TreeNode;
import org.junit.Test;

import java.util.Arrays;

public class TreeUtilTest {
    @Test
    public void testMarshal() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;

        System.out.println(Arrays.toString(TreeUtil.marshalWithKey(root)));
    }
    @Test
    public void testUnMarshal() {
        TreeNode root = TreeUtil.unmarshalWithKey(new String[]{"3", "4", "5", "1", "null", "null", "2" });
        System.out.println(root.key);
    }
}
