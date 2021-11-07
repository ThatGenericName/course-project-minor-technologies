package Entities.User;

import Entities.Entry;
import Entities.Listing.JobListing;
import UseCase.FileIO.MalformedDataException;
import UseCase.Security.Security;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    public static final String WATCHED_SEARCH_QUERIES = "watchedSearchQueries";
    public static final String[] KEYS = new String[] {ACCOUNT_NAME, WATCHED_JOB_LISTINGS, LOGIN, HASHED_PASSWORD, WATCHED_SEARCH_QUERIES ,SALT};


    /**
     * Creates a User entry with no data for Deserialization.
     */
    public User(){
        super();
    }

    /**
     * A demo constructor, this should never be called outside of demo stuff
     *
     * TODO: Delete this constructor
     * @param login
     */
    public User(String login){
        this("demo", login, null, null);
        byte[] saltArr = Security.generateSalt();
        String salt = Security.toHex(saltArr);
        String hashedPassword = Security.toHex(Security.generateHash("demo", saltArr));

        updateData(HASHED_PASSWORD, hashedPassword);
        updateData(SALT, salt);
    }


    /**
     * Creates a new User with the provided data.
     *
     * @param accountName
     * @param login
     * @param passwordHash
     * @param salt
     */
    public User(String accountName, String login, String passwordHash, String salt){
        super();
        addData(ACCOUNT_NAME, accountName);
        addData(LOGIN, login);
        addData(HASHED_PASSWORD, passwordHash);
        addData(SALT, salt);
        addData(WATCHED_JOB_LISTINGS, new HashSet<>());
        addData(WATCHED_SEARCH_QUERIES, new HashSet<>());
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

        HashMap<String, Object> preSerializedData = new HashMap<>();

        HashSet<JobListing> watchedJobListings = getWatchedListings();
        String[] watchedJobListingUUID = new String[watchedJobListings.size()];

        int i = 0;
        for (JobListing listing:
             watchedJobListings) {
            watchedJobListingUUID[i] = listing.getUUID();
            i++;
        }

        for (String key:
             KEYS) {
            Object data = getData(key);
            if (key.equals(WATCHED_JOB_LISTINGS)){
                data = watchedJobListingUUID;
            }
            else if (key.equals(WATCHED_SEARCH_QUERIES)){
                data = getSerializedSearchQueries();
            }
            preSerializedData.put(key, data);
        }

        return preSerializedData;
    }

    private ArrayList<HashMap<String, Object>> getSerializedSearchQueries(){
        ArrayList<HashMap<String, Object>> queryPreSerialized = new ArrayList<>();

        HashSet<Entry> querySet = (HashSet<Entry>) getData(WATCHED_SEARCH_QUERIES);

        for (Entry entry:
                querySet ) {
            HashMap<String, Object> preSerializedQueryData = entry.serialize();
            queryPreSerialized.add(preSerializedQueryData);
        }
        return queryPreSerialized;
    }

    @Override
    public synchronized void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

        if (entryDataMap.keySet().size() != KEYS.length){
            throw new MalformedDataException(MALFORMED_EXCEPTION_MSG);
        }

        String accountName = (String) entryDataMap.get(ACCOUNT_NAME);
        String login = (String) entryDataMap.get(LOGIN);
        String salt = (String) entryDataMap.get(SALT);
        String hashedPassword = (String) entryDataMap.get(HASHED_PASSWORD);
        HashSet<Entry> watchedSearchQueries = (HashSet<Entry>) entryDataMap.get(WATCHED_SEARCH_QUERIES);

        //TODO: Unjank this
        ArrayList<String> uuids = (ArrayList<String>) entryDataMap.get(WATCHED_JOB_LISTINGS);
        String[] uuidsArray = new String[uuids.size()];
        this.watchedListingsUUID = uuids.toArray(uuidsArray);

        addData(ACCOUNT_NAME, accountName);
        addData(LOGIN, login);
        addData(SALT, salt);
        addData(HASHED_PASSWORD, hashedPassword);
        addData(WATCHED_SEARCH_QUERIES, watchedSearchQueries);
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
