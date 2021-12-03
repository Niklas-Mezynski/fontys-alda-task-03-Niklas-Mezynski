package sorterTests;

import sortingservice.SortKind;

public class HeapSorterTest extends SorterTestBase {
    @Override
    SortKind getSortKind() {
        return SortKind.HEAP;
    }
}
