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
//        StreamSupport.stream(q.spliterator(), false).forEach(System.out::println);
        if (q.isEmpty())
            return q;

        SimpleQueue<T> temp = new SimpleQueue<>();
        while (!q.isEmpty()) {
            temp.put(q.get());
        }

        Node<T> current = temp.first;
        for (int i = 0; i < temp.size(); i++) {
            Node<T> smallest = current;
            Node<T> cursor = current.next;
            for (int j = i+1; j < temp.size(); j++) {
                if (comp.compare(cursor.element, smallest.element) < 0) {
                    smallest = cursor;
                }
                cursor = cursor.next;
            }
            T smallestElement = smallest.element;
            smallest.element = current.element;
            current.element = smallestElement;
            current = current.next;
            q.put(smallestElement);
        }
        return q;
    }

}
