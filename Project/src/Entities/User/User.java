package Entities.User;

import Entities.IEntry;
import Entities.Listing.JobListing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class User implements IEntry {
    private String name;
    private HashSet<JobListing> watchedJobListings;
    private String login;
    private String password;

    public String getName() {
        return name;
    }

    public User(String name){
        this(name, "demo", "demo");
    }

    public User(String name, String login, String password){
        this.name = name;
        watchedJobListings = new HashSet<>();
    }

    public boolean matchLogin(String login){
        return login.equals(this.login);
    }

    public boolean matchPassword(String password){
        return password.equals(this.password);
    }

    public HashSet<JobListing> getWatchedListings() {
        return watchedJobListings;
    }

    /**
     * Adds a listing to watchedListings. If listing is already in watchedListing, returns false, otherwise returns true.
     *
     * @param jobListing adds this listing to the user's watched listings
     * @return a boolean, false if listing is already in watchedListing, otherwise returns true.
     */
    public boolean addListingToWatch(JobListing jobListing){
        jobListing.setSaved(true);
        return !watchedJobListings.add(jobListing);
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
