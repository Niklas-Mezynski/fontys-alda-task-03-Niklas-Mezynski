package asortingservice;

import sortingservice.Queue;
import sortingservice.Sorter;

import java.util.Comparator;

public class InsertionSorter<T> implements Sorter<T> {

    private final Comparator<T> comp;

    public InsertionSorter(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override
    public Queue<T> sort(Queue<T> q) {
        if (!(q instanceof DoubleLinkedQueue))
            throw new UnsupportedOperationException("Queue type not supported");
        DoubleLinkedQueue<T> queue = (DoubleLinkedQueue<T>) q;

        DoubleLinkedQueue.Node<T> current = queue.head.next;
        for (int i = 1; i < queue.size(); i++) {
            DoubleLinkedQueue.Node<T> cursor = current.next;
            while (cursor.prev != queue.head) {
                if (comp.compare(cursor.element, cursor.prev.element) < 0) {
                    T temp = cursor.element;
                    cursor.element = cursor.prev.element;
                    cursor.prev.element = temp;
                    cursor = cursor.prev;
                } else {
                    break;
                }
            }
            current = current.next;
        }
        return queue;
    }
}
