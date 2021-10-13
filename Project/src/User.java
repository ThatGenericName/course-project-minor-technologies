import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Listing> watchedListings;


    public User(String name){
        this.name = name;
        watchedListings = new ArrayList<Listing>();
    }

    /**
     * Adds a listing to watchedListings. If listing is already in watchedListing, returns false, otherwise returns true.
     *
     * @param listing
     * @return a boolean, false if listing is already in watchedListing, otherwise returns true.
     */
    public boolean addListingToWatch(Listing listing){
        throw new java.lang.UnsupportedOperationException();
    }
}
