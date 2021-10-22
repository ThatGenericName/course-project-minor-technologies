package Entities.Listing;

import org.json.JSONObject;

public class CustomListingBuilder {
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

    public CustomListing createCustomListing() {
        return new CustomListing(title, location, pay, jobType, qualifications, requirements, applicationRequirements,
                description, origin);
    }
}