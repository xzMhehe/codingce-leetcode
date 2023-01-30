package cn.com.codingce.树.重建二叉树;

import cn.com.codingce.树.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().reConstructBinaryTree(
                new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                new int[]{4, 7, 2, 1, 5, 3, 8, 6}));

    }

    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();


    /**
     * @param pre 先根遍历
     * @param in  中根遍历
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++)
            // 4 0 ｜ 7 1 ｜ 2 3 | ...
            indexForInOrders.put(in[i], i);
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    /**
     * @param pre  先根遍历
     * @param preL
     * @param preR
     * @param inL
     * @return
     */
    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param preOrder int整型一维数组
     * @param vinOrder int整型一维数组
     * @return TreeNode类
     */
    public TreeNode reConstructBinaryTreeT(int[] preOrder, int[] vinOrder) {
        // write code here
        int n = preOrder.length;
        int m = vinOrder.length;
        //每个遍历都不能为0
        if (n == 0 || m == 0)
            return null;
        //构建根节点
        TreeNode root = new TreeNode(preOrder[0]);
        for (int i = 0; i < vinOrder.length; i++) {
            //找到中序遍历中的前序第一个元素
            if (preOrder[0] == vinOrder[i]) {
                //构建左子树
                root.left = reConstructBinaryTreeT(Arrays.copyOfRange(preOrder, 1, i + 1), Arrays.copyOfRange(vinOrder, 0, i));
                //构建右子树
                root.right = reConstructBinaryTreeT(Arrays.copyOfRange(preOrder, i + 1, preOrder.length), Arrays.copyOfRange(vinOrder, i + 1, vinOrder.length));
                break;
            }
        }
        return root;
    }
}
