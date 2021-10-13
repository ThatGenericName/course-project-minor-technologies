import java.time.*;
import java.util.ArrayList;
import java.util.List;
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
    private String jobType;
    private String qualifications;
    private String requirements;
    private String applicationRequirements;
    private String description;
    private int UID; // for now this will just be the title hashed
    private boolean saved;
    private LocalDateTime listingDate;
    private ArrayList<Listing> crossPlatformDuplicates;
    protected ListingType listingType;


    public Listing(){
    }

    public Listing(String title, String location, int pay, String jobType, String qualifications, String requirements,
                   String applicationRequirements, String description){
        this.title = title;
        this.location = location;
        this.pay = pay;
        this.jobType = jobType;
        this.qualifications = qualifications;
        this.requirements = requirements;
        this.applicationRequirements = applicationRequirements;
        this.description = description;
        this.UID = title.hashCode();
        this.saved = false;
        this.listingDate = LocalDateTime.now();
        this.crossPlatformDuplicates = new ArrayList<>();
    }

    // heres a billion getters and setters because java convention is to make everything private

    public String getTitle() {
        return title;
    }

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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
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
     * @return a string in JSON formatting
     */
    public abstract String toJson();

    /**
     * Reads JSON data and parses it into data for this object
     *
     * @param JSON - a JSON Object representing the data of a listing. From JSON object
     * @return boolean - true if the JSON data loaded correctly into the object, false
     * if the data was unable to be loaded into the object for any reason
     *
     * go to https://www.javadoc.io/doc/org.json/json/latest/index.html for documentation of org.json, which is the
     * JSON library we will use for this project
     */
    public abstract boolean FromJson(JSONObject JSON);

}
