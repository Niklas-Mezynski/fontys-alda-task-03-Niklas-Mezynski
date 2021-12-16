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
        if (q.size() <= 1) {
            return q;
        }
        DoubleLinkedQueue<T> less = new DoubleLinkedQueue<>();
        DoubleLinkedQueue<T> equal = new DoubleLinkedQueue<>();
        DoubleLinkedQueue<T> larger = new DoubleLinkedQueue<>();
        T pivot = q.get();
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
}