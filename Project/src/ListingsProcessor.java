import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public abstract class ListingsProcessor {
    /**
     * abstract class ListingsProcessor containing template method processList and implementation
     * of steps which is the same for all types of processors. Those implementations have
     * been marked as final.
     * @param listings the ArrayList of listings to be filtered and sorted
     * @param filters an ArrayList of Predicates providing conditions for
     *                whether a given listing should be filtered out
     * @param comparator a comparator object in which contains the criteria
     *                   for comparing listings
     * @return the filtered and sorted ArrayList of listings
     */
    public final ArrayList<Listing> processList(ArrayList<Listing> listings, ArrayList<Predicate<Listing>> filters,
                                                Comparator<Listing> comparator) {
        ArrayList<Listing> filteredListings = filter(listings, filters);
        ArrayList<Listing> sortedListings = sort(filteredListings, comparator);
        return sortedListings;
    }
    /**
     * default method when no comparator is provided (sorts alphabetically ascending, A to Z by default)
     */
    public final ArrayList<Listing> processList(ArrayList<Listing> listings, ArrayList<Predicate<Listing>> filters) {
        filter(listings, filters);
        sort(listings, new AlphabeticalComparator());
        return listings;
    }
    /**
     * default method when no filters are provided
     */
    public final ArrayList<Listing> processList(ArrayList<Listing> listings, Comparator<Listing> comparator) {
        filter(listings, new ArrayList<>());
        sort(listings, comparator);
        return listings;
    }

    /**
     * Creates a new ArrayList containing only listings that meet the criteria given by filters
     *
     * @param listings the ArrayList of listings to be filtered and sorted
     * @param filters an ArrayList of Predicates providing conditions for
     *                whether a given listing should be filtered out
     * @return the filtered ArrayList of listings
     */
    private static ArrayList<Listing> filter(ArrayList<Listing> listings, ArrayList<Predicate<Listing>> filters) {
        // when no filters are given
        if(filters.size() == 0){
            return listings;
        }
        // combines all Predicates into a single composite Predicate
        Predicate<Listing> filter = filters.get(0);
        for(int i = 0; i < filters.size(); i++){
            filter.and(filters.get(i));
        }
        // filters listings by the composite predicate and returns an ArrayList containing those which pass
        return listings.stream().filter(filter).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    abstract ArrayList<Listing> sort(ArrayList<Listing> listings, Comparator<Listing> comparator);
}