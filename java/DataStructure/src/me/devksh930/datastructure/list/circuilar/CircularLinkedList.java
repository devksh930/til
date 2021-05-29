package me.devksh930.datastructure.list.circuilar;

public class CircularLinkedList {
    private Node head;
    private Node tail;

    public CircularLinkedList() {
    }

    public CircularLinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public void append(int value) {
        //HEAD와 TAIL이 없을경우
        if (this.head == null && this.tail == null) {
            Node node = new Node(value);
            this.head = node;
            this.tail = node;
        }
        Node pointer = this.tail;
        //마지막위치에 추가
        pointer.setNext(new Node(value));
        this.tail = pointer.getNext();
        //tail의 next 포인터를 head로 연결
        this.tail.setNext(head);
    }

    public void printAll() {
        Node pointer = this.head;

        StringBuilder builder = new StringBuilder();
        while (pointer != this.tail) {
            builder.append(pointer.getValue());
            builder.append(" -> ");
            pointer = pointer.getNext();
        }
        builder.append(pointer.getValue());
        builder.append("(tail) -> ");

        builder.append(this.head.getValue());
        builder.append("(head)");
        System.out.println(builder.toString());

    }
}
