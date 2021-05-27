package me.devksh930.datastructure.list.doubleLink;

public class Node {
    private int value;
    private Node prev;
    private Node next;

    public Node(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }

    public Node(int value, Node prev, Node next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
