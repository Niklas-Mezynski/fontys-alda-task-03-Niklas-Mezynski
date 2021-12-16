package asortingservice;

import asortingservice.DoubleLinkedQueue.Node;
import sortingservice.Queue;
import sortingservice.Sorter;

import java.util.Comparator;

public class QuickSorter<T> implements Sorter<T> {

    private final Comparator<T> comp;
    private DoubleLinkedQueue<T> queue;

    QuickSorter(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override
    public Queue<T> sort(Queue<T> q) {
        if (!(q instanceof DoubleLinkedQueue)) {
            throw new UnsupportedOperationException("Wrong queue type");
        }
        DoubleLinkedQueue<T> queue = (DoubleLinkedQueue<T>) q;
        quicksort(queue);
        return queue;
    }

    public DoubleLinkedQueue<T> quicksort(DoubleLinkedQueue<T> q) {
        if (q.size() <= 1) {
            return q;
        }
        //Finding the middle pivot from 3 queue elements
        lessExch(q.tail.prev, q.head.next);
        lessExch(q.tail.prev, q.head.next.next);
        lessExch(q.head.next, q.head.next.next);
        T pivot = q.get();

        //intermediate queues for the elements smaller/equal/larger as the pivot
        DoubleLinkedQueue<T> less = new DoubleLinkedQueue<>();
        DoubleLinkedQueue<T> equal = new DoubleLinkedQueue<>();
        DoubleLinkedQueue<T> larger = new DoubleLinkedQueue<>();
        equal.put(pivot);
        while (!q.isEmpty()) {
            T element = q.get();
            if (comp.compare(element, pivot) < 0) {
                less.put(element);
            } else if (comp.compare(element, pivot) == 0) {
                equal.put(element);
            } else {
                larger.put(element);
            }
        }
        sort(less);
        sort(larger);

        while (!less.isEmpty()) {
            q.put(less.get());
        }
        while (!equal.isEmpty()) {
            q.put(equal.get());
        }
        while (!larger.isEmpty()) {
            q.put(larger.get());
        }

        return q;
    }

    //If a's element is smaller than b's -> swap the elemtns
    private void lessExch(Node<T> a, Node<T> b) {
        if (comp.compare(a.element, b.element) < 0) {
            T temp = a.element;
            a.element = b.element;
            b.element = temp;
        }
    }
}