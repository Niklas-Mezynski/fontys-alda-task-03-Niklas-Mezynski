package sorterTests;

import asortingservice.SortingServices;
import org.junit.jupiter.api.Test;
import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SortingServiceFactory;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class SelectionSorterTest {

    private SortingServiceFactory factory = new SortingServices();

    @Test
    void t01SimpleSortingTest() {
        //Creating and randomly filling the queue
        SortKind sortKind = SortKind.SELECTION;
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        fillRandom( queue, 100 );
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

    @Test
    void t02EmptyQueueTest() {
        //Creating the queue
        SortKind sortKind = SortKind.SELECTION;
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        fillRandom( queue, 0 );
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
    }

    @Test
    void t03PreSortedQueue() {
        //Creating the queue
        SortKind sortKind = SortKind.SELECTION;
        Queue<Integer> queue = factory.createPreferredQueue( sortKind );
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(7);
        queue.put(12);
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        //Creating an ArrayList and sort it in order to compare later on
        List<Integer> sortedListForComparison = new ArrayList<>();
        queue.forEach(sortedListForComparison::add);
        Collections.sort(sortedListForComparison);
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
        assertThat(queue).containsExactlyElementsOf(List.of(1,2,3,7,12));
    }

    private void fillRandom(Queue<Integer> queue, int i) {
        Random random = new Random(69);
        for (int j = 0; j < i; j++) {
            queue.put(random.nextInt(i));
        }
    }
}