package me.devksh930.datastructure.list.doubleLink;

public class DoubleLinkedList {
    private Node head;

    public DoubleLinkedList() {
        this.head = null;
    }

    public void pringPrevNode(int value) {
        if (this.head == null) {
            System.out.println(" 이중 연결 리스트가 비어 있습니다");
            return;
        }
        if (this.head.getValue() == value) {
            System.out.println(String.format("노드 %s의 앞 노드는 존재하지 않습니다.", value));
            return;
        }

        Node pointer = this.head;
        while (pointer != null && pointer.getValue() != value) {
            pointer = pointer.getNext();
        }

        if (pointer == null) {
            System.out.println(String.format("노드 %s의 앞 노드는 존재하지 않습니다.", value));
        } else {
            System.out.println(String.format("노드 %s의 앞 노드의 값은 %s 입니다.", value, pointer.getPrev().getValue()));
        }
    }

    public void append(int value) {
        if (this.head == null) {
            this.head = new Node(value);
            return;
        }
        Node pointer = this.head;
        while (pointer.getNext() != null) {
            pointer = pointer.getNext();
        }

        Node newNode = new Node(value);
        newNode.setPrev(pointer);
        pointer.setNext(newNode);
    }
    public void printAll() {
        Node pointer = this.head;

        StringBuilder builder = new StringBuilder();
        while (pointer != null) {
            builder.append(pointer.getValue());
            builder.append(" <-> ");
            pointer = pointer.getNext();
        }

        builder.delete(builder.lastIndexOf(" <-> "), builder.length());
        System.out.println(builder.toString());
    }
}
