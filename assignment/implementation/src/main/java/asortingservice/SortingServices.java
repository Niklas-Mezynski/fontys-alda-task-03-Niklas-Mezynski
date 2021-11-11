package asortingservice;

import java.util.Comparator;
import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SortingServiceFactory;

public class SortingServices implements SortingServiceFactory {

    @Override
    public Queue createPreferredQueue( SortKind forSorter ) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> Sorter<T> createSorter( SortKind kind, Comparator<T> comparator ) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
