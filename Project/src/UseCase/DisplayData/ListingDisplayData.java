package UseCase.DisplayData;

import Entities.Listing.Listing;
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

    public ListingDisplayData(Listing listing){
        this.title = listing.getTitle();
        this.location = listing.getLocation();
        this.pay = listing.getPay();
        switch (listing.getJobType()){
            case FULL_TIME:
                this.jobType = "Full Time";
                break;
            case PART_TIME:
                this.jobType = "Job Type";
                break;
        }
        this.qualifications = listing.getQualifications();
        this.requirements = listing.getRequirements();
        this.applicationRequirements = listing.getApplicationRequirements();
        this.description = listing.getDescription();
        this.listingDate = dateParse(listing.getDateTime());

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
