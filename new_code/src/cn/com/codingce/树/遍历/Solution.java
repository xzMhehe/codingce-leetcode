package cn.com.codingce.树.遍历;

import cn.com.codingce.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的遍历
 *
 * @author inke219223m
 */
public class Solution {

    public static void main(String[] args) {
        // 层序遍历测试 SRART
        System.out.println("==========层序遍历测试 SRART==========");
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode root_left = new TreeNode();
        root_left.val = 3;
        TreeNode root_right = new TreeNode();
        root_right.val = 2;

        root.left = root_left;
        root.right = root_right;


        TreeNode root_left_left = new TreeNode();
        root_left_left.val = 4;
        root_left.left = root_left_left;

        new Solution().levelOrder(root).forEach(System.out::println);
        // 层序遍历测试 END
        System.out.println();
        // 前序遍历 START
        System.out.println("==========前序遍历 START==========");

        new Solution().preOrderTraverse(root);
        System.out.println();
        // 前序遍历 END
        System.out.println();
        // 中序遍历 START
        System.out.println("==========中序遍历 START==========");

        new Solution().inOrderTraverse(root);
        System.out.println();
        // 中序遍历 END
        System.out.println();

        // 后序遍历 START
        System.out.println("==========后序遍历 START==========");

        new Solution().postOrderTraverse(root);
        System.out.println();
        // 后序遍历 END

    }

    /**
     * 层序遍历（BFS思想）
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
            // 用先进先出的队列
            Queue<TreeNode> queue = new LinkedList<>();
            // 在容量已满的情况下，add() 方法会抛出IllegalStateException异常，offer() 方法只会返回 false。
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                ArrayList<Integer> level = new ArrayList<Integer>();
                while (size-- > 0) {
                    // 在队列元素为空的情况下，remove() 方法会抛出NoSuchElementException异常，poll() 方法只会返回 null 。
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

    /**
     * 前序列遍历
     *
     * @param node
     */
    public void preOrderTraverse(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + "\t");
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
        System.out.print(node.val + "\t");
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
        System.out.print(node.val + "\t");
    }

}
