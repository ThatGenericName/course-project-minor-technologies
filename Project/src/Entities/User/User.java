package Entities.User;

import Entities.IEntry;
import Entities.Listing.JobListing;
import UseCase.Security.Security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class User implements IEntry {
    private final String accountName;
    private final HashSet<JobListing> watchedJobListings;
    private final String login;
    private String hashedPassword;

    /**
     * This is a debug method. You should never be using this method.
     *
     * @return the User's hashed password.
     */
    public String getHashedPassword(){
        return hashedPassword;
    }

    private String salt;

    public String getAccountName() {
        return accountName;
    }

    public String getLogin(){
        return login;
    }

    public User(String login){
        this("demo", login, null, null);
        byte[] saltArr = Security.generateSalt();
        salt = Security.toHex(saltArr);
        hashedPassword = Security.toHex(Security.generateHash("demo", saltArr));
    }

    public User(String accountName, String login, String passwordHash, String salt){
        this.accountName = accountName;
        watchedJobListings = new HashSet<>();
        this.hashedPassword = passwordHash;
        this.salt = salt;
        this.login = login;
    }

    public boolean matchPassword(String password){
        return password.equals(this.hashedPassword);
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

        HashMap<String, Object> preSerializedData = new HashMap<>();

        preSerializedData.put("accountName", accountName);
        preSerializedData.put("login", login);
        preSerializedData.put("hashedPassword", hashedPassword);


        String[] watchedJobListingUUID = new String[watchedJobListings.size()];

        int i = 0;
        for (JobListing listing:
             watchedJobListings) {
            watchedJobListingUUID[i] = listing.getUUID();
            i++;
        }

        preSerializedData.put("watchedJobListings", watchedJobListingUUID);

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
        return login;
    }

    @Override
    public int hashCode(){
        return this.login.hashCode();
    }

    public String getSalt() {
        return salt;
    }
}
