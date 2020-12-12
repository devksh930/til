package Homework2;

public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T item) {
        data = item;
        next = null;
    }
}
