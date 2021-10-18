package Entities.Listing;

import java.io.IOException;
import java.time.*;
import java.util.*;

import org.json.*;

public abstract class Listing {

    /*
        placeholder types for some of these variables, I think for a some of these, such as job type, listing type
        enums should be used instead,

        ListingDate should also just use a time/date object (if java has one).

     */

    private String title;
    private String location;
    private int pay;
    private JobType jobType;
    private String qualifications;
    private String requirements;
    private String applicationRequirements;
    private String description;
    private int UID; // for now this will just be the title + type hashed
    private boolean saved;
    private LocalDateTime listingDate;
    private ArrayList<Listing> crossPlatformDuplicates;
    private ListingType listingType;
    private int[] CPDUIDs;

    public void setCrossPlatformDuplicates(ArrayList<Listing> crossPlatformDuplicates) {
        this.crossPlatformDuplicates = crossPlatformDuplicates;
    }

    public ListingType getListingType() {
        return listingType;
    }

    public void setListingType(ListingType listingType) {
        this.listingType = listingType;
    }

    /**
     *
     */
    public Listing(JSONObject jsonData) throws IOException {
        if (!fromJson(jsonData)){
            throw new IOException("JSON data missing keys");
        }

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

    public Listing(String title, String location, int pay, JobType jobType, String qualifications, String requirements,
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

        this.UID = this.hashCode(); // UID generated as it is a hash of this object
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

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
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

    public ArrayList<Listing> getCrossPlatformDuplicates() {
        return crossPlatformDuplicates;
    }

    /**
     * creates a JSON representation of this listing
     *
     * @return a JSONObject object
     */
    public JSONObject toJson(){

        JSONObject jsonData = new JSONObject();

        jsonData.put("UID", UID);
        jsonData.put("listingType", listingType);
        jsonData.put("title", title);
        jsonData.put("location", location);
        jsonData.put("pay", pay);
        jsonData.put("jobType", jobType);
        jsonData.put("qualifications", qualifications);
        jsonData.put("requirements", requirements);
        jsonData.put("applicationReq", applicationRequirements);
        jsonData.put("description", description);
        jsonData.put("saved", saved);
        jsonData.put("listingDate", listingDate);
        jsonData.put("crossPlatformDuplicates", getCPDIDs());

        return jsonData;
    }

    /**
     * Gets the UIDs of cross platform duplicates.
     *
     *
     * @return an integer array of the UIDs of any listings in crossPlatformDuplicates
     */
    public int[] getCPDIDs(){
        int[] cpdids = new int[crossPlatformDuplicates.size()];

        for (int i = 0; i < crossPlatformDuplicates.size(); i++) {
            Listing dup = crossPlatformDuplicates.get(i);
            cpdids[i] = dup.getUID();
        }

        return cpdids;
    }

    /*
     * go to https://www.javadoc.io/doc/org.json/json/latest/index.html for documentation of org.json, which is the
     * JSON library we will use for this project
     */

    /**
     * Reads JSON data and parses it into data for this object
     * @param jsonData - a JSON Object representing the data of a listing. From JSON object
     * @return - true if the JSON data loaded correctly into the object, false if the data was unable to be
     * loaded into the object for any reason
     * @throws IOException - if there are any missing keys, an IOException will be thrown
     */
    //TODO integrate the boolean return instead of throwing an IOException as it's significantly faster.
    // TODO: these integrity checks should probably be done in Controllers.DataFormat as then an IO exception would be unnecessary
    public boolean fromJson(JSONObject jsonData) throws IOException {

        this.listingType = ListingType.valueOf((String)jsonData.get("listingType"));
        this.title = (String) jsonData.get("title");
        this.location = (String)jsonData.get("location");
        this.pay = (int) jsonData.get("pay");
        this.jobType = JobType.valueOf((String) jsonData.get("jobType")) ;
        this.qualifications = (String) jsonData.get("qualifications");
        this.requirements = (String) jsonData.get("requirements");
        this.applicationRequirements = (String) jsonData.get("applicationReq");
        this.description = (String) jsonData.get("description");
        this.saved = (boolean) jsonData.get("saved");
        if (!JSONObject.NULL.equals(jsonData.get("listingDate"))){
            this.listingDate = LocalDateTime.parse((String)jsonData.get("listingDate")) ;
        }
        else{
            this.listingDate = LocalDateTime.now();
        }
        this.crossPlatformDuplicates = new ArrayList<>();
        // a list of Cross Platform Duplicates can only be made once all listings have been loaded, therefore initially
        // only an empty list is made. Instead, the array of UIDs is stored as a variable

        JSONArray jarray = (JSONArray) jsonData.get("crossPlatformDuplicates");
        CPDUIDs = new int[jarray.length()];
        for (int i = 0; i < jarray.length(); i++) {
            CPDUIDs[i] = jarray.getInt(i);
        }
        if (!JSONObject.NULL.equals(jsonData.get("UID"))){
            this.UID = (int)jsonData.get("UID");
        }
        else{
            this.UID = this.hashCode();
        }

        return true;
    }

    @Override
    public boolean equals(Object other){
        String rep = this.title + this.jobType.name();

        if (!(other instanceof Listing)){
            return false;
        }
        else{
            Listing otherListing = (Listing)other;
            String oth = otherListing.getTitle() + otherListing.getJobType().name();
            return rep.equals(oth);
        }
    }

    @Override
    public int hashCode(){
        String rep = this.title + this.jobType.name();
        return rep.hashCode();
    }

    public static boolean verifyJsonIntegrity(JSONObject jsonData){
        throw new UnsupportedOperationException();
    }
}
