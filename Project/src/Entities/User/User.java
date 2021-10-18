package Entities.User;

import Entities.Listing.Listing;

import java.util.HashSet;

public class User {
    private String name;
    private HashSet<Integer> watchedListings;

    public String getName() {
        return name;
    }

    public User(String name){
        this.name = name;
        watchedListings = new HashSet<>();
    }

    public HashSet<Integer> getWatchedListings() {
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
        return !watchedListings.add(listing.getUID());
    }
}
