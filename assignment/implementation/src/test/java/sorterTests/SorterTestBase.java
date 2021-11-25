package sorterTests;

import asortingservice.SortingServices;
import org.junit.jupiter.api.Test;
import sortingservice.Queue;
import sortingservice.SortKind;
import sortingservice.Sorter;
import sortingservice.SortingServiceFactory;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public abstract class SorterTestBase {
    private SortingServiceFactory factory = new SortingServices();

    abstract SortKind getSortKind();

    @Test
    void t01SimpleSortingTest() {
        //Creating and randomly filling the queue
        Queue<Integer> queue = factory.createPreferredQueue( getSortKind() );
        fillRandom( queue, 1000 );
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( getSortKind(),  comp );
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
        Queue<Integer> queue = factory.createPreferredQueue( getSortKind() );
        fillRandom( queue, 0 );
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( getSortKind(),  comp );
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
        assertThat(queue).isEmpty();
    }

    @Test
    void t03PreSortedQueue() {
        //Creating the queue
        Queue<Integer> queue = factory.createPreferredQueue( getSortKind() );
        queue.put(1);
        queue.put(2);
        queue.put(3);
        queue.put(7);
        queue.put(12);
        queue.put(13);
        queue.put(13);
        queue.put(13);
        queue.put(15);
        queue.put(20);
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( getSortKind(),  comp );
        //Sorting the queue and checking if its in right order
        assertThatCode(() -> sorter.sort( queue )).doesNotThrowAnyException();
        assertThat(queue).containsExactlyElementsOf(List.of(1,2,3,7,12,13,13,13,15,20));
    }

    @Test
    void t04AllElementsTheSame() {
        //Creating the queue
        Queue<Integer> queue = factory.createPreferredQueue( getSortKind() );
        List<Integer> sortedListForComparison = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            queue.put(3);
            sortedListForComparison.add(3);
        }
        //Creating the sorter
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( getSortKind(),  comp );
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
