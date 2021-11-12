package Entities.User;

import Entities.Entry;
import Entities.Listing.JobListing;
import Main.Main;
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
    public static final String SKILL_SET = "skillSet"; // ArrayList<String>
    public static final String REL_WORK_EXP = "relWorkExp"; // ArrayList<Experience>
    public static final String UREL_WORK_EXP = "urelWorkExp"; // ArrayList<Experience>
    public static final String LEADERSHIP = "leadership"; // ArrayList<Experience>
    public static final String LOCATION = "location"; // String
    public static final String AWARDS = "awards"; // ArrayList<String>
    public static final String INCENTIVE = "incentive"; // ArrayList<String>
    public static final String[] KEYS = new String[] {ACCOUNT_NAME, WATCHED_JOB_LISTINGS, LOGIN, HASHED_PASSWORD,
            WATCHED_SEARCH_QUERIES ,SALT, SKILL_SET, REL_WORK_EXP, UREL_WORK_EXP, LEADERSHIP, LOCATION, AWARDS,
            INCENTIVE};


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

    /**
     * Watched Listings in a user's profile are stored just as UUIDs. This method returns the entries associated
     * with the UUIDs
     *
     * @return
     */
    public HashSet<JobListing> getWatchedListings() {
        ArrayList<String> uuids = (ArrayList<String>) getData(WATCHED_JOB_LISTINGS);
        HashSet<JobListing> watchedListings = new HashSet<>();

        for (String uuid:
             uuids) {
            JobListing listing = Main.getLocalCache().getListingFromUUID(uuid);
            watchedListings.add(listing);
        }

        return watchedListings;
    }

    /**
     * Adds a listing to watchedListings. If listing is already in watchedListing, returns false, otherwise returns true.
     *
     * @param jobListing adds this listing to the user's watched listings
     * @return a boolean, false if listing is already in watchedListing, otherwise returns true.
     */
    public boolean addListingToWatch(JobListing jobListing){
        jobListing.setSaved(true);

        ((HashSet<String>) getData(WATCHED_SEARCH_QUERIES)).add(jobListing.getUUID());
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
            switch (key){
                case WATCHED_JOB_LISTINGS:
                    data = watchedJobListingUUID;
                    break;
                case WATCHED_SEARCH_QUERIES:
                    data = getSerializedSearchQueries();
                case REL_WORK_EXP:
                case UREL_WORK_EXP:
                case LEADERSHIP:
                    ArrayList<Entry> dataMaps = (ArrayList<Entry>) getData(key);
                    data = getNestedSerializationData(dataMaps);
            }

            preSerializedData.put(key, data);
        }

        return preSerializedData;
    }

    private ArrayList<HashMap<String, Object>> getSerializedSearchQueries(){

        return getNestedSerializationData((HashSet<Entry>) getData(WATCHED_SEARCH_QUERIES));
    }

    @Override
    public synchronized void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

        if (entryDataMap.keySet().size() != KEYS.length){
            throw new MalformedDataException(MALFORMED_EXCEPTION_MSG);
        }

        for (String key:
             KEYS) {
            Object data = entryDataMap.get(key);
            addData(key, data);
        }
    }

    @Override
    public String getSerializedFileName() {
        return (String) getData(LOGIN);
    }

    // TODO: implement this method.
    @Override
    public synchronized void updateEntry(Map<String, Object> entryDataMap) {
        for (String key:
                KEYS) {
            if (!entryDataMap.containsKey(key)){
                Object data = entryDataMap.get(key);
                updateData(key, data);
            }
        }
    }

    @Override
    public synchronized void updateEntry(Entry entry) {
        Map<String, Object> entryData = entry.serialize();
        updateEntry(entryData);
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
