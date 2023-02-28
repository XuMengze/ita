package com.ita.alg.tree;

import com.ita.alg.model.TreeNode;
import org.junit.Before;
import org.junit.Test;

public class AVLTest {
    AVL avlTree;

    @Before
    public void setup() {
        avlTree = new AVL();
        for (Integer i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}) {
            avlTree.insert(i);
        }
    }

    @Test
    public void testAVLInsert() {
        assert isBalanced(avlTree.getRoot());
    }

    private static boolean isBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }
        return AVL.BalanceStatus.getNodeBalanceStatus(node) == AVL.BalanceStatus.BALANCED && isBalanced(node.left) && isBalanced(node.right);
    }

    @Test
    public void testAVLPrevAndNext() {
        assert avlTree.prev(avlTree.getRoot().left).key == 5;
        assert avlTree.next(avlTree.getRoot().left).key == 7;
    }
}
