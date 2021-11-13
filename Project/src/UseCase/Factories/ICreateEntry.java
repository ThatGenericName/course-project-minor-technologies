package UseCase.Factories;

import Entities.Entry;
import Entities.Listing.JobListing;
import Entities.SearchQuery.SearchQuery;
import Entities.User.User;
import UseCase.Factories.JobListingFactory.ICreateJobListing;
import UseCase.FileIO.EntryDataMapTypeCaster;
import UseCase.FileIO.MalformedDataException;
import UseCase.SearchQuery.CreateSearchQuery;
import UseCase.Factories.UserFactory.CreateUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public interface ICreateEntry {

    Entry create(Map<String, Object> entryDataMap) throws IOException, MalformedDataException;

    static Entry createEntry(Map<String, Object> entryDataMap) throws MalformedDataException{
        return createEntry(entryDataMap, true);
    }

    static Entry createEntry(Map<String, Object> entryDataMap, boolean parseData) throws MalformedDataException {

        if (parseData){
            (new EntryDataMapTypeCaster()).convertValueTypes(entryDataMap);
        }

        if (entryDataMap.containsKey(User.LOGIN)){
            return new CreateUser().create(entryDataMap);
        }
        else if (entryDataMap.containsKey(JobListing.LISTING_TYPE)){
            return ICreateJobListing.createListing(entryDataMap);
        }
        else if (entryDataMap.containsKey(SearchQuery.SEARCH_TERMS)){
            return new CreateSearchQuery().create(entryDataMap);
        }
        else{
            throw new MalformedDataException("Cannot Identify Entry Type");
        }
    }

    ArrayList<String> verifyMapIntegrity(Map<String, Object> entryDataMap);

    static String missingKeyInfo(ArrayList<String> keys, String type){
        StringBuilder msg = new StringBuilder("JSON data for {" + type + "} listing missing keys:");
        for (String key :
                keys) {
            msg.append(" {").append(key).append("},");
        }

        return msg.toString();
    }

    static LocalDateTime parseDateTime(Object dateString){
        if (dateString instanceof String){
            return LocalDateTime.parse((String) dateString);
        }
        if (dateString instanceof LocalDateTime){
            return (LocalDateTime) dateString;
        }
        else{
            return LocalDateTime.now();
        }
    }
}
