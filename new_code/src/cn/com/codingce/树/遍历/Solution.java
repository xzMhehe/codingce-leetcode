package cn.com.codingce.树.遍历;

import cn.com.codingce.树.TreeNode;

public class Solution {

    public static void main(String[] args) {

    }

    /**
     * 前序列遍历
     *
     * @param node
     */
    public void preOrderTraverse(TreeNode node) {
        if(node==null)
            return;
        System.out.print(node.val);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private void inOrderTraverse(TreeNode node) {
        if(node==null)
            return;
        inOrderTraverse(node.left);
        System.out.print(node.val);
        inOrderTraverse(node.right);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    private void postOrderTraverse(TreeNode node) {
        if(node==null)
            return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.print(node.val);
    }
}
