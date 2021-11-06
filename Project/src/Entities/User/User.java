package Entities.User;

import Entities.Entry;
import Entities.Listing.JobListing;
import UseCase.Security.Security;
import org.apache.commons.lang3.SerializationUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class User extends Entry {

    private String[] watchedListingsUUID;


    // Some constants for keys

    public static final String ACCOUNT_NAME = "accountName";
    public static final String WATCHED_JOB_LISTINGS = "watchedJobListings";
    public static final String LOGIN = "login";
    public static final String HASHED_PASSWORD = "hashedPassword";
    public static final String SALT = "salt";
    public static final String[] Keys = new String[] {ACCOUNT_NAME, WATCHED_JOB_LISTINGS, LOGIN, HASHED_PASSWORD, SALT};

    /**
     * This is a debug method. You should never be using this method.
     *
     * @return the User's hashed password.
     */

    public User(String login){
        this("demo", login, null, null);
        byte[] saltArr = Security.generateSalt();
        String salt = Security.toHex(saltArr);
        String hashedPassword = Security.toHex(Security.generateHash("demo", saltArr));

        updateData(HASHED_PASSWORD, hashedPassword);
        updateData(SALT, salt);
    }

    public User(String accountName, String login, String passwordHash, String salt){
        super();
        addData(ACCOUNT_NAME, accountName);
        addData(LOGIN, login);
        addData(HASHED_PASSWORD, passwordHash);
        addData(SALT, salt);
        addData(WATCHED_JOB_LISTINGS, new HashSet<>());
    }

    public boolean matchPassword(String password){
        return password.equals(getData(HASHED_PASSWORD));
    }

    public HashSet<JobListing> getWatchedListings() {
        Object wjl = getData(WATCHED_JOB_LISTINGS);
        return wjl instanceof HashSet<?> ? (HashSet<JobListing>) getData(WATCHED_JOB_LISTINGS) : null; // Apparently there is nothing you can do for unchecked cast warnings
    }

    /**
     * Adds a listing to watchedListings. If listing is already in watchedListing, returns false, otherwise returns true.
     *
     * @param jobListing adds this listing to the user's watched listings
     * @return a boolean, false if listing is already in watchedListing, otherwise returns true.
     */
    public boolean addListingToWatch(JobListing jobListing){
        jobListing.setSaved(true);
        return !getWatchedListings().add(jobListing);
    }

    @Override
    public synchronized HashMap<String, Object> serialize() {

        HashMap<String, Object> preSerializedData = SerializationUtils.clone(getEntryData());

        HashSet<JobListing> watchedJobListings = getWatchedListings();

        String[] watchedJobListingUUID = new String[watchedJobListings.size()];

        int i = 0;
        for (JobListing listing:
             watchedJobListings) {
            watchedJobListingUUID[i] = listing.getUUID();
            i++;
        }

        preSerializedData.put("watchedJobListings", watchedJobListingUUID);

        return preSerializedData;
    }

    @Override
    public synchronized void deserialize(Map<String, Object> entryDataMap) {
        String accountName = (String) entryDataMap.get(ACCOUNT_NAME);
        String login = (String) entryDataMap.get(LOGIN);
        String salt = (String) entryDataMap.get(SALT);
        String hashedPassword = (String) entryDataMap.get(HASHED_PASSWORD);
        this.watchedListingsUUID = (String[]) entryDataMap.get(WATCHED_JOB_LISTINGS);

        addData(ACCOUNT_NAME, accountName);
        addData(LOGIN, login);
        addData(SALT, salt);
        addData(HASHED_PASSWORD, hashedPassword);
    }


    public boolean verifyKeyCount(Map<String, Object> entryDataMap) {
        return entryDataMap.size() == 4;
    }

    @Override
    public String getSerializedFileName() {
        return (String) getData(LOGIN);
    }

    @Override
    public int hashCode(){
        return getData(LOGIN).hashCode();
    }

    public String getSalt() {
        return (String) getData(SALT);
    }

    public String[] getWatchedListingsUUID() {
        HashSet<JobListing> watchedListings = getWatchedListings();
        String[] uuids = new String[watchedListings.size()];
        int i = 0;
        for (JobListing listing:
             watchedListings) {
            uuids[i] = listing.getUUID();
        }
        return uuids;
    }
}
