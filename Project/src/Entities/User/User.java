package Entities.User;

import Entities.IEntry;
import Entities.Listing.JobListing;
import UseCase.Security.Security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

public class User implements IEntry {
    private String name;
    private HashSet<JobListing> watchedJobListings;
    private String login;
    private String password;

    public String getUuid() {
        return uuid;
    }

    private final String uuid;
    private String salt;

    public String getName() {
        return name;
    }

    public String getLogin(){
        return login;
    }

    public User(String login){
        this("demo", login, null, null);
        byte[] saltArr = Security.generateSalt();
        salt = Security.toHex(saltArr);
        password = Security.toHex(Security.generateHash("demo", saltArr));
    }

    public User(String name, String login, String passwordHash, String salt){
        this.name = name;
        watchedJobListings = new HashSet<>();
        this.uuid = UUID.randomUUID().toString();
        this.password = passwordHash;
        this.salt = salt;
        this.login = login;
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
    public void deserialize(Map<String, Object> entryDataMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verifyKeyCount(Map<String, Object> entryDataMap) {
        return false;
    }

    @Override
    public String getSerializedFileName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode(){
        return this.login.hashCode();
    }

    public String getSalt() {
        return salt;
    }
}
