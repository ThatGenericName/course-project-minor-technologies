package Entities.Listing;

import Entities.Entry;
import UseCase.FileIO.MalformedDataException;

import java.time.LocalDateTime;
import java.util.*;

public abstract class JobListing extends Entry {

    public static final String UID = "UUID";
    public static final String LISTING_TYPE = "listingType";
    public static final String TITLE = "title";
    public static final String LOCATION = "location";
    public static final String PAY = "pay";
    public static final String JOB_TYPE = "jobType";
    public static final String QUALIFICATIONS = "qualifications";
    public static final String REQUIREMENTS = "requirements";
    public static final String APPLICATION_REQUIREMENTS = "applicationReq";
    public static final String DESCRIPTION = "description";
    public static final String LISTING_DATE = "listingDate";
    public static final String CROSS_PLATFORM_DUPLICATES = "crossPlatformDuplicates";

    public static final String[] KEYS = new String[] {UID, LISTING_DATE, TITLE, LOCATION, PAY, JOB_TYPE, QUALIFICATIONS, LISTING_TYPE, REQUIREMENTS, APPLICATION_REQUIREMENTS, DESCRIPTION, CROSS_PLATFORM_DUPLICATES};

    private boolean saved;
    private ArrayList<String> cpdUUIDS; // UUIDS of cross platform duplicates.

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public ArrayList<String> getCpdUUIDS(){
        return cpdUUIDS;
    }

    @Override
    public HashMap<String, Object> serialize() {
        HashMap<String, Object> serializeDataMap = new HashMap<>();

        for (String key:
             KEYS) {

            if (Objects.equals(key, CROSS_PLATFORM_DUPLICATES)){
                serializeDataMap.put(key, getCPDUUIDS());
            }
            else{
                serializeDataMap.put(key, getData(key));
            }
        }
        return serializeDataMap;
    }

    @Override
    public void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {

        for (String key:
             KEYS) {
            if (!entryDataMap.containsKey(key)){
                throw new MalformedDataException(Entry.MALFORMED_EXCEPTION_MSG);
            }

            //TODO: maybe this switch can be simplified somehow.
            switch (key){
                case LISTING_DATE:
                    addData(key, parseDateTime(entryDataMap.get(key)));
                    break;
                case CROSS_PLATFORM_DUPLICATES:
                    ArrayList<JobListing> cpdList = new ArrayList<>();
                    addData(key, cpdList);
                    cpdUUIDS = new ArrayList<>();
                    break;
                case LISTING_TYPE:
                    ListingType lt = ListingType.valueOf((String) entryDataMap.get("listingType"));
                    addData(key, lt);
                case JOB_TYPE:
                    JobType jt = JobType.valueOf((String) entryDataMap.get("jobType"));
                    addData(key, jt);
                case UID:
                    if (entryDataMap.get(UID) == null){
                        addData(key, UUID.randomUUID().toString());
                    }
                    else{
                        addData(key, entryDataMap.get(UID));
                    }
                default:
                    addData(key, entryDataMap.get(key));
            }
        }
    }

    private LocalDateTime parseDateTime(Object dateString){
        if (dateString instanceof String){
            return LocalDateTime.parse((String) dateString);
        }
        if (dateString instanceof LocalDateTime){
            return (LocalDateTime) dateString;
        }
        else{
            return LocalDateTime.now();
        }
    }

    private String[] getCPDUUIDS(){
        Object cpdsObj = getData(CROSS_PLATFORM_DUPLICATES);
        ArrayList<JobListing> cpdsList;
        if (cpdsObj instanceof ArrayList){
            cpdsList = (ArrayList<JobListing>) cpdsObj;
            String[] cpduuids = new String[cpdsList.size()];

            for (int i = 0; i < cpduuids.length; i++) {
                cpduuids[i] = cpdsList.get(i).getUUID();
            }
            return cpduuids;
        }
        return null;
    }

    @Override
    public String getSerializedFileName() {
        return getUUID();
    }

    public String getUUID(){
        if (getData(UID) == null){
            updateData(UID, UUID.randomUUID().toString());
        }
        return (String) getData(UID);
    }

    public ListingType getListingType(){
        return (ListingType) getData(JobListing.LISTING_TYPE);
    }

    public JobType getJobType(){
        return (JobType) getData(JOB_TYPE);
    }

    @Override
    public int hashCode(){
        String rep = getData(TITLE) + ((JobType) getData(JOB_TYPE)).name();
        return rep.hashCode();
    }

    public String getTitle(){
        return (String) getData(TITLE);
    }

    public LocalDateTime getListingDate(){
        return (LocalDateTime) getData(LISTING_DATE);
    }
}
