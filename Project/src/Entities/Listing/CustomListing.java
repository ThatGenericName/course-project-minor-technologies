package Entities.Listing;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class CustomListing extends Listing {

    private String origin;


    public CustomListing(JSONObject jsonData) throws IOException {
        super(jsonData);
        fromJson(jsonData);
    }

    public CustomListing(String title, String location, int pay, JobType jobType, String qualifications,
                         String requirements, String applicationRequirements, String description, String origin){
        super(title, location, pay, jobType, qualifications, requirements, applicationRequirements, description);

        this.setListingType(ListingType.CUSTOM);
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public HashMap<String, Object> serialize() {

        HashMap<String, Object> serialized = super.serialize();

        serialized.put("origin", origin);

        return serialized;
    }

    /**
     * Loads JSON data into the object. Returns true if successfully loaded, otherwise returns false.
     *
     *
     * @param jsonData - a JSON Object representing the data of a listing. From JSON object
     * @return boolean - true if successfully loaded, otherwise returns false.
     */

    @Override
    public boolean fromJson(JSONObject jsonData) throws IOException {
        super.fromJson(jsonData);
        Set<String> keys = jsonData.keySet();

        if (keys.contains("origin")){
            this.origin = (String) jsonData.get("origin");
            return true;
        }
        else{
            return false;
        }
    }

    public CustomListingBuilder customListingBuilder(){
        return new CustomListingBuilder();
    }

    public static class CustomListingBuilder {
        private String title;
        private String location;
        private int pay;
        private JobType jobType;
        private String qualifications;
        private String requirements;
        private String applicationRequirements;
        private String description;
        private String origin;


        public CustomListingBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public CustomListingBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public CustomListingBuilder setPay(int pay) {
            this.pay = pay;
            return this;
        }

        public CustomListingBuilder setJobType(JobType jobType) {
            this.jobType = jobType;
            return this;
        }

        public CustomListingBuilder setQualifications(String qualifications) {
            this.qualifications = qualifications;
            return this;
        }

        public CustomListingBuilder setRequirements(String requirements) {
            this.requirements = requirements;
            return this;
        }

        public CustomListingBuilder setApplicationRequirements(String applicationRequirements) {
            this.applicationRequirements = applicationRequirements;
            return this;
        }

        public CustomListingBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CustomListingBuilder setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public CustomListing build() {
            return new CustomListing(title, location, pay, jobType, qualifications, requirements, applicationRequirements,
                    description, origin);
        }
    }
}
