package sorterTests;

import sortingservice.SortKind;

public class InsertionSorterTest extends SorterTestBase{
    @Override
    SortKind getSortKind() {
        return SortKind.INSERTION;
    }
}
