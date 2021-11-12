import java.util.ArrayList;
import java.util.Comparator;

public class DefaultProcessor extends ListingsProcessor {
    /**
     * A child of ListingsProcessor which uses Java's Collection.sort()'s
     * default sorting algorithm
     *
     * Returns a negative integer, zero, or a positive integer
     * as l1 is less than, equal to, or greater than l2 in terms
     * of the alphabetical order of their titles.
     *
     * @param listings the ArrayList of listings to be sorted
     * @param comparator a comparator object in which contains the
     *                   criteria for comparing listings
     * @return the sorted ArrayList of listings
     */
    protected ArrayList<Listing> sort(ArrayList<Listing> listings, Comparator<Listing> comparator) {
        ArrayList<Listing> sorted = (ArrayList<Listing>) listings.clone();
        sorted.sort(comparator);
        return sorted;
    }
}