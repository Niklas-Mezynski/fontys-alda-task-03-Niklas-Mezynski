package asortingservice;

import sortingservice.Queue;
import java.util.Iterator;

public class SimpleQueue<E> implements Queue<E> {

    Node<E> first;
    Node<E> last;
    private long size = 0;

    @Override
    public void put(E t) {
        Node<E> newNode = new Node<>(t);
        if (last == null) {
            first = newNode;
            last = first;
            size++;
            return;
        }
        last.next = newNode;
        last = newNode;
        size++;
    }

    @Override
    public E get() {
        E element = first.element;
        first = first.next;
        size--;
        if (size == 0) {
            last = null;
        }
        return element;
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

            Node<E> current;

            @Override
            public boolean hasNext() {
                return current != last;
            }

            @Override
            public E next() {
                if (current == null) {
                    current = first;
                    return first.element;
                }
                current = current.next;
                return current.element;
            }
        };
    }


    static class Node<E> {
        E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }
}
