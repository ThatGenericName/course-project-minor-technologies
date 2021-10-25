package UseCase.DisplayData;

import Entities.Listing.JobListing;
import Entities.Listing.ListingType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ListingDisplayData implements IDisplayData{
    private String title;
    private String location;
    private int pay;
    private String jobType;
    private String qualifications;
    private String requirements;
    private String applicationRequirements;
    private String description;
    private String listingDate;
    private ArrayList<ListingPreviewData> crossPlatformDuplicates;
    private ListingType listingType;

    public ListingDisplayData(JobListing jobListing){
        this.title = jobListing.getTitle();
        this.location = jobListing.getLocation();
        this.pay = jobListing.getPay();
        switch (jobListing.getJobType()){
            case FULL_TIME:
                this.jobType = "Full Time";
                break;
            case PART_TIME:
                this.jobType = "Job Type";
                break;
        }
        this.qualifications = jobListing.getQualifications();
        this.requirements = jobListing.getRequirements();
        this.applicationRequirements = jobListing.getApplicationRequirements();
        this.description = jobListing.getDescription();
        this.listingDate = dateParse(jobListing.getDateTime());

        ArrayList<ListingPreviewData> cpd = new ArrayList<>();
        this.crossPlatformDuplicates = cpd;
    }

    private String dateParse(LocalDateTime dateTime){
        return dateTime.getDayOfWeek() +
                ", " +
                dateTime.getMonth() +
                " " + dateTime.getDayOfMonth() +
                ", " + dateTime.getYear();
    }
}
