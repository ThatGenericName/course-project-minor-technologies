import java.util.ArrayList;
import java.util.HashMap;

public class Search {

    //TODO: Complete methods
    /**
     * Searches the LocalCache (files already loaded by the program) using query.
     *
     *
     * @param query The search query
     * @return Listing[] - returns an array of Search Listings from the LocalCache according to the search query
     */
    public static HashMap<String, ArrayList<Listing>> searchLocalCache(SearchQuery query){

        ArrayList<Listing> new_list = new ArrayList<>(LocalCache.listingsMap.get(ListingType.CUSTOM));
        new_list.addAll(LocalCache.listingsMap.get(ListingType.LINKED_IN));
        new_list.addAll(LocalCache.listingsMap.get(ListingType.INDEED));

        ArrayList<Listing> l1 = new ArrayList<>();
        ArrayList<Listing> l2 = new ArrayList<>();
        ArrayList<Listing> l3 = new ArrayList<>();
        HashMap<String, ArrayList<Listing>> sorted_list = new HashMap<>();

        for(Listing list: new_list){
            if(search_terms(query, list.getTitle()))
                l1.add(list);
            if(query.getLocation().equalsIgnoreCase(list.getLocation()))
                l2.add(list);
            if(query.getJobType() == list.getJobType())
                l3.add(list);
        }
        sorted_list.put("terms",l1);
        sorted_list.put("location",l2);
        sorted_list.put("jobtype",l3);
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
     * @return Listing[] - returns an array of Search Listings from the API/Scraper according to the search query
     */
    public static Listing[] searchWeb(SearchQuery query){
        throw new java.lang.UnsupportedOperationException();
    }
}
