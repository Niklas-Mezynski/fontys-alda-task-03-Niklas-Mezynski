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
            throw new UnsupportedOperationException("Wrong queue type for QuickSort - needs to be a doubly linked list");
        }
//        DoubleLinkedQueue<T> queue = (DoubleLinkedQueue<T>) q;
        queue = (DoubleLinkedQueue<T>) q;
        quicksort(queue.head.next, queue.tail.prev);
        StringBuilder sb = new StringBuilder();
        queue.forEach(element -> sb.append(element).append(","));
        System.out.println(sb);
        return queue;
    }

    private void quicksort(Node<T> left, Node<T> right) {
        if (touch(left, right)) {
            return;
        }
        exchange(getMiddle(left, right), left.next);
        compExch(left, right);
        compExch(left.next, right);
        compExch(left.next, left);
        Node<T> pivotNode = partition(left, right);
        quicksort(left, pivotNode.prev);
        quicksort(pivotNode.next, right);
    }

    private Node<T> partition(Node<T> left, Node<T> right) {
        Node<T> pivot = left;
        Node<T> l = left;
        Node<T> r = right.next;
        while (true) {
            l = l.next;
            while (less(l, pivot)) {
                l = l.next;
            }
            r = r.prev;
            while (less(pivot, r)) {
                r = r.prev;
            }
            if (touch(l, r)) {
                break;
            }
            exchange(l, r);
        }
        Node<T> leftOne = getLeft(l, r);
        exchange(leftOne, pivot);
        return leftOne;
    }

    private Node<T> getLeft(Node<T> l, Node<T> r) {
        if (l.prev == r || l == r) {
            return r;
        }
        return l;
    }

    private void compExch(Node<T> left, Node<T> right) {
        if (less(right, left)) {
            exchange(left, right);
        }
    }

    private boolean less(Node<T> a, Node<T> b) {
        return comp.compare(a.element, b.element) < 0;
    }

    private Node<T> getMiddle(Node<T> l, Node<T> r) {
        if (l.prev == r || l == r) {
            return r;
        }
        while (l.element != null) {
            l = l.next;
            if (l == r) {
                break;
            }
        }
        return l;
    }

    private boolean touch(Node<T> l, Node<T> r) {
        return l == r || l.prev == r;
    }

    private void exchange(Node<T> a, Node<T> b) {
        T temp = a.element;
        a.element = b.element;
        b.element = temp;
    }
}