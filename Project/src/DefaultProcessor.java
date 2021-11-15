import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DefaultProcessor extends ListingsProcessor {
    /**
     * A child of ListingsProcessor which implements abstract method sort()
     * using Java's Collection.sort()'s default sorting algorithm, which sorts
     * by ascending order.
     *
     * Note. I believe Java uses MergeSort by default (could be wrong)
     *
     * @param listings the ArrayList of listings to be sorted
     * @param comparator a comparator object in which contains the
     *                   criteria for comparing listings
     * @return the sorted ArrayList of listings
     */
    protected ArrayList<Listing> sort(ArrayList<Listing> listings, Comparator<Listing> comparator) {
        ArrayList<Listing> sorted = (ArrayList<Listing>) listings.clone();
        sorted.sort(comparator);
        // We reverse here because we choose to sort descending by default
        Collections.reverse(sorted);
        return sorted;
    }
}