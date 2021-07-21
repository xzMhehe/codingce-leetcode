package cn.com.codingce.数组.dfs;

import java.util.Scanner;

public class BiTree {
    
    private BiTree left, right;
    private char data;

    public BiTree creat(String des) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("des:" + des);

        String str = scanner.next();

        if (str.charAt(0) < 'a') return null;
        BiTree root = new BiTree();
        root.data = str.charAt(0);
        root.left = creat(str.charAt(0) + "左子树");
        root.right = creat(str.charAt(0) + "右子树");
        return root;
    }

    //中序遍历
    public void midprint(BiTree btree) {
        if (btree != null) {
            midprint(btree.left);
            System.out.print(btree.data + "  ");
            midprint(btree.right);
        }
    }

    //先序遍历

    public void firprint(BiTree btree) {
        if (btree != null) {
            System.out.print(btree.data + " ");
            firprint(btree.left);
            firprint(btree.right);
        }
    }

    //后序遍历
    public void lastPrint(BiTree btree) {

        if (btree != null) {
            lastPrint(btree.left);
            lastPrint(btree.right);
            System.out.print(btree.data + "  ");
        }
    }

    public static void main(String[] args) {
        BiTree tree = new BiTree();
        BiTree newtree = tree.creat("根节点");
        tree.firprint(newtree);
        System.out.println();
        tree.midprint(newtree);
        System.out.println();
        tree.lastPrint(newtree);
    }
}