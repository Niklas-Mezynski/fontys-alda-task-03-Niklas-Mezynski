package asortingservice;

import asortingservice.SimpleQueue.Node;
import sortingservice.Queue;
import sortingservice.Sorter;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class SelectionSorter<T> implements Sorter<T> {

    private final Comparator<T> comp;

    SelectionSorter(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override
    public Queue<T> sort(Queue<T> q) {
        if (!(q instanceof SimpleQueue))
            throw new UnsupportedOperationException("Wrong queue type");

        SimpleQueue<T> queue = (SimpleQueue<T>) q;

        //"Current" is the cursor for the first unsorted element in the queue
        Node<T> current = queue.first;
        //Outer loop iterating through the whole queue once
        while (current != null) {
            Node<T> smallest = current;
            Node<T> cursor = current.next;
            //Inner loop for searching the smallest element in the remaining unsorted queue
            while (cursor != null) {
                if (comp.compare(cursor.element, smallest.element) < 0) {
                    smallest = cursor;
                }
                cursor = cursor.next;
            }
            //Exchanging the first unsorted element with the smallest from the queue
            T smallestElement = smallest.element;
            smallest.element = current.element;
            current.element = smallestElement;
            //Going to the next iteration
            current = current.next;
        }
        return queue;
    }

}
