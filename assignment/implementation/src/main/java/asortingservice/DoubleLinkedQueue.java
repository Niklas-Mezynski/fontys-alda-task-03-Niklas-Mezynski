package asortingservice;

import sortingservice.Queue;
import java.util.Iterator;

public class DoubleLinkedQueue<E> implements Queue<E> {

    Node<E> head;
    Node<E> tail;
    private long size = 0;

    public DoubleLinkedQueue() {
        //Queue with dummy head and tail
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void put(E t) {
        Node<E> newNode = new Node<>(t);
        tail.prev.next = newNode;
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev = newNode;
        size++;
    }

    @Override
    public E get() {
        E element = head.next.element;
        head.next = head.next.next;
        head.next.prev = head;
        size--;
        return element;
    }

    public void insertAfter(Node<E> node) {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current.next != tail;
            }

            @Override
            public E next() {
                current = current.next;
                return current.element;
            }
        };
    }


    static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(){}

        Node(E element) {
            this.element = element;
        }
    }
}
