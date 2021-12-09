package sorterTests;

import asortingservice.SortingServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SortingServiceFactory;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class SorterTests {

    private static SortingServiceFactory factory = new SortingServices();

    private static Stream<SortKind> getSortKinds() {
        return Stream.of(factory.supportedSorters());
    }

    @ParameterizedTest
    @MethodSource("getSortKinds")
    void t01SimpleSortingTest(SortKind sortKind) {
        //Creating and randomly filling the queue
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        fillRandom( queue, 10000 );
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        //Creating an ArrayList and sort it in order to compare later on
        List<Integer> sortedListForComparison = new ArrayList<>();
        queue.forEach(sortedListForComparison::add);
        Collections.sort(sortedListForComparison);
        //Sorting the queue and checking if its in right order
        Queue<Integer> sortedQueue = sorter.sort( queue );
        assertThat( sortedQueue ).isSameAs( queue );
        assertThat(sortedQueue).containsExactlyElementsOf(sortedListForComparison);
    }

    @ParameterizedTest
    @MethodSource("getSortKinds")
    void t02EmptyQueueTest(SortKind sortKind) {
        //Creating the queue
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        fillRandom( queue, 0 );
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
        assertThat(queue).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("getSortKinds")
    void t03PreSortedQueue(SortKind sortKind) {
        //Creating an ArrayList and sort it in order to compare later on
        List<Integer> sortedListForComparison = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            sortedListForComparison.add(random.nextInt(100));
        }
        Collections.sort(sortedListForComparison);
        //Creating the queue and filling it with the pre sorted elements
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        sortedListForComparison.forEach(queue::put);
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
        assertThat(queue).containsExactlyElementsOf(sortedListForComparison);
    }

    @ParameterizedTest
    @MethodSource("getSortKinds")
    void t04AllElementsTheSame(SortKind sortKind) {
        //Creating the queue
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        List<Integer> sortedListForComparison = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            queue.put(3);
            sortedListForComparison.add(3);
        }
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
        assertThat(queue).containsExactlyElementsOf(sortedListForComparison);
    }

    private void fillRandom(Queue<Integer> queue, int i) {
        Random random = new Random();
        for (int j = 0; j < i; j++) {
            queue.put(random.nextInt(i));
        }
    }
}
