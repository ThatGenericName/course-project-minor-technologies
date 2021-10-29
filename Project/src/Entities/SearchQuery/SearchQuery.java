package Entities.SearchQuery;

import Entities.IEntry;
import Entities.Listing.JobType;
import UseCase.FileIO.MalformedDataException;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public HashMap<String, Object> serialize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deserialize(Map<String, Object> entryDataMap) throws MalformedDataException{
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSerializedFileName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean verifyKeyCount(Map<String, Object> entryDataMap){
        throw new UnsupportedOperationException();
    }
}
