/**
 * Sorting service as ALDA project.
 * 
 * <br>
 * This package defines the types that are used by the teachers tests.
 * To make your implementation accessible by the teacher tests, you must 
 * make sure that your implementation declares it's {@code SortingsServicveFactory }
 * in a the file with this exact name <b>{@code src/main/resources/META-INF/services/sortingservice.SortingServiceFactory}</b>,
 * and with content the fully qualified name of the implementing factory class.
 * 
 * <img src='doc-files/sorters-cd.svg' alt='class diagram' >
 * 
 * <br>
 * The sorting service's sorters  are tested as follows:
 * <pre class='brush:java'>
 *      
   SortKind sortKind = getSortKind();
   Queue&lt;E&gt; queue = factory.createPreferredQueue( sortKind );
   for (E e: testData) {
      queue.put(w);
   }
   Comparator&lt;E&gt; comp = new CountingComparator&lt;&gt;( ... );
   Sorter&lt;E&gt; sorter = createSorter( comp );
   Queue&lt;E&gt; queueSorted= sorter.sort( queue );
   assertSame("I want my queue back", queueSorted, queue );
   E previous = queueSorted.get();
   long i = 1;
   String sorterName=sorter.getClass().getCanonicalName();
   while ( !queueSorted.isEmpty() ) {
      E current = queueSorted.get();
      assertTrue( "with sorter class "+sorterName
                  + ", element at position "
                  + i + " is less than its predecessor: previous="
                  + previous
                  + ", current=" + current,
                  previous.compareTo( current ) &lt;= 0 );
            i++;
    }
   </pre>
 */
package sortingservice;
