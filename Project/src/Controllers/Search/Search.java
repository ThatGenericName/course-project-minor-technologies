package Controllers.Search;

import Controllers.LocalCache.LocalCache;
import Entities.IEntry;
import Entities.SearchQuery.SearchQuery;
import Entities.Listing.Listing;
import Entities.Listing.ListingType;
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
    public static HashMap<String, ArrayList<Listing>> searchLocalCache(SearchQuery query){

        ArrayList<Listing> l1 = new ArrayList<>();
        ArrayList<Listing> l2 = new ArrayList<>();
        ArrayList<Listing> l3 = new ArrayList<>();
        ArrayList<Listing> l4 = new ArrayList<>();
        HashMap<String, ArrayList<Listing>> sorted_list = new HashMap<>();

        for(IEntry item: Main.getLocalCache().getListingDB()) {
            if (item instanceof Listing) {
                Listing listing = (Listing) item;
                if (search_terms(query, listing.getTitle()) || Objects.equals(listing.getTitle(), "") || search_terms(query, listing.getDescription()))
                    l1.add(listing);
                if (query.getLocation().equalsIgnoreCase(listing.getLocation()) ||
                        Objects.equals(listing.getLocation(), ""))
                    l2.add(listing);
                if (query.getJobType() == listing.getJobType())
                    l3.add(listing);
                if(query.getDateTime().getYear() == listing.getDateTime().getYear()){
                    if(query.getDateTime().getMonthValue() < listing.getDateTime().getMonthValue())
                        l4.add(listing);
                    if(query.getDateTime().getMonthValue() == listing.getDateTime().getMonthValue() &&
                            query.getDateTime().getDayOfMonth() <= listing.getDateTime().getDayOfMonth())
                        l4.add(listing);
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
    public static Listing[] searchWeb(SearchQuery query){
        throw new java.lang.UnsupportedOperationException();
    }
}
