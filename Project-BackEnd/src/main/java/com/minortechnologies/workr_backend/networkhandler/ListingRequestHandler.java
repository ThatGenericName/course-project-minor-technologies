package com.minortechnologies.workr_backend.networkhandler;

import com.minortechnologies.workr_backend.controllers.localcache.LocalCache;
import com.minortechnologies.workr_backend.controllers.search.Search;
import com.minortechnologies.workr_backend.entities.Entry;
import com.minortechnologies.workr_backend.entities.listing.JobListing;
import com.minortechnologies.workr_backend.entities.searchquery.SearchQuery;
import com.minortechnologies.workr_backend.usecase.factories.ICreateEntry;
import com.minortechnologies.workr_backend.usecase.fileio.MalformedDataException;

import java.util.ArrayList;
import java.util.HashMap;

public class ListingRequestHandler {

    public static HashMap<String, Object> getListing(String uuid){
        LocalCache lc = Application.getLocalCache();
        Entry listing = lc.getListingFromUUID(uuid);

        return listing.serialize();
    }

    public static ArrayList<HashMap<String, Object>> getListing(String[] uuids){
        ArrayList<HashMap<String, Object>> listings = new ArrayList<>();
        for (String uuid:
             uuids) {
            listings.add(getListing(uuid));
        }
        return listings;
    }

    public static ArrayList<HashMap<String, Object>> searchListings(HashMap<String, Object> searchQuery){
        try {
            Entry query = ICreateEntry.createEntry(searchQuery);
            if (query instanceof SearchQuery){
                ArrayList<Entry> initResults = Search.searchWeb((SearchQuery) query);
                ArrayList<HashMap<String, Object>> results = new ArrayList<>();
                for (Entry entry:
                     initResults) {
                    results.add(entry.serialize());
                }
                return results;
            }
        } catch (MalformedDataException e) {
            e.printStackTrace();

        }
        return null;
    }

}
