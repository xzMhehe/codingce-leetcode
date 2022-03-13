package cn.com.codingce.树.二叉树的下一个结点;

import cn.com.codingce.树.TreeLinkNode;

/**
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回 。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * @author mxz
 */
public class Solution {
    public static void main(String[] args) {

    }


    /**
     * 中序遍历的过程：先遍历树的左子树，再遍历根节点，最后再遍历右子树。
     * 所以最左节点是中序遍历的第一个节点。
     * void traverse(TreeNode root) {
     *     if (root == null) return;
     *     traverse(root.left);
     *     visit(root);
     *     traverse(root.right);
     * }
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }
}
