package Entities.SearchQuery;

import Entities.IEntry;
import Entities.Listing.JobType;

import java.time.*;

/**
 * SearchQuery object represents a search query that a user would make. These are also stored in the User class for
 * the automatic update function.
 *
 * This is basically a struct in c, c++, c#, etc.
 */

public class SearchQuery implements IEntry {

    public SearchQuery(String searchTerms, String location, LocalDateTime dateTime, JobType jobType){
        this.searchTerms = searchTerms;
        this.location = location;
        this.dateTime = dateTime;
        this.jobType = jobType;
    }

    private final String searchTerms;
    private final String location;
    private final LocalDateTime dateTime;
    private final JobType jobType;

    public String getSearchTerms() {
        return searchTerms;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public JobType getJobType() {
        return jobType;
    }
}
