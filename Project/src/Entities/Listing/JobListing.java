package Entities.Listing;

import java.time.*;
import java.util.*;

import Entities.IEntry;
import UseCase.FileIO.MalformedDataException;
import org.json.*;

public abstract class JobListing implements IEntry {

    /*
        placeholder types for some of these variables, I think for a some of these, such as job type, listing type
        enums should be used instead,

        ListingDate should also just use a time/date object (if java has one).

     */


    protected static final int KEY_COUNT = 13;
    private String title;
    private String location;
    private int pay;
    private JobType jobType;
    private String qualifications;
    private String requirements;
    private String applicationRequirements;
    private String description;
    private String uuid;
    private boolean saved;
    private LocalDateTime listingDate;
    private ArrayList<JobListing> crossPlatformDuplicates;
    private ListingType listingType;
    private ArrayList<String> CPDUIDs;

    public void setCrossPlatformDuplicates(ArrayList<JobListing> crossPlatformDuplicates) {
        this.crossPlatformDuplicates = crossPlatformDuplicates;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public void setListingType(ListingType listingType) {
        this.listingType = listingType;
    }

    /**
     * creates a JobListing instance from a Map containing the listing data.
     *
     * Assumes listingDataMap is not malformed (and therefore it's integrity has already been
     * checked elsewhere, such as by a factory). A minor check (check key count) is made which
     * throws a MalformedDataException if failed.
     *
     * @param listingDataMap - A Map containing the data for the listing
     * @throws MalformedDataException - if during deserialization, the final integrity check fails.
     */
    public JobListing(Map<String, Object> listingDataMap) throws MalformedDataException{
        deserialize(listingDataMap);
    }

    /**
     * Gets crossplatform duplicates from UIDs.
     *
     * TODO: Implement this.
     * @param UIDs a list of UIDs that are crossplatform duplicates of this listing.
     */
    public void setCPDFromUIDs(int[] UIDs){
        throw new java.lang.UnsupportedOperationException();
    }

    public JobListing(String title, String location, int pay, JobType jobType, String qualifications, String requirements,
                      String applicationRequirements, String description){
        this.title = title;
        this.location = location;
        this.pay = pay;
        this.jobType = jobType;
        this.qualifications = qualifications;
        this.requirements = requirements;
        this.applicationRequirements = applicationRequirements;
        this.description = description;
        this.saved = false;
        this.listingDate = LocalDateTime.now();
        this.crossPlatformDuplicates = new ArrayList<>();

        this.uuid = UUID.randomUUID().toString();
    }

    // heres a billion getters and setters because java convention is to make everything private

    public LocalDateTime getDateTime() {return this.listingDate;}

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getApplicationRequirements() {
        return applicationRequirements;
    }

    public void setApplicationRequirements(String applicationRequirements) {
        this.applicationRequirements = applicationRequirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUUID() {
        if (uuid == null)
        {
            this.uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public LocalDateTime getListingDate() {
        return listingDate;
    }

    public void setListingDate(LocalDateTime listingDate) {
        this.listingDate = listingDate;
    }

    public ArrayList<JobListing> getCrossPlatformDuplicates() {
        return crossPlatformDuplicates;
    }

    /**
     * creates a JSON representation of this listing
     *
     * @return a JSONObject object
     */
    @Override
    public HashMap<String, Object> serialize(){

        HashMap<String, Object> serialized = new HashMap<>();

        serialized.put("UUID", uuid);
        serialized.put("listingType", listingType);
        serialized.put("title", title);
        serialized.put("location", location);
        serialized.put("pay", pay);
        serialized.put("jobType", jobType);
        serialized.put("qualifications", qualifications);
        serialized.put("requirements", requirements);
        serialized.put("applicationReq", applicationRequirements);
        serialized.put("description", description);
        serialized.put("saved", saved);
        serialized.put("listingDate", listingDate);
        serialized.put("crossPlatformDuplicates", getCPDIDs());

        return serialized;
    }

    /**
     * Gets the UIDs of cross platform duplicates.
     *
     *
     * @return an integer array of the UIDs of any listings in crossPlatformDuplicates
     */
    public String[] getCPDIDs(){
        String[] cpdids = new String[crossPlatformDuplicates.size()];

        for (int i = 0; i < crossPlatformDuplicates.size(); i++) {
            JobListing dup = crossPlatformDuplicates.get(i);
            cpdids[i] = dup.getUUID();
        }

        return cpdids;
    }


    //TODO: replace keys for any de/serialization with constants
    /**
     * loads instance variables for a JobListing object from deserialized data (in a form of a map).
     *
     * Precondition: Deserialized Data is not malformed (missing keys, etc.)
     *
     * @param entryDataMap - the deserialized data stored in a Map data type
     */
    @Override
    public void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {
        if (!verifyKeyCount(entryDataMap)){
            throw new MalformedDataException(MALFORMED_EXCEPTION_MSG);
        }
        this.listingType = ListingType.valueOf((String) entryDataMap.get("listingType"));
        this.title = (String) entryDataMap.get("title");
        this.location = (String) entryDataMap.get("location");
        this.pay = (int) entryDataMap.get("pay");
        this.jobType = JobType.valueOf((String) entryDataMap.get("jobType")) ;
        this.qualifications = (String) entryDataMap.get("qualifications");
        this.requirements = (String) entryDataMap.get("requirements");
        this.applicationRequirements = (String) entryDataMap.get("applicationReq");
        this.description = (String) entryDataMap.get("description");
        this.saved = (boolean) entryDataMap.get("saved");
        if (!JSONObject.NULL.equals(entryDataMap.get("listingDate"))){
            this.listingDate = LocalDateTime.parse((String) entryDataMap.get("listingDate")) ;
        }
        else{
            this.listingDate = LocalDateTime.now();
        }
        this.crossPlatformDuplicates = new ArrayList<>();
        // a list of Cross Platform Duplicates can only be made once all listings have been loaded, therefore initially
        // only an empty list is made. Instead, the array of UUIDs is stored as a variable

        CPDUIDs = (ArrayList<String>) entryDataMap.get("crossPlatformDuplicates");

        if (entryDataMap.get("UUID") == null){
            this.uuid = (String) entryDataMap.get("UUID");
        }
        else{
            this.uuid = UUID.randomUUID().toString();
        }
    }

    @Override
    public boolean equals(Object other){
        String rep = this.title + this.jobType.name();

        if (!(other instanceof JobListing)){
            return false;
        }
        else{
            JobListing otherJobListing = (JobListing)other;
            String oth = otherJobListing.getTitle() + otherJobListing.getJobType().name();
            return rep.equals(oth);
        }
    }

    @Override
    public int hashCode(){
        String rep = this.title + this.jobType.name();
        return rep.hashCode();
    }

    @Override
    public String getSerializedFileName(){
        return this.getUUID();
    }

    @Override
    public boolean verifyKeyCount(Map<String, Object> entryDataMap){
        return (entryDataMap.size() >= KEY_COUNT);
    }
}
