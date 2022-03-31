package cn.com.codingce.树.遍历;

import cn.com.codingce.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {
        // 层序遍历测试 SRART
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode root_left = new TreeNode();
        root_left.val = 3;
        TreeNode root_right = new TreeNode();
        root_right.val = 2;

        root.left = root_left;
        root.right = root_right;

        new Solution().levelOrder(root).forEach(str -> {
            System.out.println(str);
        });
        // 层序遍历测试 END
    }

    /**
     * 前序列遍历
     *
     * @param node
     */
    public void preOrderTraverse(TreeNode node) {
        if (node == null) return;
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
        if (node == null) return;
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
        if (node == null) return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.print(node.val);
    }

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        //根节点为空，直接返回res
        if (root == null) return res;
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                ArrayList<Integer> level = new ArrayList<Integer>();
                while (size-- > 0) {
                    TreeNode temp = queue.poll();
                    level.add(temp.val);
                    if (temp.left != null) queue.offer(temp.left);
                    if (temp.right != null) queue.offer(temp.right);
                }
                res.add(level);
            }
        }
        return res;
    }
}
