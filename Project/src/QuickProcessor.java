import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuickProcessor extends ListingsProcessor {
    /**
     * A child of ListingsProcessor which implements abstract method sort()
     * using the QuickSort sorting algorithm, and sorts by ascending order.
     *
     * @param listings   the ArrayList of listings to be sorted
     * @param comparator a comparator object in which contains the
     *                   criteria for comparing listings
     * @return the sorted ArrayList of listings
     */
    protected ArrayList<Listing> sort(ArrayList<Listing> listings, Comparator<Listing> comparator) {
        // Don't need to sort
        if (listings.size() <= 1)
            return listings;

        ArrayList<Listing> lesser = new ArrayList<>();
        ArrayList<Listing> greater = new ArrayList<>();

        // Pivot set to last listing
        Listing pivot = listings.get(listings.size() - 1);
        for (int i = 0; i < listings.size() - 1; i++) {
            // >= because we sort by descending
            if (comparator.compare(listings.get(i), pivot) >= 0)
                lesser.add(listings.get(i));
            else
                greater.add(listings.get(i));
        }

        lesser = sort(lesser, comparator);
        greater = sort(greater, comparator);

        lesser.add(pivot);
        lesser.addAll(greater);
        return lesser;
    }
}