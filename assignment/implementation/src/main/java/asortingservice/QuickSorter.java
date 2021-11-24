package asortingservice;

import asortingservice.DoubleLinkedQueue.Node;
import sortingservice.Queue;
import sortingservice.Sorter;

import java.util.Comparator;

public class QuickSorter<T> implements Sorter<T> {

    private final Comparator<T> comp;
    private Node<T> queueHead;
    private Node<T> queueTail;

    QuickSorter(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override
    public Queue<T> sort(Queue<T> q) {
        if (! (q instanceof DoubleLinkedQueue)) {
            throw new UnsupportedOperationException("Wrong queue type for QuickSort - needs to be a doubly linked list");
        }
        DoubleLinkedQueue<T> queue = (DoubleLinkedQueue<T>) q;
        queueHead = queue.head;
        queueTail = queue.tail;
        quicksort(queue.head.next, queue.tail.prev);

        return queue;
    }

    private void quicksort(Node<T> left, Node<T> right) {
        if (overlaps(left,right)) {
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
        T pivot = left.element;
        Node<T> l = left;
        Node<T> r = right.next;
        System.out.println("Pivot is: " + pivot );
        while (true) {
            l = l.next;
            while (less(l.element, pivot)) {
                l = l.next;
            }
            r = r.prev;
            while(less(pivot, r.element)) {
                r = r.prev;
            }
            if (isAfter(l,r)) {
                break;
            }
            System.out.println("Exchanged " + l.element + " with " + r.element);
            exchange(l, r);
        }
        exchange(left, r);
        return r;
    }

    //Get the middle node between two other nodes
    private Node<T> getMiddle(Node<T> left, Node<T> right) {
        while (true) {
            if (overlaps(left, right)) {
                return right;
            }
            left = left.next;
            right = right.prev;
        }
    }

    private boolean less(T a, T b) {
        return comp.compare(a, b) <= 0;
    }

    //Swaps two elements in the linked queue
    private void exchange(Node<T> a, Node<T> b) {
//        Node<T> bPrev = b.prev;
//        Node<T> bNext = b.next;
//        //Putting b in place
//        a.prev.next = b;
//        a.next.prev = b;
//        b.prev = a.prev;
//        b.next = a.next;
//
//        //Putting a in place
//        bPrev.next = a;
//        bNext.prev = a;
//        a.prev = bPrev;
//        a.next = bNext;
        T temp = a.element;
        a.element = b.element;
        b.element = temp;
    }

    //If a is greater than b --> Swap the elements
    private void compExch(Node<T> a, Node<T> b) {
        if (less(b.element,a.element))
            exchange(a,b);
    }

    //Check if the left node already skipped the right node
    private boolean overlaps(Node<T> left, Node<T> right) {
        return left == right || left.prev == right;
    }

    //Checks if left node comes after the right node
    private boolean isAfter(Node<T> left, Node<T> right) {
        while (right.element != null) {
            if (left == right) {
                return true;
            }
            right = right.next;
        }
        return false;
    }
}
