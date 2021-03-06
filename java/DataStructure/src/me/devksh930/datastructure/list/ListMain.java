package me.devksh930.datastructure.list;

import me.devksh930.datastructure.list.circuilar.CircularLinkedList;
import me.devksh930.datastructure.list.doubleLink.DoubleLinkedList;
import me.devksh930.datastructure.list.singlLink.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
//        SinglyLinkedList singlyLinkedList = initSingleList();
//        singleLinkedListDelete(singlyLinkedList);
//        DoubleLinkedList doubleLinkedList = initDoubleLinkedList();
        CircularLinkedList circularLinkedList = initCircularLinkedList();
    }

    public static SinglyLinkedList initSingleList() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.append(12);
        singlyLinkedList.append(49);
        singlyLinkedList.append(21);
        singlyLinkedList.append(23);
        singlyLinkedList.append(42);
        singlyLinkedList.append(87);
        singlyLinkedList.append(54);
        singlyLinkedList.printAll();
        return singlyLinkedList;
    }


    public static void singleLinkedListDelete(SinglyLinkedList singlyLinkedList) { // 단일 연결리스트 삭제

        //삭제
        System.out.println("노드 12 삭제 ");
        singlyLinkedList.delete(12);
        singlyLinkedList.printAll();

        System.out.println("노드 21 삭제 ");
        singlyLinkedList.delete(21);
        singlyLinkedList.printAll();

        System.out.println("노드 23 삭제 ");
        singlyLinkedList.delete(23);
        singlyLinkedList.printAll();

        System.out.println("노드 87 삭제 ");
        singlyLinkedList.delete(87);
        singlyLinkedList.printAll();

        System.out.println("노드 54 삭제 ");
        singlyLinkedList.delete(54);
        singlyLinkedList.printAll();
    }

    public static DoubleLinkedList initDoubleLinkedList() {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.append(1);
        doubleLinkedList.append(3);
        doubleLinkedList.append(2);
        doubleLinkedList.append(20);
        doubleLinkedList.append(19);

        System.out.println("이중 연결 리스트");

        doubleLinkedList.printAll();
        doubleLinkedList.printPrevNode(2);
        doubleLinkedList.printPrevNode(1);
        doubleLinkedList.printPrevNode(19);

        return doubleLinkedList;
    }

    public static void doubleLinkedListDelete(DoubleLinkedList doubleLinkedList) {
        doubleLinkedList.delete(1);
    }

    public static CircularLinkedList initCircularLinkedList() {
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.append(1);
        circularLinkedList.append(3);
        circularLinkedList.append(5);
        circularLinkedList.append(4);
        circularLinkedList.append(7);
        circularLinkedList.append(6);

        circularLinkedList.printAll();
        return circularLinkedList;
    }
}
