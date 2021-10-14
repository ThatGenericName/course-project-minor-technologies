import java.util.ArrayList;
import java.util.HashSet;

public class User {
    private String name;
    private HashSet<Listing> watchedListings;


    public User(String name){
        this.name = name;
        watchedListings = new HashSet<>();
    }

    public HashSet<Listing> getWatchedListings() {
        return watchedListings;
    }

    /**
     * Adds a listing to watchedListings. If listing is already in watchedListing, returns false, otherwise returns true.
     *
     * @param listing
     * @return a boolean, false if listing is already in watchedListing, otherwise returns true.
     */
    public boolean addListingToWatch(Listing listing){
        return !watchedListings.add(listing);
    }
}
