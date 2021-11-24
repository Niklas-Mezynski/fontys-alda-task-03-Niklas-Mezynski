package asortingservice;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SortingServiceFactory;

public class SortingServices implements SortingServiceFactory {

    Map<SortKind, Supplier<Queue>> queueMap = Map.of(
            SortKind.SELECTION, SimpleQueue::new,
            SortKind.INSERTION, DoubleLinkedQueue::new,
            SortKind.QUICK, DoubleLinkedQueue::new
    );

    Map<SortKind, Function<Comparator, Sorter>> sorterMap = Map.of(
            SortKind.SELECTION, SelectionSorter::new,
            SortKind.INSERTION, InsertionSorter::new,
            SortKind.QUICK, QuickSorter::new
    );

    @Override
    public <T> Queue<T> createPreferredQueue(SortKind forSorter) {
        return queueMap.get(forSorter).get();
    }

    @Override
    public <T> Sorter<T> createSorter(SortKind kind, Comparator<T> comparator) {
        return sorterMap.get(kind).apply(comparator);
    }

    @Override
    public SortKind[] supportedSorters() {
        return new SortKind[]{
                SortKind.SELECTION,
                SortKind.INSERTION,
                SortKind.QUICK
        };
    }
}