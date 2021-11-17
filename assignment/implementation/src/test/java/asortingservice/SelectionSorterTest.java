package asortingservice;

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
        List<Integer> sortedListForComparison = new ArrayList<>();
        fillRandom( queue, 100 );
        queue.forEach(sortedListForComparison::add);
        Collections.sort(sortedListForComparison);
        Comparator<Integer> comp = Comparator.naturalOrder();
        Sorter<Integer> sorter = factory.createSorter( sortKind,  comp );
        Queue<Integer> sortedQueue = sorter.sort( queue );
        assertThat( sortedQueue ).isSameAs( queue );
        assertThat(sortedQueue).containsExactlyElementsOf(sortedListForComparison);
    }

    private void fillRandom(Queue<Integer> queue, int i) {
        Random random = new Random(69);
        for (int j = 0; j < i; j++) {
            queue.put(random.nextInt(i));
        }
    }
}
