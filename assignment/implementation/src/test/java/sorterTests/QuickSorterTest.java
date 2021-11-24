package sorterTests;

import sortingservice.SortKind;

public class QuickSorterTest extends SorterTestBase {
    @Override
    SortKind getSortKind() {
        return SortKind.QUICK;
    }
}
