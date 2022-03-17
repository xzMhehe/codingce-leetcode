package cn.com.codingce.树.从上往下打印二叉树;

import cn.com.codingce.树.TreeNode;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {

    }

    /**
     * 思路是用arraylist模拟一个队列来存储相应的TreeNode
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        //      8
        //   6     10
        //        2   1
        while (queue.size() != 0) {
            TreeNode temp = queue.remove(0);
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            list.add(temp.val);
        }
        return list;
    }
}
