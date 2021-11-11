package sortingservice;

import java.util.Comparator;
import static sortingservice.SortKind.*;

/**
 * Abstract factory for sorters a and queue implementation classes.
 * The implementing class provides mandatory and optional sorters a student may want to implement.
 *
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
public interface SortingServiceFactory {

    /**
     * Get an instance of the preferred queue type for a sorter kind.If no such
     * queue exists, return null.Do NOT throw any exceptions.
     *
     * @param <T> type of queue
     * @param forSorter kind of sorter, like QUICK, SELECTION, {@link sortingservice.SortKind SortKind
     * }
     * @return the instance
     */
    <T> Queue<T> createPreferredQueue( SortKind forSorter );

    /**
     * Create a sorter service. If no sorter for of the given SortKind exists,
     * return null. Do NOT throw any exceptions.
     *
     * @param <T> type of elements in the queues
     * @param kind SortKind, sorter characteristic like INSERTION or QUICK
     * @param comparator to use in determining the order
     * @return a sorter that sorts a given queue by the order imposed by the
     * comparator.
     */
    <T> Sorter<T> createSorter( SortKind kind, Comparator<T> comparator );

    /**
     * Get the supported sorter kinds of this service.
     *
     * The values returned determine which sorters will be subject to the
     * teacher tests.
     *
     * By default the service is supposed to support all kinds. Overwrite this
     * method when you are not yet done, by returning the subset you DO support.
     *
     * @return the supported sorters.
     */
    default SortKind[] supportedSorters() {
        return SortKind.values();
    }

    /**
     * Provide optional extra sorters for demo purposes.
     *
     * @return the names of the sorters.
     */
    default String[] extraSorters() {
        return new String[]{};
    }

    /**
     * Create a sorter service. If no sorter for of the given SortKind exists,
     * return null. Do NOT throw any exceptions.
     *
     * @param <T> type of elements in the queues
     * @param sorterKind Name of extra sorter sorter characteristic like INSERTION or QUICK
     * @param comparator to use in determining the order
     * @return a sorter that sorts a given queue by the order imposed by the
     * comparator.
     */
    default <T> Sorter<T> createSorter( String sorterKind, Comparator<T> comparator ) {
        return null;
    }

    /**
     * Get an instance of the preferred queue type for a sorter kind. If no such
     * queue exists, return null. Do NOT throw any exceptions.
     *
     * @param <T> type of queue
     * @param forSorter kind of sorter, like QUICK, SELECTION, {@link sortingservice.SortKind SortKind
     * }
     * @return the instance
     */
    default <T> Queue<T> createPreferredQueue( String forSorter ) {
        return null;
    }

}
