package sorterTests;

import sortingservice.SortKind;

public class SelectionSorterTest extends SorterTestBase{
    @Override
    SortKind getSortKind() {
        return SortKind.SELECTION;
    }
}
