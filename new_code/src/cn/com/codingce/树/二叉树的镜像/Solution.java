package cn.com.codingce.树.二叉树的镜像;

import cn.com.codingce.树.TreeNode;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * @author mxz
 */
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pRoot TreeNode类
     * @return TreeNode类
     */
    public TreeNode Mirror (TreeNode pRoot) {
        if (pRoot == null) {
            return pRoot;
        }
        swap(pRoot);
        Mirror(pRoot.left);
        Mirror(pRoot.right);
        return pRoot;
    }

    public void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }
}
