package me.devksh930.datastructure.list.circuilar;

public class Node {
    private int value;
    private Node next;

    public void setNext(Node next) {
        this.next = next;
    }


    public Node getNext() {
        return next;
    }

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

}
