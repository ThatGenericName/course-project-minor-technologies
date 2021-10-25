package Controllers.Search;

import Entities.IEntry;
import Entities.Listing.JobListing;
import Entities.SearchQuery.SearchQuery;
import Main.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Search {

    //TODO: Complete methods
    /**
     * Searches the Controllers.LocalCache.LocalCache (files already loaded by the program) using query.
     *
     *
     * @param query The search query
     * @return Entities.Listings.Listing[] - returns an array of Controllers.Search.Search Listings from the Controllers.LocalCache.LocalCache according to the search query
     */
    public static HashMap<String, ArrayList<JobListing>> searchLocalCache(SearchQuery query){

        ArrayList<JobListing> l1 = new ArrayList<>();
        ArrayList<JobListing> l2 = new ArrayList<>();
        ArrayList<JobListing> l3 = new ArrayList<>();
        ArrayList<JobListing> l4 = new ArrayList<>();
        HashMap<String, ArrayList<JobListing>> sorted_list = new HashMap<>();

        for(IEntry item: Main.getLocalCache().getListingDB()) {
            if (item instanceof JobListing) {
                JobListing jobListing = (JobListing) item;
                if (search_terms(query, jobListing.getTitle()) || Objects.equals(jobListing.getTitle(), "") || search_terms(query, jobListing.getDescription()))
                    l1.add(jobListing);
                if (query.getLocation().equalsIgnoreCase(jobListing.getLocation()) ||
                        Objects.equals(jobListing.getLocation(), ""))
                    l2.add(jobListing);
                if (query.getJobType() == jobListing.getJobType())
                    l3.add(jobListing);
                if(query.getDateTime().getYear() == jobListing.getDateTime().getYear()){
                    if(query.getDateTime().getMonthValue() < jobListing.getDateTime().getMonthValue())
                        l4.add(jobListing);
                    if(query.getDateTime().getMonthValue() == jobListing.getDateTime().getMonthValue() &&
                            query.getDateTime().getDayOfMonth() <= jobListing.getDateTime().getDayOfMonth())
                        l4.add(jobListing);
                }
            }
        }
        sorted_list.put("terms",l1);
        sorted_list.put("location",l2);
        sorted_list.put("jobtype",l3);
        sorted_list.put("DateTime", l4);
        return sorted_list;

    }

    /**
     *
     * @return boolean
     */
    private static boolean search_terms(SearchQuery query, String title){
        title = title.trim();
        String []search_terms = query.getSearchTerms().split(" ");

        int prev_index = 0;
        for(int i =0;i<title.length();i++){
            if(title.charAt(i) == ' ')
            {
                String word = title.substring(prev_index, i);
                word = word.trim();
                prev_index = i + 1;
                if(word.equals(""))
                    continue;
                for (String search_term : search_terms) {
                    if (word.equalsIgnoreCase(search_term))
                        return true;
                }

            }
        }
        return false;
    }

    /**
     * Uses API/Scraper (not yet implemented) to process a search query
     *
     * @param query The search query
     * @return Entities.Listings.Listing[] - returns an array of Controllers.Search.Search Listings from the API/Scraper according to the search query
     */
    public static JobListing[] searchWeb(SearchQuery query){
        throw new java.lang.UnsupportedOperationException();
    }
}
