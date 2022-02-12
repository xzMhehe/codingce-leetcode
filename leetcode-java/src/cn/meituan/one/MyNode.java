package cn.meituan.one;


public class MyNode {

    public static void main(String[] args) {

        MyNode head = new MyNode("我是单链表头");
        head.next = new MyNode("我是第二个", new MyNode("我是第三个", null));


        System.out.println(head);


    }

    private String value;
    private MyNode next;

    public MyNode(String value, MyNode next) {
        this.value = value;
        this.next = next;
    }

    public MyNode(String value) {
        this.value = value;
    }

    public MyNode() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "value='" + value + '\'' +
                ", next=" + next +
                '}';
    }
}
