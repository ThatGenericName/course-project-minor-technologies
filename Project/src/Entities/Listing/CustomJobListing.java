package Entities.Listing;

import UseCase.FileIO.MalformedDataException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomJobListing extends JobListing {

    private String origin;

    private static final String ORIGIN = "origin";

    /**
     * Creates a JobListing from a Map, containing the data for a Job Listing
     * Assumes that the Map Data integrity has already been checked.
     *
     * @param listingDataMap - A map containing the data for a job listing.
     */
    public CustomJobListing(Map<String, Object> listingDataMap) throws MalformedDataException{
        super(listingDataMap);
        deserialize(listingDataMap);
    }

    public CustomJobListing(String title, String location, int pay, JobType jobType, String qualifications,
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

        serialized.put(ORIGIN, origin);

        return serialized;
    }

    @Override
    public void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException {
        super.deserialize(entryDataMap);
        if (entryDataMap.size() != KEY_COUNT + 1){
            throw new MalformedDataException(MALFORMED_EXCEPTION_MSG);
        }
        this.origin = (String) entryDataMap.get(ORIGIN);
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

        public CustomJobListing build() {
            return new CustomJobListing(title, location, pay, jobType, qualifications, requirements, applicationRequirements,
                    description, origin);
        }
    }
}
