package me.devksh930.datastructure.list.singlLink;

public class SinglyLinkedList {
    private Node head;


    public SinglyLinkedList() {
        this.head = null;
    }

    public void printAll() {
        Node pointer = this.head;

        StringBuilder builder = new StringBuilder();
        while (pointer.getNext() != null) {
            builder.append(pointer.getValue());
            builder.append(" -> ");
            pointer = pointer.getNext();
        }

        builder.append(pointer.getValue());
        System.out.println(builder.toString());
    }

    public void append(int value) {// 노드 삽입
        if (this.head == null) { // 빈 리스트
            this.head = new Node(value, null);
            return;
        }
        Node pointer = this.head;
        while (pointer.getNext() != null) {
            pointer = pointer.getNext();
        }

        pointer.setNext(new Node(value, null));
    }

    public void delete(int value) { // 노드 삭제
        Node pointer = this.head;

        //삭제할 노드가 첫노드 인경우
        if (pointer.getValue() == value) {
            Node removeNode = this.head;
            this.head = this.head.getNext();

            removeNode = null;
            return;
        }
        Node temp = pointer; // 삭제될 노드의 전 노드 저장
        while (pointer != null && pointer.getValue() != value) {
            temp = pointer;
            pointer = pointer.getNext();
        }
        if (pointer.getNext() == null) {
            temp.setNext(null);
        } else {
            temp.setNext(pointer.getNext());
        }

        pointer = null;
    }
}
