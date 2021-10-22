package Entities.User;

import Entities.IEntry;
import Entities.Listing.Listing;

import java.util.HashMap;
import java.util.HashSet;

public class User implements IEntry {
    private String name;
    private HashSet<Listing> watchedListings;

    public String getName() {
        return name;
    }

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
     * @param listing adds this listing to the user's watched listings
     * @return a boolean, false if listing is already in watchedListing, otherwise returns true.
     */
    public boolean addListingToWatch(Listing listing){
        listing.setSaved(true);
        return !watchedListings.add(listing);
    }

    @Override
    public HashMap<String, Object> serialize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSerializedFileName() {
        throw new UnsupportedOperationException();
    }
}
